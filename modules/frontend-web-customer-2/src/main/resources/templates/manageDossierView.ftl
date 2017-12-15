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
			<div id="wrapMain" class="table-responsive">
				<table class="table table-bordered M0">
					<#-- Table header -->
				    <thead>
				      <tr>
				        <th class="fieldDossier text-center hover-pointer" sort="count" sort-type="desc">
									<span>STT</span>
									<span class="pull-right align-middle PT5 text-light-gray">
										<i class="fa fa-sort" aria-hidden="true"></i>
									</span>
				        </th>
				        <th class="fieldDossier text-center hover-pointer" sort="serviceName" sort-type="desc">
				        	<span>Tên thủ tục</span>
				        	<span class="pull-right align-middle PT5 text-light-gray">
										<i class="fa fa-sort" aria-hidden="true"></i>
									</span></br>
									<span>Cơ quan quản lý</span>		
				        </th>
				        <th class="fieldDossier text-center hover-pointer PL0 PR5" sort="dossierId" sort-type="desc">
				        	<span>Mã hồ sơ </span>
				        	<span class="pull-right align-middle PT5 text-light-gray">
										<i class="fa fa-sort" aria-hidden="true"></i>
									</span></br>
									<span class="PR10">Số hồ sơ</span>
				        </th>
				        <th class="fieldDossier text-center hover-pointer" sort="submitDate" sort-type="asc">
				        	<strong>Ngày gửi</strong>
				        	<span class="pull-right align-middle PT5 text-light-gray">
										<i class="fa fa-sort" aria-hidden="true"></i>
									</span></br>
									<strong>Ngày tiếp nhận</strong>
				        </th>
				        <th class="fieldDossier text-center hover-pointer">
				        	<strong>Số chứng chỉ</strong>
				        	<span class="pull-right align-middle PT5 text-light-gray">
										<i class="fa fa-sort" aria-hidden="true"></i>
									</span>	
				        </th>
				        <th class="fieldDossier text-center hover-pointer">
				        	<strong>Nội dung</strong>
				        	<span class="pull-right align-middle PT5 text-light-gray">
										<i class="fa fa-sort" aria-hidden="true"></i>
									</span>	
				        </th>
				        <th class="fieldDossier text-center hover-pointer" sort="actionNote" sort-type="desc">
				        	<strong>Ghi chú</strong>
				        	<span class="pull-right align-middle PT5 text-light-gray">
										<i class="fa fa-sort" aria-hidden="true"></i>
									</span>	
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
				<td class="text-center" style="width: 5%">
					#=count#
				</td>
				
				<td class="" style="width: 25%">
					<strong>
						<a href="javascript:;" class="link-detail-employee text-hover-blue" data-pk="#=dossierId#" data-bind="events: { click : loadDossierDetail}">
							#=serviceName#
						</a>
					</strong>
					<br>
					#=govAgencyName#
				</td>

				<td class="text-center" style="width: 8%">
						<p><strong>#=dossierId#</strong></p>
					
						<p>#=dossierNo#</p>
				</td>

				<td class="text-center" style="width: 12%">
					#if ( submitDate!="" && submitDate!=null ) {#
						<p>#= kendo.toString(kendo.parseDate(submitDate, 'yyyy-MM-dd'), 'dd/MM/yyyy')#</p>
					#}#
					
					#if ( receiveDate!="" && receiveDate!=null ) {#
						<p>#= kendo.toString(kendo.parseDate(receiveDate, 'yyyy-MM-dd'), 'dd/MM/yyyy')#</p>
					#}#
				</td>

				<td class="" style="width: 10%">
					<#-- Số chứng chỉ -->
					<#-- #=briefNote# -->
				</td>

				<td class="" style="width: 18%">
					<i class="text-light-gray">#=briefNote#</i>
				</td>

				<td class="text-center" style="width: 13%">
					# if(typeof actionNote !== "undefined"){#
						<i>#:actionNote#</i>
					#}#
				</td>

				<td class="text-center" style="width: 9%">
					#if(dossierStatus == "done"){#
						<button type="button" class="btn-link no-border downloadProfile" data-pk="#:dossierId#">
							<i class="fa fa-download" aria-hidden="true"/>
							Tải kết quả
						</button>
					#}#
					<button type="button" class="btn-link no-border copyProfile" data-pk="#:dossierId#">
						<i class="fa fa-file-o" aria-hidden="true"></i>
						Sao chép
					</button>
						
				</td>

	 		</tr>
		</script>
