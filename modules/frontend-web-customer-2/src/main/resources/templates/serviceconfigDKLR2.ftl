<#if (Request)??>
<#include "init.ftl">
</#if>
<#-- Xe chở hàng bốn bánh có gắn động cơ -->
<div class="accordion-group">
	<div class="accordion-heading">
		<a class="accordion-toggle" data-toggle="collapse" href="#lv14G1"> 
			<i class="fa fa-play PR5" aria-hidden="true" style="color: #14bef0"></i>Xe chở hàng bốn bánh có gắn động cơ
		</a>
	</div>
	<div id="lv14G1" class="accordion-body collapse">
		<div class="accordion-inner PB0 PT0">
			<#-- lv23G1 -->
			<div class="PT5">
				<div class="align-middle PL40">
					<a data-toggle="collapse" href="#item41"> 
						<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Chứng nhận Chất lượng kiểu loại
					</a>
				</div>
				<div id="item41" class="collapse MT10">
					<div class="PB10">
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip1item41" class="MR10">Cấp mới</label>
								<input type="text" class="form-control" id="ip1item41" placeholder="Nhập số GCN (hồ sơ) CNCL kiểu loại">
								<button class="btn btn-reset pull-right chooseService P0 PT5" id="btn1item41">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip2item41" class="MR10">Mở rộng</label>
								<input type="text" class="form-control" id="ip2item41" placeholder="Nhập số GCN hồ sơ gốc">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn2item41">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip3item41" class="MR20">Cấp lại</label>
								<input type="text" class="form-control" id="ip3item41" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn3item41">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
			<#-- lv24G1 -->
			<div class="PT5 PB5">
				<div class="align-middle PL40">
					<a data-toggle="collapse" href="#item42"> 
						<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Cấp phát phôi phiếu
					</a>
				</div>
				<div id="item42" class="collapse MT10">
					<div class="PB10">
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip1item42" class="MR45">Phương thức 1: cấp theo lô</label>
								<input type="text" class="form-control" id="ip1item42" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1item42">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip2item42" class="MR60">Phương thức 2: giám sát</label>
								<input type="text" class="form-control" id="ip2item42" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn2item42">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip3item42" class="MR10">Phương thức 3: kiểm tra đột xuất</label>
								<input type="text" class="form-control" id="ip3item42" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn3item42">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<#-- Xe đạp điện -->
<div class="accordion-group">
	<div class="accordion-heading">
		<a class="accordion-toggle" data-toggle="collapse" href="#lv15G1"> 
			<i class="fa fa-play PR5" aria-hidden="true" style="color: #14bef0"></i>Xe đạp điện
		</a>
	</div>
	<div id="lv15G1" class="accordion-body collapse">
		<div class="accordion-inner PB0 PT0">
			<#-- lv23G1 -->
			<div class="PT5">
				<div class="align-middle PL40">
					<a data-toggle="collapse" href="#item51"> 
						<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Chứng nhận Chất lượng kiểu loại
					</a>
				</div>
				<div id="item51" class="collapse MT10">
					<div class="PB10">
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip1item51" class="MR10">Cấp mới</label>
								<input type="text" class="form-control" id="ip1item51" placeholder="Nhập số GCN (hồ sơ) CNCL kiểu loại">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1item51">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip2item451" class="MR10">Mở rộng</label>
								<input type="text" class="form-control" id="ip2item51" placeholder="Nhập số GCN hồ sơ gốc">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn2item51">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip3item51" class="MR20">Cấp lại</label>
								<input type="text" class="form-control" id="ip3item51" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn3item51">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
			<#-- lv24G1 -->
			<div class="PT5 PB5">
				<div class="align-middle PL40">
					<a data-toggle="collapse" href="#item52"> 
						<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Cấp phát phôi phiếu
					</a>
				</div>
				<div id="item52" class="collapse MT10">
					<div class="PB10">
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip1item52" class="MR45">Phương thức 1: cấp theo lô</label>
								<input type="text" class="form-control" id="ip1item52" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1item52">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip2item52" class="MR60">Phương thức 2: giám sát</label>
								<input type="text" class="form-control" id="ip2item52" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn2item52">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip3item52" class="MR10">Phương thức 3: kiểm tra đột xuất</label>
								<input type="text" class="form-control" id="ip3item52" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn3item52">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<#-- Xe máy chuyên dùng -->
