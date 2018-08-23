<#if (Request)??>
<#include "init.ftl">
</#if>
<#-- main section template -->

<#if registration?has_content>
	<input type="hidden" name="registrationId__hidden" id="registrationId__hidden" value="${(registration.registrationId)!}">
</#if>

<script type="text/x-kendo-template" id="mainTemplate">
	<div id="contentMain" class="row panel M0" style="border: none;box-shadow: none">
		<div class="panel-heading P0">
			<div class="row PL15 PR15">
				<div class="row-header align-middle-lg">
					<div class="background-triangle-big">
						<i class="fa fa-file-text"></i>
					</div>
					<span class="text-bold" id="statusName" style="text-transform:uppercase;"></span> &nbsp;&nbsp;&nbsp;
					<#if registration?has_content>
						
					<#else>
						<span class="text-bold red" style="text-transform:uppercase;">(Tài khoản này chưa bổ sung thông tin doanh nghiệp)</span>
					</#if>
					<div class="MLA form-inline">

						<input type="text" class="form-control" id="noInput" placeholder="Nhập số chứng chỉ" 
						data-bind="events: { keyup: filterInvestigationNo}" >

						<input type="text" class="form-control" id="keyInput" placeholder="Nhập từ khóa"
						data-bind="events: { keyup: filterKey}" style="width: 290px;">

					</div>
					
					<a class="PL5 PR5 hover-pointer no-icon" href="#searchAdvancedCollapse1" data-toggle="collapse">Nâng cao</a>

					<span id="fullScreen" data-bind="events: { click: fullScreen}">
						<i class="fa fa-expand fs20 toggle-collapse MR10 ML10" aria-hidden="true"></i>
						<i class="fa fa-compress fs20 MR10 ML10" aria-hidden="true" style="display: none"></i>
					</span>

				</div>
			</div>
		</div>
		<#--	-->
		<div id="wrapMain" class="table-responsive">
			<div class="row collapse toggle-hide M0" id="searchAdvancedCollapse1">

				<div class="col-sm-12">
					<div class="row PT10">
						<#-- <div class="col-sm-3">
							<label>Thủ tục</label>
							<input name="advanced_serviceName_search" id="advanced_serviceName_search"
							data-role="combobox"
							data-placeholder="Chọn thủ tục"
							data-text-field="serviceName"
							data-value-field="serviceCode"
							data-filter="contains"
							data-bind="
							source: dataServiceInfo
							"
							>
						</div> -->
						<div class="col-sm-4">
							<label>Số hồ sơ</label>
							<input class="form-control input-sm" name="advanced_dossierNo_search" id="advanced_dossierNo_search"
							placeholder="Số hồ sơ">
						</div>
						<div class="col-sm-4">
							<label>Từ ngày (Ngày tiếp nhận)</label>
							<input name="fromReceiveDate" id="fromReceiveDate"
							data-role="datepicker"
							data-placeholder="Từ ngày"
							data-format="dd/MM/yyyy"
							> 
						</div>
						<div class="col-sm-4">
							<label>Đến ngày (Ngày tiếp nhận)</label>
							<input name="toReceiveDate" id="toReceiveDate"
							data-role="datepicker"
							data-placeholder="Đến ngày"
							data-format="dd/MM/yyyy"
							>
							<!-- <div class="input-group date" data-date-format="dd.mm.yyyy">
								<input  type="text" class="form-control" placeholder="dd.mm.yyyy">
								<div class="input-group-addon" >
									<span class="glyphicon glyphicon-th"></span>
								</div>
							</div> -->
						</div>
					</div>

					<div class="row PT10">
						<div class="col-sm-4">
							<label>Mã hồ sơ</label>
							<input class="form-control input-sm" name="dossierIdCTNAdvanced" id="dossierIdCTNAdvanced" placeholder="Mã hồ sơ" >
						</div>
						<div class="col-sm-4">
							<label>Từ ngày (Ngày gửi)</label>
							<input name="fromSubmitDate" id="fromSubmitDate"
							data-role="datepicker"
							data-placeholder="Từ ngày"
							data-format="dd/MM/yyyy"
							> 
						</div>
						<div class="col-sm-4">
							<label>Đến ngày (Ngày gửi)</label>
							<input name="toSubmitDate" id="toSubmitDate"
							data-role="datepicker"
							data-placeholder="Đến ngày"
							data-format="dd/MM/yyyy"
							>
							<!-- <div class="input-group date" data-date-format="dd.mm.yyyy">
								<input  type="text" class="form-control" placeholder="dd.mm.yyyy">
								<div class="input-group-addon" >
									<span class="glyphicon glyphicon-th"></span>
								</div>
							</div> -->
						</div>
						
					</div>

					<div class="row PT10">
						<div class="col-sm-4">
							<label>Số chứng chỉ</label>
							<input class="form-control input-sm" name="so_chung_chi" id="so_chung_chi" placeholder="Số chứng chỉ" >
						</div>
						<div class="col-sm-4">
							<label>Từ ngày (Ngày ký)</label>
							<input name="tu_ngay_ky_cc" id="tu_ngay_ky_cc"
							data-role="datepicker"
							data-placeholder="Từ ngày"
							data-format="dd/MM/yyyy"
							> 
						</div>
						<div class="col-sm-4">
							<label>Đến ngày (Ngày ký)</label>
							<input name="den_ngay_ky_cc" id="den_ngay_ky_cc"
							data-role="datepicker"
							data-placeholder="Đến ngày"
							data-format="dd/MM/yyyy"
							>
							<!-- <div class="input-group date" data-date-format="dd.mm.yyyy">
								<input  type="text" class="form-control" placeholder="dd.mm.yyyy">
								<div class="input-group-addon" >
									<span class="glyphicon glyphicon-th"></span>
								</div>
							</div> -->
						</div>
						
					</div>
					<div class="row PT15 PB10 border-bottom ">
						<div class="col-sm-12">
							<button class="btn btn-active" data-bind="events: {
							click: searchAdvanced}">Tìm kiếm</button>
						</div>
					</div>
				</div>

			</div> 
			<table class="table table-bordered M0">
				<#-- Table header -->
				<thead>
					<tr>
						<th class="text-center hover-pointer">
							<span>STT</span>
						</th>
						<th class="fieldDossier text-center hover-pointer" sort="serviceName" sort-type="desc">
							<span>Tên thủ tục</span>
							<span class="pull-right align-middle PT5 text-light-gray">
								<i class="fa fa-sort" aria-hidden="true"></i>
							</span>		
						</th>
						<th class="fieldDossier text-center hover-pointer PL0 PR5" sort="dossierId" sort-type="desc">
							<span>Mã hồ sơ </span>
							<span class="pull-right align-middle PT5 text-light-gray">
								<i class="fa fa-sort" aria-hidden="true"></i>
							</span></br>
							<span class="PR10">Số hồ sơ</span>
						</th>
						<th class="fieldDossier text-center hover-pointer" sort="submitDate" sort-type="desc">
							<strong>Ngày gửi</strong>
							<span class="pull-right align-middle PT5 text-light-gray">
								<i class="fa fa-sort" aria-hidden="true"></i>
							</span></br>
							<strong>Ngày tiếp nhận</strong>
						</th>
						<th class="text-center hover-pointer">
							<strong>Số chứng chỉ</strong></br>
							<strong>Ngày ký</strong>
						</th>
						<th class="text-center hover-pointer">
							<strong>Nội dung</strong>
						</th>
						<th class="text-center hover-pointer">
							<strong>Ghi chú</strong>
						</th>
						<th class="text-center">
							<strong>Hành động</strong>
						</th>
					</tr>
				</thead>
				<#-- Table body -->
				<tbody class="" data-role="listview" data-auto-bind="false" data-bind="source: dataSourceProfile, events:{dataBound : changeList}" data-template="proFileTemplate" id="listViewDossier">

				</tbody>
			</table>

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
				<span id="pagerProfile" class="M0 P0" data-role="pager" data-info="false" data-bind="source: dataSourceProfile, events:{change: stylePager}" data-button-count="10" style="background: #ffffff" data-auto-bind="false"></span>
			</div>	
		</div>

	</div>
