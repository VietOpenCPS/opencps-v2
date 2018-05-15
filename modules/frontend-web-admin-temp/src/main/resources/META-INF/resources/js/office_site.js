(function($) {

	$.fn.editable.defaults.mode = 'inline';
	$.fn.editable.defaults.send = "always";
	
	var officeSiteAPICall = '/o/rest/v2/officesites';
	var searchSitesAPICall = '/o/rest/v2/sites';
	var searchUserSitesAPICall = '/o/rest/v2/sites/users';
	
	$(document).on('click', '#_officeSiteSub_nameIcon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		$('#_officeSiteSub_name').editable({
			name: 'name',
			url: officeSiteAPICall + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					name: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success: function(data) {
				
				$("#_officeSite_listView").getKendoListView().dataSource.read();
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			}
		}); 
				 
		$('#_officeSiteSub_name').editable('toggle');
			
	});
	
	$(document).on('click', '#_officeSiteSub_enNameIcon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		$('#_officeSiteSub_enName').editable({
			name: 'enName',
			url: officeSiteAPICall + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					enName: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success: function(data) {
				
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			}
		}); 
				 
		$('#_officeSiteSub_enName').editable('toggle');
			
	});
	
	$(document).on('click', '#_officeSiteSub_govAgencyCodeIcon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		$('#_officeSiteSub_govAgencyCode').editable({
			name: 'govAgencyCode',
			url: officeSiteAPICall + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					govAgencyCode: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success: function(data) {
				
				$("#_officeSite_listView").getKendoListView().dataSource.read();
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			}
		}); 
				 
		$('#_officeSiteSub_govAgencyCode').editable('toggle');
			
	});
	
	$(document).on('click', '#_officeSiteSub_addressIcon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		$('#_officeSiteSub_address').editable({
			name: 'address',
			url: officeSiteAPICall + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					address: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success: function(data) {
				
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			}
		}); 
				 
		$('#_officeSiteSub_address').editable('toggle');
			
	});
	
	$(document).on('click', '#_officeSiteSub_telNoIcon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		$('#_officeSiteSub_telNo').editable({
			name: 'telNo',
			url: officeSiteAPICall + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					telNo: params.value
				};
			},
			validate: function(value) {
				
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				} else if ( isNaN(value) || value.length > 11 ) {
					return 'Số điện thoại trên 10 số';
				}
			},
			success: function(data) {
				
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			}
		}); 
				 
		$('#_officeSiteSub_telNo').editable('toggle');
		
	});
	
	$(document).on('click', '#_officeSiteSub_faxNoIcon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		$('#_officeSiteSub_faxNo').editable({
			name: 'faxNo',
			url: officeSiteAPICall + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					faxNo: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success: function(data) {
				
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			}
		}); 
				 
		$('#_officeSiteSub_faxNo').editable('toggle');
			
	});
	
	$(document).on('click', '#_officeSiteSub_emailIcon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		$('#_officeSiteSub_email').editable({
			name: 'email',
			url: officeSiteAPICall + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					email: params.value
				};
			},
			validate: function(value) {
				var pattern="[A-Za-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$";
				
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				} else if (!value.match(pattern)) {
					return 'Định dạng: contact@email.com';
				}
			},
			success: function(data) {
				
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			}
		}); 
				 
		$('#_officeSiteSub_email').editable('toggle');
			
	});
	
	$(document).on('click', '#_officeSiteSub_websiteIcon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		$('#_officeSiteSub_website').editable({
			name: 'website',
			url: officeSiteAPICall + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					website: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success: function(data) {
				
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			}
		}); 
				 
		$('#_officeSiteSub_website').editable('toggle');
			
	});
	
	$(document).on('click', '#_officeSiteSub_siteGroupIdIcon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		$('#_officeSiteSub_siteGroupId').editable({
			name: 'siteGroupId',
			url: officeSiteAPICall + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					siteGroupId: params.value
				};
			},
			success: function(data) {
				
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			},
			select2: {
				placeholder: 'Chọn Site sử dụng',
				minimumInputLength: 0,
				id: function (item) {
					return item.id;
				},
				ajax: {
					url: searchSitesAPICall,
					dataType: 'json',
					data: function (term, page) {
						return { 'keywords': term };
					},
					results: function (data, page) {
						return {
							results: $.map(data.results, function (item) {
								return {
									text: item.text,
									id: item.id
								}
							})
						};
					}
				},
				formatResult: function (item) {
					return item.text;
				},
				formatSelection: function (item) {
					return item.text;
				},
				initSelection: function (element, callback) {
					return $.get(searchSitesAPICall + "/" + element.val(), { 'query': element.val() }, function (data) {
						callback(data);
					});
				} 
			}
		}); 
				 
		$('#_officeSiteSub_siteGroupId').editable('toggle');
			
	});
	
	$(document).on('click', '#_officeSiteSub_adminUserIdIcon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		var groupId = $(this).attr('groupId');
		
		$('#_officeSiteSub_adminUserId').editable({
			name: 'adminUserId',
			url: officeSiteAPICall + '/' + pk,
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
					adminUserId: params.value
				};
			},
			success: function(data) {
				
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			},
			select2: {
				placeholder: 'Chọn tài khoản quản trị',
				minimumInputLength: 0,
				id: function (item) {
					return item.id;
				},
				ajax: {
					url: searchUserSitesAPICall,
					dataType: 'json',
					data: function (term, page) {
						return { 'keywords': term, 'groupId': groupId };
					},
					results: function (data, page) {
						return {
							results: $.map(data.results, function (item) {
								return {
									text: item.text,
									id: item.id
								}
							})
						};
					}
				},
				formatResult: function (item) {
					return item.text;
				},
				formatSelection: function (item) {
					return item.text;
				},
				initSelection: function (element, callback) {
					return $.get(searchUserSitesAPICall + "/" + element.val(), { 'query': element.val() }, function (data) {
						callback(data);
					});
				} 
			}
		}); 
				 
		$('#_officeSiteSub_adminUserId').editable('toggle');
			
	});
	
$(document).on('click', '#_officeSiteSub_preferencesIcon', function(e){
		
		e.preventDefault();
		e.stopPropagation();
		e.stopImmediatePropagation();
		
		var pk = $(this).attr('data-pk');
		
		$('#_officeSiteSub_preferences').editable({
			name: 'preferences',
			url: officeSiteAPICall + '/' + pk,
			ajaxOptions: {
				method: 'PUT',
				dataType: 'json',
				contentType: "application/x-www-form-urlencoded;charset=utf-8",
			},
			params: function(params) { 
				return {
					preferences: params.value
				};
			},
			validate: function(value) {
				if (value.length < 1){
					return 'Đây là trường bắt buộc';
				}
			},
			success: function(data) {
				
				showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
				
			},
			error: function(event, id, obj) {
				showMessageToastr("error", 'Yêu cầu của bạn được xử lý thất bại!');
			}
		}); 
				 
		$('#_officeSiteSub_preferences').editable('toggle');
			
	});
	
})(jQuery);