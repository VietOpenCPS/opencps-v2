<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="demo-section k-content MT20">
	<ul id="customer_menu">
		<li class="k-state-active">
			<strong>Quản lý hồ sơ trực tuyến</strong> <br>
			<div class="row">
				<div class="col-sm-12" style="padding-top: 10px;">
					<p>Tìm kiếm hồ sơ</p>
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<input class="form-control" name="serviceInfo" id="serviceInfo">
					</div>
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<input class="form-control" name="receiptCode" id="receiptCode" placeholder="Mã tiếp nhận">
					</div>
				</div>
				<div class="col-sm-12">
					<div class="row">
						<div class="col-sm-2">
							<strong>Từ</strong>
						</div>
						<div class="col-sm-10">
							<div class="form-group">
								<input class="form-control" id="startDate" name="startDate" placeholder="ngày/tháng/năm" title="Từ ngày"/>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-2">
							<strong>Đến</strong>
						</div>
						<div class="col-sm-10">
							<input class="form-control" id="endDate" name="endDate" placeholder="ngày/tháng/năm" title="Đến ngày" />
						</div>
					</div>
				</div>
				<div class="col-sm-12 text-right MB10">
					<button class="btn btn-blue" type="button" id="btnSearch"><i class="glyphicon glyphicon-search"></i> Tìm kiếm</button>
				</div>
				<div class="col-sm-12">
					<div style="padding: 10px 0;">
						<strong>Trạng thái hồ sơ</strong>
					</div>
					<ul id="profileStatus" style="margin-left: 20px;">
						
					</ul>
				</div>
			</div>
		</li>
		<li>
			<strong>Tra cứu hồ sơ</strong>
			<div class="row">
				<div class="col-sm-12" style="padding: 10px 0;">
					<div class="input-group">
						<input id="keyword" class="form-control" name="keyword" placeholder="Nhập từ khóa tìm kiếm"><span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
					</div>
				</div>
			</div>
		</li>
		<li>
			<strong>Thống kê báo cáo tổng hợp</strong>
			<ul>
				<li>Thống kê báo cáo 1</li>
				<li>Thống kê báo cáo 2</li>
				<li>Thống kê báo cáo 3</li>
			</ul>
		</li>
	</ul>
</div>

<script type="text/javascript">
	$("#serviceInfo").kendoComboBox({
		placeholder:"Lọc theo thủ tục hành chính",
		dataTextField:"serviceName",
		dataValueField:"serviceCode",
		dataSource:{
			transport:{
				read:{
					url:"http://localhost:8081/api/admin/serviceinfos",
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

	$("#customer_menu").kendoPanelBar({ animation: {} });

	$(function() {
		$("[data-role=combobox]").each(function() {
			var widget = $(this).getKendoComboBox();
			widget.input.on("focus", function() {
				widget.open();
			});
		});
	});

	$(function(){
		var arrStatus=${customer.status};
		$("#profileStatus").empty();
		for(var i=0;i<arrStatus.length;i++){
			$("#profileStatus").append("<li style='padding:5px 0;' dataPk='"+arrStatus[i].statusCode+"' class='itemStatus'><a href='javascript:;' >"+arrStatus[i].statusName+"</a></li>");
		}
		
		$("#profileStatus li").first().addClass("active");

		$("#customer_dossier_followgov_list").load("${ajax.customer_dossier_followgov_list}",function(event){
			var id=$("#profileStatus li").first().attr("dataPk");
		});
	});
	
	$(document).on("click",".itemStatus",function(event){
		event.preventDefault();
		
		$("#profileStatus li").removeClass('active');
		$(this).addClass('active');
		
		var id=$(this).attr("dataPk");
		$("#customer_dossier_followgov_list").load("${ajax.customer_dossier_followgov_list}",function(event){
			var id=$("#profileStatus li").first().attr("dataPk");
		});
	});

	$("#btnSearch").click(function(){
		
	});

	$("#keyword").change(function(){
		console.log($(this).val());
		
	});

</script>