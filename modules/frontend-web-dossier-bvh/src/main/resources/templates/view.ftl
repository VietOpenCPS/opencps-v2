
<#include "init.ftl">
<input type="hidden" name="employeeTitle" value="${(employee.title)!}">
<input type="hidden" name="employeeFullName" value="${(employee.fullName)!}">
<div class="application theme--light">
  	<object id="plugin0" type="application/x-cryptolib05plugin" width="0" height="0"></object>
	<div id="dossierViewJX" style="width: 100%;"> </div>
	
</div>
<style>
	.table-bordered table.table thead tr {
		height: 42px;
		background: #dae8e8;
	}
	body .table-bordered {
		border: 1px solid lightgray;
		border-right: 0;
		border-top: 0;
	}
	body .table-bordered tbody > tr > td {
		border-right: 1px solid lightgray;
		vertical-align: top;
	}
	body .table-bordered thead > tr > th {
		border-right: 1px solid lightgray;
		vertical-align: top;
	}

	.table-bordered table.table tbody td:first-child, 
	.table-bordered table.table tbody td:not(:first-child), 
	.table-bordered table.table tbody th:first-child, 
	.table-bordered table.table tbody th:not(:first-child), 
	.table-bordered table.table thead td:first-child, 
	.table-bordered table.table thead td:not(:first-child), 
	.table-bordered table.table thead th:first-child, 
	.table-bordered table.table thead th:not(:first-child) {
		padding: 2px 5px;
	}
</style>
<script type="text/javascript">
	document.addEventListener('DOMContentLoaded', function (event) {
		var govAgencyCode = "${(govAgencyCode)!}";

		funLoadVue(govAgencyCode);
	});

</script>