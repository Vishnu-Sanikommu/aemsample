package com.aemsample.core.models.impl;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.aemsample.core.models.OSGiConfigDemo;
import com.aemsample.core.services.OSGiConfig;
import com.aemsample.core.services.OSGiConfigModule;
import com.aemsample.core.services.OSGiFactoryConfig;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = OSGiConfigDemo.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
        )
public class OSGiConfigDemoImpl implements OSGiConfigDemo{

    @OSGiService
    OSGiConfig oSGiConfig;
    
    @OSGiService
    OSGiConfigModule oSGiConfigModule;
    
    @OSGiService
    OSGiFactoryConfig oSGiFactoryConfig;
    
    @Override
    public String getServiceName() {
        return oSGiConfig.getServiceName();
    }

    @Override
    public int getServiceCount() {
        // TODO Auto-generated method stub
        return oSGiConfig.getServiceCount();
    }

    @Override
    public boolean isLiveData() {
        // TODO Auto-generated method stub
        return oSGiConfig.isLiveData();
    }

    @Override
    public String[] getCountries() {
        // TODO Auto-generated method stub
        return oSGiConfig.getCountries();
    }

    @Override
    public String getRunModes() {
        // TODO Auto-generated method stub
        return oSGiConfig.getRunModes();
    }

    @Override
    public int getServiceId() {
        // TODO Auto-generated method stub
        return oSGiConfigModule.getServiceId();
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return oSGiConfigModule.getserviceName();
    }

    @Override
    public String getServiceUrl() {
        // TODO Auto-generated method stub
        return oSGiConfigModule.getServiceURL();
    }

    @Override
    public List<OSGiFactoryConfig> getAllConfigs() {
        // TODO Auto-generated method stub
        return oSGiFactoryConfig.getAllconfigs();
    }

}
