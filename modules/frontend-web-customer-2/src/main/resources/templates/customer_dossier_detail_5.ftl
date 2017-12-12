<div class="box">
	
	<div class="row-header align-middle">
		<div class="background-triangle-big">Tên thủ tục</div> 
		<span class="text-bold" data-bind="text:serviceName">Cấp giấy chứng nhận đăng kí quyền tác giả cho cá nhân, tổ chức Việt Nam</span>
		<div class="pull-right group-icons">
			<a href="">
				<i class="fa fa-paper-plane" aria-hidden="true"></i> 
				Lưu
			</a>
		</div>
	</div>

	<div class="dossier-general-info P15 MB30">
		<p>Thông tin chung hồ sơ</p>
		<div class="col-sm-4">
			<span class="text-bold">Cơ quan thực hiện</span>: <span data-bind="text:govAgencyName"></span>
		</div>
		<div class="col-sm-4" id="">
			<span class="text-bold">Thời gian gửi</span>: <span data-bind="text:submitDate"></span>
		</div>
		<div class="col-sm-4" id="">
			<span class="text-bold">Mã tiếp nhận</span>: <span data-bind="text:dossierNo"></span>
		</div>
		<div class="col-sm-4" id="">
			<span class="text-bold">Trạng thái</span>: <i data-bind="text:dossierStatusText">Chờ bổ sung</i> 
		</div>
		<div class="col-sm-4" id="">
			<span class="text-bold">Mã số hồ sơ</span>: <span data-bind="text : dossierNo">0123456789</span>
		</div>
		<div class="col-sm-4">
			<a href="#" class="text-blue text-underline">Thông tin chung hồ sơ</a>
		</div>
		<div class="col-sm-12">
			<span data-bind="value:actionNote" id="actionNote"></span>
		</div>
	</div>

	<div class="guide-section" id="guideDossier" style="display: none;">
		<div class="head-part">
			<div class="background-triangle-small"><i class="fa fa-star"></i></div> <span class="text-uppercase">Hướng dẫn</span> <span class="text-light-gray">((gồm các bước làm thủ tục))</span>
		</div>
		<div class="content-part">
			<span data-bind="text:submissionNote">
				
			</span>
		</div>
		<p class="MB0 text-light-blue"><a href="javascript:;" id="guide-toggle">Xem thêm >></a></p>
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
				url : "${api.server}/dossiers/${dossierId}/files/"+id+"/formdata",
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
	
<div class="dossier-parts" id="paymentDossier">
	<div class="head-part align-middle">
		<div class="background-triangle-small">III</div> <span class="text-uppercase">Thanh toán</span>
	</div>
	<div class="content-part">
		<div class="row-parts-head MT5">
			<div class="row">
				<div class="col-sm-12" id="paymentDossierContent">
					
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
						<div class="col-sm-11">
							<div class="row">
								<span class="text-bold">#:author# &nbsp;#:notificationType#</span>
								<p>#:createDate#</p>
							</div>
							<div class="row">
								<p>Ý kiến: #:content#</p>
							</div>
							<div class="row">
								<img src="../../../../asset/images/pdf.png" alt="">
								<a href="\\#" class="text-greyy"> #:notificationType#</a>
							</div>
						</div>
					</div>
				</li>
			</script>
		</div>
	</div>
</div> 

<div class="row-parts-content">
	<div class="row">
		<div class="col-sm-12 MB10">
			<span class="text-bold">Ông bà sử dụng phương thức nhận kết quả hồ sơ qua đường Bưu Điện qua địa chỉ: </span>
			<span data-bind="text:postalAddress"></span> | <span data-bind="text:postalCityName"></span> | <span data-bind="text:postalTelNo"></span>
		</div>
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
</div>

<div class="button-row MT20">
	<button class="btn btn-active" id="btn-submit-dossier"><i class="fa fa-paper-plane"></i> Nộp hồ sơ</button>
</div>

