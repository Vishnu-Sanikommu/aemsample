package com.aemsample.core.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aemsample.core.services.DemoServiceA;
import com.aemsample.core.services.DemoServiceB;
import com.aemsample.core.services.MultiService;
import com.day.cq.wcm.api.Page;

@Component(service = DemoServiceB.class,immediate = true)
public class DemoServiceBImpl implements DemoServiceB {
    private static final Logger LOG= LoggerFactory.getLogger(DemoServiceBImpl.class);

    @Reference
    DemoServiceA demoServiceA;
    
    @Reference(target = "(component.name=com.aemsample.core.services.impl.MultiServiceBImpl)")
    MultiService multiServiceReference;
    
    public String getNameWithReference() {
        return "Response From " + multiServiceReference.getName();
    }

    
    @Override
    public List<String> getPages() {
        List<String> listPages = new ArrayList<String>();

        try {
            Iterator<Page> pages=demoServiceA.getPages();
            while (pages.hasNext()) {
                listPages.add(pages.next().getTitle());
            }
            return listPages;
        } catch (Exception e) {
            LOG.info("\n  Exception {} ",e.getMessage());
        }
        return null;
    }


}
