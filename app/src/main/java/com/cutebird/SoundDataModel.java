package com.cutebird;

import android.database.Cursor;

/**
 * Created by islam on 3/29/2016.
 */
public class SoundDataModel {
    private static SoundDataModel instance;
    String artist, album, title, id;
  /*  MediaStore.Audio.Media.ARTIST,
    MediaStore.Audio.Media.ALBUM,
    MediaStore.Audio.Media.TITLE,
    MediaStore.Audio.Media._ID,
    MediaStore.Audio.Media._ID},*/

    public SoundDataModel(Cursor cursor) {
        try {
            //  setArtist(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
            int nameIndex = cursor.getColumnIndexOrThrow("name");
            //  long id = cursor.getLong(idIndex);
            String name = cursor.getString(nameIndex);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SoundDataModel getInstance(Cursor cursor) {
        if (instance == null) {
            instance = new SoundDataModel(cursor);
        }
        return instance;
    }

    public static SoundDataModel getInstance() {
        return instance;
    }

    public static void setInstance(SoundDataModel instance) {
        SoundDataModel.instance = instance;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
