package org.melato.xml;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import javax.swing.text.html.HTML;

public class HTMLWriter extends XMLWriter {

	public HTMLWriter(OutputStream out) throws IOException {
		super(out);
		setNewlines(true);
	}

	public HTMLWriter(Writer writer) {
		super(writer);
		setNewlines(true);
	}
	
	public void anchor( String url, String label ) {
		tagOpen( "a", false );
		tagAttribute( "href", url );
		tagClose();
		text( label );
		tagEnd( "a" );
	}
	
	public void tagOpen( HTML.Tag tag ) {
		super.tagOpen( tag.toString() );
	}
	
	public void tagEnd( HTML.Tag tag ) {
		super.tagEnd( tag.toString() );
	}
	
}
