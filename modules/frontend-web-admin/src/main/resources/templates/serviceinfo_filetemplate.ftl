<#if (Request)??>
<#include "init.ftl">
</#if>

<h4><b>Thông tin chi tiết</b></h4>
<div id="serviceInfoDetail">
	<div class="row">
		<div class="col-sm-2">
			<label>Tên thủ tục:</label>
		</div>
		<div class="col-sm-10">
			<p>
				<#if (serviceInfo.itemDetail.serviceName)??>${serviceInfo.itemDetail.serviceName}</#if>
			</p>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="row">
				<div class="col-sm-4">
					<label>Số hiệu:</label>
				</div>
				<div class="col-sm-8">
					<p>
						<#if (serviceInfo.itemDetail.serviceCode)??>${serviceInfo.itemDetail.serviceCode}</#if>
					</p>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="row">
				<div class="col-sm-4">
					<label>Cơ quan:</label>
				</div>
				<div class="col-sm-8">
					<p>
						<#if (serviceInfo.itemDetail.administrationName)??>${serviceInfo.itemDetail.administrationName}</#if>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-6">
			<div class="row">
				<div class="col-sm-4">
					<label>Lĩnh vực:</label>
				</div>
				<div class="col-sm-8">
					<p>
						<#if (serviceInfo.itemDetail.domainName)??>${serviceInfo.itemDetail.domainName}</#if>
					</p>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="row">
				<div class="col-sm-4">
					<label>Trạng thái:</label>
				</div>
				<div class="col-sm-8">
					<p>
						<#if (serviceInfo.itemDetail.activeStatus)??>${serviceInfo.itemDetail.activeStatus}</#if>
					</p>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row MT10 MB10">
	<div class="col-sm-12">
		<button class="k-button btn-primary" id="btnAddServiceInfoFile">Thêm biểu mẫu</button>
	</div>
</div>
<div class="row" style="border-bottom: 1px solid #ccc;">
	<div class="col-sm-1">
		<p><b>STT</b></p>
	</div>
	<div class="col-sm-3">
		<p><b>Số biểu mẫu</b></p>
	</div>
	<div class="col-sm-6">
		<p><b>Tên biểu mẫu</b></p>
	</div>
	<div class="col-sm-2 text-center">
		<p><b>Thao tác</b></p>
	</div>
</div>
<div class="row">
	<div class="col-sm-12">
		<ul class='ul-with-border'>
			<div id='listViewFileTemplate'></div>
		</ul>
		<div id='pagerFileTemplate' class='k-pager-wrap k-widget k-floatwrap'></div>
	</div>
	<script type="text/x-kendo-template" id="serviceInfoFileTemplates">
		<li>
			<div class="row">
				<div class="col-sm-1">
					1
				</div>
				<div class="col-sm-3">
					#=fileTemplateNo#
				</div>
				<div class="col-sm-6">
					#=templateName#
				</div>
				<div class="col-sm-2 text-center">
					<a href="javascript:;" class="_itemServiceInfo_fileTempalte_btnEdit" data-pk="#:fileTemplateNo#"><i class="glyphicon glyphicon-pencil MB10"></i></a>
					<a href="javascript:;" class="_itemServiceInfo_fileTempalte_btnDelete" data-pk="#:fileTemplateNo#"><i class="glyphicon glyphicon-remove"></i></a>
					<a href="${api.server}/<#if (serviceInfo.serviceInfoId)??>${serviceInfo.serviceInfoId}</#if>/filetemplates/#:fileTemplateNo#"><i class="glyphicon glyphicon-download-alt"></i></a>
				</div>
			</div>
		</li>
	</script>
</div>

