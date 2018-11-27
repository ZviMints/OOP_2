package File_format;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
	private static String path;
	private static ArrayList<ArrayList<String>> matrix;
	private static BufferedReader br;
	/* * * * * * * * * * * * * * * * * * Constructor * * * * * * * * * * * * * * * */
	public CSVReader(String path) {
		this.setPath(path);
		try {
			Reader();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/* * * * * * * * * * * * * * * * * * Setters and Getters * * * * * * * * * * * * * * * */
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) { 
		CSVReader.path = path; 
	}
	public ArrayList<String> getHeader() {
		return matrix.get(1);
	}
	public int getRowsSize()
	{
		return matrix.size();
	}
	public int getColumnsSize()
	{
		return matrix.get(0).size();
	}
	public ArrayList<String> getRowAtIndexI(int i)
	{
		return matrix.get(i);
	}
	/* * * * * * * * * * * * * * * * * * toString * * * * * * * * * * * * * * * */
	public String toString()
	{
		String ans = "";
		for(int i=0; i<this.getRowsSize(); i++)
			ans += matrix.get(i) +"\n";
		return ans;
	}
	/* * * * * * * * * * * * * * * * * * Reader * * * * * * * * * * * * * * * */
	public static void Reader() throws IOException {
		matrix = new ArrayList<ArrayList<String>>(); 
		File file = new File(path);
		br = new BufferedReader(new FileReader(file));
		String data = br.readLine();
		int Columns = 0;
		while(data != null)
		{
			matrix.add(new ArrayList<String>());
			String[] rows = data.split(",");
			for (int i = 0; i < rows.length; i++) {
				matrix.get(Columns).add(rows[i]);
			}
			Columns++;
			data = br.readLine();
		}
	}
}