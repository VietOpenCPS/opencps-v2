<div class="row">
	<div class="col-sm-12" style="padding-top: 10px;">
		<button class="btn btn-active form-control" id="btn_create_new_dossier">Tạo hồ sơ mới</button>
	</div>
	<div class="col-sm-12">
		<div style="padding: 10px 0;">
			<strong>Trạng thái hồ sơ</strong>
		</div>
		<ul id="profileStatus" class="ul-default ul-with-left-icon" style="margin-left: 10px;">
		</ul>
	</div>

	<div class="col-sm-12">
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
					<input class="form-control" id="startDate" name="startDate" placeholder="Năm" title="Từ ngày"/>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<input class="form-control" id="endDate" name="endDate" placeholder="Tháng" title="Đến ngày" />
				</div>
			</div>
		</div>
	</div>

</div>

<script type="text/javascript">
	$("#serviceInfo").kendoComboBox({
		placeholder:"Chọn thủ tục hành chính",
		dataTextField:"serviceName",
		dataValueField:"serviceCode",
		dataSource:{
			transport:{
				read:{
					url:"",
					dataType:"json",
					type:"GET"
				}
			},
			schema:{
				data:"data",
				total:"total",
				model:{
					id:"serviceinfoid"
				}
			}
		}

	});

	$("#govAgencyCode").kendoComboBox({
		placeholder:"Chọn cơ quan",
		dataTextField:"serviceName",
		dataValueField:"serviceCode",
		dataSource:{
			transport:{
				read:{
					url:"",
					dataType:"json",
					type:"GET"
				}
			},
			schema:{
				data:"data",
				total:"total",
				model:{
					id:"serviceinfoid"
				}
			}
		}

	});

	$("#startDate").kendoDatePicker({
		format:"dd/MM/yyyy"
	});

	$("#endDate").kendoDatePicker({
		format:"dd/MM/yyyy"
	});

	$(function() {
		$("[data-role=combobox]").each(function() {
			var widget = $(this).getKendoComboBox();
			widget.input.on("focus", function() {
				widget.open();
			});
		});
	});

	$(function(){
		console.log(${customer.status});
		var arrStatus;
		if(${customer.status}!=null){
			arrStatus=${customer.status};
		}else{
			arrStatus=[];
		}
		$("#profileStatus").empty();
		for(var i=0;i<arrStatus.length;i++){
			$("#profileStatus").append("<li style='padding:5px 0;' dataPk='"+arrStatus[i].statusCode+"' class='itemStatus'><i class='fa fa-folder-o' aria-hidden='true'></i>  <a href='javascript:;' >"+arrStatus[i].statusName+"</a></li>");
		}

		$("#profileStatus li").first().addClass("active");
		$("#profileStatus li > i").first().removeClass("fa fa-folder-o").addClass("fa fa-folder-open-o");

		$("#dossier_detail").hide();
		$("#dossier_list").show();
		$("#customer_dossierlist").load("${ajax.customer_dossierlist}",function(event){
			var id=$("#profileStatus li").first().attr("dataPk");
			dataSourceProfile.read({
				"serviceInfo":$("#serviceInfo").val(),
				"receiptCode":$("#receiptCode").val(),
				"startDate":$("#startDate").val(),
				"endDate":$("#endDate").val(),
				"statusCode":id
			});
		});

		$("#customer_additional_requirements").load("${ajax.customer_additional_requirements}",function(event){
			var id=$("#profileStatus li").first().attr("dataPk");
		});

		$("#customer_payment_request").load("${ajax.customer_payment_request}",function(event){
			var id=$("#profileStatus li").first().attr("dataPk");
		});

		$("#customer_result_request").load("${ajax.customer_result_request}",function(event){
			var id=$("#profileStatus li").first().attr("dataPk");
		});

	});

	$(document).on("click",".itemStatus",function(event){
		event.preventDefault();
		$("#dossier_detail").hide();
		$("#left_container").hide();

		$("#dossier_list").show();

		$("#profileStatus li").removeClass('active');
		$("#profileStatus li>i").removeClass('fa-folder-open-o').addClass("fa fa-folder-o");
		$(this).addClass('active');
		$(this).children("i").removeClass("fa fa-folder-o").addClass("fa fa-folder-open-o");

		var id=$(this).attr("dataPk");
		$("#customer_dossierlist").load("${ajax.customer_dossierlist}",function(event){
			var id=$("#profileStatus li").first().attr("dataPk");
			dataSourceProfile.read({
				"serviceInfo":$("#serviceInfo").val(),
				"receiptCode":$("#receiptCode").val(),
				"startDate":$("#startDate").val(),
				"endDate":$("#endDate").val(),
				"statusCode":id
			});
		});
	});

	//phan xu ly notification
	$(document).ready(function(){
		
		var dataSourceNotificationNew = new kendo.data.DataSource({
			transport:{
				read:{
					url: "http://localhost:8081/api/applicants",
					type: "GET",
					dataType: "json",
					success: function(res) {

					},
					error: function(res){

					}
				}
			},
			schema:{
				data:"data",
				total:"total",
				model:{
					id:"id"
				}
			}
		});

		$("#listViewNotificationNew").kendoListView({
			dataSource: dataSourceNotificationNew,
			template: kendo.template($("#dropdownNotificationNewTemp").html())
		});

		$("#btn-showall-notification").click(function(event){
			event.preventDefault();
			console.log("notificationAll");
			$("#dossier_detail").show();
			$("#dossier_list").hide();
			$("#dossier_detail").load("${ajax.notification}",function(result){
				dataSourceNotification.read();
			});
			
		});
	});

	$("#change_password").click(function(){
		$("#dossier_detail").hide();
		$("#dossier_list").hide();

		$("#left_container").show();

		$("#left_container").load(
			"${ajax.change_password}",
			function(){

			}
			);
	});

	$("#account_info").click(function(){
		$("#dossier_detail").hide();
		$("#dossier_list").hide();

		$("#left_container").show();

		$("#left_container").load(
			"${ajax.account_info}",
			function(){

			}
			);
	});

	$("#submited_dossier_info").click(function(){
		$("#dossier_detail").hide();
		$("#dossier_list").hide();

		$("#left_container").show();

		$("#left_container").load(
			"${ajax.submited_dossier_info}",
			function(){

			}
			);
	});

	$("#btn_create_new_dossier").click(function(){
		$("#dossier_detail").hide();
		$("#dossier_list").hide();

		$("#left_container").show();

		$("#left_container").load(
			"${ajax.serviceconfig}",
			function(){

			}
			);
	});

</script>
