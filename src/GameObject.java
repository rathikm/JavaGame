import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected double x, y;
	protected double velX, velY;
	protected ID id;
	protected Handler h;
	
	public GameObject(double x, double y, ID id, Handler h) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.h = h;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public ID getID() {
		return id;
	}
	
	public abstract Rectangle getRect();
	
	public double getVelX() {
		return velX;
	}
	
	public double getVelY() {
		return velY;
	}
}
