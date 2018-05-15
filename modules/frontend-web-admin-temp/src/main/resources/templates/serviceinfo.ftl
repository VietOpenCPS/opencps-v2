<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row serviceinfo">
	<div class="col-sm-3 panel P0">
		<div class='panel-body'>
			<div class="row">
				<div class="col-sm-12">
					<button class="btn btn-active form-control MB5" id="btnAddServiceInfo"><i class="glyphicon glyphicon-plus"></i> Thêm thủ tục </button>
					<select class="form-control" id="administrationCodeSearch" name="administrationCodeSearch"></select>
					<select class="form-control" id="domainCodeSearch" name="domainCodeSearch"></select>
					<div class="form-group search-icon">
						<input type="text" id="keyword" name="keyword" class="form-control" placeholder="Nhập từ khóa">
					</div>
				</div>
				
				<div class="col-sm-12">
					<ul class='ul-with-border' id="listViewTTHC">

					</ul>
					<div id='pagerTTHC' class='k-pager-wrap k-widget k-floatwrap'></div>
					<script type="text/x-kendo-template" id="templateTTHC" >
						<li class="" data-pk="#:id#">
							<div class="row">
								<div class="col-sm-12 MB5">
									<span class="showServiceinfoDetail" data-pk="#:id#">
										<span class="" data-pk="#:id#">#:serviceCode# </span>
										#if(typeof active !== "undefined" && active) {#
										<i class="fa fa-check ML5" aria-hidden="true"></i>
										#}#
									</span>
									<i class="fa fa-trash pull-right _itemServiceinfo_btnDelete" data-pk="#:id#"></i>
								</div>
								<div>
									<div class="showServiceinfoDetail" data-pk="#:id#">
										<div class="col-sm-12">
											<p class="" data-pk="#:id#">#:serviceName#</p>
										</div>
										<div class="col-sm-12">
											<i class="fa fa-suitcase" aria-hidden="true"></i> <span class="" data-pk="#:id#">#:domainName#</span>
										</div>
										<div class="col-sm-12">
											<i class="fa fa-fort-awesome" aria-hidden="true"></i> <i class="" data-pk="#:id#">#:administrationName#</i>
										</div>
									</div>
								</div>
							</div>
						</li>
					</script>
				</div>
			</div>

		</div>
	</div>
	<div class="col-sm-9 PR0">
		<div class="panel panel-body PL0 PR0" id="serviceinfo_detail">

		</div>
	</div>
	<input type="hidden" name="itemServiceInfoId" id="itemServiceInfoId">
</div>
<script type="text/javascript">
	var loadFirst = true;
	var dataSourceTTHC=new kendo.data.DataSource({
		transport:{
			read: function(options) {
				$.ajax({
					url: "${api.server}"+"/serviceinfos",
					dataType: "json",
					type:"GET",
					headers: {"groupId": ${groupId}},
					data:{
						domain: options.data.domain,
						administration: options.data.administration,
						keyword: options.data.keyword,
						page: options.data.page,
						pageSize: options.data.pageSize,
						order : true
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
				id:"serviceInfoId"
			}
		},
		pageSize: 10,
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
		seperatorColor:"transparent",
		selectable : "single",
		dataBound : function(){
			if(loadFirst){
				if(dataSourceTTHC.view()[0]){
					var id = dataSourceTTHC.view()[0].id;

					$("#listViewTTHC > li").removeClass("k-state-selected");
					$("#listViewTTHC > li:first-child").addClass("k-state-selected");

					$("#itemServiceInfoId").val(id);
					formControl(id);
				}
			}
			loadFirst = false;
		}
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
		dataTextField:"itemName",
		dataValueField:"itemCode",
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
		change:function(){
			dataSourceTTHC.read({
				"domain": $("#domainCodeSearch").val(),
				"administration": $("#administrationCodeSearch").val(),
				"keyword": $("#keyword").val()
			});
		},
		filter:"contains",
		noDataTemplate: 'Không có dữ liệu'
	});

	$("#domainCodeSearch").kendoComboBox({
		placeholder:"Chọn lĩnh vực",
		dataTextField:"itemName",
		dataValueField:"itemCode",
		change:function(){
			dataSourceTTHC.read({
				"domain": $("#domainCodeSearch").val(),
				"administration" :$("#administrationCodeSearch").val(),
				"keyword": $("#keyword").val()
			});
		},
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
		filter:"contains",
		noDataTemplate: 'Không có dữ liệu'
	});

	$("#keyword").change(function(){
		dataSourceTTHC.read({
			"domain": $("#domainCodeSearch").val(),
			"administration": $("#administrationCodeSearch").val(),
			"keyword": $("#keyword").val()
		});
	});

	$("#keyword").kendoAutoComplete({
		dataTextField : "serviceName",
		dataSource: {
			transport : {
				read : {
					url : "${api.server}/serviceinfos",
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
				data : "data"
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

	$(document).on("click", ".showServiceinfoDetail", function(event){
		event.preventDefault();
		$("#itemServiceInfoId").val($(this).attr("data-pk"));
		console.log("show detail");
		console.log($(this).attr("data-pk"));

		$("#listViewTTHC > li").removeClass("k-state-selected");
		console.log($(this).parent().parent().parent());
		$(this).parent().parent().parent().addClass("k-state-selected");
		pullDataDetail($(this).attr("data-pk"));
		crtAddOrEdit();
	});

	$(document).on("click", "#btnAddServiceInfo", function(event){
		event.preventDefault();
		$("#itemServiceInfoId").val("");
		$("#listViewTTHC > li").removeClass("k-state-selected");
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
			"serviceInfoId":dataPk,
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
			console.log(item);
			var cf = confirm("Bạn có muốn xóa "+item.serviceCode+"!");
			if(cf){
				$.ajax({
					url : "${api.server}"+"/serviceinfos/"+id,
					dataType : "json",
					type : "DELETE",
					headers: {"groupId": ${groupId}},
					success : function(result){
						if(item){
							var index = dataSourceTTHC.remove(item);
							var currentItemId = $("#itemServiceInfoId").val();
							if(id === currentItemId){
								$("#itemServiceInfoId").val("");
								formControl();
							}
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
