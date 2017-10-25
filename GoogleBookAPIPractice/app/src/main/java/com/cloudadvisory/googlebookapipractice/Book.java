package com.cloudadvisory.googlebookapipractice;

import org.json.JSONArray;

/**
 * Created by pmeno on 11/07/2017.
 */

public class Book {

    /*** Title of the book */
    private String mTitle;

    /*** Author of the book*/
    private String mAuthor;

    /*** Thumbnail of the book*/
    private String mImageUrl;

    /***
     * constructs a new {@link Book} object.
     *
     * @param title is the name of the book
     * @param author is the creator of the book
     */
    public Book(String title, String author, String imageUrl) {
        mTitle = title;
        mAuthor = author;
        mImageUrl = imageUrl;
    }

    /***
     * returns the magnitude of the earthquake
     */
    public String getTitle() {
        return mTitle;
    }

    /***
     * returns the location of the earthquake
     */
    public String getAuthor() {
        return mAuthor;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

}
