<#if (Request)??>
<#include "init.ftl">
</#if>

<div  class="row" id="employee_list">

	<div class="col-md-9 PR0-lg">

		<div id="employee-list-screen">

			<div class="row">

				<div id="employee-list-filter" class="col-sm-3">

					<div class="row">

						<div class="col-xs-12 col-sm-12">

							<button data-toggle="modal" class="btn btn-active btn-block border-rad-4"
							href="${(url.employeePortlet.employee_create)!}" data-target="#modal-lg"> 
							<i class="fa fa-user" aria-hidden="true"></i>

							<span class="p-xxs" >Thêm nhân sự</span> 
							<i class="fa fa-plus-circle"></i> 
						</button>
						
					</div>

					<div class="col-sm-12 MT15">

						<ul id="employee-list-filter-by-workingunit" class="contact-navigator"></ul>

						<script type="text/x-kendo-tmpl" id="employee-list-filter-by-workingunit-template">
							# if( item.treeLevel == 0 ) { # 
							<span class="nav-icon">

								<i class="fa fa-book fa-1.5x" aria-hidden="true"></i>

							</span>
							# } #

							<span class="filter-workingunit" id="workingUnitId_#=item.workingUnitId#"
							data-vl="#=item.workingUnitId#">
							#=item.name# 
						</span>
					</script>

				</div>

				<div class="col-sm-12 MT15">

					<select id="employee-list-filter-by-jobpos" name="jobpos" class="form-control contact-navigator-filter"></select>

				</div>

				<div class="col-sm-12 MT15">

					<select id="employee-list-filter-by-working-status" name="status" class="form-control contact-navigator-filter">
					</select>

				</div>

			</div>

		</div>

		<div id="employee-list-wrapper" class="col-sm-9 PL0-lg">

			<div class="row M0 box-s3">

				<div class="col-sm-12">

					<div class="row align-middle">

						<div class="col-sm-8 align-middle">

							<h4 class="M0" id="emoloyee-list-title"></h4>

							<div class="text-gray ML15">

								<i>
									Tổng số <span id="emoloyee-list-counter">0</span> tài khoản được tìm thấy
								</i>

							</div>

						</div>

						<div class="col-sm-4">
							
							<div class="input-group">

								<input type="text" class="form-control border-rad-Lside" id="employeeKeySearch"
								placeholder="Tìm theo tên, email, điện thoại...">

								<div class="input-group-addon btn-primary border-rad-Rside">

									<i class="fa fa-search" aria-hidden="true"></i>

								</div>

							</div>

						</div>

					</div>

				</div>

				<div class="col-sm-12 MT15">

					<div class="row PT15 PB15">

						<div class="form-group row M0 border-bottom PB5 border-color-lightblue">

							<div class="col-sm-2">

								<strong>Mã</strong>

							</div>

							<div class="col-sm-3">

								<strong>Họ và tên</strong>

							</div>

							<div class="col-sm-3">

								<strong>Chức vụ</strong>

							</div>

							<div class="col-sm-4">

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


					<script type="text/x-kendo-tmpl" id="employee-listview-template">

						<li class="PT10 PB10 border-bottom">

							<div class="row M0 ">

								<div class="col-sm-2">

									#=employeeNo#

								</div>

								<div class="col-sm-3 text-ellipsis">

									<strong>

										<a href="javascript:;" class="link-detail-employee" data-pk="#=employeeId#" >
											
											#=fullName#

										</a>

									</strong>

									<br>#= kendo.toString(kendo.parseDate(birthdate, 'yyyy-MM-dd'), 'dd/MM/yyyy')#

								</div>

								<div class="col-sm-3 text-ellipsis">
									#=jobPosTitle#<br>#=workingUnitName#
								</div>

								<div class="col-sm-3 text-ellipsis">
									#=telNo#<br>#=email#
								</div>

								<div class="col-xs-1 col-sm-1 text-right">

									<span class="ML10 k-delete-button">

										<i aria-hidden="true" class="fa fa-times"></i>

									</span>

								</div>


							</div>	

						</li>

					</script>

				</div>

			</div>

		</div>

	</div>

</div>

</div>

<div class="col-md-3">

	<div id="employee-birthdate"></div>

</div>

</div>

<div class="" id="employee_detail">
	
</div>



