<#if (Request)??>
<#include "init.ftl">
</#if>
<div id="frmDetailAccount">
<div class="row account-info" >
	<div class="col-sm-2 col-xs-12">
		<img src="/o/frontend.web.portal/images/default_avatar.png" class="img-responsive max-width-100 img-rounded">
		<div class="text-center"><a href="" class="text-light-gray">Thay đổi avatar</a></div>
		<p class="name text-bold text-center" data-bind="text:applicantName" id="profileName"></p>
		<div>Số CMND/Hộ chiếu: <span class="text-bold" data-bind="text:applicantIdNo" id="profileIdNo"></span></div>
		<div>Ngày cấp: <span class="text-bold" data-bind="text:applicantIdDate" id="profileDate"></span></div>
		<div>Thư điện tử: <span class="text-bold" data-bind="text:contactEmail" id="profileEmail"></span></div>

		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#tttk" role="tab" data-toggle="tab">Thông tin tài khoản</a></li>
			<li role="presentation"><a href="#dmk" role="tab" data-toggle="tab">Đổi mật khẩu</a></li>
		</ul>
	</div>
	<div class="col-sm-10 col-xs-12">
	<div class="tab-content box">
		<#if applicantIdType == "business">
		<div role="tabpanel" class="tab-pane active" id="tttk">
			<div class="row-header">
				<div class="background-triangle-big"><i class="fa fa-user" aria-hidden="true"></i></div>
				<span class="text-bold">Thông tin tài khoản</span>
			</div>
			<div class="row-parts-content PB15">
				<div class="row MB15">
					<div class="col-sm-2">
						<p> Tên công ty</p>
					</div>
					<div class="col-sm-7">
						<span id="applicantName" data-pk="1" data-toggle="#editApplicantName" data-original-title="Nhập tên công ty" tabindex="-1" class="" data-bind="text:applicantName"> <#-- ${api.applicant.applicantName} --> </span>
						<span class="pull-right">
							<a href="#" id="editApplicantName" style="float: right"><i class="fa fa-pencil"></i></a>
						</span>
					</div>
				</div>
				<div class="row MB15">
					<div class="col-sm-2">
						<p>Địa chỉ tổ chức</p>
					</div>
					<div class="col-sm-7">
						<span id="address" data-pk="1" data-toggle="#editAddress" data-original-title="Nhập địa chỉ" tabindex="-1" class="" data-bind="text:address"> ${address} </span>
						<span class="pull-right">
							<a href="#" id="editAddress" style="float: right"><i class="fa fa-pencil"></i></a>
						</span>
					</div>
				</div>
				<div class="row MB15">
					<div class="col-sm-2">
						<p>Tỉnh/ Thành phố</p>
					</div>
					<div class="col-sm-7">
						<span id="city" data-pk="1" data-type="select" data-toggle="#editCity" data-original-title="Chọn thành phố" tabindex="-1" class="" data-bind="text:cityName"><#--  ${api.applicant.cityName} --> </span>
						<span class="pull-right">
							<a href="#" id="editCity" style="float: right"><i class="fa fa-pencil"></i></a>
						</span>
					</div>
				</div>
				<div class="row MB15">
					<div class="col-sm-2">
						<p>Quận/ Huyện</p>
					</div>
					<div class="col-sm-7">
						<span id="district" data-pk="1" data-type="select" data-toggle="#editDistrict" data-original-title="Chọn quận/ huyện" tabindex="-1" class="" data-bind="text:districtName"><#-- ${api.applicant.districtName} --></span>
						<span class="pull-right">
							<a href="#" id="editDistrict" style="float: right"><i class="fa fa-pencil"></i></a>
						</span>
					</div>
				</div>
				<div class="row MB15">
					<div class="col-sm-2">
						<p>Xã/ Phường</p>
					</div>
					<div class="col-sm-7">
						<span id="wards" data-pk="1" data-type="select" data-toggle="#editWards" data-original-title="Chọn xã/ phường" tabindex="-1" class="" data-bind="text:wardName"><#-- ${api.applicant.wardName} --></span>
						<span class="pull-right">
							<a href="#" id="editWards" style="float: right"><i class="fa fa-pencil"></i></a>
						</span>
					</div>
				</div>
				<div class="row MB15">
					<div class="col-sm-2">
						<p>Điện thoại</p>
					</div>
					<div class="col-sm-7">
						<span id="phone" data-pk="1" data-toggle="#editPhone" data-original-title="Nhập số điện thoại" tabindex="-1" class="" data-bind="text:contactTelNo"><#-- ${api.applicant.contactTelNo} --></span>
						<span class="pull-right">
							<a href="#" id="editPhone" style="float: right"><i class="fa fa-pencil"></i></a>
						</span>
					</div>
				</div>
				<div class="row MB15">
					<div class="col-sm-2">
						<p>Thư điện tử - Email</p>
					</div>
					<div class="col-sm-7">
						<span id="email" data-pk="1" data-toggle="#editEmail" data-original-title="Nhập email" tabindex="-1" class="" data-bind="text:contactEmail"><#-- ${api.applicant.contactEmail} --> </span>
						<span class="pull-right">
							<a href="#" id="editEmail" style="float: right"><i class="fa fa-pencil"></i></a>
						</span>
					</div>
				</div>
				<div class="row MB15">
					<div class="col-sm-2">
						<p>Tên người đại diện</p>
					</div>
					<div class="col-sm-7">
						<span id="contactName" data-pk="1" data-toggle="#editContactName" data-original-title="Tên người đại diện" tabindex="-1" class="" data-bind="text:contactName"><#-- ${api.applicant.contactName} --></span>
						<span class="pull-right">
							<a href="#" id="editContactName" style="float: right"><i class="fa fa-pencil"></i></a>
						</span>
					</div>
				</div>
			</div>
			<#else>
			<div role="tabpanel" class="tab-pane active" id="tttk">
				<div class="row-header">
					<div class="background-triangle-big"><i class="fa fa-user" aria-hidden="true"></i></div>
					<span class="text-bold">Thông tin tài khoản</span>
				</div>
				<div class="row-parts-content PB15">
					<div class="row MB15">
						<div class="col-sm-2">
							<p>Họ và tên</p>
						</div>
						<div class="col-sm-7">
							<span id="applicantName" data-pk="1" data-toggle="#editApplicantName" data-original-title="Nhập họ và tên" tabindex="-1" class="" data-bind="text:applicantName"><#-- ${api.applicant.applicantName} --></span>
							<span class="pull-right">
								<a href="#" id="editApplicantName" style="float: right"><i class="fa fa-pencil"></i></a>
							</span>
						</div>
					</div>
					<div class="row MB15">
						<div class="col-sm-2">
							<p>Địa chỉ</p>
						</div>
						<div class="col-sm-7">
							<span id="address" data-pk="1" data-toggle="#editAddress" data-original-title="Nhập địa chỉ" tabindex="-1" class="" data-bind="text:address"><#-- ${api.applicant.address} --></span>
							<span class="pull-right">
								<a href="#" id="editAddress" style="float: right"><i class="fa fa-pencil"></i></a>
							</span>
						</div>
					</div>
					<div class="row MB15">
						<div class="col-sm-2">
							<p>Tỉnh/ Thành phố</p>
						</div>
						<div class="col-sm-7">
							<span id="city" data-pk="1" data-type="select" data-toggle="#editCity" data-original-title="Chọn tỉnh/ thành phố" tabindex="-1" class="" data-bind="text:cityName"><#-- ${api.applicant.cityName} --></span>
							<span class="pull-right">
								<a href="#" id="editCity" style="float: right"><i class="fa fa-pencil"></i></a>
							</span>
						</div>
					</div>
					<div class="row MB15">
						<div class="col-sm-2">
							<p>Quận/ Huyện</p>
						</div>
						<div class="col-sm-7">
							<span id="district" data-pk="1" data-type="select" data-toggle="#editDistrict" data-original-title="Chọn quận/ huyện" tabindex="-1" class="" data-bind="text:districtName"><#-- ${api.applicant.districtName} --></span>
							<span class="pull-right">
								<a href="#" id="editDistrict" style="float: right"><i class="fa fa-pencil"></i></a>
							</span>
						</div>
					</div>
					<div class="row MB15">
						<div class="col-sm-2">
							<p>Xã/ Phường</p>
						</div>
						<div class="col-sm-7">
							<span id="wards" data-pk="1" data-type="select" data-toggle="#editWards" data-original-title="Chọn xã/ phường" tabindex="-1" class="" data-bind="text:wardName"><#-- ${api.applicant.wardName} --></span>
							<span class="pull-right">
								<a href="#" id="editWards" style="float: right"><i class="fa fa-pencil"></i></a>
							</span>
						</div>
					</div>
					<div class="row MB15">
						<div class="col-sm-2">
							<p>Điện thoại</p>
						</div>
						<div class="col-sm-7">
							<span id="phone" data-pk="1" data-toggle="#editPhone" data-original-title="Nhập số điện thoại" tabindex="-1" class="" data-bind="text:contactTelNo"><#-- ${api.applicant.contactTelNo} --></span>
							<span class="pull-right">
								<a href="#" id="editPhone" style="float: right"><i class="fa fa-pencil"></i></a>
							</span>
						</div>
					</div>
					<div class="row MB15">
						<div class="col-sm-2">
							<p>Thư điện tử - Email</p>
						</div>
						<div class="col-sm-7">
							<span id="email" data-pk="1" data-toggle="#editEmail" data-original-title="Nhập email" tabindex="-1" class="" data-bind="text:contactEmail"><#-- ${api.applicant.contactEmail} --></span>
							<span class="pull-right">
								<a href="#" id="editEmail" style="float: right"><i class="fa fa-pencil"></i></a>
							</span>
						</div>
					</div>
				</div>
				</#if>
			</div>
			<div role="tabpanel" class="tab-pane" id="dmk">
				<div class="row-header">
					<div class="background-triangle-big"><i class="fa fa-user" aria-hidden="true"></i></div>
					<span class="text-bold">Đổi mật khẩu</span>
				</div>
				<form id="fmChangePasswordUser">
					<div class="row-parts-content PB15">
						<div class="row">
							<div class="col-sm-2">
								<label class="with-input-sm">Mật khẩu hiện tại:</label>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<input type="password" class="form-control" id="old_password">
								</div>
							</div>
							<div class="col-sm-6 MT5" id="messagePassword">

							</div>
						</div>
						<div class="row">
							<div class="col-sm-2">
								<label class="with-input-sm">Mật khẩu mới:</label>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<input type="password" class="form-control" id="new_password">
								</div>
							</div>
							<div class="col-sm-6">

							</div>
						</div>
						<div class="row">
							<div class="col-sm-2">
								<label class="with-input-sm">Nhập lại mật khẩu mới:</label>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<input type="password" class="form-control" id="retype_new_password">
								</div>
								<div class="checkbox">
									<input type="checkbox" id="show_password"> <label>Hiển thị mật khẩu</label>
								</div>
							</div>
							<div class="col-sm-6">

							</div>
						</div>
						<div class="row">
							<div class="col-sm-2">

							</div>
							<div class="col-sm-10">
								<button class="btn btn-active" id="btn-change-password-user" data-bind="attr:{data-pk : userId}">Lưu thay đổi</button>
							</div>
						</div>
					</div>
				</form>
			</div>

		</div>
	</div>
	<span id="notification"></span>
