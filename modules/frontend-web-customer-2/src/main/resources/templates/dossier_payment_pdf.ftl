<html>
<head>
	<meta charset="utf-8">
	<title>Admin Theme</title>
	<!-- library -->
	<link rel="stylesheet" href="../../../../../../style-component/css/bootstrap.min.css">
	<link rel="stylesheet" href="../../../../../../style-component/css/kendo.common.core.min.css">
	<link rel="stylesheet" href="../../../../../../style-component/css/override-kendoui.css">
	<script src="../../../../../../style-component/js/jquery-1.11.3.js"></script>
	<script src="../../../../../../style-component/js/bootstrap.min.js"></script>
	<script src="../../../../../../style-component/js/kendo.ui.core.min.js"></script>
	<script src="../../../../../../style-component/js/jasny-bootstrap.min.js"></script>
	<script src="../../../../../../style-component/js/custom.js"></script>

	<link rel="stylesheet" href="../../../../../../style-component/css/modern_component.css">
	<link href="../../../../../../style-component/css/modern_custom.css" rel="stylesheet">
	<link rel="stylesheet" href="../../../../../../../style-component/css/custom.css">

	<link href="https://cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.1/bootstrap3-editable/css/bootstrap-editable.css" rel="stylesheet">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.1/bootstrap3-editable/js/bootstrap-editable.js"></script>
	<script src="http://v2.opencps.vn/o/backend-theme/js/handlebars.min.js"></script>
	<script src="http://v2.opencps.vn/o/backend-theme/js/alpaca.min.js"></script>
