package json;

public class Json {
	public static String startList(String name){
		return "\""+name+"\": {\n";
	}
	public static String endList(){
		return "},\n";
	}
	public static String startArray(String name){
		return "\""+name+"\": [\n";
	}
	public static String endArray(){
		return "],\n";
	}
	public static String enclose(){
		return "{\n";
	}
	public static String endEnclose(){
		return "},\n";
	}
	public static String addKey(String key, String value){
		return "\""+
				key+
				"\" : \""+
				value+
				"\""+
				",\n";
	}
	public static String addKey(String key, boolean value){
		return "\""+
				key+
				"\" : \""+
				value+
				"\""+
				",\n";
	}
	public static String addKey(String key, int value){
		return "\""+
				key+
				"\" : "+
				value+
				",\n";
	}
	
	public static String removeComma(String input){
		StringBuilder sb=new StringBuilder(input);
		return sb.substring(0, sb.lastIndexOf(",")).concat("\n");
	}


}
