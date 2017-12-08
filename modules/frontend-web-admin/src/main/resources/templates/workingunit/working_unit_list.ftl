<#include "init.ftl">

<div class="row">

	<!— left —>
	<div class="col-md-3 panel P0">

		<!--search-->
		<div class="panel-body">
	
			<span id="_workingUnit_editWorkingUnit" class="btn btn-active btn-block"> 
				<i class="fa fa-outdent" aria-hidden="true"></i>
				<span class="p-xxs" >Tổng số</span> 
				<span id="_workingUnit_CounterList">0</span>
				<span class="p-xxs" >Phòng ban</span> 
				<i class="fa fa-plus-circle"></i> 
			</span>

			<div class="input-group MT15">
				
				<input type="text" class="form-control" id="_workingUnit_keySearch" oninput="_workingUnit_autocompleteSearch(this.value)" placeholder="Tìm kiếm theo mã, tên, điện thoại, email phòng ban">
						
				<span class="input-group-addon"><i class="fa fa-search"></i></span>
	
			</div>
			
		</div>
				
			
		<ul class="ul-with-border ul-default mh-head-2" id="_workingUnit_listView"></ul>
		
		<script type="text/x-kendo-tmpl" id="_workingUnit_template">
		
			<li class="clearfix PT10 PR0 PB10 PL#:10+level*20#">
	
				<div class="col-sm-2 clearfix PL0 PR0">
					
					<a href="javascript:;" >
						
						<i class="fa fa-outdent fs26 P5" aria-hidden="true"></i>
							
					</a>
						
				</div>
					
				<div class="col-sm-9 PL0">
				
					<strong class="btn-block">#= name #</strong>
					<span class="btn-block">#= govAgencyCode #</span>
				
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
	<!— end left —>

	<!--load right-->
	<div class="col-md-9 " id="_workingUnit_right-page"> </div>

</div>

<input type="hidden" value="0" id="_workingUnit_hidden_new_id"/>

