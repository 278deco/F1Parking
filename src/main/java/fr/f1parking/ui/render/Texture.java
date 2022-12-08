package fr.f1parking.ui.render;

import java.io.File;
import java.util.Optional;

import javafx.scene.image.Image;

public class Texture {
	
	private String textureName;
	
	private String path;
	private String fileName;
	
	private Image image;
	
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
	
	public Texture copy() {
		return new Texture(this.textureName, this.path, this.fileName);
	}
	
	public Image loadImage(int sizeX, int sizeY) {
		if(this.image == null || (sizeX != this.image.getWidth() || sizeY != this.image.getHeight())) 
			this.image = new Image(new File(getFullPath()).toURI().toString(), sizeX, sizeY, true, false);
		return this.image;
	}
	
	public Optional<Image> getImage() {
		return Optional.of(this.image);
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
		return this.path +File.separator+ this.fileName;
	}
	
	@Override
	public String toString() {
		return "Texture:[path: "+this.path+", file: "+this.fileName+"]";
	}
}
