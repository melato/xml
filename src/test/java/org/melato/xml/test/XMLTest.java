package org.melato.xml.test;

/* $Header: c:\\cvsroot/dev/java/aa/xml/test/XMLTest.java,v 1.1 2007/12/01 22:57:03 athana Exp $
 * Copyright (c) Alex Athanasopoulos 2007
 */
import java.io.InputStream;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class XMLTest {
	public @Test void testContacts() throws Exception {
		InputStream stream = getClass().getResourceAsStream( "contacts.xml" );
		try {
			List<Contact> contacts = ContactManager.readXML( stream );
			Assert.assertEquals( 1, contacts.size() );
			Contact c = contacts.get(0);
			Assert.assertEquals( "Alex Athanasopoulos", c.getName() );
			Assert.assertEquals( "206-291-8105", c.getPhone() );
			Assert.assertEquals( "aathanasopoulos@yahoo.com", c.getEmail() );
			Assert.assertEquals( "Example Contact", c.getDescription() );
		} finally {
			stream.close();
		}
	}
}
