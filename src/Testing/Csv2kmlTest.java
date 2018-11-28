/**
 * This Test Class Testing Csv2Kml
 * @author Tzvi Mints and Or Abuhazira
 */
package Testing;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import org.junit.jupiter.api.Test;
import File_format.CSV2kml;
import GIS.GIS_element;
import Geom.Point3D;
class Csv2kmlTest {
	@Test
	void testFileAlredyExists() throws Exception {	
		CSV2kml kml = new CSV2kml("./data/WigleWifi_20171201110209.csv");
		Iterator<GIS_element> layer = kml.getLayer().getSet().iterator();
		while(layer.hasNext())
		{
			GIS_element element = layer.next();
			if(element == null) fail("No Element!");
			// ***** Check Print ***** //
//			System.out.println(element);
			// ***** Check UTC ***** //
//			System.out.println("UTC Time:" + element.getData().getUTC());
			// ***** Check Translate ***** //
//			Point3D vec = new Point3D(10,10,10);
//			System.out.print("Before:" + element.getGeom() + " | ");
//			element.translate(vec);
//			System.out.print("After:" + element.getGeom() + "\n");

		}
	}
}
