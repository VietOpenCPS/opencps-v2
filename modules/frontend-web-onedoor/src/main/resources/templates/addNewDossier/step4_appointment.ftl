<#if (Request)??>
<#include "../init.ftl">
</#if>
<div class="P0">
	<div class="row">
		<div class="row-header align-middle-lg">
			<div class="background-triangle-big">
				Giấy hẹn
			</div>
		</div>
	</div>
	<div class="row MT30 MB30">
		<div class="col-xs-10 col-xs-offset-1 border-shadow">
			<div class="row" id="applicant-paper">
				<div class="col-xs-3 text-center">
					<b>CỤC VĂN HÓA CƠ SỞ</b>
					<hr/>
					<span>20180091CVBCS</span>
				</div>
				<div class="col-xs-5 pull-right text-center">
					<b>CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM</b>
					<br>
					<span>Độc lập - Tự do - Hạnh phúc</span>
					<hr/>
				</div>
				<div class="col-xs-12 text-center">
					<h4><b>PHIẾU HẸN</b></h4>
					<h5>TRẢ KẾT QUẢ GIẢI QUYẾT HỒ SƠ</h5>
					<div id="service-name">Tên thủ tục</div>
				</div>
				<div class="col-xs-12 MT20">
					<span class="MR20">Người nộp:</span>
					<span id="applicant-name">Tên người nộp</span>
					<br/>
					<span class="MR20">Số CMT/ Hộ chiếu/ Mã số thuế:</span>
					<span id="applicant-id-no">Số cmt</span>
					<br/>
					<span class="MR20">Địa chỉ</span>
					<span id="address">Địa chỉ</span>
					<br/>
					<span>Thành phần hồ sơ:</span>
					<ul id="dossier-files-list" class="list-part ML30"></ul>
					<span>Cục Văn hóa Cơ sở sẽ hẹn trả kết quả hồ sơ vào lúc </span><span id="appointment-time">08:00 ngày 14/4/2018</span>
					<br/>
					<span class="MR20">Hình thức trả kết quả:</span><b>Dịch vụ chuyển phát nhanh</b>
					<div class="MT20">
						<span class="dossierId">
							<i>Mã số hồ sơ:</i> <i id="dossierId">54321</i>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row MB30">
		<div class="col-xs-12 text-center">
			<button class="btn btn-classic" onclick="printAppointment()" type="button">In phiếu hẹn</button>
			<button class="btn btn-classic btn-classic-active" type="button" onclick="createActionDossier(dossierObject.dossierId)">Kết thúc</button>
			<button class="btn btn-classic" onclick="window.history.back()">Quay lại</button>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function(){

		$("#applicant-name").text(dossierObject.applicantName);
		$("#address").text(dossierObject.address);
		$("#applicant-id-no").text(dossierObject.applicantIdNo);
		$("#dossierId").text(dossierObject.dossierId);
		$("#service-name").text(dossierObject.serviceName);

		var partDatasource = new kendo.data.DataSource({
			transport:{
				read: function (options) {
					$.ajax({
						type : 'GET', 
						dataType : "json",
						url  : '${api.server}/dossiertemplates/'+dossierObject.dossierTemplateNo,
						headers: {"groupId": ${groupId}},
						success :  function(result){ 
							options.success(result.dossierParts);
						}
					})
				}
			}
		})
		$("#dossier-files-list").kendoListView({
			dataSource : partDatasource,
			template: "<li>#=partName#</li>"
		});
	})

	function printAppointment()
	{
		window.print();
	}

	function createActionDossier(dossierId){
		if(dossierId){
			$.ajax({
				url  : '${api.server}/dossiers/'+dossierObject.dossierId, 
				dataType : "json",
				type : 'PUT', 
				headers: {"groupId": ${groupId}},
				data : dossierObject,
				success :  function(result){                       
					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");
				},
				error:function(result){
					notification.show({
						message: "Xảy ra lỗi, xin vui lòng thử lại"
					}, "error");
				}	
			});
		}
	}

	function sendViaPost() {
		var postalObject = {
			customerCode : "ccth",
			orderNumber : dossierObject.dossierId,
			senderProvince : 10,
			senderAddress : "Số 20, ngõ 2, Hoa Lư, Q.Hai Bà Trưng",
			senderName : "CỤC VĂN HÓA CƠ SỞ",
			senderTel: "0243.9745845",
			receiverName: dossierObject.contactName,
			receiverAddress: dossierObject.postalAddress,
			receiverTel: dossierObject.postalTelNo,
			receiverProvince: dossierObject.postalCityCode
		}

		$.ajax({
			url : "${api.server}/postal/vnpost",
			dataType : "json",
			type : "POST",
			headers: {"groupId": ${groupId}},
			data : postalObject,
			success : function(result){
				notification.show({
					message: "Yêu cầu được thực hiện thành công"
				}, "success");
			},
			error : function(result){
				notification.show({
					message: "Xảy ra lỗi, xin vui lòng thử lại"
				}, "error");
			}
		});
	}
</script>