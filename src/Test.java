
public class Test {

	public static void main(String[] args) {
		String password = "QWer1234";
		char[] passwordChar = password.toCharArray();
		for (char c : passwordChar) {
			System.out.println(c);
		}
		java.util.Arrays.fill(passwordChar, '\0');
		for (char c : passwordChar) {
			System.out.println(c);
		}
	}
}
