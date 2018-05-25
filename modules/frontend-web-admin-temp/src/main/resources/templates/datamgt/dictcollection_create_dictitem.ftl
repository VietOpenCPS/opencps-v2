<#include "init.ftl">

<div class="modal-header form-group">

	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	
	<h4 class="modal-title">
		
		Cập nhật thay đổi danh mục
				
	</h4>
	
</div>

<div class="modal-body">

	<form id="_collectionSub_dictItem_edit_form" class="PR15">
		
		<div class="row">
				
			<div class="col-sm-6">
				
				<div class="form-group">
		
					<label for="colectionCode">Mã danh mục
						
						<span class="icon-asterisk text-warning"></span>
				
					</label>
					<input type="text" id="_collectionSub_dictItem_edit_itemCode" name="_collectionSub_dictItem_edit_itemCode" class="form-control"
						placeholder="Mã danh mục" required validationMessage="Nhập mã danh mục" value="${(dictCollection_dictItem.itemCode)!}" <#if dictCollection_dictItem?has_content >
						disabled</#if>  />
				</div>

			</div>

			<div class="col-sm-6">
				
				<div class="form-group">
		
					<label for="colectionCode">Mã danh mục cha:</label>
					<input type="text" id="_collectionSub_dictItem_edit_parentItemCode" 
						name="_collectionSub_dictItem_edit_parentItemCode" class="form-control"
						data-value='${(dictCollection_dictItem.parentItemCode)!}'
						data-pk='${(dictCollection_dictItem.itemCode)!}'
						  />
				
				</div>
				
			</div>

		</div>

		
		<div class="row">
				
			<div class="col-sm-6">
				
				<div class="form-group">
		
					<label for="colectionName">Tên danh mục
						
						<span class="icon-asterisk text-warning"></span>

					</label>
					<input type="text" id="_collectionSub_dictItem_edit_itemName" name="_collectionSub_dictItem_edit_itemName" class="form-control"
						placeholder="Tên danh mục" required validationMessage="Nhập tên danh mục" value="${(dictCollection_dictItem.itemName)!}"  />
					
				</div>

			</div>

			<div class="col-sm-6">
				
				<div class="form-group">
		
					<label for="colectionName">Tên tiếng anh:</label>
					<input type="text" id="_collectionSub_dictItem_edit_itemNameEN" name="_collectionSub_dictItem_edit_itemNameEN" class="form-control"
						placeholder="Tên tiếng anh" value="${(dictCollection_dictItem.itemNameEN)!}"  />
				
				</div>
				
			</div>

		</div>


		<div class="row">
				
			<div class="col-sm-6">
				
				<div class="form-group">
		
					<label for="description">Nhóm danh mục:</label>
					<select multiple="multiple" id="_collectionSub_dictItem_edit_groupCode" 
						data-pk="${(dictCollection_dictItem.itemCode)!}" data-value='${(dictCollection_dictItem.groupCode)!}'>
					</select>
				
				</div>

			</div>

			<div class="col-sm-6">
				
				<div class="form-group">
				
					<label for="_collectionSub_dictItem_edit_sibling">Số thứ tự cùng cấp:</label>
					<input type="text" id="_collectionSub_dictItem_edit_sibling" name="_collectionSub_dictItem_edit_sibling" class="form-control"
						placeholder="Số thứ tự cùng cấp" value="${(dictCollection_dictItem.sibling)!}"  />
				
				</div>
				
			</div>

		</div>
		
		<div class="row">

			<div class="col-sm-12">
				
				<div class="form-group">
				
					<label for="_collectionSub_dictItem_edit_description">Mô tả chi tiết:</label>
					<textarea rows="4" class="form-control" id="_collectionSub_dictItem_edit_itemDescription" placeholder="Mô tả chi tiết"  >${(dictCollection_dictItem.itemDescription)!}</textarea>
				
				</div>
				
			</div>

		</div>

		<div class="row" id="_collectionSub_dictItem_edit_isShowAlpacalForm">
				
			<div class="col-sm-12">
				
				<div class="form-group">
		
					<label for="description">Thông tin bổ sung:</label>
					<div class="panel" id="_collectionSub_dictItem_edit_metaData">
					</div>
					
					<div style="display: none;">

						<textarea id="_collectionSub_dictCollection_dataForm"  >${(dictCollection_dictCollection.dataForm)!}</textarea>

						<textarea id="_collectionSub_dictItem_edit_metaData_inp"  >${(dictCollection_dictItem.metaData)!}</textarea>
				
					</div>

				</div>

			</div>

		</div>

		<div class="eq-height">
						
			<button class="btn btn-sm btn-active" id="_collectionSub_dictItem_edit_editDictItem" 
				name="_collectionSub_dictItem_edit_editDictItem" type="button"
				data-pk="${(param.dictCollection_itemCode)!}"
				data-collectionCode="${(param.dictCollection_collectionCode)!}"
				data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang lưu thông tin...">
					<i class="fa fa-check-circle"></i>
					<span class="lfr-btn-label">Xác nhận</span>
				</button>
		
			<button class="btn btn-default btn-sm MLA" data-dismiss="modal" value="Quay lại">
				<i class="icon-undo"></i>
				<span class="lfr-btn-label">Quay lại</span>
			</button>

		</div>
		
	</form>

