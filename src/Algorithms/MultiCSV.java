/**
 * This Class Transfer Folder that include CSV to one KML File that Google Earth can read
 * on the way we init Project that contains Layer and Element.
 * @author Tzvi Mints and Or Abuhazira
 */
package Algorithms;
import File_format.Object2KML;
import GIS.Project;
public class MultiCSV {
	private Project project;
	private String path;
	private String filename = "Project.kml";
	public MultiCSV(String path)
	{
		this.path = path;
		project = new Project(path);
		String dir;
		if(!path.contains(".csv")) 
			dir = path + filename; // Make KML Project with current name
		else
			dir = path.replaceAll(".csv", ".kml"); // if We Send .csv file to Project
		Object2KML kml = new Object2KML(project,dir);
		try {
			kml.MakeFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* * * * * * * * * * * * * * * * * * Getters * * * * * * * * * * * * * * * */
	public Project getProject() { return project; }
	public String getPath() { return path; }
	public String getFileName() { return filename; }
}