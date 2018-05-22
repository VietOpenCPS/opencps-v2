<#include "init.ftl">

<div class="row MB10">
				
	<div class="col-xs-12 col-sm-12 one-row">
	
		<button class="btn btn-active MR10 border-rad-4 visible-xs"
			id="employee-menu-toggle">

			<i class="fa fa-bars" aria-hidden="true"></i>

		</button>
		
		<button data-toggle="modal" class="btn btn-active btn-block border-rad-4"
			href="${(url.employeePortlet.employee_create)!}" data-target="#modal-lg" id="employee-create-btn">

			<i class="fa fa-user" aria-hidden="true"></i>
			<span class="p-xxs" >Thêm nhân sự</span> 
			<i class="fa fa-plus-circle"></i> 

		</button>
	
	</div>

</div>

<div id="employee-filters-wrapper" class="contact-navigator-wrapper">

	<ul id="employee-nav-container" class="contact-navigator">
		
	</ul>

	<div class="MB10" id="mEmployeeLeftTop">
		<div class="row MB10">

			<div class="col-md-12 MB5">
				
				<select id="employee-nav-selectbox-by-jobpos" 
					name="jobpos" class="form-control employee-nav-selectbox">
				</select>
				
			</div>

			<div class="col-md-12 MB5">
				
				<select id="employee-nav-selectbox-by-working-status"
					name="status" class="form-control employee-nav-selectbox">
				</select>
				
			</div>

		</div>

	</div>

</div>

<script type="text/x-kendo-tmpl" id="employee-panelbar-template">
	
	<div class="MB10">
		
		# if( item.treeLevel == 0 ) { # 
			<span class="nav-icon">

				<i class="fa fa-book fa-1.5x" aria-hidden="true"></i>

			</span>
		# } #
		
		<span class="panel-item-container" 
			data-value="#=item.workingUnitId#"
			data-text="#=item.name#"
		>
			#=item.name# 
		</span>

	</div>

</script>

<script>

$(function() {

	'use strict';

	function searchEmployee(params){

		var employeeListView = $("#employee-listview").getKendoListView();
		
		$("#employee-listview")[0]["filterKeys"] = params;
		employeeListView.dataSource.read();
		
	}

	var employeeMenuFilter = $('#employee-filters-wrapper').filterMenu({

		inputClass: ".employee-nav-selectbox",
		panelBarId: "#employee-nav-container",
		keywordClass: ".employee-keyword",
		doFilter: function(params){
			
			searchEmployee(params);

		},
		refreshSelected: resetfilterKeys

	}).register();

	var getWorkingUnitBaseUrl = "${api.endpoint}/workingunits";
	//Init panelbar
	$.ajax({
	
		url: getWorkingUnitBaseUrl,
		dataType: "json",
		type: 'GET',
		async: false,
		headers: {
			"groupId": ${groupId}
		},
		data: {
			sort: "treeIndex",
			order: true
		},
		success: function(result) {
			
			var dataTree =[{workingUnitId: "0", name: "Tất cả", parentWorkingUnitId: 0, level: 0}];

			if (result.total > 0) {
				dataTree = result.data.concat(dataTree);
			}

			dataTree =  arrayToTree( dataTree, "workingUnitId", "parentWorkingUnitId", "childrens_item");

			var dataSourceWorkingUnit = new kendo.data.HierarchicalDataSource({

				data: dataTree,
				schema: {
					model: {
						id: "workingUnitId",
						children: "childrens_item"
					}
				}

			});
			
			employeeMenuFilter._createPanelBar({
				expandMode: "single",
				select: function(e){

					var container = $( e.item ).find(".panel-item-container");
					var dataValue = $(container).attr("data-value");
					var dataText = $(container).attr("data-text");

					if (dataValue == "0") {
						delete employeeMenuFilter.filterKeys[ "workingunit" ];
					} else {
						employeeMenuFilter.filterKeys[ "workingunit" ] = dataValue;
					}
					employeeMenuFilter.filterKeys.panelBar_text = dataText;
					
					$( employeeMenuFilter.options.panelBarId )[0]["params"] = employeeMenuFilter.filterKeys;

				},
				dataSource: dataSourceWorkingUnit,
				template: function(data){
					return kendo.template($("#employee-panelbar-template").html())(data);
				},
				
			});

			employeeMenuFilter.getPanelBar().select( $("#employee-nav-container").find("li").first() );
			
		}
	
	});
	
	$("#employee-nav-selectbox-by-working-status").kendoComboBox({
		placeholder: "Tình trạng làm việc...",
		dataTextField: "text",
		dataValueField: "value",
		dataSource: [
			{text: "Đang làm việc", value: "1" },
			{text: "Đã nghỉ việc", value: "0" }
		]
		
	});
	
	var getJobPosBaseUrl = "${api.endpoint}/jobpos";
	
	$("#employee-nav-selectbox-by-jobpos").kendoComboBox({
		
		placeholder: "Lọc theo chức vụ",
		dataTextField: "title",
		dataValueField: "jobPosId",
		filter: "contains",
		dataSource: {

			transport: {
				read: function(options) {
					
					$.ajax({
					  
						url: getJobPosBaseUrl,
						dataType: "json",
						type: 'GET',
						headers: {
							"groupId": ${groupId}
						},
						data: {
							sorts: "title",
							order: "true"
						},
						success: function(result) {
							
							result["data"] = result.total==0 ? []: result["data"];
							options.success(result);
							
						},
						error: function(xhr, st, pr){
							options.success({data:[], total: 0});
						}
					
					});

				}
			},
			schema: {
				data: "data",
				total: "total"
			}
		}
		
	});

	function resetfilterKeys(){

		$("#employee-nav-selectbox-by-jobpos").data("kendoComboBox").text("");
		$("#employee-nav-selectbox-by-working-status").data("kendoComboBox").text("");
		
		employeeMenuFilter.getPanelBar().clearSelection();
		employeeMenuFilter.filterKeys = {};
		
	}

});

</script>