package com.example.lekham.lazada.Until;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by Le Kham on 6/23/2017.
 */

public class LoadMoreRecyclerView extends RecyclerView.OnScrollListener {

    private RecyclerView.LayoutManager mLayoutManager;
    private int mTotalItemCount;
    private int mLastVisibleItem;
    private final int mVisibleThreshold = 1;
    private boolean mIsLoading = false;

    private LoadMoreRecyclerView.OnScrollListener mOnScrollListener;

    public interface OnScrollListener {
        public void loadMoreData(int sumItem);
    }

    public LoadMoreRecyclerView(RecyclerView.LayoutManager layoutManager, LoadMoreRecyclerView.OnScrollListener onScrollListener) {
        mLayoutManager = layoutManager;
        mOnScrollListener = onScrollListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mTotalItemCount = mLayoutManager.getItemCount();
        if (mLayoutManager instanceof LinearLayoutManager) {
            mLastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        } else if (mLayoutManager instanceof GridLayoutManager) {
            mLastVisibleItem = ((GridLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        }
        if (mTotalItemCount > 0 && mLastVisibleItem > 0) {
            if (!mIsLoading && (mTotalItemCount <= (mLastVisibleItem + mVisibleThreshold))) {
                mOnScrollListener.loadMoreData(mTotalItemCount);
                mIsLoading = true;
            }
        }
    }

    public void setLoaded() {
        mIsLoading = false;
    }
}
