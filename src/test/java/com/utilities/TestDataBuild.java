package com.utilities;

import java.util.ArrayList;
import java.util.List;

import com.serilization.pojo.AddAPI;
import com.serilization.pojo.Location;

public class TestDataBuild {
	
	
	public AddAPI addPlacebody(String name,String language,String address)
	{
		AddAPI addplace=new AddAPI();
		addplace.setAccuracy(50);
		addplace.setName(name);
		addplace.setPhone_number("(+91) 983 893 3937");
		addplace.setAddress(address);
		addplace.setWebsite("http://google.com");
		addplace.setLanguage(language);
	    Location loc=new Location();
	    loc.setLat(-38.383494);
	    loc.setLng(33.427362);
	     addplace.setLocation(loc);
	     List<String> l1=new ArrayList<String>();
	     l1.add("shoe park");
	     l1.add("shop");
	     addplace.setTypes(l1);
	     return addplace;
		
	}

	
	public String deleteapibody(String placeid) {
		
		return "{\r\n    \"place_id\":\""+placeid+"\"\r\n}\r\n\r\n";
		
	}
}
