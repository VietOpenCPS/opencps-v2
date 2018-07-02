<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="row MT20">
	<div class="col-sm-4">
		<button class="k-button"><i class="glyphicon glyphicon-edit"></i> Tìm theo cơ quan</button>
		<button class="k-button"><i class="glyphicon glyphicon-edit"></i> Tìm theo tên</button>
	</div>
	<div class="col-sm-8">
		<div class="form-group"> 
			<div class="input-group stylish-input-group"> 
				<input type="text" class="form-control" placeholder="Nhập từ khóa"> 
				<span class="input-group-addon"> 
					<button type="submit">
						<span class="glyphicon glyphicon-search icon-search"></span> 
					</button> 
				</span> 
			</div> 
		</div>
	</div>
</div>

<div class="accordion border" id="accordion1">
	<#list customer.domain as itemParren>
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#${itemParren.id}" href="#${itemParren.id}" aria-expanded="true">
				${itemParren.name}
			</a>
		</div>
		<div id="${itemParren.id}" class="accordion-body collapse in" aria-expanded="true">
			<div class="accordion-inner">
				<#list itemParren.item as itemChild>
				<div class="eq-height">
					<div class="col-xs-12 col-sm-8 align-middle">
						<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#">
							${itemChild.name}
						</a>
					</div>
					<div class="col-xs-12 col-sm-1 border-left align-center lh32 text-light-gray">
						Mức 4
					</div>
					<div class="col-xs-12 col-sm-3 border-left text-right">
						<div class="row">
							<select>
								<option>Cục bản quyền tác gỉa</option>
							</select>
							<button class="btn btn-reset btn-choise-domain">Chọn</button>
						</div>
					</div>
				</div>
				</#list>
			</div>
		</div>
	</div>
	</#list>
</div> 

<script type="text/javascript">
	$(document).on("click",".btn-choise-domain",function(event){
		
	});
</script>