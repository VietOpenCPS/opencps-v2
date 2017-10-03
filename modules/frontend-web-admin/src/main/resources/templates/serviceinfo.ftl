<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row">
	<div class="col-sm-3 panel">
		<div class='panel-body'>
			<div class="row">
				<button class="k-button btn-primary form-control MB5" id="btnAddServiceInfo"><i class="glyphicon glyphicon-plus"></i> Thêm thủ tục </button>
				<select class="form-control" id="administrationCodeSearch" name="administrationCodeSearch">
					<option value=""></option>
					<#list administrations as item>
					<option value="${item.id}">${item.name}</option>
					</#list> 
				</select>
				<select class="form-control" id="domainCodeSearch" name="domainCodeSearch">
					<option value=""></option>
					<#list domains as item>
					<option value="${item.id}">${item.name}</option>
					</#list> 
				</select>
				<div class="input-group">
					<input id="keyword" class="form-control" name="keyword" placeholder="Nhap tu khoa tim kiem"><span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
				</div>
			</div>
			<ul class='ul-with-border MT10'>
				<div id='listViewTTHC'></div>
			</ul>
			<div id='pagerTTHC' class='k-pager-wrap k-widget k-floatwrap'></div>
			<script type="text/x-kendo-template" id="templateTTHC" >
				<li class="PL0 PR0">
					<div class="row">
						<div class="col-sm-12 P0 MB5">
							#:serviceCode# 
							#if(public) {#
							<i class="fa fa-check ML5" aria-hidden="true"></i> 
							#} #
							<i class="fa fa-trash pull-right _itemServiceinfo_btnDelete" data-pk="#:id#"></i>
						</div>
						<div class="col-sm-12 P0">
							<p class="showServiceinfoDetail" data-pk="#:id#">#:serviceName#</p>
						</div>
						<div class="col-sm-12 P0">
							<i class="fa fa-suitcase" aria-hidden="true"></i> <span>#:domainName#</span>  
						</div>
						<div class="col-sm-12 P0">
							<i class="fa fa-steam-square" aria-hidden="true"></i> <i>#:administrationName#</i>
						</div>
					</div>
				</li>
			</script>
		</div>
	</div>
	<div class="col-sm-9 panel panel-body" id="serviceinfo_detail">

	</div>
	<input type="hidden" name="itemServiceInfoId" id="itemServiceInfoId">	
</div>
<script type="text/javascript">
	var dataSourceTTHC=new kendo.data.DataSource({
		transport:{
			read: function(options) {
				$.ajax({
					url: "${api.server}"+"/serviceinfos",
					dataType: "json",
					type:"GET", 
					data:{
						domain: options.data.domain,
						administration: options.data.administration,
						keyword: options.data.keyword,
						page: options.data.page,
						pageSize: options.data.pageSize
					},
					success: function(result) {
						console.log(options.data);
						options.success(result);
					},
					error: function(result) {
						options.error(result);
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
				id:"serviceinfoId"
			}
		},
		pageSize: 5,
		serverPaging: false,
		serverSorting: false,
		serverFiltering: false,
		change : function(e){
			console.log("change");
			console.log(e);
			console.log(e.items[0].id);
			$("#itemServiceInfoId").val(e.items[0].id);
			formControl(e.items[0].id);
			
		}
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
		dataSource:dataSourceTTHC,
		input: true,
		numeric: false,
		messages: {
			empty: "Không có kết quả phù hợp!",
			display: "Hiển thị {0}-{1} trong {2} bản ghi",
			page: "",
			of: "/ {0}"
		}
	});

	$("#administrationCodeSearch").kendoComboBox({
		placeholder:"Chọn cơ quan",
		dataTextField:"administrationName",
		dataValueField:"administrationCode",
		change:function(){
			dataSourceTTHC.read({
				"domain": $("#domainCodeSearch").val(),
				"administration": $("#administrationCodeSearch").val(),
				"keyword": $("#keyword").val()
			});
		},
		filter:"contains"
	});

	$("#domainCodeSearch").kendoComboBox({
		placeholder:"Chọn lĩnh vực",
		dataTextField:"domainName",
		dataValueField:"domainCode",
		change:function(){
			dataSourceTTHC.read({
				"domain": $("#domainCodeSearch").val(),
				"administration" :$("#administrationCodeSearch").val(),
				"keyword": $("#keyword").val()
			});
		},
		filter:"contains",
	});

	$("#keyword").change(function(){
		dataSourceTTHC.read({
			"domain": $("#domainCodeSearch").val(),
			"administration": $("#administrationCodeSearch").val(),
			"keyword": $("#keyword").val()
		});
	});

	$(function() {
		$("[data-role=combobox]").each(function() {
			var widget = $(this).getKendoComboBox();
			widget.input.on("focus", function() {
				widget.open();
			});
		});
	});

	$(document).on("click", ".showServiceinfoDetail", function(event){
		event.preventDefault();
		$("#itemServiceInfoId").val($(this).attr("data-pk"));
		console.log($(this).attr("data-pk"));
		formControl($(this).attr("data-pk"));
	});

	$(document).on("click", "#btnAddServiceInfo", function(event){
		event.preventDefault();
		$("#itemServiceInfoId").val("");
		formControl();
	});

	var formControl = function(dataPk){
		$("#serviceinfo_detail").load("${ajax.serviceinfo_detail}",function(result){
			if (dataPk){
				pullDataDetail(dataPk);
			} else {
				/*addServiceInfo();*/
			}
			crtAddOrEdit();
		});
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
			"activeStatus":$("#activeStatus").val()
			/*"fileTemplates":$("#serviceInfoTemplate").data("kendoMultiSelect").value().join()*/
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
			"activeStatus":$("#activeStatus").val()
			/*"fileTemplates":$("#serviceInfoTemplate").data("kendoMultiSelect").value().join()*/
		});
	};

	var addServiceInfoIfSuccess=function(result){
		dataSourceTTHC.add({
			"serviceinfoId": result.serviceinfoId,
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

	$(document).on("click","._itemServiceinfo_btnDelete",function(event){
		var id = $(this).attr("data-pk");
		if(id && id > 0){
			var item =  dataSourceTTHC.get(id);
			var cf = confirm("Bạn có muốn xóa "+item.serviceCode+"!");
			if(cf){
				$.ajax({
					url : "${api.server}"+"/serviceinfos/"+id,
					dataType : "json",
					type : "DELETE",
					success : function(result){

						if(item){
							var index = dataSourceTTHC.remove(item);
						}
					},
					error : function(xhr){

					}
				});
			}else {
				return;
			}
		}
		
	});

	var crtAddOrEdit = function(){
		if($("#itemServiceInfoId").val() != ""){
			console.log("enabled");
			$("#add-file-template").show();
			activateTab();
		}else {
			console.log("disabled");
			$("#add-file-template").hide();
			disabledTab();
		}
	}

	var activateTab=function() {
		$("#serviceinfo-tabstrip > li").removeClass("disabled");
		$("#serviceinfo-tabstrip > li > a").attr("data-toggle","tab");

	}

	var disabledTab = function () {
		$("#serviceinfo-tabstrip > li").addClass("disabled");
		$("#serviceinfo-tabstrip > li > a").removeAttr("data-toggle");
		$("#serviceinfo-tabstrip > li").first().removeClass("disabled");
		$("#serviceinfo-tabstrip > li > a").first().attr("data-toggle","tab");
	}

	//-------------------------------------------------
	
</script>
