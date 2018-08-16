<#if (Request)??>
<#include "init.ftl">
</#if>

<#if employee?has_content>
<div role="tabpanel" class="tab-pane active" id="tttk">
<div class="detailEmployee MT20">
	<div class="row">
		<div class="col-sm-2">
			
		</div>
		<div class="col-sm-8 ">
			<div class="row MB10 MT20">	
				<div class="col-sm-4 PT5">
					<span>Tên người dùng </span> <span class="red">(*)</span>
				</div>
				<div class="col-sm-8">
					<input class="form-control input-sm" id="fullnameEmployee" name="fullnameEmployee" value="${(employee.fullName)!}">
				</div>
			</div>

			<div class="row MB10">
				<div class="col-sm-4 PT5">
					<span>Số điện thoại </span> <span class="red">(*)</span>
				</div>
				<div class="col-sm-8">
					<input class="form-control input-sm" id="telNoEmployee" name="telNoEmployee" data-bind="value: telNo" value="${(employee.telNo)!}">
				</div>
			</div>

			<div class="row MB10">
				<div class="col-sm-4 PT5">
					<span>Email </span>
				</div>
				<div class="col-sm-8">
					<input class="form-control input-sm" id="emailEmployee" name="emailEmployee" data-bind="value: email" value="${(employee.email)!}" >
				</div>
			</div>

			<div class="row MB10">
				<div class="col-sm-4 PT5">
					<span>Contact code </span> <span class="red">(*)</span>
				</div>
				<div class="col-sm-8">
					<input class="form-control input-sm" value="${(employee.employeeNo)!}">
				</div>
			</div>

			<div class="row MB10">
				<div class="col-sm-4 PT5">
					<span>Địa điểm ký số </span> <span class="red">(*)</span>
				</div>
				<div class="col-sm-8">
					<input class="form-control input-sm" value="Hà Nội">
				</div>
			</div>

			<div class="row MB10">
				<div class="col-sm-4 PT5">
					<span>Chức danh ký số </span> <span class="red">(*)</span>
				</div>
				<div class="col-sm-8">
					<input class="form-control input-sm" id="title" name="title" value="${(employee.title)!}" >
				</div>
			</div>

			<#-- <div class="row MB10">
				<div class="col-sm-4">
					<span>Vai trò trong nhóm </span>
				</div>
				<div class="col-sm-8">
					<input name="form-control" >
				</div>
			</div>
			
			<div class="row MB10">
				<div class="col-sm-4">
					<span>Thuộc đơn vị </span> 
				</div>
				<div class="col-sm-8">
					<input name="form-control" >
				</div>
			</div>
			
			<div class="row MB10">
				<div class="col-sm-4">
					<span>Chức vụ </span> 
				</div>
				<div class="col-sm-8">
					<input name="form-control" >
				</div>
			</div>
			
			<div class="row MB10">
				<div class="col-sm-4">
					<span>Nhóm </span> 
				</div>
				<div class="col-sm-8">
					<input name="form-control" >
				</div>
			</div> -->

			<div class="row MB10">
				<div class="col-sm-4 PT5">
					<span>Tài khoản đăng nhập </span> 
				</div>
				<div class="col-sm-8">
					<input class="form-control input-sm" value="${(employee.email)!}">
				</div>
			</div>

			<div class="row MB15 MT20">
				<div class="col-sm-4 PT5">
					<p class="MB20">File ảnh ký số </p>  
					File chứng thư gốc <span class="red">*</span>
				</div>
				<div class="col-sm-4">
					<input type="file" class="MB20" id="fileRegisNumber" name="fileRegisNumber">
					<input type="file" id="fileRoot" name="fileRoot">
				</div>
				<div class="col-sm-4">
					 <img id="previewRegis" class="img-rounded" src="#" alt="" style="width: 100%; height: 122px;" />
				</div>
			</div>

			<#-- <div class="row MB15">
				<table class="table table-bordered">
					<thead>
						<th>Lãnh đạo</th>
						<th>Xem dữ liệu</th>
						<th>Tên đội</th>
					</thead>
			
					<tbody>
						
					</tbody>
				</table>
			</div> -->


		</div>
		<div class="col-sm-2">
			
		</div>
	</div>
