package com.example.lekham.lazada.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lekham.lazada.Model.ObjectClass.ThuongHieu;
import com.example.lekham.lazada.R;

import java.util.List;

/**
 * Created by Le Kham on 6/15/2017.
 */

public class BrandAdapter extends BaseRecyclerViewAdapter<ThuongHieu> {
    public BrandAdapter(Context context, OnRecyclerItemClicked onRecyclerItemClicked, List<ThuongHieu> thuongHieuList) {
        super(context, onRecyclerItemClicked, thuongHieuList);
    }

    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_them_one, viewGroup, false);
        return view;
    }

    @Override
    protected void bindView(ThuongHieu item, MyViewHolder viewHolder) {
        if (item != null) {
            TextView txtTitle = (TextView) viewHolder.getView(R.id.txtTitle);
            ImageView imageIcon = (ImageView) viewHolder.getView(R.id.imgIcon);
            txtTitle.setText(item.getTENTH());
            Glide.with(getContext()).load(item.getHINHTHUONGHIEU())
                    .thumbnail(0.5f)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageIcon);
        }
    }
}
