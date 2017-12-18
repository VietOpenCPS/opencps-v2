<#if (Request)??>
<#include "init.ftl">
</#if>
	<#-- main section template -->
	<script type="text/x-kendo-template" id="mainTemplate">
		<div id="contentMain" class="row panel M0" style="border: none;box-shadow: none">
			<div class="panel-heading changeLayout P0" style="border: none">
				<div class="row PL15 PR15">
					<div class="row-header">
						<div class="col-sm-11 P0" style="border-right: 2px solid white"> 
							<div class="background-triangle-big PL5">
								<div class="checkbox-inline"> <input type="checkbox" class="ML0"> <label></label> </div>
								<i class="fa fa-cog"></i>
							</div>
							<span class="text-bold" id ="statusName" style="text-transform:uppercase;"></span>
							<div class="form-group search-icon pull-right MB0 MR10" id="search-input" style="margin-top:5px">
								<input type="text" class="form-control" id="keyInput" placeholder="Nhập từ khóa" data-bind="events: { keyup: filterKey}" style="width: 290px; height:30px">
							</div>
						</div>
						<div class="col-sm-1 P10" style="font-size: 14px">
							<span class="PR10"><i class="fa fa-calendar" aria-hidden="true"></i></span>
							<span class="PR10"><i class="fa fa-sort-alpha-asc text-light-gray" aria-hidden="true"></i></span>
							<span><i class="fa fa-arrows-alt text-light-gray" aria-hidden="true"></i></span>
						</div>
					</div>
				</div>
			</div>
			
			<div id="wrapMain">
				<div class="col-sm-12 P0 changeLayout" id="customer_dossierlist">
					<ul class="ul-with-border listDossierEmployee" data-role="listview" data-auto-bind="false" data-bind="source: dataSourceProfile, events:{dataBound : changeList}" data-template="proFileTemplate" id="listViewDossier">
						
					</ul>
				</div>
				<div class="col-sm-12 P0 changeLayout"  style="display: none;">
					<div class="panel-heading col-sm-12 P0">
						<div class="row PL15 PR15">
							<div class="row-header">
								<div class="col-sm-9 P0">
									<div class="background-triangle-big PL5">
										<div class="checkbox-inline"> <input type="checkbox" class="ML0"> <label></label> </div>
										<i class="fa fa-cog"></i>
									</div>
									<span class="text-bold" id="statusNameSub" style="text-transform:uppercase;"></span>
								</div>
								<div class="col-sm-3 P0 PT10">
									<span class="PR10"><i class="fa fa-calendar" aria-hidden="true"></i></span>
									<span class="PR10"><i class="fa fa-sort-alpha-asc text-light-gray" aria-hidden="true"></i></span>
									<span><i class="fa fa-arrows-alt text-light-gray" aria-hidden="true"></i></span>
								</div>
							</div>
						</div>
					</div>
					<ul class="ul-with-border col-sm-12 listDossierEmployee P0" data-role="listview" data-auto-bind="false" data-bind="source: dataSourceProfile, events:{dataBound : changeList}" data-template="proFileTemplateSummary" id="listViewDossierSummary">
						
					</ul>
				</div>
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
					<span id="pagerProfile" class="M0 P0" data-role="pager" data-info="false" data-bind="source: dataSourceProfile, events:{change: stylePager}" data-button-count="3" style="background: #ffffff"></span>
				</div>	
			</div>
			
		</div>
	</script>
		<#-- for listview-dossier-full-->
		<script type="text/x-kendo-template" id="proFileTemplate">
			<div class="row eq-height-lg P0 hover-pointer M0 P0 itemCustomerDossierList" dataPk="#:id#" data-bind="events:{click: loadDossierDetail}" style="border-bottom: 1px solid \\#ddd">
				<div class="PL5">
					<div class="checkbox"> <input type="checkbox" class="ML0"> <label></label> </div>
				</div>
				<div class="col-sm-12 PL0 PT10 PB5">
					<div class="row M0">
						<div class="col-sm-9 PL5" style="border-right: 1px solid \\#ddd">
							<p class="MB5">
								<span style="color: red;">#:count# | </span>
								<span class="text-bold">#:serviceName#</span>
							</p>
							<p class="MB5">
								#if(typeof stepInstruction !== "undefined"){#
									<i class="text-light-gray">#:stepInstruction#</i>
								#}#
							</p>
							<p class="MB5">
								<span class="text-bold">Chủ hồ sơ: </span><i>#:applicantName#</i>
							</p>
							<p class="MB5">
								<span class="text-bold">Chuyển bởi: </span><i>Chuyên viên A - Dự thảo giấy chứng nhận</i>
							</p>
							<p class="MB5">
								<span class="text-bold">Người thực hiện: </span><i>Chuyên viên C - Chứng nhận hồ sơ</i>
							</p>
							
							<p class="MB5">
								#if(typeof actionNote !== "undefined"){#
									<i class="fa fa-bolt" aria-hidden="true" style="color: red;"></i> 
									<i>Nhờ anh duyệt giấy chứng nhận này giúp em</i>
								#}#
							</p>

						</div>
						<#-- Content DATE -->
						<div class="col-sm-3">
							<div class="row M0">
								<p title="Mã hồ sơ" class="MB5"><span class="red">\\# </span>#:dossierId#</p>
								<p class="MB5"><span class="text-bold">Mã tiếp nhận: </span> #:dossierNo#</p>
								#if(receiveDate != ""){#
									<p class="MB5"><span class="text-bold">Ngày tiếp nhận: </span>#:receiveDate#</p>
								#}#

								#if(dueDate != ""){#
									<p class="MB5"><span class="text-bold">Ngày hẹn trả: </span>#:dueDate#</p>
								#}#

								#if(finishDate != ""){#
									<p class="MB5"><span class="text-bold">Hạn xử lý: </span>#:finishDate#</p>
								#}#
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</script>
		<#-- for listview-dossier-summary-->
		<script type="text/x-kendo-template" id="proFileTemplateSummary">
			<div class="row eq-height-lg P0 hover-pointer M0 P0 itemCustomerDossierList" dataPk="#:id#" data-bind="events:{click: loadDossierDetail}" style="border-bottom: 1px solid \\#ddd">
				<div class="PL5">
					<div class="checkbox"> <input type="checkbox" class="ML0"> <label></label> </div>
				</div>
				<div class="col-sm-12 P0 PT10 PB5">
					<div class="row M0">
						<div class="col-sm-12 PL5">
							<p class="MB5">
								<span>#:count# | </span>
								<span title="Mã hồ sơ" class="MB5"><span class="red">\\# </span>#:dossierId#</span>
							</p>
							<p class="MB5"><span class="text-bold">Mã tiếp nhận: </span> #:dossierNo#</p>
							<p class="MB5">#:serviceName#</p>
							<p class="MB5">
								<i>#:applicantName#</i>
							</p>
							#if(receiveDate != ""){#
							<p class="MB5"><span class="text-light-gray">#:receiveDate#</span></p>
							#}#
							#
								var label="label-info";
								switch(dossierStatus) {
								    case "new":
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
							        case "done":
								        label="status-complete";
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
				</div>
			</div>
		</script>

		<script type="text/javascript">
			$(function() {
				$(".checkbox-inline input[type='checkbox']:checked label:after").css("color","black")
			})
		</script>
