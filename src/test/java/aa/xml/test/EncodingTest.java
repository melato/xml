package aa.xml.test;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import aa.xml.XMLDelegator;
import aa.xml.XMLStringHandler;

public class EncodingTest {
	public @Test void parseGreek() throws IOException, SAXException {
		XMLStringHandler stringHandler = new XMLStringHandler();
		stringHandler.setRecursive(true);
		InputStream input = getClass().getResourceAsStream("abc.xml");
		try {
			XMLDelegator.parse( stringHandler, input );
			String text = stringHandler.getText();
			/*
			char[] cc = text.toCharArray();
			for( char c: cc ) {
				System.out.println( Integer.toHexString(c));
			}
			*/
			Assert.assertEquals( "ΑΒΓαβγ", text );
		} finally {
			input.close();
		}
	}
}
