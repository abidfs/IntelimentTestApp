package com.example.sayyadabid.testapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sayyadabid.testapp.R;

/**
 * The dummy framgemt for Scenario1Activity ViewPager
 * Created by Sayyad.abid on 12-Mar-16.
 */
public class DummyFragment extends Fragment {
    private static final String ARG_FRAGMENT_NAME = "FragmentName";

    private String mFragmentName;


    public DummyFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param fragmentName the fragment name to be displayed.
     * @return A new instance of fragment DummyFragment.
     */
    public static DummyFragment newInstance(String fragmentName) {
        DummyFragment fragment = new DummyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FRAGMENT_NAME, fragmentName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dummy_fragment, container, false);
        if (getArguments() != null) {
            mFragmentName = getArguments().getString(ARG_FRAGMENT_NAME);
        }
        TextView tvFragmentName = (TextView) view.findViewById(R.id.tv_fragment_name);
        tvFragmentName.setText(mFragmentName);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), mFragmentName, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
