package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import ar.fiuba.tecnicas.formato.Formato;
import ar.fiuba.tecnicas.logging.Niveles;

public class FormatoTest 
{
	
	@Test
    public void testFormatoNumeroLinea() 
    {
		// Nota: Test peculiar, su correcta ejecucion depende del numero de linea
		// Por favor no mover a otro lugar en este archivo, o inventar algo mejor
		Formato formato = new Formato("%L", null);
        assertEquals("19",formato.darFormato(null,null));
    }
	
	@Test
    public void testFormatoArchivo() 
    {
		// Nota: Test peculiar, su correcta ejecucion depende del numero de linea
		// Por favor no mover a otro lugar en este archivo, o inventar algo mejor
		Formato formato = new Formato("%F", null);
        assertEquals("FormatoTest.java",formato.darFormato(null,null));
    }
	
	@Test
    public void testFormatoMetodo() 
    {
		// Nota: Test peculiar, su correcta ejecucion depende del numero de linea
		// Por favor no mover a otro lugar en este archivo, o inventar algo mejor
		Formato formato = new Formato("%M", null);
        assertEquals("testFormatoMetodo",formato.darFormato(null,null));
    }
	
	@Test
    public void testFormatoTexto() 
    {
    	Formato formato = new Formato("Texto cualquiera.", null);
        assertEquals("Texto cualquiera.",formato.darFormato(null,null));
    }
	
	@Test
    public void testFormatoNivel() 
    {
        Formato formato = new Formato("%p", null);
        assertEquals("info",formato.darFormato(null,Niveles.info));
        assertEquals("warning",formato.darFormato(null,Niveles.warning));
    }
	
	@Test
    public void testFormatoThread() 
    {
    	Formato formato = new Formato("%t", null);
        assertEquals(Thread.currentThread().getName(),formato.darFormato(null,null));
    }
	
	@Test
    public void testFormatoMensaje() 
    {
    	Formato formato = new Formato("%m", null);
        assertEquals("Un mensaje.",formato.darFormato("Un mensaje.",null));
    }
	
	@Test
    public void testFormatoEscape() 
    {
    	Formato formato = new Formato("%%", null);
        assertEquals("%",formato.darFormato(null,null));
        formato.setFormato("%%%", null);
        assertEquals("%%",formato.darFormato(null,null));
        formato.setFormato("%%%%", null);
        assertEquals("%%",formato.darFormato(null,null));
    }
	
	@Test
    public void testFormatoSeparador() 
    {
		Formato formato = new Formato("%n", "-");
        assertEquals("-",formato.darFormato(null,null));
        formato.setFormato("%n", "@");
        assertEquals("@",formato.darFormato(null,null));
    }
	
	@Test
    public void testFormatoFecha() 
    {
		Formato formato = new Formato("%d{yyyy/MM/dd HH:mm:ss}", null);
        assertEquals(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        		.format(new Date()),formato.darFormato(null,null));
    }
	
	@Test
    public void testFormatoCompuesto() 
    {
		Formato formato = new Formato("[%d{HH:mm:ss}] %n [%p] %n [%M] %n [%m]", "-");
		assertEquals("["+new SimpleDateFormat("HH:mm:ss").format(new Date())+
        		"] - [fatal] - [testFormatoCompuesto] - [Fallo algo.]",
        		formato.darFormato("Fallo algo.",Niveles.fatal));        
    }
	   
}
