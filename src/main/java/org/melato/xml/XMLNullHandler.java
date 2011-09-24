/* $Header: c:\\cvsroot/dev/java/aa/xml/XMLNullHandler.java,v 1.1 2007/12/01 22:56:36 athana Exp $
 * Copyright (c) Alex Athanasopoulos 2007
 */
package org.melato.xml;

import org.xml.sax.SAXException;

/** An element handler that does nothing.
 **/
public class XMLNullHandler implements XMLElementHandler {
	/** A static null handler. */
	public static final XMLElementHandler INSTANCE = new XMLNullHandler();

	/** Return a static null handler. */
	public static XMLElementHandler getInstance() {
		return INSTANCE;
	}
	
	public void end() throws SAXException {
	}

	public XMLElementHandler getHandler(XMLTag tag) {
		return INSTANCE;
	}

	public void start(XMLTag tag) throws SAXException {
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
	}

}
