<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="dossier-with-result">
	<div class="col-sm-12">
		<span class="title">HỒ SƠ ĐÃ CÓ KẾT QUẢ</span>
	</div>
	<div class="col-sm-12">
		<ul class="PT10 PB5" id="lvDossierResult"></ul>
		<script type="text/x-kendo-template" id="tempDossierResult">
			<li class="">#:applicantName# - #:dossierId#</li>
		</script>
		<div class="pager pull-left" id="pagerDossirResult"></div>
	</div>
	<div class="button-holder">
		<div class="col-sm-9">
			<div class="form-group"> <input id="searchDossierCode" type="text" class="form-control input-sm" placeholder="Nhập mã hồ sơ/ họ và tên"> </div>
		</div>
		<div class="col-sm-3">
			<button class="btn btn-small" type="button" id="btn-search-dossier">Tra cứu</button>
		</div>
	</div>
</div>

<script type="text/javascript">
	var dataSourceDossierResult = new kendo.data.DataSource({
		transport : {
			read : function(options){
				$.ajax({
					url : "${api.server}/dossiers?status=done",
					dataType : "json",
					type : "GET",
					data : {
						dossierCode : options.data.dossierCode
					},
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
		pageSize : 10,
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
		template : kendo.template($("#tempDossierResult").html())
	});
	$("#pagerDossirResult").kendoPager({
		dataSource : dataSourceDossierResult,
		info : false,
		selectTemplate: '<li class="k-link"><i class="fa fa-circle" aria-hidden="true"></i></li>',
		linkTemplate: '<li><a href="\\#" class="k-link" data-#=ns#page="#=idx#"><i class="fa fa-circle" aria-hidden="true"></i></a></li>'
	});
	$("#btn-search-dossier").click(function(){
		var keyword = $("#searchDossierCode").val();
		window.location.href="http://v2.opencps.vn/tra-cuu-ho-so?keyword="+keyword+"";
	});
</script>