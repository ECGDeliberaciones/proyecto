package utilities;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.json.JSONObject;

import com.google.common.collect.Multimap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class RESTClient {

	public JSONObject get(String url) {
		return get(url, null, null);
	}
	
	public JSONObject get(String url, Multimap<String, String> request) {
		return get(url, request, null);
	}
	
	public JSONObject get(String url, Multimap<String, String> request, Map<String, String> headers) {
		return request(url, request, headers, "get");
	}

	public JSONObject post(String url) {
		return post(url, null, null);
	}
	
	public JSONObject post(String url, Multimap<String, String> request) {
		return post(url, request, null);
	}
	
	public JSONObject post(String url, Multimap<String, String> request, Map<String, String> headers) {
		return request(url, request, headers, "post");
	}

	private JSONObject request(String url, Multimap<String, String> request, Map<String, String> headers, String type) {
		// Build request URL
		String header = url;
		if (request != null) {
			header += "?";
			boolean firstAttribute = true;
			for (String key : request.keySet()) {
				for (String value: request.get(key)) {
					if (firstAttribute) {
						header+=key+"="+value;
						firstAttribute = false;
					} else {
						header+="&"+key+"="+value;
					}
				}
			}
		}
		
		// Encode URL
		try {
			header = URIUtil.encodeQuery(header);
		} catch (URIException e) {
			e.printStackTrace();
		}
		
		// Create request
		ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    WebResource service = client.resource(header);
	    WebResource.Builder serviceBuilder = service.accept(MediaType.APPLICATION_JSON);
	    
	    // Set headers
	    if (headers != null) {
		    for (String key: headers.keySet()) {
		    	serviceBuilder = serviceBuilder.header(key, headers.get(key));
		    }
	    }
	    
	    // Send request
	    JSONObject result = null;
	    if (type == "get") {
	    	result = new JSONObject(serviceBuilder.get(String.class));
	    }
	    if (type == "post") {
	    	result = new JSONObject(serviceBuilder.post(String.class));
	    }
		
	    return result;
	}

	 public static void main(String[] args) {
		 RESTClient rc = new RESTClient();
		// rc.get("RESTClient");
		 Multimap<String, String> res = null;
		 System.out.println(rc.get("https://graph.facebook.com/search?q=java&type=post", res));	
		 }
	
}
