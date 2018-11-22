package File_format;
import java.io.File;
import java.io.IOException;

public class MultiCSV {
	public MultiCSV(String path) throws IOException
	{
		MultiWriter(path);
	}
	private static void MultiWriter(String path) throws IOException {
		File root = new File(path);
		File[] list = root.listFiles();

		if (list == null) return; // The Folder not contain any files
		for ( File f : list ) { // for all files in list
			if (f.isDirectory()) { 
				MultiWriter(f.getAbsolutePath()); // recursive 
			}
			else {
				String Location = f.getAbsolutePath(); // Location <-- getting the Location of the file
				String ending = Location.substring(Location.lastIndexOf(".") + 1 ); // ending <-- ending of the file
				if(ending.equals("csv")) // if the file is ".csv" format 
				{
					try { Csv2kml kml = new Csv2kml(f.getAbsolutePath()); } 
					catch (Exception e) { System.out.println(e.getMessage());} 
				}
			}
		}
	}
}