package resources;

public class payLoad {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static String getBodyForPostPlace() {
		return "{\n" + 
				"  \"location\": {\n" + 
				"    \"lat\": -38.383494,\n" + 
				"    \"lng\": 33.427362\n" + 
				"  },\n" + 
				"  \"accuracy\": 50,\n" + 
				"  \"name\": \"NewPlace Lynn\",\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\n" + 
				"  \"address\": \"29, side PayLoad, cohen 09\",\n" + 
				"  \"types\": [\n" + 
				"    \"shoe park\",\n" + 
				"    \"shop\"\n" + 
				"  ],\n" + 
				"  \"website\": \"http://google.com\",\n" + 
				"  \"language\": \"French-IN\"\n" + 
				"}";
	}
	
	public static String getBodyForUpdatePlace(String placeId) {
		return "{\n" + 
				"\"place_id\"" + ":\"" + placeId + "\",\n" + 
				"\"address\":\"70 Update walk, USA\",\n" + 
				"\"key\":\"qaclick123\"\n" + 
				"}\n" + 
				"";
	}
	
	public static String getBodyForDeletePlace(String placeId) {
		return "{\n" + 
				"\"place_id\"" + ":\"" + placeId + "\"\n" +  
				"}\n" + 
				"";
	}

}
