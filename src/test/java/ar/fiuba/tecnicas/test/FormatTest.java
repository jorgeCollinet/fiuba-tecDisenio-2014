package ar.fiuba.tecnicas.test;

import static org.junit.Assert.assertEquals;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;

import ar.fiuba.tecnicas.format.Format;
import ar.fiuba.tecnicas.format.JSONFormat;
import ar.fiuba.tecnicas.logging.Level;

public class FormatTest 
{
	
	@Test
    public void formatLineNumber() 
    {
		// Nota: Test peculiar, su correcta ejecucion depende del numero de linea
		Format format = new Format("%L");
        assertEquals("20",format.giveFormat(null,null));
    }
	
	@Test
    public void formatFile() 
    {
		// Nota: Test peculiar, su correcta ejecucion depende del nombre
		// de este archivo de test.
		Format format = new Format("%F");
        assertEquals("FormatTest.java",format.giveFormat(null,null));
    }
	
	@Test
    public void formatMethodName() 
    {
		// Nota: Test peculiar, su correcta ejecucion depende del nombre de este metodo
		Format format = new Format("%M");
        assertEquals("formatMethodName",format.giveFormat(null,null));
    }
	
	@Test
    public void formatText() 
    {
    	Format format = new Format("Texto cualquiera.");
        assertEquals("Texto cualquiera.",format.giveFormat(null,null));
    }
	
	@Test
    public void formatLevel() 
    {
        Format format = new Format("%p");
        assertEquals("info",format.giveFormat(null,Level.info));
        assertEquals("warning",format.giveFormat(null,Level.warning));
    }
	
	@Test
    public void formatThreadName() 
    {
    	Format format = new Format("%t");
        assertEquals(Thread.currentThread().getName(),format.giveFormat(null,null));
    }
	
	@Test
    public void formatMessage() 
    {
    	Format format = new Format("%m");
        assertEquals("Un mensaje.",format.giveFormat("Un mensaje.",null));
        format = new Format("%m");
        assertEquals("Un mensaje.",format.giveFormat("Un mensaje.",Level.trace,"nombreLogger"));
    }
	
	@Test
    public void formatEscape() 
    {
    	Format format = new Format("%%");
        assertEquals("%",format.giveFormat(null,null));
        format.setFormat("%%F%");
        assertEquals("%F%",format.giveFormat(null,null));
        format.setFormat("%%%%");
        assertEquals("%%",format.giveFormat(null,null));
    }
	
	@Test
    public void formatSeparator() 
    {
		Format format = new Format("%n");
        assertEquals(Format.separadorDefault,format.giveFormat(null,null));
        format.setFormat("%n", "@");
        assertEquals("@",format.giveFormat(null,null));
    }
	
	@Test
    public void formatDate() 
    {
		Format format = new Format("%d{yyyy/MM/dd HH:mm:ss}");
        assertEquals(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        		.format(new Date()),format.giveFormat(null,null));
    }
	
	@Test
    public void formatLoggerName() 
    {
		Format format = new Format("%g");
        assertEquals("logger",format.giveFormat(null,null,"logger"));
    }
	
	@Test
    public void compositeFormat() 
    {
		Format format = new Format("[%d{HH:mm:ss}] %n [%p] %n [%M] %n [%m]", "_");
		assertEquals("["+new SimpleDateFormat("HH:mm:ss").format(new Date())+
        		"] _ [fatal] _ [compositeFormat] _ [Test.]",
        		format.giveFormat("Test.",Level.fatal));        
    }
	
	@Test
	public void JSONFormat()
	{
		Format format = new JSONFormat("%p %M %m %g");
		assertEquals("{'level': 'fatal', 'method': 'JSONFormat', 'message': 'Test.', 'logger': 'lnombre'}",
        		format.giveFormat("Test.",Level.fatal,"lnombre"));   
	}
	   
}
