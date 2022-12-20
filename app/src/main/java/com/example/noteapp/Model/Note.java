package com.example.noteapp.Model;

import com.google.gson.annotations.SerializedName;

public class Note {
    private @SerializedName("isDone") String isDone;
    private @SerializedName("title") String title;
    private @SerializedName("body")String body;
    private @SerializedName("dateCreated")String dateCreated;
    private @SerializedName("dateModified")String dateModified;
    private @SerializedName("alarmDate")String alarmDate;
    private @SerializedName("tag")String tag;
    private @SerializedName("_id")String id;
    private @SerializedName("id")String id2;
    private @SerializedName("author")String author;

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }
    public Note(String title) {
        this.title = title;
    }
    public Note(String title, String body, String id) {
        this.title = title;
        this.body = body;
        this.id = id;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getIsDone() {
        return isDone;
    }

    public void setIsDone(String isDone) {
        this.isDone = isDone;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getAlarmDate() {
        return alarmDate;
    }

    public void setAlarmDate(String alarmDate) {
        this.alarmDate = alarmDate;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
