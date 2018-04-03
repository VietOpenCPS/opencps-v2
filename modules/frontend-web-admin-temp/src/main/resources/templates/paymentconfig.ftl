<#if (Request)??>
<#include "init.ftl">
</#if>
<form class="row panel panel-body" id="frmPaymentConfig">
	<div class="col-sm-6">
		<label>Tên cơ quan thực hiện  </label>
		<select class="form-control" id="govAgencyCode" name="govAgencyCode" required="required" validationMessage="Bạn phải chọn cơ quan">
			<option value=""></option>

		</select>
		<span data-for="govAgencyCode" class="k-invalid-msg"></span>
	</div>
	<div class="col-sm-6">

	</div>
	<div class="col-sm-6">
		<label>Mã số thuế cơ quan</label>
		<div class="form-group">
			<input type="text" name="govAgencyTaxNo" id="govAgencyTaxNo" class="form-control" placeholder="Số hiệu" data-bind="value:govAgencyTaxNo" required="required" validationMessage="Bạn phải điền số hiệu">
			<span data-for="govAgencyTaxNo" class="k-invalid-msg"></span>
		</div>
	</div>
	<div class="col-sm-6">
		<label>Ký hiệu hóa đơn</label>
		<div class="form-group">
			<input type="text" name="invoiceIssueNo" id="invoiceIssueNo" class="form-control" placeholder="Số hiệu" data-bind="value:invoiceIssueNo" required="required" validationMessage="Bạn phải điền số hiệu">
			<span data-for="invoiceIssueNo" class="k-invalid-msg"></span>
		</div>
	</div>
	<div class="col-sm-6">
		<label>Mẫu số hóa đơn</label>
		<div class="form-group">
			<input type="text" name="invoiceTemplateNo" id="invoiceTemplateNo" class="form-control" placeholder="Số hiệu" data-bind="value:invoiceTemplateNo" required="required" validationMessage="Bạn phải điền số hiệu">
			<span data-for="invoiceTemplateNo" class="k-invalid-msg"></span>
		</div>
	</div>
	<div class="col-sm-6">
		<label>Số hóa đơn tạo ra cuối cùng</label>
		<div class="form-group">
			<input type="text" name="invoiceLastNo" id="invoiceLastNo" class="form-control" placeholder="Số hiệu" data-bind="value:invoiceLastNo" required="required" validationMessage="Bạn phải điền số hiệu">
			<span data-for="invoiceLastNo" class="k-invalid-msg"></span>
		</div>
	</div>
	<div class="col-sm-12">
		<div class="form-group">
			<label class="control-label">Mẫu biên lai  
			</label> 
			<textarea class="form-control" rows="3" id="invoiceForm" name="invoiceForm" data-bind="text:invoiceForm" ></textarea>
		</div>
	</div>
	<div class="col-sm-12">
		<label>Thông tin tài khoản ngân hàng</label>
		<div class="form-group">
			<input type="text" name="bankInfo" id="bankInfo" class="form-control" placeholder="Số hiệu" data-bind="value:bankInfo" required="required" validationMessage="Bạn phải điền thông tin tài khoản ngân hàng">
			<span data-for="bankInfo" class="k-invalid-msg"></span>
		</div>
	</div>
	<div class="col-sm-12">
		<div class="form-group">
			<label class="control-label">Cấu hình thanh toán điện tử 
			</label> 
			<textarea class="form-control" rows="3" id="epaymentConfig" name="epaymentConfig" data-bind="text:epaymentConfig"></textarea>
		</div>
	</div>
	<div class="col-sm-12 text-center">
		<button class="btn btn-active" id="btn-submit-paymentconfig">Ghi lại</button>
		<button class="btn " id="btn-cancel-paymentconfig">Hủy bỏ</button>
	</div>
	<input type="hidden" name="itemPaymentConfig" id="itemPaymentConfig">
</form>

