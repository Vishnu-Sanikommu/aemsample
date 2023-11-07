package com.aemsample.core.services;

import java.util.List;

public interface OSGiFactoryConfig {

    public int getConfigId();
    public String getServiceName();
    public String getServiceUrl();
    public List<OSGiFactoryConfig> getAllconfigs();

    // public OSGiFactoryConfig get(int configID);

}
