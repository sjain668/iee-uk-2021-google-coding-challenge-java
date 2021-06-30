package com.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public boolean containsId(final List<Video> list, final String id){
    return list.stream().map(Video::getVideoId).filter(id::equals).findFirst().isPresent();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }



  public void showAllVideos() {
    List<Video> videos = videoLibrary.getVideos();

    Collections.sort(videos, new Comparator<Video>() {
      @Override
      public int compare(Video v1, Video v2) {
        return v1.getTitle().compareTo(v2.getTitle());
      }
    });



    for(Video video : videos) {
      System.out.print(video.getTitle() + " ");
      System.out.print("(" + video.getVideoId() + ") ");
      System.out.print(video.getTags());
      System.out.println();
    }
  }

  public void playVideo(String videoId) {
    List<Video> videos = videoLibrary.getVideos();

    if (!(containsId(videos, videoId))) {
      System.out.println("Cannot play video: Video does not exist");
      return;
    }

    for(Video video : videos) {
      if (video.isPlaying()) {
        System.out.println("Stopping video: " + video.getTitle());
        video.stopPlaying();
      }
    }

    System.out.println("Playing video: " + videoLibrary.getVideo(videoId).getTitle());
    videoLibrary.getVideo(videoId).startPlaying();

  }

  public void stopVideo() {
    List<Video> videos = videoLibrary.getVideos();

    int noVideosNotPlaying = 0;

    for(Video video : videos) {
      if (!video.isPlaying()) {
        noVideosNotPlaying += 1;
      }
    }

    if (noVideosNotPlaying == videos.size()) {
      System.out.println("Cannot stop video: No video is currently playing");
      return;
    }

    for(Video video : videos) {
      if (video.isPlaying()) {
        System.out.println("Stopping video: " + video.getTitle());
        video.stopPlaying();
      }
    }

  }

  public void playRandomVideo() {
    List<Video> videos = videoLibrary.getVideos();

    for(Video video : videos) {
      if (video.isPlaying()) {
        System.out.println("Stopping video: " + video.getTitle());
        video.stopPlaying();
      }
    }




  }

  public void pauseVideo() {
    System.out.println("pauseVideo needs implementation");
  }

  public void continueVideo() {
    System.out.println("continueVideo needs implementation");
  }

  public void showPlaying() {
    System.out.println("showPlaying needs implementation");
  }

  public void createPlaylist(String playlistName) {
    System.out.println("createPlaylist needs implementation");
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    System.out.println("addVideoToPlaylist needs implementation");
  }

  public void showAllPlaylists() {
    System.out.println("showAllPlaylists needs implementation");
  }

  public void showPlaylist(String playlistName) {
    System.out.println("showPlaylist needs implementation");
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    System.out.println("removeFromPlaylist needs implementation");
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}