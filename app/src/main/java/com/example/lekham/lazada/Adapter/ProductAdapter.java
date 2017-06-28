package com.example.lekham.lazada.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lekham.lazada.Model.ObjectClass.SanPham;
import com.example.lekham.lazada.R;
import com.example.lekham.lazada.Until.ConvertUtils;
import com.example.lekham.lazada.Until.DataPublic;
import com.example.lekham.lazada.Until.Number;

import java.util.List;

/**
 * Created by Le Kham on 6/19/2017.
 */

public class ProductAdapter extends BaseRecyclerViewAdapter<SanPham> {
    private Context mContext;
    private int mWidthCardView;
    private int mModeType = -1;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public ProductAdapter(Context context, OnRecyclerItemClicked onRecyclerItemClicked, List<SanPham> sanPhamList) {
        super(context, onRecyclerItemClicked, sanPhamList);
        mContext = context;
    }

    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_product_grirst, viewGroup, false);
            if (mModeType > 0) {
                if (mModeType == DataPublic.ModeGrist) {
                    view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_product_grirst, viewGroup, false);
                } else {
                    view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_product_list, viewGroup, false);
                }
            }
        return view;
    }

    @Override
    protected void bindView(SanPham item, MyViewHolder viewHolder) {
        if (item != null) {
            if (mModeType == DataPublic.ModeGrist || mModeType == -1) {
                CardView cardView = (CardView) viewHolder.getView(R.id.cardView);
                cardView.setLayoutParams(new RecyclerView.LayoutParams(mWidthCardView > 0 ? ConvertUtils.toPixels(mWidthCardView, mContext) : RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
            }
            TextView txtTitle = (TextView) viewHolder.getView(R.id.txtTitle);
            TextView txtPrice = (TextView) viewHolder.getView(R.id.txtPrice);
            TextView txtPriceSale = (TextView) viewHolder.getView(R.id.txtPriceSale);
            ImageView icon = (ImageView) viewHolder.getView(R.id.imgIcon);
            Glide.with(mContext).load(item.getHINHSPLON())
                    .thumbnail(0.5f)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(icon);
            txtTitle.setText(item.getTENSP());
            txtPrice.setText(Number.formatPrice(item.getGIASP()) + " VND");
        }
    }

    public void setWidthCardView(int widthCardView) {
        mWidthCardView = widthCardView;
    }

    public void setModeType(int mode) {
        mModeType = mode;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }
}
