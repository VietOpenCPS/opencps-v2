<script type="text/javascript">
	
	var manageRegistrations = new kendo.Router({
		
	});
	
	// Show màn hình thông báo chi tiết
	manageRegistrations.route("/registration/(:id)", function(id){
		
		//load registration detail
		$("#registrations_detail").load("${ajax.registrations_detail}&${portletNamespace}registrationId="+id,function(result){

		});
    });

	manageRegistrations.start();
</script>

