/**
 * This Class Transfer CSV file to KML File that Google Earth can read
 * on the way we init Layer that contains Element.
 * @author Tzvi Mints and Or Abuhazira
 */
package File_format;

import GIS.Layer;

public class CSV2kml {
	private Layer layer;
	public CSV2kml(String path)
	{
		CSVToMatrix cr = new CSVToMatrix(path);
		MatrixToLayer cw = new MatrixToLayer(cr);
		Layer csv_layer = cw.MakeLayer();
		layer = new Layer();
		this.setLayer(csv_layer);
		Layer2KML kml = new Layer2KML(csv_layer,path);
		try {
			kml.MakeFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	public Layer getLayer() { return layer; }
	public void setLayer(Layer layer) { this.layer = layer; }
}
