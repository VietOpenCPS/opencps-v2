<#include "init.ftl">

<div class="row MB5">

	<!--_collectionSub_name-->

	<label class="col-sm-3">Tên danh mục:</label>

	<div class="col-sm-8">
	
		<a href="javascript:;" id="_collectionSub_name" data-type="text" 

			data-pk="${(dictCollection_dictCollection.collectionCode)!}" 
			data-title="Tên danh mục:" 
			data-value="${(dictCollection_dictCollection.collectionName)!}">
					
				${(dictCollection_dictCollection.collectionName)!}

		</a>
	</div>

	<div class="col-sm-1 text-right">

		<a href="javascript:;" id='_collectionSub_nameIcon' data-pk="${(dictCollection_dictCollection.collectionCode)!}" data-groupId="${(groupId)!}">

			<i class="fa fa-pencil" aria-hidden="true"></i>

		</a>
		
	</div>

</div>

<div class="row MB5">

	<label class="col-sm-3">Tên tiếng anh:</label>

	<div class="col-sm-8">
	
		<a href="javascript:;" id="_collectionSub_nameEN" data-type="text" 

			data-pk="${(dictCollection_dictCollection.collectionCode)!}" 
			data-title="Tên tiếng anh:" 
			data-value="${(dictCollection_dictCollection.collectionNameEN)!}">
					
				${(dictCollection_dictCollection.collectionNameEN)!}

		</a>
	</div>

	<div class="col-sm-1 text-right">		
		<a href="javascript:;" id='_collectionSub_nameENIcon' data-pk="${(dictCollection_dictCollection.collectionCode)!}" data-groupId="${(groupId)!}">

			<i class="fa fa-pencil" aria-hidden="true"></i>

		</a>

	</div>

</div>

<div class="row MB5">
	
	<!--_collectionSub_name-->

	<label class="col-sm-3">Mã danh mục:</label>

	<div class="col-sm-8">
	
		<a href="javascript:;" id="_collectionSub_code" data-type="text" 

			data-pk="${(dictCollection_dictCollection.collectionCode)!}" 
			data-title="Mã danh mục:" 
			data-value="${(dictCollection_dictCollection.collectionCode)!}">
					
				${(dictCollection_dictCollection.collectionCode)!}

		</a>

	</div>

	<div class="col-sm-1 text-right">		
		
		<a href="javascript:;" id='_collectionSub_codeIcon' data-pk="${(dictCollection_dictCollection.collectionCode)!}" data-groupId="${(groupId)!}">

			<i class="fa fa-pencil" aria-hidden="true"></i>

		</a>
		
	</div>
	
</div>

<div class="row MB5">

	<label class="col-sm-3">Mô tả chi tiết:</label>

	<div class="col-sm-8">
	
		<a href="javascript:;" id="_collectionSub_desc" data-type="text" 

			data-pk="${(dictCollection_dictCollection.collectionCode)!}" 
			data-title="Mô tả chi tiết:" 
			data-value="${(dictCollection_dictCollection.description)!}">
					
				${(dictCollection_dictCollection.description)!}

		</a>

	</div>

	<div class="col-sm-1 text-right">		
		
		<a href="javascript:;" id='_collectionSub_descIcon' data-pk="${(dictCollection_dictCollection.collectionCode)!}" data-groupId="${(groupId)!}">

			<i class="fa fa-pencil" aria-hidden="true"></i>

		</a>
		
	</div>
		
</div>

