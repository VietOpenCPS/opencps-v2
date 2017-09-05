<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h4 class="modal-title"> CẬP NHẬT CẤU HÌNH DỊCH VỤ CÔNG </h4>
		</div>
		<div class="modal-body">
			<form id="serviceConfigForm">
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label class="control-label">Thủ tục hành chính: 
							</label> 
							<input class="form-control" value="<#if (serviceconfig.serviceInfoId)??>${serviceconfig.serviceInfoId}</#if>" id="serviceInfoId" name="serviceInfoId" required="required" validationMessage="Bạn phải chọn thủ tục hành chính"/>
							<span data-for="serviceInfoId" class="k-invalid-msg"></span>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label class="control-label">Mã cơ quan: 
							</label> 
							<input class="form-control" value="<#if (serviceconfig.govAgencyCode)??>${serviceconfig.govAgencyCode}</#if>" id="govAgencyCode" name="govAgencyCode" required="required" validationMessage="Bạn phải chọn cơ quan"/>
							<span data-for="govAgencyCode" class="k-invalid-msg"></span>
						</div>				
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Cấp độ dịch vụ: 
							</label> 
							<input class="form-control" value="<#if (serviceconfig.serviceLevel)??>${serviceconfig.serviceLevel}</#if>" id="serviceLevel" name="serviceLevel"/>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Chứng thực: 
							</label> 
							<input class="form-control" value="<#if (serviceconfig.authentication)??>${serviceconfig.authentication}</#if>" id="authentication" name="authentication"/>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Hướng dẫn: 
							</label> 
							<textarea class="form-control" rows="3" id="serviceInstruction" name="serviceInstruction"><#if (serviceconfig.serviceInstruction)??>${serviceconfig.serviceInstruction}</#if></textarea>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Service URL: 
							</label> 
							<textarea class="form-control" rows="3" id="serviceUrl" name="serviceUrl"><#if (serviceconfig.serviceUrl)??>${serviceconfig.serviceUrl}</#if></textarea>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="text-center">
						<button class="k-button btn-primary" id="btnSaveServiceConfig"><i class="glyphicon glyphicon-ok"></i> Đồng ý </button>
						<button class="k-button" id="btnCancelServiceConfig"><i class="glyphicon glyphicon-trash"></i> Hủy bỏ </button>
					</div>
				</div>
				<div class="status invalid"></div>
			</form>

		</div>
	</div>
</div>

<script type="text/javascript">
	$("#serviceInfoId").kendoComboBox({
		
	});
	$("#govAgencyCode").kendoComboBox({
		
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