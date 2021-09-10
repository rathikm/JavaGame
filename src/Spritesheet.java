import java.awt.image.BufferedImage;

public class Spritesheet {
	public BufferedImage img;
	
	public Spritesheet(BufferedImage img) {
		this.img = img;
	}
	
	public BufferedImage getImage(int col, int row, int width, int height) {
		return img.getSubimage(col, row, width, height);
	}
}
