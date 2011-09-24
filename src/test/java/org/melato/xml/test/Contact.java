/* $Header: c:\\cvsroot/dev/java/aa/xml/test/Contact.java,v 1.1 2007/12/01 22:53:40 athana Exp $
 * Copyright (c) Alex Athanasopoulos 2007
 */
package org.melato.xml.test;

/** Simple test class representing a contact object.  */
public class Contact {
	private String name;
	private String phone;
	private String email;
	private String description;
	
	public Contact(String name, String phone, String email, String description) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getDescription() {
		return description;
	}
	
	public String toString() {
		return name + ", " + phone + ", " + email + ": " + description;
	}
}
