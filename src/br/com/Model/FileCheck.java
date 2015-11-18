package br.com.Model;
import java.io.File;

public class FileCheck {
	
	private static String dir;
	private static String filename;
	private static File file;

	public void createFile(String fn, String directory) {
		filename = fn;
		dir = directory;
		file = new File( dir + "/" + filename );
	}

	public boolean validExtension() {
		try{
			if( filename.endsWith(".cm") )
				return true;
		} catch( NullPointerException ex ){
			System.out.println( ex.getMessage() );
		}
		return false;
	}

	public boolean validName() {
		if( filename.isEmpty() ||  !filename.matches("[a-zA-Z0-9]+.cm") )
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
