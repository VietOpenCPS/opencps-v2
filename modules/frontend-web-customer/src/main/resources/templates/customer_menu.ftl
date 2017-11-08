<#if (Request)??>
	<#include "init.ftl">
</#if>
<div class="row">
	<div class="col-sm-12">
		<button class="btn btn-active form-control" id="btn_create_new_dossier">Tạo hồ sơ mới</button>
	</div>
	<div class="col-sm-12">
		<ul id="profileStatus" class="ul-default ul-with-left-icon" style="margin-left: 10px;">
			<li dataPk='New' class='itemStatus PT10' data-filterdate="submitDate">
				<i class='fa fa-folder-o' aria-hidden='true'></i>  
				<a href='javascript:;' > Hồ sơ mới</a>
			</li>
			<li dataPk='Receiving' class='itemStatus' data-filterdate="receiveDate">
				<i class='fa fa-folder-o' aria-hidden='true'></i>  
				<a href='javascript:;' > Hồ sơ đang thực hiện</a>
			</li> 
			<li dataPk='Waiting' class='itemStatus' data-filterdate="correctingDate">
				<i class='fa fa-folder-o' aria-hidden='true'></i>  
				<a href='javascript:;' > Hồ sơ chờ bổ sung</a>
			</li>
			<li dataPk='Paying' class='itemStatus' data-filterdate="correctingDate">
				<i class='fa fa-folder-o' aria-hidden='true'></i>  
				<a href='javascript:;' > Hồ sơ chờ thanh toán</a>
			</li>
			<li dataPk='Done' class='itemStatus' data-filterdate="finishDate">
				<i class='fa fa-folder-o' aria-hidden='true' ></i>  
				<a href='javascript:;' > Hồ sơ đã kết thúc</a>
			</li>
		</ul>
		<input type="hidden" name="monthYearFilter" id="monthYearFilter" value="submitDate">
	</div>

	<div class="col-sm-12 MT20">
		<div class="form-group">
			<input class="form-control" name="serviceInfo" id="serviceInfo">
		</div>
	</div>
	<div class="col-sm-12">
		<div class="form-group">
			<input class="form-control" name="govAgencyCode" id="govAgencyCode" >
		</div>
	</div>
	<div class="col-sm-12">
		<div class="row">
			<div class="col-sm-6">
				<div class="form-group">
					<input class="form-control" name="year" id="year" >
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<input class="form-control" name="month" id="month" >
				</div>
			</div>
		</div>
	</div>
</div>

