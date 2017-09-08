
<div class="panel panel-main"> 
	<div class="panel-heading"> <h3 class="panel-title">Yêu cầu bổ sung</h3>
		<span class="pull-right clickable panel-collapsed">
			<i class="glyphicon glyphicon-chevron-down"></i>
		</span>
	</div>
	<div class="panel-body">
		<ul class='ul-with-border'>
			<div id='listViewCustomer_Additional_Requirement'></div>
		</ul>
		<div id='pagerCustomer_Additional_Requirement'></div>
		<script type="text/x-kendo-template" id="additional_Requirement_Template">
			<li data-pk="#:id#">
				<p>#:content#</p>
				<span class="text-greyy">#:govAgencyName#</span> <br>
				<span class="text-greyy">#:createDate#</span>
			</li>
		</script>
	</div>
</div>

<script type="text/javascript">

	$(document).on('click', '.panel-heading span.clickable', function(e) {
		var $this = $(this);
		if (!$this.hasClass('panel-collapsed')) {
			$this.parents('.panel').find('.panel-body').slideUp();
			$this.addClass('panel-collapsed');
			$this.find('i').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
		} else {
			$this.parents('.panel').find('.panel-body').slideDown();
			$this.removeClass('panel-collapsed');
			$this.find('i').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
		}
	});

	var dataSourceAdditionalRequirement=new kendo.data.DataSource({
		transport:{
			read:function(options){
				$.ajax({
					url:"${api.server}/dossiers/dossierlogs",
					dataType:"json",
					type:"GET",
					data:{

					},
					success:function(result){
						options.success(result);
					},
					error:function(result){
						options.error(result);
					}
				});
			}

		},
		pageSize:5,
		schema:{
			data:"data",
			total:"total",
			model:{
				id:"dossierLogId"
			}
		}
	});

	$("#listViewCustomer_Additional_Requirement").kendoListView({
		dataSource:dataSourceAdditionalRequirement,
		template:kendo.template($("#additional_Requirement_Template").html())
		/*		autoBind:false*/
	});

	$("#pagerCustomer_Additional_Requirement").kendoPager({
		dataSource:dataSourceAdditionalRequirement,
		input: true,
		numeric: false,
		messages: {
			empty: "Không có kết quả phù hợp!",
			display: "Hiển thị {0}-{1} trong {2} bản ghi",
			page: "",
			of: "/ {0}"
		}
	});
</script>
