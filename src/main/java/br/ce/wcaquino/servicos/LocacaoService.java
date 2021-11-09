package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import exceptions.FilmeSemEstoqueException;
import exceptions.LocadoraException;

import org.junit.Assert;
import org.junit.Test;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, List<Filme> filme) throws FilmeSemEstoqueException, LocadoraException {
		
		if(usuario == null) {
			throw new LocadoraException("Usu�rio vazio!");
		}
		
		if(filme == null || filme.isEmpty()) {
			throw new LocadoraException("Filme vazio!");
		}
		
		for(Filme fm: filme) {
			if(fm.getEstoque() == 0) {
				throw new FilmeSemEstoqueException("Filme sem estoque!");
			}
		}
		
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		Double valorTotal = 0d;
		for(int i = 0; i < filme.size();i++) {
			Double valorFilme = filme.get(i).getPrecoLocacao();
			switch (i) {
				case 2: valorFilme = valorFilme*0.75; break;
				case 3: valorFilme = valorFilme*0.5; break;
				case 4: valorFilme = valorFilme*0.25; break;
				case 5: valorFilme = 0.0; break;
			}
			valorTotal += valorFilme;
		}
		locacao.setValor(valorTotal);

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		if(DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)) {
			dataEntrega = adicionarDias(dataEntrega, 1);
		}
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...			
		return locacao;
	}
}