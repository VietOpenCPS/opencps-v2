<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="panel panel-default">
	<div class="panel-heading">THÔNG TIN HỒ SƠ |</div>
	<div class="panel-body">
		<p><strong>Tên hồ sơ:</strong></p>
		<strong>Mã hồ sơ:</strong>
		<p><strong>Mã tiếp nhận:</strong></p>
		<p><strong>Cơ quan thực hiện:</strong></p>
		<p><strong>Người nộp hồ sơ:</strong></p>
		<p><strong>Ngày nộp:</strong></p>
		<p><strong>Ngày hẹn trả:</strong></p>
		<p><strong>Tình trạng hồ sơ:</strong></p>
	</div>
	<div class="panel-footer">
		<div class="col-sm-6">
			<p>Bạn muốn xem chi tiết thông tin hồ sơ >></p>
		</div>
		<div class="input-group MB15">
			<input id="input_search_serviceinfo" type="text" class="form-control" placeholder="Nhập mã bí mật">
			<div class="input-group-btn">
				<button class="btn btn-default" type="submit">
					Tra cứu
				</button>
			</div>
		</div>
	</div>
</div>