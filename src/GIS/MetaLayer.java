/**
 * This Class Represent Layer Data
 * @author Tzvi Mints and Or Abuhazira
 */
package GIS;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import Geom.Point3D;

public class MetaLayer implements Meta_data {
	private String path; // Represent Layer name.
	private String time ; // Represent the current time of creating Layer

	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	/**
	 * This method returns the path of the current MetaLayer
	 * @return String that represent the path of the Layer
	 */
	public String getPath() { return path; }
	/**
	 * This method is responsible to return the creation time
	 */
	public String getTime() { return time; }
	/**
	 * This method is responsible to Update the current Layer name
	 */
	public void updateName(String name) { this.path += name; }
	
	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	public MetaLayer(String path)
	{
		// ************ initialize MetaLayer Path ************ //
		this.path = new String();
		updateName(path);
		// ************ initialize time ************ //
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").  // Represent the time when the Layer was created.
				format(Calendar.getInstance().getTime());
	}
	public MetaLayer()
	{
		// ************ initialize time ************ //
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").  // Represent the time when the Layer was created.
				format(Calendar.getInstance().getTime());
		// ************ initialize MetaLayer Path ************ //
		this.path = new String();
	}
	/* * * * * * * * * * * * * * * * * * Overrides * * * * * * * * * * * * * * * */
	@Override
	public long getUTC() {		
		long Time = -1;
		try {
			String time_string = this.getTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date dateStr = dateFormat.parse(time_string);
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			String formattedDate = dateFormat.format(dateStr);
			Time = dateFormat.parse(formattedDate).getTime();
			Time = Time / 1000;
		}
		catch(Exception e) { e.getStackTrace(); }
		return Time;
	}

	@Override
	public String toString()
	{
		String ans=	"  Layer ---> " + this.getPath() + ", Created at:" + this.getUTC() + "|"+this.getTime()  + "\n";
		return ans;
	}

	@Override
	public Point3D get_Orientation() {
		return null; // Boaz said to IGNORE!
	}


}
