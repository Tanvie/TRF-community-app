package com.example.trfcommunityapp.Activities;

import com.google.firebase.firestore.Exclude;

public class blogs {

    private String documentId;
    private String title;
    private String description;
    private  String author;
    public blogs() {
        //public no-arg constructor needed
    }
    @Exclude
    public String getDocumentId() {
        return documentId;
    }
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
    public blogs(String title, String description, String author) {
        this.title = title;
        this.author = author;
        this.description = description;
    }
    public  void setTitle(String title1){
        this.title = title1;
    }
    public  void setAuthor(String author1){
        this.author = author1;
    }
    public  void setDescription(String description1){
        this.description = description1;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public String getAuthor()
    {
        return  author;
    }

}
