<#if (Request)??>
	<#include "init.ftl">
</#if>
<#-- Routing trang Quanlyhoso -->
	<script type="text/javascript">
		var viewPanel = new kendo.View("panelTemplate", {model: modelPanel});
		var viewMainList = new kendo.View("mainTemplate", {model: modelMain});
		var viewMainList_2 = new kendo.View("mainTemplate_2", {model: modelMain});
		var statusRouteTem = {
			status : "",
			subStatus : ""
		};
		var firstLoadDataSource = true;
		// Show màn hình chi tiết hồ sơ
		manageDossier.route("/(:dossierItemStatus)/dossiers/(:id)", function(dossierItemStatus, id){
			$("#panel_list").show();
			$("#mainType1").removeClass("col-sm-12").addClass("col-sm-10");
			$("#mainType1").hide();
			$("#noInput").hide();
			$("#mainType2").show();
			$(".filterField").hide();
			layout.showIn("#main_section", viewMainList);
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

			statusRouteTem.status = dossierItemStatus;
			$('#searchCC').removeClass('active');
			getTotal(function(dossierArr){

			});
			/*$("#profileStatus li").removeClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
			$('#profileStatus li[dataPk='+dossierItemStatus+']').children("i").removeClass("fa fa-folder").addClass("fa fa-folder-open");
			$('#profileStatus li[dataPk='+dossierItemStatus+']').addClass('active');*/
		});

		manageDossier.route("/(:dossierItemStatus)/sub/(:idSub)/dossiers/(:id)", function(dossierItemStatus, idSub, id){
			$("#panel_list").show();
			$("#mainType1").removeClass("col-sm-12").addClass("col-sm-10");
			$("#mainType1").hide();
			$("#noInput").hide();
			$("#mainType2").show();
			$(".filterField").hide();
			layout.showIn("#main_section", viewMainList);
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

			statusRouteTem.status = dossierItemStatus;
			statusRouteTem.subStatus = idSub;
			$('#searchCC').removeClass('active');
			$("#profileStatus li").removeClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
			$('#profileStatus li[dataPk='+dossierItemStatus+'][dataPkSub='+idSub+']').children("i").removeClass("fa fa-folder").addClass("fa fa-folder-open");
			$('#profileStatus li[dataPk='+dossierItemStatus+'][dataPkSub='+idSub+']').addClass('active');
		});
		
		// Show
		manageDossier.route("/dossiers/(:id)/yeucauhuy", function(id){
			$("#panel_list").show();
			$("#mainType1").removeClass("col-sm-12").addClass("col-sm-10");
			$("#mainType1").hide();
			$("#mainType2").show();
			$("#mainType2").load("${ajax.customer_dossier_detail_4}&${portletNamespace}dossierId="+id+"&${portletNamespace}resCancelling=true",function(result){
			});
			getTotal(function(dossierArr){

			});
		});
		manageDossier.route("/dossiers/(:id)/guibosung", function(id){
			$("#panel_list").show();
			$("#mainType1").removeClass("col-sm-12").addClass("col-sm-10");
			$("#mainType1").hide();
			$("#mainType2").show();
			$("#mainType2").load("${ajax.customer_dossier_detail_4}&${portletNamespace}dossierId="+id+"&${portletNamespace}sendAdd=true",function(result){
			});
			getTotal(function(dossierArr){

			});
		});
		// 
		manageDossier.route("/dossiers/(:id)/yeucaucaplai", function(id){
			$("#panel_list").show();
			$("#mainType1").removeClass("col-sm-12").addClass("col-sm-10");
			$("#mainType1").hide();
			$("#mainType2").show();
			$("#mainType2").load("${ajax.customer_dossier_detail_4}&${portletNamespace}dossierId="+id+"&${portletNamespace}sendReissue=true",function(result){
			});
			getTotal(function(dossierArr){

			});
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

			if(firstLoadDataSource === true){

				getTotal(function(dossierArr){
					if (id == "all") {
						dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"serviceInfo":$("#serviceInfo").val(),
							"govAgencyCode":$("#govAgency").val(),
							"status": "new,receiving,processing,waiting,paying,done,cancelling,cancelled,expired",
							"dossierArr" : ""
						});
					} else if (id == "cancelling") {
						dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"serviceInfo":$("#serviceInfo").val(),
							"govAgencyCode":$("#govAgency").val(),
							"state":"cancelling",
							"dossierArr" : dossierArr
						});
					}else if(id == "correcting"){

						dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"serviceInfo":$("#serviceInfo").val(),
							"govAgencyCode":$("#govAgency").val(),
							"state": "correcting",
							"dossierArr" : dossierArr
						});

					}else if (id == "endorsement") {
						dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"serviceInfo":$("#serviceInfo").val(),
							"govAgencyCode":$("#govAgency").val(),
							"state": "endorsement",
							"dossierArr" : dossierArr
						});

					}else if (id == "submitting") {
						dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"serviceInfo":$("#serviceInfo").val(),
							"govAgencyCode":$("#govAgency").val(),
							"submitting" : true,
							"applicantIdNo" : "${applicant.applicantIdNo}",
							"pendding" : true
						});

					}else {
						dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"serviceInfo":$("#serviceInfo").val(),
							"govAgencyCode":$("#govAgency").val(),
							"status":id,
							"dossierArr" : dossierArr
						});
					};
				});



				/*var promise = new Promise(function(resolve, reject){
					$.ajax({
						url:"${api.server}/dossiers",
						dataType:"json",
						type:"GET",
						data:{
							"submitting": "true"
						},
						headers : {"groupId": ${groupId}},
						success:function(result){
							if(result.data){
								var data = [];
								for (var i = 0; i < result.data.length; i++) {
									data.push(result.data[i].dossierId);
								}

								resolve(data.join());
							}else {
								resolve("");
							}

						},
						error:function(result){
							reject(false);
						}
					});
				});*/


				/*promise.then(function(success){
					var dossierIds = success;
					if (id == "all") {
						dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"serviceInfo":$("#serviceInfo").val(),
							"govAgencyCode":$("#govAgency").val(),
							"status": "new,receiving,processing,waiting,paying,done,cancelling,cancelled,expired",
							"dossierArr" : dossierIds
						});
					} else if (id == "cancelling") {
						dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"serviceInfo":$("#serviceInfo").val(),
							"govAgencyCode":$("#govAgency").val(),
							"state":"cancelling",
							"dossierArr" : dossierIds
						});
					}else if(id == "correcting"){

						dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"serviceInfo":$("#serviceInfo").val(),
							"govAgencyCode":$("#govAgency").val(),
							"state": "correcting",
							"dossierArr" : dossierIds
						});

					}else if (id == "endorsement") {
						dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"serviceInfo":$("#serviceInfo").val(),
							"govAgencyCode":$("#govAgency").val(),
							"state": "endorsement",
							"dossierArr" : dossierIds
						});

					}else if (id == "submitting") {
						dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"serviceInfo":$("#serviceInfo").val(),
							"govAgencyCode":$("#govAgency").val(),
							"submitting": "true",
							"dossierArr" : dossierIds
						});

					}else {
						dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"serviceInfo":$("#serviceInfo").val(),
							"govAgencyCode":$("#govAgency").val(),
							"status":id,
							"dossierArr" : dossierIds
						});
					};

					getTotal();
				}, 
				function(error){

				});*/


				
			}
			

			
			statusRouteTem.status = id;
			$("#profileStatus li").removeClass('active');
			$('#searchCC').removeClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
			$('#profileStatus li[dataPk='+id+']').children("i").removeClass("fa fa-folder").addClass("fa fa-folder-open");
			$('#profileStatus li[dataPk='+id+']').addClass('active');
			modelMain.set("visibleHeader", $('#profileStatus li[dataPk='+id+'] .dossierStatus').text());
			modelMain.set("isInvestigated", false);

    }); 

	manageDossier.route("/(:id)/sub/(:idSub)", function(id,idSub) {
			$(".fa-expand").css("display","block");
			$(".fa-compress").css("display","none");
			$("#mainType1").show();
			$("#noInput").hide();
			$(".filterField").show();
			$("#mainType2").hide();
			layout.showIn("#main_section", viewMainList);

			if(idSub === 0){
				idSub = "";
			}

			if(firstLoadDataSource === true){
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
				}else {
					dataSourceProfile.read({
						"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
						"serviceInfo":$("#serviceInfo").val(),
						"govAgencyCode":$("#govAgency").val(),
						"status":id,
						"substatus" : idSub
					});
				};
			}
			

			
			statusRouteTem.status = id;
			statusRouteTem.subStatus = idSub;
			$("#profileStatus li").removeClass('active');
			$('#searchCC').removeClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
			$('#profileStatus li[dataPk='+id+'][dataPkSub='+idSub+']').children("i").removeClass("fa fa-folder").addClass("fa fa-folder-open");
			$('#profileStatus li[dataPk='+id+'][dataPkSub='+idSub+']').addClass('active');
			modelMain.set("visibleHeader", $('#profileStatus li[dataPk='+id+'][dataPkSub='+idSub+'] .dossierStatus').text());
			modelMain.set("isInvestigated", false);

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

    manageDossier.route("/keyPay/(:id)/(:refUid)", function(id,refUid,params){
			$("#panel_list").show();
			$("#mainType1").removeClass("col-sm-12").addClass("col-sm-10");
			$("#mainType1").hide();
			$("#mainType2").show();
			$(".filterField").hide();
			$("#mainType2").load("${ajax.notificationPaying}&${portletNamespace}dossierUUid="+id+"&${portletNamespace}paymentFileUUid="+refUid+"&${portletNamespace}trans_id="+params.trans_id+"&${portletNamespace}good_code="+params.good_code,function(result){
			});
			
			
		});
		// View file trong Thành phần hồ sơ
		manageDossier.route("/(:dossierId)/files/(:dossierTemplateNo)/(:partNo)", function(dossierId,dossierTemplateNo,partNo){
			$(".mWrapper").load("${ajax.customer_dossier_component_profiles}&${portletNamespace}dossierPartNo="+partNo+"&${portletNamespace}dossierId="+dossierId+"&${portletNamespace}dossierTemplateNo="+dossierTemplateNo,function(result){
				
			});
		});
	// Show màn hình tra cứu
		manageDossier.route("/tra-cuu/tra-cuu-ho-so", function() {
			/*$(".fa-expand").css("display","block");
			$(".fa-compress").css("display","none");
			$("#mainType1").show();
			$(".filterField").hide();
			$("#noInput").show();
			$("#mainType2").hide();
			resetValueFilter();
			layout.showIn("#main_section", viewMainList_2);
			$("#profileStatus li").removeClass('active');
			$('#searchCC').addClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");*/

			$(".fa-expand").css("display","block");
			$(".fa-compress").css("display","none");
			$("#mainType1").show();
			$("#noInput").hide();
			$(".filterField").show();
			$("#mainType2").hide();
			layout.showIn("#main_section", viewMainList);

			if(firstLoadDataSource === true){
				dataSourceProfile.read({
					"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
					"serviceInfo" : $("#serviceInfo").val(),
					"govAgencyCode" : $("#govAgency").val(),
					"status" : "all"
				});
			}

			statusRouteTem.status = "all";


			$("#profileStatus li").removeClass('active');
			$('#searchCC').removeClass('active');
			$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
			/*$('#profileStatus li[dataPk='+id+']').children("i").removeClass("fa fa-folder").addClass("fa fa-folder-open");
			$('#profileStatus li[dataPk='+id+']').addClass('active');*/
			/*modelMain.set("visibleHeader", $('#profileStatus li[dataPk='+id+'] .dossierStatus').text());*/
			modelMain.set("isInvestigated", false);
		}); 
	</script>

