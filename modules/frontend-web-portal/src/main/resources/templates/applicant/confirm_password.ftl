<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row box MA" style="max-width: 550px;">
	<div class="col-sm-12 text-center">
		<h4><b>NHẬP MÃ BẢO MẬT</b></h4>
	</div>
	<div class="col-sm-12 text-center MB10">
		<p>Bạn vui lòng kiểm tra email hoặc số điện thoại để xem nội dung văn bản có mã.</p>
	</div>
	<div class="col-sm-12">
		<div class="form-group">
			<input id="vertifyCode" type="text" class="form-control" placeholder="Nhập mã">
		</div>
	</div>
	<#-- <div class="col-sm-3 with-input">
		<a href="#" class="text-hover-blue">Bạn chưa có mã?</a>
	</div> -->
	<div class="col-sm-12 text-center MT15 MB15">
		<button class="btn btn-active MR10" id="btn-next-step1">Gửi</button>
	</div>
</div>
<script type="text/javascript">
	$("#btn-next-step1").click(function() {
		$.ajax({
			url : "${api.server}/users/"+$("#emailOrPhone").val()+"/forgot/confirm/"+$("#vertifyCode").val(),
			dataType : "json",
			type : "GET",
			success : function(result){

			},
			error : function(xhr){

			},
			statusCode:{
				200: function(result) {
					notification.show({
						title: "Success",
						message: "Xác thực thành công. Mời bạn vào hòm thư để lấy lại mật khẩu mới!!!"
					}, "success");
					setTimeout(function(){
			            window.location.href = "/web/cong-tiep-nhan/login?p_p_id=com_liferay_login_web_portlet_LoginPortlet&p_p_lifecycle=0&_com_liferay_login_web_portlet_LoginPortlet_redirect=%2Fgroup%2Fcong-tiep-nhan";
          			}, 2000);
				},
				500: function(result) {
					notification.show({
						title: "Error",
						message: "Xẩy ra lỗi, vui lòng thử lại."
					}, "error");
				},
				409: function(result) {
					notification.show({
						title: "Success",
						message: "Xác thực thành công. Mời bạn vào hòm thư để lấy lại mật khẩu mới!!!"
					}, "success");
				}	
			}
		});
	});
</script>
