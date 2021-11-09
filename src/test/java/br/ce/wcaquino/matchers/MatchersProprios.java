package br.ce.wcaquino.matchers;

import java.util.Calendar;

public class MatchersProprios {
	
	public static DiaSemanaMatcher caiEm(Integer diaSemana) {
		return new DiaSemanaMatcher(diaSemana);
	}
	
	public static DiaSemanaMatcher caiNumDomingo() {
		return new DiaSemanaMatcher(Calendar.SUNDAY);
	}
	
	public static DataDiferencaDiasMatcher eHojeComDiferencaDias(Integer qtdDias) {
		return new DataDiferencaDiasMatcher(qtdDias);
	}

}
