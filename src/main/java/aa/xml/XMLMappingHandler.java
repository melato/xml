/* $Header: c:\\cvsroot/dev/java/aa/xml/XMLMappingHandler.java,v 1.1 2007/12/01 22:56:36 athana Exp $
 * Copyright (c) Alex Athanasopoulos 2007
 */
package aa.xml;

import java.util.HashMap;
import java.util.Map;

/** An XML Element handler that delegates tags to handlers through a map of handlers.
 * @author Alex Athanasopoulos
 * @date Dec 1, 2007
 */
public class XMLMappingHandler extends XMLNullHandler {
	private Map<String,XMLElementHandler> handlerMap = new HashMap<String,XMLElementHandler>();

	/** Associate an XML sub tag with a handler. */
	public void setHandler( String tag, XMLElementHandler handler ) {
		handlerMap.put( tag, handler );
	}

	public void setPathHandler( String path, XMLElementHandler handler ) {
		XMLMappingHandler leafHandler = this;
		String[] pp = path.split( "/" );
		for( int i = 0; i < pp.length - 1; i++ ) {
			String tag = pp[i];
			if ( tag.length() == 0 ) {
				throw new IllegalArgumentException( "Empty path component in: " + path );
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
		leafHandler.setHandler( pp[pp.length-1], handler );
	}
		
	public XMLElementHandler getHandler(XMLTag tag) {
		XMLElementHandler handler = handlerMap.get( tag.getName() );
		if ( handler == null )
			handler = XMLNullHandler.getInstance();
		return handler;
	}
}
