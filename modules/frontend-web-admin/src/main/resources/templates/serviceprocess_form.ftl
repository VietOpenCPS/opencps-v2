<#if (Request)??>
	<#include "init.ftl">
</#if>
<div class="modal-header">
	<a href="#" data-dismiss="modal" class="class pull-right">
		<span class="glyphicon glyphicon-remove"></span>
	</a>
	<h3 class="modal-title">Thêm quy trình xử lý hồ sơ</h3>
</div>
<div class="modal-body">
	<div id="tabstrip_service_process_modal">
    <ul>
        <li class="k-state-active">
            Cấu hình quy trình
        </li>
        <li>
            Cấu hình phân quyền
        </li>
    </ul>
    <div>
			<form id="fm">
				<div class="row">
					<div class="col-xs-12 col-sm-3">Mã quy trình</div>
					<div class="col-xs-12 col-sm-9">
						<input id="processNo" name="processNo" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="${serviceprocess.processNo}"/>
					</div>
				</div>
				<div class="row MT10">
					<div class="col-xs-12 col-sm-3">Tên quy trình</div>
					<div class="col-xs-12 col-sm-9">
						<input id="processName" name="processName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="${serviceprocess.processName}"/>
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
    <div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<div class="row">
						<button class="btn ML15" id="btn_add_role">Thêm phân quyền</button>
						<ul id ="service_process_role_list_view" class="ul-with-border ul-with-border-style-2"></ul>
						<div id="service_process_role_pager" class="k-pager-wrap full-width-pager"></div>
					</div>
					<script type="text/x-kendo-template" id="service_process_role_template">
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
					<script type="text/x-kendo-template" id="service_process_role_edit_template">
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
				<button id="btn_hide_service_process_modal" class="k-button btn-primary" title="Hoàn tất">Hoàn tất</button>
			</div>
    </div>
	</div>
</div>

<script type="text/javascript">
  $(document).ready(function() {
      $("#tabstrip_service_process_modal").kendoTabStrip({
          animation:  {
              open: {
                  effects: "fadeIn"
              }
          }
      });

			$("#btn_hide_service_process_modal").click(function(e){
				 e.preventDefault();
				 $("#service_process_form").modal("hide");
			});
  });
</script>
