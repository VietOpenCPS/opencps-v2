<#-- Routing SPA-->
<script type="text/javascript">
	var layout = new kendo.Layout("layoutTemplate");
	var manageDossier = new kendo.Router({
	    init: function() {
	        layout.render("#appManagerDossier");
	        layout.showIn("#panel_list", viewPanel);
		    layout.showIn("#main_section", viewMainList);
		    layout.showIn("#sidebar_list", viewSidebar);
	    }
	});
	manageDossier.route("/", function() {
		$("#mainType1").show();
		$("#mainType2").hide();
		resetValueFilter();
		layout.showIn("#main_section", viewMainList);
		dataSourceProfile.read({
			"serviceInfo":$("#serviceInfo").val(),
			"govAgencyCode":$("#govAgency").val(),
			"year":$("#year").val(),
			"month":$("#month").val(),
			"status":$("#keyInput").val()
		});
		$("#profileStatus li").removeClass("active");
		$("#profileStatus li>i").removeClass("fa fa-folder-open-o").addClass("fa fa-folder-o");
		$(".itemStatus").css("pointer-events","auto"); 
	});
</script>
<#-- Include Router Component -->
<#include "manageDossierRouter.ftl">


<#--  -->
<script type="text/javascript">
	$(function(){
    	// Run Routing
    	manageDossier.start();
    	// option kendo-page
		$(".k-pager-first").css("display","none");
		$(".k-pager-last").css("display","none");
		// js collapse
		var flagClick = 0;
		$(".clickable").on("click",function() {
			if (flagClick == 0) {
				$(this).children().css("transform","rotate(180deg)");
				flagClick = 1
			} else {
				$(this).children().css("transform","rotate(0deg)");
				flagClick = 0
			}
		});
    });
    var resetValueFilter = function(){
		$("#month").data("kendoComboBox").text("");
		$("#year").data("kendoComboBox").text("");
		$("#keyInput").val("");
		$("#serviceInfo").data("kendoComboBox").text("");
		$("#govAgency").data("kendoComboBox").text("");
	}
</script>