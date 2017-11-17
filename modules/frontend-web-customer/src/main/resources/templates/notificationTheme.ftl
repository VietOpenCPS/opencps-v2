
<ul class="dropdown-menu" role="menu" aria-labelledby="btn-show-notification" id="dropdown-menu-notification">
    <span class="text-bold PL10 PT10 PB10">Thông báo</span> <span class="pull-right hover-pointer MR5">Đánh dấu tất cả đã đọc</span>
    <li role="presentation" class="divider MB0"></li>
    <div data-spy="scroll" data-target="#listViewNotificationDrop" data-offset="0">
        <ul class="ul-with-border" id="listViewNotificationDrop" style="height:305px;width:100%;overflow:auto;">
            
        </ul> 
    </div>
    <li role="presentation" class="divider MT0"></li>
    <div class="row text-center">
        <a  href="#/thongbao" id="btn-showall-notification">Xem tất cả</a>
    </div>
</ul>

<style type="text/css">
    .dropdown-menu {
        width: 250px !important;
        height: 380px !important;
    }
</style>

<script type="text/x-kendo-template" id="dropdownNotificationTemp">
	<li class="PL10 row-blue text-normal">
		<i class="fa fa-circle text-light-gray hover-pointer"></i> <span class="ML5">#:notificationType#</span> <br>
		<p class="PL20 M0 text-bold">#:notificationSubject#</p>
		<p class="PL20 M0 text-light-gray">#:notificationContent#</p>
		<p class="PL20 M0 text-light-gray">#:createDate#</p>
	</li>
</script>
<script type="text/javascript">
    var dataSourceNotification = new kendo.data.DataSource({
        transport:{
            read:function(options){
                    $.ajax({
                        // url: "${api.server}/users/notification",
                        url:"http://localhost:3000/notification",
                        dataType:"json",
                        type:"GET",
                        success:function(result){
                            if(result.data){
                                options.success(result);
                                $("#totalNotify").html(dataSourceNotification.total());
                            }
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

    $("#listViewNotificationDrop").kendoListView({
        dataSource: dataSourceNotification,
        template: kendo.template($("#dropdownNotificationTemp").html()),
        selectable: "single",
    });
</script>