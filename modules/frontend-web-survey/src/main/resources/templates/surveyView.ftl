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
				<div id="groupFilterStatus" class="accordion-body collapse in">
					<div class="accordion-inner">
						<ul id="profileStatus" class="ul-default ul-with-left-icon icon-folder have-bagde">

						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<#--  -->
	<div class="col-sm-9">
		<div class="box">
			<div class="row">
				<div class="col-sm-11 MT15 ML25">
					<b>Mức độ thường xuyên cập nhật thông tin của bạn</b>
				</div>
				<div class="col-sm-7 ML25">				
					<div class="radio MB20"> <input type="radio" name="test"> <label>Rất thường xuyên</label> </div>
					<div class="radio MB20"> <input type="radio" name="test"> <label>Thường xuyên</label> </div>
					<div class="radio MB20"> <input type="radio" name="test"> <label>Không hay cập nhật</label> </div>
				</div>
				<div class="col-sm-4">
					<ul>
						<li>
							<div class="progress">
								<div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="70"
								aria-valuemin="0" aria-valuemax="100" style="width:70%">70%</div>
							</div>
						</li>
						<li>
							<div class="progress">
								<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="45"
								aria-valuemin="0" aria-valuemax="100" style="width:45%">45%</div>
							</div>
						</li>
						<li>
							<div class="progress">
								<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="30"
								aria-valuemin="0" aria-valuemax="100" style="width:30%">30%</div>
							</div>
						</li>
					</ul>
				</div>

				<div class="col-sm-11 MT15 ML25">
					<b>Quý khách hài lòng về nơi tiếp đón của Bộ phận tiếp nhận và trả kết quả không</b>
				</div>
				<div class="col-sm-7 ML25">					
					<div class="radio MB20"> <input type="radio" name="test1"> <label>Rất thường xuyên</label> </div>
					<div class="radio MB20"> <input type="radio" name="test1"> <label>Thường xuyên</label> </div>
					<div class="radio MB20"> <input type="radio" name="test1"> <label>Không hay cập nhật</label> </div>
					<div class="radio MB20"> <input type="radio" name="test1"> <label>Không hay cập nhật</label> </div>
				</div>
				<div class="col-sm-4">
					<ul>
						<li>
							<div class="progress">
								<div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="70"
								aria-valuemin="0" aria-valuemax="100" style="width:70%">70%</div>
							</div>
						</li>
						<li>
							<div class="progress">
								<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="45"
								aria-valuemin="0" aria-valuemax="100" style="width:45%">45%</div>
							</div>
						</li>
						<li>
							<div class="progress">
								<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="30"
								aria-valuemin="0" aria-valuemax="100" style="width:30%">30%</div>
							</div>
						</li>
						<li>
							<div class="progress">
								<div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="30"
								aria-valuemin="0" aria-valuemax="100" style="width:20%">20%</div>
							</div>
						</li>
					</ul>
				</div>
				<div class="col-sm-11 MT15 ML25">
					<b>Ý kiến khác của bạn về đơn vị là gì ?</b>
					<textarea class="MT20 MB20 form-control custom-control" rows="3" style="resize:none"></textarea>
					<button class="MB20 btn btn-small">Gửi đánh giá</button>    
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/x-kendo-template" id="proFileTemplate">
	<li id='#:workingUnitId#' class='itemStatus'" onclick="refresh(this.id);">
		<i class='fa fa-university icon-left' aria-hidden='true'></i>  
		<span class="hover-pointer text-hover-blue dossierStatus">#:name#</span>
	</li>
</script>

<script type="text/javascript" >
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
	$("#profileStatus").kendoListView({
		dataSource: groupFilterdataSource,
		template: kendo.template($("#proFileTemplate").html())
	});

	function refresh(id){
		var listView = $("#listView").data("kendoListView");

		// listView.dataSource.transport.read = function(options){
		// 	$.ajax({
		// 		url: "/o/rest/v2/employees?workingunit="+id+"",
		// 		dataType:"json",
		// 		headers : {"groupId": 55301},
		// 		type:"GET",
		// 		data: options.data,
		// 		success:function(result){
		// 			if(result.data){
		// 				options.success(result);
		// 			}else{
		// 				options.success({
		// 					"total":0,
		// 					"data":[]
		// 				})
		// 			}
		// 		},
		// 		error:function(result){
		// 			options.error(result);
		// 		}
		// 	});
		// };

		listView.dataSource.read();	
	}

</script>
