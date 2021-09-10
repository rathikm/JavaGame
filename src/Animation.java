import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
	private int speed;
	private int frames;
	
	private int index;
	private int count;
	
	private BufferedImage[] imgs;
	private BufferedImage current;
	
	public Animation(int speed, BufferedImage... images) {
		this.speed = speed;
		imgs = new BufferedImage[images.length];
		for(int i = 0; i < images.length; i++) {
			imgs[i] = images[i];
		}
		
		frames = images.length;
	}
	
	public void runAnimation() {
		index++;
		
		if (index > speed) {
			index = 0;
			nextFrame();
		}
		
	}
	
	public void nextFrame() {
		for (int i = 0; i < frames; i++) {
			if (count == i) {
				current = imgs[i];
			}
		}
		
		count++;
		
		if (count > frames) {
			count = 0;
		}
	}
	
	public void drawAnimation(Graphics g, int x, int y) {
		g.drawImage(current, (int)x, (int)y, null);
		
	}
}
