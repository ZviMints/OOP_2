/**
 * This method is responsible for actions between Objects of the kind Point3D.
 * @author Tzvi Mints and Or Abuhazira
 * @version 1.0
 */
package Coords;
import Geom.Point3D;

public class MyCoords implements coords_converter {
	
	/* * * * * * * * * * * * * * * * * * Constant * * * * * * * * * * * * * * * */
	/** This Constant represent radius of the globe **/
	private static final double radius = 6371000;
	/** This Constant represent Math.PI ~ 3.14 **/
	private static final double PI = Math.PI;

	/* * * * * * * * * * * * * * * * * * Override * * * * * * * * * * * * * * * */
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
		double alpha = RTD(Math.atan(Math.abs(dy/dx)));
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
	/* * * * * * * * * * * * * * * * * * Calculation * * * * * * * * * * * * * * * */
	/** This Method get the Lon_Norm from double **/
	private double getLon_Norm(double x) { return Math.cos(x * (PI/180)); }
	/** This Method convert Degrees to Radian **/
	private double DTR (double x) { return x* PI/180; }
	/** This Method convert Radian to Meter for Lat **/
	private double RTM_x (double x) { return Math.sin(x)*radius; }
	/** This Method convert Radian to Meter for Lon **/
	private double RTM_y (double y , double Lon_Norm) { return  Math.sin(y)*radius*Lon_Norm; }
	/** This Method convert Degree to Meter for Lat **/
	private double DTM_x(double x) { return RTM_x(DTR(x)); }
	/** This Method convert Degree to Meter for Lon **/
	private double DTM_y(double y, double x) { return RTM_y(DTR(y),getLon_Norm(x)); } 
	/** This Method convert Radian to Degrees **/
	private double RTD (double x) { return x* 180/PI; }
	/** This Method convert Meter to Radian for Lat **/
	private double MTR_x (double x) { return Math.asin(x/radius); }
	/** This Method convert Meter to Radian for Lon **/
	private double MTR_y (double y , double Lon_Norm) { return  Math.asin(y/(radius*Lon_Norm)); } //  Meter to Radian
	/** This Method convert Meter to Degrees for Lat **/
	private double MTD_x(double x) { return (RTD(MTR_x(x))); } // Degrees to Meter
	/** This Method convert Meter to Degrees for Lon **/
	private double MTD_y(double y, double x) {return RTD(MTR_y(y,getLon_Norm(x)));} // Degrees to Meter
}
