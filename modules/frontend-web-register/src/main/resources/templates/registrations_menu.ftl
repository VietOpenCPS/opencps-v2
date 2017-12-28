<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row">
	
	<div class="col-sm-12">
		<div class="hidden">
			<input 
				class="form-control"
				data-role="combobox"
				data-placeholder="Chọn cơ quan đăng ký"
				data-text-field="itemName"
				data-value-field="itemCode"
				data-bind="value: govAgency.value,
						source: govAgency.dataSource,
						events: {
							change: govAgency.change
						}"
			/>
	
			<div data-bind="visible: show, text: show"></div>
		</div>

		<button class="btn btn-active form-control" 
			data-bind="click: updateDossierBusiness">
			Cập nhật hồ sơ Doanh Nghiệp
		</button>

		<ul id="registrationsListView"
			data-role="listview"
			
			data-template="registrationsTemplate"
			data-bind="source: registrationsListView_dataSource,
				events: {
					change: registrationsListView_change
				}"

		></ul>
		
		<script type="text/x-keno-template" id="registrationsTemplate">
		
			<li class="clearfix PT10 PR0 PB10 PL10 registrationsLogItem hover-pointer"
				data-pk="#= registrationId#"
				data-bind="events: {
					click: registrationsListView_change
				}">
	
				<div class="col-sm-2 clearfix PL0 PR0">
					
					<a href="javascript:;" >
								
						<i class="fa fa-book fs20 P5" aria-hidden="true"></i>
							
					</a>
						
				</div>
					
				<div class="col-sm-9 PL0">
				
					<strong class="btn-block">#= applicantName #</strong>
					<span class="btn-block">#= applicantIdType #</span>
				
				</div>
					
				<span class="col-sm-1 PL0 PR0"></span>
				
			 </li>
			
		</script>
		
	</div>
</div>
