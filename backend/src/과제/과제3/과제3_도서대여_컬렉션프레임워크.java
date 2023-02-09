package 과제.과제3;

import java.util.ArrayList;
import java.util.Scanner;

public class 과제3_도서대여_컬렉션프레임워크 {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		ArrayList<Book> bookList = new ArrayList<>();
		
		while(true) { // while s
			System.out.println("번호\t대여여부\t도서장르\t도서명");
			for(int i = 0 ; i<bookList.size(); i++ ) {
				System.out.println(i +"\t" + bookList.get(i).rent + "\t" + bookList.get(i).name + "\t" + bookList.get(i).category);
			}
			
			// 출력부
			System.out.println(" 메뉴 > 1.도서대여 2.도서반납 3.도서등록[관리자] ");
			int ch = scanner.nextInt();
			// 도서대여
			if(ch == 1) {
				System.out.println("--- 대여 페이지 ---");
				System.out.println("대여 할 도서번호 : "); int no = scanner.nextInt();
				
				if(bookList.get(no).rent == true) { // 대입은 == 2개
					System.out.println("도서 대여완료");
					bookList.get(no).rent = false;	// 변경은 =  1개
				}else {
					System.out.println("대여불가");
				}
			}
			
			// 도서반납
			else if(ch == 2) {
				System.out.println("--- 반납 페이지 ---");
				System.out.println("반납 할 도서번호 : "); int no = scanner.nextInt();
					if( bookList.get(no).rent == false) {
					System.out.println("도서 반납완료");
					bookList.get(no).rent = true;
				}else {
					System.out.println("반납불가");
				}
			}
			
			// 도서등록[관리자]
			else if(ch == 3) {
				System.out.println("--- 등록 페이지 ---");
				System.out.println("등록할 도서명 : "); String inputName = scanner.next();
				System.out.println("도서장르 : "); 	String inputCategory = scanner.next();
													boolean inputRent = true;
				
				Book book = new Book();
				book.name = inputName; book.category = inputCategory; book.rent = inputRent;
				bookList.add(book); 
			}
			
			else { System.err.println("잘못된 메뉴 선택입니다.");}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		} // while e
		
	}
}
