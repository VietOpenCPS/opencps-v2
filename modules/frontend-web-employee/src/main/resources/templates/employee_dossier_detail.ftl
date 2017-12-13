<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="box" id="detailDossier">

	<input type="hidden" name="dossierTemplateId" id="dossierTemplateId">
	<input type="hidden" name="dossierItemId" id="dossierItemId">
	<input type="hidden" name="dossierTemplateNo" id="dossierTemplateNo" data-bind="value : dossierTemplateNo">
	<div class="row-header align-middle">
		<div class="background-triangle-big">Tên thủ tục</div> 
		<span class="text-bold" data-bind="text:serviceName"></span>
		<div class="pull-right group-icons">
			<a href="javascript:;" onclick="funSaveDossier();">
				Lưu
				<i class="fa fa-save"></i>
			</a>
		</div>
	</div>

	<div class="dossier-general-info P15 MB15" style="display: none;">
		<div class="col-sm-6">
			<span class="text-bold">Người gửi: </span> <span class="" data-bind="text : applicantName"></span> <br>
			<span class="text-bold">Thời gian gửi: </span> <span class="" data-bind="text : "></span> <br>
			<span class="text-bold">Chuyển bởi: </span> <span class="" data-bind=""></span> <br>
			<span class="text-bold">Người thực hiện: </span> <span class=""></span> <br>
			<span class="text-bold">Trạng thái: </span> <span class=""></span> <br>
			<p><span data-bind="text: applicantNote"></span></p>
		</div>
		<div class="col-sm-6">
			<span class="text-bold">Mã số hồ sơ: </span> <span class=""></span> <br>
			<span class="text-bold">Mã tiếp nhận: </span> <span class=""></span> <br>
			<span class="text-bold">Hạn xử lý: </span> <span class=""></span> <br>
			<span class="text-bold">Ngày tiếp nhận: </span> <span class=""></span> <br>
			<span class="text-bold">Ngày hẹn trả: </span> <span class=""></span> <br>
			<span class="text-bold">Bước xử lý: </span> <span class=""></span> <br>
			<a href="javascript:;" class="">Thông tin liên hệ >></a>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-12">
			<div class="dossier-parts">
				<div class="head-part align-middle">
					<div class="background-triangle-small">I</div> <span class="text-uppercase">Thông tin chủ hồ sơ</span>
				</div>
				<div class="content-part">
					<div class="row-parts-head MT5">
						
					</div>
				</div>
			</div>
		</div>
	</div>

	<div id="dossierFormSubmiting">
		<div class="dossier-parts">
			<div class="head-part align-middle PB5">
				<div class="background-triangle-small">II</div> <span class="text-uppercase">Thành phần hồ sơ</span> <span class="text-light-gray"><i>Những thành phần hồ sơ có dấu (<span class="red">*</span>) là thành phần bắt buộc</i></span>
			</div>
			<div class="content-part" id="lsDossierTemplPart">
				<#-- <#include "customer_dossier_online_form.ftl"> -->
			</div>
			<script type="text/x-kendo-template" id="templateDossierPart">
				
			</script>
		</div>
	</div>

	<div class="row-parts-content">

	</div>
</div>



<div id="uploadFileTemplateDialog" class="modal fade" role="dialog">
	
</div>


<div id="profileDetail" class="modal fade" role="dialog">
	
</div>

<script type="text/javascript">

	$(function() {
		$("[data-role=combobox]").each(function() {
			var widget = $(this).getKendoComboBox();
			widget.input.on("focus", function() {
				widget.open();
			});
		});
	});

	var printDetailDossier = function(dossierId){
		if(dossierId){
			$.ajax({
				url : "${api.server}/dossiers/"+dossierId,
				dataType : "json",
				type : "GET",
				headers : {"groupId": ${groupId}},
				success : function(result){
					console.log("load detail dossier!");
					console.log(result);
					dataSourceDossierTemplate.read({
						dossierTemplateNo : result.dossierTemplateNo
					});
					var viewModel = kendo.observable({
						dossierTemplateNo : function(){
							result.dossierTemplateNo;
						},

						serviceCode : result.serviceCode,
						govAgencyCode : result.govAgencyCode,
						dossierTemplateNo : result.dossierTemplateNo,
						serviceName : result.serviceName,
						govAgencyName : result.govAgencyName,

						applicantName : result.applicantName,
						address : result.address,
						cityCode : result.cityCode,
						districtCode : result.districtCode,
						wardCode : result.wardCode,
						contactTelNo : result.contactTelNo,
						contactEmail : result.contactEmail,
						dossierNote : function(){
							if(result.dossierNote){
								return result.dossierNote;
							}
						},
						viaPostal : function(){
							if(result.viaPostal === 2){
								$("#viaPostal").prop('checked', true);
								$("#postalAddress").prop('disabled', false);
								$("#postalCityCode").data("kendoComboBox").enable(true);
								$("#postalTelNo").prop('disabled', false);
							}else {
								$("#viaPostal").prop('checked', false);
							}
							
						},
						postalAddress : result.postalAddress,
						postalCityCode : result.postalCityCode,
						postalTelNo : result.postalTelNo

					});
					kendo.bind($("#detailDossier"), viewModel);
				},
				error : function(result){

				}

			});
		}
	}


</script>
