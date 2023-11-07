package com.aemsample.core.models;

import java.util.Calendar;
import java.util.List;

public interface XmlExporter {
	
	public String getTitle();
    public String getDescription();
    public Calendar getDate();
    public String getAuthorName();
    public List<String> getBooks();
}
