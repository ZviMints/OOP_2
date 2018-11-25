/**
 * This Class Transfer CSV file to KML File that Google Earth can read
 * @author Tzvi Mints and Or Abuhazira
 */
package File_format;
import java.io.*;
import java.text.*;
import java.util.TimeZone;

import Geom.Point3D;
public class Csv2kml {
	private static String path;
	private static BufferedReader br;
	private static String ans;

	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	public Csv2kml(String path) throws Exception { this.setPath(path); Convert(); }

	/* * * * * * * * * * * * * * Setters And Getters * * * * * * * * * * * * * * * */
	public String getPath() { return path; }
	public void setPath(String path) { Csv2kml.path = path; }
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
	public static void Convert() throws Exception
	{
		File file = new File(path);
		br = new BufferedReader(new FileReader(file));
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
		br.readLine();
		br.readLine();
		String data = br.readLine();
		while(data != null)
		{
			String[] rows = data.split(",");
			Point3D point = new Point3D(Double.parseDouble(rows[6]),
					Double.parseDouble(rows[7]),
					Double.parseDouble(rows[8]));
			KML_BODY += CSV_TO_KML(rows[0], // MAC
					rows[1], // SSID
					rows[2], // AuthMode
					rows[3], // FirstSeen
					rows[4], // Channel
					rows[5], // RSSI
					point,   // (CurrentLatitude(רוחב),CurrentLongitude(אורך),AltitudeMeters(גובה)
					rows[9], // AccuracyMeters
					rows[10]); // Type
			data = br.readLine();
		}
		String tail = "</Folder>" + "\n" 
				+ "</Document></kml>";
		ans = header + KML_BODY + tail;
		MakeFile();
	}

	/* * * * * * * * * * * * * * * * * * CSV To KML Convert * * * * * * * * * * * * * * * */
	public static String CSV_TO_KML(String MAC, String SSID, String AuthMode, String FirstSeen, String Channel, String RSSI, Point3D point, String AccuracyMeters, String Type )
	{	
		// Ask what to do with RSSI,Accurate Meter, type?
		String body ="<Placemark>" + "\n"
				+ "<name>"+ "<![CDATA[" + SSID +"]]>"+"</name>" + "\n"
				+ "<description><![CDATA[BSSID: <b>" + MAC + "</b><br/>"
				+ "Capabilities: <b>" + AuthMode + "</b><br/>" 
				+ "Frequency: <b>" + getFrequency(Channel) + "</b><br/>" 
				+ "Timestamp: <b>" + getTimeStampFromString(FirstSeen) + "</b><br/>"

				+ "Date: <b>" + FirstSeen +"</b>]]></description><styleUrl>#red</styleUrl>" + "\n"
				+ "<Point>" + "\n"
				+ "<coordinates>" + point.y() + "," + point.x() + "," + point.z() + "</coordinates>" + "\n"
				// Notice! Google takes Lon and then Lat.
				+ "</Point>" + "\n"
				+ "</Placemark>" + "\n";
		return body;
	}

	/* * * * * * * * * * * * * * * * * * File Writer * * * * * * * * * * * * * * * */
	public static void MakeFile() throws Exception
	{
		String filename = path.replaceAll(".csv", ".kml");
		boolean fileExists = new File(filename).exists();
		if(fileExists) throw new Exception(filename + " Alredy Exists!");
		else
		{
			PrintWriter pw = new PrintWriter(new File(filename));
			StringBuilder sb = new StringBuilder();	
			sb.append(ans);
			pw.write(sb.toString());
			pw.close();
		}
	}

}
