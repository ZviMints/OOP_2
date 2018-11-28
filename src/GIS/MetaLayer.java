/**
 * This Class Represent Layer Data
 * @author Tzvi Mints and Or Abuhazira
 */
package GIS;
import Geom.Point3D;

public class MetaLayer implements Meta_data {
	private String name; // Represent Layer name.
	private String time; // Represent the time when the Layer was created.
	@Override
	public long getUTC() {
		// TODO Auto-generated method stub
		return 0;
	}
	public String toString()
	{
		// time --> ...
	}
	@Override
	public Point3D get_Orientation() {
		return null; // Boaz said to IGNORE!
	}

}
