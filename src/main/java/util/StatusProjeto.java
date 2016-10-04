package util;

import java.util.ArrayList;
import java.util.List;

public class StatusProjeto {

	private static List<String> listStatusProjeto;		

	public StatusProjeto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static List<String> getListStatusProjeto() {
		listStatusProjeto = new ArrayList<String>();
		listStatusProjeto = new ArrayList<String>();
		listStatusProjeto.add("");
		listStatusProjeto.add("Aprovado");
		listStatusProjeto.add("Em análise");
		listStatusProjeto.add("Rejeitado");
		return listStatusProjeto;
	}

	public static void setListStatusProjeto(List<String> listStatusProjeto) {
		StatusProjeto.listStatusProjeto = listStatusProjeto;
	}
}
