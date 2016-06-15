package com.cutebird;

import android.app.Application;
import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by islam on 3/29/2016.
 */
public class App extends Application {

    private static Context applicationContext;
    /**
     * The {@code FirebaseAnalytics} used to record screen views.
     */
    // [START declare_analytics]
    private static FirebaseAnalytics firebaseAnalytics;
    // [END declare_analytics]

    public static Context getContext() {
        return applicationContext;
    }

    /**
     * return instance of firebase analytics
     *
     * @return
     */
    public static FirebaseAnalytics getAnalytics() {
        return firebaseAnalytics;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //init dbflow database
        FlowManager.init(new FlowConfig.Builder(this).openDatabasesOnInit(true).build());
        App.applicationContext = getApplicationContext();
        //Shared.getInstance();
        // Util.getInstance().setDefaultLocale(this);
        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

    }

    @Override
    public void onTerminate() {
        //destroy dbflow instance
        FlowManager.destroy();
        super.onTerminate();
    }
}
