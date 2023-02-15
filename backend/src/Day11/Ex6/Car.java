package Day11.Ex6;

public class Car {
	public Tire tire;	// 원래는 형식이 Tire만 줘서 Tire만 됨. 하지만 상속을 줬기 때문에 한국타이어, 금호타이어를 사용할 수 있음.
	public void run() {
		tire.roll();
	}
	public Tire parking( Tire tire ) {
		tire.stop();
		return tire;
	}
}
