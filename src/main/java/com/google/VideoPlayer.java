package com.google;

import java.util.*;
import java.util.ArrayList;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private ArrayList<Playlist> playlists = new ArrayList<Playlist>();

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

//  private static boolean containsId(final List<Video> videos, final String id){
//    return videos.stream()
//            .map(Video::getVideoId)
//            .filter(id::equals)
//            .findFirst()
//            .isPresent();
//  }

  private static Video getRandomVideo(List<Video> videos) {
    return videos.get(new Random().nextInt(videos.size()));
  }

  private static Video getCurrentlyPlayingVideo(List<Video> videos) {

    Video returnVal = null;

    for (Video video : videos) {
      if (video.isPlaying()) {
        returnVal = video;
      }
    }
    return returnVal;
  }


  private static boolean aVideoIsPlaying(List<Video> videos) {

    boolean returnVal = false;

    for (Video video : videos) {
      if (video.isPlaying()) {
        returnVal = true;
      }
    }
    return returnVal;
  }

  private boolean playlistExists(String playlistName) {

    boolean returnVal = false;

    for (Playlist playlist : playlists) {
      if (playlist.getName().equalsIgnoreCase(playlistName)) {
        returnVal = true;
      }
    }
    return returnVal;
  }

  private boolean videoExists(List<Video> videos, String videoId) {

    boolean returnVal = false;

    for (Video video : videos) {
      if (video.getVideoId().equals(videoId)) {
        returnVal = true;
      }
    }
    return returnVal;
  }


  private boolean videoExistsInPlaylist(String playlistName, String videoId) {

    boolean returnVal = false;
    Playlist queriedPlaylist = getPlaylistByName(playlistName);

    for (Video video : queriedPlaylist.getVideos()) {
      if (video.getVideoId() == videoId) {
        returnVal = true;
      }
    }
    return returnVal;
  }



  private Playlist getPlaylistByName(String playlistName) {

    Playlist queriedPlaylist = new Playlist("null");

    for (Playlist playlist : playlists) {
      if (playlist.getName().equalsIgnoreCase(playlistName)) {
        queriedPlaylist = playlist;
      }
    }
    return queriedPlaylist;
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


    System.out.println("Here's a list of all available videos:");

    for(Video video : videos) {
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




  public void playVideo(String videoId) {
    List<Video> videos = videoLibrary.getVideos();

    if (!(videoExists(videos, videoId))) {
      System.out.println("Cannot play video: Video does not exist");
      return;
    }


    if (aVideoIsPlaying(videos)) {
      Video currentlyPlayingVideo = getCurrentlyPlayingVideo(videos);
      System.out.println("Stopping video: " + currentlyPlayingVideo.getTitle());
      currentlyPlayingVideo.stopPlaying();
    }

    if (aVideoIsPlaying(videos)) {
      Video currentlyPlayingVideo = getCurrentlyPlayingVideo(videos);
      if (currentlyPlayingVideo.isPaused()) {
        currentlyPlayingVideo.unPause();
      }
    }

    System.out.println("Playing video: " + videoLibrary.getVideo(videoId).getTitle());
    videoLibrary.getVideo(videoId).startPlaying();
    videoLibrary.getVideo(videoId).unPause();




  }




  public void stopVideo() {
    List<Video> videos = videoLibrary.getVideos();
    
    if (!aVideoIsPlaying(videos)) {
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

    if (videos.isEmpty()) {
      System.out.println("No videos available");
      return;
    }

    for(Video video : videos) {
      if (video.isPlaying()) {
        System.out.println("Stopping video: " + video.getTitle());
        video.stopPlaying();
      }
    }

    Video randomVideo = getRandomVideo(videos);

    System.out.println("Playing video: " + randomVideo.getTitle());
    randomVideo.startPlaying();
  }




  public void pauseVideo() {
    List<Video> videos = videoLibrary.getVideos();
    
    if (!aVideoIsPlaying(videos)) {
      System.out.println("Cannot pause video: No video is currently playing");
      return;
    }

    for(Video video : videos) {
      if (video.isPlaying() && (!video.isPaused())) {
        System.out.println("Pausing video: " + video.getTitle());
        video.pause();
      } else if (video.isPaused()) {
        System.out.println("Video already paused: " + video.getTitle());
      }
    }

  }

  public void continueVideo() {
    List<Video> videos = videoLibrary.getVideos();

    if (!aVideoIsPlaying(videos)) {
      System.out.println("Cannot continue video: No video is currently playing");
      return;
    }

    Video currentlyPlayingVideo = getCurrentlyPlayingVideo(videos);

    if (!currentlyPlayingVideo.isPaused()) {
      System.out.println("Cannot continue video: Video is not paused");
      return;
    }

    if (currentlyPlayingVideo.isPaused()) {
      System.out.println("Continuing video: " + currentlyPlayingVideo.getTitle());
      currentlyPlayingVideo.unPause();
      return;
    }

  }

  public void showPlaying() {
    List<Video> videos = videoLibrary.getVideos();

    if (!aVideoIsPlaying(videos)) {
      System.out.println("No video is currently playing");
      return;
    }

    Video currentlyPlayingVideo = getCurrentlyPlayingVideo(videos);

    if (currentlyPlayingVideo.isPaused()) {
      System.out.print("Currently playing: " + currentlyPlayingVideo.getTitle() + " "
                          + "(" + currentlyPlayingVideo.getVideoId() + ") ");

      currentlyPlayingVideo.printTags();

      System.out.println(" - PAUSED");


    } else if (!currentlyPlayingVideo.isPaused()) {

      System.out.print("Currently playing: " + currentlyPlayingVideo.getTitle() + " "
              + "(" + currentlyPlayingVideo.getVideoId() + ") ");

      currentlyPlayingVideo.printTags();

      System.out.println();

    }

  }

  public void createPlaylist(String playlistName) {

    if (playlistExists(playlistName)) {
      System.out.println("Cannot create playlist: A playlist with the same name already exists");
      return;
    }

    playlists.add(new Playlist(playlistName));
    System.out.println("Successfully created new playlist: " + playlistName);

  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    List<Video> videos = videoLibrary.getVideos();

    if (!playlistExists(playlistName)) {
      System.out.println("Cannot add video to " + playlistName + ": Playlist does not exist");
      return;
    }

    if (!videoExists(videos, videoId)) {
      System.out.println("Cannot add video to " + playlistName + ": Video does not exist");
      return;
    }

    if (videoExistsInPlaylist(playlistName, videoId)) {
      System.out.println("Cannot add video to " + playlistName + ": Video already added");
      return;
    }

    Playlist playlistToBeUpdated = getPlaylistByName(playlistName);
    Video videoToBeAdded = videoLibrary.getVideo(videoId);
    playlistToBeUpdated.addVideo(videoToBeAdded);
    System.out.println("Added video to " + playlistName + ": " + videoToBeAdded.getTitle());

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