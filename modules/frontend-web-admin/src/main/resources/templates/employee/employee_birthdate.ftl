<#include "init.ftl">
<div class="row">

	<div class="col-sm-12">

		<div class="panel-group">

			<div class="panel panel-default">

				<div class="panel-heading">

					<h4 class="panel-title border-bottom border-color-lightblue">

						<a data-toggle="collapse" href="#employee-birthdate-collapse">

							Sinh nhật tháng <span id="this-month">0</span>

						</a>

					</h4>

				</div>

				<div id="employee-birthdate-collapse" class="panel-collapse collapse in">
					
					<div class="panel-body">

						<ul class="ul-with-border mh-head-1" id="employee-birthdate-listview"></ul>
		
						<script type="text/x-kendo-tmpl" id="employee-birthdate-listview-template">
							
							<li class="clearfix PL0">
		
								<div class="col-sm-2 PL0 PR5">
									
									<img class="img-responsive center-block img-circle" src="/o/orgopencpsfrontendadmin/images/default_avatar.png" title="" id="employee-birthdate-#=id#-thumbnil"/>
										
								</div>
									
								<div class="col-sm-10">
								
									<strong class="full-width">#= fullName #</strong><br>
									<div class="full-width">#= jobPosTitle #</div>
									<div class="full-width">SN: 
										#if(birthdate != null && birthdate != "") { #
														
											#= kendo.toString(kendo.parseDate(birthdate, 'yyyy-MM-dd'), 'dd/MM/yyyy')#

										#} else {#
											__/__/__
										#}#
									</div>
								
								</div>
									
							</li>

						</script>

						<div id="employee-birthdate-pager" class="col-sm-offset-6 k-pager-style3">
						</div>

					</div>

				</div>

			</div>

		</div>

	</div>	

</div>


<script type="text/javascript">
(function($) {

	var month = new Array();
	month[0] = "1";
	month[1] = "2";
	month[2] = "3";
	month[3] = "4";
	month[4] = "5";
	month[5] = "6";
	month[6] = "7";
	month[7] = "9";
	month[8] = "9";
	month[9] = "10";
	month[10] = "11";
	month[11] = "12";
	var thisMonth = month[(new Date()).getMonth()];

	$('#this-month').html(thisMonth);
	
	var getEmployeeBaseUrl = "${api.endpoint}/employees";
	
	var getEmployeeDataSource = new kendo.data.DataSource({
		
		transport: {
			
			read: function(options) {

				var page = options.data.page;
				var pageSize = options.data.pageSize;

				$.ajax({
				
					url: getEmployeeBaseUrl,
					dataType: "json",
					type: 'GET',
					headers: {
						"groupId": ${groupId}
					},
					data: {
						start: (page - 1)*pageSize,
						end: (page - 1)*pageSize + pageSize,
						sort: 'birthdate',
						month: thisMonth
					},
					success: function(result) {
						
						result["data"] = result.total==0 ? []: result["data"];
						options.success(result);
						
					},
					error: function(xhr, textStatus, errorThrown) {
						
						showMessageToastr("error", 'Yêu cầu của bạn xử lý thất bại!');
					
					}
				
				});
			}
		},
		schema: {
			data: "data",
			total: "total",
			model: {
				id: "employeeId",
				fields: {
					employeeId: {
						editable: false,
						nullable: true
					},
					fullName: {
						type: "string"
					},
					employeeNo: {
						type: "string"
					},
					title: {
						type: "string"
					},
					jobPosTitle: {
						type: "string"
					}

				}
			}
		},
		pageSize: 5,
		serverPaging: true,
		serverSorting: true,
		serverFiltering: true
		
	});

	$("#employee-birthdate-listview").kendoListView({
		
		dataSource: getEmployeeDataSource,

		dataBound: function (e) {
			
			if(this.dataSource.data().length == 0){
					$("#employee-birthdate-listview").append("<i class='text-center text-gray'>Không có sinh nhật trong tháng!</i>");
			} else {
			
				var employeeListview = e.sender;
				
				var children = employeeListview.element.children();
	
				var employeeBirthdateAvataBaseUrl = '${api.endpoint}/employees';
				
				for (var x = 0; x < children.length; x++) {
					
					var getObj = employeeListview.dataSource.view()[x];
	
					getImageBlob(employeeBirthdateAvataBaseUrl +"/"+ getObj.employeeId +"/photo", $("#employee-birthdate-" +getObj.employeeId+ "-thumbnil"), "${groupId}");
					
				};
			
			}

		},
		
		template: kendo.template($("#employee-birthdate-listview-template").html())

	});

	$("#employee-birthdate-pager").kendoPager({
        dataSource: getEmployeeDataSource,
        input: true,
        numeric: false,
        messages: {
            empty: "Không có kết quả phù hợp!",
            display: "",
            page: "",
            of: "",
            itemsPerPage: ""
        }
    });

})(jQuery);
</script>