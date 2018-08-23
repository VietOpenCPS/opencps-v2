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

	<div class="dossier-general-info P15">
		<div class="row">
			<div class="col-sm-6">
				<span class="text-bold">Người gửi: </span> <span class="" data-bind="text : applicantName"></span> <br>
				<span class="text-bold">Thời gian gửi: </span> <span class="" data-bind="text : submitDate"></span> <br>
				<span class="text-bold">Chuyển bởi: </span> <span class="" ></span> <br>
				<span class="text-bold">Người thực hiện: </span> <span class=""></span> <br>
				<span class="text-bold">Trạng thái: </span> <span class="" data-bind="text : dossierStatusText"></span> <br>
				<p><span data-bind="text: applicantNote"></span></p>
			</div>
			<div class="col-sm-6">
				<span class="text-bold">Mã số hồ sơ: </span> <span class="" data-bind="text: dossierId"></span> <br>
				<span class="text-bold">Mã tiếp nhận: </span> <span class="" data-bind="text :dossierNo"></span> <br>
				<span class="text-bold">Hạn xử lý: </span> <span class="" data-bind="" ></span> <br>
				<span class="text-bold">Ngày tiếp nhận: </span> <span class="" data-bind="text : receiveDate"></span> <br>
				<span class="text-bold">Ngày hẹn trả: </span> <span class="" data-bind="text : dueDate"></span> <br>
				<span class="text-bold">Bước xử lý: </span> <span class="" ></span> <br>
				<a href="javascript:;" class="text-light-blue">Thông tin liên hệ >></a>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-12">
			<div id="dossierFormSubmiting">
				<div class="dossier-parts">
					<div class="head-part align-middle PB5">
						<div class="background-triangle-small">I</div> <span class="text-uppercase">Thành phần hồ sơ</span> <span class="text-light-gray"><i>Những thành phần hồ sơ có dấu (<span class="red">*</span>) là thành phần bắt buộc</i></span>
					</div>
					<div class="content-part" id="lsDossierTemplPart">
					</div>
					<script type="text/x-kendo-template" id="templateDossierPart">
						#if(partType == 0){#
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

								<a href="javascript:;" class="dossier-component-profile" data-toggle="tooltip" data-placement="top" title="Số tệp tin" data-partno="#:id#" >
									<span class="number-in-circle" >0</span>
								</a>

							</div>
						</div>
						#}#
					</script>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-12">
			<div class="row-parts-content">
				<div class="head-part align-middle">
					<div class="background-triangle-small">II</div> <span class="text-uppercase">Thụ lý hồ sơ</span>
				</div>

				<div class="content-part">
					<div class="row-parts-head MT5">
						<div class="col-sm-12">
							<ul id="listViewProcess">

							</ul>
							<script type="text/x-kendo-template">
								<div class="row">
									<div class="col-sm-9">

									</div>
									<div class="col-sm-3">
										<label class="MB0 ML10 hover-pointer" for="file#:id#" title="Tải file lên" >
											<i class="fa fa-upload text-light-blue"></i>
										</label>

										<input type='file' id="file#:id#" name="file#:id#" class="hidden dossier-file">

										<a href="javascript:;" class="dossier-component-profile" data-toggle="tooltip" data-placement="top" title="Số tệp tin" >
											<span class="number-in-circle" ></span>
										</a>

										<a href="javascript:;" class="text-light-gray delete-dossier-file" data-toggle="tooltip" data-placement="top" title="Xóa" data-partno="#:id#">
											<i class="fa fa-trash-o" aria-hidden="true"></i> Xóa
										</a>
									</div>
								</div>
							</script>
						</div>

						<span class="text-bold MB5">Nhập ý kiến xử lý</span> <br>
						<textarea class="form-control MB15 MT5" rows="3" placeholder="Ghi ý kiến" ></textarea>
						<button class="btn btn-btn-border-color MR5 MB10" type="button"> Chấp nhận</button>
						<button class="btn btn-btn-border-color MR5 MB10" type="button"> Từ chối</button>
						<button class="btn btn-btn-border-color MB10" type="button"> Yêu cầu bổ sung</button>

					</div>
				</div>
			</div>
		</div>

	</div>

	<div class="row">
		<div class="col-sm-12">
			<div class="row-parts-content">
				<div class="head-part align-middle">
					<div class="background-triangle-small">III</div> <span class="text-uppercase">Tiến trình xử lý</span>
				</div>

				<div class="content-part">
					<div class="row-parts-head MT5">
						<ul class="ul-with-border">
							<div id="listViewDossiserLog"></div>
						</ul>
						<script type="text/x-kendo-template" id="dossiserLogTempl">
							<li>
								<div class="row">
									<div class="col-sm-1">
										#:itemIndex#.
									</div>
									<div class="col-sm-11 PL0">
										<div class="row">
											<span class="text-bold">#:author# <#-- (#:payload.jobposTitle#) &nbsp;</span> <span class="text-light-blue">#:payload.briefNote#</span> -->
											<p>#:createDate#</p>
										</div>
										<div class="row">
											<p>Ý kiến: #:content#</p>
										</div>

										<div class="row">
									<#-- 
									#if(payload.fileType === "pdf" ){#
										<img src="images/pdf.png" alt=""> <a href="#" class="text-greyy">#:payload.fileName#</a>
									#}else {#
										<img src="images/docx.png" alt=""> <a href="#" class="text-greyy">#:payload.fileName#</a>
										#}# 
									-->
								</div>
							</div>
						</div>
					</li>
				</script>
			</div>
		</div>
	</div>
