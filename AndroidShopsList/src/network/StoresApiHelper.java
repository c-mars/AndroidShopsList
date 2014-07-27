package network;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import c.mars.androidshopslist.Globals;
import c.mars.androidshopslist.models.Store;


public class StoresApiHelper extends BaseApiHelper {
	
	public void getStores(HttpResponseHandler responseHandler) {
		getHttpResponse(Globals.STORES_URL, responseHandler);
	}
	
	public ArrayList<Store> parseStoresResponse(String response) {
		ArrayList<Store> stores = new ArrayList<Store>();

		try {
			JSONArray  jsonArray = new JSONArray(response);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonStore = jsonArray.getJSONObject(i);
				Store store = new Store(jsonStore);
				stores.add(store);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stores;
	}
}
