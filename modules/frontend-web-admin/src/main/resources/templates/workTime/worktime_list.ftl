<#include "init.ftl">

<div class="row">


	<div class="col-md-3 panel P0">

		
		<div class="panel-body">
	
			<span id="_worktime_editLabel" class="btn btn-active btn-block"> 
				<i class="fa fa-clock-o" aria-hidden="true"></i>
				Thêm ngày làm việc
			</span>

			<div class="input-group MT15">
				
				<input type="text" class="form-control" id="_worktime_keySearch"
					placeholder="Tìm kiếm theo ngày làm việc">
	
				<#-- <div class="input-group-addon btn-active" id="_worktime_btnSearch">
					
					<i class="fa fa-search" aria-hidden="true"></i>
	
				</div> -->
	
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
	
	function _worktime_autocompleteSearch(data) {
	
		$("#_worktime_listView").getKendoListView().dataSource.filter({
			 field: "day", operator: "contains", 	value: data 
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
			var viewModel = {};
			if (selected[0]) {
				viewModel = kendo.observable({
					day: selected[0].day,
					hours: function (e) {
						var hoursTemp = selected[0].hours.split(',');
						var hoursMorning = hoursTemp[0].split('-');
						var hoursAfter = hoursTemp[1].split('-');
						var worktimeStartMorning = hoursMorning[0];
						var worktimeEndMorning = hoursMorning[1];
						var worktimeStartAfter = hoursAfter[0];
						var worktimeEndAfter = hoursAfter[1];
						$("#worktimeStartMorning").data('kendoTimePicker').value(worktimeStartMorning);
						$("#worktimeEndMorning").data('kendoTimePicker').value(worktimeEndMorning);
						$("#worktimeStartAfter").data('kendoTimePicker').value(worktimeStartAfter);
						$("#worktimeEndAfter").data('kendoTimePicker').value(worktimeEndAfter);
					}
				});
				$("#_worktimeDetail_submitBtn > span").html('Lưu lại');
				$("#_worktime_hidden_new_id").val(selected[0].day);
			} else {
				viewModel = kendo.observable({
					day: 0,
					hours: function (e) {
						$("#worktimeStartMorning").data('kendoTimePicker').value('06.00');
						$("#worktimeEndMorning").data('kendoTimePicker').value('12.00');
						$("#worktimeStartAfter").data('kendoTimePicker').value('12.30');
						$("#worktimeEndAfter").data('kendoTimePicker').value('18.00');
					}
				});
				$("#_worktimeDetail_submitBtn > span").html('Thêm mới');
				$("#_worktime_hidden_new_id").val(0);
			}
			
			kendo.bind($("#_worktimeDetail_form"), viewModel);
		}

		$(document).on('click', '#_worktime_editLabel', function(event){
		
			event.preventDefault();
			event.stopPropagation();
			event.stopImmediatePropagation();
			
			// $("#_jobpos_right-page").load(
			// 	'${url.adminJobPosPortlet.jobpos_detail}'
			// 	);
			$("#_worktimeDetail_submitBtn > span").html('Thêm mới');
			$("#_worktime_listView").getKendoListView().clearSelection();
		});

		$("#_worktime_keySearch").kendoComboBox({
			placeholder : "Tìm theo ngày làm việc",
			dataTextField : "text",
			dataValueField : "value",
			dataSource : [
			{
				value: 0,
				text: 'Chủ nhật'
			},
			{
				value: 1,
				text: 'Thứ 2'
			},
			{
				value: 2,
				text: 'Thứ 3'
			},
			{
				value: 3,
				text: 'Thứ 4'
			},
			{
				value: 4,
				text: 'Thứ 5'
			},
			{
				value: 5,
				text: 'Thứ 6'
			},
			{
				value: 6,
				text: 'Thứ 7'
			}
			],
			dataBound : function(e){
				$(".k-clear-value").addClass("k-hidden");
			},
			change: function (e) {
				console.log(this.value())
				if (this.value() !== null && this.value() !== '' && this.value() !== undefined) {
					_worktime_autocompleteSearch(this.value())
				}
			},
			noDataTemplate: 'Không có dữ liệu'
		});
		
	})(jQuery);
</script>