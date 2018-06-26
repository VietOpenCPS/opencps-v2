<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="steps align-space-between">
  <div class="step align-middle-lg done">
    <span>1</span>
    <span>Lựa chọn Dịch vụ công</span>
  </div>
  <div class="step align-middle-lg done" id="step2">
    <span>2</span>
    <span>Chuẩn bị hồ sơ</span>
  </div>
  <div class="step align-middle-lg" id="step3">
    <span>3</span>
    <span>Nộp hồ sơ</span>
  </div>
</div>
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
		<div class="col-sm-12">
			Cơ quan thực hiện <span class="text-bold" data-bind="text:govAgencyName"></span>
		</div>
	</div>

	<div class="guide-section PB0">
		<div class="head-part" data-toggle="collapse" data-target="#guideDossier">
			<div class="background-triangle-small"><i class="fa fa-star"></i></div> <span class="text-uppercase">Hướng dẫn</span> <span class="text-light-gray"></span>
		</div>

		<div class="content-part toggle-hide collapse" id="guideDossier">
			<div style="height: 100px;overflow: hidden;" id="guideDossierContent">
				<span data-bind="html:dossierNote" id="textDossierNote"></span>
			</div>
			<p class="MB0 text-light-blue PB10"><a href="javascript:;" id="guide-toggle" state="normal">Xem thêm >></a></p>
		</div>
		
	</div>

	<div class="row">
		<div class="col-sm-12">
			<div class="dossier-parts">
				<div class="head-part align-middle" data-toggle="collapse" data-target="#collapseDossierI">
					<div class="background-triangle-small">I</div> 
					<div class="col-sm-12 PL0">
						
						<span class="text-uppercase hover-pointer">Thông tin chủ hồ sơ</span>
						<i class="fa fa-angle-down pull-right hover-pointer" aria-hidden="true"></i>
					</div>
					
				</div>
				<div class="content-part" id="collapseDossierI" class="collapse">
					<div class="row-parts-head MT5">
						<div class="row MT5">
							
							<div class="col-sm-2 PT5 text-right">
								Họ và tên<span style="color: red;"> * </span>
							</div>
							<div class="col-sm-10">
								<div class="form-group"> 
									<input type="text" class="form-control" data-bind="value : applicantName" id="applicantName" name="applicantName" required="required" validationMessage="Bạn phải điền họ tên"> 
									<span data-for="applicantName" class="k-invalid-msg"></span>
								</div>
								
							</div>

							<div class="col-sm-2 PT5 text-right">
								Địa chỉ<span style="color: red;"> * </span>
							</div>
							<div class="col-sm-10">
								<div class="form-group"> 
									<input type="text" class="form-control" data-bind="value: address" id="address" name="address" required="required" validationMessage="Bạn phải điền địa chỉ"> 
								</div>
							</div>

							<div class="col-sm-2 PT5 text-right">
								Tỉnh/ Thành phố<span style="color: red;"> * </span>
							</div>
							<div class="col-sm-2 PR0">
								<div class="form-group"> 
									<select class="form-control" id="cityCode" name="cityCode" data-bind="value : cityCode" required="required" validationMessage="Bạn phải chọn Tỉnh/ Thành phố"> 
									</select>
									<span data-for="cityCode" class="k-invalid-msg"></span>
								</div>
							</div>

							<div class="col-sm-2 PT5 text-right">
								Quận/ Huyện<span style="color: red;"> * </span>
							</div>
							<div class="col-sm-2 PR0">
								<div class="form-group"> 
									<select class="form-control" id="districtCode" name="districtCode" data-bind="value : districtCode" required="required" validationMessage="Bạn phải chọn Quận/ Huyện"> 
									</select>
									<span data-for="districtCode" class="k-invalid-msg"></span>
								</div>
							</div>
							
							<div class="col-sm-2 PT5 text-right">
								Xã/ Phường<span style="color: red;"> * </span>
							</div>
							<div class="col-sm-2 PL0">
								<div class="form-group"> 
									<select class="form-control" id="wardCode" name="wardCode" data-bind="value : wardCode" required="required" validationMessage="Bạn phải chọn Xã/ Phường"> 
									</select>
									<span data-for="wardCode" class="k-invalid-msg"></span>
								</div>
							</div>

							<div class="col-sm-2 PT5 text-right">
								Điện thoại<span style="color: red;"> * </span>
							</div>
							<div class="col-sm-2 PR0">
								<div class="form-group"> 
									<input type="text" class="form-control" id="contactTelNo" name="contactTelNo" data-bind="value : contactTelNo" required="required" validationMessage="Bạn phải điền số điện thoại">
									<span data-for="contactTelNo" class="k-invalid-msg"></span> 
								</div>
							</div>
							
							<div class="col-sm-2 PT5 text-right">
								Địa chỉ email
							</div>
							<div class="col-sm-6">
								<div class="form-group"> 
									<input type="text" class="form-control" id="contactEmail" name="contactEmail" data-bind="value : contactEmail" required="required" validationMessage="Bạn phải điền email" readonly>
									<span data-for="contactEmail" class="k-invalid-msg"></span> 
								</div>
							</div>

							<input type="hidden" name="serviceCode" id="serviceCode" data-bind="value:serviceCode">
							<input type="hidden" name="govAgencyCode_" id="govAgencyCode_" data-bind="value:govAgencyCode">
							<input type="hidden" name="dossierTemplateNo" id="dossierTemplateNo" data-bind="value:dossierTemplateNo">

						</div>
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
				#if(partType == 1){#
				<div class="row-parts-head align-middle">
					<span class="text-bold MR5">#:itemIndex#.</span>
					<span>#:partName# 
						#
						if(required){
						#
						<span class="red">*</span>
						<input type="hidden" id="validPart#:id#" name="validPart#:id#" class="validPart" value="0">
						#}#
					</span>
					
					<div class="actions">
						<#-- <a href="javascript:;" class="text-light-blue uploadfile-form-repository" data-toggle="tooltip" data-placement="top" title="Tải giấy tờ từ kho lưu trữ" part-no="#:id#">
							<i class="fa fa-archive" aria-hidden="true"></i>
						</a> -->

						<label class="MB0 ML10 hover-pointer" for="file#:id#" title="Tải file lên" >
							<i class="fa fa-upload text-light-blue"></i>
						</label>
						
						<input type='file' id="file#:id#" name="file#:id#" class="hidden dossier-file" #if(multiple){# multiple #}# part-no="#:id#" file-template-no="#:fileTemplateNo#" eForm="#if(hasForm){# true #}#">
						
						<a href="javascript:;" class="dossier-component-profile" data-toggle="tooltip" data-placement="top" title="Số tệp tin" data-partno="#:id#" data-number="#if(hasForm){# 1 #}else {# 0 #}#">
							<span class="number-in-circle" >#if(hasForm){# 1 #}else {# 0 #}#</span>
						</a>

						<a href="javascript:;" class="text-light-gray delete-dossier-file" data-toggle="tooltip" data-placement="top" title="Xóa" data-partno="#:id#" fileTemplateNo="#:fileTemplateNo#" eForm="#if(hasForm){# true #}#">
							<i class="fa fa-trash-o" aria-hidden="true"></i> Xóa
						</a>
					</div>
				</div>

				#
				if(hasForm){
				var dossierFile =  getReferentUidFile(${dossierId},id);
				#

				<div class="row">
					<div class="col-xs-12 col-sm-12 text-right">
						<button id="btn-save-formalpaca#:id#" class="btn btn-active MB10 MT10 MR20 saveForm saveFormAlpaca" 
						type="button" data-pk="#:id#" referenceUid="#:dossierFile.referenceUid#" >Ghi lại</button>
						<input type="hidden" name="" id="dossierFileId#:id#" value="#:dossierFile.dossierFileId#">
					</div>
				</div>

				<div class="col-sm-12" #if(dossierFile.referenceUid){# style="height:450px;width:100%;overflow:auto;" #}#>
					<form id="formPartNo#:id#">

					</form>
				</div>

				#
				$.ajax({
				url : "${api.server}/dossiers/${dossierId}/files/"+dossierFile.referenceUid+"/formscript",
				dataType : "text",
				type : "GET",
				headers : {"groupId": ${groupId}},
				success : function(result){
				$("\\#formPartNo"+id).empty();

				try {
					var alpaca = eval("(" + result + ")");
					<#-- var alpaca = result; -->
					var formdata = fnGetFormData(${dossierId},dossierFile.referenceUid);
					
					<#-- if(formdata.dinh_danh){
						var dinh_danh = formdata.dinh_danh;
						if(dinh_danh.indexOf("Thành phố") !== -1){
							
							dinh_danh = dinh_danh.replace("Thành phố", "");
							formdata.dinh_danh = dinh_danh;
						}else if(dinh_danh.indexOf("Tỉnh") !== -1){
							
							dinh_danh = dinh_danh.replace("Tỉnh", "");
							formdata.dinh_danh = dinh_danh;
						}
					} -->
					
					alpaca.data = formdata; 

					setTimeout(function(){
						$("\\#formPartNo"+id).alpaca(alpaca);
					}, 1000);
				} catch (e) {
					
				}

		},
		error : function(result){

	}
});
}#

