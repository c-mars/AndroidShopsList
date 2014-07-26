package c.mars.androidshopslist.fragments;

import c.mars.androidshopslist.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StoreDetailsFragment extends Fragment {
	
	@Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {  
		View rootView = inflater.inflate(R.layout.fragment_storedetails, container, false);
		            
		return rootView; //super.onCreateView(inflater, container, savedInstanceState);  
	} 
}
