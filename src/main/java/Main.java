import org.json.JSONObject;

public class Main {

	public static void main(String[] args) {
		JSONObject result = new JSONObject();
		JSONObject js = new JSONObject();
		String[] array = {"ldivad","ldchanta","ldzozo","ldbilly","ldldldl","ldtruc"};
		js.put("pseudo", array);
		result.put("result", js);
		
		System.out.print(result.toString());
	}

}