#}#
</script>
</div>
</div>

<div class="row-parts-content">
	<div class="row">
		<div class="col-sm-12">
			<label>Ghi chú</label>
			<input class="form-control" name="applicantNote" id="applicantNote" placeholder="Ghi chú">
		</div>
	</div>

	<div class="ML15 MT15">
		<input type="checkbox" id="viaPostal" name="viaPostal" data-bind="attr : {viaPostal : viaPostal}"> <label class="text-normal" for="viaPostal">Ông bà muốn sử dụng phương thức nhận kết quả hồ sơ qua đường bưu điện</label>
	</div>
	
	<div class="row MB20">
		<div class="col-xs-12 col-sm-7">
			<label>Địa chỉ nhận kết quả</label>
			<input type="text" class="form-control input-small" placeholder="Ghi rõ thôn, số nhà, tên đường - phố" id="postalAddress" name="postalAddress" required="required" validationMessage="Bạn phải nhập địa chỉ nhận kết quả" data-bind="value : postalAddress">
			<span data-for="postalAddress" class="k-invalid-msg"></span> 
		</div>
		<div class="col-xs-12 col-sm-2">
			<label>Tỉnh/Thành phố</label>
			<input class="form-control MB0" name="postalCityCode" id="postalCityCode" required="required" validationMessage="Bạn phải nhập Tỉnh/ Thành phố" data-bind="value : postalCityCode">
			<span data-for="postalCityCode" class="k-invalid-msg"></span> 
		</div>
		<div class="col-xs-12 col-sm-3">
			<label>Số điện thoại</label>
			<input type="text" class="form-control input-small" placeholder="" id="postalTelNo" name="postalTelNo" required="required" validationMessage="Bạn phải nhập số điện thoại" data-bind="value: postalTelNo">
			<span data-for="postalTelNo" class="k-invalid-msg"></span> 
		</div>
	</div>
