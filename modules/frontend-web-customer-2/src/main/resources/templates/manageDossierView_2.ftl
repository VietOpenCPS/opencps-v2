<#if (Request)??>
<#include "init.ftl">
</#if>
<#-- main section template -->
<script type="text/x-kendo-template" id="mainTemplate_2">
	<div id="contentMain" class="row panel M0" style="border: none;box-shadow: none">
		<div class="panel-heading P0">
			<div class="row PL15 PR15">
				<div class="row-header align-middle-lg">
					<div class="background-triangle-big">
						<i class="fa fa-file-text"></i>
					</div>
					<span class="text-bold" id="statusName" style="text-transform:uppercase;">TRA CỨU CHỨNG CHỈ</span>
					<div class="MLA form-inline">

						<input type="text" class="form-control input-sm" id="noInputDelivable" placeholder="Nhập số chứng chỉ" 
						data-bind="events: { keyup: filterDelivableNo}" >

						<input type="text" class="form-control input-sm" id="keyInputDelivable" placeholder="Nhập từ khóa"
						data-bind="events: { keyup: filterDelivableKey}" style="width: 290px;">
					</div>
					
					<a class="PL5 PR5 hover-pointer" href="#searchAdvancedCollapse" data-toggle="collapse">Nâng cao</a>

					<span id="fullScreen" data-bind="events: { click: fullScreen}">
						<i class="fa fa-expand fs20 toggle-collapse MR10 ML10" aria-hidden="true"></i>
						<i class="fa fa-compress fs20 MR10 ML10" aria-hidden="true" style="display: none"></i>
					</span>



				</div>
			</div>
		</div>
		<#--	-->
		<div id="wrapMain" class="table-responsive">
			<div class="row collapse toggle-hide" id="searchAdvancedCollapse">

				<div class="col-sm-12">
					<div class="row PL10 PR10 PT10">

						<div class="col-sm-3">
							<input name="advanced_deliverableTypes_search" id="advanced_deliverableTypes_search"
							data-role="combobox"
							data-placeholder="Chọn loại chứng chỉ"
							data-text-field="deliverableName"
							data-value-field="deliverableType"
							data-filter="contains"
							data-bind="
							source: deliverableTypesSource
							"
							>
						</div>
						<div class="col-sm-3">
							<input class="form-control input-sm" name="advanced_companyName_search" id="advanced_companyName_search"
							placeholder="Tên doanh nghiệp">
						</div>
						<div class="col-sm-3">
							<input class="form-control input-sm" name="advanced_dossierNo_search" id="advanced_dossierNo_search"
							placeholder="Số hồ sơ">
						</div>
						<div class="col-sm-3">
							<input class="form-control input-sm" name="advanced_certificateNo_search" id="advanced_certificateNo_search" placeholder="Số chứng chỉ" >
						</div>
					</div>

					<div class="row P10">
						<div class="col-sm-3">
							<input name="advanced_product_search" id="advanced_product_search"
							data-role="combobox"
							data-placeholder="Loại sản phẩm"
							data-text-field="itemName"
							data-value-field="itemCode"
							data-filter="contains"
							data-bind="
							source: productSource
							"
							>
						</div>
						<div class="col-sm-3">
							<input name="advanced_brand_search" id="advanced_brand_search"
							data-role="combobox"
							data-placeholder="Nhãn hiệu"
							data-text-field="itemName"
							data-value-field="itemCode"
							data-filter="contains"
							data-bind="
							source: brandSource
							"
							>
						</div>
						<div class="col-sm-3">
							<input class="form-control input-sm" name="advanced_tradenames_search" id="advanced_tradenames_search"
							placeholder="Tên thương mại">
						</div>
						<div class="col-sm-3">
							<input class="form-control input-sm" name="advanced_typeNo_search" id="advanced_typeNo_search" placeholder="Mã kiểu loại">
						</div>
					</div>
					<div class="row PL10 PB10 border-bottom ">
						<div class="col-sm-12">
							<button class="btn btn-active" data-bind="events: {
							click: searchAdvancedDeliverables}">Tìm kiếm</button>
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
							<span>Tên chứng chỉ</span>
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
							<strong>Ngày gửi</strong></br>
							<strong>Ngày tiếp nhận</strong>
						</th>
						<th class="text-center hover-pointer">
							<strong>Số chứng chỉ</strong></br>
							<strong>Ngày ký</strong>
						</th>
						<th class="text-center hover-pointer">
							<strong>Tên doanh nghiệp</strong>
						</th>
						<th class="text-center">
							<strong>Hành động</strong>
						</th>
					</tr>
				</thead>
				<#-- Table body -->
				<tbody class="" data-role="listview" data-auto-bind="true" data-bind="source: dataSourceDeliverables" data-template="deliverableTemplate" id="listViewDeliverable">

				</tbody>
			</table>

		</div>
		<div class="footerListDeliverables row-header col-sm-12 PT20 PR0" style="background: #f6f6f6">
			<div class="clearfix align-middle" style="float: right">
				<span class="text-light-gray MR15"><i>Tổng số <span id="totalItem_dossierList_Deliverables" class="red"></span> kết quả được tìm thấy</i></span>
				<span class="show-per-page MT0">Hiển thị
					<span class="select-wrapper">
						<select class="ML5" id="itemPpageDeliverables" data-bind="events:{change: changePageSizeDeliverables}" style="background-color: #ffffff">

						</select>
					</span>
				</span>
				<span id="pagerDeliverables" class="M0 P0" data-role="pager" data-info="false" data-bind="source: dataSourceDeliverables, events:{change: stylePagerDeliverables}" data-button-count="3" style="background: #ffffff"></span>
			</div>	
		</div>

	</div>
