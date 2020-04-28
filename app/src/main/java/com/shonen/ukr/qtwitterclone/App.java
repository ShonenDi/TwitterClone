package com.shonen.ukr.qtwitterclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("k0ReyacoZkFfYhIoMJ5AEzvOcf2z0j0n5vwePxCu")
                // if defined
                .clientKey("7ndOKdfvzdNdncOdwh0ul4wOYvyGO7BseFZfEh1M")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
