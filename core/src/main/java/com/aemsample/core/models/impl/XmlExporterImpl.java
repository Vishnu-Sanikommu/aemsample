package com.aemsample.core.models.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aemsample.core.models.XmlExporter;

@Model(adaptables = SlingHttpServletRequest.class,
		adapters = XmlExporter.class,
		resourceType = XmlExporterImpl.RESOURCE_TYPE,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
		)
@Exporter(name = "XMLExporter", extensions = "xml")
@XmlRootElement(name = "xml-exporter")

public class XmlExporterImpl implements XmlExporter{

	public final static Logger LOG = LoggerFactory.getLogger(XmlExporterImpl.class);
	static final String RESOURCE_TYPE = "aemsample/components/content/xmlexporter";
	
	@Inject
	Resource resource;
	
	@ValueMapValue
	String xmltitle;
	
	@ValueMapValue
	String xmldescription;
	
	@ValueMapValue
	Calendar xmldate;
	
	@ValueMapValue
	List<String> books;
	
	@Override
	@XmlElement(name = "author-title")
	public String getTitle() {
		return xmltitle;
	}
	@Override
	@XmlElement
	public String getDescription() {
		return xmldescription;
	}
	@Override
	@XmlElement
	public Calendar getDate() {
		return xmldate;
	}
	@Override
	@XmlElementWrapper(name = "books")
	@XmlElement(name="book")
	public List<String> getBooks() {
		if (books != null) {
            return new ArrayList<String>(books);
        } else {
            return Collections.emptyList();
        }
	}
	@Override
	@XmlElement(name = "author-name")
	public String getAuthorName() {
		return "AEM Sample";
	}
}
