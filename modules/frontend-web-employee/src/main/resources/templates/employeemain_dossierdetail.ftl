<div class="panel panel-body MT20">
	<div class="row" style="border-bottom: 1px solid #ccc; padding-bottom: 5px;">
		<div class="col-sm-11">
			<strong>Thủ tục hành chính cấp giấy chứng nhận đủ điều kiện kinh doanh</strong>
		</div>
		<div class="col-sm-1 text-right">
			<i class="fa fa-arrows-alt" id="fullScreenEmployeeDetail" status="default"></i>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12" style="margin: 5px 0;">
			<strong>Thông tin chung hồ sơ</strong>
		</div>
		<div class="col-sm-12" style="margin-bottom: 15px;">
			<div id="dataDossierDetail">
				<div class="row">
					<div class="col-sm-4">
						<strong>${employee.dossierDetail.govAgencyName}</strong>
					</div>
					<div class="col-sm-4">
						<strong>Thời gian gửi: ${employee.dossierDetail.submitDate}</strong>
					</div>
					<div class="col-sm-4">
						<strong>Mã tiếp nhận: ${employee.dossierDetail.referenceUid}</strong>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-4">
						<strong>Tình trạng: ${employee.dossierDetail.applicantIdType}</strong>
					</div>
					<div class="col-sm-4">
						<strong>Mã hồ sơ: ${employee.dossierDetail.serviceCode}</strong>
					</div>
					<div class="col-sm-4">
						<a href="javascript:;" id="btnProfileDetail" class="text-blue text-underline">Thông tin liên hệ</a>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-12" style="margin-bottom: 10px;">
			<a href="#" class="k-button" >Thụ lý hồ sơ</a>
			<a href="#employeeDossierFileTemplate" class="k-button">Thành phần hồ sơ</a>
			<a href="#employeeDossierLog" class="k-button">Tiến trình xử lý</a>
			<a href="#employeeDossierResult" class="k-button">Kết quả</a>
			<a href="#employeeDossierExchange" class="k-button">Trao đổi</a>
			<button class="k-button" id="showFileTemplateDialog">File Template</button>
		</div>
	</div>

	<div class="row" style="border-bottom: 1px solid #ccc;padding: 5px 0;">
		<i class="arrow-right"></i>
		<div style="padding-top: 8px;"><strong>&nbsp;&nbsp;THỤ LÝ HÔ SƠ</strong></div>
	</div>

	<!--include phan thu ly ho so-->
	<#include "${employee.include.dossierdetail_defaultprocess}">

	<div id="employeeDossierFileTemplate" class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 10px;padding: 5px 0;">
		<div class="col-sm-5">
			<div class="row">
				<i class="arrow-right"></i>
				<div style="padding-top: 8px;"><strong>&nbsp;&nbsp;THÀNH PHÂN HÔ SƠ</strong></div>
			</div>
		</div>
		<div class="col-sm-7 text-right">
			Những thành phần có dấu (<span style="color: red;">*</span>) là bắt buộc
		</div>
	</div>
	<div class="row" style="border-bottom: 1px solid #ccc;">
		<div class="col-sm-12">
			<ul class="ul-with-border">
				<div id="listViewDossiserFileTemplate"></div>
			</ul>
			<script type="text/x-kendo-template" id="templateDossiserFileTemplate">
				<li>
					<div class="row">
						<div class="col-sm-8">
							1. #:displayName#
						</div>
						<div class="col-sm-4 text-right">
							<a href="\\#" class="text-aqua"><i class="glyphicon glyphicon-eye-open text-aqua"></i> Xem đơn trực tuyến  <span class="badge">5</span></a>
						</div>
					</div>
				</li>
			</script>
		</div>
	</div>

	<div id="employeeDossierLog" class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 10px;padding: 5px 0;">
		<i class="arrow-right"></i>
		<div style="padding-top: 8px;"><strong>&nbsp;&nbsp;TIÊN TRÌNH XƯ LÝ</strong></div>
	</div>
	<div class="row" style="border-bottom: 1px solid #ccc;">
		<div class="col-sm-12">
			<ul class="ul-with-border">
				<div id="listViewDossiserLog"></div>
			</ul>
			<script type="text/x-kendo-template" id="templateDossiserLog">
				<li>
					<div class="row">
						<div class="col-sm-1">
							1.
						</div>
						<div class="col-sm-11">
							<div class="row">
								<strong>#:author#</strong>
								<p>#:deliveredDate#</p>
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
	<div id="employeeDossierResult" class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 10px;padding: 5px 0;">
		<i class="arrow-right"></i>
		<div style="padding-top: 8px;"><strong>&nbsp;&nbsp;KÊT QUẢ</strong></div>
	</div>
	<div class="row" style="border-bottom: 1px solid #ccc;padding-bottom: 10px;">
		<div class="col-sm-12">
			<ul>
				<li><a href="#" ><i class="glyphicon glyphicon-download-alt"></i> Biên bản thẩm định</a></li>
				<li><a href="#"><i class="glyphicon glyphicon-download-alt"></i> Giấy chứng nhận kinh doanh</a></li>
				<li><a href="#"><i class="glyphicon glyphicon-download-alt"></i> Văn bản yêu cầu bổ sung</a></li>
				<li><a href="#"><i class="glyphicon glyphicon-download-alt"></i> Văn bản từ chối</a></li>

			</ul>
		</div>
	</div>

	<div id="employeeDossierExchange" class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 10px;padding: 5px 0;">
		<i class="arrow-right"></i>
		<div style="padding-top: 8px;"><strong>&nbsp;&nbsp;TRAO ĐÔỈ</strong></div>
	</div>
