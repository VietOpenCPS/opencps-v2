<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="nav-tabs-wrapper">
	<ul class="nav nav-tabs" id="serviceinfo-tabstrip">
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
							<input class="form-control" id="domainCode" name="domainCode" required="required" validationMessage="Bạn phải chọn lĩnh vực" data-bind="value: domainCode"/>
						</div>
						<span data-for="domain" class="k-invalid-msg"></span>
					</div>
					<div class="col-sm-12">
						<div class="form-group">
							<label>Tên thủ tục</label>
							<input name="service" id="service" class="form-control" placeholder="Tên thủ tục" data-bind="value:serviceName" validationMessage="Bạn phải chọn thủ tục hành chính" required="required"> 
						</div>
						<span data-for="service" class="k-invalid-msg"></span>
					</div>
					<div class="col-sm-6 MB15">
						<label>Cơ quan thực hiện</label>
						<select class="form-control" id="govAgency" name="govAgency" data-bind="value: govAgencyName" required="required" validationMessage="Bạn phải chọn cơ quan thực hiện">
							<option value=""></option>
							<#list administrations as item>
							<option value="${item.id}">${item.name}</option>
							</#list> 
						</select>
						<span data-for="govAgency" class="k-invalid-msg"></span>
					</div>
					<div class="col-sm-6 MB15">
						<label>Mức độ</label>
						<select class="form-control" id="serviceLevel" name="serviceLevel" data-bind="value: serviceLevel" required="required" validationMessage="Bạn phải chọn mức độ">
							<option value=""></option>
							<#list levels as item>
							<option value="${item.id}">${item.name}</option>
							</#list> 
						</select>
						<span data-for="serviceLevel" class="k-invalid-msg"></span>
					</div>
					<div class="col-sm-12 MB15">
						<label>Quy trình thực hiện dịch vụ</label>
						<select class="form-control" id="process" name="process" data-bind="value: processName" required="required" validationMessage="Bạn phải chọn quy trình">
							<option value=""></option>
							<#list levels as item>
							<option value="${item.id}">${item.name}</option>
							</#list> 
						</select>
						<span data-for="process" class="k-invalid-msg"></span>
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
							<textarea class="form-control" rows="3" id="address" name="address" data-bind="text:address" ></textarea>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="checkbox"> <input type="checkbox" name="forCitizen" id="forCitizen" data-bind="checked : forCitizen"> <label>Công dân nộp hồ sơ</label> </div>
					</div>
					<div class="col-sm-6">
						<div class="checkbox"> <input type="checkbox" name="postService" id="postService" data-bind="checked : postService"> <label>Áp dụng phương thức nộp bưu điện</label> </div>
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


		</div>
	</div>
</div>

