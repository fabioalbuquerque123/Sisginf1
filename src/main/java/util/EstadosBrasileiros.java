package util;

import java.util.ArrayList;
import java.util.List;

public class EstadosBrasileiros {

	private static List<String> estados;
	
	public EstadosBrasileiros() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static List<String> getEstados() {
		estados = new ArrayList<String>();
		estados.add("");
		estados.add("AC");
		estados.add("AL");
		estados.add("AM");
		estados.add("AP");
		estados.add("BA");
		estados.add("CE");
		estados.add("DF");
		estados.add("ES");
		estados.add("GO");
		estados.add("MA");
		estados.add("MG");			
		estados.add("MS");
		estados.add("MT");
		estados.add("PA");
		estados.add("PB");
		estados.add("PE");
		estados.add("PI");		
		estados.add("PR");
		estados.add("RJ");
		estados.add("RN");
		estados.add("RO");
		estados.add("RR");
		estados.add("RS");		
		estados.add("SC");
		estados.add("SE");
		estados.add("SP");		
		estados.add("TO");
		return estados;
	}

	public static void setEstados(List<String> estados) {
		EstadosBrasileiros.estados = estados;
	}

	
}
