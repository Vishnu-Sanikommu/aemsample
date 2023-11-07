package com.aemsample.core.services.impl;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aemsample.core.services.DemoServiceA;
import com.aemsample.core.utils.ResolverUtil;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = DemoServiceA.class, immediate = true)
public class DemoServiceAImpl implements DemoServiceA {

    private static final Logger LOG = LoggerFactory.getLogger(DemoServiceAImpl.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

//    public String pathString = null;
//    
//    public String getPathString() {
//        return pathString;
//    }
//    
//    public void setPathString(String x) {
//        pathString =x;
//    }

    @Activate
    public void activate(ComponentContext componentContext) {
        LOG.info("\n============Activate Method=========");
        LOG.info("\n " + componentContext.getBundleContext().getBundle().getBundleId() + " = "
                + componentContext.getBundleContext().getBundle().getSymbolicName());

    }

    @Deactivate
    public void deactivate() {
        LOG.info("\n============Deactivate Method=======");
    }

    @Modified
    public void modified() {
        LOG.info("\n============modified Method=======");
    }

    @Override
    public Iterator<Page> getPages() {

        try {
            ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Page page = pageManager.getPage("/content/aemsample/language-masters");
//            "/content/aemsample/language-masters"
            Iterator<Page> pages = page.listChildren();
            return pages;
        } catch (LoginException e) {
            LOG.info("\n Login Exception {} ", e.getMessage());

        }

        return null;
    }

}