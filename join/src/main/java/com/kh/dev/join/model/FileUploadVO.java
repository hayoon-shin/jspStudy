package com.kh.dev.join.model;

import java.util.Date;

public class FileUploadVO {
    private int id;
    private String title;
    private String author;
    private String content;
    private String password;
    private Date createdDate;

    // Getter and Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
}
