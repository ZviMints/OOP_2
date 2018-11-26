/**
 * This Class Transfer CSV file to KML File that Google Earth can read
 * @author Tzvi Mints and Or Abuhazira
 */
package File_format;

import GIS.Layer;

public class Csv2kml {
	public Csv2kml(String path)
	{
		CSVReader cr = new CSVReader(path);
		CSVWriter cw = new CSVWriter(cr);
		Layer csv_layer = cw.MakeLayer();
		Layer2KML kml = new Layer2KML(csv_layer,path);
	}
}
