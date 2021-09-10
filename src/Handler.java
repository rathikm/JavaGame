import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public Handler() {
		
	}
	
	public ArrayList<GameObject> getObjects() {
		return objects;
	}
	
	public void tick() {
		for (GameObject ob:objects) {
			ob.tick();
		}
	}
	
	public void render(Graphics g) {
		for (GameObject ob:objects) {
			ob.render(g);
		}
	}
	
	public void addObject(GameObject go) {
		objects.add(go);
	}
	
	public void removeObject(GameObject go) {
		objects.remove(go);
	}
}
