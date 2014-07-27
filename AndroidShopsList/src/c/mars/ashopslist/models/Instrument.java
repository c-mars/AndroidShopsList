package c.mars.ashopslist.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Instrument {
	public Integer id;
	public String brand;
	public String model;	
	public TYPE type;
	public Double price;
	public Integer quantity;
	
	public enum TYPE {
		GUITAR,
		PIANO,
		INSTRUMENT
	}
	
	public Instrument(String brand, String model, TYPE type, Double price) {
		this.id = -1;
		this.brand = brand;
		this.model = model;
		this.type = type;
		this.price = price;
		this.quantity = 0;
	}
	
	public Instrument(Integer id, String brand, String model, TYPE type, Double price, Integer quantity) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.type = type;
		this.price = price;
		this.quantity = quantity;
	}
	
	public static class TAGS {
		public static final String INSTRUMENT = "instrument";
		public static final String QUANTITY = "quantity";
		
		public static final String ID = "id";
		public static final String BRAND = "brand";
		public static final String MODEL = "model";
		public static final String TYPE = "type";
		public static final String PRICE = "price";
		
		public static final String GUITAR = "GUITAR";
		public static final String PIANO = "PIANO";
		
		public static final String STOREID = "storeId";
	}
	
//	This constructor can fail with JSONException that must be handled somewhere in calling code
	public Instrument(JSONObject jsonInstrument) throws JSONException {
		JSONObject jsonInstrumentDetails = jsonInstrument.getJSONObject(TAGS.INSTRUMENT);
		if (jsonInstrumentDetails != null) {
			id = jsonInstrumentDetails.getInt(TAGS.ID);
			brand = jsonInstrumentDetails.getString(TAGS.BRAND);
			model = jsonInstrumentDetails.getString(TAGS.MODEL);
			
			String typeStr = jsonInstrumentDetails.getString(TAGS.TYPE);
			if (typeStr.equals(TAGS.GUITAR)) {
				type = TYPE.GUITAR;
			} else if (typeStr.equals(TAGS.PIANO)) {
				type = TYPE.PIANO;
			} else {
				type = TYPE.INSTRUMENT;
			}
			price = jsonInstrumentDetails.getDouble(TAGS.PRICE);
		}
		
		quantity = jsonInstrument.getInt(TAGS.QUANTITY);
	}

	public String getType() {
		switch (type) {
		case GUITAR:
			return "Guitar";
		case PIANO:
			return "Piano";
		case INSTRUMENT:
		default:
			return "Instrument";
		}
	}
}
