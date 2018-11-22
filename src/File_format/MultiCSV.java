package File_format;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class MultiCSV {
	public MultiCSV(String path) throws IOException
	{
		MultiWriter(path);
	}
	private static void MultiWriter(String path) throws IOException {
		 File root = new File( path );
	        File[] list = root.listFiles();

	        if (list == null) return;

	        for ( File f : list ) {
	            if ( f.isDirectory() ) {
	            	MultiWriter( f.getAbsolutePath() );
	            	
	                System.out.println( "Dir:" + f.getAbsoluteFile() );
	            }
	            else {
	            	Csv2kml kml = new Csv2kml( f.getAbsolutePath());
	                System.out.println( "File:" + f.getAbsoluteFile() );
	            }
	        }
	    }
	}
		


