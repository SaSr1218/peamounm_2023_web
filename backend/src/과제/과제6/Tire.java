package 과제.과제6;
public class Tire {
	// 1. 필드
	public int maxRotation; 		// 최대 회전수
	public int accumulatedRotation; // 회전 축적수
	public String location;			// 위치
	public Tire(int maxRotation, String location) {
		this.maxRotation = maxRotation;
		this.location = location;
	}
	public boolean roll() {
		++accumulatedRotation;		// roll = 회전 축적수 1씩 증가
		if( accumulatedRotation < maxRotation ) { // 회전축적수가 최대회전수를 넘기전까지
			System.out.println( location + " Tire 수명 : " 
				+ (maxRotation-accumulatedRotation) + "회");
			return true; //	최대회전수 - 회전축적수 = Tire 수명 , 0 반환
		}else {
			System.out.println("*** " + location +" Tire 펑크 ***");
			return false; // 회전축적수가 최대회전수를 넘었을 경우 Tire 펑크 , 1반환 
		}
	}
}
