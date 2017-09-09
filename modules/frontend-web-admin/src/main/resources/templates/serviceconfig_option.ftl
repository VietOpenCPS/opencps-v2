<#if (Request)??>
	<#include "init.ftl">
</#if>
<button class="k-button btn-primary MB10" id="btnAddServiceConfigOption"><i class="glyphicon glyphicon-plus"></i>Thêm cấu hình</button>
<div class="row MB10" style="border-top:1px solid #ddd;"></div>
<div class="row">
	<div class="col-sm-1">
		<p><b>STT</b></p>
	</div>
	<div class="col-sm-6">
		<p><b>Mã thủ tục</b></p>
	</div>
	<div class="col-sm-3 text-center">
		<p><b>Thứ tự</b></p>
	</div>
	<div class="col-sm-2 text-center">
		<p><b>Thao tác</b></p>
	</div>
</div>
<ul class='ul-with-border'>
	<div id='serviceConfigOptionListView'></div>
</ul>
<div id="pagerServiceConfigOption"></div>

<div class="row">
	<div id="serviceConfigOptionDialog" class="modal fade serviceConfigOptionDialog" role="dialog">
	</div>
</div>

<input type="hidden" name="itemServiceConfigOptionId" id="itemServiceConfigOptionId">

<script type="text/x-kendo-template" id="serviceConfigOptionTemplate">
	<div class="row">
		<div class="col-sm-1">
			<p>1</p>
		</div>
		<div class="col-sm-6">
			<p>#:processNo#</p>
		</div>
		<div class="col-sm-3 text-center">
			<p>#:processName#</p>
		</div>
		<div class="col-sm-2 text-center">
			<a href="javascript:;" class="_itemServiceConfig_option_btnEdit" data-pk="#:id#"><i class="glyphicon glyphicon-pencil MB10"></i></a>
			<a href="javascript:;" class="_itemServiceConfig_option_btnDelete" data-pk="#:id#"><i class="glyphicon glyphicon-remove"></i></a>
		</div>
	</div>
