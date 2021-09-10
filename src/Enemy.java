import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject {
	private int HP;
	public Enemy(double x, double y, ID id, Handler h) {
		super(x, y, id, h);
		HP = 100;
		// TODO Auto-generated constructor stub
	}
	
	public void collision() {
		for (GameObject ob:h.getObjects()) {
			if (ob.getID() == ID.Block) {
				if(getCushion().intersects(ob.getRect())) {
					x += velX * -5;
					y += velY * -5;
					velX *= -1;
					velY *= -1;
				}
			} else if (ob.getID() == ID.Bullet) {
				if(getCushion().intersects(ob.getRect())) {
					HP -= 5;
				}
			}
		}
	}
	
	
	public int calcHP() {
		if (HP == 0) {
			h.removeObject(this);
		}
		return HP;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;
		
		collision(); 
		
		Random r = new Random();
		int x = r.nextInt(8);
		if (x == 3) {
			velX = r.nextInt(7) - 3;
			velY = r.nextInt(9) - 3;
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(new Color(255, 100, 100));
		g.fillRect((int)x, (int)y, 32, 20);
		g.setColor(Color.green);
		g.fillRect((int)x, (int)(y + 24), (calcHP() * 32)/100, 8);
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public Rectangle getCushion() {
		// TODO Auto-generated method stub
		return new Rectangle((int)(x - 8), (int)(y - 8), 48, 48);
	}

}
