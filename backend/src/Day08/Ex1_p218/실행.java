package Day08.Ex1_p218;

public class 실행 {
	public static void main(String[] args) {
		// 1. Car 클래스를 이용한 객체 생성
		// 클래스명 변수명 = new 생성자명();
		
		 // 2,3 번을 써야함. 1번은 다이렉트. 유효성검사가 안됨. 문제 확률 높아짐
		 // 2,3번의 경우 유효성검사를 해주고 통과하면 this로 값을 받으면 된다.
		
		// 1. 기본생성자                        
		Car car = new Car();					
		car.model="그랜저"; car.color="노랑";
		System.out.println( car.toString() );

		// 2. 2개의 매개변수 생성자
		Car car3 = new Car("그랜저", "파랑");
		System.out.println( car3.toString() ); // .toString() = 객체 주소값 할당
		
		// 3. 3개의 매개변수 생성자
		Car car2 = new Car("그랜저", "검정", 250);
		System.out.println( car2.toString() );
		
		
		
		
	}

}
