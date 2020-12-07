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
	 
		$.ajax('/o/rest/v2/dvcqgsso/authMic?authCode=' + code,{
			type: 'GET', 
			dataType: 'json', 
			timeout: 10000,
			async: false,
			success: function (data,status,xhr) {
				$.LoadingOverlay('show');

				if($.isEmptyObject(data)){
					alert("Không lấy được thông tin từ máy chủ");
					window.location.href = '/';
				}else{
					var idToken = data.idToken;
					var accessToken = data.accessToken;
					setCookie('id_token', idToken, 1);
					setCookie('access_token', accessToken, 1);
					var statusCode = parseInt(data.statusCode)

					if(statusCode == 1) {
						alert("Không lấy được token đăng nhập từ MIC.");
						window.location.href = '/';
					} else if(statusCode == 2) {
						alert("Không lấy được thông tin người dùng từ MIC.");
						window.location.href = '/';
					} else if(statusCode == 6) {
						alert("Không lấy được thông tin người dùng từ hệ thống một cửa.");
						window.location.href = '/';
					} else if(statusCode == 5) {
						$('#userinfo').append('Authorization successful');
						window.location.href = redirect;
					} else if(statusCode = 3) {
						alert("Lỗi hệ thống, vui lòng thử lại sau.");
						window.location.href = '/';
					} else {
						alert("STATUS CODE INVALID");
						window.location.href = '/';
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


<%! private Log _log = LogFactoryUtil.getLog("dvcqssso.indexMic.jsp");%>