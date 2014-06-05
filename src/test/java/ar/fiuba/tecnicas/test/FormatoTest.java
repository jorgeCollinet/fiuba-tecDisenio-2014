package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import ar.fiuba.tecnicas.formato.Formato;
import ar.fiuba.tecnicas.formato.FormatoJSON;
import ar.fiuba.tecnicas.logging.Niveles;

public class FormatoTest 
{
	
	@Test
    public void formatoNumeroLinea() 
    {
		// Nota: Test peculiar, su correcta ejecucion depende del numero de linea
		Formato formato = new Formato("%L");
        assertEquals("19",formato.darFormato(null,null));
    }
	
	@Test
    public void formatoArchivo() 
    {
		// Nota: Test peculiar, su correcta ejecucion depende del nombre
		// de este archivo de test.
		Formato formato = new Formato("%F");
        assertEquals("FormatoTest.java",formato.darFormato(null,null));
    }
	
	@Test
    public void formatoMetodo() 
    {
		// Nota: Test peculiar, su correcta ejecucion depende del nombre de este metodo
		Formato formato = new Formato("%M");
        assertEquals("formatoMetodo",formato.darFormato(null,null));
    }
	
	@Test
    public void formatoTexto() 
    {
    	Formato formato = new Formato("Texto cualquiera.");
        assertEquals("Texto cualquiera.",formato.darFormato(null,null));
    }
	
	@Test
    public void formatoNivel() 
    {
        Formato formato = new Formato("%p");
        assertEquals("info",formato.darFormato(null,Niveles.info));
        assertEquals("warning",formato.darFormato(null,Niveles.warning));
    }
	
	@Test
    public void formatoThread() 
    {
    	Formato formato = new Formato("%t");
        assertEquals(Thread.currentThread().getName(),formato.darFormato(null,null));
    }
	
	@Test
    public void formatoMensaje() 
    {
    	Formato formato = new Formato("%m");
        assertEquals("Un mensaje.",formato.darFormato("Un mensaje.",null));
        formato = new Formato("%m");
        assertEquals("Un mensaje.",formato.darFormato("Un mensaje.",Niveles.trace,"nombreLogger"));
    }
	
	@Test
    public void formatoEscape() 
    {
    	Formato formato = new Formato("%%");
        assertEquals("%",formato.darFormato(null,null));
        formato.setFormato("%%F%");
        assertEquals("%F%",formato.darFormato(null,null));
        formato.setFormato("%%%%");
        assertEquals("%%",formato.darFormato(null,null));
    }
	
	@Test
    public void formatoSeparador() 
    {
		Formato formato = new Formato("%n");
        assertEquals(Formato.separadorDefault,formato.darFormato(null,null));
        formato.setFormato("%n", "@");
        assertEquals("@",formato.darFormato(null,null));
    }
	
	@Test
    public void formatoFecha() 
    {
		Formato formato = new Formato("%d{yyyy/MM/dd HH:mm:ss}");
        assertEquals(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        		.format(new Date()),formato.darFormato(null,null));
    }
	
	@Test
    public void formatoNombreLogger() 
    {
		Formato formato = new Formato("%g");
        assertEquals("logger",formato.darFormato(null,null,"logger"));
    }
	
	@Test
    public void formatoCompuesto() 
    {
		Formato formato = new Formato("[%d{HH:mm:ss}] %n [%p] %n [%M] %n [%m]", "_");
		assertEquals("["+new SimpleDateFormat("HH:mm:ss").format(new Date())+
        		"] _ [fatal] _ [formatoCompuesto] _ [Test.]",
        		formato.darFormato("Test.",Niveles.fatal));        
    }
	
	@Test
	public void formatoJSON()
	{
		Formato formato = new FormatoJSON("%p %M %m %g");
		assertEquals("{'level': 'fatal', 'method': 'formatoJSON', 'message': 'Test.', 'logger': 'lnombre'}",
        		formato.darFormato("Test.",Niveles.fatal,"lnombre"));   
	}
	   
}
