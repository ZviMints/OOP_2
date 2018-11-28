/**
 * This Class represent Data that implements Meta_data
 * each Data is a Line information,
 * each Data have RSSI,FirstSeen,Channel,SSID,Mac,Authmode.
 * @author Tzvi Mints and Or Abuhazira
 */
package GIS;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import Geom.Point3D;

public class Data implements Meta_data {
	/* * * * * * * * * * * * * *  Private Strings * * * * * * * * * * * * * * * */
	private String RSSI;
	private String FirstSeen;
	private String Channel;
	private String SSID;
	private String MAC;
	private String AuthMode;
	// Color of the current Data //
	private String Color;

	/* * * * * * * * * * * * * * * * * * Override * * * * * * * * * * * * * * * */
	@Override
	public long getUTC(){
		long Time = -1;
		try {
			String time_string = this.getFirstSeen();
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
	public String toString() {
		String ans = "";
		ans += 	"RSSI" + ":" + getRSSI() + ","
				+"FirstSeen" + ":" + getFirstSeen() + ","
				+"Channel" + ":" + getChannel() + ","
				+"SSID" + ":" + getSSID() + ","
				+"MAC" + ":" + getMAC() + ","
				+"AuthMode" + ":" + getAuthMode()
		        +"Color" + ":" + getColor();
		return ans;
	}

	@Override
	public Point3D get_Orientation() {
		return null; // Boaz said to IGNORE!
	}
	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */ 
	public String getRSSI() { return RSSI; }
	public void setRSSI(String rSSI) { RSSI = rSSI; }
	public String getFirstSeen() { return FirstSeen; }
	public void setFirstSeen(String firstSeen) { FirstSeen = firstSeen; }
	public String getChannel() { return Channel; }
	public void setChannel(String channel) { Channel = channel; }
	public String getSSID() { return SSID; }
	public void setSSID(String sSID) { SSID = sSID; }
	public String getMAC() { return MAC; }
	public void setMAC(String mAC) { MAC = mAC;}
	public String getAuthMode() { return AuthMode; }
	public void setAuthMode(String authMode) { AuthMode = authMode; }
	public void setColor(String Color) { this.Color = Color; }
	public String getColor() {
		if(Double.parseDouble(this.getRSSI()) < - 90)
			this.setColor("green");
		else
			this.setColor("red");
		return Color; 
	}

}
