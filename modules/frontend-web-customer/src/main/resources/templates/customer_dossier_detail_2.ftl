<div id="detailDossier">
	<div class="box" >
		<input type="hidden" name="dossierTemplateId" id="dossierTemplateId">
		<input type="hidden" name="dossierItemId" id="dossierItemId">

		<div class="row-header align-middle">
			<div class="background-triangle-big">Tên thủ tục</div> 
			<span class="text-bold" data-bind="text:serviceName"></span>
			<div class="pull-right group-icons">
				<a href="javascript:;" onclick="funSubmitDossier()" >
					<i class="fa fa-paper-plane" aria-hidden="true"></i> 
					Nộp hồ sơ
				</a>
				<a href="#">
					<i class="fa fa-paper-plane" aria-hidden="true"></i> 
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
				<span class="text-bold">Trạng thái</span>: <i data-bind="text:dossierStatusText">Mới</i>
			</div>
			<div class="col-sm-7">
				<span class="text-bold">Mã số hồ sơ</span>: <span data-bind="text : dossierNo">0123456789</span>
			</div>
		</div>

		<div class="guide-section">
			<div class="head-part">
				<div class="background-triangle-small"><i class="fa fa-star"></i></div> <span class="text-uppercase">Hướng dẫn</span> <span class="text-light-gray">((gồm các bước làm thủ tục))</span>
			</div>
			<div class="content-part">
				<span data-bind="text:stepInstruction"></span>
			</div>
			<p class="MB0 text-light-blue"><a href="javascript:;" id="guide-toggle">Xem thêm >></a></p>
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
									<span id="contactName" data-pk="1" data-type="text" data-toggle="#editContactName" data-original-title="Nhập họ và tên" tabindex="-1" class="" data-bind="text:contactName"></span>
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
									<span id="address" data-pk="1" data-type="text" data-toggle="#editAddress" data-original-title="Nhập địa chỉ" tabindex="-1" class="" data-bind="text:address"></span>
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
									<span id="city" data-pk="1" data-type="text" data-toggle="#editCity" data-original-title="nhập thành phố" tabindex="-1" class="" data-bind="text:cityName"></span>
									<span class="pull-right">
										<a href="javascript:;" id="editCity" style="float: right"><i class="fa fa-pencil"></i></a>
									</span>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-2">
									<label>Điện thoại</label>
								</div>
								<div class="col-sm-10">
									<span id="contactTelNo" data-pk="1" data-type="text" data-toggle="#editContactTelNo" data-original-title="Nhập số điện thoại" tabindex="-1" class="" data-bind="text:contactTelNo"></span>
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

		<form id="dossierFormSubmiting" data-bind="value:dossierTemplateNo">
			<div class="dossier-parts">
				<div class="head-part align-middle">
					<div class="background-triangle-small">I</div> <span class="text-uppercase">Thành phần hồ sơ</span> <span class="text-light-gray"><i>Những thành phần hồ sơ có dấu (<span class="red">*</span>) là thành phần bắt buộc</i></span>
				</div>
				<div class="content-part" id="lsDossierTemplPart">
					<#-- <#include "customer_dossier_online_form.ftl"> -->
				</div>
				<script type="text/x-kendo-template" id="templateDossierPart">

					<div class="row-parts-head align-middle">
						<span class="text-bold MR5">#:itemIndex#.</span>
						<span>&nbsp;&nbsp;#:partName# 
							#if(required){#
							<span class="red">*</span>
							#}#
						</span>

						<div class="actions">

							<a href="javascript:;" class="dossier-component-profile" data-toggle="tooltip" data-placement="top" title="Số tệp tin" data-partno="#:id#" data-number="#if(hasForm){# 1 #}else {# 0 #}#">
								<span class="number-in-circle" >#if(hasForm){# 1 #}else {# 0 #}#</span>
							</a>
						</div>
					</div>

					#if(hasForm){#
					<div class="col-sm-12" id="formPartNo#:id#">

					</div>
					#
					var dossierTemplateId = $("\\#dossierTemplateId").val();
					console.log(dossierTemplateId);
					$.ajax({
					url : "${api.server}/dossiers/${dossierId}/files/#:id#/formdata",
					dataType : "json",
					type : "GET",
					headers : {"groupId": ${groupId}},
					data : {

				},
				success : function(result){
				console.log(result);
				$("\\#formPartNo"+id).html(result);
				console.log($("\\#formPartNo"+id));
			},
			error : function(result){

		}
	});
	#
	#}#
