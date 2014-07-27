package c.mars.androidshopslist.fragments;

import java.util.ArrayList;
import java.util.Arrays;

import network.BaseApiHelper.HttpResponseHandler;
import network.NetworkUtils;
import network.StoresApiHelper;
import android.content.Context;
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
	
	private ArrayList<Store> stores = new ArrayList<Store>();
	private StoresListAdapter adapter;
	private StoresApiHelper apiHelper;
	
	@Override  
	public void onListItemClick(ListView l, View v, int position, long id) {  
//  TODO: implement opening detailed fragment
		Store selectedStore = adapter.getItem((int) id);
		if (selectedStore != null) {
			MainActivity a = (MainActivity)getActivity();
			Bundle args = new Bundle();
			args.putParcelable(Store.TAGS.STORE, selectedStore);
			a.showDetailsFragment(args);
		}
	} 
	
//	This test data used for first prototyping
	@SuppressWarnings("unused")
	private void fillAdapterWithTestData(Context context) {
		stores = new ArrayList<Store>( Arrays.asList(
				new Store("Shop1", "Moscow", "2375932573"),
				new Store("Shop2", "New York", "769753697"),
				new Store("Shop3", "Los Angeles", "353293253425")
				));
		addAllStoresToAdapter();
	}
	
//	Update or create adapter if necessary
	private void addAllStoresToAdapter() {
		if (adapter == null) {
			adapter = new StoresListAdapter(getActivity(), stores);
			setListAdapter(adapter);
		} 
			else {
			adapter.clear();
			adapter.addAll(stores);
		}
		
		adapter.notifyDataSetChanged();
	}
	
	@Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {  

		apiHelper = new StoresApiHelper();
		
		MainActivity activity = (MainActivity) getActivity();
		if (NetworkUtils.checkConnection(activity)) {
			
//			Because we don't need pagination or multiple calls to handler, we create it in-place
			apiHelper.getStores( new HttpResponseHandler() {
				
				@Override
				public void onHttpResponse(String response) {
					Log.d(TAG, response);
					stores = apiHelper.parseStoresResponse(response);
					addAllStoresToAdapter();
				}
			});
		} else {
			Toast.makeText(activity, getString(R.string.connectionError), Toast.LENGTH_SHORT).show();
		}
		
		return super.onCreateView(inflater, container, savedInstanceState);  
	} 
	
}
