package c.mars.androidshopslist;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import c.mars.androidshopslist.adapters.ShopsListAdapter;
import c.mars.androidshopslist.models.Shop;

public class ShopsListFragment extends ListFragment {
	
	private ShopsListAdapter adapter;

	@Override  
	public void onListItemClick(ListView l, View v, int position, long id) {  

	}  

	@Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {  
		
		ArrayList<Shop> shops = new ArrayList<Shop>( Arrays.asList(
				new Shop("Shop1", "Moscow", "2375932573"),
				new Shop("Shop2", "New York", "769753697"),
				new Shop("Shop3", "Los Angeles", "353293253425")
				));
		adapter = new ShopsListAdapter(inflater.getContext(), shops);

		setListAdapter(adapter);  
		
		return super.onCreateView(inflater, container, savedInstanceState);  
	} 
	
}
