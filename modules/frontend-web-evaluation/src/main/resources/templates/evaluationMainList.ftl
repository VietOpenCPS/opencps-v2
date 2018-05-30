<#if (Request)??>
<#include "init.ftl">
</#if>
<script type="text/x-kendo-template" id="template">

	<div class="row M0 PL15 PR15 employeeId" data-pk="#=employeeId#">
		<div class="col-sm-12 P0" style="border-bottom: 1px solid \\#ddd">
			<div class="col-sm-8 P0">
				<div class="col-sm-3">
					<img class="MT15 ML20" style="width: 110px;" src="/documents/55217/0/2018-05-08_10-24-33+%281%29.png/8415dbc6-4c6c-f6cc-0eaa-6efe8974736d?version=1.0&amp;t=1525750870038" alt="" />
				</div>
				<div class="col-sm-9" >
					<p class="MB20 MT15" style="font-weight: 600;">#=fullName#</p>
					<p>#=birthdate# | #=jobPosTitle#</p>
					<p>#=workingUnitName#</p>	
				</div>
			</div>
			<div class="col-sm-4 MT15 MB15" style="border-left: 1px solid \\#ddd">

				#
					function countItemForScore(resultArray, score) {
					   	var i, count = 0;
					   	for (i=0; i < resultArray.length; i++){
					   		if (resultArray[i].score == score) {
					         	count++;
					      	}
					   	}
					   return count;
					}
					var i, total = 0, ratHaiLong = 0, haiLong = 0, khongHaiLong = 0;
					var percentRatHaiLong = 0, percentHaiLong = 0, percentKhongHaiLong = 0;

					$.ajax({
						url : "/o/rest/v2/pk5/evaluation/"+employeeId+"",
						dataType : "json",
						type : "GET",
						headers : {"groupId": 55301},
						success : function(result){
							if(result.data){
								total = result.total;

								ratHaiLong = countItemForScore(result.data, "1");
								haiLong = countItemForScore(result.data, "2");
								khongHaiLong = countItemForScore(result.data, "3");

								percentRatHaiLong = (ratHaiLong/total)*100;
								percentHaiLong = haiLong/total*100;
								percentKhongHaiLong = khongHaiLong/total*100;
							}

							$("\\#total"+employeeId).html("");
							$("\\#total"+employeeId).append('Tổng số lượt đánh giá: <b>'+total+'</b>');

							$("\\#ratHaiLong"+employeeId).html("");
							$("\\#ratHaiLong"+employeeId).append('<div class="col-xs-5"><span>Rất hài lòng \('+ratHaiLong+'\)</span></div><div class="progress"><div class="progress-bar progress-bar-danger col-xs-7 P0" role="progressbar" aria-valuenow="'+percentRatHaiLong+'" aria-valuemin="0" aria-valuemax="100" style="width:'+percentRatHaiLong+'%">'+percentRatHaiLong+'%<span id="abc"></span></div></div>');

							$("\\#haiLong"+employeeId).html("");
							$("\\#haiLong"+employeeId).append('<div class="col-xs-5"><span>Hài lòng \('+haiLong+'\)</span></div><div class="progress" ><div class="progress-bar progress-bar-warning col-xs-7 P0" role="progressbar" aria-valuenow="'+percentHaiLong+'" aria-valuemin="0" aria-valuemax="100" style="width:'+percentHaiLong+'%">'+percentHaiLong+'%<span id="abc"></span></div></div>');

							$("\\#khongHaiLong"+employeeId).html("");
							$("\\#khongHaiLong"+employeeId).append('<div class="col-xs-5"><span>Không hài lòng \('+khongHaiLong+'\)</span></div><div class="progress" ><div class="progress-bar progress-bar-success col-xs-7 P0" role="progressbar" aria-valuenow="'+percentKhongHaiLong+'" aria-valuemin="0" aria-valuemax="100" style="width:'+percentKhongHaiLong+'%">'+percentKhongHaiLong+'%<span id="abc"></span></div></div>');
						},
						error : function(result){

						}
					});
				#
				<p class="MB15" id="total#:employeeId#"></p>
				<ul id="evaluation-bar"> 
					<li class="row" id="ratHaiLong#:employeeId#">

					</li>
					<li class="row" id="haiLong#:employeeId#">

					</li>
					<li class="row" id="khongHaiLong#:employeeId#">
						
					</li>
				</ul>						
			</div>
			<hr>
		</div>
	</div>
</script>

<script>
	$(document).ready(function(){
		var dataSource = new kendo.data.DataSource({
			transport : {
				read:function(options){
					$.ajax({
						url: "/o/rest/v2/employees",
						dataType:"json",
						headers : {"groupId": 55301},
						type:"GET",
						data: options.data,
						success:function(result){
							if(result.data){
								options.success(result);

							}else{
								options.success({
									"total":0,
									"data":[]
								})
							}
						},
						error:function(result){
							options.error(result);
						}
					});
				}
			},
			pageSize: 5,
			schema:{
				data:"data",
				total:"total"
			}
		});

		$("#pager").kendoPager({
			dataSource: dataSource,
			messages: {
	        	display: "Hiển thị {0}-{1} trên {2} bản ghi",
	        	empty: "Không có dữ liệu",
	      	}
		});

		$("#listView").kendoListView({
			dataSource: dataSource,
			template: kendo.template($("#template").html()),
			change: function(e){
		        var index = this.select().index(),
            		dataItem = this.dataSource.view()[index];
        		console.log("employeeId = "+ dataItem.employeeId);
				openWindow(dataItem.employeeId);
				
			},
			selectable: "single"
		});

	});
</script>