</div>


<script type="text/javascript">

(function($) {

	var dataForm = $("#_collectionSub_dictCollection_dataForm").val().trim();

	if (dataForm!=null && dataForm!="") {
		
		dataForm = eval("(" + dataForm + ")");
		var dataValue = $("#_collectionSub_dictItem_edit_metaData_inp").val().trim();

		if (dataValue!=null && dataValue!="") {
		
			dataValue = eval("(" + dataValue + ")");
			dataForm.data = dataValue;
			$("#_collectionSub_dictItem_edit_metaData").alpaca(dataForm);
			
		} else {
			$("#_collectionSub_dictItem_edit_metaData").alpaca(dataForm);
		}
		
	} else {

		$("#_collectionSub_dictItem_edit_isShowAlpacalForm").hide();

	}
		
	$(document).on('click', '#_collectionSub_dictItem_edit_editDictItem', function(event){
		
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();
		
		var validator = $("#_collectionSub_dictItem_edit_form").kendoValidator().data("kendoValidator");
		
		if (!validator.validate()) {
			return;
		}

		var dictCollection_itemCode = $(this).attr('data-pk');
		var dictCollection_collectionCode = $(this).attr('data-collectionCode');
		var _dictCollection_edit_dictItem_BaseUrl ="${(url.adminDataMgtPortlet.dictcollection_dictitem_edit_action)!}&${portletNamespace}collectionCode="+dictCollection_collectionCode;
		var groupCode = $('#_collectionSub_dictItem_edit_groupCode').val();

		var metaData = "";

		if ($("#_collectionSub_dictItem_edit_metaData").alpaca("get")!=null) {

			metaData = $("#_collectionSub_dictItem_edit_metaData").alpaca("get").getValue();

		}

		groupCode = (groupCode != null && groupCode !="")? groupCode.join() : "";
		metaData = (metaData==null || metaData=="")?"":JSON.stringify(metaData);

		if (dictCollection_itemCode!=null && dictCollection_itemCode!="") {

			$.ajax({
				type: 'POST',
				url: _dictCollection_edit_dictItem_BaseUrl+"&${portletNamespace}itemCodeOld="+dictCollection_itemCode,
				data: {

					${portletNamespace}itemCodeOld: dictCollection_itemCode,
					${portletNamespace}itemCode: $( "#_collectionSub_dictItem_edit_itemCode" ).val().trim(),
					${portletNamespace}itemName: $( "#_collectionSub_dictItem_edit_itemName" ).val().trim(),
					${portletNamespace}itemNameEN: $( "#_collectionSub_dictItem_edit_itemNameEN" ).val().trim(),
					${portletNamespace}itemDescription: $( "#_collectionSub_dictItem_edit_itemDescription" ).val().trim(),
					${portletNamespace}sibling: $( "#_collectionSub_dictItem_edit_sibling" ).val().trim(),
					${portletNamespace}parentItemCode: $( "#_collectionSub_dictItem_edit_parentItemCode" ).val().trim(),
					${portletNamespace}metaData: metaData,
					${portletNamespace}groupCode: groupCode

				},
				dataType: 'json',
				beforeSend: function( xhr ) {
					$(event.currentTarget).button('loading');
				},
				success: function(data) {

					if (data.hasOwnProperty('msg') && data.msg == "error") {

						showMessageByAPICode(data.statusCode);

					} else {

						var dataSource = $("#_collectionSub_dictItem_listView").getKendoListView().dataSource;
						dataSource.pushUpdate(data);
						$("#modal-lg").trigger({ type: "click" });
						showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');

					}
					$(event.currentTarget).button('reset');
				},
				error: function(xhr, textStatus, errorThrown) {
					
					showMessageToastr("error", 'Yêu cầu của bạn xử lý thất bại!');
					$(event.currentTarget).button('reset');
				}
			})

		} else {

			// create a new collection dictItem
		
			$.ajax({
				type: 'POST',
				url: _dictCollection_edit_dictItem_BaseUrl,
				data: {

					${portletNamespace}itemCode: $( "#_collectionSub_dictItem_edit_itemCode" ).val().trim(),
					${portletNamespace}itemName: $( "#_collectionSub_dictItem_edit_itemName" ).val().trim(),
					${portletNamespace}itemNameEN: $( "#_collectionSub_dictItem_edit_itemNameEN" ).val().trim(),
					${portletNamespace}itemDescription: $( "#_collectionSub_dictItem_edit_itemDescription" ).val().trim(),
					${portletNamespace}sibling: $( "#_collectionSub_dictItem_edit_sibling" ).val().trim(),
					${portletNamespace}parentItemCode: $( "#_collectionSub_dictItem_edit_parentItemCode" ).val().trim(),
					${portletNamespace}metaData: metaData,
					${portletNamespace}groupCode: groupCode
				},
				dataType: 'json',
				
				success: function(data) {

					if (data.hasOwnProperty('msg') && data.msg == "error") {

						showMessageByAPICode(data.statusCode);

					} else {

						var dataSource = $("#_collectionSub_dictItem_listView").getKendoListView().dataSource;
					
						dataSource.pushUpdate(data);
						$('#_collectionSub_dictItem_CounterListDetail').html(dataSource.total());
						$("#modal-lg").trigger({ type: "click" });
						showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');

					}

				},
				error: function(xhr, textStatus, errorThrown) {
					
					showMessageToastr("error", 'Yêu cầu của bạn xử lý thất bại!');

				}
			}) 

		}
		
	});
			
})(jQuery);

