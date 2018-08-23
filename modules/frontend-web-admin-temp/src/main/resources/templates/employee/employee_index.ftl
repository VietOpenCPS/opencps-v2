 <#include "init.ftl">

<div id="employee-index-page">

	<div  class="row">

		<div class="col-md-9 PR0-lg">
	
			<div id="employee-list-screen">
	
				<div class="row">
	
					<div id="employee-list-filter" class="col-sm-3">
						
						<#include "employee_nav.ftl">
						
					</div>
					
					<div id="employee-list-wrapper" class="col-sm-9 PL0-lg">
						
						<#include "employee_list.ftl">
						
					</div>
					
				</div>
				
			</div>
			
		</div>
		
		<div class="col-md-3">
		
			<#include "employee_birthdate.ftl">
	
		</div>
</div>
