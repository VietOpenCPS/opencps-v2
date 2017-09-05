
<div class="modal-header">
	<a href="#" data-dismiss="modal" class="class pull-right">
		<span class="glyphicon glyphicon-remove"></span>
	</a>
	<h3 class="modal-title">Thêm thành phần hồ sơ</h3>
</div>
<div class="modal-body">
	<form id="fm">
		<div class="row">
			<div class="col-xs-12 col-sm-3">Số thành phần hồ sơ</div>
			<div class="col-xs-12 col-sm-9">
				<input id="partNo" name="partNo" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="${dossiertemplate_part.partNo}"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Tên thành phần hồ sơ</div>
			<div class="col-xs-12 col-sm-9">
				<input id="partName" name="partName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="${dossiertemplate_part.partName}"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Mô tả</div>
			<div class="col-xs-12 col-sm-9">
        <textarea id="partTip" name="partTip" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc">${dossiertemplate_part.partTip}</textarea>
			</div>
		</div>
    <div class="row MT10">
			<div class="col-xs-12 col-sm-3">Kiểu thành phần hồ sơ</div>
			<div class="col-xs-12 col-sm-9">
				<input id="partType" name="partType" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="${dossiertemplate_part.partType}"/>
			</div>
		</div>
    <div class="row MT10">
			<div class="col-xs-12 col-sm-3">Mã kí hiệu mẫu đơn</div>
			<div class="col-xs-12 col-sm-9">
				<input id="fileTemplateNo" name="fileTemplateNo" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="${dossiertemplate_part.fileTemplateNo}"/>
			</div>
		</div>
    <div class="row MT10">
			<div class="col-xs-12 col-sm-3">Bắt buộc</div>
			<div class="col-xs-12 col-sm-9">
				<input type="checkbox" id="required" name="required" ${dossiertemplate_part.required?string('checked', '')}/>
			</div>
		</div>
    <div class="row MT10">
			<div class="col-xs-12 col-sm-3">Ký số</div>
			<div class="col-xs-12 col-sm-9">
				<input type="checkbox" id="eSign" name="eSign" ${dossiertemplate_part.eSign?string('checked', '')}>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Mã tạo form</div>
			<div class="col-xs-12 col-sm-9">
				<textarea id="formScript" name="formScript" class="k-textbox form-control">${dossiertemplate_part.formScript}</textarea>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Mã thiết kế xml jasper</div>
			<div class="col-xs-12 col-sm-9">
				<textarea id="formReport" name="formReport" class="k-textbox form-control">${dossiertemplate_part.formReport}</textarea>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Dữ liệu mẫu</div>
			<div class="col-xs-12 col-sm-9">
				<textarea id="sampleData" name="sampleData" class="k-textbox form-control">${dossiertemplate_part.sampleData}</textarea>
			</div>
		</div>

		<div class="row MT10 text-center">
			<button id="btn_save_dossier_template_part" class="k-button btn-primary" title="Ghi lại">Ghi lại</button>
			<button id="btn_cancle_dossier_template_part" class="k-button btn-default" title="Hủy bỏ">Hủy bỏ</button>
		</div>
	</form>
</div>
