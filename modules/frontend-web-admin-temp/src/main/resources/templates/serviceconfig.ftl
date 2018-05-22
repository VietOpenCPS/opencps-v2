<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="row">
	<div class="col-sm-3 panel P0">
		<div class="panel-body">
			<div class="row">
				<div class="col-sm-12">
					<button class="btn btn-active form-control MB5" id="btnAddServiceConfig"><i class="glyphicon glyphicon-plus"></i> Thêm dịch vụ công</button>
					<button class="btn btn-active form-control" id="btnAddServiceConfigS"><i class="glyphicon glyphicon-plus"></i> Tạo nhiều DVC</button>
				</div>
				<div class="col-sm-12">
					<div class="form-group search-icon MT10">
						<input type="text" id="keywordSearchServiceConfig" name="keywordSearchServiceConfig" class="form-control" placeholder="Nhập từ khóa">
					</div>
					<ul class='ul-with-border' id="serviceConfigListView">
					</ul>
					<div id='pager'></div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-9 PR0" >
		<div class="panel panel-body PL0 PR0" id="serviceConfigDetail">
			<#include "serviceconfig_option_form.ftl">
		</div>
	</div>
</div>
<script type="text/x-kendo-template" id="serviceConfigTemplate">
	<li style="padding: 10px 0 10px 5px;" role="option" aria-selected="true">
		<div class="row">
			<div class="col-sm-11 PR0 service-config-item" data-pk="#:id#">
				<p class="" data-pk="#:id#">
					<#-- #if(serviceName.length>40){#
					# var dcontent = serviceName.substring(0,38)+"..."; #
					#:kendo.toString(dcontent)#
					#}else{#
					#:serviceName#
					#}# -->
					#:serviceName#
				</p>
			</div>
			<div class="col-sm-1 PL0 PR0">
				<a class="item-serviceconfig-delete" href="javascript:;" data-pk="#:id#"><i class="fa fa-trash"></i></a>
			</div>
			<div class="col-sm-12 service-config-item" data-pk="#:id#">
				<i class="fa fa-university"></i> <span class="ML5 service-config-item" data-pk="#:id#">#:govAgencyName#</span>
			</div>
			<div class="col-sm-12 service-config-item" data-pk="#:id#">
				#var lbl = "text-link";
				if(serviceLevel == 1){
				lbl = "text-link";
			} else if(serviceLevel == 2){
			lbl = "text-link";
		} else if(serviceLevel == 3){
		lbl = "text-orange";
	} else {
	lbl = "text-danger";
}#
<span class="#:lbl#" data-pk="#:id#">Mức độ <span>#:serviceLevel#</span></span>
</div>
</div>
</li>
</script>

<input type="hidden" name="itemServiceConfigId" id="itemServiceConfigId">
<div id="serviceConfigDialog" class="modal fade serviceConfigDialog" role="dialog">

