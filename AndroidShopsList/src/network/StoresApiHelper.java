package network;


public class StoresApiHelper extends BaseApiHelper {
	private static final String storesUrl = "http://aschoolapi.appspot.com/stores";
	
	public void getStores() {
		getHttpResponse(storesUrl);
	}
	
	
}
