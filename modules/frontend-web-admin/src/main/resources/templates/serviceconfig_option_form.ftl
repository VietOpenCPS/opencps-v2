<#if (Request)??>
	<#include "init.ftl">
</#if>
<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h4 class="modal-title"> Cấu hình </h4>
		</div>
		<div class="modal-body">
			<form id="serviceConfigOptionForm">
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Mã thủ tục: 
							</label> 
							<input class="form-control" value="<#if (serviceoptions.submissionNote)??>${serviceoptions.submissionNote}</#if>" name="submissionNote" id="submissionNote">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Mẫu hồ sơ: 
							</label> 
							<input class="form-control" value="<#if (serviceoptions.dossierTemplateId)??>${serviceoptions.dossierTemplateId}</#if>" name="dossierTemplateId" id="dossierTemplateId">
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Quy trình xử lý: 
							</label> 
							<input class="form-control" value="<#if (serviceoptions.serviceProcessId)??>${serviceoptions.serviceProcessId}</#if>" name="serviceProcessId" id="serviceProcessId">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Thứ tự: 
							</label> 
							<input class="form-control" value="<#if (serviceoptions.seqOrder)??>${serviceoptions.seqOrder}</#if>" name="seqOrder" id="seqOrder">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Hướng dẫn chi tiết: 
							</label> 
							<textarea class="form-control" rows="3" name="instructionNote" id="instructionNote"><#if (serviceoptions.instructionNote)??>${serviceoptions.instructionNote}</#if></textarea> 
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label">Lựa chọn tự động: 
							</label> 
							<textarea class="form-control" rows="3" name="autoSelect" id="autoSelect"><#if (serviceoptions.autoSelect)??>${serviceoptions.autoSelect}</#if></textarea> 
						</div>
					</div>
				</div>

				<div class="row text-center">
					<div class="col-sm-12">
						<button class="k-button btn-primary" id="btnSaveServiceOption"><i class="glyphicon glyphicon-ok"></i> Ghi lại</button>
						<button class="k-button" id="btnCancelServiceOption"><i class="glyphicon glyphicon-remove"></i> Hủy</button>
					</div>
				</div>

				<div class="status invalid"></div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#dossierTemplateId").kendoComboBox({
		dataTextField:"templateName",
		dataValueField:"templateNo",
		filter: "contains",
		dataSource:{
			transport:{
				read:{
					url:"${api.server}/dossiertemplates",
					dataType:"json",
					type:"GET",
					success:function(result){

					},
					error:function(result){

					}
				}
			},
			schema:{
				data:"data",
				total:"total",
				model:{
					id:"templateNo"
				}
			}
		}
	});
	$("#serviceProcessId").kendoComboBox({
		dataTextField:"processName",
		dataValueField:"processNo",
		filter: "contains",
		dataSource:{
			transport:{
				read:{
					url:"${api.server}/serviceprocesses",
					dataType:"json",
					type:"GET",
					success:function(result){

					},
					error:function(result){

					}
				}
			},
			schema:{
				data:"data",
				total:"total",
				model:{
					id:"processNo"
				}
			}
		}
	});

</script>

