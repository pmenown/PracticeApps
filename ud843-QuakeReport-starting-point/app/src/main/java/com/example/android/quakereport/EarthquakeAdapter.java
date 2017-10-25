package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by pmeno on 18/06/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /***
     * constructs a new {@link EarthquakeAdapter}.
     *
     * @param context of the app;
     * @param earthquakes is the list of earthquakes, which is the data source of the adapter
     */


    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    /***
     * returns a list item view that displays information about the earthquake at the given position
     * in the list of earthquakes.
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //check if there is an existing list item view(called convertView) that we can reuse,
        //otherwise, if convertView is null, then inflate a new list item layout.
        View listitemview = convertView;
        if (listitemview == null) {
            listitemview = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        //find the earthquake at the given position in the list of earthquakes
        Earthquake currentEarthquake = getItem(position);

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        //format the mag double variable to "deciminal to one place".
        DecimalFormat decimalMag = new DecimalFormat("0.0");
        String mag = decimalMag.format(currentEarthquake.getMagnitude());

        //find the TextView with view ID magnitude
        TextView magnitudeView = (TextView) listitemview.findViewById(R.id.magnitude);
        //display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(mag);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        //split the Location string into 2 strings to be entered into two separate textviews.
        String of = "of ";
        String offset;

        int stringSplit = currentEarthquake.getLocation().indexOf(of) + 3;
        if (stringSplit < 4) {
            stringSplit = 0;
        }

        String primaryLocation = currentEarthquake.getLocation().substring(stringSplit);

        if (stringSplit < 4) {
            offset = "Near the";
        } else {
            offset = currentEarthquake.getLocation().substring(0, stringSplit);
        }

        //find the textview with view ID proximity
        TextView offsetView = (TextView) listitemview.findViewById(R.id.location_offset);
        //display the proximity of the current earthquake in that TextView
        offsetView.setText(offset);

        //find the textview with view ID location
        TextView primaryLocationView = (TextView) listitemview.findViewById(R.id.primary_location);
        //display the location of the current earthquake in that TextView
        primaryLocationView.setText(primaryLocation);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listitemview.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listitemview.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        return listitemview;

    }

    private int getMagnitudeColor(double magnitude) {

        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
