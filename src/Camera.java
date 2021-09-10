
public class Camera {
	private double x,y;
	
	public Camera(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject obj) {
		x += ((obj.x - x) - 250.0) * 0.05;
		y += ((obj.y - y) - 250.0) * 0.05;
		
		if (x <= 0) {x = 0;}
		if (y <= 0) {y = 0;} 
		
		if (x >= 532) {x = 532;}
		if (y >= 548) {y = 548;}
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}