<script type="text/javascript">

	$("#govAgencyCode").kendoComboBox({
		placeholder : "Chọn cơ quan",
		dataTextField:"itemName",
		dataValueField:"itemCode",
		noDataTemplate: 'Không có dữ liệu',
		filter: "contains",
		index: -1,
		dataSource : {
			transport : {
				read : {
					url : "${api.server}/dictcollections/GOVERNMENT_AGENCY/dictitems",
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
		change : function(e){
			var value = this.value();
			pullPaymentConfig(value);
		},
		dataBound: function(e){
			pullPaymentConfig($("#govAgencyCode").data("kendoComboBox").value());
		}
	});

	var pullPaymentConfig = function(value){
		if(value){
			$.ajax({
				url : "${api.server}/paymentconfigs",
				dataType : "json",
				type : "GET",
				headers: {"groupId": ${groupId}},
				data : {
					//govAgencyCode : value
				},
				success : function(result){
					var hasPaymentConfig = false;
					if(result.data){
						if (result.data[0]){
							for (var i = 0; i < result.data.length; i++){
								var dataObj = result.data[i];

								if (dataObj.govAgencyCode == value){
									hasPaymentConfig = true;
									var viewModel = kendo.observable({
										govAgencyCode : dataObj.govAgencyCode,
										govAgencyName : dataObj.govAgencyName,
										govAgencyTaxNo : dataObj.govAgencyTaxNo,
										invoiceTemplateNo : dataObj.invoiceTemplateNo,
										invoiceIssueNo : dataObj.invoiceIssueNo,
										invoiceLastNo : dataObj.invoiceLastNo,
										bankInfo : dataObj.bankInfo,
									});

									kendo.bind($("#frmPaymentConfig"), viewModel);

									$("#itemPaymentConfig").val(dataObj.paymentConfigId);

									pullOtherField(dataObj.paymentConfigId);
								}
							}
						} else {
							var dataObj = result.data;

							if (dataObj.govAgencyCode == value){
								hasPaymentConfig = true;
								var viewModel = kendo.observable({
									govAgencyCode : dataObj.govAgencyCode,
									govAgencyName : dataObj.govAgencyName,
									govAgencyTaxNo : dataObj.govAgencyTaxNo,
									invoiceTemplateNo : dataObj.invoiceTemplateNo,
									invoiceIssueNo : dataObj.invoiceIssueNo,
									invoiceLastNo : dataObj.invoiceLastNo,
									bankInfo : dataObj.bankInfo,
								});

								kendo.bind($("#frmPaymentConfig"), viewModel);

								$("#itemPaymentConfig").val(dataObj.paymentConfigId);

								pullOtherField(dataObj.paymentConfigId);
							}
						}
					}

					if (!hasPaymentConfig){
						var viewModel = kendo.observable({
							govAgencyCode : "",
							govAgencyName : "",
							govAgencyTaxNo : "",
							invoiceTemplateNo : "",
							invoiceIssueNo : "",
							invoiceLastNo : "",
							bankInfo : "",
							invoiceForm: "",
							epaymentConfig: ""
						});

						kendo.bind($("#frmPaymentConfig"), viewModel);

						$("#itemPaymentConfig").val("");
					}
				},
				error : function(xhr){

				}
			});
		}
	}

	function pullOtherField(paymentConfigId){
		$.ajax({
			url : "${api.server}/paymentconfigs/" + paymentConfigId + "/invoiceform",
			dataType : "json",
			type : "GET",
			headers: {"groupId": ${groupId}},
			success : function(result){
				$("textarea#invoiceForm").val(result.value);
			},
			error : function(xhr){

			}
		});

		$.ajax({
			url : "${api.server}/paymentconfigs/" + paymentConfigId + "/epaymentconfig",
			dataType : "json",
			type : "GET",
			headers: {"groupId": ${groupId}},
			success : function(result){
				$("textarea#epaymentConfig").val(result.value);
			},
			error : function(xhr){

			}
		});
	}

	$("#btn-submit-paymentconfig").click(function(e){
		e.preventDefault();

		var id = $("#itemPaymentConfig").val();
		if(id){
			$.ajax({
				url : "${api.server}/paymentconfigs/" + id,
				dataType : "json",
				type : "PUT",
				headers: {"groupId": ${groupId}},
				data : {
					govAgencyCode  : $("#govAgencyCode").val(),
					govAgencyName : $("#govAgencyCode").text(),
					govAgencyTaxNo : $("#govAgencyTaxNo").val(),
					invoiceTemplateNo : $("#invoiceTemplateNo").val(),
					invoiceIssueNo : $("#invoiceIssueNo").val(),
					invoiceLastNo : $("#invoiceLastNo").val(),
					bankInfo : $("#bankInfo").val(),
				},
				success : function(result){
					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");
				},
				error : function(xhr){
					notification.show({
						message: "Xẩy ra lỗi, vui lòng thử lại"
					}, "error");
				}
			});

			$.ajax({
				url : "${api.server}/paymentconfigs/" + id + "/invoiceform",
				dataType : "json",
				type : "POST",
				headers: {"groupId": ${groupId}},
				data : {
					value: $("textarea#invoiceForm").val()
				},
				success : function(result){
					
				},
				error : function(xhr){
					
				}
			});

			$.ajax({
				url : "${api.server}/paymentconfigs/" + id + "/epaymentconfig",
				dataType : "json",
				type : "POST",
				headers: {"groupId": ${groupId}},
				data : {
					value: $("textarea#epaymentConfig").val()
				},
				success : function(result){
					
				},
				error : function(xhr){
					
				}
			});
		}else{
			$.ajax({
				url : "${api.server}/paymentconfigs",
				dataType : "json",
				type : "POST",
				headers: {"groupId": ${groupId}},
				data : {
					govAgencyCode  : $("#govAgencyCode").val(),
					govAgencyName : $("#govAgencyCode").text(),
					govAgencyTaxNo : $("#govAgencyTaxNo").val(),
					invoiceTemplateNo : $("#invoiceTemplateNo").val(),
					invoiceIssueNo : $("#invoiceIssueNo").val(),
					invoiceLastNo : $("#invoiceLastNo").val(),
					bankInfo : $("#bankInfo").val(),
				},
				success : function(result){
					$.ajax({
						url : "${api.server}/paymentconfigs/" + result.paymentConfigId + "/invoiceform",
						dataType : "json",
						type : "POST",
						headers: {"groupId": ${groupId}},
						data : {
							value: $("textarea#invoiceForm").val()
						},
						success : function(result){
							
						},
						error : function(xhr){
							
						}
					});

					$.ajax({
						url : "${api.server}/paymentconfigs/" + result.paymentConfigId + "/epaymentconfig",
						dataType : "json",
						type : "POST",
						headers: {"groupId": ${groupId}},
						data : {
							value: $("textarea#epaymentConfig").val()
						},
						success : function(result){
							
						},
						error : function(xhr){
							
						}
					});

					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");
				},
				error : function(xhr){
					notification.show({
						message: "Xẩy ra lỗi, vui lòng thử lại"
					}, "error");
				}
			});
		}
	});
</script>
