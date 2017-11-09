<#-------------- Phần ViewModel/DataSource ------------>
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
		var dataSourceProfile=new kendo.data.DataSource({
			transport:{
				read:function(options){
					$.ajax({
						url:"${api.server}/dossiers",
						dataType:"json",
						type:"GET",
						data:{
							service: options.data.serviceInfo,
							agency: options.data.govAgencyCode,
							year: options.data.year,
							month: options.data.month,
							keyword: options.data.keyword,
							status : options.data.status
						},	
						success:function(result){
							options.success(result);
							kendo.fx($("#main_section")).expand("vertical").duration(700).play();
							dataSourceProfile.page(1);
							var totalItem = parseInt(dataSourceProfile.total());
							var pSize = dataSourceProfile.pageSize();
							var arrPsize = [];
							var selectHtml = "<option value='"+totalItem+"'>Tất cả</option>";
							$("#totalItem_dossierList").text(dataSourceProfile.total());
							if (totalItem <= dataSourceProfile.pageSize()) {
								$("#itemPpage").html("<option value='"+totalItem+"' selected>"+totalItem+"</option>")
							} else {
								for (var i = pSize; i < totalItem; i+=pSize) {
									arrPsize.push(i)
								};
								var sub = "";
								$.each(arrPsize,function(index,value){
									sub+="<option value='"+value+"'>"+value+"</option>"
								});
								$("#itemPpage").html(sub);
								$("#itemPpage").append(selectHtml)
							}
						},
						error:function(result){
							options.error(result);
						}
					});
				}
			},
			error: function(e) {         
				this.cancelChanges();
			},
			pageSize: 2,
			schema:{
				data:"data",
				total:"total",
				model:{
					id: "dossierId"
				}
			}
		});
		var dataDossierDetail = new kendo.data.DataSource({
			transport:{
				read:function(options){
					$.ajax({
						url:"${ajax.customer_dossier_detail}",
						data:{
							id : options.data.id
						},	
						success:function(result){
							options.success(result);
							$("#detail").html(result)
						},
						error:function(result){
							options.error(result);
						}
					});
				}
			},
		});
		var dataLoadServiceConfig = new kendo.data.DataSource({
			transport:{
				read:function(options){
					$.ajax({
						url:"${ajax.serviceconfig}",
						data:{
							
						},	
						success:function(result){
							options.success(result);
							$("#serviceconfig").html(result)
						},
						error:function(result){
							options.error(result);
						}
					});
				}
			}
		})
	// Source for sidebar list
		var dataAddRequest = new kendo.data.DataSource({
			transport:{
				read:function(options){
					$.ajax({
						url:"${api.server}/dossiers/dossierlogs",
						dataType:"json",
						type:"GET",
						data:{
							type:123,
							sort: options.data.sort_modified
						},
						success:function(result){
							if(result.data){
								options.success(result);
								kendo.fx($("#wrapAddRes")).expand("vertical").duration(500).play();
								$("#total_Additional_Requirement").text(dataAddRequest.total())
							}else{
								$("#sideItemAdd").hide();
							}
						},
						error:function(result){
							options.error(result);
							$("#sideItemAdd").hide()
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
						url:"${api.server}/dossiers/dossierlogs",
						dataType:"json",
						type:"GET",
						data:{
							type: 234,
							sort: options.data.sort_modified
						},
						success:function(result){
							if(result.data){
								options.success(result);
								kendo.fx($("#wrapPayRes")).expand("vertical").duration(500).play();
								$("#total_Payment_Request").text(dataPayRequest.total())
							}else{
								$("#sideItemPayment").hide();
							}
						},
						error:function(result){
							options.error(result);
							$("#sideItemPayment").hide()
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
						url:"${api.server}/dossiers/dossierlogs",
						dataType:"json",
						type:"GET",
						data:{
							type: 345,
							sort: options.data.sort_modified
						},
						success:function(result){
							if(result.data){
								options.success(result);
								kendo.fx($("#wrapResult")).expand("vertical").duration(500).play();
								$("#total_result").text(dataResult.total())
							}else{
								$("#sideItemResult").hide();
							}
						},
						error:function(result){
							options.error(result);
							$("#sideItemResult").hide()
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
		    arrYear.push(yyyy)
		};
		var dataYear = new kendo.data.DataSource({
			data: arrYear
		});
		var arrMonth = ["1","2","3","4","5","6","7","8","9","10","11","12"];
		var dataMonth = new kendo.data.DataSource({
			data: arrMonth
		});

	// -=-=-=-=-=-=-=Model=-=-=-=-=-=-=-=-=-
	// Model Panel
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
				$("#profileStatus li>i").removeClass("fa fa-folder-open-o").addClass("fa fa-folder-o");
				$(e.currentTarget).children("i").removeClass("fa fa-folder-o").addClass("fa fa-folder-open-o");
				$("#keyInput").val("");
				var id = $(e.currentTarget).attr("dataPk");
				managerDossier.navigate("/"+id);
				return true;
			},
			load_serviceConfig:function(e){
				e.preventDefault();
				managerDossier.navigate("/service")
			},
		});
	// Model MainSection
		var modelMain = kendo.observable({
			dataSourceProfile : dataSourceProfile,
			modelPanel: modelPanel,
			changePageSize: function(){
				dataSourceProfile.pageSize(parseInt($("#itemPpage").val()))
			},
			filterKey: modelPanel.eventLookup,
			loadDossierDetail:function(e){
				e.preventDefault();
				var dossierItemStatus = e.data.dossierStatus;
				var id = $(e.currentTarget).attr("dataPk");
				managerDossier.navigate("/dossierStatus"+dossierItemStatus+"/detailDossier"+id)
			},
		});
		var modelDossierDetail = kendo.observable({
			dataDossierDetail: dataDossierDetail
		});
		var modelServiceConfig = kendo.observable({
			dataLoadServiceConfig: dataLoadServiceConfig
		});
	// Model Sidebar Menu
		var modelSidebar = kendo.observable({
			dataAddRequest: dataAddRequest,
			dataPayRequest: dataPayRequest,
			dataResult: dataResult,
			loadDossierDetail:function(e){
				e.preventDefault();
				var id = e.data.dossierId;
				var dataDossierId = new kendo.data.DataSource({
					transport:{
						read:function(options){
							$.ajax({
								url:"${api.server}/dossiers/101", //insert: "${api.server}/dossiers/"+id
								dataType:"json",
								type:"GET",
								success:function(result){
									var dossierItemStatus = result.dossierStatus;
									managerDossier.navigate("/dossierStatus"+dossierItemStatus+"/detailDossier"+id)
								},
								error:function(result){
									options.error(result);
								}
							});
						}
					},
					error: function(e) {         
						this.cancelChanges();
					},
				});
				dataDossierId.read();
			},
			sortDate: function(e){
				var TypeItem = e.currentTarget.id;
				if (TypeItem == "sort_modified") {
					e.data.dataAddRequest.read({sort_modified: "modified"})
				} 
				else if (TypeItem == "sort_modified1") {
					e.data.dataPayRequest.read({sort_modified: "modified"})
				}
				else if (TypeItem == "sort_modified2") {
					e.data.dataResult.read({sort_modified: "modified"})
				}
			}
		})
	</script>