</script>
</div>
</form>

<div class="row-parts-content">

	<div class="checkbox ML15">
		<input type="checkbox"> <label class="text-normal">Ông bà muốn sử dụng phương thức nhận kết quả hồ sơ qua đường bưu điện</label>
	</div>

	<div class="row MB5">
		<div class="col-xs-12 col-sm-2">
			<label>Địa chỉ nhận kết quả</label>
		</div>
		<div class="col-sm-10">
			<span id="postalAddress" data-pk="1" data-type="text" data-toggle="#editPostalAddress" data-original-title="Nhập địa chỉ nhận kết quả" tabindex="-1" class="" data-bind="text:postalAddress"></span>
			<span class="pull-right">
				<a href="javascript:;" id="editPostalAddress" style="float: right"><i class="fa fa-pencil"></i></a>
			</span>
		</div>
	</div>
	<div class="row MB5">
		<div class="col-xs-12 col-sm-2">
			<label>Tỉnh/Thành phố</label>
		</div>
		<div class="col-sm-10">
			<span id="postalCityCode" data-pk="1" data-type="select" data-toggle="#editPostalCityCode" data-original-title="Chọn Tỉnh/Thành phố" tabindex="-1" class="" data-bind="text:postalCityCode"></span>
			<span class="pull-right">
				<a href="javascript:;" id="editPostalCityCode" style="float: right"><i class="fa fa-pencil"></i></a>
			</span>
		</div>
	</div>
	<div class="row MB5">
		<div class="col-xs-12 col-sm-2">
			<label>Số điện thoại</label>
		</div>
		<div class="col-sm-10">
			<span id="postalTelNo" data-pk="1" data-type="text" data-toggle="#editPostalTelNo" data-original-title="Nhập số điện thoại" tabindex="-1" class="" data-bind="text:postalTelNo"></span>
			<span class="pull-right">
				<a href="javascript:;" id="editPostalTelNo" style="float: right"><i class="fa fa-pencil"></i></a>
			</span>
		</div>
	</div>
</div>
</div>

<div class="button-row MT20">
	<button class="btn btn-active" id="btn-submit-dossier"><i class="fa fa-paper-plane"></i> Nộp hồ sơ</button>
	<button class="btn btn-active" id="btn-delete-dossier" data-bind="attr : {data-pk : dossierId}"><i class="fa fa-trash"></i> Xóa</button>
</div>
</div>

<div id="uploadFileTemplateDialog" class="modal fade" role="dialog">
</div>

<div id="showDossierOnlineForm" class="modal fade" role="dialog">
</div>

<div id="dossierSubmitInfo" class="modal fade" role="dialog">
</div>

<div id="profileDetail" class="modal fade" role="dialog">
	
</div>

<div id="fileTemplateDialog" class="modal fade" role="dialog">

</div>

