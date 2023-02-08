package Day06;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class Ex6_비회원게시판_변수배열버전 {
	public static void main(String[] args) throws Exception {
		
		// step1 : 필요한 데이터를 입력받아 저장
		// step2 : 쓰기 페이지 실행되는 조건
		
		// * 입력객체
		Scanner sa = new Scanner(System.in);
		
		while(true) { // 1. 무한루프 [ 종료조건 : -2 입력시 ]
			
			// 현재 파일에 존재하는 모든 문자열 호출
			// 1. 파일 클래스 객체 생성 ( 파일경로 )
			FileInputStream fin = new FileInputStream("c:/java/board.txt");
			// 2. 읽어온 바이트를 저장하기 위해 바이트배열 1000바이트 미리 생성
			byte[] inbytes = new byte[ 1000 ];	// 영문 1바이트 , 한글 3바이트
			// 3. .read( ) 메소드를 이요한 파일 읽기 [ * 읽은 바이트를 바이트배열 저장 ]
				// inbytes : 읽어온 바이트가 배열에 저장
				// bytecount : 읽어온 바이트의 개수
			int bytecount =  fin.read( inbytes );		
			// 4. 바이트배열 ---> 문자열
				// new String("유재석");
				// new String( 바이트배열 ); new String( 바이트배열 , 시작인덱스 , 마지막인덱스 );  =  0부터 읽어온 바이트 개수만큼 문자열 반환
			String fStr = new String( inbytes , 0 , bytecount );
			
			//* 행 기준 자르기
			String[] boards = fStr.split("\n");	// 행 기준으로 분리 [ 만약에 게시물2개일때는 2조각 ]
			// 행 기준 자른 후 : boards = { "안녕하세요,자바글쓰기,유재석,1234", "호호호호호,자바글쓰기,강호동,4567)

			System.out.println("-----------------게시물 목록------------------");
			System.out.printf("%2s %10s%5s  \n" , "번호" , "제목" , "작성자");
			//*[행마다] 열 기준 자르기
			for ( int i = 0 ; i<boards.length; i++ ) {
				// 게시물마다 열 자르기
				String[] cols =  boards[i].split(","); // 4조각
				String title = cols[0]; 
				String content = cols[1]; 
				String writer = cols[2]; 
				String password = cols[3]; 
				System.out.printf("%2d %10s%5s  \n" , i , title , writer);
			}
			System.out.println("------------------------------------------");
			
			/*
		 	// boards = { "안녕하세요,자바글쓰기,유재석,1234" , "호호호호호,자바글쓰기,강호동,4567" } 
		 	for i = 0일때
		 			boards[0] = "안녕하세요,자바글쓰기,유재석,1234"
		 			cols = { "안녕하세요" , "자바글쓰기" , "유재석" ,"1234" } 
		 	for i = 1일때 
		  			boards[1] = "호호호호호,자바글쓰기,강호동,4567"
		  			cols = { "호호호호호" , "자바글쓰기" , "강호동" ,"4567" } 
		 */
			 
			System.out.println("메뉴 > 게시물보기[번호] 쓰기[-1] 나가기[-2] ");
			int ch = sa.nextInt();
			if( ch == -1 ) {
				System.out.println(" 제목 : "); 		String title = sa.next();
				System.out.println(" 내용 : "); 		String content = sa.next();
				System.out.println(" 작성자 : "); 	String writer = sa.next();
				System.out.println(" 비밀번호 : "); 	String password = sa.next();
				
				// , : 열 구분선	\n : 행 구분선 (메모장이니까)
				String outStr = title+","+content+","+writer+","+password+"\n";
				
				// 1. 파일처리 [ 클래스 ]
				FileOutputStream fout = new FileOutputStream("c:/java/board.txt", true);
				fout.write( outStr.getBytes() );
			}
			else if( ch == -2 ) { break; }
			
			
			
			
			
			
			
			
			} // while end
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
