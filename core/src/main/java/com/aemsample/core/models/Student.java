package com.aemsample.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;

@Model(adaptables=Resource.class,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public interface Student {
	@Inject
	@Required
	@Default(values = "AEM")
	String getFirstName();
	@Inject
	String getLastName();
	@Inject
	@Default(values = "DESCRIBE")
	String getDescribe();
	
	
	
}
