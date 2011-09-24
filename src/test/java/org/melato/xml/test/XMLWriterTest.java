package org.melato.xml.test;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Test;
import org.melato.xml.XMLWriter;


public class XMLWriterTest {
	public @Test void single() {
		StringWriter writer = new StringWriter();
		XMLWriter xml = new XMLWriter( writer );
		xml.tagOpen( "a" );
		xml.text( "test" );
		xml.tagEnd( "a" );
		Assert.assertEquals( "<a>test</a>", writer.toString() );
	}
	public @Test void escape() throws IOException {
		StringWriter writer = new StringWriter();
		XMLWriter xml = new XMLWriter( writer );
		xml.text( ">" );
		Assert.assertEquals( "&gt;", writer.toString() );
	}
	private void testAttribute( String value, String expected ) {		
		StringWriter writer = new StringWriter();
		XMLWriter xml = new XMLWriter( writer );
		xml.tagOpen( "a", false );
		xml.tagAttribute( "x", value );
		xml.tagClose();		
		Assert.assertEquals( ("<a x=\"" + expected + "\">"), writer.toString() );
	}
	public @Test void attribute() {		
		testAttribute( "plain", "plain" );
		testAttribute( "", "" );
		testAttribute( "\"", "&quote;" );
		testAttribute( "a\"", "a&quote;" );
		testAttribute( "\"b", "&quote;b" );
		testAttribute( "a\"b", "a&quote;b" );
	}
	public @Test void attributes() {		
		StringWriter writer = new StringWriter();
		XMLWriter xml = new XMLWriter( writer );
		xml.tagOpen( "a", false );
		xml.tagAttribute( "x", "1" );
		xml.tagClose();		
		Assert.assertEquals( "<a x=\"1\">", writer.toString() );
	}
	
}