<script type="text/javascript">
	
	function _workingUnit_autocompleteSearch(val) {
		
		var _workingUnit_listView = $("#_workingUnit_listView").data("kendoListView");
		_workingUnit_listView.dataSource.filter({
		
			logic: "or",
			filters: [
				
				{ field: "name", operator: "contains", value: $("#_workingUnit_keySearch").val().trim() },
				{ field: "govAgencyCode", operator: "contains", value: $("#_workingUnit_keySearch").val().trim() },
				{ field: "telNo", operator: "contains", value: $("#_workingUnit_keySearch").val().trim() },
				{ field: "email", operator: "contains", value: $("#_workingUnit_keySearch").val().trim() }
			]
		});
		
	}
	
	(function($) {
	
		var _workingUnit_BaseUrl = "${api.endpoint}/workingunits";
		
		var _workingUnit_dataSource = new kendo.data.DataSource({
			
			transport: {
	
				read: function(options) {
					
					$.ajax({
					
						url: _workingUnit_BaseUrl,
						dataType: "json",
						type: 'GET',
						headers: {
							"groupId": ${groupId}
						},
						data: {},
						success: function(result) {
							
							$('#_workingUnit_CounterList').html(result.total);
							result["data"] = result.total==0 ? []: result["data"];
							options.success(result);
							
						},
						error: function(xhr, textStatus, errorThrown) {
							
							showMessageByAPICode(xhr.status);
						
						}
					
					});
				},
				create: function(options) {
					
					$.ajax({
						url: _workingUnit_BaseUrl,
						headers: {
							"groupId": ${groupId},
						},
						data: {
							parentWorkingUnitId: options.data.parentWorkingUnitId,
							govAgencyCode: options.data.govAgencyCode,
							name: options.data.name,
							enName: options.data.enName,
							address: options.data.address,
							telNo: options.data.telNo,
							faxNo: options.data.faxNo,
							email: options.data.email,
							website: options.data.website,
							sibling: options.data.sibling,
							ceremonyDate: options.data.ceremonyDate
						},
						type: 'POST',
						dataType: 'json',
						contentType: 'application/x-www-form-urlencoded; charset=utf-8',
						beforeSend: function( xhr ) {
							$("#_workingUnitDetail_submitBtn").button('loading');
						},
						success: function(data, textStatus, xhr) {
							
							_workingUnitDetail_uploadLogoFileEntry(document.getElementById("_workingUnitDetail_logoFileEntryId"), data.workingUnitId);

							//var dataSource = $("#_workingUnit_listView").getKendoListView().dataSource;
							
							$("#_workingUnit_hidden_new_id").val(data.workingUnitId);
							
							$("#_workingUnit_listView").getKendoListView().dataSource.read();
							
							//dataSource.error();
							//dataSource.pushUpdate(data);
							//$('#_workingUnit_CounterList').html(dataSource.total());
							showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
							//$("#_workingUnitDetail_submitBtn").button('reset');
						},
						error: function(xhr, textStatus, errorThrown) {
							
							$("#_workingUnit_listView").getKendoListView().dataSource.error();
							showMessageByAPICode(xhr.status);
							$("#_workingUnitDetail_submitBtn").button('reset');
						}
					});
					
				},
				update: function(options) {
					
					$.ajax({
						url: _workingUnit_BaseUrl + "/" + options.workingUnitId,
						data: {
							
							parentWorkingUnitId: options.parentWorkingUnitId,
							govAgencyCode: options.govAgencyCode,
							name: options.name,
							enName: options.enName,
							address: options.address,
							telNo: options.telNo,
							faxNo: options.faxNo,
							email: options.email,
							website: options.website,
							sibling: options.sibling,
							ceremonyDate: options.ceremonyDate
						},
						type: 'PUT',
						dataType: 'json',
						contentType: 'application/x-www-form-urlencoded; charset=utf-8',
						beforeSend: function( xhr ) {
							$("#_workingUnitDetail_submitBtn").button('loading');
						},
						success: function(data, textStatus, xhr) {
							
							_workingUnitDetail_uploadLogoFileEntry(document.getElementById("_workingUnitDetail_logoFileEntryId"), data.workingUnitId);
							
							$("#_workingUnit_listView").getKendoListView().dataSource.read();
							showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
							$("#_workingUnitDetail_submitBtn").button('reset');
						},
						error: function(xhr, textStatus, errorThrown) {
							
							$("#_workingUnit_listView").getKendoListView().dataSource.error();
							showMessageByAPICode(xhr.status);
							$("#_workingUnitDetail_submitBtn").button('reset');
						}
					});
				
				},
				destroy: function(options) {
					
					var confirmWindown = showWindowConfirm('#template-confirm','Cảnh báo','Bạn có chắc muốn xóa bản ghi này?', $("#_workingUnit_listView") );
					
					confirmWindown.then(function(confirmed){
					
						if(confirmed){
	
							$.ajax({
								url: _workingUnit_BaseUrl + "/" + options.data.workingUnitId,
								type: 'DELETE',
								success: function(result) {
									
									$("#_workingUnit_hidden_new_id").val("0");
									options.success();
									$('#_workingUnit_CounterList').html($("#_workingUnit_listView").getKendoListView().dataSource.total());
									showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
									
								},
								error: function(xhr, textStatus, errorThrown) {
								
									$("#_workingUnit_listView").getKendoListView().dataSource.error();
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
				id: "workingUnitId",
				fields: {
					workingUnitId: {
						editable: false,
						nullable: true
					},
					govAgencyCode: { type: "string" },
					name: { type: "string" },
					telNo: { type: "string" },
					email: { type: "string" }
					}
				}
			},
			error: function(e) {
				
				this.cancelChanges();
				
			}
			
		});
	
		$("#_workingUnit_listView").kendoListView({
		
			remove: function(e) {
			
			},
			
			dataSource: _workingUnit_dataSource,
			
			selectable: "true",
			
			dataBound: _workingUnit_dataBound,
			
			change: _workingUnit_onChange,
			
			template: kendo.template($("#_workingUnit_template").html()),
			
			filterable: {
			
				logic: "or",
				filters: [
					
					{ field: "name", operator: "contains", value: $("#_workingUnit_keySearch").val().trim() },
					{ field: "govAgencyCode", operator: "contains", value: $("#_workingUnit_keySearch").val().trim() },
					{ field: "telNo", operator: "contains", value: $("#_workingUnit_keySearch").val().trim() },
					{ field: "email", operator: "contains", value: $("#_workingUnit_keySearch").val().trim() }
				]
			
			}
		
		});
		
		function _workingUnit_dataBound(e) {
			
			var _workingUnit_listView = e.sender;
			
			var children = _workingUnit_listView.element.children();
			
			var index = $("#_workingUnit_hidden_new_id").val();
			
			for (var x = 0; x < children.length; x++) {
				
				var getObj = _workingUnit_listView.dataSource.view()[x];
				
				if (getObj.workingUnitId == index) {
				
			 		index = x;
				
				};
				
			};
			
			_workingUnit_listView.select(children[index]);
			
		}
		
		function _workingUnit_onChange(e) {
	
			var data = _workingUnit_dataSource.view(),
	
			selected = $.map(this.select(), function(item) {
			
				return data[$(item).index()].workingUnitId;
			
			});
			
			$("#_workingUnit_hidden_new_id").val(selected[0]);
			
			$("#_workingUnit_right-page").load(
				'${url.adminWorkingUnitPortlet.working_unit_detail}&${portletNamespace}type=${constant.type_workingUnit}&${portletNamespace}workingUnitId='+selected[0]);
			
		}

		$(document).on('click', '#_workingUnit_editWorkingUnit', function(event){
		
			event.preventDefault();
			event.stopPropagation();
			event.stopImmediatePropagation();
			
			$("#_workingUnit_right-page").load(
				'${url.adminWorkingUnitPortlet.working_unit_detail}'
				);
				
			$("#_workingUnit_listView").getKendoListView().clearSelection();
			
		});
		
	})(jQuery);
</script>