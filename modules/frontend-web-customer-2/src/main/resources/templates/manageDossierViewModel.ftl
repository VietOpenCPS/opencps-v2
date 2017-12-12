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
					// if (options.data.year=="") {
					// 	options.data.year = 0;
					// };
					// if (options.data.month=="") {
					// 	options.data.month = 0;
					// };
					$.ajax({
						// url: "http://localhost:3000/dossiers",
						url:"${api.server}/dossiers",
						dataType:"json",
						type:"GET",
						headers : {"groupId": ${groupId}},
						data:{
							service: options.data.serviceInfo,
							dossierNo: options.data.dossierNo,
							keyword: options.data.keyword,
							status : options.data.status,
							// year: options.data.year,
							// month: options.data.month
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
			// $("#month").data("kendoComboBox").text("");
			// $("#year").data("kendoComboBox").text("");
			$("#dossier-emp-nav-selectbox-by-dossierNo").val("");
			$("#keyInput").val("");
			$("#serviceInfo").data("kendoComboBox").text("");
			// $("#govAgency").data("kendoComboBox").text("");
		}
		var modelPanel = kendo.observable({
			// dataGovAgency: dataGovAgency,
			dataServiceInfo: dataServiceInfo,
			// dataYear: dataYear,
			// dataMonth: dataMonth,
			// Lọc theo tháng, năm, cơ quan thực hiện, thủ tục hành chính
			eventLookup : function(e){
				e.preventDefault();
				var statusDossier = $("li.itemStatus.active").attr("dataPk");
			    if (statusDossier !== undefined) {
					dataSourceProfile.read({
						"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
						"serviceInfo": $("#serviceInfo").val(),
						// "govAgencyCode": $("#govAgency").val(),
						// "year": $("#year").val(),
						// "month": $("#month").val(),
						"keyword": $("#keyInput").val(),
						"status": statusDossier 
					})
			    } else {
					dataSourceProfile.read({
						"serviceInfo": $("#serviceInfo").val(),
						"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
						// "govAgencyCode": $("#govAgency").val(),
						// "year": $("#year").val(),
						// "month": $("#month").val(),
						"keyword": $("#keyInput").val(),
						"status": statusDossier 
					})
			    }
			},
			filterDossierNo: function(e){
				e.preventDefault();
				$("#keyInput").val("");
				var statusDossier = $("li.itemStatus.active").attr("dataPk");
				dataSourceProfile.read({
					"serviceInfo": $("#serviceInfo").val(),
					"dossierNo" : $("#dossier-emp-nav-selectbox-by-dossierNo").val(),
					"status":statusDossier
				})
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
				manageDossier.navigate("/taohosomoi");
			},
			dataBound: function() {
				$(".k-clear-value").addClass("k-hidden")
			}
		});
	// Model MainSection
		var loadProfile = function(){
			$(".downloadProfile").click(function(e){
				e.stopPropagation();
				var id = $(this).attr("data-Pk");
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
				loadProfile();
				loadAddRes();
				copyProfile();
				$(".actionDossier a").hover(function(){ $(this).css("color","#14bef0")}, function(){ $(this).css("color","#2a2a2a") }
				);
			}
		});
		

	</script>
		


	