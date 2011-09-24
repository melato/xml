package org.melato.xml.test;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.melato.xml.XMLDelegator;
import org.melato.xml.XMLMappingHandler;
import org.melato.xml.XMLStringHandler;
import org.xml.sax.SAXException;


public class PathTest {
	public @Test void longPath() throws IOException, SAXException {
		XMLMappingHandler rootHandler = new XMLMappingHandler();
		XMLStringHandler stringHandler = new XMLStringHandler();
		rootHandler.setPathHandler( "a/b/c", stringHandler );
		InputStream input = getClass().getResourceAsStream("path.xml");
		try {
			XMLDelegator.parse( rootHandler, input );
			Assert.assertEquals( "c-content", stringHandler.getText() );
		} finally {
			input.close();
		}
	}
}
