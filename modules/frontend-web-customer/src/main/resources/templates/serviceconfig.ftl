<#if (Request)??>
	<#include "init.ftl">
</#if>
<div class="panel">
  <div class="">
    <div class="row row MT10 PL10 PR10">
      <div class="col-xs-12 col-sm-3">
        <button id="btn_fillter_by_admintration" class="btn btn-active form-control">Tìm theo cơ quan <i class="fa fa-university ML5" aria-hidden="true"></i>
        </div>
        <div class="col-xs-12 col-sm-3 PL0">
          <button id="btn_fillter_by_domain" class="btn form-control">Tìm theo lĩnh vực <i class="fa fa-archive ML5" aria-hidden="true"></i>
          </div>
          <div class="col-xs-12 col-sm-6 PL0">
           <div class="form-group">
            <div class="input-group">
             <input type="text" class="form-control" id="input_search" placeholder="Nhập từ khóa" title="Nhập từ khóa">
             <div class="input-group-addon" id="btn_search" title="Tìm kiếm">
               <a href="#"><i class="fa fa-search"></i></a>
             </div>
           </div>
         </div>
       </div>
     </div>
   </div>
 </div>
 <div id="serviceconfig_container">
  <#include "serviceconfig_administration.ftl">
</div>

<script type="text/javascript">
  $(document).ready(function(){
    $('#btn_fillter_by_domain').click(function(){
      $('#btn_fillter_by_admintration').removeClass('btn-active');
      $('#btn_fillter_by_domain').addClass('btn-active');
      $('#serviceconfig_container').load("${ajax.serviceconfig_domain}");
    });

    $('#btn_fillter_by_admintration').click(function(){
      $('#btn_fillter_by_admintration').addClass('btn-active');
      $('#btn_fillter_by_domain').removeClass('btn-active');
      $('#serviceconfig_container').load("${ajax.serviceconfig_administration}");
    });

    $('#input_search').change(function(){
      searchServiceInfo();
    });

    $('#btn_search').change(function(){
      searchServiceInfo();
    });

    function searchServiceInfo(){
      if ($('#btn_fillter_by_admintration').hasClass('btn-active')){
        $('#serviceconfig_container').load("${ajax.serviceconfig_domain}&" + $('#input_search').val());
      }
      if ($('#btn_fillter_by_domain').hasClass('btn-active')){
        $('#serviceconfig_container').load("${ajax.serviceconfig_administration}&" + $('#input_search').val());
      }
    }
  });
</script>
