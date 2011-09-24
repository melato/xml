package org.melato.xml;

import org.xml.sax.SAXException;

/**
 * An XMLElementHandler that does nothing.
 * @author Alex Athanasopoulos
 */
public class AbstractHandler implements XMLElementHandler {

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		
	}

	@Override
	public void end() throws SAXException {
		
	}

	@Override
	public XMLElementHandler getHandler(XMLTag tag) {
		return null;
	}

	@Override
	public void start(XMLTag tag) throws SAXException {
	}

}
