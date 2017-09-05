
<div class="modal-header">
	<a href="#" data-dismiss="modal" class="class pull-right">
		<span class="glyphicon glyphicon-remove"></span>
	</a>
	<h3 class="modal-title">Thêm quy trình xử lý hồ sơ</h3>
</div>
<div class="modal-body">
	<form id="fm">
		<div class="row">
			<div class="col-xs-12 col-sm-3">Mã quy trình</div>
			<div class="col-xs-12 col-sm-9">
				<input id="processNo" name="processNo" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="${serviceprocess.processNo}"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Mô tả</div>
			<div class="col-xs-12 col-sm-9">
				<textarea id="description" name="description" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc">${serviceprocess.description}</textarea>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">durationCount</div>
			<div class="col-xs-12 col-sm-9">
				<input id="durationCount" name="durationCount" type="number" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="${serviceprocess.durationCount}"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">durationUnit</div>
			<div class="col-xs-12 col-sm-9">
				<input id="durationUnit" name="durationUnit" type="number" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="${serviceprocess.durationUnit}"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">counter</div>
			<div class="col-xs-12 col-sm-9">
				<input id="counter" name="counter" type="number" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="${serviceprocess.counter}"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Sinh mã hồ sơ</div>
			<div class="col-xs-12 col-sm-9">
				<input id="generateDossierNo" name="generateDossierNo" type="checkbox" ${serviceprocess.generateDossierNo?string('checked', '')}/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Mẫu sinh mã hồ sơ</div>
			<div class="col-xs-12 col-sm-9">
				<input id="dossierNoPattern" name="dossierNoPattern" class="k-textbox form-control" value="${serviceprocess.dossierNoPattern}"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Sinh ngày tới hạn</div>
			<div class="col-xs-12 col-sm-9">
				<input id="generateDueDate" name="generateDueDate" type="checkbox" ${serviceprocess.generateDueDate?string('checked', '')}/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Mẫu sinh ngày tới hạn</div>
			<div class="col-xs-12 col-sm-9">
				<input id="dueDatePattern" name="dueDatePattern" class="k-textbox form-control" value="${serviceprocess.dueDatePattern}"/>
			</div>
		</div>
		<div class="row MT10 text-center">
			<button id="btn_save_service_process" class="k-button btn-primary" title="Ghi lại">Ghi lại</button>
			<button id="btn_cancle_service_process" class="k-button btn-default" title="Hủy bỏ">Hủy bỏ</button>
		</div>
	</form>
</div>
