package main;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import DTO.*;
import util.Mylib;
import DB.*;
import DAO.*;
public class SubClass {
	Scanner sc;
	public SubClass() {
		 sc = new Scanner(System.in);
	}


	public void start() {
		for(;;) {
		sc = new Scanner(System.in);
		UserDAOImpl dao = new UserDAOImpl();
		System.out.println("회원가입을 원하시면 아무거나 입력해주세요..");
		System.out.println("ID : ");
		String id = sc.next();
		UserDTO re = dao.selectid(id);
		//ID 검사
		if(re == null) {
			System.out.println("없는 아이디 입니다. 회원가입을 진행합니다.");
			System.out.print("아이디를 입력하세요 : ");
			id = sc.next();
			System.out.print("비밀번호를 입력하세요 : ");
			String pw = sc.next();			
			System.out.print("이름을 입력하세요 : ");
			String name = sc.next();
			System.out.print("이메일을 입력하세요 : ");
			String em = sc.next();
	        String emresult = em.substring(0,(em.lastIndexOf("@")));
	        String addrresult = em.substring(em.lastIndexOf("@")+1);  
	        System.out.print("전화번호를 입력하세요 : ");
			String hp= sc.next();
			String tel = Mylib.makePhoneNumber(hp);
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
			Calendar cal = Calendar.getInstance();
			String today = null;
			today = formatter.format(cal.getTime());
			Timestamp tm = Timestamp.valueOf(today);
			re = new UserDTO();
			re.setId(id);
			re.setPw(pw);
			re.setName(name);
			re.setEm(emresult);
			re.setAddr(addrresult);
			re.setHp(tel);
			re.setTm(tm);
			dao.insert(re);
		}else {
			System.out.println("PW : ");
			String pw = sc.next();
			//PW 검사
			if(re.getPw().equals(pw)) {
				System.out.println("로그인 완료");
				System.out.println("당신의 정보입니다.");
				System.out.println("아이디: "+re.getId());
				System.out.println("패스워드 : "+re.getPw());
				System.out.println("이름 : "+re.getName());
				System.out.println("이메일: "+re.getEm()+"@"+re.getAddr());
				System.out.println("휴대폰: "+re.getHp());
				System.out.println("가입날짜: "+re.getTm());
				break;
			}else {
				System.out.println("비밀 번호가 틀렸습니다.");
			}
		}
	}
	}
}
