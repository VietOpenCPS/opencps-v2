<#include "init.ftl">

<div class="row">

	<!-- left-->
	<div class="col-md-3 panel P0">

		<!--search-->
		<div class="panel-body">
	
			<span id="_jobpos_editLabel" class="btn btn-primary image-preview-input btn-block"> 
				<i class="fa fa-graduation-cap" aria-hidden="true"></i>
				<span class="p-xxs" >Tổng số</span> 
				<span id="_jobpos_CounterList">0</span>
				<span class="p-xxs" >Chức vụ</span> 
				<i class="fa fa-plus-circle"></i> 
			</span>

			<div class="input-group MT15">
				
				<input type="text" class="form-control" id="_jobpos_keySearch"
					oninput="_jobpos_autocompleteSearch(this.value)" 
					placeholder="Tìm kiếm theo tên chức vụ">
	
				<div class="input-group-addon btn-primary" id="_jobpos_btnSearch">
					
					<i class="fa fa-search" aria-hidden="true"></i>
	
				</div>
	
			</div>
			
		</div>
				
			
		<ul class="ul-with-border ul-with-border-style-2 mh-head-2" id="_jobpos_listView"></ul>
		
		<script type="text/x-kendo-tmpl" id="_jobpos_template">
		
			<li class="clearfix PT20 PR0 PB20 PL15">
	
				<div class="col-sm-2 clearfix PL0 PR0">
					
					<a href="javascript:;" >
						
						<i style="font-size: 30px;padding: 5px;" class="fa fa-graduation-cap" aria-hidden="true"></i>
							
					</a>
						
				</div>
					
				<div class="col-sm-9 PL0">
				
					<strong class="btn-block">#= title #</strong>
					<span class="btn-block">
						
						#if(leader==0){#
							Thông thường
						#} else if(leader==1) { #
							Cấp trưởng
						#} else { #
							Cấp phó
						#}#

					</span>
				
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
	<!-- end left -->

	<!--load right-->
	<div class="col-md-9 " id="_jobpos_right-page"> </div>

</div>

<input type="hidden" value="0" id="_jobpos_hidden_new_id"/>

