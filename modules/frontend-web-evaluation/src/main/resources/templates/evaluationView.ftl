<#include "init.ftl">

<div class="row">
	<div class="col-sm-2 MB10">
		<div class="accordion">
			<div class="accordion-group">
				<div class="accordion-heading" style="background-color: #14bef0;border: none;font-family: 'Roboto-Regular'">
					<a class="" style="color: #ffffff" data-toggle="collapse" href="#groupFilterStatus">
						CƠ QUAN CHUYÊN MÔN
					</a>
				</div>
				<div id="groupFilter" class="accordion-body collapse in">
					
				</div>
			</div>
		</div>
	</div>

	<div class="col-sm-10">
		<div class="demo-section k-content wide">
			<div class="box" id="listView"></div>
			<div id="pager" class="k-pager-wrap"></div>
		</div>
	</div>

</div>
<#include "evaluationMainList.ftl">

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
	<div>
		<ul id="profileStatus" class="ul-default ul-with-left-icon ML20">
			<li id='#:workingUnitId#' class='itemStatus'" onclick="refresh(this.id);">
				<i class='fa fa-university icon-left' aria-hidden='true'></i>  
				<span class="hover-pointer text-hover-blue dossierStatus">#:name#</span>
			</li>
		</ul>
	</div>
</script>

<script type="text/x-kendo-template" id="detailWindow">
	<div class="row M0">
		<div class="col-xs-4">
			<img style="width: 140px;" src="/documents/55217/0/2018-05-08_10-24-33+%281%29.png/8415dbc6-4c6c-f6cc-0eaa-6efe8974736d?version=1.0&amp;t=1525750870038">
		</div>
		<div class="col-xs-8">
			<p class="PB10 text-bold">#=fullName #</p>

			<p>Ngày Sinh: #=fullName #</p>
			<p>Số điện thoại: #=telNo #</p>
			<p>Chức vụ: #=title #</p>
			<p>Đơn vị: #=workingUnitName #</p>
			<p>Email: #=email #</p>
		</div>
	</div>
	<div class="row PT15 M0">
		<div class="radio-inline"> <input type="radio" name="test" value="1"> <label>Rất hài lòng</label> </div>
		<div class="radio-inline ML65"> <input type="radio" name="test" value="2"> <label>Hài lòng</label> </div>
		<div class="radio-inline ML65"> <input type="radio" name="test" value="3"> <label>Không hài lòng</label> </div>
	</div>
	<div class="row M0 PT10">
		<div class="form-group"> <input type="text" class="form-control" placeholder="Nhập địa chỉ email"> </div>
	</div>
	<div class="row M0">
		<button class="btn btn-active" onclick="postEvaluation(#=employeeId #,'#=fullName #');">Gửi đánh giá</button>
		<button class="btn btn-border-color huybo">Hủy bỏ</button>
	</div>
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
