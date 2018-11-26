package GIS;
import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class Element implements GIS_element{
	private Geom_element geo;
	private Data data;
	/* * * * * * * * * * * * * * * * * * Getters * * * * * * * * * * * * * * * */
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
		String ans = data.toString();
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
