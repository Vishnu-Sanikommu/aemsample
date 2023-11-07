package com.aemsample.core.models.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aemsample.core.models.Author;
import com.day.cq.wcm.api.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@Model(adaptables = SlingHttpServletRequest.class,
		adapters = Author.class,
		resourceType = AuthorImpl.RESOURCE_TYPE,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

@Exporter(name = "jackson", extensions = "json", selector = "aemsample",
options = {
	@ExporterOption(name = "SerializationFeature.WRAP_ROOT_VALUE", value = "true"),
	@ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value = "true")
})
@JsonRootName("author-details")

public class AuthorImpl implements Author{
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthorImpl.class);
	static final String RESOURCE_TYPE = "aemsample/components/content/author";
	
	@SlingObject
	ResourceResolver resourceResolver;
	
	@Self
	SlingHttpServletRequest slingHttpServletRequest;
	
	@ScriptVariable
	Page currentPage;
	
	@RequestAttribute(name = "rAttribute")
	String reqAttribute;
	
	@Inject
	@Via("resource")
	String fname;
	
	@ValueMapValue
//	@Required
//	@Default(values = "GEEKS")
	String lname;
	
	@ValueMapValue
	@Required
	@Default(values = "GEEKS")
	String text;
	
	@ValueMapValue
	List<String> books;
	
	@Inject
    @Via("resource")
    @Named("jcr:lastModifiedBy")
    String modifiedBy;
	
	@ResourcePath(path = "/content/aemsample/us/en/home")
	@Via("resource")
	Resource resourcePage;
	
	@Override
	public String getFirstName() {
		return fname;
	}

	@Override
	public String getLastName() {
		return lname;
		
	}

	@Override
	public String getText() {
		
		return text;
	}
	
	@PostConstruct
	public String getName() {
		return fname+" "+lname;
		
	}

	@Override
	public List<String> getBooks() {
		if (books != null) {
            return new ArrayList<String>(books);
        } else {
            return Collections.emptyList();
        }
	}

	@Override
	public String getPageTitle() {
		return currentPage.getTitle();
	}

	@Override
	public String getLastModifiedBy() {
		return modifiedBy;
	}

	@Override
	public String getHomePage() {
		return resourcePage.getName();
	}
	// @JsonProperty(value = "Req-Attbr")
	@Override
	// @JsonIgnore
	public String getRequestAttribute() {
		return reqAttribute;
	}

	@JsonProperty(value = "Author-Name")
	public String authorName() {
		return "AEM SAMPLE";
	}
	
	@PostConstruct
	public void init() {
		LOG.info("It is log generator"+ fname + " "+ lname);
	}
	
	
}

