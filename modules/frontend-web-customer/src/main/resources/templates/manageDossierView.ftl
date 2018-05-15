<#if (Request)??>
	<#include "init.ftl">
</#if>
	<#-- main section template -->
	<script type="text/x-kendo-template" id="mainTemplate">
		<div id="contentMain" class="row panel M0" style="border: none;box-shadow: none">
			<div class="panel-heading P0">
				<div class="row PL15 PR15">
					<div class="row-header"> 
						<div class="background-triangle-big">
							<i class="fa fa-file-text"></i>
						</div>
						<span class="text-bold" id="statusName" style="text-transform:uppercase;"></span>
						<div class="form-group search-icon pull-right MB0 MR10" style="margin-top:4px">
							<input type="text" class="form-control" id="keyInput" placeholder="Nhập số hồ sơ, Mã tiếp nhận, Tên hồ sơ" data-bind="events: { keyup: filterKey}" style="width: 290px; height:30px">
						</div>
					</div>
				</div>
			</div>
			<div id="wrapMain">
				<div class="col-sm-12 P0" id="customer_dossierlist">
					<ul class="ul-with-border" data-role="listview" data-auto-bind="false" data-bind="source: dataSourceProfile, events:{dataBound : changeList}" data-template="proFileTemplate" id="listViewDossier">
						
					</ul>
				</div>
				<div class="footerListProfile row-header col-sm-12 PT20 PR0" style="background: #f6f6f6">
					<div class="clearfix align-middle" style="float: right">
						<span class="text-light-gray MR15"><i>Tổng số <span id="totalItem_dossierList" class="red"></span> kết quả được tìm thấy</i></span>
						<span class="show-per-page MT0">Hiển thị
							<span class="select-wrapper">
								<select class="ML5" id="itemPpage" data-bind="events:{change: changePageSize}" style="background-color: #ffffff">
									
								</select>
							</span>
						</span>
						<span id="pagerProfile" class="M0 P0" data-role="pager" data-auto-bind="false" data-info="false" data-bind="source: dataSourceProfile, events:{change: stylePager}" data-button-count="3" style="background: #ffffff"></span>
					</div>	
				</div>
			</div>
			
		</div>
	</script>
		<#-- for listview dossier-->
		<script type="text/x-kendo-template" id="proFileTemplate">
			<div class="row hover-pointer PL15 PR15 itemCustomerDossierList" dataPk="#:id#" data-bind="events:{click: loadDossierDetail}">
				<div class="row M0">
					<div class="row-blue align-middle">
						<div class="order-number">#:count#</div>
						<#-- <div class="dossier-number" data-toggle="tooltip" title="Mã hồ sơ"><span class="red">\\#</span> #:dossierId#</div> -->
						<div class="receive-number"><span class="text-normal">Mã tiếp nhận hồ sơ:</span> #:dossierNo#</div>
						#
							var label="label-info";
							switch(dossierStatus) {
							    case "new":
							        label="label-info";
							        break;
							    case "collecting":
							        label="label-info";
							        break;
							    case "receiving":
							        label="status-processing";
							        break;
							    case "waiting":
							        label="status-additional";
							        break;
							    case "processing":
							        label="label-info";
							        break;
							    case "paying":
							       	label="status-pending-payment";
							        break;
							    case "handover":
							        label="label-info";
							        break;  
							    case "releasing":
							        label="label-info";
							        break;	
							    case "posting":
							        label="label-info";
							        break;
						        case "done":
							        label="status-complete";
							        break;
						        case "cancelled":
							        label="status-cancel";
							        break;					        
							    default:
							        label="label-info";
							}
						#
						#
							var status;
							if(dossierSubStatusText == ""){
								status = dossierStatusText
							} else {
								status = dossierSubStatusText
							}
						#
						<span class="label #:label# MLA">#:status#</span> 
					</div>
				</div>
				<div class="col-sm-12 PL0 PT5 PB5">
					<div class="row M0">
						<div class="col-sm-9" style="border-right: 1px solid \\#ddd">
							<p class="MB5">#:serviceName#</p>
							<p class="MB5">
								<i class="fa fa-university" style="color: \\#84FAFA;" aria-hidden="true"></i> #:govAgencyName#
							</p>
							
							<p class="MB5">
								#if(typeof actionNote !== "undefined"){#
									<i class="fa fa-bolt" aria-hidden="true" style="color: red;"></i> 
									<i>#:actionNote#</i>
								#}#
							</p>

							<p class="MB5">
								#if(typeof stepInstruction !== "undefined"){#
									<i class="text-light-gray">#:stepInstruction#</i>
								#}#
							</p>
							<p class="actionDossier MB0">
								#if(dossierStatus === "waiting"){#
								<a href="javascript:;" data-Pk="#:id#" class="downloadAddRes PR15" data-bind=" events:{click : downloadResDossier}">
									<i class="fa fa-download" aria-hidden="true">
									</i> Tải yêu cầu bổ sung
								</a>
								#}#

								#if(dossierStatus === "done"){#
									<a href="javascript:;" data-Pk="#:id#" class=" PR15" data-bind=" events:{click : downloadProfile}">
										<i class="fa fa-download" aria-hidden="true">
										</i> Tải giấy tờ kết quả
									</a>
								#}#
								<#-- ${api.server}/dossiers/#:id#/result -->
								<#-- #if(dossierStatus === "done" ){#
									<a href="javascript:;" data-Pk="#:id#" class="copyProfile PR15"><i class="fa fa-file-archive-o" aria-hidden="true"></i> Sao chép hồ sơ
									</a>
								#}# -->
							</p>	
						</div>
						<#-- Content DATE -->
						<div class="col-sm-3 text-right">
							<div class="row">
								#
								if(typeof submitDate !== "undefined"){
									if(submitDate != ""){
								#
									<p data-toggle="tooltip" title="Ngày gửi">
										<i class="fa fa-paper-plane-o" aria-hidden="true"></i> #:submitDate#
									</p>
								#
									}
								}
								#

								#
								if(typeof receiveDate !== "undefined"){
									if(receiveDate != ""){

								#
									<p data-toggle="tooltip" title="Ngày tiếp nhận">
										<i class="fa fa-file-o" aria-hidden="true"></i> #:receiveDate#
									</p>
								#
									}
								}
								#

								#
								if(typeof dueDate !== "undefined"){
									if(dueDate != ""){

								#
									<p data-toggle="tooltip" title="Ngày hẹn trả">
										<i class="fa fa-clock-o" aria-hidden="true"></i> #:dueDate#
									</p>
								#
									}
								}
								#
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</script>

	<#-- sidebar list template-->
	<script type="text/x-kendo-template" id="sidebarTemplate">
		<div class="row">
			<div class="col-sm-12" id="customer_additional_requirements">
				<div class="panel panel-main MB15" id="sideItemAdd" >
					<div class="panel-heading row-header"> 
						<span class="panel-title">Yêu cầu bổ sung</span>
						<span class="pull-right clickable" data-toggle="collapse" data-target="#additionalRequirement" data-bind="events:{click: jsCollaps}">
							<i class="glyphicon glyphicon-chevron-up"></i>
							<i class="glyphicon glyphicon-chevron-down" style="display: none"></i>
						</span>
						<span class="pull-right MR10 text-light-gray hover-pointer" id="sort_modified" title="Sắp xếp theo ngày" data-button-count="1" data-bind="events:{click: sortDate}"><i class="fa fa-calendar" aria-hidden="true"></i></span>
					</div>
					<div class="panel-body P0 collapse in" id="additionalRequirement">
						<ul class="ul-with-border" data-role="listview" data-bind="source:dataAddRequest" data-auto-bind="false" data-template="additional_Requirement_Template" id="wrapAddRes" style="border-bottom: 1px dashed #ddd">
							
						</ul>
						<div class="clearfix align-middle PL10">
							<span class="text-light-gray MR80"><i>Có <span id="total_Additional_Requirement" class="red"> </span> yêu cầu</i></span>
							<span id="pagerCustomer_Additional_Requirement" class="M0 PR5" data-bind="source:dataAddRequest" data-role="pager" data-numeric="false" data-info="false"></span>
						</div>	
					</div>
				</div>
			</div>
			<div class="col-sm-12" id="customer_payment_request">
				<div class="panel panel-main MB15" id="sideItemPayment" > 
					<div class="panel-heading row-header"> 
						<span class="panel-title">Yêu cầu thanh toán</span> 
						<span class="pull-right clickable" data-toggle="collapse" data-target="#paymentRequest" data-bind="events:{click: jsCollaps}"> 
							<i class="icon_collapse glyphicon glyphicon-chevron-up"></i>
							<i class="glyphicon glyphicon-chevron-down" style="display: none"></i>
						</span>
						<span class="pull-right MR10 text-light-gray hover-pointer" id="sort_modified1" title="Sắp xếp theo ngày" data-bind="events:{click: sortDate}"><i class="fa fa-calendar" aria-hidden="true"></i></span>
					</div> 
					<div class="panel-body P0 collapse in" id="paymentRequest">
						<ul class="ul-with-border" data-role="listview" data-bind="source: dataPayRequest" data-auto-bind="false" data-template="payment_Request_Template" id="wrapPayRes" style="border-bottom: 1px dashed #ddd">
							
						</ul>
						<div class="clearfix align-middle PL10">
							<span class="text-light-gray MR80"><i>Có <span id="total_Payment_Request" class="red"> </span> yêu cầu</i></span>
							<span id="pagerCustomer_Payment_Request" class="M0 PR5" data-bind="source:dataPayRequest" data-role="pager" data-numeric="false" data-info="false"></span>
						</div>

					</div> 
				</div>
			</div>
			<div class="col-sm-12" id="customer_result_request">
				<div class="panel panel-main" id="sideItemResult" > 
					<div class="panel-heading row-header">
						<span class="panel-title">Trả kết quả</span> 
						<span class="pull-right clickable" data-toggle="collapse" data-target="#resultRequest" data-bind="events:{click: jsCollaps}"> 
							<i class="icon_collapse glyphicon glyphicon-chevron-up"></i>
							<i class="glyphicon glyphicon-chevron-down" style="display: none"></i>
						</span> 
						<span class="pull-right MR10 text-light-gray hover-pointer" id="sort_modified2" title="Sắp xếp theo ngày" data-bind="events:{click: sortDate}"><i class="fa fa-calendar" aria-hidden="true"></i></span>
					</div>
					<div class="panel-body P0 collapse in" id="resultRequest">	
						<ul class="ul-with-border" data-role="listview" data-bind="source: dataResult" data-auto-bind="false" data-template="result_Request_Template" id="wrapResult" style="border-bottom: 1px dashed #ddd">
							
						</ul>
						<div class="clearfix align-middle PL10">
							<span class="text-light-gray MR80"><i>Có <span id="total_result" class="red"> </span> kết quả</i></span>
							<span id='pagerCustomer_Result_Request' class="M0 PR5" data-bind="source:dataResult" data-numeric="false" data-role="pager" data-info="false"></span>
						</div>
					</div> 
				</div>
			</div>
		</div>
	</script>
		<#-- For menu additionnal -->
	<script type="text/x-kendo-template" id="additional_Requirement_Template">
		<li data-pk="#:id#" class="P10 hover-pointer" title="Xem chi tiết" data-bind="events:{click: loadDossierDetail}">
			<p class="MB5">#:content#</p>
			<span class="text-light-gray">#:govAgencyName#</span> <br>
			<span class="text-light-gray">#:createDate#</span>
		</li>
	</script>
		<#-- For menu payment -->
	<script type="text/x-kendo-template" id="payment_Request_Template">
		<li data-pk="#:id#" class="P10 hover-pointer" title="Xem chi tiết" data-bind="events:{click: loadDossierDetail}">
			<p class="MB5">#:content#</p>
			<span class="text-light-gray">#:govAgencyName#</span> <br>
			<span class="text-light-gray">#:createDate#</span>
		</li>
	</script>
		<#-- For menu result -->
	<script type="text/x-kendo-template" id="result_Request_Template">
		<li data-pk="#:id#" class="P10 hover-pointer" title="Xem chi tiết" data-bind="events:{click: loadDossierDetail}">
			<p class="MB5">#:content#</p>
			<span class="text-light-gray">#:govAgencyName#</span> <br>
			<span class="text-light-gray">#:createDate#</span>
		</li>
	</script>

	