<#if (Request)??>
<#include "init.ftl">
</#if>

<div>
	<form id="process_detail_form" name="fm" class="" method="post">
		<div class="row">
			<div class="col-xs-12 col-sm-6">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Tên bước
					</div>
				</div>
				<div class="row MT5">
					<div class="col-xs-12 col-sm-12">
						<input id="stepName" name="stepName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value: stepName"/>
					</div>
				</div>
			</div>
			<div class="col-xs-2"></div>
			<div class="col-xs-12 col-sm-4">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Số thứ tự
					</div>
				</div>
				<div class="row MT5">
					<div class="col-xs-12 col-sm-12">
						<input id="sequenceNo" name="sequenceNo" class="k-textbox form-control" data-bind="value: sequenceNo"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-6">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Trạng thái chính
					</div>
				</div>
				<div class="row MT5">
					<div class="col-xs-12 col-sm-12">
						<input id="dossierStatus" name="dossierStatus" class="form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value: dossierStatus"/>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-2"></div>
			<div class="row">
				<div class="col-xs-12 col-sm-4">
					<div class="row">
						<div class="col-xs-12 col-sm-12">
							Mã bước quy trình
						</div>
					</div>
					<div class="row MT5">
						<div class="col-xs-12 col-sm-12">
							<input id="stepCode" name="stepCode" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value: stepCode"/>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-6">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Trạng thái phụ
					</div>
				</div>
				<div class="row MT5">
					<div class="col-xs-12 col-sm-12">
						<input id="dossierSubStatus" name="dossierSubStatus" class="form-control" data-bind="value: dossierSubStatus"/>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-2"></div>
			<div class="col-xs-12 col-sm-4">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Thời gian xử lý
					</div>
				</div>
				<div class="row MT5">
					<div class="col-xs-12 col-sm-12">
						<input id="durationCount_" name="durationCount" type="text" class="k-textbox form-control" data-bind="value: durationCount"/>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-8"></div>
			<div class="col-xs-12 col-sm-4">
				<div class="checkbox"> <input type="checkbox" name="editable" id="editable" data-bind="checked: editable"> <label>Cho phép sửa hồ sơ</label> </div>
			</div>
		</div>
		<div class="service-process-form-step-controls">
			<div class="service-process-form-step-entry">
				<div class="row MT10">
					<div class="col-xs-12 col-sm-12">Vai trò xử lý</div>
				</div>
				<div class="row MT5">
					<div class="col-xs-12 col-sm-6">
						<div class="row">
							<div class="col-xs-12 col-sm-12">
								<select class="form-control" name="roleId" data-bind="value: roleId">
									<option value=""></option>
								</select>
							</div>
						</div>
						<div class="row MT5">
							<div class="col-xs-12 col-sm-12">
								<div class="form-group"> <input name="condition" type="text" class="form-control" placeholder="Điều kiện phân công xử lý" data-bind="value: condition" title="Điều kiện phân công xử lý"> </div>
							</div>
						</div>
					</div>
					<div class="col-xs-12 col-sm-3">
						<div class="radio-inline"> <input type="radio" name="moderator_1" value="false" data-bind="checked: moderator"> <label>Theo dõi</label> </div>
					</div>
					<div class="col-xs-12 col-sm-2">
						<div class="radio-inline"> <input type="radio" name="moderator_1" value="true" data-bind="checked: moderator"> <label>Chủ trì</label> </div>
					</div>
					<div class="col-xs-12 col-sm-1">
						<button class="btn btn-success btn-add-step-role" type="button">
							<span class="glyphicon glyphicon-plus"></span>
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-12">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Lock State
					</div>
				</div>
				<div class="row MT5">
					<div class="col-xs-12 col-sm-12">
						<input type="text" class="form-control" id="lockState" name="lockState" data-bind="value: lockState">
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-12">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Hướng dẫn
					</div>
				</div>
				<div class="row MT5">
					<div class="col-xs-12 col-sm-12">
						<textarea class="form-control" name="stepInstruction" id="stepInstruction" data-bind="value: stepInstruction"></textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-12">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Tóm tắt hồ sơ
					</div>
				</div>
				<div class="row MT5">
					<div class="col-xs-12 col-sm-12">
						<textarea class="form-control" name="briefNote" id="briefNote" data-bind="value: briefNote"></textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10">
			<div class="col-xs-12 col-sm-12">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						Nhúng ajax của phần nghiệp vụ
					</div>
				</div>
				<div class="row MT5">
					<div class="col-xs-12 col-sm-12">
						<textarea class="form-control" name="customProcessUrl" id="customProcessUrl" data-bind="value: customProcessUrl"></textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="row MT10 text-center">
			<button id="btn_save_service_process_step" class="k-button btn-primary" title="Ghi lại">Ghi lại</button>
			<button id="btn_cancle_service_process_step" class="k-button btn-default" title="Hủy bỏ">Hủy bỏ</button>
		</div>
	</form>
