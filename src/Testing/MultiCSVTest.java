/**
 * This Test Class Testing MultiCSV
 * @author Tzvi Mints and Or Abuhazira
 */
package Testing;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import org.junit.jupiter.api.Test;
import Algorithms.MultiCSV;
import File_format.Project2KML;
import GIS.GIS_element;
import GIS.GIS_layer;

class MultiCSVTest {
	@Test
	void testRecursiveFolder() throws IOException {
		MultiCSV mc = new MultiCSV("./data/");
		boolean fileExists = new File("./data/CheckReq/WigleWifi_20171201110209.kml").exists();
		if(!fileExists) fail("MultiCSV not Reqursive");
		String ans = mc.getProject().toString();
//		System.out.println(ans);  // Print all Project
		if(ans.isEmpty()) fail("No Project!");
		
		
		Set<GIS_layer> projectSet = mc.getProject().getSet();
		Iterator<GIS_layer> it_project  = projectSet.iterator();
		while(it_project.hasNext())
		{
			GIS_layer layer = it_project.next();
			if(layer == null) fail("No Layer!");
//			System.out.println(layer);
			Iterator<GIS_element> it_layer = layer.iterator();
			while(it_layer.hasNext())
			{
				GIS_element element = it_layer.next();
				if(element == null) fail("No Element!");
//				System.out.println(element);
			}
		}
		// * * * * * * * * * * KML Project * * * * * * * * //
		mc.getProject().MakeKml("./data/ProjectKML.kml");
		// * * * * * * * * * * * * * * * * * * * * * * * * //
	}
}
