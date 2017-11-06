<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h4 class="modal-title">Khai trực tuyến</h4>
		</div>
		<div class="modal-body">
			<div class="row-parts-content">
				<div class="row">
					<div class="col-xs-12 col-sm-2 text-right">
						<label class="with-input-sm">Kính gủi:</label>
					</div>
					<div class="col-xs-12 col-sm-10">
						<input type="text" class="form-control input-small" placeholder="Ghi rõ tên Cơ quan, Phòng ban nhà nước cần làm thủ tục">
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-2 text-right">
						<label class="with-input-sm">Tên người gửi:</label>
					</div>
					<div class="col-xs-12 col-sm-4">
						<input type="text" class="form-control input-small" placeholder="Họ và tên cá nhân">
					</div>
					<div class="col-xs-12 col-sm-2 text-right">
						<label class="with-input-sm">Số điện thoại:</label>
					</div>
					<div class="col-xs-12 col-sm-4">
						<input type="text" class="form-control input-small" placeholder="">
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-2 text-right">
						<label class="with-input-sm">Email/Thư điện tử:</label>
					</div>
					<div class="col-xs-12 col-sm-10">
						<input type="text" class="form-control input-small" placeholder="">
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-2 text-right">
						<label class="with-input-sm">Mục đích:</label>
					</div>
					<div class="col-xs-12 col-sm-10">
						<input type="text" class="form-control input-small" placeholder="">
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-2 text-right"></div>
					<div class="col-xs-12 col-sm-10">
						<div class="checkbox MT0">
							<input type="checkbox"> <label class="text-normal">Bản photo công chứng Văn bản cần đăng ký Bản quyền tác giả</label>
						</div>
					</div>
					<div class="col-xs-12 col-sm-2 text-right"></div>
					<div class="col-xs-12 col-sm-10">
						<div class="checkbox MT0">
							<input type="checkbox"> <label class="text-normal">Bản photo công chứng Văn bản cần đăng ký Bản quyền tác giả</label>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-2 text-right">
						<label class="with-input-sm">Ngày khai hồ sơ:</label>
					</div>
					<div class="col-xs-12 col-sm-4">
						<input type="text" class="form-control input-small" placeholder="">
					</div>
					<div class="col-xs-12 col-sm-2 text-right">
						<label class="with-input-sm">Chữ ký:</label>
					</div>
					<div class="col-xs-12 col-sm-4">
						<input type="text" class="form-control input-small" placeholder="">
					</div>
				</div>
				<div class="row MB10">
					<div class="col-xs-12 col-sm-2 text-right"></div>
					<div class="col-xs-12 col-sm-10">
						<button class="btn btn-active MR15 btn-hover"><i class="fa fa-paper-plane"></i> Ghi lại</button>
						<button class="btn btn-hover" data-dismiss="modal">Đóng</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>