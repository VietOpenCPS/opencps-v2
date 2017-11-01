
<div class="panel-body PT0">
	<input type="hidden" name="idItemCustomerDossier" id="idItemCustomerDossier">
	<ul class="ul-with-border">
		<div id="listViewProfile"></div>
	</ul>
	
	<script type="text/x-kendo-template" id="proFileTemplate">
		<div class="row itemCustomerDossierList" dataPk="#:id#">
			<div class="row M0">
				<div class="row-blue align-middle">
					<div class="order-number">#:counter#</div>
					<div class="dossier-number" data-toggle="tooltip" title="Mã hồ sơ"><span class="red">\\#</span> #:serviceCode#</div>
					<div class="receive-number"><span class="text-normal">Mã tiếp nhận:</span> #:dossierNo#</div>
					#
						var label="label-info";
						switch(dossierStatus) {
						    case "New":
						        label="label-info";
						        break;
						    case "Collecting":
						        label="label-info";
						        break;
						    case "Receiving":
						        label="label-info";
						        break;
						    case "Waiting":
						        label="label-info";
						        break;
						    case "Paying":
						        label="label-info";
						        break;
						    case "Processing":
						        label="label-info";
						        break;
						    case "Waiting":
						        label="label-info";
						        break;
						    case "Paying":
						       	label="label-info";
						        break;
						    case "Handover":
						        label="label-info";
						        break;  
						    case "Releasing":
						        label="label-info";
						        break;
						    case "Paying":
						        label="label-info";
						        break;	
						    case "Posting":
						        label="label-info";
						        break;
					        case "Done":
						        label="label-info";
						        break;
					        case "Cancelled":
						        label="label-info";
						        break;					        
						    default:
						        label="label-info";
						}
					#
					<span class="label #:label# MLA"><#-- #:dossierSubStatusText# -->Hoàn thành</span> 
				</div>
			</div>
			<div class="col-sm-12 PL0 PT5 PB10">
				<div class="row M0">
					<div class="col-sm-8">
						<p>#:serviceName#</p>
						<p>
							<i class="fa fa-university" style="color: \\#84FAFA;" aria-hidden="true"></i> #:govAgencyName#
						</p>
						
						<p>
							#if(typeof actionNote !== "undefined"){#
								<i class="fa fa-bolt" aria-hidden="true" style="color: red;"></i> 
								<i>#:actionNote#</i>
							#}#
						</p>

						<p>
							#if(typeof stepInstruction !== "undefined"){#
								<i>#:stepInstruction#</i>
							#}#
						</p>

						#if(dossierStatus === "Done"){#
							<a href="${api.server}/dossiers/#:id#/result" style="margin-right: 10px;">
								<i class="fa fa-download" aria-hidden="true">
								</i> Tải giấy tờ kết quả
							</a>
						#}#
						
						#if(dossierStatus === "Done" ){#
							<a href="javascript:;" onclick="javascript:copyProfile(#:id#)"><i class="fa fa-file-archive-o" aria-hidden="true"></i> Sao chép hồ sơ
							</a>
						#}#
					</div>
					
					<div class="col-sm-4 MT10 text-right">
						<div class="row">
							<p data-toggle="tooltip" title="Ngày gửi">
								<i class="fa fa-paper-plane-o" aria-hidden="true"></i> #:submitDate#
							</p>
							<p data-toggle="tooltip" title="Ngày tiếp nhận">
								<i class="fa fa-file-o" aria-hidden="true"></i> #:receiveDate#
							</p>
							<p data-toggle="tooltip" title="Ngày hẹn trả">
								<i class="fa fa-clock-o" aria-hidden="true"></i> #:dueDate#
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>

	</script>
</div>
<div class="footerListProfile row-header PT20" style="background: #f6f6f6">
	<div class="clearfix align-middle" style="float: right">
		<span class="text-light-gray MR15"><i>Tổng số <span id="totalItem_dossierList" class="red"></span> kết quả được tìm thấy</i></span>
		<span class="show-per-page MT0">Hiển thị
			<span class="select-wrapper">
				<select class="ML5" id="itemPpage">
					
				</select>
			</span>
		</span>
		<span id="pagerProfile" class="M0 P0 PR5"></span>
	</div>
	
</div>

<script type="text/javascript">	
	var dataSourceProfile=new kendo.data.DataSource({
		transport:{
			read:function(options){
				$.ajax({
					url:"${api.server}/dossiers",
					dataType:"json",
					type:"GET",
					data:{
						serviceInfo: options.data.serviceInfo,
						govAgencyCode: options.data.govAgencyCode,
						year: options.data.year,
						month: options.data.month,
						keyword: options.data.keyword,
						status : options.data.status
					},	
					success:function(result){
						options.success(result);
						var totalItem = parseInt(dataSourceProfile.total());
						var pSize = dataSourceProfile.pageSize();
						var arrPsize = [];
						var selectHtml = "<option value='"+totalItem+"'>Tất cả</option>";
						$("#totalItem_dossierList").text(dataSourceProfile.total());
						if (totalItem <= dataSourceProfile.pageSize()) {
							$("#itemPpage").html("<option value='"+totalItem+"' selected>"+totalItem+"</option>")
						} else {
							for (var i = pSize; i < totalItem; i+=pSize) {
								arrPsize.push(i)
							};
							var sub = "";
							$.each(arrPsize,function(index,value){
								sub+="<option value='"+value+"'>"+value+"</option>"
							});
							$("#itemPpage").html(sub);
							$("#itemPpage").append(selectHtml)
						}
					},
					error:function(result){
						options.error(result);
					}
				});
			}
		},
		error: function(e) {         
			this.cancelChanges();
		},
		pageSize: 2,
		schema:{
			data:"data",
			total:"total",
			model:{
				id: "dossierId"
			}
		}
	});

	$("#listViewProfile").kendoListView({
		dataSource:dataSourceProfile,
		template:kendo.template($("#proFileTemplate").html()),
		selectable: "single",
		autoBind: false
	});

	$("#pagerProfile").kendoPager({
		dataSource:dataSourceProfile,
		buttonCount: 3,
		info: false
	});

	$(document).on("click",".itemCustomerDossierList",function(event){
		var id = $(this).attr("dataPk");	
		$("#idItemCustomerDossier").val(id);
		$("#dossier_detail").show();
		$("#dossier_list").hide();
		$("#dossier_detail").load("${ajax.customer_dossier_detail}?id="+id+"",function(result){
		});
	});
	// load content/select item per page
	$("#itemPpage").change(function(){
		$("#listViewProfile").getKendoListView().dataSource.pageSize(parseInt($("#itemPpage").val()))
	});

	$(document).ready(function(){
		$('[data-toggle="tooltip"]').tooltip(); 
	});

	var copyProfile=function(id){
		$.ajax({
			url:"${api.server}/"+id+"/cloning",
			dataType:"json",
			type:"GET",
			success:function(result){
				
			},
			error:function(result){

			}
		})
	};
	// option kendo-page
	$(".k-pager-first").css("display","none");
	$(".k-pager-last").css("display","none")
</script>