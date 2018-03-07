<#if (Request)??>
	<#include "init.ftl">
</#if>
	<script type="text/javascript">
	// Source for panel list

		var currentStateBage = 0;
		var currentStateBageFlag = 0;
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

		var fnCheckIsChangeForm = function(){
			try{
				if (arrIsChangeForm) {
					for (var i = 0; i < arrIsChangeForm.length; i++) {
						if(!arrIsChangeForm[i].isSave){
							return arrIsChangeForm[i];
						}
					}
				}


			}catch(e){
				return  null;
			}
			return null;
		}

		var fnBind
	//Source for main listview
		var dataGetTotal = new kendo.data.DataSource({
			transport:{
				read:function(options){
					$.ajax({
						// url: "http://localhost:3000/dossiers",
						url:"${api.server}/dossiers",
						dataType:"json",
						type:"GET",
						data:{
							status: "new,receiving,processing,waiting,paying,done,cancelling,cancelled,expired"
						},
						headers : {"groupId": ${groupId}},
						success:function(result){
							options.success(result);
						},
						error:function(result){
							options.error(result)
						}
					});
				}
			},
			schema : {
				data : "data",
				total : "total"
			},
			group: {field: "dossierStatus"},
		});
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

		var optBoxPageSizeDeliverables = function(){
			var totalItem = parseInt(dataSourceDeliverables.total());
			var pSize = 10;
			var arrPsize = [];
			var selectHtml = "<option class='optPageDeliverables' value='"+totalItem+"'>Tất cả</option>";
			$("#totalItem_dossierList_Deliverables").text(dataSourceDeliverables.total());
			if (totalItem <= pSize) {
				$("#itemPpageDeliverables").html("<option value='"+totalItem+"'>"+totalItem+"</option>")
			} else {
				for (var i = pSize; i < totalItem; i+=pSize) {
					arrPsize.push(i)
				};
				var sub = "";
				$.each(arrPsize,function(index,value){
					sub+="<option class='optPageDeliverables' value='"+value+"'>"+value+"</option>"
				});
				$("#itemPpageDeliverables").html(sub);
				$("#itemPpageDeliverables").append(selectHtml)
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
							
							$("#statusName").html($(".itemStatus.active .dossierStatus").text());
							
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

		var dataSourceDeliverables = new kendo.data.DataSource({
			transport:{
				read:function(options){
					if(options.data.typeSearch === "advanced"){
						options.data.applicant = "${(applicant.applicantIdNo)!}";
						console.log("has keyword");
						var deliverableTypes = $("#advanced_deliverableTypes_search").val();
						if(!deliverableTypes){
							notification.show({
								message: "Bạn phải chọn loại chứng chỉ trước khi tìm kiếm!"
							}, "success");
							options.error();
							return;
						}else {
							$.ajax({

								url : "${api.server}/deliverables/agency/BGTVTCDKVN/type/"+deliverableTypes,
								dataType : "json",
								type : "POST",
								headers : {"groupId": ${groupId}},
								data : options.data,
								success : function(result){

									if (result.total!=0) {
										var indexItem = 0;
										$(result.data).each(function(index, value){
											indexItem += 1;
											value.count = indexItem;
										});
									};
									options.success(result);

									optBoxPageSizeDeliverables();

								},
								error:function(result){
									options.error(result);
								}
							});
						}
						
					}else {
						$.ajax({

							url:"${api.server}/deliverables",
							dataType:"json",
							type:"GET",
							headers : {"groupId": ${groupId}},
							data : {
								applicant : "${(applicant.applicantIdNo)!}"
							},
							success:function(result){

								if (result.total!=0) {
									var indexItem = 0;
									$(result.data).each(function(index, value){
										indexItem += 1;
										value.count = indexItem;
									});
								};
								options.success(result);

								optBoxPageSizeDeliverables();

							},
							error:function(result){
								options.error(result);
							}
						});
					}
					
				}
			},
			page: 1,
			pageSize: 10,
			schema:{
				data:"data",
				total:"total",
				model:{
					id: "deliverableId"
				}
			}
			
		});

		var loadSoCC = function(){
			try {
				var data = dataSourceProfile.view();
				for (var i = 0; i < data.length; i++) {
					var dossierId = data[i].dossierId;
					if(dossierId){
						$.ajax({
							url : "${api.server}/dossiers/"+dossierId+"/deliverableState/"+"1",
							dataType : "json",
							type : "GET",
							headers: {"groupId": ${groupId}},
							success : function(result){
								$("#so_cc"+dossierId).text(result.so_chung_chi);
								$("#ngayki_cc"+dossierId).text(result.ngay_ky_cc);
							},
							error : function(result){

							}
						});
					}
					
				}
			}catch(e){
				return;
			}
			return;
		}
		// Get total dossierStatus
		// var statusDossierItems = ["new","receiving","processing","waiting","paying","done","cancelling","cancelled","expired","all"];
		var getTotal = function(){
			// console.log("GET total");
			// $(statusDossierItems).each(function(index,value){
			// 	getTotalItemDossier(value);
			// })
			getTotalItemDossier();
		};
		// var getTotalItemDossier = function(dossierItemStatus){
		// 	if ((dossierItemStatus == "cancelling")){
		// 		$.ajax({
		// 			// url: "http://localhost:3000/dossiers",
		// 			url:"${api.server}/dossiers",
		// 			dataType:"json",
		// 			type:"GET",
		// 			headers : {"groupId": ${groupId}},
		// 			data:{
		// 				state: "cancelling"
		// 			},
		// 			success:function(result){
		// 				$('#profileStatus li[dataPk='+dossierItemStatus+'] .bagde').html(result.total);
		// 			},
		// 			error:function(result){
		// 			}
		// 		})
		// 	} else if(dossierItemStatus == "all"){
		// 		$.ajax({
		// 			// url: "http://localhost:3000/dossiers",
		// 			url:"${api.server}/dossiers",
		// 			dataType:"json",
		// 			type:"GET",
		// 			headers : {"groupId": ${groupId}},
		// 			data:{
		// 				status:"new,receiving,processing,waiting,paying,done,cancelling,cancelled,expired"
		// 			},
		// 			success:function(result){
		// 				$('#profileStatus li[dataPk='+dossierItemStatus+'] .bagde').html(result.total);
		// 			},
		// 			error:function(result){
		// 			}
		// 		})
		// 	} else {
		// 		$.ajax({
		// 			// url: "http://localhost:3000/dossiers",
		// 			url:"${api.server}/dossiers",
		// 			dataType:"json",
		// 			type:"GET",
		// 			headers : {"groupId": ${groupId}},
		// 			data:{
		// 				status : dossierItemStatus
		// 			},
		// 			success:function(result){
		// 				$('#profileStatus li[dataPk='+dossierItemStatus+'] .bagde').html(result.total);
		// 			},
		// 			error:function(result){
		// 			}
		// 		})
		// 	}
		// }
		// -=-=-=-=-=-=-=-=-=-=-=-=-
		var getTotalItemDossier = function(){
			 var dossierStatus = $("#profileStatus > li.itemStatus.active").attr("dataPK");
			 if(!dossierStatus){
			 	dossierStatus = "all";
			 }
			$.ajax({
				// url: "http://localhost:3000/dossiers",
				url:"${api.server}/dossiers",
				dataType:"json",
				type:"GET",
				headers : {"groupId": ${groupId}},
				data:{
					state: "cancelling"
				},
				success:function(result){
					$("#profileStatus li[dataPk='cancelling'] .bagde").html(result.total);
				},
				error:function(result){
				}
			});

			$.ajax({
				// url: "http://localhost:3000/dossiers",
				url:"${api.server}/dossiers",
				dataType:"json",
				type:"GET",
				headers : {"groupId": ${groupId}},
				data:{
					status:"new,receiving,processing,waiting,paying,done,cancelling,cancelled,expired"
				},
				success:function(result){
					$("#profileStatus li[dataPk='all'] .bagde").html(result.total);
					if(dossierStatus === "all"){
						currentStateBageFlag = result.total;
					}
				},
				error:function(result){
				}
			});


			dataGetTotal.read();
			dataGetTotal.fetch(function(){
			  $('#profileStatus li > span.bagde').html("0");
			  var view = dataGetTotal.view();
			  $.each(view, function(index,value){
					$('#profileStatus li[dataPk='+value.value+'] .bagde').html(value.items.length);
					if(dossierStatus !== "all"){
						if(dossierStatus === value.value){
							currentStateBageFlag = value.items.length;
						}
					}else {
						currentStateBageFlag = 0;
					}
				});
			});
						
		}
		// -=-=-=-=-=-=-=-=-=-=-=-=-=
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

		function fileAttachmentUrl ( options) {


			var xhttp = new XMLHttpRequest();
			var a,filename;
			var data = {};

			xhttp.onreadystatechange = function() {

				if (xhttp.readyState === 4 && xhttp.status === 200) {


					var disposition = xhttp.getResponseHeader('Content-Disposition');
					if (disposition && disposition.indexOf('attachment') !== -1) {
						var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
						var matches = filenameRegex.exec(disposition);
						if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');
					}


					a = document.createElement('a');
					a.href = window.URL.createObjectURL(xhttp.response);

					var url = window.URL.createObjectURL(xhttp.response);


					options.success({url : url, status : xhttp.status});
				} else if (xhttp.readyState === 4 && xhttp.status !== 200) {
					options.error(xhttp.status);
				}

			};


			xhttp.open(options.method, options.url);
			xhttp.setRequestHeader("Content-Type", "application/json");

			if (options.hasOwnProperty("headers")){
				Object.keys( options.headers ).map(function(objectKey, index) {
					var value = options.headers[objectKey];
					xhttp.setRequestHeader(objectKey, value);
				});
			}


			if (options.hasOwnProperty("responseType")){
				xhttp.responseType = options.responseType;
			} else {
				xhttp.responseType = 'blob';
			}


			if (options.hasOwnProperty("data")){
				data = options.data;
			}


			xhttp.send(data);

		};

var fnConvertArrFile = function(arrFile){
	var arrConvert = new Object();
	if(arrFile){
		var flag = 0;
		for (var i = 0; i < arrFile.length; i++) {
			if(flag !== arrFile[i]){
				var obj = new Object()
			}
		}
	}
}

		var modelPanel = kendo.observable({
			dataServiceInfo: dataServiceInfo,
			eventLookup : function(e){
				e.preventDefault();
				$("#dossier-emp-nav-selectbox-by-dossierNo").val("");
				var statusDossier = $("li.itemStatus.active").attr("dataPk");
			    if (statusDossier == "all") {
						dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"service": $("#serviceInfo").val(),
							"keyword": $("#keyInput").val(),
							"status": "new,receiving,processing,waiting,paying,done,cancelling,cancelled,expired"
						})
			    } else if(statusDossier == "cancelling"){
			    	dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"service": $("#serviceInfo").val(),
							"keyword": $("#keyInput").val(),
							"state": "cancelling"
						})
			    } 
			    else {
						dataSourceProfile.read({
							"service": $("#serviceInfo").val(),
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
							"service": $("#serviceInfo").val(),
							"keyword": $("#keyInput").val(),
							"status": "new,receiving,processing,waiting,paying,done,cancelling,cancelled,expired"
						})
			    } 
			    else if(statusDossier == "cancelling"){
			    	dataSourceProfile.read({
							"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
							"service": $("#serviceInfo").val(),
							"keyword": $("#keyInput").val(),
							"state": "cancelling"
						})
			    }
			    	else {
						dataSourceProfile.read({
							"service": $("#serviceInfo").val(),
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
				try{

					if($("#mainType2").is(":visible")){
						if($("button.saveFormAlpaca").length > 0){
							if(arrIsChangeForm){
								var isChange = fnCheckIsChangeForm();
								if(isChange){
									var cf = confirm("Bạn vừa thay đổi dữ liệu form bạn có muốn lưu lại!");
									if(cf){
										$(".saveFormAlpaca[data-pk="+isChange.partNo+"]").trigger("click");
									}
								}else {
									manageDossier.navigate("/"+id);
								}
							}else {
								manageDossier.navigate("/"+id);
							}
						}else {
							manageDossier.navigate("/"+id);
						}
					}else {
						manageDossier.navigate("/"+id);
					} 

					console.log(arrIsChangeForm);
					
				}catch(e){

				}

				
				// modelMain.set("isInvestigated", false);
				// modelMain.set("visibleHeader", $(".itemStatus.active .dossierStatus").text());
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
				manageDossier.navigate("/tra-cuu/tra-cuu-chung-chi");
			}
		});
		
	// Model MainSection
		var loadProfile = function(){
			$(".downloadProfile").click(function(event){
				var id = $(this).attr("data-Pk");
				event.preventDefault();
				event.stopPropagation();
				event.stopImmediatePropagation();

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
			try {
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
			}catch(e){
				return;
			}
		};


		var modelMain = kendo.observable({
			dataSourceProfile : dataSourceProfile,
			dataSourceDeliverables : dataSourceDeliverables,

			// modelPanel: modelPanel,
			filterKey: modelPanel.eventLookup,
			changePageSize: function(){
				dataSourceProfile.pageSize(parseInt($("#itemPpage").val()));
				$("#pagerProfile .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"})
			},
			changePageSizeDeliverables : function(){
				dataSourceDeliverables.pageSize(parseInt($("#itemPpageDeliverables").val()));
				$("#pagerDeliverables .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"});
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
			loadDeliverableDetail : function(e){
				
				var btn = e.currentTarget;
				var dataPK = $(btn).attr('data-pk');

				$.ajax({
					method : "GET",
					url : "${api.server}/dossiers/number/headers"+dataPK,
					dataType : "json",
					headers: {"groupId": ${groupId}},
					success: function(result){
						var dossierId = result.dossierId;
						manageDossier.navigate("/done/dossiers/"+dossierId);
					},
					error: function(result){

					}
				});	

			},
			viewDeliverableFile : function(e){
				e.preventDefault();
				var deliverableCode = $(this).attr("data-pk");
				var url = "/o/rest/v2/dossierfiles/file/"+deliverableCode;
				var urlReadFile = fileAttachmentUrl({
					method : "GET",
					url : url,
					async : false,
					success: function(options){
						urlOut = options.url;
						window.open(urlOut , '_blank');

					},
					error: function(){}
				});
			},
			stylePager: function(e){
				e.preventDefault();
				$("#pagerProfile .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"});
			},
			stylePagerDeliverables : function(e){
				e.preventDefault();
				$("#pagerDeliverables .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"});
			},
			changeList: function(e){
				e.preventDefault();
				loadProfile();
				loadAddRes();
				copyProfile();
				resCancelling();
				sendAdd();
				counter();
				resDone();

				$("#pagerProfile .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"});
				$("th").css("vertical-align","top");


				loadSoCC();

			},
			filterInvestigationNo : function(e){
				e.preventDefault();
				dataSourceProfile.read({
					"keyword": $("#noInput").val(),
					"status": "done"
				})
			    
			},
			filterDelivableNo : function(e){
				/*var so_chung_chi = $("#noInputDelivable").val();
				var keyword = $("#keyInputDelivable").val();


				var queryKeyArr = new Array();
				var queryValueArr = new Array();
				var queryTypeArr = new Array();

				if(so_chung_chi){

					queryKeyArr.push('(so_chung_chi = ?)');
					queryValueArr.push('*'+so_chung_chi+'*');
					queryTypeArr.push('String');

				}

				dataSourceDeliverables.read({
					keyword : keyword,
					typeSearch : "advanced"
				});*/

			},
			filterDelivableKey : function(e){
				e.preventDefault();
				var keyword = $("#keyInputDelivable").val();
				dataSourceDeliverables.read({
					keyword : keyword
				});
			},
			fullScreen: function(e){
				e.preventDefault();
				$("#fullScreen").children().toggle();
				$("#panel_list").toggle();
				$("#mainType1").toggleClass("col-sm-10","col-sm-12");
				
			},
			searchAdvanced : function(e){
				console.log("action searchAdvanced");

				var statusDossier = $("li.itemStatus.active").attr("dataPk");
				dataSourceProfile.read({
					"status": statusDossier,
					"serviceName": $("#advanced_serviceName_search").val(),
					"companyName": $("#advanced_companyName_search").val(),
					"product": $("#advanced_product_search").val(),
					"brand": $("#advanced_brand_search").val(),
					"dossierNo" : $("#advanced_dossierNo_search").val(),
					"certificateNo" : $("#advanced_certificateNo_search").val(),
					"tradenames" : $("#advanced_tradenames_search").val(),
					"typeNo" : $("#advanced_typeNo_search").val()
				});
			},
			searchAdvancedDeliverables : function(e){
				var loai_chung_chi = $("#advanced_deliverableTypes_search").val();
				var ten_doanh_nghiep = $("#advanced_companyName_search").val();
				var so_ho_so = $("#advanced_dossierNo_search").val();
				var so_chung_chi = $("#advanced_certificateNo_search").val();
				var loai_san_pham = $("#advanced_product_search").data('kendoComboBox').text();
				var nhan_hieu = $("#advanced_brand_search").data('kendoComboBox').text();
				var ten_thuong_mai = $("#advanced_tradenames_search").val();
				var ma_kieu_loai = $("#advanced_typeNo_search").val();

				if(!loai_chung_chi && !ten_doanh_nghiep && !so_ho_so && !so_chung_chi && !loai_san_pham && !nhan_hieu && 
					!ten_thuong_mai && !ma_kieu_loai){
					dataSourceDeliverables.read();
				
					return ;
				}

				var queryKeyArr = new Array();
				var queryValueArr = new Array();
				var queryTypeArr = new Array();

				if(ten_doanh_nghiep){

					queryKeyArr.push('(ten_doanh_nghiep like ?)');
					queryValueArr.push('*'+ten_doanh_nghiep+'*');
					queryTypeArr.push('String');

				}

				if(so_ho_so){

					queryKeyArr.push('(so_ho_so = ?)');
					queryValueArr.push(so_ho_so);
					queryTypeArr.push('String');
				}

				if(so_chung_chi){

					queryKeyArr.push('(so_chung_chi = ?)');
					queryValueArr.push(so_chung_chi);
					queryTypeArr.push('String');
				}

				if(loai_san_pham){

					queryKeyArr.push('(loai_san_pham = ?)');
					queryValueArr.push(loai_san_pham);
					queryTypeArr.push('String');
				}

				if(nhan_hieu){

					queryKeyArr.push('(nhan_hieu = ?)');
					queryValueArr.push(nhan_hieu);
					queryTypeArr.push('String');
				}

				if(ten_thuong_mai){

					queryKeyArr.push('(ten_thuong_mai like ?)');
					queryValueArr.push('*'+ten_thuong_mai + '*');
					queryTypeArr.push('String');
				}

				if(ma_kieu_loai){

					queryKeyArr.push('(ma_kieu_loai like ?)');
					queryValueArr.push('*'+ma_kieu_loai + '*');
					queryTypeArr.push('String');
				}

				var query = '{ "query": "' + queryKeyArr.join(" [and] ") + '", "values": "' + queryValueArr.join("#") + '", "type": "' + queryTypeArr.join(",") + '"}';

				/*var query = '{"query": "(ten_doanh_nghiep like ?) [and] (so_ho_so = ?) [and] (so_chung_chi = ?) [and] (loai_san_pham = ?) [and] (nhan_hieu = ?) [and] (ten_thuong_mai like ?) [and] (ma_kieu_loai like ?)", "values": "*'+ten_doanh_nghiep+"*#"+so_ho_so+"#"+so_chung_chi+"#"+loai_san_pham+"#"+nhan_hieu+"#*"+ten_thuong_mai+"*#*"+ma_kieu_loai+'*", "type": "String,String,String,String,String,String,String"}';*/

				console.log(query);

				dataSourceDeliverables.read({
					keyword : query,
					typeSearch : "advanced"
				});
			},
			deliverableTypesSource : new kendo.data.DataSource({
				transport : {
					read : function(options){
						$.ajax({
							url : "${api.server}/deliverabletypes",
							dataType : "json",
							type : "GET",
							headers: {"groupId": ${groupId}},
							success : function(result){
								options.success(result);
							},
							error : function(result){
								options.error(result);
							}
						});
					}
				},
				schema : {
					data : "data",
					total : "total",
					model : {
						fields : {
							deliverableName : {
								type : "string"
							}
						}
					}
				}
			}),
			productSource : new kendo.data.DataSource({
				transport : {
					read : function(options){
						$.ajax({
							url : "${api.server}/dictcollections/VR02/dictitems?sort=sibling",
							dataType : "json",
							type : "GET",
							headers: {"groupId": ${groupId}},
							success : function(result){
								options.success(result);
							},
							error : function(result){
								options.error(result);
							}
						});
					}
				},
				schema : {
					data : "data",
					total : "total",
					model : {

					}
				}
			}),
			brandSource : new kendo.data.DataSource({
				transport : {
					read : function(options){
						$.ajax({
							url : "${api.server}/dictcollections/VR03/dictitems?sort=sibling",
							dataType : "json",
							type : "GET",
							headers: {"groupId": ${groupId}},
							success : function(result){
								options.success(result);
							},
							error : function(result){
								options.error(result);
							}
						});
					}
				},
				schema : {
					data : "data",
					total : "total",
					model : {

					}
				}
			}),
			// isInvestigated: false,
			// filterInvestigation: function(e){
			// 	e.preventDefault();
			// 	manageDossier.navigate("/tra-cuu-chung-chi");
				
			// }
			// visibleHeader: "default",
			// soChungChi: ""
		});
		

		//sau 10s refrest lai danh sach ro trang thai va danh sach tuong ung
		setInterval(function(){
			//refrest lai ro trang thai
			getTotal();

			//refrest lai danh sach ho so dang dc active
			var dossierStatus = $("#profileStatus > li.itemStatus.active").attr("dataPK");

			if(currentStateBage !== currentStateBageFlag){
				console.log("Run!!!!!!!!!!!!!!!!!!!!!");
				if (dossierStatus == "all") {
					dataSourceProfile.read({
						"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
						"service":$("#serviceInfo").val(),
						"govAgencyCode":$("#govAgency").val(),
						"status": "new,receiving,processing,waiting,paying,done,cancelling,cancelled,expired"
					});
				} else if (dossierStatus == "cancelling") {
					dataSourceProfile.read({
						"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
						"service":$("#serviceInfo").val(),
						"govAgencyCode":$("#govAgency").val(),
						"state":"cancelling"
					});
				}else {
					dataSourceProfile.read({
						"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
						"service":$("#serviceInfo").val(),
						"govAgencyCode":$("#govAgency").val(),
						"status":dossierStatus
					});
				};
				currentStateBage = currentStateBageFlag;
			} 

		}, 10000);

		var viewConfirmSaveForm = $("#confirmSaveForm");

	</script>



	