</div>

<script type="text/javascript">

	$(document).on('click', '.btn-add-step-role', function(e){
		e.preventDefault();

		var controlForm = $('.service-process-form-step-controls');
		var	currentEntry = $(this).parents('.service-process-form-step-entry:first');

		var radioValue = currentEntry.find('input[type=radio]:checked').val();

		var newEntry = $(currentEntry.clone()).appendTo(controlForm);

		var index = controlForm.children().length;
		var name = 'moderator_' + index;
		newEntry.find('input[type=radio]').attr('name', name);

		newEntry.find('select').val('');
		newEntry.find('input[name=condition]').val('');
		newEntry.find('input[type=radio]:checked').prop('checked',false);
		currentEntry.find('input[type=radio][value=' + radioValue + ']').prop('checked',true);

		controlForm.find('.service-process-form-step-entry:not(:last) .btn-add-step-role')
		.removeClass('btn-add-step-role').addClass('btn-remove-step-role')
		.removeClass('btn-success').addClass('btn-danger')
		.html('<span class="glyphicon glyphicon-minus"></span>');
	}).on('click', '.btn-remove-step-role', function(e){
		$(this).parents('.service-process-form-step-entry:first').remove();
		e.preventDefault();
		return false;
	});

	(function($){
		$("#dossierStatus").kendoComboBox({
			dataTextField: "itemName",
			dataValueField: "itemCode",
			filter: "contains",
			dataSource : {
				transport : {
					read : {
						url : "${api.server}/dictcollections/DOSSIER_STATUS/dictitems",
						dataType : "json",
						type : "GET",
						headers: {"groupId": ${groupId}},
						success : function(result){

						},
						error : function(xhr){

						}
					}
				},
				schema: {
					data : "data",
					total : "total"
				}
			},
			noDataTemplate: 'Không có dữ liệu',
			change : function(e){
				var value = this.value();
				
				console.log(value);
				if(value){
					console.log("change");
					$("#dossierSubStatus").data("kendoComboBox").dataSource.read({
						parent : value
					});
				}

			}
		});

		$("#dossierSubStatus").kendoComboBox({
			dataTextField: "itemName",
			dataValueField: "itemCode",
			filter: "contains",
			autoBind : false,
			dataSource : {
				transport : {
					read : function(options){
						console.log("read");
						var parent = 0;
						if(options.data.parent){
							parent = options.data.parent;
						}
						$.ajax({
							url : "${api.server}/dictcollections/DOSSIER_STATUS/dictitems",
							dataType : "json",
							type : "GET",
							headers: {"groupId": ${groupId}},
							data : {
								parent : parent
							},
							success : function(result){
								options.success(result);
							},
							error : function(xhr){
								options.error(xhr);
							}

						});
					}
				},
				schema: {
					total : "total",
					data : "data",
					model : {
						id : "itemCode"
					}
				}
			},
			noDataTemplate: 'Không có dữ liệu'
		});

		$(function() {
			$("[data-role=combobox]").each(function() {
				var widget = $(this).getKendoComboBox();
				widget.input.on("focus", function() {
					widget.open();
				});
			});
		});

		$("#tabstrip_service_process_step_modal").kendoTabStrip({
			animation:  {
				open: {
					effects: "fadeIn"
				}
			}
		});

	})(jQuery);

	$(document).ready(function(){
		$.ajax({
			url: "${api.server}" + "/jobpos",
			type: "GET",
			dataType: "json",
			headers: {"groupId": ${groupId}},
			async: false,
			success: function(result) {
				console.log(result);
				if (result && result.data && result.data.length > 0){
					result.data.forEach(function(jobpos){
						var newOpt = $(".service-process-form-step-entry select")[0].appendChild(document.createElement('option'));
						newOpt.value = jobpos.roleId;
						newOpt.text = jobpos.title;
					});
				}
			}
		});
	});
</script>
