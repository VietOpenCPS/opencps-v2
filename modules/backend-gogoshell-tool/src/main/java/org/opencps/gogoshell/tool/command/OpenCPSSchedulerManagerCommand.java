package org.opencps.gogoshell.tool.command;

import static org.fusesource.jansi.Ansi.ansi;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.felix.service.command.Descriptor;
import org.apache.felix.service.command.Parameter;
import org.opencps.gogoshell.tool.command.model.FiredTrigger;
import org.opencps.gogoshell.tool.command.util.Console;
import org.opencps.gogoshell.tool.command.util.QuartzUtils;
import org.osgi.service.component.annotations.Component;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

@Component(
	property = {
		"osgi.command.function=list",
		"osgi.command.function=info",
		"osgi.command.function=pause",
		"osgi.command.function=pauseAll",
		"osgi.command.function=resume",
		"osgi.command.function=resumeAll",
		"osgi.command.function=jobIsFired",
		"osgi.command.function=jobsIsFired",
		"osgi.command.function=listJobsInProgress",
		"osgi.command.scope=opencpsscheduler"
	},
	service = Object.class
)
@Descriptor("Lệnh gogoshell để quản lý các tác vụ chạy ngầm trong OpenCPS")
public class OpenCPSSchedulerManagerCommand {
	/**
	 * In danh sách tất cả các tác vụ đang chạy của OpenCPS theo trạng thái (mặc định ALL - tất cả)
	 *
	 * @param triggerState các trạng thái
	 *                     COMPLETE,NORMAL,EXPIRED,PAUSED,UNSCHEDULED
	 * @throws PortalException nếu có lỗi.
	 */
	@Descriptor("Liệt kê danh sách các tác vụ chạy ngầm của OpenCPS theo trạng thái (mặc định ALL)")
	public void list(
		@Descriptor("Lọc theo trạng thái tác vụ {trạng thái: COMPLETE,NORMAL,EXPIRED,PAUSED,UNSCHEDULED}")
		@Parameter(names = {
			"--status", "-s"
		}, absentValue = "ALL") String triggerState)
		throws PortalException {

		Console.println(
			ansi().eraseScreen().render(
				"@|green Danh sách tác vụ theo trạng thái:|@ @|red " +
				triggerState + " |@"));
		Console.println(getJobsListTableHeader());
		Console.println(getJobsListTableRows(triggerState));
	}

