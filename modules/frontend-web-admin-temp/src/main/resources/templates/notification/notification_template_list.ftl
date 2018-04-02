<#include "init.ftl">

<div class="row">

	<!— left —>
	<div class="col-md-3 panel P0">

		<!--search-->
		<div class="panel-body">
	
			<div class="input-group">
				
				<input type="text" class="form-control" id="_notification_keySearch" 
					oninput="_notification_autocompleteSearch()" 
					placeholder="Tên mẫu thông báo">
				
				<div class="input-group-addon btn-active" id="_notification_btnSearch">
					
					<i class="fa fa-search" aria-hidden="true"></i>
				
				</div>
				
			</div>
			
		</div>
				
			
		<ul class="ul-default ul-with-border ul-with-border-style-2 mh-head-1" id="_notification_listView"></ul>
		
		<script type="text/x-kendo-tmpl" id="_notification_template">
		
			<li class="clearfix PT20 PR0 PB20 PL15">
			
				<div class="col-sm-2 clearfix PL0 PR0">
					
					<a href="javascript:;" >
								
						<i style="font-size: 30px;padding: 5px;" class="fa fa-envelope-open" aria-hidden="true"></i>
							
					</a>
						
				</div>
					
				<div class="col-sm-9 PL0">
				
					#if (typeName != "" && typeName != null) {#
						<strong id="notificationTitle#:id#" title="#= typeName #" 
							class="btn-block">#= typeName #
						</strong>
					#} else {#
						<strong id="notificationTitle#:id#" title="#= emailSubject #" 
							class="btn-block">#= emailSubject #
						</strong>
					#}#
					
					<span id="notificationMsg#:id#" class="btn-block">#= notificationType #</span>
				
				</div>
					
				<span class="col-sm-1 PL0 PR0"></span>
					
			 </li>
	
		</script>
		
	</div>
	<!— end left —>

	<!--load right-->
	<div class="col-md-9 " id="_notification_right-page"> </div>

</div>

<input type="hidden" value="0" id="_notification_hidden_new_id"/>

<script type="text/javascript">
	
	function _notification_autocompleteSearch() {
	
		
		var _notification_ListView = $("#_notification_listView").getKendoListView();
	
		_notification_ListView.dataSource.filter({
			
			logic: "or",
			filters: [
				
				{ field: "notificationType", operator: "contains", 	value: $("#_notification_keySearch").val() },
				{ field: "emailSubject", operator: "contains", 	value: $("#_notification_keySearch").val() }
			]
			 
		});
		
	}
	
	(function($) {
	
		var _notification_BaseUrl = "${api.endpoint}/notificationtemplates";

		var _notification_dataSource = new kendo.data.DataSource({
			
			transport: {
	
				read: function(options) {
					
					$.ajax({
					  
						url: _notification_BaseUrl,
						dataType: "json",
						type: 'GET',
						headers: {
							"groupId": ${groupId}
						},
						data: {
							sort: "notificationType"
						},
						success: function(result) {
							
							options.success(result);
						},
						error: function(xhr, textStatus, errorThrown) {
							
							showMessageByAPICode(xhr.status);
						
						}
					
					});
				},
				update: function(options) {
					
					$.ajax({
						url: _notification_BaseUrl + "/" + options.notificationType,
						type: 'PUT',
						dataType: "json",
						headers: {
							"groupId": ${groupId}
						},
						data: {
							
							emailSubject: options.emailSubject,
							emailBody: options.emailBody,
							textMessage: options.textMessage,
							expireDuration: options.expireDuration,
							interval: options.interval,
							userUrlPattern: options.userUrlPattern,
							guestUrlPattern: options.guestUrlPattern,
							sendEmail: options.sendEmail,
							sendSMS: options.sendSMS
							
						},
						beforeSend: function( xhr ) {
							$(' #_notification_submitEditNotification ').button('loading');
						},
						success: function(data, textStatus, xhr) {
							
							var dataSource = $("#_notification_listView").getKendoListView().dataSource;
							dataSource.pushUpdate(data);

							$.map( dataSource.data(), function( obj, i ) {
								
								if(obj.notificationType == data.notificationType) {
									
									var listView = $("#_notification_listView").data("kendoListView");
									listView.select(listView.element.children()[i]);

								}
							});

							showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
							$(' #_notification_submitEditNotification ').button('reset');
						},
						error: function(xhr, textStatus, errorThrown) {
							
							$("#_notification_listView").getKendoListView().dataSource.error();
							showMessageByAPICode(xhr.status);
							$(' #_notification_submitEditNotification ').button('reset');
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
				id: "notificationType",
				fields: {
					
					notificationType: {
							type: "string"
						},
					emailSubject: {
							type: "string"
						},
					emailBody: {
							type: "string"
						},
					textMessage: {
							type: "string"
						},
					sendEmail: {
							type: "string"
						},
					sendSMS: {
							type: "string"
						},
					}
				}
			}
			
		});
	
		$("#_notification_listView").kendoListView({
			
			dataSource: _notification_dataSource,
			
			selectable: "true",
			
			dataBound: _notification_dataBound,
			
			change: _notification_onChange,
			
			template: kendo.template($("#_notification_template").html()),
			
			filterable: {
				
				logic: "or",
				filters: [
					
					{ field: "notificationType", operator: "contains", 	value: $("#_notification_keySearch").val() },
					{ field: "emailSubject", operator: "contains", 	value: $("#_notification_keySearch").val() }
				]
				
			}
		
		});
	
		function _notification_dataBound(e) {
			
			var _notification_listView = e.sender;
			
			var children = _notification_listView.element.children();
			
			var index = $("#_notification_hidden_new_id").val();
			
			for (var x = 0; x < children.length; x++) {
				
				var getObj = _notification_listView.dataSource.view()[x];
				
				if (getObj.notificationType == index) {
				
			 		index = x;
				
				};
				
			};
			
			_notification_listView.select(children[index]);
			
		}
		
		function _notification_onChange(e) {
	
			var data = _notification_dataSource.view(),
	
			selected = $.map(this.select(), function(item) {
			
				return data[$(item).index()].notificationType;
			
			});
			
			$("#_notification_hidden_new_id").val(selected[0]);
			
			$("#_notification_right-page").load(
				'${(url.adminNotificationPortlet.notification_template_detail)!}&${portletNamespace}notificationType='+selected[0]);
			
		}
		
	})(jQuery);
</script>

