package c.mars.androidshopslist.models;

import org.json.JSONException;
import org.json.JSONObject;

import c.mars.ashopslist.Globals;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

public class Store implements Parcelable {
	public Integer id;
	public String name;
	public String address;
	public String phone;
	public Location location = new Location(Globals.API_URL);
	
	public Store (String name, String address, String phone) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		
		// Default "empty" values
		this.id = -1;
		this.location = null;
	}
	
	public Store (Integer id, String name, String address, String phone, Location location) {
		this(name, address, phone);
		this.id = id;
		this.location = location;
	}
	
	public static class TAGS {
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String PHONE = "phone";
		public static final String ADDRESS = "address";
		public static final String LOCATION = "location";
		public static final String LATITUDE = "latitude";
		public static final String LONGITUDE = "longitude";
		
		public static final String STORE = "store";
	}
	
//	This constructor can fail with JSONException that must be handled somewhere in calling code
	public Store(JSONObject jsonStore) throws JSONException {
		id = jsonStore.getInt(TAGS.ID);
		name = jsonStore.getString(TAGS.NAME);
		phone = jsonStore.getString(TAGS.PHONE);
		address = jsonStore.getString(TAGS.ADDRESS);
		JSONObject jsonLocation = jsonStore.getJSONObject(TAGS.LOCATION);
		Double latitude = jsonLocation.getDouble(TAGS.LATITUDE);
		Double longitude = jsonLocation.getDouble(TAGS.LONGITUDE);
		location.setLatitude(latitude);
		location.setLongitude(longitude);
	}

	public Store(Parcel in){
//		Number of fields in parcel string array
		final int FIELD_COUNT = 6;
        String[] data = new String[FIELD_COUNT];

        in.readStringArray(data);
        id = Integer.parseInt(data[0]);
        name = data[1];
		phone = data[2];
		address = data[3];
		location.setLatitude(Double.parseDouble(data[4]));
		location.setLongitude(Double.parseDouble(data[5]));
    }
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
//		This is fast method, without keys - so pay attention for order of values in array (!) to support updates in this class
		dest.writeStringArray(new String[] {
				Integer.toString(id),
				name,
				phone,
				address,
				Double.toString(location.getLatitude()),
				Double.toString(location.getLongitude()) 
				});
	}
}
