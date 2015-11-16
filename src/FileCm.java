import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class FileCm {
	private File file;
	private String filename;

	public FileCm(String filename) {
		this.filename = filename;
		file = new File(filename);
	}

	public boolean exists() {
		if (file.exists())
			return true;
		System.out.println("File not founded");
		return false;
	}

	public boolean verifyExtension() {
		if(filename.endsWith(".cm"))
			return true;
		System.out.println("Please, enter a file with .cm extension");
		return false;
	}

	public boolean valid() {
		return exists() && verifyExtension();
	}

	public char[] convertVectorChar() {
		char []cbuffer;
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			cbuffer = new char [(int) file.length()];
			br.read(cbuffer);
			return cbuffer;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
