<#if (Request)??>
	<#include "init.ftl">
</#if>
	<script type="text/javascript">
	// Source for panel list
		var dataGovAgency = new kendo.data.DataSource({
			transport:{
				read:{
					url:"${api.server}/dictcollections/GOVERNMENT_AGENCY/dictitems",
					dataType:"json",
					type:"GET",
					headers : {"groupId": ${groupId}}
				}
			},
			schema:{
				data:"data",
				total:"total"
			}
		});
		var dataServiceInfo = new kendo.data.DataSource({
			transport:{
				read:{
					url:"${api.server}/serviceinfos",
					dataType:"json",
					type:"GET",
					headers : {"groupId": ${groupId}}
				}
			},
			schema:{
				data:"data",
				total:"total"
			}
		});
	//Source for main listview
		var optBoxPageSize = function(){
			var totalItem = parseInt(dataSourceProfile.total());
			var pSize = 10;
			var arrPsize = [];
			var selectHtml = "<option class='optPage' value='"+totalItem+"'>Tất cả</option>";
			$("#totalItem_dossierList").text(dataSourceProfile.total());
			if (totalItem <= pSize) {
				$("#itemPpage").html("<option value='"+totalItem+"'>"+totalItem+"</option>")
			} else {
				for (var i = pSize; i < totalItem; i+=pSize) {
					arrPsize.push(i)
				};
				var sub = "";
				$.each(arrPsize,function(index,value){
					sub+="<option class='optPage' value='"+value+"'>"+value+"</option>"
				});
				$("#itemPpage").html(sub);
				$("#itemPpage").append(selectHtml)
			};
		}
		var dataSourceProfile = new kendo.data.DataSource({
			transport:{
				read:function(options){
					$.ajax({
						// url: "http://localhost:3000/dossiers",
						url:"${api.server}/dossiers",
						dataType:"json",
						type:"GET",
						headers : {"groupId": ${groupId}},
						data: options.data,
						success:function(result){
							if (result.total!=0) {
								var indexItem = result.total+1;
								$(result.data).each(function(index, value){
									indexItem -= 1;
									value.count = indexItem;
								});
							};

							options.success(result);
							optBoxPageSize();
							if (result.total!=0) {
								dataSourceProfile.page(1);
								dataSourceProfile.sort({ field: "createDate", dir: "desc" });
							};
							
							//$("#statusName").html($(".itemStatus.active .dossierStatus").text());
							
							//
							$('.optPage[value="'+dataSourceProfile.pageSize()+'"]').attr("selected","selected");
							// Option kendo-page
							$(".k-pager-first").css("display","none");
							$(".k-pager-last").css("display","none");
							$("#pagerProfile .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"})
						},
						error:function(result){
							options.error(result);
						}
					});
				}
			},
			page: 1,
			pageSize: 10,
			schema:{
				data:"data",
				total:"total",
				model:{
					id: "dossierId"
				}
			},
			sort: { field: "createDate", dir: "desc" },
			
		});
		// Get total dossierStatus
		var statusDossierItems = ["new","receiving","processing","waiting","paying","done","cancelling","cancelled","expired","all"];
		var getTotal = function(){
			console.log("GET total");
			$(statusDossierItems).each(function(index,value){
				getTotalItemDossier(value);
			})
		};
		var getTotalItemDossier = function(dossierItemStatus){
			if ((dossierItemStatus == "cancelling")){
				$.ajax({
					url:"${api.server}/dossiers",
					dataType:"json",
					type:"GET",
					headers : {"groupId": ${groupId}},
					data:{
						state: "cancelling"
					},
					success:function(result){
						$('#profileStatus li[dataPk='+dossierItemStatus+'] .bagde').html(result.total);
					},
					error:function(result){
					}
				})
			} else if(dossierItemStatus == "all"){
				$.ajax({
					url:"${api.server}/dossiers",
					dataType:"json",
					type:"GET",
					headers : {"groupId": ${groupId}},
					data:{
						status:"new,receiving,processing,waiting,paying,done,cancelling,cancelled,expired"
					},
					success:function(result){
						$('#profileStatus li[dataPk='+dossierItemStatus+'] .bagde').html(result.total);
					},
					error:function(result){
					}
				})
			} else {
				$.ajax({
					url:"${api.server}/dossiers",
					dataType:"json",
					type:"GET",
					headers : {"groupId": ${groupId}},
					data:{
						status : dossierItemStatus
					},
					success:function(result){
						$('#profileStatus li[dataPk='+dossierItemStatus+'] .bagde').html(result.total);
					},
					error:function(result){
					}
				})
			}
		}
	// Source for combobox Year, Month
		var today = new Date();
	    yyyy = today.getFullYear();
	    var arrYear = [];
		for (var i = 0; i < 10; i++, yyyy--) {
		    arrYear.push({year: "Năm "+yyyy, valYear: yyyy})
		};
		var dataYear = new kendo.data.DataSource({
			data: arrYear
		});
		//
		var arrMonth = [];
		for (var i = 1; i <= 12; i++) {
		    arrMonth.push({month: "Tháng "+i, valMonth: i})
		};
		var dataMonth = new kendo.data.DataSource({
			data: arrMonth
		});

	// -=-=-=-=-=-=-=Model=-=-=-=-=-=-=-=-=-
	// Model Panel
		var resetValueFilter = function(){
			$("#dossier-emp-nav-selectbox-by-dossierNo").val("");
			$("#keyInput").val("");
			$("#serviceInfo").data("kendoComboBox").text("");
		};

		var modelPanel = kendo.observable({
			dataServiceInfo: dataServiceInfo,
			eventLookup : function(e){
				e.preventDefault();
				$("#dossier-emp-nav-selectbox-by-dossierNo").val("");
				var statusDossier = $("li.itemStatus.active").attr("dataPk");
			    if (statusDossier == "all") {
						dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"serviceInfo": $("#serviceInfo").val(),
							"keyword": $("#keyInput").val(),
							"status": "new,receiving,processing,waiting,paying,done,cancelling,cancelled,expired"
						})
			    } else if(statusDossier == "cancelling"){
			    	dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"serviceInfo": $("#serviceInfo").val(),
							"keyword": $("#keyInput").val(),
							"state": "cancelling"
						})
			    } 
			    else {
						dataSourceProfile.read({
							"serviceInfo": $("#serviceInfo").val(),
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"keyword": $("#keyInput").val(),
							"status": statusDossier
						})
			    }
			},
			filterDossierNo: function(e){
				e.preventDefault();
				$("#keyInput").val("");
				var statusDossier = $("li.itemStatus.active").attr("dataPk");
				if (statusDossier == "all") {
						dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"serviceInfo": $("#serviceInfo").val(),
							"keyword": $("#keyInput").val(),
							"status": "new,receiving,processing,waiting,paying,done,cancelling,cancelled,expired"
						})
			    } 
			    else if(statusDossier == "cancelling"){
			    	dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"serviceInfo": $("#serviceInfo").val(),
							"keyword": $("#keyInput").val(),
							"state": "cancelling"
						})
			    }
			    	else {
						dataSourceProfile.read({
							"serviceInfo": $("#serviceInfo").val(),
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"keyword": $("#keyInput").val(),
							"status": statusDossier
						})
			    }
			},
			filterStatus: function(e){
				e.preventDefault();
				$("#profileStatus li").removeClass("active");
				$(e.currentTarget).addClass("active");
				$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
				$(e.currentTarget).children("i").removeClass("fa fa-folder").addClass("fa fa-folder-open");
				$("#keyInput").val("");
				$("#dossier-emp-nav-selectbox-by-dossierNo").val("");
				//
				// dataSourceProfile.sort({ field: "submitDate", dir: "desc" }); 
				var id = $(e.currentTarget).attr("dataPk");
				manageDossier.navigate("/"+id);
				modelMain.set("isInvestigated", false);
				modelMain.set("visibleHeader", $(".itemStatus.active .dossierStatus").text());
			},
			load_serviceConfig:function(e){
				e.preventDefault();
				manageDossier.navigate("/taohosomoi");
			},
			dataBound: function() {
				$(".k-clear-value").addClass("k-hidden");
			},
			filterInvestigation: function(e){
				e.preventDefault();
				
				var id = $(e.currentTarget).attr("data-pk");
				
				var textHead = $(e.currentTarget).text().trim();
				modelMain.set("isInvestigated", true);
				modelMain.set("visibleHeader", textHead);
				modelPanel.set("investigationId", id);
				
				manageDossier.navigate("/tracuu/"+id);
				
				dataSourceProfile.read({
					"tracuu": modelPanel.get("investigationId"),
					"soChungChi": modelMain.soChungChi,
					"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
					"serviceInfo":$("#serviceInfo").val(),
					"govAgencyCode":$("#govAgency").val(),
					"status": "new,receiving,processing,waiting,paying,done,cancelling,cancelled,expired"
				});
				
			},
			investigationId: "default"
		});
		
	// Model MainSection
		var loadProfile = function(){
			$(".downloadProfile").click(function(event){
				var id = $(this).attr("data-Pk");
				event.preventDefault();
				event.stopPropagation();
				event.stopImmediatePropagation();
				// fileAttachmentDownload({
				// 	method: "GET",
				// 	url:"${api.server}/dossiers/"+id+"/download",
				// 	headers: {"groupId": ${groupId}},
				// 	success: function(sttCode){
						
				// 	},
				// 	error: function(sttCode){
						
				// 	}
				// });
				$.ajax({
					url:"${api.server}/dossiers/"+id+"/download",
					headers: {"groupId": ${groupId}},
					dataType:"json",
					type:"GET",
					success:function(res){
						
					},
					error:function(res){
						
					}
				});
			});
		};
		// 
		var resDone = function(){
			$(".resDone").click(function(event){
				var id = $(this).attr("data-Pk");
				event.preventDefault();
				event.stopPropagation();
				event.stopImmediatePropagation();
				manageDossier.navigate("/dossiers/"+id+"/yeucaucaplai");
			});
		}
		// 
		var loadAddRes = function(){
			$(".downloadAddRes").click(function(e){
				e.stopPropagation();
				var id = $(this).attr("data-Pk");
				$.ajax({
					url:"${api.server}/dossiers/"+id,
					headers: {"groupId": ${groupId}},
					dataType:"json",
					type:"GET",
					success:function(res){
						
					},
					error:function(res){
						
					}
				});
			});
		};
		var copyProfile = function(){
			$(".copyProfile").click(function(e){
				e.stopPropagation();
				var cf = confirm("Bạn có muốn sao chép hồ sơ!");
				var id = $(this).attr("data-Pk");
				if (cf) {
					$.ajax({
						url:"${api.server}/dossiers/"+id+"/cloning",
						dataType:"json",
						type:"POST",
						headers: {"groupId": ${groupId}},
						success:function(res){
							manageDossier.navigate("/taohosomoi/nophoso/"+res.dossierId);
						},
						error:function(res){
							
						}
					})
				}
			})
		};
		var resCancelling = function(){
			$(".resCancelling").click(function(e){
				e.stopPropagation();
				var id = $(this).attr("data-Pk");
				manageDossier.navigate("/dossiers/"+id+"/yeucauhuy");
			});
		};
		var sendAdd = function(){
			$(".sendAdd").click(function(e){
				e.stopPropagation();
				var id = $(this).attr("data-Pk");
				manageDossier.navigate("/dossiers/"+id+"/guibosung");
			});
		};
		var counter = function(){
			if ($("#listViewDossier").data("kendoListView").dataSource.total()!=0) {
				var count = $("#listViewDossier").data("kendoListView").dataSource.currentRangeStart();
				var countLast = $("#listViewDossier").data("kendoListView").dataSource.view().length+count;
				var arrCount = [];
				for (var i = count; i < countLast; i++) {
					arrCount.push(i+1)
				};
				$(".count").each(function(index,value){
					$(value).html(arrCount[index])
				})
			}		
		};
		var modelMain = kendo.observable({
			dataSourceProfile : dataSourceProfile,
			// modelPanel: modelPanel,
			filterKey: modelPanel.eventLookup,
			changePageSize: function(){
				dataSourceProfile.pageSize(parseInt($("#itemPpage").val()));
				$("#pagerProfile .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"})
			},
			loadDossierDetail:function(e){
				e.preventDefault();
				var dossierItemStatus = e.data.dossierStatus;
				var cancellingDateDossier = e.data.cancellingDate;
				var id = e.data.dossierId;
				//
				if (cancellingDateDossier != null && dossierItemStatus != "cancelled") {
					manageDossier.navigate("/cancelling/dossiers/"+id);
				} else {
					manageDossier.navigate("/"+dossierItemStatus+"/dossiers/"+id);	
				}
				// 
			},
			stylePager: function(e){
				e.preventDefault();
				$("#pagerProfile .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"})
			},
			changeList: function(e){
				e.preventDefault();
				loadProfile();
				loadAddRes();
				copyProfile();
				resCancelling();
				sendAdd();
				counter();
				$("#pagerProfile .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"});
				$("th").css("vertical-align","top");
			},
			fullScreen: function(e){
				e.preventDefault();
				$("#fullScreen").children().toggle();
				$("#panel_list").toggle();
				$("#mainType1").toggleClass("col-sm-10","col-sm-12");
				
			},
			isInvestigated: false,
			filterInvestigation: function(e){
				e.preventDefault();
				
				dataSourceProfile.read({
					"tracuu": modelPanel.get("investigationId"),
					"soChungChi": modelMain.soChungChi,
					"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
					"serviceInfo":$("#serviceInfo").val(),
					"govAgencyCode":$("#govAgency").val(),
					"status": "new,receiving,processing,waiting,paying,done,cancelling,cancelled,expired"
				});
				
			},
			visibleHeader: "default",
			soChungChi: ""
		});

	</script>



	