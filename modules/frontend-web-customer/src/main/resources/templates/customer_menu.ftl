<div class="row">
	<div class="col-sm-12" style="padding-top: 10px;">
		<button class="btn btn-active form-control" id="btn_create_new_dossier">Tạo hồ sơ mới</button>
	</div>
	<div class="col-sm-12">
		<div style="padding: 10px 0;">
			<strong>Trạng thái hồ sơ</strong>
		</div>
		<ul id="profileStatus" class="ul-default ul-with-left-icon" style="margin-left: 10px;">
			<li style='padding:5px 0;' dataPk='New' class='itemStatus' data-filterdate="submitDate">
				<i class='fa fa-folder-o' aria-hidden='true'></i>  
				<a href='javascript:;' >Hồ sơ mới</a>
			</li>
			<li style='padding:5px 0;' dataPk='Receiving' class='itemStatus' data-filterdate="receiveDate">
				<i class='fa fa-folder-o' aria-hidden='true'></i>  
				<a href='javascript:;' >Hồ sơ đang thực hiện</a>
			</li>
			<li style='padding:5px 0;' dataPk='Waiting' class='itemStatus' data-filterdate="correctingDate">
				<i class='fa fa-folder-o' aria-hidden='true'></i>  
				<a href='javascript:;' >Hồ sơ chờ bổ sung</a>
			</li>
			<li style='padding:5px 0;' dataPk='Paying' class='itemStatus' data-filterdate="correctingDate">
				<i class='fa fa-folder-o' aria-hidden='true'></i>  
				<a href='javascript:;' >Hồ sơ chờ thanh toán</a>
			</li>
			<li style='padding:5px 0;' dataPk='Done' class='itemStatus' data-filterdate="finishDate">
				<i class='fa fa-folder-o' aria-hidden='true' ></i>  
				<a href='javascript:;' >Hồ sơ đã kết thúc</a>
			</li>
		</ul>
		<input type="hidden" name="monthYearFilter" id="monthYearFilter" value="submitDate">
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
					<input class="form-control" id="year" name="year" placeholder="Năm" title="year"/>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<input class="form-control" id="month" name="month" placeholder="Tháng" title="Đến ngày" />
				</div>
			</div>
		</div>
	</div>

</div>

<script type="text/javascript">
	$(function() {
		$("[data-role=combobox]").each(function() {
			var widget = $(this).getKendoComboBox();
			widget.input.on("focus", function() {
				widget.open();
			});
		});
	});

	$(function(){
		/*console.log(${customer.status});
		vả arStatus;
		ì(${customer.status}!=null)Ơ
			arStatus=${customer.status};
		}else{
			arStatus=ơ];
		Ư
		$("#profileStatus").empty();
		for(vả i=0;i<arStatus.length;i++)Ơ
			$("#profileStatus").append("<li style='padding:5px 0;' dataPk='"+arStatus[i].statusCode+"' class='itemStatus'><i class='fa fa-folder-o' ẩi-hidden='true'></i>  <a href='javascript:;' >"+arStatus[i].statusName+"</a></li>");
		Ư*/
		// ADD class Active, change icon folder open/close TRANG THAI  DAU TIEN
		// $("#profileStatus li").first().addClass("active");
		// $("#profileStatus li > i").first().removeClass("fa fa-folder-o").addClass("fa fa-folder-open-o");

		$("#dossier_detail").hide();
		$("#dossier_list").show();
		$("#customer_dossierlist").load("${ajax.customer_dossierlist}",function(event){
			dataSourceProfile.read({
				"serviceInfo":$("#serviceInfo").val(),
				"govAgencyCode":$("#govAgencyCode").val(),
				"year":$("#year").val(),
				"month":$("#month").val()
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
	// Lọc theo các module hồ sơ
	$(document).on("click",".itemStatus",function(event){
		event.preventDefault();
		$("#year").val("");
		$("#month").val("");
		$("#serviceInfo").data("kendoComboBox").text("");
		$("#govAgencyCode").data("kendoComboBox").text("");
		$("#dossier_detail").hide();
		$("#left_container").hide();
		$("#dossier_list").show();
		$("#profileStatus li").removeClass('active');
		$("#profileStatus li>i").removeClass('fa-folder-open-o').addClass("fa fa-folder-o");
		$(this).addClass('active');
		$(this).children("i").removeClass("fa fa-folder-o").addClass("fa fa-folder-open-o");
		var id=$(this).attr("dataPk");
		$("#customer_dossierlist").load("${ajax.customer_dossierlist}",function(event){
			dataSourceProfile.read({
				"serviceInfo":$("#serviceInfo").val(),
				"govAgencyCode":$("#govAgencyCode").val(),
				"year":$("#year").val(),
				"month":$("#month").val(),
				"status":id
			});
		})
	});
	// Lọc theo tháng, năm, cơ quan, thủ tục hành chính
	var eventLookup = function(){
		var statusDossier = $("li.itemStatus.active").attr("dataPk");
	    if (statusDossier != undefined) {
	    	dataSourceProfile.read({
				"serviceInfo":$("#serviceInfo").val(),
				"govAgencyCode":$("#govAgencyCode").val(),
				"year":$("#year").val(),
				"month":$("#month").val(),
				"status":statusDossier
			});
	    } else {
	    	dataSourceProfile.read({
				"serviceInfo":$("#serviceInfo").val(),
				"govAgencyCode":$("#govAgencyCode").val(),
				"year":$("#year").val(),
				"month":$("#month").val(),
				"status":""
			});
	    };
	    console.log(statusDossier);
	}
	
	$("#serviceInfo").kendoComboBox({
		placeholder:"Chọn thủ tục hành chính",
		dataTextField:"serviceName",
		dataValueField:"serviceCode",
		dataSource:{
			transport:{
				read:{
					url:"${api.server}/serviceconfigs",
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
		},
		select: function(e) {
		    eventLookup()
		}
	});

	$("#govAgencyCode").kendoComboBox({
		placeholder:"Chọn cơ quan",
		dataTextField:"govAgencyName",
		dataValueField:"govAgencyCode",
		dataSource:{
			transport:{
				read:{
					url:"${api.server}/serviceconfigs",
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
		},
		select: function(e) {
		    eventLookup()
		}
	});

	$("#year").kendoDatePicker({
		format:"dd/MM/yyyy"
	});

	$("#month").kendoDatePicker({
		format:"dd/MM/yyyy"
	});

	//phan xu ly notification
	$(document).ready(function(){
		var dataSourceNotificationNew = new kendo.data.DataSource({
			transport:{
				read:{
					url: "${api.server}/applicants",
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

	$("#profileStatus > li").click(function(){
		var dateStatus = $(this).attr("data-filterdate");
		$("#monthYearFilter").val(dateStatus);
	});
</script>