<div class="accordion-group">
	<#--lv1G1 -->
	<div class="accordion-heading">
		<a class="accordion-toggle" data-toggle="collapse" href="#lv16G1"> 
			<i class="fa fa-play PR5" aria-hidden="true" style="color: #14bef0"></i>Xe chuyên dùng
		</a>
	</div>
	<div id="lv16G1" class="accordion-body collapse">
		<div class="accordion-inner PB0 PT0">
			<#-- lv2G1 -->
			<div class="PT5">
				<div class="ML40">
					<a data-toggle="collapse" href="#item61"> 
						<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Thẩm định thiết kế
					</a>
				</div>
				<div id="item61" class="collapse MT10">
					<#-- lv3G1 -->
					<div class="PB10">
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip1item61" class="MR10">Cấp mới</label>
								<input type="text" class="form-control" id="ip1item61" placeholder="Nhập số GCN (hồ sơ) thẩm định thiết kế">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1item61">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<#-- lv31G1 -->
					<div class="PB10">
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip2item61" class="MR10">Mở rộng</label>
								<input type="text" class="form-control" id="ip2item61" placeholder="Nhập số GCN hồ sơ gốc">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn2item61">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
					</div>
					<#-- lv32G1 -->
					<div class="PB10">
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip3item61" class="MR20">Cấp lại</label>
								<input type="text" class="form-control" id="ip3item61" placeholder="Nhập số GCN hồ sơ gốc/ mở rộng">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn3item61">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
			<#-- lv23G1 -->
			<div class="PT5">
				<div class="align-middle PL40">
					<a data-toggle="collapse" href="#item62"> 
						<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Chứng nhận Chất lượng kiểu loại
					</a>
				</div>
				<div id="item62" class="collapse MT10">
					<div class="PB10">
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip1item62" class="MR10">Cấp mới</label>
								<input type="text" class="form-control" id="ip1item62" placeholder="Nhập số GCN (hồ sơ) thẩm định thiết kế">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1item62">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip2item62" class="MR10">Mở rộng</label>
								<input type="text" class="form-control" id="ip2item62" placeholder="Nhập số GCN hồ sơ gốc">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn2item62">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip3item62" class="MR20">Cấp lại</label>
								<input type="text" class="form-control" id="ip3item62" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn3item62">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
			<#-- lv24G1 -->
			<div class="PT5 PB5">
				<div class="align-middle PL40">
					<a data-toggle="collapse" href="#item63"> 
						<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Cấp phát phôi phiếu
					</a>
				</div>
				<div id="item63" class="collapse MT10">
					<div class="PB10">
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip1item63" class="MR45">Phương thức 1: cấp theo lô</label>
								<input type="text" class="form-control" id="ip1item63" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1item63">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip2item63" class="MR60">Phương thức 2: giám sát</label>
								<input type="text" class="form-control" id="ip2item63" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn2item63">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip3item63" class="MR10">Phương thức 3: kiểm tra đột xuất</label>
								<input type="text" class="form-control" id="ip3item63" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn3item63">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<#-- Linh kiện phụ tùng -->
