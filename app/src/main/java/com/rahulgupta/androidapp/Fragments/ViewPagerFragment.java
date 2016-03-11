package com.rahulgupta.androidapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rahulgupta.androidapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment implements View.OnClickListener {


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_POSITION = "somePosition";
    private static final String ARG_TITLE = "someTitle";

    // Store instance variables
    private String title;
    private int page;

    public ViewPagerFragment() {
        // Required empty public constructor
    }

    // newInstance constructor for creating fragment with arguments
    public static ViewPagerFragment newInstance(int page, String title) {
        ViewPagerFragment fragmentFirst = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, page);
        args.putString(ARG_TITLE, title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt(ARG_POSITION, 0);
        title = getArguments().getString(ARG_TITLE);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.tv_view_pager);
        tvLabel.setText(title);
        tvLabel.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_view_pager:
                Toast.makeText(getActivity(), title, Toast.LENGTH_LONG).show();
                break;
            default:


        }
    }
}


