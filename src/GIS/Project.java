/**
 * This Class represent Project that is a Set of Layers
 * @author Tzvi Mints and Or Abuhazira
 */

package GIS;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Project implements GIS_project {
	private Set<GIS_layer> set = new HashSet<GIS_layer>();
	private Meta_data data;
	private String name = "-->";
	
	
	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	/**
	 * This method returns the name of the current Project
	 * @return String that represent the name of the Project
	 */
	public String getName() {
		return name;
	}
	/**
	 * This method returns the Set of  GIS_layer,
	 * Each project is set of layers
	 * @return returns the Set of  GIS_layer
	 */
	public Set<GIS_layer> getSet() {
		return set;
	}
	/**
	 * This method is responsible to Update the current Project name
	 */
	public void updateName(String name) {
		this.name += name + ",";
	}
	
	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "Project " + this.getName() + "\n";
		Iterator<GIS_layer> it = set.iterator();
		while(it.hasNext())
		{
			Layer layer = (Layer) it.next();
			ans += layer + "    "+ "*************"
					             + "*************"
					             + "*************"
					             + "*************"
					             + "*************"
					             + "*************"
					     + "\n";
		}
		return ans;
	}
	/* * * * * * * * * * * * * * * * * * Override * * * * * * * * * * * * * * * */
	@Override
	public boolean add(GIS_layer e) { return set.add(e); }

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) { return set.addAll(c); }

	@Override
	public void clear() { set.clear();  }

	@Override
	public boolean contains(Object o) { return set.contains(o); }

	@Override
	public boolean containsAll(Collection<?> c) { return set.containsAll(c); }

	@Override
	public boolean isEmpty() { return set.isEmpty(); }

	@Override
	public Iterator<GIS_layer> iterator() { return set.iterator(); }

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