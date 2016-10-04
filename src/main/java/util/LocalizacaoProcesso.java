package util;

import java.util.ArrayList;
import java.util.List;

public class LocalizacaoProcesso {
	
	private static List<String> localizaoSituacaoProcesso;

	public LocalizacaoProcesso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static List<String> getLocalizaoSituacaoProcesso() {
		localizaoSituacaoProcesso = new ArrayList<String>();
		localizaoSituacaoProcesso.add("");
		localizaoSituacaoProcesso.add("Arquivado no DGLP");
		localizaoSituacaoProcesso.add("Em an�lise pela CGDP/DGLP");
		localizaoSituacaoProcesso.add("Encaminhado para ASSJUR");
		localizaoSituacaoProcesso.add("Com pend�ncias ap�s an�lise da ASSJUR");
		localizaoSituacaoProcesso.add("Aguardando chancela da portaria pela ASSJUR");
		localizaoSituacaoProcesso.add("Aguardando assinatura da portaira pelo Ministro");
		localizaoSituacaoProcesso.add("Enviado � Antaq");	
		return localizaoSituacaoProcesso;
	}

	public static void setLocalizaoSituacaoProcesso(List<String> localizaoSituacaoProcesso) {
		LocalizacaoProcesso.localizaoSituacaoProcesso = localizaoSituacaoProcesso;
	}
}
