<#if (Request)??>
<#include "init.ftl">
</#if>
<div id="detailDossier">
	<div class="box">

		<input type="hidden" name="dossierStatus" id="dossierStatus">
		
		<input type="hidden" name="dossierTemplateNo" id="dossierTemplateNo">
		<input type="hidden" name="dossierId" id="dossierId" value="${(dossierId)!}">
		<div class="row-header align-middle">
			<div class="background-triangle-big">Tên thủ tục</div> 
			<span class="text-bold" data-bind="text:serviceName"></span>
			<div class="pull-right group-icons">
				<a href="javascript:;" onclick="fnBack();">
					<i class="fa fa-reply" aria-hidden="true"></i>
					Quay lại
				</a>
			</div>
		</div>

		<div class="dossier-general-info P15 MB30">
			<div class="col-sm-4">
				<div class="row">
					<span class="text-bold">Tình trạng</span>: <i data-bind="text:dossierStatusText" class="red"></i>
				</div>
				<div class="row">
					<i data-bind="html:briefNote" class="text-light-gray"></i>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="row MB5" id="">
					<span>Số hồ sơ (Mã tiếp nhận)</span>: <span data-bind="text:dossierNo" class="text-bold"></span>
					
				</div>
				<div class="row" id="">
					<span>Mã hồ sơ</span>: <span data-bind="text : dossierIdCTN" class="text-bold"></span>
				</div>
			</div>
			
			<div class="col-sm-4">
				<div class="row MB5" id="">
					<span>Thời gian gửi</span>: <span data-bind="text : submitDate" class="text-bold"></span>
				</div>
				<#-- <div class="row" id="">
					<a href="javascript:;" class="text-light-blue text-underline">
						Thông tin chủ hồ sơ
					</a>
				</div> -->
			</div>
			
			<div class="col-sm-12">
				<span data-bind="attr : {actionNote1 : actionNote1}" id="actionNote1"></span> 
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="guide-section PB0">
					<div class="head-part" data-toggle="collapse" data-target="#collapseDossierG">
						<div class="background-triangle-small">
							<i class="fa fa-star"></i>
						</div> 
						<span class="text-uppercase hover-pointer">Hướng dẫn</span> 
						<i class="fa fa-angle-down pull-right hover-pointer MR15" aria-hidden="true" style="font-size: 150%;"></i>
					</div>	

					<div class="content-part collapse PB15" id="collapseDossierG">

						<span data-bind="html:dossierNote"></span>
						<#-- <p class="MB0 text-light-blue PB15"><a href="javascript:;" id="guide-toggle">Xem thêm >></a></p> -->
					</div>

				</div>
			</div>
		</div>
		<#-- Bổ sung thông tin chủ hồ sơ -->
		<div class="row">
			<div class="col-sm-12">
				<div class="dossier-parts" >
					<div class="head-part align-middle" data-toggle="collapse" data-target="#collapseDossierI">
						<div class="background-triangle-small">
							<i class="fa fa-star"></i>
						</div>
						<div class="col-sm-12 PL0">

							<span class="text-uppercase hover-pointer">
								Thông tin chủ hồ sơ
							</span>
							<i class="fa fa-angle-down pull-right hover-pointer" aria-hidden="true" style="font-size: 150%;"></i>
						</div>
					</div>
					<div class="content-part collapse in" id="collapseDossierI">
						<div class="row-parts-head MT5">

							<div class="row MT5">
								<div class="col-sm-2">
									<label>Họ và tên</label>
								</div>
								<div class="col-sm-10">
									<span data-bind="text:contactName"></span>
									
								</div>
							</div>

							<div class="row">
								<div class="col-sm-2">
									<label>Địa chỉ</label>
								</div>
								<div class="col-sm-10">
									<span data-bind="text:address"></span>
									
								</div>
							</div>

							<div class="row">
								<div class="col-sm-2">
									<label>Tỉnh/ Thành phố</label>
								</div>
								<div class="col-sm-10">
									<span data-bind="text:cityName"></span>
									
								</div>
							</div>
							<div class="row">
								<div class="col-sm-2">
									<label>Quận/ Huyện</label>
								</div>
								<div class="col-sm-10">
									<span data-bind="text:districtName" ></span>
									
								</div>
							</div>
							<div class="row">
								<div class="col-sm-2">
									<label>Xã/ Phường</label>
								</div>
								<div class="col-sm-10">
									<span data-bind="text:wardName" required></span>
									
								</div>
							</div>

							<div class="row">
								<div class="col-sm-2">
									<label>Điện thoại</label>
								</div>
								<div class="col-sm-10">
									<span data-bind="text:contactTelNo"></span>
									
								</div>
							</div>

							<div class="row">
								<div class="col-sm-2">
									<label>Địa chỉ email</label>
								</div>
								<div class="col-sm-10">
									<span data-bind="text:contactEmail"></span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<#--  -->
		<div class="row">
			<div class="col-sm-12">
				<form id="dossierFormSubmiting">
					<div class="dossier-parts">
						<div class="head-part align-middle MB5" data-toggle="collapse" data-target="#lsDossierTemplPart">
							<div class="background-triangle-small">I</div> 
							<div class="col-sm-12 PL0">
								<span class="text-uppercase hover-pointer">Thành phần hồ sơ</span> 
								<span class="hover-pointer pull-right"><i class="text-light-gray">Những thành phần hồ sơ có dấu 
									(<span class="red">*</span>) là thành phần bắt buộc</i>
									<span>
										<i class="fa fa-angle-down hover-pointer" aria-hidden="true" style="font-size: 150%;"></i>
									</span>
								</span>
							</div>
						</div>
						<div class="content-part collapse in" id="lsDossierTemplPart">
							<#-- <#include "customer_dossier_online_form.ftl"> -->
						</div>
						<script type="text/x-kendo-template" id="templateDossierPart">
							#if(partType == 1){#
							<div class="row-parts-head align-middle">
								<span class="text-bold MR5">#:itemIndex#.</span>
								<span class="hover-pointer show-dossierpart-new-tab" data-partno="#:id#" #if(hasForm){# hasForm="true" #}# data-toggle="collapse" data-target="\\#collapseDossierPart#:id#">
									#:partName# 
									#if(required){#
									<span class="red">*</span>
									<input type="hidden" id="validPart#:id#" name="validPart#:id#" class="validPart" value="0">
									#}#
								</span>

								#
									var lockState = fnCheckLockTemplate("${dossier.lockState}",id);
								#

								<div class="actions">
									
									<#-- #if("${(dossier.dossierStatus)!}" === "new" || "${(dossier.dossierStatus)!}" === "waiting" || "${(dossier.dossierStatus)!}" === "" || "${(sendAdd)!}" === "true"){#
									
									#}# -->

									<input type='file' id="file#:id#" name="file#:id#" class="hidden dossier-file" #if(multiple){# multiple #}# part-no="#:id#" file-template-no="#:fileTemplateNo#">

									<a href="javascript:;" class="dossier-component-profile" data-placement="top" title="Số tệp tin" data-partno="#:id#" data-number="#if(hasForm){# 1 #}else {# 0 #}#">
										<span class="number-in-circle" >#if(hasForm){# 1 #}else {# 0 #}#</span>
									</a>
									
								</div>
							</div>

							#if(hasForm){
							var dossierFile =  getReferentUidFile(${dossierId},id);
							console.log(dossierFile);

							var hiddenState = "";

							if(lockState){
								hiddenState = "pointer-events:none;";
							}

							#

							<div class="collapse" id="collapseDossierPart#:id#">
								
								<div class="col-sm-12" #if(dossierFile.referenceUid){# style="height:450px; width:100%;overflow:auto;" #}# >
									<div class="formAlpacaDN" id="formPartNo#:id#" style="pointer-events:none;" data-pk="#:id#" data-partname="#:partName#">
										
									</div>
								</div>
							</div>
							
							#
							$.ajax({
							url : "${api.server}/dossiers/${dossierId}/files/"+dossierFile.referenceUid+"/formscript",
							dataType : "text",
							type : "GET",
							headers : {"groupId": ${groupId}},
							success : function(result){
							$("\\#formPartNo"+id).empty();
							var alpaca1 = eval("(" + result + ")");
							var formdata = fnGetFormData(${dossierId},dossierFile.referenceUid);
							
						alpaca1.data = formdata;

						$("\\#formPartNo"+id).alpaca(alpaca1);

						<#-- $("\\#formPartNo"+id).append('<div class="row"><div class="col-xs-12 col-sm-12 "><button id="btn-save-formalpaca'+id+'" class="btn btn-active MB10 MT10 saveForm" type="button" data-pk="'+id+'" referentUid="'+referentUidFile+'">Ghi lại</button></div></div>'); -->

					},
					error : function(result){

				}
			});
		}#

		#}#
	</script>
