import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

public class Main {

	public static void main(String[] args) throws IOException {
		
		String path = new File(".").getCanonicalPath();
		System.out.print(path);
	}

}