<script type="text/javascript">
	
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
			success : function(result){
				console.log(result);
				var viewModel = kendo.observable({
					serviceCode: result.serviceCode,
					serviceName: result.serviceName,
					domainCode: result.domainCode,
					domainName: result.domainName,
					govAgencyCode: result.govAgencyCode,
					govAgencyName: result.govAgencyName,
					serviceInstruction: result.serviceInstruction,
					serviceLevel: function(e){
						return "Mức độ "+result.serviceLevel;
					},
					serviceUrl: result.serviceUrl,
					forCitizen: result.forCitizen,
					forBusiness: result.forBusiness,
					postService: result.postService,
					registration: result.registration
				});

				kendo.bind($("#frmServiceConfigDetail"), viewModel);
			},
			error : function(xhr){

			}
		});
	}

	$("#btn-submit-serviceconfig").click(function(){
		var id =$("#itemServiceConfigId").val();
		var validator = $("#frmServiceConfigDetail").kendoValidator().data("kendoValidator");
		if(validator.validate()){
			if(id && id >0){
				$.ajax({
					url : "${api.server}/serviceconfigs/"+id,
					dataType : "json",
					type : "PUT",
					data : {
						domainCode : $("#domainCode").val(),
						serviceInfoId : $("#service").val(),
						govAgencyCode : $("#govAgency").val(),
						serviceLevel :$("#serviceLevel").val(),
						process : $("#process").val(),
						serviceInstruction :$("#serviceInstruction").val(),
						address : $("#address").val(),
						forCitizen : $("#forCitizen").val(),
						postalService : $("#postService").val(),
						forBusiness : $("#forBusiness").val(),
						registration : $("#registration").val()
					},
					success : function(result){
						updateServieConfigIfSuccess(id,result);
					},
					error : function(xhr){

					}
				});
			}else {
				$.ajax({
					url : "${api.server}/serviceconfigs",
					dataType : "json",
					type : "POST",
					data : {
						domainCode : $("#domainCode").val(),
						serviceInfoId : $("#service").val(),
						govAgencyCode : $("#govAgency").val(),
						serviceLevel :$("#serviceLevel").val(),
						process : $("#process").val(),
						serviceInstruction :$("#serviceInstruction").val(),
						address : $("#address").val(),
						forCitizen : $("#forCitizen").val(),
						postalService : $("#postService").val(),
						forBusiness : $("#forBusiness").val(),
						registration : $("#registration").val()
					},
					success : function(result){
						addServiceConfigIfSuccess(result);
						$("#itemServiceConfigId").val(result.serviceConfigId);
						funcCheckValueItem();
					},
					error : function(xhr){

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
				item.set("serviceName",result.serviceName);
				item.set("domainCode",result.domainCode);
				item.set("domainName",result.domainName);
				item.set("govAgencyCode",result.govAgencyCode);
				item.set("govAgencyName",result.govAgencyName);
				item.set("serviceInstruction",result.serviceInstruction);
				item.set("serviceLevel",result.serviceLevel);
				item.set("serviceUrl",result.serviceUrl);
				item.set("forCitizen",result.forCitizen);
				item.set("forBusiness",result.forBusiness);
				item.set("postService",result.postService);
				item.set("registration",result.registration);
			}

		});
	}
	var addServiceConfigIfSuccess = function(result){
		dataSourceServiceConfig.add({
			"serviceConfigId" : result.serviceConfigId,
			"serviceInfoId" : result.serviceInfoId,
			"serviceCode" : result.serviceCode,
			"serviceName" : result.serviceName,
			"domainCode" : result.domainCode,
			"domainName" : result.domainName,
			"govAgencyCode" : result.govAgencyCode,
			"govAgencyName" : result.govAgencyName,
			"serviceInstruction" : result.serviceInstruction,
			"serviceLevel" : result.serviceLevel,
			"serviceUrl" : result.serviceUrl,
			"forCitizen" : result.forCitizen,
			"forBusiness" : result.forBusiness,
			"postService" : result.postService,
			"registration" : result.registration
		});	
	};

	$("#serviceInstruction").summernote({
		height: 150
	});
	$("#serviceLevel").kendoComboBox({
		placeholder : "Chọn mức độ",
		dataTextField:"processName",
		dataValueField:"processNo",
		noDataTemplate: 'Không có dữ liệu',
		filter: "contains",
		/*dataSource:{
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
		}*/
		dataSource : []
	});

	$("#govAgency").kendoComboBox({
		placeholder : "Chọn cơ quan",
		dataTextField:"processName",
		dataValueField:"processNo",
		noDataTemplate: 'Không có dữ liệu',
		filter: "contains",
		/*dataSource:Ơ
			transport:Ơ
				read:Ơ
					url:"${api.server}/serviceprocesses",
					dataType:"json",
					type:"GET",
					success:function(result)Ơ

					Ư,
					ẻo:function(result)Ơ

					Ư
				Ư
			Ư,
			schema:Ơ
				dât:"dât",
				total:"total",
				model:Ơ
					id:"processNo"
				Ư
			Ư
			Ư*/
			dataSource : []
		});
	
	$("#process").kendoComboBox({
		placeholder : "Chọn tiến trình",
		dataTextField:"processName",
		dataValueField:"processNo",
		noDataTemplate: 'Không có dữ liệu',
		filter: "contains",
		/*dataSource:{
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
		}*/
		dataSource : []
	});


	$("#domainCode").kendoComboBox({
		placeholder : "Chọn lĩnh vực",
		dataTextField:"processName",
		dataValueField:"processNo",
		noDataTemplate: 'Không có dữ liệu',
		filter: "contains",
		/*dataSource:{
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
		}*/
		dataSource : []
	});

	$("#service").kendoComboBox({
		placeholder : "Chọn thủ tục",
		dataTextField:"processName",
		dataValueField:"processNo",
		noDataTemplate: 'Không có dữ liệu',
		filter: "contains",
		/*dataSource:{
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
		}*/
		dataSource : []
	});
</script>