<script type="text/javascript">
	
	(function($) {

		'use strict';

		var empMenuFilter = $('#employee-list-filter').filterMenu({
			inputClass: ".employee-list-filter",
			panelBarId: "#employee-list-filter-by-workingunit",
			filterClass: "",
			filterId: "",
			doFilter: function(){
				
				employeeListSearch( employeeList );

			}
		}).register();

		var getWorkingUnitBaseUrl = "${api.server}/workingunits";

		var getJobPosBaseUrl = "${api.server}/jobpos";

		var employeeList = initEmployeeList( empMenuFilter.filterKeys );

		$( '#employee-birthdate' ).load("${(url.employeePortlet.employee_birthdate)!}");

		$('#employeeKeySearch').on('keyup', function(evt){

			empMenuFilter.filterKeys.keywords = $('#employeeKeySearch').val();
			employeeListSearch( employeeList );

		});

		$(document).on('click', '.link-detail-employee', function(event){

			event.preventDefault();
			event.stopPropagation();
			event.stopImmediatePropagation();
			$("#employee_list").hide();
			$("#employee_detail").show();

			var employeeId = $(this).attr('data-pk');

			$( "#employee_detail").load("${(url.employeePortlet.employee_detail)!}&${portletNamespace}employeeId="+employeeId);

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

		//Init panelbar
		$.ajax({

			url: getWorkingUnitBaseUrl,
			dataType: "json",
			type: 'GET',
			async: false,
			headers: {
				"groupId": ${groupId}
			},
			data: {
				sort: "treeIndex",
				order: false
			},
			success: function(result) {

				initEmployeePanelBar( result );
				
			}

		});

		function rescusiveSaveListWorkingUnit (workingUnitId) {

			try{

				var li = $("#workingUnitId_" + workingUnitId).closest('li');
				
				if (workingUnitId > 0 && li.length > 0 ) {

					empMenuFilter.filterKeys.dataExpand.push(workingUnitId);

					var liParent = li.parent().closest('li');
					var parentWorkingUnitId = liParent.find("> .k-link .filter-workingunit").attr('data-vl');

					rescusiveSaveListWorkingUnit(parentWorkingUnitId);
				}

			} catch (e) {
				console.log(e);
			}

		};
		
		$("#employee-list-filter-by-working-status").kendoDropDownList({
			
			optionLabel: "Tình trạng làm việc...",
			dataTextField: "text",
			dataValueField: "value",
			dataSource: [
			{text: "Đang làm việc", value: "1" },
			{text: "Đã nghỉ việc", value: "0" }
			],
			value: empMenuFilter.filterKeys.status
			
		});
		
		var getJobPosDataSource = new kendo.data.DataSource({
			
			transport: {

				read: function(options) {
					
					$.ajax({

						url: getJobPosBaseUrl,
						dataType: "json",
						type: 'GET',
						headers: {
							"groupId": ${groupId}
						},
						data: {
							sort: "treeIndex",
							order: false

						},
						success: function(result) {
							
							options.success(result);
							
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
					id: "jobPosId",
					fields: {
						jobPosId: {
							editable: false,
							nullable: true
						},
						title: {
							type: "string"
						}
					}
				}
			}
			
		});

		$("#employee-list-filter-by-jobpos").kendoDropDownList({
			
			optionLabel: "Lọc theo chức vụ",
			dataTextField: "title",
			dataValueField: "jobPosId",
			dataSource: getJobPosDataSource,
			value: empMenuFilter.filterKeys.jobpos
			
		});

		function employeeListSearch(employeeListView) {

			if (employeeListView!=null && employeeListView!="" && employeeListView!== undefined) {

				employeeListView.dataSource.filter({});

			}
		}

		function initEmployeeList(filterKeys){

			var getEmployeeBaseUrl = "${api.server}/employees";
			
			var getEmployeeDataSource = new kendo.data.DataSource({
				
				transport: {
					
					read: function(options) {

						var page = options.data.page;
						var pageSize = options.data.pageSize;

						$.ajax({

							url: getEmployeeBaseUrl,
							dataType: "json",
							type: 'GET',
							headers: {
								"groupId": ${groupId}
							},
							data: {
								sort: 'employeeNo',
								start: (page - 1)*pageSize,
								end: (page - 1)*pageSize + pageSize,
								keywords: $("#employeeKeySearch").val(),

								jobpos: filterKeys.hasOwnProperty('jobpos')? filterKeys.jobpos : "",
								workingunit: filterKeys.hasOwnProperty('workingunit')? filterKeys.workingunit : "",
								status: filterKeys.hasOwnProperty('status')? filterKeys.status : ""

							},
							success: function(result) {
								
								$('#emoloyee-list-counter').html(result.total);

								var titleHead = "";
								var splitSym = "";

								if ( filterKeys.hasOwnProperty('workingunit_text') && filterKeys.workingunit_text!="" ) {
									titleHead = titleHead + splitSym + filterKeys.workingunit_text;
									splitSym = " - ";
								}

								if ( filterKeys.hasOwnProperty('jobpos_text') && filterKeys.jobpos_text!="" ) {
									titleHead = titleHead + splitSym + filterKeys.jobpos_text;
									splitSym = " - ";
								}

								if ( filterKeys.hasOwnProperty('status_text') && filterKeys.status_text!="" ) {
									titleHead = titleHead + splitSym + filterKeys.status_text;
									splitSym = " - ";
								}

								if (titleHead == "") {
									titleHead = "Quản lý nhân sự.";
								} else {
									titleHead = titleHead + ".";
								}

								$('#emoloyee-list-title').text(titleHead);
								options.success(result);
								
							},
							error: function(xhr, textStatus, errorThrown) {
								
								showMessageToastr("error", 'Yêu cầu của bạn xử lý thất bại!');

							}

						});

					},
					destroy: function(options) {
						
						$.ajax({
							url: getEmployeeBaseUrl + "/" + options.data.employeeId,
							type: 'DELETE',
							success: function(result) {
								
								options.success();
								$('#emoloyee-list-counter').html($("#employee-listview").getKendoListView().dataSource.total());
								showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
								
							},
							error: function(xhr, textStatus, errorThrown) {
								
								options.error(xhr, textStatus, errorThrown);
								showMessageByAPICode(xhr.status);

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
					
					if (!confirm("Xác nhận xoá " + e.model.get("fullName") + "?")) {
						
						e.preventDefault();

					}
					
				},
				
				dataSource: getEmployeeDataSource,
				
				template: function(data){

					return kendo.template($("#employee-listview-template").html())(data);

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

			return employeeListView.data("kendoListView");

		}

		function initEmployeePanelBar ( dataSource ) {
			var dataTree = dataSource.total > 0 ? arrayToTree(dataSource.data, "workingUnitId", "parentWorkingUnitId", "childrens") : [];
			var dataSourceWorkingUnit = new kendo.data.HierarchicalDataSource({

				data: dataTree,
				schema: {
					model: {
						id: "workingUnitId",
						children: "childrens"
					}
				}

			});

			var empPanelBar = $("#employee-list-filter-by-workingunit").kendoPanelBar({

				dataSource: dataSourceWorkingUnit,
				template: kendo.template($("#employee-list-filter-by-workingunit-template").html()),
				select: function(e) {
					
					var workingUnitId = $(e.item).find("> .k-link .filter-workingunit").attr('data-vl');
					
					empMenuFilter.filterKeys.dataExpand = [];
					rescusiveSaveListWorkingUnit( workingUnitId );

					empMenuFilter.filterKeys.workingunit = workingUnitId;
					empMenuFilter.filterKeys.workingunit_text = $(e.item).find("> .k-link .filter-workingunit").text().trim();

					employeeListSearch( employeeList );
					
				}

			}).data("kendoPanelBar");

			if ( empPanelBar != null ) {

				empPanelBar.expand(function(){

					for ( var i = empMenuFilter.filterKeys.dataExpand.length; i > 0; ) {
						
						i--;

						var li = $("#workingUnitId_"+ empMenuFilter.filterKeys.dataExpand[i] ).closest("li");

						if (i == 0) {

							empPanelBar.select(li);

						} else {

							empPanelBar.expand( li );

						}
					}
					
				});

			}

		}

		ts.tabGroup.on('click','li.employee',function(e){
			//Init panelbar
			$.ajax({

				url: getWorkingUnitBaseUrl,
				dataType: "json",
				type: 'GET',
				async: false,
				headers: {
					"groupId": ${groupId}
				},
				data: {
					sort: "treeIndex",
					order: false
				},
				success: function(result) {

					initEmployeePanelBar( result );
					
				}

			});
		});
		
	})(jQuery);

</script>