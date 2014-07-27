package c.mars.androidshopslist.adapters;

import java.util.ArrayList;
import java.util.Collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import c.mars.androidshopslist.R;
import c.mars.androidshopslist.models.Instrument;

public class InstrumentsListAdapter extends ArrayAdapter<Instrument> {

	private final Context context;
	private ArrayList<Instrument> values;
	
	private class ViewHolder {
		TextView name;
		TextView price;
		TextView quantity;
	}
	
	public InstrumentsListAdapter(Context context, ArrayList<Instrument> values) {
		super(context, R.layout.item_instrument, values);
		
		this.context = context;
		this.values = values;
	}

	@Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    
	    ViewHolder holder;
	    
	    if (convertView == null) {
	    	convertView = inflater.inflate(R.layout.item_instrument, parent, false);
	    	holder = new ViewHolder();
	    	holder.name = (TextView) convertView.findViewById(R.id.name);
	    	holder.price = (TextView) convertView.findViewById(R.id.price);
	    	holder.quantity = (TextView) convertView.findViewById(R.id.quantity);
	    	
	    	convertView.setTag(holder);
	    } else {
	    	holder = (ViewHolder)convertView.getTag();
	    }
	    
	    Instrument instrument = values.get(position);
	    if (instrument != null) {
	    	String name = instrument.getType() + " " + instrument.brand + " " + instrument.model;
	    	holder.name.setText(name);
	    	holder.price.setText(Double.toString(instrument.price));
	    	holder.quantity.setText(Integer.toString(instrument.quantity));
	    }
	    
	    return convertView;
	  }
	
	public void addAll(Collection<? extends Instrument> collection) {
		values.addAll(collection);
	}
	
	public void clear() {
		values.clear();
	}
	
	public Instrument getItem(int position) {
		return values.get(position);
	}
}
