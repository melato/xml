/* $Header: c:\\cvsroot/dev/java/aa/xml/DelegatingHandler.java,v 1.1 2007/12/01 22:56:35 athana Exp $
 * Copyright (c) Alex Athanasopoulos 2007
 */

package aa.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/** An implementation of ContentHandler that uses XMLElementHandlers
 * @author Alex Athanasopoulos
 * @date Dec 1, 2007
 */
class DelegatingHandler implements ContentHandler {
	private Logger logger = Logger.getLogger( DelegatingHandler.class.getName() );
	/** The current stack of handlers, containing all the nested handlers,
	 * up to and including the current handler.
	 */
	private List<XMLElementHandler> handlerStack = new ArrayList<XMLElementHandler>();
	private XMLElementHandler currentHandler;
	
	DelegatingHandler( XMLElementHandler rootHandler ) {
		logger.fine( "root: " + rootHandler.getClass().getName() );
		handlerStack.add( rootHandler );
		currentHandler = rootHandler;
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		currentHandler.characters(ch, start, length);
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ( logger.isLoggable( Level.FINE ) ) {
			logger.fine( "end:   " + qName + " " + currentHandler.getClass().getName() );
		}
		currentHandler.end();
		handlerStack.remove( handlerStack.size() - 1 );
		currentHandler = handlerStack.get( handlerStack.size() - 1 );
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
	}

	@Override
	public void setDocumentLocator(Locator locator) {
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
	}

	@Override
	public void startDocument() throws SAXException {
		if ( logger.isLoggable( Level.FINE ) ) {
			logger.fine( "startDocument: " + currentHandler.getClass().getName() );
		}
		currentHandler.start(null);
	}

	public void endDocument() throws SAXException {
		if ( logger.isLoggable( Level.FINE ) ) {
			logger.fine( "endDocument: " + currentHandler.getClass().getName() );
		}
		currentHandler.end();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts)
		throws SAXException {
		XMLTag tag = new XMLTag( uri, localName, qName, atts );
		XMLElementHandler nextHandler = currentHandler.getHandler( tag );
		if ( nextHandler == null )
			nextHandler = XMLNullHandler.getInstance();
		if ( logger.isLoggable( Level.FINE ) ) {
			logger.fine( "start: " + tag.getName() + " " + nextHandler.getClass().getName() );
		}
		currentHandler = nextHandler;
		handlerStack.add( currentHandler );
		currentHandler.start( tag );
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
	}
	
	

}