</head>
<body>
	<style type="text/css">
		body {
			font-size: 16px;
		}
	</style>
	<div class="container PT50 PB50" id="contentPaymentFpdf">
		<div class="row MB10">
			<div class="col-sm-4 red" style="font-size: 13px;">
				<div class="row ML30">
					<img class="text-center" style="width: 75px;" src="http://dangkiemlaprap.mt.gov.vn/o/dklr-theme-theme/images/logo.png"> <br>
				</div>
				<span class="text-bold" style="font-size: 15px;">CỤC ĐĂNG KIỂM VIỆT NAM</span> <br>
				<span>ĐC: 18 Phạm Hùng - Nam Từ Liêm - TP. Hà Nội</span> <br>
				<span>MST: <span class="text-bold">0100109120</span></span> <br>
				<span>ĐT: 0437684715 - Fax: 0437684721</span> 
			</div>
			<div class="col-sm-4 text-center">
				<span class="text-bold red" style="font-size: 17px;">HÓA ĐƠN GTGT</span> <br>
				<span class="text-bold red" style="font-size: 17px;">(DỊCH VỤ ĐĂNG KIỂM)</span>

				<p style="font-size: 15px;" class="PT20"><span class="red">Ngày</span> <i>12</i> <span class="red">tháng</span> <i>01</i> <span class="red">năm</span> <i>2018</i></p>
			</div>
			<div class="col-sm-4 red text-center" style="font-size: 17px;">
				<span>Mẫu số: 01GTKT0/001</span><br>
				<span>Ký hiệu: AA/17E</span>
				<p>Số: <span class="text-bold"></span></p>
			</div>
		</div>

		<div class="row PB10">
			<div class="col-sm-12">
				<span class="red">Đơn vị đăng kiểm: </span> <span class="text-bold PR0" style="display:inline-block;
				border-bottom:1px dotted red;
				">Cục Đăng kiểm Việt Nam</span>
			</div>
		</div>
		<div class="row PB10">
			<div class="col-sm-12">
				<span class="red">Địa chỉ: </span> <span class=" PR0" style="display:inline-block;
				border-bottom:1px dotted red;
				">Số 18 Đường Phạm Hùng, Phường Mỹ Đình 2, Quận Nam Từ Liêm, Hà Nội</span>
			</div>
		</div>
		<div class="row PB10">
			<div class="col-sm-5">
				<span class="red">Số TK: </span> <span class=" PR0" style="display:inline-block;
				border-bottom:1px dotted red;
				">0021000002046</span>
			</div>
			<div class="col-sm-7">
				<span class="red">Tại ngân hàng:</span> <span class=" PR0" style="display:inline-block;
				border-bottom:1px dotted red;
				">Ngoại thương Việt Nam - Chi nhánh Thăng Long</span>
			</div>
		</div>
		<div class="row PB10">
			<div class="col-sm-5">
				<span class="red">Tel/Fax:</span> <span class=" PR0" style="display:inline-block;
				border-bottom:1px dotted red;
				">04.37684721</span>
			</div>
			<div class="col-sm-7">
				<span class="red">Mã số thuế:</span> <span class="text-bold PR0" style="display:inline-block;
				border-bottom:1px dotted red;
				">0100109120</span>
			</div>
		</div>
		<div class="row PB10">
			<div class="col-sm-6">
				<span class="red">Đối tượng đăng kiểm:</span> <span class="text-bold PR0" style="display:inline-block;
				border-bottom:1px dotted red;
				"></span>
			</div>
			<div class="col-sm-6">
				<span class="red">Số đăng ký:</span> <span class=" PR0" style="display:inline-block;
				border-bottom:1px dotted red;
				"></span>
			</div>
		</div>
		<div class="row PB10">
			<div class="col-sm-12">
				<span class="red">Đơn vị trả tiền:</span> <span class="text-bold PR0" style="display:inline-block;
				border-bottom:1px dotted red;
				" data-bind="html:applicantName"></span>
			</div>
		</div>
		<div class="row PB10">
			<div class="col-sm-12">
				<span class="red">Địa chỉ:</span> <span class=" PR0" style="display:inline-block;
				border-bottom:1px dotted red;
				"></span>
			</div>
		</div>
		<div class="row PB10">
			<div class="col-sm-5">
				<span class="red">Số TK:</span> <span class=" PR0" style="display:inline-block;
				border-bottom:1px dotted red;
				"></span>
			</div>
			<div class="col-sm-7">
				<span class="red">Tại ngân hàng:</span> <span class="PR0" style="display:inline-block;
				border-bottom:1px dotted red;
				"></span>
			</div>
		</div>
		<div class="row MB10">
			<div class="col-sm-5">
				<span class="red">Hình thức thanh toán:</span> <span class=" PR0" style="display:inline-block;
				border-bottom:1px dotted red;
				"></span>
			</div>
			<div class="col-sm-7">
				<span class="red">Mã số thuế:</span> <span class="text-bold PR0" style="display:inline-block;
				border-bottom:1px dotted red;
				" data-bind="html:applicantIdNo"></span>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<table class="table table-bordered">
					<thead style="font-size: 15px;">
						<tr>
							<th class="text-center"><span class="red">Số TT</span></th>
							<th class="text-center"><span class="red">Tên hàng hóa dịch vụ</span></th>
							<th class="text-center"><span class="red">Đơn vị tính</span></th>
							<th class="text-center"><span class="red">Số lượng</span></th>
							<th class="text-center"><span class="red">Đơn gía</span></th>
							<th class="text-center"><span class="red">Thành tiền</span></th>
						</tr>
					</thead>
					<tbody id="lvPaymentFile">
						<script type="text/x-kendo-template" id="paymentFileTableTemplate">
							<tr>
								<td style="width: 5%" class="text-center">1</td>
								<td style="width: 45%" >#:paymentFee#</td>
								<td style="width: 12%" class="text-center">bộ</td>
								<td style="width: 12%" class="text-center">1</td>
								<td style="width: 12%" class="text-right">#:paymentAmount#</td>
								<td style="width: 14%" class="text-right">#:paymentAmount#</td>
							</tr>
						</script>
						 <tr>
							<td style="width: 5%" class="text-center">2</td>
							<td style="width: 45%" >000003/18DC (498_xe)</td>
							<td style="width: 12%" class="text-center">bộ</td>
							<td style="width: 12%" class="text-center">1</td>
							<td style="width: 12%" class="text-right">4.526.820</td>
							<td style="width: 14%" class="text-right">4.526.820</td>
						</tr>
						<tr>
							<td style="width: 5%" class="text-center PT20 PB20"></td>
							<td style="width: 45%" ></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 14%" class="text-right"></td>
						</tr>
						<tr>
							<td style="width: 5%" class="text-center PT20 PB20"></td>
							<td style="width: 45%" ></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 14%" class="text-right"></td>
						</tr>
						<tr>
							<td style="width: 5%" class="text-center PT20 PB20"></td>
							<td style="width: 45%" ></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 14%" class="text-right"></td>
						</tr>
						<tr>
							<td style="width: 5%" class="text-center PT20 PB20"></td>
							<td style="width: 45%" ></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 14%" class="text-right"></td>
						</tr>
						<tr>
							<td style="width: 5%" class="text-center PT20 PB20"></td>
							<td style="width: 45%" ></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 14%" class="text-right"></td>
						</tr>
						<tr>
							<td style="width: 5%" class="text-center PT20 PB20"></td>
							<td style="width: 45%" ></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 12%" class="text-right"></td>
							<td style="width: 14%" class="text-right"></td>
						</tr>
						<tr>
							<td colspan="6">
								<div class="col-sm-9 text-center">
									<span class="red">Cộng tiền dịch vụ</span>
								</div>
								<div class="col-sm-3 text-right" style="display:inline-block;
								border-bottom:1px dotted red;
								">
								4.999.999
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="6">
							<div class="col-sm-9 text-center">
								<span class="red">Thuế GTGT: </span> <span class="red PL20 PR20"></span> <span class="PR30 red">%</span> <span class="red">Tiền thuế GTGT:</span>
							</div>
							<div class="col-sm-3 text-right" style="display:inline-block;
							border-bottom:1px dotted red;
							">
							4.999.999
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
						<div class="col-sm-9 text-center">
							<span class="red">Lệ phí giấy chứng nhận đăng kiểm:</span>
						</div>
						<div class="col-sm-3 text-right" style="display:inline-block;
						border-bottom:1px dotted red;
						">
						4.999.999
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<div class="col-sm-9 text-center">
						<span class="red text-bold">Tổng cộng tiền thanh toán:</span>
					</div>
					<div class="col-sm-3 text-right" style="display:inline-block;
					border-bottom:1px dotted red;
					">
					4.999.999
				</div>
			</td>
		</tr>
	</tbody>
