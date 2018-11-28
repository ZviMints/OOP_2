package Testing;

import org.junit.jupiter.api.Test;
import GIS.Layer;
import GIS.Project;

class GISTest {

	@Test
	void testLayer() {
		Layer layer = new Layer("C:\\Users\\аеш\\Desktop\\Ex2\\data\\WigleWifi_20171203085618.csv");
		System.out.println(layer.get_Meta_data().getUTC());
		System.out.println(layer.get_Meta_data().toString());
	}
	
	@Test
	void testProject() {
		Project project = new Project("C:\\Users\\аеш\\Desktop\\Ex2\\data");
		System.out.println(project.get_Meta_data().getUTC());
		System.out.println(project.get_Meta_data());
	}
}
