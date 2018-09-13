<#if (Request)??>
<#include "init.ftl">
</#if>
<ul class="mimic-table">
	<li class="clearfix">
		<div class="col-sm-1 text-center">
			<b>STT</b>
		</div>
		<div class="col-sm-5">
			<b>Tên quy trình xác lập dịch vụ</b>
		</div>
		<div class="col-sm-2 text-center">
			<b>Mã mẫu hồ sơ</b>
		</div>
		<div class="col-sm-2 text-center">
			<b>Mã quy trình</b>
		</div>
		<div class="col-sm-2 text-center">
			<b>Hành động</b>
		</div>
	</li> 

</ul>
<ul class='mimic-table' id="serviceConfigOptionListView">

</ul>
<div id='pagerServiceConfigOption'></div>
<script type="text/x-kendo-template" id="serviceConfigOptionTemplate">
	<li class="clearfix eq-height-lg" data-pk="#:id#" style="padding: 10px 0 10px 5px;" role="option" aria-selected="true">
		<div class="col-sm-1 text-center">
			<span>#:itemIndex#</span>
		</div>
		<div class="col-sm-5">
			<a href="javascript:;" data-pk="#:id#" class="_itemServiceConfig_option_btnEdit text-hover-blue"><span>#:optionName#</span></a>
		</div>
		<div class="col-sm-2 text-center">
			<a href="javascript:;" data-pk="#:id#" class="_itemServiceConfig_option_btnEdit text-hover-blue"><span>#:templateNo#</span></a>
		</div>
		<div class="col-sm-2 text-center">
			<a href="javascript:;" data-pk="#:id#" class="_itemServiceConfig_option_btnEdit text-hover-blue"><span>#:processNo#</span></a>
		</div>
		<div class="col-sm-2 text-center">
			<a href="javascript:;" data-pk="#:id#" class="_itemServiceConfig_option_btnDelete text-hover-blue">
				<i class="fa fa-trash"></i>
			</a>
		</div>
	</li>
</script>

<div class="col-sm-12 text-right MB20">
	<button class="btn btn-active" type="button" id="btn-add-serviceconfig-option"><i class="fa fa-plus" aria-hidden="true"></i> Thêm quy trình dịch vụ</button>
</div>

<form id="frmEstablishedProcess" style="display: none;">
	<div class="row">

		<div class="col-sm-12 MB15">
			<label>Số thứ tự</label>
			<input name="seqOrder" id="seqOrder" class="form-control" placeholder="Số thứ tự" data-bind="value:seqOrder" >
			<span data-for="seqOrder" class="k-invalid-msg"></span>
		</div>

		<div class="col-sm-12 MB15">
			<label>Tên quy trình xác lập dịch vụ (<span class="red">*</span>)</label>
			<input name="optionName" id="optionName" class="form-control" placeholder="Tên quy trình xác lập dịch vụ" data-bind="value:optionName" validationMessage="Bạn phải điền tên quy trình xác lập" required="required">
			<span data-for="optionName" class="k-invalid-msg"></span>
		</div>

		<div class="col-sm-12 MB15">
			<label>Tên mẫu hồ sơ (<span class="red">*</span>)</label>
			<select class="form-control" id="dossierTemplate" name="dossierTemplate" data-bind="value: dossierTemplate" required="required" validationMessage="Bạn phải chọn mẫu hồ sơ">
			</select>
			<span data-for="dossierTemplate" class="k-invalid-msg"></span>
		</div>

		<div class="col-sm-12 MB15">
			<label>Tên quy trình xử lý thủ tục (<span class="red">*</span>)</label>
			<select class="form-control" id="serviceProcess" name="serviceProcess" data-bind="value: serviceProcess" required="required" validationMessage="Bạn phải chọn quy trình xử lý">
			</select>
			<span data-for="serviceProcess" class="k-invalid-msg"></span>
		</div>

		<div class="col-sm-12 MB15">
			<label>Chuỗi pattern xác định việc lựa chọn tự động theo đối tượng xử lý hồ sơ</label>
			<input name="autoSelect" id="autoSelect" class="form-control" placeholder="Chuỗi pattern" data-bind="value:autoSelect">
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

		<div class="col-sm-12 text-center MT15 MB15">
			<button class="btn btn-active" id="btn-submit-established-process" type="button">Ghi lại</button> <button class="btn" type="button" id="btn-revert-serviceoptions">Hủy bỏ</button>
		</div>

		<input type="hidden" name="itemServiceConfigOption" id="itemServiceConfigOption">
	</div>