</script>


<script type="text/javascript">


(function($) {

	var _collectionSub_dictItem_BaseUrl_detail = "${api.endpoint}/temp/dictcollections/${(dictCollection_dictCollection.collectionCode)!}/dictitems";
	
	var _collectionSub_dictItem_edit_parentItemCode = $("#_collectionSub_dictItem_edit_parentItemCode").kendoDropDownList({
		
		optionLabel: "Không có danh mục cấp trên",
		dataTextField: "itemName",
		dataValueField: "itemCode",
		dataSource: {
			transport: {
				read: function(options) {
					
					$.ajax({
				
						url: _collectionSub_dictItem_BaseUrl_detail,
						dataType: "json",
						type: 'GET',
						headers: {
							"groupId": ${groupId}
						},
						data: {
							
							sort: 'sibling',
							level: 0
							
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
				total: "total"
			},
			filter: { field: "itemCode", operator: "neq", value: $( "#_collectionSub_dictItem_edit_parentItemCode" ).attr("data-pk") }
		}
		
	}).data("kendoDropDownList");
	
	_collectionSub_dictItem_edit_parentItemCode.value($( "#_collectionSub_dictItem_edit_parentItemCode" ).attr("data-value"));

	var _collectionSub_group_BaseUrl_detail = "${api.endpoint}/temp/dictcollections/${(dictCollection_dictCollection.collectionCode)!}/dictgroups";
	
	var _collectionSub_dictItem_edit_groupCode = $("#_collectionSub_dictItem_edit_groupCode").kendoMultiSelect({
		placeholder: "Chọn nhóm danh mục...",
		height: 400,
		clearButton: false,
		dataSource: {
			transport: {
				read: function(options) {
					
					$.ajax({
				
						url: _collectionSub_group_BaseUrl_detail,
						dataType: "json",
						type: 'GET',
						headers: {
							"groupId": ${groupId}
						},
						data: {
							
							sort: 'groupName',
							level: 0
							
						},
						success: function(result) {
							
							result["data"] = result.total==0 ? []: result["data"];
							options.success(result);
							
						}
					
					});
				
				}
			},
			schema: {
				data: "data",
				total: "total"
			}
		},
		dataTextField: "groupName",
		dataValueField: "groupCode",
		value:[]
	}).data("kendoMultiSelect");

	_collectionSub_dictItem_edit_groupCode.value($('#_collectionSub_dictItem_edit_groupCode').attr('data-value').split(","));
	
})(jQuery);

</script>