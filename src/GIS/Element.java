package GIS;
import java.util.TreeMap;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class Element implements GIS_element{
	private Geom_element geo;
	private Data info;
	
	/* * * * * * * * * * * * * * * * * * Constructors * * * * * * * * * * * * * * * */
	public Element(Geom_element geo,Data data)
	{
		this.geo = geo;
		this.info = data;
	}
	public Element() {
	}
	/* * * * * * * * * * * * * * * * * * getMapFromData * * * * * * * * * * * * * * * */
	public TreeMap<String, String> getMap()
	{
		return this.info.getMap();
	}
	/* * * * * * * * * * * * * * * * * * Setters * * * * * * * * * * * * * * * */
	public Data getDataElement()
	{
		return this.info;
	}
	
	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */

	public String toString()
	{
		return this.getMap() + "";
	}
	
	/* * * * * * * * * * * * * * * * * * Override * * * * * * * * * * * * * * * */
	@Override
	public Geom_element getGeom() {
		return geo;
	}

	@Override
	public Meta_data getData() {
		return info;
	}
	@Override
	public void translate(Point3D vec) {
		Point3D p = (Point3D)this.getGeom();
		MyCoords coords = new MyCoords();
		geo = new Point3D(coords.add(p, vec));
	}

}
