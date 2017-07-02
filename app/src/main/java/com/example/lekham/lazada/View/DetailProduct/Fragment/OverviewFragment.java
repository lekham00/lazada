package com.example.lekham.lazada.View.DetailProduct.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.lekham.lazada.Manager.DialogManager;
import com.example.lekham.lazada.Model.DetailProduct.DetailProductContract;
import com.example.lekham.lazada.Model.ObjectClass.SanPham;
import com.example.lekham.lazada.Network.ApiUtils;
import com.example.lekham.lazada.Presenter.DetailProductPresenter;
import com.example.lekham.lazada.R;
import com.example.lekham.lazada.Until.CustomSliderView;
import com.example.lekham.lazada.Until.Number;
import com.example.lekham.lazada.View.DetailProduct.DetailProductActivity;
import com.example.lekham.lazada.View.ReviewProduct.ReviewProductActitvity;
import com.example.lekham.lazada.View.ReviewProduct.ReviewProductFragment;

import java.util.HashMap;


/**
 * Created by Le Kham on 6/27/2017.
 */

public class OverviewFragment extends Fragment implements DetailProductContract.View, ViewPagerEx.OnPageChangeListener, View.OnClickListener {

    public static final String TAG = OverviewFragment.class.getSimpleName();

    private DetailProductPresenter mDetailProductPresenter;
    private DialogManager mDialogFragment;
    private int mId;
    private SliderLayout mSliderLayout;
    private TextView mTxtTitle, mTxtPrice, mTxtInformationDetailProduct, mTxtWritterReview;
    private LinearLayout mIndicatorDot;
    private TextView[] textViewDot;
    private String[] listImages;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        mSliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        mTxtTitle = (TextView) view.findViewById(R.id.txtTitle);
        mTxtPrice = (TextView) view.findViewById(R.id.txtPrice);
        mTxtInformationDetailProduct = (TextView) view.findViewById(R.id.txtInformationDetailProduct);
        mTxtWritterReview = (TextView) view.findViewById(R.id.txtWritterReview);
        mTxtWritterReview.setOnClickListener(this);
        mIndicatorDot = (LinearLayout) view.findViewById(R.id.indicator);
        if (getActivity().getIntent() != null) {
            mId = getActivity().getIntent().getIntExtra(DetailProductActivity.ID, -1);
        }
        SanPham listSanPham = ((DetailProductActivity) getActivity()).getSanPham();
        if (listSanPham == null && mId > 0) {
            mDetailProductPresenter = new DetailProductPresenter(this);
            mDetailProductPresenter.getDetailProduct(mId);
            mDialogFragment = new DialogManager(getActivity());
            mDialogFragment.showProgressDialog(getString(R.string.message_progress_dialog));
        } else {
            handelSlider(listSanPham);
            handelInformation(listSanPham);
        }
        return view;
    }

    @Override
    public void resultDetailProduct(SanPham sanPham) {
        mDialogFragment.dismiss();
        if (sanPham != null) {
            ((DetailProductActivity) getActivity()).setSanPham(sanPham);
            handelSlider(sanPham);
            handelInformation(sanPham);
        }
    }

    public void handelInformation(SanPham sanPham) {
        if (sanPham != null) {
            mTxtTitle.setText(sanPham.getTENSP());
            mTxtPrice.setText(Number.formatPrice(sanPham.getGIASP()) + " VND");
            String textInformationDetailProduct = sanPham.getTHONGTINSP().substring(0, 200);
            mTxtInformationDetailProduct.setText(textInformationDetailProduct.lastIndexOf(".") < 0 ? textInformationDetailProduct : textInformationDetailProduct.substring(0, textInformationDetailProduct.lastIndexOf(".") + 1));
        }
    }

    public void handelSlider(SanPham sanPham) {
        if (sanPham != null) {
            HashMap<String, String> hashMap = new HashMap<>();
            listImages = sanPham.getHINHSPNHO().toString().split(",");
            if (listImages.length > 1) {
                for (String s : listImages) {
                    hashMap.put(s, ApiUtils.DOMAN + "/images" + s);
                }
            } else {
                hashMap.put("0", ApiUtils.DOMAN + "/images" + sanPham.getHINHSPLON());
            }
            for (String name : hashMap.keySet()) {
                CustomSliderView customSliderView = new CustomSliderView(getContext());
                customSliderView.image(hashMap.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit);
                mSliderLayout.addSlider(customSliderView);
            }

            mSliderLayout.stopAutoCycle();
            mSliderLayout.addOnPageChangeListener(this);
            mSliderLayout.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
            if (hashMap.size() > 1) {
                setIndicator(0);
            }
        }
    }

    private void setIndicator(int position) {
        textViewDot = new TextView[listImages.length];
        mIndicatorDot.removeAllViews();
        for (int i = 0; i < listImages.length; i++) {
            textViewDot[i] = new TextView(getContext());
            textViewDot[i].setText(Html.fromHtml("&#8226;"));
            textViewDot[i].setTextSize(30);
            if (position == i)
                textViewDot[i].setTextColor(getColor(R.color.bg_main));
            else
                textViewDot[i].setTextColor(getColor(R.color.com_facebook_likeview_text_color));
            mIndicatorDot.addView(textViewDot[i]);
        }
    }

    private int getColor(int idColor) {
        int color = 0;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            color = ContextCompat.getColor(getContext(), idColor);
        } else {
            color = getContext().getResources().getColor(idColor);
        }
        return color;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d(TAG, "onPageScrolled :" + position);
    }

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, "onPageSelected :" + position);
        setIndicator(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d(TAG, "onPageScrollStateChanged :" + state);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtWritterReview:
                SanPham sanPham = ((DetailProductActivity) getActivity()).getSanPham();
                if (sanPham != null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(ReviewProductActitvity.KEY_ID, sanPham.getMASP());
                    bundle.putString(ReviewProductActitvity.KEY_TITLE, sanPham.getTENSP());
                    bundle.putInt(ReviewProductActitvity.KEY_PRICE, sanPham.getGIASP());
                    ReviewProductActitvity.startIntent(getContext(), bundle);
                }
                break;
        }
    }
}
