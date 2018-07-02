package metro;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		Metro m = new Metro("metro.in");
		m.resolver();
	}

}
