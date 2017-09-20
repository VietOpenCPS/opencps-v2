<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row">
  <div class="col-xs-12 col-sm-3">
    <div>
      <div id="service_info_tabstrip" class="navtab-menu">
        <ul>
          <li class="k-state-active" value="1">
            <div title="Cơ quan quản lý">CƠ QUAN QUẢN LÝ</div>
          </li>
          <li value="2">
            <div title="Lĩnh vực">LĨNH VỰC</div>
          </li>
          <li value="3">
            <div title="Mức độ">MỨC ĐỘ</div>
          </li>
        </ul>
        <div>
          <ul class="ul-default ul-with-right-icon" id="administration">
             <#-- <#if serviceinfo.administrations?has_content>
            <#list serviceinfo.administrations as administration>
            <li dataPk="${administration.administrationCode}" class='administration'>
              <a href='javascript:;' >${administration.administrationName}</a>
              <div class="btn-group">
                <span>${administration.administrationCount}</span>
              </div>
            </li>
            </#list>
            </#if> -->
            <script type="text/x-kendo-template" id="tempStatisticsAgencies">
              <li dataPk="#:administrationCode#" class='administration'>
                <a href='javascript:;' >#:administrationName#</a>
                <div class="btn-group">
                  <span>#:count#</span>
                </div>
              </li>
            </script>
          </ul>
        </div>
        <div>
          <ul class="ul-default ul-with-right-icon" id="domain">
             <#-- <#if serviceinfo.domains?has_content>
            <#list serviceinfo.domains as domain>
            <li dataPk="${domain.domainCode}" class='domain'>
              <a href='javascript:;' >${domain.domainName}</a>
              <div class="btn-group">
                <span>${domain.domainCount}</span>
              </div>
            </li>
            </#list>
            </#if> -->
            <script type="text/x-kendo-template" id="tempStatisticsDomains">
              <li dataPk="#:domainCode#" class='domain'>
                <a href='javascript:;' >#:domainName#</a>
                <div class="btn-group">
                  <span>#:count#</span>
                </div>
              </li>
            </script>
          </ul>
        </div>
        <div>
         <ul class="ul-default ul-with-right-icon" id="level">
            <#-- <#if serviceinfo.levels?has_content>
            <#list serviceinfo.levels as level>
            <li dataPk="${level.levelCode}" class='level'>
              <a href='javascript:;' >${level.levelName}</a>
              <div class="btn-group">
                <span>${level.levelCount}</span>
              </div>
            </li>
            </#list>
            </#if> -->
            <script type="text/x-kendo-template" id="tempStatisticsLevels">
              <li dataPk="#:level#" class='level'>
                <a href='javascript:;' >Mức độ #:levelName#</a>
                <div class="btn-group">
                  <span>#:count#</span>
                </div>
              </li>
            </script>
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
      animation: { open: { effects: "fadeIn"} },
      activate: function(e){
        if ($(e.item).val()==1) {
          var administrationId = $("#administration > li:first-child").attr("dataPk");
          $("#administration > li:first-child").addClass("active");

          $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){
            $("#administrationCodeSearch").data("kendoComboBox").value(administrationId);
            $("#service_info_list_view").getKendoListView().dataSource.read({
              "administration": administrationId
            });
          });
        }else if($(e.item).val()==2){
          var domainId = $("#domain > li:first-child").attr("dataPk");
          $("#domain > li:first-child").addClass("active");
          $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){
            $("#domainCodeSearch").data("kendoComboBox").value(domainId);
            $("#service_info_list_view").getKendoListView().dataSource.read({
              "domain": domainId
            });
          });
        }else {
          var levelId = $("#level > li:first-child").attr("dataPk");
          $("#level > li:first-child").addClass("active");
          $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){
            $("#levelSearch").data("kendoComboBox").value(levelId);
            $("#service_info_list_view").getKendoListView().dataSource.read({
              "level": levelId
            });
          });
        }
      }
    }).data('kendoTabStrip');

    $(document).on("click",".administration",function(event){
      event.preventDefault();

      $("#administration li").removeClass('active');
      $(this).addClass('active');

      var administrationCode = $(this).attr("dataPk");
      $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){
        $("#administrationCodeSearch").data("kendoComboBox").value(administrationCode);
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
        $("#domainCodeSearch").data("kendoComboBox").value(domainCode);
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
        $("#levelSearch").data("kendoComboBox").value(levelCode);
        $("#service_info_list_view").getKendoListView().dataSource.read({
          "level": levelCode
        });
      });

    });

    //gen statistics agencies,domain,level
    var templateAdministration = kendo.template($("#tempStatisticsAgencies").html());
    var dataSourceAdministrations= new kendo.data.DataSource({
      transport : {
        read : {
          url : "${api.server}/serviceinfos/statistics/agencies",
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
        total : "total"
      },
      change : function(){
        $("#administration").html(kendo.render(templateAdministration, this.view()));
        $("#administration > li:first-child").addClass("active");
      }
    });
    dataSourceAdministrations.read();

    var templateDomains = kendo.template($("#tempStatisticsDomains").html());
    var dataSourceDomains= new kendo.data.DataSource({
      transport : {
        read : {
          url : "${api.server}/serviceinfos/statistics/domains",
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
        total : "total"
      },
      change : function(){
        $("#domain").html(kendo.render(templateDomains, this.view()));
      }
    });
    dataSourceDomains.read();

    var templateLevels = kendo.template($("#tempStatisticsLevels").html());
    var dataSourceLevels= new kendo.data.DataSource({
      transport : {
        read : {
          url : "${api.server}/serviceinfos/statistics/levels",
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
        total : "total"
      },
      change : function(){
        $("#level").html(kendo.render(templateLevels, this.view()));
      }
    });

    dataSourceLevels.read();

    $(document).on("click",".item-serviceinfo",function(event){
      var id=$(this).attr("data-pk");
      console.log(id);
      $("#serviceinfo-right-content").load("${ajax.serviceinfo_detail}",function(result){
        pullDataDetail(id);
      });
    });
  });
</script>
