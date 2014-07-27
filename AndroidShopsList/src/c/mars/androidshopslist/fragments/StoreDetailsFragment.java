package c.mars.androidshopslist.fragments;

import c.mars.androidshopslist.R;
import c.mars.androidshopslist.models.Store;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;
import android.widget.Toast;

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
		} else {
			Toast.makeText(getActivity(), getString(R.string.storeDetailsReadError), Toast.LENGTH_SHORT).show();;
		}
		
		return rootView;  
	} 
}
