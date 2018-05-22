<#include "init.ftl">

<div class="row">
		
	<div class="col-xs-4 col-sm-4">
	
		<span data-toggle="modal" class="btn btn-active btn-block"
			href="${url.adminDataMgtPortlet.dictcollection_create_dictitem}&${portletNamespace}type=${constant.type_dictCollection}&${portletNamespace}collectionCode=${(dictCollection_dictCollection.collectionCode)!}" data-target="#modal-lg"> 
			<i class="fa fa-book" aria-hidden="true"></i>
			
			<span class="p-xxs" >Thêm mục</span> 
			<i class="fa fa-plus-circle"></i> 
		</span>
	
	</div>
	
	<div class="col-xs-8 col-sm-8 input-group MB15 PR15">

		<input type="text" class="form-control" id="_collectionSub_dictItem_keySearch"
				oninput="_collectionSub_dictItem_autocompleteSearch(this.value)" 
				placeholder="Tên, mã loại danh mục">
		
		<span class="input-group-addon"><i class="fa fa-search"></i></span>
	
	</div>

	<div class="col-xs-12 col-sm-12">
		
		<ul class="mh-head-1">
			
			<li class="PB10">
				
				<div class="row M0">
					
					<div class="col-xs-1 col-sm-1 PL0 text-center">
					
						<span><strong>STT</strong></span>
					
					</div>
					<div class="col-xs-3  col-sm-3">
						
						<span><strong>Mã danh mục</strong></span>
					
					</div>
					
					<div class="col-xs-6 col-sm-6">
						
						<span><strong>Tên danh mục</strong></span>
					
					</div>
					
					<div class="col-xs-2 col-sm-2 text-right">
						
						<strong>Tổng số <span id="_collectionSub_dictItem_CounterListDetail">0</span></strong>
					
					</div>
				
				</div>
			
			</li>
			
			<div id ="_collectionSub_dictItem_listView"></div>

		</ul>

	</div>
	
</div>

<script type="text/x-kendo-tmpl" id="_collectionSub_dictItem_template">
	
	<li class="PT5 PB5 line-dashed">
	
		<div class="row M0 eq-height" >
		
			<div class="col-xs-1 col-sm-1 PL0 align-middle" >
				
				<div class="full-width text-center">

					<a href="javascript:;">
					
						<span>#:sibling#</span>
						
					</a>

				</div>
				
			</div>
			
			<div class="col-xs-3 col-sm-3 align-middle" >
			
				<span>#:itemCode#</span>
				
			</div>

			<div class="col-xs-6 col-sm-6 align-middle" >
			
				<span>#:itemName#</span>
				
			</div>

			<div class="col-xs-2 col-sm-2 align-middle" >
				
				<div class="full-width text-right">

					<span data-toggle="modal" 
						href="${url.adminDataMgtPortlet.dictcollection_create_dictitem}&${portletNamespace}type=${constant.type_dictCollection}&${portletNamespace}itemCode=#:itemCode#&${portletNamespace}collectionCode=${(dictCollection_dictCollection.collectionCode)!}"
						 data-target="\\#modal-lg">
						
						<i class="fa fa-pencil" aria-hidden="true"></i>
						
					</span>
					
					<span class="ML10 k-delete-button">

						<i aria-hidden="true" class="fa fa-times"></i>

					</span>

				</div>
				
			</div>
			
		</div>
		
	</li> 

</script>



<script type="text/javascript">

function _collectionSub_dictItem_autocompleteSearch(val) {
	
	var _collectionSub_dictItem_listView = $("#_collectionSub_dictItem_listView").getKendoListView();
	
	_collectionSub_dictItem_listView.dataSource.filter({
	
		logic: "or",
		filters: [
			
			{ field: "itemCode", operator: "contains", 	value: val },
			{ field: "itemName", operator: "contains", 	value: val }
		]
	});
	
	$('#_collectionSub_dictItem_CounterListDetail').html($("#_collectionSub_dictItem_listView").getKendoListView().dataSource.total());
}

(function($) {
	
	var _collectionSub_dictItem_BaseUrl_detail = "${api.endpoint}/dictcollections/${(dictCollection_dictCollection.collectionCode)!}/dictitems",
	
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
							
							sort: 'sibling'
							
						},
						success: function(result) {
						
							$('#_collectionSub_dictItem_CounterListDetail').html(result.total);	
							
							result["data"] = result.total==0 ? []: result["data"];
							options.success(result);
							
						},
						error: function(xhr, textStatus, errorThrown) {
							
							showMessageByAPICode(xhr.status);
						
						}
					
					});
				},
				destroy: function(options) {
					
					var confirmWindown = showWindowConfirm('#template-confirm','Cảnh báo','Bạn có chắc muốn xóa bản ghi này?', $("#_collectionSub_dictItem_listView") );
					
					confirmWindown.then(function(confirmed){
					
						if(confirmed){

							$.ajax({
								url: _collectionSub_dictItem_BaseUrl_detail + "/" + options.data.itemCode,
								headers: {
									"groupId": ${groupId}
								},
								type: 'DELETE',
								success: function(result) {
									options.success();
									showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
									
								},
								error: function(xhr, textStatus, errorThrown) {
									
									_collectionSub_dictItem_dataSource_detail.dataSource.error();
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
						},
						sibling: {
							type: "string"
						},
						itemDescription: {
							type: "string"
						}
					}
				}
			},
			error: function(e) {
				
				this.cancelChanges();
				
			}
		});

		$("#_collectionSub_dictItem_listView").kendoListView({
			remove: function(e) {
				
			},
			
			dataSource: _collectionSub_dictItem_dataSource_detail,
			
			template: kendo.template($("#_collectionSub_dictItem_template").html()),
			
			filterable: {
			
				logic: "or",
				filters: [
					
					{ field: "itemCode", operator: "contains", 	value: $("#_collectionSub_dictItem_keySearch").val().trim() },
					{ field: "itemName", operator: "contains", 	value: $("#_collectionSub_dictItem_keySearch").val().trim() }
				]
			
			}
			
		});
		

})(jQuery);
</script>
