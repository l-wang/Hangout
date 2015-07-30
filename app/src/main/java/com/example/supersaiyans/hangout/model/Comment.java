package com.example.supersaiyans.hangout.model;

/**
 * Created by Chetan on 7/30/2015.
 */
public class Comment {
    private int commentID;
    private String commentText;

    public Comment(int commentID, String commentText) {
        super();
        this.commentID = commentID;
        this.commentText = commentText;
    }

    public int getCommentID() {
        return commentID;
    }
    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }
    public String getCommentText() {
        return commentText;
    }
    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
