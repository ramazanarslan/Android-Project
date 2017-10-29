package com.aprigoldcorporation.allculator.ui.activities.ruler;

import android.app.Application;

public class AndroidRulerApplication extends Application {

    private static AndroidRulerApplication sInstance;

    public AndroidRulerApplication() {sInstance = this;}
    public static synchronized AndroidRulerApplication getsInstance() {return sInstance;}
}
