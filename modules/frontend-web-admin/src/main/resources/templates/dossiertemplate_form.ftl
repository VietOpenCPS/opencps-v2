<#if (Request)??>
	<#include "init.ftl">
</#if>

<form id="fm">
	<div class="row MT10">
		<div class="col-xs-12 col-sm-3">Mã mẫu hồ sơ</div>
		<div class="col-xs-12 col-sm-9">
			<input id="templateNo" name="templateNo" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:templateNo"/>
		</div>
	</div>
	<div class="row MT10">
		<div class="col-xs-12 col-sm-3">Tên mẫu hồ sơ</div>
		<div class="col-xs-12 col-sm-9">
			<input id="templateName_" name="templateName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:templateName"/>
		</div>
	</div>
	<div class="row MT10">
		<div class="col-xs-12 col-sm-3">Mô tả</div>
		<div class="col-xs-12 col-sm-9">
			<textarea id="description" name="description" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:description"></textarea>
		</div>
	</div>
	<div class="row MT10 text-center">
		<button id="btn_save_dossier_template" class="k-button btn-primary" title="Ghi lại">Ghi lại</button>
		<button id="btn_cancle_dossier_template" class="k-button btn-default" title="Hủy bỏ">Hủy bỏ</button>
	</div>
</form>
