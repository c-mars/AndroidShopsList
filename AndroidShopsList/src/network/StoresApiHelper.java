package network;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;
import c.mars.androidshopslist.models.Store;


public class StoresApiHelper extends BaseApiHelper {
	private static final String storesUrl = "http://aschoolapi.appspot.com/stores";
	
	public void getStores(HttpResponseHandler responseHandler) {
		getHttpResponse(storesUrl, responseHandler);
	}
	
	public ArrayList<Store> parseStoresResponse(String response) {
		ArrayList<Store> stores = new ArrayList<Store>();

		try {
			JSONArray  jsonArray = new JSONArray(response);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonStore = jsonArray.getJSONObject(i);
				String name = jsonStore.getString("name");
				String phone = jsonStore.getString("phone");
				String address = jsonStore.getString("address");
				JSONObject jsonLocation = jsonStore.getJSONObject("location");
				Double latitude = jsonLocation.getDouble("latitude");
				Double longitude = jsonLocation.getDouble("longitude");
//				Provider now doesn't make reason, so we mark it with server name as provider
				Location location = new Location("http://aschoolapi.appspot.com");
				location.setLatitude(latitude);
				location.setLongitude(longitude);
				Store store = new Store(name, address, phone);
				stores.add(store);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stores;
	}
}
