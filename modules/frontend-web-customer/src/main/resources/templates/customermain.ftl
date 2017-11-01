<#if (Request)??>
	<#include "init.ftl">
</#if>
	<div class="row">
		<div class="col-sm-3">
			<#include "customer_menu.ftl">
		</div>
		<div class="col-sm-9" id="dossier_list">
			<div class="row">
				<div class="col-sm-9">
					<div class="row panel" style="border: none;box-shadow: none">
						<div class="panel-heading P0">
							<div class="row PL15 PR15">
								<div class="row-header"> 
									<div class="background-triangle-big">
										<i class="fa fa-file-text"></i>
									</div> 
									<span class="text-bold">KÊT QUẢ TÌM KIẾM HỒ SƠ</span> 
									<div class="form-group search-icon pull-right MT5 MB5 MR10">
										<input type="text" class="form-control" id="keyInput" placeholder="Nhập từ khóa">
									</div>
								</div>	
							</div>
						</div>
						<div class="col-sm-12 P0" id="customer_dossierlist">

						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="row">
						<div class="col-sm-12" id="customer_additional_requirements">

						</div>
						<div class="col-sm-12" id="customer_payment_request">

						</div>
						<div class="col-sm-12" id="customer_result_request">

						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-9 P0" style="display: none;" id="dossier_detail">

		</div>
		<div class="col-sm-9 P0" style="display: none;" id="left_container">

		</div>
	</div>

	<!-- popup notification -->
	<!-- container -->
	<span id="notification" style="display:none;"></span>

	<!-- templates -->
	<script id="successTemplate" type="text/x-kendo-template">
	  <div class="popup-error-notification">
	    <p>#= message #</p>
	  </div>
	</script>

	<script id="errorTemplate" type="text/x-kendo-template">
	  <div class="popup-success-notification">
	    <p>#= message #</p>
	  </div>
	</script>

	<!-- script -->
	<script type="text/javascript">
		var notification;
		$(document).ready(function() {
			notification = $("#notification").kendoNotification({
			    position: {
			        pinned: true,
			        top: 30,
			        right: 30
			    },
			    autoHideAfter: 2000,
			    stacking: "down",
			    templates: [
			      {
			        type: "success",
			        template: $("#successTemplate").html()
			      },
			      {
			        type: "error",
			        template: $("#errorTemplate").html()
			      }
			    ]
			}).data("kendoNotification");
		});
	</script>
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
			$("#dossier_detail").hide();
			$("#dossier_list").show();
			$("#customer_dossierlist").load("${ajax.customer_dossierlist}",function(event){
				dataSourceProfile.read({
					"serviceInfo": $("#serviceInfo").val(),
					"govAgencyCode": $("#govAgencyCode").val(),
					"year": $("#year").val(),
					"month": $("#month").val()
				});
			});

			$("#customer_additional_requirements").load("${ajax.customer_additional_requirements}",function(event){

			});

			$("#customer_payment_request").load("${ajax.customer_payment_request}",function(event){
			});

			$("#customer_result_request").load("${ajax.customer_result_request}",function(event){
			});

		});
		// Lọc theo các module hồ sơ
		$(document).on("click",".itemStatus",function(event){
			event.preventDefault();
			$("#month").data("kendoComboBox").text("");
			$("#year").data("kendoComboBox").text("");
			$("#keyInput").val("");
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
		    if (statusDossier !== undefined) {
		    	$("#customer_dossierlist").load("${ajax.customer_dossierlist}",function(event){
					dataSourceProfile.read({
						"serviceInfo": $("#serviceInfo").val(),
						"govAgencyCode": $("#govAgencyCode").val(),
						"year": $("#year").val(),
						"month": $("#month").val(),
						"keyword": $("#keyInput").val(),
						"status": statusDossier
					})
				})
		    } else {
		    	$("#customer_dossierlist").load("${ajax.customer_dossierlist}",function(event){
					dataSourceProfile.read({
						"serviceInfo": $("#serviceInfo").val(),
						"govAgencyCode": $("#govAgencyCode").val(),
						"year": $("#year").val(),
						"month": $("#month").val(),
						"keyword": $("#keyInput").val(),
						"status":""
					})
				})
		    }
		};
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
		$("#month").kendoComboBox({
			placeholder:"Tháng",
			dataSource: ["1","2","3","4","5","6","7","8","9","10","11","12"],
			clearButton: true,
			change: function(e) {
			    eventLookup()
			}
		});
		// load select option YEAR
			var today = new Date();
		    yyyy = today.getFullYear();
		    var arrYear = [];
			for (var i = 0; i < 10; i++, yyyy--) {
			    arrYear.push(yyyy)
			};
		$("#year").kendoComboBox({
			placeholder:"Năm",
			dataSource: arrYear,
			clearButton: true,
			change: function(e) {
			    eventLookup()
			}
		});

		$("#keyInput").keyup(function(){
			eventLookup()
		});
		
		// $("#year").kendoDatePicker({
		// 	format:"dd/MM/yyyy"
		// });

		// $("#month").kendoDatePicker({
		// 	format:"dd/MM/yyyy"
		// });

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
