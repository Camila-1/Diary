package com.pchpsky.diary.presentation.components.circularlist

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.verticalDrag
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.data.network.model.Insulin
import com.pchpsky.diary.presentation.components.InsulinMenuItem
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.pchpsky.diary.utils.extensions.toHex
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt
import kotlin.math.sqrt

data class CircularListConfig(
    val contentHeight: Float = 0f,
    val contentWidth: Float = 0f,
    val numItems: Int = 0,
    val visibleItems: Int = 0,
    val circularFraction: Float = 1f,
    val overshootItems: Int = 0,
)

@Stable
interface CircularListState {
    val verticalOffset: Float
    var visible: Boolean
    val firstVisibleItem: Int
    val lastVisibleItem: Int

    suspend fun snapTo(value: Float)
    suspend fun decayTo(velocity: Float, value: Float)
    suspend fun stop()
    fun offsetFor(placeable: Placeable, index: Int): IntOffset
    fun setup(config: CircularListConfig)
}

class CircularListStateImpl(
    currentOffset: Float = 0f,
    override var visible: Boolean = false
) : CircularListState {

    private val animatableOffset = Animatable(currentOffset)
    private var itemHeight = 0f
    private var config = CircularListConfig()
    private var initialOffset = 0f
    private val decayAnimationSpec = FloatSpringSpec(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessLow,
    )

    private val minOffset: Float
        get() = -(config.numItems - 1) * itemHeight

    override val verticalOffset: Float
        get() = animatableOffset.value

    override val firstVisibleItem: Int
        get() = ((-verticalOffset - initialOffset) / itemHeight).toInt().coerceAtLeast(0)

    override val lastVisibleItem: Int
        get() = (((-verticalOffset - initialOffset) / itemHeight).toInt() + config.visibleItems)
            .coerceAtMost(config.numItems - 1)

    override suspend fun snapTo(value: Float) {
        val minOvershoot = -(config.numItems + config.overshootItems) * itemHeight
        val maxOvershoot = config.overshootItems * itemHeight
        animatableOffset.snapTo(value.coerceIn(minOvershoot, maxOvershoot))
    }

    override suspend fun decayTo(velocity: Float, value: Float) {
        val constrainedValue = value.coerceIn(minOffset, 0f).absoluteValue
        val remainder = (constrainedValue / itemHeight) - (constrainedValue / itemHeight).toInt()
        val extra = if (remainder <= 0.5f) 0 else 1
        val target =((constrainedValue / itemHeight).toInt() + extra) * itemHeight
        animatableOffset.animateTo(
            targetValue = -target,
            initialVelocity = velocity,
            animationSpec = decayAnimationSpec,
        )
    }

    override suspend fun stop() {
        animatableOffset.stop()
    }

    override fun setup(config: CircularListConfig) {
        this.config = config
        itemHeight = config.contentHeight / config.visibleItems
        initialOffset = (config.contentHeight - itemHeight) / 2f
    }

    override fun offsetFor(placeable: Placeable, index: Int): IntOffset {
        val maxOffset = config.contentHeight / 2f + itemHeight / 2f
        val y = (verticalOffset + initialOffset + index * itemHeight)
        val deltaFromCenter = (y - initialOffset)
        val radius = config.contentHeight / 2f
        val scaledY = deltaFromCenter.absoluteValue * (config.contentHeight / 2f / maxOffset)
        val x = if (scaledY < radius) {
            config.contentWidth - sqrt((radius * radius - scaledY * scaledY)) * config.circularFraction
        } else {
            config.contentWidth
        }
        return IntOffset(
            x = x.roundToInt() - placeable.width,
            y = y.roundToInt()
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CircularListStateImpl

        if (animatableOffset.value != other.animatableOffset.value) return false
        if (itemHeight != other.itemHeight) return false
        if (config != other.config) return false
        if (initialOffset != other.initialOffset) return false
        if (decayAnimationSpec != other.decayAnimationSpec) return false

        return true
    }

    override fun hashCode(): Int {
        var result = animatableOffset.value.hashCode()
        result = 31 * result + itemHeight.hashCode()
        result = 31 * result + config.hashCode()
        result = 31 * result + initialOffset.hashCode()
        result = 31 * result + decayAnimationSpec.hashCode()
        return result
    }

    companion object {
        val Saver = Saver<CircularListStateImpl, List<Any>>(
            save = { listOf(it.verticalOffset) },
            restore = {
                CircularListStateImpl(it[0] as Float)
            }
        )
    }
}

@Composable
fun rememberCircularListState(): CircularListState {
    val state = rememberSaveable(saver = CircularListStateImpl.Saver) {
        CircularListStateImpl()
    }
    return state
}

@Composable
fun CircularList(
    visibleItems: Int,
    visible: Boolean,
    modifier: Modifier = Modifier,
    state: CircularListState = rememberCircularListState(),
    circularFraction: Float = 1f,
    overshootItems: Int = 3,
    content: @Composable () -> Unit,
) {
    check(visibleItems > 0) { "Visible items must be positive" }
    check(circularFraction > 0f) { "Circular fraction must be positive" }

    if (!visible) return
    Layout(
        modifier = modifier
            .clipToBounds()
            .drag(state)
            .wrapContentWidth(),
        content = content,
    ) { measurables, constraints ->
        val itemHeight = constraints.maxHeight / visibleItems
        val itemConstraints = Constraints.fixedHeight(itemHeight)
        val placeables = measurables.map { measurable -> measurable.measure(itemConstraints) }
        state.setup(
            CircularListConfig(
                contentHeight = constraints.maxHeight.toFloat(),
                contentWidth = constraints.maxWidth.toFloat(),
                numItems = placeables.size,
                visibleItems = visibleItems,
                circularFraction = circularFraction,
                overshootItems = overshootItems,
            )
        )

        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight,
        ) {
            for (i in state.firstVisibleItem..state.lastVisibleItem) {
                placeables[i].placeRelative(state.offsetFor(placeables[i], i))
            }
        }
    }
}

