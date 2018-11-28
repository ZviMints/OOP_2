/**
 * This Class represent Project that is a Set of Layers
 * @author Tzvi Mints and Or Abuhazira
 */
package GIS;
import java.io.*;
import java.util.*;

public class Project implements GIS_project {
	private Set<GIS_layer> set;
	private Meta_data data; // We can insert data here. for now we did not enter any data.
	private String ProjectName;


	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	/**
	 * This method returns the name of the current Project
	 * @return String that represent the name of the Project
	 */
	public String getName() {
		return ProjectName;
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
		this.ProjectName += name + ",";
	}
	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	/**
	 * This method is responsible to get Folder path and save Data
	 * @param path is the Folder String
	 */
	public Project(String path)
	{
		// ************ initialize Set ************ //
		set = new HashSet<GIS_layer>();
		// ************ initialize name ************ //
		ProjectName = new String();
		// ************ initialize Set of Layers ************ //
		try {
			RecursiveSearch(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Project() {
		// ************ initialize Set ************ //
		set = new HashSet<GIS_layer>();
		// ************ initialize name ************ //
		ProjectName = new String();
	}
	/* * * * * * * * * * * * * * * * * * RecursiveSearch * * * * * * * * * * * * * * * */
	private void RecursiveSearch(String path) throws IOException {
		File root = new File(path);
		File[] list = root.listFiles();
		if (list == null) 
		{
			if(!root.isDirectory())
			{
				System.err.println("Enter folder! if u want to make .csv file then use Layer");
			}
			else
			return; // The Folder not contain any files
		}
		for ( File f : list ) { // for all files in list
			if (f.isDirectory()) { 
				RecursiveSearch(f.getAbsolutePath()); // recursive 
			}
			else {
				String Location = f.getAbsolutePath(); // Location <-- getting the Location of the file
				String ending = Location.substring(Location.lastIndexOf(".") + 1 ); // ending <-- ending of the file
				if(ending.equals("csv")) // if the file is ".csv" format 
				{
					try { 
						Layer layer = new Layer(Location);
						this.add(layer);
						this.updateName(root.getAbsolutePath());
					} 
					catch (Exception e) {} 
				}
			}
		}
}
/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
public String toString()
{
	String ans = "Project ---> " + this.getName() + "\n";
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
				+ "\n"; }
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