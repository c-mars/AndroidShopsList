package c.mars.androidshopslist;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import c.mars.ashopslist.fragments.InstrumentsListFragment;
import c.mars.ashopslist.fragments.StoreDetailsFragment;
import c.mars.ashopslist.fragments.StoresListFragment;
import c.mars.ashopslist.fragments.StoresMapFragment;
import c.mars.ashopslist.models.Store;


public class MainActivity extends ActionBarActivity {
	
	private ArrayList<Store> stores;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        StoresListFragment newFragment = new StoresListFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.mainFragment, newFragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void showDetailsFragment(Bundle args) {
    	StoreDetailsFragment detailsFragment = new StoreDetailsFragment();
		detailsFragment.setArguments(args);
    	FragmentManager fragmentManager = getSupportFragmentManager();
	    fragmentManager.beginTransaction()
	    		.replace(R.id.mainFragment, detailsFragment)
	    		.addToBackStack(null)
	    		.commit(); 
    }
    
    public void showInstrumentsListFragment(Bundle args) {
    	InstrumentsListFragment instrumentsListFragment = new InstrumentsListFragment();
		instrumentsListFragment.setArguments(args);
    	FragmentManager fragmentManager = getSupportFragmentManager();
	    fragmentManager.beginTransaction()
	    		.replace(R.id.listFragment, instrumentsListFragment)
	    		.commit();
    }
    
    public void showMapFragment(Bundle args) {
    	StoresMapFragment mapFragment = new StoresMapFragment();
    	args.putParcelableArrayList(Store.TAGS.ALL_STORES, stores);
		mapFragment.setArguments(args);
    	FragmentManager fragmentManager = getSupportFragmentManager();
	    fragmentManager.beginTransaction()
	    		.replace(R.id.listFragment, mapFragment)
	    		.commit();
    }
    
    public void updateInstrumentsCount(Integer count) {
    	Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.mainFragment);
    	if (fragment instanceof StoreDetailsFragment) {
    		StoreDetailsFragment storeDetailsFragment = (StoreDetailsFragment)fragment;
    		storeDetailsFragment.updateInstrumentsCount(count);
    	}
    }
    
    public void setStores(ArrayList<Store> stores) {
    	this.stores = stores;
    }
}
