package File_format;
import java.util.ArrayList;
import GIS.Data;
import GIS.Element;
import GIS.Layer;
import Geom.Geom_element;
import Geom.Point3D;
public class CSVWriter {
	private CSVReader cr;
	private Layer layer;
	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	public CSVWriter(CSVReader cr)
	{
		this.cr = cr;
		layer = new Layer();
	}
	/* * * * * * * * * * * * * * * * * * MakeLayer * * * * * * * * * * * * * * * */
	public Layer MakeLayer()
	{
		Element element = null;
		for(int i=2; i < cr.getRowsSize(); i++)
		{
			element = MakeElement(cr.getRowAtIndexI(i));
			layer.add(element);
		}
		return layer;
	}
	/* * * * * * * * * * * * * * * * * * MakeElement * * * * * * * * * * * * * * * */
	private Element MakeElement(ArrayList<String> row)
	{
		Data info = new Data();
		ArrayList<String> header = cr.getHeader();
		Geom_element geo = new Point3D(Double.parseDouble(row.get(6)) // Latitude
				,Double.parseDouble(row.get(7)) // Longitude
				,Double.parseDouble(row.get(8))); // Altitude

		for(int i=0; i < cr.getColumnsSize();i++)
		{
			String s = header.get(i);
			switch(s)
			{
			case "RSSI": info.setRSSI(row.get(i)); break;
			case "FirstSeen": info.setFirstSeen(row.get(i)); break;
			case "Channel": info.setChannel(row.get(i)); break;
			case "SSID": info.setSSID(row.get(i));  break;
			case "MAC": info.setMAC(row.get(i)); break;
			case "AuthMode": info.setAuthMode(row.get(i)); break;
			}
		}
		Element ans = new Element(geo,info);
		return ans;
	}
}
