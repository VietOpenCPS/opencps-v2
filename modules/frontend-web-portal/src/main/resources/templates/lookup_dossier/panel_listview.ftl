<#if (Request)??>
    <#include "init.ftl">
</#if>
<!-- List-view hồ sơ đã có kết quả -->
<div class="dossier-with-result box clearfix">
	<div class="col-sm-12 box-title">
		<span>HỒ SƠ ĐÃ CÓ KẾT QUẢ</span>
	</div>
	<div class="col-sm-12 P0">
		<ul class="ul-default" id="listDossierResult"></ul>
		<!-- Template listview -->
		<script type="text/x-kendo-template" id="tempDossierResult">
			<li class="PL15 item-listview hover-pointer text-hover-blue align-middle-lg" dataPk="#:id#">#:applicantName# - #:dossierId#</li>
		</script>
	</div>
	<hr class="col-sm-12 P0 M0 MP15">
	<!-- element handle pagination -->
	<div class="pull-left P5 M0 PL15" id="pagerDossirResult"></div>
</div>
