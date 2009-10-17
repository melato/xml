/* $Header: c:\\cvsroot/dev/java/aa/xml/XMLMappingHandler.java,v 1.1 2007/12/01 22:56:36 athana Exp $
 * Copyright (c) Alex Athanasopoulos 2007
 */
package aa.xml;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xml.sax.SAXException;

/**
 * An XML Element handler that delegates tags to handlers through a map of handlers.
 * It also delegates its element body to another handler. 
 * @author Alex Athanasopoulos
 * @date Dec 1, 2007
 */
public class XMLMappingHandler implements XMLElementHandler {
	private Map<String,XMLElementHandler> handlerMap = new HashMap<String,XMLElementHandler>();
	private	XMLElementHandler	bodyHandler = XMLNullHandler.getInstance();
	private Logger logger = Logger.getLogger(XMLMappingHandler.class.getName());
	
	private static class EmptyPathException extends RuntimeException {
		private static final long serialVersionUID = 1L;		
	}

	/** Associate an XML sub tag with a handler. */
	public void setHandler( String tag, XMLElementHandler handler ) {
		handlerMap.put( tag, handler );
	}
	
	/** Define a handler for the element body.
	 *  It handles the start, characters, and end calls. */
	public void setBodyHandler( XMLElementHandler handler ) {
		this.bodyHandler = handler;
	}

	public void setPathHandler( String[] path, XMLElementHandler handler ) {
		XMLMappingHandler leafHandler = this;
		for( int i = 0; i < path.length - 1; i++ ) {
			String tag = path[i];
			if ( tag.length() == 0 ) {
				throw new EmptyPathException();
			}
			XMLElementHandler h = leafHandler.handlerMap.get( tag );
			if ( h == null ) {
				h = new XMLMappingHandler();
				leafHandler.setHandler( tag, h );
			} else if ( ! (h instanceof XMLElementHandler) ) {
				throw new RuntimeException( "not a mapping handler" );
			}
			leafHandler = (XMLMappingHandler) h;
		}
		leafHandler.setHandler( path[path.length-1], handler );
	}
		
	public void setPathHandler( String path, XMLElementHandler handler ) {
		String[] pp = path.split( "/" );
		try {
			setPathHandler( pp, handler );
		} catch( EmptyPathException e ) {
			throw new IllegalArgumentException( "Empty path component in: " + path );
		}
	}

	public XMLElementHandler getHandler(XMLTag tag) {
		XMLElementHandler handler = handlerMap.get( tag.getName() );
		if ( handler == null )
			handler = XMLNullHandler.getInstance();
		if ( logger.isLoggable(Level.FINE )) {
			logger.fine( tag.getName() + ": " + handler.getClass().getName() );
		}
		return handler;
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		bodyHandler.characters(ch, start, length);
	}

	public void end() throws SAXException {
		bodyHandler.end();
	}

	public void start(XMLTag tag) throws SAXException {
		bodyHandler.start(tag);
	}
}
