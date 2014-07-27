package c.mars.ashopslist.fragments;

import java.util.ArrayList;

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
import c.mars.ashopslist.adapters.InstrumentsListAdapter;
import c.mars.ashopslist.models.Instrument;
import c.mars.ashopslist.network.InstrumentsApiHelper;
import c.mars.ashopslist.network.NetworkUtils;
import c.mars.ashopslist.network.BaseApiHelper.HttpResponseHandler;

public class InstrumentsListFragment extends ListFragment {

private static final String TAG = "StoresListFragment";
	
	private Integer storeId;
	private ArrayList<Instrument> instruments= new ArrayList<Instrument>();
	private InstrumentsListAdapter adapter;
	private InstrumentsApiHelper apiHelper = new InstrumentsApiHelper();
	private Context context;
	
	@Override  
	public void onListItemClick(ListView l, View v, int position, long id) {  

	} 
	
//	Update or create adapter if necessary
	private void updateInstrumentsAdapter() {
		if (adapter == null) {
			adapter = new InstrumentsListAdapter(getActivity(), instruments);
			setListAdapter(adapter);
		} 
			else {
			adapter.clear();
			adapter.addAll(instruments);
		}
		
		MainActivity activity = (MainActivity) getActivity();
		activity.updateInstrumentsCount(instruments.size()); ;
		adapter.notifyDataSetChanged();
	}
	
	@Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {  
		context = inflater.getContext();
//		Custom layout to include header in list fragment
		View view = inflater.inflate(R.layout.fragment_instrumentslist, container, false);
		
		Bundle args = getArguments();
		if (args != null && args.containsKey(Instrument.TAGS.STOREID)) {
			storeId = args.getInt(Instrument.TAGS.STOREID);
			
			if (NetworkUtils.checkConnection(context)) {
				
//				Because we don't need pagination or multiple calls to handler, we create it in-place
				apiHelper.getInstruments(storeId, new HttpResponseHandler() {
					
					@Override
					public void onHttpResponse(String response) {
						Log.d(TAG, response);
						instruments = apiHelper.parseInstrumentsResponse(response);
						updateInstrumentsAdapter();
					}
				});
			} else {
				Toast.makeText(context, getString(R.string.connectionError), Toast.LENGTH_SHORT).show();
			}
			
		} else {
			Toast.makeText(context, getString(R.string.unknownStoreId), Toast.LENGTH_SHORT).show();
		}
		
		return view; //super.onCreateView(inflater, container, savedInstanceState);  
	} 
	
}
