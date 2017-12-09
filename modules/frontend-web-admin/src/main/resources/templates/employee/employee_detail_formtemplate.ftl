<#include "init.ftl">
<div class="row">

	<div class="col-sm-12">

		<div class="panel-group">

			<div class="panel panel-default">

				<div class="panel-heading">

					<h4 class="panel-title border-bottom border-color-lightblue">

						<a data-toggle="collapse" href="#employee_formTemplate-collapse">

							Hồ sơ nhân sự

						</a>

					</h4>

				</div>

				<div id="employee_formTemplate-collapse" class="panel-collapse collapse in">
					
					<div id="employee_form_online_container" data-pk="${(employee.employeeId)!}"></div>

				</div>

			</div>

		</div>

	</div>	

</div>

<script>

(function($) {

	var className = "${(constants.className)!}";
	var classPk = $("#employee_form_online_container").attr('data-pk');
	var getFormOnlineBaseUrl = "${api.endpoint}/onlineforms/"+className+"/" + classPk;

	function getFormTemplate () {
		
		$.ajax({
			
			url: getFormOnlineBaseUrl + "/buildform/${(constants.alpaca_templateNo)!}" ,
			type: 'GET',
			headers: {
				"groupId": ${groupId}
			},
			data: {
			},
			dataType: 'text',
			success: function(result) {
				
				if (result!=null && result!="") {
					
					var formOnline = new MobilinkFormOnline({
						container: "#employee_form_online_container",
						groupId: "${groupId}",
						className: className,
						dataPK: classPk,
						alpacaAPI: "${api.endpoint}/onlineforms",
						alpacaDataAPI: "${api.endpoint}/onlineforms"
					});
					
					formOnline.show();
				
				}
				
			},
			error: function(xhr){
				
			}
		
		});

	};
	
	getFormTemplate ();
		
})(jQuery);
</script>