<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="row">
	<div class="col-sm-9 col-sm-offset-1 text-center searchbar">
		<div class="input-group MB15">
			<input id="input_search_serviceinfo" type="text" class="form-control" placeholder="Nhập tên thủ tục hành chính">
			<div class="input-group-btn">
				<button class="btn btn-default" type="submit">
					<i class="glyphicon glyphicon-search"></i>
				</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#input_search_serviceinfo").kendoAutoComplete({
		dataSource: {
			transport :{
				read : {
					url : "${api.server}/serviceinfos",
					dataType : "json",
					type : "GET",
					beforeSend: function(req) {
						req.setRequestHeader('groupId', ${groupId});
					},
					success : function(result){
					},
					error : function(xhr){
					}
				}
			},
			schema : {
				data : "data",
				total : "total",
			    model: {
			      fields: {
			        serviceName: {
			          type: "string"
			        }
			      }
			    }
			}
		},
		dataTextField: "serviceName",
		filter: "contains",
		placeholder: "Nhập tên thủ tục hành chính",
		noDataTemplate: 'Không có dữ liệu'
	});
</script>