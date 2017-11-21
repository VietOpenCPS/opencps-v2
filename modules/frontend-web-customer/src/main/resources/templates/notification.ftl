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
				        if(read === false) {
				            bgBlue = "row-blue";
				            title = "Đánh dấu đã đọc"
				        }
				    #
					<li class="col-sm-12 #:bgBlue# text-normal itemNotify" dataPk="#:dossierId#" >
						<div class="col-sm-10">
							<div class="row">
								<div style="float: left; width: 25px;">
									<i dataPk="#:notificationId#" class="fa fa-circle text-light-gray checkRead text-light-gray hover-pointer" title="#:title#""></i>
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
	// var count1 = 0;
    // var dataSourceNotification1 = new kendo.data.DataSource({
    //     transport:{
	   //      read:function(options){
    //             $.ajax({
    //                 url:"http://localhost:3000/notification",
    //                 // url: "${api.server}/users/notification",
    //                 dataType:"json",
    //                 type:"GET",
    //                 headers : {"groupId": ${groupId}},
    //                 success:function(result){   
    //                     if(result.data){
    //                         options.success(result);
    //                         $(result.data).each(function(index, value){
    //                             if(value.read === true){
    //                                 count1+=1
    //                             }
    //                         });
    //                         if (count1>0) {
    //                             $("#btn-show-notification").css("background-color","#84FAFA")
    //                         };
    //                         $("#totalNotify").html(count1);
    //                     };   
    //                 },
    //                 error:function(result){
    //                     options.error(result);
    //                 }
    //             });
    //         }
    //     },
    //     schema:{
    //         total: "total",
    //         data: "data",
    //         model: {
    //             id: "notificationSubject"
    //         }
    //     }
    // });

    // var dataSourceNotification = new kendo.data.DataSource({
    //     transport:{
    //         read:function(options){
    //             $.ajax({
    //                 url:"http://localhost:3000/notification",
    //                 // url: "${api.server}/users/notifications",
    //                 dataType:"json",
    //                 type:"GET",
    //                 headers : {"groupId": ${groupId}},
    //                 success:function(result){
    //                     if(result.data){
    //                         options.success(result);
    //                         $(result.data).each(function(index, value){
    //                             if(value.read === false){
    //                                 count+=1
    //                             }
    //                         });
    //                         if (count>0) {
    //                             $("#btn-show-notification").css("background-color","#84FAFA")
    //                         };
    //                         $("#totalNotify").html(count);
    //                     };
    //                     $(".checkRead").click(function(e){
    //                         e.stopPropagation();
    //                         var idItem = $(this).attr("dataPk");
    //                         dataSourceNotification.transport.update(idItem)
    //                     });
    //                     $(".itemNotify").click(function(e){
    //                         var idItem = $(this).children(".checkRead").attr("dataPk");
    //                         var dossierid = $(this).attr("dataPk");
    //                         var dataDossierId = new kendo.data.DataSource({
    //                             transport:{
    //                                 read:function(options){
    //                                     $.ajax({
    //                                         url:"${api.server}/dossiers/"+dossierid, 
    //                                         dataType:"json",
    //                                         headers : {"groupId": ${groupId}},
    //                                         type:"GET",
    //                                         success:function(result){
    //                                             var dossierItemStatus = result.dossierStatus;
    //                                             manageDossier.navigate("/"+dossierItemStatus+"/dossiers/"+dossierid);
    //                                             dataSourceNotification.transport.update(idItem)
    //                                         },
    //                                         error:function(result){
    //                                             options.error(result);
    //                                         }
    //                                     });
    //                                 }
    //                             },
    //                             error: function(e) {         
    //                                 this.cancelChanges();
    //                             },
    //                         });
    //                         dataDossierId.read();
    //                     });
    //                 },
    //                 error:function(result){
    //                     options.error(result);
    //                 }
    //             });
    //         },
    //         update: function(id){
    //             console.log("Run update");
    //             $.ajax({
    //                 url: "${api.server}/users/notifications/"+id+"/mark",
    //                 // url:"http://localhost:3000/notification",
    //                 dataType:"json",
    //                 type: "PUT",
    //                 success:function(result){
    //                     console.log(result);
    //                     dataSourceNotification.pushUpdate([
    //                         {notificationSubject: id, read: result.read }
    //                     ]);
    //                 },
    //                 error: function(result) {
                        
    //                 }
    //             })                       
    //         }
    //     },
    //     schema:{
    //         total: "total",
    //         data: "data",
    //         model: {
    //             id: "notificationSubject"
    //         }
    //     }
    // });
    $("#listViewNotification").kendoListView({
        dataSource: dataSourceNotification,
        template: kendo.template($("#notificationTemplate").html()),
        selectable: "single",
        autoBind: false
    });
</script>
