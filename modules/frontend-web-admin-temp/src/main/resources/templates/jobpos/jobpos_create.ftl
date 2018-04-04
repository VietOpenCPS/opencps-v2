<#include "init.ftl">

<div class="modal-header form-group">

	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	
	<h4 class="modal-title">
		
		Thêm chức vụ

	</h4>
	
</div>

<div class="modal-body">
	
	<form id="_jobposAdd_form">
		
		<div class="form-group">
		
			<label for="_jobposAdd_title">Tên chức vụ:</label>
			<input type="text" id="_jobposAdd_title" name="_jobposAdd_title" class="form-control"
				placeholder="Tên chức vụ" required validationMessage="Nhập tên chức vụ"  />
			
		</div>
		
		<div class="form-group">
		
			<label for="_jobposAdd_leader">Vị trí :</label>
			
			<select class="form-control" id="_jobposAdd_leader" name="_jobposAdd_leader"> 
				
				<option value="0"> Thông thường </option>
				<option value="1"> Cấp trưởng </option>
				<option value="2"> Cấp phó </option>

			</select>
			
		</div>

		<div class="form-group">
	
			<label for="_jobposAdd_workingUnitId">Thuộc phòng ban/Tổ chức :</label>
			<input style="width: 100%" id="_jobposAdd_workingUnitId" name="_jobposAdd_workingUnitId" class="form-control"
				placeholder="Chọn phòng ban... " />
			
		</div>
		
		<div class="form-group">
		
			<label for="_jobposAdd_description">Mô tả :</label>
			<textarea rows="4" class="form-control" id="_jobposAdd_description" name="_jobposAdd_description"
				 placeholder="Mô tả chi tiết"  ></textarea>
		
		</div>

		<div class="form-group">
			<button class="btn btn-sm btn-active" id="_jobPos_submitAddJobpos" name="_jobPos_submitAddJobpos" type="button" 
				data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang lưu thông tin...">
				<i class="fa fa-check-circle"></i>
				<span class="lfr-btn-label">Xác nhận</span>
				
			</button> 
			<button class="btn btn-default pull-right" data-dismiss="modal" value="Quay lại">
			
				<i class="icon-undo"></i>
				<span class="lfr-btn-label">Quay lại</span>
				
			</button> 
		</div>

	</form>

</div>


<script type="text/javascript">


(function($) {
	
	$(document).on('click', '#_jobPos_submitAddJobpos', function(event){
		
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();
		
		var validator = $("#_jobposAdd_form").kendoValidator().data("kendoValidator");
		
		if (!validator.validate()) {
			return;
		}
		
		var workingUnitId = $( "#_jobposAdd_workingUnitId" ).val().trim();
		
		workingUnitId = ( workingUnitId == "" || workingUnitId == null )?0:workingUnitId;
		
		// create a new jobpos
		$("#_jobpos_listView").getKendoListView().dataSource.add({
		
			title: $( "#_jobposAdd_title" ).val().trim(),
			leader: $( "#_jobposAdd_leader" ).val().trim(),
			description: $( "#_jobposAdd_description" ).val().trim(),
			workingUnitId: workingUnitId
			
		});
		
		// save the created jobpos
		$("#_jobpos_listView").getKendoListView().dataSource.sync();
		
	});
	
	var _jobposAdd_workingUnit_BaseUrl = "${api.endpoint}/workingunits";

	var _jobposAdd_workingUnit_dataSource = new kendo.data.DataSource({
		
		transport: {

			read: function(options) {
				
				$.ajax({
				
					url: _jobposAdd_workingUnit_BaseUrl,
					dataType: "json",
					type: 'GET',
					headers: {
						"groupId": ${groupId}
					},
					data: {
						sort: 'name'
					},
					success: function(result) {
						
						result["data"] = result.total==0 ? []: result["data"];
						options.success(result);
						
					},
					error: function(xhr, textStatus, errorThrown) {
						
						showMessageByAPICode(xhr.status);
					
					}
				
				});
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
				name: { type: "string" },
				}
			}
		}
		
	});
	
	var _jobposAdd_workingUnitId = $("#_jobposAdd_workingUnitId").kendoDropDownList({
		
		optionLabel: "${(workingUnitNameDefault)!}",
		dataTextField: "name",
		dataValueField: "workingUnitId",
		dataSource: _jobposAdd_workingUnit_dataSource
		
	}).data("kendoDropDownList");
	
})(jQuery);

</script>


