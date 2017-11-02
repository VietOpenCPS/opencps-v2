<div class="panel panel-main MT15" id="sideItemResult"> 
	<div class="panel-heading row-header"> 
		<span class="panel-title">Trả kết quả</span> 
		<span class="pull-right clickable" data-toggle="collapse" data-target="#resultRequest"> 
			<i id="icon_collapse2" class="glyphicon glyphicon-chevron-up"></i>
		</span> 
		<span class="pull-right MR10 text-light-gray" id="sort_modified2"><i class="fa fa-calendar" aria-hidden="true"></i></span>
	</div> 
	<div class="panel-body P0 collapse in" id="resultRequest">	
		<ul class='ul-with-border'>
			<div id='listViewCustomer_Result_Request'></div>
		</ul>
		<div class="clearfix align-middle PL10">
			<span class="text-light-gray MR50"><i>Có <span id="total_result" class="red"> </span> kết quả</i></span>
			<span id='pagerCustomer_Result_Request' class="M0 PR5"></span>
		</div>
		
		<script type="text/x-kendo-template" id="result_Request_Template">
			<li data-pk="#:id#" class="P10">
				<p>#:content#</p>
				<span class="text-greyy">#:govAgencyName#</span> <br>
				<span class="text-greyy">#:createDate#</span>
			</li>
		</script>
	</div> 
</div>

<script type="text/javascript">
	var flagClick2 = 0;
	$(".clickable").on("click",function() {
		if (flagClick2 == 0) {
			$("#icon_collapse2").css("transform","rotate(180deg)");
			flagClick2 = 1
		} else {
			$("#icon_collapse2").css("transform","rotate(0deg)");
			flagClick2 = 0
		}
	});
	var dataSourceResult=new kendo.data.DataSource({
		transport:{
			read:function(options){
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
							$("#total_result").text(dataSourceResult.total())
						}else{
							$("#sideItemResult").hide();
						}
						
					},
					error:function(result){
						options.error(result);
						$("#sideItemResult").hide();
					}
				});
			}
		},
		schema:{
			data:"data",
			total:"total",
			model:{
				id:"dossierLogId"
			}
		},
		pageSize:5
	});

	$("#listViewCustomer_Result_Request").kendoListView({
		dataSource: dataSourceResult,
		template:kendo.template($("#result_Request_Template").html()),
		selectable: "single",
		change: function(){
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
	dataSourceResult.read({type : 345});
	$("#sort_modified2").click(function(){
		console.log("asdsasdasdasdasdasd");
		dataSourceResult.read({type : 345, sort_modified: "modified"});
		
	})
	// option kendo-page
	$("#pagerCustomer_Result_Request").kendoPager({
		dataSource: dataSourceResult,
		input: false,
		numeric: false,
		info : false
	});
	$(".k-pager-first").css("display","none");
	$(".k-pager-last").css("display","none");
</script>