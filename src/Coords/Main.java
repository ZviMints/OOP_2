package Coords;
import java.util.Arrays;

import Geom.Point3D;

public class Main {

	public static void main(String[] args) {
		MyCoords me = new MyCoords();
		
		 Point3D a = new Point3D(32.103315,35.209039,670);
		 Point3D b = new Point3D(32.106352,35.205225,650);
		
		System.out.println(me.distance3d(a, b));
		System.out.println(me.vector3D(a, b));
		System.out.println(me.add(a, me.vector3D(a, b)));

	}

}
