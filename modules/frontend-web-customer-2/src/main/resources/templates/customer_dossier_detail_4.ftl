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

				<#if sendReissue?has_content >
				
				<a class="" id="btn-sendReissue-dossier-header" onclick="fnCorrecting(${(dossierId)!});" data-bind="value : submitting"><i class="fa fa-paper-plane"></i> Yêu cầu cấp lại</a>

				<#elseif sendAdd?has_content >

				<a class="" id="btn-sendadd-dossier-header" onclick="fnSubmitting(${(dossierId)!});" data-bind="value : submitting"><i class="fa fa-paper-plane"></i> Gửi bổ sung</a>

				<#else>

				</#if>
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
					<span class="text-bold">Mã tiếp nhận</span>: <span data-bind="text:dossierNo"></span>
					
				</div>
				<div class="row" id="">
					<span class="text-bold">Mã hồ sơ</span>: <span data-bind="text : dossierIdCTN"></span>
				</div>
			</div>
			
			<div class="col-sm-4">
				<div class="row MB5" id="">
					<span class="text-bold">Thời gian gửi</span>: <span data-bind="text : submitDate"></span>
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
					<div class="head-part slide-toggle">
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
					<div class="head-part align-middle slide-toggle">
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
					<div class="content-part collapse" id="collapseDossierI">
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
						<div class="head-part align-middle MB5 slide-toggle">
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
							<div class="row-parts-head align-middle slide-toggle">
								<span class="text-bold MR5">#:itemIndex#.</span>
								<span class="hover-pointer">
									#:partName# 
									#if(required){#
									<span class="red">*</span>
									#}#
								</span>

								<div class="actions">
									<a href="javascript:;" class="text-light-blue uploadfile-form-repository" data-toggle="tooltip" data-placement="top" title="Tải giấy tờ từ kho lưu trữ" part-no="#:id#>
										<i class="fa fa-archive" aria-hidden="true"></i>
									</a>
									
									#if("${(dossier.dossierStatus)!}" === "new" || "${(dossier.dossierStatus)!}" === "waiting" || "${(dossier.dossierStatus)!}" === ""){#
									<label class="MB0 ML10 hover-pointer" for="file#:id#" title="Tải file lên" >
										<i class="fa fa-upload text-light-blue"></i>
									</label>
									#}#

									<input type='file' id="file#:id#" name="file#:id#" class="hidden dossier-file" #if(multiple){# multiple #}# part-no="#:id#" file-template-no="#:fileTemplateNo#">

									<#-- <a href="javascript:;" class="dossier-component-profile" data-toggle="tooltip" data-placement="top" title="Số tệp tin" data-partno="#:id#"  data-number="#if(hasForm){# 1 #}else {# 0 #}#">
										<span class="number-in-circle" >#if(hasForm){# 1 #}else {# 0 #}#</span>
									</a> -->
									<a href="\\#/${(dossierId)!}/files/${(dossier.dossierTemplateNo)!}/#:id#" target="_blank" class="dossier-component-profile" data-placement="top" title="Số tệp tin" data-partno="#:id#" data-number="#if(hasForm){# 1 #}else {# 0 #}#">
										<span class="number-in-circle" >#if(hasForm){# 1 #}else {# 0 #}#</span>
									</a>

									<a href="javascript:;" class="text-light-gray delete-dossier-file" data-toggle="tooltip" data-placement="top" title="Xóa" data-partno="#:id#" fileTemplateNo="#:fileTemplateNo#" eForm="#:hasForm#">
										<i class="fa fa-trash-o" aria-hidden="true"></i> Xóa
									</a>
								</div>
							</div>

							#if(hasForm){
							var dossierFile =  getReferentUidFile(${dossierId},id);
							console.log(dossierFile);
							var hiddenState = "pointer-events:none;";
							if("${(dossier.dossierStatus)!}" === "new" || "${(dossier.dossierStatus)!}" === "waiting" || "${(dossier.dossierStatus)!}" === ""){
								hiddenState = "";
							}

							#

							<div class="collapse" id="collapseDossierPart#:id#">
								
								#if("${(dossier.dossierStatus)!}" === "new" || "${(dossier.dossierStatus)!}" === "waiting" || "${(dossier.dossierStatus)!}" === ""){#
								<div class="col-xs-12 col-sm-12 text-right">
									<button id="btn-save-formalpaca#:id#" class="btn btn-active MB10 MT10 MR20 saveForm saveFormAlpaca" 
									type="button" data-pk="#:id#" referenceUid="#:dossierFile.referenceUid#">Ghi lại</button>
									<input type="hidden" name="" id="dossierFileId#:id#" value="#:dossierFile.dossierFileId#">
								</div>
								#}#
								

								<div class="col-sm-12" #if(dossierFile.referenceUid){# style="height:450px; width:100%;overflow:auto;" #}# >
									<div class="formAlpacaDN" id="formPartNo#:id#" style="#:hiddenState#" data-pk="#:id#" data-partname="#:partName#">
										
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
							if(formdata){
							$("\\#validPart"+id).val("1");
						}
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

	<div class="head-part align-middle MB5 slide-toggle">

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
								<button class="btn btn-sm btn-border-color MR10 text-light-blue" id="dossier-payment-online" data-bind="attr : {data-pk : referenceUid}">Thanh toán trực tuyến</button> 
								<button class="btn btn-sm btn-border-color MR10 text-light-blue" data-bind="attr : {data-pk : referenceUid}" id="dossier-payment-confirm">Thông báo đã nộp chuyển khoản</button>
								<button class="btn btn-sm btn-border-color text-light-blue" onclick="">Xem phiếu thanh toán</button>
							</div>
						</div>

						<div class="row MB20 MT20" data-bind="value: isPay">
							<div class="col-sm-12 text-center">
								<div class="row">
									<div class="col-sm-4">

									</div>
									<div class="col-sm-4 text-center MB10">
										<i class="fa fa-file-image-o text-center text-light-gray MB10" aria-hidden="true" style="font-size:100px;">

										</i> <br>
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

	<div class="head-part align-middle slide-toggle">

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
							<a href="${api.server}/dossiers/${(dossierId)!}/files/#:referenceUid#" class="download-file-result" data-pk="#:referenceUid#">
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

	<div class="head-part align-middle slide-toggle">
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

				#if ( stepName!="" && stepName!=null ) {#

				<span class="text-light-blue">(#:stepName#)</span> 
				#}#

				<p>
					#if ( createDate!="" && createDate!=null ) {#
					#= kendo.toString(kendo.parseDate(createDate, "yyyy-MM-ddTHH:mm:ss"), "HH:mm - dd/MM/yyyy")#
					#}#
				</p>

				#if ( content!="" && content!=null ) {#
				<p>Ý kiến: #:content#</p>
				#}#

				#
				if(dossier){
				for(var i = 0 ; i < dossier.length ; i++){
				#
				<p>
					<a target="_blank" href="${api.server}/dossiers/${dossierId}/files/#:dossier[i].dossierFileId#" class="text-greyy text-hover-blue">
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
	<#if sendReissue?has_content >
	<div class="row MB20">
		<div class="col-sm-12">
			<label>${lblApplicantNote}</label>
			<textarea class="form-control" name="applicantNote" id="applicantNote" placeholder="Ghi chú" data-bind="text : applicantNote" rows="3"></textarea>
		</div>
	</div>

	<#elseif sendAdd?has_content >
	<div class="row MB20">
		<div class="col-sm-12">
			<label>${lblApplicantNote}</label>
			<textarea class="form-control" name="applicantNote" id="applicantNote" placeholder="Ghi chú" data-bind="text : applicantNote" rows="3"></textarea>
		</div>
	</div>

	<#elseif dossier.dossierStatus?has_content && dossier.dossierStatus == "waiting" && dossier.submitting?has_content && dossier.submitting != true>
	<div class="row MB20">
		<div class="col-sm-12">
			<label>Yêu cầu gửi bổ sung</label>
			<textarea class="form-control" name="applicantNote" id="applicantNote" placeholder="Ghi chú" data-bind="text : applicantNote" rows="3"></textarea>
		</div>
	</div>
	<#else>

	</#if>
</div>

<div id="uploadFileTemplateDialog" class="modal fade" role="dialog">
	
</div>

<#-- <div id="profileDetail" class="modal fade" role="dialog">

</div> -->

</div>
<div class="button-row MT20">
	<button class="btn btn-active" id="btn-back-dossier" type="button"><i class="fa fa-reply" aria-hidden="true"></i> Quay lại</button>
	<#if sendReissue?has_content >
	
	<button class="btn btn-active" id="btn-sendReissue-dossier" data-bind="value : submitting" style="display:none"><i class="fa fa-paper-plane"></i> Xác nhận</button>
	<a class="btn btn-active" id="btn-sendReissue-dossier-footer" onclick="fnCorrecting(${(dossierId)!});" data-bind="value : submitting"><i class="fa fa-paper-plane"></i> Yêu cầu cấp lại</a>

	<#elseif sendAdd?has_content >
	
	<button class="btn btn-active" id="btn-sendadd-dosier" data-bind="value : submitting" style="display:none"><i class="fa fa-paper-plane"></i> Xác nhận</button>
	<a class="btn btn-active" id="btn-sendadd-dossier-footer" onclick="fnSubmitting(${(dossierId)!});" data-bind="value : submitting"><i class="fa fa-paper-plane"></i> Gửi bổ sung</a>

	<#elseif dossier.dossierStatus?has_content && dossier.dossierStatus == "waiting" &&            	dossier.submitting?has_content && dossier.submitting != true>

	<button class="btn btn-active" id="btn-submit-dossier" ><i class="fa fa-paper-plane"></i> Nộp hồ sơ</button>

	</#if>
</div>
</div>

<script type="text/javascript">
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

	$(function(){
		$( "body" ).data( "dossierFiles", [] );

		$(document).off("change",".dossier-file");
		$(document).on("change",".dossier-file",function(){

			var partNo = $(this).attr("part-no");
			var fileTemplateNo = $(this).attr("file-template-no");
			var dossierTemplateNo = $("#dossierTemplateNo").val();
			var hasform = $(this).attr("hasform");

			kendo.ui.progress($("#mainType2"), true);

			funUploadFile($(this),partNo,dossierTemplateNo+"",fileTemplateNo,hasform);
		});

		$(document).off("click",".uploadfile-form-repository");
		$(document).on("click",".uploadfile-form-repository",function(){
			var dossierId = "${(dossierId)!}";
			var dossierTemplateNo = $("#dossierTemplateNo").val();
			var partNo = $(this).attr("part-no");
			$("#uploadFileTemplateDialog").load("${ajax.customer_dossier_detail_filetemplate}&${portletNamespace}dossierPartNo="+partNo+"&${portletNamespace}dossierId="+dossierId+"&${portletNamespace}dossierTemplateNo="+dossierTemplateNo,function(result){
				$(this).modal("show");
			});
		});

		// $(document).off("click",".dossier-component-profile");
		// $(document).on("click",".dossier-component-profile",function(){
		// 	var partNo = $(this).attr("data-partno");
		// 	var dossierId = "${(dossierId)!}";
		// 	var dossierTemplateNo = $("#dossierTemplateNo").val();
		// 	$("#profileDetail").load("${ajax.customer_dossier_component_profiles}&${portletNamespace}dossierPartNo="+partNo+"&${portletNamespace}dossierId="+dossierId+"&${portletNamespace}dossierTemplateNo="+dossierTemplateNo,function(result){

		// 	});

		// });

		$(document).off("click",".delete-dossier-file");
		$(document).on("click",".delete-dossier-file",function(){
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
										notification.show({
											message: "Yêu cầu được thực hiện thành công"
										}, "success");
									}else {
										notification.show({
											message: "Xẩy ra lỗi, vui lòng thử lại"
										}, "error");
									}

								}
							},
							error : function(result) {
								notification.show({
									message: "Xẩy ra lỗi, vui lòng thử lại"
								}, "error");
							}
						});

					}else {
						removeDossierFileNotEform(dossierId,fileTemplateNo,dataPartNo);
					}
				}
				
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

		var indexDossiserPart =0 ;
		$("#lsDossierTemplPart").kendoListView({
			dataSource : dataSourceDossierTemplate,
			autoBind : false,
			change : function(){

			},
			template : function(data){

				indexDossiserPart ++;

				data.itemIndex = indexDossiserPart;

				return kendo.template($("#templateDossierPart").html())(data);

			},
			dataBound : function(){
				indexDossiserPart = 0;

			//kiem tra dossier status, neu status thuoc new thi cho phep upoad hoac sua file
			fnCheckStatusAndHideUpload("${(dossier.dossierStatus)!}");

			//gen number file cho icon thanh phan ho so
			
			var	arrFile = funDossierFile("${dossierId}");
			funGenNumberFile(arrFile);
		}
	});

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
										return "Chờ nộp";
									}else if(this.get('paymentDossier').paymentStatus === 1){
										return "Báo đã nộp";
									}else if(this.get('paymentDossier').paymentStatus === 2){
										return "Hoàn thành";
									}else {
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
							isPay : function(){
								if(this.get('paymentDossier')){
									if(this.get('paymentDossier').paymentStatus !== 2){
										$("#unpaid").show();
										$("#alreadyPaid").hide();
									}
								}
							},
							submitting : function(){
								if(result.submitting){
									$("#btn-submit-dossier").hide();
								}
							}

						/*dossierTemplateNo : function(e){
							dataSourceDossierTemplate.read({
								dossierPart : 201 //result.dossierTemplateNo
							});	
						}*/

					});

