<#if (Request)??>
<#include "init.ftl">
</#if>

<div id="detailDossier">
	
	
	<div class="box" >
		<input type="hidden" name="dossierTemplateId" id="dossierTemplateId">
		<input type="hidden" name="dossierItemId" id="dossierItemId">
		<input type="hidden" name="dossierTemplateNo" id="dossierTemplateNo">
		<div class="row-header align-middle">
			<div class="background-triangle-big">Tên thủ tục</div> 
			<span class="text-bold" data-bind="text:serviceName"></span>
			<div class="pull-right group-icons">
				<a href="javascript:;" id="btn-submit-dossier-header">
					<i class="fa fa-paper-plane" aria-hidden="true"></i> 
					Nộp hồ sơ
				</a> 
				<a href="javascript:;" id="btn-delete-dossier-header">
					<i class="fa fa-trash"></i>
					Xóa
				</a>
			</div>
		</div>

		<div class="dossier-general-info P15 MB30">
			<div class="col-sm-12">
				<span class="text-bold">Cơ quan thực hiện</span>: 
				<span data-bind="text:govAgencyName"></span>
			</div>
			<div class="col-sm-5">
				<span class="text-bold">Trạng thái</span>: <i data-bind="text:dossierStatusText"></i>
			</div>
			<div class="col-sm-7">
				<span class="text-bold">Số hồ sơ</span>: <span data-bind="text : dossierIdCTN"></span>
			</div>
		</div>

		<div class="guide-section PB0">
			<div class="head-part"  data-toggle="collapse" data-target="#guideDossier">
				<div class="background-triangle-small"><i class="fa fa-star"></i></div> <span class="text-uppercase">Hướng dẫn</span> <span class="text-light-gray">(gồm các bước làm thủ tục)</span>
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
					<div class="head-part align-middle">
						<div class="background-triangle-small">I</div> <span class="text-uppercase">Thông tin chủ hồ sơ</span>
					</div>
					<div class="content-part">
						<div class="row-parts-head MT5">

							<div class="row MT5">
								<div class="col-sm-2">
									<label>Họ và tên</label>
								</div>
								<div class="col-sm-10">
									<span id="contactName" data-pk="1" data-type="text" data-toggle="#editContactName" data-original-title="Nhập họ và tên" tabindex="-1" class="" data-bind="text:contactName" required></span>
									<span class="pull-right">
										<a href="javascript:;" id="editContactName" style="float: right"><i class="fa fa-pencil"></i></a>
									</span>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-2">
									<label>Địa chỉ</label>
								</div>
								<div class="col-sm-10">
									<span id="address" data-pk="1" data-type="text" data-toggle="#editAddress" data-original-title="Nhập địa chỉ" tabindex="-1" class="" data-bind="text:address" required></span>
									<span class="pull-right">
										<a href="javascript:;" id="editAddress" style="float: right"><i class="fa fa-pencil"></i></a>
									</span>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-2">
									<label>Tỉnh/ Thành phố</label>
								</div>
								<div class="col-sm-10">
									<span id="city" data-pk="1" data-type="select" data-toggle="#editCity" data-original-title="Chọn tỉnh/ thành phố" tabindex="-1" class="" data-bind="text:cityName" required><#-- ${api.applicant.cityName} --></span>
									<span class="pull-right">
										<a href="javascript:;" id="editCity" style="float: right"><i class="fa fa-pencil"></i></a>
									</span>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-2">
									<label>Quận/ Huyện</label>
								</div>
								<div class="col-sm-10">
									<span id="district" data-pk="1" data-type="select" data-toggle="#editDistrict" data-original-title="Chọn quận/ huyện" tabindex="-1" class="" data-bind="text:districtName" required><#-- ${api.applicant.districtName} --></span>
									<span class="pull-right">
										<a href="javascript:;" id="editDistrict" style="float: right"><i class="fa fa-pencil"></i></a>
									</span>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-2">
									<label>Xã/ Phường</label>
								</div>
								<div class="col-sm-10">
									<span id="wards" data-pk="1" data-type="select" data-toggle="#editWards" data-original-title="Chọn xã/ phường" tabindex="-1" class="" data-bind="text:wardName" required><#-- ${api.applicant.wardName} --></span>
									<span class="pull-right">
										<a href="javascript:;" id="editWards" style="float: right"><i class="fa fa-pencil"></i></a>
									</span>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-2">
									<label>Điện thoại</label>
								</div>
								<div class="col-sm-10">
									<span id="contactTelNo" data-pk="1" data-type="text" data-toggle="#editContactTelNo" data-original-title="Nhập số điện thoại" tabindex="-1" class="" data-bind="text:contactTelNo" required></span>
									<span class="pull-right">
										<a href="javascript:;" id="editContactTelNo" style="float: right"><i class="fa fa-pencil"></i></a>
									</span>
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
							#if(required){#
							<span class="red">*</span>
							<input type="hidden" id="validPart#:id#" name="validPart#:id#" class="validPart" value="0">
							#}#
						</span>

						<div class="actions">

							<a href="javascript:;" class="text-light-blue uploadfile-form-repository" data-toggle="tooltip" data-placement="top" title="Tải giấy tờ từ kho lưu trữ" part-no="#:id#">
								<i class="fa fa-archive" aria-hidden="true"></i>
							</a>

							<label class="MB0 ML10 hover-pointer" for="file#:id#" title="Tải file lên" >
								<i class="fa fa-upload text-light-blue"></i>
							</label>

							<input type='file' id="file#:id#" name="file#:id#" class="hidden dossier-file" #if(multiple){# multiple #}# part-no="#:id#" file-template-no="#:fileTemplateNo#">


							<a href="javascript:;" class="dossier-component-profile" data-toggle="tooltip" data-placement="top" title="Số tệp tin" data-partno="#:id#" data-number="#if(hasForm){# 1 #}else {# 0 #}#">
								<span class="number-in-circle" >#if(hasForm){# 1 #}else {# 0 #}#</span>
							</a>

							<a href="javascript:;" class="text-light-gray delete-dossier-file" data-toggle="tooltip" data-placement="top" title="Xóa" data-partno="#:id#">
								<i class="fa fa-trash-o" aria-hidden="true"></i> Xóa
							</a>
						</div>
					</div>

					#if(hasForm){
					var dossierFile =  getReferentUidFile(${dossierId},id);
					#
					<div class="row">
						<div class="col-xs-12 col-sm-12 text-right">
							<button id="btn-save-formalpaca#:id#" class="btn btn-active MB10 MT10 MR20 saveForm saveFormAlpaca" 
							type="button" data-pk="#:id#" referenceUid="#:dossierFile.referenceUid#">Ghi lại</button>
							<input type="hidden" name="" id="dossierFileId#:id#" value="#:dossierFile.dossierFileId#">
						</div>
					</div>

					<div class="col-sm-12" #if(dossierFile.referenceUid){# style="height:450px; width:100%;overflow:auto;" #}#>
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
					var alpaca = eval("(" + result + ")");
					var formdata = fnGetFormData(${dossierId},dossierFile.referenceUid);
					if(formdata){
					$("\\#validPart"+id).val("1");
				}
				alpaca.data = formdata;

				$("\\#formPartNo"+id).alpaca(alpaca);

				<#-- $("\\#formPartNo"+id).append('<div class="row"><div class="col-xs-12 col-sm-12 "><button id="btn-save-formalpaca'+id+'" class="btn btn-active MB10 MT10 saveForm" type="button" data-pk="'+id+'" referentUid="'+referentUidFile+'">Ghi lại</button></div></div>'); -->

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

	<div class="checkbox ML15">
		<input type="checkbox" data-bind="attr : {viaPostal: viaPostal}" name="viaPostal" id="viaPostal"> <label class="text-normal">Ông bà muốn sử dụng phương thức nhận kết quả hồ sơ qua đường bưu điện</label>
	</div>

	<div class="row" id="viaPostalContent">

		<div class="col-xs-12 MB5">
			<div class="row MB5">
				<div class="col-xs-12 col-sm-2 ">
					<label>Địa chỉ nhận kết quả</label>
				</div>
				<div class="col-sm-10">
					<span id="postalAddress" data-pk="1" data-type="text" data-toggle="#editPostalAddress" data-original-title="Nhập địa chỉ nhận kết quả" tabindex="-1" class="" data-bind="text:postalAddress" required></span>
					<span class="pull-right">
						<a href="javascript:;" id="editPostalAddress" style="float: right"><i class="fa fa-pencil"></i></a>
					</span>
				</div>
			</div>
		</div>

		<div class="col-xs-12 MB5">
			<div class="row ">
				<div class="col-xs-12 col-sm-2 ">
					<label>Tỉnh/Thành phố</label>
				</div>
				<div class="col-sm-10">
					<span id="postalCityCode" data-pk="1" data-type="select" data-toggle="#editPostalCityCode" data-original-title="Chọn Tỉnh/Thành phố" tabindex="-1" class="" data-bind="text:postalCityName" required></span>
					<span class="pull-right">
						<a href="javascript:;" id="editPostalCityCode" style="float: right"><i class="fa fa-pencil"></i></a>
					</span>
				</div>
			</div>
		</div>


		<div class="col-xs-12 MB5">
			<div class="row MB5">
				<div class="col-xs-12 col-sm-2 ">
					<label>Số điện thoại</label>
				</div>
				<div class="col-sm-10">
					<span id="postalTelNo" data-pk="1" data-type="text" data-toggle="#editPostalTelNo" data-original-title="Nhập số điện thoại" tabindex="-1" class="" data-bind="text:postalTelNo" required></span>
					<span class="pull-right">
						<a href="javascript:;" id="editPostalTelNo" style="float: right"><i class="fa fa-pencil"></i></a>
					</span>
				</div>
			</div>
		</div>

	</div>

	<div class="row">

		<div class="col-sm-1">
			<label>Ghi chú</label>
		</div>
		<div class="col-sm-11">
			<span id="applicantNote" data-pk="1" data-type="textarea" data-toggle="#editApplicantNote" data-original-title="Ghi chú" tabindex="-1" class="" data-bind="text:applicantNote"></span>
			<span class="pull-right">
				<a href="javascript:;" id="editApplicantNote" style="float: right"><i class="fa fa-pencil"></i></a>
			</span>
		</div>

	</div>
