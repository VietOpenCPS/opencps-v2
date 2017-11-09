<#-- Layout and View -->
	<script type="text/javascript">
		var layout = new kendo.Layout("layoutTemplate");
		var viewPanel = new kendo.View("panelTemplate", {model: modelPanel});
		var viewMainList = new kendo.View("mainTemplate", {model: modelMain});
		var viewSidebar = new kendo.View("sidebarTemplate", {model: modelSidebar});
		var viewDetailDossier = new kendo.View("dossier_detailTemp", {model: modelDossierDetail});
		var viewServiceConfig = new kendo.View("serviceconfigTemp", {model: modelDossierDetail})
	</script>
	<#-- Routing -->
	<script type="text/javascript">
		var managerDossier = new kendo.Router({
		    init: function() {
		        layout.render("#appManagerDossier");
		        layout.showIn("#panel_list", viewPanel);
			    layout.showIn("#main_section", viewMainList);
			    layout.showIn("#sidebar_list", viewSidebar);
		    }
		});
		managerDossier.route("/", function() {
			layout.showIn("#main_section", viewMainList);  
		});
		managerDossier.route("/dossierStatus(:dossierItemStatus)/detailDossier(:id)", function(dossierItemStatus, id){
			layout.showIn("#main_section", viewDetailDossier);
		    dataDossierDetail.read({"id": id});
		    $(".itemStatus").css("pointer-events","auto");
			$("#profileStatus li").removeClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open-o").addClass("fa fa-folder-o");
			$('#profileStatus li[dataPk='+dossierItemStatus+']').children("i").removeClass("fa fa-folder-o").addClass("fa fa-folder-open-o");
			$('#profileStatus li[dataPk='+dossierItemStatus+']').css("pointer-events","none");
			$('#profileStatus li[dataPk='+dossierItemStatus+']').addClass('active');
		});
		managerDossier.route("/service", function(id){
			layout.showIn("#main_section", viewServiceConfig);
		    dataLoadServiceConfig.read()
		});

		managerDossier.route("/:id", function(id) {
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
			$("#profileStatus li>i").removeClass("fa fa-folder-open-o").addClass("fa fa-folder-o");
			$('#profileStatus li[dataPk='+id+']').children("i").removeClass("fa fa-folder-o").addClass("fa fa-folder-open-o");
			$('#profileStatus li[dataPk='+id+']').css("pointer-events","none");
			$('#profileStatus li[dataPk='+id+']').addClass('active');
        });

        $(function(){
        	managerDossier.start();
        	// option kendo-page
			$(".k-pager-first").css("display","none");
			$(".k-pager-last").css("display","none");
			// js collapse
			var flagClick = 0;
			$(".clickable").on("click",function() {
				if (flagClick == 0) {
					$("#icon_collapse").css("transform","rotate(180deg)");
					flagClick = 1
				} else {
					$("#icon_collapse").css("transform","rotate(0deg)");
					flagClick = 0
				}
			});
        })
        
	</script>