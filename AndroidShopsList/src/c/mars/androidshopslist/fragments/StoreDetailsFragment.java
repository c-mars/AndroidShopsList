package c.mars.androidshopslist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import c.mars.androidshopslist.MainActivity;
import c.mars.androidshopslist.R;
import c.mars.androidshopslist.models.Instrument;
import c.mars.androidshopslist.models.Store;

public class StoreDetailsFragment extends Fragment {
	
	private Store store;
	
	@Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {  
		View rootView = inflater.inflate(R.layout.fragment_storedetails, container, false);    
		
		Bundle args = getArguments();
		if (args != null) {
			store = args.getParcelable(Store.TAGS.STORE);
			
			TextView name = (TextView)rootView.findViewById(R.id.name);
			name.setText(store.name);
			TextView phone = (TextView)rootView.findViewById(R.id.phone);
			phone.setText(store.phone);
			TextView address = (TextView)rootView.findViewById(R.id.address);
			address.setText(store.address);
			
			Bundle fragArgs = new Bundle();
			fragArgs.putInt(Instrument.TAGS.STOREID, store.id);
			MainActivity activity = (MainActivity)getActivity();
			activity.showInstrumentsListFragment(fragArgs);
		} else {
			Toast.makeText(getActivity(), getString(R.string.storeDetailsReadError), Toast.LENGTH_SHORT).show();;
		}
		
		return rootView;  
	} 
}
