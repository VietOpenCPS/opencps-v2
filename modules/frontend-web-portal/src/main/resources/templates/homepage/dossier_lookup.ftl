<!-- List-view hồ sơ đã có kết quả -->
<div class="dossier-with-result">
	<div class="col-sm-12">
		<span class="title">HỒ SƠ ĐÃ CÓ KẾT QUẢ</span>
	</div>
	<div class="col-sm-12">
		<ul class="ul-default" id="lvDossierResult"></ul>
		<!-- Template render list -->
		<script type="text/x-kendo-template" id="tempDossierResult">
			<li class="">#:applicantName# - #:dossierId#</li>
		</script>
	</div>
	<!-- element handle pagination -->
	<div class="pager pull-left" id="pagerDossirResult"></div>
</div>

<script type="text/javascript">
	// Cấu hình dataSource
	var dataSourceDossierResult = new kendo.data.DataSource({
		transport : {
			read : function(options){
				$.ajax({
					url : "${api.server}/dossiers",
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
        // dataBound: function(e) {
        //    var listView = e.sender;
        //    var firstItem = listView.element.children().first();
        //    listView.select(firstItem);
        // }
	});
	$("#pagerDossirResult").kendoPager({
		dataSource : dataSourceDossierResult,
		info : false,
		selectTemplate: '<li class="k-link"><i class="fa fa-circle" aria-hidden="true"></i></li>',
		linkTemplate: '<li><a href="\\#" class="k-link" data-#=ns#page="#=idx#"><i class="fa fa-circle" aria-hidden="true"></i></a></li>'
	});
</script>