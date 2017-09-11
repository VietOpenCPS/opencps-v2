<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="row">
  <div class="col-xs-12 col-sm-3">
    <div class="panel">
      <div id="service_info_tabstrip">
        <ul class="ul-with-border ul-with-border-style-2">
          <li class="k-state-active" >
            <div class="col-sm-12" title="Cơ quan quản lý">CƠ QUAN QUẢN LÝ</div>
          </li>
          <li>
            <div class="col-sm-12" title="Lĩnh vực">LĨNH VỰC</div>
          </li>
          <li>
            <div class="col-sm-12" title="Mức độ">MỨC ĐỘ</div>
          </li>
        </ul>
        <div>
          <ul class="ul-default ul-with-border ul-with-right-icon">
            <#if serviceinfo.administrations?has_content>
            <#list serviceinfo.administrations as administration>
            <li dataPk="administration.administrationCode" class='administration'>
              <a href='javascript:;' >${administration.administrationName}</a>
              <div class="btn-group">
                <span class="label label-default PL15 PR15 text-link">${administration.administrationCount}</span>
              </div>
            </li>
            </#list>
            </#if>
          </ul>
        </div>
        <div>
          <ul class="ul-default ul-with-border ul-with-right-icon">
            <#if serviceinfo.domains?has_content>
            <#list serviceinfo.domains as domain>
            <li dataPk="domain.domainCode" class='domain'>
              <a href='javascript:;' >${domain.domainName}</a>
              <div class="btn-group">
                <span class="label label-default PL15 PR15 text-link">${domain.domainCount}</span>
              </div>
            </li>
            </#list>
            </#if>
          </ul>
        </div>
        <div>
          <ul class="ul-default ul-with-border ul-with-right-icon">
            <#if serviceinfo.levels?has_content>
            <#list serviceinfo.levels as level>
            <li dataPk="level.levelCode" class='level'>
              <a href='javascript:;' >${level.levelName}</a>
              <div class="btn-group">
                <span class="label label-default PL15 PR15 text-link">${level.levelCount}</span>
              </div>
            </li>
            </#list>
            </#if>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="col-xs-12 col-sm-9" id="serviceinfo-right-content">
    <#include "serviceinfomain_list.ftl">
  </div>
</div>

<script type="text/javascript">
  $(function() {
    var ts = $("#service_info_tabstrip").kendoTabStrip({
      animation: { open: { effects: "fadeIn"} }
    }).data('kendoTabStrip');

    $("#administration li").first().addClass("active");

    $(document).on("click",".administration",function(event){
      event.preventDefault();

      $("#administration li").removeClass('active');
      $(this).addClass('active');

      var administrationCode = $(this).attr("dataPk");
      $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){
        $("#service_info_list_view").getKendoListView().dataSource.read({
          "administration": administrationCode
        });
      });
      
    });

    $(document).on("click",".domain",function(event){
      event.preventDefault();

      $("#domain li").removeClass('active');
      $(this).addClass('active');

      var domainCode = $(this).attr("dataPk");
      $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){
        $("#service_info_list_view").getKendoListView().dataSource.read({
          "domain": domainCode
        });
      });
      
    });

    $(document).on("click",".level",function(event){
      event.preventDefault();

      $("#level li").removeClass('active');
      $(this).addClass('active');

      var levelCode = $(this).attr("dataPk");

      $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){
        $("#service_info_list_view").getKendoListView().dataSource.read({
          "level": levelCode
        });
      });
      
    });

  });
</script>