<script type="text/javascript">
	
	function _jobpos_autocompleteSearch() {
	
		$("#_jobpos_listView").getKendoListView().dataSource.filter({
			 field: "title", operator: "contains", 	value: $("#_jobpos_keySearch").val() 
		});
		
		$('#_jobpos_CounterList').html($("#_jobpos_listView").getKendoListView().dataSource.total());
		
	}
	
	(function($) {
	
		var _jobpos_BaseUrl = "${api.endpoint}/jobpos";
		
		var _jobpos_dataSource = new kendo.data.DataSource({

			transport: {
	
				read: function(options) {
					
					$.ajax({
					
						url: _jobpos_BaseUrl,
						dataType: "json",
						type: 'GET',
						headers: {
							"groupId": ${groupId}
						},
						data: {
							
							sort: 'title'
						},
						success: function(result) {
						
							$('#_jobpos_CounterList').html(result.total);
							options.success(result);
							
						},
						error: function(xhr, textStatus, errorThrown) {
							
							showMessageByAPICode(xhr.status);
						
						}
					
					});
				},
				create: function(options) {
					
					$.ajax({
						url: _jobpos_BaseUrl,
						headers: {
							"groupId": ${groupId},
						},
						data: {
							title: options.data.title,
							description: options.data.description,
							leader: options.data.leader,
							workingUnitId: options.data.workingUnitId
						},
						type: 'POST',
						dataType: 'json',
						contentType: 'application/x-www-form-urlencoded; charset=utf-8',
						success: function(data, textStatus, xhr) {
							
							var dataSource = $("#_jobpos_listView").getKendoListView().dataSource;
							
							$("#_jobpos_hidden_new_id").val(data.labelId);
							dataSource.error();
							dataSource.pushUpdate(data);
							$('#_jobpos_CounterList').html(dataSource.total());
							showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
							
						},
						error: function(xhr, textStatus, errorThrown) {
							
							$("#_jobpos_listView").getKendoListView().dataSource.error();
							showMessageByAPICode(xhr.status);
						
						}
					});
				},
				update: function(options) {
					
					$.ajax({
						url: _jobpos_BaseUrl + "/" + options.jobPosId,
						headers: {
							"groupId": ${groupId}
						},
						data: {
						
							title: options.title,
							leader: options.leader,
							description: options.description,
							workingUnitId: options.workingUnitId
						
						},
						type: 'PUT',
						dataType: 'json',
						success: function(data, textStatus, xhr) {
						
							var dataSource = $("#_jobpos_listView").getKendoListView().dataSource;
							dataSource.pushUpdate(data);

							$.map( dataSource.data(), function( obj, i ) {
								
								if(obj.jobPosId == data.jobPosId) {
									
									var listView = $("#_jobpos_listView").data("kendoListView");
									listView.select(listView.element.children()[i]);

								}
							});

							showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
							
						},
						error: function(xhr, textStatus, errorThrown) {
							
							$("#_jobpos_listView").getKendoListView().dataSource.error();
							showMessageByAPICode(xhr.status);
						
						}
					});
				
				},
				destroy: function(options) {
					
					$.ajax({
						url: _jobpos_BaseUrl + "/" + options.data.jobPosId,
						headers: {
							"groupId": ${groupId}
						},
						type: 'DELETE',
						success: function(result) {
							
							$("#_jobpos_hidden_new_id").val("0");
							options.success();
							$('#_jobpos_CounterList').html($("#_jobpos_listView").getKendoListView().dataSource.total());
							showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
							
						},
						error: function(xhr, textStatus, errorThrown) {
						
							$("#_jobpos_listView").getKendoListView().dataSource.error();
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
					id:"jobPosId",
					fields: {
						jobPosId: { editable: false, nullable: true },
						title: { type: "string" },
						leader: { type: "string" },
						workingUnitId: { type: "string" }
					}
				}
			},
			error: function(e) {
				
				this.cancelChanges();
				
			}

		});
	
		$("#_jobpos_listView").kendoListView({
		
			remove: function(e) {
				
				if (!confirm("Xác nhận xoá " + e.model.get("title") + "?")) {
			
					e.preventDefault();
				
				}
			
			},
			
			dataSource: _jobpos_dataSource,
			
			selectable: "true",
			
			dataBound: _jobpos_dataBound,
			
			change: _jobpos_onChange,
			
			template: kendo.template($("#_jobpos_template").html()),
			
			filterable: {
				field: "title", operator: "contains", 	value: $("#_jobpos_keySearch").val() 
			}
		
		});
		
		function _jobpos_dataBound(e) {
			
			var _jobpos_listView = e.sender;
			
			var children = _jobpos_listView.element.children();
			
			var index = $("#_jobpos_hidden_new_id").val();
			
			for (var x = 0; x < children.length; x++) {
				
				var getObj = _jobpos_listView.dataSource.view()[x];
				
				if (getObj.jobPosId == index) {
				
			 		index = x;
				
				};
				
			};
			
			_jobpos_listView.select(children[index]);
			
		}
		
		function _jobpos_onChange(e) {
	
			var data = _jobpos_dataSource.view(),
	
			selected = $.map(this.select(), function(item) {
			
				return data[$(item).index()].jobPosId;
			
			});
			
			$("#_jobpos_hidden_new_id").val(selected[0]);
			
			$("#_jobpos_right-page").load(
				'${url.adminJobPosPortlet.jobpos_detail}&${portletNamespace}type=${constant.type_jobPos}&${portletNamespace}jobPosId='+selected[0]);
			
		}

		$(document).on('click', '#_jobpos_editLabel', function(event){
		
			event.preventDefault();
			event.stopPropagation();
			event.stopImmediatePropagation();
			
			$("#_jobpos_right-page").load(
				'${url.adminJobPosPortlet.jobpos_detail}'
				);
			
		});
		
	})(jQuery);
</script>
