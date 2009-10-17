/* $Header: c:\\cvsroot/dev/java/aa/xml/XMLElementHandler.java,v 1.1 2007/12/01 22:56:36 athana Exp $
 * Copyright (c) Alex Athanasopoulos 2007
 */
package aa.xml;

import org.xml.sax.SAXException;

/**
 * A modular handler for a single XML element.
 * An XMLElementHandler:
 * <ul>
 * <li>Receives data for the element that it handles, including calls to start() and end().
 * <li>Provides handlers for sub-elements.
 * <li>Receives calls to start() and end() at the beginning and end of the element that it handles. 
 * </ul>
 * @see XMLDelegator
 * @author Alex Athanasopoulos
 * @date Dec 1, 2007
 */
public interface XMLElementHandler {
	/** Start parsing the element.
	 * @param tag The XML tag.  It is null for the 'document' pseudo-element.
	 */
	public void start( XMLTag tag ) throws SAXException;
	
	/** Get a handler for a child element.
	 * @param tag  The tag of the child element.
	 * @return  The handler for the tag, or null if the element should be ignored.
	 */
	public XMLElementHandler getHandler( XMLTag tag );
	
	/** Add some content text to the current element.
	 *  @see org.xml.sax.ContentHandler#characters
	 * @param ch
	 * @param start
	 * @param length
	 * @throws SAXException
	 */
	public void characters(char[] ch, int start, int length) throws SAXException;
	
	/** End parsing of the current element. */
	public void end() throws SAXException;
}