<div class="row MT30">

	<div class="col-xs-3 col-sm-3">
	
		<span data-toggle="modal" class="btn btn-active btn-block"
			href="${url.adminDataMgtPortlet.dictcollection_create_dictgroup}" data-target="#modal"> 
			<i class="fa fa-book" aria-hidden="true"></i>
			
			<span class="p-xxs" >Thêm nhóm</span> 
			<i class="fa fa-plus-circle"></i> 
		</span>
	
	</div>
	
	<div class="col-xs-9 col-sm-9 input-group MB15 PR15">

		<input type="text" class="form-control" id="_collectionSub_group_keySearch"
				oninput="_collectionSub_group_autocompleteSearch(this.value)" 
				placeholder="Tên, mã nhóm">
		
		<span class="input-group-addon"><i class="fa fa-search"></i></span>
	
	</div>

	<div class="col-xs-12 col-sm-12">
			
		<ul class="mh-head-3">
			
			<li class="PB10">
				
				<div class="row M0">
					
					<div class="col-xs-1 col-sm-1 PL0 text-center">
					
						<strong>STT</strong>
					
					</div>
					<div class="col-xs-3  col-sm-3">
						
						<strong>Mã nhóm</strong>
					
					</div>
					
					<div class="col-xs-3 col-sm-3">
						
						<strong>Tên nhóm</strong>
					
					</div>

					<div class="col-xs-2 col-sm-2">
						
						<strong>Tên tiếng anh</strong>
					
					</div>
					
					<div class="col-xs-3 col-sm-3 text-right">
						
						<strong>Tổng số <span id="_collectionSub_group_CounterListDetail">0</span> nhóm</strong>
					
					</div>
				
				</div>
			
			</li>
			
			<div id ="_collectionSub_group_listView"></div>

		</ul>
	
	</div>	

</div>

<script type="text/x-kendo-tmpl" id="_collectionSub_group_template">
	
	<li class="PT5 PB5 line-dashed">
	
		<div class="row M0 eq-height flex-break" >
		
			<div class="col-xs-1 col-sm-1 PL0 align-middle" >
				
				<div class="full-width text-center">

					<a href="javascript:;">
					
						#:itemIndex#
						
					</a>

				</div>
				
			</div>
			
			<div class="col-xs-3 col-sm-3 align-middle" >
				
				<div class="text-ellipsis">
					#:groupCode#
				</div>
				
			</div>

			<div class="col-xs-3 col-sm-3 align-middle" >
				
				<div class="text-ellipsis">
				#:groupName#
				</div>
				
			</div>

			<div class="col-xs-3 col-sm-3 align-middle" >
			
				<div class="text-ellipsis">
				#:groupNameEN#
				</div>
				
			</div>

			<div class="col-xs-2 col-sm-2 align-middle" >
				
				<div class="full-width text-right">

					<a  class="btn-sm _collectionSub_group_btnToggle PL0" data-pk="#:groupCode#">
							
						<i class="fa fa-chevron-down" aria-hidden="true"></i>
						
					</a>
				
					<span data-toggle="modal" 
						href="${url.adminDataMgtPortlet.dictcollection_create_dictgroup}&${portletNamespace}type=${constant.type_dictCollection}&${portletNamespace}groupCode=#:groupCode#&${portletNamespace}dictCollectionCode=${(dictCollection_dictCollection.collectionCode)!}"
						 data-target="\\#modal">
						
						<i class="fa fa-pencil" aria-hidden="true"></i>
						
					</span>
					
					<span class="ML10 k-delete-button">

						<i aria-hidden="true" class="fa fa-times"></i>

					</span>

				</div>

			</div>

			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 " style="display: none" id = "_collectionSub_group_toggle_#:groupCode#">
						
				<div class="box box-s3 MT10 _collectionSub_group_dictItem" data-pk="#:groupCode#" >
						
					<label>Danh sách mục dữ liệu: </label>
					<select name="_collectionSub_group_dictItem#:groupCode#" 
						id="_collectionSub_group_dictItem#:groupCode#"
						data-placeholder="Mục dữ liệu...">
						
					</select>
					
				</div>
					
			</div>
			
		</div>
		
	</li> 

</script>



<script type="text/javascript">

function _collectionSub_group_autocompleteSearch(val) {
	
	var _collectionSub_group_listView = $("#_collectionSub_group_listView").getKendoListView();
	
	_collectionSub_group_listView.dataSource.filter({
	
		logic: "or",
		filters: [
			
			{ field: "groupCode", operator: "contains", 	value: val },
			{ field: "groupName", operator: "contains", 	value: val }
		]
	});
	
	$('#_collectionSub_group_CounterListDetail').html($("#_collectionSub_group_listView").getKendoListView().dataSource.total());
}

