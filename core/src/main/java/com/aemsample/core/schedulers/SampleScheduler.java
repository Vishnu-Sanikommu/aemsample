package com.aemsample.core.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aemsample.core.config.SchedulerConfig;


@Component(immediate = true, service = Runnable.class)
@Designate(ocd = SchedulerConfig.class)
public class SampleScheduler implements Runnable{
    
    private static final Logger LOG = LoggerFactory.getLogger(SampleScheduler.class);
    
    private int schedulerId;
    
    @Reference
    private Scheduler scheduler;
    
    @Activate
    protected void activate(SchedulerConfig config) {
        
        schedulerId = config.schedulerName().hashCode();
        addScheduler(config);
        
    }

    @Deactivate
    protected void deactivate(SchedulerConfig config) {
        removeScheduler();
    }
    
    protected void addScheduler(SchedulerConfig config) {
        ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
        scheduleOptions.name(String.valueOf(schedulerId));
        scheduler.schedule(this, scheduleOptions);
        
        ScheduleOptions scheduleOptionsNow = scheduler.NOW();
        scheduler.schedule(this, scheduleOptionsNow);
        LOG.info("======= SAMPLE SCHEDULER STARTED ===========");

    }
    
    protected void removeScheduler() {
        scheduler.unschedule(String.valueOf(schedulerId));
    }
    
    @Override
    public void run() {
        LOG.info("======= SAMPLE SCHEDULER RUN METHOD ===========");
    }

}
