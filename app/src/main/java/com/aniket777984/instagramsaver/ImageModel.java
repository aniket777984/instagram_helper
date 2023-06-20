package com.aniket777984.instagramsaver;

public class ImageModel {

    String video_url,display_url;

    public ImageModel(String video_url, String display_url) {
        this.video_url = video_url;
        this.display_url = display_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getDisplay_url() {
        return display_url;
    }

    public void setDisplay_url(String display_url) {
        this.display_url = display_url;
    }
}
