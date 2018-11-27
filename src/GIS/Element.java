/**
 * This Class represent Element that implements GIS_element
 * each Element is a Line,
 * each Element have Geom Element, Data, and Name.
 * @author Tzvi Mints and Or Abuhazira
 */
package GIS;
import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class Element implements GIS_element{
	private Geom_element geo;
	private Data data;
	private String name = "";

	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	/**
	 * This method returns the name of the current Element
	 * @return String that represent the name of the Element
	 */
	public String getName() {
		return name;
	}
	/**
	 * This method is responsible to Update the current Element name
	 */
	public void updateName(String name) {
		this.name += name + ",";
	}
	/**
	 * This method is responsible return all the information
	 * that data have, for example SSID,Channel and more.
	 * @return data
	 */
	public Data getInfo()
	{
		return data;
	}
	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
	public Element(Geom_element geo,Data data)
	{
		this.geo = geo;
		this.data = data;
	}

	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "Element:" + this.getName() + "--> ";
		ans += data.toString();
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
		return data;
	}
	@Override
	public void translate(Point3D vec) {
		Point3D p = (Point3D)this.getGeom();
		MyCoords coords = new MyCoords();
		geo = new Point3D(coords.add(p, vec));
	}

}
