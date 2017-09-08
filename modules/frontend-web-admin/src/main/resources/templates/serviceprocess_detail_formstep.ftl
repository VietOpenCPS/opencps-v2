<#if (Request)??>
	<#include "init.ftl">
</#if>
<div class="modal-header">
	<a href="#" data-dismiss="modal" class="class pull-right">
		<span class="glyphicon glyphicon-remove"></span>
	</a>
	<h3 class="modal-title">Thêm bước xử lý hồ sơ</h3>
</div>
<div class="modal-body">
	<div id="tabstrip_service_process_step_modal">
    <ul>
        <li class="k-state-active">
            Cấu hình bước xử lý
        </li>
        <li>
            Cấu hình phân quyền
        </li>
    </ul>
    <div>
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
    <div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<div class="row">
						<button class="btn ML15" id="btn_add_step_role">Thêm phân quyền</button>
						<ul id ="service_process_step_role_list_view" class="ul-with-border ul-with-border-style-2"></ul>
						<div id="service_process_step_role_pager" class="k-pager-wrap full-width-pager"></div>
					</div>
					<script type="text/x-kendo-template" id="service_process_step_role_template">
						 <li class="clearfix" style="padding: 10px 0 10px 5px;" role="option" aria-selected="true">
							 <div class="col-sm-11 PL0">
									<div class="col-sm-6">
										#: roleName #
									</div>
									<div class="col-sm-1">
										<input id="moderator" name="moderator" type="checkbox" title="Xử lý chính" # if (moderator == true) { # checked # } #/>
									</div>
									<div class="col-sm-4">
										#: condition #
									</div>
							 </div>
							 <div class="col-sm-1 PL0">
									<div class="edit-buttons">
										<a class="btn-group k-edit-button" href="\\#" title="Sửa">
												<i aria-hidden="true" class="fa fa-pencil"></i>
										</a>
										<a class="btn-group k-delete-button" href="\\#" title="Xóa">
												<i aria-hidden="true" class="fa fa-times"></i>
										</a>
									</div>
							 </div>
						 </li>
					</script>
					<script type="text/x-kendo-template" id="service_process_step_role_edit_template">
						 <li class="clearfix" style="padding: 10px 0 10px 5px;" role="option" aria-selected="true">
							 <div class="col-sm-11 PL0">
									<div class="col-sm-6">
										<select id="roleName" name="roleName" data-pk="#: id #" class="form-control select-role"></select>
									</div>
									<div class="col-sm-1">
										<input id="moderator" name="moderator" type="checkbox" data-bind="checked:moderator" title="Xử lý chính"/>
									</div>
									<div class="col-sm-4">
										<input id="condition" name="condition" class="form-control" data-bind="condition"/>
									</div>
							 </div>
							 <div class="col-sm-1 PL0">
									<div class="edit-buttons">
										<a class="btn-group k-update-button" href="\\#" title="Lưu">
												<i aria-hidden="true" class="fa fa-check"></i>
										</a>
										<a class="btn-group k-cancel-button" href="\\#" title="Hủy">
												<i aria-hidden="true" class="fa fa-ban"></i>
										</a>
									</div>
							 </div>
						 </li>
					</script>
				</div>
			</div>
			<div class="row MT10 text-center">
				<button id="btn_hide_service_process_step_modal" class="k-button btn-primary" title="Hoàn tất">Hoàn tất</button>
			</div>
    </div>
	</div>

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

		$("#tabstrip_service_process_step_modal").kendoTabStrip({
				animation:  {
						open: {
								effects: "fadeIn"
						}
				}
		});

	})(jQuery);
</script>
