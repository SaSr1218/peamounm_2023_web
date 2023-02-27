package Day19.Ex5;

import java.util.Scanner;

public class 실행 {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		boolean musicState = false; // 음악재생 여부
		boolean movieState = false; // 영화재생 여부
		
		//
		Thread thread = new Music() ;
		thread.start();
		
		Music music = new Music(); 
		Movie movie = new Movie(); 
		
		while (true) {
			System.out.println("1.음악재생/중지 2.영화재생/중지 : ");
			int ch = scanner.nextInt();
			// 1번 입력하고 음악 중지되어있으면 음악실행
			if ( ch == 1 && musicState == false) { 
				music.start();
				musicState = true;
				music.stop = true;
			// 1번 입력하고 음악 실행중이면 음악중지
			}else if ( ch == 1 && musicState == true ) {
				musicState = false;		// 음악재생 여부 false;
				music.stop = false;		
				music = new Music();	// 음악스레드 초기화
			}
			// 2번 입력하고 영화 중지되어있으면 영화실행
			else if ( ch == 2 && movie.stop == false ) {
				movie.start();
				movie.stop = true;
			}
			// 2번 입력하고 영화 실행중이면 영화중지
			else if ( ch == 2 && movie.stop == true ) {
				movie.stop = false;
				movie = new Movie();
			}	
		} // while end
		
	}
}

/*
if ( ch == 1 && music.stop == false) {
	music.start();
	music.stop = true;
	}
	
	이렇게도 가능! musicState 변수를 쓴 이유는 조금 더 안전하게 하기 위함;

*/
