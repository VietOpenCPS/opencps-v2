<#include "init.ftl">
<div class="row panel">
	
	<div class="form-group">
		
		<h3 class="detail-header MT5">
			
			<span> Chi tiết ngày làm việc</span>

		</h3>
		
	</div>

	<div class="panel-body">

		<form id="_worktimeDetail_form">

			<div class="form-group">
			
				<label for="worktimeDay">Ngày làm việc
				
					<span class="icon-asterisk text-warning"></span>
				
				</label>
				
				<input type="text" id="worktimeDay" name="worktimeDay" class="form-control"
					placeholder="Ngày nghỉ" required validationMessage="Nhập ngày nghỉ" />
				
			</div>
			
			<div class="form-group">
			
				<label> 
					Buổi sáng
				</label>
				<br>
				<div class="row">
					<div class="col-sm-6 col-xs-12">
						<label for="worktimeStartMorning"> Từ
							<span class="icon-asterisk text-warning"></span>
						</label>
						<input type="text" id="worktimeStartMorning" name="worktimeStartMorning" class="form-control"
						placeholder="Từ" required validationMessage="Nhập thời gian"/>
					</div>
					<div class="col-sm-6 col-xs-12">
						<label for="worktimeEndMorning"> đến
							<span class="icon-asterisk text-warning"></span>
						</label>
						<input type="text" id="worktimeEndMorning" name="worktimeEndMorning" class="form-control"
						placeholder="Đến" required validationMessage="Nhập thời gian"/>
					</div>
				</div>

				<label class="MT15"> 
					Buổi chiều
				</label>
				<br>
				<div class="row">
					<div class="col-sm-6 col-xs-12">
						<label for="worktimeStartAfter"> Từ
							<span class="icon-asterisk text-warning"></span>
						</label>
						<input type="text" id="worktimeStartAfter" name="worktimeStartAfter" class="form-control"
						placeholder="Từ" required validationMessage="Nhập thời gian"/>
					</div>
					<div class="col-sm-6 col-xs-12">
						<label for="worktimeEndAfter"> đến
							<span class="icon-asterisk text-warning"></span>
						</label>
						<input type="text" id="worktimeEndAfter" name="worktimeEndAfter" class="form-control"
						placeholder="Đến" required validationMessage="Nhập thời gian"/>
					</div>
				</div>
				
			</div>
				
			<div class="form-group text-right">
				<#-- <button class="btn btn-sm btn-active" 
					id="_worktimeDetail_AddBtn" name="_worktimeDetail_AddBtn" type="button"
					dât-loading-tẽt="<i class='fa fa-spinner fa-spin '></i> Đang lưu thông tin...">
					<i class="fa fa-check-circle"></i>
					<span class="lfr-btn-label">Lưu và thêm mới</span>
				</button> -->

				<button class="btn btn-sm btn-active" 
					id="_worktimeDetail_submitBtn" name="_worktimeDetail_submitBtn" type="button"
					data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang lưu thông tin...">
					<i class="fa fa-check-circle"></i>
					<span class="lfr-btn-label">Lưu lại</span>
				</button>
			
			</div>
				
		</form>

	</div>

</div>


<script type="text/javascript">


