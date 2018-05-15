<#if (Request)??>
	<#include "init.ftl">
</#if>
	<div id="confirm" name="confirm">
		
	</div>
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

		var fnConfirm = function(title, message, okText, cancelText, okCallBack, cancelCallBack){

			var cf = $("#confirm").kendoDialog({
				width: "400px",
				title: title,
				closable: true,
				modal: false,
				content: "<p>"+message+"?<p>",
				actions: [
				{ text: cancelText, action: cancelCallBack },
				{ text: okText, primary: true, action: okCallBack }
				]
			}).data("kendoDialog");

			return cf;
		};
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
					if (options.data.year=="") {
						options.data.year = 0;
					};
					if (options.data.month=="") {
						options.data.month = 0;
					};
					$.ajax({
						// url: "http://localhost:3000/dossiers",
						url:"${api.server}/dossiers",
						dataType:"json",
						type:"GET",
						headers : {"groupId": ${groupId}},
						data:{
							service: options.data.serviceInfo,
							agency: options.data.govAgencyCode,
							keyword: options.data.keyword,
							status : options.data.status,
							year: options.data.year,
							month: options.data.month
						},
						success:function(result){
							if (result.total!=0) {
								$(result.data).each(function(index, value){
									value.count = index + 1;
								});
							};
							options.success(result);
							optBoxPageSize();
							if (result.total!=0) {
								dataSourceProfile.page(1);
							};
							
							$("#statusName").html($(".itemStatus.active > a").text());
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
			pageSize: 10,
			schema:{
				data:"data",
				total:"total",
				model:{
					id: "dossierId"
				}
			}
		});
		
	// Source for sidebar list
		var dataAddRequest = new kendo.data.DataSource({
			transport:{
				read:function(options){
					$.ajax({
						// url: "http://localhost:3000/dossierlogs",
						url:"${api.server}/dossierlogs",
						dataType:"json",
						type:"GET",
						headers : {"groupId": ${groupId}},
						data:{
							type:"DOSSIER-01",
							sort: options.data.sort_modified
						},
						success:function(result){
							options.success(result);
							if(result.data){
								$("#sideItemAdd").show();
								$("#total_Additional_Requirement").text(dataAddRequest.total());
							}else {
								$("#total_Additional_Requirement").text(0);
							};
							// Option kendo-page
							$(".k-pager-first").css("display","none");
							$(".k-pager-last").css("display","none");
						},
						error:function(result){
							options.error(result);
						}
					});
				}
			},
			pageSize:5,
			schema:{
				data:"data",
				total:"total",
				model:{
					id:"dossierId"
				}
			}	
		});
		var dataPayRequest = new kendo.data.DataSource({
			transport:{
				read:function(options){
					$.ajax({
						// url: "http://localhost:3000/dossierlogs",
						url:"${api.server}/dossierlogs",
						dataType:"json",
						type:"GET",
						headers : {"groupId": ${groupId}},
						data:{
							type: "DOSSIER-02",
							sort: options.data.sort_modified
						},
						success:function(result){
							options.success(result);
							if(result.data){
								$("#sideItemPayment").show();
								$("#total_Payment_Request").text(dataPayRequest.total());
							}else {
								$("#total_Payment_Request").text(0);
							};
							// Option kendo-page
							$(".k-pager-first").css("display","none");
							$(".k-pager-last").css("display","none");
						},
						error:function(result){
							options.error(result);
						}
					});
				}
			},
			pageSize:5,
			schema:{
				data:"data",
				total:"total",
				model:{
					id:"dossierId"
				}
			}	
		});
		var dataResult = new kendo.data.DataSource({
			transport:{
				read:function(options){
					$.ajax({
						// url: "http://localhost:3000/dossierlogs",
						url:"${api.server}/dossierlogs",
						dataType:"json",
						type:"GET",
						headers : {"groupId": ${groupId}},
						data:{
							type: "DOSSIER-10",
							sort: options.data.sort_modified
						},
						success:function(result){
							options.success(result);
							if(result.data){
								$("#sideItemResult").show();
								$("#total_result").text(dataResult.total());
							}else {
								$("#total_result").text(0);
							};
							// Option kendo-page
							$(".k-pager-first").css("display","none");
							$(".k-pager-last").css("display","none");
						},
						error:function(result){
							options.error(result);
						}
					});
				}
			},
			pageSize:5,
			schema:{
				data:"data",
				total:"total",
				model:{
					id:"dossierId"
				}
			}	
		});
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
			$("#month").data("kendoComboBox").text("");
			$("#year").data("kendoComboBox").text("");
			$("#keyInput").val("");
			$("#serviceInfo").data("kendoComboBox").text("");
			$("#govAgency").data("kendoComboBox").text("");
		}
		var modelPanel = kendo.observable({
			dataGovAgency: dataGovAgency,
			dataServiceInfo: dataServiceInfo,
			dataYear: dataYear,
			dataMonth: dataMonth,
			// Lọc theo tháng, năm, cơ quan thực hiện, thủ tục hành chính
			eventLookup : function(e){
				e.preventDefault();
				var statusDossier = $("li.itemStatus.active").attr("dataPk");
			    if (statusDossier !== undefined) {
					dataSourceProfile.read({
						"serviceInfo": $("#serviceInfo").val(),
						"govAgencyCode": $("#govAgency").val(),
						"year": $("#year").val(),
						"month": $("#month").val(),
						"keyword": $("#keyInput").val(),
						"status": statusDossier
					})
			    } else {
					dataSourceProfile.read({
						"serviceInfo": $("#serviceInfo").val(),
						"govAgencyCode": $("#govAgency").val(),
						"year": $("#year").val(),
						"month": $("#month").val(),
						"keyword": $("#keyInput").val(),
						"status":""
					})
			    }
			},
			filterStatus: function(e){
				e.preventDefault();
				$("#profileStatus li").removeClass("active");
				$(e.currentTarget).addClass("active");
				$(".itemStatus").css("pointer-events","auto");
				$(e.currentTarget).css("pointer-events","none");
				$("#profileStatus li>i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
				$(e.currentTarget).children("i").removeClass("fa fa-folder").addClass("fa fa-folder-open");
				$("#keyInput").val("");
				var id = $(e.currentTarget).attr("dataPk");
				manageDossier.navigate("/"+id)
			},
			load_serviceConfig:function(e){
				e.preventDefault();
				if('${(applicant)!}'){
					manageDossier.navigate("/taohosomoi");
				}else {
					var cf = confirm("Bạn chưa khai báo đủ thông tin, Bạn có muốn chuyển đến trang khác khai báo!");
					if(cf){
						location.href = "group/cong-tiep-nhan/ho-so-doanh-nghiep";
					}else {
						location.href = "group/cong-tiep-nhan/quan-ly-ho-so";
					}
				}
			},
			dataBound: function() {
				$(".k-clear-value").addClass("k-hidden")
			}
		});
	// Model MainSection
		var copyProfile = function(){
			$(".copyProfile").click(function(e){
				e.stopPropagation();
				var id = $(this).attr("data-Pk");
				$.ajax({
					url:"${api.server}/dossiers/"+id+"/cloning",
					dataType:"json",
					type:"POST",
					headers: {"groupId": ${groupId}},
					success:function(res){
						var referenceUid = res.referenceUid;
						manageDossier.navigate("/taohosomoi/nophoso/"+referenceUid);
					},
					error:function(res){
						
					}
				})
			})
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
				var id = $(e.currentTarget).attr("dataPk");
				manageDossier.navigate("/"+dossierItemStatus+"/dossiers/"+id);	
			},
			stylePager: function(e){
				e.preventDefault();
				$("#pagerProfile .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"})
			},
			changeList: function(e){
				e.preventDefault();
				copyProfile();
				$(".actionDossier a").hover(function(){ $(this).css("color","#14bef0")}, function(){ $(this).css("color","#2a2a2a") }
				);
			},
			downloadProfile : function(e){
				e.stopPropagation();
				var id = $(e.currentTarget).attr("data-Pk");
				var url = "${api.server}/dossiers/"+id+"/download";
				
				$.ajax({
					url : url,
					type : "GET",
					success : function(xml, statusText, xhr){
						if(xhr.status === 204){
							notification.show({
								message: "Hồ sơ này chưa có file đính kèm"
							}, "error");
						}else if(xhr.status === 200){
							window.open(url, '_blank');
						}
					}
				});
			},
			downloadResDossier : function(e){
				e.stopPropagation();
				var id = $(e.currentTarget).attr("data-Pk");
				var url = "${api.server}/dossiers/"+id+"/download";

				$.ajax({
					url : url,
					type : "GET",
					success : function(xml, statusText, xhr){
						if(xhr.status === 204){
							notification.show({
								message: "Hồ sơ này chưa có file đính kèm"
							}, "error");
						}else if(xhr.status === 200){
							window.open(url, '_blank');
						}
					}
				});
				
			}
		});
		
	// Model Sidebar Menu
		var flagSortAdd = true;
		var flagSortPay = true;
		var flagSortResult = true;
		var modelSidebar = kendo.observable({
			dataAddRequest: dataAddRequest,
			dataPayRequest: dataPayRequest,
			dataResult: dataResult,
			loadDossierDetail:function(e){
				e.preventDefault();
				var id = e.data.dossierId;
				// Call API get dossierStatus
				var dataDossierId = new kendo.data.DataSource({
					transport:{
						read:function(options){
							$.ajax({
								url:"${api.server}/dossiers/"+id, 
								dataType:"json",
								headers : {"groupId": ${groupId}},
								type:"GET",
								success:function(result){
									var dossierItemStatus = result.dossierStatus;
									manageDossier.navigate("/"+dossierItemStatus+"/dossiers/"+id)
								},
								error:function(result){
									options.error(result);
								}
							});
						}
					},
					error: function(e) {         
						
					},
				});
				dataDossierId.read();
			},
			sortDate: function(e){
				var TypeItem = e.currentTarget.id;
				if (TypeItem == "sort_modified") {
					if (flagSortAdd) {
						e.data.dataAddRequest.read({sort_modified: "modified"});
						flagSortAdd = false;
					} else {
						e.data.dataAddRequest.read();
						flagSortAdd = true;
					}	
				} 
				else if (TypeItem == "sort_modified1") {
					if (flagSortPay) {
						e.data.dataPayRequest.read({sort_modified: "modified"});
						flagSortPay = false;
					} else {
						e.data.dataPayRequest.read();
						flagSortPay = true;
					}
				}
				else if (TypeItem == "sort_modified2") {
					if (flagSortResult) {
						e.data.dataResult.read({sort_modified: "modified"});
						flagSortResult = false;
					} else {
						e.data.dataResult.read();
						flagSortResult = true;
					}
				};
			},
			jsCollaps: function(e){
				$(e.currentTarget).children().toggle();
			}
		});

		window.onload = function(e){
			var serviceConfigId = "${(serviceConfigId)!}";
			if(serviceConfigId){
				var serviceProcess = fnGetServiceProcess(serviceConfigId);
				var serviceConfig = fnGetServiceConfig(serviceConfigId);

				fnCreateDossier(serviceProcess.templateNo, serviceConfig.serviceCode, serviceConfig.govAgencyCode);
			}
		}

		var fnCreateDossier = function(dossierTemplateNo,serviceCode,govAgencyCode){
			$.ajax({
				url : "${api.server}/dossiers",
				dataType : "json",
				type : "POST",
				data : {
					referenceUid : "",
					serviceCode : serviceCode,
					govAgencyCode : govAgencyCode,
					dossierTemplateNo : dossierTemplateNo,
					applicantName : "${(applicant.applicantName)!}",
					applicantIdType : "${(applicant.applicantIdType)!}",
					applicantIdNo : "${(applicant.applicantIdNo)!}",
					applicantIdDate : "01/01/2017 00:00:00",
					address : "${(applicant.address)!}",
					cityCode : "${(applicant.cityCode)!}",
					districtCode : "${(applicant.districtCode)!}",
					wardCode : "${(applicant.wardCode)!}",
					contactName : "${(applicant.contactName)!}",
					contactTelNo : "${(applicant.contactTelNo)!}",
					contactEmail : "${(applicant.contactEmail)!}"
				},
				headers : {"groupId": ${groupId}},
				success : function(result){
					$("#choiseProcessForDossier").modal("hide");

					manageDossier.navigate("/taohosomoi/chuanbihoso/"+result.dossierId);
				},
				error : function(result){
				}

			});
		};

		var fnGetServiceConfig = function(serviceConfigId){
			var serviceConfig = {};
			$.ajax({
				url : "${api.server}/serviceconfigs/"+serviceConfigId,
				type : "GET",
				dataType : "json",
				headers : {"groupId": ${groupId}},
				async : false,
				success : function(result){
					serviceConfig = result;
				},
				error : function(result){
					serviceConfig = {};
				}
			});
			return serviceConfig;
		}

		var fnGetServiceProcess = function(serviceConfigId){
			var serviceProcess = {};
			$.ajax({
				url : "${api.server}/serviceconfigs/"+serviceConfigId+"/processes",
				type : "GET",
				dataType : "json",
				headers : {"groupId": ${groupId}},
				async : false,
				success : function(result){
					if(result.data){
						serviceProcess = result.data[0];
					}else {
						serviceProcess = {};
					}
					
				},
				error : function(result){
					serviceProcess = {};
				}
			});

			return serviceProcess;
		}

		//Find by keyword from homepage
		$( window ).load(function() {
		  	var url = new URL(window.location.href);
			var keyword = url.searchParams.get("keyword");
			console.log("keyword = " + keyword);
			if(keyword !== undefined){
				dataSourceProfile.read({
					"serviceInfo": "",
					"govAgencyCode": "",
					"year": "",
					"month": "",
					"keyword": keyword,
					"status": ""
				})
			}
		});
	</script>
		


	