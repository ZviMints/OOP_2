package File_format;
import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.TimeZone;

import GIS.Data;
import GIS.Element;
import GIS.GIS_element;
import GIS.Layer;
import Geom.Point3D;

public class Layer2KML {
	private String path = null;
	private Layer layer = null;
	private String ans = "";
	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	public Layer2KML(Layer layer,String path)
	{
		this.layer = layer;
		this.path = path;
		try {
			Convert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* * * * * * * * * * * * * * * Get Methods * * * * * * * * * * * * * * * */
	/**
	 * Convert Date to Unix TimeStamp
	 * @param time is the String in yyyy-MM-dd HH:mm:ss format from CSV
	 * @return Unix Timestamp
	 * @throws ParseException
	 */
	public static long getTimeStampFromString(String time)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long unixTime = 0;
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+2")); // Israel Time Zone
		try {
			unixTime = dateFormat.parse(time).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		unixTime = unixTime / 1000;
		return unixTime;
	}
	/**
	 * Convert Channel to Frequency
	 * @param channel is the input Channel from CSV file
	 * @return Frequency that represent Channel
	 */
	private static String getFrequency(String channel) {
		switch(channel)
		{
		case "1":  return "2412";
		case "2": return "2417";
		case "3": return "2422";
		case "4": return "2427";
		case "5": return "2432";
		case "6": return "2437";
		case "7": return "2442";
		case "8": return "2447";
		case "9": return "2452";
		case "10": return "2457";
		case "11": return "2462";
		case "12": return "2467";
		case "13": return "2472";
		case "14": return "2484";
		}
		return "No Frequency";
	}
	/* * * * * * * * * * * * * * * * * * Convert * * * * * * * * * * * * * * * */
	public void Convert() throws Exception
	{
		String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"
				+ "<Document>\n"
				+ "<Style id=\"red\"><IconStyle><Icon>\n"
				+ "<href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href>\n"
				+ "</Icon></IconStyle></Style>\n"
				+ "<Style id=\"yellow\"><IconStyle><Icon>\n"
				+ "<href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href>\n"
				+ "</Icon></IconStyle></Style>\n"
				+ "<Style id=\"green\"><IconStyle><Icon>\n"
				+ "<href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href>\n"
				+ "</Icon></IconStyle></Style>\n"
				+ "<Folder><name>Wifi Networks</name>\n";
		String KML_BODY = "";
		Iterator<GIS_element> it = layer.set.iterator();
		while(it.hasNext())
		{
			Element element = (Element) it.next();
			Point3D point = (Point3D) element.getGeom();
			KML_BODY += CSV_TO_KML(
					element.getInfo().getMAC(),
					element.getInfo().getSSID(),
					element.getInfo().getAuthMode(),
					element.getInfo().getFirstSeen(),
					getFrequency(element.getInfo().getChannel()),
					element.getInfo().getRSSI(),
					point);
		}
		String tail = "</Folder>" + "\n" 
				+ "</Document></kml>";
		ans = header + KML_BODY + tail;
		MakeFile();
	}
	/* * * * * * * * * * * * * * * * * * CSV To KML Convert * * * * * * * * * * * * * * * */
	public String CSV_TO_KML(String MAC, String SSID, String AuthMode, String FirstSeen, String Frequency, String RSSI, Point3D point)
	{	
		String body ="<Placemark>" + "\n"
				+ "<name>"+ "<![CDATA[" + SSID +"]]>"+"</name>" + "\n"
				+ "<description><![CDATA[BSSID: <b>" + MAC + "</b><br/>"
				+ "Capabilities: <b>" + AuthMode + "</b><br/>" 
				+ "Frequency: <b>" + Frequency + "</b><br/>" 
				+ "Timestamp: <b>" + getTimeStampFromString(FirstSeen) + "</b><br/>"
				+ "Date: <b>" + FirstSeen +"</b>]]></description><styleUrl>#red</styleUrl>" + "\n"
				+ "<Point>" + "\n"
				+ "<coordinates>" + point.y() + "," + point.x() + "," + point.z() + "</coordinates>" + "\n"
				+ "</Point>" + "\n"
				+ "</Placemark>" + "\n";
		return body;
	}
	/* * * * * * * * * * * * * * * * * * File Writer * * * * * * * * * * * * * * * */
	public void MakeFile() throws Exception
	{
		String filename = path.replaceAll(".csv", ".kml");
		PrintWriter pw = new PrintWriter(new File(filename));
		StringBuilder sb = new StringBuilder();	
		sb.append(ans);
		pw.write(sb.toString());
		pw.close();
	}
	/* * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		return ans;
	}

}