<#if (Request)??>
	<#include "init.ftl">
</#if>

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
			<li class=""><i class="fa fa-angle-double-right icon-left"></i> #:applicantName# - #:dossierId#</li>
		</script>
	</div>	
</div>
<!--Render thông tin hồ sơ cơ bản-->
<div class="row">
	<div id="detailView"></div>
</div>
<!--Render thông tin chi tiết DossierFile-->
<div class="row">
	<div class="panel panel-default MB0">
		<div class="panel-heading"> 
	        <span class="text-bold text-light-blue">Kết quả xử lý</span>
	    </div>
	    <div class="panel-body PL0">
	    	<ul class="ul-default" id="DossierDetailFile"></ul>
	    	<script type="text/x-kendo-template" id="tempDossierDetailFile">
	    		<li><span><i class="fa fa-download"></i></span> <span>#:displayName#</span></li>
			</script>
	    </div>
	</div>
</div>
<!--Render thông tin chi tiết DossierLog-->
<div class="row">
	<div class="panel panel-default MB0">
		<div class="panel-heading"> 
	        <span class="text-bold text-light-blue">Tiến trình xử lý hồ sơ</span>
	    </div>
	    <div class="panel-body PL0">
	    	<ul class="ul-default" id="DossierDetailLog"></ul>
	    	<script type="text/x-kendo-template" id="tempDossierDetailLog">
	    		<li>
	    			<span class="text-bold stt">STT</span>
	    			<span>Văn bản yêu cầu bổ sung</span>
	    		</li>
			</script>
	    </div>
	</div>
</div>
<script type="text/javascript">
	// Cấu hình dataSource tìm kiếm hồ sơ
	$(function(){
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
            },
			autoBind:false
		});
		// event truyền tham số vào read dataSource
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
	// Cấu hình dataSource thông tin chi tiết hồ sơ
	$(function(){
		var dataSourceDossierDetail = new kendo.data.DataSource({
			type: "json",
			transport: {
			    read: function (options) {
			        $.ajax({
			            url: "${api.server}/dossiers/"+dataItem.dossierId+"/"+options.data.endpoint,
			            dataType: "json",
			            type: 'GET',
			            data: {
			            	password: options.data.password
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
		$("#DossierDetailFile").kendoListView({
			dataSource : dataSourceDossierDetail,
			template : kendo.template($("#tempDossierDetailFile").html()),
			navigatable: false,
			selectable: false,
			autoBind:false
		});
		$("#DossierDetailLog").kendoListView({
			dataSource : dataSourceDossierDetail,
			template : kendo.template($("#tempDossierDetailLog").html()),
			navigatable: false,
			selectable: false,
			autoBind:false
		});
		// event truyền tham số vào read dataSource
		var evenDataDossierDetail = function(endpoint){
			var paraValue2 = $("#input_dossier_detail").val();
			console.log(paraValue2);
		    dataSourceDossierDetail.read({password: paraValue2, endpoint: endpoint})
		};
		$("#btn_dossierinfo_detail").click(
			function(){
				console.log("Run"); 
				evenDataDossierDetail("files");
				evenDataDossierDetail("logs");
			}
		)
	})
</script>

