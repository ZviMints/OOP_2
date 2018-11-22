package Testing;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import File_format.Csv2kml;
class Csv2kmlTest {

	@Test
	void testFileAlredyExists() throws Exception {	
		Csv2kml kml = new Csv2kml("./data/WigleWifi_20171201110209.csv");
		try {
		Csv2kml kml_AlredyExists = new Csv2kml("./data/WigleWifi_20171201110209.csv");
		fail("./data/WigleWifi_20171201110209.csv  Alredy Exists!");
		}
		catch(Exception e) { } // All Good 
		
	}

}
