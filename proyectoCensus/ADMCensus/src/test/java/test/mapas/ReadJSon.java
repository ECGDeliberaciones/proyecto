package test.mapas;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.codehaus.jackson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
public class ReadJSon {
	  public static void main(String[] args) {
	 
			JSONParser parser = new JSONParser();
	 
			try {
	 
				Object obj = parser.parse(new FileReader("http://localhost:8080/ADMCensus/census/prueba.do"));
	 
				JSONObject jsonObject = (JSONObject) obj;
	 
				String blog = (String) jsonObject.get("Blog");
				System.out.println(blog);
	 
				String temas = (String) jsonObject.get("Temas");
				System.out.println(temas);
				
				long inicio = (Long) jsonObject.get("Inicio");
				System.out.println(inicio);
	 
				JSONObject innerObject = (JSONObject) jsonObject.get("Posts");
				System.out.println(innerObject.toJSONString());
				
				// loop array
				JSONArray tags = (JSONArray) jsonObject.get("Tags");
				Iterator<String> iterator = tags.iterator();
				while (iterator.hasNext()) {
					System.out.println(iterator.next());
				}
	 
			} catch (FileNotFoundException e) {
				//manejo de error
			} catch (IOException e) {
				//manejo de error
			} catch (ParseException e) {
				//manejo de error
			}
	 
		}
	 
	
}