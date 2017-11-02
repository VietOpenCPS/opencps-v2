<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="panel panel-body">
	<div class="row" style="border-bottom: 1px solid #ccc; padding-bottom: 5px;">
		<div class="col-sm-9">
			<div class="row">
				<div class="background-triangle-big">Tên thủ tục</div>
				<span>Cấp giấy chứng nhận quyền đăng ký tác gỉa cho cá nhân tổ chức Việt Nam</span>
			</div>
		</div>
		<div class="col-sm-3 text-right">
			<button class="btn btn-bigger btn-reset"><i class="glyphicon glyphicon-send"></i> Gửi</button>
			<button class="btn btn-bigger btn-reset"><i class="glyphicon glyphicon-floppy-disk"></i> Lưu</button>
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
						<strong>${customer.dossierDetail.govAgencyName}</strong>
					</div>
					<div class="col-sm-4">
						<strong>Thời gian gửi: ${customer.dossierDetail.submitDate}</strong>
					</div>
					<div class="col-sm-4">
						<strong>Mã tiếp nhận: ${customer.dossierDetail.referenceUid}</strong>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-4">
						<strong>Tình trạng: ${customer.dossierDetail.applicantIdType}</strong>
					</div>
					<div class="col-sm-4">
						<strong>Mã hồ sơ: ${customer.dossierDetail.serviceCode}</strong>
					</div>
					<div class="col-sm-4">
						<a href="javascript:;" id="btnProfileDetail" class="text-blue text-underline">Thông tin liên hệ</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row" style="border-bottom: 1px solid #ccc;padding: 5px 0;">
		<div class="background-triangle-small"><i class="fa fa-star"></i></div>
		<span style="padding-top: 8px;"><strong>&nbsp;&nbsp;HƯƠNG DÂN</span>
	</div>

	<div class="row" style="margin:5px 0;">
		<div class="col-sm-12">
			<span>Ông bà đã nhập hồ sơ thành công</span> <br>
			Vui lòng đến địa chỉ sau đẻ làm thủ tục <br>
			<div style="margin-left: 20px;">
				<span>${customer.guide.name}</span> <br>
				<span>Địa chỉ: ${customer.guide.address}</span>
			</div>
			<a href="#" class="text-aqua">Xem chi tiết thông báo <i class="fa fa-angle-double-right icon-left"></i></a>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-12" style="margin-bottom: 10px;">
			<a href="#customerDossierFileTempalte" class="k-button">Thành phần hồ sơ</a>
			<a href="#customerDossierResult" class="k-button">Kết quả</a>
			<a href="#customerDossierPayment" class="k-button">Thông tin lệ phí</a>
			<a href="#customerDossierLog" class="k-button">Tiến trình xử lý</a>
			<#-- <button class="k-button" id="showFileTemplateDialog">File Template</button> -->
		</div>
	</div>

	<div id="customerDossierFileTempalte" class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 10px;padding: 5px 0;">
		<div class="col-sm-5">
			<div class="row">
				<div class="background-triangle-small MB5">I</div>
				<span style="padding-top: 8px;"><strong>&nbsp;&nbsp;THÀNH PHÂN HÔ SƠ</span>
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

	<div id="customerDossierResult" class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 10px;padding: 5px 0;">
		<div class="background-triangle-small MB5">II</div>
		<span style="padding-top: 8px;"><strong>&nbsp;&nbsp;KÊT QUẢ</span>
	</div>
	<div class="row" style="border-bottom: 1px solid #ccc;padding-bottom: 10px;">
		<div class="col-sm-12">
			<ul>
				<li><a href="#" ><i class="glyphicon glyphicon-download-alt"></i> Văn bản yêu cầu bổ sung</a></li>
			</ul>
		</div>
	</div>

	<div id="customerDossierPayment" class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 10px;padding: 5px 0;">
		<div class="background-triangle-small MB5">III</div>
		<span style="padding-top: 8px;"><strong>&nbsp;&nbsp;THÔNG TIN LÊ PHÍ</span>
	</div>
	<div class="row" style="border-bottom: 1px solid #ccc;padding-bottom: 10px;">
		<div class="col-sm-12">
			<ul class="ul-default ul-with-left-icon">
				<li>
					<p>
						<i class="fa fa-angle-double-right icon-left"></i>
						Lệ phí cấp giấy chứng nhận quyền tác gỉa: <span style="color: red;">100.000 đồng/Giấy chứng nhận</span>
					</p>
				</li>
				<li>
					<p>
						<i class="fa fa-angle-double-right icon-left"></i>
						Lệ phí cấp giấy chứng nhận quyền tác gỉa: <span style="color: red;">300.000 đồng/Giấy chứng nhận</span>
					</p>
				</li>
				<li>
					<p>
						<i class="fa fa-angle-double-right icon-left"></i>
						Lệ phí cấp giấy chứng nhận quyền tác gỉa: <span style="color: red;">400.000 đồng/Giấy chứng nhận</span>
					</p>
				</li>
				<li>
					<p>
						<i class="fa fa-angle-double-right icon-left"></i>
						Lệ phí cấp giấy chứng nhận quyền tác gỉa: <span style="color: red;">500.000 đồng/Giấy chứng nhận</span>
					</p>
				</li>
			</ul>
		</div>
	</div>

	<div id="customerDossierLog" class="row" style="border-bottom: 1px solid #ccc; margin-bottom: 10px;padding: 5px 0;">
		<div class="background-triangle-small MB5">IV</div>
		<span style="padding-top: 8px;"><strong>&nbsp;&nbsp;TIÊN TRÌNH XƯ LÝ</span>
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

	<div class="row" style="margin-top: 10px;">
		<div class="col-sm-12">
			<p><input type="checkbox" name="cbxBD" id="cbxBD"> <label for="cbxBD">Ông bà muốn sử dụng phương 	 nhận hồ sơ qua đưòng bưu điện</label></p>

			Địa chỉ nhận kêt quả <br>
			<input class="k-textbox form-control" name="address" id="address" >
		</div>
	</div>

	<div class="row" style="margin-top: 10px;">
		<div class="col-sm-12">
			<button class="k-button btn-blue">
				<i class="glyphicon glyphicon-send"></i> Gửi
			</button>
			<button class="k-button">
				<i class="glyphicon glyphicon-floppy-disk"></i> Lưu
			</button>
		</div>
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


	});

	$("#showFileTemplateDialog").click(function(){
		$("#fileTemplateDialog").load("employeemain_dossierdetail_filetemplate.ftl",function(result){
			$(this).modal("show");
		});
	});

</script>
