package com.aemsample.core.models;

import java.util.List;

public interface Author{
	String getFirstName();
	String getLastName();
	String getText();
	String getPageTitle();
	String getLastModifiedBy();
	String getHomePage();
	String getRequestAttribute();
	
	List<String> getBooks();



}

