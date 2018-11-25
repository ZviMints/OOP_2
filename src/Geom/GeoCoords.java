package Geom;

import Coords.coords_converter;

public class GeoCoords extends Point3D { // Ask for SerialUID
	private Point3D point;
	public GeoCoords(double lat, double lon, double alt) {
		if(isValid_GPS_Point(point))
			
	}

	public Point3D add(Point3D local_vector_in_meter) {
		// TODO Auto-generated method stub
		return null;
	}

	public double distance3d(Point3D gps1) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Point3D vector3D(Point3D gps1) {
		// TODO Auto-generated method stub
		return null;
	}

	public double[] azimuth_elevation_dist(Point3D gps1) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isValid_GPS_Point() {

	}
}
