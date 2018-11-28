/**
 * This Class represent Element that implements GIS_element
 * each Element is a Line,
 * each Element have Geom Element, Data, and Name.
 * @author Tzvi Mints and Or Abuhazira
 */
package GIS;
import java.util.ArrayList;
import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class Element implements GIS_element{
	private Geom_element geo;
	private MetaElement metaElement;

	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	/**
	 * This method is responsible return all the information
	 * that data have, for example SSID,Channel and more.
	 * @return Data
	 */
	public MetaElement getInfo() { return metaElement; }
	
	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
	public Element(ArrayList<String> row, ArrayList<String> header, int ColumnsSize) { 
		// ************ initialize Geom_element ************ //
		geo = new Point3D(Double.parseDouble(row.get(6)) // Latitude
				         ,Double.parseDouble(row.get(7)) // Longitude
				         ,Double.parseDouble(row.get(8))); // Altitude
		// ************ initialize Meta Element ************ //
		metaElement = new MetaElement();
		for(int i=0; i < ColumnsSize; i++)
		{
			String s = header.get(i);
			switch(s)
			{
			case "RSSI": metaElement.setRSSI(row.get(i)); break;
			case "FirstSeen": metaElement.setFirstSeen(row.get(i)); break;
			case "Channel": metaElement.setChannel(row.get(i)); break;
			case "SSID": metaElement.setSSID(row.get(i));  break;
			case "MAC": metaElement.setMAC(row.get(i)); break;
			case "AuthMode": metaElement.setAuthMode(row.get(i)); break;
			}
		}
	}
	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "Element:" + "--> ";
		ans += metaElement.toString();
		ans +=  "," + "Geom element" + ":" + geo;
		return ans;
	}
	/* * * * * * * * * * * * * * * * * * Override * * * * * * * * * * * * * * * */
	@Override
	public Geom_element getGeom() {
		return geo;
	}

	@Override
	public Meta_data getData() {
		return metaElement;
	}
	@Override
	public void translate(Point3D vec) {
		Point3D p = (Point3D)this.getGeom();
		MyCoords coords = new MyCoords();
		geo = new Point3D(coords.add(p, vec));
	}
}
