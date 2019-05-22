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
			<div id="_collectionSub_dictItem_listView_pager" class="k-pager-wrap full-width-pager pull-right PR15 PB15"></div>

		</ul>

	</div>
	
</div>

<script type="text/x-kendo-tmpl" id="_collectionSub_dictItem_template">
	
	<li class="PT5 PB5 line-dashed">
	
		<div class="row M0 eq-height" >
		
			<div class="col-xs-1 col-sm-1 PL0 align-middle" >
				
				<div class="full-width text-center">

					<a href="javascript:;">
					
						<span>#:itemIndex#</span>
						
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
					var page = options.data.page;
					var pageSize = options.data.pageSize;
					var start = (page - 1) * pageSize;
					var end = (page - 1) * pageSize + pageSize;
					$.ajax({
					
						url: _collectionSub_dictItem_BaseUrl_detail,
						dataType: "json",
						type: 'GET',
						headers: {
							"groupId": ${groupId}
						},
						data: {
							
							sort: 'sibling',
							start: start,
							end: end
							
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
					var cf = confirm('Bạn có chắc muốn xóa bản ghi này?');
					// var confirmWindown = showWindowConfirm('#template-confirm','Cảnh báo','Bạn có chắc muốn xóa bản ghi này?', $("#_collectionSub_dictItem_listView") );
					if(cf){

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

								options.error();
								showMessageByAPICode(xhr.status);
								
							}

						});

					} else{

						options.error();
					}
					
				},
				parameterMap: function(options, operation) {
					
					if (operation !== "read" && options.models) {
						return {
							models: kendo.stringify(options.models)
						};
					}
				}
			},
			pageSize: 15,
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true,
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

		$("#_collectionSub_dictItem_listView_pager").kendoPager({
			dataSource: _collectionSub_dictItem_dataSource_detail,
			buttonCount: 5,
			info: false
		});

		var localIndex = 0;
		$("#_collectionSub_dictItem_listView").kendoListView({
			remove: function(e) {
				
			},
			
			dataSource: _collectionSub_dictItem_dataSource_detail,

			template : function(data){
				var _pageSize = _collectionSub_dictItem_dataSource_detail.pageSize();

				localIndex++;

				var currentPage = $("#_collectionSub_dictItem_listView_pager").data("kendoPager").page();
				var totalPage =  $("#_collectionSub_dictItem_listView_pager").data("kendoPager").totalPages();

				var index = (currentPage-1)*_pageSize + localIndex;

				data.itemIndex = index;

				return kendo.template($("#_collectionSub_dictItem_template").html())(data);
			},
			dataBound : function(e){
				localIndex = 0;
			},
			
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