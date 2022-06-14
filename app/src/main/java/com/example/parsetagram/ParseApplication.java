package com.example.parsetagram;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                        .applicationId("NX8QAKxWd5A9DwRZu753rlUfkyandcHID1mFyZ2C")
                        .clientKey("5HBxgzKPNRlJy7cc0q69PP6YfarHadMykRVL5fFP")
                        .server("https://parseapi.back4app.com")
                        .build()
                );
    }
}
