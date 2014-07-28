package c.mars.ashopslist.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import c.mars.androidshopslist.MainActivity;
import c.mars.androidshopslist.R;
import c.mars.ashopslist.models.Store;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StoresMapFragment extends Fragment {

	private View view;
	private GoogleMap map;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}

		view = (RelativeLayout) inflater.inflate(R.layout.fragment_map, container, false);

		setUpMapIfNeeded();

		return view;
	}

	public void setUpMapIfNeeded() {
		if (map == null) {
			MainActivity activity = (MainActivity)getActivity();
			FragmentManager fragManager = activity.getSupportFragmentManager();
			map = ((SupportMapFragment) fragManager.findFragmentById(R.id.map)).getMap();
			if (map != null)
				setUpMap();
		}
	}

	private void setUpMap() {
		map.setMyLocationEnabled(true);
		
		LatLng mainStoreLocation = null;
		
		Bundle args = getArguments();
		if (args != null && args.containsKey(Store.TAGS.STORE)) {
			Store store = args.getParcelable(Store.TAGS.STORE);
			mainStoreLocation = store.location;
			
			map.addMarker(new MarkerOptions()
			.position(mainStoreLocation)
			.title(store.name)
			.snippet(store.address)
			.icon(BitmapDescriptorFactory
		              .fromResource(R.drawable.guitar_big)));
		}
		
		if (args != null && args.containsKey(Store.TAGS.ALL_STORES)) {
			ArrayList<Store> stores = args.getParcelableArrayList(Store.TAGS.ALL_STORES);
			for (Store store : stores) {
				LatLng location = store.location;
				
//				Don't override main location
				if (mainStoreLocation == null || !location.equals(mainStoreLocation)) {
				
					map.addMarker(new MarkerOptions()
					.position(location)
					.title(store.name)
					.snippet(store.address)
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.guitar_small)));
				}
			}
		}
		
//		Zoom to current store
		if (mainStoreLocation != null) {
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(mainStoreLocation, 12.0f));
		}
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
		if (map != null)
			setUpMap();

		if (map == null) {
			MainActivity activity = (MainActivity)getActivity();
			FragmentManager fragManager = activity.getSupportFragmentManager();
			map = ((SupportMapFragment)fragManager.findFragmentById(R.id.listFragment)).getMap();
			if (map != null)
				setUpMap();
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
//		if (map != null) {
//			MainActivity activity = (MainActivity)getActivity();
//			FragmentManager fragManager = activity.getSupportFragmentManager();
//			fragManager.beginTransaction()
//			.remove(fragManager.findFragmentById(R.id.listFragment)).commit();
//			map = null;
//		}
	}
}
