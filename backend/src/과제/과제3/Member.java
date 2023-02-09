package 과제.과제3;

import java.util.ArrayList;

public class Member {
	String id;
	String password;
	String name;
	String phone;
	ArrayList<Book> rentList = new ArrayList<>(); // Member에 Book 집어넣기
}
