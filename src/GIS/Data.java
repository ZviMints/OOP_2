/**
 * This Class represent Data that implements Meta_data
 * each Data is a Line information,
 * each Data have RSSI,FirstSeen,Channel,SSID,Mac,Authmode.
 * @author Tzvi Mints and Or Abuhazira
 */
package GIS;
import Geom.Point3D;

public class Data implements Meta_data {
	/* * * * * * * * * * * * * *  Private Strings * * * * * * * * * * * * * * * */
	private String RSSI;
	private String FirstSeen;
	private String Channel;
	private String SSID;
	private String MAC;
	private String AuthMode;

	/* * * * * * * * * * * * * * * * * * Override * * * * * * * * * * * * * * * */
	@Override
	public long getUTC(){
		long milliseconds = -1;
		return milliseconds; 
	}

	@Override
	public String toString() {
		String ans = "";
		ans += 	"RSSI" + ":" + getRSSI() + ","
		        +"FirstSeen" + ":" + getFirstSeen() + ","
		        +"Channel" + ":" + getChannel() + ","
		        +"SSID" + ":" + getSSID() + ","
		        +"MAC" + ":" + getMAC() + ","
		        +"AuthMode" + ":" + getAuthMode();
	 return ans;
	}

	@Override
	public Point3D get_Orientation() {
		return null; // IGNORE!
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

}
