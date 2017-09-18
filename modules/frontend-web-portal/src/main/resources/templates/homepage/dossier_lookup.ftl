<div class="row">
	<div class="col-sm-12">
		<i class="fa fa-paperclip MT10 MB10"></i> <span>HÔ SƠ ĐÃ CÓ KÊT QUẢ</span>
	</div>
	<div class="col-sm-12" style="border-bottom: 2px solid #ccc;">

	</div>
	<div class="col-sm-12">
		<ul class="PT10 PB5" id="lvDossierResult"></ul>
		<script type="text/x-kendo-template" id="tempDossierResult">
			<li class="MT5 MB5"> <span>NGUYÊN VĂN KHOA - 7666648658 <#-- #:name# - #:dossierId# --></span></li>
		</script>
		<div class="pager pull-left" id="pagerDossirResult"></div>
	</div>
	<div class="col-sm-9">
		<div class="form-group MB10"> <input id="searchDossierCode" type="text" class="form-control input-sm" placeholder="Nhập mã hồ sơ/ họ và tên"> </div>
	</div>
	<div class="col-sm-3 PL0">
		<button class="btn btn-small" type="button" id="btn-search-dossier">Tra cứu</button>
	</div>


</div>

<script type="text/javascript">
	var dataSourceDossierResult = new kendo.data.DataSource({
		transport : {
			read : function(options){
				$.ajax({
					url : "http://hanoi.fds.vn:2281/api/serviceinfos",
					dataType : "json",
					type : "GET",
					data : {
						dossierCode : options.data.dossierCode
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
		pageSize : 5,
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
		info : false
	});

	$("#btn-search-dossier").click(function(){
		dataSourceDossierResult.read({
			dossierCode : $("#searchDossierCode").val()
		});
	});
</script>