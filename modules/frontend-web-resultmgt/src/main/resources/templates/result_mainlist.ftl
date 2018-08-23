<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="demo-section k-content wide">
	<div class="row-header"> <div class="background-triangle-big" style="width: 30%;">DANH SÁCH GIẤY PHÉP</div> </div>
	<div class="box" id="listView"></div>
	<div id="pager" class="k-pager-wrap"></div>
</div>
	
<script>
        var dataSource = new kendo.data.DataSource({
            transport: {
            	read:function(options){
					$.ajax({
						url: "${api.server}/deliverables",
						dataType:"json",
						headers : {"groupId": ${groupId}},
						type:"GET",
						data: options.data,
						success:function(result){
							if(result.data){
								options.success(result);
							}else{
								options.success({
									"total":0,
									"data":[]
								})
							}
						},
						error:function(result){
							options.error(result);
						}
					});
				}
            },
            pageSize: 5,
			schema:{
				data:"data",
				total:"total"
			}
        });


        $("#pager").kendoPager({
            dataSource: dataSource
        });

		$("#listView").kendoListView({
			dataSource: dataSource,
			template: kendo.template($("#proFileTemplate").html()),
		});
</script>

<script type="text/x-kendo-template" id="proFileTemplate">
	<div class="row PL15 PR15 itemCustomerDossierList">
		<div class="row M0">
			<div class="row-blue align-middle">
				<div class="order-number">\#</div>
				<#-- <div class="dossier-number" data-toggle="tooltip" title="Mã hồ sơ"><span class="red">\\#</span> #:dossierId#</div> -->
				<#-- <div class="receive-number PL10"> #:deliverableCode#<span class="text-normal"> - </span>#:deliverableName# </div> -->
				<div class="receive-number PL10">TT50_BVHTTDL<span class="text-normal"> - </span>#:deliverableName# </div>
				#
					var status;
					if(deliverableState == "1"){
						status = "Hiệu lực";
					}else if(deliverableState == "2"){
						status = "Hết hạn";
					}else if(deliverableState == "3"){
						status = "Tạm dừng";
					}else if(deliverableState == "4"){
						status = "Thu hồi";
					}else if(deliverableState == "5"){
						status = "Từ chối";
					}else if(deliverableState == "0"){
						status = "Không rõ";
					}
				#
				<span class="label btn btn-active MLA">#:status#</span> 
			</div>
		</div>
		<div class="col-sm-12 PL0 PT5 PB5">
			<div class="row M0">
				<div class="col-sm-9" style="border-right: 1px solid \\#ddd">
					<p class="MB5">
						<i class="fa fa-university" style="color: \\#84FAFA;" aria-hidden="true"></i> Cơ quan cấp: #:govAgencyName#
					</p>

					<p class="MB5">
						<i class="fa fa-user" style="color: \\#84FAFA;" aria-hidden="true"></i> Người được cấp: #:applicantName#
					</p>

					<p class="MB5">#:subject#</p>

					<p class="actionDeliverable MB0">
						<a href="javascript:;" class="downloadAddRes PR20" onclick= "openWindow();">
							<i class="fa fa-file-text" aria-hidden="true"></i> 
							Xem lịch sử
						</a>

						<a href="javascript:;" class="downloadProfile PR20">
							<i class="fa fa-calendar" aria-hidden="true">
							</i> Gia hạn
						</a>

						<a href="javascript:;" class="copyProfile PR20">
							<i class="fa fa-minus-circle" aria-hidden="true"></i> Tạm dừng
						</a>

						<a href="javascript:;" class="copyProfile PR20">
							<i class="fa fa-undo" aria-hidden="true"></i> Thu hồi
						</a>
					</p>
				</div>
				<#-- Content DATE -->
				<div class="col-sm-3">
					<div class="">
						<ul>
							<li data-toggle="tooltip" title="Ngày gửi" class="row PL10 PB5">Ngày cấp: #=issueDate#
							
							</li>
							<li data-toggle="tooltip" title="Ngày tiếp nhận" class="row PL10 PB5">Ngày hết hạn: #=expireDate#
							
							</li>
							<li data-toggle="tooltip" title="Ngày hẹn trả" class="row PL10 PB5">Ngày gia hạn: #=revalidate#
							
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</script>

<div id="windowId">
    <!-- Window content -->
    <div id="windowlistView"></div>
</div>
 
<!-- ListView template -->
<script type="text/x-kendo-tmpl" id="windowtemplate">
    <div>
        <p>#:applicantName#</p>
    </div>
</script>
<script type="text/javascript">
	//ListView dataSource
	var windowdataSource = new kendo.data.DataSource({
		transport: {
			read:function(options){
				$.ajax({
					url: "${api.server}/deliverables",
					dataType:"json",
					headers : {"groupId": 55301},
					type:"GET",
					data: options.data,
					success:function(result){
						if(result.data){
							options.success(result);
						}else{
							options.success({
								"total":0,
								"data":[]
							})
						}
					},
					error:function(result){
						options.error(result);
					}
				});
			}
		},
		pageSize: 5,
		schema:{
			data:"data",
			total:"total"
		}
	});

	$("#pager").kendoPager({
		dataSource: dataSource,
		messages: {
        	display: "Hiển thị {0}-{1} trên {2} bản ghi",
        	empty: "Không có dữ liệu",
      	}
	});

	//ListView initialize
	$("#windowlistView").kendoListView({
		dataSource: windowdataSource,
		template: kendo.template($("#windowtemplate").html())
	});

	var mywindow ="";

	// window initializing
	mywindow = $("#windowId").kendoWindow({
		actions: ["Close"],
		draggable: true,
		height: "400px",
		modal: true,
		resizable: true,
		title: "Lịch sử thay đổi",
		width: "650px",
		visible: false
	});


	//Open the initialized Window on button click
	function openWindow()
	{
		if ($("#windowId").data("kendoWindow")) {
			var window = $("#windowId").data("kendoWindow")
			window.open();
			window.center();
		}
	}
</script>