	/**
	 * In chi tiết một tác vụ.
	 *
	 * @param jobName     Tên tác vụ
	 * @param groupName   Tên nhóm của tác vụ
	 * @param storageType loại lưu trữ dữ liệu của tác vụ
	 *                    MEMORY, MEMORY_CLUSTERED, PERSISTED
	 * @throws PortalException
	 */
	@Descriptor("Xem chi tiết tác vụ")
	public void info(
		@Descriptor("Tên tác vụ") String jobName,
		@Descriptor("Tên nhóm tác vụ") String groupName,
		@Descriptor("Loại lưu trữ {MEMORY, MEMORY_CLUSTERED, PERSISTED}") String storageType)
		throws PortalException {

		SchedulerResponse schedulerResponse =
			SchedulerEngineHelperUtil.getScheduledJob(jobName, groupName,
				StorageType.valueOf(storageType));

		if (Validator.isNull(schedulerResponse)) {
			throw new PortalException("Job not found with the name " + jobName);
		}

		SimpleDateFormat df = new SimpleDateFormat(DateUtil.ISO_8601_PATTERN);
		AsciiTable at = new AsciiTable();

		at.setPadding(5);
		at.addRule();
		at.addRow(_COLUMN_JOB_NAME, schedulerResponse.getJobName());
		at.addRule();
		at.addRow(_COLUMN_GROUP_NAME, schedulerResponse.getGroupName());
		at.addRule();
		at.addRow(
			_COLUMN_STATE,
			SchedulerEngineHelperUtil.getJobState(schedulerResponse).name());
		at.addRule();

		if (Validator.isNotNull(
			schedulerResponse.getTrigger().getStartDate())) {
			at.addRow(
				_COLUMN_START_TIME,
				df.format(schedulerResponse.getTrigger().getStartDate()));
			at.addRule();
		}
		else {
			at.addRow(_COLUMN_START_TIME, StringPool.DASH);
			at.addRule();
		}

		if (Validator.isNotNull(
			SchedulerEngineHelperUtil.getPreviousFireTime(
				schedulerResponse))) {
			at.addRow(
				_COLUMN_PREVIOUS_FIRE_TIME,
				df.format(
					SchedulerEngineHelperUtil.getPreviousFireTime(
						schedulerResponse)));
			at.addRule();
		}
		else {
			at.addRow(_COLUMN_PREVIOUS_FIRE_TIME, StringPool.DASH);
			at.addRule();
		}

		if (Validator.isNotNull(
			SchedulerEngineHelperUtil.getNextFireTime(schedulerResponse))) {
			at.addRow(
				_COLUMN_NEXT_FIRE_TIME,
				df.format(
					SchedulerEngineHelperUtil.getNextFireTime(
						schedulerResponse)));
			at.addRule();
		}
		else {
			at.addRow(_COLUMN_NEXT_FIRE_TIME, StringPool.DASH);
			at.addRule();
		}

		at.addRow(
			"Cron Expression",
			SchedulerEngineHelperUtil.getCronText(
				Calendar.getInstance(),
				false));
		at.addRule();
		at.addRow(
			_COLUMN_DESTINATION_NAME, schedulerResponse.getDestinationName());
		at.addRule();
		at.addRow(_COLUMN_STORAGE_TYPE, schedulerResponse.getStorageType());
		at.addRule();

		if (Validator.isNotNull(SchedulerEngineHelperUtil.getJobExceptions(
			jobName, groupName, StorageType.valueOf(storageType)))) {
			at.addRow(
				"Job Exceptions", SchedulerEngineHelperUtil.getJobExceptions(
					jobName, groupName, StorageType.valueOf(storageType)));
			at.addRule();
		}
		else {
			at.addRow("Job Exceptions", StringPool.DASH);
			at.addRule();
		}

		Console.println(
			ansi().eraseScreen().render(
				"@|green Detail of the job:|@ @|red " + jobName + " |@"));
		Console.println(at.render(160));
	}

	/**
	 * Tạm dừng tác vụ có tên, tên nhóm và loại lưu trữ
	 *
	 * @param jobName     Tên tác vụ
	 * @param groupName   Tên nhóm tác vụ
	 * @param storageType Loại lưu trữ dữ liệu
	 *                    MEMORY, MEMORY_CLUSTERED, PERSISTED
	 * @throws PortalException In the case of errors.
	 */
	@Descriptor("Tạm dừng tác vụ theo tên, tên nhóm và loại lưu trữ")
	public void pause(
		@Descriptor("Tên tác vụ") String jobName,
		@Descriptor("Tên nhóm tác vụ") String groupName,
		@Descriptor("Loại dữ liệu tác vụ {MEMORY, MEMORY_CLUSTERED, PERSISTED}") String storageType)
		throws PortalException {

		SchedulerEngineHelperUtil.pause(
			jobName, groupName, StorageType.valueOf(storageType));
	}

	/**
	 * Tạm dừng toàn bộ tác vụ
	 *
	 * @throws PortalException nếu có lỗi.
	 */
	@Descriptor("Tạm dừng toàn bộ các tác vụ")
	public void pauseAll()
		throws PortalException {

		List<SchedulerResponse> schedulerResponses =
				SchedulerEngineHelperUtil.getScheduledJobs();
		List<SchedulerResponse> schedulerResponsesFiltered;

		schedulerResponsesFiltered = schedulerResponses.stream().filter(
			schedulerResponse -> schedulerResponse.getJobName().startsWith(_OPENCPS_JOB_PREFIX)).collect(
			Collectors.toList());
			
		schedulerResponsesFiltered.forEach(schedulerResponse -> {
			try {
				SchedulerEngineHelperUtil.pause(
						schedulerResponse.getJobName(), schedulerResponse.getGroupName(), schedulerResponse.getStorageType());
			} catch (SchedulerException e) {
				
			}
		});		
	}

