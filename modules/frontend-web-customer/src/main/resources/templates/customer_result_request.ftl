<div class="panel panel-main MT15"> 
	<div class="panel-heading row-header"> <span class="panel-title">Yêu cầu trả kết quả</span> 
		<span class="pull-right clickable panel-collapsed"> 
			<i class="glyphicon glyphicon-chevron-down"></i> 
		</span> 
	</div> 
	<div class="panel-body PT0" id="resultRequest">	
		<ul class='ul-with-border'>
			<div id='listViewCustomer_Result_Request'></div>
		</ul>
		<div id='pagerCustomer_Result_Request'></div>
		<script type="text/x-kendo-template" id="result_Request_Template">
			<li>
				yêu cầu thanh toán lệ phí 1,300,000 <br>
				Đăng ký bản quyền <br>

				<span class="text-greyy">Cục bản quyền tác gỉa</span> <br>
				<span class="text-greyy">09:30 10-11-2017</span>
			</li>
		</script>
	</div> 
</div>

<script type="text/javascript">
	var dataSourcePaymentRequest=new kendo.data.DataSource({
		transport:{
			read:function(options){
				$.ajax({
					url:"${api.server}/dossiers/dossierlogs",
					dataType:"json",
					type:"GET",
					data:{

					},
					success:function(result){
						if(result.data){
							options.success(result);
						}else{
							$("#resultRequest").hide();
						}
						
					},
					error:function(result){
						options.error(result);
						$("#resultRequest").hide();
					}
				});
			}
		},
		schema:{
			data:"data",
			total:"total",
			model:{
				id:"dosierFileId"
			}
		},
		pageSize:5
	});

	$("#listViewCustomer_Result_Request").kendoListView({
		dataSource:dataSourcePaymentRequest,
		template:kendo.template($("#result_Request_Template").html())
		/*		autoBind:false*/
	});

	$("#pagerCustomer_Result_Request").kendoPager({
		dataSource:dataSourcePaymentRequest,
		input: false,
		numeric: false,
		info : false
	});
</script>