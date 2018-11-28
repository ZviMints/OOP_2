/**
 * This Class represent Layer that is a Set of Elements
 * @author Tzvi Mints and Or Abuhazira
 */
package GIS;
import java.util.*;
import File_format.*;

public class Layer implements GIS_layer {
	private Set<GIS_element> set;
	private MetaLayer data;
	public String LayerName;

	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	/**
	 * This method returns the name of the current Layer
	 * @return String that represent the name of the Layer
	 */
	public String getName() {
		return LayerName;
	}
	/**
	 * This method returns the Set of  GIS_element,
	 * Each project is set of elements
	 * @return returns the Set of  GIS_element
	 */
	public Set<GIS_element> getSet() {
		return set;
	}
	/**
	 * This method is responsible to Update the current Layer name
	 */
	public void updateName(String name) {
		this.LayerName += name;
	}
	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	/**
	 * This method is responsible to get File path and save Data
	 */
	public Layer(String path)
	{
		// ************ initialize Set ************ //
		set = new HashSet<GIS_element>();
		// ************ initialize Layer name ************ //
		LayerName = new String();
		updateName(path);
		// ************ initialize Set of Elements ************ //
		CSVToMatrix cr = new CSVToMatrix(path);
		for(int i=2; i < cr.getRowsSize(); i++)
		{
			Element element = new Element(cr.getRowAtIndexI(i),cr.getHeader(),cr.getColumnsSize());
			add(element);
		}
		// ************ initialize Layer Data  ************ //
		data = new MetaLayer();
	}
	public Layer() {
		// ************ initialize Set ************ //
		set = new HashSet<GIS_element>();
		// ************ initialize Layer name ************ //
		LayerName = new String();
		// ************ initialize Layer Data  ************ //
		data = new MetaLayer();
		
	}
	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "  Layer ---> " + this.getName() + ", Created at:" + data.getUTC() +":\n";
		Iterator<GIS_element> it = set.iterator();
		while(it.hasNext())
			ans += "    " + it.next() + "\n";
		return ans;
	}

	/* * * * * * * * * * * * * * * * * * Override * * * * * * * * * * * * * * * */
	@Override
	public boolean add(GIS_element e) { return set.add(e); }

	@Override
	public boolean addAll(Collection<? extends GIS_element> c) { return set.addAll(c); }

	@Override
	public void clear() { set.clear(); }

	@Override
	public boolean contains(Object o) { return set.contains(o); }

	@Override
	public boolean containsAll(Collection<?> c) { return set.containsAll(c); }

	@Override
	public boolean isEmpty() { return set.isEmpty(); }

	@Override
	public Iterator<GIS_element> iterator() { return set.iterator(); }

	@Override
	public boolean remove(Object o) { return set.remove(o); }

	@Override
	public boolean removeAll(Collection<?> c) { return set.removeAll(c); }

	@Override
	public boolean retainAll(Collection<?> c) { return set.retainAll(c); }

	@Override
	public int size() { return set.size(); }

	@Override
	public Object[] toArray() { return set.toArray(); }

	@Override
	public <T> T[] toArray(T[] a) { return set.toArray(a); }

	@Override
	public Meta_data get_Meta_data() { return data; }
}
