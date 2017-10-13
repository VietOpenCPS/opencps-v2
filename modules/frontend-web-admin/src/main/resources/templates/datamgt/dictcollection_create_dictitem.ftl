<#if (Request)??>
<#include "init.ftl">
</#if>
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
						placeholder="Mã danh mục" required validationMessage="Nhập mã danh mục" value="${(dictCollection_dictItem.itemCode)!}"  />
				
				</div>

			</div>

			<div class="col-sm-6">
				
				<div class="form-group">
		
					<label for="colectionCode">Mã danh mục cha:</label>
					<input type="text" id="_collectionSub_dictItem_edit_parentItemCode" name="_collectionSub_dictItem_edit_parentItemCode" class="form-control"
					data-value='${(dictCollection_dictItem.parentItemCode)!}'  />
				
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
						data-pk="${(dictCollection_dictItem.itemCode)!}" data-value='${(dictCollection_dictItem_dictGroup)!}'>
					</select>
				
				</div>

			</div>

			<div class="col-sm-6">
				
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
						
			<button class="btn btn-sm btn-primary btn-default" id="_collectionSub_dictItem_edit_editDictItem" 
				name="_collectionSub_dictItem_edit_editDictItem" type="button"
				data-pk="${(param.dictCollection_itemCode)!}"
				data-collectionCode="${(param.dictCollection_collectionCode)!}">
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


	var dataForm = $("#_collectionSub_dictCollection_dataForm").val();

	if (dataForm!=null && dataForm!="") {

		var dataValue = $("#_collectionSub_dictItem_edit_metaData_inp").val();

		if (dataValue!=null && dataValue!="") {

			try {

				var dataAlpaca = dataForm+",data:"+dataValue;
				dataAlpaca = JSON.parse(dataAlpaca);

				$("#_collectionSub_dictItem_edit_metaData").alpaca(dataAlpaca);

			} catch (e) {
				console.log(e);
			} 
			
		} else {

			try {

				dataForm = JSON.parse(dataForm);
				$("#_collectionSub_dictItem_edit_metaData").alpaca(dataForm);
				
			} catch (e) {
				console.log(e);
			} 
			

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
					${portletNamespace}itemCode: $( "#_collectionSub_dictItem_edit_itemCode" ).val(),
					${portletNamespace}itemName: $( "#_collectionSub_dictItem_edit_itemName" ).val(),
					${portletNamespace}itemNameEN: $( "#_collectionSub_dictItem_edit_itemNameEN" ).val(),
					${portletNamespace}itemDescription: $( "#_collectionSub_dictItem_edit_itemDescription" ).val(),
					${portletNamespace}sibling: "",
					${portletNamespace}parentItemCode: $( "#_collectionSub_dictItem_edit_parentItemCode" ).val(),
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
						$("#modal-lg").trigger({ type: "click" });
						showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');

					}

				},
				error: function(xhr, textStatus, errorThrown) {
					
					showMessageToastr("error", 'Yêu cầu của bạn xử lý thất bại!');

				}
			})

		} else {

			// create a new collection dictItem
		
			$.ajax({
				type: 'POST',
				url: _dictCollection_edit_dictItem_BaseUrl,
				data: {

					${portletNamespace}itemCode: $( "#_collectionSub_dictItem_edit_itemCode" ).val(),
					${portletNamespace}itemName: $( "#_collectionSub_dictItem_edit_itemName" ).val(),
					${portletNamespace}itemNameEN: $( "#_collectionSub_dictItem_edit_itemNameEN" ).val(),
					${portletNamespace}itemDescription: $( "#_collectionSub_dictItem_edit_itemDescription" ).val(),
					${portletNamespace}sibling: "",
					${portletNamespace}parentItemCode: $( "#_collectionSub_dictItem_edit_parentItemCode" ).val(),
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

	var _collectionSub_dictItem_BaseUrl_detail = "${api.server}/dictcollections/${(dictCollection_dictCollection.collectionCode)!}/dictitems",

	_collectionSub_dictItem_dataSource_detail = new kendo.data.DataSource({

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
					
						options.success(result);
						
					},
					error: function(xhr, textStatus, errorThrown) {
						
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
				id: "dictItemId",
				fields: {
					dictItemId: {
						editable: false,
						nullable: true
					},
					itemCode: {
						type: "string"
					},
					itemName: {
						type: "string"
					}
				}
			}
		}
	});
	
	var _collectionSub_dictItem_edit_parentItemCode = $("#_collectionSub_dictItem_edit_parentItemCode").kendoDropDownList({
		
		optionLabel: "Không có danh mục cấp trên",
		dataTextField: "itemName",
		dataValueField: "itemCode",
		dataSource: _collectionSub_dictItem_dataSource_detail
		
	}).data("kendoDropDownList");
	
	if ( $( "#_collectionSub_dictItem_edit_parentItemCode" ).attr("data-value") !="" ) {
	
		_collectionSub_dictItem_edit_parentItemCode.value($( "#_collectionSub_dictItem_edit_parentItemCode" ).attr("data-value"));
	
	}

	
	var _collectionSub_group_BaseUrl_detail = "${api.server
}/dictcollections/${(dictCollection_dictCollection.collectionCode)!}/dictgroups",
		
	_collectionSub_group_dataSource_detail = new kendo.data.DataSource({
	
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
					
						options.success(result);
						
					},
					error: function(xhr, textStatus, errorThrown) {
						
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
				id: "dictGroupId",
				fields: {
					
					dictGroupId: {
						editable: false,
						nullable: true
					},
					dictCollectionId: {
						type: "string"
					},
					groupCode: {
						type: "string"
					},
					groupName: {
						type: "string"
					}
				}
			}
		}

	});
		
	var _collectionSub_dictItem_edit_groupCode = $("#_collectionSub_dictItem_edit_groupCode").kendoMultiSelect({
		placeholder: "Thêm nhóm danh mục...",
		height: 400,
		clearButton: false,
		dataSource: _collectionSub_group_dataSource_detail,
		dataTextField: "groupName",
		dataValueField: "groupCode"
	}).data("kendoMultiSelect");

	if ( $( "#_collectionSub_dictItem_edit_groupCode" ).attr("data-value") !=""
			&& $( "#_collectionSub_dictItem_edit_groupCode" ).attr("data-value") != null ) {
	
		var array_value_ = JSON.parse( $('#_collectionSub_dictItem_edit_groupCode').attr('data-value') );
		_collectionSub_dictItem_edit_groupCode.value(array_value_);
	
	}
	
			
})(jQuery);

</script>