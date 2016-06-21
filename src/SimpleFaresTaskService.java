import java.util.Scanner;

public class SimpleFaresTaskService {
	static String baseURL;
	static String query;
	static String resultQuery;

	public static void main( String args[] ) throws Exception
	{	
		// call the FAA web service Airport Status web service
		// to convert airport code to state name
//		String baseURL = "http://services.faa.gov/airport/status/" + args[0] + "?format=application/xml";
//		String query = "";
//		String resultQuery = "//State";
//		String stateName = RestfulServiceSupport.queryWithXMLReturn( baseURL, query, resultQuery );
//		System.out.println( "Airport code: " + args[0] + " is in the following state: " + stateName );
		
//		// call the exist rest service to convert state name to state code
//		baseURL = "http://52.26.87.189:8080/exist/rest/db/simplefares/stateZones.xml";
//		query = "?_query=data(//state[@name='" + stateName + "']/@code)";
//		resultQuery = "//exist:value";	
//		String stateCode = RestfulServiceSupport.queryWithXMLReturn( baseURL, query, resultQuery );
//		System.out.println( "State name: " + stateName + " has the following code: " + stateCode );

//		// call the exist rest service to convert state code to zone
//		baseURL = "http://52.26.87.189:8080/exist/rest/db/simplefares/stateZones.xml";
//		query = "?_query=data(//state[@code='" + stateCode + "']/../@id)";
//		resultQuery = "//exist:value";
//		String zone = RestfulServiceSupport.queryWithXMLReturn( baseURL, query, resultQuery );
//		System.out.println( "The zone for state: " + stateCode + " is: " + zone );
		
		// find the fare
//		baseURL = "http://52.26.87.189:8080/exist/rest/db/simplefares/airfareRules.xml";
//		query = "?_query=data(//antecedent[contains(@to,'1') and contains(@from,'3')]/../consequent/@fare)";
//		resultQuery = "//exist:value";
//		String fare = RestfulServiceSupport.queryWithXMLReturn( baseURL, query, resultQuery );
//		System.out.println( "The fare for the trip is: " + fare );
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter Departure Airport Code: ");
		String stateName1=findStateName(in.nextLine());
		String stateCode1=findStateCode(stateName1);
		String zone1=convertToZone(stateCode1);
		
		System.out.println("Enter Arrival Airport Code: ");
		String stateName2=findStateName(in.nextLine());
		String stateCode2=findStateCode(stateName2);
		String zone2=convertToZone(stateCode2);
		
		findFare(zone1,zone2);
	}
	
	static String findStateName(String airportCode){
		// call the FAA web service Airport Status web service
		// to convert airport code to state name
		baseURL = "http://services.faa.gov/airport/status/" + airportCode + "?format=application/xml";
		query = "";
		resultQuery = "//State";
		String stateName="";
		try {
			stateName = RestfulServiceSupport.queryWithXMLReturn( baseURL, query, resultQuery );
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println( "Airport code: " + airportCode + " is in the following state: " + stateName );
		return stateName;
	}
	
	
	static String findStateCode(String stateName){
	// call the exist rest service to convert state name to state code
		baseURL = "http://52.26.87.189:8080/exist/rest/db/simplefares/stateZones.xml";
		query = "?_query=data(//state[@name='" + stateName + "']/@code)";
		resultQuery = "//exist:value";	
		String stateCode="";
		try {
			stateCode = RestfulServiceSupport.queryWithXMLReturn( baseURL, query, resultQuery );
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println( "State name: " + stateName + " has the following code: " + stateCode );
		return stateCode;
	}
	static String convertToZone(String stateCode){
		// call the exist rest service to convert state code to zone
		baseURL = "http://52.26.87.189:8080/exist/rest/db/simplefares/stateZones.xml";
		query = "?_query=data(//state[@code='" + stateCode + "']/../@id)";
		resultQuery = "//exist:value";
		String zone="";
		try {
			zone = RestfulServiceSupport.queryWithXMLReturn( baseURL, query, resultQuery );
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println( "The zone for state: " + stateCode + " is: " + zone );
		return zone;
	}
	static String findFare(String zone1, String zone2){
		baseURL = "http://52.26.87.189:8080/exist/rest/db/simplefares/airfareRules.xml";
		query = "?_query=data(//antecedent[contains(@to,'"+zone1+"') and contains(@from,'"+zone2+"')]/../consequent/@fare)";
		resultQuery = "//exist:value";
		String fare="";
		try {
			fare = RestfulServiceSupport.queryWithXMLReturn( baseURL, query, resultQuery );
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println( "The fare for the trip is: " + fare );
		return fare;
	}
	
}
