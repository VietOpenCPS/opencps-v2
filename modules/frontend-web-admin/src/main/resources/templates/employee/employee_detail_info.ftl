<#include "init.ftl">

<div class="employee-deatil-info-wraper">
	
	<div class="row">
		
		<div class="col-sm-12">

			<div class="row">

				<div class="col-sm-7"> 
				
					<div class="label-control clearfix fs20 xeditable-left">
				
						<div class="col-sm-1 employee-deatil-info-back"
							data-href="${(url.employeePortlet.employee_index)!}">
			
							<i class="fa fa-angle-left" aria-hidden="true"></i>

						</div>

						<div class="col-sm-11">

							<a href="javascript:;" id="employee-detail-fullname" data-type="text" 

								data-pk="${(employee.employeeId)!}" 
								data-title="Họ tên:" 
								data-value="${(employee.fullName)!}">
										
									<label>
										${(employee.fullName)!"Chưa có"}
									</label>

							</a>

							<span href="javascript:;" id='employee-detail-fullname-icon' data-pk="${(employee.employeeId)!}">
				
								<i class="fa fa-pencil" aria-hidden="true"></i>

							</span>
							
						</div>
						
					</div>
					
				</div>

				<div class="col-sm-5"> 
				
					<div class="label-control clearfix text-right" >
				
						<div class="col-sm-12 PT5">
							
							<a href="javascript:;" id="employee-detail-working-status" 
								class="text-primary" 
								data-type="select" 
								data-pk="${(employee.employeeId)!}" 
								data-title="TT làm việc:" 
								data-value="${(employee.workingStatus)!}">
									
									<#if (employee.workingStatus)?? && ( employee.workingStatus?number == 1 ) >

										Đang làm việc

									<#else>

										Đã nghỉ việc

									</#if>

							</a>

							<span href="javascript:;" id='employee-detail-working-status-icon' 
								class="text-primary" 
								data-pk="${(employee.employeeId)!}">
				
								<i class="fa fa-angle-down" aria-hidden="true"></i>

							</span>

						</div>

					</div>
					
				</div>

			</div>

		</div>

	</div>

	<div class="row">
				
		<div class="col-sm-5">

			<div>
				
				<input type="file" id="employee-avatar" accept="image/*"  onchange="employeeChangeAvatar(this)" style="display: none;" />

				<img id="employee-avatar-thumbnil" class="img-responsive center-block" style="max-height: 200px;"  src="/o/orgopencpsfrontendadmin/images/default_avatar.png" alt=""/>

				<button id="employee-avatar-btn" data-pk="${(employee.employeeId)!}" class="btn btn-active  btn-block"> 
					Tải ảnh đại diện
				</button>

			</div>

		</div>

		<div class="col-sm-7 MT20">

			<div class="label-control clearfix" >

				<label class="col-sm-1">

					<i class="fa fa-hashtag" aria-hidden="true"></i>

				</label>

				<div class="col-sm-11">
					
					<a href="javascript:;" id="employee-detail-employeeno" data-type="text" 

						data-pk="${(employee.employeeId)!}" 
						data-title="Mã nhân sự:" 
						data-value="${(employee.employeeNo)!}">
								
							${(employee.employeeNo)!"Chưa có"}

					</a>
				</div>
					
				<a href="javascript:;" id='employee-detail-employeeno-icon' data-pk="${(employee.employeeId)!}">
		
					<i class="fa fa-pencil" aria-hidden="true"></i>

				</a>
				
			</div>

			<div class="label-control clearfix" >

				<label class="col-sm-1">

					<i class="fa fa-calendar-o" aria-hidden="true"></i>

				</label>

				<div class="col-sm-11">
				
					<a href="javascript:;" id="employee-detail-birthdate" data-type="combodate" 

						data-pk="${(employee.employeeId)!}" 
						data-title="Ngày sinh:" 
						data-value="${(employee.birthdate)!}">
						<#if employee.birthdate??>
							<#assign birthdate_ = employee.birthdate?datetime("iso") >
							${birthdate_?string["dd/MM/yyyy"]}
						<#else>
							<i class="text-gray">Chưa có</i>
						</#if>
					
					</a>
				</div>
					
				<a href="javascript:;" id='employee-detail-birthdate-icon' data-pk="${(employee.employeeId)!}">
		
					<i class="fa fa-pencil" aria-hidden="true"></i>

				</a>
				
			</div>

			<div class="label-control clearfix" >

				<label class="col-sm-1">
				
					<i class="fa fa-star" aria-hidden="true"></i>

				</label>

				<div class="col-sm-11">
				
					<a href="javascript:;" id="employee-detail-gender" data-type="select" 

						data-pk="${(employee.employeeId)!}" 
						data-title="Giới tính:" 
						data-value="${(employee.gender)!}">
								
							<#if (employee.gender)?? && ( employee.gender?number == 1 ) >

								Nam

							<#else>

								Nữ
								
							</#if>

					</a>
				</div>
					
				<a href="javascript:;" id='employee-detail-gender-icon' data-pk="${(employee.employeeId)!}">
		
					<i class="fa fa-pencil" aria-hidden="true"></i>

				</a>
				
			</div>

			<div class="label-control clearfix" >

				<label class="col-sm-1">

					<i class="fa fa-phone" aria-hidden="true"></i>

				</label>

				<div class="col-sm-11">
				
					<a href="javascript:;" id="employee-detail-telno" data-type="text" 

						data-pk="${(employee.employeeId)!}" 
						data-title="Số điện thoại:" 
						data-value="${(employee.telNo)!}">
							
							<#if (employee.telNo)?? && ( employee.telNo != "" ) >

								${(employee.telNo)!"Chưa có"}

							<#else>

								<i class="text-gray">Chưa có</i>
								
							</#if>

					</a>
				</div>
					
				<a href="javascript:;" id='employee-detail-telno-icon' data-pk="${(employee.employeeId)!}">
		
					<i class="fa fa-pencil" aria-hidden="true"></i>

				</a>
				
			</div>

			<div class="label-control clearfix" >

				<label class="col-sm-1">

					<i class="fa fa-envelope" aria-hidden="true"></i>

				</label>

				<div class="col-sm-11">
				
					<a href="javascript:;" id="employee-detail-email" data-type="text" 

						data-pk="${(employee.employeeId)!}" 
						data-title="Email:" 
						data-value="${(employee.email)!}">
								
							<#if (employee.email)?? && ( employee.email != "" ) >

								${(employee.email)!"Chưa có"}

							<#else>

								<i class="text-gray">Chưa có</i>
								
							</#if>

					</a>
				</div>
					
				<a href="javascript:;" id='employee-detail-email-icon' data-pk="${(employee.employeeId)!}">
		
					<i class="fa fa-pencil" aria-hidden="true"></i>

				</a>
				
			</div>

			<div class="label-control clearfix" >

				<label class="col-sm-1">

					<i class="fa fa-graduation-cap" aria-hidden="true"></i>

				</label>

				<div class="col-sm-11">
				
					<a href="javascript:;" id="employee-detail-title" data-type="text" 

						data-pk="${(employee.employeeId)!}" 
						data-title="Học hàm:" 
						data-value="${(employee.title)!}">
								
							<#if (employee.title)?? && ( employee.title != "" ) >

								${(employee.title)!"Chưa có"}

							<#else>

								<i class="text-gray">Chưa có</i>
								
							</#if>

					</a>
				</div>
					
				<a href="javascript:;" id='employee-detail-title-icon' data-pk="${(employee.employeeId)!}">
		
					<i class="fa fa-pencil" aria-hidden="true"></i>

				</a>
				
			</div>
			
			<div class="PT10">

				<div class="label-control clearfix" >

					<label class="col-sm-5">Tên đăng nhập:</label>

					<div class="col-sm-7">
					
						<a href="javascript:;" >
									
							<#if employee_accountInfo?? && employee_accountInfo.screenName?? >
						
								${(employee_accountInfo.screenName)!}
							<#else>
								<i class="text-gray">Chưa có</i>
							</#if>

						</a>

					</div>
					
				</div>

			</div>

			<div class="label-control clearfix" >

				<label class="col-sm-5">Tài khoản email:</label>

				<div class="col-sm-7">
				
					<a href="javascript:;" >
						
						<#if (employee_accountInfo.email)?? && ( employee_accountInfo.email != "" ) >

							${(employee_accountInfo.email)!"Chưa có"}

						<#else>

							<i class="text-gray">Chưa có</i>
							
						</#if>

					</a>
				</div>
				
			</div>

			<div class="label-control clearfix PT10" >
				
				
				<#if employee_accountInfo?? >
					
					<#if employee_accountInfo.lock?? && employee_accountInfo.lock == true >
					
						<button name="employee-detail-looking" 
							data-pk="${(employee.employeeId)!}" data-vl="false" 
							class="btn btn-active border-rad-4"> 
							Mở khóa tài khoản
						</button>

						<button name="employee-detail-looking" style="display: none" 
							data-pk="${(employee.employeeId)!}" data-vl="true"
							class="btn btn-active border-rad-4"> 
							Khóa tài khoản
						</button>

					<#else>
						
						<button name="employee-detail-looking" style="display: none" 
							data-pk="${(employee.employeeId)!}" data-vl="false" 
							class="btn btn-active border-rad-4"> 
							Mở khóa tài khoản
						</button>

						<button name="employee-detail-looking" 
							data-pk="${(employee.employeeId)!}" data-vl="true"
							class="btn btn-active border-rad-4"> 
							Khóa tài khoản
						</button>

					</#if>
					

				<#else>

					<button id="employee-detail-account" 
						data-pk="${(employee.employeeId)!}"
						class="btn btn-active border-rad-4"> 
							Mở tài khoản
					</button>
					
				</#if>

			</div>

		</div>

	</div>