</div>
<script type="text/javascript">
	var dataSourceServiceConfig;
	var firstLoadServiceConfig =true;
	(function($){

		dataSourceServiceConfig=new kendo.data.DataSource({
			transport:{
				read:function(options){
					$.ajax({
						url: "${api.server}/serviceconfigs",
						dataType:"json",
						type:"GET",
						headers: {"groupId": ${groupId}},
						data:{
							keyword: options.data.keyword,
							page: options.data.page,
							pageSize: options.data.pageSize,
							order : true
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
						headers: {"groupId": ${groupId}},
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
						url: "${api.server}/serviceconfigs/1",
						dataType:"json",
						type:"PUT",
						headers: {"groupId": ${groupId}},
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
						headers: {"groupId": ${groupId}},
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
			selectable : "single",
			remove:function(e){
				if(!confirm('Bạn có muốn xóa ?')){
					e.preventDefault();
				}
			},
			dataBound : function(){
				console.log(dataSourceServiceConfig.view());
				console.log($("#listViewTTHC > li"));
				if(firstLoadServiceConfig){
					if(dataSourceServiceConfig.view()[0]){
						var firstChild = dataSourceServiceConfig.view()[0].id;

						$("#serviceConfigListView > li").removeClass("k-state-selected");
						$("#serviceConfigListView > li:first-child").addClass("k-state-selected");

						$("#itemServiceConfigId").val(firstChild);
						formControl(firstChild);
					}
				}
				
				firstLoadServiceConfig =false;
			}
		});

		$(document).on("click", ".service-config-item", function(event){
			var id = $(this).attr("data-pk");

			$("#itemServiceConfigId").val($(this).attr("data-pk"));
			$("#serviceConfigListView > li").removeClass("k-state-selected");

			$(this).parent().parent().addClass("k-state-selected");
			console.log("itemServiceConfigId: "+$("#itemServiceConfigId").val());
			formControl(id);
		});

		$("#pager").kendoPager({
			dataSource:dataSourceServiceConfig,
			input: true,
			numeric: false,
			messages: {
				empty: "Không có kết quả phù hợp!",
				display: "Hiển thị {0}-{1} trong {2} bản ghi",
				page: "",
				of: "/ {0}"
			}
		});

		$("#keywordSearchServiceConfig").change(function(){
			dataSourceServiceConfig.read({
				"keyword":$("#keywordSearchServiceConfig").val()
			});
		});

		$("#keywordSearchServiceConfig").kendoAutoComplete({
			dataTextField : "serviceName",
			dataSource: {
				transport : {
					read : {
						url : "${api.server}/serviceconfigs",
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
						id : "serviceConfigId"
					}
				}
			},
			filter: "contains",
			placeholder: "Nhập từ khóa",
			noDataTemplate: 'Không có dữ liệu'
		});

		$(function() {
			$("[data-role=combobox]").each(function() {
				var widget = $(this).getKendoComboBox();
				widget.input.on("focus", function() {
					widget.open();
				});
			});
		});


		$(document).on("click", ".item-serviceconfig-delete", function(event){
			event.preventDefault();
			var id = $(this).attr("data-pk");
			if(id && id > 0){
				var item =  dataSourceServiceConfig.get(id);
				var cf = confirm("Bạn có muốn xóa "+item.serviceCode+"!");
				if(cf){
					$.ajax({
						url : "${api.server}"+"/serviceconfigs/"+id,
						dataType : "json",
						type : "DELETE",
						headers: {"groupId": ${groupId}},
						success : function(result){
							var isDelete = fnCheckIsDeleteServiceConfig(id);
							if(isDelete){
								var currentItemId = $("#itemServiceConfigId").val();
								if(item){
									var index = dataSourceServiceConfig.remove(item);
								}

								if(currentItemId === id){
									$("#itemServiceConfigId").val("");
									formControl();
								}

								notification.show({
									message: "Yêu cầu được thực hiện thành công"
								}, "success");
								
							}else {
								notification.show({
									message: "Dịch vụ công đã được thêm quy trình xác lập nên không thể xóa!"
								}, "error");
							}
							
						},
						error : function(xhr){
							notification.show({
								message: "Xẩy ra lỗi, vui lòng thử lại"
							}, "error");
						}
					});
				}else {
					return;
				}
			}
		});

		var fnCheckIsDeleteServiceConfig = function(serviceConfigId){
			var isDelete = true ;
			if(serviceConfigId){
				$.ajax({
					url : "${api.server}/serviceconfigs/"+serviceConfigId+"/processes",
					dataType : "json",
					type : "GET",
					headers: {"groupId": ${groupId}},
					async : false,
					success : function(result){
						if(result.data){
							isDelete = false;
						}
					},
					error : function(result){
						isDelete = false;
					}
				});
			}
			return isDelete;
		}

		$(document).on("click", "#btnAddServiceConfig", function(event){

			event.preventDefault();
			$("#itemServiceConfigId").val("");
			console.log("add click");
			console.log($("#itemServiceConfigId").val());
			formControl();
		});

		$("#btnAddServiceConfigS").click(function(){
			$("#itemServiceConfigId").val("");
			$("#serviceConfigDetail").load("",function(result){

			});
		});

		var formControl = function(dataPk){

			var url = "${ajax.serviceconfig_detail}";

			$("#serviceConfigDetail").load(url,function(result){
				if(dataPk){
					pullServiceConfigDetail(dataPk);
				}else {

				}
				funcCheckValueItem();
			});

		}

		var funcCheckValueItem = function(){
			if($("#itemServiceConfigId").val() !== ""){
				activateTabServiceConfig();
				dataSourceServiceOption.read({

				});
			}else{
				disabledTabServiceConfig();
			}
		}

		var activateTabServiceConfig=function() {
			$("#serviceconfig-tabstrip > li").removeClass("disabled");
			$("#serviceconfig-tabstrip > li > a").attr("data-toggle","tab");

		}

		var disabledTabServiceConfig = function () {
			$("#serviceconfig-tabstrip > li").addClass("disabled");
			$("#serviceconfig-tabstrip > li > a").removeAttr("data-toggle");
			$("#serviceconfig-tabstrip > li").first().removeClass("disabled");
			$("#serviceconfig-tabstrip > li > a").first().attr("data-toggle","tab");
		}

		var updateServieConfig = function(dataPk){
			console.log($("#serviceInfoId").val());
			console.log(dataPk);
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



		var addServiceConfig = function(){
			dataSourceServiceConfig.transport.create({
				"serviceInfoId":$("#serviceInfoId").val(),
				"govAgencyCode":$("#govAgencyCode").val(),
				"serviceName":$("#serviceName").val(),
				"serviceLevel":$("#serviceLevel").val(),
				"serviceInstruction":$("#serviceInstruction").val(),
				"authentication":$("#authentication").val(),
				"serviceUrl":$("#serviceUrl").val()
			});
		};

		//edit or delete serviceconfig option
		$(document).on("click", "._itemServiceConfig_option_btnEdit", function(event){
			event.preventDefault();
			console.log("edit service config");
			$("#frmEstablishedProcess").show();
			var id = $(this).attr("data-pk");
			var item = dataSourceServiceOption.get(id);
			pullServiceConfigOptionDetail(item);
			$("#itemServiceConfigOption").val(id);
		});

		$(document).on("click", "._itemServiceConfig_option_btnDelete", function(event){
			event.preventDefault();
			var id = $(this).attr("data-pk");
			var idServiceConfig = $("#itemServiceConfigId").val();
			if(idServiceConfig > 0 && id > 0){
				var itemDelete = dataSourceServiceOption.get(id);
				var cf = confirm("Bạn có muốn xóa !");
				if(cf){
					$.ajax({
						url : "${api.server}/serviceconfigs/"+idServiceConfig+"/processes/"+id,
						dataType : "json",
						type : "DELETE",
						headers: {"groupId": ${groupId}},
						success : function(result){
							if(itemDelete){
								var index = dataSourceServiceOption.remove(itemDelete);
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
				}else{
					return;
				}
			}
		});

	})(jQuery);
</script>
