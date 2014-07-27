package network;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import c.mars.androidshopslist.Globals;
import c.mars.androidshopslist.models.Instrument;

public class InstrumentsApiHelper extends BaseApiHelper {

	public void getInstruments(Integer storeId, HttpResponseHandler responseHandler) {
		String instrumentsUrl = Globals.STORES_URL + "/" + storeId.toString() + "/instruments";
		getHttpResponse(instrumentsUrl, responseHandler);
	}
	
	public ArrayList<Instrument> parseInstrumentsResponse(String response) {
		ArrayList<Instrument> instruments = new ArrayList<Instrument>();

		try {
			JSONArray  jsonArray = new JSONArray(response);

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonInstrument = jsonArray.getJSONObject(i);
				Instrument instrument = new Instrument(jsonInstrument);
				instruments.add(instrument);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return instruments;
	}
	
}
