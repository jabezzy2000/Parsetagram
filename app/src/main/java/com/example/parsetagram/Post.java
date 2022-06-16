package com.example.parsetagram;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

//creating a post class responsible for getter and setter methods in interacting with the api
@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String  KEY_DESCRIPTION = "description";
    public static final String  KEY_IMAGE = "image";
    public static final String  KEY_USER = "user";
    public static final String KEY_LIKED_BY = "likedBy";

    public String getDescription(){
        return getString(KEY_DESCRIPTION);
    }
    public void setDescription(String description){
        put(KEY_DESCRIPTION, description);

    }
    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
    }
    public void setImage(ParseFile image){
        put(KEY_IMAGE, image);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public List<ParseUser> getLikedBy(){
        List<ParseUser> likedBy = getList(KEY_LIKED_BY);
        if(likedBy==null){
            return new ArrayList<>();
        }
        return likedBy;
    }

    public void setLikedBY(List<ParseUser> newLikedBy) {
        put(KEY_LIKED_BY, newLikedBy);
    }


}
