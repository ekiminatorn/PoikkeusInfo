package com.gmail.ekstemi.poikkeusinfo;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class PoikkeusInfoXMLParsingActivity extends ListActivity {

	// All static variables
	static final String URL = "http://www.poikkeusinfo.fi/xml/v2/fi";
	// XML node keys
	static final String KEY_DISRUPTIONS = "DISRUPTIONS"; // parent node
	static final String KEY_DISRUPTION = "DISRUPTION";
	static final String KEY_INFO = "INFO";
	static String KEY_TEXT = "TEXT";
	static final String KEY_LINE = "LINE";
	static final String KEY_DESC = "LINE";
	static int nan;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		System.out.println("Start of Code!");

		ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

		PoikkeusInfoXMLParser parser = new PoikkeusInfoXMLParser();
		String xml = parser.getXmlFromUrl(URL); // getting XML
		Document doc = parser.getDomElement(xml); // getting DOM element
		doc.getDocumentElement().normalize(); 
		
		
		nan = doc.getElementsByTagName(KEY_DISRUPTION).getLength();	
		System.out.println(nan);

		if (nan == 0){
			System.out.println("True!");
			
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
            map.put(KEY_TEXT, "Ei poikkeusliikennetiedotteita.");
            menuItems.add(map);
		}
			else {	
			
		NodeList nl = doc.getElementsByTagName(KEY_DISRUPTION);
		// looping through all item nodes <item>
		for (int i = 0; i < nl.getLength(); i++) {
		


			

	 
		
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			map.put(KEY_DISRUPTION, parser.getValue(e, KEY_DISRUPTION));
			map.put(KEY_INFO, parser.getValue(e, KEY_INFO));
			map.put(KEY_TEXT, parser.getValue(e, KEY_TEXT));
			map.put(KEY_LINE, parser.getValue(e, KEY_LINE));
			map.put(KEY_DESC, parser.getValue(e, KEY_DESC));


			// adding HashList to ArrayList
			menuItems.add(map); 
		}
			}	

		System.out.println("End of code! ;)");

		// Adding menuItems to ListView
		ListAdapter adapter = new SimpleAdapter(this, menuItems,
				R.layout.list_item,
				new String[] { KEY_LINE, KEY_TEXT, KEY_LINE }, new int[] {
						R.id.name, R.id.desciption, R.id.cost });

		setListAdapter(adapter);
		System.out.println("End of code, really!");
		// selecting single ListView item
		ListView lv = getListView();

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
				String cost = ((TextView) view.findViewById(R.id.cost)).getText().toString();
				String description = ((TextView) view.findViewById(R.id.desciption)).getText().toString();
				
				// Starting new intent
				Intent in = new Intent(getApplicationContext(), PoikkeusInfoSingleMenuItemActivity.class);
				in.putExtra(KEY_LINE, name);
				in.putExtra(KEY_TEXT, cost);
				in.putExtra(KEY_DESC, description);
				startActivity(in);

			}
		});
	
	}
}
