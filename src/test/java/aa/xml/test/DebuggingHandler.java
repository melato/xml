/* $Header: c:\\cvsroot/dev/java/aa/xml/test/DebuggingHandler.java,v 1.1 2007/12/01 22:57:03 athana Exp $
 * Copyright (c) Alex Athanasopoulos 2007
 */
package aa.xml.test;

import java.io.File;

import org.xml.sax.SAXException;

import aa.xml.XMLDelegator;
import aa.xml.XMLElementHandler;
import aa.xml.XMLNullHandler;
import aa.xml.XMLTag;

/** An XMLElementHandler that prints its calls to stdout.
 * @author Alex Athanasopoulos
 * @date Dec 1, 2007
 */
public class DebuggingHandler extends XMLNullHandler {

	public void end() {
		System.out.println( "end" );
	}

	public XMLElementHandler getHandler(XMLTag tag) {
		return this;
	}

	public void start(XMLTag tag) {
		System.out.println( "start " + tag.getName() );
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.print( "characters: " );
		System.out.print( new String( ch, start, length ));
		System.out.println();
	}

	public static void main(String[] args) throws Exception {
		if ( args.length != 1 )
			throw new RuntimeException( "Usage: <filename>");
		XMLDelegator.parse( new DebuggingHandler(), new File( args[0] ));
	}
}