</script>
<script type="text/javascript">
	var dataSourceServiceOption=new kendo.data.DataSource({
		transport:{
			read:function(options){
				$.ajax({
					url:"${api.server}/serviceconfigs/1/processes",
					dataType:"json",
					type:"GET",
					success:function(result){
						options.success(result);
					},
					error:function(result){
						options.error(result);
					}
				});
			},
			create:function(options){
				$.ajax({
					url:"${api.server}/serviceconfigs/"+options.serviceConfigId+"/processes",
					dataType:"json",
					type:"POST",
					data:{
						serviceConfigId:options.serviceConfigId,
						seqOrder:options.seqOrder,
						dossierTemplateId:options.dossierTemplateId,
						serviceProcessId:options.serviceProcessId,
						submissionNote:options.submissionNote,
						instructionNote:options.instructionNote,
						autoSelect:options.autoSelect
					},
					success:function(result){
						console.log(result);
						addServiceConfigOptionIfSuccess(result);
					},
					error:function(result){

					}
				});
			},
			update:function(options){
				$.ajax({
					url:"${api.server}/serviceconfigs/"+options.serviceConfigId+"/processes/"+options.processNo,
					dataType:"json",
					type:"PUT",
					data:{
						serviceConfigId:options.serviceConfigId,
						processNo:options.processNo,
						seqOrder:options.seqOrder,
						dossierTemplateId:options.dossierTemplateId,
						serviceProcessId:options.serviceProcessId,
						submissionNote:options.submissionNote,
						instructionNote:options.instructionNote,
						autoSelect:options.autoSelect
					},
					success:function(result){
						console.log(result);
						console.log(options);
						updateServieConfigOptionIfSuccess($("#itemServiceConfigOptionId").val(),result);
					},
					error:function(result){
						console.log(options);
					}
				});
			},
			destroy:function(options){
				$.ajax({
					url:"${api.server}/serviceconfigs/"+options.serviceConfigId+"/processes/"+options.processNo,
					dataType:"json",
					type:"DELETE",
					data:{
						processNo:options.processNo
					},
					success:function(result){
						var item=dataSourceServiceOption.get(options.processNo);
						if(item!=null){
							dataSourceServiceOption.remove(item);
						}
					},
					error:function(result){
						
					}
				});
			}
		},
		schema:{
			total:"total",
			data:"data",
			model:{
				id:"processOptionId"
			}
		},
		error: function(e) {         
			this.cancelChanges();
		},
		autoSync: false,
		pageSize:6,
		serverPaging:false,
		serverSorting:false,
		serverFiltering:false
	});

	$("#serviceConfigOptionListView").kendoListView({
		dataSource:dataSourceServiceOption,
		template:kendo.template($("#serviceConfigOptionTemplate").html()),
		selectable: true,
		remove:function(e){
			if(!confirm('Bạn có muốn xóa ?')){ 
				e.preventDefault();
			}
		},
		autoBind: false
	});	

	$("#pagerServiceConfigOption").kendoPager({
		dataSource:dataSourceServiceOption,
		input: true,
		numeric: false,
		messages: {
			empty: "Không có kết quả phù hợp!",
			display: "Hiển thị {0}-{1} trong {2} bản ghi",
			page: "",
			of: "/ {0}"
		}
	});
	
	$(document).on("click", "._itemServiceConfig_option_btnEdit", function(event){
		event.preventDefault();
		$("#itemServiceConfigOptionId").val($(this).attr("data-pk"));
		formControl($(this).attr("data-pk"));
	});

	$(document).on("click", "._itemServiceConfig_option_btnDelete", function(event){
		event.preventDefault();
		$("#itemServiceConfigOptionId").val($(this).attr("data-pk"));
		dataSourceServiceOption.transport.destroy({
			"serviceConfigId":$("#itemServiceConfigId").val(),
			"processNo":$(this).attr("data-pk")
		});
	});

	$(document).on("click", "#btnAddServiceConfigOption", function(event){
		event.preventDefault();
		$("#itemServiceConfigOptionId").val("");
		formControl();
	});

	var formControl = function(dataPk){

		var url = "${serviceconfig.ajax.serviceconfig_option_form}";

		$("#serviceConfigOptionDialog").load(
			url,
			function(result){

				$("#serviceConfigOptionDialog").modal({show: true});

				$("#btnCancleServiceConfigOption").click(function(e){
					e.preventDefault();
					$("#serviceConfigOptionDialog").modal("hide");
				});

				var validator = $("#serviceConfigOptionForm").kendoValidator().data("kendoValidator");
				$("form").submit(function(event) {
					event.preventDefault();
					if (validator.validate()) {
						if (dataPk){
							updateServieConfigOption(dataPk);
						} else {
							addServiceConfigOption();
						}

						$("#serviceConfigOptionDialog").modal("hide");

					} else {
						return false;
					}
				});
			}
			);
	}

	var updateServieConfigOption = function(dataPk){

		dataSourceServiceOption.transport.update({
			"serviceConfigId":$("#itemServiceConfigId").val(),
			"processNo":dataPk,
			"seqOrder":$("#seqOrder").val(),
			"dossierTemplateId":$("#dossierTemplateId").val(),
			"serviceProcessId":$("#serviceProcessId").val(),
			"submissionNote":$("#submissionNote").val(),
			"instructionNote":$("#instructionNote").val(),
			"autoSelect":$("#autoSelect").val()
		});
	}

	var updateServieConfigOptionIfSuccess = function(dataPk,result){
		dataSourceServiceOption.fetch(function(){
			var item=dataSourceServiceOption.get(dataPk);
			item.set("processNo",result.processNo);
			item.set("processName",result.processName);
			item.set("dossierTemplateId",result.dossierTemplateId);
			item.set("serviceProcessId",result.serviceProcessId);
			item.set("submissionNote",result.submissionNote);
			item.set("instructionNote",result.instructionNote);
			item.set("autoSelect",result.autoSelect);
		});
	}

	var addServiceConfigOption = function(){
		console.log("itemServiceConfigId"+ $("#itemServiceConfigId").val());
		dataSourceServiceOption.transport.create({
			serviceConfigId:$("#itemServiceConfigId").val(),
			seqOrder:$("#seqOrder").val(),
			dossierTemplateId:$("#dossierTemplateId").val(),
			serviceProcessId:$("#serviceProcessId").val(),
			submissionNote:$("#submissionNote").val(),
			instructionNote:$("#instructionNote").val(),
			autoSelect:$("#autoSelect").val()
		});	
	};

	var addServiceConfigOptionIfSuccess = function(result){
		dataSourceServiceOption.add({

			processNo:result.processNo,
			processName:result.processName,
			dossierTemplateId:result.dossierTemplateId,
			serviceProcessId:result.serviceProcessId,
			submissionNote:result.submissionNote,
			instructionNote:result.instructionNote,
			autoSelect:result.autoSelect
		});	
	};

</script>