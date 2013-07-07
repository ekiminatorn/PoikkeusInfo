package com.gmail.ekstemi.poikkeusinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PoikkeusInfoSingleMenuItemActivity  extends Activity {
	
	// XML node keys
	static final String KEY_INFO = "INFO";
	static final String KEY_TEXT = "TEXT";
	static final String KEY_LINE = "LINE";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get XML values from previous intent
        String name = in.getStringExtra(KEY_LINE); //<-- Distruption message
        String cost = in.getStringExtra(KEY_INFO); //<-- Nothing
        String description = in.getStringExtra(KEY_INFO); //<-- Nothing
        
        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.name_label);
        TextView lblCost = (TextView) findViewById(R.id.cost_label);
        TextView lblDesc = (TextView) findViewById(R.id.description_label);
        
        lblName.setText(name);
        lblCost.setText(cost);
        lblDesc.setText(description);
    }
}
