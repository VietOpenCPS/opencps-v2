<#include "init.ftl">
<div class="row panel">
	
	<div class="form-group">
		
		<h3 class="detail-header MT5">
			
			<#if (param.jobPos_jobPosId??) && (param.jobPos_jobPosId?number > 0) >
				<span> Chi tiết chức vụ</span>
			<#else>
				<span> Thêm vùng chức vụ</span>
			</#if>

		</h3>
		
	</div>

	<div class="panel-body">

		<form id="_jobposDetail_form">
			
			<div class="form-group">
			
				<label for="_jobposDetail_title">Tên chức vụ
				
					<span class="icon-asterisk text-warning"></span>
				
				</label>
				
				<input type="text" id="_jobposDetail_title" name="_jobposDetail_title" class="form-control"
					placeholder="Tên chức vụ" required validationMessage="Nhập tên chức vụ" value="${(jobPos.title)!}"  />
				
			</div>
			
			<div class="form-group">
			
				<label for="_jobposDetail_leader">Vị trí :</label>
				<input id="_jobposDetail_leader" name="_jobposDetail_leader" class="form-control" data-vl="${(jobPos.leader)!}" />
				
			</div>

			<div class="form-group">
			
				<label for="_jobposDetail_permissions">Quyền hạn :</label>
				<input id="_jobposDetail_permissions" name="_jobposDetail_permissions" class="form-control" />
				
			</div>

			<div class="form-group">
			
				<label for="_jobposDetail_works">Công việc phải làm :</label>
				<input  id="_jobposDetail_works" name="_jobposDetail_works" class="form-control" />
				
			</div>
			
			<div class="form-group">
			
				<label for="_jobposDetail_description">Mô tả :</label>
				<textarea rows="4" class="form-control" id="_jobposDetail_description" name="_jobposDetail_description"
					 placeholder="Mô tả chi tiết"  >${(jobPos.description)!}</textarea>
			
			</div>
				
			<div class="form-group text-right">
			
				<button class="btn btn-sm btn-active" 
					id="_jobposDetail_submitBtn" name="_jobposDetail_submitBtn" type="button" data-pk="${(param.jobPos_jobPosId)!}"
					data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang lưu thông tin...">
					<i class="fa fa-check-circle"></i>
					<span class="lfr-btn-label">Xác nhận</span>
				</button>
			
			</div>
				
		</form>

	</div>

</div>


<script type="text/javascript">


