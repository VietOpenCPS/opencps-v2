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
			<p>#:optionName#</p>
		</div>
		<div class="col-sm-3 text-center">
			<p>#:optionOrder#</p>
		</div>
		<div class="col-sm-2 text-center">
			<a href="javascript:;" class="_itemServiceConfig_option_btnEdit" data-pk="#:id#"><i class="glyphicon glyphicon-pencil MB10"></i></a>
			<a href="javascript:;" class="k-delete-button"><i class="glyphicon glyphicon-remove"></i></a>
		</div>
	</div>
</script>
<script type="text/javascript">
	var dataSourceServiceOption=new kendo.data.DataSource({
		transport:{
			read:function(options){
				$.ajax({
					url:"${api.server}/serviceconfigs/"+options.data.id+"/serviceoptions",
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
					url:"${api.server}/serviceconfigs/"+options.serviceConfigId+"/serviceoptions",
					dataType:"json",
					type:"POST",
					data:{
						serviceConfigId:options.serviceConfigId,
						optionCode:options.optionCode,
						optionName:options.optionName,
						dossierTemplateId:options.dossierTemplateId,
						serviceProcessId:options.serviceProcessId,
						optionOrder:options.optionOrder,
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
					url:"${api.server}/serviceoptions/"+options.serviceConfigId,
					dataType:"json",
					type:"PUT",
					data:{
						serviceConfigId:options.serviceConfigId,
						serviceConfigOptionId:options.serviceConfigOptionId,
						optionCode:options.optionCode,
						dossierTemplateId:options.dossierTemplateId,
						serviceProcessId:options.serviceProcessId,
						optionOrder:options.optionOrder,
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
					url:"${api.server}/serviceoptions/"+options.data.serviceConfigOptionId,
					dataType:"json",
					type:"DELETE",
					data:{
						serviceConfigOptionId:options.serviceConfigOptionId
					},
					success:function(result){
						options.success(result);
					},
					error:function(result){
						options.error(result);
					}
				});
			}
		},
		schema:{
			total:"total",
			data:"data",
			model:{
				id:"serviceConfigId"
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
		}
	});	

	$("#pagerServiceConfigOption").kendoPager({
		dataSource:dataSourceServiceOption
	});

	$(document).on("click", "._itemServiceConfig_option_btnEdit", function(event){
		event.preventDefault();
		$("#itemServiceConfigOptionId").val($(this).attr("data-pk"));
		formControl($(this).attr("data-pk"));
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
			"serviceConfigId":dataPk,
			"optionCode":$("#optionCode").val(),
			"dossierTemplateId":$("#dossierTemplateId").val(),
			"serviceProcessId":$("#serviceProcessId").val(),
			"optionOrder":$("#optionOrder").val(),
			"instructionNote":$("#instructionNote").val(),
			"autoSelect":$("#autoSelect").val()
		});
	}

	var updateServieConfigOptionIfSuccess = function(dataPk,result){
		dataSourceServiceOption.fetch(function(){
			var item=dataSourceServiceOption.get(dataPk);
			item.set("optionCode",result.optionCode);
			item.set("optionName",result.optionName);
			item.set("dossierTemplateId",result.dossierTemplateId);
			item.set("serviceProcessId",result.serviceProcessId);
			item.set("optionOrder",result.optionOrder);
			item.set("instructionNote",result.instructionNote);
			item.set("autoSelect",result.autoSelect);
		});
	}

	var addServiceConfigOption = function(){
		console.log("itemServiceConfigId"+ $("#itemServiceConfigId").val());
		dataSourceServiceOption.transport.create({
			serviceConfigId:$("#itemServiceConfigId").val(),
			optionCode:$("#optionCode").val(),
			dossierTemplateId:$("#dossierTemplateId").val(),
			serviceProcessId:$("#serviceProcessId").val(),
			optionOrder:$("#optionOrder").val(),
			instructionNote:$("#instructionNote").val(),
			autoSelect:$("#autoSelect").val()
		});	
	};

	var addServiceConfigOptionIfSuccess = function(result){
		dataSourceServiceOption.add({

			optionCode:result.optionCode,
			optionName:result.optionName,
			dossierTemplateId:result.dossierTemplateId,
			serviceProcessId:result.serviceProcessId,
			optionOrder:result.optionOrder,
			instructionNote:result.instructionNote,
			autoSelect:result.autoSelect
		});	
	};

</script>