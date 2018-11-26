package Testing;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import Algorithms.MultiCSV;

class MultiCSVTest {
	@Test
	void testRecursiveFolder() throws IOException {
		MultiCSV mc = new MultiCSV("./data/");
		System.out.println(mc.getProject());
		boolean fileExists = new File("./data/CheckReq/WigleWifi_20171201110209.kml").exists();
		if(!fileExists) fail("MultiCSV not Reqursive");
	}

}