kendo.bind($("#detailDossier"), viewModel);
},
error : function(result){
	$("#paymentDossier").html("");
}

});
}
}

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

			

			console.log("fileLength=======",fileLength);

			var currentFileNumber = $(".dossier-component-profile").filter("[data-partno="+partNo+"]").attr("data-number");

			console.log("currentFileNumber=======",currentFileNumber);

			var totalFile = fileLength + parseInt(currentFileNumber, 0);

			console.log("totalFile=======",totalFile);

			$(".dossier-component-profile").filter("[data-partno="+partNo+"]").html('<span class="number-in-circle" >'+totalFile+'</span>');

			console.log("dossier-component-profile=======",$(".dossier-component-profile").filter("[data-partno="+partNo+"]"));


			$(".dossier-component-profile").filter("[data-partno="+partNo+"]").attr("data-number",totalFile);

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

var fnCheckStatusAndHideUpload = function(dossierStatus){
	if(dossierStatus !== "" && dossierStatus !== "new" && dossierStatus !== "waiting"){
		$(".uploadfile-form-repository").remove();
		$(".lbl-dossier-flie").remove();
		$(".delete-dossier-file").remove();
	}
}

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
		},
		error : function(result) {
			notification.show({
				message: "Xảy ra lỗi, xin vui lòng thử lại"
			}, "error");
		}
	});
}

