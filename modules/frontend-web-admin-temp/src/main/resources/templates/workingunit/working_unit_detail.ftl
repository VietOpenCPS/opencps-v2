<#include "init.ftl">
<div class="row panel">

	<h3 class="detail-header MT5">
	
		<#if (param.workingUnit_workingUnitId??) && (param.workingUnit_workingUnitId?number > 0) >
			<span> Chi tiết phòng ban</span>
		<#else>
			<span> Thêm phòng ban</span>
		</#if>
	
	</h3>
	
	<div class="panel-body">
	
		<form id="_workingUnitDetail_form">

			<div class="row">
				
				<div class="col-sm-6">
					
					<input type="file" id="_workingUnitDetail_logoFileEntryId" accept="image/*"  onchange="_workingUnitDetail_changeLogoFileEntry(this)" style="display: none;" />
 					
					<img id="_workingUnitDetail_logo_thumbnil" class="img-responsive center-block" style="max-width: 368px; max-height: 120px;"  src="/o/orgopencpsfrontendadmin/images/default-thumbnail.jpg" alt=""/>

					<button id="_workingUnitDetail_change_logo_btn" class="btn btn-active btn-block MB15">Thay đổi logo</button>

				</div>

				<div class="col-sm-6">
					
					<!-- _workingUnitDetail_govAgencyCode -->
					
					<div class="form-group">
					
						<label for="_workingUnitDetail_govAgencyCode">Mã phòng ban 
						
							<span class="icon-asterisk text-warning"></span>
						
						</label>
						
						<input type="text" id="_workingUnitDetail_govAgencyCode" name="_workingUnitDetail_govAgencyCode" 
							class="form-control" placeholder="Mã phòng ban" value="${(workingUnit.govAgencyCode)!}"
							required validationMessage="Nhập Mã phòng ban"  />
			
					</div>
					
					<!-- _workingUnitDetail_name -->
					
					<div class="form-group" >
					
						<label for="_workingUnitDetail_name">Tên phòng ban 
						
							<span class="icon-asterisk text-warning"></span>
						
						</label>
						<input type="text" id="_workingUnitDetail_name" name="_workingUnitDetail_name" class="form-control"
							placeholder="Tên phòng ban " value="${(workingUnit.name)!}"
							required validationMessage="Nhập Tên phòng ban "  />
					
					</div>

				</div>

			</div>

			<div class="row">
			
				<!-- _workingUnitDetail_enName -->
				
				<div class="form-group col-sm-6" >
				
					<label for="_workingUnitDetail_enName">Tên tiếng Anh :
					</label>
					
					<input type="text" id="_workingUnitDetail_enName" name="_workingUnitDetail_enName" class="form-control"
						placeholder="Tên tiếng Anh" value="${(workingUnit.enName)!}" />
				
				</div>

				<!-- parentWorkingUnitId -->
				
				<div class="form-group col-sm-6">
				
					<label for="_workingUnitDetail_parentWorkingUnitId">Phòng ban cấp trên :</label>
					<input id="_workingUnitDetail_parentWorkingUnitId" name="_workingUnitDetail_parentWorkingUnitId" class="form-control"
						value="${(workingUnit.parentWorkingUnitId)!}" />
				
				</div>

				<!-- _workingUnitDetail_enName -->
				
			</div>

			<div class="row">
				
				<div class="form-group col-sm-6" >
				
					<label for="_workingUnitDetail_ceremonyDate">Ngày  thành lập :
					</label>
					
					<input type="text" id="_workingUnitDetail_ceremonyDate" name="_workingUnitDetail_ceremonyDate" class="form-control"
						placeholder="Ngày  thành lập" value="${(workingUnit.ceremonyDate)!}" />
				
				</div>
				
				<!-- _workingUnitDetail_address -->
				
				<div class="form-group col-sm-6" >
				
					<label for="_workingUnitDetail_address">Địa chỉ :</label>
					<input type="text" id="_workingUnitDetail_address" name="_workingUnitDetail_address" class="form-control"
						placeholder="Địa chỉ " value="${(workingUnit.address)!}"  />
					
				</div>
				
			</div>

			<div class="row">
			
				<!-- _workingUnitDetail_telNo -->
				
				<div class="form-group col-sm-6" >
				
					<label for="_workingUnitDetail_telNo">Số điện thoại :</label>
					<input type="text" id="_workingUnitDetail_telNo" name="_workingUnitDetail_telNo" class="form-control"
						placeholder="Số điện thoại" value="${(workingUnit.telNo)!}"
						pattern="^[0-9]{10,11}" validationMessage="Nhập Số điện thoại 10 hoặc 11 số"  />
				
				</div>
				
				<!-- _workingUnitDetail_faxNo -->
				
				<div class="form-group col-sm-6">
				
					<label for="_workingUnitDetail_faxNo">Số fax :</label>
					<input type="text" class="form-control" id="_workingUnitDetail_faxNo" 
						placeholder="Số fax" value="${(workingUnit.faxNo)!}">
				
				</div>
				
			</div>

			<div class="row">
				<!-- _workingUnitDetail_email -->
				
				<div class="form-group col-sm-6" >
				
					<label for="_workingUnitDetail_email">Email :</label>
					<input type="text" id="_workingUnitDetail_email" name="_workingUnitDetail_email" class="form-control"
						placeholder="contact@email.com" value="${(workingUnit.email)!}"
						pattern="[A-Za-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" validationMessage="Nhập Email"  />
				
				</div>
				
				<!-- _workingUnitDetail_website -->
				
				<div class="form-group col-sm-6" >
				
					<label for="_workingUnitDetail_website">Website :</label>
					<input type="text" id="_workingUnitDetail_website" name="_workingUnitDetail_website" class="form-control"
						placeholder="Website" value="${(workingUnit.website)!}"  />
				
				</div>
				
			</div>

			<div class="row">
				
				<!-- _workingUnitDetail_sibling -->
				
				<div class="form-group col-sm-6" >
				
					<label for="_workingUnitDetail_sibling">Số thứ tự cùng cấp
					</label>
					
					<input type="number" id="_workingUnitDetail_sibling" name="_workingUnitDetail_sibling" class="form-control"
						placeholder="Số thứ tự cùng cấp" value="${(workingUnit.sibling)!}" />
				
				</div>
					
				<div class="col-xs-6 col-sm-6 text-right PT20">

					<button class="btn btn-active btn-default" id="_workingUnitDetail_submitBtn" 
						name="_workingUnitDetail_submitBtn" type="button" data-pk="${(param.workingUnit_workingUnitId)!}"
						data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang lưu thông tin...">
						<i class="fa fa-check-circle"></i>
						<span class="lfr-btn-label">Xác nhận</span>
					</button>
				
				</div>

			</div>
		
		</form>

	</div>

