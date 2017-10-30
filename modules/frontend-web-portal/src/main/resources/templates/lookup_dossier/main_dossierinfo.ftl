<div class="row">
	<div class="MB10">
		<span class="title text-light-blue text-bold">TRA CỨU HỒ SƠ</span>
	</div>
	<div class="form-group search-icon col-sm-6 P0 MB10"> <input type="text" class="form-control" id="input_search_dossierinfo" placeholder="Nhập mã hồ sơ / Họ và tên"> </div>
	<!--Render listview tìm kiếm theo mã hồ sơ / họ tên-->
    <div class="col-sm-12 MB10 PL0">
		<ul class="ul-default" id="lvDossierResultSearch"></ul>
		<script type="text/x-kendo-template" id="tempDossierResultSearch">
			<li class="hover-pointer text-hover-blue">> </i> #:applicantName# - #:dossierId#</li>
		</script>
	</div>	
</div>
<!--Render thông tin hồ sơ cơ bản-->
<div class="row">
	<div id="detailView"></div>
</div>
<#-- Template thông tin hồ sơ chi tiết -->
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
					# dossierId = dataItem.dossierId #
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
                    	<div class="orderNo col-sm-1 text-center center-all row-blue"></div>
                        <div class="col-sm-11 M0 P10">
                            <span class="text-bold">#:author#</span><span>( #:payload.jobposTitle# ) </span><span class="text-light-blue">#:payload.briefNote#</span><br>
                            <span>#:createDate#</span><br>
                            <span>#:content#</span><br>
                            #
					        	dossierId = dataItem.dossierId;
					        	$.each(payload.dossierFiles, function(index, file) {
					        		if (file.fileType == "docx") {#
					        			<span><img src="images/word.png" alt=""> <a href="${api.server}/dossiers/#:dossierId#/files/#:file.referenceUid#" class="text-greyy"></a></span><br>
					        		#}
						        	else if (file.fileType == "pdf") {#
						        		<span><img src="images/pdf.png" alt=""> <a href="${api.server}/dossiers/#:dossierId#/files/#:file.referenceUid#" class="text-greyy"></a></span><br>
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
	$(function(){
		$("#input_search_dossierinfo").val(${keyword});
		$("#detailView2").hide();
		// dataSource thông tin hồ sơ cơ bản
		var dataSourceDossierResultSearch = new kendo.data.DataSource({
			type: "json",
			transport: {
			    read: function (options) {
			        $.ajax({
			            url: "${api.server}/dossiers",
			            dataType: "json",
			            type: 'GET',
			            data: {
			            	keyword: options.data.keyword,
			            },
			            beforeSend: function(req) {
							req.setRequestHeader('groupId', ${groupId});
						},
			            success: function (result) {
			                var NoItem = result.data.length;
			            	if (NoItem == 1) {
			            		console.log(result.data[0].dossierId);
			            		$("#detailView").load("${ajax.dossierinfo}",
				                 	function(success){
				                 		dataItem = result.data[0];
				                  		pullDataDetail(dataItem.dossierId);
				                  	}
				              	);
				            	$("#detailView2").hide()
			            	};
			            	if (NoItem > 1) {
			            		options.success(result)
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
				data : "data"
			}
		});
		// Listview kết quả tìm kiếm phải
		$("#lvDossierResultSearch").kendoListView({
			dataSource : dataSourceDossierResultSearch,
			template : kendo.template($("#tempDossierResultSearch").html()),
			navigatable: true,
			selectable: true,
			change: function() {
	        	var index = this.select().index();
	               dataItem = this.dataSource.view()[index];
	            $("#detailView").show();
	           	$("#detailView").load("${ajax.dossierinfo}",
	               	function(success){
	               		pullDataDetail(dataItem.dossierId);
	               	}
	           	);
	           	$("#detailView2").hide()
	        },
	        autoBind: false
		});

		var refreshData = function(){
			var paraValue = $("#input_search_dossierinfo").val();
			if (paraValue == "") {
				$("#lvDossierResultSearch").html("");
			} else{
				// $("#detailView").hide();
				dataSourceDossierResultSearch.read({keyword : paraValue})
			}
		}
		refreshData()
		$("#input_search_dossierinfo").keyup(
			function(){
				$("#lvDossierResultSearch").show();
				$("#detailView").hide();
				$("#detailView2").hide();
				refreshData()
			}
		);

		// dataSource listview trái
		var dataSourceDossierResult = new kendo.data.DataSource({
			transport : {
				read : function(options){
					$.ajax({
						url : "${api.server}/dossiers",
						dataType : "json",
						type : "GET",
						beforeSend: function(req) {
							req.setRequestHeader('groupId', ${groupId});
						},
						success : function(result){
							options.success(result);
						},
						error : function(result){
							options.error(error);
						}
					});
				}
			},
			pageSize : 8,
			schema : {
				total : "total",
				data : "data",
				model : {
					id : "id"
				}
			}
		});
		$("#lvDossierResult").kendoListView({
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
		                   	}
	                	)
	                },
	        dataBound: function(e) {
	        	if(dataSourceDossierResult.total() == 1){
	        		var listView = e.sender;
	            	var firstItem = listView.element.children().first();
	            	listView.select(firstItem);
	            	$("#detailView").show();
	        	}
	        }
		});
		$("#pagerDossirResult").kendoPager({
			dataSource : dataSourceDossierResult,
			info : false,
			buttonCount: 5
		})
	});
</script>



