package in.csdc.dda.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import in.csdc.dda.database.BaseSqliteOpenHelper;


/**
 * @author kapil.vij
 */
public class BaseApplication extends Application {

    private static final String TAG = "BaseApplication";

    private static BaseSqliteOpenHelper dbHelper;
    public static Context mBaseContext;
    private boolean activityVisible;
    private String mFriendChatUserName;
    private boolean activityChatVisible;
    public static SQLiteDatabase db;



    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new BaseSqliteOpenHelper(this);
        mBaseContext = getApplicationContext();

    }

    private void insertDummyData() {

    }

    public boolean isActivityVisible() {
        return activityVisible;
    }

    public void activityResumed() {
        activityVisible = true;
    }

    public void activityPaused() {
        activityVisible = false;
    }



/*
    public VolleyManager getVolleyManagerInstance() {
        return VolleyManager.getInstance(getApplicationContext());
    }*/

    public static BaseSqliteOpenHelper getDbHelper() {
        return dbHelper;
    }

    @Override
    public void onTerminate() {
        dbHelper.close();
        super.onTerminate();
    }




    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged: " + newConfig.locale.getLanguage());
    }
}
