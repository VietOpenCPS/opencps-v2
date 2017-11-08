<div class="box" id="detailDossier">

	<input type="hidden" name="dossierTemplateId" id="dossierTemplateId">
	<input type="hidden" name="dossierItemId" id="dossierItemId">
	<div class="row-header align-middle">
		<div class="background-triangle-big">Tên thủ tục</div> 
		<span class="text-bold" data-bind="text:serviceName"></span>
		<div class="pull-right group-icons">
			<a href="javascript:;" onclick="funSaveDossier();">
				<i class="fa fa-paper-plane" aria-hidden="true"></i> 
				Lưu
			</a>
		</div>
	</div>

	<div class="dossier-general-info P15 MB15">
		<div class="col-sm-12">
			Cơ quan thực hiện <span class="text-bold" data-bind="text:govAgencyName"></span>
		</div>
	</div>

	<div class="guide-section">
		<div class="head-part">
			<div class="background-triangle-small"><i class="fa fa-star"></i></div> <span class="text-uppercase">Hướng dẫn</span> <span class="text-light-gray">((gồm các bước làm thủ tục))</span>
		</div>

		<div class="content-part">
			<span data-bind="text:dossierNote"></span>
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
								Họ và tên
							</div>
							<div class="col-sm-10">
								<div class="form-group"> 
									<input type="text" class="form-control" data-bind="value : applicantName" id="applicantName" name="applicantName"> 
								</div>
							</div>

							<div class="col-sm-2">
								Địa chỉ
							</div>
							<div class="col-sm-10">
								<div class="form-group"> 
									<input type="text" class="form-control" data-bind="value: address" id="address" name="address"> 
								</div>
							</div>

							<div class="col-sm-2">
								Tỉnh/ Thành phố
							</div>
							<div class="col-sm-2 PR0">
								<div class="form-group"> 
									<input type="text" class="form-control" id="cityCode" name="cityCode" data-bind="value : cityCode"> 
								</div>
							</div>

							<div class="col-sm-2 text-right">
								Quận/ Huyện
							</div>
							<div class="col-sm-2 PR0">
								<div class="form-group"> 
									<input type="text" class="form-control" id="districtCode" name="districtCode" data-bind="value : districtCode"> 
								</div>
							</div>
							
							<div class="col-sm-2 text-center">
								Xã/ Phường
							</div>
							<div class="col-sm-2 PL0">
								<div class="form-group"> 
									<input type="text" class="form-control" id="wardCode" name="wardCode" data-bind="value : wardCode"> 
								</div>
							</div>

							<div class="col-sm-2">
								Điện thoại
							</div>
							<div class="col-sm-2 PR0">
								<div class="form-group"> 
									<input type="text" class="form-control" id="contactTelNo" name="contactTelNo" data-bind="value : contactTelNo"> 
								</div>
							</div>
							
							<div class="col-sm-2 text-right">
								Địa chỉ email
							</div>
							<div class="col-sm-6">
								<div class="form-group"> 
									<input type="text" class="form-control" id="contactEmail" name="contactEmail" data-bind="value : contactEmail"> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<form id="dossierFormSubmiting">
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
						<a href="javascript:;" class="text-light-blue uploadfile-form-repository" data-toggle="tooltip" data-placement="top" title="Tải giấy tờ từ kho lưu trữ" >
							<i class="fa fa-archive" aria-hidden="true"></i>
						</a>

						<label class="MB0 ML10 hover-pointer" for="file#:id#" title="Tải file lên" >
							<i class="fa fa-upload text-light-blue"></i>
						</label>
						
						<input type='file' id="file#:id#" name="file#:id#" class="hidden dossier-file" #if(multiple){# multiple #}# part-no="#:id#" file-template-no="#:fileTemplateNo#">

						<#-- <a href="javascript:;" class="text-light-blue dossier-file" data-toggle="tooltip" data-placement="top" title="Tải file lên" multiple-upload="#:multiple#" part-no="#:id#">
							<i class="fa fa-upload" aria-hidden="true"></i>
						</a> -->
						
						<a href="javascript:;" class="dossier-component-profile" data-toggle="tooltip" data-placement="top" title="Số tệp tin" data-partno="#:id#" data-number="#if(hasForm){# 1 #}else {# 0 #}#">
							<span class="number-in-circle" >#if(hasForm){# 1 #}else {# 0 #}#</span>
						</a>

						<a href="javascript:;" class="text-light-gray delete-dossier-file" data-toggle="tooltip" data-placement="top" title="Xóa" data-partno="#:id#">
							<i class="fa fa-trash-o" aria-hidden="true"></i> Xóa
						</a>
					</div>
				</div>

				#if(hasForm){#
				<div class="col-sm-12" id="formPartNo#:id#">

				</div>
				#
				$.ajax({
				url : "${api.server}/dossiertemplates/${dossierTemplateNo}/parts/"+id+"/formscript",
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
	<div class="row">
		<div class="col-sm-12">
			<label>Ghi chú</label>
			<input class="form-control" name="applicantNote" id="applicantNote" placeholder="Ghi chú">
		</div>
	</div>
	<div class="checkbox ML15">
		<input type="checkbox" id="viaPostal" name="viaPostal"> <label class="text-normal">Ông bà muốn sử dụng phương thức nhận kết quả hồ sơ qua đường bưu điện</label>
	</div>
	<div class="row MB20">
		<div class="col-xs-12 col-sm-7">
			<label>Địa chỉ nhận kết quả</label>
			<input type="text" class="form-control input-small" placeholder="Ghi rõ thôn, số nhà, tên đường - phố" id="postalAddress" name="postalAddress">
		</div>
		<div class="col-xs-12 col-sm-2">
			<label>Tỉnh/Thành phố</label>
			<input class="form-control MB0" name="postalCityCode" id="postalCityCode">
		</div>
		<div class="col-xs-12 col-sm-3">
			<label>Số điện thoại</label>
			<input type="text" class="form-control input-small" placeholder="" id="postalTelNo" name="postalTelNo">
		</div>
	</div>
</div>
</div>

<div class="button-row MT20">
	<button class="btn btn-active" id="btn-submit-dossier"><i class="fa fa-save"></i> Lưu</button>
	<#-- <button class="btn btn-active" id="btn-submit-dossier"><i class="fa fa-paper-plane"></i> Nộp hồ sơ</button>
	<button class="btn btn-active"><i class="fa fa-trash"></i> Xóa</button> -->
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

	$(function(){

		var fnBindDossierTemplClick = function(){
		//upload file click
		$(".dossier-file").unbind().change(function(){
			console.log("change");
			var partNo = $(this).attr("part-no");
			var fileTemplateNo = $(this).attr("file-template-no");
			console.log(partNo);
			console.log(fileTemplateNo);
			console.log($(this)[0].files[0]);

			funUploadFile($(this),partNo,${dossierTemplateNo},fileTemplateNo);
		});

		//tai giay to kho luu tru
		$(".uploadfile-form-repository").unbind().click(function(){
			$("#uploadFileTemplateDialog").load("${ajax.customer_dossier_detail_filetemplate}",function(result){
				$(this).modal("show");
			});
		});

		//xem file tai len theo tp ho so
		$(".dossier-component-profile").unbind().click(function() {
			var partNo = $(this).attr("data-partno");
			$("#uploadFileTemplateDialog").load("${ajax.customer_dossier_component_profiles}?dossierPartNo="+partNo,function(result){
				$(this).modal("show");
			});
		});

		$(".delete-dossier-file").unbind().click(function(event){
			var dossierId  = ${dossierId};
			var dataPartNo = $(this).attr("data-partno");

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
								for (var i = 0; i < data.length; i++) {
									if(dataPartNo === data[i].dossierPartNo){
										removeDossierFile(dossierId, data[i].referenceUid);

									}
								}
								$(".dossier-component-profile").filter("[data-partno="+dataPartNo+"]").html('<span class="number-in-circle" >0</span>');

								$(".dossier-component-profile").filter("[data-partno="+dataPartNo+"]").attr("data-number",0);
							/*notification.show({
								message: "Đổi mật khẩu thành công"
							}, "success");*/
						}
					},
					error : function(result) {
						/*notification.show({
							message: "Xẩy ra lỗi, vui lòng thử lại"
						}, "error");*/
					}
				});
				}
			}
		});
	}

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
		console.log("abcd");
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
				$.ajax({
					url : "${api.server}/dossiertemplates/${dossierTemplateNo}/parts",
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
		autoBind : true,
		change : function(){

		},
		template : function(data){

			indexDossiserPart ++;

			data.itemIndex = indexDossiserPart;

			return kendo.template($("#templateDossierPart").html())(data);

		},
		dataBound : function(){
			indexDossiserPart = 0;
			fnBindDossierTemplClick();
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

	var funSaveDossier = function(){
		//PUT dossier
		$.ajax({
			url  : '${api.server}/dossiers/${dossierId}', 
			dataType : "json",
			type : 'PUT', 
			headers: {"groupId": ${groupId}},
			data : {
				applicantName : $("#applicantName").val(),
				address : $("#address").val(),
				cityCode : $("#cityCode").val(),
				districtCode : $("#districtCode").val(),
				wardCode : $("#wardCode").val(),
				contactTelNo : $("#contactTelNo").val(),
				contactEmail : $("#contactEmail").val(),

				applicantNote : $("#applicantNote").val(),
				postalTelNo: $("#postalTelNo").val(),
				postalCityCode: $("#postalCityCode").val(),
				postalAddress: $("#postalAddress").val()
			},
			success :  function(result){                       
				console.log("PUT Dossier success!");

				//finish PUT dossier create action for dossier
				createActionDossier(${dossierId});

				$("#dossier_detail").show();
				$("#dossier_list").hide();
				$("#dossier_detail").load("${ajax.customer_dossier_detail_2}",function(result){
					
				});

			},
			error:function(result){
				console.error(result);
			}	
		});
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
					/*actionUser : $("#actionUser").val(),
					actionNote :  $("#applicantNote").val()
					*/
				},
				success : function(result){
					console.log("create acion dossier success!");

				},
				error : function(result){

				}
			});
		}
	}

	$("#cityCode").kendoComboBox({
		placeholder : "Chọn tên thành phố",
		dataTextField : "itemName",
		dataValueField : "itemCode",
		noDataTemplate : "Không có dữ liệu",
		dataSource : {
			transport : {
				read : {
					// url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
					url : "${api.server}/dictcollections/101/dictitems",
					dataType : "json",
					type : "GET",
					headers: {"groupId": ${groupId}},
					data : {
						parent : 0
					},
					success : function(result){

					},
					error : function(result){

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
			$("#districtCode").data("kendoComboBox").dataSource.read({
				parent : value
			});
		}
	});

	$("#districtCode").kendoComboBox({
		placeholder : "Chọn Quận/ Huyện",
		dataTextField : "itemName",
		dataValueField : "itemCode",
		noDataTemplate : "Không có dữ liệu",
		autoBind : false,
		dataSource : {
			transport : {
				read : function(options){
					$.ajax({
						// url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
						url : "${api.server}/dictcollections/101/dictitems",
						dataType : "json",
						type : "GET",
						headers: {"groupId": ${groupId}},
						data : {
							parent : options.data.parent
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
				total : "total"
			}
		},
		change : function(e){
			var value = this.value();
			$("#wardCode").data("kendoComboBox").dataSource.read({
				parent : value
			});
		}
	});

	$("#wardCode").kendoComboBox({
		placeholder : "Chọn Xã/ Phường",
		dataTextField : "itemName",
		dataValueField : "itemCode",
		noDataTemplate : "Không có dữ liệu",
		autoBind : false,
		dataSource : {
			transport : {
				read : function(options){
					$.ajax({
						// url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
						url : "${api.server}/dictcollections/101/dictitems",
						dataType : "json",
						type : "GET",
						headers: {"groupId": ${groupId}},
						data : {
							parent : options.data.parent
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
						// url : "${api.server}/dictcollections/ADMINISTRATIVE_REGION/dictitems",
						url : "${api.server}/dictcollections/101/dictitems",
						dataType : "json",
						type : "GET",
						headers: {"groupId": ${groupId}},
						data : {
							parent : 0
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
					console.log("load detail dossier!");
					console.log(result);
					var viewModel = kendo.observable({
						serviceName : result.serviceName,
						govAgencyName : result.govAgencyName,

						applicantName : result.applicantName,
						address : result.address,
						cityCode : result.cityCode,
						districtCode : result.districtCode,
						wardCode : result.wardCode,
						contactTelNo : result.contactTelNo,
						contactEmail : result.contactEmail,
						dossierNote : result.dossierNote

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

	var funUploadFile = function(file, partNo , dossierTemplateNo , fileTemplateNo){
		var data = new FormData();
		console.log(file);

		data.append( 'displayName', "");
		data.append( 'file', $(file)[0].files[0]);
		data.append('dossierPartNo', partNo);
		data.append('referenceUid', "");
		data.append('dossierTemplateNo', dossierTemplateNo);
		data.append('fileTemplateNo', fileTemplateNo);

		$.ajax({
			type : 'POST', 
			url  : '${api.server}/dossiers/${dossierId}/files', 
			data : data,
			headers: {"groupId": ${groupId}},
			processData: false,
			contentType: false,
			cache: false,
			async : false,
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

			},
			error:function(result){
				notification.show({
					message: "Thêm không thành công do số biểu mẫu bị trùng."
				}, "error");
			}
		});
		console.log("success!");
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

</script>