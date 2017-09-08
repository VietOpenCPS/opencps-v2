<#if (Request)??>
	<#include "init.ftl">
</#if>
<div class="modal-header">
	<a href="#" data-dismiss="modal" class="class pull-right">
		<span class="glyphicon glyphicon-remove"></span>
	</a>
	<h3 class="modal-title">Thêm hành động</h3>
</div>
<div class="modal-body">
	<form id="fm">
		<div class="row">
			<div class="col-xs-12 col-sm-3">Mã hành động</div>
			<div class="col-xs-12 col-sm-9">
				<input id="actionCode" name="actionCode" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="<#if (processactions.actionCode)??>${processactions.actionCode}</#if>"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Tên hành động</div>
			<div class="col-xs-12 col-sm-9">
				<input id="actionName" name="actionName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="<#if (processactions.actionName)??>${processactions.actionName}</#if>"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Bước thực hiện thao tác</div>
			<div class="col-xs-12 col-sm-9">
				<input id="preProcessStepId" name="preProcessStepId" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="<#if (processactions.preProcessStepId)??>${processactions.preProcessStepId}</#if>"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Bước sau thực hiện thao tác</div>
			<div class="col-xs-12 col-sm-9">
				<input id="postProcessStepId" name="postProcessStepId" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="<#if (processactions.postProcessStepId)??>${processactions.postProcessStepId}</#if>"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Điều kiện thực hiện</div>
			<div class="col-xs-12 col-sm-9">
				<input id="preCondition" name="preCondition" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="<#if (processactions.preCondition)??>${processactions.preCondition}</#if>"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Kích hoạt tự động</div>
			<div class="col-xs-12 col-sm-9">
				<input id="autoEvent" name="autoEvent" class="form-control" value="<#if (processactions.autoEvent)??>${processactions.autoEvent}</#if>"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Phân công người xử lý</div>
			<div class="col-xs-12 col-sm-9">
				<input id="allowAssignUser" name="allowAssignUser" type="checkbox" ${processactions.allowAssignUser?string('checked', '')}/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Người xử lý</div>
			<div class="col-xs-12 col-sm-9">
				<input id="assignUserId" name="assignUserId" class="form-control" value="${processactions.assignUserId}"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Có thanh toán</div>
			<div class="col-xs-12 col-sm-9">
				<input id="requestPayment" name="requestPayment" type="checkbox" ${processactions.requestPayment?string('checked', '')}/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Phí thanh toán</div>
			<div class="col-xs-12 col-sm-9">
				<input id="paymentFee" name="paymentFee" class="k-textbox form-control" value="<#if (processactions.paymentFee)??>${processactions.paymentFee}</#if>"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Tạo file hồ sơ</div>
			<div class="col-xs-12 col-sm-9">
				<input id="createDossierFiles" name="createDossierFiles" class="k-textbox form-control" value="<#if (processactions.createDossierFiles)??>${processactions.createDossierFiles}</#if>"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Trả lại file hồ hồ</div>
			<div class="col-xs-12 col-sm-9">
				<input id="returnDossierFiles" name="returnDossierFiles" class="k-textbox form-control" value="<#if (processactions.returnDossierFiles)??>${processactions.returnDossierFiles}</#if>"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">Mã đồng bộ</div>
			<div class="col-xs-12 col-sm-9">
				<input id="syncActionCode" name="syncActionCode" class="k-textbox form-control" value="<#if (processactions.syncActionCode)??>${processactions.syncActionCode}</#if>"/>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-3">rollback</div>
			<div class="col-xs-12 col-sm-9">
				<input id="rollback" name="rollback" type="checkbox" ${processactions.rollback?string('checked', '')}/>
			</div>
		</div>
		<div class="row MT10 text-center">
			<button id="btn_save_service_process" class="k-button btn-primary" title="Ghi lại">Ghi lại</button>
			<button id="btn_cancle_service_process" class="k-button btn-default" title="Hủy bỏ">Hủy bỏ</button>
		</div>
	</form>
</div>

<script type="text/javascript">
	$("#preProcessStepId").kendoComboBox({
		dataTextField: "preProcessStepId",
		dataValueField: "preProcessStepName",
		dataSource: ${processactions.preProcessStep},
		filter: "contains"
	});

	$("#postProcessStepId").kendoComboBox({
		dataTextField: "postProcessStepId",
		dataValueField: "postProcessStepName",
		dataSource: ${processactions.postProcessStep},
		filter: "contains"
	});

	// $("#autoEvent").kendoComboBox({
	// 	dataTextField: "activeStatusName",
	// 	dataValueField: "activeStatusCode",
	// 	dataSource: ${processactions.autoEvent},
	// 	filter: "contains"
	// });

	$("#assignUserId").kendoComboBox({
		dataTextField: "assignUserName",
		dataValueField: "assignUserId",
		dataSource: ${processactions.assignUser},
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
</script>
