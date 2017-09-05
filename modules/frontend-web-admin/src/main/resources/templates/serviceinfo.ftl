<#include "init.ftl">

<div>
	<div class="row MB20" >
		<div class="col-sm-6">
			<div class="row">
				<div class="col-sm-5">
					<button class="k-button btn-primary" id="btnAddServiceInfo"><i class="glyphicon glyphicon-plus"></i> Thêm thủ tục </button>
				</div>
				<div class="col-sm-7">
					<select class="form-control" id="administrationCodeSearch" name="administrationCodeSearch">
						<option value=""></option>
						<#list administrations as item>
						<option value="${item.id}">${item.name}</option>
						</#list> 
					</select>
				</div>
			</div>
		</div>
		<div class="col-sm-3">
			<select class="form-control" id="domainCodeSearch" name="domainCodeSearch">
				<option value=""></option>
				<#list domains as item>
				<option value="${item.id}">${item.name}</option>
				</#list> 
			</select>
		</div>
		<div class="col-sm-3">
			<div class="input-group">
				<input id="keyword" class="form-control" name="keyword" placeholder="Nhap tu khoa tim kiem"><span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
			</div>
		</div>
	</div>
	<div class="row" style="border-bottom: 1px solid #ccc;">
	</div>
	<div class="row">
		<div class="col-sm-3 panel">
			<div class='panel-body'>
				<ul class='ul-with-border'>
					<div id='listViewTTHC'></div>
				</ul>
				<div id='pagerTTHC' class='k-pager-wrap k-widget k-floatwrap'></div>
			</div>
		</div>
		<div class="col-sm-9 panel panel-body" id="serviceInfoFileTempalte">
			
		</div>

		<script type="text/x-kendo-template" id="templateTTHC" >
			<li>
				<div class="row">
					<div class="col-sm-10 serviceinfo-filetemplate-item" data-pk="#:id#">
						<div class="row">
							<p><i>#:serviceName#</i></p>
						</div>
						<div class="row">
							<p>#:domainName#</p>
						</div>
						<div class="row">
							<p>#:administrationName#</p>
						</div>
						<div class="row">
							<p>
								# if (activeStatus==0) { #
								Đang biên soạn 
								# }else if(activeStatus==1){ #
								Đang có hiệu lực
								#}else{#
								Hết hiệu lực thi hành
								#}#
							</p>
						</div>
					</div>
					<div class="col-sm-2 text-center MT15">
						<a href="javascript:;" class="_itemServiceinfo_btnEdit" data-pk="#:id#">
							<i class="glyphicon glyphicon-pencil"></i> 
						</a>
						<a href="javascript:;" class="_itemServiceinfo_btnDelete" data-pk="#:id#">
							<i class="glyphicon glyphicon-remove"></i> 
						</a>
					</div>
				</div>
				
			</div>
		</li>
	</script>

	<div id="serviceInfoDialog" class="modal fade serviceInfoDialog" role="dialog">
	</div>

	<input type="hidden" name="itemServiceInfoId" id="itemServiceInfoId">
	<span id="popupNotification"></span>	

	<script type="text/javascript">

		var popupNotification = $("#popupNotification").kendoNotification().data("kendoNotification");

		var dataSourceTTHC=new kendo.data.DataSource({
			transport:{
				read: function(options) {
					$.ajax({
						url: "${api.server}"+"/serviceinfos",
						dataType: "json",
						type:"GET", 
						data:{
							keywords: options.data.keywords,
							page: options.data.page,
							pageSize: options.data.pageSize
						},
						success: function(result) {
							options.success(result);
						},
						error: function(result) {
							options.error(result);
						}
					});
				},
				create: function(options) {
					$.ajax({
						url: "${api.server}"+"/serviceinfos",
						dataType: "json",
						type:"POST",
						data: {
							serviceCode:options.serviceCode,
							serviceName:options.serviceName,
							processText:options.processText,
							methodText:options.methodText,
							dossierText:options.dossierText,
							conditionText:options.conditionText,
							durationText:options.durationText,
							resultText:options.resultText,
							administrationCode:options.administrationCode,
							domainCode:options.domainCode,
							activeStatus:options.activeStatus,
							fileTemplates:options.fileTemplates
						},
						success: function(result) {
							popupNotification.show("Success", "success");
							addServiceInfoIfSuccess(result);
						},
						error: function(result) {
							popupNotification.show("Error", "error");
						}
					});
				},
				update: function(options) {
					console.log(options);
					$.ajax({
						url: "${api.server}/serviceinfos/"+options.serviceinfoid,
						dataType: "json",
						type:"PUT",
						data: {
							serviceinfoid:options.serviceinfoid,
							serviceCode:options.serviceCode,
							serviceName:options.serviceName,
							processText:options.processText,
							methodText:options.methodText,
							dossierText:options.dossierText,
							conditionText:options.conditionText,
							durationText:options.durationText,
							resultText:options.resultText,
							administrationCode:options.administrationCode,
							domainCode:options.domainCode,
							activeStatus:options.activeStatus,
							fileTemplates:options.fileTemplates
						}, 
						success: function(result) {
							updateServieInfoIfSuccess($("#itemServiceInfoId").val(),result);
							popupNotification.show("Success", "Success");
						},
						error: function(result) {
							popupNotification.show("Error","Error");
						}
					});
				},
				destroy: function(options) {
					$.ajax({
						url: "${api.server}/serviceinfos/"+options.serviceInfoId,
						dataType: "json",
						type:"DELETE",
						data: {
							serviceInfoId:options.serviceInfoId,
						}, 
						success: function(result) {
							var item=dataSourceTTHC.get(options.serviceInfoId);
							if(item!=null){
								dataSourceTTHC.remove(item);
							}
							popupNotification.show("Success", "success");

						},
						error: function(result) {
							popupNotification.show("Error", "error");
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
					id:"serviceInfoId"
				}
			},
			pageSize: 5,
			serverPaging: false,
			serverSorting: false,
			serverFiltering: false
		});

		$("#listViewTTHC").kendoListView({
			dataSource:dataSourceTTHC,
			template:kendo.template($("#templateTTHC").html()),
			remove: function(e) {
				if(!confirm('Bạn có muốn xóa ' + e.model.get('serviceNo') + '?')){
					e.preventDefault();
				}
			},
			seperatorColor:"transparent"
		});

		$("#pagerTTHC").kendoPager({
			dataSource:dataSourceTTHC
		});

		$("#administrationCodeSearch").kendoComboBox({
			placeholder:"Chọn cơ quan",
			dataTextField:"administrationName",
			dataValueField:"administrationCode",
			change:onChangeAdministrationCodeSearch,
			filter:"contains"
		});

		$("#domainCodeSearch").kendoComboBox({
			placeholder:"Chọn lĩnh vực",
			dataTextField:"domainName",
			dataValueField:"domainCode",
			filter:"contains",
		});

		var onChangeDomainCodeSearch=function(){
			$("#listViewTTHC").getKendoListView().dataSource.transport.read({
				"domainCodeSearch":$("#domainCodeSearch").val(),
				"administrationCodeSearch":$("#administrationCodeSearch").val(),
				"keyword":$("#keyword").val()
			});
		}

		var onChangeAdministrationCodeSearch=function(){
			$("#listViewTTHC").getKendoListView().dataSource.transport.read({
				"domainCodeSearch":$("#domainCodeSearch").val(),
				"administrationCodeSearch":$("#administrationCodeSearch").val(),
				"keyword":$("#keyword").val()

			});
			console.log("change");
		}

		$(function() {
			$("[data-role=combobox]").each(function() {
				var widget = $(this).getKendoComboBox();
				widget.input.on("focus", function() {
					widget.open();
				});
			});
		});

		$(document).on("click", "._itemServiceinfo_btnEdit", function(event){

			event.preventDefault();
			$("#itemServiceInfoId").val($(this).attr("data-pk"));
			formControl($(this).attr("data-pk"));
		});

		$(document).on("click", "#btnAddServiceInfo", function(event){

			event.preventDefault();
			$("#itemServiceInfoId").val("");
			formControl();
		});

		var formControl = function(dataPk){

			var url = "${ajax.serviceinfo_form}";

			$("#serviceInfoDialog").load(
				url,
				function(result){

					$("#serviceInfoDialog").modal({show: true});

					$("#btnCancleServiceInfo").click(function(e){
						e.preventDefault();
						$("#serviceInfoDialog").modal("hide");
					});
					var validator = $("#serviceInfoForm").kendoValidator().data("kendoValidator");

					$("form").submit(function(event) {
						event.preventDefault();
						if (validator.validate()) {
							if (dataPk){
								updateServieInfo(dataPk);
							} else {
								addServiceInfo();
							}

							$("#serviceInfoDialog").modal("hide");

						} else {
							return false;
						}
					});
					setDefaultValueMultiSelect(dataPk);
				}
				);
		}

		var updateServieInfo = function(dataPk){
			dataSourceTTHC.transport.update({
				"serviceinfoid":dataPk,
				"serviceCode":$("#serviceCode").val(),
				"serviceName":$("#serviceName").val(),
				"processText":$("#serviceProcess").val(),
				"methodText":$("#methodText").val(),
				"dossierText":$("#dossierText").val(),
				"conditionText":$("#conditionText").val(),
				"durationText":$("#durationText").val(),
				"resultText":$("#resultText").val(),
				"administrationCode":$("#administrationCode").val(),
				"domainCode":$("#domainCode").val(),
				"activeStatus":$("#activeStatus").val(),
				"fileTemplates":$("#serviceInfoTemplate").data("kendoMultiSelect").value().join()
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

			});
		}

		var addServiceInfo = function(){
			dataSourceTTHC.transport.create({
				"serviceCode":$("#serviceCode").val(),	
				"serviceName":$("#serviceName").val(),
				"processText":$("#serviceProcess").val(),
				"methodText":$("#methodText").val(),
				"dossierText":$("#dossierText").val(),
				"conditionText":$("#conditionText").val(),
				"durationText":$("#durationText").val(),
				"resultText":$("#resultText").val(),
				"administrationCode":$("#administrationCode").val(),
				"domainCode":$("#domainCode").val(),
				"activeStatus":$("#activeStatus").val(),
				"fileTemplates":$("#serviceInfoTemplate").data("kendoMultiSelect").value().join()
			});
		};

		var addServiceInfoIfSuccess=function(result){
			dataSourceTTHC.add({
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

		var setDefaultValueMultiSelect=function(dataPk){
			if(dataPk>0){
				var item=dataSourceTTHC.get(dataPk);
				if(item!=null){
					var arrFileTemplate=new Array();
					var fileTemplates=item.fileTemplates;
					for(var i=0;i<fileTemplates.length;i++){
						arrFileTemplate[i]=fileTemplates[i].fileTemplateId;
					}
					var multiselect = $("#serviceInfoTemplate").data("kendoMultiSelect");
					multiselect.value(arrFileTemplate);
				}
			}
		}

		$(document).on("click", ".serviceinfo-filetemplate-item", function(event){
			var id = $(this).attr("data-pk");
			$("#itemServiceInfoId").val($(this).attr("data-pk"));
			$("#serviceInfoFileTempalte").load(
				"${ajax.serviceinfo_filetemplate}",
				function(result){
					$("#listViewFileTemplate").getKendoListView().dataSource.read({
						id: id
					});

					var item=dataSourceTTHC.get(id);
					var viewModel = kendo.observable({	
						serviceInfoId: id,
						serviceCode:item.serviceCode,
						serviceName:item.serviceName,
						administrationName:item.administrationName,
						domainName:item.domainName,
						activeStatus:function(){
							if (item.activeStatus==0) { 
								return "Đang biên soạn"; 
							}else if(item.activeStatus==1){ 
								return "Đang có hiệu lực";
							}else{
								return "Hết hiệu lực thi hành";
							}
						}
					});

					kendo.bind($("#serviceInfoDetail"), viewModel);
				}
				);

			
		});
		
		$(document).on("click","._itemServiceinfo_btnDelete",function(event){
			dataSourceTTHC.transport.destroy({
				serviceInfoId:$(this).attr("data-pk")
			});
		});
	</script>
</div>
</div>
