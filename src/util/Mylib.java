package util;

import java.util.*;

public class Mylib {
	//전화번호를 하이폰으로 자라는 기능
	public static String makePhoneNumber(String phoneNoStr) {
	      String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
	      if(!java.util.regex.Pattern.matches(regEx, phoneNoStr)) return null;
	      return phoneNoStr.replaceAll(regEx, "$1-$2-$3");
	   }
}
