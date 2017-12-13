<#if (Request)??>
<#include "init.ftl">
</#if>
<#if (Request)??>
	<#include "init.ftl">
</#if>
<#------------- Phần VIEW --------------->
	<#-- WRAPPER ELEMENT -->
	<div id="appEmployeeDossier" style="font-size:13px"></div>
	
	<#-- layout template -->
	<script type="text/x-kendo-template" id="layoutTemplate">
		<div id="panel_list" class="col-sm-2 PL0"></div>
		
		<div id="mainDossier" class="col-sm-10 P0">
			<div id="main_section" class="col-sm-12 P0" style="background: #ffffff;">
			</div>
		</div>

		<div id="mainDossierDetail" class="col-sm-10 P0"></div>

		<button id="changeLayout">Change</button>
	</script>

	<#-- panel list template -->
	<script type="text/x-kendo-template" id="panelTemplate">
		<div class="row">
			<div class="form-group changeLayout search-icon MB0 PL15 PR15" id="subInputSearch" style="display: none;">
				<input type="text" class="form-control" id="keyInput" placeholder="Nhập từ khóa" data-bind="events: { keyup: filterKey}">
			</div>
			<div class="col-sm-12">
				<ul id="profileStatus" class="ul-default ul-with-left-icon icon-folder">
					<li dataPk='new' class='itemStatus' data-bind="click: filterStatus">
						<i class='fa fa-folder icon-left' aria-hidden='true'></i>  
						<a href='javascript:;' > Hồ sơ chờ xử lý</a>
					</li>
					<li dataPk='receiving' class='itemStatus' data-bind="click: filterStatus">
						<i class='fa fa-folder icon-left' aria-hidden='true'></i>  
						<a href='javascript:;' > Hồ sơ sắp tới hạn xử lý</a>
					</li>
					<li dataPk='waiting' class='itemStatus' data-bind="click: filterStatus">
						<i class='fa fa-folder icon-left' aria-hidden='true'></i>  
						<a href='javascript:;' > Hồ sơ quá hạn xử lý</a>
					</li>
					<li dataPk='paying' class='itemStatus' data-bind="click: filterStatus">
						<i class='fa fa-folder icon-left' aria-hidden='true'></i>  
						<a href='javascript:;' > Hồ sơ đã xử lý</a>
					</li>
				</ul>
			</div>
			<div class="col-sm-12 MT10 filterField">
				<div class="MB10">
					<input name="govAgency" id="govAgency" data-role="combobox" data-placeholder="Chọn cơ quan quản lý" data-text-field="itemName" data-value-field="itemCode" data-source="dataGovAgency" data-bind="events: { change: eventLookup, dataBound: dataBound}">
				</div>
			</div>
			<div class="col-sm-12 filterField">
				<div class="MB10">
					<input name="serviceInfo" id="serviceInfo" data-role="combobox" data-placeholder="Chọn thủ tục hành chính" data-text-field="serviceName" data-value-field="serviceCode" data-bind="source:dataServiceInfo, events: { change: eventLookup,dataBound: dataBound}">
				</div>
			</div>
			<div class="col-sm-12 filterField">
				<div class="MB10">
					<input name="serviceInfo" id="serviceInfo" data-role="combobox" data-placeholder="Chọn vai trò" data-text-field="serviceName" data-value-field="serviceCode" data-bind="source:dataServiceInfo, events: { change: eventLookup,dataBound: dataBound}">
				</div>
			</div>
			<div class="col-sm-12 filterField">
				<div class="MB10">
					<input name="serviceInfo" id="serviceInfo" data-role="combobox" data-placeholder="Chọn trạng thái hồ sơ" data-text-field="serviceName" data-value-field="serviceCode" data-bind="source:dataServiceInfo, events: { change: eventLookup,dataBound: dataBound}">
				</div>
			</div>
			<div class="col-sm-12 filterField">
				<div class="row">
					<div class="col-sm-6 PR5">
						<div class="MB10">
							<input name="year" id="year" data-role="combobox" data-placeholder="Năm" data-text-field="year" data-value-field="valYear" data-bind="source:dataYear, events: { change: eventLookup, dataBound: dataBound}">
						</div>
					</div>
					<div class="col-sm-6 PL5">
						<div class="MB10">
							<input name="month" id="month" data-role="combobox" data-placeholder="Tháng" data-text-field="month" data-value-field="valMonth" data-bind="source:dataMonth, events: { change: eventLookup, dataBound: dataBound}">
						</div>
					</div>
				</div>
			</div>
		</div>
	</script>
	
	<#-- TEMPLATE dossierEmployee -->
	<#include "employee_dossierlist_full.ftl">
