<#if (Request)??>
	<#include "init.ftl">
</#if>
<div class="row">
	<div class="col-sm-12">
		<div class="box">
			<div class="row-header align-middle">
				<div class="background-triangle-big">THÔNG BÁO CỦA BẠN</div> 
			</div>
			<div class="row">
				<ul class='ul-with-border'>
					<div class="col-sm-12">
						<div id='listViewNotification'>
							
						</div>
					</div>
				</ul>
				<script type="text/x-kendo-template" id="notificationTemplate">
					<li class="col-sm-12">
						<div class="col-sm-10">
							<div class="row">
								<div style="float: left; width: 25px;">
									<i class="fa fa-circle blue"></i> <br>
									<i class="fa fa-cog" data-toggle="tooltip" title="Ân thông báo này"></i>
								</div>
								<div style="">
									<span>Yeu cau bo sung</span> <br>
									<span>Bo thoong tin ve giay phep kinh doanh</span>
								</div>
							</div>
						</div>
						<div class="col-sm-2">
							<span>15:00 pm | 02-08-2017</span>
						</div>
					</li>
				</script>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var dataSourceNotification;
	$(function(){
		dataSourceNotification = new kendo.data.DataSource({
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
				data: "data",
				total: "total",
				model:{
					id: "id"
				}
			}
		});

		$("#listViewNotification").kendoListView({
			dataSource: dataSourceNotification,
			template: kendo.template($("#notificationTemplate").html()),
			selectable: "single",
			autoBind: false
		});

	});
</script>