<script type="text/javascript">

	$(function(){

		$(document).on("click",".uploadfile-form-repository",function(event){
			$("#uploadFileTemplateDialog").load("${ajax.customer_dossier_detail_filetemplate}",function(result){
				$(this).modal("show");
			});
		});

		$(document).on("click",".dossier-file",function(event){
			$("#uploadFileTemplateDialog").load("${ajax.customer_uploadfile}",function(result){
				$(this).modal("show");
			});
		});

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
			var data = $('#dossierFormSubmiting').serialize();
			$.ajax({
				type : 'POST', 
				url  : '${api.server}/dossiers/${dossierId}/submitting', 
				data : data,
				headers : {"groupId": ${groupId}},
				success :  function(result){                       

				},
				error:function(result){

				}
			});
			console.log("success!");
		});

		var dataSourceDossiserFileTemplate;
		var dataSourceDossiserLog;

		dataSourceDossiserFileTemplate=new kendo.data.DataSource({
			transport:{
				read:function(options){
					$.ajax({
						url:"${api.server}/dossiers/${dossierId}/dossierfiles",
						dataType:"json",
						type:"GET",
						headers : {"groupId": ${groupId}},
						data:{

						},
						success:function(result){
							options.success(result);
						},
						error:function(result){
							options.error(result);
						}
					});
				}
			},
			schema:{
				data:"data",
				total:"total",
				model:{
					id:"dossierFileId"
				}
			}
		});

		dataSourceDossiserLog = new kendo.data.DataSource({
			transport:{
				read:function(options){
					$.ajax({
						url:"${api.server}/dossiers/${dossierId}/dossierlogs",
						dataType:"json",
						type:"GET",
						headers : {"groupId": ${groupId}},
						data:{

						},
						success:function(result){
							options.success(result);
						},
						error:function(result){
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

		var indexDossiserPart = 0 ;
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
							actionNote : function(e){
								if(result.actionNote){
									$("#actionNote").html('<i class="fa fa-bolt" aria-hidden="true" style="color: red;"></i> <span>'+result.actionNote+'</span>');
								}else{
									$("#actionNote").html("");
								}

								return "";
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

							postalAddress : result.postalAddress,
							postalCityName : result.postalCityName,
							postalTelNo : result.postalTelNo,
							submissionNote : function(e){
								if(result.submissionNote){
									$("#guideDossier").show();
									return result.submissionNote;
								}else {
									$("#guideDossier").hide();
									return "";
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

					}

				});
			}
		}

		printDetailDossier(${dossierId});

		var fnLoadPayment = function(dossierId){
			if(dossierId){
				$.ajax({
					url : "${api.server}/dossiers/"+dossierId+"/payments",
					dataType : "json",
					type : "GET",
					headers : {"groupId": ${groupId}},
					data : {
						paymentStatus : 0
					},
					success : function(result){
						if(result.data){
							var payment = result.data[0];
							$("#paymentDossierContent").html('');
							$("#paymentDossierContent").append('<div class="row MB5"><div class="col-sm-2">								<span class="text-bold">Tên phí thanh toán</span>	</div><div class="col-sm-10">'+payment.paymentFee+'</div> </div></div>');
							
							$("#paymentDossierContent").append('<div class="row MB5"><div class="col-sm-2">								<span class="text-bold">Gía trị thanh toán</span>	</div><div class="col-sm-10 red">'+payment.PaymentAmount+'</div></div>');

							$("#paymentDossierContent").append('<div class="row MB5"><div class="col-sm-2">								<span class="text-bold">Chuyển khoản đến</span>	</div><div class="col-sm-10">'+payment.govAgencyName+'</div></div>');

							$("#paymentDossierContent").append('<div class="row MB5"><div class="col-sm-2">								<span class="text-bold">Thông tin tài khoản nhận</span>	</div><div class="col-sm-10">'+payment.bankInfo+'</div></div>');

							var status = "";
							if(payment.paymentStatus === 0){
								status = "Chờ nộp";
							}else if(payment.paymentStatus === 1){
								status = "Báo nộp";
							}else if(payment.paymentStatus === 2){
								status = "Hoàn thành";
							}else if(payment.paymentStatus === 3){
								status = "Không hợp lệ";
							}

							$("#paymentDossierContent").append('<div class="row MB5"><div class="col-sm-2">								<span class="text-bold">Trạng thái</span>	</div><div class="col-sm-10">'+status+'</div></div>');

							$("#paymentDossierContent").append('<div class="row MB10"><div class="col-sm-2">								<span class="text-bold">Ngày yêu cầu nộp</span>	</div><div class="col-sm-10">'+payment.confirmDatetime+'</div></div>');


							if(payment.paymentStatus === 0){
								$("#paymentDossierContent").append('<div class="row MB10"><div class="col-sm-12"><button class="btn btn-sm MR10">Trực tuyến</button> <button class="btn btn-sm">Báo nộp chuyển khoản</button></div></div>');

								$("#paymentDossierContent").append('<div class="row"><div class="col-sm-11"><div class="form-group"> <label class="control-label">Ghi chú kèm theo</label> <textarea class="form-control" rows="2" id="serviceUrl" name="serviceUrl" data-bind="text:serviceUrl"></textarea> </div></div> <div class="col-sm-1 MT30"><button class="btn">Gửi</button></div></div>');
							}
							
						}else{
							$("#paymentDossier").hide();
						}
					},
					error :  function(result){
						$("#paymentDossier").hide();
					}

				});
			}
		}

		fnLoadPayment(${dossierId});

		
	});

</script>