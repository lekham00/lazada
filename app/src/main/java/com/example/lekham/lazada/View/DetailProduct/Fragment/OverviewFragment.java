package com.example.lekham.lazada.View.DetailProduct.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lekham.lazada.Manager.DialogManager;
import com.example.lekham.lazada.Model.DetailProduct.DetailProductContract;
import com.example.lekham.lazada.Model.ObjectClass.SanPham;
import com.example.lekham.lazada.Presenter.DetailProductPresenter;
import com.example.lekham.lazada.R;
import com.example.lekham.lazada.View.DetailProduct.DetailProductActivity;

/**
 * Created by Le Kham on 6/27/2017.
 */

public class OverviewFragment extends Fragment implements DetailProductContract.View {

    private DetailProductPresenter mDetailProductPresenter;
    private DialogManager mDialogFragment;
    private int mId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        if (getActivity().getIntent() != null) {
            mId = getActivity().getIntent().getIntExtra(DetailProductActivity.ID, -1);
        }
        if(mId >0) {
            mDetailProductPresenter = new DetailProductPresenter(this);
            mDetailProductPresenter.getDetailProduct(mId);
            mDialogFragment = new DialogManager(getActivity());
            mDialogFragment.showProgressDialog(getString(R.string.message_progress_dialog));
        }
        return view;
    }

    @Override
    public void resultDetailProduct(SanPham sanPham) {
        mDialogFragment.dismiss();
        if (sanPham != null) {
            Log.d("LK", "sanpham :" + sanPham);
        }
    }
}
