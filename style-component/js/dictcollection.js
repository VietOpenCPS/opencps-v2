(function($) {

	$.fn.editable.defaults.mode = 'inline';
	$.fn.editable.defaults.send = "always";
	
	var dictCollectionAPICall = '/o/v2/mobilink/dictcollections';
	
	$(document).on('click', '#_collectionSub_nameIcon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		var groupId = $(this).attr('data-groupId');
		
		$('#_collectionSub_name').editable({
			name: 'collectionName',
			url: dictCollectionAPICall + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				headers: {
					"groupId": groupId
				},
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					collectionName: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success: function(data) {
				
				$("#_collection_listView").getKendoListView().dataSource.pushUpdate(data);
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			}
		}); 
				 
		$('#_collectionSub_name').editable('toggle');
			
	});
	
	$(document).on('click', '#_collectionSub_nameENIcon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		var groupId = $(this).attr('data-groupId');
		
		$('#_collectionSub_nameEN').editable({
			name: 'collectionNameEN',
			url: dictCollectionAPICall + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				headers: {
					"groupId": groupId
				},
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					collectionNameEN: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success: function(data) {
				
				var dataSource = $("#_collection_listView").getKendoListView().dataSource;
				
				dataSource.pushUpdate(data);
				$.map( dataSource.data(), function( obj, i ) {
					
					if(obj.collectionCode == data.collectionCode) {
						
						var listView = $("#_collection_listView").data("kendoListView");
						listView.select(listView.element.children()[i]);

					}
				});
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			}
		}); 
				 
		$('#_collectionSub_nameEN').editable('toggle');
			
	});

	$(document).on('click', '#_collectionSub_codeIcon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		var groupId = $(this).attr('data-groupId');
		
		$('#_collectionSub_code').editable({
			name: 'collectionCode',
			url: dictCollectionAPICall + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				headers: {
					"groupId": groupId
				},
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					collectionCode: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success: function(data) {
				
				var dataSource = $("#_collection_listView").getKendoListView().dataSource;
				
				dataSource.pushUpdate(data);
				$.map( dataSource.data(), function( obj, i ) {
					
					if(obj.collectionCode == data.collectionCode) {
						
						var listView = $("#_collection_listView").data("kendoListView");
						listView.select(listView.element.children()[i]);

					}
				});
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			}
		}); 
				 
		$('#_collectionSub_code').editable('toggle');
			
	});
		
	$(document).on('click', '#_collectionSub_descIcon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		var groupId = $(this).attr('data-groupId');
		
		$('#_collectionSub_desc').editable({
			name: 'description',
			url: dictCollectionAPICall + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				headers: {
					"groupId": groupId
				},
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					description: params.value
				};
			},
			success: function(data) {
				
				var dataSource = $("#_collection_listView").getKendoListView().dataSource;
				
				dataSource.pushUpdate(data);
				$.map( dataSource.data(), function( obj, i ) {
					
					if(obj.collectionCode == data.collectionCode) {
						
						var listView = $("#_collection_listView").data("kendoListView");
						listView.select(listView.element.children()[i]);

					}
				});
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			}
		}); 
				 
		$('#_collectionSub_desc').editable('toggle');
		
		
	});	
		
})(jQuery);
