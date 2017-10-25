package com.cloudadvisory.googlebookapipractice;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

/**
 * Created by pmeno on 11/07/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    /***
     * constructs a new {@link BookAdapter}.
     *
     * @param context of the app;
     * @param books is the list of books, which is the data source of the adapter
     */
    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    /***
     * returns a list item view that displays information about the earthquake at the given position
     * in the list of books.
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //check if there is an existing list item view(called convertView) that we can reuse,
        //otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list_item, parent, false);
        }

        //find the earthquake at the given position in the list of books
        Book currentBook = getItem(position);

        //Set the title variable from the getTitle method
        String title = currentBook.getTitle();
        //find the TextView with view ID title
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.title_textview);
        //display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(title);

        //format the authors into readable text


        //Set the title variable from the getTitle method
        String author = currentBook.getAuthor();
        //find the textview with view ID location
        TextView primaryTitleView = (TextView) listItemView.findViewById(R.id.author_textview);
        //display the location of the current earthquake in that TextView
        primaryTitleView.setText(author);

        //Set the Image URL from the getURL method
        new DownloadImageTask((ImageView) listItemView.findViewById(R.id.thumbnail_imageview))
                .execute(currentBook.getImageUrl());


        return listItemView;

    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}