</div>

<div id="profileDetail" class="modal fade" role="dialog">
	
</div>

<div id="fileTemplateDialog" class="modal fade" role="dialog">

</div>

<script type="text/javascript">

	var dataSourceDossiserFileTemplate;
	var dataSourceDossiserLog;

	dataSourceDossiserFileTemplate=new kendo.data.DataSource({
		transport:{
			read:function(options){
				$.ajax({
					url:"${api.server}/dossiers/"+options.data.id+"/dossierfiles",
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

	dataSourceDossiserLog=new kendo.data.DataSource({
		transport:{
			read:function(options){
				$.ajax({
					url:"${api.server}/dossiers/"+options.data.id+"/dossierlogs",
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
		autoBind: false
	});

	$("#listViewDossiserLog").kendoListView({
		dataSource:dataSourceDossiserLog,
		template:kendo.template($("#templateDossiserLog").html()),
		autoBind: false
	});


	$("#fullScreenEmployeeDetail").click(function(){
		if($(this).attr("status")=="default"){
			$("#employeemain_dossierlist").removeClass("col-sm-4");
			$("#employeemain_dossierlist").hide();

			$("#employeemain_dossierdetail").removeClass("col-sm-8");
			$("#employeemain_dossierdetail").addClass("col-sm-12");
			$("#employeemain_dossierdetail").show();
			$(this).attr("status","full");
			$(this).removeClass("fa fa-arrows-alt").addClass("fa fa-compress");
		}else{
			$("#employeemain_dossierdetail").removeClass("col-sm-12");
			$("#employeemain_dossierdetail").addClass("col-sm-8");

			$("#employeemain_dossierlist").addClass("col-sm-4");
			$("#employeemain_dossierlist").show();
			$(this).attr("status","default");
			$(this).removeClass("fa fa-compress").addClass("fa fa-arrows-alt");
		}
	});

	$(document).ready(function(){
		$("a").on('click', function(event) {
			if (this.hash !== "") {
				event.preventDefault();
				var hash = this.hash;
				$('html, body').animate({
					scrollTop: $(hash).offset().top
				}, 800, function(){
					window.location.hash = hash;
				});
			}
		});
	});

	$("#btnProfileDetail").click(function(){
		$("#profileDetail").load("${employee.ajax.employeemain_dossierdetail_contacinfo}",function(result){
			$("#profileDetail").modal("show");
		});
		
	});

	$("#showFileTemplateDialog").click(function(){
		$("#fileTemplateDialog").load("employeemain_dossierdetail_filetemplate.ftl",function(result){
			$(this).modal("show");
		});
	});

</script>