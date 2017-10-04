<#if (Request)??>
<#include "init.ftl">
</#if>
<button class="btn btn-active" type="button" id="btn-add-serviceconfig-option"><i class="fa fa-plus" aria-hidden="true"></i> Thêm mới quy trình thực hiện dịch vụ</button>
<div class="form-group search-icon MT15">
	<input type="text" class="form-control" placeholder="Nhập từ khóa" id="search-serviceconfig-process">
</div>
<ul class='ul-with-border MB5'>
	<div id='serviceConfigOptionListView'></div>
</ul>
<div id='pagerServiceConfigOption'></div>
<script type="text/x-kendo-template" id="serviceConfigOptionTemplate">
	<li style="padding: 10px 0 10px 5px;" role="option" aria-selected="true">
		<div class="row">
			<div class="col-sm-6">
				<#-- #
				var lbl = "text-link";
				if(serviceLevel == 1){
					lbl = "text-link";
				} else if(serviceLevel == 2){
					lbl = "text-link";
				} else if(serviceLevel == 3){
					lbl = "text-orange";
				}else {
					lbl = "text-danger";
				}# -->
				<span>Cấp phép cho người Việt Nam định cư ở nước ngoài, tổ chức cá nhân Cấp phép cho người Việt Nam định cư ở nước ngoài, tổ chức cá nhân Cấp phép cho người Việt Nam định cư ở nước ngoài, tổ chức cá nhân</span> <br>
				<i class="fa fa-university"></i> <span class="ML5">Cục điện ảnh</span> <span class="ML5 pull-right text-link">Mức độ 2</span>
			</div>
			<div class="col-sm-6 border-left" >
				<div class="row">
					<div class="col-sm-11">
						<p>Tên mẫu hồ sơ: Cấp phép cho người Việt Nam định cư ở nước ngoài, tổ chức cá nhân ...</p>
					</div>
					<div class="col-sm-1 PL0 PR0">
						<a href="javascript:;" data-pk="#:id#" class="_itemServiceConfig_option_btnEdit"><i class="fa fa-pencil"></i></a>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-11">
						<p>Tên quy trình thủ tục: Cấp phép cho người Việt Nam định cư ở nước ngoài, tổ chức cá nhân ...</p>
					</div>
					<div class="col-sm-1 PL0 PR0">
						<a href="javascript:;" data-pk="#:id#" class="_itemServiceConfig_option_btnDelete"><i class="fa fa-trash"></i></a>
					</div>
				</div>
			</div>
		</div>
	</li>
</script>
<script type="text/javascript">
	var dataSourceServiceOption=new kendo.data.DataSource({
		transport:{
			read:function(options){
				$.ajax({
					url:"${api.server}/serviceconfigs/"+$("#itemServiceConfigId").val()+"/processes",
					dataType:"json",
					type:"GET",
					headers: {"groupId": ${groupId}},
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
					headers: {"groupId": ${groupId}},
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
					headers: {"groupId": ${groupId}},
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
					headers: {"groupId": ${groupId}},
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
		autoBind: true
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


	$("#btn-add-serviceconfig-option").click(function(){
		console.log("add");
		$("#xlqtdv").load("${ajax.serviceconfig_option_form}",function(result){

		});
	});

	$("#search-serviceconfig-process").kendoAutoComplete({
		dataTextField : "templateName",
		dataSource: {
			transport : {
				read : {
					url : "${api.server}/serviceconfigs/"+$("#itemServiceConfigId").val()+"/processes",
					dataType : "json",
					type : "GET",
					headers: {"groupId": ${groupId}},
					success : function(result){

					},
					error : function(xhr){

					}
				}
			},
			schema : {
				total : "total",
				data : "data",
				model : {
					id : "processOptionId"
				}
			}
		},
		filter: "contains",
		placeholder: "Nhập từ khóa",
		noDataTemplate: 'Không có dữ liệu'
	});

</script>
