<#if (Request)??>
	<#include "init.ftl">
</#if>
<#-- Routing SPA-->
<script type="text/javascript">
	var layout = new kendo.Layout("layoutTemplate");
	var manageDossier = new kendo.Router({
	    init: function() {
	        layout.render("#appManagerDossier");
	        layout.showIn("#panel_list", viewPanel);
		    layout.showIn("#main_section", viewMainList);
		    /*layout.showIn("#sidebar_list", viewSidebar);*/
	    }
	});
	manageDossier.route("/", function() {
		$("#mainType1").show();
		$(".filterField").show();
		$("#mainType2").hide();
		resetValueFilter();
		layout.showIn("#main_section", viewMainList);
		dataSourceProfile.read({"status": "new"});
		$(".itemStatus").css("pointer-events","auto");
		$("#profileStatus li").removeClass('active');
		$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
		$('#profileStatus li[dataPk=new]').children("i").removeClass("fa fa-folder").addClass("fa fa-folder-open");
		$('#profileStatus li[dataPk=new]').css("pointer-events","none");
		$('#profileStatus li[dataPk=new]').addClass('active');
	});
	manageDossier.route("/thongbao", function(){
        $("#mainType1").hide();
        $(".filterField").hide();
        $("#mainType2").show();
        $("#mainType2").load("${ajax.notification}",function(result){
        	// dataSourceNotify2.read();
        });
        $("#profileStatus li").removeClass('active');
        $("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
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
    });
</script>