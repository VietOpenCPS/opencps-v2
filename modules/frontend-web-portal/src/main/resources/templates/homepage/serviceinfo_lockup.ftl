<#-- <style type="tẽt/css">
	.img-domains Ơ
		float: lèt;
		width: 150px;
		height: 130px;
		margin: 0 10px;
	Ư
	.img-domains img Ơ
		width: 110px;
		height: 110px;
	Ư
</style> -->
<div class="row">
	<div class="col-sm-12 text-center">
		<h3><i>HÊ THÔNG DỊCH VỤ CÔNG TRƯC TUYÊN</i></h3>
		<div class="form-group search-icon"> <input type="text" class="form-control" placeholder="Nhập tên thủ rục hành chính"> </div>
	</div>
	<div class="col-sm-12 center-all">
		<div id="imgDomains">
			
		</div>
		<script type="text/x-kendo-template" id="tempDomains">
			<div class="img-domains">
				<img src="<#-- #:link#   -->https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4QFniWC6uanzGbP5uxvBxfX7YTnyVwYz1x6lg4CM0yP3p0lwZFw" />
				<p><#-- #:domainName# -->Lĩnh vực DU LỊCH</p>
			</div>
		</script>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		var templateDomains = kendo.template($("#tempDomains").html());
		var dataSourceDomains = new kendo.data.DataSource({
			transport : {
				read : function(options){
					$.ajax({
						url : "http://hanoi.fds.vn:2281/api/serviceinfos",
						dataType : "json",
						type : "GET",
						success : function(result){
							options.success(result);
						},
						error : function(result){
							options.error(error);
						}
					});
				}
			},
			pageSize : 3,
			schema : {
				total : "total",
				data : "data",
				model : {
					id : "id"
				}
			},
			change: function() { 
				$("#imgDomains").html(kendo.render(templateDomains, this.view())); 
			}
		});
		dataSourceDomains.read();
	});
</script>