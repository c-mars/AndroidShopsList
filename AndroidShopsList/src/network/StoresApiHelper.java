package network;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StoresApiHelper extends BaseApiHelper {
	private static final String storesUrl = "http://aschoolapi.appspot.com/stores";
	
	public static void getStores() {
		
		getJSON(storesUrl, 0);
//		URL url;
//	    try {
//	        url = new URL(storesUrl);
//
//	        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//	        InputStream inStream = urlConnection.getInputStream();
//	        InputStreamReader reader = new InputStreamReader(inStream);
//
//	        int data = reader.read();
//	        while (data != -1) {
//	            char current = (char) data;
//	            data = reader.read();
//	            System.out.print(current);
//	        }
//	    } catch (Exception e) {
//	        // TODO Auto-generated catch block
//	        e.printStackTrace();
//	    }
	}
}