</div>
</div>

<div class="button-row MT20">
	<button class="btn btn-active" id="btn-submit-dossier">Lưu <i class="fa fa-save"></i></button>
	<button class="btn btn-active" id="btn-next-step-dossier" type="button"><i class="fa fa-sign-in" aria-hidden="true"></i> Tiếp tục</button>
	<#-- <button class="btn btn-active" id="btn-submit-dossier"><i class="fa fa-paper-plane"></i> Nộp hồ sơ</button>
	<button class="btn btn-active"><i class="fa fa-trash"></i> Xóa</button> -->
	
</div>

<div id="uploadFileTemplateDialog" class="modal fade" role="dialog">
	
</div>


<div id="profileDetail" class="modal fade" role="dialog">
	
</div>

<script type="text/javascript">
	var funSaveDossier;
	$(function(){

		//upload file click
		$(document).off("change",".dossier-file");
		$(document).on("change",".dossier-file",function(){
			var partNo = $(this).attr("part-no");
			var fileTemplateNo = $(this).attr("file-template-no");
			var dossierTemplateNo = $("#dossierTemplateNo").val();
			var hasform = $(this).attr("eForm");
			funUploadFile($(this),partNo,dossierTemplateNo+"",fileTemplateNo,hasform);
			
		});
		
		//tai giay to kho luu tru
		$(document).off("click",".uploadfile-form-repository");
		$(document).on("click",".uploadfile-form-repository",function(){
			var dossierId = "${(dossierId)!}";
			var dossierTemplateNo = $("#dossierTemplateNo").val();
			var partNo = $(this).attr("part-no");
			$("#uploadFileTemplateDialog").load("${ajax.customer_dossier_detail_filetemplate}&${portletNamespace}dossierPartNo="+partNo+"&${portletNamespace}dossierId="+dossierId+"&${portletNamespace}dossierTemplateNo="+dossierTemplateNo,function(result){
				$(this).modal("show");
			});
		});

		//xem file tai len theo tp ho so
		$(document).off("click",".dossier-component-profile");
		$(document).on("click",".dossier-component-profile",function() {
			var partNo = $(this).attr("data-partno");
			var dossierId = "${(dossierId)!}";
			var dossierTemplateId = "${(dossierTemplateId)!}";
			$("#profileDetail").load("${ajax.customer_dossier_component_profiles}&${portletNamespace}dossierPartNo="+partNo+"&${portletNamespace}dossierId="+dossierId+"&${portletNamespace}dossierTemplateId="+dossierTemplateId,function(result){
				$(this).modal("show");
			});
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
										for (var i = 0; i < data.length; i++) {
											if(dataPartNo === data[i].dossierPartNo){
												removeDossierFile(dossierId, data[i].referenceUid);
											}
										}
										$(".dossier-component-profile").filter("[data-partno="+dataPartNo+"]").html('<span class="number-in-circle" >0</span>');

										$(".dossier-component-profile").filter("[data-partno="+dataPartNo+"]").attr("data-number",0);

										if(navigator.onLine){
											notification.show({
												message: "Yêu cầu được thực hiện thành công"
											}, "success");
										}

									}
									$("#validPart"+dataPartNo).val("0");
								},
								error : function(result) {
									if(navigator.onLine){
										showMessageByAPICode (result.status)
									}
									
								}
							});

						}else {
							removeDossierFileNotEform(dossierId,fileTemplateNo,dataPartNo);
						}
					}
				}
			}else {
				alert("Không có kết nối internet, vui lòng kiểm tra kết nối của bạn!");
			}

			
		});

	$("#btn-view-extendguide").click(function(){
		if($("#extend-guide").attr("status")==="none"){
			$("#extend-guide").show();
			$("#extend-guide").attr("status","show");
		}else{
			$("#extend-guide").hide();
			$("#extend-guide").attr("status","none");
		}
	});

	$(document).on("click",".dossier-online-form",function(event){
		$(this).unbind();
		$("#showDossierOnlineForm").load("${ajax.customer_dossier_online_form_dialog}",function(result){
			$(this).modal("show");
		});
	});

	(function($) { 
		$('.spinner .btn:first-of-type').on('click', function() { 
			$('.spinner input').val(parseInt($('.spinner input').val(), 10) + 1); 
		}); 
		$('.spinner .btn:last-of-type').on('click', function() { 
			$('.spinner input').val(parseInt($('.spinner input').val(), 10) - 1); 
		}); 
	})(jQuery);

	$("#dossier-submit-info").click(function(){
		$("#dossierSubmitInfo").load("${ajax.customer_dossier_info}",function(result){
			$(this).modal("show");
		});
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
							if (result && result.dossierParts) {
								options.success(result.dossierParts);
								$("#dossierTemplateNo").val(result.templateNo);
							} else {
								options.success({
									data: [],
									total: 0
								})
							}
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

			if(data.partType === 1){
				indexDossiserPart ++;
			}

			data.itemIndex = indexDossiserPart;

			return kendo.template($("#templateDossierPart").html())(data);

		},
		dataBound : function(){
			indexDossiserPart = 0;
		}
	});

	$("#showFileTemplateDialog").click(function(){
		$("#fileTemplateDialog").load("employeemain_dossierdetail_filetemplate.ftl",function(result){
			$(this).modal("show");
		});
	});

	$("#btn-submit-dossier").click(function(){
		funSaveDossier();
	});

	$("#btn-next-step-dossier").click(function(){
		funSaveDossier();
		
	});

	funSaveDossier = function(){
		//PUT dossier
		var validator = $("#detailDossier").kendoValidator().data("kendoValidator");
		var validateDossierTemplate = fnCheckValidTemplate();

		if(!validateDossierTemplate){
			notification.show({
				message: "Vui lòng kiểm tra lại các thông tin bắt buộc của các thành phần hồ sơ!"
			}, "error");

			return ;
		}
		if(validator.validate() && validateDossierTemplate ){
			$.ajax({
				url  : '${api.server}/dossiers/${dossierId}', 
				dataType : "json",
				type : 'PUT', 
				headers: {"groupId": ${groupId}},
				data : {
					referenceUid : "",
					serviceCode : $("#serviceCode").val(),
					govAgencyCode : $("#govAgencyCode_").val(),
					dossierTemplateNo : $("#dossierTemplateNo").val(),

					applicantName : "${(applicant.applicantName)!}",
					applicantIdType : "${(applicant.applicantIdType)!}",
					applicantIdNo : "${(applicant.applicantIdNo)!}",
					applicantIdDate : "01/01/2017 00:00:00",

					contactName : $("#applicantName").val(),
					address : $("#address").val(),
					cityCode : $("#cityCode").val(),
					districtCode : $("#districtCode").val(),
					wardCode : $("#wardCode").val(),
					contactTelNo : $("#contactTelNo").val(),
					contactEmail : $("#contactEmail").val(),
					viaPostal : $("#viaPostal").is(":checked") ? 2 : 1,
					applicantNote : $("#applicantNote").val(),
					postalTelNo: $("#postalTelNo").val(),
					postalCityCode: $("#postalCityCode").val(),
					postalAddress: $("#postalAddress").val(),

				},
				success :  function(result){                       
					if(result.dossierStatus == ''){
						createActionDossier(${dossierId});
					}
					
				},
				error:function(result){
					showMessageByAPICode (result.status)
				}	
			});
		}else {
			showMessageByAPICode (400)
			// notification.show({
			// 	message: "Vui lòng kiểm tra lại các thông tin bắt buộc trước khi lưu!"
			// }, "error");
		}

	}

	var createActionDossier = function(dossierId){
		if(dossierId){
			$.ajax({
				url : "${api.server}/dossiers/"+dossierId+"/actions",
				dataType : "json",
				type : "POST",
				headers: {"groupId": ${groupId}},
				data : {
					actionCode  : 1100,
					actionNote :  $("#applicantNote").val()
					/*actionUser : $("#actionUser").val(),
					
					*/
				},
				success : function(result){
					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");
					setTimeout(function(){ 
						manageDossier.navigate("/taohosomoi/nophoso/${dossierId}");
					}, 1000);
				},
				error : function(result){
					showMessageByAPICode (result.status);
					// notification.show({
					// 	message: "Xảy ra lỗi, xin vui lòng thử lại"
					// }, "error");
				}
			});
		}
	}

	$("#cityCode").kendoComboBox({
		placeholder : "Chọn tên thành phố",
		dataTextField : "itemName",
		dataValueField : "itemCode",
		noDataTemplate : "Không có dữ liệu",
		autoBind : true,
		dataSource : {
			transport : {
				read : function(options){
					$.ajax({
						url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
						dataType : "json",
						type : "GET",
						headers: {"groupId": ${groupId}},
						data : {
							parent : 0
						},
						success : function(result){
							if (result.data) {
								options.success(result);
							} else {
								options.success({
									data: [],
									total: 0
								})
							}
						},
						error : function(result){
							options.error(result);
						}
					});
				}
			},
			schema : {
				data : "data",
				total : "total"
			}
		},
		change : function(e){
			var value = this.value();
			if(value){
				$("#districtCode").data("kendoComboBox").dataSource.read({
					parent : value
				});
				$("#districtCode").data("kendoComboBox").select(-1);
				$("#wardCode").data("kendoComboBox").select(-1);
			}

		}

	});

	$("#districtCode").kendoComboBox({
		placeholder : "Chọn Quận/ Huyện",
		dataTextField : "itemName",
		dataValueField : "itemCode",
		noDataTemplate : "Không có dữ liệu",
		dataSource : {
			transport : {
				read : function(options){
					var parent = "${(applicant.cityCode)!}";
					if(options.data.parent){
						parent = options.data.parent;
					}
					if (parent && parent !== '-') {
						$.ajax({
							url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
							dataType : "json",
							type : "GET",
							headers: {"groupId": ${groupId}},
							data : {
								parent : parent
							},
							success : function(result){
								
								if (result.data) {
									options.success(result);
								} else {
									options.success({
										data: [],
										total: 0
									})
								}
							},
							error : function(result){
								options.error(result);
							}
						});
					} else {
						options.success({
							data: [],
							total: 0
						})
					}
				}
			},
			schema : {
				data : "data",
				total : "total"
			}
		},
		change : function(e){
			var value = this.value();
			if(value){
				$("#wardCode").data("kendoComboBox").dataSource.read({
					parent : value
				});
				$("#wardCode").data("kendoComboBox").select(-1);
			}

		}
	});

	$("#wardCode").kendoComboBox({
		placeholder : "Chọn Xã/ Phường",
		dataTextField : "itemName",
		dataValueField : "itemCode",
		noDataTemplate : "Không có dữ liệu",
		dataSource : {
			transport : {
				read : function(options){
					var parent = "${(applicant.districtCode)!}";
					if(options.data.parent){
						parent = options.data.parent;
					}
					if (parent && parent !== '-') {
						$.ajax({
							url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
							dataType : "json",
							type : "GET",
							headers: {"groupId": ${groupId}},
							data : {
								parent : parent
							},
							success : function(result){
								if (result.data) {
									options.success(result);
								} else {
									options.success({
										data: [],
										total: 0
									})
								}
								
							},
							error : function(result){
								options.error(result);
							}
						});
					} else {
						options.success({
							data: [],
							total: 0
						})
					}
				}
			},
			schema : {
				data : "data",
				total : "total"
			}
		}

	});

	$("#postalCityCode").kendoComboBox({
		placeholder : "Chọn Tỉnh/ Thành phố",
		dataTextField : "itemName",
		dataValueField : "itemCode",
		noDataTemplate : "Không có dữ liệu",
		autoBind : false,
		dataSource : {
			transport : {
				read : function(options){
					$.ajax({
						url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
						dataType : "json",
						type : "GET",
						headers: {"groupId": ${groupId}},
						data : {
							parent : 0
						},
						success : function(result){
							if (result.data) {
								options.success(result);
							} else {
								options.success({
									data: [],
									total: 0
								});
							}
						},
						error : function(result){
							options.error(result);
						}
					});
				}
			},
			schema : {
				data : "data",
				total : "total"
			}
		}

	});

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
							return "";
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

	printDetailDossier(${dossierId});

	var removeDossierFile = function(dossierId, fileId){
		$.ajax({
			url : "${api.server}/dossiers/"+dossierId+"/files/"+fileId,
			dataType : "json",
			type : "DELETE",
			headers : {"groupId": ${groupId}},
			success : function(result) {


			},
			error : function(result) {

			}
		});
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

				},
				error : function(result) {
					if(navigator.onLine){
						showMessageByAPICode (result.status)
					}
				}
			});
		}
		
	}

	/*function makeFileList() {
		var input = document.getElementById("file");
		var ul = document.getElementById("fileList");
		while (ul.hasChildNodes()) {
			ul.removeChild(ul.firstChild);
		}
		for (var i = 0; i < input.files.length; i++) {
			var li = document.createElement("li");
			li.innerHTML = input.files[i].name;
			ul.appendChild(li);
		}
		if(!ul.hasChildNodes()) {
			var li = document.createElement("li");
			li.innerHTML = 'No Files Selected';
			ul.appendChild(li);
		}
	}
	*/

	var funUploadFile = function(file, partNo , dossierTemplateNo , fileTemplateNo, hasForm){
		var data = new FormData();

		data.append( 'displayName', $(file)[0].files[0].name);
		data.append( 'file', $(file)[0].files[0]);
		data.append('dossierPartNo', partNo);
		data.append('referenceUid', "");
		data.append('dossierTemplateNo', dossierTemplateNo);
		data.append('fileTemplateNo', fileTemplateNo);
		data.append('formData', "");
		data.append('fileType', "");
		data.append('isSync', "true");

		$.ajax({
			type : 'POST', 
			url  : '${api.server}/dossiers/${dossierId}/files', 
			data : data,
			headers: {"groupId": ${groupId}},
			processData: false,
			contentType: false,
			cache: false,
			success :  function(result){ 
				var fileLength = $(file)[0].files.length;

				var currentFileNumber = $(".dossier-component-profile").filter("[data-partno="+partNo+"]").attr("data-number");

				var totalFile = fileLength + parseInt(currentFileNumber, 0);

				$(".dossier-component-profile").filter("[data-partno="+partNo+"]").html('<span class="number-in-circle" >'+totalFile+'</span>');

				$(".dossier-component-profile").filter("[data-partno="+partNo+"]").attr("data-number",totalFile);
				$("#uploadFileTemplateDialog").modal("hide");

				notification.show({
					message: "Yêu cầu được thực hiện thành công"
				}, "success");

				/*if(!hasForm){
					$("#validPart"+partNo).val("1");
				}*/

				$("#validPart"+partNo).val("1");


			},
			error:function(result){
				showMessageByAPICode (result.status)
			}
		});
	}

	$("#viaPostal").change(function(){
		if($(this).is(":checked")) {
			$("#postalAddress").prop('disabled', false);
			$("#postalCityCode").data("kendoComboBox").enable(true);
			$("#postalTelNo").prop('disabled', false);
		}else{
			$("#postalAddress").prop('disabled', true);
			$("#postalCityCode").data("kendoComboBox").enable(false);
			$("#postalTelNo").prop('disabled', true);
		}
	});

	$("#postalAddress").prop('disabled', true);
	$("#postalCityCode").data("kendoComboBox").enable(false);
	$("#postalTelNo").prop('disabled', true);
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
							if(dossierPartNo == result.data[i].dossierPartNo){
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

$(function(){
	manageDossier.route("/taohosomoi/nophoso/(:dossierId)", function(dossierId){
		$("#mainType1").hide();
		$("#mainType2").show();
		$("#mainType2").load("${ajax.customer_dossier_detail_2}&${portletNamespace}dossierId="+dossierId,function(result){
			
		});
	});
});

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

var fnCheckValidTemplate = function(){
	var valid = true;
	try {

		$(".validPart").each(function(index){
			if($(this).val() === "0"){
				valid = false;
			}
		});

	}catch(e){
		valid = false;
	}

	return valid;
}

window.onload = function(){
	if($("#textDossierNote").text().length < 550){
		$("#guide-toggle").remove();
	}
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
			},
			error : function(result){
				showMessageByAPICode (result.status)
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

				},
				error : function(result){
					showMessageByAPICode (result.status)
				}
			});
		}else {
			notification.show({
				message: "Vui lòng kiểm tra lại các thông tin bắt buộc trước khi ghi lại!"
			}, "error");
		}
	}
});

$("#guide-toggle").click(function(event){
	event.preventDefault();
	var state = $(this).attr("state");

	if(state === "normal"){
		$('#guideDossierContent').css('height', 'auto');
		$(this).attr("state","full");
		$(this).html("Thu gọn >>");
	}else {
		$('#guideDossierContent').css('height', '100px');
		$(this).attr("state","normal");
		$(this).html("Xem thêm >>");
	}

});

</script>