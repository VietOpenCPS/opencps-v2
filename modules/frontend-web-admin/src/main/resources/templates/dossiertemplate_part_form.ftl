<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="row">
	<div class="col-xs-12 col-sm-12">
		<h4>Thêm mới thành phần hồ sơ</h4>
	</div>
</div>
<div id="dossier_template_part_model">
	<form id="fm">
		<div class="row">
			<div class="col-xs-12 col-sm-4">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Số thành phần
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input id="partNo" name="partNo" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:partNo"/>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Số thứ tự sắp xếp
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input id="sibling" name="sibling" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:sibling"/>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Bắt buộc
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input type="checkbox" id="required" name="required" data-bind="required:required"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-12">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Tên thành phần
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input id="partName" name="partName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:partName"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-12">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Hướng dẫn thành phần hồ sơ
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<textarea id="partTip" name="partTip" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:partTip" />
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-4">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Kiểu thành phần hồ sơ
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input id="partType" name="partType" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:partType"/>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Mã mẫu tài liệu
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input id="fileTemplateNo" name="fileTemplateNo" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:fileTemplateNo"/>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-4">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Yêu cầu chữ ký số
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<input type="checkbox" id="esign" name="esign" data-bind="required:esign"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					Mã tạo form
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<textarea id="formScript" name="formScript" class="k-textbox form-control"></textarea>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					Mã thiết kế xml jasper
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<textarea id="formReport" name="formReport" class="k-textbox form-control"></textarea>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					Dữ liệu mẫu
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<textarea id="sampleData" name="sampleData" class="k-textbox form-control"></textarea>
				</div>
			</div>
		</div>
		<div class="row MT10 text-center">
			<button id="btn_save_dossier_template_part" class="k-button btn-primary" title="Ghi lại">Ghi lại</button>
			<button id="btn_cancle_dossier_template_part" class="k-button btn-default" title="Hủy bỏ">Hủy bỏ</button>
		</div>
	</form>
</div>

<script type="text/javascript">
	$(document).on("click", "#btn_cancle_dossier_template_part", function(event){
		 event.preventDefault();
		 $("#dossiertemplate_part_container").show();
		 $("#dossiertemplate_part_form_container").hide();
	});
</script>
