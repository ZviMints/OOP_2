package Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GIS.Layer;
import GIS.Project;

class GISTest {

	@Test
	void testLayer() {
		Layer layer = new Layer("./data/WigleWifi_20171201110209.csv");
		System.out.println(layer);
	}
	@Test
	void testProject() {
		Project project = new Project("./data/");
		System.out.println(project.get_Meta_data());
	}
}
