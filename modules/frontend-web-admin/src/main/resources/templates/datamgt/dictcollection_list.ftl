<#include "init.ftl">

<div class="row">

	<!— left —>
	<div class="col-md-3 panel P0">

		<!--search-->
		<div class="panel-body">
	
			<div class="input-group">
				
				<input type="text" class="form-control" id="_collection_keySearch"
					oninput="_collection_autocompleteSearch()" 
					placeholder="Tên, mã nhóm danh mục">
	
				<div class="input-group-addon btn-active" id="_collection_btnSearch">
					
					<i class="fa fa-search" aria-hidden="true"></i>
	
				</div>

			</div>
	
			<span data-toggle="modal" class="btn btn-active btn-block MT15"
				href="${url.adminDataMgtPortlet.dictcollection_create_dictcollection}" data-target="#modal"> 
				<i class="fa fa-book" aria-hidden="true"></i>
				<span class="p-xxs" >Tổng số</span> 
				<span id="dictCollectionCounterList">0</span>
				<span class="p-xxs" >Nhóm danh muc</span> 
				<i class="fa fa-plus-circle"></i> 
			</span>
			
		</div>
				
			
		<ul class="ul-with-border ul-default mh-head-2" id="_collection_listView"></ul>
		
		<script type="text/x-kendo-tmpl" id="_collection_template">
		
			<li class="clearfix PT10 PR0 PB10 PL10">
	
				<div class="col-sm-2 clearfix PL0 PR0">
					
					<a href="javascript:;" >
								
						<i class="fa fa-book fs26 P5" aria-hidden="true"></i>
							
					</a>
						
				</div>
					
				<div class="col-sm-9 PL0">
				
					<strong class="btn-block">#= collectionName #</strong>
					<span class="btn-block">#= collectionCode #</span>
				
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
	<div class="col-md-9 " id="_collection_right-page"> </div>

</div>

<input type="hidden" value="0" id="_collection_hidden_new_id"/>

<script type="text/javascript">
	
	function _collection_autocompleteSearch() {
		
		var _collection_ListView = $("#_collection_listView").data("kendoListView");
	
		_collection_ListView.dataSource.filter({
		
			logic: "or",
			filters: [
				
				{ field: "collectionName", operator: "contains", 	value: $("#_collection_keySearch").val().trim() },
				{ field: "collectionCode", operator: "contains", 	value: $("#_collection_keySearch").val().trim() }
			]
		});
		
		$('#dictCollectionCounterList').html($("#_collection_listView").getKendoListView().dataSource.total());
		
	}
	
	(function($) {
	
		var _collection_BaseUrl = "${api.endpoint}/dictcollections";

		var _collection_dataSource = new kendo.data.DataSource({
			
			transport: {
	
				read: function(options) {
					
					$.ajax({
					
						url: _collection_BaseUrl,
						dataType: "json",
						type: 'GET',
						headers: {
							"groupId": ${groupId}
						},
						data: {
							sort: 'collectionName'
						},
						success: function(result) {
						
							$('#dictCollectionCounterList').html(result.total);
							
							result["data"] = result.total==0 ? []: result["data"];
							options.success(result);
							
						},
						error: function(xhr, textStatus, errorThrown) {
							
							showMessageByAPICode(xhr.status);
						
						}
					
					});
				},
				destroy: function(options) {
					
					var confirmWindown = showWindowConfirm('#template-confirm','Cảnh báo','Bạn có chắc muốn xóa bản ghi này?', $("#_collection_listView") );
					
					confirmWindown.then(function(confirmed){
					
						if(confirmed){
	
							$.ajax({
								url: _collection_BaseUrl + "/" + options.data.collectionCode,
								type: 'DELETE',
								headers: {
									"groupId": ${groupId}
								},
								success: function(result) {
									
									$("#_collection_hidden_new_id").val("0");
									options.success();
									$('#dictCollectionCounterList').html($("#_collection_listView").getKendoListView().dataSource.total());
									showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
									
								},
								error: function(xhr, textStatus, errorThrown) {
								
									$("#_collection_listView").getKendoListView().dataSource.error();
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
					id: "collectionCode",
					fields: {
						
						collectionCode: {
							type: "string",
							editable: false,
							nullable: true
						},
						collectionName: {
							type: "string"
						},
						description: {
							type: "string"
						}
					}
				}
			},
			error: function(e) {
				
				this.cancelChanges();
				
			}
			
		});
	
		$("#_collection_listView").kendoListView({
		
			remove: function(e) {
				
			},
			
			dataSource: _collection_dataSource,
			
			selectable: "true",
			
			dataBound: _collection_dataBound,
			
			change: _collection_onChange,
			
			template: kendo.template($("#_collection_template").html()),
			
			filterable: {
			
				logic: "or",
				filters: [
					
					{ field: "collectionName", operator: "contains", 	value: $("#_collection_keySearch").val().trim() },
					{ field: "collectionCode", operator: "contains", 	value: $("#_collection_keySearch").val().trim() }
				]
			
			}
		
		});
	
		function _collection_dataBound(e) {
			
			var _collection_listView = e.sender;
			
			var children = _collection_listView.element.children();
			
			var index = $("#_collection_hidden_new_id").val().trim();
			
			for (var x = 0; x < children.length; x++) {
				
				var getObj = _collection_listView.dataSource.view()[x];
				
				if (getObj.collectionCode == index) {
				
			 		index = x;
				
				};
				
			};
			
			_collection_listView.select(children[index]);
			
		}
		
		function _collection_onChange(e) {
	
			var data = _collection_dataSource.view(),
	
			selected = $.map(this.select(), function(item) {
			
				return data[$(item).index()].collectionCode;
			
			});
			
			$("#_collection_hidden_new_id").val(selected[0]);
	
			$("#_collection_right-page").load(
				'${url.adminDataMgtPortlet.dictcollection_detail}&${portletNamespace}type=${constant.type_dictCollection}&${portletNamespace}collectionCode='+selected[0]);
			
		}
		
	})(jQuery);
</script>


