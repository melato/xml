/* $Header: c:\\cvsroot/dev/java/aa/xml/XMLStringHandler.java,v 1.1 2007/12/01 22:56:36 athana Exp $
 * Copyright (c) Alex Athanasopoulos 2007
 */
package aa.xml;

import org.xml.sax.SAXException;

/** An XML Element handler that collects the tag content text as a string.
 * @author Alex Athanasopoulos
 * @date Dec 1, 2007
 */
public class XMLStringHandler extends XMLNullHandler {
	private StringBuilder buf;
	private	String text;

	@Override
	public void end() {
		text = buf.toString();
	}

	@Override
	public void start(XMLTag tag) {
		buf = new StringBuilder();
	}
	
	public void clear() {
		buf = null;
		text = null;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		buf.append( ch, start, length );
	}

	/** Return the content text of the XML Element, as a string */
	public String getText() {
		return text;
	}
}
