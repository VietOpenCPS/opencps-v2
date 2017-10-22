<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="row">
	<div class="col-sm-12">
		<span class="title text-light-blue text-bold">TRA CỨU HỒ SƠ</span>
	</div>
	<div class="input-group col-md-6">
		<input id="input_search_dossierinfo" type="text" class="form-control" placeholder="Nhập mã hồ sơ / Họ và tên">
		<div class="input-group-btn">
			<button class="btn btn-default" id="#filterButton">
				<i class="glyphicon glyphicon-search"></i>
			</button>
		</div>
	</div>
	<!--Render listview tìm kiếm theo mã hồ sơ / họ tên-->
	<div class="demo-section k-content wide">
        <div class="col-sm-12">
			<ul class="ul-default ul-with-left-icon" id="lvDossierResultSearch"></ul>
			<script type="text/x-kendo-template" id="tempDossierResultSearch">
				<li class=""><i class="fa fa-angle-double-right icon-left"></i>#:applicantName# - #:dossierId#</li>
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
	$(function(){
		// Cấu hình dataSource tìm kiếm hồ sơ
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
	})
		
</script>
<script type="text/javascript">
	$("#btn_dossierinfo_detail").click(
		function() {
	    	var password = $("#input_search_dossierinfo2").val();
	    	$("#detailView2").load("${ajax.dossierinfo_detail}",
	           	function(success){
	           		pullDataDetail2(password);
	           	}
	       	);
    	}
	)	
</script>

