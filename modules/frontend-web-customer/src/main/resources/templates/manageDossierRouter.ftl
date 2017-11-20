<#-- Routing trang Quanlyhoso -->
	<script type="text/javascript">
		var viewPanel = new kendo.View("panelTemplate", {model: modelPanel});
		var viewMainList = new kendo.View("mainTemplate", {model: modelMain});
		var viewSidebar = new kendo.View("sidebarTemplate", {model: modelSidebar});
	
		manageDossier.route("/(:dossierItemStatus)/dossiers/(:id)", function(dossierItemStatus, id){
			$("#mainType1").hide();
			$("#mainType2").show();
			$("#mainType2").load("${ajax.customer_dossier_detail}?id="+id+"",function(result){
				});
		    $(".itemStatus").css("pointer-events","auto");
			$("#profileStatus li").removeClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open-o").addClass("fa fa-folder-o");
			$('#profileStatus li[dataPk='+dossierItemStatus+']').children("i").removeClass("fa fa-folder-o").addClass("fa fa-folder-open-o");
			$('#profileStatus li[dataPk='+dossierItemStatus+']').css("pointer-events","none");
			$('#profileStatus li[dataPk='+dossierItemStatus+']').addClass('active');
		});
		manageDossier.route("/taohosomoi", function(id){
			$("#mainType1").hide();
			$("#mainType2").show();
			$("#mainType2").load("${ajax.serviceconfig}",function(result){
				});
			$("#profileStatus li").removeClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open-o").addClass("fa fa-folder-o");
			$(".itemStatus").css("pointer-events","auto");
		});

		manageDossier.route("/(:id)", function(id) {
			$("#mainType1").show();
			$("#mainType2").hide();
			// $("#wrapMain").hide();
			// setTimeout(function(){$("#wrapMain").show()},300);
			layout.showIn("#main_section", viewMainList);          
			dataSourceProfile.read({
				"serviceInfo":$("#serviceInfo").val(),
				"govAgencyCode":$("#govAgency").val(),
				"year":$("#year").val(),
				"month":$("#month").val(),
				"status":id
			});
			dataSourceProfile.page(1);
			dataSourceProfile.pageSize(10);
			$(".itemStatus").css("pointer-events","auto");
			$("#profileStatus li").removeClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open-o").addClass("fa fa-folder-o");
			$('#profileStatus li[dataPk='+id+']').children("i").removeClass("fa fa-folder-o").addClass("fa fa-folder-open-o");
			$('#profileStatus li[dataPk='+id+']').css("pointer-events","none");
			$('#profileStatus li[dataPk='+id+']').addClass('active');
        }); 
	</script>