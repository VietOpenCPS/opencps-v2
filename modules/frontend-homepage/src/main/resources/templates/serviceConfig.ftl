<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="dvc34" style="width:100%">
	<div class="panel-vanhoa">
		<div class="title">Văn hóa</div>
		<div id="scList"></div>
		<script type="text/x-kendo-template" id="scTemplate">
			<div class="lv">
				<div class="sub-title">#= record++ #. #=value#</div>
				<ul>
					# for (var i = 0; i < items.length; i++) { #
					<li><a href="/group/cong-tiep-nhan/quan-ly-ho-so?serviceConfigId=#=items[i].serviceConfigId#">#= items[i].serviceName #</a></li>
        			# } #
				</ul>
			</div>
		</script>
	</div>
</div>
<script>
	$(function() {
		var dataSourceSC = new kendo.data.DataSource({
			transport: {
				read : function(options){
					$.ajax({
						url: "${(api)!}/serviceconfigs",
						dataType: 'json',
						type: 'GET',
						headers: {
							'groupId': '55217',
						},
						success: function(result){
							options.success(result)
						}
					})
				}
			},
			schema : {
				data: "data",
				total: "total"
			},
			group: [{ field: "domainName" }]
		})

		$("#scList").kendoListView({
			dataSource: dataSourceSC,
			template: kendo.template($("#scTemplate").html()),
			dataBinding: function() {
				record = 1;
			}
		});
	});
</script>