</div>

<script type="text/javascript" charset="utf-8" >

var employeeUpdateBaseUrl = '${api.endpoint}/employees';

function employeeChangeAvatar(fileInput) {
	
	var files = fileInput.files;

	if (files.length > 0) {

		//show client
		for (var i = 0; i < files.length; i++) {
		
			var file = files[i];
			var imageType = /image.*/; 
			
			if ( i>0 ) {
				continue;
			}

			if (!file.type.match(imageType)) {
				continue;
			}
			
			var img=document.getElementById("employee-avatar-thumbnil");
			img.file = file;
			var reader = new FileReader();
			
			reader.onload = (function(aImg) { 
			
				return function(e) { 
					aImg.src = e.target.result;
				}; 

			})(img);
			
			reader.readAsDataURL(file);

			// call ajax
			
			var fileName= file.name;
			var fileType= file.type;
			var fileSize= file.size;
			var className= "${(constant.className)!}";
			var classPK= $("#employee-avatar-btn").attr("data-pk");
			var formData = new FormData();

			formData.append('file', file);
			formData.append('fileName', fileName);
			formData.append('fileType', fileType);
			formData.append('fileSize', fileSize);
			formData.append('className', className);
			formData.append('classPK', classPK);

			$.ajax({
						
				url: employeeUpdateBaseUrl + "/"+ classPK + "/photo" ,
				
				type: 'PUT',
				headers: {
					"groupId": ${groupId}
				},
				async: false,
				contentType: false,
				processData: false, 
				data: formData,
				success: function(result) {
				
					showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
					
				},
				error: function(xhr, textStatus, errorThrown) {
				
					showMessageByAPICode(xhr.status);
			
				}
			
			});
			
		}

	}

}

