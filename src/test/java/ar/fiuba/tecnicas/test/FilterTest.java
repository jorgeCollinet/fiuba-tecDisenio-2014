package ar.fiuba.tecnicas.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ar.fiuba.tecnicas.filter.FilterBuilder;
import ar.fiuba.tecnicas.filter.FilterCustom;
import ar.fiuba.tecnicas.filter.FilterNivel;
import ar.fiuba.tecnicas.filter.FilterNombre;
import ar.fiuba.tecnicas.filter.FilterRegex;
import ar.fiuba.tecnicas.filter.IFilter;
import ar.fiuba.tecnicas.logging.Niveles;

public class FilterTest {

	@Test
	public void BuilderFilterBuildFromString() {
		String LoggerData = "sapoPepe,Output>console,BehaveRegex>esto_es_una_expresion_regular,BehaveClass>ar.fiuba.tecnicas.filter.FilterCustomHorario";
		ArrayList<IFilter> filters = FilterBuilder.generateFilters(Niveles.info, LoggerData);
		
		IFilter filterNombre = filters.get(0);
		IFilter filterNivel = filters.get(1);
		IFilter filterRegex = filters.get(2);
		IFilter filterCustomClass = filters.get(3);
		
		assertEquals(FilterNombre.class,filterNombre.getClass());
		assertEquals(FilterNivel.class,filterNivel.getClass());
		assertEquals(FilterRegex.class,filterRegex.getClass());
		assertEquals(FilterCustom.class,filterCustomClass.getClass());
		
	}

}
