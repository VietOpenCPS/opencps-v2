<#------------- Phần VIEW --------------->
	<#-- layout -->
	<div id="appManagerDossier"></div>
	<#-- layout template -->
	<script type="text/x-kendo-template" id="layoutTemplate">
		<div id="panel_list" class="col-sm-3"></div>

		<div id="main_section" class="col-sm-6 P0"></div>

		<div id="sidebar_list" class="col-sm-3"></div>
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
	<#-- main section template -->
	<script type="text/x-kendo-template" id="mainTemplate">
		<div class="row panel M0" style="border: none;box-shadow: none">
			<div class="panel-heading P0">
				<div class="row PL15 PR15">
					<div class="row-header"> 
						<div class="background-triangle-big">
							<i class="fa fa-file-text"></i>
						</div> 
						<span class="text-bold">KÊT QUẢ TÌM KIẾM HỒ SƠ</span> 
						<div class="form-group search-icon pull-right MT5 MB5 MR10">
							<input type="text" class="form-control" id="keyInput" placeholder="Nhập từ khóa" data-bind="events: { keyup: filterKey}">
						</div>
					</div>	
				</div>
			</div>
			<div class="col-sm-12 P0" id="customer_dossierlist">
				<ul class="ul-with-border" data-role="listview" data-bind="source: dataSourceProfile" data-auto-bind="false" data-template="proFileTemplate" id="listViewDossier">
					
				</ul>
			</div>
			<div class="footerListProfile row-header col-sm-12 PT20" style="background: #f6f6f6">
				<div class="clearfix align-middle" style="float: right">
					<span class="text-light-gray MR15"><i>Tổng số <span id="totalItem_dossierList" class="red"></span> kết quả được tìm thấy</i></span>
					<span class="show-per-page MT0">Hiển thị
						<span class="select-wrapper">
							<select class="ML5" id="itemPpage" data-bind="events:{change: changePageSize}">
								
							</select>
						</span>
					</span>
					<span id="pagerProfile" class="M0 P0 PR5" data-role="pager" data-info="false" data-bind="source: dataSourceProfile"></span>
				</div>	
			</div>
		</div>
	</script>
		<#-- for listview dossier-->
		<script type="text/x-kendo-template" id="proFileTemplate">
			<div class="row PL15 PR15 itemCustomerDossierList" dataPk="#:id#" data-bind="events:{click: loadDossierDetail}">
				<div class="row M0 hover-pointer" title="Xem chi tiết">
					<div class="row-blue align-middle">
						<div class="order-number">#:counter#</div>
						<div class="dossier-number" data-toggle="tooltip" title="Mã hồ sơ"><span class="red">\\#</span> #:serviceCode#</div>
						<div class="receive-number"><span class="text-normal">Mã tiếp nhận:</span> #:dossierNo#</div>
						#
							var label="label-info";
							switch(dossierStatus) {
							    case "New":
							        label="label-info";
							        break;
							    case "Collecting":
							        label="label-info";
							        break;
							    case "Receiving":
							        label="label-status-processing";
							        break;
							    case "Waiting":
							        label="label-status-additional";
							        break;
							    case "Processing":
							        label="label-info";
							        break;
							    case "Paying":
							       	label="label-status-pending-payment";
							        break;
							    case "Handover":
							        label="label-info";
							        break;  
							    case "Releasing":
							        label="label-info";
							        break;	
							    case "Posting":
							        label="label-info";
							        break;
						        case "Done":
							        label="label-status-complete";
							        break;
						        case "Cancelled":
							        label="label-status-cancel";
							        break;					        
							    default:
							        label="label-info";
							}
						#
						<span class="label #:label# MLA">#:dossierSubStatusText#</span> 
					</div>
				</div>
				<div class="col-sm-12 PL0 PT5 PB10">
					<div class="row M0">
						<div class="col-sm-8">
							<p>#:serviceName#</p>
							<p>
								<i class="fa fa-university" style="color: \\#84FAFA;" aria-hidden="true"></i> #:govAgencyName#
							</p>
							
							<p>
								#if(typeof actionNote !== "undefined"){#
									<i class="fa fa-bolt" aria-hidden="true" style="color: red;"></i> 
									<i>#:actionNote#</i>
								#}#
							</p>

							<p>
								#if(typeof stepInstruction !== "undefined"){#
									<i>#:stepInstruction#</i>
								#}#
							</p>

							#if(dossierStatus === "Done"){#
								<a href="${api.server}/dossiers/#:id#/result" style="margin-right: 10px;">
									<i class="fa fa-download" aria-hidden="true">
									</i> Tải giấy tờ kết quả
								</a>
							#}#
							
							#if(dossierStatus === "Done" ){#
								<a href="javascript:;" onclick="javascript:copyProfile(#:id#)"><i class="fa fa-file-archive-o" aria-hidden="true"></i> Sao chép hồ sơ
								</a>
							#}#
						</div>
						
						<div class="col-sm-4 MT10 text-right">
							<div class="row">
								<p data-toggle="tooltip" title="Ngày gửi">
									<i class="fa fa-paper-plane-o" aria-hidden="true"></i> #:submitDate#
								</p>
								<p data-toggle="tooltip" title="Ngày tiếp nhận">
									<i class="fa fa-file-o" aria-hidden="true"></i> #:receiveDate#
								</p>
								<p data-toggle="tooltip" title="Ngày hẹn trả">
									<i class="fa fa-clock-o" aria-hidden="true"></i> #:dueDate#
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</script>
	<#-- for detailDossier -->
	<script type="text/x-kendo-template" id="dossier_detailTemp">
		<div id="detail"></div>
	</script>
	<#-- for ServiceConnfig -->
	<script type="text/x-kendo-template" id="serviceconfigTemp">
		<div id="serviceconfig"></div>
	</script>
	<#-- sidebar list template-->
	<script type="text/x-kendo-template" id="sidebarTemplate">
		<div class="row">
			<div class="col-sm-12" id="customer_additional_requirements">
				<div class="panel panel-main" id="sideItemAdd"> 
					<div class="panel-heading row-header"> 
						<span class="panel-title">Yêu cầu bổ sung</span>
						<span class="pull-right clickable" data-toggle="collapse" data-target="#additionalRequirement">
							<i id="icon_collapse" class="glyphicon glyphicon-chevron-up"></i>
						</span>
						<span class="pull-right MR10 text-light-gray hover-pointer" id="sort_modified" title="Sắp xếp theo ngày" data-bind="events:{click: sortDate}"><i class="fa fa-calendar" aria-hidden="true"></i></span>
					</div>
					<div class="panel-body P0 collapse in" id="additionalRequirement">
						<ul class="ul-with-border" data-role="listview" data-bind="source:dataAddRequest" data-auto-bind="false" data-template="additional_Requirement_Template" id="wrapAddRes">
							
						</ul>
						<div class="clearfix align-middle PL10">
							<span class="text-light-gray MR100"><i>Có <span id="total_Additional_Requirement" class="red"> </span> yêu cầu</i></span>
							<span id="pagerCustomer_Additional_Requirement" class="M0 PR5" data-bind="source:dataAddRequest" data-role="pager" data-info="false"></span>
						</div>	
					</div>
				</div>
			</div>
			<div class="col-sm-12" id="customer_payment_request">
				<div class="panel panel-main MT15" id="sideItemPayment"> 
					<div class="panel-heading row-header"> 
						<span class="panel-title">Yêu cầu thanh toán</span> 
						<span class="pull-right clickable" data-toggle="collapse" data-target="#paymentRequest"> 
							<i id="icon_collapse1" class="glyphicon glyphicon-chevron-up"></i> 
						</span>
						<span class="pull-right MR10 text-light-gray hover-pointer" id="sort_modified1" title="Sắp xếp theo ngày" data-bind="events:{click: sortDate}"><i class="fa fa-calendar" aria-hidden="true"></i></span>
					</div> 
					<div class="panel-body P0 collapse in" id="paymentRequest">
						<ul class="ul-with-border" data-role="listview" data-bind="source: dataPayRequest" data-auto-bind="false" data-template="payment_Request_Template" id="wrapPayRes">
							
						</ul>
						<div class="clearfix align-middle PL10">
							<span class="text-light-gray MR100"><i>Có <span id="total_Payment_Request" class="red"> </span> yêu cầu</i></span>
							<span id="pagerCustomer_Payment_Request" class="M0 PR5" data-bind="source:dataPayRequest" data-role="pager" data-info="false"></span>
						</div>

					</div> 
				</div>
			</div>
			<div class="col-sm-12" id="customer_result_request">
				<div class="panel panel-main MT15" id="sideItemResult"> 
					<div class="panel-heading row-header"> 
						<span class="panel-title">Trả kết quả</span> 
						<span class="pull-right clickable" data-toggle="collapse" data-target="#resultRequest"> 
							<i id="icon_collapse2" class="glyphicon glyphicon-chevron-up"></i>
						</span> 
						<span class="pull-right MR10 text-light-gray hover-pointer" id="sort_modified2" title="Sắp xếp theo ngày" data-bind="events:{click: sortDate}"><i class="fa fa-calendar" aria-hidden="true"></i></span>
					</div> 
					<div class="panel-body P0 collapse in" id="resultRequest">	
						<ul class="ul-with-border" data-role="listview" data-bind="source: dataResult" data-auto-bind="false" data-template="result_Request_Template" id="wrapResult">
							
						</ul>
						<div class="clearfix align-middle PL10">
							<span class="text-light-gray MR100"><i>Có <span id="total_result" class="red"> </span> kết quả</i></span>
							<span id='pagerCustomer_Result_Request' class="M0 PR5" data-bind="source:dataResult" data-role="pager" data-info="false"></span>
						</div>
					</div> 
				</div>
			</div>
		</div>
	</script>
		<#-- For menu additionnal -->
	<script type="text/x-kendo-template" id="additional_Requirement_Template">
		<li data-pk="#:id#" class="P10 hover-pointer" title="Xem chi tiết" data-bind="events:{click: loadDossierDetail}">
			<p>#:content#</p>
			<span class="text-greyy">#:govAgencyName#</span> <br>
			<span class="text-greyy">#:createDate#</span>
		</li>
	</script>
		<#-- For menu payment -->
	<script type="text/x-kendo-template" id="payment_Request_Template">
		<li data-pk="#:id#" class="P10 hover-pointer" title="Xem chi tiết" data-bind="events:{click: loadDossierDetail}">
			<p>#:content#</p>
			<span class="text-greyy">#:govAgencyName#</span> <br>
			<span class="text-greyy">#:createDate#</span>
		</li>
	</script>
		<#-- For menu result -->
	<script type="text/x-kendo-template" id="result_Request_Template">
		<li data-pk="#:id#" class="P10 hover-pointer" title="Xem chi tiết" data-bind="events:{click: loadDossierDetail}">
			<p>#:content#</p>
			<span class="text-greyy">#:govAgencyName#</span> <br>
			<span class="text-greyy">#:createDate#</span>
		</li>
	</script>