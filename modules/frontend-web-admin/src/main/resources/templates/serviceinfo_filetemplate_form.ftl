<#if (Request)??>
<#include "init.ftl">
</#if>

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
						<input id="fileTemplateNo" name="fileTemplateNo" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="<#if (filetemplate.fileNo)??>${filetemplate.fileNo}</#if>"/>
					</div>
				</div>
				<div class="row MT10">
					<div class="col-xs-12 col-sm-3">Tên biểu mẫu</div>
					<div class="col-xs-12 col-sm-9">
						<input id="templateName" name="templateName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" value="<#if (filetemplate.fileName)??>${filetemplate.fileName}</#if>"/>
					</div>
				</div>
				<div class="row MT10">
					<div class="col-xs-12 col-sm-3">Tệp đính kèm</div>
					<div class="col-xs-12 col-sm-9">
						<input id="file" class="" type="file"/>
					</div>
				</div>
				<div class="row MT10 text-center">
					<button id="btnSaveFileTemplate" class="btn btn-active" type="button" data-bind="click: addFiletemplate">Ghi lại</button>
					<button id="btnCancleFileTemplate" class="btn" title="Hủy bỏ" data-dismiss="modal">Đóng</button>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	
</script>