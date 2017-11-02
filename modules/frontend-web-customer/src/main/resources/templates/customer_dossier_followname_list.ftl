<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="row MT20">
	<div class="col-sm-4">
		<button class="k-button"><i class="glyphicon glyphicon-edit"></i> Tìm theo cơ quan</button>
		<button class="k-button"><i class="glyphicon glyphicon-edit"></i> Tìm theo tên</button>
	</div>
	<div class="col-sm-8">
		<div class="form-group"> 
			<div class="input-group stylish-input-group"> 
				<input type="text" class="form-control" placeholder="Nhập từ khóa"> 
				<span class="input-group-addon"> 
					<button type="submit">
						<span class="glyphicon glyphicon-search icon-search"></span> 
					</button> 
				</span> 
			</div> 
		</div>
	</div>
</div>
<div class="panel panel-main"> 
	<div class="panel-heading"> 
		<h3 class="panel-title">CỤC BẢN QUYÊN TÁC GIẢ</h3> 
		<span class="pull-right clickable panel-collapsed"> 
			<i class="glyphicon glyphicon-chevron-down"></i> 
		</span> 
	</div>
	<div class="panel-body" style="display: none;">
		<div class="panel panel-main"> 
			<div class="panel-heading"> 
				<h3 class="panel-title">Cấp giấy chứng nhận quyền đăng ký tác gỉa cho cá nhân tổ chức Việt Nam</h3> 
				<span class="pull-right clickable panel-collapsed"> 
					<i class="glyphicon glyphicon-chevron-down"></i> 
				</span> 
			</div>
			<div class="panel-body" style="display: none;">
				<div class="row" id=""></div>
				<div class="row" id=""></div>
				<script type="text/x-kendo-template">
					<div class="col-sm-10">
						
					</div>
					<div class="col-sm-1">
						Mức 4
					</div>
					<div class="col-sm-1">
						Chọn
					</div>
				</script>
			</div> 
		</div>

		<div class="panel panel-main"> 
			<div class="panel-heading"> 
				<h3 class="panel-title">Cấp thẻ giám định viên, quyền tác gỉa liên quan</h3> 
				<span class="pull-right clickable panel-collapsed"> 
					<i class="glyphicon glyphicon-chevron-down"></i> 
				</span> 
			</div>
			<div class="panel-body" style="display: none;">
				<div class="row" id=""></div>
				<div class="row" id=""></div>
				<script type="text/x-kendo-template">
					<div class="col-sm-10">
						
					</div>
					<div class="col-sm-1">
						Mức 4
					</div>
					<div class="col-sm-1">
						Chọn
					</div>
				</script>
			</div> 
		</div>
	</div> 
</div>

<div class="panel panel-main"> 
	<div class="panel-heading"> 
		<h3 class="panel-title">CỤC BẢO VÊ VĂN HÓA</h3> 
		<span class="pull-right clickable panel-collapsed"> 
			<i class="glyphicon glyphicon-chevron-down"></i> 
		</span> 
	</div>
	<div class="panel-body" style="display: none;">
		<div class="panel panel-main"> 
			<div class="panel-heading"> 
				<h3 class="panel-title">Cấp giấy phép thăm dò khảo quật khảo cổ	</h3> 
				<span class="pull-right clickable panel-collapsed"> 
					<i class="glyphicon glyphicon-chevron-down"></i> 
				</span> 
			</div>
			<div class="panel-body" style="display: none;">
				<div class="row" id=""></div>
				<div class="row" id=""></div>
				<script type="text/x-kendo-template">
					<div class="col-sm-10">
						
					</div>
					<div class="col-sm-1">
						Mức 4
					</div>
					<div class="col-sm-1">
						Chọn
					</div>
				</script>
			</div> 
		</div>

		<div class="panel panel-main"> 
			<div class="panel-heading"> 
				<h3 class="panel-title">Cấp giấy phép mang di vật, cổ vật thuộc sở hữu của nhà nước tổ chức chính trị</h3>  
				<span class="pull-right clickable panel-collapsed"> 
					<i class="glyphicon glyphicon-chevron-down"></i> 
				</span> 
			</div>
			<div class="panel-body" style="display: none;">
				<p>dddddddddddddddddddddddddd</p>
				<div class="row" id=""></div>
				<div class="row" id=""></div>
				<script type="text/x-kendo-template">
					<div class="col-sm-10">
						
					</div>
					<div class="col-sm-1">
						Mức 4
					</div>
					<div class="col-sm-1">
						Chọn
					</div>
				</script>
			</div> 
		</div>
	</div> 
</div>

<div class="panel panel-main"> 
	<div class="panel-heading"> 
		<h3 class="panel-title">XUÂT NHÂPJ KHÂỦ VĂN HÓA</h3> 
		<span class="pull-right clickable panel-collapsed"> 
			<i class="glyphicon glyphicon-chevron-down"></i> 
		</span> 
	</div>
	<div class="panel-body" style="display: none;">
		
	</div> 
</div>

<div class="panel panel-main"> 
	<div class="panel-heading"> 
		<h3 class="panel-title">NHÂP KHÂỦ VĂN HÓA</h3> 
		<span class="pull-right clickable panel-collapsed"> 
			<i class="glyphicon glyphicon-chevron-down"></i> 
		</span> 
	</div>
	<div class="panel-body" style="display: none;">
		
	</div> 
</div>

<script type="text/javascript">
	$(document).on('click', '.panel-heading span.clickable', function(e) {
		var $this = $(this);
		if (!$this.hasClass('panel-collapsed')) {
			$this.parents('.panel').find('.panel-body').slideUp();
			$this.addClass('panel-collapsed');
			$this.find('i').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
		} else {
			$this.parents('.panel').find('.panel-body').slideDown();
			$this.removeClass('panel-collapsed');
			$this.find('i').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
		}
	});
</script>