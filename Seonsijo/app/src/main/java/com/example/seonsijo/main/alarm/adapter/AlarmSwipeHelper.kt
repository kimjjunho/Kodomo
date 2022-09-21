package com.example.seonsijo.main.alarm.adapter

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.seonsijo.R

class AlarmSwipeHelper(private val recyclerViewAdapter : AlarmAdapter) : ItemTouchHelper.Callback(){

    private var currentPosition : Int? = null
    private var previousPosition : Int? = null
    private var currentDx = 0f
    private var clamp = 0f

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return ItemTouchHelper.Callback.makeMovementFlags(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val fromPos: Int = viewHolder.adapterPosition
        val toPos: Int = target.adapterPosition
        recyclerViewAdapter.swapData(fromPos, toPos)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        viewHolder?.let {
            currentPosition = viewHolder.adapterPosition    // 현재 드래그 또는 스와이프 중인 view 의 position 기억하기
            ItemTouchHelper.Callback.getDefaultUIUtil().onSelected(getView(it))
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        currentDx = 0f                                      // 현재 x 위치 초기화
        previousPosition = viewHolder.adapterPosition       // 드래그 또는 스와이프 동작이 끝난 view의 position 기억하기
        ItemTouchHelper.Callback.getDefaultUIUtil().clearView(getView(viewHolder))
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            val view = getView(viewHolder)
            val isClamped = getTag(viewHolder)
            val newX = clampViewPositionHorizontal(dX, isClamped, isCurrentlyActive)

            if(newX == -clamp){
                getView(viewHolder).animate().translationX(-clamp).setDuration(100L).start()
                return
            }
            currentDx = newX
            ItemTouchHelper.Callback.getDefaultUIUtil()
                .onDraw(c, recyclerView, view, dX, dY, actionState, isCurrentlyActive)
        }
    }

    private fun getView(viewHolder: RecyclerView.ViewHolder): View = viewHolder.itemView.findViewById(
        R.id.swipe_view)
    private fun getTag(viewHolder: RecyclerView.ViewHolder): Boolean = viewHolder.itemView.tag as? Boolean ?: false
    private fun setTag(viewHolder: RecyclerView.ViewHolder, isClamped: Boolean){
        viewHolder.itemView.tag = isClamped
    }
    override fun getSwipeEscapeVelocity(defaultValue: Float): Float = defaultValue * 10
    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        // -clamp 이상 swipe시 isClamped를 true로 변경 아닐시 false로 변경
        setTag(viewHolder, currentDx <= -clamp)
        return 2f
    }

    private fun clampViewPositionHorizontal(
        dX: Float,
        isClamped: Boolean,
        isCurrentlyActive: Boolean
    ) : Float {
        // RIGHT 방향으로 swipe 막기
        val max = 0f

        // 고정할 수 있으면
        val newX = if (isClamped) {
            // 현재 swipe 중이면 swipe되는 영역 제한
            if (isCurrentlyActive)
            // 오른쪽 swipe일 때
                if (dX < 0) dX/3 - clamp
                // 왼쪽 swipe일 때
                else dX - clamp
            // swipe 중이 아니면 고정시키기
            else -clamp
        }
        // 고정할 수 없으면 newX는 스와이프한 만큼
        else dX / 2

        // newX가 0보다 작은지 확인
        return java.lang.Float.min(newX, max)
    }

    fun setClamp(clamp: Float) { this.clamp = clamp }

    fun removePreviousClamp(recyclerView: RecyclerView) {
        // 현재 선택한 view가 이전에 선택한 view와 같으면 패스
        if (currentPosition == previousPosition) return

        // 이전에 선택한 위치의 view 고정 해제
        previousPosition?.let {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(it) ?: return
            getView(viewHolder).animate().x(0f).setDuration(100L).start()
            setTag(viewHolder, false)
            previousPosition = null
        }

    }

}