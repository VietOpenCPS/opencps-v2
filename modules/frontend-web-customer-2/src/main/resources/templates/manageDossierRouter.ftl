<#if (Request)??>
	<#include "init.ftl">
</#if>
<#-- Routing trang Quanlyhoso -->
	<script type="text/javascript">
		var viewPanel = new kendo.View("panelTemplate", {model: modelPanel});
		var viewMainList = new kendo.View("mainTemplate", {model: modelMain});
	
		manageDossier.route("/(:dossierItemStatus)/dossiers/(:id)", function(dossierItemStatus, id){
			$("#mainType1").hide();
			$("#mainType2").show();
			$(".filterField").hide();
			if (dossierItemStatus == "new") {
				$("#mainType2").load("${ajax.customer_dossier_detail_2}&${portletNamespace}dossierId="+id+"",function(result){
				})
			} else {
				$("#mainType2").load("${ajax.customer_dossier_detail_4}&${portletNamespace}dossierId="+id+"",function(result){
				})
			};
		    $(".itemStatus").css("pointer-events","auto");
			$("#profileStatus li").removeClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
			$('#profileStatus li[dataPk='+dossierItemStatus+']').children("i").removeClass("fa fa-folder").addClass("fa fa-folder-open");
			$('#profileStatus li[dataPk='+dossierItemStatus+']').css("pointer-events","none");
			$('#profileStatus li[dataPk='+dossierItemStatus+']').addClass('active');
		});
		manageDossier.route("/taohosomoi", function(id){
			$("#mainType1").hide();
			$(".filterField").hide();
			$("#mainType2").show();
			$("#mainType2").load("${ajax.serviceconfigDKLR}",function(result){
				});
			$("#profileStatus li").removeClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
			$(".itemStatus").css("pointer-events","auto");
		});

		manageDossier.route("/(:id)", function(id) {
			$("#mainType1").show();
			$(".filterField").show();
			$("#mainType2").hide();
			layout.showIn("#main_section", viewMainList);
			if (id == "all") {
				dataSourceProfile.read({
					"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
					"serviceInfo":$("#serviceInfo").val(),
					"govAgencyCode":$("#govAgency").val(),
				});
			} else {
				dataSourceProfile.read({
					"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
					"serviceInfo":$("#serviceInfo").val(),
					"govAgencyCode":$("#govAgency").val(),
					"status":id
				});
			}   
			$(".itemStatus").css("pointer-events","auto");
			$("#profileStatus li").removeClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
			$('#profileStatus li[dataPk='+id+']').children("i").removeClass("fa fa-folder").addClass("fa fa-folder-open");
			$('#profileStatus li[dataPk='+id+']').css("pointer-events","none");
			$('#profileStatus li[dataPk='+id+']').addClass('active');
        }); 

      manageDossier.route("/taohosomoi/admin", function(){
        $("#mainType1").hide();
        $(".filterField").hide();
        $("#mainType2").show();
        $("#mainType2").load("${ajax.serviceconfig}",function(result){
            $('#btn_fillter_by_admintration').addClass('btn-active');
            $('#btn_fillter_by_domain').removeClass('btn-active');
            $('#serviceconfig_container').load("${ajax.serviceconfig_administration}");
            $('#input_search').val('');
        });
        $("#profileStatus li").removeClass('active');
        $("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
        $(".itemStatus").css("pointer-events","auto");
      });
      manageDossier.route("/taohosomoi/doman", function(){
        $("#mainType1").hide();
        $(".filterField").hide();
        $("#mainType2").show();
        $("#mainType2").load("${ajax.serviceconfig}",function(result){
            $('#btn_fillter_by_admintration').removeClass('btn-active');
            $('#btn_fillter_by_domain').addClass('btn-active');
            $('#serviceconfig_container').load("${ajax.serviceconfig_domain}");
            $('#input_search').val('');
        });
        $("#profileStatus li").removeClass('active');
        $("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
        $(".itemStatus").css("pointer-events","auto");
      });
	</script>

