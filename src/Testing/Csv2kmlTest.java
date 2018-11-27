package Testing;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import org.junit.jupiter.api.Test;
import File_format.Csv2kml;
import GIS.GIS_element;
class Csv2kmlTest {

	
	
	@Test
	void testFileAlredyExists() throws Exception {	
		Csv2kml kml = new Csv2kml("./data/WigleWifi_20171201110209.csv");
		Iterator<GIS_element> layer = kml.getLayer().getSet().iterator();
		while(layer.hasNext())
		{
			GIS_element element = layer.next();
			if(element == null) fail("No Element!");
//			System.out.println(element);
		}
	}
}
