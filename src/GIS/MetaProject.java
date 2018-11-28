/**
 * This Class Represent Project Data such as Time,Path
 * @author Tzvi Mints and Or Abuhazira
 */

package GIS;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import Geom.Point3D;

public class MetaProject implements Meta_data {
	private String time;
	private String path;
	
	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	/**
	 * This method is responsible to Update the current Layer path
	 */
	public void updateName(String name) { this.path += name + ","; }
	/**
	 * This method is responsible to return the creation time
	 */
	public String getTime() { return time; }
	/**
	 * This method is responsible to get the current Layer path
	 */
	public String getName() { return path; }
	
	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	
	public MetaProject(String path){
		this.path = new String();
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").  // Represent the time when the Project was created.
			   format(Calendar.getInstance().getTime());		
	}
	
	public MetaProject(){
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").  // Represent the time when the Project was created.
			   format(Calendar.getInstance().getTime());
	}

	/* * * * * * * * * * * * * * * * * * Override * * * * * * * * * * * * * * * */
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
		String ans="Project ---> " + this.getName() + " Created at:" + this.getUTC() + "|"+this.getTime() +"\n";
		return ans;
	}
	
	@Override
	public Point3D get_Orientation() {
		return null;
	}


}
