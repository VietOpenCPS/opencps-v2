create index IX_63B47ACA on opencps_notificationqueue (expireDate);
create index IX_C0F9E920 on opencps_notificationqueue (groupId, className[$COLUMN_LENGTH:512$], classPK[$COLUMN_LENGTH:75$], toEmail[$COLUMN_LENGTH:255$]);
create index IX_A4B70B87 on opencps_notificationqueue (groupId, notificationType[$COLUMN_LENGTH:255$], className[$COLUMN_LENGTH:512$], classPK[$COLUMN_LENGTH:75$], toEmail[$COLUMN_LENGTH:255$]);
create index IX_B4D26F83 on opencps_notificationqueue (notificationType[$COLUMN_LENGTH:255$], expireDate);

create index IX_E7D9C8D2 on opencps_notificationtemplate (emailSubject[$COLUMN_LENGTH:4096$]);
create index IX_DE33B5F5 on opencps_notificationtemplate (expireDuration);
create index IX_E0577FD5 on opencps_notificationtemplate (groupId, notificationType[$COLUMN_LENGTH:255$], interval_[$COLUMN_LENGTH:255$]);
create index IX_54F70B3C on opencps_notificationtemplate (interval_[$COLUMN_LENGTH:255$]);

create index IX_51758EA8 on opencps_serverconfig (groupId, protocol[$COLUMN_LENGTH:255$]);
create index IX_BA6B5400 on opencps_serverconfig (groupId, serverNo[$COLUMN_LENGTH:255$], protocol[$COLUMN_LENGTH:255$]);
create index IX_B089E85A on opencps_serverconfig (protocol[$COLUMN_LENGTH:255$]);
create index IX_14749CB0 on opencps_serverconfig (serverName[$COLUMN_LENGTH:4096$]);
create index IX_97108B26 on opencps_serverconfig (serverNo[$COLUMN_LENGTH:255$]);

create index IX_8EDCA035 on opencps_zalomap (groupId);
create index IX_41837937 on opencps_zalomap (telNo[$COLUMN_LENGTH:75$]);
create index IX_F16BEBAB on opencps_zalomap (uId[$COLUMN_LENGTH:75$]);
create index IX_E7CA6F80 on opencps_zalomap (zaloOAId[$COLUMN_LENGTH:255$]);