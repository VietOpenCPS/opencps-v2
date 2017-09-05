<div class="row">
	<div class="col-sm-12">
		<div class="row">
			<div class="">
				<div class="col-sm-3">
					<button class="k-button btn-primary" id="btnAddServiceConfig"><i class="glyphicon glyphicon-plus"></i> Thêm dịch vụ công</button>
				</div>
				<div class="col-sm-3">
					<input name="administrativeSearch" id="administrativeSearch">
				</div>
				<div class="col-sm-3">
					<input name="govAgencySearch" id="govAgencySearch">
				</div>
				<div class="col-sm-3">
					<div class="input-group">
						<input id="keyword" class="form-control" name="keyword" placeholder="Nhập từ khóa tìm kiếm"><span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row MT20">
	<div class="col-sm-3">
		<div class="panel panel-body">
			<div class="row">
				<div class="col-sm-12">
					<ul class='ul-with-border'>
						<div id='serviceConfigListView'></div>
					</ul>
					<div id='pager'></div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-9 panel">
		<div class="panel-header">
			<h4><b>Thông tin chi tiết</b></h4>
		</div>
		<div class="panel-body">
			<div id="detailServiceConfig">
				<div class="row">
					<div class="col-sm-8">
						<div class="row">
							<div class="col-sm-3">
								<p><b>Tên thủ tục</b></p>
							</div>
							<div class="col-sm-9">
								<p data-bind="text:serviceInfoName"></p>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-3">
								<p><b>Cơ quan</b></p>
							</div>
							<div class="col-sm-9">
								<p data-bind="text:govAgencyName"></p>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="row">
							<div class="col-sm-6">
								<p><b>Mức độ</b></p>
							</div>
							<div class="col-sm-6">
								<p data-bind="text:serviceLevel"></p>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6">
								<p><b>Chứng nhận</b></p>
							</div>
							<div class="col-sm-6">
								<p data-bind="text:authentication"></p>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div class="row">
				<div class="col-sm-12">
					<div id="serviceConfigOption">

					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/x-kendo-template" id="serviceConfigTemplate">
	<li style="padding: 10px 0 10px 5px;" role="option" aria-selected="true">
		<div class="row">
			<div class="col-sm-2 clearfix PL0 PR0">
				<a href="javascript:;"  class="PR5">
					<i style="font-size: 25px;padding: 5px;" class="fa fa-envelope-open" aria-hidden="true"></i> 
				</a>
			</div>
			<div class="col-sm-8 PL0 service-config-item" data-pk="#:id#">
				<div class="row">
					<div class="col-sm-12">
						<p><b>
							#if(serviceName.length>40){# 
							# var dcontent = serviceName.substring(0,38)+"..."; # 
							#:kendo.toString(dcontent)#
							#}else{#
							#:serviceName#
							#}#
						</b></p>
					</div>
					<div class="col-sm-12">
						<span>#:govAgencyName#</span>
					</div>
				</div>
			</div>
			<div class="col-sm-2">
				<a class="itemServiceConfig-popup-edit" href="javascript:;" data-pk="#:id#"><i class="glyphicon glyphicon-pencil MB10"></i></a>
				<a class="k-delete-button" href="javascript:;"><i class="glyphicon glyphicon-remove"></i></a>
			</div>
		</div>
	</li>
</script>

<input type="hidden" name="itemServiceConfigId" id="itemServiceConfigId">
<div id="serviceConfigDialog" class="modal fade serviceConfigDialog" role="dialog">

