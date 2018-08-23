<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row">

	<div class="col-sm-12 MB15">
		<label>Quy trình thủ tục</label>
		<select class="form-control" id="serviceProcess" name="serviceProcess" data-bind="value: serviceProcess" required="required" validationMessage="Bạn phải chọn quy trình xử lý">
		</select>
		<span data-for="serviceProcess" class="k-invalid-msg"></span>
	</div>

	<div class="col-sm-12">
		<div class="form-group">
			<label>Lĩnh vực thủ tục</label>
			<input class="form-control" id="domainCode" name="domainCode" data-bind="value: domainCode"/>
		</div>
		<span data-for="domain" class="k-invalid-msg"></span>
	</div>

	<div class="col-sm-12">
		<div class="form-group">
			<label>Tên thủ tục</label>
			<input name="service" id="service" class="form-control" placeholder="Tên thủ tục" data-bind="value:serviceInfoId" validationMessage="Bạn phải chọn thủ tục hành chính" required="required"> 
		</div>
		<span data-for="service" class="k-invalid-msg"></span>
	</div>

	<div class="col-sm-12 MB15" style="height: 150px; overflow: scroll; border: 1px solid #ccc;">
		<label>Cơ quan thực hiện</label>
		<div class="checkbox"> 
			<input type="checkbox" name="cbxAllGovAll" id="cbxAllGovAll"> 
			<label>Chọn tất cả</label> 
		</div>
		<div id="lsGovAgency">
			
		</div>
		<script type="text/x-kendo-template" id="cbxGovAgencyTemplate">
			<div class="checkbox"> 
				<input type="checkbox" name="cbxAllGov" id="cbxAllGov" data-pk="#:id#"> 
				<label>#:itemName#</label> 
			</div>
		</script>
		<span data-for="govAgency" class="k-invalid-msg"></span>
	</div>

	<div class="col-sm-12 MB15">
		<label>Mức độ</label>
		<select class="form-control" id="serviceLevel" name="serviceLevel" data-bind="value: serviceLevel" required="required" validationMessage="Bạn phải chọn mức độ">
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
			<textarea class="form-control" rows="3" id="serviceUrl" name="serviceUrl" data-bind="text:serviceUrl" ></textarea>
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

	<div class="col-sm-12 MB15">
		<label>Tên quy trình xác lập dịch vụ</label>
		<input name="processOptionName" id="processOptionName" class="form-control" placeholder="Tên quy trình xác lập dịch vụ" data-bind="value:processOptionName" validationMessage="Bạn phải điền tên quy trình xác lập" required="required">
		<span data-for="processOptionName" class="k-invalid-msg"></span>
	</div>

	<div class="col-sm-12 MB15">
		<label>Tên mẫu hồ sơ</label>
		<select class="form-control" id="dossierTemplate" name="dossierTemplate" data-bind="value: dossierTemplate" required="required" validationMessage="Bạn phải chọn mẫu hồ sơ">
		</select>
		<span data-for="dossierTemplate" class="k-invalid-msg"></span>
	</div>

	<div class="col-sm-12 MB15">
		<label>Chuỗi pattern xác định việc lựa chọn tự động theo đối tượng xử lý hồ sơ</label>
		<input name="autoSelect" id="autoSelect" class="form-control" placeholder="Chuỗi pattern" data-bind="value:autoSelect" validationMessage="Bạn phải điền chuỗi pattern" required="required">
		<span data-for="autoSelect" class="k-invalid-msg"></span>
	</div>

	<div class="col-sm-12 ">
		<div class="form-group">
			<label class="control-label">Hướng dẫn chi tiết để chuẩn bị hồ sơ
			</label>
			<div class="" id="instructionNote" name="instructionNote" validationMessage="Bạn phải điền hướng dẫn chi tiết" required="required" data-bind="value: instructionNote"></div>
			<span data-for="instructionNote" class="k-invalid-msg"></span>
		</div>
	</div>

	<div class="col-sm-12 ">
		<div class="form-group">
			<label class="control-label">Hướng dẫn sau khi nộp hồ sơ
			</label>
			<div class="" id="submissionNote" name="submissionNote" validationMessage="Bạn phải điền hướng dẫn sau khi nộp hồ sơ" required="required" data-bind="value: submissionNote"></div>
			<span data-for="submissionNote" class="k-invalid-msg"></span>
		</div>
	</div>

	<div class="col-sm-12 text-center MT10 MB15">
		<button class="btn btn-active" id="btn-submit-serviceconfigs" type="button">Ghi lại</button> 
		<button class="btn" type="reset">Hủy bỏ</button> 
	</div>
