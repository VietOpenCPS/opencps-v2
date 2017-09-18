<div class="row">
	<div class="col-sm-10 col-sm-offset-1 text-center searchbar">
		<h3 class="text-blue"><i>HỆ THỐNG DỊCH VỤ CÔNG TRỰC TUYẾN</i></h3>
		<div class="form-group search-icon"> <input type="text" class="form-control" placeholder="Nhập tên thủ tục hành chính"> </div>
	</div>
	<div class="col-sm-12">
		<div class="eq-height-lg">
			<div class="row" id="imgDomains">
				
			</div>
		</div>
		<script type="text/x-kendo-template" id="tempDomains">
			<div class="img-domains col-sm-4">
				<img src="<#-- #:link#   -->https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4QFniWC6uanzGbP5uxvBxfX7YTnyVwYz1x6lg4CM0yP3p0lwZFw" class="img-responsive"/>
				<div class="center-all-lg">
					<p><#-- #:domainName# -->Lĩnh vực <span>DU LỊCH</span></p>
				</div>
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
						beforeSend: function(req) {
							req.setRequestHeader('groupId', '20147');
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