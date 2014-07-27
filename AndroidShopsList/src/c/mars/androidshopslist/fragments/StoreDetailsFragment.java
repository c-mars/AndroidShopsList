package c.mars.androidshopslist.fragments;

import com.google.android.gms.maps.GoogleMap;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import c.mars.androidshopslist.R;
import c.mars.androidshopslist.models.Instrument;
import c.mars.androidshopslist.models.Store;
import c.mars.ashopslist.MainActivity;

public class StoreDetailsFragment extends Fragment {
	
	private Store store;
	private TextView instrumentsCount;
	private GoogleMap map;
	
	@Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {  
		View rootView = inflater.inflate(R.layout.fragment_storedetails, container, false);    
		
		Bundle args = getArguments();
		if (args != null) {
			store = args.getParcelable(Store.TAGS.STORE);
			
			TextView name = (TextView)rootView.findViewById(R.id.name);
			name.setText(store.name);
			TextView phone = (TextView)rootView.findViewById(R.id.phone);
			phone.setText(getString(R.string.phoneLabel) + " " + store.phone);
			phone.setPaintFlags(phone.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
			phone.setTextColor(Color.BLUE);
			phone.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(Intent.ACTION_DIAL); //ACTION_CALL
					intent.setData(Uri.parse("tel:" + store.phone));
					startActivity(intent);
				}
			});
			
			TextView address = (TextView)rootView.findViewById(R.id.address);
			address.setText(store.address);
			
//			Will be updated after instruments response handled
			instrumentsCount = (TextView)rootView.findViewById(R.id.instrumentsCount);
			instrumentsCount.setText("");
			
			Bundle fragArgs = new Bundle();
			fragArgs.putInt(Instrument.TAGS.STOREID, store.id);
			MainActivity activity = (MainActivity)getActivity();
			activity.showInstrumentsListFragment(fragArgs);
		} else {
			Toast.makeText(getActivity(), getString(R.string.storeDetailsReadError), Toast.LENGTH_SHORT).show();;
		}
		
		return rootView;  
	} 
	
	public void updateInstrumentsCount(Integer count) {
		if (instrumentsCount != null) {
			instrumentsCount.setText(getString(R.string.instrumentsCount) + " " + count.toString());
		}
	}
}
