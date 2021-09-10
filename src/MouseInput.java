import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ListIterator;

public class MouseInput extends MouseAdapter {
	private Handler h;
	private Camera cam;
	public MouseInput(Handler h, Camera cam) {
		this.h = h;
		this.cam = cam;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = (int) (e.getX() + cam.getX());
		int my = (int) (e.getY() + cam.getY());
		ArrayList<GameObject> local = new ArrayList<GameObject>();
		for(ListIterator<GameObject> ob = h.getObjects().listIterator(); ob.hasNext();) {
			GameObject temp = ob.next();
			if(temp.getID() == ID.Player) {
				if ((temp.getVelX() < -0.3 || temp.getVelX() > 0.3) || (temp.getVelY() < -0.3 || temp.getVelY() > 0.3) )
					local.add(new Bullet(temp.x + 16, temp.y + 16, ID.Bullet, h, mx, my));
			}
		}
		
		for (GameObject ob: local) {
			h.addObject(ob);
		}
	}
	

}

