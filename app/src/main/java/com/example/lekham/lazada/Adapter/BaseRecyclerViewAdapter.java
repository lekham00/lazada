package com.example.lekham.lazada.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lekham.lazada.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Le Kham on 6/15/2017.
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<T> mItems = Collections.emptyList();
    private OnRecyclerItemClicked mOnRecyclerItemClicked;

    protected abstract View createView(Context context, ViewGroup viewGroup, int viewType);

    protected abstract void bindView(T item, MyViewHolder viewHolder);


    public BaseRecyclerViewAdapter(Context context) {
        this(context, null, null);
    }

    public BaseRecyclerViewAdapter(Context context, OnRecyclerItemClicked onRecyclerItemClicked, List<T> items) {
        super();
        mContext = context;
        mOnRecyclerItemClicked = onRecyclerItemClicked;
        mItems = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(createView(parent.getContext(), parent, viewType), mOnRecyclerItemClicked);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        bindView(getItem(position), holder);
    }

    public void setItem(List<T> arrayList) {
        mItems = arrayList;
    }

    public T getItem(int position) {
        return mItems.get(position);
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setClickListener(OnRecyclerItemClicked onRecyclerItemClicked) {
        mOnRecyclerItemClicked = onRecyclerItemClicked;
    }

    public interface OnRecyclerItemClicked {
        void onItemClicked(View view, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Map<Integer, View> mViewMap;
        private OnRecyclerItemClicked mMyOnRecyclerItemClicked;

        public MyViewHolder(View itemView, OnRecyclerItemClicked onRecyclerItemClicked) {
            super(itemView);
            mViewMap = new HashMap<>();
            mViewMap.put(0, itemView);
            mMyOnRecyclerItemClicked = onRecyclerItemClicked;
            if (itemView != null)
                itemView.setOnClickListener(this);
        }

        public void initViewList(int[] idList) {
            for (int id : idList)
                initViewById(id);
        }

        public void initViewById(int id) {
            View view = (getView() != null ? getView().findViewById(id) : null);

            if (view != null)
                mViewMap.put(id, view);
        }

        public View getView() {
            return getView(0);
        }

        public View getView(int id) {
            if (mViewMap.containsKey(id))
                return mViewMap.get(id);
            else
                initViewById(id);

            return mViewMap.get(id);
        }

        @Override
        public void onClick(View v) {
            mMyOnRecyclerItemClicked.onItemClicked(v, getAdapterPosition());
        }
    }
}
