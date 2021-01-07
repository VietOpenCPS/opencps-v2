<%@page import="com.liferay.portal.kernel.log.LogFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.log.Log"%>
<%@page import="java.util.Base64"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
	String state = (String)request.getAttribute("state");
	String code = (String)request.getAttribute("code");
	String redirect = (String)request.getAttribute("redirect");
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

<!-- <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/gasparesganga-jquery-loading-overlay@2.1.6/dist/loadingoverlay.min.js"></script> -->

<script src="jquery-3.4.1.min.js"></script>
<script src="loadingoverlay.min.js"></script>
<script src="js.cookie-2.2.1.min.js"></script>
<script type="text/javascript">
	$( document ).ready(function() {
		var state = '<%=state%>';
	 	var redirect = '<%=redirect%>';
	 	var code = '<%=code%>';
	 
		$.ajax('/o/rest/v2/dvcqgsso/userinfo?authToken=' + code +'&state=' + state + '&provider=ssocs',{
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
					var id_token = data.id_token;
					var access_token = data.access_token;
					setCookie('id_token', id_token, 1);
					setCookie('access_token', access_token, 1);
					//$.cookie("id_token", id_token, { expires : 1,  path : '/', secure : true });
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
		
		
		function setCookie(name,value,days) {
		    var expires = "";
		    if (days) {
		        var date = new Date();
		        date.setTime(date.getTime() + (days*24*60*60*1000));
		        expires = "; expires=" + date.toUTCString();
		    }
		    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
		}
	});
</script>


<%! private Log _log = LogFactoryUtil.getLog("dvcqssso.index.jsp");%>