package com.aemsample.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aemsample.core.config.SampleOSGiFactoryConfig;
import com.aemsample.core.services.OSGiFactoryConfig;

@Component(service = OSGiFactoryConfig.class, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = SampleOSGiFactoryConfig.class, factory = true)
public class OSGiFactoryConfigImpl implements OSGiFactoryConfig{
    
    private static final Logger LOG = LoggerFactory.getLogger(OSGiFactoryConfigImpl.class);
    
    private int configId;
    private String serviceName;
    private String serviceUrl;
    private List<OSGiFactoryConfig> configsList;
    
    @Activate
    @Modified
    public void activate(final SampleOSGiFactoryConfig config) {
        configId = config.configID();
        serviceName =config.serviceName();
        serviceUrl =config.serviceURL();
    }
    
    @Reference(service = OSGiFactoryConfig.class, cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    public void bindOSGiFactoryConfig(final OSGiFactoryConfig config) {
        if(configsList == null) {
            configsList = new ArrayList<>();
        }
        configsList.add(config);
    }
    
    public void unbindOSGiFactoryConfig(final OSGiFactoryConfig config) {
        configsList.remove(config);
    }

    @Override
    public int getConfigId() {
        return configId;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public String getServiceUrl() {
        return serviceUrl;
    }

    @Override
    public List<OSGiFactoryConfig> getAllconfigs() {
        return configsList;
    }

}
