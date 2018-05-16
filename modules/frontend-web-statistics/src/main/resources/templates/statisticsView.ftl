<#include "init.ftl">
<style type="text/css" media="screen">
.headerListView {
	background-color: #0b72ba !important;
	color: white !important;
	text-transform: uppercase;
}
</style>
<div class="row">
	<div class="col-md-12 form-inline MB10">
		<div class="panel panel-default">
			<div class="panel-body"><div class="form-group">
				<select class="form-control" style="width: 200px">
					<option value="0" selected="">Lọc thủ tục hành chính</option>
					<option value="1">1</option>
					<option value="2">2</option>
				</select>
				<select class="form-control" style="width: 100px">
					<option value="0" selected="">Năm</option>
					<option value="1">1</option>
					<option value="2">2</option>
				</select>
				<select class="form-control" style="width: 100px">
					<option value="0" selected="">Tháng</option>
					<option value="1">1</option>
					<option value="2">2</option>
				</select>
			</div>
		</div>
	</div>
</div>
<div class="row P0 M0">
	<div>
		<div id="groupFilter"></div>
	</div>
</div>

<div id="window"  style="margin: 0; position: static !important;">
	<!-- Window content -->
</div>

<script type="text/javascript">
	// window initializing
	var mywindow = $("#window").kendoWindow({
		actions: ["Close"],
		draggable: true,
		height: "350px",
		modal: true,
		resizable: true,
		title: "Đánh giá cán bộ",
		width: "500px",
		visible: false
	});

	

	//Open the initialized Window on button click
	function openWindow(id){
		$.ajax({
			url : "/o/rest/v2/employees/"+id+"",
			dataType : "json",
			type : "GET",
			headers : {"groupId": 55301},
			success : function(result){
				console.log(result);
				var template = kendo.template($("#detailWindow").html());
				mywindow.data("kendoWindow").content(template(result)).center().open();

				mywindow.find(".huybo").click(function () {
					mywindow.data("kendoWindow").close();
				}).end()
			},
			error : function(result){

			}
		});

	}
	$(".k-listview > div").on("mouseover", function (e) {
		console.log(e.toElement);
	});

	function postEvaluation(id, fullName){
		var value = $('input[name="test"]:checked').val();
		console.log("value = " + value);
		if (value === undefined || value === null){
			alert("Hãy chọn tiêu chí đánh giá");
		}else{
			var evaluationObject = {
				evaluationName : fullName,
				score : value
			}

			$.ajax({
				url : "/o/rest/v2/pk5/evaluation/"+id+"",
				dataType : "json",
				type : "POST",
				headers: {"groupId": ${groupId}},
				data : evaluationObject,
				success : function(result){
					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");
				},
				error : function(result){
					notification.show({
						message: "Xảy ra lỗi, xin vui lòng thử lại"
					}, "error");
				}
			});

			mywindow.data("kendoWindow").close();
			var listView = $("#listView").data("kendoListView");
			listView.dataSource.read();
		}
		
	}
</script>

<script type="text/x-kendo-template" id="proFileTemplate">
	<#-- <div class="col-md-4 MB10">
		<div id='#:workingUnitId#' class="row-header headerListView PT10 PR5 PB10 PL15"> 
			<div></div> 
		</div>
	</div> -->
	<div class="panel-group col-md-4 MB10">
		<div class="panel panel-default" >
			<div class="panel-heading headerListView PT10 PR5 PB10 PL15">#:name#</div>
			<div class="panel-body">Panel Content</div>
		</div>
	</div>

</script>

<script type="text/x-kendo-template" id="detailWindow">
	
</script>

<script type="text/javascript">

	var groupFilterdataSource = new kendo.data.DataSource ({
		transport : {
			read:function(options){
				$.ajax({
					url: "/o/rest/v2/workingunits",
					dataType:"json",
					headers : {"groupId": 55301},
					type:"GET",
					data: options.data,
					success:function(result){
						if(result.data){
							options.success(result)
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
		schema:{
			data:"data",
			total:"total"
		}
	});

	//ListView initialize
	$("#groupFilter").kendoListView({
		dataSource: groupFilterdataSource,
		template: kendo.template($("#proFileTemplate").html())
	});

	function refresh(id){
		var listView = $("#listView").data("kendoListView");

		listView.dataSource.transport.read = function(options){
			$.ajax({
				url: "/o/rest/v2/employees?workingunit="+id+"",
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
		};

		listView.dataSource.read();	
	}

</script>
