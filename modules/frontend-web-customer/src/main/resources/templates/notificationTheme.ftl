<#if (Request)??>
    <#include "init.ftl">
</#if>
<ul class="dropdown-menu" role="menu" aria-labelledby="btn-show-notification" id="dropdown-menu-notification">
    <span class="text-bold PL10 PT10 PB10">Thông báo</span> <span class="pull-right hover-pointer MR5">Đánh dấu tất cả đã đọc</span>
    <li role="presentation" class="divider MB0"></li>
    <div data-spy="scroll" data-target="#listViewNotificationDrop" data-offset="0">
        <ul class="ul-with-border" id="listViewNotificationDrop" style="height:305px;width:100%;overflow:auto;">
            
        </ul>
    </div>
    <li role="presentation" class="divider MT0"></li>
    <div class="row text-center">
        <a  href="#/thongbao" class="text-link" id="btn-showall-notification">Xem tất cả</a>
    </div>
</ul>

<style type="text/css">
    .dropdown-menu {
        width: 250px !important;
        height: 380px !important;
    }
</style>

<script type="text/x-kendo-template" id="dropdownNotificationTemp">
    #
        var title = "Đánh dấu chưa đọc";    
        var bgBlue = "";
        var iconCheck = "fa fa-circle-o";
        if(read === false) {
            bgBlue = "row-blue";
            title = "Đánh dấu đã đọc";
            iconCheck = "fa fa-circle"
        }
    #
	<li class="PL10 #:bgBlue# text-normal itemNotify hover-pointer" dataPk="#:dossierId#" statusRead="#:read#" onclick="event.stopPropagation(); itemEvent(this)">
		<i dataPk="#:notificationId#" class="#:iconCheck# checkRead text-light-gray" title="#:title#" onclick="event.stopPropagation();checkRead(this)"></i> <span class="ML5">#:notificationType#</span> <br>
		<p class="PL20 M0 text-bold">#:notificationSubject#</p>
		<p class="PL20 M0 text-light-gray">#:notificationContent#</p>
		<p class="PL20 M0 text-light-gray">#:createDate#</p>
	</li>
</script>
<script type="text/javascript">
    var count = 0;
    var dataSource2 = false;
    var countNotify = function(){
        if (count == 0) {
            $("#totalNotify").html("");
            $("#btn-show-notification").css("background-color","#e1e2e1")
        } else {
            $("#totalNotify").html(count);
            $("#btn-show-notification").css("background-color","#84FAFA")
        }
    }
    var dataSourceNotification = new kendo.data.DataSource({
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
                        if (dataSource2 == true) {
                            dataSourceNotify2.read();
                        }
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

    $("#listViewNotificationDrop").kendoListView({
        dataSource: dataSourceNotification,
        dataBound: function(e){
            if(e.items){
                $(e.items).each(function(index, value){
                    if(value.read == false){
                        count+=1
                    }
                });
                countNotify();
            } 
        },
        template: kendo.template($("#dropdownNotificationTemp").html()),
        selectable: "single",
    });
    var checkRead = function(selector){
            var status1 = $(selector).parent().attr("statusRead");
            var noId = $(selector).attr("dataPk");
            if (status1 == "false"){
                dataSourceNotification.pushUpdate([
                    {notificationId: noId, read: true }                    
                ]);
                count -= 1;
                countNotify();
                $(selector).parent().attr("statusRead","true");
                $(selector).parent().removeClass("row-blue")
            } else {
                dataSourceNotification.pushUpdate([
                    {notificationId: noId, read: false }
                ]);
                count += 1;
                countNotify();
                $(selector).parent().attr("statusRead","false");
                $(selector).parent().addClass("row-blue")
            }
            dataSourceNotification.transport.update(noId)
    }
    var itemEvent = function(selector){
        var noId = $(selector).children(".checkRead").attr("dataPk");
        var dossierid = $(selector).attr("dataPk");
        var readStatus = $(selector).attr("statusRead");
        if (readStatus == "false"){
            dataSourceNotification.pushUpdate([
                {notificationId: noId, read: true }
            ]);
            count -= 1;
            countNotify();
            $(selector).attr("statusRead","true");
            $(selector).removeClass("row-blue")
        }
        var dataDossierId = new kendo.data.DataSource({
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
                                dataSourceNotification.transport.update(noId)
                            } 
                        },
                        error:function(result){
                            options.error(result);
                        }
                    });
                }
            },
            error: function(e) {         
                
            },
        });
        dataDossierId.read();
    }
    var styleNotify = function(){

    }
</script>