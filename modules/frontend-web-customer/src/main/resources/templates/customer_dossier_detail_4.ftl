<#if (Request)??>
<#include "init.ftl">
</#if>
<div id="detailDossier">
	<div class="box">

		<input type="hidden" name="dossierStatus" id="dossierStatus">
		
		<input type="hidden" name="dossierTemplateNo" id="dossierTemplateNo">
		<div class="row-header align-middle">
			<div class="background-triangle-big">Tên thủ tục</div> 
			<span class="text-bold" data-bind="text:serviceName"></span>
			<div class="pull-right group-icons">
				<a href="">
					<i class="fa fa-paper-plane" aria-hidden="true"></i> 
					Lưu
				</a>
			</div>
		</div>

		<div class="dossier-general-info P15 MB30">
			<p class="text-bold">Thông tin chung hồ sơ</p>
			<div class="col-sm-4">
				<div class="row MB5">
					<span class="text-bold">Cơ quan thực hiện</span>: <span data-bind="text:govAgencyName"></span>
				</div>
				<div class="row">
					<span class="text-bold">Tình trạng</span> <span data-bind="text:dossierStatusText"></span>
				</div>
			</div>
			<div class="col-sm-4 text-center">
				<div class="row MB5" id="">
					<span class="text-bold">Thời gian gửi</span>: <span data-bind="text:submitDate"></span>
				</div>
				<div class="row" id="">
					<span class="text-bold">Mã số hồ sơ</span>: <span data-bind="text : dossierId"></span>
				</div>
			</div>
			
			<div class="col-sm-4 text-center">
				<div class="row MB5" id="">
					<span class="text-bold">Mã tiếp nhận</span>: <span data-bind="text:dossierNo"></span>
				</div>
				<div class="row" id="">
					<a href="javascript:;" class="text-blue text-underline">Thông tin chủ hồ sơ</a>
				</div>
			</div>
			
			
			<div class="col-sm-12">
				<span data-bind="attr : {actionNote : actionNote1}" id="actionNote"></span>
			</div>
		</div>

		<div class="guide-section" id="guideDossier" style="display: none;">
			<div class="head-part">
				<div class="background-triangle-small"><i class="fa fa-star"></i></div> <span class="text-uppercase">Hướng dẫn</span> 
			</div>
			<div class="content-part">
				<span data-bind="text:submissionNote">
					
				</span>
			</div>
			<p class="MB0 text-light-blue"><a href="javascript:;" id="guide-toggle">Xem thêm >></a></p>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<form id="dossierFormSubmiting">
					<div class="dossier-parts">
						<div class="head-part align-middle MB5">
							<div class="background-triangle-small">I</div> <span class="text-uppercase">Thành phần hồ sơ</span> <span class="text-light-gray"><i>Những thành phần hồ sơ có dấu (<span class="red">*</span>) là thành phần bắt buộc</i></span>
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

									<a href="javascript:;" class="dossier-component-profile" data-toggle="tooltip" data-placement="top" title="Số tệp tin" data-partno="#:id#" data-number="#if(hasForm){# 1 #}else {# 0 #}#">
										<span class="number-in-circle" >#if(hasForm){# 1 #}else {# 0 #}#</span>
									</a>

									<a href="javascript:;" class="text-light-gray delete-dossier-file" data-toggle="tooltip" data-placement="top" title="Xóa" data-partno="#:id#">
										<i class="fa fa-trash-o" aria-hidden="true"></i> Xóa
									</a>
								</div>
							</div>

							#if(hasForm){#
							<div class="col-sm-12" style="height:450px;width:100%;overflow:auto;">
								<form id="formPartNo#:id#">

								</form>
							</div>
							#
							var referentUidFile =  getReferentUidFile(${dossierId});

							$.ajax({
							url : "${api.server}/dossiers/${dossierId}/files/"+referentUidFile+"/formscript",
							dataType : "json",
							type : "GET",
							headers : {"groupId": ${groupId}},
							success : function(result){
							console.log(result);
							$("\\#formPartNo"+id).empty();
							$("\\#formPartNo"+id).alpaca(result);
							$("\\#formPartNo"+id).append('<div class="row"><div class="col-xs-12 col-sm-12"><button id="btn-save-formalpaca" class="btn btn-active MB10 MT10" type="button" data-pk="'+id+'">Ghi lại</button></div></div>');
							$("\\#btn-save-formalpaca").click(function(){
							console.log("ccc");
							var value = $("\\#formPartNo"+id).alpaca('get').getValue();
							var validate = $("\\#formPartNo"+id).alpaca('get').isValid(true);
							if(validate){
							$.ajax({
							url : "${api.server}/dossiers/${dossierId}/files/"+referentUidFile+"/formdata",
							dataType : "json",
							type : "PUT",
							headers: {"groupId": ${groupId}},
							data : {
							formdata: JSON.stringify(value)
						},
						success : function(result){
						notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");
				},
				error : function(result){
				notification.show({
				message: "Thực hiện không thành công, xin vui lòng thử lại"
			}, "error");
		}
	});
}else {
notification.show({
message: "Vui lòng kiểm tra lại các thông tin bắt buộc trước khi gửi"
}, "error");
}

console.log(value);

});
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
	<div class="head-part align-middle">
		<div class="background-triangle-small">III</div> <span class="text-uppercase">Thanh toán</span>
	</div>
	<div class="content-part">
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
						<div class="col-sm-10 red" data-bind="text:paymentAmount"></div>
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
								<button class="btn btn-sm MR10">Trực tuyến</button> 
								<button class="btn btn-sm">Báo nộp chuyển khoản</button>
							</div>
						</div>

						<div class="row MB20 MT20" data-bind="value: isPay">
							<div class="col-sm-12 text-center">
								<div class="row">
									<div class="col-sm-3">
										
									</div>
									<div class="col-sm-6 text-center MB10">
										<span class="text-center">Chứng từ thanh toán cho chuyển khoản là giấy yêu cầu nộp tiền vào ngân hàng hoặc hóa đơn chứng nhận giao dịch chuyển khoản được in ra</span>
									</div>
									<div class="col-sm-3">
										
									</div>
								</div>
								<input type="file" id="file" name="file" class="hidden" >
								<label class="btn btn-sm MB0 ML10 hover-pointer" for="file" title="Tải file lên" >
									Chọn ảnh từ máy
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
							<div class="col-sm-1 MT30">
								<button class="btn">Gửi</button>
							</div>
						</div>
					</div>

					<div id="alreadyPaid" data-bind="value: isPay">
						<div class="row MB5">
							<div class="col-sm-2">								
								<span class="text-bold">Ghi chú kèm theo</span>	
							</div>
							<div class="col-sm-10" data-bind="text: paymentConfirmNote"></div>
						</div>

						<div class="row MB5">
							<div class="col-sm-2">								
								<span class="text-bold">Chứng từ kèm theo</span>	
							</div>
							<div class="col-sm-10" data-bind="text: paymentBankInfo"></div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</div> 


<div class="dossier-parts">
	<div class="head-part align-middle">
		<div class="background-triangle-small">II</div> <span class="text-uppercase">Kết quả</span>
	</div>
	<div class="content-part">
		<div class="row-parts-head MT5">
			<ul class="ul-with-border">
				<div id="listViewDossiserFileTemplate"></div>
			</ul>
			<script type="text/x-kendo-template" id="templateDossiserFileTemplate">
				<li>
					<div class="row">
						<div class="col-sm-12">
							<a href="${api.server}/dossiers/#:dossierId#/files/#:fileTemplateNo#" class="download-file-result" data-pk="#:dossierId#">
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
	<div class="head-part align-middle">
		<div class="background-triangle-small">III</div> <span class="text-uppercase">Tiến trình xử lý</span>
	</div>
	<div class="content-part">
		<div class="row-parts-head MT5">
			<ul class="ul-with-border">
				<div id="listViewDossiserLog"></div>
			</ul>
			<script type="text/x-kendo-template" id="templateDossiserLog">
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
									<#-- #if(payload.fileType === "pdf" ){#
										<img src="images/pdf.png" alt=""> <a href="#" class="text-greyy">#:payload.fileName#</a>
									#}else {#
										<img src="images/docx.png" alt=""> <a href="#" class="text-greyy">#:payload.fileName#</a>
										#}# -->
									</div>
								</div>
							</div>
						</li>
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

		<div id="uploadFileTemplateDialog" class="modal fade" role="dialog">
		</div>

		<div id="profileDetail" class="modal fade" role="dialog">

		</div>

	</div>
	<div class="button-row MT20">
		<button class="btn btn-active" id="btn-submit-dossier" data-bind="value:submitting"><i class="fa fa-paper-plane"></i> Nộp hồ sơ</button>
	</div>
</div>

<script type="text/javascript">

	$(function(){
		$( "body" ).data( "dossierFiles", [] );
		var fnBindDossierTemplClick = function(){
		//upload file click
		$(".dossier-file").unbind().change(function(){
			console.log("change");
			var partNo = $(this).attr("part-no");
			var fileTemplateNo = $(this).attr("file-template-no");
			var dossierTemplateNo = $("#dossierTemplateNo").val();
			console.log(partNo);
			console.log(fileTemplateNo);
			console.log($(this)[0].files[0]);

			funUploadFile($(this),partNo,dossierTemplateNo+"",fileTemplateNo);
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
			$("#profileDetail").load("${ajax.customer_dossier_component_profiles}?dossierPartNo="+partNo,function(result){
				$(this).modal("show");
			});
		});
	}

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
					url : "${api.server}/dossiers/${dossierId}/dossierfiles",
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
				id : "dossierFileId"
			}
		}
	});

	dataSourceDossiserLog = new kendo.data.DataSource({
		transport:{
			read:function(options){
				$.ajax({
					url : "${api.server}/dossiers/${dossierId}/logs",
					dataType : "json",
					type : "GET",
					headers : {"groupId": ${groupId}},
					data:{

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
			//bind su kien click cho cac class
			fnBindDossierTemplClick();

			//kiem tra dossier status, neu status thuoc new thi cho phep upoad hoac sua file
			fnCheckStatusAndHideUpload($("#dossierStatus").val());

			//gen number file cho icon thanh phan ho so
			
			var	arrFile = funDossierFile(${dossierId});
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
					console.log("load detail dossier!");
					console.log(result.dossierId);
					dataSourceDossierTemplate.read({
						dossierTemplateNo : result.dossierTemplateNo
					});

					var payment = fnLoadPayment(result.dossierId);
					if(!payment){
						$("#paymentDossierContent").html("");
					}

					var viewModel = kendo.observable({

						dossierId : result.dossierId,
						serviceName : result.serviceName,
						govAgencyName : result.govAgencyName,
						actionNote : result.actionNote,

						actionNote1 : function(e){
							var actionNote = this.get("actionNote");

							if(actionNote){
								$("#actionNote").html('<i class="fa fa-bolt" aria-hidden="true" style="color: red;"></i> <span>'+result.actionNote+'</span>');
							}else {
								$("#actionNote").remove();
							}

							return;
						},

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
						dossierStatus : result.dossierStatus,
						postalAddress : result.postalAddress,
						postalCityName : result.postalCityName,
						postalTelNo : result.postalTelNo,
						dossierTemplateNo : result.dossierTemplateNo,
						viaPostal : function(e){
							console.log(result.viaPostal);
							if(result.viaPostal === 0){
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
							console.log(this.get('paymentDossier'));
							if(this.get('paymentDossier').paymentFee){
								return this.get('paymentDossier').paymentFee;
							}
							return "";
						},
						paymentAmount : function(e){
							if(this.get('paymentDossier').paymentAmount){
								return this.get('paymentDossier').paymentAmount;
							}
							return "";
						},
						paymentGovAgencyName : function(e){
							if(this.get('paymentDossier').govAgencyName){
								return this.get('paymentDossier').govAgencyName;
							}
							return "";
						},
						paymentBankInfo : function(e){
							if(this.get('paymentDossier').bankInfo){
								return this.get('paymentDossier').bankInfo;
							}
							return "";
						},
						paymentStatus : function(e){
							if(this.get('paymentDossier').paymentStatus){
								return this.get('paymentDossier').paymentStatus;
							}
							return "";
						},
						paymentApproveDatetime : function(e){
							if(this.get('paymentDossier').approveDatetime){
								return this.get('paymentDossier').approveDatetime;
							}
							return "";
						},
						paymentConfirmNote : function(e){
							if(this.get('paymentDossier').confirmNote){
								return this.get('paymentDossier').confirmNote;
							}
							return "";
						},
						isPay : function(){
							if(this.get('paymentDossier').paymentStatus !== 2){
								$("#unpaid").show();
								$("#alreadyPaid").hide();
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
					$("#paymentDossierContent").html("");
				}

			});
		}
	}

	printDetailDossier(${dossierId});

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

	var fnCheckStatusAndHideUpload = function(dossierStatus){
		if(dossierStatus !== "New" || dossierStatus !== "Receiving"){
			$(".uploadfile-form-repository").remove();
			$(".lbl-dossier-flie").remove();
			$(".delete-dossier-file").remove();
		}
	}

	var fnLoadPayment = function(dossierId){

		console.log(dossierId);
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
					$("#paymentDossier").hide();
				}

			});
		}

		return resultModel;
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
});

var getReferentUidFile = function(dossierId){
	var referenceUid = 0;
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
							referenceUid = result.data[i].referenceUid;
							return ;
						}
					}
				}

			},
			error:function(result){

			}
		});
	}

	return referenceUid;
}

$(document).on("click","#btn-submit-dossier",function(event){
	var data = $('#dossierFormSubmiting').serialize();
	$.ajax({
		type : 'GET', 
		url  : '${api.server}/dossiers/${dossierId}/submitting', 
		data : data,
		headers : 
		{
			"groupId": ${groupId},
			Accept : "application/json"

		},
		success :  function(result){                       

		},
		error : function(result){

		}
	});
	console.log("success!");
});
</script>