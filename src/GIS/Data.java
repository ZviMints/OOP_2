package GIS;
import java.util.TreeMap;
import Geom.Point3D;

public class Data implements Meta_data {
	private static TreeMap<String, String> map;
	
	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
	public Data()
	{ map = new TreeMap<String , String>(); }
	
	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	public TreeMap<String, String> getMap() {
		return map;
	}
	/* * * * * * * * * * * * * * * * * * Override * * * * * * * * * * * * * * * */
	@Override
	public long getUTC(){
		long milliseconds = -1;
		return milliseconds; 
	}
	
	@Override
	public String toString() { return map.toString(); }

	@Override
	public Point3D get_Orientation() {
		return null; // IGNORE!
	}

}
