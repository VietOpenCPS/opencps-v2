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
						<div id="listViewNotification">
							
						</div>
					</div>
				</ul>
				<script type="text/x-kendo-template" id="notificationTemplate">
					<li class="col-sm-12">
						<div class="col-sm-10">
							<div class="row">
								<div style="float: left; width: 25px;">
									<i class="fa fa-circle text-light-gray"></i>
								</div>
								<div>
									<span>#:notificationType# </span><span class="text-bold">#:notificationSubject#</span> <br>
									<span class="text-light-gray PL25">#:notificationContent#</span>
								</div>
							</div>
						</div>
						<div class="col-sm-2">
							<span class="text-light-gray">#:createDate#</span>
						</div>
					</li>
				</script>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
    var dataSourceNotification1 = new kendo.data.DataSource({
        transport:{
	        read:function(options){
                $.ajax({
                    // url: "${api.server}/users/notification",
                    url:"http://localhost:3000/notification",
                    dataType:"json",
                    type:"GET",
                    headers : {"groupId": ${groupId}},
                    success:function(result){   
                        options.success(result);   
                    },
                    error:function(result){
                        options.error(result);
                    }
                });
            }
        },
        schema:{
            total: "total",
            data: "data",
        }
    });

    $("#listViewNotification").kendoListView({
        dataSource: dataSourceNotification1,
        template: kendo.template($("#notificationTemplate").html()),
        selectable: "single",
    });
</script>
