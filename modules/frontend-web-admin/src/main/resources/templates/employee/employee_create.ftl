<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="modal-header">

	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	
	<h4 class="modal-title">
		
		Thêm mới nhân sự

	</h4>
	
</div>

<div class="modal-body">
	
	<form id="employee-create-form">

		<div class="form-group">

			<label for="employee-create-employeeno">Mã nhân sự

				<span class="icon-asterisk text-warning"></span>

			</label>
			<input type="text" id="employee-create-employeeno" name="employee-create-employeeno" class="form-control"
			placeholder="Mã nhân sự" required validationMessage="Nhập mã nhân sự"/>

		</div>

		<div class="form-group">

			<label for="employee-create-fullname">Họ tên

				<span class="icon-asterisk text-warning"></span>

			</label>
			<input type="text" id="employee-create-fullname" name="employee-create-fullname" class="form-control"
			placeholder="Họ tên" required validationMessage="Nhập họ tên"/>

		</div>
		
		<div class="form-group">
			
			<div class="row">

				<div class="col-sm-6">
					
					<div class="form-group">

						<label for="employee-create-birthdate">Ngày sinh</label>
						<input type="text" id="employee-create-birthdate" name="employee-create-birthdate" class="form-control"
						placeholder="Ngày sinh"/>

					</div>

				</div>

				<div class="col-sm-6">
					
					<div class="form-group">

						<label for="employee-create-gender">Giới tính</label>
						<input type="text" id="employee-create-gender" name="employee-create-gender" class="form-control"
						placeholder="Giới tính"/>

					</div>

				</div>
				
			</div>

		</div>

		<div class="form-group">

			<label for="employee-create-title">Học hàm, học vị</label>
			<input type="text" id="employee-create-title" name="employee-create-title" class="form-control"
			placeholder="Học hàm, học vị"/>

		</div>

		<div class="form-group">

			<div class="row">

				<div class="col-sm-6">
					
					<div class="form-group">

						<label for="employee-create-telno">Số điện thoại liên lạc:</label>
						<input type="text" id="employee-create-telno" name="employee-create-telno" 
						class="form-control" placeholder="Số điện thoại"
						pattern="^[0-9]{10,11}" validationMessage="Nhập Số điện thoại 10 hoặc 11 số"/>

					</div>

				</div>

				<div class="col-sm-6">
					
					<div class="form-group">

						<label for="employee-create-email">Địa chỉ email:</label>
						<input type="text" id="employee-create-email" name="employee-create-email" 
						class="form-control" placeholder="contact@email.com"
						pattern="[A-Za-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" validationMessage="Nhập Email"/>

					</div>

				</div>
				
			</div>

		</div>

		<div class="form-group">

			<div class="row " >

				<div class="col-sm-6" id=""> 

					<button class="btn btn-sm btn-active pull-right" id="employee-create-submit-btn" name="employee-create-submit-btn" type="button">
						<i class="fa fa-check-circle"></i>
						<span class="lfr-btn-label">Xác nhận</span>
					</button>

				</div> 
				
				<div class="col-sm-6" id=""> 
					
					<button class="btn btn-default btn-sm pull-left" data-dismiss="modal" value="Quay lại">
						<i class="icon-undo"></i>
						<span class="lfr-btn-label">Quay lại</span>
					</button>
					
				</div>
				
			</div>

		</div>

	</form>

</div>

<script type="text/javascript" charset="utf-8">
	
	(function($) {

		var createEmployeeBaseUrl = "${api.server}/employees";

		var getTitleDataBaseUrl = "${api.server}/dictcollections/ACADEMIC_TYPE/dictitems";

		var getTitleDataSource = new kendo.data.DataSource({

			transport: {

				read: function(options) {

					$.ajax({

						url: getTitleDataBaseUrl,
						dataType: "json",
						type: 'GET',
						headers: {
							"groupId": ${groupId}
						},
						data: {

							sort: 'itemName'

						},
						success: function(result) {
							if(typeof result.data !== "undefined"){
								options.success(result);
							}
						}

					});
				}
			},
			schema: {
				data: "data",
				total: "total"
			}

		});

		$("#employee-create-title").kendoComboBox({

			optionLabel: "",
			dataTextField: "itemName",
			dataValueField: "itemName",
			dataSource: getTitleDataSource,
			noDataTemplate: ''

		});

		$("#employee-create-gender").kendoDropDownList({
			dataTextField: "text",
			dataValueField: "value",
			dataSource: [
			{text: "Nam", value: "1" },
			{text: "Nữ", value: "0" }
			]
		});

		$("#employee-create-birthdate").kendoDatePicker({
			start: "month",
			depth: "year",
			format: "dd/MM/yyyy",
			dateInput: false
		});

		$(document).on('click', '#employee-create-submit-btn', function(event){

			event.preventDefault();
			event.stopPropagation();
			event.stopImmediatePropagation();

			var checkFormValidate = $("#employee-create-form").kendoValidator().data("kendoValidator");

			if (!checkFormValidate.validate()) {
				return;
			}

			var birthdate = $("#employee-create-birthdate").data("kendoDatePicker").value();

			birthdate = (birthdate!=null )?kendo.toString(birthdate, 'yyyyMMdd'):"";

			$.ajax({
				url: createEmployeeBaseUrl,
				headers: {
					"groupId": ${groupId}
				},
				data: {

					employeeNo: $( "#employee-create-employeeno" ).val(),
					fullName: $( "#employee-create-fullname" ).val(),
					gender: $( "#employee-create-gender" ).val(),
					birthdate: birthdate,
					title: $( "#employee-create-title" ).val(),
					telNo: $( "#employee-create-telno" ).val(),
					email: $( "#employee-create-email" ).val()

				},
				type: 'POST',
				dataType: 'json',
				contentType: 'application/x-www-form-urlencoded; charset=utf-8',
				success: function(data, textStatus, xhr) {

				// redirect to detail page
				$("#modal-lg").trigger({ type: "click" });
				// $( "#employee-index-page").load("${(url.employeePortlet.employee_detail)!}&${portletNamespace}employeeId="+data.employeeId);

				$("#employee_list").hide();
				$("#employee_detail").show();

				$( "#employee_detail").load("${(url.employeePortlet.employee_detail)!}&${portletNamespace}employeeId=" + data.employeeId);

				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(xhr, textStatus, errorThrown) {

				if (xhr.status == 409) {

					showMessageToastr("error", xhr.responseJSON.message);
					
				} else {

					showMessageByAPICode(xhr.status);

				}

			}
		});

		});

	})(jQuery);

</script>