(function($) {

	$(document).on('click', '#employee-avatar-btn', function(event){
			
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();
		
		$("#employee-avatar").trigger({ type: "click" });
		
	});

	var employeeId = $("#employee-avatar-btn").attr('data-pk');

	if (employeeId!=null && employeeId!="" && employeeId > 0 ) {

        getImageBlob(employeeUpdateBaseUrl+"/"+employeeId+"/photo", $("#employee-avatar-thumbnil"), "${groupId}");

	}

})(jQuery);

</script>

<script>

(function($) {

	$.fn.editable.defaults.mode = 'inline';
	$.fn.editable.defaults.send = "always";
	
	var employeeUpdatBaseUrl = '${api.endpoint}/employees';
	
	$(document).on('click', '#employee-detail-employeeno-icon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		$('#employee-detail-employeeno').editable({
			name: 'employeeNo',
			url: employeeUpdatBaseUrl + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				headers: {
					"groupId": parseInt("${groupId}")
				},
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					employeeNo: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success: function(data) {
				
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			},
			emptytext :"Chưa có"
		}); 
				 
		$('#employee-detail-employeeno').editable('toggle');
			
	});
	
	$(document).on('click', '#employee-detail-fullname-icon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		$('#employee-detail-fullname').editable({
			name: 'fullName',
			url: employeeUpdatBaseUrl + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				headers: {
					"groupId": parseInt("${groupId}")
				},
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					fullName: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success: function(data) {
				
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			},
			emptytext :"Chưa có"
		}); 
				 
		$('#employee-detail-fullname').editable('toggle');
			
	});

	$(document).on('click', '#employee-detail-gender-icon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		$('#employee-detail-gender').editable({
			name: 'gender',
			url: employeeUpdatBaseUrl + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				headers: {
					"groupId": parseInt("${groupId}")
				},
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					gender: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success: function(data) {
				
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			},
			source: [
				{text: "Nam", value: "1" },
				{text: "Nữ", value: "0" }
			]
		}); 
				 
		$('#employee-detail-gender').editable('toggle');
			
	});
	
	$(document).on('click', '#employee-detail-birthdate-icon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		try {

			$('#employee-detail-birthdate').editable({
				name: 'birthdate',
				url: employeeUpdatBaseUrl + '/' + pk,
				template: 'DD/MM/YYYY',
				format: 'YYYY/MM/DD',
				viewformat: 'DD/MM/YYYY',
				emptytext: "__/__/__",
				ajaxOptions: {
					method: 'PUT',
					dataType: 'json',
					headers: {
						"groupId": parseInt("${groupId}")
					},
					contentType: "application/x-www-form-urlencoded;charset=utf-8",
				},
				params: function(params) { 
					return {
						birthdate: params.value.split("/").join("")
					};
				},
				validate: function(value) {
					if (value.length < 1){
						return 'Đây là trường bắt buộc';
					}
				},
				success: function(data) {
					
					showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
					
				},
				error: function(event, id, obj) {
					showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
				},
				emptytext :"Chưa có"
			}); 

		} catch (e) {
			console.log(e);
		} 
				 
		$('#employee-detail-birthdate').editable('toggle');
			
	});
	
	$(document).on('click', '#employee-detail-telno-icon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		$('#employee-detail-telno').editable({
			name: 'telNo',
			url: employeeUpdatBaseUrl + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				headers: {
					"groupId": parseInt("${groupId}")
				},
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					telNo: params.value
				};
			},
			validate: function(value) {
				
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				} else if ( isNaN(value) || value.length > 11 ) {
					return 'Số điện thoại trên 10 số';
				}
			},
			success: function(data) {
				
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			},
			emptytext :"Chưa có"
		}); 
				 
		$('#employee-detail-telno').editable('toggle');
		
	});
	
	$(document).on('click', '#employee-detail-email-icon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		$('#employee-detail-email').editable({
			name: 'email',
			url: employeeUpdatBaseUrl + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				headers: {
					"groupId": parseInt("${groupId}")
				},
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					email: params.value
				};
			},
			validate: function(value) {
				var pattern="[A-Za-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$";
				
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				} else if (!value.match(pattern)) {
					return 'Định dạng: contact@email.com';
				}
			},
			success: function(data) {
				
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				$('#employee-detail-email').attr("data-value", data.email);
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			},
			emptytext :"Chưa có"
		}); 
				 
		$('#employee-detail-email').editable('toggle');
			
	});
	
	$(document).on('click', '#employee-detail-title-icon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		$('#employee-detail-title').editable({
			name: 'title',
			url: employeeUpdatBaseUrl + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				headers: {
					"groupId": parseInt("${groupId}")
				},
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					title: params.value
				};
			},
			success: function(data) {
				
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			},
			emptytext :"Chưa có"
		}); 
				 
		$('#employee-detail-title').editable('toggle');
			
	});

	$(document).on('click', '#employee-detail-working-status-icon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		$('#employee-detail-working-status').editable({
			name: 'workingStatus',
			url: employeeUpdatBaseUrl + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				headers: {
					"groupId": parseInt("${groupId}")
				},
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					workingStatus: params.value
				};
			},
			success: function(data) {
				
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			},
			source: [
				{text: "Đang làm việc", value: "1" },
				{text: "Đã nghỉ việc", value: "0" }
			]
		}); 
				 
		$('#employee-detail-working-status').editable('toggle');
			
	});

	$(document).on('click', 'button[name="employee-detail-looking"]', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		var vl = $(this).attr('data-vl');

		$.ajax({
			url: employeeUpdatBaseUrl + '/' + pk + '/lock',
			headers: {
				"groupId": parseInt("${groupId}")
			},
			data: {
				
				locked: vl
			},
			type: 'POST',
			dataType: 'json',
			contentType: 'application/x-www-form-urlencoded; charset=utf-8',
			success: function(data, textStatus, xhr) {
			
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				$('button[name="employee-detail-looking"]').toggle("fast");
				
			},
			error: function(xhr, textStatus, errorThrown) {
				
				showMessageByAPICode(xhr.status);
				
			}
		});
			
	});

	$(document).on('click', '#employee-detail-account', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		var email = $('#employee-detail-email').attr("data-value").trim();
		var baseUrl = employeeUpdatBaseUrl + '/' + pk + "/account";
		
		if ( email != null && email != "" ) {
			employeeCreateAccount (pk, baseUrl, email, false);
		} else {
			showMessageToastr("error", 'Chưa tồn tại email nhân sự!');
		}
		
	});

	function employeeCreateAccount (pk, baseUrl, email, exist) {

		$.ajax({
			url: baseUrl,
			headers: {
				"groupId": parseInt("${groupId}")
			},
			data: {
				email: email,
				exist: exist
			},
			type: 'POST',
			dataType: 'json',
			contentType: 'application/x-www-form-urlencoded; charset=utf-8',
			success: function(data, textStatus, xhr) {
				
				if ( data.hasOwnProperty('code') && data.code == 409) {
					
					var strMess = 'Email "' + email + '" trùng với tài khoản "' + email + '". Bạn muốn sử dụng tài khoản "' + email + '" cho hồ sơ này?';
					if (confirm(strMess)) {
						
						employeeCreateAccount(pk, baseUrl, email, true);
					
					}

				} else {

					showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
					location.reload();

				}
				
			},
			error: function(xhr, textStatus, errorThrown) {
				
				if (xhr.status == 409) {
					
					var strMess = 'Email "' + xhr.responseJSON.email + '" trùng với tài khoản "' + xhr.responseJSON.screenName + '". Bạn muốn sử dụng tài khoản "' + xhr.responseJSON.screenName + '" cho hồ sơ này?';
					if (confirm(strMess)) {
						
						employeeCreateAccount(pk, baseUrl, email, true);
					
					}

				} else {

					showMessageByAPICode(xhr.status);

				}
			
			}
		});

	};
	
	$(document).delegate('.employee-deatil-info-back','click', function(e) {
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var url = $(e.currentTarget).attr('data-href');
		$("#employee-index-page").load(url);
	});
	
})(jQuery);

</script>