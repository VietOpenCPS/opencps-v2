<#if (Request)??>
	<#include "init.ftl">
</#if>

<form action="" method="POST" id="formDossierOnline">
	<div class="row-parts-content">
		<div class="row">
			<div class="col-xs-12 col-sm-2 text-right">
				<label class="with-input-sm">Kính gủi:</label>
			</div>
			<div class="col-xs-12 col-sm-10">
				<input name="organization" id="organization" type="text" class="form-control input-small" placeholder="Ghi rõ tên Cơ quan, Phòng ban nhà nước cần làm thủ tục">
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-2 text-right">
				<label class="with-input-sm">Tên người gửi:</label>
			</div>
			<div class="col-xs-12 col-sm-4">
				<input name="senderName" id="senderName" type="text" class="form-control input-small" placeholder="Họ và tên cá nhân">
			</div>
			<div class="col-xs-12 col-sm-2 text-right">
				<label class="with-input-sm">Số điện thoại:</label>
			</div>
			<div class="col-xs-12 col-sm-4">
				<input name="phone" id="phone" type="text" class="form-control input-small" placeholder="">
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-2 text-right">
				<label class="with-input-sm">Email/Thư điện tử:</label>
			</div>
			<div class="col-xs-12 col-sm-10">
				<input name="email" id="email" type="text" class="form-control input-small" placeholder="">
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-2 text-right">
				<label class="with-input-sm">Mục đích:</label>
			</div>
			<div class="col-xs-12 col-sm-10">
				<input name="purpose" id="purpose" type="text" class="form-control input-small" placeholder="">
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-2 text-right"></div>
			<div class="col-xs-12 col-sm-10">
				<div class="checkbox MT0">
					<input type="checkbox" name="cbxLicense" id="cbxLicense"> <label class="text-normal">Bản photo công chứng Văn bản cần đăng ký Bản quyền tác giả</label>
				</div>
			</div>
			<div class="col-xs-12 col-sm-2 text-right"></div>
			<div class="col-xs-12 col-sm-10">
				<div class="checkbox MT0">
					<input type="checkbox" name="cbxLicense2" id="cbxLicense2"> <label class="text-normal">Bản photo công chứng Văn bản cần đăng ký Bản quyền tác giả</label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-2 text-right">
				<label class="with-input-sm">Ngày khai hồ sơ:</label>
			</div>
			<div class="col-xs-12 col-sm-4">
				<input name="filingDate" id="filingDate" type="text" class="form-control input-small" placeholder="">
			</div>
			<div class="col-xs-12 col-sm-2 text-right">
				<label class="with-input-sm">Chữ ký:</label>
			</div>
			<div class="col-xs-12 col-sm-4">
				<input name="signature" id="signature" type="text" class="form-control input-small" placeholder="">
			</div>
		</div>
		<div class="row MB10">
			<div class="col-xs-12 col-sm-2 text-right"></div>
			<div class="col-xs-12 col-sm-10">
				<button class="btn btn-small MR15 btn-hover" id="btn-submit-online-form" type="button">Ghi lại</button>
				<button class="btn btn-small btn-hover">Xem trước</button>
			</div>
		</div>
	</div>
</form>

<script type="text/javascript">
	$("#btn-submit-online-form").click(function(){
		console.log("submit");
		var data = $('#formDossierOnline').serialize();
		$.ajax({
			type : 'POST', 
			url  : '${api.server}/dossiers/1/submitting', 
			data : data,
			success :  function(result){

			},
			error:function(result){

			}
		});
		console.log("success!");
	});
</script>