var funDossierFile = function(dossierId){
	var arrFile = new Array();
	if(dossierId){
		$.ajax({
			url : "${api.server}/dossiers/"+dossierId+"/files",
			dataType : "json",
			type : "GET",
			headers : {"groupId": ${groupId}},
			async : false,
			success : function(result){
				if(result.data){
					arrFile = result.data;
				}else {
					arrFile = [];
				}

			},
			error : function(result){

			}
		});
	}
	$( "body" ).data( "dossierFiles", arrFile );
	return arrFile;
}


var funGenNumberFile = function(arrCount){

	$(".dossier-component-profile").each(function(index){
		var partNo = $(this).attr("data-partno");
		var found = $.grep(arrCount, function(v) {
			return v.dossierPartNo === partNo;
		});

		$(this).attr("data-number",found.length);
		$(this).html('<span class="number-in-circle" >'+found.length+'</span>');
	});
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
	fnCorrecting(${(dossierId)!});
});

$("#btn-sendadd-dosier").click(function(){
	fnSubmitting(${(dossierId)!});
});


var fnCorrecting = function(dossierId){
	console.log("----------4" + "${(dossier.dossierStatus)!}");
	var applicantNote = $("textarea#applicantNote").val();
	if(applicantNote.trim() == ''){
		alert('Bạn phải nhập ý kiến trước khi gửi.');
		$("textarea#applicantNote").focus();
		return;
	}
	console.log("run sendReissue!");
	$.ajax({
		url : "${api.server}/dossiers/${dossierId}",
		dataType : "json",
		type : "PUT",
		headers: {
			"groupId": ${groupId},
			Accept : "application/json"
		},
		data : {
			applicantNote : $("textarea#applicantNote").val()
		},
		success : function(result){
			$.ajax({
				url : "${api.server}/dossiers/"+dossierId+"/correcting",
				dataType : "json",
				type : "GET",
				headers: {
					"groupId": ${groupId},
					Accept : "application/json"
				},
				data : {

				},
				success : function(result){
					notification.show({
						message: "Yêu cầu được thực hiện thành công!"
					}, "success");
					$("#btn-sendReissue-dossier-header").hide();
					$("#btn-sendReissue-dossier-footer").hide();

				},
				error : function(result){
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

$("#btn-back-dossier").click(function(){
	try{
		var isChange = fnCheckIsChangeForm();
		console.log("isChange");
		if(isChange){
			var cf = confirm("Bạn vừa thay đổi dữ liệu form bạn có muốn lưu lại!");
			if(cf){
				$(".saveFormAlpaca[data-pk="+isChange.partNo+"]").trigger("click");
			}else {

				$.each(arrIsChangeForm,function(index,value){
					if(value.partNo === isChange.partNo){
						arrIsChangeForm.splice(index, 1);
						fnBack();
						return ;
					}
				});

			}
			return ;
		}else {
			fnBack();
		}
	}catch(e){

	}
});


var fnSubmitting = function(dossierId){
	console.log("----------4" + "${(dossier.dossierStatus)!}");
	console.log("run senadd!");
	var applicantNote = $("textarea#applicantNote").val();
	if(applicantNote.trim() == ''){
		alert('Bạn phải nhập ý kiến trước khi gửi.');
		$("textarea#applicantNote").focus();
		return;
	}

	//------------------------------------------
	var isNext = false;
	var isChange = fnCheckIsChangeForm();
	if(isChange){
		var cf = confirm("Bạn vừa thay đổi dữ liệu form bạn có muốn lưu lại!");
		if(cf){
			$(".saveFormAlpaca[data-pk="+isChange.partNo+"]").trigger("click");
		}else {

			$.each(arrIsChangeForm,function(index,value){
				if(value.partNo === isChange.partNo){
					arrIsChangeForm.splice(index, 1);
					fnBack();
					return ;
				}
			});
			isNext = true ;
		}
		
	}else {
		isNext = true ;
	}
	
	if(isNext){
		$.ajax({
			url : "${api.server}/dossiers/${dossierId}",
			dataType : "json",
			type : "PUT",
			headers: {
				"groupId": ${groupId},
				Accept : "application/json"
			},
			data : {
				applicantNote : $("textarea#applicantNote").val()
			},
			success : function(result){
				$.ajax({
					url : "${api.server}/dossiers/"+dossierId+"/submitting",
					dataType : "json",
					type : "GET",
					headers: {
						"groupId": ${groupId},
						Accept : "application/json"
					},
					data : {

					},
					success : function(result){
						notification.show({
							message: "Yêu cầu được thực hiện thành công!"
						}, "success");
						$("#btn-sendadd-dossier-header").hide();
						$("#btn-sendadd-dossier-footer").hide();

					},
					error : function(result){
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

}

$("#btn-submit-dossier").click(function(){
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
				"groupId": ${groupId},
				Accept : "application/json"
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
					data : {

					},
					success : function(result){
						$("#btn-submit-dossier").hide();
						$("#btn-back-dossier").prop("disabled","");
						notification.show({
							message: "Yêu cầu được thực hiện thành công!"
						}, "success");

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
	
});

var fnBack = function(){
	window.history.back();
};


$(document).off("keyup",".formAlpacaDN input,select");
$(document).off("change",".formAlpacaDN input,select");
$(document).on({
	keyup: function () {
		var partNo = $(this).parents(".formAlpacaDN").attr("data-pk");
		var partName = $(this).parents(".formAlpacaDN").attr("data-partname");
		console.log("keyup form partno ====",partNo);
		if(partNo){
			if(arrIsChangeForm){
				for (var i = 0; i < arrIsChangeForm.length; i++) {
					if(arrIsChangeForm[i].partNo === partNo){
						arrIsChangeForm[i].isSave = false;
						arrIsChangeForm[i].partName = partName;
						return;
					}
				}
			}
			arrIsChangeForm.push({
				partNo : partNo,
				partName : partName,
				isSave : false
			});
		}
		jQuery.data( document.body, "arrIsChangeForm", arrIsChangeForm );
	},
	change: function () {
		var partNo = $(this).parents(".formAlpacaDN").attr("data-pk");
		var partName = $(this).parents(".formAlpacaDN").attr("data-partname");
		console.log("change form partno ====",partNo);
		if(partNo){
			if(arrIsChangeForm){
				for (var i = 0; i < arrIsChangeForm.length; i++) {
					if(arrIsChangeForm[i].partNo === partNo){
						arrIsChangeForm[i].isSave = false;
						arrIsChangeForm[i].partName = partName;
						return;
					}
				}
			}
			arrIsChangeForm.push({
				partNo : partNo,
				partName : partName,
				isSave : false
			});
		}
		jQuery.data( document.body, "arrIsChangeForm", arrIsChangeForm );
	}
}, '.formAlpacaDN input,select');

window.onhashchange = function(event) {
	if($(".saveFormAlpaca").length > 0){
		try{
			var isChange = fnCheckIsChangeForm();
			console.log("isChange");
			if(isChange){
				var cf = confirm("Bạn vừa thay đổi dữ liệu trong "+isChange.partName+" bạn có muốn lưu lại!");
				if(cf){
					$(".saveFormAlpaca[data-pk="+isChange.partNo+"]").trigger("click");
					event.preventDefault();
				}else {

					$.each(arrIsChangeForm,function(index,value){
						if(value.partNo === isChange.partNo){
							arrIsChangeForm.splice(index, 1);
							return ;
						}
					});

				}
				return ;
			}else {
				return ;
			}
		}catch(e){

		}
	}
	
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
