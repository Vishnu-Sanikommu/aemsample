package com.aemsample.core.models;

import java.util.Iterator;
import java.util.List;

import com.day.cq.wcm.api.Page;

public interface ServiceDemo {

    public Iterator<Page> getPagesList();
    public List<String> getPagesTitleList();
    public String getPathLink();
    
    public String getServiceName();
    public String getServiceNameB();
    public String getNameWithReference();

}
