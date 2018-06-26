<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="demo-section k-content wide">
	<div class="row-header"> <div class="background-triangle-big" style="width: 18%;">DANH SÁCH GIẤY PHÉP</div> </div>
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
				<div class="receive-number PL10"> #:deliverableType#<span class="text-normal"> - </span>#:deliverableName# </div>
				<#-- <div class="receive-number PL10">#:deliverableName# </div> -->
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

					<p class="MB0">
						<#-- <a href="javascript:;" class="downloadAddRes PR20" onclick= "openWindow();">
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
						</a> -->
						<i class="fa fa-calendar" style="color: \\#84FAFA;" aria-hidden="true"></i> Ngày cấp: &nbsp;&nbsp;#=issueDate#
					</p>
				</div>
				<#-- Content DATE -->
				<div class="col-sm-3">
					<div class="">
						<ul>
							<#-- <li data-toggle="tooltip" title="Ngày gửi" class="row PL10 PB5">Ngày cấp: #=issueDate#
							
							</li>
							<li data-toggle="tooltip" title="Ngày tiếp nhận" class="row PL10 PB5">Ngày hết hạn: #=expireDate#
							
							</li>
							<li data-toggle="tooltip" title="Ngày hẹn trả" class="row PL10 PB5">Ngày gia hạn: #=revalidate#
							
							</li> -->
							<li class="row PL10 PB5">
								<a href="javascript:;" style="font-size: 110%;" class="text-hover-blue text-bold" onclick="toDetailDossier('#:deliverableId#', '#:deliverableCode#')">Xem thông tin hồ sơ</a>
							</li>
							<li class="row PL10 PB5">
								<a href="javascript:;" style="font-size: 110%;" class="text-hover-blue text-bold" onclick="xemGiayPhep('#:deliverableCode#')">Xem giấy phép</a>
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
	function fileAttachmentUrl ( options) {

  // Use XMLHttpRequest instead of Jquery $ajax
  var xhttp = new XMLHttpRequest();
  var a,filename;
  var data = {};
  
  xhttp.onreadystatechange = function() {
  	
  	if (xhttp.readyState === 4 && xhttp.status === 200) {
  		
      // check for a filename
      var disposition = xhttp.getResponseHeader('Content-Disposition');
      if (disposition && disposition.indexOf('attachment') !== -1) {
      	var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
      	var matches = filenameRegex.exec(disposition);
      	if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');
      }
      
      // Trick for making downloadable link
      a = document.createElement('a');
      a.href = window.URL.createObjectURL(xhttp.response);

      var url = window.URL.createObjectURL(xhttp.response);
      
      //callback success
      options.success({url : url, status : xhttp.status});
  } else if (xhttp.readyState === 4 && xhttp.status !== 200) {
  	options.error(xhttp.status);
  }
  
};

  // Post data to URL which handles post request
  xhttp.open(options.method, options.url);
  xhttp.setRequestHeader("Content-Type", "application/json");
  
  // others data header
  if (options.hasOwnProperty("headers")){
  	Object.keys( options.headers ).map(function(objectKey, index) {
  		var value = options.headers[objectKey];
  		xhttp.setRequestHeader(objectKey, value);
  	});
  }

  // You should set responseType as blob for binary responses
  if (options.hasOwnProperty("responseType")){
  	xhttp.responseType = options.responseType;
  } else {
  	xhttp.responseType = 'blob';
  }
  
  // Data to post
  if (options.hasOwnProperty("data")){
  	data = options.data;
  }
  
  // excecute request
  xhttp.send(data);
  
};

function openFileNewtab(url){
	var urlOut = "";
	var urlReadFile = fileAttachmentUrl({
		method : "GET",
		url : url,
		async : false,
		success: function(options){
			urlOut = options.url;
			window.open(urlOut,"_blank");
		},
		error: function(){}
	});
}

function xemGiayPhep(deliverableCode){
	$.ajax({
		url: '/o/rest/v2/dossiers/file/' + deliverableCode,
		dataType: 'json',
		type: 'GET',
		headers: {"groupId": 55301},
		success: function (result) {
			var urlGetFile = "/o/rest/v2/dossiers/" + result.dossierId + "/files/" + result.referenceUid;
			openFileNewtab(urlGetFile)
		},
		error: function (xhr) {

		}
	})
}
function toDetailDossier(deliverableId, deliverableCode) {
	$.ajax({
		url: "/o/rest/v2/deliverables/" + deliverableId + "/number/" + deliverableCode,
		dataType:"json",
		headers : {"groupId": 55301},
		type:"GET",
		success:function(result){
			if(result.dossierId){
				resultRouter.navigate('/thong-tin-ho-so/' + result.dossierId)
			}else{
				notification.show({
					message: "Hồ sơ lỗi, bạn không thể xem được chi tiết hồ sơ này"
				}, "error");
			}
		},
		error:function(result){
			notification.show({
				message: "Hồ sơ lỗi, bạn không thể xem được chi tiết hồ sơ này"
			}, "error");
		}
	});
}
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