fun Modifier.animatedVisibility(state: CircularListState) = pointerInput(Unit) {
    coroutineScope {
        while (true) {
            val pointerId = awaitPointerEventScope { awaitFirstDown().id }
            awaitPointerEventScope {
                verticalDrag(pointerId) { change ->

                }
            }
        }
    }
}

private fun Modifier.drag(
    state: CircularListState,
) = pointerInput(Unit) {
    val decay = splineBasedDecay<Float>(this)
    coroutineScope {
        while (true) {
            val pointerId = awaitPointerEventScope { awaitFirstDown().id }
            state.stop()
            val tracker = VelocityTracker()
            awaitPointerEventScope {
                verticalDrag(pointerId) { change ->
                    val verticalDragOffset = state.verticalOffset + change.positionChange().y
                    launch {
                        state.snapTo(verticalDragOffset)
                    }
                    tracker.addPosition(change.uptimeMillis, change.position)
                    change.consumePositionChange()
                }
            }
            val velocity = tracker.calculateVelocity().y
            val targetValue = decay.calculateTargetValue(state.verticalOffset, velocity)
            launch {
                state.decayTo(velocity, targetValue)
            }
        }
    }
}

@Composable
@Preview
fun CircularListPreview() {
    DiaryTheme {
        val items = listOf(
            Insulin("id", Color.Yellow .toHex(), "Test Insulin"),
            Insulin("id", Color.Blue.toHex(), "Test Insulin"),
            Insulin("id", Color.Green.toHex(), "Test Insulin"),
            Insulin("id", Color.Yellow .toHex(), "Test Insulin"),
            Insulin("id", Color.Blue.toHex(), "Test Insulin"),
            Insulin("id", Color.Green.toHex(), "Test Insulin"),
            Insulin("id", Color.Yellow .toHex(), "Test Insulin"),
            Insulin("id", Color.Blue.toHex(), "Test Insulin"),
            Insulin("id", Color.Green.toHex(), "Test Insulin"),
            Insulin("id", Color.Yellow .toHex(), "Test Insulin"),
            Insulin("id", Color.Blue.toHex(), "Test Insulin"),
            Insulin("id", Color.Green.toHex(), "Test Insulin")
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularList(
                visibleItems = 5,
                visible = true,
                circularFraction = 1.1f,
                overshootItems = 1,
                modifier = Modifier
                    .height(150.dp)
                    .width(200.dp)
                    .background(Color.Red)
                    .align(
                        Alignment.CenterEnd
                    )
            ) {
                items.forEach {
                    InsulinMenuItem(insulin = it) {}
                }
            }
        }
    }
}

