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
							if (result.data) {
								options.success(result);
							} else{
								$(".loading").hide();
							}
							//Box lựa chọn hiển thị số bản ghi/page
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
							// 
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
						url:"${api.server}/dossierlogs",
						dataType:"json",
						type:"GET",
						data:{
							type:123,
							sort: options.data.sort_modified
						},
						success:function(result){
							if(result.data){
								options.success(result);
								$("#sideItemAdd").show();
								$("#total_Additional_Requirement").text(dataAddRequest.total());
							}
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
						url:"${api.server}/dossierlogs",
						dataType:"json",
						type:"GET",
						data:{
							type: 234,
							sort: options.data.sort_modified
						},
						success:function(result){
							if(result.data){
								options.success(result);
								$("#sideItemPayment").show();
								$("#total_Payment_Request").text(dataPayRequest.total());
							}
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
						url:"${api.server}/dossierlogs",
						dataType:"json",
						type:"GET",
						data:{
							type: 345,
							sort: options.data.sort_modified
						},
						success:function(result){
							if(result.data){
								options.success(result);
								$("#sideItemResult").show();
								$("#total_result").text(dataResult.total());
							}
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
		var arrMonth = [];
		for (var i = 1; i <= 12; i++) {
		    arrMonth.push({month: "Tháng "+i, valMonth: i})
		};
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
				$("#wrapMain").hide();
				setTimeout(function(){$("#wrapMain").show()},300);
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
				$("#wrapMain").hide();
				setTimeout(function(){$("#wrapMain").show()},300);
				$("#profileStatus li").removeClass("active");
				$(e.currentTarget).addClass("active");
				$(".itemStatus").css("pointer-events","auto");
				$(e.currentTarget).css("pointer-events","none");
				$("#profileStatus li>i").removeClass("fa fa-folder-open-o").addClass("fa fa-folder-o");
				$(e.currentTarget).children("i").removeClass("fa fa-folder-o").addClass("fa fa-folder-open-o");
				$("#keyInput").val("");
				var id = $(e.currentTarget).attr("dataPk");
				manageDossier.navigate("/"+id);
				return true;
			},
			load_serviceConfig:function(e){
				e.preventDefault();
				manageDossier.navigate("/taohosomoi");
			},
		});
	// Model MainSection
		var modelMain = kendo.observable({
			dataSourceProfile : dataSourceProfile,
			// modelPanel: modelPanel,
			filterKey: modelPanel.eventLookup,
			changePageSize: function(){
				dataSourceProfile.pageSize(parseInt($("#itemPpage").val()))
			},
			loadDossierDetail:function(e){
				e.preventDefault();
				var dossierItemStatus = e.data.dossierStatus;
				var id = $(e.currentTarget).attr("dataPk");
				manageDossier.navigate("/"+dossierItemStatus+"/dossiers/"+id);	
			},
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
				// Gọi api lấy trạng thái hồ sơ
				var dataDossierId = new kendo.data.DataSource({
					transport:{
						read:function(options){
							$.ajax({
								url:"${api.server}/dossiers/"+id, 
								dataType:"json",
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
						this.cancelChanges();
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

			}
		})
	</script>