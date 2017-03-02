package com.rahulgupta.androidapp.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rahulgupta.androidapp.Adapters.CityAdapter;
import com.rahulgupta.androidapp.Adapters.ViewPagerAdapter;
import com.rahulgupta.androidapp.R;

import android.view.ViewGroup.LayoutParams;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScenarioOneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScenarioOneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScenarioOneFragment extends Fragment implements View.OnClickListener,CityAdapter.OnAdapterInteractionListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private RecyclerView city_recycler_view;
    private OnFragmentInteractionListener mListener;
    private CityAdapter mCityAdapter;
    ViewPager viewPager;
    ViewPagerAdapter adapterViewPager;
    int pagerPadding = 20;
    private ArrayList<String> cityNamelList;


    // define components
    private Button mRedBtn, mGreenBtn, mBlueBtn;
    private LinearLayout mPointFiveLayout, mAddTextviewLayout, mMainLayout;
    private TextView mTvPune, mTvDelhi, mTvMumbai, mTvChennai, mTvAhmedabad;


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
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scenario_one, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUIComponent(view);
        setupAdapter();
        setupListners();
    }

    private void setupAdapter() {
        adapterViewPager = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);

        cityNamelList=new ArrayList<>();
        cityNamelList.add("Pune");
        cityNamelList.add("Delhi");
        cityNamelList.add("Ahmedabad");
        cityNamelList.add("Mumbai");
        cityNamelList.add("Rajkot");
        cityNamelList.add("Noida");
        cityNamelList.add("Nagpur");
        cityNamelList.add("Kola");
        cityNamelList.add("Agra");
        cityNamelList.add("Jaipur");

        mCityAdapter=new CityAdapter(cityNamelList,this);

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        city_recycler_view.setLayoutManager(horizontalLayoutManagaer);
        city_recycler_view.setAdapter(mCityAdapter);

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

        mAddTextviewLayout = (LinearLayout) view.findViewById(R.id.addTextviewlayout);
        mPointFiveLayout = (LinearLayout) view.findViewById(R.id.point_five_layout);

        city_recycler_view = (RecyclerView) view.findViewById(R.id.horizontal_recycler_view);

    }


    /**
     * this method setup listners of UI Components.
     */
    private void setupListners() {

        mRedBtn.setOnClickListener(this);
        mGreenBtn.setOnClickListener(this);
        mBlueBtn.setOnClickListener(this);

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
           /* throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
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
        }
    }


    private int getColor(int color) {
        return android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP ? ContextCompat.getColor(getActivity(), color) : getActivity().getResources().getColor(color);
    }

    @Override
    public void onViewClick(String cityName) {
        addTextView(mAddTextviewLayout,cityName,position);
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


    /**
     * this method initialise all UI Components from view
     *
     * @param mainLayout pass linear layout whe it will add new text view.
     * @param name this param will set textview text.
     */
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
        mainLayout.removeAllViews();
        mainLayout.addView(layout);
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

}
