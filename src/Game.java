import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;

public class Game extends Canvas implements Runnable {
	private boolean running = false;
	private Thread thread;
	private int width, height;
	private Handler handler;
	private KeyInput input;
	private BufferedImage level = null;
	private Camera camera;
	private MouseInput mInput;
	private BufferedImage spriteSheet = null;
	private Spritesheet ss;
	public Game(int width, int height) {
		Window display = new Window(width, height, "Game", this);
		this.width = width;
		this.height = height;
		
		camera = new Camera(0, 0);
		handler = new Handler();
		input = new KeyInput();
		mInput = new MouseInput(handler, camera);
		this.addKeyListener(input);
		this.addMouseListener(mInput);
		
		
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImg("/gamelevel.png");
		spriteSheet = loader.loadImg("/spritesheet.png");
		ss = new Spritesheet(spriteSheet);
		loadLevel(level);
		
		;
		if (ss == null) {
			System.out.println("hello");
		}
		start();
		
		
		
	
		
		
		
		
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	
	public void loadLevel(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int pixel = img.getRGB(i, j);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				if (red == 255) {
					handler.addObject(new Block(i*32, j*32, ID.Block, handler));
				}
				if (green == 255) {
					handler.addObject(new Enemy(i*32, j*32, ID.Enemy, handler));
				}
				if (blue == 255) {
					handler.addObject(new Player(i*32, j*32, ID.Player, input, handler, ss));
				}
			}
		}
	}
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long current = System.nanoTime();
		long time = System.currentTimeMillis();
		double ticks = 90.0;
		double ticksPerSecond = ticks/1000000000;
		double delta = 0.0;
		int updates = 0;
		int frames = 0;
		while(running) {
			long finalTime = System.nanoTime();
			long change = finalTime - current;
			delta += change * ticksPerSecond;
			current = finalTime;
			if (delta >= 1) {
				try {
				tick();
				} catch(ConcurrentModificationException e) {};
				frames++;
				delta--;
			}
			try {
			render();
			} catch (ConcurrentModificationException e) {}
			updates++;
			
			if (System.currentTimeMillis() - time > 1000) {
				time += 1000;
				System.out.println("FPS: " + frames + "TICKS:" + updates);
				updates = 0;
				frames = 0;
			}
		}
	}
	
	public void render() {
		// TODO Auto-generated method stub
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2D = (Graphics2D)g;
		//draw
		
		//g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		//g.drawImage(spriteSheet, 0, 0, null);
		g2D.translate((int)(-camera.getX()), (int)(-camera.getY()));

		//
		
		handler.render(g);
		g2D.translate((int)(camera.getX()), (int)(camera.getY()));
		g.dispose();
		bs.show();
	}

	public void tick() {
		// TODO Auto-generated method stub
		for (GameObject ob:handler.getObjects()) {
			if (ob.getID() == ID.Player) {
				camera.tick(ob);
			}
		}
		handler.tick();
	}

	public static void main(String args[]) {
		Game game = new Game(500, 600);
	}

}
