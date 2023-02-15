package 과제.과제6;

public class HankookTire extends Tire {	// 한국 - 자식 // Tire - 부모
	public HankookTire(int maxRotation, String location) {
		super(maxRotation, location);
	}
	@Override
	public boolean roll() {
		++accumulatedRotation;	
		if( accumulatedRotation < maxRotation ) { //회전축적수가 최대회전수를 넘기전까지
			System.out.println( location + " HankookTire 수명 : " 
				+ (maxRotation-accumulatedRotation) + "회");
			return true; //	최대회전수 - 회전축적수 = Tire 수명 , 0 반환
		}else {
			System.out.println("*** " + location +" HankookTire 펑크 ***");
			return false; // 회전축적수가 최대회전수를 넘었을 경우 Tire 펑크 , 1반환 
		}
	}
	
	
}
