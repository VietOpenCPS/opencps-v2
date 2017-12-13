<div class="row M0">
						
	<div class="col-sm-12 box box-s3">

		<div class="row eq-height">
			
			<div class="col-sm-8 align-middle">
			
				<h4 class="MT5" id="employee-list-title"></h4>

				<div class="text-gray ML15">
					
					<i>
						Tổng số <span id="emoloyee-list-counter">0</span> tài khoản được tìm thấy
					</i>
					
				</div>

			</div>
			
			<div class="col-sm-4 align-middle employee-keyword">
		
				<div class="input-group">

					<input type="text" class="form-control border-rad-Lside"
						id="employeeKeySearch" name="keywords"
						placeholder="Tìm theo tên, email, điện thoại...">

					<div class="input-group-addon btn-active border-rad-Rside">
						
						<i class="fa fa-search" aria-hidden="true"></i>

					</div>

				</div>

			</div>

		</div>

	</div>

	<div class="col-sm-12 MT15 box box-s3">
		
		<div class="row PT15 PB15">
						
			<div class="form-group row M0 border-bottom PB5 border-color-lightblue">

				<div class="col-sm-2">
					
					<strong>Mã</strong>
				
				</div>
				
				<div class="col-sm-4">
					
					<strong>Họ và tên</strong>
				
				</div>

				<div class="col-sm-3">
					
					<strong>Chức vụ</strong>
				
				</div>
				
				<div class="col-sm-3">
					
					<strong>Liên lạc</strong>
				
				</div>

			</div>

			<ul id="employee-listview">
			</ul>

			<div class="col-sm-12 k-pager-style2 eq-height-lg">

				<div class="align-middle-lg">
					<span class="text-nowrap MR10">Hiển thị </span>
				
					<input type="text" name="employee-select-page" id="employee-select-page">
				</div>

				<div id="employee-listview-pager">
					
				</div>

			</div>

		</div>

	</div>

</div>

<script type="text/x-kendo-tmpl" id="employee-listview-template">
		
	<li class="PT5 PB5 border-bottom">
	
		<div class="row M0 ">

			<div class="col-sm-2">
				
				<a href="javascript:;"
						data-href="${(url.employeePortlet.employee_detail)!}&${portletNamespace}employeeId=#=employeeId#" 
						class="link-detail-employee" data-pk="#=employeeId#" >
					
					#=employeeNo#
					
				</a>
					
			</div>
			
			<div class="col-sm-4 text-ellipsis">
			
				<strong>

					<a href="javascript:;"
						data-href="${(url.employeePortlet.employee_detail)!}&${portletNamespace}employeeId=#=employeeId#" 
						class="link-detail-employee" data-pk="#=employeeId#" >
				
						#=fullName#

					</a>

				</strong>

				<br>
				#if ( birthdate!="" && birthdate!=null ) {#
					#= kendo.toString(kendo.parseDate(birthdate, 'yyyy-MM-dd'), 'dd/MM/yyyy')#
				#} else {#
					<i class="gray">__/__/____</i>
				#}#
			</div>

			<div class="col-sm-3 text-ellipsis">
				# if(jobPosTitle != null && jobPosTitle != ""){#
		
					#=jobPosTitle#

				#} else {#
					<i class="gray">Chưa cập nhật</i>
				#}#
				<br>
				# if(workingUnitName != null && workingUnitName != ""){#
		
					#=workingUnitName#

				#} else {#
					<i class="gray">Chưa cập nhật</i>
				#}#
			</div>

			<div class="col-sm-2 text-ellipsis">
				# if(telNo != null && telNo != ""){#
		
					#=telNo#

				#} else {#
					<i class="gray">Chưa cập nhật</i>
				#}#
				<br>
				# if(email != null && email != ""){#
		
					#=email#

				#} else {#
					<i class="gray">Chưa cập nhật</i>
				#}#
			</div>

			<div class="col-xs-1 col-sm-1 text-right">

					<span class="ML10 k-delete-button">

						<i aria-hidden="true" class="fa fa-times"></i>

					</span>

			</div>


		</div>	
			
	 </li>

</script>

