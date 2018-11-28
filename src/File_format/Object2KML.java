/**
 * This Class Converting Object into KML file.
 * The class can convery Layer,Project or Element into a KML.
 */
package File_format;
import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.TimeZone;
import GIS.Element;
import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.Layer;
import GIS.Project;
import Geom.Point3D;

public class Object2KML {
	private String path;
	private Object obj;
	private String KML_BODY;
	private String KML_HEAD;
	private String KML_TAIL;
	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	/**
	 * Constructor of the KML file. make Header, make Body and make Tail.
	 * @param obj is the current Object, can be layer, element or project.
	 * @param path is the path to *put* the KML file
	 */
	public Object2KML(Object obj,String path)
	{
		this.path = path;
		this.obj = obj;
		MakeHead();
		MakeBody();
		MakeTail();
	}
	/* * * * * * * * * * * * * * * * * * Convert * * * * * * * * * * * * * * * */
	/**
	 * This method is responsible to Convert Project to KML.
	 */
	private void ConvertProject(Project project) {
		Iterator<GIS_layer> it_project  = project.getSet().iterator(); // Project iterator
		while(it_project.hasNext())
		{
			Layer layer = (Layer) it_project.next();
			ConvertLayer(layer);
		}
	}
	/**
	 * This method is responsible to Convert Layer to KML.
	 */
	private void ConvertLayer(Layer layer) {
		Iterator<GIS_element> it = layer.getSet().iterator();
		while(it.hasNext()) // Layer iterator
		{
			Element element = (Element) it.next();
			ConvertElement(element);
		}
	}
	/**
	 * This method is responsible to Convert Element to KML.
	 */
	private void ConvertElement(Element element) {
		Point3D point = (Point3D) element.getGeom();
		KML_BODY += CSV_TO_KML(
				element.getInfo().getMAC(),
				element.getInfo().getSSID(),
				element.getInfo().getAuthMode(),
				element.getInfo().getFirstSeen(),
				getFrequency(element.getInfo().getChannel()),
				element.getInfo().getRSSI(),
				point,
				element.getInfo().getColor());
	}
	
	/* * * * * * * * * * * * * * * Make Head,Body,Tail * * * * * * * * * * * * * * * */
	private void MakeHead() {
		   KML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" 
				    + "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"
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
		}
	
	private void MakeBody() {
		KML_BODY = new String();
		if(obj.getClass() == Project.class) {  ConvertProject((Project)obj); }
		else if(obj.getClass() == Layer.class) { ConvertLayer((Layer)obj); }
		else if(obj.getClass() == Element.class) {   ConvertElement((Element)obj); }
	}
	
	private void MakeTail() {
		KML_TAIL =   "</Folder>" + "\n" 
				   + "</Document></kml>";
	}
	/* * * * * * * * * * * * * * * * * * CSV To KML Convert * * * * * * * * * * * * * * * */
	/**
	 * This Method is Responsible to convert From CSV Line to KML line by format
	 * @param MAC is the input MAC from csv file
	 * @param SSID is the input SSID from csv file
	 * @param AuthMode is the input AuthMode from csv file
	 * @param FirstSeen is the input FirstSeen from csv file
	 * @param Frequency is the input Frequency from csv file
	 * @param RSSI is the input RSSI from csv file
	 * @param point is the input 3 coordinates ( lat, lon , alt ) from csv file
	 * @return
	 */
	public String CSV_TO_KML(String MAC, String SSID, String AuthMode, String FirstSeen, String Frequency, String RSSI, Point3D point, String Color)
	{	
		String body ="<Placemark>" + "\n"
				+ "<name>"+ "<![CDATA[" + SSID +"]]>"+"</name>" + "\n"
				+ "<description><![CDATA[BSSID: <b>" + MAC + "</b><br/>"
				+ "Capabilities: <b>" + AuthMode + "</b><br/>" 
				+ "Frequency: <b>" + Frequency + "</b><br/>" 
				+ "Timestamp: <b>" + getTimeStampFromString(FirstSeen) + "</b><br/>"
				+ "Date: <b>" + FirstSeen +"</b>]]></description><styleUrl>#"+Color+"</styleUrl>" + "\n"
				+ "<Point>" + "\n"
				+ "<coordinates>" + point.y() + "," + point.x() + "," + point.z() + "</coordinates>" + "\n"
				+ "</Point>" + "\n"
				+ "</Placemark>" + "\n";
		return body;
	}
	/* * * * * * * * * * * * * * * * * * File Writer * * * * * * * * * * * * * * * */
	/**
	 * This method responsible to Make the KML file
	 * @throws Exception
	 */
	public void MakeFile() throws Exception
	{
		PrintWriter pw = new PrintWriter(new File(path));
		StringBuilder sb = new StringBuilder();	
		sb.append(KML_HEAD + KML_BODY + KML_TAIL);
		pw.write(sb.toString());
		pw.close();
		System.out.println("Success! file on path: " +path);
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
	/* * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		return KML_HEAD + KML_BODY + KML_TAIL;
	}
}
