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
				        var title = "Đánh dấu chưa đọc";    
				        var bgBlue = "";
				        var iconCheck = "fa fa-circle-o"
				        if(read === false) {
				            bgBlue = "row-blue";
				            title = "Đánh dấu đã đọc";
				            iconCheck = "fa fa-circle"
				        }
				    #
					<li class="col-sm-12 #:bgBlue# text-normal itemNotify hover-pointer" dataPk="#:dossierId#" data-Pk="#:notificationId#" statusRead="#:read#" onclick="event.stopPropagation(); itemEvent2(this)">
						<div class="col-sm-10">
							<div class="row">
								<div style="float: left; width: 25px;">
									<i dataPk="#:notificationId#" statusRead="#:read#" class="#:iconCheck# text-light-gray checkRead text-light-gray" title="#:title#" onclick="event.stopPropagation();checkRead2(this)"></i>
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
    $(function(){
        dataSource2 = true;
    })
    var dataSourceNotify2 = new kendo.data.DataSource({
        transport:{
            read:function(options){
                $.ajax({
                    url:"http://localhost:3000/notification",
                    // url: "${api.server}/users/notifications",
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
            },
            update: function(id){
                console.log("Run update");
                $.ajax({
                    url: "${api.server}/users/notifications/"+id+"/mark",
                    // url:"http://localhost:3000/notification",
                    dataType:"json",
                    headers : {"groupId": ${groupId}},
                    type: "PUT",
                    success:function(result){
                        
                    },
                    error: function(result) {
                        
                    }
                })                       
            }
        },
        schema:{
            total: "total",
            data: "data",
            model: {
                id: "notificationId"
            }
        }
    });

    $("#listViewNotification").kendoListView({
        dataSource: dataSourceNotify2,
        template: kendo.template($("#notificationTemplate").html()),
        selectable: "single",
    });
    var checkRead2 = function(selector){
        var status2 = $(selector).attr("statusRead");
        var noId = $(selector).attr("dataPk");
        if (status2 == "false"){
            dataSourceNotify2.pushUpdate([
                {notificationId: noId, read: true }                    
            ]);
            dataSourceNotification.pushUpdate([
                {notificationId: noId, read: true }                    
            ]);
            count -= 1;
            countNotify();
            $(selector).parent(".itemNotify").attr("statusRead","true");
            $(selector).parent(".itemNotify").removeClass("row-blue")
        } else {
            dataSourceNotify2.pushUpdate([
                {notificationId: noId, read: false }
            ]);
            dataSourceNotification.pushUpdate([
                {notificationId: noId, read: false }                    
            ]);
            count += 1;
            countNotify();
            $(selector).parent(".itemNotify").attr("statusRead","false");
            $(selector).parent(".itemNotify").addClass("row-blue")
        }
        dataSourceNotify2.transport.update(noId)
    }
    var itemEvent2 = function(selector){
        var noId = $(selector).attr("data-Pk");
        var dossierid = $(selector).attr("dataPk");
        var readStatus = $(selector).attr("statusRead");
        console.log(noId);
        if (readStatus == "false"){
            dataSourceNotify2.pushUpdate([
                {notificationId: noId, read: true }
            ]);
            dataSourceNotification.pushUpdate([
                {notificationId: noId, read: true }                    
            ]);
            count -= 1;
            countNotify();
            $(selector).attr("statusRead","true");
            $(selector).removeClass("row-blue");
        }
        var dataDossierId2 = new kendo.data.DataSource({
            transport:{
                read:function(options){
                    $.ajax({
                        url:"${api.server}/dossiers/"+dossierid, 
                        dataType:"json",
                        headers : {"groupId": ${groupId}},
                        type:"GET",
                        success:function(result){
                            var dossierItemStatus = result.dossierStatus;
                            manageDossier.navigate("/"+dossierItemStatus+"/dossiers/"+dossierid);
                            if (readStatus=="false") {
                                dataSourceNotify2.transport.update(noId)
                            } 
                        },
                        error:function(result){
                            
                        }
                    });
                }
            },
            error: function(e) {         
                
            },
        });
        dataDossierId2.read();
    }
    var styleNotify = function(){

    }
</script>

