<#if (Request)??>
<#include "init.ftl">
</#if>
<form id="frmEstablishedProcess">
	<div class="row">
		<div class="col-sm-12 MB15">
			<label>Tên mẫu hồ sơ</label>
			<select class="form-control" id="dossierTemplate" name="dossierTemplate" data-bind="value: dossierTemplate" required="required" validationMessage="Bạn phải chọn mẫu hồ sơ">
			</select>
			<span data-for="dossierTemplate" class="k-invalid-msg"></span>
		</div>

		<div class="col-sm-12 MB15">
			<label>Số thứ tự</label>
			<input name="seqOrder" id="seqOrder" class="form-control" placeholder="Số thứ tự" data-bind="value:seqOrder" >
			<span data-for="seqOrder" class="k-invalid-msg"></span>
		</div>

		<div class="col-sm-12 MB15">
			<label>Tên quy trình xác lập dịch vụ</label>
			<input name="processOptionName" id="processOptionName" class="form-control" placeholder="Tên quy trình xác lập dịch vụ" data-bind="value:processOptionName" validationMessage="Bạn phải điền tên quy trình xác lập" required="required">
			<span data-for="processOptionName" class="k-invalid-msg"></span>
		</div>

		<div class="col-sm-12 MB15">
			<label>Tên quy trình xử lý thủ tục </label>
			<select class="form-control" id="serviceProcess" name="serviceProcess" data-bind="value: serviceProcess" required="required" validationMessage="Bạn phải chọn quy trình xử lý">
			</select>
			<span data-for="serviceProcess" class="k-invalid-msg"></span>
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

		<div class="col-sm-12">
			<span data-toggle="collapse" data-target="#searchDVC"><span class="text-bold">Dịch vụ công</span>................................................................................................................................................................................ <i class="fa fa-chevron-down pull-right MT5" aria-hidden="true"></i></span>
			<div class="collapse in" id="searchDVC">
				<div class="form-group search-icon MT15 PB0">
					<input type="text" name="keywordChoiseDossier" id="keywordChoiseDossier" class="form-control input-sm" placeholder="Nhập từ khóa">
				</div>
				<ul class='ul-with-border MB5'>
					<div id='choiseServiceConfigListView'></div>
				</ul>
				<div id="pagerChoiseServiceConfig"></div>
			</div>
		</div>
		<script type="text/x-kendo-template" id="choiseServiceConfigTemplate">
			<li role="option" aria-selected="true">
				<div class="row">
					<div class="col-sm-12">
						<div class="inline">
							<div class="checkbox"> <input type="checkbox"  class="cbxServiceConfig" data-pk="#:id#"> <label>&nbsp;</label> #:serviceName#</div>
							<div class="ML25">
								<i class="fa fa-university"></i> <span class="ML5">#:govAgencyName#</span>

								<span class="pull-right ">Mức độ <span >#:serviceLevel#</span></span>
							</div>
						</div>
					</div>
				</div>
			</li>
		</script>

		<div class="col-sm-12 text-center MT15 MB15">
			<button class="btn btn-active" id="btn-submit-established-process" type="button">Ghi lại</button> <button class="btn" type="button" id="btn-cancel-serviceoptions">Hủy bỏ</button>
		</div>

		<input type="hidden" name="itemServiceConfigOption" id="itemServiceConfigOption">
	</div>
