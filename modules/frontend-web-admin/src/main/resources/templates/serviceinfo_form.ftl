<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h4 class="modal-title"> Thông tin chung </h4>
		</div>
		<div class="modal-body">
			<form id="serviceInfoForm" >
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Tên thủ tục: 
							</label> 
							<input class="form-control" id="serviceName" name="serviceName" value="<#if (serviceInfo.serviceName)??>${serviceInfo.serviceName}</#if>" required="required" validationMessage="Tên thủ tục không đưọc bỏ trống"/>
							<span data-for="serviceName" class="k-invalid-msg"></span>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label class="control-label">Chọn cơ quan quản lý: 
								<span class="icon-asterisk text-warning"> 
									<span class="hide-accessible">*</span> 
								</span>
							</label> 
							<select class="form-control" id="administrationCode" name="administrationCode" required="required" validationMessage="Bạn phải chọn cơ quan quản lý">
								
							</select>
							<span data-for="administrationCode" class="k-invalid-msg"></span>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label class="control-label">Lọc theo lĩnh vực thủ tục: 
							</label> 
							
							<select class="form-control" id="domainCode" name="domainCode">
								
							</select>
						</div>				
					</div>
				</div>

				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label class="control-label">Số hiệu:
								<span class="icon-asterisk text-warning"> 
									<span class="hide-accessible">*</span> 
								</span> 
							</label> 
							<input class="form-control" id="serviceCode" name="serviceCode" required="required" value="<#if (serviceInfo.serviceCode)??>${serviceInfo.serviceCode}</#if>" 
							validationMessage="Số hiệu không đưọc bỏ trống"/>
							<span data-for="serviceCode" class="k-invalid-msg"></span>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label class="control-label">Trạng thái: 
							</label> 
							<select class="form-control" id="activeStatus" name="activeStatus">
								
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Trình tự thực hiện: 
							</label> 
							<textarea type="text" style="width: 100%;" rows="3"  id="processText" name="processText"><#if (serviceInfo.processText)??>${serviceInfo.processText}</#if></textarea>
						</div>
					</div>

				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Cách thực hiện: 
							</label> 
							<textarea type="text" style="width: 100%;" rows="3" id="methodText" name="methodText" ><#if (serviceInfo.methodText)??>${serviceInfo.methodText}</#if></textarea>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Thành phần, số lượng hồ sơ: 
							</label> 
							<textarea type="text" style="width: 100%;" rows="3" id="dossierText" name="dossierText" ><#if (serviceInfo.dossierText)??>${serviceInfo.dossierText}</#if></textarea>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Điều kiện thực hiện: 
							</label> 
							<textarea type="text" style="width: 100%;" rows="3" id="conditionText" name="conditionText"><#if (serviceInfo.conditionText)??>${serviceInfo.conditionText}</#if></textarea>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Thời hạn giải quyết hồ sơ: 
							</label> 
							<textarea type="text" style="width: 100%;" rows="3" id="durationText" name="durationText"><#if (serviceInfo.durationText)??>${serviceInfo.durationText}</#if></textarea>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Lệ phí thủ tục: 
							</label> 
							<textarea type="text" style="width: 100%;" rows="3" id="feeText" name="feeText">
							</textarea>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Kết quả thực hiện: 
							</label> 
							<textarea type="text" style="width: 100%;" rows="3" id="resultText" name="resultText"><#if (serviceInfo.resultText)??>${serviceInfo.resultText}</#if></textarea>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Tempalte: 
							</label> 
							<input class="form-control" name="serviceInfoTemplate" id="serviceInfoTemplate">
						</div>
					</div>
				</div>
				<div class="row text-center">
					<button class="k-button btn-primary" id="btnSaveserviceInfo">
						<i class="glyphicon glyphicon-edit"></i> Lưu
					</button>
					<button class="k-button k-primary" id="btnCancleserviceInfo" data-dismiss="modal" >
						<i class="glyphicon glyphicon-trash"></i> Đóng
					</button>
				</div>
				<div class="status invalid"></div>
			</form>
		</div>
		<script type="text/javascript">
			$("#administrationCode").kendoComboBox();
			$("#domainCode").kendoComboBox();
			$("#activeStatus").kendoComboBox();
			$(function() {
				$("[data-role=combobox]").each(function() { 
					var widget = $(this).getKendoComboBox();
					widget.input.on("focus", function() {
						widget.open();
					});
				});
			});

			$("#serviceInfoTemplate").kendoMultiSelect({
				placeholder: "Chọn biểu mẫu...",
				dataTextField: "fileName",
				dataValueField: "fileTemplateId",
				autoClose: false,
				itemTemplate: '<p>Mã biểu mẫu: #:data.fileNo#</p><p>Tên biểu mẫu: #:data.fileName#</p><div style="border-bottom: 1px solid \\#ccc;"></div>',
				tagTemplate:  '<p>#:data.fileName# - #:data.fileNo#</p>',
				dataSource: {
					transport:{
						read:{
							url:"${api.server}/filetemplates",
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
		</script>
	</div>
</div>
