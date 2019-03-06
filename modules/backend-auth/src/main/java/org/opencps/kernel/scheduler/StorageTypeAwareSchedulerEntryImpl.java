package org.opencps.kernel.scheduler;

import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.StorageTypeAware;
import com.liferay.portal.kernel.scheduler.Trigger;

public class StorageTypeAwareSchedulerEntryImpl extends SchedulerEntryImpl implements SchedulerEntry, StorageTypeAware {

	  /**
	   * StorageTypeAwareSchedulerEntryImpl: Constructor for the class.
	   * @param schedulerEntry
	   */
	  public StorageTypeAwareSchedulerEntryImpl(final SchedulerEntryImpl schedulerEntry) {
	    super();

	    _schedulerEntry = schedulerEntry;

	    // use the same default that Liferay uses.
	    _storageType = StorageType.MEMORY_CLUSTERED;
	  }

	  /**
	   * StorageTypeAwareSchedulerEntryImpl: Constructor for the class.
	   * @param schedulerEntry
	   * @param storageType
	   */
	  public StorageTypeAwareSchedulerEntryImpl(final SchedulerEntryImpl schedulerEntry, final StorageType storageType) {
	    super();

	    _schedulerEntry = schedulerEntry;
	    _storageType = storageType;
	  }

	  @Override
	  public String getDescription() {
	    return _schedulerEntry.getDescription();
	  }

	  @Override
	  public String getEventListenerClass() {
	    return _schedulerEntry.getEventListenerClass();
	  }

	  @Override
	  public StorageType getStorageType() {
	    return _storageType;
	  }

	  @Override
	  public Trigger getTrigger() {
	    return _schedulerEntry.getTrigger();
	  }

	  public void setDescription(final String description) {
	    _schedulerEntry.setDescription(description);
	  }
	  public void setTrigger(final Trigger trigger) {
	    _schedulerEntry.setTrigger(trigger);
	  }
	  public void setEventListenerClass(final String eventListenerClass) {
	    _schedulerEntry.setEventListenerClass(eventListenerClass);
	  }
	  
	  private SchedulerEntryImpl _schedulerEntry;
	  private StorageType _storageType;
	}