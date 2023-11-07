package com.aemsample.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.jackrabbit.vault.util.JcrConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class)
@SlingServletResourceTypes(
        resourceTypes = "aemsample/components/page"
        )
public class SampleResourceTypeServlet extends SlingSafeMethodsServlet{
    
    @Override
    protected void doGet(final SlingHttpServletRequest req,final SlingHttpServletResponse resp) throws IOException {
        final Resource resource = req.getResource();
        resp.setContentType("text/plain");
        resp.getWriter().write("Page Title: "+ resource.getValueMap().get(JcrConstants.JCR_TITLE));
    }

}
