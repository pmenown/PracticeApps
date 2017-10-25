package com.cloudadvisory.googlebookapipractice;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static com.cloudadvisory.googlebookapipractice.R.id.loading_bar;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    public static final String LOG_TAG = MainActivity.class.getName();

    /**
     * Search element of URL
     **/
    public static String searchElement;

    /**
     * URL for Book data from the GOOGLE dataset
     */
    public static String googleRequestURL;
    //

    /**
     * Adapter for the list of Books
     */
    private BookAdapter mAdapter;

    /**
     * Constant value for the Book loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int BOOK_LOADER_ID = 1;

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Find a reference to the {@link ListView} in the layout**/
        ListView bookListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of books as input
        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(mAdapter);

        if (searchElement != null) {
            Log.i(LOG_TAG, "onResume initloader to start..." + searchElement);
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume " + searchElement);
    }

    public void searchButton(View view) {

        //reseting previous search or error TextViews ready for new search
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.destroyLoader(BOOK_LOADER_ID);

        //setting empty Text View variable init
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        mEmptyStateTextView.setText("");

        //show loading bar when starting search
        ProgressBar loadingBar = (ProgressBar) findViewById(loading_bar);
        loadingBar.setVisibility(View.VISIBLE);

        /** Find a reference to the {@link ListView} in the layout**/
        //setting Empty View to be the text view if comes back as empty
        ListView bookListView = (ListView) findViewById(R.id.list);
        bookListView.setEmptyView(mEmptyStateTextView);

        //init search box text view and get text from EditText to enter into Variable searchElement
        EditText searchTxt = (EditText) findViewById(R.id.search_TextView);
        searchElement = searchTxt.getText().toString();

        //merge search element variable to request URL.
        googleRequestURL = "https://www.googleapis.com/books/v1/volumes?q=" + searchElement + "&orderby=newest&maxresults=10";

        Log.i(LOG_TAG, "ClickButton " + searchElement);
        Log.i(LOG_TAG, "ClickButton " + googleRequestURL);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connMgr.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            //If connected then execute loader task
            Log.i(LOG_TAG, "Test: InitLoader called...");
            // Get a reference to the LoaderManager, in order to interact with loaders.


            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).

            loaderManager.initLoader(BOOK_LOADER_ID, null, this);

        } else {
            //Otherwise display error and hide progress bar
            loadingBar.setVisibility(GONE);
            // Set empty state text to display "No books found."
            mEmptyStateTextView.setText(R.string.no_internet_connection);

        }
    }


    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        Log.i(LOG_TAG, "Test: onCreateLoader called...");

        // Create a new loader for the given URL
        return new BookLoader(this, googleRequestURL);
    }

    /**
     * This method runs on the main UI thread after the background work has been
     * completed. This method receives as input, the return value from the doInBackground()
     * method. First we clear out the adapter, to get rid of book data from a previous
     * query. Then we update the adapter with the new list of books,
     * which will trigger the ListView to re-populate its list items.
     */
    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        ProgressBar loadingBar = (ProgressBar) findViewById(loading_bar);
        loadingBar.setVisibility(GONE);

        Log.i(LOG_TAG, "Test: onLoadFinished called...");
        // Clear the adapter of previous book data
        mAdapter.clear();

        // If there is a valid list of {@link book}, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        } else {
            // Set empty state text to display "No books found."
            mEmptyStateTextView.setText(R.string.no_books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.i(LOG_TAG, "Test: onLoaderReset called...");
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
