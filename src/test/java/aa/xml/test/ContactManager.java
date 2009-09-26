/* $Header: c:\\cvsroot/dev/java/aa/xml/test/ContactManager.java,v 1.1 2007/12/01 22:57:03 athana Exp $
 * Copyright (c) Alex Athanasopoulos 2007
 */
package aa.xml.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;

import aa.xml.XMLDelegator;
import aa.xml.XMLMappingHandler;
import aa.xml.XMLStringHandler;
import aa.xml.XMLTag;

/** Performs various operations for managing contacts. */
public class ContactManager {
	/** The root tag for contacts. */
	static String CONTACTS = "contacts"; 

	/** The contact element tag name. */
	static String CONTACT = "contact"; 
	
	/** The name attribute. */
	static String NAME = "name"; 
	
	/** The phone attribute. */
	static String PHONE = "phone"; 
	
	/** The email attribute. */
	static String EMAIL = "email"; 
	
	/** The description tag. */
	static String DESCRIPTION = "description"; 
	
	/** Read contacts from an xml file.
	 * @param file  The xml file to read.
	 * @return A list of contacts.
	 */
	
	static class ContactHandler extends XMLMappingHandler {
		List<Contact> contacts = new ArrayList<Contact>();
		String name;
		String phone;
		String email;
		
		XMLStringHandler descriptionHandler = new XMLStringHandler();
		
		ContactHandler() {
			setHandler( DESCRIPTION, descriptionHandler );
		}
		public void start(XMLTag tag) {
			name = tag.getRequiredAttribute( NAME );
			phone = tag.getRequiredAttribute( PHONE );
			email = tag.getRequiredAttribute( EMAIL );
		}
		
		public void end() {
			contacts.add( new Contact( name, phone, email, descriptionHandler.getText()));
		}
		
		public List<Contact> getContacts() {
			return contacts;
		}
	}
	
	public static List<Contact> readXML( InputStream input ) throws IOException, SAXException {
		XMLMappingHandler rootHandler = new XMLMappingHandler();
		XMLMappingHandler contactsHandler = new XMLMappingHandler();
		ContactHandler contactHandler = new ContactHandler();
		rootHandler.setHandler( CONTACTS, contactsHandler );
		contactsHandler.setHandler( CONTACT, contactHandler );
		XMLDelegator.parse( rootHandler, input );
		return contactHandler.getContacts();
	}
	
	public static List<Contact> readXML( File file ) throws IOException, SAXException {
		InputStream in = new FileInputStream( file );
		try {
			in = new BufferedInputStream( in );
			return readXML( in );
		} finally {
			in.close();
		}
	}
	
	public static void main(String[] args) throws Exception {
		if ( args.length != 1 )
			throw new RuntimeException( "Usage: <filename>");
		List<Contact> contacts = ContactManager.readXML( new File( args[0] ));
		for( Contact c: contacts ) {
			System.out.println( c );
		}
	}	
	
}
