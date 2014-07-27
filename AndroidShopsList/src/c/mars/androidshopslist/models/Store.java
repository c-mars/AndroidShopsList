package c.mars.androidshopslist.models;

import org.json.JSONException;
import org.json.JSONObject;

import c.mars.androidshopslist.Globals;
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
	}
	
	public Store (String name, String address, String phone, Location location) {
		this(name, address, phone);
		this.location = location;
	}
	
	public class TAGS {
		public static final String NAME = "name";
		public static final String PHONE = "phone";
		public static final String ADDRESS = "address";
		public static final String LOCATION = "location";
		public static final String LATITUDE = "latitude";
		public static final String LONGITUDE = "longitude";
		
		public static final String STORE = "store";
	}
	
//	This constructor can fail with JSONException that must be catched somewhere in calling code
	public Store(JSONObject jsonStore) throws JSONException {
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
		final int FIELD_COUNT = 5;
        String[] data = new String[FIELD_COUNT];

        in.readStringArray(data);
        name = data[0];
		phone = data[1];
		address = data[2];
		location.setLatitude(Double.parseDouble(data[3]));
		location.setLongitude(Double.parseDouble(data[4]));
    }
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
//		This is fast method, without keys - so pay attention for order of values in array (!) to support updates in this class
		dest.writeStringArray(new String[] {
				name,
				phone,
				address,
				Double.toString(location.getLatitude()),
				Double.toString(location.getLongitude()) 
				});
	}
}
