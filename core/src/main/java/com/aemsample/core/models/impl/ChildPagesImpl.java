package com.aemsample.core.models.impl;

import java.util.Iterator;
import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aemsample.core.models.ChildPages;
import com.aemsample.core.utils.ResolverUtil;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = ChildPages.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class ChildPagesImpl implements ChildPages{
    
    private final static Logger LOG = LoggerFactory.getLogger(ChildPagesImpl.class);
    
    @SlingObject
    private ResourceResolver resourceResolver;
    
    @ValueMapValue
//    @Default(values = "Page Path")
    public String pagePath;
    
    @Override
    public String getPagePath() {
        // TODO Auto-generated method stub
        return pagePath;
    }

    @Override
    public Iterator<Page> getPages() {
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Page page = pageManager.getPage(pagePath);
            Iterator<Page> pages = page.listChildren();
            return pages;
         
    }
    
    @PostConstruct
    public String getName() {
        return pagePath+" is page path";
        
    }

}
