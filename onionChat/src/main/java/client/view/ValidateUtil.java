package client.view;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
	
	private static int MAX_LOOP_CNT = 3;
	
	public static boolean rangeLength(String value, int min, int max) {
		if (value == null || value.trim().length() < min || value.trim().length() > max) {
			return false;
		}
		return true;
	}
	
	public static boolean containsEng(String value) {
		return ValidateUtil.regex(value, "[a-zA-Z]+");
	}
	
	public static boolean containsDigit(String value) {
		return ValidateUtil.regex(value, "\\d+");
	}
	
	public static boolean onlyDigit(String value) {
		return ValidateUtil.regex(value, "^\\d+$");
	}
	
	public static void onlyDigit(String value, String msg) {
		if (ValidateUtil.regex(value, "^\\d+$") == false) {
			throw new ValidateException(msg);
		}
	}
	
	public static void canParseFloat(String value, String msg) {
		try {
			Float.parseFloat(value);
		} catch (NumberFormatException e) {
			throw new ValidateException(msg);
		} catch (Exception e) {
			throw new ValidateException(msg);
		}
	}
	
	public static boolean containsSpecialChar(String value) {
		String specialChars = "~․!@#$%^&*()_-+={}[]|\\;:'\"<>,.?/";
		for (int i = 0; i < specialChars.length(); i++) {
			if (value.indexOf(specialChars.charAt(i)) > -1) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean regex(String value, String regex) {
		if (value == null || regex == null) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		if (!matcher.find()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 한글 체크
	 *
	 * @param value
	 * @return
	 */
	public static boolean isKo(String value) {
		
		String regex = ".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*";
		if (value == null) {
			return false;
		}
		return regex(value, regex);
	}
	
	/**
	 * 동일문자 반복 체크
	 *
	 * @param userPwd
	 * @return
	 */
	public static boolean loopChar(String userPwd) {
		int tmp = 0;
		int loopCnt = 0;
		for (int i = 0; i < userPwd.length(); i++) {
			if (userPwd.charAt(i) == tmp) {
				loopCnt++;
			} else {
				loopCnt = 0;
			}
			if (loopCnt == (MAX_LOOP_CNT - 1)) {
				return true;
			}
			tmp = userPwd.charAt(i);
		}
		return false;
	}
	
	/**
	 * 연속문자 체크
	 *
	 * @param userPwd
	 * @return
	 */
	public static boolean continuosChar(String userPwd) {
		int tmp = 0;
		int reverseLoopCnt = 0;
		int loopCnt = 0;
		for (int i = 0; i < userPwd.length(); i++) {
			int gap = userPwd.charAt(i) - tmp;
			if (gap == 1) {
				reverseLoopCnt = 0;
				loopCnt++;
			} else if (gap == -1) {
				reverseLoopCnt++;
				loopCnt = 0;
			} else {
				reverseLoopCnt = 0;
				loopCnt = 0;
			}
			if (loopCnt == (MAX_LOOP_CNT - 1) || reverseLoopCnt == (MAX_LOOP_CNT - 1)) {
				return true;
			}
			tmp = userPwd.charAt(i);
		}
		return false;
	}
	
	public static void password(String newPwd) {
		password(null, newPwd, null);
	}
	
	public static void password(String userId, String newPwd) {
		password(userId, newPwd, null);
	}
	
	public static void weakPassword(String newPwd) {
		if (newPwd == null) {
			throw new ValidateException("비밀번호는 반드시 입력해 주세요.");
		}
		if (ValidateUtil.rangeLength(newPwd, 6, 20) == false) {
			throw new ValidateException("비밀번호는 6 ~ 20자리까지 입력해 주세요.");
		}
		if (ValidateUtil.containsEng(newPwd) == false) {
			throw new ValidateException("비밀번호는 영문/숫자 모두 1문자 이상 포함되게 입력해 주세요.");
		}
		if (ValidateUtil.containsDigit(newPwd) == false) {
			throw new ValidateException("비밀번호는 영문/숫자 모두 1문자 이상 포함되게 입력해 주세요.");
		}
	}
	
	public static void password(String userId, String newPwd, String encCurrentPwd) {
		if (newPwd == null) {
			throw new ValidateException("비밀번호는 반드시 입력해 주세요.");
		}
		if (userId != null && newPwd.contains(userId)) {
			throw new ValidateException("비밀번호는 사용자 ID가 포함되지 않게 입력해 주세요.");
		}
		if (ValidateUtil.rangeLength(newPwd, 8, 20) == false) {
			throw new ValidateException("비밀번호는 8 ~ 20자리까지 입력해 주세요.");
		}
		if (ValidateUtil.containsEng(newPwd) == false) {
			throw new ValidateException("비밀번호는 영문/숫자/특수 문자 모두 1문자 이상 조합되게 입력해 주세요.");
		}
		if (ValidateUtil.containsDigit(newPwd) == false) {
			throw new ValidateException("비밀번호는 영문/숫자/특수 문자 모두 1문자 이상 조합되게 입력해 주세요.");
		}
		if (ValidateUtil.containsSpecialChar(newPwd) == false) {
			throw new ValidateException("비밀번호는 영문/숫자/특수 문자 모두 1문자 이상 조합되게 입력해 주세요.");
		}
		if (ValidateUtil.loopChar(newPwd)) {
			throw new ValidateException("비밀번호는 연속적인 문자/숫자(예:111,123,abc)가 포함되지 않게 입력해 주세요.");
		}
		if (ValidateUtil.continuosChar(newPwd)) {
			throw new ValidateException("비밀번호는 연속적인 문자/숫자(예:111,123,abc)가 포함되지 않게 입력해 주세요.");
		}
		if (encCurrentPwd != null && encCurrentPwd.equals(newPwd)) {
			throw new ValidateException("변경 전 비밀번호는 사용할 수 없습니다. 새로운 비밀번호를 입력해 주세요.");
		}
	}
	
	public static void userId(String loginId) {
		if (ValidateUtil.regex(loginId, "^[a-zA-Z0-9]{6,20}$") == false) {
			throw new ValidateException("아이디는 영문, 숫자 조합으로 6~20자로 입력해주세요.");
		}
		if (ValidateUtil.regex(loginId, "^[a-zA-Z]") == false) {
			throw new ValidateException("아이디는 영문으로 시작해 주세요.");
		}
	}
	
	public static void notNull(Object value, String msg, Object cause) {
		if (value == null) {
			throw new ValidateException(msg, cause);
		}
	}
	
	public static void notNull(Object value, String msg) {
		if (value == null) {
			throw new ValidateException(msg);
		}
	}
	
	public static void notEmpty(Collection<?> value, String msg) {
		if (value == null || value.size() == 0) {
			throw new ValidateException(msg);
		}
	}
	
	public static void notEmpty(Collection<?> value, String msg, Object cause) {
		if (value == null || value.size() == 0) {
			throw new ValidateException(msg, cause);
		}
	}
	
	public static void notEmpty(Map<?, ?> value, String msg) {
		if (value == null || value.size() == 0) {
			throw new ValidateException(msg);
		}
	}
	
	public static void isNull(Object value, String msg) {
		if (value != null) {
			throw new ValidateException(msg);
		}
	}
	
	public static void isTrue(boolean value, String msg) {
		if (!value) {
			throw new ValidateException(msg);
		}
	}
	
	public static void isTrue(boolean value, String msg, Object cause) {
		if (!value) {
			throw new ValidateException(msg, cause);
		}
	}
	
	public static void isNotTrue(boolean value, String msg) {
		if (value) {
			throw new ValidateException(msg);
		}
	}
	
	public static void error(String msg, Object cause) {
		throw new ValidateException(msg, cause);
	}
	
	public static void notEmpty(String src, String msg) {
		if (src == null || src.trim().isEmpty()) {
			throw new ValidateException(msg);
		}
	}
	
	public static void email(String email, String msg) {
		String regex = "(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)";
		if (ValidateUtil.regex(email, regex) == false) {
			throw new ValidateException(msg);
		}
	}
	
	public static void isEmpty(Collection<?> collection, String msg) {
		if (collection != null && collection.size() > 0) {
			throw new ValidateException(msg);
		}
	}
	
	
	public static class ValidateException extends RuntimeException {
		
		private static final long serialVersionUID = -594115403693602549L;
		private String strCause = null;
		
		public ValidateException(String msg) {
			super(msg);
		}
		
		public ValidateException(String msg, Object cause) {
			super(msg);
			this.strCause = String.valueOf(cause);
		}
		
		public String getStrCause() {
			return strCause;
		}
	}
}