</form>

<script type="text/javascript">

	var localIndexServiceOption = 0;
	var dataSourceServiceOption=new kendo.data.DataSource({
		transport:{
			read:function(options){
				$.ajax({
					url:"${api.server}/serviceconfigs/"+$("#itemServiceConfigId").val()+"/processes",
					dataType:"json",
					type:"GET",
					headers: {"groupId": ${groupId}},
					success:function(result){
						if (result.data) {
							options.success(result);
						} else {
							options.success({
								data: [],
								total: 0
							});
						}
					},
					error:function(result){
						options.error(result);
					}
				});
			},
			create:function(options){
				$.ajax({
					url:"${api.server}/serviceconfigs/"+options.serviceConfigId+"/processes",
					dataType:"json",
					type:"POST",
					headers: {"groupId": ${groupId}},
					data:{
						serviceConfigId:options.serviceConfigId,
						seqOrder:options.seqOrder,
						dossierTemplateId:options.dossierTemplateId,
						serviceProcessId:options.serviceProcessId,
						submissionNote:options.submissionNote,
						instructionNote:options.instructionNote,
						autoSelect:options.autoSelect
					},
					success:function(result){
						console.log(result);
						addServiceConfigOptionIfSuccess(result);
					},
					error:function(result){

					}
				});
			},
			update:function(options){
				$.ajax({
					url:"${api.server}/serviceconfigs/"+options.serviceConfigId+"/processes/"+options.processNo,
					dataType:"json",
					type:"PUT",
					headers: {"groupId": ${groupId}},
					data:{
						serviceConfigId:options.serviceConfigId,
						processNo:options.processNo,
						seqOrder:options.seqOrder,
						dossierTemplateId:options.dossierTemplateId,
						serviceProcessId:options.serviceProcessId,
						submissionNote:options.submissionNote,
						instructionNote:options.instructionNote,
						autoSelect:options.autoSelect
					},
					success:function(result){
						console.log(result);
						console.log(options);
						updateServieConfigOptionIfSuccess($("#itemServiceConfigOptionId").val(),result);
					},
					error:function(result){
						console.log(options);
					}
				});
			},
			destroy:function(options){
				$.ajax({
					url:"${api.server}/serviceconfigs/"+options.serviceConfigId+"/processes/"+options.processNo,
					dataType:"json",
					type:"DELETE",
					headers: {"groupId": ${groupId}},
					data:{
						processNo:options.processNo
					},
					success:function(result){
						var item=dataSourceServiceOption.get(options.processNo);
						if(item!=null){
							dataSourceServiceOption.remove(item);
						}
					},
					error:function(result){

					}
				});
			}
		},
		schema:{
			total:"total",
			data:"data",
			model:{
				id:"processOptionId"
			}
		},
		error: function(e) {
			this.cancelChanges();
		},
		autoSync: false,
		pageSize:10,
		serverPaging:false,
		serverSorting:false,
		serverFiltering:false
	});

	$("#serviceConfigOptionListView").kendoListView({
		dataSource : dataSourceServiceOption,
		template : kendo.template($("#serviceConfigOptionTemplate").html()),
		remove : function(e){
			if(!confirm('Bạn có muốn xóa ?')){
				e.preventDefault();
			}
		},
		autoBind: true,
		template: function(data){

			var _pageSize = dataSourceServiceOption.pageSize();

			localIndexServiceOption++;

			var currentPage = $("#pagerServiceConfigOption").data("kendoPager").page();
			var totalPage =  $("#pagerServiceConfigOption").data("kendoPager").totalPages();

			var index = (currentPage-1)*_pageSize + localIndexServiceOption;

			data.itemIndex = index;

			return kendo.template($("#serviceConfigOptionTemplate").html())(data);

		},
		dataBinding: function() {
			localIndexServiceOption = 0;
		},
		dataBound: function() {
		}
	});

	$("#pagerServiceConfigOption").kendoPager({
		dataSource:dataSourceServiceOption,
		input: true,
		numeric: false,
		messages: {
			empty: "Không có kết quả phù hợp!",
			display: "Hiển thị {0}-{1} trong {2} bản ghi",
			page: "",
			of: "/ {0}"
		}
	});


	$("#btn-add-serviceconfig-option").click(function(){
		console.log("add");
		$("#itemServiceConfigOption").val("");
		$("#frmEstablishedProcess").show();
		clearForm();
	});

	$("#btn-revert-serviceoptions").click(function(){
		console.log("list-serviceconfig-option");
		clearForm();
		$("#frmEstablishedProcess").hide();
	});

	var clearForm = function(){
		$("#seqOrder").val("");
		$("#optionName").val("");
		$("#dossierTemplate").data("kendoComboBox").value("");
		$("#serviceProcess").data("kendoComboBox").value("");
		$("#instructionNote").summernote('code', "");
		$("#submissionNote").summernote('code', "");
		$("#autoSelect").val("");
	}

	$("#btn-submit-established-process").click(function(){
		var id = $("#itemServiceConfigOption").val();
		var idServiceConfig = $("#itemServiceConfigId").val();
		var validator = $("#frmEstablishedProcess").kendoValidator().data("kendoValidator");
		if(validator.validate()){
			if(id && idServiceConfig){
				$.ajax({
					url : "${api.server}/serviceconfigs/"+idServiceConfig+"/processes/"+id,
					dataType : "json",
					type : "PUT",
					headers: {"groupId": ${groupId}},
					data : {
						seqOrder : $("#seqOrder").val(),
						optionName : $("#optionName").val(),
						dossierTemplateId : $("#dossierTemplate").val(),
						serviceProcessId : $("#serviceProcess").val(),
						instructionNote : $("#instructionNote").summernote("code").toString(),
						submissionNote :$("#submissionNote").summernote("code").toString(),
						autoSelect : $("#autoSelect").val()
					},
					success : function(result){
						notification.show({
							message: "Yêu cầu được thực hiện thành công"
						}, "success");
						dataSourceServiceOption.read();
						$("#frmEstablishedProcess").hide();
					},
					error : function(xhr){
						if (xhr.responseJSON.description == "DuplicateSeqOrderException"){
							notification.show({
								message: "Lỗi trùng số thứ tự"
							}, "error");
						} else {
							notification.show({
								message: "Yêu cầu thất bại"
							}, "error");
						}
					}	
				});
			}else {
				$.ajax({
					url : "${api.server}/serviceconfigs/"+idServiceConfig+"/processes",
					dataType : "json",
					type : "POST",
					headers: {"groupId": ${groupId}},
					data : {
						seqOrder : $("#seqOrder").val(),
						optionName : $("#optionName").val(),
						dossierTemplateId : $("#dossierTemplate").val(),
						serviceProcessId : $("#serviceProcess").val(),
						instructionNote : $("#instructionNote").summernote("code").toString(),
						submissionNote : $("#submissionNote").summernote("code").toString(),
						autoSelect : $("#autoSelect").val()
					},
					success : function(result){
						notification.show({
							message: "Yêu cầu được thực hiện thành công"
						}, "success");
						dataSourceServiceOption.read();
						$("#frmEstablishedProcess").hide();
					},
					error : function(xhr){
						if (xhr.responseJSON.description == "DuplicateSeqOrderException"){
							notification.show({
								message: "Lỗi trùng số thứ tự"
							}, "error");
						} else {
							notification.show({
								message: "Yêu cầu thất bại"
							}, "error");
						}
					}
				});

			}

		}

	});

	var pullServiceConfigOptionDetail= function(item){
		console.log("detail_serviceconfig");
		if(item){
			var viewModel = kendo.observable({
				seqOrder : item.seqOrder,
				optionName : item.optionName,
				dossierTemplate : item.dossierTemplateId,
				serviceProcess : item.serviceProcessId,
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


</script>
