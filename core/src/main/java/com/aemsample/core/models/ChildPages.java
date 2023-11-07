package com.aemsample.core.models;

import java.util.Iterator;
import com.day.cq.wcm.api.Page;

public interface ChildPages {
    public Iterator<Page> getPages();
    public String getPagePath();
}