</div>
</div>
</div>

<script type="text/javascript">

	$('#btn-change-password-user').click(function(){
		var userId = $(this).attr("data-pk");
		$.ajax({
			url : "${api.server}/users/"+userId+"/changepass",
			dataType : "json",
			type : "PUT",
			headers: {"groupId": ${groupId}},
			data : {
				oldPassword : $("#old_password").val(),
				newPassword : $("#retype_new_password").val()
			},
			success : function(result){
				notification.show({
					message: "Yêu cầu được thực hiện thành công"
				}, "success");
				$("#messagePassword").html(' <span class="red"><i class="fa fa-times" aria-hidden="true"></i> <span class="message"></span></span>');
			},
			error : function(xhr){
				$("#messagePassword").html('<span class="red"><i class="fa fa-times" aria-hidden="true"></i> <span class="message">Mật khẩu hoặc tài khoản không đúng</span></span>');
			}
		});
	});

	$("#show_password").click(function(){
		if ($("#show_password").is(":checked")){
			$("#old_password")[0]['type'] = 'input';
			$("#new_password")[0]['type'] = 'input';
			$("#retype_new_password")[0]['type'] = 'input';
		} else {
			$("#old_password")[0]['type'] = 'password';
			$("#new_password")[0]['type'] = 'password';
			$("#retype_new_password")[0]['type'] = 'password';
		}
	});
	var notification = $("#notification").kendoNotification().data("kendoNotification");
	var validator = $("#fm").kendoValidator().data("kendoValidator");

	$("form").submit(function(event) {
		event.preventDefault();
		if (validator.validate()) {
			if ($("#new_password").val().length < 6 || $("#retype_new_password").val().length < 6){
				notification.show(" Xác nhận mật khẩu mới không đúng", "error");
			} else if ($("#new_password").val() != $("#retype_new_password").val()){
				notification.show(" Xác nhận mật khẩu mới không đúng", "error");
			} else if (!checkOldPassword(11111, $("#old_password").val())){
				notification.show(" Xác nhận mật khẩu mới không đúng", "error");
			} else {
				changePassword($("#old_password").val(), $("#new_password").val(), $("#retype_new_password").val());
			}
		}
	});

	function checkOldPassword(userId, oldPassword){
		var result = false;
		$.ajax({
			url: "${api.server}" + "/checkOldPassword",
			type: "GET",
			dataType: "json",
			headers: {"groupId": ${groupId}},
			data: {
				userId: userId,
				oldPassword: oldPassword,
			},
			success: function(res) {
				if (res.result == 'true'){
					result = true;
				}
			}
		});
		return result;
	};

	function changePassword(oldPassword, newPassword, retypeNewPassword){
		$.ajax({
			url: "${api.server}" + "/changePassword",
			type: "POST",
			dataType: "json",
			data: {
				oldPassword: oldPassword,
				newPassword: newPassword,
				retypeNewPassword: retypeNewPassword
			},
			headers: {"groupId": ${groupId}},
			success: function(res) {
				notification.show({
					message: "Đổi mật khẩu thành công"
				}, "success");
			},
			error: function(res) {
				notification.show({
					message: "Xẩy ra lỗi, vui lòng thử lại"
				}, "error");
			}
		});
	}

	$.fn.editable.defaults.mode = 'inline';
	$.fn.editable.defaults.send = "always";
	
	var updateProfileURL = '/o/rest/v2/applicants/${(api.applicant.applicantId)!}';
	
	$('#applicantName').editable({
		url: updateProfileURL,
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		emptytext : "-",
		params: function(params) {
			return {
				applicantName: params.value
			};
		},
		validate: function(value) {
			// if (value.length < 1){
			//   return 'Đây là trường bắt buộc';
			// }
		},
		success: function(response, newValue) {
			$("#profileName").html(newValue);
		},
		error: function(event, id, obj) {

		}
	});


	$('#address').editable({
		url: updateProfileURL,
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		emptytext : "-",
		params: function(params) {
			return {
				address: params.value
			};
		},
		validate: function(value) {
		  // if (value.length < 1){
		  //   return 'Đây là trường bắt buộc';
		  // }
		},
		success: function(data) {

		},
		error: function(event, id, obj) {

		}
	});

	$('#city').editable({
		url: updateProfileURL,
		emptytext : "",
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		params: function(params) {
			var cityName = "";
			$.ajax({
				url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
				dataType : "json",
				type : "GET",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					parent : 0
				},
				success : function(result){
					var items = result.data;
					for (var i = 0; i < items.length; i++) {
						if (items[i].itemCode == params.value){
							cityName = items[i].itemName;
						}
					}
				},
				error : function(xhr){

				}
			});
			return {
				cityCode: params.value,
				cityName: cityName
			};
		},
		validate: function(value) {
			// if (value.length < 1){
			//   return 'Đây là trường bắt buộc';
			// }
		},
		success: function(response, newValue) {
			var arrDisplay = new Array();
			$.ajax({
				url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
				dataType : "json",
				type : "GET",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					parent : newValue
				},
				success : function(result){
					var arrDataRes = result.data;
					for (var i = 0; i < arrDataRes.length; i++) {
						arrDisplay.push({ value: arrDataRes[i].itemCode, text : arrDataRes[i].itemName});
					}
				},
				error : function(xhr){

				}
			});
			$('#district').editable('option', 'source', arrDisplay);
			$('#district').html("-");
			$('#wards').html("-");
			$.ajax({
				url : "${api.server}/applicants/${(api.applicant.applicantId)!}",
				dataType : "json",
				type : "PUT",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					districtCode: "-",
					districtName: "-",
					wardCode: "-",
					wardName: "-",
				},
				success : function(result){
					
				},
				error : function(xhr){

				}
			});
		},
		error: function(event, id, obj) {

		},
		prepend: "",
		source: function(){
			var arrDisplay = new Array();
			$.ajax({
				url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
				dataType : "json",
				type : "GET",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					parent : 0
				},
				success : function(result){
					var arrDataRes = result.data;
					for (var i = 0; i < arrDataRes.length; i++) {
						arrDisplay.push({ value: arrDataRes[i].itemCode, text : arrDataRes[i].itemName});
					}
				},
				error : function(xhr){

				}
			});
			return arrDisplay;
		}
	});


	$('#district').editable({
		url: updateProfileURL,
		emptytext : "",
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		params: function(params) {
			var districtName = "";
			$.ajax({
				url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
				dataType : "json",
				type : "GET",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					parent : $('#city').editable('getValue', true)
				},
				success : function(result){
					var items = result.data;
					for (var i = 0; i < items.length; i++) {
						if (items[i].itemCode == params.value){
							districtName = items[i].itemName;
						}
					}
				},
				error : function(xhr){

				}
			});
			return {
				districtCode: params.value,
				districtName: districtName
			};
		},
		validate: function(value) {
			// if (value.length < 1){
			//   return 'Đây là trường bắt buộc';
			// }
		},
		success : function(response, newValue){
			var arrDisplay = new Array();
			$.ajax({
				url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
				dataType : "json",
				type : "GET",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					parent : newValue
				},
				success : function(result){
					var arrDataRes = result.data;
					for (var i = 0; i < arrDataRes.length; i++) {
						arrDisplay.push({ value: arrDataRes[i].itemCode, text : arrDataRes[i].itemName});
					}
				},
				error : function(xhr){

				}
			});
			$('#wards').editable('option', 'source', arrDisplay);
			$('#wards').html("-");
			$.ajax({
				url : "${api.server}/applicants/${(api.applicant.applicantId)!}",
				dataType : "json",
				type : "PUT",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					wardCode: "-",
					wardName: "-",
				},
				success : function(result){
					
				},
				error : function(xhr){

				}
			});
		},
		error : function(xhr){

		},
		prepend: "",
		source: function(){
			var arrDisplay = new Array();
			$.ajax({
				url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
				dataType : "json",
				type : "GET",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					parent : "${(api.applicant.cityCode)!}"
				},
				success : function(result){
					var arrDataRes = result.data;
					for (var i = 0; i < arrDataRes.length; i++) {
						arrDisplay.push({ value: arrDataRes[i].itemCode, text : arrDataRes[i].itemName});
					}
				},
				error : function(xhr){

				}
			});
			return arrDisplay;
		}
	});

	$('#wards').editable({
		url: updateProfileURL,
		emptytext : "",
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		params: function(params) {
			var wardName = "";
			$.ajax({
				url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
				dataType : "json",
				type : "GET",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					parent : $('#district').editable('getValue', true)
				},
				success : function(result){
					var items = result.data;
					for (var i = 0; i < items.length; i++) {
						if (items[i].itemCode == params.value){
							wardName = items[i].itemName;
						}
					}
				},
				error : function(xhr){

				}
			});
			return {
				wardCode: params.value,
				wardName: wardName
			};
		},
		validate: function(value) {
			// if (value.length < 1){
			//   return 'Đây là trường bắt buộc';
			// }
		},
		success : function(data){

		},
		error : function(xhr){

		},
		prepend: "",
		source: function(){
			var arrDisplay = new Array();
			$.ajax({
				url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
				dataType : "json",
				type : "GET",
				async: false,
				headers: {"groupId": ${groupId}},
				data : {
					parent : "${(api.applicant.districtCode)!}"
				},
				success : function(result){
					var arrDataRes = result.data;
					for (var i = 0; i < arrDataRes.length; i++) {
						arrDisplay.push({ value: arrDataRes[i].itemCode, text : arrDataRes[i].itemName});
					}
				},
				error : function(xhr){

				}
			});
			return arrDisplay;
		}
	});

	$('#phone').editable({
		url: updateProfileURL,
		emptytext : "-",
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		params: function(params) {
			return {
				contactTelNo: params.value
			};
		},
		validate: function(value) {
			// if (value.length < 1){
			//   return 'Đây là trường bắt buộc';
			// }
		},
		success: function(data) {
			
		},
		error:function(xhr) {
			if(xhr.status == 500) return 'Internal server error';
		}
	});

	$('#email').editable({
		url: updateProfileURL,
		emptytext : "-",
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		params: function(params) {
			return {
				contactEmail: params.value
			};
		},
		validate: function(value) {
			// if (value.length < 1){
			//   return 'Đây là trường bắt buộc';
			// }
		},
		success: function(response, newValue) {
			$("#profileEmail").html(newValue);

		},
		error:function(xhr) {
			if(xhr.status == 500) return 'Internal server error';
		}
	});
	
	$('#represntative').editable({
		url: 'post.php',
		emptytext : "",
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		success: function(data) {
			alert(data);
		},
		error:function(xhr) {
			if(xhr.status == 500) return 'Internal server error';
		}
	});

	$('#editApplicantName').click(function(e) {
		e.stopPropagation();
		$('#applicantName').editable('toggle');
	});

	$('#editAddress').click(function(e) {
		e.stopPropagation();
		$('#address').editable('toggle');
	});

	$('#editCity').click(function(e) {
		e.stopPropagation();
		$('#city').editable('toggle');
	});

	$('#editDistrict').click(function(e) {
		e.stopPropagation();
		$('#district').editable('toggle');
	});

	$('#editWards').click(function(e) {
		e.stopPropagation();
		$('#wards').editable('toggle');
	});

	$('#editPhone').click(function(e) {
		e.stopPropagation();
		$('#phone').editable('toggle');
	});

	$('#editEmail').click(function(e) {
		e.stopPropagation();
		$('#email').editable('toggle');
	});

	$('#editRepresntative').click(function(e) {
		e.stopPropagation();
		$('#represntative').editable('toggle');
	});

	var pullDetailProfile = function(id){
		$.ajax({
			url : "${api.server}/applicants/${(api.applicant.applicantId)!}",
			dataType : "json",
			type : "GET",
			headers: {"groupId": ${groupId}},
			success : function(result){
				var viewModel = kendo.observable({
					applicantName : function(){
						$('#applicantName').editable("setValue",result.applicantName); 
						return result.applicantName;
					},
					address : function(){
						$('#address').editable("setValue",result.address); 
						return result.address;
					},
					cityName : function(){
						$('#city').editable("setValue",result.cityCode); 
						return result.cityName;
					},
					districtName : function(){
						$('#district').editable("setValue",result.districtCode); 
						return result.districtName
					},
					wardName : function(){
						$('#wards').editable("setValue",result.wardCode); 
						return result.wardName
					},
					contactName : function(){
						$('#contactName').editable("setValue",result.contactName); 
						return result.contactName;
					},
					contactTelNo : function(){
						$('#phone').editable("setValue",result.contactTelNo); 
						return result.contactTelNo;
					},
					contactEmail : function(){
						$('#email').editable("setValue",result.contactEmail); 
						return result.contactEmail;
					},
					applicantIdDate : function(){
						return moment(result.applicantIdDate).format("DD/MM/YYYY");
					},
					applicantIdNo : result.applicantIdNo,
					userId : result.mappingUser.userId
				});

				kendo.bind($("#frmDetailAccount"), viewModel);
			},
			error : function(xhr){

			}
		});
	}
	pullDetailProfile();
</script>