</table>
</div>
</div>
<div class="row">
	<div class="col-sm-12">
		<span class="red">Số viết bằng chữ: </span>
	</div>
</div>

<div class="row">
	<div class="col-sm-6 text-center">
		<p class="text-bold red">Khách hàng</p>
	</div>

	<div class="col-sm-6 text-center">
		<p class="text-bold red">Đơn vị đăng kiểm</p>
	</div>
</div>
</div>
</body>
</html>

<script type="text/javascript">
	var dataSourcePayment_fakePdf = new kendo.data.DataSource({
		transport:{
			read:function(options){
				$.ajax({
						// url: "http://localhost:3000/dossiers",
						url:"${api.server}/dossiers/${dossierId}/payments",
						dataType:"json",
						type:"GET",
						headers : {"groupId": ${groupId}},
						data:{
							
						},
						success:function(result){
							if(result.data){
								options.success(result);
								printDetailDossier(result.data[0]);
								var sumFeePayment = sumFeePayment(result.data);
								var lephi = 100000;
								var thue = 10;
								var lephi_thue = sumFeePayment * 10 / 100;

							}else {
								options.success({
									"total" : 0,
									"data" : []
								});
							}
						},
						error:function(result){
							options.error(result);
						}
					});
			}
		},
		schema:{
			data:"data",
			total:"total",
			model:{
				id: "referenceUid"
			}
		}
	});

	$("#lvPaymentFile").kendoListView({
		dataSource : dataSourcePayment_fakePdf,
		template : kendo.template($("#paymentFileTableTemplate").html())
	});

	var printDetailPayment = function(model){
		if(model !== null){
			var viewModel = kendo.observable({
				applicantName : model.applicantName,
				applicantIdNo : model.applicantIdNo,
				modifiedDate : model.modifiedDate

			});
			kendo.bind($("#contentPaymentFpdf"), viewModel);
		}
	}

	var sumFeePayment = function(payments){
		var result = 0;
		if(payments){
			for (var i = 0; i < payments.length; i++) {
				result += payments[i];
			}
		}
		return result;
	}

	var totalFeePayment = function(sumFeePayment){
		var _thue = 10;
		var _lephi = 100000;
		var _total = sumFeePayment * _thue / 100 + sumFeePayment + _lephi;
		return total;

	}
</script>