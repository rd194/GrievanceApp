package in.csdc.dda.application;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.HashMap;

import static android.content.pm.PackageManager.GET_META_DATA;


/**
 * Created by Rajdeep yadav on 29-Aug-18.
 */
public  class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "BaseActivity";

    private static AlertDialog alertDialog;
    private BaseApplication mApplication;
    private long delayTimeForExitToast = 2000;
    private long backPressedTime;
    private ProgressDialog mProgressDialog;
    private String mDeviceId;

    private boolean isWindowFocused;
    private boolean isAppWentToBg;
    private boolean isBackPressed;
    private static int runningActivities = 0;





    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mApplication = (BaseApplication) getApplication();
        resetTitles();
    }

    public void updateToolBarTitle(String title){

    }

/*

    public void replaceFragment(String currentFragmentTag, Fragment fragment, Bundle bundle, boolean isAddToBackStack) {
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        String tag = fragment.getClass().getSimpleName();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragmentLocal = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (fragmentLocal != null && fragmentLocal.getTag().equalsIgnoreCase(tag)) {
            ((BaseFragment) fragmentLocal).refreshFragment(bundle);
            return;
        }
        if (!StringUtils.isNullOrEmpty(currentFragmentTag)) {
            ft.add(R.id.content_frame, fragment, tag);
            Fragment fragmentToHide = getSupportFragmentManager().findFragmentByTag(currentFragmentTag);
            if (fragmentToHide != null) {
                ft.hide(fragmentToHide);
            }
        } else {
            ft.replace(R.id.content_frame, fragment, tag);
        }

        fragment.setRetainInstance(true);
        if (isAddToBackStack) {
            ft.addToBackStack(tag);
        }
        try {
            ft.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            ft.commitAllowingStateLoss();
        }
    }

*/



    public void showProgressDialog() {
        if (isFinishing()) {
            return;
        }
        try {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                mProgressDialog.setCancelable(false);
                mProgressDialog.setMessage("Loading");
            }
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showProgressDialog(String msg) {
        if (isFinishing()) {
            return;
        }
        try {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                mProgressDialog.setCancelable(false);
                mProgressDialog.setMessage(msg);
            }
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes the progress dialog
     */
    public void removeProgressDialog() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.hide();
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideSoftKeyBoard() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mApplication.activityResumed();

    }


    @Override
    protected void onPause() {
        super.onPause();
        mApplication.activityPaused();

    }



    @Override
    public void onClick(View view) {

    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }






    private HashMap<String, String> getParams(int reqType) {
        HashMap<String, String> params = new HashMap<String, String>();


        return params;
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onStop() {

        super.onStop();


    }

    public void removeFragmentFromBackStack(Fragment fragment) {

        String tag = fragment.getClass().getSimpleName();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        try {
            trans.remove(fragment);
            trans.commit();
            manager.popBackStack();

        } catch (Exception ex) {
            ex.printStackTrace();
            trans.commitAllowingStateLoss();
        }
    }



    private void resetTitles() {
        try {
            ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), GET_META_DATA);
            if (info.labelRes != 0) {
                setTitle(info.labelRes);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }




}