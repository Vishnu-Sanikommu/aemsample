package com.aemsample.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.aemsample.core.config.SampleOSGiConfig;
import com.aemsample.core.services.OSGiConfigModule;

@Component(service = OSGiConfigModule.class, immediate = true)
@Designate(ocd = SampleOSGiConfig.class)
public class OSGiConfigModuleImpl implements OSGiConfigModule{

    private int serviceId;
    private String serviceName;
    private String serviceUrl;
    
    @Activate
    protected void activate(SampleOSGiConfig sampleOSGiConfig) {
        serviceId=sampleOSGiConfig.serviceID();
        serviceName=sampleOSGiConfig.serviceName();
        serviceUrl=sampleOSGiConfig.serviceURL();
    }
    
    @Override
    public int getServiceId() {
        // TODO Auto-generated method stub
        return serviceId;
    }

    @Override
    public String getserviceName() {
        // TODO Auto-generated method stub
        return serviceName;
    }

    @Override
    public String getServiceURL() {
        // TODO Auto-generated method stub
        return serviceUrl;
    }

}
