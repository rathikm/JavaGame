import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Player extends GameObject implements ImageObserver{
	private double accel = 1;
	private double deccel = 0.5;
	private KeyInput input;
	private final int WIDTH = 32;
	private Spritesheet ss;
	private BufferedImage[] imgs = new BufferedImage[3];
	
	private Animation anim;
	public Player(double x, double y, ID id, KeyInput input, Handler h, Spritesheet ss) {
		super(x, y, id, h);
		this.input = input;
		this.ss = ss;
		imgs[0] = this.ss.getImage(0, 0, 48, 63);
		imgs[1] = this.ss.getImage(47, 0, 48, 63);
		imgs[2] = this.ss.getImage(95, 0, 48, 63);
		// TODO Auto-generated constructor stub
		velX = 0;
		velY = 0;
		
		anim = new Animation(5, imgs[1], imgs[2]);
	}

	public void collision() {
		for (GameObject ob:h.getObjects()) {
			if (ob.getID() == ID.Block) {
				if(getRect().intersects(ob.getRect())) {
					x += velX * -1;
					y += velY * -1;
				}
			}
		}
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
	
		
		x += velX;
		y += velY;
		
		collision();
		
		if (input.keys[0]) {velY -= accel;}
		if (input.keys[2]) {velY += accel;}
		if (!input.keys[0] && !input.keys[2]) {
			if (velY > 0) {
				velY -= deccel;
			} else if (velY < 0) {
				velY += deccel;
			}
		}
		
		if (input.keys[1]) {velX -= accel;}
		if (input.keys[3]) {velX += accel;}
		if (!input.keys[1] && !input.keys[3]) {
			if (velX > 0) {
				velX -= deccel;
			} else if (velX < 0) {
				velX += deccel;
			}
		}
		
		if (velX >= 6) {velX = 6;} else if (velX <= -6) {velX = -6;}
		if (velY >= 6) {velY = 6;} else if (velY <= -6) {velY = -6;}
		
		
		anim.runAnimation();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		//img = img.getSubimage(0, 0, 48, 21);
		if (velX == 0 && velY == 0) {
			g.drawImage(imgs[0], (int)x, (int)y, null);
		} else {
			anim.drawAnimation(g, (int)x, (int)y);
		}
		
	}
	
	public Rectangle getRect() {
		return new Rectangle((int)x, (int)y, 48, 63);
	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}
	

	

}
