package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

public class BaseApiHelper {
	private static final String TAG = "BaseApiHelper";
	
	public interface HttpResponseHandler {
		public void onHttpResponse(String response);
	}
	
	protected void getHttpResponse(String urlString, HttpResponseHandler responseHandler) {
		new DownloadTask(responseHandler).execute(urlString);
	}
	
	private class DownloadTask extends AsyncTask<String, Void, String> {
        
		private HttpResponseHandler responseHandler;
		
		public DownloadTask(HttpResponseHandler responseHandler) {
        	this.responseHandler = responseHandler;
        }
		
		@Override
        protected String doInBackground(String... urls) {
            return downloadUrl(urls[0]);
        }
        
        @Override
        protected void onPostExecute(String result) {
        	if (responseHandler != null) {
        		responseHandler.onHttpResponse(result);
        	}
       }
    }
	
	private String downloadUrl(String urlString) {
	    InputStream is = null;
	    String contentAsString = null;
	    
	    try {
	        URL url = new URL(urlString);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setReadTimeout(10000 /* milliseconds */);
	        conn.setConnectTimeout(15000 /* milliseconds */);
	        conn.setRequestMethod("GET");
	        conn.setDoInput(true);
	        // Starts the query
	        conn.connect();
	        int response = conn.getResponseCode();
	        Log.d(TAG, "The response is: " + response);
	        is = conn.getInputStream();

	        // Convert the InputStream into a string
	        contentAsString = readIt(is);
	        
	    } catch (UnsupportedEncodingException ex) {
	    	ex.printStackTrace();
	    } catch (IOException ex ) {
	    	ex.printStackTrace();
		} finally {
	        if (is != null) {
	            try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        } 
	    }
	    
	    return contentAsString;
	}
	
	public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        reader.close();
	    
	    return out.toString();
	}
}
