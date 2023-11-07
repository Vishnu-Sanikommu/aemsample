package com.aemsample.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Reference;

public class ResolverUtil {

    private ResolverUtil() {
        
    }
    
//    @Reference
//    ResourceResolverFactory resourceResolverFactory;
    
    public final static String AEM_SAMPLE_USER = "aemsampleserviceuser";
    
    public static ResourceResolver newResolver(ResourceResolverFactory resourceResolverFactory) throws LoginException {
        final Map<String, Object> usersMap = new HashMap<String, Object>();
        usersMap.put(resourceResolverFactory.SUBSERVICE, AEM_SAMPLE_USER);
        ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver(usersMap);
        return resolver;       
    }
    
}