</div>
</div>
</#if>



<script type="text/javascript">
	/*var pullDetailProfile = function(id){
		$.ajax({
			url : "${api.server}/applicants/${(applicant.applicantId)!}",
			dataType : "json",
			type : "GET",
			headers: {"groupId": ${groupId}},
			success : function(result){
				var viewModel = kendo.observable({
					
				});

				kendo.bind($("#frmDetailAccount"), viewModel);
			},
			error : function(xhr){

			}
		});
	}*/

	$("#fileRegisNumber").change(function(){

		readURL(this);

		var data = new FormData();
		data.append( 'fileName', $(this)[0].files[0].name);
		data.append( 'fileType', $(this)[0].files[0].type);
		data.append( 'fileSize', $(this)[0].files[0].size);
		data.append( 'file', $(this)[0].files[0]);

		$.ajax({
			type : 'PUT', 
			url  : '${api.server}/users/${userId}/esign', 
			data : data,
			processData: false,
			contentType: false,
			cache: false,
			success :  function(result){ 
				notification.show({
					message: "Yêu cầu được thực hiện thành công."
				}, "success");
			},
			error:function(result){

				notification.show({
					message: "Yêu cầu không thành công, xin vui lòng thử lại."
				}, "error");
			}
		});
	});

	$("#fileRoot").change(function(){

		readURL(this);

		var data = new FormData();
		data.append( 'fileName', $(this)[0].files[0].name);
		data.append( 'fileType', $(this)[0].files[0].type);
		data.append( 'fileSize', $(this)[0].files[0].size);
		data.append( 'file', $(this)[0].files[0]);

		$.ajax({
			type : 'PUT', 
			url  : '${api.server}/users/${userId}/esigncert', 
			data : data,
			processData: false,
			contentType: false,
			cache: false,
			success :  function(result){ 
				notification.show({
					message: "Yêu cầu được thực hiện thành công."
				}, "success");
			},
			error:function(result){

				notification.show({
					message: "Yêu cầu không thành công, xin vui lòng thử lại."
				}, "error");
			}
		});
	});


	var readURL = function(input) {

		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#previewRegis').attr('src', e.target.result);
			}

			reader.readAsDataURL(input.files[0]);
		}
	}

	window.onload = function(){
		var urlReadFile = fileAttachmentUrl({
			method : "GET",
			url : "${api.server}/users/${userId}/esign",
			async : false,
			success: function(options){
				var urlOut = options.url;
				$('#previewRegis').attr('src', urlOut);

			},
			error: function(){}
		});
	}


	function fileAttachmentUrl ( options) {

		var xhttp = new XMLHttpRequest();
		var a,filename;
		var data = {};

		xhttp.onreadystatechange = function() {

			if (xhttp.readyState === 4 && xhttp.status === 200) {


				var disposition = xhttp.getResponseHeader('Content-Disposition');
				if (disposition && disposition.indexOf('attachment') !== -1) {
					var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
					var matches = filenameRegex.exec(disposition);
					if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');
				}


				a = document.createElement('a');
				a.href = window.URL.createObjectURL(xhttp.response);

				var url = window.URL.createObjectURL(xhttp.response);


				options.success({url : url, status : xhttp.status});
			} else if (xhttp.readyState === 4 && xhttp.status !== 200) {
				options.error(xhttp.status);
			}

		};

		xhttp.open(options.method, options.url);
		xhttp.setRequestHeader("Content-Type", "application/json");


		if (options.hasOwnProperty("headers")){
			Object.keys( options.headers ).map(function(objectKey, index) {
				var value = options.headers[objectKey];
				xhttp.setRequestHeader(objectKey, value);
			});
		}


		if (options.hasOwnProperty("responseType")){
			xhttp.responseType = options.responseType;
		} else {
			xhttp.responseType = 'blob';
		}


		if (options.hasOwnProperty("data")){
			data = options.data;
		}


		xhttp.send(data);

	};

</script>