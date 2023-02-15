package Day11.Ex7_타입변환;

import java.util.ArrayList;

import Day11.Ex6.HankookTrie;
import Day11.Ex6.KumhoTire;
import Day11.Ex6.Tire;

public class 실행2 {
	public static void main(String[] args) {
		
		// *
		Tire tire = new Tire();
		KumhoTire kumhoTire = new KumhoTire();
		HankookTrie hankookTrie = new HankookTrie();
		
		// 1. 참조타입의 자동타입 변환
		Tire temp = tire;
			Tire temp2 = kumhoTire;
				Tire temp3 = hankookTrie;
					Object temp4 = temp3;
					
		// 2. 강제타입 변환
		Tire tire2 = (Tire)temp4;
				KumhoTire kumhoTire2 = (KumhoTire)tire;
					HankookTrie hankookTrie2 = (HankookTrie)tire;
					
		// 3. 매개변수 , 리턴타입
		Tire result1 = 함수1( kumhoTire2 );
		KumhoTire result2 = (KumhoTire)함수2( (KumhoTire)temp2 );
		
		// 4. 배열 ArrayList : 동일한 타입만 가능
		ArrayList<KumhoTire> kumhoList = new ArrayList<>();
		kumhoList.add(kumhoTire);
		//kumhoList.add(hankookTrie); // X 형제라서
		//kumhoList.add( tire );		// X 부모라서
		
		// ----->	List를 1개 만들고 같이 저장. 슈퍼클래스를 넣기<Tire>
		ArrayList<Tire> TireList = new ArrayList<>();
		TireList.add(kumhoTire);	// O
		TireList.add(hankookTrie);	// O
		TireList.add(tire);			// O
		
	} // main
	public static KumhoTire 함수1( Tire tire ) { return new KumhoTire(); }
	public static Tire 함수2 ( KumhoTire kumhoTire ) { return new Tire(); }
}
