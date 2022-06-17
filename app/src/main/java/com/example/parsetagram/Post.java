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
    public static final String  KEY_PROFILE_IMAGE = "profile_image";
    public static final String KEY_LIKED_BY = "likedBy";

    public String getDescription(){
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description){put(KEY_DESCRIPTION, description);}

    public User getUser() {
        return (User) getParseUser(KEY_USER);
    }

    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
    }

    public ParseFile getProfileImage(){
        return getParseFile(KEY_PROFILE_IMAGE);
    }

    public void setImage(ParseFile image){
        put(KEY_IMAGE, image);
    }

    public void setProfileImage(ParseFile image){
        put(KEY_PROFILE_IMAGE, image);
    }

    public void setUser(User user) {put(KEY_USER, user);}

    public List<User> getLikedBy(){
        List<User> likedBy = getList(KEY_LIKED_BY);
        if(likedBy != null){
            return likedBy;
        }else{
            return new ArrayList<User>();
        }
    }
    public boolean isLikedByCurrentUser(){
        List<User> likedBy  = getLikedBy();
        for(int i = 0; i < getLikedBy().size(); i++){
            if(likedBy.get(i).hasSameId(ParseUser.getCurrentUser())){
                return true;
            }}
        return false;
    }

    public String likeCountDisplayText(){
        String likesText= String.valueOf(getLikedBy().size());
        likesText += getLikedBy().size() == 1 ? "like" : "likes";
        return likesText;
    }
    public void unlike(){
        List<User> likedBy  = getLikedBy();
        for(int i = 0; i < getLikedBy().size(); i++){
            if(likedBy.get(i).hasSameId(ParseUser.getCurrentUser())){
                likedBy.remove(i);
            }
            setLikedBy(likedBy);
            saveInBackground();
        }}


    public void setLikedBy(List<User> user){
        put(KEY_LIKED_BY, user);
    }

    public void like(){
        unlike();
        List<User> likedby = getLikedBy();
        likedby.add((User) User.getCurrentUser());
        setLikedBy(likedby);
        saveInBackground();
    }

}
