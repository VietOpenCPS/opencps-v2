<#if (Request)??>
<#include "init.ftl">
</#if>
<form id="frmEstablishedProcess">
	<div class="row">
		<div class="col-sm-12 MB15">
			<label>Tên mẫu hồ sơ</label>
			<input class="form-control" id="dossierTemplate" name="dossierTemplate" required="required" validationMessage="Bạn phải chọn mẫu hồ sơ" required="required"  />
			<span data-for="dossierTemplate" class="k-invalid-msg"></span>
		</div>

		<div class="col-sm-12 MB15">
			<label>Tên quy trình xử lý thủ tục</label>
			<input name="serviceProcess" id="serviceProcess" class="form-control" placeholder="Tên thủ tục" data-bind="value:serviceProcess" validationMessage="Bạn phải chọn quy trình xử lý" required="required">
			<span data-for="serviceProcess" class="k-invalid-msg"></span>
		</div>

		<div class="col-sm-12 MB15">
			<label>Chuỗi pattern xác định việc lựa chọn tự động theo đối tượng xử lý hồ sơ</label>
			<input name="pattern" id="pattern" class="form-control" placeholder="Chuỗi pattern" data-bind="value:pattern" validationMessage="Bạn phải điền chuỗi pattern" required="required">
			<span data-for="pattern" class="k-invalid-msg"></span>
		</div>

		<div class="col-sm-12 ">
			<div class="form-group">
				<label class="control-label">Hướng dẫn chi tiết để chuẩn bị hồ sơ
				</label>
				<div class="" id="instructionNote" name="instructionNote" validationMessage="Bạn phải điền hướng dẫn chi tiết" required="required"></div>
				<span data-for="instructionNote" class="k-invalid-msg"></span>
			</div>
		</div>

		<div class="col-sm-12 ">
			<div class="form-group">
				<label class="control-label">Hướng dẫn sau khi nộp hồ sơ
				</label>
				<div class="" id="submissionNote" name="submissionNote" validationMessage="Bạn phải điền hướng dẫn sau khi nộp hồ sơ" required="required"></div>
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
			<button class="btn btn-active" id="btn-submit-established-process" type="button">Ghi lại</button> <button class="btn" type="button" id="btn-revert-serviceoptions">Hủy bỏ</button>
		</div>

		<input type="hidden" name="itemServiceConfigOption" id="itemServiceConfigOption">
	</div>
</form>
<script type="text/javascript">
	var arr = [301,302,303];
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

	$("#instructionNote").summernote({
		height : 150
	});

	$("#submissionNote").summernote({
		height : 150
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
					headers: {"groupId": ${groupId}},
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

	$("#serviceProcess").kendoComboBox({
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
					headers: {"groupId": ${groupId}},
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

	$("#instructionNote").summernote({
		height : 150
	});
	$("#submissionNote").summernote({
		height : 150
	});

	$("#btn-revert-serviceoptions").click(function(){
		console.log("list-serviceconfig-option");
		$("#xlqtdv").load("${ajax.serviceconfig_option}",function(result){

		});
	});

	var pullServiceConfigOptionDetail= function(item){
		console.log("detail_serviceconfig");
		if(item){
			var viewModel = kendo.observable({
				dossierTemplate : item.dossierTemplateId,
				serviceProcess : item.processName,
				instructionNote : item.instructionNote,
				submissionNote : item.submissionNote,
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
			addServiceConfigForServiceOption($(this).attr("data-pk"));
		}else{
			removeServiceConfigForServiceOption($(this).attr("data-pk"));
		}
	});

	var addServiceConfigForServiceOption = function(id){
		console.log(arr);
		if(arr){
			for(var i=0 ;i< arr.length ;i++){
				if(arr[i] === id){
					return ;
				}
			}
			arr.push(id);
		}
	}

	var removeServiceConfigForServiceOption = function(id){
		console.log(arr);
		if(arr){
			for(var i=0 ;i< arr.length ;i++){
				if(arr[i] === id){
					arr.splice(i,1);
					return ;
				}
			}
		}
	}

	$("#btn-submit-established-process").click(function(){
		var id = $("#itemServiceConfigOption").val();
		var idServiceConfig = $("#itemServiceConfigId").val();
		var validator = $("#frmEstablishedProcess").kendoValidator().data("kendoValidator");
		if(validator.validate()){
			if(idServiceConfig){
				if(id){
					$.ajax({
						url : "${api.server}/serviceconfigs/"+idServiceConfig+"/processes/"+id,
						dataType : "json",
						type : "PUT",
						headers: {"groupId": ${groupId}},
						data : {
							dossierTemplate : $("#dossierTemplate").val(),
							serviceProcess : $("#serviceProcess").val(),
							instructionNote : $("#instructionNote").val(),
							submissionNote :$("#submissionNote").val(),
							pattern : $("#pattern").val()
						},
						success : function(result){
							updateServieConfigoptionIfSuccess(id,result);
						},
						error : function(xhr){

						}
					});

				}else {
					$.ajax({
						url : "${api.server}/serviceconfigs/"+idServiceConfig+"/processes",
						dataType : "json",
						type : "POST",
						headers: {"groupId": ${groupId}},
						data : {
							dossierTemplate : $("#dossierTemplate").val(),
							serviceProcess : $("#serviceProcess").val(),
							instructionNote : $("#instructionNote").val(),
							submissionNote : $("#submissionNote").val(),
							pattern : $("#pattern").val()
						},
						success : function(result){
							addServiceConfigOptionIfSuccess(result);
						},
						error : function(xhr){

						}
					});
					
				}
			}
		}


	});

	var addServiceConfigOptionIfSuccess = function(result){
		dataSourceServiceOption.add({
			"dossierTemplate":result.dossierTemplate,
			"serviceProcess":result.serviceProcess,
			"instructionNote":result.instructionNote,
			"submissionNote":result.submissionNote,
			"pattern" : result.pattern
		});
	};

	var updateServieConfigoptionIfSuccess = function(dataPk,result){
		console.log(dataPk);
		dataSourceServiceOption.fetch(function(){
			if(dataPk>0){
				var item=dataSourceServiceOption.get(dataPk);
				item.set("dossierTemplate",result.dossierTemplate);
				item.set("serviceProcess",result.serviceProcess);
				item.set("instructionNote",result.instructionNote);
				item.set("submissionNote",result.submissionNote);
				item.set("pattern", result.pattern);
			}

		});
	}

</script>