</div>


<script type="text/javascript">

(function($) {
	
	var _workingUnitDetail_BaseUrl = "${api.endpoint}/workingunits";

	var _workingUnitDetail_dataSource = new kendo.data.DataSource({
		
		transport: {

			read: function(options) {
				
				$.ajax({
				
					url: _workingUnitDetail_BaseUrl,
					dataType: "json",
					type: 'GET',
					headers: {
						"groupId": ${groupId}
					},
					data: {
						sort: 'name'
					},
					success: function(result) {
						
						result["data"] = result.total==0 ? []: result["data"];
						options.success(result);
						
					},
					error: function(xhr, textStatus, errorThrown) {
						
						showMessageByAPICode(xhr.status);
					
					}
				
				});
			}
		},
		schema: {
			data: "data",
			total: "total",
			model: {
			id: "workingUnitId",
			fields: {
				workingUnitId: {
					editable: false,
					nullable: true
				},
				name: { type: "string" },
				}
			}
		},
		filter: [
			{ field: "workingUnitId", operator: "neq", value: $("#_workingUnitDetail_submitBtn").attr('data-pk') }
		]
		
	});
	
	var _workingUnitDetail_parentWorkingUnitId = $("#_workingUnitDetail_parentWorkingUnitId").kendoDropDownList({
		
		optionLabel: "Không có phòng ban cấp trên",
		dataTextField: "name",
		dataValueField: "workingUnitId",
		dataSource: _workingUnitDetail_dataSource
		
	}).data("kendoDropDownList");
	
	_workingUnitDetail_parentWorkingUnitId.value($( "#_workingUnitDetail_parentWorkingUnitId" ).val().trim());
	
	$(document).on('click', '#_workingUnitDetail_submitBtn', function(event){
		
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();
		
		var _workingUnitDetail_form = $("#_workingUnitDetail_form").kendoValidator().data("kendoValidator");

		if (!_workingUnitDetail_form.validate()) {
			return;
		}
		
		var _parentWorkingUnitId_val = $("#_workingUnitDetail_parentWorkingUnitId").val().trim();
		var _workingUnitDetail_sibling_val = $( "#_workingUnitDetail_sibling" ).val().trim();
		
		_parentWorkingUnitId_val = (_parentWorkingUnitId_val==null || _parentWorkingUnitId_val=="")?0:_parentWorkingUnitId_val;
		_workingUnitDetail_sibling_val = (_workingUnitDetail_sibling_val==null || _workingUnitDetail_sibling_val=="")?0:_workingUnitDetail_sibling_val;	
		
		var workingUnitId = $(this).attr('data-pk');
		var ceremonyDate = $("#_workingUnitDetail_ceremonyDate").data("kendoDatePicker").value();

		ceremonyDate = (ceremonyDate!=null )?kendo.toString(ceremonyDate, 'yyyyMMdd'):"";
		
		if (workingUnitId!=null && workingUnitId !="" && workingUnitId > 0) {
			
			// create a new working unit
			$("#_workingUnit_listView").getKendoListView().dataSource.transport.update({
				
				workingUnitId: workingUnitId,
				parentWorkingUnitId: _parentWorkingUnitId_val,
				govAgencyCode: $( "#_workingUnitDetail_govAgencyCode" ).val().trim(),
				name: $( "#_workingUnitDetail_name" ).val().trim(),
				enName: $( "#_workingUnitDetail_enName" ).val().trim(),
				address: $( "#_workingUnitDetail_address" ).val().trim(),
				telNo: $( "#_workingUnitDetail_telNo" ).val().trim(),
				faxNo: $( "#_workingUnitDetail_faxNo" ).val().trim(),
				email: $( "#_workingUnitDetail_email" ).val().trim(),
				website: $( "#_workingUnitDetail_website" ).val().trim(),
				sibling: _workingUnitDetail_sibling_val,
				ceremonyDate: ceremonyDate
				
			});

		} else {

			$("#_workingUnit_listView").getKendoListView().dataSource.add({
				
				parentWorkingUnitId: _parentWorkingUnitId_val,
				govAgencyCode: $( "#_workingUnitDetail_govAgencyCode" ).val().trim(),
				name: $( "#_workingUnitDetail_name" ).val().trim(),
				enName: $( "#_workingUnitDetail_enName" ).val().trim(),
				address: $( "#_workingUnitDetail_address" ).val().trim(),
				telNo: $( "#_workingUnitDetail_telNo" ).val().trim(),
				faxNo: $( "#_workingUnitDetail_faxNo" ).val().trim(),
				email: $( "#_workingUnitDetail_email" ).val().trim(),
				website: $( "#_workingUnitDetail_website" ).val().trim(),
				sibling: _workingUnitDetail_sibling_val,
				ceremonyDate: ceremonyDate,
				level: 0
				
			});

			// save the created working unit
			$("#_workingUnit_listView").getKendoListView().dataSource.sync(); 

		}
		
	});

	$("#_workingUnitDetail_ceremonyDate").kendoDatePicker({
		start: "month",
		depth: "year",
		format: "dd/MM/yyyy",
		dateInput: false
	});

})(jQuery);

