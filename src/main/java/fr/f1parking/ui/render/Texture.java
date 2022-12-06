package fr.f1parking.ui.render;

public class Texture {
	
	private String textureName;
	
	private String path;
	private String fileName;
	
	/**
	 * Create a new Texture object
	 * @param textureName - the texture name (used in the program, not related to the file)
	 * @param path - the path where the image is stored
	 * @param fileName - the file's name on the disk
	 */
	public Texture(String textureName, String path,String fileName) {
		this.textureName = textureName;
		
		this.fileName = fileName;
		this.path = path;
	}
	
	/**
	 * Get the texture name (used by program)
	 * @return the texture's name
	 */
	public String getTextureName() {
		return textureName;
	}
	
	/**
	 * Get the file name (file on the disk)
	 * @return the file's name
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Get the path of the file
	 * @return the files's path
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Get the full path of the file (path + file's name)
	 * @return the full path
	 */
	public String getFullPath() {
		return this.path +"/"+ this.fileName;
	}
}
