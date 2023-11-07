package com.aemsample.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

import com.aemsample.core.services.OSGiConfig;

@Component(service = OSGiConfig.class, immediate = true)
@Designate(ocd = OSGiConfigImpl.ServiceConfig.class)
public class OSGiConfigImpl implements OSGiConfig{
    
    @ObjectClassDefinition(name = "AEM Sample - OSGi Configuraton",description = "OSGi Configuration Demo")
    public @interface ServiceConfig {
        
        @AttributeDefinition(
                name = "Service Name",
                description = "Enter service name",
                type = AttributeType.STRING
                )                
        public String serviceName() default "AEM sample service";
        
        @AttributeDefinition(
                name = "Service Count",
                description = "Add Service Count",
                type = AttributeType.INTEGER
                )
        public int getServiceCount() default 5;
        
        @AttributeDefinition(name = "Live Data",
                description = "Check this to get live data",
                type = AttributeType.BOOLEAN
                )
        public boolean isLiveData() default false;
        
        @AttributeDefinition(name = "Countries",
                description = "Add Countries to which your service needs to run",
                type = AttributeType.STRING
                )
        public String[] getCountries() default {"de","in"};
        
        @AttributeDefinition(name = "Run Modes",
                description = "Select the run modes",
                options = {
                        @Option(label = "Author", value = "author"),
                        @Option(label = "Publish", value = "publish"),
                        @Option(label = "Both", value = "both")
                },
                type = AttributeType.STRING
                )
        public String getRunModes() default "both";
        
    }
    
    private String serviceName;
    private int serviceCount;
    private boolean isLive;
    private String[] countries;
    private String runModes;
    
    @Activate
    protected void activate(ServiceConfig serviceConfig) {
        serviceName=serviceConfig.serviceName();
        serviceCount=serviceConfig.getServiceCount();
        isLive=serviceConfig.isLiveData();
        countries=serviceConfig.getCountries();
        runModes=serviceConfig.getRunModes();
    }
    
    

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public int getServiceCount() {
        return serviceCount;
    }

    @Override
    public boolean isLiveData() {
        return isLive;
    }

    @Override
    public String[] getCountries() {
        return countries;
    }

    @Override
    public String getRunModes() {
        return runModes;
    }

}
