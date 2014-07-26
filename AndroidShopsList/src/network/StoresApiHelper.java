package network;

import network.BaseApiHelper.HttpResponseHandler;


public class StoresApiHelper extends BaseApiHelper {
	private static final String storesUrl = "http://aschoolapi.appspot.com/stores";
	
	public void getStores(HttpResponseHandler responseHandler) {
		getHttpResponse(storesUrl, responseHandler);
	}
	
	
}
