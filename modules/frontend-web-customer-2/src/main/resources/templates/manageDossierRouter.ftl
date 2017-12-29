<#if (Request)??>
	<#include "init.ftl">
</#if>
<#-- Routing trang Quanlyhoso -->
	<script type="text/javascript">
		var viewPanel = new kendo.View("panelTemplate", {model: modelPanel});
		var viewMainList = new kendo.View("mainTemplate", {model: modelMain});
		// Show màn hình chi tiết hồ sơ
		manageDossier.route("/(:dossierItemStatus)/dossiers/(:id)", function(dossierItemStatus, id){
			$("#panel_list").show();
			$("#mainType1").removeClass("col-sm-12").addClass("col-sm-10");
			$("#mainType1").hide();
			$("#noInput").hide();
			$("#mainType2").show();
			$(".filterField").hide();
			if (dossierItemStatus == "new") {
				$("#mainType2").load("${ajax.customer_dossier_detail_2}&${portletNamespace}dossierId="+id+"",function(result){
				})
			} else if(dossierItemStatus == "paying"){
				$("#mainType2").load("${ajax.customer_dossier_waitpaying}&${portletNamespace}dossierId="+id+"",function(result){
				})
			} else {
				$("#mainType2").load("${ajax.customer_dossier_detail_4}&${portletNamespace}dossierId="+id+"",function(result){
				})
			};
			$('#searchCC').removeClass('active');
			$("#profileStatus li").removeClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
			$('#profileStatus li[dataPk='+dossierItemStatus+']').children("i").removeClass("fa fa-folder").addClass("fa fa-folder-open");
			$('#profileStatus li[dataPk='+dossierItemStatus+']').addClass('active');
		});
		// Show
		manageDossier.route("/dossiers/(:id)/yeucauhuy", function(id){
			$("#panel_list").show();
			$("#mainType1").removeClass("col-sm-12").addClass("col-sm-10");
			$("#mainType1").hide();
			$("#mainType2").show();
			$("#mainType2").load("${ajax.customer_dossier_detail}&${portletNamespace}dossierId="+id+"&${portletNamespace}resCancelling=true",function(result){
			})
		});
		manageDossier.route("/dossiers/(:id)/guibosung", function(id){
			$("#panel_list").show();
			$("#mainType1").removeClass("col-sm-12").addClass("col-sm-10");
			$("#mainType1").hide();
			$("#mainType2").show();
			$("#mainType2").load("${ajax.customer_dossier_detail}&${portletNamespace}dossierId="+id+"&${portletNamespace}sendAdd=true",function(result){
			})
		});
		// 
		manageDossier.route("/dossiers/(:id)/yeucaucaplai", function(id){
			$("#panel_list").show();
			$("#mainType1").removeClass("col-sm-12").addClass("col-sm-10");
			$("#mainType1").hide();
			$("#mainType2").show();
			$("#mainType2").load("${ajax.customer_dossier_detail_4}&${portletNamespace}dossierId="+id+"&${portletNamespace}sendReissue=true",function(result){
			})
		});
		// Show màn hình chọn dịch vụ công
		manageDossier.route("/taohosomoi", function(id){
			$("#mainType1").hide();
			$(".filterField").hide();
			$("#mainType2").show();
			$("#mainType2").load("${ajax.serviceconfigDKLR}",function(result){
				});
			$("#profileStatus li").removeClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
		});
		// Show danh sách hồ sơ lọc theo các trạng thái
		manageDossier.route("/(:id)", function(id) {
			$(".fa-expand").css("display","block");
			$(".fa-compress").css("display","none");
			$("#mainType1").show();
			$("#noInput").hide();
			$(".filterField").show();
			$("#mainType2").hide();
			layout.showIn("#main_section", viewMainList);
			if (id == "all") {
				dataSourceProfile.read({
					"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
					"serviceInfo":$("#serviceInfo").val(),
					"govAgencyCode":$("#govAgency").val(),
					"status": "new,receiving,processing,waiting,paying,done,cancelling,cancelled,expired"
				});
			} else if (id == "cancelling") {
				dataSourceProfile.read({
					"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
					"serviceInfo":$("#serviceInfo").val(),
					"govAgencyCode":$("#govAgency").val(),
					"state":"cancelling"
				});
			}
			else {
				dataSourceProfile.read({
					"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
					"serviceInfo":$("#serviceInfo").val(),
					"govAgencyCode":$("#govAgency").val(),
					"status":id
				});
			};   
			$("#profileStatus li").removeClass('active');
			$('#searchCC').removeClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
			$('#profileStatus li[dataPk='+id+']').children("i").removeClass("fa fa-folder").addClass("fa fa-folder-open");
			$('#profileStatus li[dataPk='+id+']').addClass('active');

    }); 
		// Show màn hình chọn dịch vụ công
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
      $('#searchCC').removeClass('active');
      $("#profileStatus li").removeClass('active');
      $("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
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
      $('#searchCC').removeClass('active');
      $("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
    });
    manageDossier.route("/keyPay/(:id)/(:refUid)", function(id,refUid){
			$("#panel_list").show();
			$("#mainType1").removeClass("col-sm-12").addClass("col-sm-10");
			$("#mainType1").hide();
			$("#mainType2").show();
			$(".filterField").hide();
			$("#mainType2").load("${ajax.notificationPaying}&dossierUUid="+id+"&paymentFileUUid="+refUid,function(result){
			});
			
			
		});
		// View file trong Thành phần hồ sơ
		// manageDossier.route("/(:dossierId)/files/(:dossierTemplateNo)/(:partNo)", function(dossierId,dossierTemplateNo,partNo){
		// 	$("#panel_list").show();
		// 	$("#mainType1").removeClass("col-sm-12").addClass("col-sm-10");
		// 	$("#mainType1").hide();
		// 	$("#mainType2").show();

		// 	$("#profileDetail").load("${ajax.customer_dossier_component_profiles}&${portletNamespace}dossierPartNo="+partNo+"&${portletNamespace}dossierId="+dossierId+"&${portletNamespace}dossierTemplateNo="+dossierTemplateNo,function(result){
		// 		$("#profileDetail").modal("show");
		// 	});
		// });
	// Show màn hình tra cứu
		manageDossier.route("/tra-cuu/tra-cuu-chung-chi", function() {
			$(".fa-expand").css("display","block");
			$(".fa-compress").css("display","none");
			$("#mainType1").show();
			$(".filterField").hide();
			$("#noInput").show();
			$("#mainType2").hide();
			resetValueFilter();
			layout.showIn("#main_section", viewMainList);
			dataSourceProfile.read({
				"status":"done"
			});
			$("#profileStatus li").removeClass('active');
			$('#searchCC').addClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
		}); 
	</script>

