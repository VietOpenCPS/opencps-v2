<%@page import="com.liferay.portal.kernel.log.LogFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.log.Log"%>
<%@page import="java.util.Base64"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
	String state = (String)request.getAttribute("state");
	String code = (String)request.getAttribute("code");
	String redirect = "";
	if(state != null && !state.equals("")){
		try{
			byte[] bytes = Base64.getDecoder().decode(state);
			String _tmp = new String(bytes);
			if(_tmp.contains("@")){
				state = _tmp.substring(0, _tmp.lastIndexOf("@"));
				redirect = _tmp.substring(_tmp.lastIndexOf("@") + 1, _tmp.length());
			}		
		}catch(Exception e){
			_log.error(e);
		}
	}

%>

<style>
	div#userinfo {
		display: block;
		height: 100%; 
		width: 100%; 
		background-color: #a5caef66; 
	}
</style>
		
<div id="userinfo"></div>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/gasparesganga-jquery-loading-overlay@2.1.6/dist/loadingoverlay.min.js"></script>

<script type="text/javascript">
	$( document ).ready(function() {
		var state = '<%=state%>';
	 	var redirect = '<%=redirect%>';
	 	var code = '<%=code%>';
	 
		$.ajax('/o/rest/v2/dvcqgsso/userinfo?authToken=' + code +'&state=' + state,{
			type: 'GET', 
			dataType: 'json', 
			timeout: 5000, 
			async: false,  
			success: function (data,status,xhr) {
				$.LoadingOverlay('show');
				
				if($.isEmptyObject(data)){
					alert("Không lấy được thông tin người dùng từ DVCQG.");
					window.location.href = '/';
				}else{
					if(parseInt(data.userId) > 0){
						
						doAuth(data, redirect);
					}else{
					
						state = data.state;
						
						var encryptData = data.encryptData;
						
						if(redirect.indexOf('?') == -1){
							
							redirect = redirect + '?data=' + encryptData;

						}else{
							
							redirect = redirect + '&data=' + encryptData;
						}
						
						if(state == 'create'){
							doAuth(data, redirect);
						}else if(state == 'mapping'){
							window.location.href = redirect;
						}
						else{
							alert("Thông tin người dùng không hợp lệ");
							window.location.href = '/';
						}
					}
				}
			},
			complete: function (data,status,xhr) {
				$.LoadingOverlay('hide');
			},
			error: function (jqXhr, textStatus, errorMessage) { 
				$('#userinfo').append('Error: ' + errorMessage);
			},
			beforeSend: function (xhr) {
			
			}
		});
		
	
		function doAuth(userinfo, redirect){
			$.ajax('/o/rest/v2/dvcqgsso/auth', 
			{
				dataType: 'json', 
				timeout: 5000,
				async: false,
				type:'POST',
				headers: {
					Accept: 'application/json; charset=utf-8',
					'Content-Type': 'application/json; charset=utf-8'
				},
				data: JSON.stringify(userinfo),
				success: function (data,status,xhr) {
					if(parseInt(data.statusCode) == 200 && data.message == 'success'){
						$('#userinfo').append('Authorization successful');
						window.location.href = redirect;

					}else{
						alert("Xác thực không thành công");
						window.location.href = '/';
					}
				},
		
				error: function (jqXhr, textStatus, errorMessage) { 
					$('#userinfo').append('Error: ' + errorMessage);
					return {};
				},
				beforeSend: function (xhr) {
		
				}
			});
		};
	});
</script>


<%! private Log _log = LogFactoryUtil.getLog("dvcqssso.index.jsp");%>