package File_format;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException
	{
		String path = "./data/WigleWifi_20171201110209.csv";
		Csv2kml kml = new Csv2kml(path);
	}
}
