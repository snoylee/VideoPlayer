package com.vcooline.li.videoplayer.tools.view;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.vcooline.li.videoplayer.R;

public class SectionTitleListView extends ExpandableListView implements
		OnScrollListener, OnGroupClickListener {
	private Context context;
	private OnScrollListenerForOtherUse onScrollListenerForOther;
	private boolean isFeedSettings;
	public SectionTitleListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		registerListener();
	}

	public SectionTitleListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		registerListener();
	}

	public SectionTitleListView(Context context) {
		super(context);
		this.context = context;
		registerListener();
	}
	
	public void setLoadingMoreEnabled(boolean value) {
	}

	@Override
	public boolean setSelectedChild(int groupPosition, int childPosition,
			boolean shouldExpandGroup) {
		// TODO Auto-generated method stub
		return super.setSelectedChild(groupPosition, childPosition, shouldExpandGroup);
	}

	/**
	 * Adapter 接口 . 列表必须实现此接口 .
	 */
	public interface IphoneTreeHeaderAdapter {
		public static final int PINNED_HEADER_GONE = 0;
		public static final int PINNED_HEADER_VISIBLE = 1;
		public static final int PINNED_HEADER_PUSHED_UP = 2;

		/**
		 * 获取 Header 的状态
		 * 
		 * @param groupPosition
		 * @param childPosition
		 * @return 
		 *         PINNED_HEADER_GONE,PINNED_HEADER_VISIBLE,PINNED_HEADER_PUSHED_UP
		 *         其中之一
		 */
		int getTreeHeaderState(int groupPosition, int childPosition);

		/**
		 * 配置 QQHeader, 让 QQHeader 知道显示的内容
		 * 
		 * @param header
		 * @param groupPosition
		 * @param childPosition
		 * @param alpha
		 */
		void configureTreeHeader(View header, int groupPosition,
                                         int childPosition, int alpha);

		/**
		 * 设置组按下的状态
		 * 
		 * @param groupPosition
		 * @param status
		 */
		void onHeadViewClick(int groupPosition, int status);

		/**
		 * 获取组按下的状态
		 * 
		 * @param groupPosition
		 * @return
		 */
		int getHeadViewClickStatus(int groupPosition);

	}

	private static final int MAX_ALPHA = 255;

	private IphoneTreeHeaderAdapter mAdapter;

	/**
	 * 用于在列表头显示的 View,mHeaderViewVisible 为 true 才可见
	 */
	private View mHeaderView;

	/**
	 * 列表头是否可见
	 */
	private boolean mHeaderViewVisible;

	private int mHeaderViewWidth;

	private int mHeaderViewHeight;

	
	
	public boolean ismHeaderViewVisible() {
		return mHeaderViewVisible;
	}

	public void setmHeaderViewVisible(boolean mHeaderViewVisible) {
		this.mHeaderViewVisible = mHeaderViewVisible;
	}

	public void setHeaderView(View view) {
		mHeaderView = view;
		LayoutParams lp = new LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		view.setLayoutParams(lp);
		if (mHeaderView != null) {
			setFadingEdgeLength(0);
		}
		requestLayout();
	}

	private void registerListener() {
		setOnScrollListener(this);
		setOnGroupClickListener(this);
		setGroupIndicator(null);
		setSelector(R.color.transparent);
	}
	
	
	public interface OnScrollListenerForOtherUse{
		public void onScrollStateChanged(AbsListView view, int scrollState);
		public void onScroll(AbsListView view, int firstVisibleItem,
                                     int visibleItemCount, int totalItemCount) ;
	}
	
	

	public void setOnScrollListenerForOther(
			OnScrollListenerForOtherUse onScrollListenerForOther) {
		this.onScrollListenerForOther = onScrollListenerForOther;
	}

	/**
	 * 点击 HeaderView 触发的事件
	 */
	private void headerViewClick() {
//		long packedPosition = getExpandableListPosition(this
//				.getFirstVisiblePosition());
//
//		int groupPosition = ExpandableListView
//				.getPackedPositionGroup(packedPosition);
//
//		if (mAdapter.getHeadViewClickStatus(groupPosition) == 1) {
//			this.collapseGroup(groupPosition);
//			mAdapter.onHeadViewClick(groupPosition, 0);
//		} else {
//			this.expandGroup(groupPosition);
//			mAdapter.onHeadViewClick(groupPosition, 1);
//		}
//
//		this.setSelectedGroup(groupPosition);
	}

	private float mDownX;
	private float mDownY;

	/**
	 * 如果 HeaderView 是可见的 , 此函数用于判断是否点击了 HeaderView, 并对做相应的处理 , 因为 HeaderView
	 * 是画上去的 , 所以设置事件监听是无效的 , 只有自行控制 .
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (mHeaderViewVisible) {
			switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mDownX = ev.getX();
				mDownY = ev.getY();
				if (mDownX <= mHeaderViewWidth && mDownY <= mHeaderViewHeight) {
					return true;
				}
				break;
			case MotionEvent.ACTION_UP:
				float x = ev.getX();
				float y = ev.getY();
				float offsetX = Math.abs(x - mDownX);
				float offsetY = Math.abs(y - mDownY);
				// 如果 HeaderView 是可见的 , 点击在 HeaderView 内 , 那么触发 headerClick()
				if (x <= mHeaderViewWidth && y <= mHeaderViewHeight
						&& offsetX <= mHeaderViewWidth
						&& offsetY <= mHeaderViewHeight) {
					if (mHeaderView != null) {
						headerViewClick();
					}

					return true;
				}
				break;
			default:
				break;
			}
		}

		return super.onTouchEvent(ev);

	}

	@Override
	public void setAdapter(ExpandableListAdapter adapter) {
		super.setAdapter(adapter);
		mAdapter = (IphoneTreeHeaderAdapter) adapter;
	}

	/**
	 * 
	 * 点击了 Group 触发的事件 , 要根据根据当前点击 Group 的状态来
	 */
	@Override
	public boolean onGroupClick(ExpandableListView parent, View v,
			int groupPosition, long id) {
//		if (mAdapter.getHeadViewClickStatus(groupPosition) == 0) {
//			mAdapter.onHeadViewClick(groupPosition, 1);
//			parent.expandGroup(groupPosition);
//			parent.setSelectedGroup(groupPosition);
//
//		} else if (mAdapter.getHeadViewClickStatus(groupPosition) == 1) {
//			mAdapter.onHeadViewClick(groupPosition, 0);
//			parent.collapseGroup(groupPosition);
//		}

		// 返回 true 才可以弹回第一行 , 不知道为什么
		return true;

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		mHeaderView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (mHeaderView != null) {
			measureChild(mHeaderView, widthMeasureSpec, heightMeasureSpec);
			mHeaderViewWidth = mHeaderView.getMeasuredWidth();
			mHeaderViewHeight = mHeaderView.getMeasuredHeight();
		}
	}

	private int mOldState = -1;

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		try {
			super.onLayout(changed, left, top, right, bottom);
			final long flatPostion = getExpandableListPosition(getFirstVisiblePosition());
			final int groupPos = ExpandableListView
					.getPackedPositionGroup(flatPostion);
			final int childPos = ExpandableListView
					.getPackedPositionChild(flatPostion);
			if (mAdapter != null) {
				int state = mAdapter.getTreeHeaderState(groupPos, childPos);
				if (mHeaderView != null && mAdapter != null) {
					mOldState = state;
					mHeaderView.layout(0, 0, mHeaderViewWidth,
							mHeaderViewHeight);
				}
			}
			configureHeaderView(groupPos, childPos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void configureHeaderView(int groupPosition, int childPosition) {
		if (mHeaderView == null || mAdapter == null
				|| ((ExpandableListAdapter) mAdapter).getGroupCount() == 0) {
			return;
		}

		int state = mAdapter.getTreeHeaderState(groupPosition, childPosition);

		switch (state) {
		case IphoneTreeHeaderAdapter.PINNED_HEADER_GONE: {
			mHeaderViewVisible = false;
			break;
		}

		case IphoneTreeHeaderAdapter.PINNED_HEADER_VISIBLE: {
			mAdapter.configureTreeHeader(mHeaderView, groupPosition,
					childPosition, MAX_ALPHA);

			if (mHeaderView.getTop() != 0) {
				mHeaderView.layout(0, 0, mHeaderViewWidth, mHeaderViewHeight);
			}

			mHeaderViewVisible = true;

			break;
		}

		case IphoneTreeHeaderAdapter.PINNED_HEADER_PUSHED_UP: {
			View firstView = getChildAt(0);
			int bottom = firstView.getBottom();

			// intitemHeight = firstView.getHeight();
			int headerHeight = mHeaderView.getHeight();

			int y;

			int alpha;

			if (bottom < headerHeight) {
				y = (bottom - headerHeight);
				alpha = MAX_ALPHA * (headerHeight + y) / headerHeight;
			} else {
				y = 0;
				alpha = MAX_ALPHA;
			}

			mAdapter.configureTreeHeader(mHeaderView, groupPosition,
					childPosition, alpha);

			if (mHeaderView.getTop() != y) {
				mHeaderView.layout(0, y, mHeaderViewWidth, mHeaderViewHeight
						+ y);
			}

			mHeaderViewVisible = true;
			break;
		}
		}
	}

	@Override
	/**
	 * 列表界面更新时调用该方法(如滚动时)
	 */
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (mHeaderViewVisible) {
			// 分组栏是直接绘制到界面中，而不是加入到ViewGroup中
			drawChild(canvas, mHeaderView, getDrawingTime());
			mHeaderView.invalidate();
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		final long flatPos = getExpandableListPosition(firstVisibleItem);
		int groupPosition = ExpandableListView.getPackedPositionGroup(flatPos);
		int childPosition = ExpandableListView.getPackedPositionChild(flatPos);

		configureHeaderView(groupPosition, childPosition);
		
		if(onScrollListenerForOther!=null){
			onScrollListenerForOther.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
		}
		
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if(onScrollListenerForOther!=null){
			onScrollListenerForOther.onScrollStateChanged(view, scrollState);
		}
	}

	public boolean isFeedSettings() {
		return isFeedSettings;
	}

	public void setFeedSettings(boolean isFeedSettings) {
		this.isFeedSettings = isFeedSettings;
	}

}
