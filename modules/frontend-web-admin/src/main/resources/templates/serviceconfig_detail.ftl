<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="nav-tabs-wrapper">
	<ul class="nav nav-tabs" id="serviceconfig-tabstrip">
		<li class="active"  value="1">
			<a data-toggle="tab" href="#ttdvc">
				Thông tin dịch vụ công
			</a>
		</li>
		<li class="" value="2">
			<a data-toggle="tab" href="#xlqtdv">
				Xác lập quy trình thực hiện dịch vụ
			</a>
		</li>
	</ul>
	<div id="dataDetailServiceInfo" class="tab-content">
		<div id="ttdvc" class="tab-pane fade in active MT5">
			<form id="frmServiceConfigDetail">
				<div class="row">
					<div class="col-sm-12">
						<div class="form-group">
							<label>Lĩnh vực thủ tục</label>
							<input class="form-control" id="domainCode" name="domainCode" data-bind="value: domainCode"/>
						</div>
						<span data-for="domain" class="k-invalid-msg"></span>
					</div>
					<div class="col-sm-12">
						<div class="form-group">
							<label>Tên thủ tục (<span class="red">*</span>)</label>
							<input name="serviceInfoSc" id="serviceInfoSc" class="form-control" placeholder="Tên thủ tục" data-bind="value:serviceInfoId" validationMessage="Bạn phải chọn thủ tục hành chính" required="required"> 
						</div>
						<span data-for="serviceInfoSc" class="k-invalid-msg"></span>
					</div>
					<div class="col-sm-6 MB15">
						<label>Cơ quan thực hiện (<span class="red">*</span>)</label>
						<select class="form-control" id="govAgency" name="govAgency" data-bind="value: govAgencyCode" required="required" validationMessage="Bạn phải chọn cơ quan thực hiện">
							
						</select>
						<span data-for="govAgency" class="k-invalid-msg"></span>
					</div>
					<div class="col-sm-6 MB15">
						<label>Mức độ (<span class="red">*</span>)</label>
						<select class="form-control" id="serviceLevel" name="serviceLevel" data-bind="value: serviceLevel" required="required" validationMessage="Bạn phải chọn mức độ">
							<option value="2">Mức độ 2</option>
							<option value="3">Mức độ 3</option>
							<option value="4">Mức độ 4</option>
						</select>
						<span data-for="serviceLevel" class="k-invalid-msg"></span>
					</div>
					<div class="col-sm-12 ">
						<div class="form-group">
							<label class="control-label">Hướng dẫn làm thủ tục hành chính tại cơ quan 
							</label> 
							<div class="" id="serviceInstruction" name="serviceInstruction" data-bind="text:serviceInstruction" required="required" validationMessage="Bạn phải điền hướng dẫn"></div>
						</div>
						<span data-for="serviceInstruction" class="k-invalid-msg"></span>
					</div>
					<div class="col-sm-12 MB15">
						<div class="form-group">
							<label class="control-label">Địa chỉ nộp hồ sơ trực tuyến: 
							</label> 
							<textarea class="form-control" rows="3" id="serviceUrl" name="serviceUrl" data-bind="text:serviceUrl" placeholder="Đường dẫn dạng : https://www.abc.com"></textarea>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="checkbox"> <input type="checkbox" name="forCitizen" id="forCitizen" data-bind="checked : forCitizen"> <label>Công dân nộp hồ sơ</label> </div>
					</div>
					<div class="col-sm-6">
						<div class="checkbox"> <input type="checkbox" name="postalService" id="postalService" data-bind="checked : postalService"> <label>Áp dụng phương thức nộp bưu điện</label> </div>
					</div>
					<div class="col-sm-6">
						<div class="checkbox"> <input type="checkbox" name="forBusiness" id="forBusiness" data-bind="checked : forBusiness"> <label>Doanh nghiệp nộp hồ sơ</label> </div>
					</div>
					<div class="col-sm-6">
						<div class="checkbox"> <input type="checkbox" name="registration" id="registration" data-bind="checked: registration"> <label>Yêu cầu đăng ký hồ sơ thương nhân</label> </div>
					</div>
					<div class="col-sm-12 text-center MT15 MB15">
						<button class="btn btn-active" id="btn-submit-serviceconfig" type="button">Ghi lại</button> <button class="btn" type="reset">Hủy bỏ</button> 
					</div>
				</div>
			</form>
		</div>
		<div id="xlqtdv" class="tab-pane fade in MT5">
			<#include "serviceconfig_option.ftl">
		</div>
	</div>
