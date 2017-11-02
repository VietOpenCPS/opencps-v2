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
				Nộp hồ sơ
			</a>
			<br class="visible-xs">
			<a href="">
				<i class="fa fa-floppy-o" aria-hidden="true"></i> 
				Lưu
			</a>
			<br class="visible-xs">
			<a href="">
				<i class="fa fa-trash-o" aria-hidden="true"></i> 
				Xóa
			</a>
		</div>
	</div>



	<div class="dossier-general-info P15">
		<h5 class="text-bold">Thông tin chung hồ sơ</h5>
		<div class="row">
			<div class="col-xs-12 col-sm-4">
				<p class="MB5">Bà <span class="text-bold"><#if (customer.dossierDetail.govAgencyName)??>${customer.dossierDetail.govAgencyName}</#if></span></p>
				<p class="MB0">Tình trạng: <i class="text-gray"><#if (customer.dossierDetail.applicantIdType)??>${customer.dossierDetail.applicantIdType}</#if></i></p>
			</div>
			<div class="col-xs-12 col-sm-4">
				<p class="MB5">Thời gian gửi: ${customer.dossierDetail.submitDate}</p>
				<p class="MB0">Mã số hồ sơ: 1234567/CV/2432</p>
			</div>
			<div class="col-xs-12 col-sm-4">
				<p class="MB5">Mã tiếp nhận: <#if (customer.dossierDetail.referenceUid)??>${customer.dossierDetail.referenceUid}</#if></p>
				<p class="MB0"><a href="javascript:;" id="dossier-submit-info" class="text-link text-underline">Thông tin nộp hồ sơ</a></i></p>
			</div>
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

	<form id="dossier-submiting">
		<div class="dossier-parts">
			<div class="head-part align-middle">
				<div class="background-triangle-small">I</div> <span class="text-uppercase">Thành phần hồ sơ</span> <span class="text-light-gray"><i>Những thành phần hồ sơ có dấu (<span class="red">*</span>) là thành phần bắt buộc</i></span>
			</div>
			<div class="content-part">
				<#assign stt=1>
				<#list dossierPart as item>
				<#if item.type == 1>

				<div class="row-parts-head align-middle MT5">
					<span class="text-bold MR5">${stt}.</span> <span>&nbsp;&nbsp;${item.name} <span class="red">*</span></span>
					<div class="actions">
						<a href="javascript:;" class="text-light-blue uploadfile-form-repository" data-toggle="tooltip" data-placement="top" title="Tải giấy tờ từ kho lưu trữ"><i class="fa fa-archive" aria-hidden="true"></i></a>
						<a href="javascript:;" class="text-light-blue dossier-file" data-toggle="tooltip" data-placement="top" title="Tải file lên"><i class="fa fa-upload" aria-hidden="true"></i></a>
						<a href="javascript:;" class="dossier-file" data-toggle="tooltip" data-placement="top" title="Số tệp tin"><span class="number-in-circle">${item.dossierFileNumber}</span></a>
						<a href="javascript:;" class="text-light-gray" data-toggle="tooltip" data-placement="top" title="Xóa"><i class="fa fa-trash-o" aria-hidden="true"></i> Xóa</a>
					</div>
				</div>
				<#include "customer_dossier_online_form.ftl">

				<#elseif item.type == 3 && item.typeDisplay==1>
				<div class="row-parts-head align-middle">
					<span class="text-bold MR5">${stt}.</span> <span>&nbsp;&nbsp;${item.name}</span>
					<div class="actions">
						<a href="javascript:;" class="dossier-online-form" data-pk="${item.id}"><i class="fa fa-pencil-square-o red" aria-hidden="true"></i></a>
						<a href="javascript:;" class="text-light-blue uploadfile-form-repository" data-toggle="tooltip" data-placement="top" title="Tải giấy tờ từ kho lưu trữ"><i class="fa fa-archive" aria-hidden="true"></i></a>
						<a href="javascript:;" class="text-light-blue dossier-file" data-toggle="tooltip" data-placement="top" title="Tải file lên"><i class="fa fa-upload" aria-hidden="true"></i></a>
						<a href="javascript:;" class="dossier-file" data-toggle="tooltip" data-placement="top" title="Số tệp tin"><span class="number-in-circle">${item.dossierFileNumber}</span></a>
						<a href="javascript:;" class="text-light-gray" data-toggle="tooltip" data-placement="top" title="Xóa"><i class="fa fa-trash-o" aria-hidden="true"></i> Xóa</a>
					</div>
				</div>
				<#else>
				<div class="row-parts-head align-middle">
					<span class="text-bold MR5">${stt}.</span> <span>&nbsp;&nbsp;${item.name}</span>
					<div class="actions">
						<a href="javascript:;" class="text-light-blue uploadfile-form-repository" data-toggle="tooltip" data-placement="top" title="Tải giấy tờ từ kho lưu trữ"><i class="fa fa-archive" aria-hidden="true"></i></a>
						<a href="javascript:;" class="text-light-blue dossier-file" data-toggle="tooltip" data-placement="top" title="Tải file lên"><i class="fa fa-upload" aria-hidden="true"></i></a>
						<a href="javascript:;" class="dossier-file" data-toggle="tooltip" data-placement="top" title="Số tệp tin"><span class="number-in-circle">${item.dossierFileNumber}</span></a>
						<a href="javascript:;" class="text-light-gray" data-toggle="tooltip" data-placement="top" title="Xóa"><i class="fa fa-trash-o" aria-hidden="true"></i> Xóa</a>
					</div>
				</div>
				</#if>
				<#assign stt=stt+1> 
				</#list>
				<div class="row-parts-content">
					<div class="checkbox ML15">
						<input name="cbx1" type="checkbox"> <label class="text-normal">Bản photo công chứng Văn bản cần đăng ký Bản quyền tác giả</label>
					</div>
					<div class="row MB20">
						<div class="col-xs-12 col-sm-7">
							<label>Địa chỉ nhận kết quả</label>
							<input name="address" type="text" class="form-control input-small" placeholder="Ghi rõ thôn, số nhà, tên đường - phố">
						</div>
						<div class="col-xs-12 col-sm-2">
							<label>Tỉnh/Thành phố</label>
							<input class="form-control" name="city" id="city">
						</div>
						<div class="col-xs-12 col-sm-3">
							<label>Số điện thoại</label>
							<input name="phone" type="text" class="form-control input-small" placeholder="">
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	
</div>
<div id="uploadFileTemplateDialog" class="modal fade" role="dialog">
</div>

<div id="showDossierOnlineForm" class="modal fade" role="dialog">
</div>

<div id="dossierSubmitInfo" class="modal fade" role="dialog">
</div>
<div class="button-row MT20">
	<button class="btn btn-active" id="btn-dossier-submit"><i class="fa fa-paper-plane"></i> Nộp hồ sơ</button>
	<button class="btn btn-active"><i class="fa fa-save"></i> Lưu</button>
	<button class="btn btn-active"><i class="fa fa-trash"></i> Xóa</button>
</div>

<script type="text/javascript">

	$("#btn-dossier-submit").click(function(){
		var data = $('#dossier-submiting').serialize();
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

	$("#city").kendoComboBox({
		dataTextField:"name",
		dataValueField:"id",
		dataSource : {
			trasport : {
				read : {
					url:"${api.server}/dictCollections/ADMINISTRATIVE_REGION/dictItems?parent=0",	
					dataType:"json",
					type:"GET"
				}
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
</script>