package com.example.sayyadabid.testapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sayyadabid.testapp.R;

/**
 * Activity for Scenario1 implementation.
 * Created by Sayyad.abid on 12-Mar-16.
 */
public class Scenario1Activity extends MainActivity implements View.OnClickListener {
    private TextView tvSelectedItem;
    private LinearLayout llButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout flContainer = (FrameLayout) findViewById(R.id.main_container);
        View view = getLayoutInflater().inflate(R.layout.content_scenario1, null, false);
        flContainer.addView(view);
        initLayout();
        tvSelectedItem = (TextView) findViewById(R.id.tv_selected_item);
    }

    /**
     * Method to initialize layout
     */
    private void initLayout() {
        initTopItems();
        setupViewPager();
        initButtons();
    }

    /**
     * Method to initiaize top 5 equal width items
     */
    private void initTopItems() {
        LinearLayout llTopItems = (LinearLayout) findViewById(R.id.ll_top_items);
        TextView topItem = (TextView) llTopItems.findViewById(R.id.tv_top_item1);
        topItem.setOnClickListener(this);

        topItem = (TextView) llTopItems.findViewById(R.id.tv_top_item2);
        topItem.setOnClickListener(this);

        topItem = (TextView) llTopItems.findViewById(R.id.tv_top_item3);
        topItem.setOnClickListener(this);

        topItem = (TextView) llTopItems.findViewById(R.id.tv_top_item4);
        topItem.setOnClickListener(this);

        topItem = (TextView) llTopItems.findViewById(R.id.tv_top_item5);
        topItem.setOnClickListener(this);
    }

    /**
     * Method to initialize bottom 3 equal width buttons
     */
    private void initButtons() {
        llButtons = (LinearLayout) findViewById(R.id.ll_buttons);
        Button btnRed = (Button) llButtons.findViewById(R.id.btn_red);
        btnRed.setOnClickListener(this);

        Button btnOrange = (Button) llButtons.findViewById(R.id.btn_orange);
        btnOrange.setOnClickListener(this);

        Button btnGreen = (Button) llButtons.findViewById(R.id.btn_green);
        btnGreen.setOnClickListener(this);
    }

    /**
     * Method to setup viewpager.
     */
    private void setupViewPager() {
        ViewPager pager = (ViewPager) findViewById(R.id.vp_dummy);
        CustomPagerAdapter adapter = new CustomPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_top_item1:
            case R.id.tv_top_item2:
            case R.id.tv_top_item3:
            case R.id.tv_top_item4:
            case R.id.tv_top_item5:
                tvSelectedItem.setText(((TextView) view).getText());
                break;

            case R.id.btn_red:
                llButtons.setBackgroundColor(getResources().getColor(R.color.colorRed));
                break;

            case R.id.btn_orange:
                llButtons.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                break;

            case R.id.btn_green:
                llButtons.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                break;
        }
    }

    /**
     * Custom pager adapter for view pager.
     */
    private class CustomPagerAdapter extends FragmentPagerAdapter {
        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            String fragmentName = String.format(getString(R.string.text_dummy_fragment), position + 1);
            Fragment dummyFragment = DummyFragment.newInstance(fragmentName);
            return dummyFragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
