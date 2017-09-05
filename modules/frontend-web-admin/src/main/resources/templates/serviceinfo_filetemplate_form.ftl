<#include "init.ftl">

<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header">
			<a href="#" data-dismiss="modal" class="class pull-right">
				<span class="glyphicon glyphicon-remove"></span>
			</a>
			<h3 class="modal-title">Thêm biểu mẫu thủ tục hành chính</h3>
		</div>
		<div class="modal-body">
			<form id="serviceInfoFileTemplateForm">
				<div class="row">
					<div class="col-xs-12 col-sm-3">Số biểu mẫu</div>
					<div class="col-xs-12 col-sm-9">
						<input id="fileNo" name="fileNo" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="<#if (filetemplate.fileNo)??>${filetemplate.fileNo}</#if>"/>
					</div>
				</div>
				<div class="row MT10">
					<div class="col-xs-12 col-sm-3">Tên biểu mẫu</div>
					<div class="col-xs-12 col-sm-9">
						<input id="fileName" name="fileName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="<#if (filetemplate.fileName)??>${filetemplate.fileName}</#if>"/>
					</div>
				</div>
				<div class="row MT10">
					<div class="col-xs-12 col-sm-3">Tệp đính kèm</div>
					<div class="col-xs-12 col-sm-9">
						<input id="file" class="" type="file"/>
					</div>
				</div>
				<div class="row MT10 text-center">
					<button id="btnSaveFileTemplate" class="k-button btn-primary" title="Ghi lại">Ghi lại</button>
					<button id="btnCancleFileTemplate" class="k-button btn-default" title="Hủy bỏ" data-dismiss="modal">Đóng</button>
				</div>
			</form>
		</div>
	</div>
</div>