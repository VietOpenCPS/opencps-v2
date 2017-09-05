
<div class="modal-header">
	<a href="#" data-dismiss="modal" class="class pull-right">
		<span class="glyphicon glyphicon-remove"></span>
	</a>
	<h3 class="modal-title">Thêm bước xử lý hồ sơ</h3>
</div>
<div class="modal-body">
	<form id="fm">
		<div class="row">
			<div class="col-xs-12 col-sm-3">Mã bước xử lý</div>
			<div class="col-xs-12 col-sm-9">
				<input id="stepCode" name="stepCode" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="${processsteps.stepCode}"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Tên bước xử lý</div>
			<div class="col-xs-12 col-sm-9">
				<input id="stepName" name="stepName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="${processsteps.stepName}"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Số thứ tự</div>
			<div class="col-xs-12 col-sm-9">
				<input id="sequenceNo" name="sequenceNo" class="k-textbox form-control" value="${processsteps.sequenceNo}"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Trạng thái chính</div>
			<div class="col-xs-12 col-sm-9">
				<input id="dossierStatus" name="dossierStatus" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="${processsteps.dossierStatus}"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Trạng thái phụ</div>
			<div class="col-xs-12 col-sm-9">
				<input id="dossierSubStatus" name="dossierSubStatus" class="form-control" value="${processsteps.dossierSubStatus}"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Số ngày</div>
			<div class="col-xs-12 col-sm-9">
				<input id="durationCount" name="durationCount" type="number" class="k-textbox form-control" value="${processsteps.durationCount}"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Liên kết tra cứu dữ liệu</div>
			<div class="col-xs-12 col-sm-9">
				<input id="customProcessUrl" name="customProcessUrl" class="k-textbox form-control" value="${processsteps.customProcessUrl}"/>
			</div>
		</div>
    <div class="row MT10">
			<div class="col-xs-12 col-sm-3">Vai trò xử lý</div>
			<div class="col-xs-12 col-sm-9">
				<div class="row">
					<div class="col-xs-12 col-sm-2">Xử lý chính</div>
					<div class="col-xs-12 col-sm-10">
						<input class="form-control" name="stepAllowance" id="stepAllowance">
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-2">Chỉ xem</div>
					<div class="col-xs-12 col-sm-10">
						<input class="form-control" name="stepAllowanceViewOnly" id="stepAllowanceViewOnly">
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10 text-center">
			<button id="btn_save_service_process" class="k-button btn-primary" title="Ghi lại">Ghi lại</button>
			<button id="btn_cancle_service_process" class="k-button btn-default" title="Hủy bỏ">Hủy bỏ</button>
		</div>
	</form>
</div>

<script type="text/javascript">
	(function($){
		$("#dossierStatus").kendoComboBox({
			dataTextField: "dossierStatusName",
			dataValueField: "dossierStatusCode",
			dataSource: ${dossierStatus},
			filter: "contains"
		});

		$("#dossierSubStatus").kendoComboBox({
			dataTextField: "dossierSubStatusName",
			dataValueField: "dossierSubStatusCode",
			dataSource: ${dossierSubStatus},
			filter: "contains"
		});

		$(function() {
			$("[data-role=combobox]").each(function() {
				var widget = $(this).getKendoComboBox();
				widget.input.on("focus", function() {
					widget.open();
				});
			});
		});

		$("#stepAllowance").kendoMultiSelect({
			placeholder: "Xử lý chính",
			dataTextField: "roleName",
			dataValueField: "roleId",
			autoClose: false,
			dataSource: {
				transport:{
					read:{
						url:"${api.server}/processsteps/id/stepallowances",
						dataType:"json",
						type:"GET"
					}
				},
				schema:{
					data:"data"
				}
			},
			filter: "contains"
		});

		$("#stepAllowanceViewOnly").kendoMultiSelect({
			placeholder: "Chỉ xem",
			dataTextField: "roleName",
			dataValueField: "roleId",
			autoClose: false,
			dataSource: {
				transport:{
					read:{
						url:"${api.server}/processsteps/id/stepallowances",
						dataType:"json",
						type:"GET"
					}
				},
				schema:{
					data:"data"
				}
			},
			filter: "contains"
		});

	})(jQuery);
</script>
