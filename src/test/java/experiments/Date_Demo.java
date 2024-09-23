package experiments;

import java.util.Date;

public class Date_Demo {

	public static String main(String[] args) {
		// TODO Auto-generated method stub
		Date date = new Date();
		
	return	date.toString().replace(" ", "_").replace(":", "_");

	}

}
