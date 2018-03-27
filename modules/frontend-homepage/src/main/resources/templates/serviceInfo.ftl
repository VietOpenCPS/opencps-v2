<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="dvc12" style="width:100%">
	<div class="container-fluid form-horizontal" style="margin: 10px 0px">
		<div class="row MB10">
			<label class="col-xs-2 control-label">Đơn vị</label>
			<div class="col-xs-4">
				<select class="form-control" id="govAgencyCode">
					<option value="">Chọn đơn vị</option>
				</select>
			</div>
			<label class="col-xs-2 control-label" >Lĩnh vực</label>
			<div class="col-xs-4">
				<select class="form-control" id="domainCode">
					<option value="">Chọn lĩnh vực</option>
				</select>
			</div>
		</div>
		<div class="row MB10">
			<label class="col-xs-2 control-label">Thủ tục</label>
			<div class="col-xs-4">
				<input type="text" name="" class="form-control" placeholder="Nhập tên thủ tục" id="serviceName" />
			</div>
			<div class="col-xs-6 text-right">
				<button class="btn btn-outline-primary" type="button" onclick="filterDatasource()">Tìm kiếm</button>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12" style="color: #00000094">
				<span style="line-height: 30px">Hiển thị <span id="numPerPage"></span> trên <span id="totalItem"></span> tổng số bản ghi được tìm thấy</span>
				<span class="pull-right">Hiển thị
					<select class="show-per-page" id="slPageSize">
						<option value="10" selected>10</option>
						<option value="20">20</option>
						<option value="30">30</option>
						<option value="40">40</option>
					</select>
				</div>
			</div>
		</div>

		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th class="text-center col-md-1">STT</th>
						<th class="text-center col-xs-6">Thủ tục</th>
						<th class="text-center col-xs-3">Lĩnh vực</th>
						<th class="text-center col-xs-2">Đơn vị</th>
					</tr>
				</thead>
				<tbody id="table-body">

				</tbody>
			</table>
		</div>
		<div id="pagination">
		</div>
	</div>
	<script type="text/x-kendo-template" id="siTemplate">
		<tr>
			<td class="text-center">#= ++record #</td>
			<td>
				<a class="text-ellipsis" href="/web/cong-tiep-nhan/thu-tuc-hanh-chinh\#/#=serviceInfoId#">#=serviceName#</a>
			</td>
			<td>#=domainName#</td>
			<td>
				#=administrationName#
			</td>
		</tr>
	</script>
	<script>
		$(function() {
		var govList = {};
		var domainList = {}
		var dataSourceSI = new kendo.data.DataSource({
			transport: {
				read : function(options){
					$.ajax({
						url: "${(api)!}/serviceinfos?level=2",
						dataType: 'json',
						type: 'GET',
						headers: {
							'groupId': '55217',
						},
						success: function(result){
							for (var i = 0; i < result.data.length; i++) {
								if(result.data[i].domainCode != "" && result.data[i].domainName != "")
								{
									domainList[result.data[i].domainCode] = result.data[i].domainName;
									govList[result.data[i].administrationCode] =  result.data[i].administrationName;

								}
								var serviceConfig = result.data[i].serviceConfigs;
								// result.data[i].govAgencyName = "";
								// result.data[i].govAgencyCode = "";
								// if((typeof serviceConfig) == "undefined")
								// {

								// }
								// else if(serviceConfig.length == undefined){
								// 	result.data[i].govAgencyName = result.data[i].govAgencyName + serviceConfig.govAgencyName;
								// 	result.data[i].govAgencyCode = result.data[i].govAgencyCode + serviceConfig.govAgencyCode;
								// 	if(serviceConfig.govAgencyCode != "" && serviceConfig.govAgencyName != "")
								// 	{
								// 		govList[serviceConfig.govAgencyCode] =  serviceConfig.govAgencyName;
								// 	}
								// }else {
								// 	for (var j = 0; j < serviceConfig.length; j++)
								// 	{
								// 		if(serviceConfig.govAgencyCode != "" && serviceConfig.govAgencyName != "")
								// 		{
								// 			govList[serviceConfig[j].govAgencyCode] =  serviceConfig[j].govAgencyName;
								// 		}
								// 		if(j == (serviceConfig.length - 1))
								// 		{
								// 			result.data[i].govAgencyName = result.data[i].govAgencyName + serviceConfig[j].govAgencyName;
								// 			result.data[i].govAgencyCode = result.data[i].govAgencyCode + serviceConfig[j].govAgencyCode;
								// 		}
								// 		else {
								// 			result.data[i].govAgencyName = result.data[i].govAgencyName + serviceConfig[j].govAgencyName + ", ";
								// 			result.data[i].govAgencyCode = result.data[i].govAgencyCode + serviceConfig[j].govAgencyCode  + ", ";
								// 		}
								// 	}
								// }
							}
							options.success(result)
							if(parseInt($("#slPageSize").val()) > parseInt(dataSourceSI.total())){
								$("#numPerPage").text(dataSourceSI.total());
								$("#totalItem").text(dataSourceSI.total());
							} else {
								$("#numPerPage").text($("#slPageSize").val());
								$("#totalItem").text(dataSourceSI.total());
							}
							for(var prop in govList) {
								var option = $("<option/>")
								option.val(prop);
								option.text(govList[prop]);
        						$("#govAgencyCode").append(option);
    						}
    						for(var prop in domainList) {
								var option = $("<option/>")
								option.val(prop);
								option.text(domainList[prop]);
        						$("#domainCode").append(option);
    						}
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

		// var dataSourceSI = new kendo.data.DataSource({
		// 	transport: {
		// 		read : function(options){
		// 			$.ajax({
		// 				url: "${(api)!}/serviceconfigs?level=2",
		// 				dataType: 'json',
		// 				type: 'GET',
		// 				headers: {
		// 					'groupId': '55217',
		// 				},
		// 				success: function(result){
		// 					options.success(result)
		// 				}
		// 			})
		// 		}
		// 	},			
		// 	schema : {
		// 		data: "data",
		// 		total: "total"
		// 	},
		// 	pageSize: 10
		// })

		$("#pagination").kendoPager({
			dataSource: dataSourceSI,
			buttonCount: 3,
			messages: {
				display: ""
			}
		});

		$("#table-body").kendoListView({
			dataSource: dataSourceSI,
			template: kendo.template($("#siTemplate").html()),
			dataBinding: function() {
				record = (this.dataSource.page() -1) * this.dataSource.pageSize();
			}
		});

		$("#slPageSize").change(function(){
		 	var dataSourceCur = $("#table-body").getKendoListView().dataSource;
			if(parseInt($("#slPageSize").val()) > parseInt(dataSourceCur.total())){
				$("#numPerPage").text(dataSourceCur.total());
				$("#totalItem").text(dataSourceCur.total());
			}else {
				$("#numPerPage").text($(this).val());
				$("#totalItem").text(dataSourceCur.total());
			}
			$("#table-body").getKendoListView().dataSource.pageSize(parseInt($("#slPageSize").val()));
		});
	});
function filterDatasource(){
	var govAgencyCode = $("#govAgencyCode").val();
	var domainCode = $("#domainCode").val();
	var serviceName = $("#serviceName").val();
	var filterAdName = {field: "administrationCode",operator: "contains",value: govAgencyCode};
	var filterDName = {field: "domainCode",operator: "eq",value: domainCode};
	var filterSName = {field: "serviceName",operator: "contains",value: serviceName}
	var filters = [];
	if(govAgencyCode != null && govAgencyCode.trim() != '')
	{
		filters.push(filterAdName)
	}
	if(domainCode != null && domainCode.trim() != '')
	{
		filters.push(filterDName)
	}
	if(serviceName != null && serviceName.trim() != '')
	{
		filters.push(filterSName)
	}
	if(filters.length > 0)
	{
		$("#table-body").getKendoListView().dataSource.filter({filters});
	}
	else {
		$("#table-body").getKendoListView().dataSource.filter({});
	}
	$("#slPageSize").change();

}
</script>
