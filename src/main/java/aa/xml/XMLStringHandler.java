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
	private	boolean includeChildren;
	private	int		level;

	
	public XMLStringHandler(boolean includeChildren) {
		this.includeChildren = includeChildren;
	}
	
	public XMLStringHandler() {
	}
	
	public void setIncludeChildren(boolean includeChildren) {
		this.includeChildren = includeChildren;
	}
	
	@Override
	public XMLElementHandler getHandler(XMLTag tag) {
		if ( includeChildren ) {
			level++;
			return this;
		}
		return super.getHandler(tag);
	}
	
	@Override
	public void end() {
		if ( level > 0 ) {
			level--;
		} else {
			text = buf.toString();
		}
	}

	@Override
	public void start(XMLTag tag) {
		if ( level == 0 ) {
			buf = new StringBuilder();
		}
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
