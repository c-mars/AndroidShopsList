package c.mars.androidshopslist.adapters;

import c.mars.androidshopslist.models.Shop;
import c.mars.androidshopslist.R;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ShopsListAdapter extends ArrayAdapter<Shop> {

	private final Context context;
	private ArrayList<Shop> values;
	
	private class ViewHolder {
		TextView name;
		TextView address;
		TextView phone;
	}
	
	public ShopsListAdapter(Context context, ArrayList<Shop> values) {
		super(context, R.layout.row_shop, values);
		
		this.context = context;
		this.values = values;
	}
	
	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    
	    ViewHolder holder;
	    
	    if (convertView == null) {
	    	convertView = inflater.inflate(R.layout.row_shop, parent, false);
	    	holder = new ViewHolder();
	    	holder.name = (TextView) convertView.findViewById(R.id.name);
	    	holder.address = (TextView) convertView.findViewById(R.id.address);
	    	holder.phone = (TextView) convertView.findViewById(R.id.phone);
	    	
	    	convertView.setTag(holder);
	    } else {
	    	holder = (ViewHolder)convertView.getTag();
	    }
	    
	    Shop shop = values.get(position);
	    if (shop != null) {
	    	holder.name.setText(shop.name);
	    	holder.address.setText(shop.address);
	    	holder.phone.setText(context.getString(R.string.phone_label) + " " + shop.phone);
	    }
	    
	    return convertView;
	  }
}
