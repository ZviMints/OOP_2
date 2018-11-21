package File_format;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class MultiCSV {
	public MultiCSV(String path) throws IOException
	{
		MultiWriter(path);
	}
	private static void MultiWriter(String path) throws IOException {
		File root = new File(path);
		File[] list = root.listFiles();
		if (list == null) return;
		for (File f : list) 
		{
			String fileEnd = f.getAbsolutePath();
			System.out.println(fileEnd);
			if (fileEnd.equals(".csv")) 
			{
				System.err.println("YES");
//				Csv2kml kml = new Csv2kml("./data/WigleWifi_20171201110209.csv");
			}
		}
	}
}

