<#if (Request)??>
<#include "init.ftl">
</#if>

<#-- Routing SPA-->
<script type="text/javascript">
	var layout = new kendo.Layout("layoutTemplate");
	var viewPanel = new kendo.View("panelTemplate", {model: modelPanel});
	var viewMainList = new kendo.View("mainTemplate", {model: modelMain});
	// 
	var manageDossier = new kendo.Router({
	    init: function() {
	        layout.render("#appEmployeeDossier");
	        layout.showIn("#panel_list", viewPanel);
		    layout.showIn("#main_section", viewMainList);
	    }
	});
	// 
	manageDossier.route("/", function() {
		layout.showIn("#main_section", viewMainList);
		$("#mainDossier").show();
		$("#mainDossierDetail").hide();
		// $("#listViewDossierSummary").hide();
		// $("#subInputSearch").hide();
		dataSourceProfile.read({"status": "new"});
		$(".itemStatus").css("pointer-events","auto");
		$("#profileStatus li").removeClass('active');
		$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
		$('#profileStatus li[dataPk=new]').children("i").removeClass("fa fa-folder").addClass("fa fa-folder-open");
		$('#profileStatus li[dataPk=new]').css("pointer-events","none");
		$('#profileStatus li[dataPk=new]').addClass('active');
	});
	// 
	manageDossier.route("/(:id)", function(id) {
		$("#mainDossier").show();
		$("#mainDossierDetail").hide();
		// $("#listViewDossierSummary").hide();
		// $("#subInputSearch").hide();
		layout.showIn("#main_section", viewMainList);
		dataSourceProfile.read({
			"serviceInfo":$("#serviceInfo").val(),
			"govAgencyCode":$("#govAgency").val(),
			"year":$("#year").val(),
			"month":$("#month").val(),
			"status":id
		});
		$(".itemStatus").css("pointer-events","auto");
		$("#profileStatus li").removeClass('active');
		$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
		$('#profileStatus li[dataPk='+id+']').children("i").removeClass("fa fa-folder").addClass("fa fa-folder-open");
		$('#profileStatus li[dataPk='+id+']').css("pointer-events","none");
		$('#profileStatus li[dataPk='+id+']').addClass('active');
    }); 
</script>

<#--  -->
<script type="text/javascript">
	$(function(){
    	// Run Routing
    	manageDossier.start();
    });

	$(function () {
		$("#changeLayout").click(function(){
			$(".changeLayout").toggle();
			$("#mainDossier").toggleClass("col-sm-3", "col-sm-10")
		})
	})
</script>