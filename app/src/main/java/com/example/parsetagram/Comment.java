package com.example.parsetagram;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class Comment extends ParseObject {
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_POST = "post";
    public static final String KEY_BODY = "body";

    public ParseUser getAuthor() {
        return (ParseUser) getParseUser(KEY_AUTHOR);
    }
    public void setAuthor(ParseUser user){
        put(KEY_AUTHOR,user);
    }
    public void setPost(ParseUser post){
        put(KEY_POST,post);
    }
    public String getPost(ParseUser post){
        return post.toString();
    }
    public String getBody(ParseUser body){
//        put(KEY_BODY,body);

    }
    public void setBody(ParseUser body){
        put(KEY_BODY,body);
    }


}
