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
		dataSourceProfile.page(1);
		dataSourceProfile.pageSize(10);
		$("#profileStatus li").removeClass("active");
		$("#profileStatus li>i").removeClass("fa fa-folder-open-o").addClass("fa fa-folder-o");
		$(".itemStatus").css("pointer-events","auto"); 
	});
	manageDossier.route("/thongbao", function(){
            console.log("RUNNNNNNNNN");
            $("#mainType1").hide();
            $("#mainType2").show();
            $("#mainType2").load("${ajax.notification}",function(result){
            });
            $("#profileStatus li").removeClass('active');
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
    	// Option kendo-page
		$(".k-pager-first").css("display","none");
		$(".k-pager-last").css("display","none");
		// js collapse
		$(".clickable").on("click",function() {
			$(this).children().toggle();
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