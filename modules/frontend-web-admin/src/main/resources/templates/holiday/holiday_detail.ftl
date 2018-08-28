<#include "init.ftl">
<div class="row panel">
	
	<div class="form-group">
		
		<h3 class="detail-header MT5">
			
			<span> Chi tiết ngày nghỉ</span>

		</h3>
		
	</div>

	<div class="panel-body">

		<form id="_holidayDetail_form">

			<div class="form-group">
			
				<label for="holidayDate">Ngày nghỉ
				
					<span class="icon-asterisk text-warning"></span>
				
				</label>
				
				<input type="text" id="holidayDate" name="holidayDate" class="form-control"
					placeholder="Ngày nghỉ" data-bind="value: holidayDate" required validationMessage="Nhập ngày nghỉ" data-bind="value: holidayDate"/>
				
			</div>
			
			<div class="form-group">
			
				<label for="holidayDescription">Mô tả
				
					<span class="icon-asterisk text-warning"></span>
				
				</label>
				
				<textarea id="holidayDescription" name="holidayDescription" class="form-control"
					placeholder="Mô tả" required validationMessage="Nhập mô tả" data-bind="value: holidayDescription"> </textarea>
				<#-- <input type="text" id="holidayDescription" name="holidayDescription" class="form-control"
					placeholder="Mô tả" required validationMessage="Nhập mô tả"/> -->
				
			</div>
				
			<div class="form-group text-right">
				<#-- <button class="btn btn-sm btn-active" 
					id="_holidayDetail_AddBtn" name="_holidayDetail_AddBtn" type="button"
					data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang lưu thông tin...">
					<i class="fa fa-check-circle"></i>
					<span class="lfr-btn-label">Lưu và thêm mới</span>
				</button> -->

				<button class="btn btn-sm btn-active" 
					id="_holidayDetail_submitBtn" name="_holidayDetail_submitBtn" type="button"
					data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang lưu thông tin...">
					<i class="fa fa-check-circle"></i>
					<span class="lfr-btn-label">Xác nhận</span>
				</button>
			
			</div>
				
		</form>

	</div>

</div>


<script type="text/javascript">


(function($) {
	
	$(document).on('click', '#_holidayDetail_submitBtn', function(event){
		
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();
		
		var validator = $("#_holidayDetail_form").kendoValidator().data("kendoValidator");
		
		if (!validator.validate()) {
			return;
		}
		
		var _holiday_BaseUrl = "/o/rest/v2/holidays";
		var holidayDate = new Date($("#holidayDate").data('kendoDatePicker').value());
		var timeSt = holidayDate.getTime();
		if ($("#_holiday_hidden_new_id").val() != 0 ) {

			// update jobpos
			
			$.ajax({
				type: 'PUT',
				url: _holiday_BaseUrl + '/' + $("#_holiday_hidden_new_id").val(),
				data: {
					holidayDate: timeSt,
					description: $("#holidayDescription").val()
				},
				dataType: 'json',
				headers: {
					"groupId": ${groupId}
				},
				beforeSend: function( xhr ) {
					$(event.currentTarget).button('loading');
				},
				success: function(data) {

					if (data.hasOwnProperty('msg') && data.msg == "error") {
						showMessageByAPICode(data.statusCode);
					} else {
						// $("#_holiday_hidden_new_id").val(data.holidayDate);
						var dataSource = $("#_holiday_listView").getKendoListView().dataSource;
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
						// showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
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

			$.ajax({
				type: 'POST',
				url: _holiday_BaseUrl,
				data: {
					holidayDate: timeSt,
					description: $("#holidayDescription").val()
				},
				dataType: 'json',
				headers: {
					"groupId": ${groupId}
				},
				success: function(data) {

					if (data.hasOwnProperty('msg') && data.msg == "error") {

						showMessageByAPICode(data.statusCode);

					} else {

						// $("#_holiday_hidden_new_id").val(data.holidayDate);

						var dataSource = $("#_holiday_listView").getKendoListView().dataSource;
						
						dataSource.pushUpdate(data);
						$('#_holiday_CounterList').html(dataSource.total());
						notification.show({
							message: "Yêu cầu được thực hiện thành công"
						}, "success");
						// showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
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

	$('#_holidayDetail_AddBtn').click(function (event) {
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();
		setTimeout(function () {
			$("#_holidayDetail_submitBtn").click();
			$("#_holiday_listView").getKendoListView().clearSelection();
			clearHoliday();
		}, 500)
	})

	$("#holidayDate").kendoDatePicker({
		format: "dd/MM/yyyy",
		dateInput: true,
		placeholder: 'Ngày nghỉ'
	});
})(jQuery);

</script>


