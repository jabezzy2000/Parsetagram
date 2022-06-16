package com.example.parsetagram;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

//creating a post class responsible for getter and setter methods in interacting with the api
@ParseClassName("_User")
public class User extends ParseUser {
    public static final String KEY_PROFILE_IMAGE = "profile_image";

    public ParseFile getProfileImage() {
        return getParseFile(KEY_PROFILE_IMAGE);
    }

    public void setProfileImage(ParseFile image) {
        put(KEY_PROFILE_IMAGE, image);
    }
}


