package com.wisplu.wisplu;

import android.app.Application;

import com.kii.cloud.storage.Kii;

/**
 * Created by shuikuhiro on 16/9/12.
 */
public class pref extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Kii.initialize(getApplicationContext(), "258f5624", "511a9fecc22d6397c740148b7e65ae5e", Kii.Site.CN3, true);

        System.out.println("1111111111111111 ");
    }
}
