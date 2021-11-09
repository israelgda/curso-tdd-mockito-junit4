package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import exceptions.DivisaoPorZeroException;
import junit.framework.Assert;

public class CalculadoraTest {
	
	private Calculadora calc;
	
	@Before
	public void setup() {
		calc = new Calculadora();
	}

	@Test
	public void deveSomarDoisValores() {
		int a = 5;
		int b = 3;

		int resultado = calc.somar(a, b);

		assertEquals(8, resultado);
	}

	@Test
	public void deveSubtrairDoisValores() {
		int a = 5;
		int b = 3;

		int resultado = calc.subtrair(a, b);

		assertEquals(2, resultado);
	}

	@Test
	public void deveDividirDoisValores() throws DivisaoPorZeroException {
		int a = 6;
		int b = 3;

		int resultado = calc.dividir(a, b);

		assertEquals(2, resultado);
	}

	@Test(expected = DivisaoPorZeroException.class)
	public void deveLancarExceptionSeDivisaoPorZero() throws DivisaoPorZeroException {
		int a = 6;
		int b = 0;

		int resultado = calc.dividir(a, b);
	}

}
