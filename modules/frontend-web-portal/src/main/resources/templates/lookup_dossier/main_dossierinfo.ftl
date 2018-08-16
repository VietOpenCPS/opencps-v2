<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="row" style="display: none;">
	<div class="MB10">
		<span class="title text-light-blue text-bold">TRA CỨU HỒ SƠ</span>
	</div>
	<div class="form-group search-icon col-sm-6 P0 MB10" > <input type="text" class="form-control" id="input_search_dossierinfo" oninput="" placeholder="Nhập mã hồ sơ / Họ và tên" > </div>
	<!--Render listview tìm kiếm theo mã hồ sơ / họ tên-->
	<div class="col-sm-12 MB10 PL0">
		<#-- <ul class="ul-default" id="lvDossierResultSearch"></ul> -->
		<script type="text/x-kendo-template" id="tempDossierResultSearch">
			<li class="itemResult" data-pk="#:dossierId#"><a href="javascript:;" class="hover-pointer text-hover-blue">> </i> #:applicantName# - #:dossierId#</a></li>
		</script>
	</div>	
</div>
<!--Render thông tin hồ sơ cơ bản-->
<div class="row">
	<div id="detailView"></div>
	<div id="detailViewNodata"></div>
</div>
<!-- Template thông tin hồ sơ chi tiết -->
<div id="detailView2">
	<!--Render thông tin chi tiết DossierFiles-->
	<div class="row">
		<div class="panel panel-default MB0">
			<div class="panel-heading"> 
				<span class="text-bold text-light-blue">Kết quả xử lý</span>
			</div>
			<div class="panel-body P5 PL15">
				<ul class="ul-default" id="DossierDetailFile"></ul>
				<script type="text/x-kendo-template" id="tempDossierDetailFile">
					<li><a href="${api.server}/dossiers/#:dossierId#/files/#:referenceUid#"> <span><i class="fa fa-download"></i></span> <span class="ML10">#:displayName#</span> </a></li>
				</script>
			</div>
		</div>
	</div>
	<!--Render thông tin chi tiết DossierLogs-->
	<div class="row">
		<div class="panel panel-default MB0">
			<div class="panel-heading"> 
				<span class="text-bold text-light-blue">Tiến trình xử lý hồ sơ</span>
			</div>
			<div class="panel-body P0">
				<ul class="ul-default mimic-table" id="DossierDetailLog"></ul>
				<script type="text/x-kendo-template" id="tempDossierDetailLog">
					<li class="clearfix eq-height-lg P0">
						<div class="orderNo col-sm-0 text-center center-all row-blue P15"></div>
						<div class="col-sm-12 M0 P5 PL10">
							<span class="text-bold">#:author# </span><span class="text-light-gray">( #:payload.jobposTitle# ) </span><span class="text-light-blue">#:payload.briefNote#</span><br>
							<span class="text-light-gray">#:createDate#</span><br>
							<p class="MB5">#:content#</p>
							#
							$(payload.dossierFiles).each(function(index, file) {
							if (file.fileType == "docx") {#
							<p class="MB5"><img src="images/word.png" alt=""> <a class="text-hover-blue" href="${api.server}#:file.fileUrl#">#:file.displayName#</a></p>
							#}
							else if (file.fileType == "pdf") {#
							<p class="MB5"><img src="images/pdf.png" alt=""> <a class="text-hover-blue" href="${api.server}#:file.fileUrl#">#:file.displayName#</a></p>
							#}
						})
						# 
					</div>
				</li>
			</script>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">

	var dossierId;
	$(function(){
		$("#input_search_dossierinfo").val("${(keyword)!}");
		var paramKeyword = $("#input_search_dossierinfo").val().toLowerCase();
		$("#detailView2").hide();
		$("#detailViewNodata").hide();
		// dataSource thông tin hồ sơ cơ bản
		var dataSourceDossierResultSearch = new kendo.data.DataSource({
			transport: {
				read: function (options) {
					$.ajax({
			        	// url: "http://localhost:3000/dossiers",
			        	url: "${api.server}/dossiers",
			        	dataType: "json",
			        	type: 'GET',
			        	headers : {"groupId": 55217},
			        	data: {
			        		keyword: paramKeyword,
			        		secetKey: "OPENCPSV2"
			        	},
			        	success: function (result) {
			        		if (result.data) {
			        			options.success(result);
			        			loadDetail();
			        			var NoItem = result.data.length;
			        			$("#detailView").load("${ajax.dossierinfo}",
			        				function(success){
			        					dataItem = result.data[0];
			        					pullDataDetail(dataItem.dossierId);
			        					var viewModel = kendo.observable({
			        						dossierIdCTN: dataItem.dossierIdCTN,
			        						applicantName: dataItem.applicantName,
			        						serviceName: dataItem.serviceName,
			        						dossierNo: dataItem.dossierNo,
			        						govAgencyName: dataItem.govAgencyName,
			        						submitDate: dataItem.submitDate,
			        						dueDate: dataItem.dueDate,
			        						dossierStatusText: dataItem.dossierStatusText
			        					});
			        					kendo.bind($("#DossiersDetailInfo"), viewModel);
			        					$(".panel").css("border-radius","0");
			        				}
			        			);
			        			$("#detailView2").hide()
			        		}else{
										$("#detailViewNodata").show();
										$("#detailView").hide();
			        			$("#detailViewNodata").html("Hồ sơ với mã hồ sơ đã nhập không tồn tại trong hệ thống");
			        		}
			        	},
			        	error : function(xhr){
			        		options.error(xhr);
			        	}
			        });
				}
			},
			schema : {
				total : "total",
				data : "data",
				model : {
					id : "dossierId"
				}
			}
		});
		
		var refreshData = function(){
			var paraValue = $("#input_search_dossierinfo").val();
			if (paraValue == "") {
				$("#lvDossierResultSearch").hide();
			} else{
				dataSourceDossierResultSearch.read({keyword : paraValue});
			}
		};
		refreshData();
		$("#input_search_dossierinfo").on("input",	
			function(){
				var paraValue = $("#input_search_dossierinfo").val();
				$("#detailView").hide();
				$("#detailViewNodata").hide();
				$("#detailView2").hide();
				if (paraValue == "") {
					$("#lvDossierResultSearch").hide();
				} else{
					$("#lvDossierResultSearch").show();
					dataSourceDossierResultSearch.read({keyword : paraValue});
				}
			}
			);
		// Listview kết quả tìm kiếm phải
		$("#lvDossierResultSearch").kendoListView({
			dataSource : dataSourceDossierResultSearch,
			template : kendo.template($("#tempDossierResultSearch").html()),
			navigatable: true,
			selectable: true,
			autoBind: false
		});
		// dataSource listview trái
		
		// var dataSourceDossierResult = new kendo.data.DataSource({
		// 	transport: {
		// 		read: function (options) {
		// 			$.ajax({
		// 	        	// url: "http://localhost:3000/dossiers",
		// 	        	url: "${api.server}/dossiers",
		// 	        	dataType: "json",
		// 	        	type: 'GET',
		// 	        	headers : {"groupId": 55217},
		// 	        	data: {
		// 	        		keyword: paramKeyword,
		// 	        		secetKey: "OPENCPSV2"
		// 	        	},
		// 	        	success : function(result){
		// 	        		if (result.data) {
		// 	        			options.success(result);
		// 	        		}
		// 	        	},
		// 	        	error : function(result){
		// 	        		options.error(result);
		// 	        	}
		// 	        });
		// 		}
		// 	},
		// 	pageSize : 10,
		// 	schema : {
		// 		total : "total",
		// 		data : "data",
		// 		model : {
		// 			id : "dossierId"
		// 		}
		// 	}
		// });
		
		/*$("#listDossierResult").kendoListView({
			dataSource : dataSourceDossierResult,
			template : kendo.template($("#tempDossierResult").html()),
			navigatable: true,
			selectable: true,
			// Load dossierinfo.ftl
			change: function() {
				$("#lvDossierResultSearch").hide();
				$("#detailView2").hide();
				$("#detailView").show();
				var index = this.select().index();
				dataItem = this.dataSource.view()[index];
				$("#input_search_dossierinfo").val(dataItem.dossierId);
				$("#detailView").load("${ajax.dossierinfo}",
					function(success){
						pullDataDetail(dataItem.dossierId);
						var viewModel = kendo.observable({
							dossierId: dataItem.dossierId,
							applicantName: dataItem.applicantName,
							serviceName: dataItem.serviceName,
							dossierNo: dataItem.dossierNo,
							govAgencyName: dataItem.govAgencyName,
							submitDate: dataItem.submitDate,
							dueDate: dataItem.dueDate,
							dossierStatusText: dataItem.dossierStatusText
						});
						kendo.bind($("#DossiersDetailInfo"), viewModel);
						$(".panel").css("border-radius","0");
					});
				dossierId = dataItem.dossierId;
			},
			dataBound: function(e) {
	        	// if(dataSourceDossierResult.total() == 1){
	        	// 	var listView = e.sender;
	         //    	var firstItem = listView.element.children().first();
	         //    	listView.select(firstItem);
	         //    	$("#detailView").show();
	        	// }
	        }
	    });*/
		// $("#pagerDossirResult").kendoPager({
		// 	dataSource : dataSourceDossierResult,
		// 	info : false,
		// 	selectTemplate: '<li class="k-link"><i class="fa fa-circle" aria-hidden="true"></i></li>',
		// 	linkTemplate: '<li><a href="\\#" class="k-link" data-#=ns#page="#=idx#"><i class="fa fa-circle" aria-hidden="true"></i></a></li>'
		// });
		var loadDetail = function(){
			$(".itemResult").click(function(){
				dossierId = $(this).attr("data-pk");
				console.log(dossierId);
				$(".itemResult").css("pointer-events","auto");
				$(".itemResult").removeClass("text-light-blue");
				$(this).css("pointer-events","none");
				$(this).addClass("text-light-blue");
				$("#detailView").show();
				$("#detailView").load("${ajax.dossierinfo}",
					function(success){
						pullDataDetail(dossierId)
					}
				);
				$("#detailViewNodata").hide();
				$("#detailView2").hide()
			})
		}
	});
</script>