</script>
<#-- <#include "notificationPaying.ftl"> -->
<#-- for listview dossier-->
<script type="text/x-kendo-template" id="deliverableTemplate">
	<tr class="rowTable">
		<td class="text-center count" style="width: 1%">
			#:count#
		</td>

		<td class="" style="width: 18%">
			<span>
				<a href="javascript:;" class="link-detail-employee text-hover-blue"  data-bind="events: { click : loadDeliverableDetail}" #if(ma_ho_so){# data-pk = "#:ma_ho_so#" #}#>
					#if(jQuery.type(ten_chung_chi) !== "undefined"){#
						#=ten_chung_chi#
					#}#
				</a>
			</span>

		</td>

		

		<td class="text-center" style="width: 15%">
			<p title="Mã hồ sơ">
				#if(ma_ho_so){#
				<strong>#=ma_ho_so#</strong>
				#}#

			</p>
			
			<p title="Số hồ sơ">
				#if(so_ho_so){#
				<strong>#=so_ho_so#</strong>
				#}#
			</p> 
		</td>

		<td class="text-center" style="width: 15%">
			<p title="Ngày gửi">
				#if(ngay_gui){#
				<strong>#=ngay_gui#</strong>
				#}#

			</p>
			
			<p title="Ngày tiếp nhận">
				#if(ngay_tiep_nhan){#
				<strong>#=ngay_tiep_nhan#</strong>
				#}#
			</p> 
		</td>

		<td class="text-center" style="width: 15%">
			<#-- Số chứng chỉ -->
			<#-- #=briefNote# -->
			#if(so_chung_chi){#
			<strong>#=so_chung_chi#</strong>
			#}#

			#if(ngay_ky_cc){#
			<strong>#:ngay_ky_cc#</strong>
			#}#
		</td>

		<td class="text-center" style="width: 20%">
			# if(ten_doanh_nghiep){#
			<i>#:ten_doanh_nghiep#</i>
			#}#
		</td>

		<td class="PT0 PR0 text-center" style="width: 15%">

			<a href="javascript:;" data-bind="events: { click : viewDeliverableFile}" data-pk = "#:deliverableCode#" class="text-hover-blue">Xem CC</a>
			<#--	-->
			<!-- <button type="button" class="btn-link no-border PT10 copyProfile" data-pk="">
				<i class="fa fa-file-o" aria-hidden="true"></i>
				Sao chép
			</button> -->
			<#--	-->
			<#-- #if(deliverableState == "done"){#
			<button type="button" class="btn-link no-border PT10 downloadProfile" data-pk="">
				<i class="fa fa-download" aria-hidden="true"/>
				Tải kết quả
			</button>
			
			<button type="button" class="btn-link no-border PT10 resDone" data-pk="">
				<i class="fa fa-reply" aria-hidden="true"/>
				Yêu cầu cấp lại
			</button>
			
			<button type="button" class="btn-link no-border PT10 sendAdd" data-pk="">
				<i class="fa fa-paper-plane" aria-hidden="true"></i>
				Sửa đổi bổ sung
			</button>
			#}#
			
			#if(deliverableState == "waiting"){#
			<button type="button" class="btn-link no-border PT10 resCancelling" data-pk="">
				<i class="fa fa-trash-o" aria-hidden="true"></i>
				Yêu cầu hủy
			</button></br>
			<button type="button" class="btn-link no-border PT10 sendAdd" data-pk="">
				<i class="fa fa-paper-plane" aria-hidden="true"></i>
				Gửi bổ sung
			</button>
			#}#
			
			#if(deliverableState == "receiving"){#
			<button type="button" class="btn-link no-border PT10 resCancelling" data-pk="">
				<i class="fa fa-trash-o" aria-hidden="true"></i>
				Yêu cầu hủy
			</button></br>
			
			#}# -->

		</td>

	</tr>
</script>

<script type="text/javascript">
	var fnGetFormData = function(deliverableId){
		var deliverableRes = {};
		if(deliverableId){
			$.ajax({
				url : "${api.server}/deliverables/"+deliverableId+"/formdata",
				dataType : "json",
				type : "GET",
				headers: {"groupId": ${groupId}},
				async : false,
				success : function(result){
					deliverableRes = result;
				},
				error : function(result){
					deliverableRes = {};
				}
			});
		}

		return deliverableRes;
	}
</script>