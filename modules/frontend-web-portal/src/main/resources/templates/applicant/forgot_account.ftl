<form id="fmPasswordWorkflow">
	<div class="row box MA" style="max-width: 500px;">
		<div class="col-sm-12 text-center MB5">
			<h3>QUÊN MÂT KHÂỦ</h3>
		</div>
		<div class="col-sm-12 text-center MB5">
			<p>Vui lòng nhập Email hoặc số CMTND/ Hộ chiếu/ Mã số thuế để tìm kiếm tài khoản</p>
		</div>
		<div class="col-sm-12">
			<div class="form-group"> 
				<input type="text" id="emailOrPhone" class="form-control" placeholder="Email hoặc số CMTND/ Hộ chiếu/ Mã số thuế" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" > 
			</div>
		</div>
		<div class="col-sm-12 text-center MT10 MB15">
			<button class="btn btn-active MR10" id="btn-next-step" type="button">Tiếp tục</button> <button class="btn">Hủy bỏ</button>
		</div>
	</div>
</form>

<script type="text/javascript">
	$("#btn-next-step").click(function() {
		var validator = $("#fmPasswordWorkflow").kendoValidator().data("kendoValidator");
		if(validator.validate()){
			var data = $("#fmPasswordWorkflow").serialize();
			$.ajax({
				url : "",
				dataType : "json",
				type : "GET",
				data : data,
				success : function(result){

				},
				error : function(xhr){

				}
			});

			if($("#emailOrPhone").val()!= ""){
				$("#password_workflow_content").load("${ajax.confirm_password}",function(result){

				});
			}
		}
		
	});
</script>