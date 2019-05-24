/**
 * @author Md Faruk Hossain
 * FIXME
 */
package com.faruk.retrofitshimmer.appfrm;

import android.app.Application;

// Put this in the manifest file
public class BaseApplication extends Application {

    public void onCreate() {
        super.onCreate();
    }

    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * We need a folder, localization, or other data that should be available in whole application.
     */
    public void init() {
    }


}