<script>
	$(function() {

		var getEmployeeBaseUrl = "${api.endpoint}/employees";
			
		var getEmployeeDataSource = new kendo.data.DataSource({
			
			transport: {
				
				read: function(options) {
					
					var filterKeys = $("#employee-listview")[0]["filterKeys"];
					filterKeys = (filterKeys!=null && typeof filterKeys === 'object')?filterKeys:{};
					
					var page = options.data.page;
					var pageSize = options.data.pageSize;
					filterKeys["sort"] = 'employeeNo';
					filterKeys["start"] = (page - 1)*pageSize;
					filterKeys["end"] = (page - 1)*pageSize + pageSize;
					
					
					$.ajax({
					
						url: getEmployeeBaseUrl,
						dataType: "json",
						type: 'GET',
						headers: {
							"groupId": ${groupId}
						},
						data: filterKeys,
						success: function(result) {
							
							$('#emoloyee-list-counter').html(result.total);
							setHeader(filterKeys);
							result["data"] = result.total==0 ? []: result["data"];
							options.success(result);
							
						},
						error: function(xhr, textStatus, errorThrown) {
							
							showMessageToastr("error", 'Yêu cầu của bạn xử lý thất bại!');
						
						}
					
					});

				},
				destroy: function(options) {
					
					var confirmWindown = showWindowConfirm('#template-confirm','Cảnh báo','Bạn có chắc muốn xóa bản ghi này?', $("#employee-listview") );
				
					confirmWindown.then(function(confirmed){
					
						if(confirmed){
	
							$.ajax({
								url: getEmployeeBaseUrl + "/" + options.data.employeeId,
								type: 'DELETE',
								success: function(result) {
									
									options.success();
									$('#emoloyee-list-counter').html($("#employee-listview").getKendoListView().dataSource.total());
									$("#employee-birthdate-listview").getKendoListView().dataSource.read();
									showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
									
								},
								error: function(xhr, textStatus, errorThrown) {
									
									options.error(xhr, textStatus, errorThrown);
									showMessageByAPICode(xhr.status);
								
								}
				
							});
	
						} else{
							
							options.error();
						}
					});
					
				},
				parameterMap: function(options, operation) {
					
					if (operation !== "read" && options.models) {
						return {
							models: kendo.stringify(options.models)
						};
					}
				}
			},
			schema: {
				data: "data",
				total: "total",
				model: {
					id: "employeeId",
					fields: {
						employeeId: {
							editable: false,
							nullable: true
						},
						fullName: {
							type: "string"
						},
						employeeNo: {
							type: "string"
						},
						title: {
							type: "string"
						},
						jobPosTitle: {
							type: "string"
						},
						workingUnitName: {
							type: "string"
						},
						telNo: {
							type: "string"
						},
						email: {
							type: "string"
						},
						mobile: {
							type: "string"
						}

					}
				}
			},
			error: function(e) {
				
				this.cancelChanges();
				
			},
			pageSize: 5,
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true
			
		});
		
		var  employeeListView = $("#employee-listview").kendoListView({
		
			remove: function(e) {
				
			},
			
			dataSource: getEmployeeDataSource,
			
			template: function(data){

				return kendo.template($("#employee-listview-template").html())(data);

			}

		});
		
		$("#employee-select-page").kendoDropDownList({
			
			optionLabel: "",
			dataTextField: "text",
			dataValueField: "value",
			dataSource: [
				{text: "5", value: 5 },
				{text: "10", value: 10 },
				{text: "15", value: 15 },
				{text: "25", value: 25 },
				{text: "50", value: 50 }
			],
			select: function (e) {

				var dataItem = this.dataItem(e.item);

				$("#employee-listview").data("kendoListView").dataSource.pageSize(dataItem.value);

			}
		});

		$("#employee-listview-pager").kendoPager({
			dataSource: getEmployeeDataSource,
			input: true,
			numeric: false,
			messages: {
				empty: "Không có kết quả phù hợp!",
				display: "",
				page: "",
				of: "/ {0}"
			}
		});
		
		function setHeader (params) {
		
			var titleHead = "";
			var splitSym = "";
	
			for (var x in params) {
				
				if ( x=="keywords" && params.keywords!= "") {
				
					titleHead = "Tìm kiếm theo từ khoá: " + params.keywords;
				
				} else if ( x.indexOf("_text") > 0 && params[x] != "" && params[x] != null) {
					
					if(!params[x]) delete params[x];
					
					titleHead = titleHead + splitSym + params[x];
					splitSym = ", ";
	
				}
	
			}
			
			if (titleHead == "") {
				titleHead = "Quản lý nhân sự.";
			} else {
				titleHead = titleHead + ".";
			}
	
			$('#employee-list-title').html(titleHead);
		
		};
		
		$(document).delegate('.link-detail-employee','click', function(e) {
			
			e.preventDefault();
			e.stopPropagation();
			e.stopImmediatePropagation();
			
			var url = $(e.currentTarget).attr('data-href');
			$("#employee-index-page").load(url);
		});
		
	});
 </script>