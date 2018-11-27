/**
 * This Class Transfer CSV file to KML File that Google Earth can read
 * on the way we init Layer that contains Element.
 * @author Tzvi Mints and Or Abuhazira
 */
package File_format;

import GIS.Layer;

public class Csv2kml {
	private Layer layer;
	public Csv2kml(String path)
	{
		CSVReader cr = new CSVReader(path);
		CSVWriter cw = new CSVWriter(cr);
		Layer csv_layer = cw.MakeLayer();
		layer = new Layer();
		this.setLayer(csv_layer);
		Layer2KML kml = new Layer2KML(csv_layer,path);
	}
	
	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	public Layer getLayer() { return layer; }
	public void setLayer(Layer layer) { this.layer = layer; }
}
