package com.collegare.com.collegare.utilities;

import android.app.Application;
import android.content.Context;


/**
 * Created by RadhePC on 13-11-2015.
 */
public class Contexter extends Application {

    public Context getContext(){
        return getBaseContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
       /* Parse.initialize(this, "cVC7Rse3Ueddx5x6GAUIPKT5ROX7zBQIwttUXlbN", "wUS4KAUPagAfa0fbI1lgvrOoBlt9bQb2JAoD7chA");
        ParseInstallation.getCurrentInstallation().saveInBackground();*/
    }
}