</div>

<script type="text/javascript">

	var cbxGovAgencyTemplate = kendo.template($("#cbxGovAgencyTemplate").html());
	var dataSourceGovAgency =  new kendo.data.DataSource({
		transport : {
			read : {
				url : "${api.server}/dictcollections/GOVERNMENT_AGENCY/dictitems",
				dataType : "json",
				type : "GET",
				header : {"groupId" , ${groupId}},
				success : function(result){

				},
				error : function(xhr){

				}
			}
		},
		schema : {
			total : "total",
			data : "data",
			model : {
				id : "itemCode"
			}
		},
		change : function(){
			$("#lsGovAgency").html(kendo.render(cbxGovAgencyTemplate, this.view()));
		}
	});
	dataSourceGovAgency.read();

	$("#dossierTemplate").kendoComboBox({
		placeholder : "Chọn mẫu hồ sơ",
		dataTextField:"templateName",
		dataValueField:"dossierTemplateId",
		noDataTemplate: 'Không có dữ liệu',
		filter: "contains",
		dataSource:{
			transport:{
				read:{
					url:"${api.server}/dossiertemplates",
					dataType:"json",
					type:"GET",
					headers: {"groupId": ${groupId}},
					success:function(result){

					},
					error:function(result){

					}
				}
			},
			schema:{
				data:"data",
				total:"total"			
			}
		}
	});

	$("#serviceProcess").kendoComboBox({
		placeholder : "Chọn tiến trình",
		dataTextField:"processName",
		dataValueField:"serviceProcessId",
		noDataTemplate: 'Không có dữ liệu',
		filter: "contains",
		dataSource:{
			transport:{
				read:{
					url:"${api.server}/serviceprocesses",
					dataType:"json",
					type:"GET",
					headers: {"groupId": ${groupId}},
					success:function(result){

					},
					error:function(result){

					}
				}
			},
			schema:{
				data:"data",
				total:"total"
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
			$("#service").data("kendoComboBox").dataSource.read({
				domain : value
			});
		}
	});

	$("#service").kendoComboBox({
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
				total:"total"
			}
		},
		change : function(e){
			var domainCode = $("#service").data("kendoComboBox").dataItem().domainCode;
			if(domainCode){
				$("#domainCode").data("kendoComboBox").value(domainCode);
				$("#domainCode").data("kendoComboBox")._isSelect = false;
			}
		}
	});

	$("#instructionNote").summernote({
		height : 150,
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

	$("#submissionNote").summernote({
		height : 150,
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

	$("#btn-submit-serviceconfigs").click(function(){
		var arrGov = getArrGovAgency();
		for(var i=0; i<arrGov.length; i++){
			$.ajax({
				url : "${api.server}/serviceconfigs",
				dataType : "json",
				type : "POST",
				headers: {"groupId": ${groupId}},
				data : {
					serviceInfoId : $("#service").val(),
					govAgencyCode : arrGov[i],
					serviceLevel :$("#serviceLevel").val(),
					process : $("#process").val(),
					serviceInstruction :$("#serviceInstruction").val(),
					serviceUrl : $("textarea#serviceUrl").val(),
					forCitizen : $("#forCitizen").val(),
					postalService : $("#postService").val(),
					forBusiness : $("#forBusiness").val(),
					registration : $("#registration").val()
				},
				success : function(result){
					createProcessForServiceConfig(result.serviceConfigId);
				},
				error : function(xhr){
					
				}	
			});
		}
		
	});

	var createProcessForServiceConfig = function(id){
		if (id) {
			$.ajax({
				url : "${api.server}/serviceconfigs/"+id+"/processes",
				dataType : "json",
				type : "POST",
				header : {"groupId" : ${groupId}},
				data : {

				},
				success : function(result){

				},
				error : function(xhr){

				}
			});
		}
	}

	var getArrGovAgency = function(){
		var arrR = new Array();
		$('.cbxAllGov').each(function(){
			var id = $(this).attr("data-pk");
			if(id){
				arrR.push(id);
			}
		});
		console.log(arrR);
		return arrR;
	}
</script>