<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="col-xs-2">	
	<div class = "row">
		<div class="col-sm-12">
			<button class="btn btn-active form-control" id="btn_create_new_dossier">Thêm mới kết quả</button>
		</div>

		<div class="col-md-12 MT10 filterField">
			<div class="form-group search-icon">
                <input type="text" class="form-control" placeholder="Nhập từ khóa">
            </div>
		</div>

		<div class="col-sm-12 filterField">
			<select class="form-control MB15" style="font-size: 13px">
                <option value="0" selected="">Trạng thái cấp giấy</option>
                <option value="1">1</option>
                <option value="2">2</option>
            </select>
		</div>

		<div class="col-sm-12 filterField">
			<select class="form-control MB15" style="font-size: 13px">
                <option value="0" selected="">Chủ giấy pháp/Giấy chứng nhận</option>
                <option value="1">1</option>
                <option value="2">2</option>
            </select>
		</div>

		<div class="col-sm-12 MB10">
			<div class="accordion">
				<div class="accordion-group">
					<div class="accordion-heading" style="background-color: #14bef0;border: none;font-family: 'Roboto-Regular'">
						<a class="" style="color: #ffffff" data-toggle="collapse" href="#groupFilterStatus">
							Danh sách hiển thị
						</a>
					</div>
					<div id="groupFilterStatus" class="accordion-body collapse in">
						<div class="accordion-inner">
							<ul id="profileStatus" class="ul-default ul-with-left-icon icon-folder have-bagde">
								<li dataPk='1' class='itemStatus'>
									<i class='fa fa-folder icon-left' aria-hidden='true'></i>  
									<a href='javascript:;' id="filterStatus1" value ="1"> Mới cấp</a>
								</li>
								<li dataPk='2' class='itemStatus'>
									<i class='fa fa-folder icon-left' aria-hidden='true' ></i>  
									<a href='javascript:;' id="filterStatus2" value ="2"> Sắp hết hạn</a>
								</li>
								<li dataPk='3' class='itemStatus'>
									<i class='fa fa-folder icon-left' aria-hidden='true'></i>  
									<a href='javascript:;' id="filterStatus3" value ="3"> Đã hết hạn</a>
								</li> 
								<li dataPk='4' class='itemStatus'>
									<i class='fa fa-folder icon-left' aria-hidden='true'></i>  
									<a href='javascript:;' id="filterStatus4" value ="4"> Tạm dừng</a>
								</li>
								<li dataPk='5' class='itemStatus'>
									<i class='fa fa-folder icon-left' aria-hidden='true'></i>  
									<a href='javascript:;' id="filterStatus5" value ="5"> Thu hồi</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<#-- TEMPLATE TRANG QUẢN LÝ HỒ SƠ/ C-04 -->
<div class="col-sm-10" id="result_right_content">
	<#include "result_mainlist.ftl">
</div>

<#include "result_router.ftl">

<script>
	$('.itemStatus').click(function(e){
		e.preventDefault();
		$("#profileStatus li").removeClass("active");
		$(e.currentTarget).addClass("active");
		$(".itemStatus").css("pointer-events","auto");
		$(e.currentTarget).css("pointer-events","none");
		$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
		$(e.currentTarget).children("i").removeClass("fa fa-folder").addClass("fa fa-folder-open");
		var id = $(this).attr('dataPk');
		resultRouter.navigate("/"+id)	
	});
	
	$('#test').click(function(){ openWindow(); return false; });
</script>
