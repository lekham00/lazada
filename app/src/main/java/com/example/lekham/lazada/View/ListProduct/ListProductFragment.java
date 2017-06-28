package com.example.lekham.lazada.View.ListProduct;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.example.lekham.lazada.Adapter.BaseRecyclerViewAdapter;
import com.example.lekham.lazada.Adapter.ProductAdapter;
import com.example.lekham.lazada.Model.ListProduct.ListProductContract;
import com.example.lekham.lazada.Model.ObjectClass.SanPham;
import com.example.lekham.lazada.Presenter.ListProductPresenter;
import com.example.lekham.lazada.R;
import com.example.lekham.lazada.Until.DataPublic;
import com.example.lekham.lazada.Until.LoadMoreRecyclerView;
import com.example.lekham.lazada.Until.SharePre;
import com.example.lekham.lazada.View.DetailProduct.DetailProductActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Le Kham on 6/22/2017.
 */

public class ListProductFragment extends Fragment implements View.OnClickListener, BaseRecyclerViewAdapter.OnRecyclerItemClicked, ListProductContract.View, LoadMoreRecyclerView.OnScrollListener {
    public static final String TAG = ListProductFragment.class.getSimpleName();

    protected ShimmerRecyclerView mRecyclerViewDetail;
    private View mView;
    private Button mBtnFiter, mBtnSort;
    private ImageView mImgTyleShow;
    private int mModeType;
    private ListProductPresenter mListProductPresenter;
    private int idKey = -1;
    ProductAdapter mProductAdapter;
    private int mTake = 10;
    private LoadMoreRecyclerView mLoadMoreRecyclerView;
    private List<SanPham> mSanPhamList = new ArrayList<>();
    private FrameLayout mFrameLayoutLoading;

    public static ListProductFragment initDetailFragment(Bundle bundle) {
        ListProductFragment detailFragment = new ListProductFragment();
        detailFragment.setArguments(bundle);
        return detailFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_list_product, container, false);
        mListProductPresenter = new ListProductPresenter(this);
        mBtnFiter = (Button) getActivity().findViewById(R.id.btnFiter);
        mBtnSort = (Button) getActivity().findViewById(R.id.btnSort);
        mImgTyleShow = (ImageView) getActivity().findViewById(R.id.imgTyleShow);
        mFrameLayoutLoading = (FrameLayout) mView.findViewById(R.id.frameLayoutLoading);
        mBtnFiter.setOnClickListener(this);
        mBtnSort.setOnClickListener(this);
        mImgTyleShow.setOnClickListener(this);
        if (getArguments() != null) {
            idKey = getArguments().getInt(ListProductActivity.ID_KEY);
        }
        setModeTyple();
        updateUIModeTyle();
        setAdapterProduct();
        handelGetData(0);

        return mView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSort:
                break;
            case R.id.btnFiter:
                break;
            case R.id.imgTyleShow:
                updateModeTple();
                updateUIModeTyle();
                handelUpdateListData();
                break;
        }
    }

    private void setAdapterProduct() {
        mRecyclerViewDetail = (ShimmerRecyclerView) mView.findViewById(R.id.recycleViewListProduct);
        mProductAdapter = new ProductAdapter(getContext(), this, mSanPhamList);
        handelUpdateListData();
        mRecyclerViewDetail.showShimmerAdapter();
    }

    @Override
    public void onItemClicked(View view, int position) {
        DetailProductActivity.startIntent(getContext(),mSanPhamList.get(position).getMASP());
    }

    @Override
    public void onResultGetListProduct(final List<SanPham> sanPhamList) {
        if (sanPhamList != null && sanPhamList.size() > 0) {
            if (mSanPhamList.size() == 0) {
                mRecyclerViewDetail.hideShimmerAdapter();
            }
            mSanPhamList.addAll(sanPhamList);

            mProductAdapter.notifyDataSetChanged();
            mLoadMoreRecyclerView.setLoaded();
        }
        mFrameLayoutLoading.setVisibility(View.GONE);
    }

    public void handelGetData(final int sumItem) {
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                if (mListProductPresenter != null && idKey > 0) {
                    mListProductPresenter.getListProductBrand(idKey, sumItem, mTake);
                }
                return null;
            }
        };
        asyncTask.execute();
    }

    private void setModeTyple() {
        if (SharePre.instantSharePre(getContext()).getValueInt(SharePre.KEY_MODE) < 0) {
            mModeType = DataPublic.ModeGrist;
        } else
            mModeType = SharePre.instantSharePre(getContext()).getValueInt(SharePre.KEY_MODE);
    }

    private void updateModeTple() {
        if (mModeType == DataPublic.ModeGrist) {
            mModeType = DataPublic.ModeList;
        } else if (mModeType == DataPublic.ModeList) {
            mModeType = DataPublic.ModeGrist;
        }
        SharePre.instantSharePre(getContext()).setValueInt(SharePre.KEY_MODE, mModeType);
    }

    private void updateUIModeTyle() {
        if (mModeType == DataPublic.ModeList) {
            mImgTyleShow.setBackgroundResource(R.drawable.ic_view_list_black_24dp);
        } else if (mModeType == DataPublic.ModeGrist) {
            mImgTyleShow.setBackgroundResource(R.drawable.ic_apps_black_24dp);
        }
    }

    private void handelUpdateListData() {
        if (mModeType == DataPublic.ModeGrist) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
            mRecyclerViewDetail.setLayoutManager(gridLayoutManager);
            mLoadMoreRecyclerView = new LoadMoreRecyclerView(gridLayoutManager, this);
            mRecyclerViewDetail.addOnScrollListener(mLoadMoreRecyclerView);
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            mRecyclerViewDetail.setLayoutManager(linearLayoutManager);
            mLoadMoreRecyclerView = new LoadMoreRecyclerView(linearLayoutManager, this);
            mRecyclerViewDetail.addOnScrollListener(mLoadMoreRecyclerView);
        }
        mProductAdapter.setModeType(mModeType);
        mRecyclerViewDetail.setAdapter(mProductAdapter);
    }

    @Override
    public void loadMoreData(int sumItem) {
        mFrameLayoutLoading.setVisibility(View.VISIBLE);
        handelGetData(sumItem);
    }
}
