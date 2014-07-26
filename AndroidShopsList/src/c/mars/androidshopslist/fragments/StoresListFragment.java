package c.mars.androidshopslist.fragments;

import java.util.ArrayList;
import java.util.Arrays;

import network.BaseApiHelper.HttpResponseHandler;
import network.NetworkUtils;
import network.StoresApiHelper;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import c.mars.androidshopslist.MainActivity;
import c.mars.androidshopslist.R;
import c.mars.androidshopslist.adapters.StoresListAdapter;
import c.mars.androidshopslist.models.Store;

public class StoresListFragment extends ListFragment {
	
	private static final String TAG = "StoresListFragment";
	private StoresListAdapter adapter;
	private StoresApiHelper apiHelper;

	@Override  
	public void onListItemClick(ListView l, View v, int position, long id) {  
//  TODO: implement opening detailed fragment
	}  

	@Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {  
		
		ArrayList<Store> shops = new ArrayList<Store>( Arrays.asList(
				new Store("Shop1", "Moscow", "2375932573"),
				new Store("Shop2", "New York", "769753697"),
				new Store("Shop3", "Los Angeles", "353293253425")
				));
		adapter = new StoresListAdapter(inflater.getContext(), shops);
		apiHelper = new StoresApiHelper();
		
		MainActivity activity = (MainActivity) getActivity();
		if (NetworkUtils.checkConnection(activity)) {
			apiHelper.getStores( new HttpResponseHandler() {
				
				@Override
				public void onHttpResponse(String response) {
					Log.d(TAG, response);
//					adapter = new StoresListAdapter(inflater.getContext(), shops);
				}
			});
		} else {
			Toast.makeText(activity, getString(R.string.connectionError), Toast.LENGTH_SHORT).show();
		}
		
		setListAdapter(adapter);  
		
		return super.onCreateView(inflater, container, savedInstanceState);  
	} 
	
}
