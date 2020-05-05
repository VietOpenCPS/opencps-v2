package org.opencps.gogoshell.tool.command.model;

import java.util.Date;

public class FiredTrigger {
	public String getEntryId() {
		return _entryId;
	}

	public Date getFiredTime() {
		return _firedTime;
	}

	public String getInstanceName() {
		return _instanceName;
	}

	public String getSchedulerName() {
		return _schedulerName;
	}

	public String getState() {
		return _state;
	}

	public String getTriggerGroup() {
		return _triggerGroup;
	}

	public String getTriggerName() {
		return _triggerName;
	}

	public void setEntryId(String entryId) {
		_entryId = entryId;
	}

	public void setFiredTime(Date firedTime) {
		_firedTime = firedTime;
	}

	public void setInstanceName(String instanceName) {
		_instanceName = instanceName;
	}

	public void setSchedulerName(String schedulerName) {
		_schedulerName = schedulerName;
	}

	public void setState(String state) {
		_state = state;
	}

	public void setTriggerGroup(String triggerGroup) {
		_triggerGroup = triggerGroup;
	}

	public void setTriggerName(String triggerName) {
		_triggerName = triggerName;
	}

	private String _entryId;
	private Date _firedTime;
	private String _instanceName;
	private String _schedulerName;
	private String _state;
	private String _triggerGroup;
	private String _triggerName;
}