	/**
	 * Khởi động lại toàn bộ tác vụ
	 *
	 * @throws PortalException nếu có lỗi.
	 */
	@Descriptor("Khởi động lại toàn bộ các tác vụ")
	public void resumeAll()
		throws PortalException {

		List<SchedulerResponse> schedulerResponses =
				SchedulerEngineHelperUtil.getScheduledJobs();
		List<SchedulerResponse> schedulerResponsesFiltered;

		schedulerResponsesFiltered = schedulerResponses.stream().filter(
			schedulerResponse -> schedulerResponse.getJobName().startsWith(_OPENCPS_JOB_PREFIX)).collect(
			Collectors.toList());
			
		schedulerResponsesFiltered.forEach(schedulerResponse -> {
			try {
				SchedulerEngineHelperUtil.resume(
						schedulerResponse.getJobName(), schedulerResponse.getGroupName(), schedulerResponse.getStorageType());
			} catch (SchedulerException e) {
				
			}
		});		
	}
	
	/**
	 * Tạm dừng các tác vụ theo tên và tên nhóm
	 *
	 * @param groupName   Tên nhóm
	 * @param storageType Loại dữ liệu tác vụ
	 *                    MEMORY, MEMORY_CLUSTERED, PERSISTED
	 * @throws PortalException In the case of errors.
	 */
	@Descriptor("Tạm dừng các tác vụ theo tên nhóm và loại dữ liệu")
	public void pause(
		@Descriptor("Tên nhóm") String groupName,
		@Descriptor("Loại dữ liệu tác vụ {MEMORY, MEMORY_CLUSTERED, PERSISTED}") String storageType)
		throws PortalException {

		SchedulerEngineHelperUtil.pause(
			groupName, StorageType.valueOf(storageType));
	}


	/**
	 * Khởi động lại theo tên, tên nhóm và loại dữ liệu
	 *
	 * @param jobName     Tên tác vụ
	 * @param groupName   Tên nhóm
	 * @param storageType Loại dữ liệu tác vụ
	 *                    MEMORY, MEMORY_CLUSTERED, PERSISTED
	 * @throws PortalException In the case of errors.
	 */
	@Descriptor("Khởi động lại tác vụ theo tên, tên nhóm và loại dữ liệu")
	public void resume(
		@Descriptor("Tên tác vụ") String jobName,
		@Descriptor("Tên nhóm") String groupName,
		@Descriptor("Loại dữ liệu {MEMORY, MEMORY_CLUSTERED, PERSISTED}") String storageType)
		throws PortalException {

		SchedulerEngineHelperUtil.resume(
			jobName, groupName, StorageType.valueOf(storageType));
	}

	/**
	 * Khởi động lại các tác vụ theo tên nhóm, loại lưu trữ dữ liệu
	 *
	 * @param groupName   Tên nhóm tác vụ
	 * @param storageType Loại dữ liệu tác vụ
	 *                    MEMORY, MEMORY_CLUSTERED, PERSISTED
	 * @throws PortalException In the case of errors.
	 */
	@Descriptor("Khởi động lại tên nhóm, loại dữ liệu tác vụ")
	public void resume(
		@Descriptor("Tên nhóm") String groupName,
		@Descriptor("Loại dữ liệu tác vụ {MEMORY, MEMORY_CLUSTERED, PERSISTED}") String storageType)
		throws PortalException {

		SchedulerEngineHelperUtil.resume(
			groupName, StorageType.valueOf(storageType));
	}

