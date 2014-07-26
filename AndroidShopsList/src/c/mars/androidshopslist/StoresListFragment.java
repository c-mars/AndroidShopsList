package c.mars.androidshopslist;

import java.util.ArrayList;
import java.util.Arrays;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import c.mars.androidshopslist.adapters.StoresListAdapter;
import c.mars.androidshopslist.models.Store;

public class StoresListFragment extends ListFragment {
	
	private StoresListAdapter adapter;

	@Override  
	public void onListItemClick(ListView l, View v, int position, long id) {  

	}  

	@Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {  
		
		ArrayList<Store> shops = new ArrayList<Store>( Arrays.asList(
				new Store("Shop1", "Moscow", "2375932573"),
				new Store("Shop2", "New York", "769753697"),
				new Store("Shop3", "Los Angeles", "353293253425")
				));
		adapter = new StoresListAdapter(inflater.getContext(), shops);

		
		
		setListAdapter(adapter);  
		
		return super.onCreateView(inflater, container, savedInstanceState);  
	} 
	
}
