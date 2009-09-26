/* $Header: c:\\cvsroot/dev/java/aa/xml/XMLElementHandler.java,v 1.1 2007/12/01 22:56:36 athana Exp $
 * Copyright (c) Alex Athanasopoulos 2007
 */
package aa.xml;

import org.xml.sax.SAXException;

/** A modular handler for a single XML element.
 * The modularity of the handler consists in delegating child elements
 * to other handlers.
 * @see XMLDelegator
 * @author Alex Athanasopoulos
 * @date Dec 1, 2007
 */
public interface XMLElementHandler {
	/** Start parsing the tag.
	 * @param tag The XML tag.
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
