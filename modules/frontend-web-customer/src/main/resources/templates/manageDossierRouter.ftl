<#if (Request)??>
	<#include "init.ftl">
</#if>
<#-- Routing trang Quanlyhoso -->
	<script type="text/javascript">
		var viewPanel = new kendo.View("panelTemplate", {model: modelPanel});
		var viewMainList = new kendo.View("mainTemplate", {model: modelMain});
		var viewSidebar = new kendo.View("sidebarTemplate", {model: modelSidebar});
		var statusRouteTem = "";
		manageDossier.route("/(:dossierItemStatus)/dossiers/(:id)", function(dossierItemStatus, id){
			$("#mainType1").hide();
			$("#mainType2").show();
			$(".filterField").hide();
			if (dossierItemStatus === "new") {
				$("#mainType2").load("${ajax.customer_dossier_detail_2}&${portletNamespace}dossierId="+id+"",function(result){
				});
			} else if(dossierItemStatus === "paying"){
				$("#mainType2").load("${ajax.customer_dossier_waitpaying}&${portletNamespace}dossierId="+id+"",function(result){
				});
			}else {
				$("#mainType2").load("${ajax.customer_dossier_detail_4}&${portletNamespace}dossierId="+id+"",function(result){
				});
			};

			statusRouteTem = dossierItemStatus;

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
			$("#mainType2").load("${ajax.serviceconfig}",function(result){
				});
			$("#profileStatus li").removeClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
			$(".itemStatus").css("pointer-events","auto");
		});

		manageDossier.route("/taohosomoi/(:id)", function(id){
			$("#mainType1").hide();
			$(".filterField").hide();
			$("#mainType2").show();
			$("#mainType2").load("${ajax.serviceconfig}&${portletNamespace}govAgencyCode="+id+"",function(result){
				});

			$("#profileStatus li").removeClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
			$(".itemStatus").css("pointer-events","auto");
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

		manageDossier.route("/(:id)", function(id) {
			$("#mainType1").show();
			$(".filterField").show();
			$("#mainType2").hide();
			layout.showIn("#main_section", viewMainList);
			if(id === "receiving"){
				dataSourceProfile.read({
					"serviceInfo":$("#serviceInfo").val(),
					"govAgencyCode":$("#govAgency").val(),
					"year":$("#year").val(),
					"month":$("#month").val(),
					"status":"receiving,releasing,processing"
				});
			}else if (id === "done") {
				dataSourceProfile.read({
					"serviceInfo":$("#serviceInfo").val(),
					"govAgencyCode":$("#govAgency").val(),
					"year":$("#year").val(),
					"month":$("#month").val(),
					"status":"done,cancelled,denied,ended"
				});
			}else {
				dataSourceProfile.read({
					"serviceInfo":$("#serviceInfo").val(),
					"govAgencyCode":$("#govAgency").val(),
					"year":$("#year").val(),
					"month":$("#month").val(),
					"status":id
				});
			}

			statusRouteTem = id;
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

      manageDossier.route("/keyPay/(:id)/(:refUid)", function(id,refUid,params){
      	$("#panel_list").show();
      	$("#mainType1").removeClass("col-sm-12").addClass("col-sm-10");
      	$("#mainType1").hide();
      	$("#mainType2").show();
      	$(".filterField").hide();
      	$("#mainType2").load("${ajax.notificationPaying}&${portletNamespace}dossierUUid="+id+"&${portletNamespace}paymentFileUUid="+refUid+"&${portletNamespace}trans_id="+params.trans_id+"&${portletNamespace}good_code="+params.good_code,function(result){
      	});
      	
      	
      });
	</script>

