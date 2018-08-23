<#if (Request)??>
	<#include "init.ftl">
</#if>
<#------------- Phần VIEW --------------->
	<#-- WRAPPER ELEMENT -->
	<div id="appManagerDossier" style="font-size:13px"></div>
	<div id="confirmSaveForm" style="background-color: #ffffff; color: black" class="MT20">
	
	</div>

	<#-- layout template -->
	<script type="text/x-kendo-template" id="layoutTemplate">
		<div id="panel_list" class="col-sm-2 PL0"></div>
		
		<div id="mainType1" class="col-sm-10 P0" >
			<div id="main_section" class="col-sm-12 P0" style="background: #ffffff;">
			</div>
		</div>

		<div id="mainType2" class="col-sm-10 P0"></div>
	</script>

	<#-- panel list template -->
	<script type="text/x-kendo-template" id="panelTemplate">
		<div class="dossier-emp-navigator-wrapper">
			<div class="row">
				<div class="col-sm-12">
					<button class="btn btn-active form-control" id="btn_create_new_dossier" data-bind = "click: load_serviceConfig">Tạo hồ sơ mới</button>
				</div>
			</div>
	
			<div class="row MT15" id="dossier-emp-navigator-filter-wrapper">
				
				<div class="col-sm-12 filterField">
					<div class="MB10">
						<input name="serviceInfo" id="serviceInfo" data-role="combobox" data-placeholder="Chọn thủ tục hành chính" data-text-field="serviceName" data-value-field="serviceCode" data-bind="source:dataServiceInfo, events: { change: eventLookup,dataBound: dataBound}">
					</div>
				</div>
				<div class="col-md-12 MB10 filterField">
					<input id="dossier-emp-nav-selectbox-by-dossierNo" placeholder="Số hồ sơ" name="dossierNo" class="form-control dossier-emp-nav-selectbox" data-bind="events: { keyup: filterDossierNo}" style="height:30px" />
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
				<#--  -->
				<div class="col-sm-12 MB10">
					<div class="accordion">
						<div class="accordion-group">
							<div class="accordion-heading" style="background-color: #14bef0;border: none;font-family: 'Roboto-Regular'">
								<a class="" style="color: #ffffff" data-toggle="collapse" href="#groupFilterStatus">
									DANH SÁCH
								</a>
							</div>
							<div id="groupFilterStatus" class="accordion-body collapse in">
								<div class="accordion-inner">
									<ul id="profileStatus" class="ul-default ul-with-left-icon icon-folder have-bagde">
										<#if constants.dossierStatus?has_content>
											<#list constants.dossierStatus as odossierStatus>
												<li dataPk='${odossierStatus.value}' dataPkSub='${odossierStatus.valueSub}' class='itemStatus' data-bind="click: filterStatus">
													<i class='fa fa-folder icon-left' aria-hidden='true'></i>  
													<span class="hover-pointer text-hover-blue dossierStatus">${odossierStatus.text}</span>
													<span class="bagde">0</span>
												</li>
											</#list>
										</#if>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<#--  -->
				<div class="col-sm-12">
					<div class="accordion">
						<div class="accordion-group">
							<div class="accordion-heading" style="background-color: #14bef0;border: none;font-family: 'Roboto-Regular'">
								<a style="color: #ffffff" data-toggle="collapse" href="#groupLookup">
									TRA CỨU
								</a>
							</div>
							<div id="groupLookup" class="accordion-body collapse in">
								<div class="accordion-inner">
									<ul id="" class="ul-default have-bagde">
										<#-- <li class="hover-pointer text-hover-blue itemStatus" id="searchCC" 
											data-bind="click: filterInvestigation" >
											<span class="dossierStatus">Tra cứu chứng chỉ</span>
										</li> -->
										<li class="hover-pointer text-hover-blue itemStatus" id="searchCC" 
											data-bind="click: filterInvestigation" >
											<span class="dossierStatus">Tra cứu hồ sơ</span>
										</li>
										<li class="hover-pointer text-hover-blue itemStatus" 
											data-bind="click: filterInvestigation">
											<span class="dossierStatus">Tra cứu phương tiện xuất xưởng</span>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<#--  -->
			</div>
	
		</div>
	</script>
	<#-- TEMPLATE TRANG QUẢN LÝ HỒ SƠ/ C-04 -->
	<#include "manageDossierView.ftl">
	<#include "manageDossierView_2.ftl">