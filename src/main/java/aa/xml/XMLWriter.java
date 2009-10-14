/*
 * @(#)HTMLWriter.java	1.40 06/04/07
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package aa.xml;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

import org.xml.sax.Attributes;

/**
 * This is a writer for XML Documents.
 */
public class XMLWriter {
    private	PrintWriter	writer;
	private	boolean		useNewlines = false;
    
	public void setUseNewlines(boolean useNewlines) {
		this.useNewlines = useNewlines;
	}
	
    /**
     * Temporary buffer.
     */
    private char[] tempChars;

    public XMLWriter(Writer writer ) {
    	this.writer = new PrintWriter( writer );
    }

    public XMLWriter(OutputStream out) {
    	this.writer = new PrintWriter( out );
    }
    
    public void println() {
    	writer.println();
    }
    
    public void write( XMLTag tag ) {
    	tagOpen( tag.getName(), false );
    	Attributes attributes = tag.getAttributes();
    	int n = attributes.getLength();
    	for( int i = 0; i < n; i++ ) {
    		tagAttribute( attributes.getQName(i), attributes.getValue(i));
    	}
    	tagClose();
    }
    
    public void tagAttribute( String name, String value ) {
    	writer.write( " " );
		writer.write( name );
		writer.write( "=" );
		writer.write( '"' );
		int length = value.length();
		char[] cc = getTempChars(length);
		value.getChars( 0, length, cc, 0 );
		int start = 0;
		for (int i = 0; i < length; i++ ) {
			char c = cc[i];
			switch( c ) {
			case '"':
				writer.write( cc, start, i - start );
				writer.write( "&quote;" );
				start = i + 1;
				break;
			default:
				break;
			}
		}
		writer.write( cc, start, length - start );
		writer.write( '"' );    				
    }
    
    public void tagEnd( String tag ) {    	
    	writer.write( "</" );
    	writer.write( tag );
    	writer.write( ">" );
    }

    public void tagClose() {    	
    	writer.write( ">" );
    }
    
    public void tagOpen( String tag ) {
    	tagOpen( tag, true );
    }
    
    public void tagOpen( String tag, boolean close ) {
    	if ( useNewlines ) {
    		writer.println();
    	}
    	writer.write( "<" );
    	writer.write( tag );
    	if ( close ) {
    		tagClose();
    	}
    }
    
    /**
     * This method is overriden to map any character entities, such as
     * &lt; to &amp;lt;. <code>super.output</code> will be invoked to
     * write the content.
     * @since 1.3
     */
    public void text(char[] chars, int start, int length)
	           {
	int last = start;
	length += start;
	for (int counter = start; counter < length; counter++) {
	    // This will change, we need better support character level
	    // entities.
	    switch(chars[counter]) {
		// Character level entities.
	    case '<':
		if (counter > last) {
		    writer.write(chars, last, counter - last);
		}
		last = counter + 1;
		writer.write("&lt;");
		break;
	    case '>':
		if (counter > last) {
		    writer.write(chars, last, counter - last);
		}
		last = counter + 1;
		writer.write("&gt;");
		break;
	    case '&':
		if (counter > last) {
		    writer.write(chars, last, counter - last);
		}
		last = counter + 1;
		writer.write("&amp;");
		break;
	    case '"':
		if (counter > last) {
		    writer.write(chars, last, counter - last);
		}
		last = counter + 1;
		writer.write("&quot;");
		break;
		// Special characters
	    case '\n':
	    case '\t':
	    case '\r':
		break;
	    default:
		if (chars[counter] < ' ' || chars[counter] > 127) {
		    if (counter > last) {
			writer.write(chars, last, counter - last);
		    }
		    last = counter + 1;
		    // If the character is outside of ascii, write the
		    // numeric value.
		    writer.write("&#");
		    writer.write(String.valueOf((int)chars[counter]));
		    writer.write(";");
		}
		break;
	    }
	}
	if (last < length) {
	    writer.write(chars, last, length - last);
	}
    }

    private char[] getTempChars( int length ) {
    	if (tempChars == null || tempChars.length < length) {
    	    tempChars = new char[length];
    	}
    	return tempChars;
    }
    
    /**
     * This directly invokes super's <code>output</code> after converting
     * <code>string</code> to a char[].
     */
    public void text(String string) {
	int length = string.length();
	char[] cc = getTempChars(length);
	string.getChars(0, length, cc, 0);
	text(cc, 0, length);
    }

    public PrintWriter getWriter() {
		return writer;
	}
    
    public void flush() {
    	writer.flush();
    }
    
    public void close() {
    	writer.close();
    }
}
