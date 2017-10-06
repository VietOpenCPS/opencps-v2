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
						<label>Số hiệu:</label>
					</div>
					<div class="col-sm-10">
						<div class="form-group">
							<input type="text" name="serviceCode" id="serviceCode" class="form-control" placeholder="Số hiệu" data-bind="value:serviceCode" required="required" validationMessage="Bạn phải điền số hiệu">
							<span data-for="serviceCode" class="k-invalid-msg"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-2">
						<label>Thủ tục hành chính:</label>
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
								<label>Cơ quan thực hiện:</label>
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
								<label>Lĩnh vực:</label>
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
								<label>Mức độ:</label>
							</div>
							<div class="col-sm-8">
								<select class="form-control" id="level" name="level" data-bind="value: maxLevel" required="required" validationMessage="Bạn phải chọn mức độ">
									<option value="2">Mức độ 2</option>
									<option value="3">Mức độ 3</option>
									<option value="4">Mức độ 4</option>
								</select>
								<span data-for="level" class="k-invalid-msg"></span>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="row">
							<div class="col-sm-4">
								<label>Trạng thái:</label>
							</div>
							<div class="col-sm-8">
								<select class="form-control" id="status" name="status" required="required" validationMessage="Bạn phải chọn trạng thái">
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
		<div id="ttct" class="tab-pane fade in MT15">
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

				<div class="col-sm-12 text-center MT15">
					<button class=" btn btn-active" id="btn-submit-serviceinfo-detail" type="button">Ghi lại</button> <button class=" btn" type="reset">Hủy bỏ</button>
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
	</div>
</div>

<div id="serviceInfoFileTempalteDialog" class="modal fade serviceInfoFileTempalteDialog" role="dialog">
</div>

<script type="text/javascript">
	$("#processText").summernote({
		height: 150
	});
	$("#methodText").summernote({
		height: 150
	});
	$("#dossierText").summernote({
		height: 150
	});
	$("#conditionText").summernote({
		height: 150
	});
	$("#durationText").summernote({
		height: 150
	});
	$("#applicantText").summernote({
		height: 150
	});
	$("#feeText").summernote({
		height: 150
	});
	$("#resultText").summernote({
		height: 150
	});
	$("#regularText").summernote({
		height: 150
	});

	$("#administration").kendoComboBox({
		placeholder : "Chọn cơ quan thực hiện",
		dataTextField : "itemName",
		dataValueField : "itemCode",
		dataSource : {
			transport : {
				read : {
					url : "${api.server}/dictcollections/GOVERNMENT_AGENCY/dictitems",
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

	var pullDataDetail= function(id){
		console.log(id);
		$.ajax({
			url : "${api.server}/serviceinfos/"+id,
			dataType : "json",
			type : "GET",
			success : function(result){
				console.log(result.domainName);
				var viewModel = kendo.observable({
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
					fileTemplates : result.fileTemplates,
					maxLevel: result.maxLevel,
					deleteFileTemplate : function(e){
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
									data : {
										filetemplates : idFileTemplate
									},
									success : function(result){
										var fileDelete = e.data;
										var index = fileTemplates.indexOf(fileDelete);
										fileTemplates.splice(index, 1);
									},
									error : function(xhr){

									}
								});
							} else {
								return;
							}

						}
					},
					addFiletemplate : function(e){
						var idServiceinfo = $("#itemServiceInfoId").val();
						if(idServiceinfo>0){
							var data = $("#serviceInfoFileTemplateForm").serialize();
							$.ajax({
								url : "${api.server}/serviceinfos/"+idServiceinfo+"/filetemplates",
								dataType : "json",
								type : "POST",
								data : data,
								success : function(result){
									notification.show({
										message: "Yêu cầu được thực hiện thành công"
									}, "success");
									this.get("fileTemplates").push({
										fileTemplateNo: $("#fileTemplateNo").val(),
										templateName: $("#templateName").val(),
									});
									$("#serviceInfoFileTempalteDialog").modal('hide');

								},
								error : function(xhr){
									notification.show({
										message: "Xẩy ra lỗi, vui lòng thử lại"
									}, "error");
								}
							});
						}else {

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

				var validator = $("#serviceInfoFileTemplateForm").kendoValidator().data("kendoValidator");
				$("form").submit(function(event) {
					event.preventDefault();
					if (validator.validate()) {
						if (dataPk){
							updateServieInfoFileTemplate(dataPk);
						} else {
							addServieInfoFileTemplate();
						}

						$("#serviceInfoFileTempalteDialog").modal("hide");

					} else {
						return false;
					}
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
		formControlFileTemplate();
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
				},
				error : function(xhr){
					notification.show({
						message: "Xẩy ra lỗi, vui lòng thử lại"
					}, "error");
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

				},
				error : function(xhr){
					notification.show({
						message: "Xẩy ra lỗi, vui lòng thử lại"
					}, "error");
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
						serviceCode : $("#serviceCode").val(),
						serviceName : $("#serviceName").val(),
						administrationCode : $("#administration").val(),
						domainCode : $("#domain").val(),
						maxLevel : $("#level").val(),
						public : $("#status").val()
					},
					success : function(result){
						updateServieInfoIfSuccess(idServiceinfo,result);
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
			}else{
				var data = $("#frmServiceinfoGenaral").serialize();
				$.ajax({
					url : "${api.server}/serviceinfos",
					type : "POST",
					dataType : "json",
					headers: {"groupId": ${groupId}},
					data : {
						serviceCode : $("#serviceCode").val(),
						serviceName : $("#serviceName").val(),
						administrationCode : $("#administration").val(),
						domainCode : $("#domain").val(),
						maxLevel : $("#level").val(),
						public : $("#status").val()
					},
					success : function(result){
						$("#itemServiceInfoId").val(result.serviceInfoId);
						console.log($("#itemServiceInfoId").val());
						addServiceInfoIfSuccess(result);
						console.log($("#itemServiceInfoId").val());
						crtAddOrEdit();
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
			}
		}

	});

	var addServiceInfoIfSuccess=function(result){
		console.log(result);
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
			"fileTemplates":result.fileTemplates
		});
	}

	var updateServieInfoIfSuccess = function(dataPk,result){
		console.log(result.serviceCode);
		console.log(result.domainName);
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

		});
	}

	$("#add-file-template").click(function(){
		$("#serviceInfoFileTempalteDialog").load("${ajax.serviceinfo_filetemplate_form}",function(result){
			$(this).modal('show');
		});
	});

</script>
