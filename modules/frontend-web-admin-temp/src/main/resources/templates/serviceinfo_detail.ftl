<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="nav-tabs-wrapper">
	<ul class="nav nav-tabs" id="serviceinfo-tabstrip">
		<li class="active"  value="1">
			<a data-toggle="tab" href="#ttc">
				Thông tin chung
			</a>
		</li>
		<li class="" value="2">
			<a data-toggle="tab" href="#ttct">
				Thông tin chi tiết
			</a>
		</li>
		<li class="" value="3">
			<a data-toggle="tab" href="#bm">
				Biểu mẫu
			</a>
		</li>
	</ul>
	<div id="dataDetailServiceInfo" class="tab-content">
		<div id="ttc" class="tab-pane fade in active MT15">
			<form id="frmServiceinfoGenaral">
				<div class="row">
					<div class="col-sm-2">
						<label>Số hiệu <span class="red">*</span></label>
					</div>
					<div class="col-sm-10">
						<div class="form-group">
							<input type="text" name="serviceCode" id="serviceCode" class="form-control" placeholder="Số hiệu" data-bind="value:serviceCode" required="required" validationMessage="Bạn phải điền số hiệu">
							<span data-for="serviceCode" class="k-invalid-msg"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2 PR0 PL10">
						<label>Thủ tục hành chính <span class="red">*</span></label>
					</div>
					<div class="col-sm-10">
						<div class="form-group">
							<input type="text" name="serviceName" id="serviceName" class="form-control" placeholder="Tên thủ tục" data-bind="value:serviceName" required="required" validationMessage="Bạn phải điền thủ tục">
							<span data-for="serviceName" class="k-invalid-msg"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<div class="row">
							<div class="col-sm-4">
								<label>Cơ quan quản lý <span class="red">*</span></label>
							</div>
							<div class="col-sm-8">
								<select class="form-control" id="administration" name="administration" data-bind="value: administrationCode" required="required" validationMessage="Bạn phải chọn cơ quan">
									
									
								</select>
								<span data-for="administration" class="k-invalid-msg"></span>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="row">
							<div class="col-sm-4">
								<label>Lĩnh vực <span class="red">*</span></label>
							</div>
							<div class="col-sm-8">
								<select class="form-control" id="domain" name="domain" data-bind="value:domainCode" required="required" validationMessage="Bạn phải chọn lĩnh vực">
									
									
								</select>
								<span data-for="domain" class="k-invalid-msg"></span>
							</div>
						</div>
					</div>
				</div>
				<div class="row MT15">
					<div class="col-sm-6">
						<div class="row">
							<div class="col-sm-4">
								<label>Mức độ </label>
							</div>
							<div class="col-sm-8">
								<select class="form-control" id="level" name="level" data-bind="value: maxLevel" >
									<option value="2">Mức độ 2</option>
									<option value="3">Mức độ 3</option>
									<option value="4">Mức độ 4</option>
								</select>
								
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="row">
							<div class="col-sm-4">
								<label>Trạng thái </label>
							</div>
							<div class="col-sm-8">
								<select class="form-control" id="status" name="status" required="required" validationMessage="Bạn phải chọn trạng thái" data-bind="value:active">
									<option value="true">Công khai</option>
									<option value="false">Không công khai</option>
								</select>
								<span data-for="status" class="k-invalid-msg"></span>
							</div>
						</div>
					</div>
				</div>
				<div class="row MT15">
					<div class="col-sm-12 text-center">
						<button class="btn btn-active" id="btn-submit-serviceinfo-general" type="button">Ghi lại</button> <button class="btn" type="reset">Hủy bỏ</button>
					</div>
				</div>
			</form>
		</div>
		<div id="ttct" class="tab-pane fade in MT10">
			<form id="fmServiceinfoDetail">
				<label>Trình tự thực hiện</label>
				<div id="processText" name="processText" data-bind="text:processText"></div>

				<label class="MT15">Cách thức thực hiện</label>
				<div id="methodText" name="methodText" data-bind="text:methodText"></div>

				<label class="MT15">Thành phần hồ sơ</label>
				<div id="dossierText" name="dossierText" data-bind="text:dossierText"></div>

				<label class="MT15">Điều kiện thực hiện</label>
				<div id="conditionText" name="conditionText" data-bind="text:conditionText"></div>

				<label class="MT15">Thời gian giải quyết</label>
				<div id="durationText" name="durationText" data-bind="text:durationText"></div>

				<label class="MT15">Đối tượng thực hiện</label>
				<div id="applicantText" name="applicantText" data-bind="text:applicantText"></div>

				<label class="MT15">Lệ phí thủ tục</label>
				<div id="feeText" name="feeText" data-bind="text:feeText"></div>

				<label class="MT15">Kết quả thực hiện</label>
				<div id="resultText" name="resultText" data-bind="text:resultText"></div>

				<label class="MT15">Căn cứ pháp lý</label>
				<div id="regularText" name="regularText" data-bind="text:regularText"></div>

				<div class="col-sm-12 text-center MT10">
					<button class=" btn btn-active" id="btn-submit-serviceinfo-detail" type="button">Ghi lại</button> <button class=" btn" type="reset" id="btn-reset-serviceinfo-detail">Hủy bỏ</button>
				</div>
			</form>
		</div>
		<div id="bm" class="tab-pane fade in MT15">
			<button class="btn btn-active MB15" id="add-file-template">Tải biểu mẫu lên	</button>
			<ul class="ML10" id ="service_info_filetemplate" data-template="service_info_filetemplate_template" data-bind="source: fileTemplates">

			</ul>
			<script type="text/x-kendo-template" id="service_info_filetemplate_template">
				<li class="clearfix item-serviceinfo-filetemplate eq-height-lg" data-bind="attr: {data-pk : fileTemplateNo}" role="option" aria-selected="true">
					<div class="col-sm-4">
						<a data-bind="attr : { href: fileTemplateDownLoad}"><i class="fa fa-download" aria-hidden="true"></i> <span data-bind="text: templateName"></span>
						</a>
						<i class="fa fa-trash pull-right" data-bind="click: deleteFileTemplate"></i>
					</div>
				</li>
			</script>
		</div>
		<div id="serviceInfoFileTempalteDialog" class="modal fade serviceInfoFileTempalteDialog" role="dialog">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<a href="#" data-dismiss="modal" class="class pull-right">
							<span class="glyphicon glyphicon-remove"></span>
						</a>
						<h3 class="modal-title">Thêm biểu mẫu thủ tục hành chính</h3>
					</div>
					<div class="modal-body">
						<form id="serviceInfoFileTemplateForm">
							<div class="row">
								<div class="col-xs-12 col-sm-3">Số biểu mẫu</div>
								<div class="col-xs-12 col-sm-9">
									<input id="fileTemplateNo" name="fileTemplateNo" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="<#if (filetemplate.fileNo)??>${filetemplate.fileNo}</#if>"/>
								</div>
							</div>
							<div class="row MT10">
								<div class="col-xs-12 col-sm-3">Tên biểu mẫu</div>
								<div class="col-xs-12 col-sm-9">
									<input id="templateName" name="templateName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="<#if (filetemplate.fileName)??>${filetemplate.fileName}</#if>"/>
								</div>
							</div>
							<div class="row MT10">
								<div class="col-xs-12 col-sm-3">Tệp đính kèm</div>
								<div class="col-xs-12 col-sm-9">
									<input id="file" class="" name="file" type="file" accept="application/msword, application/vnd.ms-excel, application/vnd.ms-powerpoint,
									text/plain, application/pdf, image/*"/>
								</div>
							</div>
							<div class="row MT10 text-center">
								<button id="btnSaveFileTemplate" class="btn btn-active" type="button" data-bind="events:{ click: addFiletemplate }">Ghi lại</button>
								<button id="btnCancleFileTemplate" class="btn" title="Hủy bỏ" data-dismiss="modal">Đóng</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$("#processText").summernote({
		height: 150,
		toolbar: [
		['style', ['bold', 'italic', 'underline', 'clear']],
		['font', ['strikethrough', 'superscript', 'subscript']],
		['fontname', ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New']],
		['fontsize', ['fontsize']],
		['color', ['color']],
		['para', ['ul', 'ol', 'paragraph']],
		['height', ['height']],
		['table', ['table']],
		['insert', ['link', 'picture' , 'video']]
		]
	});
	$("#methodText").summernote({
		height: 150,
		toolbar: [
		['style', ['bold', 'italic', 'underline', 'clear']],
		['font', ['strikethrough', 'superscript', 'subscript']],
		['fontname', ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New']],
		['fontsize', ['fontsize']],
		['color', ['color']],
		['para', ['ul', 'ol', 'paragraph']],
		['height', ['height']],
		['table', ['table']],
		['insert', ['link', 'picture' , 'video']]
		]
	});
	$("#dossierText").summernote({
		height: 150,
		toolbar: [
		['style', ['bold', 'italic', 'underline', 'clear']],
		['font', ['strikethrough', 'superscript', 'subscript']],
		['fontname', ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New']],
		['fontsize', ['fontsize']],
		['color', ['color']],
		['para', ['ul', 'ol', 'paragraph']],
		['height', ['height']],
		['table', ['table']],
		['insert', ['link', 'picture' , 'video']]
		]
	});
	$("#conditionText").summernote({
		height: 150,
		toolbar: [
		['style', ['bold', 'italic', 'underline', 'clear']],
		['font', ['strikethrough', 'superscript', 'subscript']],
		['fontname', ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New']],
		['fontsize', ['fontsize']],
		['color', ['color']],
		['para', ['ul', 'ol', 'paragraph']],
		['height', ['height']],
		['table', ['table']],
		['insert', ['link', 'picture' , 'video']]
		]
	});
	$("#durationText").summernote({
		height: 150,
		toolbar: [
		['style', ['bold', 'italic', 'underline', 'clear']],
		['font', ['strikethrough', 'superscript', 'subscript']],
		['fontname', ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New']],
		['fontsize', ['fontsize']],
		['color', ['color']],
		['para', ['ul', 'ol', 'paragraph']],
		['height', ['height']],
		['table', ['table']],
		['insert', ['link', 'picture' , 'video']]
		]
	});
	$("#applicantText").summernote({
		height: 150,
		toolbar: [
		['style', ['bold', 'italic', 'underline', 'clear']],
		['font', ['strikethrough', 'superscript', 'subscript']],
		['fontname', ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New']],
		['fontsize', ['fontsize']],
		['color', ['color']],
		['para', ['ul', 'ol', 'paragraph']],
		['height', ['height']],
		['table', ['table']],
		['insert', ['link', 'picture' , 'video']]
		]
	});
	$("#feeText").summernote({
		height: 150,
		toolbar: [
		['style', ['bold', 'italic', 'underline', 'clear']],
		['font', ['strikethrough', 'superscript', 'subscript']],
		['fontname', ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New']],
		['fontsize', ['fontsize']],
		['color', ['color']],
		['para', ['ul', 'ol', 'paragraph']],
		['height', ['height']],
		['table', ['table']],
		['insert', ['link', 'picture' , 'video']]
		]
	});
	$("#resultText").summernote({
		height: 150,
		toolbar: [
		['style', ['bold', 'italic', 'underline', 'clear']],
		['font', ['strikethrough', 'superscript', 'subscript']],
		['fontname', ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New']],
		['fontsize', ['fontsize']],
		['color', ['color']],
		['para', ['ul', 'ol', 'paragraph']],
		['height', ['height']],
		['table', ['table']],
		['insert', ['link', 'picture' , 'video']]
		]
	});
	$("#regularText").summernote({
		height: 150,
		toolbar: [
		['style', ['bold', 'italic', 'underline', 'clear']],
		['font', ['strikethrough', 'superscript', 'subscript']],
		['fontname', ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New']],
		['fontsize', ['fontsize']],
		['color', ['color']],
		['para', ['ul', 'ol', 'paragraph']],
		['height', ['height']],
		['table', ['table']],
		['insert', ['link', 'picture' , 'video']]
		]
	});

	$("#administration").kendoComboBox({
		placeholder : "Chọn cơ quan quản lý",
		dataTextField : "itemName",
		dataValueField : "itemCode",
		dataSource : {
			transport : {
				read : {
					url : "${api.server}/dictcollections/SERVICE_ADMINISTRATION/dictitems",
					dataType : "json",
					type : "GET",
					headers: {"groupId": ${groupId}},
					success : function(result){

					},
					error : function(xhr){

					}
				}
			},
			schema: {
				data : "data",
				total : "total"
			}
		},
		dataBound : function(e){
			$(".k-clear-value").addClass("k-hidden");
		},
		noDataTemplate: 'Không có dữ liệu'
	});
	$("#domain").kendoComboBox({
		placeholder : "Chọn lĩnh vực thủ tục",
		dataTextField : "itemName",
		dataValueField : "itemCode",
		dataSource : {
			transport : {
				read : {
					url : "${api.server}/dictcollections/SERVICE_DOMAIN/dictitems",
					dataType : "json",
					type : "GET",
					headers: {"groupId": ${groupId}},
					success : function(result){

					},
					error : function(xhr){

					}
				}
			},
			schema: {
				data : "data",
				total : "total"
			}
		},
		dataBound : function(e){
			$(".k-clear-value").addClass("k-hidden");
		},
		noDataTemplate: 'Không có dữ liệu'
	});
	$("#level").kendoComboBox({
		placeholder : "Chọn mức độ",
		noDataTemplate: 'Không có dữ liệu'
	});

	$("#status").kendoComboBox({
		placeholder : "Chọn trạng thái",
		noDataTemplate: 'Không có dữ liệu'
	});

	var pullDataDetail = function(id){
		$.ajax({
			url : "${api.server}/serviceinfos/"+id,
			dataType : "json",
			type : "GET",
			success : function(result){
				var arrFile = result.fileTemplates;
				if(!arrFile){
					arrFile = [];
				}
				var viewModel = kendo.observable({
					addFiletemplate : function(e){
						var that = this;
						var idServiceinfo = $("#itemServiceInfoId").val();
						
						if(idServiceinfo>0){
							var form = $('#serviceInfoFileTemplateForm')[0];
							var data = new FormData(form);
							var file = document.getElementById("file").files[0];
							data.append( 'fileType', file.type );
							data.append( 'fileName', file.name );
							data.append( 'fileSize', file.size );
							console.log(file);
							$.ajax({
								url : "${api.server}/serviceinfos/"+idServiceinfo+"/filetemplates",
								dataType : "json",
								type : "POST",
								data : data,
								headers: {"groupId": ${groupId}},
								processData: false,
								contentType: false,
								cache: false,
								async : false,
								success : function(result){
									notification.show({
										message: "Yêu cầu được thực hiện thành công"
									}, "success");

									if(that.get("fileTemplates")){
										if(!that.get("fileTemplates")[0]){
											var fileObj = that.get("fileTemplates");
											if(typeof fileObj.length === 'undefined'){
												var arr = new Array();
												arr.push(fileObj);
												that.set("fileTemplates",arr);
											}
										}
									}else{
										that.set("fileTemplates",[]);
									}
									that.get("fileTemplates").push({
										fileTemplateNo: result.fileTemplateNo,
										templateName: result.templateName
									});
									$("#serviceInfoFileTempalteDialog").modal('hide');

									//clear thong tin file vua chon
									$("#fileTemplateNo").val('');
									$("#templateName").val('');
									$("#file").val('');

								},
								error : function(result){
									if (result.responseJSON.description == "No Content."){
										notification.show({
											message: "Thêm không thành công do số biểu mẫu bị trùng."
										}, "error");
									} else {
										notification.show({
											message: "Xẩy ra lỗi, vui lòng thử lại"
										}, "error");
									}
								}
							});
						}else {

						}
					},
					fileTemplateDownLoad : function(e){
						var serviceInfoId = $("#itemServiceInfoId").val();
						return '${api.server}/serviceinfos/'+serviceInfoId+'/filetemplates/'+e.fileTemplateNo
					},
					serviceinfoId: result.serviceInfoId,
					serviceName: result.serviceName,
					serviceCode: result.serviceCode,
					administrationName: result.administrationName,
					domainName: result.domainName,
					administrationCode: result.administrationCode,
					domainCode: result.domainCode,
					resultText: function(e){
						$('#resultText').summernote('code', result.resultText);
					},
					feeText: function(e){
						$('#feeText').summernote('code', result.feeText);
					},
					methodText : function(e){
						$('#methodText').summernote('code', result.methodText);
					},
					durationText : function(e){
						$('#durationText').summernote('code', result.durationText);
					},
					dossierText : function(e){
						$('#dossierText').summernote('code', result.dossierText);
					},
					processText : function(e){
						$('#processText').summernote('code', result.processText);
					},
					applicantText : function(e){
						$('#applicantText').summernote('code', result.applicantText);
					},
					regularText : function(e){
						$('#regularText').summernote('code', result.regularText);
					},
					conditionText : function(e){
						$('#conditionText').summernote('code', result.conditionText);
					},
					fileTemplates : arrFile,
					maxLevel: result.maxLevel,
					active : result.active,
					deleteFileTemplate : function(e){
						var that = this;
						var fileTemplates = this.get("fileTemplates");
						var idFileTemplate = e.data.fileTemplateNo;
						var idServiceinfo = $("#itemServiceInfoId").val();
						console.log(e.data.fileTemplateNo);
						if(idFileTemplate && idServiceinfo>0){
							var cf = confirm("Bạn có muốn xóa file "+e.data.templateName+"!");
							if (cf == true) {
								$.ajax({
									url : "${api.server}/serviceinfos/"+idServiceinfo+"/filetemplates/"+idFileTemplate,
									type : "DELETE",
									dataType : "json",
									headers: {"groupId": ${groupId}},
									async : false,
									data : {
										filetemplates : idFileTemplate
									},
									success : function(result){
										
										var fileDelete = e.data;
										if(fileTemplates[0]){
											var index = fileTemplates.indexOf(fileDelete);
											fileTemplates.splice(index, 1);
										}else{
											that.set("fileTemplates", []);
										}
										
										notification.show({
											message: "Yêu cầu được thực hiện thành công"
										}, "success");

									},
									error : function(xhr){
										notification.show({
											message: "Xẩy ra lỗi, vui lòng thử lại"
										}, "error");
									}
								});
							} else {
								return;
							}

						}
					}
				});

kendo.bind($("#dataDetailServiceInfo"), viewModel);
},
error : function(xhr){

}
});
}

var formControlFileTemplate = function(dataPk){

	var url = "${ajax.serviceinfo_filetemplate_form}";

	$("#serviceInfoFileTempalteDialog").load(
		url,
		function(result){

			$("#serviceInfoFileTempalteDialog").modal({show: true});

			$("#btnCancleServiceConfigOption").click(function(e){
				e.preventDefault();
				$("#serviceInfoFileTempalteDialog").modal("hide");
			});
		}
		);
}

var addServieInfoFileTemplate=function(){
	dataSourceFileTemplate.transport.create({
		"serviceinfoId":$("#itemServiceInfoId").val(),
		"fileNo":$("#fileNo").val(),
		"fileName":$("#fileName").val(),
		"file": $('input#file')[0].files[0]
	});
}

var addFileTemplateIfSuccess=function(result){
	dataSourceFileTemplate.add({
		"fileTemplateNo":result.fileTemplateNo,
		"templateName":result.templateName
	});
}

$("#btnAddServiceInfoFile").click(function(){
	$("#serviceInfoFileTempalteDialog").modal({show: true});
});

$(document).on("click",".btn-delete-filetemplate",function(event){
	var idFileTemplate = $(this).attr("data-pk");
	console.log(idFileTemplate);
});

$("#btn-submit-serviceinfo-detail").click(function(){
	var idServiceinfo = $("#itemServiceInfoId").val();
	if(idServiceinfo>0){
		console.log($("#processText").summernote("code"));
		console.log($("#methodText").summernote("code"));
		$.ajax({
			url : "${api.server}/serviceinfos/"+idServiceinfo,
			type : "PUT",
			dataType : "json",
			headers: {"groupId": ${groupId}},
			data : {
				serviceCode : $.trim($("#serviceCode").val()),
				serviceName : $.trim($("#serviceName").val()),
				administrationCode : $.trim($("#administration").val()),
				domainCode : $.trim($("#domain").val()),
				maxLevel : ($("#level").val()) ? $("#level").val() : 2,
				active : $.trim($("#status").val()),

				resultText : $("#resultText").summernote("code").toString(),
				feeText : $("#feeText").summernote("code").toString(),
				methodText : $("#methodText").summernote("code").toString(),
				durationText : $("#durationText").summernote("code").toString(),
				dossierText : $("#dossierText").summernote("code").toString(),
				processText : $("#processText").summernote("code").toString(),
				applicantText : $("#applicantText").summernote("code").toString(),
				regularText : $("#regularText").summernote("code").toString(),
				conditionText : $("#conditionText").summernote("code").toString()
			},
			success : function(result){
				updateServieInfoIfSuccess(idServiceinfo,result);
				notification.show({
					message: "Yêu cầu được thực hiện thành công"
				}, "success");

				setTimeout(function(){
					$("#listViewTTHC li[data-pk=" + idServiceinfo + "]").addClass("k-state-selected");
				}, 100);
			},
			error : function(result){
				if (result.responseJSON.description == "DuplicateServiceCodeException"){
					notification.show({
						message: "Sửa không thành công do mã thủ tục bị trùng."
					}, "error");
				} else {
					notification.show({
						message: "Xẩy ra lỗi, vui lòng thử lại"
					}, "error");
				}
			}
		});
	}else{
		console.log($("#processText").summernote("code"));
		console.log($("#methodText").summernote("code"));
		$.ajax({
			url : "${api.server}/serviceinfos",
			type : "POST",
			dataType : "json",
			headers: {"groupId": ${groupId}},
			data : {
				serviceCode : $.trim($("#serviceCode").val()),
				serviceName : $.trim($("#serviceName").val()),
				administrationCode : $.trim($("#administration").val()),
				domainCode : $.trim($("#domain").val()),
				maxLevel : ($("#level").val()) ? $("#level").val() : 2,
				active : $.trim($("#status").val()),

				resultText : $("#resultText").summernote("code").toString(),
				feeText : $("#feeText").summernote("code").toString(),
				methodText : $("#methodText").summernote("code").toString(),
				durationText : $("#durationText").summernote("code").toString(),
				dossierText : $("#dossierText").summernote("code").toString(),
				processText : $("#processText").summernote("code").toString(),
				applicantText : $("#applicantText").summernote("code").toString(),
				regularText : $("#regularText").summernote("code").toString(),
				conditionText : $("#conditionText").summernote("code").toString()
			},
			success : function(result){
				$("#itemServiceInfoId").val(result.serviceInfoId);
				crtAddOrEdit();
				addServiceInfoIfSuccess(result);
				notification.show({
					message: "Yêu cầu được thực hiện thành công"
				}, "success");

				setTimeout(function(){
					$("#listViewTTHC li[data-pk=" + result.serviceInfoId + "]").addClass("k-state-selected");
				}, 100);
			},
			error : function(result){

				if (result.responseJSON.description == "DuplicateServiceCodeException"){
					notification.show({
						message: "Thêm không thành công do mã thủ tục bị trùng."
					}, "error");
				} else {
					notification.show({
						message: "Xẩy ra lỗi, vui lòng thử lại"
					}, "error");
				}
			}
		});
	}

});

$("#btn-submit-serviceinfo-general").click(function(){
	var idServiceinfo = $("#itemServiceInfoId").val();
	var validator = $("#frmServiceinfoGenaral").kendoValidator().data("kendoValidator");
	if(validator.validate()){
		if(idServiceinfo>0){
			var data = $("#frmServiceinfoGenaral").serialize();
			$.ajax({
				url : "${api.server}/serviceinfos/"+idServiceinfo,
				type : "PUT",
				dataType : "json",
				headers: {"groupId": ${groupId}},
				data : {
					serviceCode : $.trim($("#serviceCode").val()),
					serviceName : $.trim($("#serviceName").val()),
					administrationCode : $.trim($("#administration").val()),
					domainCode : $.trim($("#domain").val()),
					maxLevel : ($("#level").val()) ? $("#level").val() : 2,
					active : $.trim($("#status").val())
				},
				success : function(result){
					updateServieInfoIfSuccess(idServiceinfo,result);
					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");

					setTimeout(function(){
						$("#listViewTTHC li[data-pk=" + idServiceinfo + "]").addClass("k-state-selected");
					}, 100);
				},
				error : function(result){
					if (result.responseJSON){

						if(result.responseJSON.description == "DuplicateServiceCodeException"){
							notification.show({
								message: "Thêm không thành công do mã hồ sơ bị trùng."
							}, "error");
						}
						

					} else {

						notification.show({
							message: "Phải nhập gía trị!"
						}, "error");
					}
				}
			});
		}else{
			var data = $("#frmServiceinfoGenaral").serialize();
			$.ajax({
				url : "${api.server}/serviceinfos",
				type : "POST",
				dataType : "json",
				headers: {"groupId": ${groupId}},
				data : {
					serviceCode : $.trim($("#serviceCode").val()),
					serviceName : $.trim($("#serviceName").val()),
					administrationCode : $.trim($("#administration").val()),
					domainCode : $.trim($("#domain").val()),
					maxLevel : ($("#level").val()) ? $("#level").val() : 2,
					active : $.trim($("#status").val())
				},
				success : function(result){
					
					$("#itemServiceInfoId").val(result.serviceInfoId);
					addServiceInfoIfSuccess(result);
					crtAddOrEdit();

					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");
					pullDataDetail(result.serviceInfoId);

					setTimeout(function(){
						$("#listViewTTHC li[data-pk=" + result.serviceInfoId + "]").addClass("k-state-selected");
					}, 100);
				},
				error : function(result){
					if (result.responseJSON){

						if(result.responseJSON.description == "DuplicateServiceCodeException"){
							notification.show({
								message: "Thêm không thành công do mã hồ sơ bị trùng."
							}, "error");
						}


					} else {

						notification.show({
							message: "Phải nhập gía trị!"
						}, "error");
					}
				}
			});
		}
	}

});

var addServiceInfoIfSuccess=function(result){
	dataSourceTTHC.insert(0,{
		"serviceInfoId": result.serviceInfoId,
		"serviceCode":result.serviceCode,
		"serviceName":result.serviceName,
		"processText":result.processText,
		"methodText":result.methodText,
		"dossierText":result.dossierText,
		"conditionText":result.conditionText,
		"durationText":result.durationText,
		"resultText":result.resultText,
		"administrationCode":result.administrationCode,
		"domainCode":result.domainCode,
		"activeStatus":result.activeStatus,
		"administrationName":result.administrationName,
		"domainName":result.domainName,
		"fileTemplates":result.fileTemplates,
		"active" : result.active
	});
}

var updateServieInfoIfSuccess = function(dataPk,result){
	dataSourceTTHC.fetch(function() {
		var item = dataSourceTTHC.get(dataPk);
		item.set("serviceCode",result.serviceCode);
		item.set("serviceName",result.serviceName);
		item.set("processText",result.processText);
		item.set("methodText",result.methodText);
		item.set("dossierText",result.dossierText);
		item.set("conditionText",result.conditionText);
		item.set("durationText",result.durationText);
		item.set("resultText",result.resultText);
		item.set("administrationCode",result.administrationCode);
		item.set("domainCode",result.domainCode);
		item.set("activeStatus",result.activeStatus);
		item.set("administrationName",result.administrationName);
		item.set("domainName",result.domainName);
		item.set("active",result.active);
	});
}

$("#add-file-template").click(function(){
	$("#serviceInfoFileTempalteDialog").modal({show: true});
});

$("#btn-reset-serviceinfo-detail").click(function(){
	$(' #processText ').summernote('code', '');
	$(' #methodText ').summernote('code', '');
	$(' #dossierText ').summernote('code', '');
	$(' #conditionText ').summernote('code', '');
	$(' #durationText ').summernote('code', '');
	$(' #applicantText').summernote('code', '');
	$(' #feeText ').summernote('code', '');
	$(' #resultText ').summernote('code', '');
	$(' #regularText').summernote('code', '');
	
	$('#processText').trigger('focus');
});

</script>
