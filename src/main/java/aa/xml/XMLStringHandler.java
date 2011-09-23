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
	private	boolean recursive;
	private	boolean append;
	private	int		level;

	
	public XMLStringHandler() {
	}
	
	/**
	 * In recursive mode, the body text of children tags are also included, recursively.
	 * The default is non-recursive. 
	 * @param recursive
	 */
	public void setRecursive(boolean recursive) {
		this.recursive = recursive;
	}
	
	/**
	 * Enable append mode:  keep appending to the string until explicitly cleared.
	 * The default is false:  clear the string at the beginning of the tag.
	 * @param append
	 */
	public void setAppend(boolean append) {
		this.append = append;
	}
	
	@Override
	public XMLElementHandler getHandler(XMLTag tag) {
		if ( recursive ) {
			return this;
		}
		return super.getHandler(tag);
	}
	
	@Override
	public void start(XMLTag tag) throws SAXException {
		if ( ! append && level++ == 0 ) {
			clear();
		}
	}

	public boolean isTopLevel() {
		return level == 0;
	}
	
	@Override
	public void end() throws SAXException {
		level--;
	}
	
	public void clear() {
		buf = null;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if ( buf == null )
			buf = new StringBuilder();
		buf.append( ch, start, length );
	}

	/** Return the content text of the XML Element, as a string */
	public String getText() {
		if ( buf == null )
			return null;
		return buf.toString();
	}
}
