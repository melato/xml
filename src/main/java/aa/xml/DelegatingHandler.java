/* $Header: c:\\cvsroot/dev/java/aa/xml/DelegatingHandler.java,v 1.1 2007/12/01 22:56:35 athana Exp $
 * Copyright (c) Alex Athanasopoulos 2007
 */

package aa.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/** An implementation of ContentHandler that uses XMLElementHandlers
 * @author Alex Athanasopoulos
 * @date Dec 1, 2007
 */
class DelegatingHandler implements ContentHandler {
	/** The current stack of handlers, containing all the nested handlers,
	 * up to and including the current handler.
	 */
	private List<XMLElementHandler> handlerStack = new ArrayList<XMLElementHandler>();
	private XMLElementHandler currentHandler;
	
	DelegatingHandler( XMLElementHandler rootHandler ) {
		handlerStack.add( rootHandler );
		currentHandler = rootHandler;
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		currentHandler.characters(ch, start, length);
	}

	public void endDocument() throws SAXException {
	}

	public void endElement(String arg0, String arg1, String arg2)
			throws SAXException {
		currentHandler.end();
		handlerStack.remove( handlerStack.size() - 1 );
		currentHandler = handlerStack.get( handlerStack.size() - 1 );
	}

	@Override
	public void endPrefixMapping(String arg0) throws SAXException {
	}

	@Override
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
			throws SAXException {
	}

	@Override
	public void processingInstruction(String arg0, String arg1)
			throws SAXException {
	}

	@Override
	public void setDocumentLocator(Locator arg0) {
	}

	@Override
	public void skippedEntity(String arg0) throws SAXException {
	}

	@Override
	public void startDocument() throws SAXException {
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts)
		throws SAXException {
		XMLTag tag = new XMLTag( uri, localName, qName, atts );
		XMLElementHandler nextHandler = currentHandler.getHandler( tag );
		if ( nextHandler == null )
			nextHandler = XMLNullHandler.getInstance();
		currentHandler = nextHandler;
		handlerStack.add( currentHandler );
		currentHandler.start( tag );
	}

	@Override
	public void startPrefixMapping(String arg0, String arg1)
			throws SAXException {
	}
	
	

}
