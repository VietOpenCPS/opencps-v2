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
	    }
	});
	manageDossier.route("/", function() {
		$("#mainType1").show();
		$(".filterField").show();
		$("#mainType2").hide();
		resetValueFilter();
		layout.showIn("#main_section", viewMainList);
		dataSourceProfile.read();
		// $(".itemStatus").css("pointer-events","auto");
		$("#profileStatus li").removeClass('active');
		$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
		$('#profileStatus li[dataPk= "all"]').children("i").removeClass("fa fa-folder").addClass("fa fa-folder-open");
		// $('#profileStatus li[dataPk= "all"]').css("pointer-events","none");
		$('#profileStatus li[dataPk= "all"]').addClass('active')
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
        // $(".itemStatus").css("pointer-events","auto");
    });
    manageDossier.route("/taohosomoi/chuanbihoso/(:dossierId)", function(dossierId){
	    $("#mainType1").hide();
	    $("#mainType2").show();
	    $("#mainType2").load("${ajax.customer_dossier_detail}&${portletNamespace}dossierId="+dossierId,function(result){

	    });
	  });
	  manageDossier.route("/taohosomoi/nophoso/(:dossierId)", function(dossierId){
			$("#mainType1").hide();
			$("#mainType2").show();
			$("#mainType2").load("${ajax.customer_dossier_detail_2}&${portletNamespace}dossierId="+dossierId,function(result){

			});
		});
</script>
<#-- Include Router Component -->
<#include "manageDossierRouter.ftl">

<#--  -->
<script type="text/javascript">
	$(function(){
    	// Run Routing
    	var flagSort = true;
    	manageDossier.start();
    	// 
    	getTotal();
    	// 
    	var sortItem;
		var sortType;
		var sortFieldDossier = function(selected){
			sortItem = $(selected).attr("sort");
			sortType = $(selected).attr("sort-type");
			// 
			if (sortType == "desc") {
    			dataSourceProfile.sort({ field: sortItem, dir: "desc" });
    			$(selected).attr("sort-type","asc")
    		} else {
    			dataSourceProfile.sort({ field: sortItem, dir: "asc" });
    			$(selected).attr("sort-type","desc")
    		}
		};
    	$(".fieldDossier").click(function(){
    		sortFieldDossier(this);
    		// $(this).children(".icon-sort").children().toggle();
    		$("#pagerProfile .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"});	
    	});
    });
</script>