package com.google;

import java.util.ArrayList;

public class Playlist {

    private String name;
    private ArrayList<Video> videos = new ArrayList<Video>();

    public Playlist(String name) {
        this.name = name;
        this.videos = new ArrayList<Video>();
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ArrayList<Video> getVideos() {
        return this.videos;
    }

    public void addVideo(Video video) {
        this.videos.add(video);
    }

}
