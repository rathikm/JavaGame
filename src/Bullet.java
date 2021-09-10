import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject {
	private int mx, my;
	public Bullet(double x, double y, ID id, Handler h, int mx, int my) {
		super(x, y, id, h);
		this.mx = mx;
		this.my = my;
		
		velX = (mx - x)/8;
		velY = (my - y)/8;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.white);
		g.fillOval((int)(x - 20), (int)(y + 20), 10, 10);
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, 10, 10);
	}

}
