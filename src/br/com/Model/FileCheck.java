package br.com.Model;
import java.io.File;

public class FileCheck {
	
	private static String dir;
	private static String name;
	private static String ext;
	private static File file;

	public void createFile(String filename, String directory) {
		int index = Math.abs(filename.lastIndexOf("."));
		name = filename.substring(0, index);
		ext = filename.substring(index+1);
		dir = directory;
		file = new File( dir + "/" + name + "." + ext );
	}

	public boolean validExtension() {
		try{
			if( ext.compareTo("cm") == 0 )
				return true;
		} catch( NullPointerException ex ){
			System.out.println( ex.getMessage() );
		}
		return false;
	}

	public boolean validName() {
		if( name.isEmpty() ||  !name.matches("[a-zA-Z0-9]+") )
			return false;
		return true;
	}

	public boolean existFile() {
		if( file.exists() )
			return true;
		return false;
	}
	
	public File getFile(){
		return file;
	}
	
}