<script type="text/javascript">

	/*$(document).on("click",".uploadfile-form-repository",function(event){
		$("#uploadFileTemplateDialog").load("${ajax.customer_dossier_detail_filetemplate}",function(result){
			$(this).modal("show");
		});
	});

	$(document).on("click",".dossier-file",function(event){
		$("#uploadFileTemplateDialog").load("${ajax.customer_uploadfile}",function(result){
			$(this).modal("show");
		});
	});*/

	$(document).on("click",".dossier-component-profile",function(event){
		$("#uploadFileTemplateDialog").load("${ajax.customer_dossier_component_profiles}",function(result){
			$(this).modal("show");
		});
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
				$.ajax({
					url : "${api.server}/dossiertemplates/"+options.data.dossierPart+"/parts",
					dataType : "json",
					type : "GET",
					headers : {"groupId": ${groupId}},
					data : {

					},
					success : function(result){
						options.success(result);
					},
					error : function(result){
						options.error(result);
					}
				});
			}
		},
		schema : {
			data : "data",
			total : "total",
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
			console.log(arrFile);
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

	$("#btn-submit-dossier").click(function(){
		funSubmitDossier();
	});

	var funSubmitDossier = function(){
		$.ajax({
			type : 'PUT', 
			url  : '${api.server}/dossiers/${dossierId}/submitting', 
			data : {

			},
			headers: {"groupId": ${groupId}},
			success :  function(result){                       
				$("#dossier_detail").show();
				$("#dossier_list").hide();
				$("#dossier_detail").load("${ajax.submited_dossier_info}",function(result){
					
				});
			},
			error:function(result){

			}
		});
		console.log("submit dossier success!");
	}

	var funDeleteDossier = function(dossierId){
		if(dossierId){
			var cf = confirm("Bạn có muốn xóa !");
			if(cf){
				$.ajax({
					type : 'DELETE', 
					url  : '${api.server}/dossiers/'+dossierId, 
					dataType : "json",
					data : {

					},
					headers: {"groupId": ${groupId}},
					success :  function(result){                       
						$("#dossier_detail").show();
						$("#dossier_list").hide();
						$("#dossier_detail").load("${ajax.submited_dossier_info}",function(result){

						});
					},
					error:function(result){

					}
				});
				console.log("delete dossier success!");
			}
		}
	}

	$("#btn-delete-dossier").click(function(){
		var dossierId = $(this).attr("data-pk");
		funDeleteDossier(dossierId);

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

		},
		success: function(response, newValue) {
			$("#profileName").html(newValue);
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
				cityCode: params.value,
			};
		},
		validate: function(value) {

		},
		success: function(response, newValue) {

		},
		error: function(event, id, obj) {

		},
		prepend: "",
		source: function(){
			var arrDisplay = new Array();
			$.ajax({
				url : "${api.server}/dictcollections/101/dictItems",
				dataType : "json",
				type : "GET",
				headers: {"groupId": ${groupId}},
				data : {
					parent : 0
				},
				success : function(result){
					console.log(result);
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
		ajaxOptions:{
			type:'PUT',
			dataType: "json",
			headers: {"groupId": ${groupId}}
		},
		emptytext : "",
		params: function(params) {
			return {
				postalCityCode: params.value
			};
		},
		validate: function(value) {
		},
		success: function(response, newValue) {
			$("#profileName").html(newValue);
		},
		error: function(event, id, obj) {

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
		},
		success: function(response, newValue) {
			$("#profileName").html(newValue);
		},
		error: function(event, id, obj) {

		}
	});

	$('#editPostalTelNo').click(function(e) {
		e.stopPropagation();

		$('#postalTelNo').editable('toggle');
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

					var viewModel = kendo.observable({

						dossierId : result.dossierId,
						serviceName : result.serviceName,
						govAgencyName : result.govAgencyName,

						applicantName : result.applicantName,
						address : result.address,
						cityCode : result.cityCode,
						districtCode : result.districtCode,
						wardCode : result.wardCode,
						contactTelNo : result.contactTelNo,
						contactEmail : result.contactEmail,
						dossierNo : result.dossierNo,
						dossierStatusText : result.dossierStatusText,
						stepInstruction : result.stepInstruction,

						dossierTemplateNo : function(e){
							dataSourceDossierTemplate.read({
								dossierPart : 201 //result.dossierTemplateNo
							});	
						}

					});

					kendo.bind($("#detailDossier"), viewModel);
				},
				error : function(result){

				}

			});
		}
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
					arrFile = result.data;
					
				},
				error : function(result){

				}
			});
		}
		return arrFile;
	}
	

	var funGenNumberFile = function(arrCount){
		console.log($(".dossier-component-profile"));
		$(".dossier-component-profile").each(function(index){
			var partNo = $(this).attr("data-partno");
			var found = $.grep(arrCount, function(v) {
				return v.dossierPartNo === partNo;
			});
			
			console.log(partNo);
			console.log(found);

			$(this).attr("data-number",found.length);
			$(this).html('<span class="number-in-circle" >'+found.length+'</span>');
		});
	}

	printDetailDossier(${dossierId});
</script>