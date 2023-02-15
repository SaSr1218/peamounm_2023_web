package Day11.Ex3;

public class Car {
	
	public int speed;
	
	public void speedUp() {
		speed ++;
	}
	public final void stop() {
		System.out.println("차 멈춤");
	speed = 0;
	}
	
}
