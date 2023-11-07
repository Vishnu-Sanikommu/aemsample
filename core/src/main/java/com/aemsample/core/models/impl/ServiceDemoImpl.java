package com.aemsample.core.models.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.service.component.annotations.Reference;

import com.adobe.reef.siren.Action.Method;
import com.aemsample.core.models.ServiceDemo;
import com.aemsample.core.services.DemoServiceA;
import com.aemsample.core.services.DemoServiceB;
import com.aemsample.core.services.MultiService;
import com.day.cq.wcm.api.Page;

@Model(adaptables = SlingHttpServletRequest.class, adapters = ServiceDemo.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ServiceDemoImpl implements ServiceDemo {

    @ValueMapValue
    public String pagepath;

    @OSGiService
    DemoServiceA demoServiceA;

    @OSGiService
    DemoServiceB demoServiceB;

    @Override
    public Iterator<Page> getPagesList() {
        return demoServiceA.getPages();
    }

    @Override
    public List<String> getPagesTitleList() {
        return demoServiceB.getPages();
    }

//    Method - 1
    @OSGiService(filter = "(component.name=serviceA)")
    MultiService multiService;

//    Method - 2
    @OSGiService(filter = "(component.name=com.aemsample.core.services.impl.MultiServiceBImpl)")
    MultiService multiServiceB;

    @Override
    public String getServiceName() {
        return multiService.getName();
    }

    @Override
    public String getServiceNameB() {
        return multiServiceB.getName();
    }

    @Override
    public String getNameWithReference() {
        return demoServiceB.getNameWithReference();
    }

    @Override
    public String getPathLink() {
        // TODO Auto-generated method stub
        return pagepath;
    }

//    @PostConstruct
//    public void init() {
//        demoServiceA.setPathString(pagepath);
//    }

}
