package json;

public class Json {
	public static String startList(String name){
		return "\""+name+"\": {\n";
	}
	public static String endList(boolean comma){
		return endEnclose(comma);
	}
	public static String startArray(String name, boolean newLine){
		return "\""+name+"\": [";
	}
	public static String startArray(String name){
		return "\""+name+"\": [\n";
	}
	public static String endArray(boolean comma){
		return "]"+(comma?",":"")+"\n";
	}
	public static String enclose(){
		return "{\n";
	}
	public static String endEnclose(boolean comma){
		return "}"+(comma?",":"")+"\n";
	}
	public static String addKey(String key, String value, boolean comma){
		return "\""+
				key+
				"\" : \""+
				value+
				"\""+
				(comma?",":"")+"\n";
	}
	public static String addKey(String key, boolean value, boolean comma){
		return "\""+
				key+
				"\" : "+
				value+
				(comma?",":"")+"\n";
	}
	public static String addKey(String key, int value, boolean comma){
		return "\""+
				key+
				"\" : "+
				value+
				(comma?",":"")+"\n";
	}
	
	public static String removeComma(String input){
		StringBuilder sb=new StringBuilder(input);
		return sb.substring(0, sb.lastIndexOf(",")).concat("\n");
	}

	public static String addComma(String input){
		StringBuilder sb=new StringBuilder(input);
		if((sb.charAt(sb.length()-1)+"").equals("\n")){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(",\n");
		return sb.toString();
	}

}