</div>
</form>
</div>
</div>

<div class="dossier-parts" id="paymentDossier">

	<div class="head-part align-middle MB5" data-toggle="collapse" data-target="#collapseDossierPayment">

		<div class="background-triangle-small" id="paymentDossierNum">II</div> 
		<div class="col-sm-12 PL0">

			<span class="text-uppercase hover-pointer">Thanh toán</span>
			<i class="fa fa-angle-down pull-right hover-pointer" aria-hidden="true" style="font-size: 150%;"></i>
		</div>

	</div>
	<div class="content-part collapse" id="collapseDossierPayment">
		<div class="row-parts-head MT5">
			<div class="row">
				<div class="col-sm-12" id="paymentDossierContent" data-bind="value: paymentDossier">

					<div class="row MB5">
						<div class="col-sm-2">								
							<span class="text-bold">Tên phí thanh toán</span>	
						</div>
						<div class="col-sm-10" data-bind="text: paymentFee"></div> 
					</div>

					<div class="row MB5">
						<div class="col-sm-2">								
							<span class="text-bold">Gía trị thanh toán</span>	
						</div>
						<div class="col-sm-10 red">
							<span data-bind="text:paymentAmount"></span>
							<span> VNĐ</span>
						</div>
					</div>

					<div class="row MB5">
						<div class="col-sm-2">								
							<span class="text-bold">Chuyển khoản đến</span>	
						</div>
						<div class="col-sm-10" data-bind="text:paymentGovAgencyName "></div>
					</div>

					<div class="row MB5">
						<div class="col-sm-2">								
							<span class="text-bold">Thông tin tài khoản nhận</span>
						</div>
						<div class="col-sm-10" data-bind="text:paymentBankInfo"></div>
					</div>

					<div class="row MB5">
						<div class="col-sm-2">								
							<span class="text-bold">Trạng thái</span>	
						</div>
						<div class="col-sm-10" data-bind="text:paymentStatus"></div>
					</div>

					<div class="row MB10">
						<div class="col-sm-2">								
							<span class="text-bold">Ngày thanh toán</span>	
						</div>
						<div class="col-sm-10" data-bind="text:paymentApproveDatetime"></div>
					</div>

					<div id="unpaid">
						<div class="row MB10">
							<div class="col-sm-12">
								<button class="btn btn-sm btn-border-color MR10 text-light-blue" id="dossier-payment-online" data-bind="attr : {data-pk : referenceUidPayment}">Thanh toán trực tuyến</button> 
								<button class="btn btn-sm btn-border-color MR10 text-light-blue" data-bind="attr : {data-pk : referenceUidPayment}" id="dossier-payment-confirm">Thông báo đã nộp chuyển khoản</button>
								<button class="btn btn-sm btn-border-color text-light-blue" id="dossier-payment-viewpdf" data-bind="attr : {data-pk : referenceUidPayment}">Xem phiếu thanh toán</button>
							</div>
						</div>

						<div class="row MB20 MT20" data-bind="value: isPay">
							<div class="col-sm-12 text-center">
								<div class="row">
									<div class="col-sm-4">

									</div>
									<div class="col-sm-4 text-center MB10">
										<i class="fa fa-file-image-o text-center text-light-gray MB10" aria-hidden="true" style="font-size:100px;">

										</i>
										<br>
										<span id="fileNamePayment" name="fileNamePayment"></span>
										 <br>
										<span class="text-center" style="font-size: 10px;">Chứng từ thanh toán cho chuyển khoản là giấy yêu cầu nộp tiền vào ngân hàng hoặc hóa đơn chứng nhận giao dịch chuyển khoản được in ra</span>
									</div>
									<div class="col-sm-4">

									</div>
								</div>
								<input type="file" id="filePayment" name="filePayment" class="hidden" >
								<label class="btn btn-sm MB0 ML10 hover-pointer" for="filePayment" title="Tải file lên" >
									<span class="text-normal">Chọn ảnh từ máy</span>
								</label>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-11">
								<div class="form-group"> 
									<label class="control-label">Ghi chú kèm theo</label> 
									<textarea class="form-control" rows="2" id="confirmNote" name="confirmNote" data-bind="text:confirmNote">

									</textarea> 
								</div>
							</div> 
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</div> 

<div class="dossier-parts">

	<div class="head-part align-middle"  data-toggle="collapse" data-target="#resultDossierNum">

		<div class="background-triangle-small" id="resultDossierNum">II</div> 
		<div class="col-sm-12 PL0">

			<span class="text-uppercase hover-pointer">Kết quả</span>
			<i class="fa fa-angle-down pull-right hover-pointer" aria-hidden="true" style="font-size: 150%;"></i>
		</div>

	</div>
	<div class="content-part collapse in" id="collapseDossierResult">
		<div class="row-parts-head P0">
			<ul class="ul-with-border">
				<div id="listViewDossiserFileTemplate"></div>
			</ul>
			<script type="text/x-kendo-template" id="templateDossiserFileTemplate">
				<li>
					<div class="row ML5">
						<div class="col-sm-12">
							<a href="javascript:;" onclick="openFileNewtab('${api.server}/dossiers/${dossierId}/files/#:id#');" class="download-file-result" data-pk="#:id#">
								<i class="fa fa-download"></i> 
								#:displayName#
							</a>
						</div>
					</div>
				</li>
			</script>
		</div>
	</div>
</div>

