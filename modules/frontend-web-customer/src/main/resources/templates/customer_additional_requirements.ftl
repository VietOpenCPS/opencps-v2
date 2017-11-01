
<div class="panel panel-main"> 
	<div class="panel-heading row-header"> <span class="panel-title">Yêu cầu bổ sung</span>
		<span class="pull-right clickable panel-collapsed">
			<i class="glyphicon glyphicon-chevron-down"></i>
		</span>
	</div>
	<div class="panel-body P0" id="additionalRequirement">
		<ul class='ul-with-border'>
			<div id='listViewCustomer_Additional_Requirement'></div>
		</ul>
		<div class="clearfix align-middle PL10">
			<span class="text-light-gray  MR50"><i>Có <span id="total_Additional_Requirement" class="red"> </span> yêu cầu</i></span>
			<span id="pagerCustomer_Additional_Requirement" class="M0 PR5"></span>
		</div>
		<script type="text/x-kendo-template" id="additional_Requirement_Template">
			<li data-pk="#:id#" class="P10">
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
						if(result.data){
							options.success(result);
							$("#total_Additional_Requirement").text(dataSourceAdditionalRequirement.total())
						}else{
							$("#additionalRequirement").hide();
						}
					},
					error:function(result){
						options.error(result);
						$("#additionalRequirement").hide();
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
	});
	// option kendo-page
	$("#pagerCustomer_Additional_Requirement").kendoPager({
		dataSource:dataSourceAdditionalRequirement,
		input: false,
		numeric: false,
		info: false
	});
	$(".k-pager-first").css("display","none");
	$(".k-pager-last").css("display","none");
</script>