</div>
</div>

<div class="button-row MT20">
	<#if resCancelling?has_content >

	<button class="btn btn-active" id="btn-submit-dossier" data-bind="value : submitting"><i class="fa fa-paper-plane"></i> Nộp hồ sơ</button>

	<#elseif sendAdd?has_content >

	<button class="btn btn-active" id="btn-submit-dossier" data-bind="value : submitting"><i class="fa fa-paper-plane"></i> Nộp hồ sơ</button>

	<#else>

	<button class="btn btn-active" id="btn-submit-dossier" data-bind="value : submitting"><i class="fa fa-paper-plane"></i> Nộp hồ sơ</button>
	
	</#if>

	
	<button class="btn btn-active" id="btn-delete-dossier" data-bind="attr : {data-pk : dossierId}"><i class="fa fa-trash"></i> Xóa</button>
</div>
</div>

<div id="uploadFileTemplateDialog" class="modal fade" role="dialog">
</div>


<div id="profileDetail" class="modal fade" role="dialog">
	
</div>


<script type="text/javascript">
	
	

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

		//upload file click
		$(document).off("change",".dossier-file");
		$(document).on("change",".dossier-file",function(){
			console.log("change");
			var partNo = $(this).attr("part-no");
			var fileTemplateNo = $(this).attr("file-template-no");
			var dossierTemplateNo = $("#dossierTemplateNo").val();
			console.log(partNo);
			console.log(fileTemplateNo);
			console.log($(this)[0].files[0]);

			funUploadFile($(this),partNo,dossierTemplateNo,fileTemplateNo);
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
		$(document).on("click",".delete-dossier-file",function(event){
			var dossierId  = ${dossierId};
			var dataPartNo = $(this).attr("data-partno");
			try{
				$("#formPartNo"+dataPartNo).alpaca('get').setValue({});
			}catch (e){
				
			}
			console.log(dossierId);
			console.log(dataPartNo);
			var cf = confirm("Bạn có muốn xóa file toàn bộ file của thành phần này!");
			if(cf){
				if(dossierId && dataPartNo){
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
									if(dataPartNo === data[i].dossierPartNo){
										var isSuccess = removeDossierFile(dossierId, data[i].referenceUid);
										arrIsSuccess.push(isSuccess);

									}
								}

								if(jQuery.inArray( false, arrIsSuccess ) == -1){
									$(".dossier-component-profile").filter("[data-partno="+dataPartNo+"]").html('<span class="number-in-circle" >0</span>');

									$(".dossier-component-profile").filter("[data-partno="+dataPartNo+"]").attr("data-number",0);
									notification.show({
										message: "Yêu cầu được thực hiện thành công"
									}, "success");

									$("#validPart"+dataPartNo).val("0");
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

				var arrFile = funDossierFile(${dossierId});
				funGenNumberFile(arrFile);
			}
		});

		$(document).on("click",".dossier-online-form",function(event){
			console.log("abcd");
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

		$("#showFileTemplateDialog").click(function(){
			$("#fileTemplateDialog").load("employeemain_dossierdetail_filetemplate.ftl",function(result){
				$(this).modal("show");
			});
		});

		$("#btn-submit-dossier,#btn-submit-dossier-header").click(function(){
			var cf = fnConfirm("Thông báo",
				"Bạn có chắc chắn muốn gửi hồ sơ trực tuyến", 
				"Nộp hồ sơ", "Hủy bỏ",
				function(){

					funSubmitDossier();

				}, function(){
					
				});

			cf.open();
			
		});

		var funSubmitDossier = function(){
			var validateAplicantInfo = $('#contactName, #city , #district , #wards , #contactTelNo' ).editable('validate');
			var validatePostal = true;

			if($("#viaPostal").is(":checked")){
				validatePostal = $('#address, #postalCityCode , #postalTelNo' ).editable('validate');
			}

			var validTemplate = fnCheckValidTemplate();

			if(!validTemplate){
				notification.show({
					message: "Vui lòng kiểm tra lại các thông tin bắt buộc của các thành phần hồ sơ!"
				}, "error");

				return ;
			}

			console.log(validatePostal);
			console.log(validateAplicantInfo);

			if( jQuery.isEmptyObject(validateAplicantInfo) && jQuery.isEmptyObject(validatePostal) ){
				$.ajax({
					type : 'GET', 
					url  : '${api.server}/dossiers/${dossierId}/submitting', 
					data : {

					},
					headers: {
						"groupId": ${groupId},
						Accept : "application/json"
					},
					success :  function(result){    

						manageDossier.navigate("/taohosomoi/nopthanhcong/${dossierId}"); 
						notification.show({
							message: "Yêu cầu được thực hiện thành công!"
						}, "success");      
					},
					error:function(result){
						notification.show({
							message: "Có lỗi sảy ra!"
						}, "error");
					}
				});

				console.log("submit dossier success!");
			}else {
				notification.show({
					message: "Vui lòng kiểm tra lại các thông tin bắt buộc!"
				}, "error");
			}
			
		}

		var funDeleteDossier = function(dossierId){
			if(dossierId){
				$.ajax({
					type : 'DELETE', 
					url  : '${api.server}/dossiers/'+dossierId, 
					dataType : "json",
					data : {

					},
					headers: {"groupId": ${groupId}},
					success :  function(result){                       
						manageDossier.navigate("/new");
					},
					error:function(result){

					}
				});
				console.log("delete dossier success!");
			}
		}

		$("#btn-delete-dossier,#btn-delete-dossier-header").click(function(){
			var dossierId = $(this).attr("data-pk");
			var cf = fnConfirm("Thông báo",
				"Bạn muốn xóa hồ sơ này", 
				"Ok", "Hủy bỏ",
				function(){

					funDeleteDossier(${(dossierId)!});

				}, function(){
					
				});

			cf.open();

		});

		var updateDossierURL = "/o/rest/v2/dossiers/${dossierId}";

		$('#contactName').editable({
			url: updateDossierURL,
			ajaxOptions:{
				type:'PUT',
				dataType: "json",
				headers: {"groupId": ${groupId}}
			},
			emptytext : "",
			params: function(params) {
				return {
					contactName: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success: function(response, newValue) {
				
			},
			error: function(event, id, obj) {

			}
		});
		$('#editContactName').click(function(e) {
			e.stopPropagation();
			$('#contactName').editable('toggle');
		});

		$('#address').editable({
			url: updateDossierURL,
			ajaxOptions:{
				type:'PUT',
				dataType: "json",
				headers: {"groupId": ${groupId}}
			},
			emptytext : "",
			params: function(params) {
				return {
					address: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success: function(response, newValue) {

			},
			error: function(event, id, obj) {

			}
		});
		$('#editAddress').click(function(e) {
			e.stopPropagation();
			$('#address').editable('toggle');
		});

		$('#city').editable({
			url: updateDossierURL,
			emptytext : "",
			ajaxOptions:{
				type:'PUT',
				dataType: "json",
				headers: {"groupId": ${groupId}}
			},
			params: function(params) {
				return {
					cityCode: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success: function(response, newValue) {
				var arrDisplay = new Array();
				$.ajax({
					url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
					dataType : "json",
					type : "GET",
					async: false,
					headers: {"groupId": ${groupId}},
					data : {
						parent : newValue
					},
					success : function(result){
						var arrDataRes = result.data;
						for (var i = 0; i < arrDataRes.length; i++) {
							arrDisplay.push({ value: arrDataRes[i].itemCode, text : arrDataRes[i].itemName});
						}
					},
					error : function(xhr){

					}
				});
				$('#district').editable('option', 'source', arrDisplay);
				$('#district').html("-");
				$('#wards').html("-");
				$.ajax({
					url : updateDossierURL,
					dataType : "json",
					type : "PUT",
					async: false,
					headers: {"groupId": ${groupId}},
					data : {
						districtCode: "-",
						districtName: "-",
						wardCode: "-",
						wardName: "-",
					},
					success : function(result){
						
					},
					error : function(xhr){

					}
				});
			},
			error: function(event, id, obj) {

			},
			prepend: "",
			source: function(){
				var arrDisplay = new Array();
				$.ajax({
					url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
					dataType : "json",
					type : "GET",
					async: false,
					headers: {"groupId": ${groupId}},
					data : {
						parent : 0
					},
					success : function(result){
						var arrDataRes = result.data;
						for (var i = 0; i < arrDataRes.length; i++) {
							arrDisplay.push({ value: arrDataRes[i].itemCode, text : arrDataRes[i].itemName});
						}
					},
					error : function(xhr){

					}
				});
				return arrDisplay;
			}
		});


		$('#district').editable({
			url: updateDossierURL,
			emptytext : "",
			ajaxOptions:{
				type:'PUT',
				dataType: "json",
				headers: {"groupId": ${groupId}}
			},
			params: function(params) {
				return {
					districtCode: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success : function(response, newValue){
				var arrDisplay = new Array();
				$.ajax({
					url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
					dataType : "json",
					type : "GET",
					async: false,
					headers: {"groupId": ${groupId}},
					data : {
						parent : newValue
					},
					success : function(result){
						var arrDataRes = result.data;
						for (var i = 0; i < arrDataRes.length; i++) {
							arrDisplay.push({ value: arrDataRes[i].itemCode, text : arrDataRes[i].itemName});
						}
					},
					error : function(xhr){

					}
				});
				$('#wards').editable('option', 'source', arrDisplay);
				$('#wards').html("-");
				$.ajax({
					url : updateDossierURL,
					dataType : "json",
					type : "PUT",
					async: false,
					headers: {"groupId": ${groupId}},
					data : {
						wardCode: "-",
						wardName: "-",
					},
					success : function(result){
						
					},
					error : function(xhr){

					}
				});
			},
			error : function(xhr){

			},
			prepend: "",
			source: function(){
				var arrDisplay = new Array();
				$.ajax({
					url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
					dataType : "json",
					type : "GET",
					async: false,
					headers: {"groupId": ${groupId}},
					data : {
						parent : "${(dossier.cityCode)!}"
					},
					success : function(result){
						var arrDataRes = result.data;
						for (var i = 0; i < arrDataRes.length; i++) {
							arrDisplay.push({ value: arrDataRes[i].itemCode, text : arrDataRes[i].itemName});
						}
					},
					error : function(xhr){

					}
				});
				return arrDisplay;
			}
		});

		$('#wards').editable({
			url: updateDossierURL,
			emptytext : "",
			ajaxOptions:{
				type:'PUT',
				dataType: "json",
				headers: {"groupId": ${groupId}}
			},
			params: function(params) {
				return {
					wardCode: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success : function(data){

			},
			error : function(xhr){

			},
			prepend: "",
			source: function(){
				var arrDisplay = new Array();
				$.ajax({
					url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
					dataType : "json",
					type : "GET",
					async: false,
					headers: {"groupId": ${groupId}},
					data : {
						parent : "${(dossier.districtCode)!}"
					},
					success : function(result){
						var arrDataRes = result.data;
						for (var i = 0; i < arrDataRes.length; i++) {
							arrDisplay.push({ value: arrDataRes[i].itemCode, text : arrDataRes[i].itemName});
						}
					},
					error : function(xhr){

					}
				});
				return arrDisplay;
			}
		});

		$('#editCity').click(function(e) {
			e.stopPropagation();
			$('#city').editable('toggle');
		});

		$('#editDistrict').click(function(e) {
			e.stopPropagation();
			$('#district').editable('toggle');
		});

		$('#editWards').click(function(e) {
			e.stopPropagation();
			$('#wards').editable('toggle');
		});

		$('#contactTelNo').editable({
			url: updateDossierURL,
			ajaxOptions:{
				type:'PUT',
				dataType: "json",
				headers: {"groupId": ${groupId}}
			},
			emptytext : "",
			params: function(params) {
				return {
					contactTelNo: params.value
				};
			},
			validate: function(value) {
				if(value === ""){
					return "Bạn phải nhập số điện thoại";
				}
			},
			success: function(response, newValue) {

			},
			error: function(event, id, obj) {

			}
		});
		$('#editContactTelNo').click(function(e) {
			e.stopPropagation();
			$('#contactTelNo').editable('toggle');
		});

		$('#postalAddress').editable({
			url: updateDossierURL,
			ajaxOptions:{
				type:'PUT',
				dataType: "json",
				headers: {"groupId": ${groupId}}
			},
			emptytext : "",
			params: function(params) {
				return {
					postalAddress: params.value
				};
			},
			validate: function(value) {
				if(value === ""){
					return "Bạn phải nhập địa chỉ";
				}
			},
			success: function(response, newValue) {

			},
			error: function(event, id, obj) {

			}
		});
		
		$('#editPostalAddress').click(function(e) {
			e.stopPropagation();
			$('#postalAddress').editable('toggle');
		});

		$('#postalCityCode').editable({
			url: updateDossierURL,
			emptytext : "",
			ajaxOptions:{
				type:'PUT',
				dataType: "json",
				headers: {"groupId": ${groupId}}
			},
			params: function(params) {
				return {
					postalCityCode: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success: function(response, newValue) {
				
			},
			error: function(event, id, obj) {

			},
			prepend: "",
			source: function(){
				var arrDisplay = new Array();
				$.ajax({
					url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
					dataType : "json",
					type : "GET",
					async: false,
					headers: {"groupId": ${groupId}},
					data : {
						parent : 0
					},
					success : function(result){
						var arrDataRes = result.data;
						for (var i = 0; i < arrDataRes.length; i++) {
							arrDisplay.push({ value: arrDataRes[i].itemCode, text : arrDataRes[i].itemName});
						}
					},
					error : function(xhr){

					}
				});
				return arrDisplay;
			}
		});

		$('#editPostalCityCode').click(function(e) {
			e.stopPropagation();
			$('#postalCityCode').editable('toggle');
		});

		$('#postalTelNo').editable({
			url: updateDossierURL,
			ajaxOptions:{
				type:'PUT',
				dataType: "json",
				headers: {"groupId": ${groupId}}
			},
			emptytext : "",
			params: function(params) {
				return {
					postalTelNo: params.value
				};
			},
			validate: function(value) {
				if(value === ""){
					return "Bạn phải nhập số điện thoại";
				}
			},
			success: function(response, newValue) {
				
			},
			error: function(event, id, obj) {

			}
		});

		$('#editPostalTelNo').click(function(e) {
			e.stopPropagation();

			$('#postalTelNo').editable('toggle');
		});

		$('#applicantNote').editable({
			url: updateDossierURL,
			ajaxOptions:{
				type:'PUT',
				dataType: "json",
				headers: {"groupId": ${groupId}}
			},
			emptytext : "",
			params: function(params) {
				return {
					applicantNote: params.value
				};
			},
			validate: function(value) {

			},
			success: function(response, newValue) {

			},
			error: function(event, id, obj) {

			}
		});
		$('#editApplicantNote').click(function(e) {
			e.stopPropagation();
			$('#applicantNote').editable('toggle');
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
						console.log(result.dossierId);

						dataSourceDossierTemplate.read({
							dossierTemplateNo : result.dossierTemplateNo
						});

						var viewModel = kendo.observable({

							dossierId : result.dossierId,
							serviceName : result.serviceName,
							govAgencyName : result.govAgencyName,
							dossierIdCTN : result.dossierIdCTN,
							dossierNote : function(e){

								if(result.dossierNote){
									return result.dossierNote;
								}

								return "";
							},
							contactName : function(){
								$('#contactName').editable("setValue",result.contactName); 
								return result.contactName;
							},
							address : function(){
								$('#address').editable("setValue",result.address); 
								return result.address;
							},
							cityName : function(){
								$('#city').editable("setValue",result.cityCode); 
								return result.cityName;
							},
							districtName : function(){
								$('#district').editable("setValue",result.districtCode); 
								return result.districtName
							},
							wardName : function(){
								$('#wards').editable("setValue",result.wardCode); 
								return result.wardName
							},
							contactTelNo : function(){
								$('#contactTelNo').editable("setValue",result.contactTelNo);
								return result.contactTelNo; 
							},
							contactEmail : result.contactEmail,
							dossierNo : result.dossierNo,
							dossierStatusText : result.dossierStatusText,
							stepInstruction : function(){
								if(result.stepInstruction){
									return result.stepInstruction;
								}

								return "";
							},
							applicantNote : function(){
								$('#applicantNote').editable("setValue",result.applicantNote);
								if(!result.applicantNote){
									return "Ghi chú người dùng";
								}
								return result.applicantNote;
							},
							viaPostal : function(e){
								console.log(result.viaPostal);

								if(result.viaPostal < 2){
									$("#viaPostalContent").hide();
									$("#viaPostal").prop('checked', false);
								}else {
									$("#viaPostalContent").show();
									$("#viaPostal").prop('checked', true);
								}

								return result.viaPostal;
							},
							postalAddress : function(){
								$('#postalAddress').editable("setValue",result.postalAddress);
								return result.postalAddress;
							},
							postalCityName : function(){
								console.log(result.postalCityCode);
								console.log(result.postalCityName);
								$('#postalCityCode').editable("setValue",result.postalCityCode);
								return result.postalCityName;
							},
							postalTelNo : function(){
								$('#postalTelNo').editable("setValue",result.postalTelNo);
								return result.postalTelNo;
							},
							submitting : function(){
								if(result.submitting){
									$("#btn-submit-dossier").hide();
									$("#btn-submit-dossier-header").hide();
								}
							}

						});

						kendo.bind($("#detailDossier"), viewModel);
					},
					error : function(result){

					}

				});
			}
		}

		var fnGetReferenceUidForm = function(arrFile){
			var referenceUid = 0;
			if(arrFile){
				for (var i = 0; i < arrFile.length; i++) {
					if(arrFile[i].eForm){
						referenceUid = arrFile[i].referenceUid;
						break;
					}
				}
			}
			return referenceUid;
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
			return arrFile;
		}
		

		var funGenNumberFile = function(arrCount){
			$(".dossier-component-profile").each(function(index){
				var partNo = $(this).attr("data-partno");
				var found = $.grep(arrCount, function(v) {
					if(v.dossierPartNo === partNo){
						$("#validPart"+partNo).val(1);
					}
					return v.dossierPartNo === partNo;
				});

				$(this).attr("data-number",found.length);
				$(this).html('<span class="number-in-circle" >'+found.length+'</span>');
			});
		}

		var removeDossierFile = function(dossierId, fileId){
			var isSuccess = false;
			$.ajax({
				url : "${api.server}/dossiers/"+dossierId+"/files/"+fileId,
				dataType : "json",
				type : "DELETE",
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

		var funUploadFile = function(file, partNo , dossierTemplateNo , fileTemplateNo){
			var data = new FormData();
			console.log(file);

			data.append( 'displayName', $(file)[0].files[0].name);
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
					var fileLength = $(file)[0].files.length;

					var currentFileNumber = $(".dossier-component-profile").filter("[data-partno="+partNo+"]").attr("data-number");

					var totalFile = fileLength + parseInt(currentFileNumber, 0);

					$(".dossier-component-profile").filter("[data-partno="+partNo+"]").html('<span class="number-in-circle" >'+totalFile+'</span>');

					$(".dossier-component-profile").filter("[data-partno="+partNo+"]").attr("data-number",totalFile);
					$("#uploadFileTemplateDialog").modal("hide");

					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");

					$("#validPart"+partNo).val("1");

				},
				error:function(result){
					notification.show({
						message: "Xảy ra lỗi, xin vui lòng thử lại"
					}, "error");
				}
			});
			console.log("success!");
		}

		printDetailDossier(${dossierId});

		$("#viaPostal").change(function(){
			var viaPostal = 1;
			if($(this).is(":checked")) {
				$("#viaPostalContent").show();
				viaPostal = 2;
			}else{
				$("#viaPostalContent").hide();	
				viaPostal = 1;
			}
			$.ajax({
				url : "${api.server}/dossiers/${dossierId}",
				dataType : "json",
				type : "PUT",
				headers: {"groupId": ${groupId}},
				data : {
					viaPostal : viaPostal
				},
				success : function(result){

				},
				error : function(xhr){

				}
			});
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
			console.log(dossierFile);

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

		$(function(){
			manageDossier.route("/taohosomoi/nopthanhcong/(:dossierId)", function(dossierId){
				$("#mainType1").hide();
				$("#mainType2").show();
				$("#mainType2").load("${ajax.submited_dossier_info}&${portletNamespace}dossierId="+dossierId,function(result){

				});
			});
		});

		window.onload = function(){
			if($("#textDossierNote").text().length < 550){
				$("#guide-toggle").remove();
			}
		}


		var fnSaveForm = function(id, value){
			var current = $("#btn-save-formalpaca"+id);
			var referentUid = current.attr("referenceUid");
			console.log(referentUid);
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
						console.log($("#validPart"+id));
						$("#validPart"+id).val("1");
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

			console.log(id);
			console.log("ccc");

			var formType = $("#formPartNo"+id+" .formType").val();
			var value ;

			if(formType !== "dklr"){
				value = $("#formPartNo"+id).alpaca('get').getValue();

				var errorMessage = '';
				$("#formPartNo"+id+' div[class*="has-error"] > label').each(function( index ) {

					errorMessage = "notValid";

				});
				console.log(errorMessage);
				console.log(referentUidFile);
				console.log(value);

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
							console.log($("#validPart"+id));
							$("#validPart"+id).val("1");

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