</script>

<script type="text/javascript">

var _workingUnitDetail_uploadLogo_BaseUrl = "${api.endpoint}/workingunits";

function _workingUnitDetail_changeLogoFileEntry(fileInput) {
	
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
			
			var img=document.getElementById("_workingUnitDetail_logo_thumbnil");
			img.file = file;
			var reader = new FileReader();
			
			reader.onload = (function(aImg) { 
			
				return function(e) { 
					aImg.src = e.target.result;
				}; 

			})(img);
			
			reader.readAsDataURL(file);
			
		}

	}

}

function _workingUnitDetail_uploadLogoFileEntry(fileInput, workingUnitId) {
	
	var files = fileInput.files;

	if (files.length > 0) {

		//show client
		for (var i = 0; i < files.length; i++) {
		
			var file = files[i];

			// call ajax
			
			var fileName= file.name;
			var fileType= file.type;
			var fileSize= file.size;
			var className= "${(constant.workingUnit_workingUnitClassName)!}";
			var classPK= workingUnitId;
			var formData = new FormData();

			formData.append('file', file);
			formData.append('fileName', fileName);
			formData.append('fileType', fileType);
			formData.append('fileSize', fileSize);
			formData.append('className', className);
			formData.append('classPK', classPK);

			$.ajax({
						
				url: _workingUnitDetail_uploadLogo_BaseUrl+"/"+workingUnitId+"/logo",
				
				type: 'PUT',
				headers: {
					"groupId": ${groupId}
				},
				async: false,
				contentType: false,
				processData: false, 
				data: formData,
				success: function(result) {
				
					//showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
					
				},
				error: function(xhr, textStatus, errorThrown) {
				
					showMessageByAPICode(xhr.status);
			
				}
			
			});
			
		}

	}

}


(function($) {

	$(document).on('click', '#_workingUnitDetail_change_logo_btn', function(event){
			
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();
		
		$("#_workingUnitDetail_logoFileEntryId").trigger({ type: "click" });
		
	});

	var workingUnitId = $("#_workingUnitDetail_submitBtn").attr('data-pk');

	if (workingUnitId!=null && workingUnitId!="" && workingUnitId > 0 ) {

		/*$.ajax({
						
			url: _workingUnitDetail_uploadLogo_BaseUrl+"/"+workingUnitId+"/logo",
			
			type: 'GET',
			headers: {
				"groupId": ${groupId}
			},
			async: false,
			contentType: false,
			processData: false,
			success: function(result) {
				
				$("#_workingUnitDetail_logo_thumbnil").attr("src","data:image/gif;base64," + result);
				
			},
			error: function(xhr, textStatus, errorThrown) {
				
				//showMessageByAPICode(xhr.status);
		
			}
		
		});*/
		getImageBlob(_workingUnitDetail_uploadLogo_BaseUrl+"/"+workingUnitId+"/logo", $("#_workingUnitDetail_logo_thumbnil"), "${groupId}");
	}
	

})(jQuery);

</script>