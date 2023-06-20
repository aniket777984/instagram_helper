package com.aniket777984.instagramsaver;


public class SearchModel {

    private ImageModel shortcode_media;

    public SearchModel(ImageModel shortcode_media) {
        this.shortcode_media = shortcode_media;
    }

    public ImageModel getShortcode_media() {
        return shortcode_media;
    }

    public void setShortcode_media(ImageModel shortcode_media) {
        this.shortcode_media = shortcode_media;
    }
}