(function($) {
	
	var _collectionSub_group_BaseUrl_detail = "${api.endpoint}/dictcollections/"+$("#_collectionSub_code").text().trim()+"/dictgroups",
		
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
						
						sort: 'sibling'
						
					},
					success: function(result) {
					
						$('#_collectionSub_group_CounterListDetail').html(result.total);
						
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
					url: _collectionSub_group_BaseUrl_detail,
					headers: {
						"groupId": ${groupId}
					},
					data: {
						
						groupCode: options.data.groupCode,
						groupName: options.data.groupName,
						groupNameEN: options.data.groupNameEN,
						groupDescription: options.data.groupDescription

					},
					type: 'POST',
					dataType: 'json',
					contentType: 'application/x-www-form-urlencoded; charset=utf-8',
					beforeSend: function( xhr ) {
						$("#_collectionSub_dictItem_edit_editDictGroup").button('loading');
					},
					success: function(data, textStatus, xhr) {
						
						var dataSource = $("#_collectionSub_group_listView").getKendoListView().dataSource;
							
						dataSource.error();
						dataSource.pushUpdate(data);
						$('#_collectionSub_group_CounterListDetail').html(dataSource.total());
						$("#modal").trigger({ type: "click" });
						showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
						$("#_collectionSub_dictItem_edit_editDictGroup").button('reset');
					},
					error: function(xhr, textStatus, errorThrown) {
						
						$("#_collectionSub_group_listView").getKendoListView().dataSource.error();
						showMessageByAPICode(xhr.status);
						$("#_collectionSub_dictItem_edit_editDictGroup").button('reset');
					}
				});
				
			},
			update: function(options) {
				
				$.ajax({
					url: _collectionSub_group_BaseUrl_detail + "/" + options.groupCodeOld,
					headers: {
						"groupId": ${groupId}
					},
					data: {
						
						groupCode: options.groupCode,
						groupName: options.groupName,
						groupNameEN: options.groupNameEN,
						groupDescription: options.groupDescription
					},
					type: 'PUT',
					dataType: 'json',
					contentType: 'application/x-www-form-urlencoded; charset=utf-8',
					success: function(data, textStatus, xhr) {
					
						showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
						$("#_collectionSub_group_listView").getKendoListView().dataSource.pushDestroy(data);
						$("#_collectionSub_group_listView").getKendoListView().dataSource.pushUpdate(data);
						$("#modal").trigger({ type: "click" });
						
					},
					error: function(xhr, textStatus, errorThrown) {
						
						$("#_collectionSub_group_listView").getKendoListView().dataSource.error();
						showMessageByAPICode(xhr.status);
					
					}
				});
			
			},
			destroy: function(options) {
				
				var confirmWindown = showWindowConfirm('#template-confirm','Cảnh báo','Bạn có chắc muốn xóa bản ghi này?', $("#_collectionSub_group_listView") );
					
				confirmWindown.then(function(confirmed){
				
					if(confirmed){

						$.ajax({
							url: _collectionSub_group_BaseUrl_detail + "/" + options.data.groupCode,
							headers: {
								"groupId": ${groupId}
							},
							type: 'DELETE',
							success: function(result) {
								
								options.success();
								$('#_collectionSub_group_CounterListDetail').html($("#_collectionSub_group_listView").getKendoListView().dataSource.total());
								showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
								
							},
							error: function(xhr, textStatus, errorThrown) {
								
								$("#_collectionSub_group_listView").getKendoListView().dataSource.error();
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
					},
					groupNameEN: {
						type: "string"
					},
					groupDescription: {
						type: "string"
					}
				}
			}
		},
		error: function(e) {
			
			this.cancelChanges();
			
		}
	});

	var globleIndex = 0;

	$("#_collectionSub_group_listView").kendoListView({
		remove: function(e) {
		},
		
		dataSource: _collectionSub_group_dataSource_detail,

		dataBound : function (e){

			e.preventDefault();

			globleIndex = 0;

			var _collectionSub_group_listView = e.sender;
			var children = _collectionSub_group_listView.element.children();
			var objGroupCode = "";

			for (var x = 0; x < children.length; x++) {
				
				var getObj = _collectionSub_group_listView.dataSource.view()[x];
				var isCreate = $("#_collectionSub_group_dictItem"+getObj.groupCode)[0]["isKMulti"];
				
				if (getObj.groupCode != null && getObj.groupCode !="" && isCreate != true) {

					$.ajax({
				
						url: _collectionSub_group_BaseUrl_detail+"/"+getObj.groupCode+"/dictitems",
						dataType: "json",
						type: 'GET',
						headers: {
							"groupId": ${groupId}
						},
						async: false,
						data: {
							
							sort: 'sibling',
							full: true
							
						},
						success: function(result) {
							
							var dictGroupItems =[];

							for (var i = 0, len = result.total; i < len; i++) {
								
								result.data[i].groupCode = getObj.groupCode;

								if (result.data[i].selected) {

									dictGroupItems.push(result.data[i].itemCode);

								}

							}

							var _collectionSub_group_dictItem =  $("#_collectionSub_group_dictItem"+getObj.groupCode).kendoMultiSelect({
						
								clearButton: false,				
								dataSource: result.data,
								dataTextField: "itemName",
								dataValueField: "itemCode",
								select: addNewDictItem,
								deselect: removeDictItem

							}).data("kendoMultiSelect");

							_collectionSub_group_dictItem.value(dictGroupItems);
							$("#_collectionSub_group_dictItem"+getObj.groupCode)[0]["isKMulti"] = true;
							
						},
						error: function(xhr, textStatus, errorThrown) {
							
							$("#_collectionSub_group_dictItem"+getObj.groupCode).kendoMultiSelect();
						
						}
					
					});

				};
				
			};

		},
		
		template: function(data){
			
			globleIndex++;
			
			data.itemIndex = globleIndex;
			
			return kendo.template($("#_collectionSub_group_template").html())(data);
			
		},
		
		filterable: {
		
			logic: "or",
			filters: [
				
				{ field: "groupCode", operator: "contains", 	value: $("#_collectionSub_group_keySearch").val().trim() },
				{ field: "groupName", operator: "contains", 	value: $("#_collectionSub_group_keySearch").val().trim() }
			]
		
		}
		
	});

	$(document).on('click', '._collectionSub_group_btnToggle', function(event){
		
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();
		
		var pk = $(this).attr("data-pk");

		$("#_collectionSub_group_toggle_"+pk).slideToggle( "fast" );
		
	});

	function addNewDictItem(e){

		if (e.item) {
			
			var dataItem = this.dataItem(e.item.index());

			$.ajax({
				url: _collectionSub_group_BaseUrl_detail + "/" + dataItem.groupCode + "/dictitems",
				headers: {
					"groupId": ${groupId}
				},
				data: {
					
					itemCode: dataItem.itemCode,

				},
				type: 'POST',
				dataType: 'json',
				contentType: 'application/x-www-form-urlencoded; charset=utf-8',
				success: function(data, textStatus, xhr) {
					
					showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');

				},
				error: function(xhr, textStatus, errorThrown) {

					showMessageByAPICode(xhr.status);
				
				}
			});

		}

	}

	function removeDictItem(e){

		var dataItem = this.dataItem(e.item.index());
		
		$.ajax({

			url: _collectionSub_group_BaseUrl_detail + "/" + dataItem.groupCode + "/dictitems/" + dataItem.itemCode,
			headers: {
				"groupId": ${groupId}
			},
			type: 'DELETE',
			success: function(result) {
				
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(xhr, textStatus, errorThrown) {
				
				showMessageByAPICode(xhr.status);
			
			}

		});

	}

})(jQuery);
</script>