<div class="dossier-parts">

	<div class="head-part align-middle" data-toggle="collapse" data-target="#collapseDossierPart">
		<div class="background-triangle-small" id="logDossierNum">III</div> 
		<div class="col-sm-12 PL0">

			<span class="text-uppercase hover-pointer">Tiến trình xử lý</span>
			<i class="fa fa-angle-down pull-right hover-pointer" aria-hidden="true" style="font-size: 150%;"></i>
		</div>

	</div>
	<div class="content-part collapse in" id="collapseDossierPart">
		<div class="row-parts-head MT5">

			<div class="table-responsive">
				<table class="table table-bordered table_history_style">
					<tbody id="listViewDossiserLog">

					</tbody>
				</table>
			</div>
			<script type="text/x-kendo-template" id="templateDossiserLog">

				#
				var jobposTitle = "";
				var briefNote = "";
				var dossier ;
				try {

				var payLoadObj = payload;

				stepName = payLoadObj.hasOwnProperty("stepName")?payLoadObj.stepName : "";
				dossier = payLoadObj.hasOwnProperty("files")?payLoadObj.files : "";

			}catch(e){
			console.log(e);
		}
		#
		<tr>
			<td style="padding-top: 15px; width: 1%;">
				<span class="text-bold">#:itemIndex#</span>
			</td>
			<td style="padding-top: 15px">

				<span class="text-bold PR10">#:author# </span>

				#
				if ( stepName!="" && stepName!=null ) {
					if(stepName === "type_cancelling"){
						stepName = "Yêu cầu hủy";
					}else if(stepName === "type_submitting"){
						stepName = "Yêu cầu sửa đổi bổ sung";
					}else if(stepName === "type_submit"){
						stepName = "Yêu cầu sửa thành phần hồ sơ";
					}else if(stepName === "type_correcting"){
						stepName = "Yêu cầu chỉnh sửa kết quả";
					}else if(stepName === "type_reject_cancelling"){
						stepName = "Từ chối yêu cầu hủy";
					}else if(stepName === "type_reject_submit"){
						stepName = "Hủy yêu cầu sửa thành phần hồ sơ";
					}else if(stepName === "type_reject_submitting"){
						stepName = "Từ chối yêu cầu bổ sung";
					}else if(stepName === "type_reject_correcting"){
						stepName = "Hủy yêu cầu chỉnh sửa kết quả";
					}
				#

				<span class="text-light-blue">(#:stepName#)</span> 
				#}#

				<p>
					#if ( createDate!="" && createDate!=null ) {#
					#= kendo.toString(kendo.parseDate(createDate, "yyyy-MM-ddTHH:mm:ss"), "HH:mm - dd/MM/yyyy")#
					#}#
				</p>

				#if ( content!="" && content!=null ) {#
				<span>Ý kiến: </span> #=content# <br>
				#}#

				#
				if(dossier){
				for(var i = 0 ; i < dossier.length ; i++){
				#
				<p>
					<a target="_blank" href="javascript:;" onclick="openFileNewtab('${api.server}/dossiers/${dossierId}/files/#:dossier[i].dossierFileId#');" class="text-greyy text-hover-blue">
						<i aria-hidden="true" class="fa fa-download PR5"></i>
						#:dossier[i].fileName#
					</a> 
				</p>
				#
			}	 
		}
		#

	</td>
</tr>
</script>

</div>
</div>
</div> 


<div class="row-parts-content" id="postal" data-bind="value: viaPostal">
	<div class="row">
		<div class="col-sm-12 MB10" >
			<span class="text-bold">Ông bà sử dụng phương thức nhận kết quả hồ sơ qua đường Bưu Điện qua địa chỉ: </span> <br>
			<span data-bind="text:postalAddress"></span> <span data-bind="text:postalCityName"></span> <span data-bind="text:postalTelNo"></span>
		</div>
	</div>
</div>
<div class="row-parts-content">
	<div class="row MB20">
		<div class="col-sm-12">
			<label>Thông báo</label>
			<textarea class="form-control" name="applicantNote" id="applicantNote" placeholder="Ghi chú" data-bind="text : applicantNote" rows="3"></textarea>
		</div>
	</div>
</div>

<div id="uploadFileTemplateDialog" class="modal fade" role="dialog">
	
</div>

<#-- <div id="profileDetail" class="modal fade" role="dialog">

</div> -->

</div>
<div class="button-row MT20">

	<button class="btn btn-active" id="btn-back-dossier" type="button"><i class="fa fa-reply" aria-hidden="true"></i> Quay lại</button>
	
</div>
</div>

<script type="text/javascript">


	$("#btn-submit-dossier").button('loading');

	var dataSourceDossierTemplate = new kendo.data.DataSource({
		transport :{
			read : function(options){
				if(options.data.dossierTemplateNo){
					$.ajax({
						url : "${api.server}/dossiertemplates/"+options.data.dossierTemplateNo,
						dataType : "json",
						type : "GET",
						headers : {"groupId": ${groupId}},
						data : {

						},
						success : function(result){
							options.success(result.dossierParts);
							$("#dossierTemplateNo").val(result.templateNo);
						},
						error : function(result){
							options.error(result);
						}
					});
				}
			}
		},
		schema : {
			model : {
				id : "partNo"
			}
		}
	});

	var fnLoadPayment = function(dossierId){

		var resultModel = null;
		if(dossierId){
			$.ajax({
				url : "${api.server}/dossiers/"+dossierId+"/payments",
				dataType : "json",
				type : "GET",
				headers : {"groupId": ${groupId}},
				async : false,
				data : {
					paymentStatus : 0
				},
				success : function(result){
					if(result.data){
						resultModel = result.data[0];
					}
				},
				error :  function(result){
					$("#paymentDossier").remove();
				}
			});
		}

		return resultModel;
	}

	var printDetailDossier = function(dossierId){
			if(dossierId){
				$.ajax({
					url : "${api.server}/dossiers/"+dossierId,
					dataType : "json",
					type : "GET",
					headers : {"groupId": ${groupId}},
					success : function(result){
						
						dataSourceDossierTemplate.read({
							dossierTemplateNo : result.dossierTemplateNo
						});

						var payment = fnLoadPayment(result.dossierId);
						if(!payment){
							$("#paymentDossier").remove();
							$("#resultDossierNum").html("II");
							$("#logDossierNum").html("III");
						}else {
							$("#resultDossierNum").html("III");
							$("#logDossierNum").html("IV");
						}

						var viewModel = kendo.observable({
							dossierId : result.dossierId,
							lockState : function(e){
								if(result.lockState){
									if(result.lockState.startsWith("LOCK")){
										if(result.lockState === "LOCK ALL"){
											$("#btn-submit-dossier").remove();
											$("#btn-delete-dossier").remove();
											$("#btn-submit-dossier-header").remove();
											$("#btn-delete-dossier-header").remove();
										}else {
											$("#btn-submit-dossier").show();
											$("#btn-delete-dossier").show();
											$("#btn-submit-dossier-header").show();
											$("#btn-delete-dossier-header").show();
										}
									}else {
										$("#btn-submit-dossier").show();
										$("#btn-delete-dossier").show();
										$("#btn-submit-dossier-header").show();
										$("#btn-delete-dossier-header").show();
									}

								}else {
									$("#btn-submit-dossier").show();
									$("#btn-delete-dossier").show();
									$("#btn-submit-dossier-header").show();
									$("#btn-delete-dossier-header").show();
								}

								return "";
							},
							dossierIdCTN : function(e){
								if(result.dossierIdCTN){
									return result.dossierIdCTN;
								}

								return "";
							},
							serviceName : result.serviceName,
							govAgencyName : result.govAgencyName,

							actionNote1 : function(e){

								if(result.actionNote){
									$("#actionNote1").html('<i class="fa fa-bolt" aria-hidden="true" style="color: red;"></i> <span>'+result.actionNote+'</span>');
								}else {
									$("#actionNote1").remove();
								}

								return;
							},

							applicantName : result.applicantName,
							applicantNote : "",
							address : result.address,
							briefNote : function(e){
								if(result.briefNote){
									return result.briefNote;
								}else {
									return "";
								}
							},
							submitDate : function(){
								if(result.submitDate){
									return result.submitDate;
								}

								return "";
							},
							cityCode : result.cityCode,
							districtCode : result.districtCode,
							wardCode : result.wardCode,
							contactTelNo : result.contactTelNo,
							contactEmail : result.contactEmail,
							dossierNo : result.dossierNo,
							dossierStatusText : result.dossierStatusText,
							stepInstruction : result.stepInstruction,
							dossierStatus : result.dossierStatus,
							postalAddress : result.postalAddress,
							postalCityName : result.postalCityName,
							postalTelNo : result.postalTelNo,
							dossierTemplateNo : result.dossierTemplateNo,
							contactName: result.contactName,
							cityName:result.cityName,
							districtName:result.districtName,
							wardName:result.wardName,
							viaPostal : function(e){
								
								if(result.viaPostal === 0 || result.viaPostal === 1){
									$("#postal").remove();
								}
							},
							submissionNote : function(e){
								if(result.submissionNote){
									$("#guideDossier").show();
									return result.submissionNote;
								}else {
									$("#guideDossier").hide();
									return "";
								}
							},
							paymentDossier : payment,
							paymentFee : function(e){
								if(this.get('paymentDossier')){
									if(this.get('paymentDossier').paymentFee){
										return this.get('paymentDossier').paymentFee;
									}
								}
								return "";
							},
							paymentAmount : function(e){
								if(this.get('paymentDossier').paymentAmount){
									var value = this.get('paymentDossier').paymentAmount;
									var moneyCur = (value/1).toFixed(0).replace('.', ',');
									return moneyCur.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
								}
								return "";
							},
							paymentGovAgencyName : function(e){
								if(this.get('paymentDossier')){
									if(this.get('paymentDossier').govAgencyName){
										return this.get('paymentDossier').govAgencyName;
									}
								}
								return "";
							},
							paymentBankInfo : function(e){
								if(this.get('paymentDossier')){
									if(this.get('paymentDossier').bankInfo){
										return this.get('paymentDossier').bankInfo;
									}
								}
								return "";
							},
							paymentStatus : function(e){
								if(this.get('paymentDossier')){
									if(this.get('paymentDossier').paymentStatus === 0){
										$("#dossier-payment-confirm").prop("disabled",false);
										return "Chờ nộp";
									}else if(this.get('paymentDossier').paymentStatus === 1){
										$("#dossier-payment-confirm").prop("disabled",true);
										return "Báo đã nộp";
									}else if(this.get('paymentDossier').paymentStatus === 2){
										$("#dossier-payment-confirm").prop("disabled",false);
										return "Hoàn thành";
									}else {
										$("#dossier-payment-confirm").prop("disabled",false);
										return "Không hợp lệ";
									}
								}
								return "";
							},
							paymentApproveDatetime : function(e){
								if(this.get('paymentDossier')){
									if(this.get('paymentDossier').approveDatetime){
										return this.get('paymentDossier').approveDatetime;
									}
								}
								return "---";
							},
							paymentConfirmNote : function(e){
								if(this.get('paymentDossier')){
									if(this.get('paymentDossier').confirmNote){
										return this.get('paymentDossier').confirmNote;
									}
								}
								return "";
							},
							referenceUidPayment : function(e){
								if(this.get('paymentDossier')){
									if(this.get('paymentDossier').referenceUid){
										return this.get('paymentDossier').referenceUid;
									}
								}
								return "";
							},
							isPay : function(){
								if(this.get('paymentDossier')){
									if(this.get('paymentDossier').paymentStatus !== 2){
										$("#unpaid").show();
										$("#alreadyPaid").hide();
									}
								}
							},
							cancellingDate : function(){
								if(result.cancellingDate){
									$("#btn-rescancelling-dossier").hide();
									$("#btn-cancelling-header").hide();
								}

								return "";
							},
							correctingDate : function(){
								if(result.correctingDate){
									$("#btn-sendReissue-dossier-header,#btn-sendReissue-dossier-footer").hide();
								}
								return "";
							},
							submitting : function(){
								if(result.submitting){
									return result.submitting;
								}
								return;
							},
							endorsementDate : function(){
								if(result.endorsementDate){
									$("#btn-sendadd-dossier-header,#btn-sendadd-dossier-footer").hide();
								}

								return "";
							}

						});

						kendo.bind($("#detailDossier"), viewModel);

						},
						error : function(result){
							$("#paymentDossier").html("");
						}

					});
				}
		}

	var arrIsChangeForm = [];

	var fnCheckIsChangeForm = function(){
		if (arrIsChangeForm) {
			for (var i = 0; i < arrIsChangeForm.length; i++) {
				if(!arrIsChangeForm[i].isSave){
					return arrIsChangeForm[i];
				}
			}
		}

		return null;
	}

	var fnCheckValidTemplate = function(){
		console.log($(".validPart"));
		var valid = true;
		try {

			$(".validPart").each(function(index){
				console.log($(this).val());
				if($(this).val() === "0"){
					valid = false;
				}
			});

		}catch(e){
			valid = false;
		}

		return valid;
	}

	var fnCheckLockTemplate = function(lockState, item){
		if(lockState.startsWith("LOCK")){

			if(lockState === "LOCK INPUT"){
				return true;
			}else if(lockState === "LOCK ALL"){
				return true;
			}else if (lockState !== "LOCK ALL" && lockState !== "LOCK INPUT" && lockState !== "LOCK OUTPUT" )  {
				var partLocksStr = lockState.split(" ")[1];
				if(partLocksStr){
					var partLocks = partLocksStr.split(",");
					for (var i = 0; i < partLocks.length; i++) {
						if(partLocks[i] === item){
							return true;
						}
					}
				}
			}

		}else if(lockState.startsWith("UPDATE")){
			if(lockState === "UPDATE INPUT"){
				return false;
			}

			if(lockState === "UPDATE ALL"){
				return false;
			}

			if (lockState !== "UPDATE ALL" && lockState !== "UPDATE INPUT" && lockState !== "UPDATE OUTPUT" ){
				var partLocksStr = lockState.split(" ")[1];
				if(partLocksStr){
					var partLocks = partLocksStr.split(",");
					for (var i = 0; i < partLocks.length; i++) {
						if(partLocks[i] === item){
							return false;
						}
					}
				}
			}
		}

		return false;
	}

	$(function(){
		$( "body" ).data( "dossierFiles", [] );


		$(document).off("click",".show-dossierpart-new-tab");
		$(document).on("click",".show-dossierpart-new-tab",function(){
			var partNo = $(this).attr("data-partno");
			var hasForm = $(this).attr("hasForm");
			var hasFile = $(this).attr("hasFile");

			if(!hasForm){
				if(hasFile){
					var dossierId = "${(dossierId)!}";
					var dossierTemplateNo = "${(dossier.dossierTemplateNo)!}";

					var url = "/"+dossierId+"/files/"+dossierTemplateNo+"/"+partNo;

					window.open(url,"_blank");
				}
				
			}
			
		});

		$(document).off("click",".dossier-file");
		$(document).on("click",".dossier-file",function(){
			funDossierFile("${dossierId}",funGenNumberFile);
		});

		$(document).off("change",".dossier-file");
		$(document).on("change",".dossier-file",function(){

			var partNo = $(this).attr("part-no");
			var fileTemplateNo = $(this).attr("file-template-no");
			var dossierTemplateNo = $("#dossierTemplateNo").val();
			var hasform = $(this).attr("hasform");

			kendo.ui.progress($("#mainType2"), true);

			funUploadFile($(this),partNo,dossierTemplateNo+"",fileTemplateNo,hasform);
		});

		$(document).off("click",".dossier-component-profile");
		$(document).on("click",".dossier-component-profile",function(){
			var partNo = $(this).attr("data-partno");
			var dossierId = "${(dossierId)!}";
			var dossierTemplateNo = "${(dossier.dossierTemplateNo)!}";
			var lockState = "${(dossier.lockState)!}";
			
			var url = "#/"+dossierId+"/files/"+dossierTemplateNo+"/"+partNo;

			window.open(url,"_blank");

			funDossierFile("${dossierId}",funGenNumberFile);

		});

		$(document).off("click",".delete-dossier-file");
		$(document).on("click",".delete-dossier-file",function(){
			if(navigator.onLine){

				var dossierId  = "${dossierId}";
				var dataPartNo = $(this).attr("data-partno");
				var eForm = $(this).attr("eForm");
				var fileTemplateNo = $(this).attr("fileTemplateNo");
				try{
					$("#formPartNo"+dataPartNo).alpaca('get').setValue({});
				}catch (e){

				}

				var cf = confirm("Bạn có muốn xóa file toàn bộ file của thành phần này!");
				if(cf){
					if(dossierId && dataPartNo){
						if(eForm === "true"){

							$.ajax({
								url : "${api.server}/dossiers/"+dossierId+"/files",
								dataType : "json",
								type : "GET",	
								headers : {"groupId": ${groupId}},
								success : function(result) {
									var data = result.data;
									if(data){
										var arrIsSuccess = new Array();
										for (var i = 0; i < data.length; i++) {
											var isSuccess = false;
											if(dataPartNo === data[i].dossierPartNo){
												isSuccess = removeDossierFile(dossierId, data[i].referenceUid);
												arrIsSuccess.push(isSuccess);

											}
										}

										if(jQuery.inArray( false, arrIsSuccess ) == -1){
											$(".dossier-component-profile").filter("[data-partno="+dataPartNo+"]").html('<span class="number-in-circle" >0</span>');

											$(".dossier-component-profile").filter("[data-partno="+dataPartNo+"]").attr("data-number",0);

											if(navigator.onLine){
												notification.show({
													message: "Yêu cầu được thực hiện thành công"
												}, "success");
											}
											$("#validPart"+dataPartNo).val("0");
											
										}else {
											if(navigator.onLine){
												notification.show({
													message: "Xẩy ra lỗi, vui lòng thử lại"
												}, "error");
											}
										}

									}
								},
								error : function(result) {
									if(navigator.onLine){
										notification.show({
											message: "Xẩy ra lỗi, vui lòng thử lại"
										}, "error");
									}
								}
							});

						}else {
							removeDossierFileNotEform(dossierId,fileTemplateNo,dataPartNo);
						}
					}
					
				}
			}else{
				alert("Không có kết nối internet, vui lòng kiểm tra kết nối của bạn!");
			}
		});

		$("#btn-view-extendguide").click(function(){
			if($("#extend-guide").attr("status")=="none"){
				$("#extend-guide").show();
				$("#extend-guide").attr("status","show");
			}else{
				$("#extend-guide").hide();
				$("#extend-guide").attr("status","none");
			}
		});

		var dataSourceDossiserFileTemplate;
		var dataSourceDossiserLog;

		dataSourceDossiserFileTemplate=new kendo.data.DataSource({
			transport:{
				read:function(options){
					$.ajax({
						url : "${api.server}/dossiers/${dossierId}/files",
						dataType : "json",
						type : "GET",
						headers : {"groupId": ${groupId}},
						data : {
							
						},
						success : function(result){
							var arrResult = fnGetPartype2(result.data);
							options.success(arrResult);
						},
						error : function(result){
							options.error(result);
						}
					});
				}
			},
			schema : {
				model : {
					id : "dossierFileId"
				}
			}
		});

		dataSourceDossiserLog = new kendo.data.DataSource({
			transport:{
				read:function(options){
					$.ajax({
						url : "${api.server}/dossierlogs/${dossierId}/logs",
						dataType : "json",
						type : "GET",
						headers : {"groupId": ${groupId}},
						data:{

						},
						success : function(result){
							result["data"] = result.hasOwnProperty("data")?result["data"]:[];
							
							var arrLogsResult = fnGetLogs(result.data);
							console.log(arrLogsResult);
							options.success(arrLogsResult);
						},
						error : function(result){
							options.error(result);
						}
					});
				}
			},
			schema:{
				data:"data",
				total:"total",
				model:{
					id:"dossierLogId"
				}
			}
		});

		$("#listViewDossiserFileTemplate").kendoListView({
			dataSource:dataSourceDossiserFileTemplate,
			template:kendo.template($("#templateDossiserFileTemplate").html()),
			autoBind: true
		});

		var fnGetPartype2 = function(arrFile){
			var arrResult = new Array();
			if(arrFile){
				for (var i = 0; i < arrFile.length; i++) {
					if(arrFile[i].dossierPartType == 2){
						arrResult.push(arrFile[i]);
					}
				}
			}
			
			return arrResult;
		}

		var fnGetLogs = function(arrLogs){
			
			var arrLogsResult = new Array();
			var count = 0;
			var result = {};
			if(arrLogs){
				for (var i = 0; i < arrLogs.length; i++) {
					if(arrLogs[i].notificationType === 'PROCESS_TYPE'){
						arrLogsResult.push(arrLogs[i]);
						count++;
					}
				}
			}
			result["data"] = arrLogsResult;
			result["total"] = count;
			return result;
		}

		var indexDossiserLog = 0;
		$("#listViewDossiserLog").kendoListView({
			dataSource:dataSourceDossiserLog,
			autoBind: true,
			dataBound : function(){
				indexDossiserLog = 0;
			},
			template: function(data){

				indexDossiserLog ++;

				data.itemIndex = indexDossiserLog;

				return kendo.template($("#templateDossiserLog").html())(data);

			},
		});

		var indexDossiserPart =0 ;
		$("#lsDossierTemplPart").kendoListView({
			dataSource : dataSourceDossierTemplate,
			autoBind : false,
			change : function(){

			},
			template : function(data){

				if(data.partType === 1){
					indexDossiserPart ++;
				}

				data.itemIndex = indexDossiserPart;

				return kendo.template($("#templateDossierPart").html())(data);

			},
			dataBound : function(){
				indexDossiserPart = 0;
			var dossierParts = this.dataSource.view();
			//kiem tra dossier status, neu status thuoc new thi cho phep upoad hoac sua file
			//fnCheckStatusAndHideUpload("${(dossier.dossierStatus)!}",dossierParts);

			//gen number file cho icon thanh phan ho so
			
			funDossierFile("${dossierId}",funGenNumberFile);
		}
	});

printDetailDossier(${dossierId});

var funUploadFile = function(file, partNo , dossierTemplateNo , fileTemplateNo){
	var data = new FormData();
	var fileLength = $(file)[0].files.length;
	data.append( 'displayName', "");
	data.append( 'file', $(file)[0].files[0]);
	data.append('dossierPartNo', partNo);
	data.append('referenceUid', "");
	data.append('dossierTemplateNo', dossierTemplateNo);
	data.append('fileTemplateNo', fileTemplateNo);
	data.append('formData', "");
	data.append('isSync', "true");
	data.append('fileType', "");
	//data.append('deliverableCode', "");

	$.ajax({
		type : 'POST', 
		url  : '${api.server}/dossiers/${dossierId}/files', 
		data : data,
		headers: {"groupId": ${groupId}},
		processData: false,
		contentType: false,
		cache: false,
		success :  function(result){ 
			kendo.ui.progress($("#mainType2"), false);

			funDossierFile("${dossierId}",funGenNumberFile);

			notification.show({
				message: "Yêu cầu được thực hiện thành công"
			}, "success");

			$(file).val("");

		},
		error:function(result){
			kendo.ui.progress($("#mainType2"), false);
			
			notification.show({
				message: "Thêm không thành công do số biểu mẫu bị trùng."
			}, "error");

			$(file).val("");
		}
	});

}

var fnCheckStatusAndHideUpload = function(dossierStatus, dossierParts){
	/*if(dossierStatus !== "" && dossierStatus !== "new" && dossierStatus !== "waiting" && "${(sendAdd)!}" !== "true"){
		$(".uploadfile-form-repository").remove();
		$(".lbl-dossier-file").remove();
		$(".delete-dossier-file").remove();
	}*/

	console.log("dossierParts=========",dossierParts);
	var promise = new Promise(function(resolve, reject){
		$.ajax({
			url : "${api.server}/dossiers/${(dossierId)!}/processstep",
			dataType : "json",
			type : "GET",
			headers : {"groupId": ${groupId}},
			success : function(result){
				resolve(result);
			},
			error : function(xhr){
				reject(xhr);
			}
		});
	});


	promise.then(function(success){
		var arrPart = success.split(",");
		console.log("arrPart=========",arrPart);
		if(arrPart){
			for (var i = 0; i < arrPart.length; i++) {
				var index = $.inArray( arrPart[i], dossierParts );
				if(index != -1){
					$(".lbl-dossier-file[data-partno="+arrPart[i]+"]").remove();
					$(".uploadfile-form-repository[partno="+arrPart[i]+"]").remove();
					$(".delete-dossier-file[data-partno="+arrPart[i]+"]").remove();
				}
			}
		}
		

	},function(error){

	});


}

var removeDossierFile = function(dossierId, fileId){
	var isSuccess = false;

	$.ajax({
		url : "${api.server}/dossiers/"+dossierId+"/files/"+fileId+"/resetformdata",
		dataType : "json",
		type : "PUT",
		headers : {"groupId": ${groupId}},
		async : false,
		success : function(result) {
			isSuccess = true;

		},
		error : function(result) {
			isSuccess = false;
		}	
	});
	return isSuccess;
}

var removeDossierFileNotEform = function(dossierId,fileTemplateNo,partNo){

	if(navigator.onLine){
		var data = new FormData();
		$.ajax({
			url : "${api.server}/dossiers/"+dossierId+"/files/"+fileTemplateNo+"/all",
			type : "DELETE",
			dataType : "json",
			processData: false,
			contentType: false,
			cache: false,
			headers : {
				"groupId": ${groupId},
				"Accept" : "application/json"
			},
			data : data,
			success : function(result) {
				notification.show({
					message: "Yêu cầu được thực hiện thành công"
				}, "success");
				$(".dossier-component-profile").filter("[data-partno="+partNo+"]").html('<span class="number-in-circle" >0</span>');

				$(".dossier-component-profile").filter("[data-partno="+partNo+"]").attr("data-number",0);
				$("#validPart"+partNo).val("0");
			},
			error : function(result) {
				notification.show({
					message: "Xảy ra lỗi, xin vui lòng thử lại"
				}, "error");
			}
		});
	}

	
}

var funDossierFile = function(dossierId,callBack){
	if(dossierId){
		$.ajax({
			url : "${api.server}/dossiers/"+dossierId+"/files",
			dataType : "json",
			type : "GET",
			headers : {"groupId": ${groupId}},
			success : function(result){
				if(result.data){
					callBack(result.data);
				}else {
					callBack([]);
				}


			},
			error : function(result){

			}
		});
	}
}


var funGenNumberFile = function(arrCount){

	$(".dossier-component-profile").each(function(index){
		var partNo = $(this).attr("data-partno");
		var found = $.grep(arrCount, function(v) {
			return v.dossierPartNo === partNo;
		});

		$(this).attr("data-number",found.length);
		$(this).html('<span class="number-in-circle" >'+found.length+'</span>');
		if(found.length > 0){
			$(".show-dossierpart-new-tab[data-partno="+partNo+"]").attr('hasFile', 'true');
		}else {
			$("#validPart"+partNo).val("0");
			$(".show-dossierpart-new-tab[data-partno="+partNo+"]").attr('hasFile', '');
		}
	});

	$("#btn-submit-dossier").button('reset');

}
});


var getReferentUidFile = function(dossierId,dossierPartNo){
	var dossierFile;
	if(dossierId){
		$.ajax({
			type : 'GET', 
			dataType : "json",
			url  : '${api.server}/dossiers/${dossierId}/files', 
			headers: {"groupId": ${groupId}},
			async : false,
			success :  function(result){ 
				if(result.data){
					for (var i = 0; i < result.data.length; i++) {
						if(result.data[i].eForm){
							if(dossierPartNo === result.data[i].dossierPartNo){
								dossierFile = result.data[i];
								return ;
							}
						}
					}
				}

			},
			error:function(result){

			}
		});
	}

	return dossierFile;
}

var fnGetFormData = function(dossierId,referentUid){
	var value = null;
	if(dossierId && referentUid){
		$.ajax({
			url : "${api.server}/dossiers/"+dossierId+"/files/"+referentUid+"/formdata",
			type : "GET",
			dataType : "json",
			async : false,
			success : function(result){
				value = result;

			},
			error : function(result){

			}

		});
	}

	return value;
}


var fnSaveForm = function(id, value){
	var current = $("#btn-save-formalpaca"+id);
	var referentUid = current.attr("referenceUid");
	
	if(referentUid){
		$.ajax({
			url : "${api.server}/dossiers/${dossierId}/files/"+referentUid+"/formdata",
			dataType : "json",
			type : "PUT",
			headers: {
				"groupId": ${groupId},
				Accept : "application/json"
			},
			data : {
				formdata: JSON.stringify(value)
			},
			success : function(result){
				notification.show({
					message: "Yêu cầu được thực hiện thành công!"
				}, "success");
				
				$("#validPart"+id).val("1");
				try{
					for (var i = 0; i < arrIsChangeForm.length; i++) {
						if(arrIsChangeForm[i].partNo === id){
							arrIsChangeForm[i].isSave = true;
						}
					}
				}catch(e){

				}
			},
			error : function(result){
				notification.show({
					message: "Thực hiện không thành công, xin vui lòng thử lại!"
				}, "error");
			}
		});
	}
}

$(document).off("click",".saveFormAlpaca");
$(document).on("click",".saveFormAlpaca",function(event){
	var id = $(this).attr("data-pk");
	var referentUidFile = $(this).attr("referenceUid");

	var formType = $("#formPartNo"+id+" .formType").val();
	var value ;

	if(formType !== "dklr"){
		value = $("#formPartNo"+id).alpaca('get').getValue();


		var errorMessage = '';
		$("#formPartNo"+id+' div[class*="has-error"] > label').each(function( index ) {

			errorMessage = "notValid";

		});

		if(errorMessage === '' && referentUidFile){
			
			$.ajax({
				url : "${api.server}/dossiers/${dossierId}/files/"+referentUidFile+"/formdata",
				dataType : "json",
				type : "PUT",
				headers: {
					"groupId": ${groupId},
					Accept : "application/json"
				},
				data : {
					formdata: JSON.stringify(value)
				},
				success : function(result){
					notification.show({
						message: "Yêu cầu được thực hiện thành công!"
					}, "success");
					
					$("#validPart"+id).val("1");

					try{
						for (var i = 0; i < arrIsChangeForm.length; i++) {
							if(arrIsChangeForm[i].partNo === id){
								arrIsChangeForm[i].isSave = true;
							}
						}
						jQuery.data( document.body, "arrIsChangeForm", arrIsChangeForm );
					}catch(e){

					}
				},
				error : function(result){
					notification.show({
						message: "Thực hiện không thành công, xin vui lòng thử lại!"
					}, "error");
				}
			});

		}else {
			notification.show({
				message: "Vui lòng kiểm tra lại các thông tin bắt buộc trước khi ghi lại!"
			}, "error");
		}
	}

});

$("#btn-sendReissue-dossier").click(function(){
	var cf = fnConfirm("Thông báo",
		"Bạn có muốn gửi yêu cầu cấp lại?", 
		"OK", "Thoát",
		function(){

			fnCorrecting(${(dossierId)!});

		}, function(){

		});

	cf.open();
	
});


var createActionDossier = function(dossierId, callBackSubmit){
	if(dossierId){
		$.ajax({
			url : "${api.server}/dossiers/"+dossierId+"/actions",
			dataType : "json",
			type : "POST",
			headers: {
				"groupId": ${groupId},
				Accept : "application/json"
			},
			data : {
				actionCode  : 1100,
				actionNote :  $("textarea#applicantNote").val(),
				actionUser: '${(userInfo.actionUser)!}'
			},
			success : function(result){
				callBackSubmit();
				console.log("create acion dossier success!");
			},
			error : function(result){
			}
		});
	}
}

var fnSubmitting2 = function(dossierId){
	kendo.ui.progress($("#mainType2"), true);
	$("#btn-sendadd-dossier-header,#btn-sendadd-dossier-footer").hide();
	console.log("----------5" + "${(dossier.dossierStatus)!}");
	var applicantNote = $("textarea#applicantNote").val();
	/*if(applicantNote.trim() == ''){
		alert('Bạn phải nhập ý kiến trước khi gửi.');
		$("textarea#applicantNote").focus();
		return;
	}*/
	console.log("run sendReissue!");

	$.ajax({
		url : "${api.server}/dossiers/"+dossierId+"/submitting",
		dataType : "json",
		type : "POST",
		headers : {
			"groupId" : ${groupId},
			"Accept" : "application/json"
		},
		processData: false,
		contentType: 'text/plain',
		data : $("textarea#applicantNote").val(),
		success : function(result){
			kendo.ui.progress($("#mainType2"), false);
			notification.show({
				message: "Yêu cầu được thực hiện thành công!"
			}, "success");
			$("#btn-sendadd-dossier-header,#btn-sendadd-dossier-footer").hide();
			try {
				manageDossier.navigate("/"+statusRouteTem.status); 
				$('html,body').scrollTop(0);
			}catch(e){

			}
		},
		error : function(result){
			kendo.ui.progress($("#mainType2"), false);
			notification.show({
				message: "Thực hiện không thành công, xin vui lòng thử lại!"
			}, "error");
			$("#btn-sendadd-dossier-header,#btn-sendadd-dossier-footer").show();
		}
	});

	
}


var fnCorrecting = function(dossierId){
	kendo.ui.progress($("#mainType2"), true);
	$("#btn-sendReissue-dossier-header,#btn-sendReissue-dossier-footer").hide();
	console.log("----------4" + "${(dossier.dossierStatus)!}");
	var applicantNote = $("textarea#applicantNote").val();
	/*if(applicantNote.trim() == ''){
		alert('Bạn phải nhập ý kiến trước khi gửi.');
		$("textarea#applicantNote").focus();
		return;
	}*/
	console.log("run sendReissue!");

	$.ajax({
		url : "${api.server}/dossiers/"+dossierId+"/correcting",
		dataType : "json",
		type : "POST",
		headers : {
			"groupId" : ${groupId},
			"Accept" : "application/json"
		},
		processData: false,
		contentType: 'text/plain',
		data : $("textarea#applicantNote").val(),
		success : function(result){
			kendo.ui.progress($("#mainType2"), false);
			notification.show({
				message: "Yêu cầu được thực hiện thành công!"
			}, "success");
			$("#btn-sendReissue-dossier-header,#btn-sendReissue-dossier-footer").hide();
			$("#btn-sendReissue-dossier-header").hide();
			$("#btn-sendReissue-dossier-footer").hide();

			try {
				manageDossier.navigate("/"+statusRouteTem.status); 
				$('html,body').scrollTop(0);
			}catch(e){

			}
		},
		error : function(result){
			kendo.ui.progress($("#mainType2"), false);
			$("#btn-sendReissue-dossier-header,#btn-sendReissue-dossier-footer").show();
			notification.show({
				message: "Thực hiện không thành công, xin vui lòng thử lại!"
			}, "error");
		}
	});

}


$("#btn-cancelling-header").click(function(){
	var cf = fnConfirm("Thông báo",
		"Bạn có muốn gửi yêu cầu hủy?", 
		"OK", "Thoát",
		function(){

			fnCancelling(${(dossierId)!});

		}, function(){

		});

	cf.open();
});


$("#btn-rescancelling-dossier").click(function(){
	var cf = fnConfirm("Thông báo",
		"Bạn có muốn gửi yêu cầu hủy?", 
		"OK", "Thoát",
		function(){

			fnCancelling(${(dossierId)!});

		}, function(){

		});

	cf.open();
	
});

var fnCancelling = function(dossierId){
	kendo.ui.progress($("#mainType2"), true);
	$("#btn-rescancelling-dossier,#btn-cancelling-header").hide();
	console.log("-----------1" + "${(dossier.dossierStatus)!}");
	if("${(dossier.dossierStatus)!}" !== "new" && "${(dossier.dossierStatus)!}" !== "null" && "${(dossier.dossierStatus)!}" !== "done"){

		/*var applicantNote = $("textarea#applicantNote").val();
		if(applicantNote.trim() == ''){
			alert('Bạn phải nhập ý kiến trước khi gửi.');
			$("textarea#applicantNote").focus();
			return;
		}*/

		console.log("run rescancelling!");

		$.ajax({
			url : "${api.server}/dossiers/"+dossierId+"/cancelling",
			dataType : "json",
			type : "POST",
			headers : {
				"groupId" : ${groupId},
				"Accept" : "application/json"
			},
			processData: false,
			contentType: 'text/plain',
			data : $("textarea#applicantNote").val(),
			success : function(result){
				kendo.ui.progress($("#mainType2"), false);
				notification.show({
					message: "Yêu cầu được thực hiện thành công!"
				}, "success");

				$("#btn-rescancelling-dossier,#btn-cancelling-header").hide();

				try{
					manageDossier.navigate("/"+statusRouteTem.status); 
					$('html,body').scrollTop(0);
				}catch(e){
					
				}
			},
			error : function(result){
				kendo.ui.progress($("#mainType2"), false);
				$("#btn-rescancelling-dossier,#btn-cancelling-header").show();
				notification.show({
					message: "Thực hiện không thành công, xin vui lòng thử lại!"
				}, "error");
			}
		});

	}

}


$("#btn-back-dossier").click(function(){
	fnBack();
});

$("#btn-sendadd-dossier-header,#btn-sendadd-dossier-footer").click(function(){
	var validateDossierTemplate = fnCheckValidTemplate();

	if(!validateDossierTemplate){
		notification.show({
			message: "Vui lòng kiểm tra lại các thông tin bắt buộc của các thành phần hồ sơ!"
		}, "error");

		return false;
	}
	
	var cf = fnConfirm("Thông báo",
		"Bạn có muốn gửi yêu cầu bổ sung?", 
		"OK", "Thoát",
		function(){

			fnSubmitting2(${(dossierId)!});

		}, function(){

		});

	cf.open();
});

$("#btn-sendReissue-dossier-header,#btn-sendReissue-dossier-footer").click(function(){

	var cf = fnConfirm("Thông báo",
		"Bạn có muốn gửi yêu cầu cấp lại?", 
		"OK", "Thoát",
		function(){

			fnCorrecting(${(dossierId)!});

		}, function(){

		});

	cf.open();
	
});

$("#btn-submit-dossier,#btn-submit-dossier-header").click(function(){

	
	/*if($("textarea#applicantNote").val() === ""){
		var cf = showAlert("Thông báo","Bạn phải nhập thông báo gửi bổ sung!","Thoát");
		cf.open();
		return false;
	}*/

	var validateDossierTemplate = fnCheckValidTemplate();

	if(!validateDossierTemplate){
		notification.show({
			message: "Vui lòng kiểm tra lại các thông tin bắt buộc của các thành phần hồ sơ!"
		}, "error");

		return false;
	}

	var cf = fnConfirm("Thông báo",
		"Bạn có muốn nộp hồ sơ?", 
		"OK", "Thoát",
		function(){

			$("#btn-back-dossier").prop("disabled","disabled");
			var isNext = false;
			try{
				var isChange = fnCheckIsChangeForm();
				console.log("isChange");
				if(isChange){
					var cf = confirm("Bạn vừa thay đổi dữ liệu trong "+isChange.partName+" bạn có muốn lưu lại!");
					if(cf){
						$(".saveFormAlpaca[data-pk="+isChange.partNo+"]").trigger("click");
					}else {

						$.each(arrIsChangeForm,function(index,value){
							if(value.partNo === isChange.partNo){
								arrIsChangeForm.splice(index, 1);
								return ;
							}
						});
						isNext = true;
					}

				}else {
					isNext = true;
				}
			}catch(e){
				isNext = true;
			}


			if(isNext){
				$.ajax({
					url : "${api.server}/dossiers/${dossierId}",
					dataType : "json",
					type : "PUT",
					headers: {
						"groupId": ${groupId}
					},
					data : {
						applicantNote : $("textarea#applicantNote").val()
					},
					success : function(result){
						$.ajax({
							url : "${api.server}/dossiers/${dossierId}/submitting",
							dataType : "json",
							type : "GET",
							headers: {
								"groupId": ${groupId},
								Accept : "application/json"
							},
							/*processData: false,
							contentType: 'text/plain',
							data : $("textarea#applicantNote").val(),*/
							data : {
								applicantNote : $("textarea#applicantNote").val()
							},
							success : function(result){
								$("#btn-submit-dossier").hide();
								$("#btn-back-dossier").prop("disabled","");

								notification.show({
									message: "Yêu cầu được thực hiện thành công!"
								}, "success");

								try {

									firstLoadDataSource = true;
									manageDossier.navigate("/"+statusRouteTem.status); 
									$('html,body').scrollTop(0);
								}catch(e){

								}

								$("#lsDossierTemplPart").getKendoListView().refresh();

								printDetailDossier(${dossierId});
							},
							error : function(result){
								$("#btn-back-dossier").prop("disabled","");
								notification.show({
									message: "Thực hiện không thành công, xin vui lòng thử lại!"
								}, "error");
							}
						});
					},
					error : function(result){

					}

				});
			}

		}, function(){

		});

	cf.open();
	
});

var fnBack = function(){
	window.history.back();
};

$("#dossier-payment-online").click(function(){
		var referenceUid = $(this).attr("data-pk");
		if(referenceUid){
			$.ajax({
				url : "${api.server}/dossiers/${dossierId}/payments/"+referenceUid+"/epaymentprofile",
				dataType : "json",
				type : "GET",
				headers : {"groupId": ${groupId}},
				async : false,
				data : {
				},
				success : function(result){
					window.location.href = result.keypayUrl;
				},
				error :  function(result){
					
				}

			});
		}
	});

	
$("#dossier-payment-confirm").click(function(){
	var referenceUid = $(this).attr("data-pk");
	if(referenceUid){

		var data = new FormData();

		data.append( 'file', $("#filePayment")[0].files[0]);
		data.append( 'confirmNote', $("textarea#confirmNote").val());
		data.append( 'paymentMethod', "Chuyển khoản");
		data.append( 'confirmPayload', null);
		$.ajax({
			url : "${api.server}/dossiers/${dossierId}/payments/"+referenceUid+"/confirm",
			dataType : "json",
			type : "PUT",
			headers : {"groupId": ${groupId}},
			processData: false,
			contentType: false,
			cache: false,
			data : data,
			success : function(result){
				notification.show({
					message: "Yêu cầu được thực hiện thành công"
				}, "success");
				$("#dossier-payment-confirm").prop("disabled",true);
			},
			error :  function(result){
				notification.show({
					message: "Thực hiện không thành công, xin vui lòng thử lại"
				}, "error");
			}

		});
	}
});

$("#filePayment").change(function(event){
	event.preventDefault();
	try{
		var fileName = $("#filePayment")[0].files[0].name;
		if(fileName){
			$("#fileNamePayment").html(fileName);
		}else {
			$("#fileNamePayment").html("");
		}
		
	}catch(e){
		$("#fileNamePayment").html("");
	}

});

$("#dossier-payment-viewpdf").click(function(){
	var referenceUid = $(this).attr("data-pk");
	if(referenceUid){
		$.ajax({
			url : "${api.server}/dossiers/${dossierId}/payments/"+referenceUid+"/invoicefile",
			dataType : "json",
			type : "GET",
			headers : {"groupId": ${groupId}},
			responseType: 'blob',
			data : {

			},
			success : function(result){
				var urlblob = window.URL.createObjectURL(response);
				window.open(urlblob, '_blank');
			},
			error :  function(result){

			}

		});
	}
});

function fileAttachmentUrl ( options) {

  // Use XMLHttpRequest instead of Jquery $ajax
  var xhttp = new XMLHttpRequest();
  var a,filename;
  var data = {};
  
  xhttp.onreadystatechange = function() {
  	
  	if (xhttp.readyState === 4 && xhttp.status === 200) {
  		
      // check for a filename
      var disposition = xhttp.getResponseHeader('Content-Disposition');
      if (disposition && disposition.indexOf('attachment') !== -1) {
      	var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
      	var matches = filenameRegex.exec(disposition);
      	if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');
      }
      
      // Trick for making downloadable link
      a = document.createElement('a');
      a.href = window.URL.createObjectURL(xhttp.response);

      var url = window.URL.createObjectURL(xhttp.response);
      
      //callback success
      options.success({url : url, status : xhttp.status});
  } else if (xhttp.readyState === 4 && xhttp.status !== 200) {
  	options.error(xhttp.status);
  }
  
};

  // Post data to URL which handles post request
  xhttp.open(options.method, options.url);
  xhttp.setRequestHeader("Content-Type", "application/json");
  
  // others data header
  if (options.hasOwnProperty("headers")){
  	Object.keys( options.headers ).map(function(objectKey, index) {
  		var value = options.headers[objectKey];
  		xhttp.setRequestHeader(objectKey, value);
  	});
  }

  // You should set responseType as blob for binary responses
  if (options.hasOwnProperty("responseType")){
  	xhttp.responseType = options.responseType;
  } else {
  	xhttp.responseType = 'blob';
  }
  
  // Data to post
  if (options.hasOwnProperty("data")){
  	data = options.data;
  }
  
  // excecute request
  xhttp.send(data);
  
};

function openFileNewtab(url){
	var urlOut = "";
	var urlReadFile = fileAttachmentUrl({
		method : "GET",
		url : url,
		async : false,
		success: function(options){
			urlOut = options.url;
			window.open(urlOut,"_blank");
		},
		error: function(){}
	});
}
</script>

<style type="text/css" media="screen">

	.table_history_style tr:nth-child(odd) td:first-child {
		background-color: #E9F7F8;
	}

	.table_history_style tr:nth-child(even) td:first-child {
		background-color: #D9E7E8;
	}
</style>
