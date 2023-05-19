import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.text.StringEscapeUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Utilities extends javax.swing.text.Utilities {
	private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";
	
	public Map<String, Object> jsonToHashMap() {
		/*
		 * creates a TypeToken object representing the type of a HashMap 
		 * with String keys and Object values, and then retrieves the
		 * java.lang.reflect.Type object representing that type.
		 */
		String j = "{\r\n"
				+ "\"name\" : \"abc\" ,\r\n"
				+ "\"email id \" : [\"abc@gmail.com\",\"def@gmail.com\",\"ghi@gmail.com\"]\r\n"
				+ "}";
		
		Map<String, Object> retMap = new Gson().fromJson(
				j,
				new TypeToken<HashMap<String, Object>>() {}.getType()
				);
		return retMap;
	}
	
    public String getKey() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(fis);
            
            // Retrieve property values
            String key = properties.getProperty("api.key");
            return key;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public void searchAndPrintDogParksByRadius(String latitude, String longitude, int radius) throws URISyntaxException, InterruptedException, IOException{
		
		String myGoogleAPIKey = this.getKey();
		String GoogleFindPlaceAPI = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=Museum%20of%20Contemporary%20Art%20Australia&inputtype=textquery&fields=formatted_address%2Cname%2Crating%2Copening_hours%2Cgeometry&key=" + myGoogleAPIKey;
		
		/*
		 * Sending a Text Search GET request
		 */
		
		HttpRequest getRequest;
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpResponse<String> getResponse;
		String responseBody;
		String fixUnicodeChars;
		
		String textToSerach = "dog+parks";
		String location = latitude + "%2C" + longitude;
		String textSearchUrl = this.buildTextSearchURL(location, radius, myGoogleAPIKey, textToSerach);
		
		getRequest = this.createGetRequest(textSearchUrl);
		getResponse = httpClient.send(getRequest, BodyHandlers.ofString());
		
		responseBody = getResponse.body();
		fixUnicodeChars = StringEscapeUtils.unescapeJava(responseBody);
		System.out.println(fixUnicodeChars);
	}
	
	public String buildTextSearchURL(String location, int radius, String googleApiKey, String textToSerach) {
		String textSearchUrl = "https://maps.googleapis.com/maps/api/place/textsearch/json?"
				+ "location=" + location
				+ "&radius=" + radius
				+ "&key=" + googleApiKey
				+ "&query=" + textToSerach;
		
		return textSearchUrl;
	}
	
	public HttpRequest createGetRequest(String textSearchUrl)throws URISyntaxException {
		HttpRequest getRequest = HttpRequest.newBuilder()
				.uri(new URI(textSearchUrl))
				.header("MediaType", "text/plain")
				.GET()
				.build();
		
		return getRequest;
	}
	
	
}
