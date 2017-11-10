<#if (Request)??>
	<#include "init.ftl">
</#if>
<div class="panel panel-main MT15" id="sideItemPayment"> 
	<div class="panel-heading row-header"> 
		<span class="panel-title">Yêu cầu thanh toán</span> 
		<span class="pull-right clickable" data-toggle="collapse" data-target="#paymentRequest"> 
			<i id="icon_collapse1" class="glyphicon glyphicon-chevron-up"></i> 
		</span>
		<span class="pull-right MR10 text-light-gray hover-pointer" id="sort_modified1" title="Sắp xếp theo ngày"><i class="fa fa-calendar" aria-hidden="true"></i></span>
	</div> 
	<div class="panel-body P0 collapse in" id="paymentRequest">
		<ul class='ul-with-border'>
			<div id='listViewCustomer_Payment_Request'></div>
		</ul>
		<div class="clearfix align-middle PL10">
			<span class="text-light-gray MR50"><i>Có <span id="total_Payment_Request" class="red"> </span> yêu cầu</i></span>
			<span id="pagerCustomer_Payment_Request" class="M0 PR5"></span>
		</div>

		<script type="text/x-kendo-template" id="payment_Request_Template">
			<li data-pk="#:id#" class="P10">
				<p>#:content#</p>
				<span class="text-greyy">#:govAgencyName#</span> <br>
				<span class="text-greyy">#:createDate#</span>
			</li>
		</script>
	</div> 
</div>

<script type="text/javascript">
	var flagClick1 = 0;
	$(".clickable").on("click",function() {
		if (flagClick1 == 0) {
			$("#icon_collapse1").css("transform","rotate(180deg)");
			flagClick1 = 1
		} else {
			$("#icon_collapse1").css("transform","rotate(0deg)");
			flagClick1 = 0
		}
	});
	var dataSourcePaymentRequest=new kendo.data.DataSource({
		transport:{
			read:function(options, itemRequest){
				$.ajax({
					url:"${api.server}/dossiers/dossierlogs",
					dataType:"json",
					type:"GET",
					data:{
						type: options.data.type,
						sort: options.data.sort_modified
					},
					success:function(result){
						if(result.data){
							options.success(result);
							$("#total_Payment_Request").text(dataSourcePaymentRequest.total())
						}else{
							$("#sideItemPayment").hide();
						}
						
					},
					error:function(result){
						options.error(result);
						$("#sideItemPayment").hide();
					}
				});
			}

		},
		schema:{
			data:"data",
			total:"total",
			model:{
				id:"dossierId"
			}
		},
		pageSize:5
	});

	$("#listViewCustomer_Payment_Request").kendoListView({
		dataSource:dataSourcePaymentRequest,
		template:kendo.template($("#payment_Request_Template").html()),
		selectable: "single",
		change: function(){
			//event load dossier_detail dossier_log
	    	//event load dossier_detail dossier_log
	    	var index = this.select().index();
	            dataItem = this.dataSource.view()[index];
			$("#dossier_detail").show();
			$("#dossier_list").hide();
			$("#dossier_detail").load("${ajax.customer_dossier_detail}?id="+dataItem.dossierId+"",function(result){
			})
		},
		autoBind: false
	});
	//
	dataSourcePaymentRequest.read({type : 234});
	$("#sort_modified1").click(function(){
		console.log("asdsasdasdasdasdasd");
		dataSourcePaymentRequest.read({type : 234, sort_modified: "modified"});
		
	})
	// option kendo-page
	$("#pagerCustomer_Payment_Request").kendoPager({
		dataSource:dataSourcePaymentRequest,
		input: false,
		numeric: false,
		info: false
	});
	$(".k-pager-first").css("display","none");
	$(".k-pager-last").css("display","none");
</script>