(function($) {
	
	$(document).on('click', '#_jobposDetail_submitBtn', function(event){
		
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();
		
		var validator = $("#_jobposDetail_form").kendoValidator().data("kendoValidator");
		
		if (!validator.validate()) {
			return;
		}
		
		var _jobposDetail_BaseUrl = "${(url.adminJobPosPortlet.jobpos_edit_action)!}";
		var permissions = $("#_jobposDetail_permissions").data("kendoMultiSelect").dataItems();
		var works = $("#_jobposDetail_works").data("kendoMultiSelect").dataItems();

		permissions = (permissions != null && permissions !="" && permissions.length > 0)?JSON.stringify(permissions):"";
		works = (works != null && works !="" && works.length > 0)?JSON.stringify(works):"";

		var _jobposDetail_jobPosId = ($(this).attr("data-pk")!="" && $(this).attr("data-pk")!=null)?$(this).attr("data-pk"):0;

		if (_jobposDetail_jobPosId!=0) {

			// update jobpos
			
			$.ajax({
				type: 'POST',
				url: _jobposDetail_BaseUrl +"&${portletNamespace}jobPosId="+ _jobposDetail_jobPosId,
				data: {

					${portletNamespace}title: $( "#_jobposDetail_title" ).val().trim(),
					${portletNamespace}leader: $( "#_jobposDetail_leader" ).val().trim(),
					${portletNamespace}description: $( "#_jobposDetail_description" ).val().trim(),
					${portletNamespace}permissions: permissions,
					${portletNamespace}works: works

				},
				dataType: 'json',
				beforeSend: function( xhr ) {
					$(event.currentTarget).button('loading');
				},
				success: function(data) {

					if (data.hasOwnProperty('msg') && data.msg == "error") {

						showMessageByAPICode(data.statusCode);

					} else {

						var dataSource = $("#_jobpos_listView").getKendoListView().dataSource;
						dataSource.pushUpdate(data);

						$.map( dataSource.data(), function( obj, i ) {
							
							if(obj.jobPosId == data.jobPosId) {
								
								var listView = $("#_jobpos_listView").data("kendoListView");
								listView.select(listView.element.children()[i]);

							}
						});

						showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');

					}
					$(event.currentTarget).button('reset');
				},
				error: function(xhr, textStatus, errorThrown) {
					$(event.currentTarget).button('reset');
					showMessageToastr("error", 'Yêu cầu của bạn xử lý thất bại!');

				}
			});

		} else {

			$.ajax({
				type: 'POST',
				url: _jobposDetail_BaseUrl,
				data: {

					${portletNamespace}title: $( "#_jobposDetail_title" ).val().trim(),
					${portletNamespace}leader: $( "#_jobposDetail_leader" ).val().trim(),
					${portletNamespace}description: $( "#_jobposDetail_description" ).val().trim(),
					${portletNamespace}permissions: permissions,
					${portletNamespace}works: works

				},
				dataType: 'json',
				
				success: function(data) {

					if (data.hasOwnProperty('msg') && data.msg == "error") {

						showMessageByAPICode(data.statusCode);

					} else {

						$("#_jobpos_hidden_new_id").val(data.jobPosId);

						var dataSource = $("#_jobpos_listView").getKendoListView().dataSource;
						
						dataSource.pushUpdate(data);
						$('#_jobpos_CounterList').html(dataSource.total());
						showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');

					}

				},
				error: function(xhr, textStatus, errorThrown) {
					
					showMessageToastr("error", 'Yêu cầu của bạn xử lý thất bại!');

				}
			});

		}
		

	});
	

	var _jobposDetail_permission_work_BaseUrl = "${api.endpoint}/jobpos";

	var _jobposDetail_permission_dataSource = new kendo.data.DataSource({
		
		transport: {

			read: function(options) {
				
				$.ajax({
				
					url: _jobposDetail_permission_work_BaseUrl+"/"+(($( "#_jobposDetail_submitBtn" ).attr("data-pk")!="" && $( "#_jobposDetail_submitBtn" ).attr("data-pk")!=null)?$( "#_jobposDetail_submitBtn" ).attr("data-pk"):0)+"/permissions",
					dataType: "json",
					type: 'GET',
					headers: {
						"groupId": ${groupId}
					},
					data: {
						sort: 'actionName'
					},
					success: function(result) {
						
						result["data"] = result.total==0 ? []: result["data"];
						options.success(result);

						var dataSelected=[];
						$.map( result.data, function( obj, i ) {
							
							if(obj.selected) {

								dataSelected.push(obj)

							} 
						});

						_jobposDetail_permissions.value(dataSelected);

					},
					error: function(xhr, textStatus, errorThrown) {
						
						//showMessageByAPICode(xhr.status);
					
					}
				
				});
			}
		},
		schema: {
			data: "data",
			total: "total",
			model: {
				id: "actionId",
				fields: {
					actionId: {
						editable: false,
						nullable: true
					},
					actionName: { type: "string" },
					selected: "selected"
				}
			}
		}
		
	});
	
	var _jobposDetail_permissions = $("#_jobposDetail_permissions").kendoMultiSelect({
		
		optionLabel: "Chọn quyền hạn...",
		dataTextField: "actionName",
		dataValueField: "actionId",
		dataSource: _jobposDetail_permission_dataSource
		
	}).data("kendoMultiSelect");


	var _jobposDetail_work_dataSource = new kendo.data.DataSource({
		
		transport: {

			read: function(options) {
				
				$.ajax({
				
					url: _jobposDetail_permission_work_BaseUrl+"/"+(($( "#_jobposDetail_submitBtn" ).attr("data-pk")!="" && $( "#_jobposDetail_submitBtn" ).attr("data-pk")!=null)?$( "#_jobposDetail_submitBtn" ).attr("data-pk"):0)+"/works",
					dataType: "json",
					type: 'GET',
					headers: {
						"groupId": ${groupId}
					},
					data: {
						sort: 'actionName',
						full: true
					},
					success: function(result) {
						
						result["data"] = result.total==0 ? []: result["data"];
						options.success(result);

						var dataSelected=[];
						$.map( result.data, function( obj, i ) {
							
							if(obj.selected) {

								dataSelected.push(obj)

							} 
						});

						_jobposDetail_works.value(dataSelected);

					},
					error: function(xhr, textStatus, errorThrown) {
						
						//showMessageByAPICode(xhr.status);
					
					}
				
				});
			}
		},
		schema: {
			data: "data",
			total: "total",
			model: {
				id: "checklistCat",
				fields: {
					checklistCat: {
						editable: false,
						nullable: true
					},
					checklistType: { type: "string" },
					categoryName: { type: "string" },
					selected: "selected"

				}
			}
		}
		
	});
	
	var _jobposDetail_works = $("#_jobposDetail_works").kendoMultiSelect({
		
		optionLabel: "Chọn công việc...",
		dataTextField: "categoryName",
		dataValueField: "checklistCat",
		dataSource: _jobposDetail_work_dataSource
		
	}).data("kendoMultiSelect");
	
	var _jobposDetail_leader = $("#_jobposDetail_leader").kendoDropDownList({
		dataSource: [
					{ value:0, text: "Thông thường"},
					{ value:1, text: "Cấp trưởng"},
					{ value:2, text: "Cấp phó"}
					],
		dataTextField: "text",
		dataValueField: "value"
		
	}).data("kendoDropDownList");

	var leader_vl = $("#_jobposDetail_leader").attr('data-vl');
	
	leader_vl = (leader_vl!="" && leader_vl!=null )?leader_vl:0;
	_jobposDetail_leader.value(leader_vl);
	
	
})(jQuery);

</script>