</div>
<script type="text/javascript">
	(function($){

		var dataSourceServiceConfig=new kendo.data.DataSource({
			transport:{
				read:function(options){
					$.ajax({
						url: "${api.server}/serviceconfigs",
						dataType:"json",
						type:"GET",
						data:{
							keywords: options.data.keywords,
							page: options.data.page,
							pageSize: options.data.pageSize
						},
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
						url: "${api.server}/serviceconfigs",
						dataType:"json",
						type:"POST",
						data:{
							serviceInfoId:options.serviceInfoId,
							serviceName:options.serviceName,
							govAgencyName:options.govAgencyName,
							govAgencyCode:options.govAgencyCode,
							serviceLevel:options.serviceLevel,
							serviceInstruction:options.serviceInstruction,
							authentication:options.authentication,
							serviceUrl:options.serviceUrl
						},
						success:function(result){
							addServiceConfigIfSuccess(result);

						},
						error:function(result){
							
						}
					});
				},
				update:function(options){
					$.ajax({
						url: "${api.server}/serviceconfigs/"+options.serviceConfigId,
						dataType:"json",
						type:"PUT",
						data:{
							serviceConfigId:options.serviceConfigId,
							serviceInfoId:options.serviceInfoId,
							serviceName:options.serviceName,
							govAgencyName:options.govAgencyName,
							govAgencyCode:options.govAgencyCode,
							serviceLevel:options.serviceLevel,
							serviceInstruction:options.serviceInstruction,
							authentication:options.authentication,
							serviceUrl:options.serviceUrl
						},
						success:function(result){
							updateServieConfigIfSuccess($("#itemServiceConfigId").val(),result);
						},
						error:function(result){
							
						}
					});
				},
				destroy:function(options){
					$.ajax({
						url: "${api.server}/serviceconfigs/"+options.data.serviceConfigId,
						dataType:"json",
						type:"DELETE",
						data:{
							serviceConfigId:options.data.serviceConfigId
						},
						success:function(result){
							console.log(options);
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
					id:"serviceConfigId"
				}
			},
			error: function(e) {         
				this.cancelChanges();
			},
			autoSync: false,
			pageSize:5,
			serverPaging:false,
			serverSorting:false,
			serverFiltering:false
		});


		$("#serviceConfigListView").kendoListView({
			dataSource:dataSourceServiceConfig,
			template:kendo.template($("#serviceConfigTemplate").html()),
			selectable: true,
			remove:function(e){
				if(!confirm('Bạn có muốn xóa ?')){
					e.preventDefault();
				}
			}

		});	

		$(document).on("click", ".service-config-item", function(event){
			var id = $(this).attr("data-pk");
			$("#itemServiceConfigId").val($(this).attr("data-pk"));
			console.log("itemServiceConfigId: "+$("#itemServiceConfigId").val());
			$("#serviceConfigOption").load(
				"${serviceconfig.ajax.serviceconfig_option}",
				function(result){
					$("#serviceConfigOptionListView").getKendoListView().dataSource.read({
						id: id
					});
				}
				);

			var item=dataSourceServiceConfig.get($(this).attr("data-pk"));
			var viewModel = kendo.observable({	
				serviceInfoName:item.serviceName,
				govAgencyName:item.govAgencyName,
				serviceLevel:item.serviceLevel,
				authentication:function(){
					if(item.authentication==1){
						return "Có";
					}else{
						return "Không";
					}
				}
			});

			kendo.bind($("#detailServiceConfig"), viewModel);
		});

		$("#pager").kendoPager({
			dataSource:dataSourceServiceConfig
		});

		$("#govAgencySearch").kendoComboBox({

		});

		$("#administrativeSearch").kendoComboBox({

		});

		$(function() {
			$("[data-role=combobox]").each(function() {
				var widget = $(this).getKendoComboBox();
				widget.input.on("focus", function() {
					widget.open();
				});
			});
		});


		$(document).on("click", ".itemServiceConfig-popup-edit", function(event){

			event.preventDefault();
			$("#itemServiceConfigId").val($(this).attr("data-pk"));
			console.log($("#itemServiceConfigId").val());
			formControl($(this).attr("data-pk"));
		});

		$(document).on("click", "#btnAddServiceConfig", function(event){

			event.preventDefault();
			$("#itemServiceConfigId").val("");
			console.log($("#itemServiceConfigId").val());
			formControl();
		});

		var formControl = function(dataPk){

			var url = "${serviceconfig.ajax.serviceconfig_form}";

			$("#serviceConfigDialog").load(
				url,
				function(result){

					$("#serviceConfigDialog").modal({show: true});

					$("#btnCancleServiceConfig").click(function(e){
						e.preventDefault();
						$("#serviceConfigDialog").modal("hide");
					});

					var validator = $("#serviceConfigForm").kendoValidator().data("kendoValidator");

					$("form").submit(function(event) {
						event.preventDefault();
						if (validator.validate()) {
							if (dataPk){
								updateServieConfig(dataPk);
							} else {
								addServiceConfig();
							}

							$("#serviceConfigDialog").modal("hide");

						} else {
							return false;
						}
					});
				}
				);
		}

		var updateServieConfig = function(dataPk){
			dataSourceServiceConfig.transport.update({
				"serviceConfigId":dataPk,
				"serviceInfoId":$("#serviceInfoId").val(),
				"govAgencyCode":$("#govAgencyCode").val(),
				"serviceLevel":$("#serviceLevel").val(),
				"serviceInstruction":$("#serviceInstruction").val(),
				"serviceUrl":$("#serviceUrl").val(),
				"authentication":$("#authentication").val()
			});
		}

		var updateServieConfigIfSuccess = function(dataPk,result){
			dataSourceServiceConfig.fetch(function(){
				if(dataPk>0){
					var item=dataSourceServiceConfig.get(dataPk);
					item.set("serviceInfoId",result.serviceInfoId);
					item.set("govAgencyCode",result.govAgencyCode);

					item.set("serviceName",result.serviceName);
					item.set("govAgencyName",result.govAgencyName);

					item.set("serviceLevel",result.serviceLevel);
					item.set("serviceInstruction",result.serviceInstruction);
					item.set("serviceUrl",result.serviceUrl);
					item.set("authentication",result.authentication);
				}

			});
		}

		var addServiceConfig = function(){
			dataSourceServiceConfig.transport.create({
				"serviceInfoId":$("#serviceInfoId").val(),
				"govAgencyCode":$("#govAgencyCode").val(),
				"serviceLevel":$("#serviceLevel").val(),
				"serviceInstruction":$("#serviceInstruction").val(),
				"authentication":$("#authentication").val(),
				"serviceUrl":$("#serviceUrl").val()
			});	
		};

		var addServiceConfigIfSuccess = function(result){
			dataSourceServiceConfig.add({
				"serviceInfoId":result.serviceInfoId,
				"serviceName":result.serviceName,
				"govAgencyName":result.govAgencyName,
				"govAgencyCode":result.govAgencyCode,
				"serviceLevel":result.serviceLevel,
				"serviceInstruction":result.serviceInstruction,
				"authentication":result.authentication,
				"serviceUrl":result.serviceUrl
			});	
		};

	})(jQuery);
</script>
