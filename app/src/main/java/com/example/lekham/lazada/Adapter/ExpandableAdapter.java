package com.example.lekham.lazada.Adapter;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lekham.lazada.Model.Main.ActionMenu.Menu;
import com.example.lekham.lazada.Model.ObjectClass.ProductType;
import com.example.lekham.lazada.R;

import java.util.List;

/**
 * Created by Le Kham on 5/29/2017.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<ProductType> mProductTypes;

    public ExpandableAdapter(Context context, List<ProductType> productTypes) {
        mContext = context;
        mProductTypes = productTypes;
        getDataChild();
    }

    @Override
    public int getGroupCount() {
        return mProductTypes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (mProductTypes.get(groupPosition).getProductTypeList().size() > 0)
            return 1;
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mProductTypes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mProductTypes.get(groupPosition).getProductTypeList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mProductTypes.get(groupPosition).getIDPRODUCT_TYPE();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mProductTypes.get(groupPosition).getProductTypeList().get(childPosition).getIDPRODUCT_TYPE();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public class ViewHolderMenu {
        TextView textViewItemMenu;
        ImageView imageViewIconMenu;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolderMenu viewHolderMenu;
        if (convertView == null) {
            viewHolderMenu = new ViewHolderMenu();
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_expandable_root, parent, false);
            viewHolderMenu.imageViewIconMenu = (ImageView) convertView.findViewById(R.id.iview_icon_menu);
            viewHolderMenu.textViewItemMenu = (TextView) convertView.findViewById(R.id.txtItemRoot);
            convertView.setTag(viewHolderMenu);
        } else {
            viewHolderMenu = (ViewHolderMenu) convertView.getTag();
        }

        viewHolderMenu.textViewItemMenu.setText(mProductTypes.get(groupPosition).getNAME_PRODUCT_TYPE().toString());
        if (mProductTypes.get(groupPosition).getProductTypeList().size() > 0) {
            viewHolderMenu.imageViewIconMenu.setVisibility(View.VISIBLE);
        } else {
            viewHolderMenu.imageViewIconMenu.setVisibility(View.INVISIBLE);
        }
        if (isExpanded) {
            viewHolderMenu.imageViewIconMenu.setImageResource(R.drawable.ic_remove_black_24dp);
            viewHolderMenu.textViewItemMenu.setTextColor(ContextCompat.getColor(mContext, R.color.bg_main));
        } else {
            viewHolderMenu.imageViewIconMenu.setImageResource(R.drawable.ic_add_black_24dp);
            viewHolderMenu.textViewItemMenu.setTextColor(ContextCompat.getColor(mContext, R.color.grey_600));
        }

        convertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(mContext, mProductTypes.get(groupPosition).getNAME_PRODUCT_TYPE(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SeconExpandable expandableListView = new SeconExpandable(mContext);
        ExpandableAdapter expandableAdapter = new ExpandableAdapter(mContext, mProductTypes.get(groupPosition).getProductTypeList());
        expandableListView.setAdapter(expandableAdapter);
        expandableListView.setGroupIndicator(null);
        expandableListView.setPadding(50, 0, 0, 0);
        expandableAdapter.notifyDataSetChanged();
        return expandableListView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private void getDataChild() {
        Menu menu = new Menu();
        for (ProductType productType : mProductTypes
                ) {
            productType.setProductTypeList(menu.getDataChildForMenu(productType.getIDPRODUCT_TYPE()));
        }
    }

    public class SeconExpandable extends ExpandableListView {

        public SeconExpandable(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
//            widthMeasureSpec = MeasureSpec.makeMeasureSpec(point.x, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(point.y, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