</form>
<script type="text/javascript">
	var arr = [];
	var dataSourceServiceConfigChoise=new kendo.data.DataSource({
		transport:{
			read:function(options){
				$.ajax({
					url:"${api.server}/serviceconfigs",
					dataType:"json",
					type:"GET",
					headers: {"groupId": ${groupId}},
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
			total:"total",
			data:"data",
			model:{
				id:"serviceConfigId"
			}
		},
		error: function(e) {
			this.cancelChanges();
		},
		autoSync: false,
		pageSize:5,
		serverPaging:false,
		serverSorting:false,
		serverFiltering:false
	});

	var checkedForServiceConfig = function(arr){
		if(arr && arr.length > 0){
			for(var i=0;i<arr.length;i++){
				console.log($('input[data-pk='+arr[i]+']'));
				$('input[data-pk='+arr[i]+']').prop('checked', true);
			}
		}
	}

	$("#choiseServiceConfigListView").kendoListView({
		dataSource : dataSourceServiceConfigChoise,
		template : kendo.template($("#choiseServiceConfigTemplate").html()),
		selectable : true,
		remove : function(e){
			if(!confirm('Bạn có muốn xóa ?')){
				e.preventDefault();
			}
		},
		dataBound: function() {
			checkedForServiceConfig(arr);
		}
	});

	$("#pagerChoiseServiceConfig").kendoPager({
		dataSource : dataSourceServiceConfigChoise,
		input : true,
		numeric : false,
		messages : {
			empty : "Không có kết quả phù hợp!",
			display : "Hiển thị {0}-{1} trong {2} bản ghi",
			page : "",
			of : "/ {0}"
		}
	});

	$("#keywordChoiseDossier").kendoAutoComplete({
		dataTextField : "serviceName",
		dataSource: {
			transport : {
				read : {
					url : "${api.server}/serviceconfigs",
					dataType : "json",
					type : "GET",
					headers: {"groupId": ${groupId}},
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
					id : "serviceConfigId"
				}
			}
		},
		filter: "contains",
		placeholder: "Nhập từ khóa"
	});

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

	$("#btn-cancel-serviceoptions").click(function(){
		console.log("list-serviceconfig-option");
		
	});

	var pullServiceConfigOptionDetail= function(item){
		console.log("detail_serviceconfig");
		if(item){
			var viewModel = kendo.observable({
				dossierTemplate : item.dossierTemplateId,
				serviceProcess : item.serviceProcessId,
				seqOrder : result.seqOrder,
				processOptionName : result.processOptionName,
				instructionNote : function(e){
					$("#instructionNote").summernote('code', item.instructionNote);
				},
				submissionNote : function(e){
					$("#submissionNote").summernote('code', item.submissionNote);
				},
				autoSelect : item.autoSelect,
				serviceConfigs : function(){
					checkedForServiceConfig(item.serviceConfigs);
					arr = item.serviceConfigs;
				}
			});
			
			kendo.bind($("#frmEstablishedProcess"), viewModel);
		}
	}

	$(document).on("change", ".cbxServiceConfig", function () {
		if($(this).prop("checked")){
			arr.push($(this).attr("data-pk"));
		}
	});

	var removeCoincident = function(arrS){
		var arrLength = arrS.length;
		var arrR = [];
		for( var i = 0; i < arrLength; i++ ) {
			if( arrR.indexOf(arrS[i] ) < 0) {
				arrR.push( arrS[i] );
			}
		}
		return arrR;
	}

	$("#btn-submit-established-process").click(function(){
		var id = $("#itemServiceConfigOption").val();
		var idServiceConfig = $("#itemServiceConfigId").val();
		var validator = $("#frmEstablishedProcess").kendoValidator().data("kendoValidator");
		var checkSuccess = 0;
		if(validator.validate()){
			var arrServiceConfig = removeCoincident(arr);
			for(var i=0;i< arrServiceConfig.length;i++){
				if(arrServiceConfig[i]){
					if(id){
						$.ajax({
							url : "${api.server}/serviceconfigs/"+arrServiceConfig[i]+"/processes/"+id,
							dataType : "json",
							type : "PUT",
							headers: {"groupId": ${groupId}},
							async : false,
							data : {
								dossierTemplateId : $("#dossierTemplate").val(),
								serviceProcessId : $("#serviceProcess").val(),
								instructionNote : $("#instructionNote").summernote("code").toString(),
								submissionNote :$("#submissionNote").summernote("code").toString(),
								autoSelect : $("#autoSelect").val()
							},
							success : function(result){
								checkSuccess ++;

							},
							error : function(xhr){

							}
						});
					}else {
						$.ajax({
							url : "${api.server}/serviceconfigs/"+arrServiceConfig[i]+"/processes",
							dataType : "json",
							type : "POST",
							headers: {"groupId": ${groupId}},
							async : false,
							data : {
								dossierTemplateId : $("#dossierTemplate").val(),
								serviceProcessId : $("#serviceProcess").val(),
								instructionNote : $("#instructionNote").summernote("code").toString(),
								submissionNote : $("#submissionNote").summernote("code").toString(),
								autoSelect : $("#autoSelect").val()
							},
							success : function(result){
								checkSuccess ++;

							},
							error : function(xhr){

							}
						});

					}
				}
			}
			
			if(checkSuccess === arrServiceConfig.length){
				notification.show({
					message: "Yêu cầu được thực hiện thành công"
				}, "success");
			}else{
				var message = checkSuccess + " Yêu cầu được thực hiện thành công "+ (arrServiceConfig.length-checkSuccess) +" Yêu cầu thất bại"
				notification.show({
					message: message
				}, "error");
			}

		}

	});

	var addServiceConfigOptionIfSuccess = function(result){
		dataSourceServiceOption.add({
			"dossierTemplate":result.dossierTemplate,
			"serviceProcess":result.serviceProcess,
			"instructionNote":result.instructionNote,
			"submissionNote":result.submissionNote,
			"autoSelect" : result.autoSelect
		});
	};

	var updateServieConfigoptionIfSuccess = function(dataPk,result){
		dataSourceServiceOption.fetch(function(){
			if(dataPk>0){
				var item=dataSourceServiceOption.get(dataPk);
				item.set("dossierTemplate",result.dossierTemplate);
				item.set("serviceProcess",result.serviceProcess);
				item.set("instructionNote",result.instructionNote);
				item.set("submissionNote",result.submissionNote);
				item.set("autoSelect", result.autoSelect);
			}

		});
	}

</script>
