package Testing;
import static org.junit.jupiter.api.Assertions.*;
import java.text.DecimalFormat;
import org.junit.jupiter.api.Test;
import Coords.MyCoords;
import Geom.Point3D;
class MyCoordsTest {
	private MyCoords coords = new MyCoords();
	private Point3D p0;
	private Point3D p1;
	private Point3D vec;
	private DecimalFormat df = new DecimalFormat("#.#######");

	@Test
	void testAdd() {
		 p0 = new Point3D(32.103315,35.209039,670);
		 p1 = new Point3D(32.106352,35.205225,650);
		 vec = new Point3D(337.69899206128815,-359.24920693881893,-20.0);
		 assertEquals(coords.add(p0, vec).toString(),p1.toString()); 	
	}

	@Test
	void testDistance3d() {
		 p0 = new Point3D(32.103315,35.209039,670);
		 p1 = new Point3D(32.106352,35.205225,650);
		 double actual = coords.distance3d(p0, p1);
		 double expected =  493.0523318;
		 assertEquals(expected,Double.parseDouble(df.format(actual)));
	}

	@Test
	void testVector3D() {
		 p0 = new Point3D(32.103315,35.209039,670);
		 p1 = new Point3D(32.106352,35.205225,650);
		 vec = coords.vector3D(p0, p1);
		 assertEquals(coords.add(p0, vec).toString(),p1.toString()); 
		 }

	@Test
	void testAzimuth_elevation_dist() {
		 p0 = new Point3D(32.103315,35.209039,670);
		 p1 = new Point3D(32.106352,35.205225,650);
		 fail("Not Implement");
	}

	@Test
	void testIsValid_GPS_Point() {
		Point3D p1_wrong_x_1 = new Point3D(-190,0,0);
		Point3D p1_wrong_x_2 = new Point3D(190,0,0);
		Point3D p1_wrong_y_1 = new Point3D(0,100,0);
		Point3D p1_wrong_y_2 = new Point3D(0,-100,0);
		Point3D p1_wrong_z = new Point3D(0,0,-500);
		
		Point3D p2_true_1 = new Point3D(-180,-90,-450);
		Point3D p2_true_2 = new Point3D(180,90,Integer.MAX_VALUE);

		assertFalse(coords.isValid_GPS_Point(p1_wrong_x_1));
		assertFalse(coords.isValid_GPS_Point(p1_wrong_x_2));
		assertFalse(coords.isValid_GPS_Point(p1_wrong_y_1));
		assertFalse(coords.isValid_GPS_Point(p1_wrong_y_2));
		assertFalse(coords.isValid_GPS_Point(p1_wrong_z));
		assertTrue(coords.isValid_GPS_Point(p2_true_1));
		assertTrue(coords.isValid_GPS_Point(p2_true_2));

	}
}
