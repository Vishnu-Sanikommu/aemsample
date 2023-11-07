package com.aemsample.core.services.impl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;

import com.aemsample.core.services.MultiService;

@Component(service = MultiService.class, immediate = true, name = "serviceA")
@ServiceRanking(1001)
public class MultiServiceAImpl implements MultiService{

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "MultiServiceAImpl";
    }

}
