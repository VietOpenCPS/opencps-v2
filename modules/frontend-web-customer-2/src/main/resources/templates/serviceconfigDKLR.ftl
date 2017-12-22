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
			<a class="accordion-toggle" data-toggle="collapse" href="#group1">
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
								<div class="PT5">
									<div class="ML40 col-sm-6 P5 PL0">
										<span> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Thẩm định thiết kế TEST
										</span>
										<button data-TTHC="TEST" data-CQTH="BGTVTCDKVN" data-MMHS="TEST" class="btn btn-reset chooseService pull-right P0">Chọn</button>
									</div>
									<div class="clear"></div>
									
								</div>
								<#-- lv2G1 -->
								<div class="PT5">
									<div class="ML40 col-sm-6 P5 PL0">
										<span> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Thẩm định thiết kế
										</span>
										<button data-TTHC="TT302011BGTVTTDTKXCG" data-CQTH="BGTVTCDKVN" data-MMHS="TT302011BGTVTTDTKXCG" class="btn btn-reset chooseService pull-right P0" id="btn1Lv31G1">Chọn</button>
									</div>
									<div class="clear"></div>
									
								</div>
								<#-- lv22G1 -->
								<div class="PT5">
									<div class="ML40 col-sm-6 P5 PL0">
										<span> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Kiểm tra sản phẩm mẫu
										</span>
										<button data-TTHC="TT302011BGTVTKTSPMXCG" data-CQTH="BGTVTCDKVN" data-MMHS="TT302011BGTVTKTSPMXCG" class="btn btn-reset chooseService pull-right P0" id="btn1Lv32G1">Chọn</button>
									</div>
									<div class="clear"></div>
									
								</div>
								<#-- lv23G1 -->
								<div class="PT5">
									<div class="ML40 col-sm-6 P5 PL0">
										<span> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Chứng nhận Chất lượng kiểu loại
										</span>
										<button data-TTHC="TT302011BGTVTCNCLKLTXXCG" data-CQTH="BGTVTCDKVN" data-MMHS="TT302011BGTVTCNCLKLTXXCG" class="btn btn-reset chooseService pull-right P0" id="btn1Lv23G1">Chọn</button>
									</div>
									<div class="clear"></div>
									
								</div>
								<#-- lv24G1 -->
								<div class="PT5 PB5">
									<div class="ML40 col-sm-6 P5 PL0">
										<span> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Cấp phát phôi phiếu
										</span>
										<button data-TTHC="TT302011BGTVTCPPXCG" data-CQTH="BGTVTCDKVN" data-MMHS="TT302011BGTVTCPPXCG" class="btn btn-reset chooseService pull-right P0" id="btn1Lv24G1">Chọn</button>
									</div>
									<div class="clear"></div>
									
								</div>
								<div class="clear"></div>
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
						<div id="lv12G1" class="accordion-body collapse toggle-hide">
							<div class="accordion-inner PB0 PT0">
								<#-- lv23G1 -->
								<div class="PT5">
									<div class="ML40 col-sm-6 P5 PL0">
										<span> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Chứng nhận Chất lượng kiểu loại
										</span>
										<button data-TTHC="TT302011BGTVTCNCLKLTXXMTGM" data-CQTH="BGTVTCDKVN" data-MMHS="TT302011BGTVTCNCLKLTXXMTGM" class="btn btn-reset chooseService pull-right P0" id="btn1item21">Chọn</button>
									</div>
									<div class="clear"></div>
									
								</div>
								<#-- lv24G1 -->
								<div class="PT5 PB5">
									<div class="ML40 col-sm-6 P5 PL0">
										<span> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Cấp phát phôi phiếu
										</span>
										<button data-TTHC="TT302011BGTVTCPPXMTGM" data-CQTH="BGTVTCDKVN" data-MMHS="TT302011BGTVTCPPXMTGM" class="btn btn-reset chooseService pull-right P0" id="btn1item22">Chọn</button>
									</div>
									<div class="clear"></div>
									
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
						<div id="lv13G1" class="accordion-body collapse toggle-hide">
							<div class="accordion-inner PB0 PT0">
								<#-- lv22G1 -->
								<div class="PT5">
									<div class="ML40 col-sm-6 P5 PL0">
										<span> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Kiểm tra sản phẩm mẫu
										</span>
										<button data-TTHC="TT302011BGTVTKTSPMXBBCN" data-CQTH="BGTVTCDKVN" data-MMHS="TT302011BGTVTKTSPMXBBCN" class="btn btn-reset chooseService pull-right P0" id="btn1Lv32G1">Chọn</button>
									</div>
									<div class="clear"></div>
									
								</div>
								<#-- lv23G1 -->
								<div class="PT5">
									<div class="ML40 col-sm-6 P5 PL0">
										<span> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Chứng nhận Chất lượng kiểu loại
										</span>
										<button data-TTHC="TT302011BGTVTCNCLKLTXXBBCN" data-CQTH="BGTVTCDKVN" data-MMHS="TT302011BGTVTCNCLKLTXXBBCN" class="btn btn-reset chooseService pull-right P0" id="btn1item32">Chọn</button>
									</div>
									<div class="clear"></div>
									
								</div>
								<#-- lv24G1 -->
								<div class="PT5 PB5">
									<div class="ML40 col-sm-6 P5 PL0">
										<span> 
											<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Cấp phát phôi phiếu
										</span>
										<button data-TTHC="TT302011BGTVTCPPXBBCN" data-CQTH="BGTVTCDKVN" data-MMHS="TT302011BGTVTCPPXBBCN"  class="btn btn-reset chooseService pull-right P0" id="btn1item33">Chọn</button>
									</div>
									<div class="clear"></div>
									
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
		<div id="group2" class="accordion-body collapse toggle-hide" style="background-color: #ffffff">
			<div class="accordion-inner PB0 PT0">
				<div class="PT5">
					<div class="ML40 col-sm-6 P5 PL0">
						<span> 
							<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Đăng ký chứng nhận mức tiêu thụ nhiên liệu đối với xe ô tô sản xuất, lắp ráp
						</span>
						<button data-TTHC="432014TTLTBGTVTBCTTTNLSXLR" data-CQTH="BGTVTCDKVN" data-MMHS="432014TTLTBGTVTBCTTTNLSXLR" class="btn btn-reset chooseService pull-right P0">Chọn</button>
					</div>
					<div class="clear"></div>
				</div>
				<div class="PT5">
					<div class="ML40 col-sm-6 P5 PL0">
						<span> 
							<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Đăng ký chứng nhận mức tiêu thụ nhiên liệu đối với xe ô tô nhập khẩu
						</span>
						<button data-TTHC="432014TTLTBGTVTBCTTTNLNK" data-CQTH="BGTVTCDKVN" data-MMHS="432014TTLTBGTVTBCTTTNLNK" class="btn btn-reset chooseService pull-right P0">Chọn</button>
					</div>
					<div class="clear"></div>
				</div>
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
		<div id="group3" class="accordion-body collapse toggle-hide" style="background-color: #ffffff">
			<div class="accordion-inner">
				<div class="PT5">
					<div class="ML40 col-sm-6 P5 PL0">
						<span> 
							<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Triệu hồi sản phẩm xe cơ giới sản xuất, lắp ráp linh kiện sử dụng để lắp ráp xe
						</span>
						<button data-TTHC="TT302011BGTVTTHSPSXLR" data-CQTH="BGTVTCDKVN" data-MMHS="TT302011BGTVTTHSPSXLR" class="btn btn-reset chooseService pull-right P0">Chọn</button>
					</div>
					<div class="clear"></div>
				</div>
				<div class="PT5">
					<div class="ML40 col-sm-6 P5 PL0">
						<span> 
							<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Triệu hồi sản phẩm xe cơ giới nhập khẩu linh kiện sử dụng để lắp ráp xe
						</span>
						<button data-TTHC="TT302011BGTVTTHSPNK" data-CQTH="BGTVTCDKVN" data-MMHS="TT302011BGTVTTHSPNK" class="btn btn-reset chooseService pull-right P0">Chọn</button>
					</div>
					<div class="clear"></div>
				</div>
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
		<div id="group4" class="accordion-body collapse toggle-hide" style="background-color: #ffffff">
			<div class="accordion-inner">
				<div class="PT5">
					<div class="ML40 col-sm-6 P5 PL0">
						<span> 
							<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Kiểm tra thiết bị kiểm tra xuất xưởng đối với cơ sở đăng ký lần đầu
						</span>
						<button data-TTHC="TT302011BGTVTKTTBLANDAU" data-CQTH="BGTVTCDKVN" data-MMHS="TT302011BGTVTKTTBLANDAU" class="btn btn-reset chooseService pull-right P0">Chọn</button>
					</div>
					<div class="clear"></div>
				</div>

				<div class="PT5">
					<div class="ML40 col-sm-6 P5 PL0">
						<span> 
							<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Kiểm tra thiết bị kiểm tra xuất xưởng đối với cơ sở đăng ký kiểm tra hàng năm
						</span>
						<button data-TTHC="TT302011BGTVTKTTB" data-CQTH="BGTVTCDKVN" data-MMHS="TT302011BGTVTKTTB" class="btn btn-reset chooseService pull-right P0">Chọn</button>
					</div>
					<div class="clear"></div>
				</div>

				<div class="PT5">
					<div class="ML40 col-sm-6 P5 PL0">
						<span class="col-sm-10 PL0"> 
							<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Đánh giá điều kiện đảm bảo chất lượng xưởng lắp ráp của cơ sở sản xuất/ nhà sản xuất nước ngoài (Đánh giá COP) trong thủ tục sản xuất lắp ráp
						</span>
						<button data-TTHC="TT302011BGTVTCOP" data-CQTH="BGTVTCDKVN" data-MMHS="TT302011BGTVTCOP" class="btn btn-reset chooseService pull-right P0">Chọn</button>
					</div>
					<div class="clear"></div>
				</div>

				<div class="PT5">
					<div class="ML40 col-sm-6 P5 PL0">
						<span> 
							<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Kiểm tra, đánh giá cơ sở bảo hành, bảo dưỡng đối với cơ sở đánh giá lần đầu
						</span>
						<button data-TTHC="TT192012TTBGTVTDGLD" data-CQTH="BGTVTCDKVN" data-MMHS="TT192012TTBGTVTDGLD" class="btn btn-reset chooseService pull-right P0">Chọn</button>
					</div>
					<div class="clear"></div>
				</div>

				<div class="PT5">
					<div class="ML40 col-sm-6 P5 PL0">
						<span class="col-sm-10 PL0">
							<i class="fa fa-play PR5 text-light-gray" aria-hidden="true"></i>Kiểm tra, đánh giá cơ sở bảo hành, bảo dưỡng đối với cơ sở đánh giá bổ sung, đánh giá cấp lại GCN hết hiệu lực
						</span>
						<button data-TTHC="TT192012TTBGTVTDGBS" data-CQTH="BGTVTCDKVN" data-MMHS="TT192012TTBGTVTDGBS" class="btn btn-reset chooseService pull-right P0">Chọn</button>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var dossierTemplateNo;
	var serviceCode;
	var govAgencyCode;
	var selector;
	
	var createDossier = function(dossierTemplateNo,serviceCode,govAgencyCode,selector){
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
      	selector.button('reset');
        manageDossier.navigate("/taohosomoi/chuanbihoso/"+result.dossierId);
      },
      error : function(result){
      	selector.button('reset');
      }
    });
	} 
	$(function () {
		$(".chooseService").click(function(){
			$(this).button('loading');
			selector = $(this);
			dossierTemplateNo = $(this).attr("data-MMHS");
			serviceCode = $(this).attr("data-TTHC");
			govAgencyCode = $(this).attr("data-CQTH");
			createDossier(dossierTemplateNo, serviceCode, govAgencyCode,selector)
		});
		// 
		$("label").css("font-family","Roboto-Regular");
		$(".clear").css("clear","both");
		//
	})
</script>
