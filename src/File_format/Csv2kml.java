package File_format;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
public class Csv2kml {
	private static String path;
	
	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	public Csv2kml(String path) { this.setPath(path); }

	/* * * * * * * * * * * * * * Setters And Getters * * * * * * * * * * * * * * * */
	public String getPath() { return path; }
	public void setPath(String path) { Csv2kml.path = path; }

	/* * * * * * * * * * * * * * * * * * Main * * * * * * * * * * * * * * * */
	public static void main(String[] args) throws FileNotFoundException
	{
		File file = new File(path);
		Scanner input = new Scanner(file);
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
		while(input.hasNext())
		{
			String data = input.next();
			String[] rows = data.split(",");
			System.out.println(Arrays.toString(rows) +"*****"+ rows.length);
			KML_BODY += CSV_TO_KML(rows[0],
					               rows[1],
					               rows[2],
					               rows[3] + "," + rows[4], // coordinates
					               rows[5],
					               rows[6],
					               rows[7]);
		}
		input.close();
		String tail = "</Document></kml>";
		KML_BODY = header + KML_BODY + tail;
		System.out.println(KML_BODY);
	}
	public static String CSV_TO_KML(String name, String description, String coordinates,
			String rows, String rows2, String rows3, String rows4,
			String rows5 )
	{	
		String body ="<Placemark>" + "\n"
				+ "<name>" +name+ "</name>" + "\n"
				+ "<description>" + description + "</description>" + "\n"
				+ "<Point>" + "\n"
				+ "<coordinates>" + coordinates + "</coordinates>" + "\n"
				+ "</Point>" + "\n"
				+ "</Placemark>" + "\n";
		return body;
	}
}
