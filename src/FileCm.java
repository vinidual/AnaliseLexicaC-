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
		return file.exists();
	}

	public boolean verifyExtension() {
		return filename.endsWith(".cm");
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
