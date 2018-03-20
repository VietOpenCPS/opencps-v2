<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="dvc12">
	<div class="filters">
		<div class="row">
			<div class="col-xs-6 MB5">
				<div class="row">
					<div class="col-xs-2">Đơn vị</div>
					<div class="col-xs-10">
						<select>
							<option>Chọn đơn vị</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-6 MB5">
				<div class="row-fluid">
					<div class="col-xs-2">Lĩnh vực</div>
					<div class="col-xs-10">
						<select>
							<option>Chọn lĩnh vực</option>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 MB5">
				<div class="row">
					<div class="col-xs-2">Thủ tục</div>
					<div class="col-xs-10">
						<select>
							<option>Nhập tên thủ tục</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-xs-6 MB5 text-right">
				<button class="btn btn-outline-primary">Tìm kiếm</button>
			</div>
		</div>
	</div>
	<table class="table">
		<thead>
			<tr>
				<th>STT</th>
				<th>Thủ tục</th>
				<th>Lĩnh vực</th>
				<th>Đơn vị</th>
			</tr>
		</thead>
		<tbody id="table-body">

		</tbody>
	</table>
	<div id="pagination">
	</div>
</div>
<script type="text/x-kendo-template" id="siTemplate">
	<tr>
		<td>#= ++record #</td>
		<td>
			<div class="text-ellipsis">#=serviceName#</div>
		</td>
		<td>#=domainName#</td>
		<td>#=administrationName#</td>
	</tr>
</script>
<script>
	$(function() {
		var dataSourceSI = new kendo.data.DataSource({
			transport: {
				read : function(options){
					$.ajax({
						url: "http://103.21.148.29/o/rest/v2/serviceinfos",
						dataType: 'json',
						type: 'GET',
						headers: {
							'groupId': 55217,
						},
						success: function(result){
							console.log("OK");
							options.success(result)
						}
					})
				}
			},
			schema : {
				data: "data",
				total: "total"
			},
			pageSize: 10
		});

		$("#pagination").kendoPager({
			dataSource: dataSourceSI
		});

		$("#table-body").kendoListView({
			dataSource: dataSourceSI,
			template: kendo.template($("#siTemplate").html()),
			dataBinding: function() {
				record = (this.dataSource.page() -1) * this.dataSource.pageSize();
			}
		});
	});
</script>