	/**
	 * Đếm số tác vụ trong nhóm đang chạy
	 *
	 * @param groupName Tên nhóm tác vụ
	 * @throws PortalException Lỗi trả về.
	 */
	@Descriptor("Trả về số lượng tác vụ trong nhóm đang chạy. CHỈ ĐỐI VỚI CÁC TÁC VỤ QUARTZ PERSISTED!!!")
	public int jobsIsFired(
		@Descriptor("Tên nhóm") String groupName)
		throws PortalException {

		return QuartzUtils.getFiredJobsCount(groupName);
	}

	/**
	 * Trả về đúng nếu tác vụ đang chạy hoặc sai nếu không phải
	 *
	 * @param jobName Tên tác vụ
	 * @throws PortalException Lỗi trả về.
	 */
	@Descriptor("Trả về đúng nếu tác vụ đang chạy hoặc sai nếu không phải. CHỈ ĐỐI VỚI CÁC TÁC VỤ QUARTZ PERSISTED!!!")
	public boolean jobIsFired(
		@Descriptor("Tên tác vụ") String jobName)
		throws PortalException {

		return QuartzUtils.getFiredJobCount(jobName) > 0;
	}

	/**
	 * Danh sách các tác vụ trong nhóm đang chạy
	 *
	 * @param groupName Tên nhóm
	 * @throws PortalException Lỗi nếu có.
	 */
	@Descriptor("Danh sách các tác vụ trong nhóm đang chạy. CHỈ ĐỐI VỚI CÁC TÁC VỤ QUARTZ PERSISTED!!!")
	public void listJobsInProgress(
		@Descriptor("Tên nhóm") String groupName)
		throws PortalException {

		Console.println(
			ansi().eraseScreen().render(
				"@|green Danh sách các tác vụ đang chạy lọc theo tên nhóm:|@ @|red " +
				groupName + " |@"));
		Console.println(getJobsListInProgressTableHeader());
		Console.println(getJobsListInProgressTableRows(groupName));


	}

	/**
	 * Danh sách các tác vụ đang chạy phần đầu.
	 *
	 * @return String phần tên cột trong bảng
	 */
	private String getJobsListInProgressTableHeader() {
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow(
			_COLUMN_JOB_NAME, _COLUMN_GROUP_NAME, _COLUMN_INSTANCE_NAME,
			_COLUMN_FIRED_TIME,
			_COLUMN_STATE);
		at.addRule();

		return at.render(160);
	}

	/**
	 * Trả về tất cả tên cột trong bảng danh sách tác vụ đang chạy.
	 *
	 * @return String Dữ liệu tên cột trong bảng
	 */
	private String getJobsListTableHeader() {
		AsciiTable at = new AsciiTable();
		at.addRule();
		at.addRow(
			_COLUMN_JOB_NAME, _COLUMN_GROUP_NAME, _COLUMN_STATE,
			_COLUMN_START_TIME,
			_COLUMN_PREVIOUS_FIRE_TIME, _COLUMN_NEXT_FIRE_TIME,
			_COLUMN_STORAGE_TYPE);
		at.addRule();

		return at.render(160);
	}

	/**
	 * Trả về danh sách tác vụ đang chạy trong bảng.
	 *
	 * @param groupName Tên nhóm
	 * @return String Dữ liệu dòng
	 */
	private String getJobsListInProgressTableRows(String groupName) {
		List<FiredTrigger> firedTriggerList =
			QuartzUtils.getFiredTrigger(groupName);

		AsciiTable at = new AsciiTable();
		SimpleDateFormat df = new SimpleDateFormat(DateUtil.ISO_8601_PATTERN);

		firedTriggerList.forEach(firedTrigger -> {
			Collection<String> columnsValue = new ArrayList<>();

			columnsValue.add(firedTrigger.getTriggerName());
			columnsValue.add(firedTrigger.getTriggerGroup());
			columnsValue.add(firedTrigger.getInstanceName());
			columnsValue.add(
				df.format(firedTrigger.getFiredTime()
				));
			columnsValue.add(firedTrigger.getState());

			at.addRow(columnsValue);
			at.addRule();
		});

		if (firedTriggerList.isEmpty()) {
			at.addRow("Không tác vụ nào tìm thấy");
			at.setTextAlignment(TextAlignment.CENTER);
			at.addRule();
		}

		return at.render(160);
	}

