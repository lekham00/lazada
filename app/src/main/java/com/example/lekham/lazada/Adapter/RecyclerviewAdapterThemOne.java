package com.example.lekham.lazada.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lekham.lazada.Model.ObjectClass.ThuongHieu;
import com.example.lekham.lazada.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Le Kham on 6/14/2017.
 */

public class RecyclerviewAdapterThemOne extends RecyclerView.Adapter<RecyclerviewAdapterThemOne.MyViewHolder> {

    private List<ThuongHieu> mThuongHieuList = new ArrayList<>();
    private Context mContext;

    public RecyclerviewAdapterThemOne(Context context) {
        mContext = context;
    }

    public void setListData(List<ThuongHieu> thuongHieuList) {
        mThuongHieuList = thuongHieuList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_recyclerview_them_one, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ThuongHieu thuongHieu = mThuongHieuList.get(position);
        holder.txtTitle.setText(thuongHieu.getTENTH());
        Glide.with(mContext).load(thuongHieu.getHINHTHUONGHIEU())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageIcon);
    }

    @Override
    public int getItemCount() {
        return mThuongHieuList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private ImageView imageIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            imageIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
        }
    }
}
