import java.util.Random;

//Sinan Hacisoyu

public class Car {
	private Random ran;
	private int x;
	private int y;
	private int speed;
	private int direction;  //0-->Top 1-->Right 2-->Bottom 3-->Left
	private boolean hasSemaphore;
	private boolean finished; //acquired and released the semaphore
	
	public Car() {
		hasSemaphore = false;
		finished = false;
		ran = new Random();
		direction = ran.nextInt(4); // to randomly determine where the car will come
		if (direction == 0) {
			x = 330;
			y = 0;
		} else if (direction == 1) {
			x = 710;
			y = 330;
		} else if (direction == 2) {
			x = 390;
			y = 710;
		} else {
			x = 10;
			y = 380;
		}
		speed = 10;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isHasSemaphore() {
		return hasSemaphore;
	}

	public void setHasSemaphore(boolean hasSemaphore) {
		this.hasSemaphore = hasSemaphore;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
}
