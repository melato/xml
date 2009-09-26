/* $Header: c:\\cvsroot/dev/java/aa/xml/XMLNullHandler.java,v 1.1 2007/12/01 22:56:36 athana Exp $
 * Copyright (c) Alex Athanasopoulos 2007
 */
package aa.xml;

import org.xml.sax.SAXException;

/** An element handler that does nothing.
 **/
public class XMLNullHandler implements XMLElementHandler {
	private static XMLElementHandler nullHandler = new XMLNullHandler();

	/** Return a static null handler. */
	public static XMLElementHandler getInstance() {
		return nullHandler;
	}
	
	public void end() {
	}

	public XMLElementHandler getHandler(XMLTag tag) {
		return nullHandler;
	}

	public void start(XMLTag tag) {
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
	}

}
