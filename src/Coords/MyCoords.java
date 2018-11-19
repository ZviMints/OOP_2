package Coords;
import Geom.Point3D;

public class MyCoords implements coords_converter {
	private static final double radius = 6371000;
	private static final double PI = Math.PI;

	private double getLon_Norm(double x) { return Math.cos(x * (PI/180)); }
	private double DTR (double x) { return x* PI/180; } // Degrees to Radian
	private double RTM_x (double x) { return Math.sin(x)*radius; } // Radian to Meter
	private double RTM_y (double y , double Lon_Norm) { return  Math.sin(y)*radius*Lon_Norm; } // Radian to Meter
	private double DTM_x(double x) { return RTM_x(DTR(x)); }
	private double DTM_y(double y, double x) { return RTM_y(DTR(y),getLon_Norm(x)); }

	private double RTD (double x) { return x* 180/PI; } // Radian to Degrees
	private double MTR_x (double x) { return Math.asin(x/radius); } // Meter to Radian
	private double MTR_y (double y , double Lon_Norm) { return  Math.asin(y/(radius*Lon_Norm)); } //  Meter to Radian
	private double MTD_x(double x) { return (RTD(MTR_x(x))); }
	private double MTD_y(double y, double x) {return RTD(MTR_y(y,getLon_Norm(x)));}


	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		if(!isValid_GPS_Point(gps)) return new Point3D(0,0,0); // Wrong input, will will return (0,0,0)
		double x = MTD_x(local_vector_in_meter.x());
		double y = MTD_y(local_vector_in_meter.y(),gps.x());
		return new Point3D(gps.x() + x ,
				gps.y() + y ,
				gps.z() + local_vector_in_meter.z());
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		if(!isValid_GPS_Point(gps0) || !isValid_GPS_Point(gps1)) return 0; // Wrong input, will return 0
		double dx = gps1.x() - gps0.x();
		double dy = gps1.y() - gps0.y();
		double x_m = DTM_x(dx);
		double y_m = DTM_y(dy,gps0.x()); // Ask Why ??
		return Math.sqrt(Math.pow(x_m, 2) + Math.pow(y_m, 2));
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		if(!isValid_GPS_Point(gps0) || !isValid_GPS_Point(gps1)) return new Point3D(0,0,0); // Wrong input, will return (0,0,0)
		double dx = gps1.x() - gps0.x();
		double dy = gps1.y() - gps0.y();
		double dz = gps1.z() - gps0.z();
		return new Point3D(DTM_x(dx),
				DTM_y(dy,gps0.x()),
				dz);
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		if(!isValid_GPS_Point(gps0) || !isValid_GPS_Point(gps1)) return new double[3]; // Wrong input, will return [0,0,0]
		double[] ans = new double[3]; // ans[0,1,2] = [azimuth,elevation,distance]
		double dx = gps1.x() - gps0.x();
		double dy = gps1.y() - gps0.y();
		double dz = gps1.z() - gps0.z();
		ans[2] = distance3d(gps0,gps1);  // distance
		ans[1] = Math.asin(dz / ans[2]); // elevation
		double alpha = Math.atan(Math.abs(dy/dx));
		if(dy >=0 && dx < 0 ) alpha = 180 - alpha;
		else if(dy<0 && dx <0) alpha = 180 + alpha;
		else if(dy<0 && dx >=0) alpha = 360 - alpha;
		ans[0] = alpha; // azimuth
		return ans;
	}


	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		double lat = p.x();
		double lon = p.y();
		double alt = p.z();
		return((lat >= -180 && lat <= 180) &&
				(lon >= -90 && lon <= 90) &&
				(alt >= -450));
	}

}
