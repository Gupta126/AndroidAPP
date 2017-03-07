package com.rahulgupta.androidapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rahulgupta.androidapp.Activity.ViewoOnMapsActivity;
import com.rahulgupta.androidapp.Application.AppController;
import com.rahulgupta.androidapp.Beans.Location;
import com.rahulgupta.androidapp.Helper.Utils;
import com.rahulgupta.androidapp.R;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScenarioTwoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScenarioTwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScenarioTwoFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {



    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    // json Array response url
    private String URL = "http://express-it.optusnet.com.au/sample.json";


    // Progress dialog
    private ProgressDialog pDialog;

    private static String TAG = ScenarioTwoFragment.class.getSimpleName();

    private OnFragmentInteractionListener mListener;

    private ArrayList<Location> location_details;
    private TextView mCarTravelTime, mTrainTravelTime;
    private Button mBtnNavigationToMap;
    private Spinner mSpnCityName;

    private ArrayList<String> city_name;

    private Location location;

    public ScenarioTwoFragment() {
        // Required empty public constructor
        // initialise data
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ScenarioTwoFragment.
     */

    public static ScenarioTwoFragment newInstance() {
        ScenarioTwoFragment fragment = new ScenarioTwoFragment();

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
        View view = inflater.inflate(R.layout.fragment_scenario_two, container, false);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        location_details = new ArrayList<Location>();
        city_name = new ArrayList<String>();

        setupUIComponent(view);

        if (Utils.isNetworkAvailable(getActivity()))
            makeJsonArrayRequest();
        else
            Toast.makeText(getActivity(), "Please check internet connection", Toast.LENGTH_SHORT).show();


        setupListners();

        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_navigation:
                startMap(location);
                break;
            default:


        }


    }

    private void startMap(Location location) {

        Intent mapIntent = new Intent(getActivity(), ViewoOnMapsActivity.class);
        mapIntent.putExtra("Location", location.getLocation());
        getActivity().startActivity(mapIntent);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        location = location_details.get(position);


        if (location.getFromcentral().getCar() != null
                && !location.getFromcentral().getCar().equalsIgnoreCase("")) {
            mCarTravelTime.setText(location.getFromcentral().getCar());
        } else {
            mCarTravelTime.setText("No best way for car travel");
        }

        if (location.getFromcentral().getTrain() != null
                && !location.getFromcentral().getTrain().equalsIgnoreCase("")) {
            mTrainTravelTime.setText(location.getFromcentral().getTrain());
        } else {
            mTrainTravelTime.setText("No best way for train travel");
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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

    private void showpDialog() {
        if (pDialog != null && !pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog != null && pDialog.isShowing())
            pDialog.dismiss();
    }

    private void setupAdapter() {

        // feed city name to sppiner list
        for (Location location : location_details) {

            if (city_name != null) {
                city_name.add(location.getName());
            }
        }


        Log.d(TAG, "setupAdapter: check what is null??" + city_name.toString()+"??"+ getContext());

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, city_name);

        mSpnCityName.setAdapter(spinnerAdapter);

    }

    /**
     * this method initialise all UI Components from view
     *
     * @param view
     */
    private void setupUIComponent(View view) {


        mBtnNavigationToMap = (Button) view.findViewById(R.id.btn_navigation);
        mCarTravelTime = (TextView) view.findViewById(R.id.tv_car_time);
        mTrainTravelTime = (TextView) view.findViewById(R.id.tv_train_time);
        mSpnCityName = (Spinner) view.findViewById(R.id.spinner_city_name);

    }


    private void setupListners() {

        mBtnNavigationToMap.setOnClickListener(ScenarioTwoFragment.this);
        mSpnCityName.setOnItemSelectedListener(ScenarioTwoFragment.this);

    }

    private void makeJsonArrayRequest() {

        showpDialog();

        JsonArrayRequest req = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        Gson gson = new Gson();
                        location_details = gson.fromJson(response.toString(), new TypeToken<ArrayList<Location>>() {
                        }.getType());

                        if (getActivity() != null)
                            setupAdapter();

                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }

    @Override
    public void onDestroyView() {
        hidepDialog();
        super.onDestroyView();
    }
}