<div class="accordion-group">
	<#--lv1G1 -->
	<div class="accordion-heading">
		<a class="accordion-toggle" data-toggle="collapse" href="#lv17G1"> 
			<i class="fa fa-play PR5" aria-hidden="true" style="color: #14bef0"></i>Linh kiện phụ tùng
		</a>
	</div>
	<div id="lv17G1" class="accordion-body collapse">
		<div class="accordion-inner PB0 PT0">
			<#-- lv23G1 -->
			<div class="PT5">
				<div class="align-middle PL40">
					<a data-toggle="collapse" href="#item71"> 
						<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Chứng nhận Chất lượng kiểu loại LK xe cơ giới
					</a>
				</div>
				<div id="item71" class="collapse MT10">
					<div class="PB10">
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip1item71" class="MR10">Cấp mới</label>
								<input type="text" class="form-control" id="ip1item71" placeholder="Nhập số GCN (hồ sơ) CNCL kiểu loại LK">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1item71">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip2item71" class="MR10">Mở rộng</label>
								<input type="text" class="form-control" id="ip2item71" placeholder="Nhập số GCN hồ sơ gốc">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn2item71">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip3item71" class="MR20">Cấp lại</label>
								<input type="text" class="form-control" id="ip3item71" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn3item71">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
			<#-- lv24G1 -->
			<div class="PT5 PB5">
				<div class="align-middle PL40">
					<a data-toggle="collapse" href="#item72"> 
						<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Chứng nhận Chất lượng kiểu loại LK xe mô tô, xe gắn máy
					</a>
				</div>
				<div id="item72" class="collapse MT10">
					<div class="PB10">
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip1item72" class="MR10">Cấp mới</label>
								<input type="text" class="form-control" id="ip1item72" placeholder="Nhập số GCN (hồ sơ) CNCL kiểu loại LK">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1item72">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip2item72" class="MR10">Mở rộng</label>
								<input type="text" class="form-control" id="ip2item72" placeholder="Nhập số GCN hồ sơ gốc">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn2item72">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip3item72" class="MR20">Cấp lại</label>
								<input type="text" class="form-control" id="ip3item72" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn3item72">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
			<#--  -->
			<div class="PT5 PB5">
				<div class="align-middle PL40">
					<a data-toggle="collapse" href="#item73"> 
						<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Chứng nhận Chất lượng kiểu loại LK xe đạp điện
					</a>
				</div>
				<div id="item73" class="collapse MT10">
					<div class="PB10">
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip1item73" class="MR10">Cấp mới</label>
								<input type="text" class="form-control" id="ip1item73" placeholder="Nhập số GCN (hồ sơ) CNCL kiểu loại LK">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1item73">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip2item73" class="MR10">Mở rộng</label>
								<input type="text" class="form-control" id="ip2item73" placeholder="Nhập số GCN hồ sơ gốc">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn2item73">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip3item73" class="MR20">Cấp lại</label>
								<input type="text" class="form-control" id="ip3item73" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn3item73">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
			<#--  -->
			<div class="PT5 PB5">
				<div class="align-middle PL40">
					<a data-toggle="collapse" href="#item74"> 
						<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Chứng nhận Chất lượng kiểu loại LK xe máy chuyên dùng
					</a>
				</div>
				<div id="item74" class="collapse MT10">
					<div class="PB10">
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip1item74" class="MR10">Cấp mới</label>
								<input type="text" class="form-control" id="ip1item74" placeholder="Nhập số GCN (hồ sơ) CNCL kiểu loại LK">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1item74">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip2item74" class="MR10">Mở rộng</label>
								<input type="text" class="form-control" id="ip2item74" placeholder="Nhập số GCN hồ sơ gốc">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn2item74">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip3item74" class="MR20">Cấp lại</label>
								<input type="text" class="form-control" id="ip3item74" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn3item74">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
			<#--  -->
			<div class="PT5 PB5">
				<div class="align-middle PL40">
					<a data-toggle="collapse" href="#item75"> 
						<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Chứng nhận Chất lượng kiểu loại LK xe chở người bốn bánh
					</a>
				</div>
				<div id="item75" class="collapse MT10">
					<div class="PB10">
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip1item75" class="MR10">Cấp mới</label>
								<input type="text" class="form-control" id="ip1item75" placeholder="Nhập số GCN (hồ sơ) CNCL kiểu loại LK">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1item75">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip2item75" class="MR10">Mở rộng</label>
								<input type="text" class="form-control" id="ip2item75" placeholder="Nhập số GCN hồ sơ gốc">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn2item75">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip3item75" class="MR20">Cấp lại</label>
								<input type="text" class="form-control" id="ip3item75" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn3item75">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
			<#--  -->
			<div class="PT5 PB5">
				<div class="align-middle PL40">
					<a data-toggle="collapse" href="#item76"> 
						<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Chứng nhận Chất lượng kiểu loại LK xe chở người bốn bánh
					</a>
				</div>
				<div id="item76" class="collapse MT10">
					<div class="PB10">
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip1item76" class="MR10">Cấp mới</label>
								<input type="text" class="form-control" id="ip1item76" placeholder="Nhập số GCN (hồ sơ) CNCL kiểu loại LK">
								<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1item76">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip2item76" class="MR10">Mở rộng</label>
								<input type="text" class="form-control" id="ip2item76" placeholder="Nhập số GCN hồ sơ gốc">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn2item76">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
						<div class="ML80">
							<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
								<label for="ip3item76" class="MR20">Cấp lại</label>
								<input type="text" class="form-control" id="ip3item76" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
								<span class="red ML10">(*)</span>
								<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn3item76">Chọn</button>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>