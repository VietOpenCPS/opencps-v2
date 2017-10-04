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
				<#-- <ul class="mimic-table">
					<li class="clearfix">
						<div class="col-sm-2">
							<div class="row">
								<div class="col-sm-3">
									STT
								</div>
								<div class="col-sm-9">
									Họ và tên
								</div>
							</div>
						</div>
						<div class="col-sm-2">
							Số định danh
						</div>
						<div class="col-sm-2">
							Email
						</div>
						<div class="col-sm-2">
							Số điện thoại
						</div>
						<div class="col-sm-2">
							Thời gian  <span class="caret" id="btn-filter-by-date-citizen" type="1"></span>
						</div>
						<div class="col-sm-1">
							Trạng thái
						</div>
						<div class="col-sm-1">
							Hành động
						</div>
					</li>
				</ul>
				<ul class='mimic-table' id='citizenListView'>
				</ul> -->
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
					<#-- <li class="item-citizen clearfix eq-height-lg">
						<div class="col-sm-2">
							<div class="row">
								<div class="col-sm-3">
									#:itemIndex#
								</div>
								<div class="col-sm-9">
									#:contactName#
								</div>
							</div>
						</div>
						<div class="col-sm-2">
							12345657
						</div>
						<div class="col-sm-2">
							<span>#:contactEmail#</span>
						</div>
						<div class="col-sm-2">
							<span>#:contactTelNo#</span>
						</div>
						<div class="col-sm-2">
							#:createDate#
						</div>
						<div class="col-sm-1">
							Chưa kích hoạt
						</div>
						<div class="col-sm-1">
							#if(mappingUser.locking){#
							<button class="btn btn-sm btn-unlock-account-business" type="button">Kích hoạt</button>
							<button class="btn btn-sm btn-delete-account-business" type="button">Xóa</button>
							#}else {#
							<button class="btn btn-sm btn-lock-account-business" type="button">Khóa tài khoản</button>
							#}#
						</div>
					</li> -->
					<tr>
						<td class="text-center">#:itemIndex#</td>
						<td>#:contactName#</td>
						<td>12345657</td>
						<td>#:contactEmail#</td>
						<td>#:contactTelNo#</td>
						<td>#:createDate#</td>
						<td>Chưa kích hoạt</td>
						<td class="text-center">
							#if(mappingUser.locking){#
							<button class="btn btn-sm btn-unlock-account-business" type="button">Kích hoạt</button>
							<button class="btn btn-sm btn-delete-account-business" type="button">Xóa</button>
							#}else {#
							<button class="btn btn-sm btn-lock-account-business" type="button">Khóa tài khoản</button>
							#}#
						</td>
					</tr>
				</script>
			</div>
			<div id="mndn" class="tab-pane fade in PL0 PR0"  >
				<#-- <ul class="mimic-table">
					<li class="clearfix">
						
						<tr>
							<td>John</td>
							<td>Doe</td>
							<td>john@example.com</td>
						</tr>
						<tr>
							<td>Mary</td>
							<td>Moe</td>
							<td>mary@example.com</td>
						</tr>
						<tr>
							<td>July</td>
							<td>Dooley</td>
							<td>july@example.com</td>
						</tr>

						<div class="col-sm-2">
							<div class="row">
								<div class="col-sm-3">
									STT
								</div>
								<div class="col-sm-9">
									Tên tổ chức
								</div>
							</div>
						</div>
						<div class="col-sm-2">
							Số định danh
						</div>
						<div class="col-sm-2">
							Email
						</div>
						<div class="col-sm-2">
							Số điện thoại
						</div>
						<div class="col-sm-2">
							Thời gian  <span class="caret" id="btn-filter-by-date-business" type="1"></span>
						</div>
						<div class="col-sm-1">
							Trạng thái
						</div>
						<div class="col-sm-1">
							Hành động
						</div>
					</li>
				</ul> -->
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
						<td>#:itemIndex#</td>
						<td>#:contactName#</td>
						<td>12345657</td>
						<td>#:contactEmail#</td>
						<td>#:contactTelNo#</td>
						<td>#:createDate#</td>
						<td>Chưa kích hoạt</td>
						<td class="text-center">
							#if(mappingUser.locking){#
							<button class="btn btn-sm btn-unlock-account-business" type="button">Kích hoạt</button>
							<button class="btn btn-sm btn-delete-account-business" type="button">Xóa</button>
							#}else {#
							<button class="btn btn-sm btn-lock-account-business" type="button">Khóa tài khoản</button>
							#}#
						</td>
					</tr>
					<#-- <li class="item-business clearfix eq-height-lg">
						<div class="col-sm-2">
							<div class="row">
								<div class="col-sm-3">
									#:itemIndex#
								</div>
								<div class="col-sm-9">
									#:contactName#
								</div>
							</div>
						</div>
						<div class="col-sm-2">
							12345657
						</div>
						<div class="col-sm-2">
							<span>#:contactEmail#</span>
						</div>
						<div class="col-sm-2">
							<span>#:contactTelNo#</span>
						</div>
						<div class="col-sm-2">
							<span>#:createDate#</span>
						</div>
						<div class="col-sm-1">
							Chưa kích hoạt
						</div>
						<div class="col-sm-1">
							#if(mappingUser.locking){#
							<button class="btn btn-sm btn-unlock-account-business" type="button">Kích hoạt</button>
							<button class="btn btn-sm btn-delete-account-business" type="button">Xóa</button>
							#}else {#
							<button class="btn btn-sm btn-lock-account-business" type="button">Khóa tài khoản</button>
							#}#
						</div>
					</li> -->
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
					data : {
						status : options.data.statusApplicant,
						keyword : options.data.searchApplicant
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
				id : "id"
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
					data : {
						status : options.data.statusApplicant,
						keyword : options.data.searchApplicant
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
				id : "id"
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
		var id = $(this).attr("data-pk");
		$.ajax({
			url : "",
			dataType : "json",
			type : "PUT",
			success : function(result){
				var item = dataSourceCitizen.get(id);
				if(item){
					item.set("statusName","Khóa tài khoản");
					item.set("status",2);
				}
			},
			error : function(xhr){

			}
		});
	});

	$(document).on("click",".btn-unlock-account-citizen",function(event){
		var id = $(this).attr("data-pk");
		$.ajax({
			url : "",
			dataType : "json",
			type : "PUT",
			success : function(result){
				var item = dataSourceCitizen.get(id);
				if(item){
					item.set("statusName","Kích hoạt");
					item.set("status",1);
				}
			},
			error : function(xhr){

			}
		});
	});

	$(document).on("click",".btn-delete-account-citizen",function(event){
		var id = $(this).attr("data-pk");
		var cf = confirm("Bạn có muốn xóa !");
		if(cf){
			$.ajax({
				url : "",
				dataType : "json",
				type : "DELETE",
				success : function(result){
					var item = dataSourceCitizen.get(id);
					var index = dataSourceCitizen.remove(item);
				},
				error : function(xhr){

				}
			});
		}
	});

	$(document).on("click",".btn-lock-account-business",function(event){
		var id = $(this).attr("data-pk");
		$.ajax({
			url : "",
			dataType : "json",
			type : "PUT",
			success : function(result){
				var item = dataSourceBusiness.get(id);
				if(item){
					item.set("statusName","Khóa tài khoản");
					item.set("status",2);
				}
			},
			error : function(xhr){

			}
		});
	});

	$(document).on("click",".btn-unlock-account-business",function(event){
		var id = $(this).attr("data-pk");
		$.ajax({
			url : "",
			dataType : "json",
			type : "PUT",
			success : function(result){
				var item = dataSourceBusiness.get(id);
				if(item){
					item.set("statusName","Kích hoạt");
					item.set("status",1);
				}
			},
			error : function(xhr){

			}
		});
	});

	$(document).on("click",".btn-delete-account-business",function(event){
		var id = $(this).attr("data-pk");
		var cf = confirm("Bạn có muốn xóa !");
		if(cf){
			$.ajax({
				url : "",
				dataType : "json",
				type : "DELETE",
				success : function(result){
					var item = dataSourceBusiness.get(id);
					var index = dataSourceBusiness.remove(item);
				},
				error : function(xhr){

				}
			});
		}
	});

	$("#btn-filter-by-date-business").click(function(){
		var type = $(this).attr("type");
		console.log("filter business");
		if(type ===  1){
			dataSourceBusiness.sort({ field: "date", dir: "desc" });
			$(this).attr("type","2");
		}else{
			dataSourceBusiness.sort({ field: "date", dir: "asc" });
			$(this).attr("type","1");
		}
	});

	$("#btn-filter-by-date-citizen").click(function(){
		var type = $(this).attr("type");
		console.log("filter citizen");
		if(type === 1){
			dataSourceCitizen.sort({ field: "date", dir: "desc" });
			$(this).attr("type","2");
		}else{
			dataSourceCitizen.sort({ field: "date", dir: "asc" });
			$(this).attr("type","1");
		}
	});

	$("#statusApplicant").kendoComboBox({
		placeholder : "Chọn trạng thái",
		dataTextField : "name",
		dataSource : [
		{"name" : "Chưa kích hoạt", "value" : "1"},
		{"name" : "Hoàn thành", "value" : "2"},
		{"name" : "Khóa tài khoản", "value" : "3"}
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
			"statusApplicant" : $("#statusApplicant").val(),
			"searchApplicant" : $("#searchApplicant").val()
		});
		dataSourceBusiness.read({
			"statusApplicant" : $("#statusApplicant").val(),
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