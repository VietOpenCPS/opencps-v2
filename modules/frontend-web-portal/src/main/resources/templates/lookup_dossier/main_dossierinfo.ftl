<div class="row">
	<div class="MB10">
		<span class="title text-light-blue text-bold">TRA CỨU HỒ SƠ</span>
	</div>
	<div class="input-group col-sm-6 MB15">
		<input id="input_search_dossierinfo" type="text" class="form-control" placeholder="Nhập mã hồ sơ / Họ và tên">
		<div class="input-group-btn">
			<button class="btn btn-default" id="#filterButton">
				<i class="glyphicon glyphicon-search"></i>
			</button>
		</div>
	</div>
	<!--Render listview tìm kiếm theo mã hồ sơ / họ tên-->
    <div class="col-sm-12 MB10">
		<ul class="ul-default" id="lvDossierResultSearch"></ul>
		<script type="text/x-kendo-template" id="tempDossierResultSearch">
			<li class="hover-pointer text-hover-blue"><i class="fa fa-angle-double-right icon-left"></i> #:applicantName# - #:dossierId#</li>
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
                <ul class="ul-default" id="DossierDetailLog"></ul>
                <script type="text/x-kendo-template" id="tempDossierDetailLog">
                    <li class="clearfix P0">
                    	<div class="orderNo col-sm-1 text-center center-all row-blue"></div>
                        <div class="col-sm-11 M0 P10">
                            <span class="text-bold">#:author#</span><span>( #:payload.jobposTitle# )</span> <span class="text-light-blue">#:payload.briefNote#</span><br>
                            <span>#:createDate#</span><br>
                            <span>#:content#</span><br>
                            #
					        	var dossierId = dataItem.dossierId;
					        	$.each(payload.dossierFiles, function(index, file) {
					        		if (file.fileType == "docx") {#
					        			<span><img src="images/word.png" alt=""> <a href="${api.server}/dossiers/#:dossierId#/files/#:file.referenceUid#" class="text-greyy"></a></span><br>
					        		#};
						        	if (file.fileType == "pdf") {#
						        		<span><img src="images/pdf.png" alt=""> <a href="${api.server}/dossiers/#:dossierId#/files/#:file.referenceUid#" class="text-greyy"></a></span><br>
						        	#}
						        })
				        	#
                            
                        </div>
                        <hr class="col-sm-12 P0 M0">
                    </li>
                </script>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	$(function(){
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
			            	keyword: options.data.keyword
			            },
			            beforeSend: function(req) {
							req.setRequestHeader('groupId', ${groupId});
						},
			            success: function (result) {
			                options.success(result);
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
		$("#lvDossierResultSearch").kendoListView({
			dataSource : dataSourceDossierResultSearch,
			template : kendo.template($("#tempDossierResultSearch").html()),
			navigatable: true,
			selectable: true,
			change: function() {
               var index = this.select().index();
                   dataItem = this.dataSource.view()[index];
               $("#detailView").load("${ajax.dossierinfo}",
                   	function(success){
                   		pullDataDetail(dataItem.dossierId);
                   	}
               	);
               $("#detailView2").hide()
            },
			autoBind:false
		});
		// event truyền tham số cho datasource lấy thông tin hồ sơ cơ bản
		var evenSearch = function(){
			var paraValue = $("#input_search_dossierinfo").val();
			console.log(paraValue);
		    dataSourceDossierResultSearch.read({keyword: paraValue})
		}
		$("#input_search_dossierinfo").change(
			function(){ evenSearch() }
		);
		$("#filterButton").click(
			function(){ evenSearch() }
		)
	});
</script>


