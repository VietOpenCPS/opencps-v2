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
						<i class="fa fa-expand fs20 toggle-collapse MT10 MR10 pull-right" aria-hidden="true"></i>
						<div class="form-group search-icon pull-right MB0 MR10" style="margin-top:4px">
							<input type="text" class="form-control" id="keyInput" placeholder="Nhập từ khóa" data-bind="events: { keyup: filterKey}" style="width: 290px; height:30px">
						</div>
					</div>
				</div>
			</div>
			<#--  -->
			<div id="wrapMain" class="table-responsive">
				<table class="table table-bordered M0">
					<#-- Table header -->
				    <thead>
				      <tr>
				        <th class="fieldDossier text-center hover-pointer">
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
				        <th class="fieldDossier text-center hover-pointer">
				        	<strong>Số chứng chỉ</strong>
				        </th>
				        <th class="fieldDossier text-center hover-pointer">
				        	<strong>Nội dung</strong>
				        </th>
				        <th class="fieldDossier text-center hover-pointer">
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
					<span id="pagerProfile" class="M0 P0" data-role="pager" data-info="false" data-bind="source: dataSourceProfile, events:{change: stylePager}" data-button-count="3" style="background: #ffffff"></span>
				</div>	
			</div>
			
			
		</div>
	</script>
		<#-- for listview dossier-->
		<script type="text/x-kendo-template" id="proFileTemplate">
			<tr class="">
				<td class="text-center count" style="width: 5%">
					
				</td>
				
				<td class="" style="width: 25%">
					<strong>
						<a href="javascript:;" class="link-detail-employee text-hover-blue" data-pk="#=dossierId#" data-bind="events: { click : loadDossierDetail}">
							#=serviceName#
						</a>
					</strong>
					
				</td>

				<td class="text-center" style="width: 8%">
						<p title="Mã hồ sơ"><strong>#=dossierId#</strong></p>
					
						<p title="Số hồ sơ">#=dossierNo#</p>
				</td>

				<td class="text-center" style="width: 12%">
					#if (submitDate) {#
						<p title="Ngày gửi">#:submitDate#</p>
					#}#
					
					#if (receiveDate) {#
						<p title="Ngày tiếp nhận">#:receiveDate#</p>
					#}#
				</td>

				<td class="" style="width: 10%">
					<#-- Số chứng chỉ -->
					<#-- #=briefNote# -->
				</td>

				<td class="" style="width: 17%">
					<i class="text-light-gray">#=briefNote#</i>
				</td>

				<td class="" style="width: 13%">
					# if(typeof actionNote !== "undefined"){#
						<i>#:actionNote#</i>
					#}#
				</td>

				<td class="PT0 PR0" style="width: 10%">
					#if(dossierStatus == "done"){#
						<button type="button" class="btn-link no-border PT10 downloadProfile" data-pk="#:dossierId#">
							<i class="fa fa-download" aria-hidden="true"/>
							Tải kết quả
						</button>
					#}#
					<#--  -->
					<button type="button" class="btn-link no-border PT10 copyProfile" data-pk="#:dossierId#">
						<i class="fa fa-file-o" aria-hidden="true"></i>
						Sao chép
					</button>
					<#--  -->
					#if(dossierStatus == "waiting"){#
						<button type="button" class="btn-link no-border PT10 resCancelling" data-pk="#:dossierId#">
							<i class="fa fa-reply" aria-hidden="true"></i>
							Yêu cầu hủy
						</button></br>
						<button type="button" class="btn-link no-border PT10 sendAdd" data-pk="#:dossierId#">
							<i class="fa fa-paper-plane" aria-hidden="true"></i>
							Gửi bổ sung
						</button>
					#}#
						
				</td>

	 		</tr>
		</script>
