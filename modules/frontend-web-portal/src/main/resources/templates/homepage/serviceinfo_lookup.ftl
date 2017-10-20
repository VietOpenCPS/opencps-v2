<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="row">
	<div class="col-sm-12">
		<span class="title">TRA CỨU HỒ SƠ</span>
	</div>
	<div class="input-group col-md-6">
		<input id="input_search_dossierinfo" type="text" class="form-control" placeholder="Nhập mã hồ sơ / Họ và tên">
		<div class="input-group-btn">
			<button class="btn btn-default" id="#filterButton">
				<i class="glyphicon glyphicon-search"></i>
			</button>
		</div>
	</div>
	<#--Render list hồ sơ tìm kiếm theo mã hồ sơ / họ tên-->
	<div class="demo-section k-content wide">
        <div class="col-sm-12">
			<ul class="ul-default" id="lvDossierResultSearch"></ul>
			<script type="text/x-kendo-template" id="tempDossierResultSearch">
				<li class="">#:applicantName# - #:dossierId#</li>
			</script>
		</div>	
	</div>
</div>
<!--Render thông tin hồ sơ cơ bản-->
<div class="row">
	<div id="detailView"></div>
</div>
<!--Render thông tin chi tiết-->
<div class="row">
	<div id="detailView2"></div>
</div>
<script type="text/javascript">
	// Cấu hình dataSource search thông tin hồ sơ cơ bản
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
			selectable: true,
			change: function() {
               var index = this.select().index();
                   dataItem = this.dataSource.view()[index];
               $("#detailView").load("${ajax.dossierinfo_detail}",
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
			if (paraValue == "") {
				$("#lvDossierResultSearch").html("<span>Nhập thông tin tiềm kiếm</span>")
			} else{
				console.log(paraValue);
		    	$("#lvDossierResultSearch").getKendoListView().dataSource.read({keyword: paraValue})
			}
		}

		$("#input_search_dossierinfo").change( 
			function(){
				var paraValue = $("#input_search_dossierinfo").val();
				if (paraValue == "") {
					$("#lvDossierResultSearch").html("<span>Nhập thông tin tiềm kiếm</span>")
				} else{
					console.log(paraValue);
			    	$("#lvDossierResultSearch").getKendoListView().dataSource.read({param1: paraValue})
				}
			}
		);
		$("#filterButton").click(
			function(){
				var paraValue = $("#input_search_dossierinfo").val();
				if (paraValue == "") {
					$("#detailView").html("<span>Nhập thông tin tiềm kiếm</span>")
				} else{
					console.log(paraValue);
			    	$("#detailView").getKendoListView().dataSource.read({param1: paraValue})
				}
			}
		);
	}) 
</script>
<script type="text/javascript">
	$("#btn_dossierinfo_detail").click(
		function() {
	    	var password = $("#input_search_dossierinfo2").val();
	    	$("#detailView2").load("${ajax.dossier_detail}",
	           	function(success){
	           		pullDataDetail2(password);
	           	}
	       	);
    	}
	)	
</script>

