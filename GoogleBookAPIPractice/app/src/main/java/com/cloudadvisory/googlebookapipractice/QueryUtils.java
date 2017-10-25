package com.cloudadvisory.googlebookapipractice;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.cloudadvisory.googlebookapipractice.MainActivity.LOG_TAG;

/**
 * Created by pmeno on 11/07/2017.
 */

public final class QueryUtils {

    //author string
    private static String mCurrentBookAuthors;

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {

    }

    /**
     * Return a list of {@link Book} objects that has been built up from
     * parsing a JSON response.
     */

    public static ArrayList<Book> extractItemFromJson(String BookJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(BookJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding Books to
        ArrayList<Book> Books = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Book objects with the corresponding data.
            //storing Json response as a JSON object for parsing later
            JSONObject baseJsonResponse = new JSONObject(BookJSON);

            //extract the JSONArray associated with the key called Features,
            // which represents a list of features (or Books).
            JSONArray bookArray = baseJsonResponse.getJSONArray("items");

            //for each Book in the Book array, create an {@link Book} object
            for (int bookPosition = 0; bookPosition < bookArray.length(); bookPosition++) {

                //extracting the details of each Book as per the position of the Features
                // array
                JSONObject currentBook = bookArray.getJSONObject(bookPosition);

                //storing the volumeinfo object as a json object
                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");

                //storing title as a string.
                String title = volumeInfo.getString("title");

                //storing Authors as a JSONArray.
                JSONArray authorArray = volumeInfo.getJSONArray("authors");

                //StringBuilder is used to store more than one author in the same
                // mCurrentBookAuthors String variable
                StringBuilder currentBookAuthors = new StringBuilder();

                //for loop to add each author to the String
                for (int authorPosition = 0;
                     authorPosition < authorArray.length();
                     authorPosition++) {

                    String currentAuthor = authorArray.getString(authorPosition);
                    if (authorPosition != 0) {
                        currentBookAuthors.append(", ");
                    }
                    currentBookAuthors.append(currentAuthor);

                    mCurrentBookAuthors = currentBookAuthors.toString();
                }

                //storing the imagelinks object as a json object
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");

                //storing URL of smallThumbnail image of book as a string.
                String imageUrl = imageLinks.getString("smallThumbnail");


                Book book = new Book(title, mCurrentBookAuthors, imageUrl);
                Books.add(book);

                Log.i(LOG_TAG, "Json Parsing title " + title);
                Log.i(LOG_TAG, "Json Parsing author array " + authorArray);
                Log.i(LOG_TAG, "Json Parsing authors formatted " + mCurrentBookAuthors);
                Log.i(LOG_TAG, "Json Parsing image url " + imageUrl);

            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the Book JSON results", e);
        }

        // Return the list of Books
        return Books;
    }

    /**
     * Query the USGS dataset and return a list of {@link Book} objects.
     */
    public static List<Book> fetchBookData(String requestUrl) {

        Log.i(LOG_TAG, "Test: fetchBookData called...");
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Books}
        List<Book> Books = extractItemFromJson(jsonResponse);

        // Return the list of {@link Books}
        return Books;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the Book JSON results.", e);

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}