</div>

</div>
<div class="row">
	<div class="col-sm-12">
		<div class="row-parts-content">
			<div class="head-part align-middle">
				<div class="background-triangle-small">IV</div> <span class="text-uppercase">Trao đổi thông tin</span>
			</div>

			<div class="content-part">
				<div class="row-parts-head MT5">

				</div>
			</div>
		</div>
	</div>
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

	var dataSourceDossierTemplate = new kendo.data.DataSource({
		transport : {
			read : function(options){
				
				$.ajax({
					url : "${api.server}/dossiertemplates/201/parts",
					dataType : "json",
					type : "GET",
					headers : {"groupId": ${groupId}},
					success : function(result){
						options.success(result);
					},
					error : function(xhr){
						options.error(xhr);
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

	var indexDossiserPart = 0;
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

		}
	});


	var dataSourceDossierLog = new kendo.data.DataSource({
		transport : {
			read : function(options){
				$.ajax({
					url : "${api.server}/dossiers/dossierlogs",
					dataType : "json",
					type : "GET",
					headers : {"groupId": ${groupId}},
					success : function(result){
						options.success(result);
					},
					error : function(xhr){
						options.error(xhr);
					}

				});
			}
		},
		schema : {
			data : "data",
			total : "total",
			model : {
				id : "dossierLogId"
			}
		}
	});

	var indexDossiserLog = 0;
	$("#listViewDossiserLog").kendoListView({
		dataSource : dataSourceDossierLog,
		autoBind : true,
		change : function(){

		},
		template : function(data){

			indexDossiserLog ++;

			data.itemIndex = indexDossiserLog;

			return kendo.template($("#dossiserLogTempl").html())(data);
		},
		dataBound : function(){
			indexDossiserLog = 0;

		}
	});

	var printDetailDossier = function(dossierId){
		if(dossierId){
			$.ajax({
				url : "${api.server}/dossiers/101",
				dataType : "json",
				type : "GET",
				headers : {"groupId": ${groupId}},
				success : function(result){
					console.log("load detail dossier!");
					console.log(result);
					/*dataSourceDossierTemplate.read({
						dossierTemplateNo : result.dossierTemplateNo
					});*/
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
						dueDate : result.dueDate,
						receiveDate : result.receiveDate,
						submitDate : result.submitDate,
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

	printDetailDossier(${dossierId});

</script>
