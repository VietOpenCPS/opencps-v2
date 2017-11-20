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
					#
				        var title = "Đánh dấu đã đọc";    
				        var bgBlue = "";
				        if(read === true) {
				            bgBlue = "row-blue";
				            title = "Đánh dấu chưa đọc"
				        }
				    #
					<li class="col-sm-12 #:bgBlue# text-normal" >
						<div class="col-sm-10">
							<div class="row">
								<div style="float: left; width: 25px;">
									<i class="fa fa-circle text-light-gray checkRead text-light-gray hover-pointer" title="#:title#""></i>
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
	var count1 = 0;
    var dataSourceNotification1 = new kendo.data.DataSource({
        transport:{
	        read:function(options){
                $.ajax({
                    // url:"http://localhost:3000/notification",
                    url: "${api.server}/users/notification",
                    dataType:"json",
                    type:"GET",
                    headers : {"groupId": ${groupId}},
                    success:function(result){   
                        if(result.data){
                            options.success(result);
                            $(result.data).each(function(index, value){
                                if(value.read === true){
                                    count1+=1
                                }
                            });
                            if (count1>0) {
                                $("#btn-show-notification").css("background-color","#84FAFA")
                            };
                            $("#totalNotify").html(count1);
                        };   
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
            model: {
                id: "notificationSubject"
            }
        }
    });

    $("#listViewNotification").kendoListView({
        dataSource: dataSourceNotification1,
        template: kendo.template($("#notificationTemplate").html()),
        selectable: "single",
    });
</script>
