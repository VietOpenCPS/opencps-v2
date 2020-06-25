create index IX_AECA614E on opencps_apdungDVC (maTTHC[$COLUMN_LENGTH:75$], maCQTH[$COLUMN_LENGTH:75$], mucdo);
create index IX_D06786EF on opencps_apdungDVC (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_EAB2FD31 on opencps_apdungDVC (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_2F9FB400 on opencps_philephi (groupId, serviceConfigMappingId);
create index IX_F8DD9B84 on opencps_philephi (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D5F3306 on opencps_philephi (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_296A42D on opencps_serviceconfigmapping (groupId, maTTHC[$COLUMN_LENGTH:75$]);
create index IX_6399334 on opencps_serviceconfigmapping (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_57B556B6 on opencps_serviceconfigmapping (uuid_[$COLUMN_LENGTH:75$], groupId);