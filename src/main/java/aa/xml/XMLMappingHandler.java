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
	
	public XMLElementHandler getHandler(XMLTag tag) {
		XMLElementHandler handler = handlerMap.get( tag.getName() );
		if ( handler == null )
			handler = XMLNullHandler.getInstance();
		return handler;
	}

	
}
