
<div class="modal-header">
	<a href="#" data-dismiss="modal" class="class pull-right">
		<span class="glyphicon glyphicon-remove"></span>
	</a>
	<h3 class="modal-title">Thêm biểu mẫu hồ sơ</h3>
</div>
<div class="modal-body">
	<form id="fm">
		<div class="row">
			<div class="col-xs-12 col-sm-3">Số biểu mẫu</div>
			<div class="col-xs-12 col-sm-9">
				<input id="templateNo" name="templateNo" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="${dossiertemplate.templateNo}"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Tên biểu mẫu</div>
			<div class="col-xs-12 col-sm-9">
				<input id="templateName" name="templateName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="${dossiertemplate.templateName}"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Mô tả</div>
			<div class="col-xs-12 col-sm-9">
        <textarea id="description" name="description" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc">${dossiertemplate.description}</textarea>
			</div>
		</div>
		<div class="row MT10 text-center">
			<button id="btn_save_dossier_template" class="k-button btn-primary" title="Ghi lại">Ghi lại</button>
			<button id="btn_cancle_dossier_template" class="k-button btn-default" title="Hủy bỏ">Hủy bỏ</button>
		</div>
	</form>
</div>
