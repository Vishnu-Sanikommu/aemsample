package com.aemsample.core.models;

import java.util.List;

import com.aemsample.core.services.OSGiFactoryConfig;

public interface OSGiConfigDemo {
    
    public String getServiceName();
    public int getServiceCount();
    public boolean isLiveData();
    public String[] getCountries();
    public String getRunModes();
    
    
    public int getServiceId();
    public String getName();
    public String getServiceUrl();
    
    
    public List<OSGiFactoryConfig> getAllConfigs();

}
