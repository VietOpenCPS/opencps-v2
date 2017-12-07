<#include "init.ftl">

<div class="modal-header form-group">

	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	
	<h4 class="modal-title">
		
		Cập nhật thay đổi nhóm
				
	</h4>
	
</div>

<div class="panel-body">

	<form id="_collectionSub_dictGroup_edit_form">

		<div class="form-group">
		
			<label for="_collectionSub_dictItem_edit_groupName">Tên nhóm:</label>
			<input type="text" id="_collectionSub_dictItem_edit_groupName" name="_collectionSub_dictItem_edit_groupName" class="form-control"
				placeholder="Tên nhóm" required validationMessage="Nhập tên nhóm" value="${(dictCollection_dictGroup.groupName)!}"  />
			
		</div>
		
		<div class="form-group">
		
			<label for="_collectionSub_dictItem_edit_groupCode">Mã nhóm:</label>
			<input type="text" id="_collectionSub_dictItem_edit_groupCode" name="_collectionSub_dictItem_edit_groupCode" class="form-control"
				placeholder="Mã nhóm" required validationMessage="Nhập mã nhóm" value="${(dictCollection_dictGroup.groupCode)!}"  />
		
		</div>
		
		<div class="form-group">
		
			<label for="_collectionSub_dictItem_edit_groupNameEN">Tên tiếng anh:</label>
			<input type="text" id="_collectionSub_dictItem_edit_groupNameEN" name="_collectionSub_dictItem_edit_groupNameEN" class="form-control"
				placeholder="Tên tiếng anh" required validationMessage="Nhập tên tiếng anh" value="${(dictCollection_dictGroup.groupNameEN)!}"  />
		
		</div>
		
		<div class="form-group">
		
			<label for="_collectionSub_dictItem_edit_description">Mô tả chi tiết:</label>
			<textarea rows="4" class="form-control" id="_collectionSub_dictItem_edit_groupDescription" placeholder="Mô tả chi tiết"  >${(dictCollection_dictGroup.groupDescription)!}</textarea>
		
		</div>
	
		<div class="row">
		
			<div class="col-xs-12 col-sm-6">
				
				<button class="btn btn-sm btn-active" 
					id="_collectionSub_dictItem_edit_editDictGroup" name="_collectionSub_dictItem_edit_editDictGroup" type="button" 
					data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang lưu thông tin...">
					<i class="fa fa-check-circle"></i>
					<span class="lfr-btn-label">Cập nhật thay đổi</span>
				</button>
				
			</div>
			
			<div class="col-xs-12 col-sm-6 text-right">
			
				<button class="btn btn-default btn-sm" data-dismiss="modal" value="Quay lại">
					<i class="icon-undo"></i>
					<span class="lfr-btn-label">Quay lại</span>
				</button>
			
			</div>

		</div>
		
	</form>

</div>


<script type="text/javascript">


(function($) {
		
	$(document).on('click', '#_collectionSub_dictItem_edit_editDictGroup', function(event){
		
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();
		
		
		var validator = $("#_collectionSub_dictGroup_edit_form").kendoValidator().data("kendoValidator");
		
		if (!validator.validate()) {
			return;
		}
		
		var dictCollection_groupCode = "${(param.dictCollection_groupCode)!}";

		if (dictCollection_groupCode!=null && dictCollection_groupCode!="") {

			$("#_collectionSub_group_listView").getKendoListView().dataSource.transport.update({
				
				groupCodeOld: dictCollection_groupCode,
				groupCode: $( "#_collectionSub_dictItem_edit_groupCode" ).val().trim(),
				groupName: $( "#_collectionSub_dictItem_edit_groupName" ).val().trim(),
				groupNameEN: $( "#_collectionSub_dictItem_edit_groupNameEN" ).val().trim(),
				groupDescription: $( "#_collectionSub_dictItem_edit_groupDescription" ).val().trim()
				
			});

		} else {

			// create a new collection group
		
			$("#_collectionSub_group_listView").getKendoListView().dataSource.add({
				
				groupCode: $( "#_collectionSub_dictItem_edit_groupCode" ).val().trim(),
				groupName: $( "#_collectionSub_dictItem_edit_groupName" ).val().trim(),
				groupNameEN: $( "#_collectionSub_dictItem_edit_groupNameEN" ).val().trim(),
				groupDescription: $( "#_collectionSub_dictItem_edit_groupDescription" ).val().trim()
				
			});

			// save the created collectionGroup
			$("#_collectionSub_group_listView").getKendoListView().dataSource.sync(); 

		}
		
	});
			
})(jQuery);

</script>
