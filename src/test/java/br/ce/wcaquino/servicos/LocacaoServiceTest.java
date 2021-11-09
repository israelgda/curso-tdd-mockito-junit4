package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import exceptions.FilmeSemEstoqueException;
import exceptions.LocadoraException;

public class LocacaoServiceTest {

	private static final Calendar dataHoje = Calendar.getInstance();
	private static final int diaHoje = dataHoje.get(Calendar.DAY_OF_WEEK);
	
	private LocacaoService service;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		service = new LocacaoService();
	}
	

	@Test
	public void testeLocacao() throws Exception {
		// cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filme = Arrays.asList(new Filme("Filme 1", 2, 5.0));

		// acao
		Locacao locacao;

		locacao = service.alugarFilme(usuario, filme);

		// verificacao
		error.checkThat(locacao.getValor(), CoreMatchers.is(5.0));
		assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		
		if(diaHoje == Calendar.SATURDAY) {
			assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(2)));
		}else {
			assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
		}
		

	}

	@Test(expected = FilmeSemEstoqueException.class)
	public void testeLocacaoFilmeSemEstoque() throws Exception {
		// cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filme = Arrays.asList(new Filme("Filme 1", 0, 5.0));

		service.alugarFilme(usuario, filme);
	}

//	@Test
//	public void testeLocacaoFilmeSemEstoque2() throws Exception {
//		// cenario
//		Usuario usuario = new Usuario("Usuario 1");
//		List<Filme> filme = Arrays.asList(new Filme("Filme 1", 0, 5.0));
//
//		try {
//			service.alugarFilme(usuario, filme);
//			fail("Deveria ter lançado uma exceção!");
//		} catch (FilmeSemEstoqueException e) {
//			assertEquals("Filme sem estoque!", e.getMessage());
//		}
//	}
//
//	@Test
//	public void testeLocacaoFilmeSemEstoque3() throws FilmeSemEstoqueException, LocadoraException {
//		// cenario
//		Usuario usuario = new Usuario("Usuario 1");
//		List<Filme> filme = Arrays.asList(new Filme("Filme 1", 0, 5.0));
//
//		exception.expect(FilmeSemEstoqueException.class);
//		exception.expectMessage("Filme sem estoque!");
//
//		// Ação
//		service.alugarFilme(usuario, filme);
//	}

	@Test
	public void testeLocacaoUsuarioVazio() throws FilmeSemEstoqueException {

		// cenario
		List<Filme> filme = Arrays.asList(new Filme("Filme 1", 2, 5.0));

		// Ação
		try {
			service.alugarFilme(null, filme);
		} catch (LocadoraException e) {
			assertEquals("Usuário vazio!", e.getMessage());
		}
	}

	@Test
	public void testeLocacaoFilmeVazio() throws FilmeSemEstoqueException, LocadoraException {
		// cenario
		Usuario usuario = new Usuario("Usuario 1");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio!");
		
		//Acao
		service.alugarFilme(usuario, null);
		
	}
	
//	@Test
//	public void devePagar75PorCentoNoFilme3() throws FilmeSemEstoqueException, LocadoraException {
//		
//		Usuario usuario = new Usuario("Usuário 1");
//		List<Filme> filmes = Arrays.asList(
//				new Filme("Filme 1", 2, 4.0),
//				new Filme("Filme 2", 2, 4.0),
//				new Filme("Filme 3", 2, 4.0));
//		
//		Locacao resultado = service.alugarFilme(usuario, filmes);
//		
//		assertEquals(11.0, resultado.getValor(), 0.01);
//	}
//	
//	@Test
//	public void devePagar50PorCentoNoFilme4() throws FilmeSemEstoqueException, LocadoraException {
//		
//		Usuario usuario = new Usuario("Usuário 1");
//		List<Filme> filmes = Arrays.asList(
//				new Filme("Filme 1", 2, 4.0),
//				new Filme("Filme 2", 2, 4.0),
//				new Filme("Filme 3", 2, 4.0),
//				new Filme("Filme 4", 2, 4.0));
//		
//		Locacao resultado = service.alugarFilme(usuario, filmes);
//		
//		assertEquals(13.0, resultado.getValor(), 0.01);
//	}
//	
//	@Test
//	public void devePagar25PorCentoNoFilme5() throws FilmeSemEstoqueException, LocadoraException {
//		
//		Usuario usuario = new Usuario("Usuário 1");
//		List<Filme> filmes = Arrays.asList(
//				new Filme("Filme 1", 2, 4.0),
//				new Filme("Filme 2", 2, 4.0),
//				new Filme("Filme 3", 2, 4.0),
//				new Filme("Filme 4", 2, 4.0),
//				new Filme("Filme 5", 2, 4.0));
//		
//		Locacao resultado = service.alugarFilme(usuario, filmes);
//		
//		assertEquals(14.0, resultado.getValor(), 0.01);
//	}
//	
//	@Test
//	public void sextoFilmeDeveFicarDeGraca() throws FilmeSemEstoqueException, LocadoraException {
//		
//		Usuario usuario = new Usuario("Usuário 1");
//		List<Filme> filmes = Arrays.asList(
//				new Filme("Filme 1", 2, 4.0),
//				new Filme("Filme 2", 2, 4.0),
//				new Filme("Filme 3", 2, 4.0),
//				new Filme("Filme 4", 2, 4.0),
//				new Filme("Filme 5", 2, 4.0),
//				new Filme("Filme 6", 2, 4.0));
//		
//		Locacao resultado = service.alugarFilme(usuario, filmes);
//		
//		assertEquals(14.0, resultado.getValor(), 0.01);
//	}
	
	@Test
	public void naoDeveDevolverFilmeNoDomingo() throws FilmeSemEstoqueException, LocadoraException {
		
		Usuario usuario = new Usuario("Usuário 1");
		List<Filme> filmes = Arrays.asList(
				new Filme("Filme 1", 2, 4.0));
		
		Locacao resultado = service.alugarFilme(usuario, filmes);
		
		boolean verificacao = DataUtils.verificarDiaSemana(resultado.getDataRetorno(), Calendar.SUNDAY);
		assertFalse(verificacao);
	}
}
