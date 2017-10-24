<!-- List-view hồ sơ đã có kết quả -->
<div class="dossier-with-result box clearfix">
	<div class="col-sm-12 box-title">
		<span>HỒ SƠ ĐÃ CÓ KẾT QUẢ</span>
	</div>
	<div class="col-sm-12 P0">
		<ul class="ul-default" id="lvDossierResult"></ul>
		<!-- Template listview -->
		<script type="text/x-kendo-template" id="tempDossierResult">
			<li class="PL15 item-listview">#:applicantName# - #:dossierId#</li>
		</script>
	</div>
	<hr class="col-sm-12 P0 M0 MP15">
	<!-- element handle pagination -->
	<div class="pull-left" id="pagerDossirResult"></div>
</div>

<script type="text/javascript">
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
                	var index = this.select().index();
                       dataItem = this.dataSource.view()[index];
                	$("#detailView").load("${ajax.dossierinfo}",
	                   	function(success){
	                   		pullDataDetail(dataItem.dossierId);
	                   	}
                   	);
                   	$("#detailView2").hide();
                },
        dataBound: function(e) {
        	if(dataSourceDossierResult.total() == 1){
        		var listView = e.sender;
            	var firstItem = listView.element.children().first();
            	listView.select(firstItem)
        	}
        }
	});
	$("#pagerDossirResult").kendoPager({
		dataSource : dataSourceDossierResult,
		info : false,
		buttonCount: 5,
		selectTemplate: '<li class="k-link"><i class="fa fa-circle" aria-hidden="true"></i></li>',
		linkTemplate: '<li><a href="\\#" class="k-link" data-#=ns#page="#=idx#"><i class="fa fa-circle" aria-hidden="true"></i></a></li>'
	})
</script>