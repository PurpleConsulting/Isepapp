import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

public class Main {

	public static void main(String[] args) {
		JSONObject result = new JSONObject();
		JSONObject js = new JSONObject();
		String[] array = {"ldivad","ldchanta","ldzozo","ldbilly","ldldldl","ldtruc"};
		js.put("pseudo", array);
		result.put("result", js);
		
		System.out.print(result.toString());
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("1", 1);
		map.put("2", 2);
		map.put("3", 3);
		
		Integer i = map.get("7");
		System.out.print(i);
		
	}

}
