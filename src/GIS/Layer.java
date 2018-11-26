package GIS;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Layer implements GIS_layer {
	public Set<GIS_element> set = new HashSet<GIS_element>();
	private Meta_data data;

	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = null;
		Iterator<GIS_element> it = set.iterator();
		while(it.hasNext())
			ans += it.next() + "\n";
		return ans;
	}

	/* * * * * * * * * * * * * * * * * * Override * * * * * * * * * * * * * * * */
	@Override
	public boolean add(GIS_element e) {
		return set.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> c) {
		return set.addAll(c);
	}

	@Override
	public void clear() {
		set.clear();
	}

	@Override
	public boolean contains(Object o) {
		return set.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return set.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return set.isEmpty();

	}

	@Override
	public Iterator<GIS_element> iterator() {
		return set.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return set.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return set.removeAll(c);

	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return set.retainAll(c);
	}

	@Override
	public int size() {
		return set.size();
	}

	@Override
	public Object[] toArray() {
		return set.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return set.toArray(a);
	}

	@Override
	public Meta_data get_Meta_data() {
		return data; 
	}
}
