package app.hamcr7.mapr.prototypeapp;

import java.util.Date;

/**
 * Created by MaPr on 31-12-2017.
 */

public class Blog extends BlogPostId{


    public String user_id, image_url, desc,title,catg,image_thumb;
    public Date timestamp;

    public Blog(){

    }

    public Blog(String user_id, String image_url,String title,String catg, String desc, String image_thumb, Date timestamp) {

        this.user_id = user_id;
        this.image_url = image_url;
        this.title = title;
        this.catg = catg;
        this.desc = desc;
        this.image_thumb = image_thumb;
        this.timestamp = timestamp;

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getCatgory() {
        return catg;
    }

    public void setCatgory(String catg) {
        this.catg = catg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage_thumb() {
        return image_thumb;
    }

    public void setImage_thumb(String image_thumb) {
        this.image_thumb = image_thumb;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }



}
