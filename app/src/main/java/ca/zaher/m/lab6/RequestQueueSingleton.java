package ca.zaher.m.lab6;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by zaher on 2018-03-06.
 */

public class RequestQueueSingleton {

    private static RequestQueueSingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context context;

    private RequestQueueSingleton(Context context) {
        context = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized RequestQueueSingleton getmInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RequestQueueSingleton(context.getApplicationContext());
        }
        return mInstance;
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return mRequestQueue;
    }
}
