<#include "init.ftl">

<div class="row">


	<div class="col-md-3 panel P0">

		
		<div class="panel-body">
	
			<span id="_jobpos_editLabel" class="btn btn-active btn-block"> 
				<i class="fa fa-clock-o" aria-hidden="true"></i>
				Thêm ngày làm việc
			</span>

			<div class="input-group MT15">
				
				<input type="text" class="form-control" id="_worktime_keySearch"
					oninput="_worktime_autocompleteSearch(this.value)" 
					placeholder="Tìm kiếm theo ngày làm việc">
	
				<div class="input-group-addon btn-active" id="_worktime_btnSearch">
					
					<i class="fa fa-search" aria-hidden="true"></i>
	
				</div>
	
			</div>
			
		</div>
				
			
		<ul class="ul-with-border ul-default mh-head-2" id="_worktime_listView"></ul>
		
		<script type="text/x-kendo-tmpl" id="_worktime_template">
		
			<li class="clearfix PT10 PR0 PB10 PL10">
	
				<div class="col-sm-2 clearfix PL0 PR0">
					
					<a href="javascript:;" >
						
						<i class="fa fa-graduation-cap fs26 P5" aria-hidden="true"></i>
							
					</a>
						
				</div>
					
				<div class="col-sm-9 PL0">
					#
						if (day === 0) {
					#
						<strong class="btn-block">Chủ nhật</strong>
					#
						} else {
					#
						<strong class="btn-block">Thứ #= day + 1 #</strong>
					#
						}
					#
					#
						var hoursTemp = hours.replace(',', '  ');
					#
					<strong class="btn-block">#= hoursTemp #</strong>
				</div>
					
				<span class="col-sm-1 PL0 PR0"></span>
					
				<div class="product-view">
				
					<div class="edit-buttons">
				
						<a class="k-delete-button k-delete-icon-listview" href="\\#">
							<i class="fa fa-times" aria-hidden="true"></i>
						</a>
			
					</div>
				
				</div>
					
			 </li>
	
		</script>

	</div>
	

	
	<div class="col-md-9 " id="_worktime_right-page">
		<#include "worktime_detail.ftl">
	</div>

</div>

<input type="hidden" value="0" id="_worktime_hidden_new_id"/>

<script type="text/javascript">
	
	function _jobpos_autocompleteSearch() {
	
		$("#_worktime_listView").getKendoListView().dataSource.filter({
			 field: "day", operator: "contains", 	value: $("#_worktime_keySearch").val().trim() 
		});
		
		$('#_worktime_CounterList').html($("#_worktime_listView").getKendoListView().dataSource.total());
		
	}
	
	(function($) {
	
		var _worktime_BaseUrl = "/o/rest/v2/worktimes";
		
		var _worktime_dataSource = new kendo.data.DataSource({

			transport: {
	
				read: function(options) {
					
					$.ajax({
					
						url: _worktime_BaseUrl,
						dataType: "json",
						type: 'GET',
						headers: {
							"groupId": ${groupId}
						},
						data: {
						},
						success: function(result) {
						
							$('#_worktime_CounterList').html(result.total);
							result["data"] = result.total==0 ? []: result["data"];
							options.success(result);
							
						},
						error: function(xhr, textStatus, errorThrown) {
							
							showMessageByAPICode(xhr.status);
						
						}
					
					});
				},
				destroy: function(options) {
					
					var confirmWindown = showWindowConfirm('#template-confirm','Cảnh báo','Bạn có chắc muốn xóa bản ghi này?', $("#_worktime_listView") );
					
					confirmWindown.then(function(confirmed){
					
						if(confirmed){
	
							$.ajax({
								url: _worktime_BaseUrl + "/" + options.data.day,
								headers: {
									"groupId": ${groupId}
								},
								type: 'DELETE',
								success: function(result) {
									
									$("#_worktime_hidden_new_id").val("0");
									options.success();
									$('#_worktimebpos_CounterList').html($("#_worktime_listView").getKendoListView().dataSource.total());
									showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
									
								},
								error: function(xhr, textStatus, errorThrown) {
								
									$("#_worktime_listView").getKendoListView().dataSource.error();
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
					id:"day"
				}
			},
			error: function(e) {
				
				this.cancelChanges();
				
			}

		});
	
		$("#_worktime_listView").kendoListView({
		
			remove: function(e) {
			
			},
			
			dataSource: _worktime_dataSource,
			
			selectable: "true",
			
			dataBound: _worktime_dataBound,
			
			change: _worktime_onChange,
			
			template: kendo.template($("#_worktime_template").html()),
			
			filterable: {
				field: "day", operator: "contains", 	value: $("#_worktime_keySearch").val().trim() 
			}
		
		});
		
		function _worktime_dataBound(e) {
			
			var _worktime_listView = e.sender;
			
			var children = _worktime_listView.element.children();
			
			var index = $("#_worktime_hidden_new_id").val().trim();
			
			for (var x = 0; x < children.length; x++) {
				
				var getObj = _worktime_listView.dataSource.view()[x];
				
				if (getObj.day == index) {
				
			 		index = x;
				
				};
				
			};
			
			_worktime_listView.select(children[index]);
			
		}
		
		function _worktime_onChange(e) {
	
			var data = _worktime_dataSource.view(),
	
			selected = $.map(this.select(), function(item) {
			
				return data[$(item).index()];
			
			});
			var viewModel = kendo.observable({
				day: selected[0].day,
				hours: selected[0].hours
			});
			kendo.bind($("#_worktimeDetail_form"), viewModel);
			$("#_worktime_hidden_new_id").val(selected[0].day);
			
		}

		$(document).on('click', '#_worktime_autocompleteSearch', function(event){
		
			// event.preventDefault();
			// event.stopPropagation();
			// event.stopImmediatePropagation();
			
			// $("#_jobpos_right-page").load(
			// 	'${url.adminJobPosPortlet.jobpos_detail}'
			// 	);
			
			// $("#_jobpos_listView").getKendoListView().clearSelection();
		});
		
	})(jQuery);
</script>