<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="box">

	<div class="row-header align-middle">
		<div class="background-triangle-big">Tên thủ tục</div> 
		<span class="text-bold">Cấp giấy chứng nhận đăng kí quyền tác giả cho cá nhân, tổ chức Việt Nam</span>
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
			<span class="text-bold">Cơ quan thực hiện</span>: <span>Cục bản quyền tác gỉa</span>
		</div>
		<div class="col-sm-4" id="">
			<span class="text-bold">Thời gian gửi</span>: <span>Cục bản quyền tác gỉa</span>
		</div>
		<div class="col-sm-4" id="">
			<span class="text-bold">Mã tiếp nhận</span>: <span>Cục bản quyền tác gỉa</span>
		</div>
		<div class="col-sm-4" id="">
			<span class="text-bold">Trạng thái</span>: <i>Chờ bổ sung</i> 
		</div>
		<div class="col-sm-4" id="">
			<span class="text-bold">Mã số hồ sơ</span>: <span data-bind="text : dossierNo">0123456789</span>
		</div>
		<div class="col-sm-4">
			<a href="#" class="text-blue text-underline">
				Thông tin chủ hồ sơ
			</a>
		</div>
	</div>

	<div class="guide-section">
		<div class="head-part">
			<div class="background-triangle-small"><i class="fa fa-star"></i></div> <span class="text-uppercase">Hướng dẫn</span> <span class="text-light-gray">((gồm các bước làm thủ tục))</span>
		</div>
		<div class="content-part">
			<p class="MB0">
				<span class="text-bold text-blue MR5">Bước 1:</span>
				Đăng nhập
			</p>
			<p class="MB0">
				<span class="text-bold text-blue MR5">Bước 2:</span>
				Lựa chọn loại hồ sơ cần chuẩn bị
			</p>
			<p class="MB0">
				<span class="text-bold text-blue MR5">Bước 3:</span>
				Soạn thảo, chuẩn bị hồ sơ
			</p>
			<p class="MB0">
				<span class="text-bold text-blue MR5">Bước 4:</span>
				...
			</p>
			<p class="MB0">
				<span class="text-bold text-blue MR5">Bước 5:</span>
				...
			</p>
		</div>
		<p class="MB0 text-light-blue"><a href="javascript:;" id="guide-toggle">Xem thêm >></a></p>
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
						#if(hasForm && partType === 1){#
						<a href="javascript:;" class="text-light-blue dossier-online-declaration" data-toggle="tooltip" data-placement="top" title="Khai trực tuyến" >
							<i class="fa fa-pencil-square-o red" aria-hidden="true"></i>
						</a>
						#}#

						<a href="javascript:;" class="text-light-blue uploadfile-form-repository" data-toggle="tooltip" data-placement="top" title="Tải giấy tờ từ kho lưu trữ" >
							<i class="fa fa-archive" aria-hidden="true"></i>
						</a>

						<a href="javascript:;" class="text-light-blue dossier-file" data-toggle="tooltip" data-placement="top" title="Tải file lên" multiple-upload="#:multiple#">
							<i class="fa fa-upload" aria-hidden="true"></i>
						</a>

						<a href="javascript:;" class="dossier-component-profile" data-toggle="tooltip" data-placement="top" title="Số tệp tin">
							<span class="number-in-circle" >#if(hasForm){# 1 #}else {# 0 #}#</span>
						</a>

						<a href="javascript:;" class="text-light-gray delete-dossier-file" data-toggle="tooltip" data-placement="top" title="Xóa" data-pk="#:id#">
							<i class="fa fa-trash-o" aria-hidden="true"></i> Xóa
						</a>
					</div>
				</div>
			</script>
		</div>
	</form>

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
								<a href="${api.server}/dossiers/#:dossierId#/files/#:fileTemplateNo#" class="download-file-result" data-pk="#:dossierId#"><i class="fa fa-download"></i> #:displayName#</a>
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
				<span data-bind="text:postalAddress"></span> | <span data-bind="text:postalTelNo"></span>
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
	<button class="btn btn-active" id="btn-submit-dossier">
	<i class="fa fa-paper-plane"></i> Nộp hồ sơ</button>
</div>

<script type="text/javascript">

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

	$(".dossier-online-declaration").click(function (event) {
		
	})

	$("#btn-submit-dossier").click(function(){
		var data = $('#dossierFormSubmiting').serialize();
		$.ajax({
			type : 'POST', 
			url  : '${api.server}/dossiers/{id}/submitting', 
			data : data,
			success :  function(result){                       

			},
			error:function(result){

			}
		});
		console.log("success!");
	});

	var dataSourceDossiserFileTemplate;
	var dataSourceDossiserLog;

	dataSourceDossiserFileTemplate = new kendo.data.DataSource({
		transport:{
			read:function(options){
				$.ajax({
					url:"${api.server}/dossiers/101/dossierfiles",
					dataType:"json",
					type:"GET",
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
					url:"${api.server}/dossiers/101/dossierlogs",
					dataType:"json",
					type:"GET",
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
					url : "${api.server}/dossiertemplates/201/parts",
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
		}
	});

</script>