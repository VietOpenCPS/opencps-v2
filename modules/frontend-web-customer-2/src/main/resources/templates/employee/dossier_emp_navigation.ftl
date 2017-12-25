<#-- <#include "init.ftl">

<div class="dossier-emp-navigator-wrapper">

	<div class="row">

		<div class="col-sm-12">

			<button data-toggle="modal" href="${(url.dossierEmp_update)!}" 
				data-target="#modal" class="btn btn-active btn-block" name="add-dossier-emp" type="button">
				
				<i class="fa fa-plus-circle" aria-hidden="true"></i>

				Tạo hồ sơ mới
			
			</button>

		</div>

	</div>
	
	<div class="row MT15" id="dossier-emp-navigator-filter-wrapper">
		
		<div class="col-md-12 MB5">
				
			<select id="dossier-emp-nav-selectbox-by-service" 
				name="service" class="form-control dossier-emp-nav-selectbox">
			</select>
			
		</div>
		
		<div class="col-md-12 MB5">
				
			<input id="dossier-emp-nav-selectbox-by-dossierNo" placeholder="Mã tiếp nhận"
				name="dossierNo" class="form-control dossier-emp-nav-selectbox" />
			
		</div>
		
		<div class="hidden col-md-12 MB5">
				
			<input id="dossier-emp-nav-selectbox-by-startDate" placeholder="Từ ngày"
				data-datePicker
				data-datePicker-msg="Ngày không hợp lệ!"
				name="startDate" class="form-control dossier-emp-nav-selectbox" />
			
		</div>
		
		<div class="hidden col-md-12 MB5">
				
			<input id="dossier-emp-nav-selectbox-by-endDate" placeholder="Đến ngày"
				data-datePicker
				data-datePicker-msg="Ngày không hợp lệ!"
				name="endDate" class="form-control dossier-emp-nav-selectbox" />
			
		</div>
		
		<div class="col-sm-12">

			<ul class="contact-navigator dossier-emp-navigator have-bagde" id="dossier-emp-navigator-filter">

				<#if constants.dossierStatus?has_content>
					<#list constants.dossierStatus as odossierStatus>
						
						<li class="MB10" data-text="${odossierStatus.text}" data-value="${odossierStatus.value}">

							<span class="nav-icon">

								<i class="fa fa-id-card fa-1.5x" aria-hidden="true"></i>

							</span>${odossierStatus.text}

							<span class="bagde">${odossierStatus.count}</span>

						</li>
						
					</#list>
				</#if>

				<input name="dossier-emp-nav-selected-param" type="hidden">
				<input name="dossier-emp-nav-selected-value" type="hidden">

			</ul>

		</div>

	</div>
	
</div>

<script type="text/javascript">

(function($) {

	function dossierEmpAutocompleteSearch(params) {
		
		var dossierEmpListview = $("#dossier-emp-listview").data("kendoListView");

		if (dossierEmpListview!=null && dossierEmpListview!="" && dossierEmpListview!== undefined) {
			
			$("#dossier-emp-listview")[0]["filterKeys"] = params;
			dossierEmpListview.dataSource.read();

		}
		
	}
	
	function resetfilterKeys(){
		
		$("#dossier-emp-nav-selectbox-by-service").data("kendoComboBox").text("");

		$(".dossier-emp-nav-selectbox").each(function(index, element) {
			$( element ).val("");
		});

		dossierEmpMenuFilter.getPanelBar().clearSelection();
		dossierEmpMenuFilter.filterKeys = {};
	
	}

	var dossierEmpMenuFilter = $('#dossier-emp-navigator-filter-wrapper').filterMenu({

		inputClass: ".dossier-emp-nav-selectbox",
		panelBarId: "#dossier-emp-navigator-filter",
		keywordClass: ".dossier-emp-keyword",
		doFilter: function(params){
			
			dossierEmpAutocompleteSearch(params);

		},
		refreshSelected: resetfilterKeys

	}).register();
	
	dossierEmpMenuFilter._createPanelBar({
		animation: {
			collapse: {
				duration: 500
			}
		},
		select: function(e){

			dossierEmpMenuFilter.filterKeys["panelBar_text"] = $(e.item).attr('data-text');
			dossierEmpMenuFilter.filterKeys["status"] = $(e.item).attr('data-value');
			
			$( dossierEmpMenuFilter.options.panelBarId )[0]["params"] = dossierEmpMenuFilter.filterKeys;

		}
		
	});
	
	dossierEmpMenuFilter.getPanelBar().select( $("#dossier-emp-navigator-filter").find("li").last() );

	$("#dossier-emp-nav-selectbox-by-startDate").kendoDatePicker({
		start: "month",
		depth: "year",
		format: "dd/MM/yyyy",
		dateInput: false
	});

	$("#dossier-emp-nav-selectbox-by-endDate").kendoDatePicker({
		start: "month",
		depth: "year",
		format: "dd/MM/yyyy",
		dateInput: false
	});

	var getServiceBaseUrl = "${api.endpoint}/serviceinfos";
	$("#dossier-emp-nav-selectbox-by-service").kendoComboBox({
		
		placeholder: "Chọn thủ tục hành chính...",
		dataTextField: "serviceName",
		dataValueField: "serviceCode",
		filter: "contains",
		dataSource: {

			transport: {
				read: function(options) {
					
					$.ajax({
					  
						url: getServiceBaseUrl,
						dataType: "json",
						type: 'GET',
						headers: {
							"groupId": ${groupId}
						},
						data: {
							sort: "serviceCode"
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

})(jQuery);
</script> -->