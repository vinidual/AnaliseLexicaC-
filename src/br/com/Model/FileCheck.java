package br.com.Model;
import java.io.File;

public class FileCheck {
	private String filename;
	private File file;
	
	public FileCheck(String filename){
		this.filename = filename;
		this.file = new File(filename);
	}
	
	public boolean exists() {
		if (file.exists())
			return true;
		else{
			System.out.println("File not found");
			return false;
		}
	}
	
	public boolean verifyExtension() {
		if(filename.endsWith(".cm"))
			return true;
		System.out.println("Please, enter a file with .cm extension");
		return false;
	}
	
	public File getFile(){
		return file;
	}

}
