package com.example.android.mewok;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import android.widget.ArrayAdapter;

public class PhrasesAdapter extends ArrayAdapter<Word_Phrases> {

    private int mColorResourceIdp;
    public PhrasesAdapter(Activity context, ArrayList<Word_Phrases> phrases, int mcolorResourceIdp) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, phrases);
        mColorResourceIdp=mcolorResourceIdp;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_phrases, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word_Phrases currentPhrase = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_phrase_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        defaultTextView.setText(currentPhrase.getDefaultTranslation1());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.mewok_phrase_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(currentPhrase.getMiwokTranslation1());

        View textContainer = listItemView.findViewById(R.id.list_phrases);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceIdp);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }


}
