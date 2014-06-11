package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ar.fiuba.tecnicas.filter.FilterBuilder;
import ar.fiuba.tecnicas.filter.FilterCustom;
import ar.fiuba.tecnicas.filter.FilterData;
import ar.fiuba.tecnicas.filter.FilterNivel;
import ar.fiuba.tecnicas.filter.FilterNombre;
import ar.fiuba.tecnicas.filter.FilterRegex;
import ar.fiuba.tecnicas.filter.IFilter;
import ar.fiuba.tecnicas.logging.Level;

public class FilterTest {

	@Test
	public void BuilderFilterBuildFromString() {
		String LoggerData = "sapoPepe,Output>console,BehaveRegex>esto_es_una_expresion_regular,BehaveClass>ar.fiuba.tecnicas.test.FilterCustomMock";
		ArrayList<IFilter> filters = FilterBuilder.generateFilters(Level.info, LoggerData);
		
		IFilter filterNombre = filters.get(0);
		IFilter filterNivel = filters.get(1);
		IFilter filterRegex = filters.get(2);
		IFilter filterCustomClass = filters.get(3);
		
		assertEquals(FilterNombre.class,filterNombre.getClass());
		assertEquals(FilterNivel.class,filterNivel.getClass());
		assertEquals(FilterRegex.class,filterRegex.getClass());
		assertEquals(FilterCustom.class,filterCustomClass.getClass());
	}
	
	@Test
	public void filterNombre() {
		FilterNombre filterNombre = new FilterNombre("logger1");
		FilterData filterData = new FilterData(Level.trace, "logger1", "mensaje prueba");
		assertTrue(filterNombre.hasToLog(filterData));
	}
	
	@Test
	public void filterNivel() {
		FilterNivel filterNivel = new FilterNivel(Level.trace);
		FilterData filterData = new FilterData(Level.trace, "logger1", "mensaje prueba");
		assertTrue(filterNivel.hasToLog(filterData));
	}
	
	@Test
	public void filterRegex() {
		FilterRegex filterRegex = new FilterRegex("mensaje.*");
		FilterData filterData = new FilterData(Level.trace, "logger1", "mensaje prueba");
		assertTrue(filterRegex.hasToLog(filterData));
	}
	
	@Test
	public void filterCustom() throws Exception {
		FilterCustom filterCustom = FilterCustom.generateFilterCustom("ar.fiuba.tecnicas.test.FilterCustomMock");
		FilterData filterData = new FilterData(Level.trace, "logger1", "mensaje prueba");
		assertTrue(filterCustom.hasToLog(filterData));
	}
}
