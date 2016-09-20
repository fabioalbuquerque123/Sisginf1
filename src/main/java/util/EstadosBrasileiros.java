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
		estados.add("Acre");
		estados.add("Alagoas");
		estados.add("Amap�");
		estados.add("Amazonas");
		estados.add("Bahia");
		estados.add("Cear�");
		estados.add("Distrito Federal");
		estados.add("Esp�rito Santo");
		estados.add("Goi�s");
		estados.add("Maranh�o");
		estados.add("Mato Grosso");	
		estados.add("Mato Grosso do Sul");
		estados.add("Minas Gerais");
		estados.add("Par�");
		estados.add("Para�ba");
		estados.add("Paran�");
		estados.add("Pernambuco");
		estados.add("Piau�");
		estados.add("Rio de Janeiro");
		estados.add("Rio Grande do Norte");
		estados.add("Rio Grande do Sul");
		estados.add("Rond�nia");
		estados.add("Roraima");
		estados.add("Santa Catarina");
		estados.add("S�o Paulo");
		estados.add("Sergipe");
		estados.add("Tocantins");
		return estados;
	}

	public static void setEstados(List<String> estados) {
		EstadosBrasileiros.estados = estados;
	}

	
}
