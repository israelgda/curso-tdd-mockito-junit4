package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import exceptions.FilmeSemEstoqueException;
import exceptions.LocadoraException;

public class CalculoValorLocacaoTest {
	
	private LocacaoService service;
	
	private List<Filme> filmes = Arrays.asList(
			new Filme("Filme 1", 2, 4.0),
			new Filme("Filme 2", 2, 4.0),
			new Filme("Filme 3", 2, 4.0),
			new Filme("Filme 4", 2, 4.0),
			new Filme("Filme 5", 2, 4.0),
			new Filme("Filme 6", 2, 4.0));
	
	private Double valorLocacao;
		
	@Before
	public void setup() {
		service = new LocacaoService();
	}
	
	@Test
	public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeSemEstoqueException, LocadoraException {
		
		Usuario usuario = new Usuario("Usuário 1");
		
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		assertEquals(resultado.getValor(), valorLocacao);
	}

}
