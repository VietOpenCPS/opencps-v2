<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="panel panel-body">
	<div class="nav-tabs-wrapper MT15">
		<ul class="nav nav-tabs" id="serviceinfo-tabstrip">
			<li class="active"  value="1">
				<a data-toggle="tab" href="#mncd">
					Công dân
				</a>
			</li>
			<li class="" value="2">
				<a data-toggle="tab" href="#mndn">
					Doanh nghiệp
				</a>
			</li>
		</ul>
		<div id="dataDetailServiceInfo" class="tab-content">
			<div class="row">
				<div class="col-sm-3">
					<div class="form-group">
						<input name="statusApplicant" id="statusApplicant" class="form-control">
					</div>
				</div>
				<div class="col-sm-8">
					<div class="form-group">
						<input type="text" id="searchApplicant" name="searchApplicant" class="form-control" placeholder="Nhập từ khóa">

					</div>
				</div>
				<div class="col-sm-1 PL0">
					<button class="btn btn-sm" id="btn-search-applicant" type="button">Tìm kiếm</button>
				</div>
			</div>
			<div id="mncd" class="tab-pane fade in active PL0 PR0">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>STT</th>
							<th>Họ và tên</th>
							<th>Số định danh</th>
							<th class="text-center">Email</th>
							<th>Số điện thoại</th>
							<th class="text-center">Thời gian <span class="caret" id="btn-filter-by-date-citizen" type="1"></span></th>
							<th class="text-center">Trạng thái</th>
							<th class="text-center">Hành động</th>
						</tr>
					</thead>
					<tbody id='citizenListView'>

					</tbody>
				</table>
				<div id='pagerCitizen'></div>
				<script type="text/x-kendo-template" id="templateCitizen">
					<tr>
						<td class="text-center">#:itemIndex#</td>
						<td>#:applicantName#</td>
						<td>#:applicantIdNo#</td>
						<td>#:contactEmail#</td>
						<td>#:contactTelNo#</td>
						<td>#:createDate#</td>
						<td class="applicant-status">
							#if(mappingUser.locking){#
							<span>Chưa kích hoạt</span>
							#}else {#
							<span>Hoàn thành</span>
							#}#
						</td>
						<td class="text-center">
							#if(mappingUser.locking){#
							<button class="btn btn-sm btn-unlock-account-citizen" type="button" data-pk="#:id#">Kích hoạt</button>
							<button class="btn btn-sm btn-delete-account-citizen" type="button" data-pk="#:id#">Xóa</button>
							#}else {#
							<button class="btn btn-sm btn-lock-account-citizen" type="button" data-pk="#:id#">Khóa tài khoản</button>
							#}#
						</td>
					</tr>
				</script>
			</div>
			<div id="mndn" class="tab-pane fade in PL0 PR0"  >
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>STT</th>
							<th>Tên tổ chức</th>
							<th>Số định danh</th>
							<th class="text-center">Email</th>
							<th>Số điện thoại</th>
							<th class="text-center">Thời gian <span class="caret" id="btn-filter-by-date-business" type="1"></span></th>
							<th class="text-center">Trạng thái</th>
							<th class="text-center">Hành động</th>
						</tr>
					</thead>
					<tbody id='businessListView'>

					</tbody>
				</table>
				<div id='pagerBussiness'></div>
				<script type="text/x-kendo-template" id="templateBusiness">
					<tr>
						<td class="text-center">#:itemIndex#</td>
						<td>#:applicantName#</td>
						<td>#:applicantIdNo#</td>
						<td>#:contactEmail#</td>
						<td>#:contactTelNo#</td>
						<td>#:createDate#</td>
						<td>
							#if(mappingUser.locking){#
							<span>Chưa kích hoạt</span>
							#}else {#
							<span>Hoàn thành</span>
							#}#
						</td>
						<td class="text-center">
							#if(mappingUser.locking){#
							<button class="btn btn-sm btn-unlock-account-business" type="button" data-pk="#:id#">Kích hoạt</button>
							<button class="btn btn-sm btn-delete-account-business" type="button" data-pk="#:id#">Xóa</button>
							#}else {#
							<button class="btn btn-sm btn-lock-account-business" type="button" data-pk="#:id#">Khóa tài khoản</button>
							#}#
						</td>
					</tr>
				</script>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var localIndexCitizen = 0;
	var localIndexBusiness = 0;
	var dataSourceCitizen = new kendo.data.DataSource({
		transport : {
			read : function(options){
				$.ajax({
					url : "${api.server}/applicants",
					dataType : "json",
					type : "GET",		
					headers: {"groupId": ${groupId}},
					data : {
						type : "citizen",
						keyword : options.data.searchApplicant,
						lock : options.data.lock,
						order: true
					},
					success : function (result) {
						options.success(result);
					},
					error : function(xhr){
						options.error(xhr);
					}
				});
			}
		},
		pageSize : 5,
		schema : {
			data : "data",
			total : "total",
			model : {
				id : "applicantId"
			}
		}
	});

	var dataSourceBusiness = new kendo.data.DataSource({
		transport : {
			read : function(options){
				$.ajax({
					url : "${api.server}/applicants",
					dataType : "json",
					type : "GET",
					headers: {"groupId": ${groupId}},
					data : {
						type : "business",
						keyword : options.data.searchApplicant,
						lock : options.data.lock
					},
					success : function (result) {
						options.success(result);
					},
					error : function(xhr){
						options.error(xhr);
					}
				});
			}
		},
		pageSize : 5,
		schema : {
			data : "data",
			total : "total",
			model : {
				id : "applicantId"
			}
		}
	});

	$("#citizenListView").kendoListView({
		dataSource : dataSourceCitizen,
		template : kendo.template($("#templateCitizen").html()),
		selectable : true,
		remove : function(e){
			if(!confirm('Bạn có muốn xóa ?')){
				e.preventDefault();
			}
		},
		template: function(data){


			var _pageSize = dataSourceCitizen.pageSize();

			localIndexCitizen++;

			var currentPage = $("#pagerCitizen").data("kendoPager").page();
			var totalPage =  $("#pagerCitizen").data("kendoPager").totalPages();

			var index = (currentPage-1)*_pageSize + localIndexCitizen;

			data.itemIndex = index;

			return kendo.template($("#templateCitizen").html())(data);

		},
		dataBinding: function() {
			localIndexCitizen = 0;
		}

	});

	$("#pagerCitizen").kendoPager({
		dataSource : dataSourceCitizen,
		input : true,
		numeric : false,
		messages : {
			empty : "Không có kết quả phù hợp!",
			display : "Hiển thị {0}-{1} trong {2} bản ghi",
			page : "",
			of : "/ {0}"
		}
	});

	$("#businessListView").kendoListView({
		dataSource : dataSourceBusiness,
		template : kendo.template($("#templateBusiness").html()),
		selectable : true,
		remove : function(e){
			if(!confirm('Bạn có muốn xóa ?')){
				e.preventDefault();
			}
		},
		template: function(data){

			var _pageSize = dataSourceBusiness.pageSize();

			localIndexBusiness++;

			var currentPage = $("#pagerBussiness").data("kendoPager").page();
			var totalPage =  $("#pagerBussiness").data("kendoPager").totalPages();

			var index = (currentPage-1)*_pageSize + localIndexBusiness;

			data.itemIndex = index;

			return kendo.template($("#templateBusiness").html())(data);

		},
		dataBinding: function() {
			localIndexBusiness = 0;
		}
	});
	
	$("#pagerBussiness").kendoPager({
		dataSource : dataSourceBusiness,
		input : true,
		numeric : false,
		messages : {
			empty : "Không có kết quả phù hợp!",
			display : "Hiển thị {0}-{1} trong {2} bản ghi",
			page : "",
			of : "/ {0}"
		}
	});

	$(document).on("click",".btn-lock-account-citizen",function(event){
		var that = this;
		var id = $(this).attr("data-pk");
		$.ajax({
			url : "${api.server}/applicants/"+id+"/lock",
			dataType : "json",
			type : "POST",
			headers: {"groupId": ${groupId}},
			data : {
				locked : true
			},
			success : function(result){
				var item = dataSourceCitizen.get(id);
				if(item){
					var mappingUser = item.mappingUser;
					mappingUser.locking = true;
					
					dataSourceCitizen.fetch(function(){
						item.set("mappingUser",mappingUser);
					});

					$(that).closest("td").html('<button class="btn btn-sm btn-unlock-account-citizen" type="button" data-pk="' + id + '">Kích hoạt</button><button class="btn btn-sm btn-delete-account-citizen" type="button" data-pk="' + id + '">Xóa</button>');
					$(that).closest("tr").find(".applicant-status").html("<span>Chưa kích hoạt</span>");
				}
			},
			error : function(xhr){

			}
		});
	});

	$(document).on("click",".btn-unlock-account-citizen",function(event){
		var that = this;
		var id = $(this).attr("data-pk");
		$.ajax({
			url : "${api.server}/applicants/"+id+"/lock",
			dataType : "json",
			type : "POST",
			headers: {"groupId": ${groupId}},
			data : {
				locked : false
			},
			success : function(result){
				var item = dataSourceCitizen.get(id);
				if(item){
					var mappingUser = item.mappingUser;
					mappingUser.locking = false;
					item.set("mappingUser",mappingUser);
				}

				$(that).closest("td").html('<button class="btn btn-sm btn-lock-account-citizen" type="button" data-pk="' + id + '">Khóa tài khoản</button>');
				$(that).closest("tr").find(".applicant-status").html("<span>Hoàn thành</span>");
			},
			error : function(xhr){
				showMessageByAPICode(xhr.status);
			}
		});
	});

	$(document).on("click",".btn-delete-account-citizen",function(event){
		var id = $(this).attr("data-pk");
		var cf = confirm("Bạn có muốn xóa !");
		if(cf){
			$.ajax({
				url : "${api.server}/applicants/"+id,
				dataType : "json",
				type : "DELETE",
				headers: {"groupId": ${groupId}},
				success : function(result){
					var item = dataSourceCitizen.get(id);
					var index = dataSourceCitizen.remove(item);
				},
				error : function(xhr){
					showMessageByAPICode(xhr.status);
				}
			});
		}
	});

	$(document).on("click",".btn-lock-account-business",function(event){
		var that = this;
		var id = $(this).attr("data-pk");
		$.ajax({
			url : "${api.server}/applicants/"+id+"/lock",
			dataType : "json",
			type : "POST",
			headers: {"groupId": ${groupId}},
			data : {
				locked : true
			},
			success : function(result){
				var item = dataSourceBusiness.get(id);

				if(item){
					var mappingUser = item.mappingUser;
					mappingUser.locking = true;
					item.set("mappingUser",mappingUser);
				}

				$(that).closest("td").html('<button class="btn btn-sm btn-unlock-account-business" type="button" data-pk="' + id + '">Kích hoạt</button><button class="btn btn-sm btn-delete-account-business" type="button" data-pk="' + id + '">Xóa</button>');
				$(that).closest("tr").find(".applicant-status").html("<span>Chưa kích hoạt</span>");
			},
			error : function(xhr){
				showMessageByAPICode(xhr.status);
			}
		});
	});

	$(document).on("click",".btn-unlock-account-business",function(event){
		var that = this;
		var id = $(this).attr("data-pk");
		$.ajax({
			url : "${api.server}/applicants/"+id+"/lock",
			dataType : "json",
			type : "POST",
			headers: {"groupId": ${groupId}},
			data : {
				locked : false
			},
			success : function(result){
				var item = dataSourceBusiness.get(id);
				if(item){
					var mappingUser = item.mappingUser;
					mappingUser.locking = false;
					item.set("mappingUser",mappingUser);
				}

				$(that).closest("td").html('<button class="btn btn-sm btn-lock-account-business" type="button" data-pk="' + id + '">Khóa tài khoản</button>');
				$(that).closest("tr").find(".applicant-status").html("<span>Hoàn thành</span>");
			},
			error : function(xhr){
				showMessageByAPICode(xhr.status);
			}
		});
	});

	$(document).on("click",".btn-delete-account-business",function(event){
		var id = $(this).attr("data-pk");
		var cf = confirm("Bạn có muốn xóa !");
		if(cf){
			$.ajax({
				url : "${api.server}/applicants/"+id,
				dataType : "json",
				type : "DELETE",
				headers: {"groupId": ${groupId}},
				success : function(result){
					var item = dataSourceBusiness.get(id);
					var index = dataSourceBusiness.remove(item);
				},
				error : function(xhr){
					showMessageByAPICode(xhr.status);
				}
			});
		}
	});

	$("#btn-filter-by-date-business").click(function(){
		var type = $(this).attr("type");
		console.log("filter business");
		if(type ===  1){
			dataSourceBusiness.sort({ field: "date", dir: "desc" });
			$("#btn-filter-by-date-business").attr("type","2");
		}else{
			dataSourceBusiness.sort({ field: "date", dir: "asc" });
			$("#btn-filter-by-date-business").attr("type","1");
		}
	});

	$("#btn-filter-by-date-citizen").click(function(){
		var type = $(this).attr("type");
		console.log("filter citizen");
		if(type === 1){
			dataSourceCitizen.sort({ field: "date", dir: "desc" });
			$("#btn-filter-by-date-citizen").attr("type","2");
		}else{
			dataSourceCitizen.sort({ field: "date", dir: "asc" });
			$("#btn-filter-by-date-citizen").attr("type","1");
		}
	});

	$("#statusApplicant").kendoComboBox({
		placeholder : "Chọn trạng thái",
		dataTextField : "name",
		dataValueField : "value",
		dataSource : [
		{"name" : "Chưa kích hoạt", "value" : true},
		{"name" : "Hoàn thành", "value" : false},
		],
		noDataTemplate: 'Không có dữ liệu'
	});

	$("#searchApplicant").kendoAutoComplete({
		dataTextField : "applicantName",
		dataSource: {
			transport : {
				read : {
					url : "${api.server}/applicants",
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
					id : "applicantId"
				}
			}
		},
		filter: "contains",
		placeholder: "Nhập từ khóa",
		noDataTemplate: 'Không có dữ liệu'
	});

	$("#btn-search-applicant").click(function(){
		console.log("search");
		dataSourceCitizen.read({
			"lock" : $("#statusApplicant").val(),
			"searchApplicant" : $("#searchApplicant").val()
		});
		dataSourceBusiness.read({
			"lock" : $("#statusApplicant").val(),
			"searchApplicant" : $("#searchApplicant").val()
		});
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
