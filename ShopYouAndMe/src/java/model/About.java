/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Phung An
 */
public class About {
    private String AboutId;
    private String Title;
    private String Content;
    private String img;

    public About() {
    }

    public About(String AboutId, String Title, String Content, String img) {
        this.AboutId = AboutId;
        this.Title = Title;
        this.Content = Content;
        this.img = img;
    }

    public String getAboutId() {
        return AboutId;
    }

    public void setAboutId(String AboutId) {
        this.AboutId = AboutId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
    
  
    
  
}
