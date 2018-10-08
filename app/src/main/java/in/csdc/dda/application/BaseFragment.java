package in.csdc.dda.application;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.HashMap;

import in.csdc.dda.util.CommonMethods;

/**
 * Created by Rajdeep yadav on 13-Sep-18.
 */
public class BaseFragment extends Fragment implements View.OnClickListener {
    public static String HOME_FRAGMENT = "frg_home";
    private String frgamentName;
    public BaseActivity mActivity;
    private float density;


    public BaseFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        density = mActivity.getResources().getDisplayMetrics().density;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

        }
    }


    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    CommonMethods.hideKeyboard(getActivity());
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        hideKeyboard();
        mActivity.removeProgressDialog();

    }

    public HashMap<String, String> getBaseParams() {
        HashMap<String, String> params = new HashMap<String, String>();

        return params;
    }

    public float getDensity() {
        return density;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseActivity) activity;
    }


    private void hideKeyboard() {
        // Check if no view has focus:
        View view = mActivity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) mActivity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    public void setFragmentName(String frgamentName) {
        this.frgamentName = frgamentName;
    }

    public String getFragmentName() {
        return frgamentName;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {

        super.onStop();
    }


    private HashMap<String, String> getParams(int reqType) {
        HashMap<String, String> params = getBaseParams();


        return params;
    }

    /**
     * Override this method when refresh page is required in your fragment
     *
     * @param bundle
     */
    protected void refreshFragment(Bundle bundle) {

    }

    public void updateView(Object object) {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            default:
                break;
        }

        Log.d("click", BaseFragment.class.getSimpleName().toString());

    }


}