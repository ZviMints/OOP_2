package Coords;
import java.util.Arrays;

import Geom.Point3D;

public class Main {

	public static void main(String[] args) {
		MyCoords me = new MyCoords();
		
		Point3D a = new Point3D(32.10332,35.20904,32);
		Point3D b = new Point3D(32.10635,35.60523,99.9);
		

	
		System.out.println(Arrays.toString(me.azimuth_elevation_dist(a,b)));

	}

}
