<#include "init.ftl">

<div class="row panel "> 
	
	<h3 class="detail-header MT5">
		
		${(notificationTemplate.typeName)!}

	</h3>

	<div class="panel-body">
		
		<form id="_notificationSubDetail_form">
		
			<!--_notificationSubDetail_type-->
			
			<div class="form-group">

				<input type="hidden" id="_notificationSubDetail_notificationType" value="${(notificationTemplate.notificationType)!}" />
						
			</div>
			
			<!--_notificationSubDetail__notificationSubDetail_emailSubject-->
			
			<div class="form-group" >
				
				<label>
					Chủ đề thư:
				</label>
				
				<input type="text" id="_notificationSubDetail_emailSubject" name="_notificationSubDetail_emailSubject"
					class="form-control" placeholder="Chủ đề thư" required validationMessage="Nhập chủ đề thư" 
						value="${(notificationTemplate.emailSubject)!}" />
				
			</div>
			
			<!--_notificationSubDetail_name-->
			
			<div class="form-group" >
				
				<label>Nội dung thư:</label>
				
				<textarea id="_notificationSubDetail_emailBody" rows="10" cols="30" style="width:100%;height:400px">
					${(notificationTemplate.emailBody)!}
				</textarea>
					
			</div>
			
			<!--_notificationSubDetail__notificationSubDetail_textMessage-->
			
			<div class="form-group" >
				
				<label class="col-xs-6 col-sm-6 col-md-6 col-lg-6 font-bold">Nội dung thông báo:</label>
				<label class="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-right">Tổng số ${ ((notificationTemplate.textMessage)!"" )?length} ký tự</label>
				
				<input class="form-control" id="_notificationSubDetail_textMessage"
					value="${(notificationTemplate.textMessage)!}"
					name="_notificationSubDetail_textMessage" type="text">
					
			</div>
			
			<div class="form-group" >
                
                <label class="font-bold">Số giờ sau khi tạo thông báo bị expire nếu không gửi được</label>
                
                <input class="form-control" id="_notificationSubDetail_expireDuration"
                    value="${(notificationTemplate.expireDuration)!}"
                    name="_notificationSubDetail_expireDuration" type="text">
                    
            </div>
            
            <div class="form-group" >
                <label class="font-bold">Lịch kiểm tra để gửi thông báo</label>
                <select class="form-control" id="_notificationSubDetail_interval" name="_notificationSubDetail_interval">
                    <#if constants.notification_interval?has_content>
                      
                        <#list constants.notification_interval as oInterval>
                            
                            <option value="${(oInterval.value)!}" 
                            	<#if oInterval.value == notificationTemplate.interval>
                                selected="true"
                            	</#if>
                            >
                                ${(oInterval.text)!}
                            </option>
                        </#list>
                    </#if> 
                     
                            
                </select>
    
            </div>
            
            <div class="form-group" >
                
                <label class="font-bold">Địa chỉ truy cập dành cho user</label>
                
                <input class="form-control" id="_notificationSubDetail_userUrlPattern"
                    value="${(notificationTemplate.userUrlPattern)!}"
                    name="_notificationSubDetail_userUrlPattern" type="text">
                    
            </div>
            
            <div class="form-group" >
                
                <label class="font-bold">Địa chỉ truy cập dành cho guest</label>
                
                <input class="form-control" id="_notificationSubDetail_guestUrlPattern"
                    value="${(notificationTemplate.guestUrlPattern)!}"
                    name="_notificationSubDetail_guestUrlPattern" type="text">
                    
            </div>
			
			<!--_notificationSubDetail_send sms email-->
			
			<div class="form-group" >
				
				<div class="row">
					
					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 font-bold"> 
					
						<div class="form-group form-inline input-checkbox-wrapper"> 
						
							<input class="field" id="_notificationSubDetail_sendEmail" name="_notificationSubDetail_sendEmail"
								<#if ((notificationTemplate.sendEmail)!false)! == true>
										checked=""
								</#if> 
								value="1" type="checkbox" >
							<label for="_notificationSubDetail_sendEmail"> Gửi Email </label> 
							
						</div> 
						
					</div>

					<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 font-bold"> 
					
						<div class="form-group form-inline input-checkbox-wrapper"> 
						
							<input class="field" id="_notificationSubDetail_sendSMS" name="_notificationSubDetail_sendSMS"
								<#if ((notificationTemplate.sendSMS)!false)! == true>
										checked=""
								</#if> 
								value="1" type="checkbox" >
							<label for="_notificationSubDetail_sendSMS"> Gửi SMS </label> 
							
						</div> 
						
					</div> 

				</div>
					
			</div>
			
			<div class="form-group text-right" >
					
				<button class="btn btn-sm btn-active" id="_notification_submitEditNotification" 
					name="_notification_submitEditNotification" type="button" 
					data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang lưu thông tin...">
					<i class="fa fa-check-circle"></i> 
					<span class="lfr-btn-label">Cập nhật thay đổi</span> 
				</button> 
				
			</div>
			
		</form>
		
	</div>

</div>


<script type="text/javascript">

(function($) {
	
	$('#_notificationSubDetail_emailBody').summernote();
	
	// submit
	$(' #_notification_submitEditNotification ').click(function(event) {
	
		event.preventDefault();
		event.stopPropagation();
		event.stopImmediatePropagation();
		
		var validator = $("#_notificationSubDetail_form").kendoValidator().data("kendoValidator");
				
			if (!validator.validate()) {
				return;
			}
		
		$("#_notification_listView").getKendoListView().dataSource.transport.update({
			
			notificationType: $('#_notificationSubDetail_notificationType').val(),
			emailSubject: $('#_notificationSubDetail_emailSubject').val(),
			emailBody: $('#_notificationSubDetail_emailBody').summernote('code'),
			textMessage: $('#_notificationSubDetail_textMessage').val(),
			expireDuration: $('#_notificationSubDetail_expireDuration').val(),
			interval: $('#_notificationSubDetail_interval').val(),
			userUrlPattern: $('#_notificationSubDetail_userUrlPattern').val(),
			guestUrlPattern: $('#_notificationSubDetail_guestUrlPattern').val(),
			sendEmail: $("#_notificationSubDetail_sendEmail").prop('checked'),
			sendSMS: $("#_notificationSubDetail_sendSMS").prop('checked')
			
		});
		
	});
	

})(jQuery);

</script>

