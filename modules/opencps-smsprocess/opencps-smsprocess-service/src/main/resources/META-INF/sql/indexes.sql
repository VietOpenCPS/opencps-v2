create index IX_F18681F0 on opencps_smsgatewaylog (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_F9A6C472 on opencps_smsgatewaylog (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_BE613452 on opencps_smsqueue (moid[$COLUMN_LENGTH:255$]);
create index IX_C3B2C1D5 on opencps_smsqueue (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_5D3BB997 on opencps_smsqueue (uuid_[$COLUMN_LENGTH:75$], groupId);