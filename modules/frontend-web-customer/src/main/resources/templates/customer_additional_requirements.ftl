<#if (Request)??>
	<#include "init.ftl">
</#if>
<div class="panel panel-main" id="sideItemAdd"> 
	<div class="panel-heading row-header"> 
		<span class="panel-title">Yêu cầu bổ sung</span>
		<span class="pull-right clickable" data-toggle="collapse" data-target="#additionalRequirement">
			<i id="icon_collapse" class="glyphicon glyphicon-chevron-up"></i>
		</span>
		<span class="pull-right MR10 text-light-gray hover-pointer" id="sort_modified" title="Sắp xếp theo ngày"><i class="fa fa-calendar" aria-hidden="true"></i></span>
	</div>
	<div class="panel-body P0 collapse in" id="additionalRequirement">
		<ul class="ul-with-border">
			<div id="listViewCustomer_Additional_Requirement"></div>
		</ul>
		<div class="clearfix align-middle PL10">
			<span class="text-light-gray MR50"><i>Có <span id="total_Additional_Requirement" class="red"> </span> yêu cầu</i></span>
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
	var flagClick = 0;
	$(".clickable").on("click",function() {
		if (flagClick == 0) {
			$("#icon_collapse").css("transform","rotate(180deg)");
			flagClick = 1
		} else {
			$("#icon_collapse").css("transform","rotate(0deg)");
			flagClick = 0
		}
	});
	var dataSideBarList = function(){
		
	}
	var dataSourceAdditionalRequirement = new kendo.data.DataSource({
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
							$("#total_Additional_Requirement").text(dataSourceAdditionalRequirement.total())
						}else{
							$("#sideItemAdd").hide();
						}
					},
					error:function(result){
						options.error(result);
						$("#sideItemAdd").hide()
					}
				});
			}

		},
		pageSize:5,
		schema:{
			data:"data",
			total:"total",
			model:{
				id:"dossierId"
			}
		}
		
	});

	$("#listViewCustomer_Additional_Requirement").kendoListView({
		dataSource: dataSourceAdditionalRequirement,
		template:kendo.template($("#additional_Requirement_Template").html()),
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
	// option kendo-page
	$("#pagerCustomer_Additional_Requirement").kendoPager({
		dataSource: dataSourceAdditionalRequirement,
		input: false,
		numeric: false,
		info: false
	});
	//
	dataSourceAdditionalRequirement.read({type : 123});
	$("#sort_modified").click(function(){
		dataSourceAdditionalRequirement.read({type : 123, sort_modified: "modified"});
	})
	//
	$(".k-pager-first").css("display","none");
	$(".k-pager-last").css("display","none");
</script>
