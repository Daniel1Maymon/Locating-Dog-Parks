import java.io.IOException;
import java.net.URISyntaxException;

public class GoogleAPIMain {

	public static void main(String[] args) throws URISyntaxException, InterruptedException,  IOException {

//		String GoogleFindPlaceAPI = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=Museum%20of%20Contemporary%20Art%20Australia&inputtype=textquery&fields=formatted_address%2Cname%2Crating%2Copening_hours%2Cgeometry&key=" + myGoogleAPIKey;
		Utilities utils = new Utilities();
		
		String latitude = "32.07480056769963";
		String longitude = "34.81147322536574";
		int radius = 150;
		utils.searchAndPrintDogParksByRadius(latitude, longitude, radius);
		

		

	}

}
