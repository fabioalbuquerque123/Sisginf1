package util;

import java.io.InputStream;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

public class Portaria {
	
	private static Map<String, Integer> parametros;
	private static InputStream inputStream;
	
	public static void gerarPortaria(int id){						
		try{
			parametros = new HashedMap<>();
			parametros.put("ID_PROJETO", id);
			//inputStream.getClass().getResourceAsStream(arg0);												
		}catch(Exception e){
			e.printStackTrace();
		}				
	}

	public static Map<String, Integer> getParametros() {
		return parametros;
	}

	public static void setParametros(Map<String, Integer> parametros) {
		Portaria.parametros = parametros;
	}

	public static InputStream getInputStream() {
		return inputStream;
	}

	public static void setInputStream(InputStream inputStream) {
		Portaria.inputStream = inputStream;
	}
}
