package com.google;

import java.util.ArrayList;

public class Playlist {

    private String name;
    private ArrayList<Video> videos = new ArrayList<Video>();

    public Playlist(String name) {
        this.name = name;
        this.videos = new ArrayList<Video>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Video> getVideos() {
        return this.videos;
    }

    public void addVideo(Video video) {
        this.videos.add(video);
    }

    public void printVideos() {

        if (videos.isEmpty()) {
            System.out.println("No videos here yet");
            return;
        }

        for (Video video : this.videos) {

            System.out.print(video.getTitle() + " ");
            System.out.print("(" + video.getVideoId() + ") [");

            for (int i = 0; i < video.getTags().size(); i++) {

                if (i != video.getTags().size() - 1) {
                    System.out.print(video.getTags().get(i) + " ");
                } else if (i == video.getTags().size() - 1) {
                    System.out.print(video.getTags().get(i));
                }
            }

            System.out.print("]");
            System.out.println();
        }

    }

}