</div>

<script type="text/javascript">
	$("#serviceInstruction").summernote({
		height: 150,
		toolbar: [
		['style', ['bold', 'italic', 'underline', 'clear']],
		['font', ['strikethrough', 'superscript', 'subscript']],
		['fontsize', ['fontsize']],
		['color', ['color']],
		['para', ['ul', 'ol', 'paragraph']],
		['height', ['height']],
		['table', ['table']],
		['insert', ['link', 'picture' , 'video']]
		]
	});

	$(function() {
		$("[data-role=combobox]").each(function() {
			var widget = $(this).getKendoComboBox();
			widget.input.on("focus", function() {
				widget.open();
			});
		});
	});

	var funcCheckValueItem = function(){
		if($("#itemServiceConfigId").val() != ""){
			$("#xlqtdv").load("${ajax.serviceconfig_option}",function(result){
				dataSourceServiceOption.read();
			});
		}else {
			
		}
	}

	var pullServiceConfigDetail= function(id){
		console.log("detail_serviceconfig");
		$.ajax({
			url : "${api.server}/serviceconfigs/"+id,
			dataType : "json",
			type : "GET",
			headers: {"groupId": ${groupId}},
			success : function(result){
				console.log(result);
				var viewModel = kendo.observable({
					serviceInfoId: result.serviceInfoId,	
					govAgencyCode: result.govAgencyCode,
					serviceLevel: result.serviceLevel,
					serviceUrl: result.serviceUrl,
					domainCode: result.domainCode
				});

				kendo.bind($("#frmServiceConfigDetail"), viewModel);

				$("#forCitizen").prop("checked",result.forCitizen);
				$("#forBusiness").prop("checked",result.forBusiness);
				$("#postalService").prop("checked",result.postalService);
				$("#registration").prop("checked",result.registration);
				$('#serviceInstruction').summernote('code', result.serviceInstruction);
			},
			error : function(xhr){

			}
		});
	}


	$("#btn-submit-serviceconfig").click(function(){
		var id = $("#itemServiceConfigId").val();
		var validator = $("#frmServiceConfigDetail").kendoValidator().data("kendoValidator");
		if(validator.validate()){
			if(id){
				console.log("PUT serviceConfig");
				console.log($("#serviceInfoSc").val());
				$.ajax({
					url : "${api.server}/serviceconfigs/"+id,
					dataType : "json",
					type : "PUT",
					headers: {"groupId": ${groupId}},
					data : {
						serviceInfoId : $("#serviceInfoSc").val(),
						govAgencyCode : $("#govAgency").val(),
						serviceLevel :$("#serviceLevel").val(),
						process : $("#process").val(),
						serviceInstruction :$("#serviceInstruction").summernote('code'),
						serviceUrl : $("textarea#serviceUrl").val(),
						forCitizen : $("#forCitizen").prop( "checked" ),
						postalService : $("#postalService").prop( "checked" ),
						forBusiness : $("#forBusiness").prop( "checked" ),
						registration : $("#registration").prop( "checked" )
					},
					success : function(result){
						updateServieConfigIfSuccess(id,result);
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
			}else {
				console.log("POST serviceConfig");
				console.log($("#serviceInfoSc").val());
				$.ajax({
					url : "${api.server}/serviceconfigs",
					dataType : "json",
					type : "POST",
					headers: {"groupId": ${groupId}},
					data : {
						serviceInfoId : $("#serviceInfoSc").val(),
						govAgencyCode : $("#govAgency").val(),
						serviceLevel :$("#serviceLevel").val(),
						process : $("#process").val(),
						serviceInstruction :$("#serviceInstruction").summernote('code'),
						serviceUrl : $("textarea#serviceUrl").val(),
						forCitizen : $("#forCitizen").prop( "checked" ),
						postalService : $("#postalService").prop( "checked" ),
						forBusiness : $("#forBusiness").prop( "checked" ),
						registration : $("#registration").prop( "checked" )
					},
					success : function(result){
						addServiceConfigIfSuccess(result);
						$("#itemServiceConfigId").val(result.serviceConfigId);
						funcCheckValueItem();
						notification.show({
							message: "Yêu cầu được thực hiện thành công"
						}, "success");
					},
					error : function(xhr){
						if (xhr.responseJSON.description == "ServiceConfigHasExsist"){
							notification.show({
								message: "Dịch vụ công đã tồn tại"
							}, "error");
						} else {
							notification.show({
								message: "Xẩy ra lỗi, vui lòng thử lại"
							}, "error");
						}
					}	
				});
			}
		}
		
	});

	var updateServieConfigIfSuccess = function(dataPk,result){
		console.log(dataPk);
		dataSourceServiceConfig.fetch(function(){
			if(dataPk>0){
				var item=dataSourceServiceConfig.get(dataPk);

				item.set("serviceInfoId",result.serviceInfoId);
				item.set("serviceCode",result.serviceCode);
				item.set("serviceName",$("#serviceInfoSc").data("kendoComboBox").text());
				item.set("domainCode",result.domainCode);
				item.set("domainName",result.domainName);
				item.set("govAgencyCode",result.govAgencyCode);
				item.set("govAgencyName",result.govAgencyName);
				item.set("serviceInstruction",result.serviceInstruction);
				item.set("serviceLevel",result.serviceLevel);
				item.set("serviceUrl",result.serviceUrl);
				item.set("forCitizen",result.forCitizen);
				item.set("forBusiness",result.forBusiness);
				item.set("postalService",result.postalService);
				item.set("registration",result.registration);
			}

		});
	}
	var addServiceConfigIfSuccess = function(result){
		dataSourceServiceConfig.insert(0,{
			"serviceConfigId" : result.serviceConfigId,
			"serviceInfoId" : result.serviceInfoId,
			"serviceCode" : result.serviceCode,
			"serviceName" : $("#serviceInfoSc").data("kendoComboBox").text(),
			"domainCode" : result.domainCode,
			"domainName" : result.domainName,
			"govAgencyCode" : result.govAgencyCode,
			"govAgencyName" : result.govAgencyName,
			"serviceInstruction" : result.serviceInstruction,
			"serviceLevel" : result.serviceLevel,
			"serviceUrl" : result.serviceUrl,
			"forCitizen" : result.forCitizen,
			"forBusiness" : result.forBusiness,
			"postalService" : result.postalService,
			"registration" : result.registration
		});	
	};

	
	$("#serviceLevel").kendoComboBox({
		placeholder : "Chọn mức độ",
		noDataTemplate: 'Không có dữ liệu',
		filter: "contains"
	});

	$("#govAgency").kendoComboBox({
		placeholder : "Chọn cơ quan",
		dataTextField:"itemName",
		dataValueField:"itemCode",
		noDataTemplate: 'Không có dữ liệu',
		filter: "contains",
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
		}
	});


	$("#domainCode").kendoComboBox({
		placeholder : "Chọn lĩnh vực",
		dataTextField:"itemName",
		dataValueField:"itemCode",
		noDataTemplate: 'Không có dữ liệu',
		filter: "contains",
		dataSource : {
			transport : {
				read : {
					url : "${api.server}/dictcollections/SERVICE_DOMAIN/dictitems",
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
			if(value){

				$("#serviceInfoSc").data("kendoComboBox").dataSource.read({
					domain : value
				});

				$("#serviceInfoSc").data("kendoComboBox").select(-1);
			}
			
		}
	});

	$("#serviceInfoSc").kendoComboBox({
		placeholder : "Chọn thủ tục",
		dataTextField:"serviceName",
		dataValueField:"serviceInfoId",
		noDataTemplate: 'Không có dữ liệu',
		filter: "contains",
		dataSource:{
			transport:{
				read:function(options) {
					$.ajax({
						url:"${api.server}/serviceinfos",
						dataType:"json",
						type:"GET",
						headers: {"groupId": ${groupId}},
						data : {
							domain : options.data.domain
						},
						success:function(result){
							options.success(result);
						},
						error:function(result){
							options.error(result);
						}
					});
				}
			},
			schema:{
				data:"data",
				total:"total",
				model : {
					id : "serviceInfoId",
					fields : {
						serviceName : {
							type: "string"
						}
					}
				}
			}
		},
		change : function(e){
			console.log(this.value());
			try {
				var domainCode = $("#serviceInfoSc").data("kendoComboBox").dataItem().domainCode;
				console.log(domainCode);
				if(domainCode){
					$("#domainCode").data("kendoComboBox").value(domainCode);
					$("#domainCode").data("kendoComboBox")._isSelect = false;
				}
			}catch (e) {
				console.log(e);
			}
			
		},
		dataBound : function(){
			$(".k-clear-value").addClass("k-hidden")
		}
	});
</script>