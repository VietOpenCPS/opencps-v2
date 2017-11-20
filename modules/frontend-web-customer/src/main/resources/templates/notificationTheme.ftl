
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
        var title = "Đánh dấu đã đọc";    
        var bgBlue = "";
        if(read === true) {
            bgBlue = "row-blue";
            title = "Đánh dấu chưa đọc"
        }
    #
	<li class="PL10 #:bgBlue# text-normal">
		<i dataPk="#:notificationSubject#" class="fa fa-circle checkRead text-light-gray hover-pointer" title="#:title#"></i> <span class="ML5">#:notificationType#</span> <br>
		<p class="PL20 M0 text-bold">#:notificationSubject#</p>
		<p class="PL20 M0 text-light-gray">#:notificationContent#</p>
		<p class="PL20 M0 text-light-gray">#:createDate#</p>
	</li>
</script>
<script type="text/javascript">
    var count = 0; 
    var displayNotification = function(){

    };
    var dataSourceNotification = new kendo.data.DataSource({
        transport:{
            read:function(options){
                $.ajax({
                    // url:"http://localhost:3000/notification",
                    url: "${api.server}/users/notifications",
                    dataType:"json",
                    type:"GET",
                    headers : {"groupId": ${groupId}},
                    success:function(result){
                        if(result.data){
                            options.success(result);
                            $(result.data).each(function(index, value){
                                if(value.read === true){
                                    count+=1
                                }
                            });
                            if (count>0) {
                                $("#btn-show-notification").css("background-color","#84FAFA")
                            };
                            $("#totalNotify").html(count);
                        };
                        $(".checkRead").click(function(e){
                            e.stopPropagation();
                            var idItem = $(this).attr("dataPk");
                            dataSourceNotification.transport.update(idItem)
                        })
                    },
                    error:function(result){
                        options.error(result);
                    }
                });
            },
            update: function(id){
                $.ajax({
                    url: "${api.server}/users/notifications/"+id+"/mark",
                    // url:"http://localhost:3000/notification",
                    dataType:"json",
                    type: "PUT",
                    success:function(result){
                        console.log(result);
                        dataSourceNotification.pushUpdate([
                            {notificationSubject: id, read: result.read }
                        ]);
                    },
                    error: function(result) {
                        this.cancelChanges();
                    }
                })                       
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

    $("#listViewNotificationDrop").kendoListView({
        dataSource: dataSourceNotification,
        template: kendo.template($("#dropdownNotificationTemp").html()),
        selectable: "single",
    });

    var changeRead = function(){

    }

</script>