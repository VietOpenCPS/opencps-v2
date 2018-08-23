<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="row nav-tabs-wrapper">
  <ul class="nav nav-tabs">
      <li class="active" id="dossiertemplate_detail_tab_1">
          <a data-toggle="tab" href="#ttmhs">
              Thông tin mẫu hồ sơ
          </a>
      </li>
      <li>
          <a data-toggle="tab" href="#tphs">
              Thành phần hồ sơ
          </a>
      </li>
  </ul>
  <div class="tab-content">
      <div id="ttmhs" class="tab-pane fade in active">
        <#include "dossiertemplate_form.ftl">
      </div>
      <div id="tphs" class="tab-pane fade">
				<div id="dossiertemplate_part_container">
					<#include "dossiertemplate_part.ftl">
				</div>
				<div id="dossiertemplate_part_form_container" style="display: none;">
					<#include "dossiertemplate_part_form.ftl">
				</div>
     </div>
	</div>
 </div>

<script type="text/javascript">
	$(document).on("click", "#dossiertemplate_detail_tab_1", function(event){
		 event.preventDefault();
		 $("#dossiertemplate_part_container").show();
		 $("#dossiertemplate_part_form_container").hide();
	});
</script>