<div class="row">
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
								<input id="file" class="" type="file"/>
							</div>
						</div>
						<div class="row MT10 text-center">
							<button id="btnSaveFileTemplate" class="btn btn-active" type="button" data-bind="click: addFiletemplate">Ghi lại</button>
							<button id="btnCancleFileTemplate" class="btn" title="Hủy bỏ" data-dismiss="modal">Đóng</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var dataSourceFileTemplate=new kendo.data.DataSource({
		transport:{
			read:function(options){
				$.ajax({
					url:"${api.server}/serviceinfos/1/filetemplates",
					dataType:"json",
					type:"GET",
					data:{

					},
					success:function(result){
						console.log(options.data.id);
						if (result.data) {
							options.success(result);
						} else {
							options.success({
								data: [],
								total: 0
							});
						}
					},
					error:function(result){
						console.log(options.data.id);
						options.error(result);
					}
				});
			},
			create:function(options){
				var documentData = new FormData();
				documentData.append('file', options.file);
				documentData.append('fileTemplateNo', options.fileTemplateNo);
				documentData.append('templateName', options.templateName);

				$.ajax({
					url:"${api.server}/serviceinfos/"+options.serviceinfoId+"/filetemplates",
					type:"POST",
					headers: {"groupId": ${groupId}},
					dataType:"json",
					data: documentData,
					cache: false,
					contentType: false,
					processData: false,
					success:function(result){
						addFileTemplateIfSuccess(result);
					},
					error:function(result){
					}
				});
			},
			update:function(options){
				var documentData = new FormData();
				documentData.append('file', options.file);
				documentData.append('fileTemplateNo', options.fileTemplateNo);
				documentData.append('templateName', options.templateName);

				$.ajax({
					url:"${api.server}/serviceinfos/"+options.serviceinfoId+"/filetemplates/"+options.fileTemplateId,
					dataType:"json",
					type:"PUT",
					headers: {"groupId": ${groupId}},
					data: documentData,
					cache: false,
					contentType: false,
					processData: false,
					success:function(result){
						updateFileTemplateIfSuccess(options.fileTemplateId,result);

					},
					error:function(result){

					}
				});
			},
			destroy:function(options){
				$.ajax({
					url:"${api.server}/serviceinfos/"+options.serviceinfoId+"/filetemplates/"+options.fileTemplateId,
					dataType:"json",
					type:"DELETE",
					data:{
						fileTemplateId:options.fileTemplateId
					},
					success:function(result){
						var item=dataSourceFileTemplate.get(options.fileTemplateId);
						if(item!=null){
							dataSourceFileTemplate.remove(item);
						}
					},
					error:function(result){
					}
				});
			}
		},
		error: function(e) {
			this.cancelChanges();
		},
		schema:{
			total:"total",
			data:"data",
			model:{
				id:"fileTemplateNo"
			}
		},
		autoSync:false,
		pageSize: 5,
		serverPaging: false,
		serverSorting: false,
		serverFiltering: false
	});

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

	$("#listViewFileTemplate").kendoListView({
		dataSource:dataSourceFileTemplate,
		template:kendo.template($("#serviceInfoFileTemplates").html()),
		selectable: true,
		remove:function(e){
			if(!confirm('Bạn có muốn xóa ?')){
				e.preventDefault();
			}
		},
		autoBind:false
	});

	$("#pagerFileTemplate").kendoPager({
		dataSource:dataSourceFileTemplate,
		input: true,
		numeric: false,
		messages: {
			empty: "Không có kết quả phù hợp!",
			display: "Hiển thị {0}-{1} trong {2} bản ghi",
			page: "",
			of: "/ {0}"
		}
	});

	var updateServieInfoFileTemplate=function(dataPk){

		console.log($("#itemServiceInfoId").val());
		dataSourceFileTemplate.transport.update({
			"fileTemplateId":dataPk,
			"serviceinfoId":$("#itemServiceInfoId").val(),
			"fileNo":$("#fileNo").val(),
			"fileName":$("#fileName").val(),
			"file": $('input#file')[0].files[0]
		});
	}

	var addServieInfoFileTemplate=function(){
		dataSourceFileTemplate.transport.create({
			"serviceinfoId":$("#itemServiceInfoId").val(),
			"fileNo":$("#fileNo").val(),
			"fileName":$("#fileName").val(),
			"file": $('input#file')[0].files[0]
		});
	}

	var updateFileTemplateIfSuccess = function(dataPk,result){
		console.log("id:"+dataPk);
		dataSourceFileTemplate.fetch(function() {
			var item = dataSourceFileTemplate.get(dataPk);
			item.set("fileTemplateNo",result.fileTemplateNo);
			item.set("templateName",result.templateName);
		});
	}
	var addFileTemplateIfSuccess=function(result){
		dataSourceFileTemplate.add({
			"fileTemplateNo":result.fileTemplateNo,
			"templateName":result.templateName
		});
	}

	$(document).on("click","._itemServiceInfo_fileTempalte_btnEdit",function(event){
		var dataPk=$(this).attr("data-pk");
		if(dataPk>0){
			formControlFileTemplate(dataPk);
		}
	});

	$(document).on("click","._itemServiceInfo_fileTempalte_btnDelete",function(event){
		if(confirm('Bạn có muốn xóa ?')){
			var fileTemplateId=$(this).attr("data-pk");
			if(fileTemplateId>0){
				dataSourceFileTemplate.transport.destroy({
					"serviceinfoId":$("#itemServiceInfoId").val(),
					"fileTemplateId":fileTemplateId
				});
			}
		}

	});

	$("#btnAddServiceInfoFile").click(function(){
		formControlFileTemplate();
	});
</script>
