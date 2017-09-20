<#if (Request)??>
<#include "init.ftl">
</#if>

<form id="fmPasswordWorkflow">
	<div class="row box MA" style="max-width: 550px;">
		<div class="col-sm-12 text-center MB5">
			<h4><b>QUÊN MẬT KHẨU</b></h4>
		</div>
		<div class="col-sm-12 text-center MB5">
			<p>Vui lòng nhập Email hoặc số CMND/ Hộ chiếu/ Mã số thuế để tìm kiếm tài khoản.</p>
		</div>
		<div class="col-sm-12">
			<div class="form-group">
				<input type="text" id="emailOrPhone" class="form-control" placeholder="Email hoặc số CMND/ Hộ chiếu/ Mã số thuế" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" >
			</div>
		</div>
		<div class="col-sm-12 text-center MT10 MB15">
			<button class="btn btn-active MR10" id="btn-next-step" type="button">Đồng ý</button>
		</div>
	</div>
</form>

<div class="MT15" id="password_workflow_content"></div>

<script type="text/javascript">
	$("#btn-next-step").click(function() {
		var validator = $("#fmPasswordWorkflow").kendoValidator().data("kendoValidator");
		if(validator.validate()){
			var data = $("#fmPasswordWorkflow").serialize();
			$.ajax({
				url : "${api.server}/users/"+$("#emailOrPhone").val()+"/forgot",
				dataType : "json",
				type : "GET",
				data : data,
				success : function(result){
					if($("#emailOrPhone").val()!= ""){
						$("#password_workflow_content").load("${ajax.confirm_password}",function(result){

						});
					}
				},
				error : function(xhr){

				}
			});

			
		}

	});
</script>
