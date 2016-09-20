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
		estados.add("Amapá");
		estados.add("Amazonas");
		estados.add("Bahia");
		estados.add("Ceará");
		estados.add("Distrito Federal");
		estados.add("Espírito Santo");
		estados.add("Goiás");
		estados.add("Maranhão");
		estados.add("Mato Grosso");	
		estados.add("Mato Grosso do Sul");
		estados.add("Minas Gerais");
		estados.add("Pará");
		estados.add("Paraíba");
		estados.add("Paraná");
		estados.add("Pernambuco");
		estados.add("Piauí");
		estados.add("Rio de Janeiro");
		estados.add("Rio Grande do Norte");
		estados.add("Rio Grande do Sul");
		estados.add("Rondônia");
		estados.add("Roraima");
		estados.add("Santa Catarina");
		estados.add("São Paulo");
		estados.add("Sergipe");
		estados.add("Tocantins");
		return estados;
	}

	public static void setEstados(List<String> estados) {
		EstadosBrasileiros.estados = estados;
	}

	
}
