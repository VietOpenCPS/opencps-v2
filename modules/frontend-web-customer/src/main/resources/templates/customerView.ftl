<#------------- Phần VIEW --------------->
	<#-- WRAPPER ELEMENT -->
	<div id="appManagerDossier"></div>

	<#-- layout template -->
	<script type="text/x-kendo-template" id="layoutTemplate">
		<div id="panel_list" class="col-sm-3 PL0"></div>
		
		<div id="mainType1" class="col-sm-9 P0">
			<div id="main_section" class="col-sm-9 P0">
				<i class="fa fa-spinner" aria-hidden="true"></i>
			</div>
			<div id="sidebar_list" class="col-sm-3 PR0"></div>
		</div>

		<div id="mainType2" class="col-sm-9 P0"></div>
	</script>

	<#-- panel list template -->
	<script type="text/x-kendo-template" id="panelTemplate">
		<div class="row">
			<div class="col-sm-12">
				<button class="btn btn-active form-control" id="btn_create_new_dossier" data-bind = "click: load_serviceConfig">Tạo hồ sơ mới</button>
			</div>
			<div class="col-sm-12">
				<ul id="profileStatus" class="ul-default ul-with-left-icon" style="margin-left: 10px;">
					<li dataPk='New' class='itemStatus PT10' data-bind="click: filterStatus">
						<i class='fa fa-folder-o' aria-hidden='true'></i>  
						<a href='javascript:;' > Hồ sơ mới</a>
					</li>
					<li dataPk='Receiving' class='itemStatus' data-bind="click: filterStatus">
						<i class='fa fa-folder-o' aria-hidden='true'></i>  
						<a href='javascript:;' > Hồ sơ đang thực hiện</a>
					</li> 
					<li dataPk='Waiting' class='itemStatus' data-bind="click: filterStatus">
						<i class='fa fa-folder-o' aria-hidden='true'></i>  
						<a href='javascript:;' > Hồ sơ chờ bổ sung</a>
					</li>
					<li dataPk='Paying' class='itemStatus' data-bind="click: filterStatus">
						<i class='fa fa-folder-o' aria-hidden='true'></i>  
						<a href='javascript:;' > Hồ sơ chờ thanh toán</a>
					</li>
					<li dataPk='Done' class='itemStatus' data-bind="click: filterStatus">
						<i class='fa fa-folder-o' aria-hidden='true' ></i>  
						<a href='javascript:;' > Hồ sơ đã kết thúc</a>
					</li>
				</ul>
			</div>
			<div class="col-sm-12">
				<div class="form-group">
					<input class="form-control" name="govAgency" id="govAgency" data-role="combobox" data-placeholder="Chọn cơ quan" data-text-field="govAgencyName" data-value-field="govAgencyCode" data-source="dataGovAgency" data-bind="events: { change: eventLookup}">
				</div>
			</div>
			<div class="col-sm-12 MT20">
				<div class="form-group">
					<input class="form-control" name="serviceInfo" id="serviceInfo" data-role="combobox" data-placeholder="Chọn thủ tục hành chính" data-text-field="serviceName" data-value-field="serviceCode" data-bind="source:dataServiceInfo, events: { change: eventLookup}">
				</div>
			</div>
			
			<div class="col-sm-12">
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<input class="form-control" name="year" id="year" data-role="combobox" data-placeholder="Năm" data-bind="source:dataYear, events: { change: eventLookup}">
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<input class="form-control" name="month" id="month" data-role="combobox" data-placeholder="Tháng" data-bind="source:dataMonth, events: { change: eventLookup}">
						</div>
					</div>
				</div>
			</div>
		</div>
	</script>

	<#-- TEMPLATE TRANG QUẢN LÝ HỒ SƠ/ C-04 -->
	<#include "manageDossierView.ftl">