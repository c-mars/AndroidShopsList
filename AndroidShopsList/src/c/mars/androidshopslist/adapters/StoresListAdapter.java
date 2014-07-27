package c.mars.androidshopslist.adapters;

import c.mars.androidshopslist.models.Store;
import c.mars.androidshopslist.R;

import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StoresListAdapter extends ArrayAdapter<Store> {

	private final Context context;
	private ArrayList<Store> values;
	
	private class ViewHolder {
		TextView name;
		TextView address;
		TextView phone;
	}
	
	public StoresListAdapter(Context context, ArrayList<Store> values) {
		super(context, R.layout.item_store, values);
		
		this.context = context;
		this.values = values;
	}
	
	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    
	    ViewHolder holder;
	    
	    if (convertView == null) {
	    	convertView = inflater.inflate(R.layout.item_store, parent, false);
	    	holder = new ViewHolder();
	    	holder.name = (TextView) convertView.findViewById(R.id.name);
	    	holder.address = (TextView) convertView.findViewById(R.id.address);
	    	holder.phone = (TextView) convertView.findViewById(R.id.phone);
	    	
	    	convertView.setTag(holder);
	    } else {
	    	holder = (ViewHolder)convertView.getTag();
	    }
	    
	    Store shop = values.get(position);
	    if (shop != null) {
	    	holder.name.setText(shop.name);
	    	holder.address.setText(shop.address);
	    	holder.phone.setText(context.getString(R.string.phoneLabel) + " " + shop.phone);
	    }
	    
	    return convertView;
	  }
	
	public void addAll(Collection<? extends Store> collection) {
		values.addAll(collection);
	}
	
	public void clear() {
		values.clear();
	}
	
	public Store getItem(int position) {
		return values.get(position);
	}
}
