package c.mars.ashopslist.fragments;

import c.mars.androidshopslist.MainActivity;
import c.mars.androidshopslist.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class StoresMapFragment extends Fragment {

	private View view;
	private GoogleMap map;
	private Double latitude, longitude;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}

		view = (RelativeLayout) inflater.inflate(R.layout.fragment_map, container, false);
		latitude = 26.78;
		longitude = 72.56;

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
		map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("My Home").snippet("Home Address"));
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,
				longitude), 12.0f));
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

//	@Override
//	public void onDestroyView() {
//		super.onDestroyView();
//		if (map != null) {
//			MainActivity activity = (MainActivity)getActivity();
//			FragmentManager fragManager = activity.getSupportFragmentManager();
//			fragManager.beginTransaction()
//			.remove(fragManager.findFragmentById(R.id.listFragment)).commit();
//			map = null;
//		}
//	}
}
