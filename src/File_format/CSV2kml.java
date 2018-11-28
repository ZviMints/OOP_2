/**
 * This Class Transfer CSV file to KML File that Google Earth can read
 * on the way we init Layer that contains Element.
 * @author Tzvi Mints and Or Abuhazira
 */
package File_format;
import GIS.Layer;
public class CSV2kml {
	private Layer layer;
	String path;
	public CSV2kml(String path)
	{
		this.path = path;
		layer = new Layer(path);
		String dir = path.replaceAll(".csv", ".kml");
		Object2KML kml = new Object2KML(layer,dir);
			try {
				kml.MakeFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/* * * * * * * * * * * * * * * * * * Getters * * * * * * * * * * * * * * * */
	public Layer getLayer() { return layer; }
	public String getPath() { return path; }

}
