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
							<input type="text" class="form-control" id="keyInput" placeholder="Nhập từ khóa" data-bind="events: { keyup: filterKey}" style="width: 290px; height:30px">
						</div>
					</div>
				</div>
			</div>
			<div id="wrapMain">
				<div class="col-sm-12 P0" id="customer_dossierlist">
					<div class="form-group row M0 border-bottom PB5 border-color-lightblue">

				<div class="col-sm-1">
					
					<strong>STT</strong>
				
				</div>
				
				<div class="col-sm-3">
					
					<strong>
						<div>Tên thủ tục</div>
						<div>Cơ quan quản lý</div>

					</strong>
				
				</div>

				<div class="col-sm-1">
					
					<strong>

						<div>Mã hồ sơ</div>
						<div>Số hồ sơ</div>

					</strong>
				
				</div>
				
				<div class="col-sm-1">
					
					<strong>

						<div>Ngày gửi</div>
						<div>Ngày tiếp nhận</div>

					</strong>
				
				</div>

				<div class="col-sm-1">
					
					<strong>

						<div>Số chứng chỉ</div>

					</strong>
				
				</div>

				<div class="col-sm-3">
					
					<strong>

						<div>Nội dung</div>

					</strong>
				
				</div>

				<div class="col-sm-1">
					
					<strong>

						<div>Ghi chú</div>

					</strong>
				
				</div>

				<div class="col-sm-1">
					
					<strong>

						<div>Hành động</div>

					</strong>
				
				</div>

			</div>

			<div>

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
					<span id="pagerProfile" class="M0 P0" data-role="pager" data-info="false" data-bind="source: dataSourceProfile, events:{change: stylePager}" data-button-count="3" style="background: #ffffff"></span>
				</div>	
			</div>
			
			
		</div>
	</script>
		<#-- for listview dossier-->
		<script type="text/x-kendo-template" id="proFileTemplate">
			<li class="PT10 PB10 border-bottom">
	
		<div class="row M0 ">

			<div class="col-sm-1">
				
				#=counter#
					
			</div>
			
			<div class="col-sm-3">
				<strong>
					<a href="javascript:;" class="link-detail-employee" data-pk="#=dossierId#" data-bind="events: { click : loadDossierDetail}">
						#=serviceName#
					</a>
				</strong>
				<br>
				#=govAgencyName#
			</div>

			<div class="col-sm-1">
				# if(dossierNo != null && dossierNo != ""){#
		
					#=dossierNo#

				#} else {#
					<i class="text-gray">Chưa cập nhật</i>
				#}#
				<br>
				# if(dossierId != null && dossierId != ""){#
		
					#=dossierId#

				#} else {#
					<i class="text-gray">Chưa cập nhật</i>
				#}#
			</div>

			<div class="col-sm-1">
				#if ( submitDate!="" && submitDate!=null ) {#
					#= kendo.toString(kendo.parseDate(submitDate, 'yyyy-MM-dd'), 'dd/MM/yyyy')#
				#} else {#
					<i class="text-gray">__/__/____</i>
				#}#
				<br>
				#if ( receiveDate!="" && receiveDate!=null ) {#
					#= kendo.toString(kendo.parseDate(receiveDate, 'yyyy-MM-dd'), 'dd/MM/yyyy')#
				#} else {#
					<i class="text-gray">__/__/____</i>
				#}#
			</div>

			<div class="col-sm-1">
				# if(briefNote != null && briefNote != ""){#
		
					#=briefNote#

				#} else {#
					<i class="text-gray">Chưa cập nhật</i>
				#}#
			</div>

			<div class="col-sm-3">
				-Loại sản phẩm:
				# if(briefNote != null && briefNote != ""){#
		
					#=briefNote#

				#} else {#
					<i class="text-gray">Chưa cập nhật</i>
				#}#
				<br>
				-Tên thuơng mại:
				# if(briefNote != null && briefNote != ""){#
		
					#=briefNote#

				#} else {#
					<i class="text-gray">Chưa cập nhật</i>
				#}#
				<br>
				-Nhãn hiệu/số loại:
				# if(briefNote != null && briefNote != ""){#
		
					#=briefNote#

				#} else {#
					<i class="text-gray">Chưa cập nhật</i>
				#}#
				<br>
				<i class="text-gray">
					Hồ sơ này sẽ giải quyết trong vòng #=dossierId# ngày, khi đó bạn hãy đến cơ quan tiếp nhận hồ sơ để lấy kết quả khi hồ sơ hoàn tất.
				</i>
			</div>

			<div class="col-sm-1">
				# if(briefNote != null && briefNote != ""){#
		
					#=briefNote#

				#} else {#
					<i class="text-gray">Chưa cập nhật</i>
				#}#
			</div>

			<div class="col-xs-1 col-sm-1 text-right">

				<button type="button" class="btn-link no-border downloadProfile" data-pk="#:dossierId#">
					<i class="fa fa-download" aria-hidden="true"/>
					Tải kết quả
				</button>
				
				<button type="button" class="btn-link no-border copyProfile" data-pk="#:dossierId#">
					<i class="fa fa-file-o" aria-hidden="true"></i>
					Sao chép
				</button>
					
			</div>
		</div>	
			
	 </li>

</script>
