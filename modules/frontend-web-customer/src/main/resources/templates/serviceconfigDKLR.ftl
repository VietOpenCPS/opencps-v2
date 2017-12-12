<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="steps align-space-between">
    <div class="step align-middle-lg done">
      <span>1</span>
      <span>Lựa chọn Dịch vụ công</span>
    </div>
    <div class="step align-middle-lg" id="step2">
      <span>2</span>
      <span>Chuẩn bị hồ sơ</span>
    </div>
    <div class="step align-middle-lg" id="step3">
      <span>3</span>
      <span>Nộp hồ sơ</span>
    </div>
</div>
<#--  -->
<div class="accordion MT25" id="accordion">
	<#-- Group1 -->
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#group1">
				</i> HỒ SƠ SẢN XUẤT LẮP RÁP
			</a>
		</div>
		<div id="group1" class="accordion-body collapse in">
			<div class="accordion-inner" style="background: #ffffff">
				<div class="accordion" id="">
					<#-- Xe cơ giới -->
					<div class="accordion-group">
						<#--lv1G1 -->
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse" href="#lv1G1"> 
								<i class="fa fa-play PR5" aria-hidden="true" style="color: #14bef0"></i>Xe cơ giới
							</a>
						</div>
						<div id="lv1G1" class="accordion-body collapse in">
							<div class="accordion-inner PB0 PT0">
								<#-- lv2G1 -->
								<div class="PT5">
									<div class="ML40">
										<a data-toggle="collapse" href="#lv21G1"> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Thẩm định thiết kế
										</a>
									</div>
									<div id="lv21G1" class="collapse in MT10">
										<#-- lv3G1 -->
										<div class="PB10">
											<div class="ML80">
												<a data-toggle="collapse" href="#lv31G1"> 
													<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Cấp mới
												</a>
											</div>
											<div id="lv31G1" class="collapse in MT5 ML100">
												<div class="form-inline form-group col-sm-7 MB5 PL0">
													<label for="ip1Lv31G1" class="MR10">Xe SXLR, nhập khẩu từ xe cơ sở</label>
													<input type="text" class="form-control" id="ip1Lv31G1" placeholder="Nhập số GCN xe SXLR hoặc nhập khẩu">
													<button data-TTHC="TT302011BGTVT-TĐTK-XCG" data-CQTH="BGTVT-CĐKVN" data-MMHS="TT302011BGTVT-TĐTK-XCG-NK-XCS" class="btn btn-reset chooseService pull-right P0 PT5" id="btn1Lv31G1">Chọn</button>
												</div>
												<div class="clear"></div>
												<div class="col-sm-7 PL0 MB5">
													<span class="PR10">Xe SXLR theo thiết kế và mang nhãn hiệu hàng hóa nước ngoài</span>
													<button class="btn btn-reset pull-right P0" id="btn2Lv31G1">Chọn</button>
												</div>
												<div class="clear"></div>
												<div class="col-sm-7 form-inline form-group MB5 PL0">
													<label for="ip2Lv31G1" class="MR10">Xe SXLR, nhập khẩu từ linh kiện</label>
													<input type="text" class="form-control" id="ip2Lv31G1" placeholder="Nhập số GCN xe SXLR hoặc nhập khẩu">
													<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn3Lv31G1">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
										</div>
										<#-- lv31G1 -->
										<div class="PB10">
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip4Lv31G1" class="MR10">Mở rộng</label>
													<input type="text" class="form-control" id="ip4Lv31G1" placeholder="Nhập số GCN hồ sơ gốc">
													<span class="red ML10">(*)</span>
													<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn4Lv31G1">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
										</div>
										<#-- lv32G1 -->
										<div class="PB10">
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip5Lv32G1" class="MR20">Cấp lại</label>
													<input type="text" class="form-control" id="ip5Lv32G1" placeholder="Nhập số GCN hồ sơ gốc/ mở rộng">
													<span class="red ML10">(*)</span>
													<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn5Lv31G1">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
										</div>
									</div>
								</div>
								<#-- lv22G1 -->
								<div class="PT5">
									<div class="align-middle PL40">
										<a data-toggle="collapse" href="#lv22G1"> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Kiểm tra sản phẩm mẫu
										</a>
									</div>
									<div id="lv22G1" class="collapse MT10">
											<#-- lv32G1 -->
										<div class="PB10">
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip1Lv32G1" class="MR10">Đăng ký kiểm tra lần đầu</label>
													<input type="text" class="form-control" id="ip1Lv32G1" placeholder="Nhập số GCN (hồ sơ) thẩm định thiết kế">
													<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1Lv32G1">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip2Lv32G1" class="MR40">Đăng ký kiểm tra lại</label>
													<input type="text" class="form-control" id="ip2Lv32G1" placeholder="Nhập số GCN (hồ sơ) thẩm định thiết kế">
													<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn2Lv32G1">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
										</div>
									</div>
								</div>
								<#-- lv23G1 -->
								<div class="PT5">
									<div class="align-middle PL40">
										<a data-toggle="collapse" href="#lv23G1"> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Chứng nhận Chất lượng kiểu loại
										</a>
									</div>
									<div id="lv23G1" class="collapse MT10">
										<div class="PB10">
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip1Lv23G1" class="MR10">Cấp mới</label>
													<input type="text" class="form-control" id="ip1Lv23G1" placeholder="Nhập số GCN (hồ sơ) thẩm định thiết kế">
													<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1Lv23G1">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip2Lv23G1" class="MR10">Mở rộng</label>
													<input type="text" class="form-control" id="ip2Lv23G1" placeholder="Nhập số GCN hồ sơ gốc">
													<span class="red ML10">(*)</span>
													<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn2Lv23G1">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip3Lv23G1" class="MR20">Cấp lại</label>
													<input type="text" class="form-control" id="ip3Lv23G1" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
													<span class="red ML10">(*)</span>
													<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn3Lv23G1">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
										</div>
									</div>
								</div>
								<#-- lv24G1 -->
								<div class="PT5 PB5">
									<div class="align-middle PL40">
										<a data-toggle="collapse" href="#lv24G1"> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Cấp phát phôi phiếu
										</a>
									</div>
									<div id="lv24G1" class="collapse MT10">
										<div class="PB10">
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip1Lv24G1" class="MR45">Phương thức 1: cấp theo lô</label>
													<input type="text" class="form-control" id="ip1Lv24G1" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
													<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1Lv24G1">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip2Lv24G1" class="MR60">Phương thức 2: giám sát</label>
													<input type="text" class="form-control" id="ip2Lv24G1" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
													<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn2Lv24G1">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip3Lv24G1" class="MR10">Phương thức 3: kiểm tra đột xuất</label>
													<input type="text" class="form-control" id="ip3Lv24G1" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
													<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn3Lv24G1">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<#-- Xe mô tô, xe gắn máy -->
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse" href="#lv12G1"> 
								<i class="fa fa-play PR5" aria-hidden="true" style="color: #14bef0"></i>Xe mô tô, xe gắn máy
							</a>
						</div>
						<div id="lv12G1" class="accordion-body collapse">
							<div class="accordion-inner PB0 PT0">
								<#-- lv23G1 -->
								<div class="PT5">
									<div class="align-middle PL40">
										<a data-toggle="collapse" href="#item21"> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Chứng nhận Chất lượng kiểu loại
										</a>
									</div>
									<div id="item21" class="collapse MT10">
										<div class="PB10">
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip1item21" class="MR10">Cấp mới</label>
													<input type="text" class="form-control" id="ip1item21" placeholder="Nhập số GCN (hồ sơ) CL kiểu loại">
													<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1item21">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip2item21" class="MR10">Mở rộng</label>
													<input type="text" class="form-control" id="ip2item21" placeholder="Nhập số GCN hồ sơ gốc">
													<span class="red ML10">(*)</span>
													<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn2item21">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip3item21" class="MR20">Cấp lại</label>
													<input type="text" class="form-control" id="ip3item21" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
													<span class="red ML10">(*)</span>
													<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn3item21">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
										</div>
									</div>
								</div>
								<#-- lv24G1 -->
								<div class="PT5 PB5">
									<div class="align-middle PL40">
										<a data-toggle="collapse" href="#item22"> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Cấp phát phôi phiếu
										</a>
									</div>
									<div id="item22" class="collapse MT10">
										<div class="PB10">
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip1item22" class="MR45">Phương thức 1: cấp theo lô</label>
													<input type="text" class="form-control" id="ip1item22" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
													<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1item22">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip2item22" class="MR60">Phương thức 2: giám sát</label>
													<input type="text" class="form-control" id="ip2item22" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
													<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn2item22">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip3item22" class="MR10">Phương thức 3: kiểm tra đột xuất</label>
													<input type="text" class="form-control" id="ip3item22" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
													<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn3item22">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<#-- Xe chở người bốn bánh có gắn động cơ -->
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse" href="#lv13G1"> 
								<i class="fa fa-play PR5" aria-hidden="true" style="color: #14bef0"></i>Xe chở người bốn bánh có gắn động cơ
							</a>
						</div>
						<div id="lv13G1" class="accordion-body collapse">
							<div class="accordion-inner PB0 PT0">
								<#-- lv22G1 -->
								<div class="PT5">
									<div class="align-middle PL40">
										<a data-toggle="collapse" href="#item31"> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Kiểm tra sản phẩm mẫu
										</a>
									</div>
									<div id="item31" class="collapse MT10">
											<#-- lv32G1 -->
										<div class="PB10">
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip1item31" class="MR10">Đăng ký kiểm tra lần đầu</label>
													<input type="text" class="form-control" id="ip1item31" placeholder="Nhập số GCN (hồ sơ) thẩm định thiết kế">
													<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1Lv32G1">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip2item31" class="MR40">Đăng ký kiểm tra lại</label>
													<input type="text" class="form-control" id="ip2item31" placeholder="Nhập số GCN (hồ sơ) thẩm định thiết kế">
													<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn2item31">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
										</div>
									</div>
								</div>
								<#-- lv23G1 -->
								<div class="PT5">
									<div class="align-middle PL40">
										<a data-toggle="collapse" href="#item32"> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Chứng nhận Chất lượng kiểu loại
										</a>
									</div>
									<div id="item32" class="collapse MT10">
										<div class="PB10">
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip1item32" class="MR10">Cấp mới</label>
													<input type="text" class="form-control" id="ip1item32" placeholder="Nhập số GCN (hồ sơ) thẩm định thiết kế">
													<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1item32">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip2item32" class="MR10">Mở rộng</label>
													<input type="text" class="form-control" id="ip2item32" placeholder="Nhập số GCN hồ sơ gốc">
													<span class="red ML10">(*)</span>
													<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn2item32">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip3item32" class="MR20">Cấp lại</label>
													<input type="text" class="form-control" id="ip3item32" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
													<span class="red ML10">(*)</span>
													<button class="btn btn-reset chooseServiceValidate pull-right P0 PT5" id="btn3Lv23G1">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
										</div>
									</div>
								</div>
								<#-- lv24G1 -->
								<div class="PT5 PB5">
									<div class="align-middle PL40">
										<a data-toggle="collapse" href="#item33"> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Cấp phát phôi phiếu
										</a>
									</div>
									<div id="item33" class="collapse MT10">
										<div class="PB10">
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip1item33" class="MR45">Phương thức 1: cấp theo lô</label>
													<input type="text" class="form-control" id="ip1item33" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
													<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn1item33">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip2item33" class="MR60">Phương thức 2: giám sát</label>
													<input type="text" class="form-control" id="ip2item33" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
													<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn2item33">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
											<div class="ML80">
												<div class="col-sm-7 form-inline form-group MB5 PL0 PR5">
													<label for="ip3item33" class="MR10">Phương thức 3: kiểm tra đột xuất</label>
													<input type="text" class="form-control" id="ip3item33" placeholder="Nhập số GCN hồ sơ gốc / mở rộng">
													<button class="btn btn-reset chooseService pull-right P0 PT5" id="btn3item33">Chọn</button>
												</div>
												<div class="clear"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<#--  -->
					<#include "serviceconfigDKLR2.ftl">
				</div>
			</div>
		</div>
	</div>
	<#-- Group2 -->
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#group2">
				QUẢN LÝ DÁN NHÃN NĂNG LƯỢNG
			</a>
		</div>
		<div id="group2" class="accordion-body collapse">
			<div class="accordion-inner">
			</div>
		</div>
	</div>
	<#-- Group3 -->
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#group3">
				QUẢN LÝ TRIỆU HỒI SẢN PHẨM
			</a>
		</div>
		<div id="group3" class="accordion-body collapse">
			<div class="accordion-inner">
			</div>
		</div>
	</div>
	<#-- Group4 -->
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#group4">
				QUẢN LÝ THÔNG TIN DOANH NGHIỆP
			</a>
		</div>
		<div id="group4" class="accordion-body collapse">
			<div class="accordion-inner">
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var inputValue;
	var dossierTemplateNo;
	var serviceCode;
	var govAgencyCode;
	var dataSourceDossier = function(id){
		$.ajax({
			url : "${api.server}/dossiers/"+id,
			dataType : "json",
			type : "GET",
			data : {},
			headers : {"groupId": ${groupId}},
			success : function(result){
				manageDossier.navigate("/taohosomoi/chuanbihoso/"+id);
			},
			error : function(result){
				alert("Số hồ sơ không tồn tại!")
			}
		});
	};
	var dataSourceDossierEx = function(id){
		$.ajax({
			url : "${api.server}/dossiers/"+id,
			dataType : "json",
			type : "GET",
			data : {},
			headers : {"groupId": ${groupId}},
			success : function(result){
				cloningProfile(id)
			},
			error : function(result){
				alert("Số hồ sơ không tồn tại!")
			}
		});
	};
	var cloningProfile = function(id){
		$.ajax({
			url:"${api.server}/dossiers/"+id+"/cloning",
			dataType:"json",
			type:"POST",
			headers: {"groupId": ${groupId}},
			success:function(res){
				var dossierId = res.dossierId;
				manageDossier.navigate("/taohosomoi/nophoso/"+dossierId);
			},
			error:function(res){
				
			}
		})
	};
	var createDossier = function(dossierTemplateNo,serviceCode,govAgencyCode){
		$.ajax({
      url : "${api.server}/dossiers",
      dataType : "json",
      type : "POST",
      data : {
        referenceUid : "",
        serviceCode : serviceCode,
        govAgencyCode : govAgencyCode,
        dossierTemplateNo : dossierTemplateNo,
        applicantName : "${(applicant.applicantName)!}",
        applicantIdType : "${(applicant.applicantIdType)!}",
        applicantIdNo : "${(applicant.applicantIdNo)!}",
        applicantIdDate : "01/01/2017 00:00:00",
        address : "${(applicant.address)!}",
        cityCode : "${(applicant.cityCode)!}",
        districtCode : "${(applicant.districtCode)!}",
        wardCode : "${(applicant.wardCode)!}",
        contactName : "${(applicant.contactName)!}",
        contactTelNo : "${(applicant.contactTelNo)!}",
        contactEmail : "${(applicant.contactEmail)!}"
      },
      headers : {"groupId": ${groupId}},
      success : function(result){
        manageDossier.navigate("/taohosomoi/chuanbihoso/"+result.dossierId);
      },
      error : function(result){
      }
    });
	}
	// Chọn dịch vụ công
	var chooseDVC = function(selector){
		inputValue = $(selector).parent().children("input").val();
		if (inputValue == "") {
			dossierTemplateNo = $(selector).attr("data-MMHS");
			serviceCode = $(selector).attr("data-TTHC");
			govAgencyCode = $(selector).attr("data-CQTH");
			createDossier(dossierTemplateNo, serviceCode, govAgencyCode)
		} else {
			dataSourceDossier(inputValue);
		}
	};
	var chooseDVCvalidate = function(selector){
		inputValue = $(selector).parent().children("input").val();
		if (inputValue == "") {
			$(selector).parent().addClass("has-error");
			return false;
		} else {
			$(selector).parent().removeClass("has-error");
			dataSourceDossierEx(inputValue);
		}
	};
	// 
	$(function () {
		$(".chooseService").click(function(){
			chooseDVC(this);
		});
		$(".chooseServiceValidate").click(function(){
			chooseDVCvalidate(this);
		});
		// 
		$("label").css("font-family","Roboto-Regular");
		$("input").css({"width":"260px", "height": "25px", "border-radius":"0"});
		$(".clear").css("clear","both");
		// 
		$(function(){
		  manageDossier.route("/taohosomoi/chuanbihoso/(:dossierId)", function(dossierId){
		    $("#mainType1").hide();
		    $("#mainType2").show();
		    $("#mainType2").load("${ajax.customer_dossier_detail}&${portletNamespace}dossierId="+dossierId,function(result){

		    });
		  });
		  manageDossier.route("/taohosomoi/nophoso/(:dossierId)", function(dossierId){
				$("#mainType1").hide();
				$("#mainType2").show();
				$("#mainType2").load("${ajax.customer_dossier_detail_2}&${portletNamespace}dossierId="+dossierId,function(result){

				});
			});
		})
	})
</script>