	/**
	 * Trả về danh sách các tác vụ dưới dạng bảng.
	 *
	 * @param trạng thái
	 * @return chuỗi được format theo định dạng
	 * @throws SchedulerException đối tượng lỗi trả về nếu có lỗi
	 */
	private String getJobsListTableRows(String status)
		throws SchedulerException {
		List<SchedulerResponse> schedulerResponses =
			SchedulerEngineHelperUtil.getScheduledJobs();
		List<SchedulerResponse> schedulerResponsesFiltered;

		AsciiTable at = new AsciiTable();
		SimpleDateFormat df = new SimpleDateFormat(DateUtil.ISO_8601_PATTERN);

		if (!"ALL".equals(status)) {

			schedulerResponsesFiltered = schedulerResponses.stream().filter(
				schedulerResponse -> status.equals(
					SchedulerEngineHelperUtil.getJobState(
						schedulerResponse).name()) && schedulerResponse.getJobName().startsWith(_OPENCPS_JOB_PREFIX)).collect(
				Collectors.toList());
		}
		else {
			schedulerResponsesFiltered = schedulerResponses.stream().filter(
					schedulerResponse -> schedulerResponse.getJobName().startsWith(_OPENCPS_JOB_PREFIX)).collect(
					Collectors.toList());
		}

		schedulerResponsesFiltered.forEach(schedulerResponse -> {
			Collection<String> columnsValue = new ArrayList<>();

			columnsValue.add(schedulerResponse.getJobName());
			columnsValue.add(schedulerResponse.getGroupName());
			columnsValue.add(
				SchedulerEngineHelperUtil.getJobState(
					schedulerResponse).name());

			if (Validator.isNotNull(
				schedulerResponse.getTrigger().getStartDate())) {
				columnsValue.add(
					df.format(schedulerResponse.getTrigger().getStartDate()));
			}
			else {
				columnsValue.add(StringPool.DASH);
			}

			if (Validator.isNotNull(
				SchedulerEngineHelperUtil.getPreviousFireTime(
					schedulerResponse))) {
				columnsValue.add(
					df.format(
						SchedulerEngineHelperUtil.getPreviousFireTime(
							schedulerResponse)));
			}
			else {
				columnsValue.add(StringPool.DASH);
			}

			if (Validator.isNotNull(
				SchedulerEngineHelperUtil.getNextFireTime(schedulerResponse))) {
				columnsValue.add(
					df.format(
						SchedulerEngineHelperUtil.getNextFireTime(
							schedulerResponse)));
			}
			else {
				columnsValue.add(StringPool.DASH);
			}

			columnsValue.add(schedulerResponse.getStorageType().name());

			at.addRow(columnsValue);
			at.addRule();
		});

		if (schedulerResponsesFiltered.isEmpty()) {
			at.addRow("No Jobs found");
			at.setTextAlignment(TextAlignment.CENTER);
			at.addRule();
		}

		return at.render(160);
	}

	private static final String _COLUMN_DESTINATION_NAME = "Tên đích";

	private static final String _COLUMN_JOB_NAME = "Tên tác vụ";

	private static final String _COLUMN_FIRED_TIME = "Thời gian chạy";

	private static final String _COLUMN_GROUP_NAME = "Tên nhóm";

	private static final String _COLUMN_INSTANCE_NAME = "Tên thể hiện";

	private static final String _COLUMN_STATE = "Trạng thái";

	private static final String _COLUMN_START_TIME = "Thời gian bắt đầu";

	private static final String _COLUMN_PREVIOUS_FIRE_TIME =
		"Lần chạy trước";

	private static final String _COLUMN_NEXT_FIRE_TIME = "Lần chạy kế";

	private static final String _COLUMN_STORAGE_TYPE = "Loại lưu trữ";	
	private static final String _OPENCPS_JOB_PREFIX = "org.opencps";
}