</script>
<#-- <#include "notificationPaying.ftl"> -->
<#-- for listview dossier-->
<script type="text/x-kendo-template" id="proFileTemplate">
	<tr class="rowTable">
		<td class="text-center count" style="width: 1%">
			#:count#
		</td>

		<td class="" style="width: 15%">
			<span>
				<a href="javascript:;" class="link-detail-employee text-hover-blue" data-pk="#=dossierId#" data-bind="events: { click : loadDossierDetail}">
					#=serviceName#
				</a>
			</span>

		</td>

		<td class="text-center" style="width: 8%">
			<p title="Mã hồ sơ">
				#if(dossierIdCTN){#
				<strong>#=dossierIdCTN#</strong>
				#}#

			</p>

			<p title="Số hồ sơ">#=dossierNo#</p>
		</td>

		<td class="text-center" style="width: 8%">
			#if (submitDate) {#
			<p title="Ngày gửi">
				#:submitDate#
			</p>
			#} else {#
			<p><i title="Ngày gửi" class="text-gray">---</i></p>
			#}#
			
			#if(dossierNo){
				if(receiveDate){#
					<p title="Ngày tiếp nhận">#:receiveDate#</p>
				#}else{#
					<p><i title="Ngày tiếp nhận" class="text-gray">---</i></p>
				#}
			}#
			
		</td>

		<td class="text-center" style="width: 10%">
			<#-- Số chứng chỉ -->
			<#-- #=briefNote# -->
			<p>#=certNo#</p>
			<p>#=certDate#</p>
		</td>

		<td class="" style="width: 23%">
			<i class="text-light-gray">#=briefNote#</i>
		</td>

		<td class="" style="width: 23%">
			# 
			if(applicantNote){
				var applicantNotes = applicantNote.split("<br>");
				var strResult = "";
				for(var i=0; i< applicantNotes.length; i++){
					if(applicantNotes[i].startsWith("DN")){
						applicantNotes[i] = "<span class='text-light-blue'>"+applicantNotes[i]+"</span>"+"<br>";
					}else {
						applicantNotes[i] = "<span class='red'>"+applicantNotes[i]+"</span>"+"<br>";
					}
					strResult += applicantNotes[i];
				}
			#
			<i>#=strResult#</i>
			#}#
		</td>

		<td class="PT0 PR0" style="width: 12%">
			<#--	-->
			<#-- <button type="button" class="btn-link no-border PT10 copyProfile" data-pk="#:dossierId#">
				<i class="fa fa-file-o" aria-hidden="true"></i>
				Sao chép
			</button> -->
			<#--	-->
			<#-- <button type="button" class="btn-link no-border PT10 downloadProfile" data-pk="#:dossierId#">
					<i class="fa fa-download" aria-hidden="true"/>
					Tải kết quả
				</button> -->


			#
			if(dossierStatus === "done" && dossierSubStatus === "" && statusReg !== 3){
			#
				<button type="button" class="btn-link no-border PT10 sendAdd" data-pk="#:dossierId#">
					<i class="fa fa-paper-plane" aria-hidden="true"></i>
					Sửa đổi bổ sung
				</button>
				<button type="button" class="btn-link no-border PT10 resDone" data-pk="#:dossierId#">
					<i class="fa fa-reply" aria-hidden="true"/>
					Yêu cầu cấp lại
				</button>

			#
			}
			#


			#
			if(dossierStatus == "waiting" && !cancellingDate && !endorsementDate && !correctingDate){
			#

				<button type="button" class="btn-link no-border PT10 resCancelling" data-pk="#:dossierId#">
					<i class="fa fa-trash-o" aria-hidden="true"></i>
					Yêu cầu hủy
				</button></br>

			#
			}
			#
			
			<#--  -->
			#if(dossierStatus == "receiving" && !cancellingDate && !endorsementDate && !cancellingDate){#
			<button type="button" class="btn-link no-border PT10 resCancelling" data-pk="#:dossierId#">
				<i class="fa fa-trash-o" aria-hidden="true"></i>
				Yêu cầu hủy
			</button></br>

			#}#

		</td>

	</tr>
</script>