(function($) {
	
	$(document).on('click', '#_worktimeDetail_submitBtn', function(event){
		
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();
		
		var validator = $("#_worktimeDetail_form").kendoValidator().data("kendoValidator");
		
		if (!validator.validate()) {
			return;
		}
		
		var _worktime_BaseUrl = "/o/rest/v2/worktimes";
		var hourTem = '';
		var worktimeStartMorning = $("#worktimeStartMorning").val();
		var worktimeEndMorning = $("#worktimeEndMorning").val();
		var worktimeStartAfter = $("#worktimeStartAfter").val();
		var worktimeEndAfter = $("#worktimeEndAfter").val();
		hourTem = worktimeStartMorning + '-' + worktimeEndMorning + ',' + worktimeStartAfter + '-' + worktimeEndAfter;

		if ($("#_worktime_hidden_new_id").val() != "" && $("#_worktime_hidden_new_id").val() !== undefined && $("#_worktime_hidden_new_id").val() !== null) {

			// update jobpos
			
			$.ajax({
				type: 'PUT',
				url: _worktime_BaseUrl + '/' + $("#_worktime_hidden_new_id").val(),
				data: {
					day: $("#worktimeDay").val(),
					hours: hourTem
				},
				dataType: 'json',
				beforeSend: function( xhr ) {
					$(event.currentTarget).button('loading');
				},
				headers: {
					"groupId": ${groupId}
				},
				success: function(data) {

					if (data.hasOwnProperty('msg') && data.msg == "error") {
						showMessageByAPICode(data.statusCode);
					} else {
						// $("#_worktime_hidden_new_id").val(data.holidayDate);
						var dataSource = $("#_worktime_listView").getKendoListView().dataSource;
						dataSource.pushUpdate(data);

						// $.map( dataSource.data(), function( obj, i ) {
						// 	if(obj.jobPosId == data.jobPosId) {
						// 		var listView = $("#_jobpos_listView").data("kendoListView");
						// 		listView.select(listView.element.children()[i]);
						// 	}
						// });
						notification.show({
							message: "Yêu cầu được thực hiện thành công"
						}, "success");
					}
					$(event.currentTarget).button('reset');
				},
				error: function(xhr, textStatus, errorThrown) {
					$(event.currentTarget).button('reset');
					notification.show({
						message: "Yêu cầu của bạn xử lý thất bại!"
					}, "error");
					// showMessageToastr("error", 'Yêu cầu của bạn xử lý thất bại!');

				}
			});

		} else {

			var dataSource = $("#_worktime_listView").getKendoListView().dataSource;
			var datas = dataSource.data();
			if (datas.length > 0) {
				for (var i = 0; i < datas.length; i++) {
					if (datas[i]['day'] == $("#worktimeDay").val()) {
						notification.show({
							message: "Ngày này đã có trong hệ thống, không thể thêm mới!"
						}, "error");
						return;
					}
				}
			}

			$.ajax({
				type: 'POST',
				url: _worktime_BaseUrl,
				data: {
					day: $("#worktimeDay").val(),
					hours: hourTem
				},
				dataType: 'json',
				headers: {
					"groupId": ${groupId}
				},
				success: function(data) {

					if (data.hasOwnProperty('msg') && data.msg == "error") {

						showMessageByAPICode(data.statusCode);

					} else {

						// $("#_worktime_hidden_new_id").val(data.holidayDate);

						
						// $("#_worktime_listView").getKendoListView().clearSelection();
						dataSource.pushCreate(data);
						$('#_worktime_CounterList').html(dataSource.total());
						notification.show({
							message: "Yêu cầu được thực hiện thành công"
						}, "success");
					}

				},
				error: function(xhr, textStatus, errorThrown) {
					notification.show({
						message: "Yêu cầu của bạn xử lý thất bại!"
					}, "error");
					// showMessageToastr("error", 'Yêu cầu của bạn xử lý thất bại!');

				}
			});

		}
		

	});

	function clearHoliday(argument) {
		$( "#holidayDescription" ).val('');
		$("#holidayDate").data("kendoMultiSelect").value('');
	}

	$('#_worktimeDetail_AddBtn').click(function (event) {
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();
		setTimeout(function () {
			$("#_worktimeDetail_submitBtn").click();
			$("#_worktime_listView").getKendoListView().clearSelection();
			clearHoliday();
		}, 500)
	})
	$('#worktimeStartMorning').kendoTimePicker({
		dateInput: true,
		format: "HH.mm",
		placeholder: 'Thời gian bắt đầu'
	});
	$('#worktimeEndMorning').kendoTimePicker({
		dateInput: true,
		format: "HH.mm",
		placeholder: 'Thời gian kết thúc'
	});
	$('#worktimeStartAfter').kendoTimePicker({
		dateInput: true,
		format: "HH.mm",
		placeholder: 'Thời gian bắt đầu'
	});
	$('#worktimeEndAfter').kendoTimePicker({
		dateInput: true,
		format: "HH.mm",
		placeholder: 'Thời gian kết thúc'
	});
	$("#worktimeDay").kendoComboBox({
		placeholder : "Chọn ngày làm việc",
		dataTextField : "text",
		dataValueField : "value",
		dataSource : [
			{
				value: 0,
				text: 'Chủ nhật'
			},
			{
				value: 1,
				text: 'Thứ 2'
			},
			{
				value: 2,
				text: 'Thứ 3'
			},
			{
				value: 3,
				text: 'Thứ 4'
			},
			{
				value: 4,
				text: 'Thứ 5'
			},
			{
				value: 5,
				text: 'Thứ 6'
			},
			{
				value: 6,
				text: 'Thứ 7'
			}
		],
		dataBound : function(e){
			$(".k-clear-value").addClass("k-hidden");
		},
		noDataTemplate: 'Không có dữ liệu'
	});
})(jQuery);

</script>


