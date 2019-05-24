/**
 * @author Md Faruk Hossain
 * FIXME
 */

package com.faruk.retrofitshimmer.appfrm;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtill {

    /**
     * This method check if network is avilable or not
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo = getNetworkInfo(context);
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Get the network info
     *
     * @param context
     * @return
     */
    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }
}
