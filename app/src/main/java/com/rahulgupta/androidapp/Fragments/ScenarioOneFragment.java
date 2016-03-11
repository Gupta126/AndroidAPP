package com.rahulgupta.androidapp.Fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rahulgupta.androidapp.Adapters.ViewPagerAdapter;
import com.rahulgupta.androidapp.R;

import android.view.ViewGroup.LayoutParams;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScenarioOneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScenarioOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScenarioOneFragment extends Fragment implements View.OnClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    ViewPager viewPager;
    ViewPagerAdapter adapterViewPager;
    int pagerPadding = 20;


    // define components
    private Button mRedBtn, mGreenBtn, mBlueBtn;
    private LinearLayout mPointFiveLayout, mFourLayout, mMainLayout;
    private TextView mTvItem1, mTvItem2, mTvItem3, mTvItem4, mTvItem5;


    final int position = 3;
    final float weight = 0.5f;
    final int textSize = 15;

    public ScenarioOneFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ScenarioOneFragment.
     */

    public static ScenarioOneFragment newInstance() {
        ScenarioOneFragment fragment = new ScenarioOneFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scenario_one, container, false);

        setupUIComponent(view);

        setupAdapter();

        setupListners();
        return view;
    }


    private void setupAdapter() {
        adapterViewPager = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);

    }

    /**
     * this method initialise all UI Components from view
     *
     * @param view
     */
    private void setupUIComponent(View view) {

        mMainLayout = (LinearLayout) view.findViewById(R.id.main_layout);
        // mFourLayout = (LinearLayout) view.findViewById(R.id.point_four_layout);
        viewPager = (ViewPager) view.findViewById(R.id.myviewpager);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(pagerPadding);

        mRedBtn = (Button) view.findViewById(R.id.red_btn);
        mGreenBtn = (Button) view.findViewById(R.id.green_btn);
        mBlueBtn = (Button) view.findViewById(R.id.blue_btn);

        mPointFiveLayout = (LinearLayout) view.findViewById(R.id.point_five_layout);

        mTvItem1 = (TextView) view.findViewById(R.id.info_text_item_one);
        mTvItem2 = (TextView) view.findViewById(R.id.info_text_item_two);
        mTvItem3 = (TextView) view.findViewById(R.id.info_text_item_three);
        mTvItem4 = (TextView) view.findViewById(R.id.info_text_item_four);
        mTvItem5 = (TextView) view.findViewById(R.id.info_text_item_five);

    }


    private void setupListners() {

        mRedBtn.setOnClickListener(this);
        mGreenBtn.setOnClickListener(this);
        mBlueBtn.setOnClickListener(this);

        mTvItem1.setOnClickListener(this);
        mTvItem2.setOnClickListener(this);
        mTvItem3.setOnClickListener(this);
        mTvItem4.setOnClickListener(this);
        mTvItem5.setOnClickListener(this);
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("ACT", "attached");
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.red_btn:
                mPointFiveLayout.setBackgroundColor(getColor(R.color.red_selected));
                break;
            case R.id.green_btn:
                mPointFiveLayout.setBackgroundColor(getColor(R.color.green_selected));
                break;
            case R.id.blue_btn:
                mPointFiveLayout.setBackgroundColor(getColor(R.color.blue_selected));
                break;
            case R.id.info_text_item_one:
                addTextView(mMainLayout, mTvItem1.getText().toString(), position);
                break;

            case R.id.info_text_item_two:
                addTextView(mMainLayout, mTvItem2.getText().toString(), position);
                break;

            case R.id.info_text_item_three:
                addTextView(mMainLayout, mTvItem3.getText().toString(), position);
                break;

            case R.id.info_text_item_four:
                addTextView(mMainLayout, mTvItem4.getText().toString(), position);
                break;
            case R.id.info_text_item_five:
                addTextView(mMainLayout, mTvItem5.getText().toString(), position);
                break;


        }
    }


    private int getColor(int color) {
        return android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP ? ContextCompat.getColor(getActivity(), color) : getActivity().getResources().getColor(color);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void addTextView(LinearLayout mainLayout, String name, int position) {

        TextView textView = new TextView(getActivity());
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(textSize);
        textView.setGravity(Gravity.CENTER);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setText(name);


        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        layoutParams.weight = weight;

        layout.setBackgroundColor(getColor(android.R.color.holo_blue_bright));
        layout.setLayoutParams(layoutParams);
        layout.addView(textView);

        // popout to main layout
        mainLayout.addView(layout, position);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
