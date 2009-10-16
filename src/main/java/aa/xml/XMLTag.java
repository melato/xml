/* $Header: c:\\cvsroot/dev/java/aa/xml/XMLTag.java,v 1.1 2007/12/01 22:56:36 athana Exp $
 * Copyright (c) Alex Athanasopoulos 2007
 */
package aa.xml;

import org.xml.sax.Attributes;
import org.xml.sax.ext.Attributes2Impl;

/** Encapsulates the information of the starting tag of an XML Element.
 * 
 * @author Alex Athanasopoulos
 * @date Dec 1, 2007
 */ 
public class XMLTag {
	private String uri;
	private String localName;
	private String qName;
	private Attributes attributes;

	/** Constructor.
	 *  @see XMLReader.startElement
	 * @param uri
	 * @param localName
	 * @param qName
	 * @param atts
	 */
	public XMLTag(String uri, String localName, String qName, Attributes atts) {
		this.uri = uri;
		this.localName = localName;
		this.qName = qName;
		this.attributes = atts;
	}
	
	/** Simple way to create a tag.  Use for testing. */
	public XMLTag( String name ) {
		this( " ", name, name, new Attributes2Impl() );		
	}
	/** Return the name of the attribute.
	 *  Just uses qualified name for now.
	 * @return
	 */
	public String getName() {
		return qName;
	}
	
	/** Return the XML Attributes of the tag. */
	public Attributes getAttributes() {
		return attributes;
	}
	
	/** Get the value of an attribute, by name.
	 * 
	 * @param name
	 * @return  The value, or null if the attribute is not found.
	 */
	public String getAttribute( String name ) {
		return attributes.getValue( name );
	}
	
	/** Same as getAttribute(), but throws an exception if the attribute is not found.
	 * @param name
	 * @return  The value of the attribute.
	 */
	public String getRequiredAttribute( String name ) {
		String value = getAttribute( name );
		if ( value == null )
			throw new RuntimeException( "XML Attribute not found: " + name );
		return value;
	}
	
}
