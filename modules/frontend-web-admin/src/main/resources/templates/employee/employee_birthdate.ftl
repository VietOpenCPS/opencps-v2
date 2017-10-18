<#if (Request)??>
<#include "init.ftl">
</#if>
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
		
								<div class="col-sm-3 PL0 PR5">
									
									<img class="img-responsive center-block img-circle" src="/o/frontend-web-usermgt/images/user_01.png" title="" id="employee-birthdate-#=id#-thumbnil"/>
										
								</div>
									
								<div class="col-sm-9">
								
									<strong class="full-width">#= fullName #</strong><br>
									<span class="full-width">#= jobPosTitle #</span><br>
									<span class="full-width">SN: 
										#if(birthdate != null && birthdate != "") { #
														
											#= kendo.toString(kendo.parseDate(birthdate, 'yyyy-MM-dd'), 'MM/dd/yyyy')#

										#}#
									</span>
								
								</div>
									
							</li>

						</script>

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
	
	var getEmployeeBaseUrl = "${api.server}/employees";
	
	var getEmployeeDataSource = new kendo.data.DataSource({
		
		transport: {
			
			read: function(options) {
				
				$.ajax({
				
					url: getEmployeeBaseUrl,
					dataType: "json",
					type: 'GET',
					headers: {
						"groupId": ${groupId}
					},
					data: {
						sort: 'birthdate',
						month: thisMonth
					},
					success: function(result) {
						
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
		}
		
	});

	$("#employee-birthdate-listview").kendoListView({
		
		dataSource: getEmployeeDataSource,

		dataBound: function (e) {

			var employeeListview = e.sender;
			
			var children = employeeListview.element.children();

			var employeeBirthdateAvataBaseUrl = '${api.server}/employees';
			
			for (var x = 0; x < children.length; x++) {
				
				var getObj = employeeListview.dataSource.view()[x];

				getImageBlob(employeeBirthdateAvataBaseUrl +"/"+ getObj.employeeId +"/photo", $("#employee-birthdate-" +getObj.employeeId+ "-thumbnil"));
				
			};

		},
		
		template: kendo.template($("#employee-birthdate-listview-template").html())

	});


})(jQuery);
</script>