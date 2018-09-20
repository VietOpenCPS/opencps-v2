<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row">
  <div class="col-xs-12 col-sm-3">
    <div>
      <div id="service_info_tabstrip" class="navtab-menu">
        <ul>
          <li class="<#if (domain??) && (domain ? has_content) ><#else>k-state-active</#if>"  value="1">
            <div title="Cơ quan quản lý">CƠ QUAN QUẢN LÝ</div>
          </li>
          <li class="<#if (domain??) && (domain ? has_content)>k-state-active<#else></#if>" value="2">
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
              #
              var active = "";
              if("${domain}"){
              if("${domain}" == domainCode){
              active = "active";
            }
          }
          #
          <li dataPk="#:domainCode#" class='domain #:active#'>
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
   <#--  <#include "serviceinfomain_list.ftl"> -->
  </div>
</div>

<!-- Modal -->
<div id="instructionModal" class="modal fade" role="dialog">
  <div class="modal-dialog" style="position: relative; margin-top: 100px;">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Hướng dẫn nộp hồ sơ</h4>
      </div>
      <div class="modal-body">
        <p id="modal-content-instruction"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
      </div>
    </div>
  </div>
</div>

<#include "serviceinfo_router.ftl">

<script type="text/javascript">
  var initServiceinfo;
  $(function() {
    var ts = $("#service_info_tabstrip").kendoTabStrip({
      animation: { open: { effects: "fadeIn"} },
      activate: function(e){
        if ($(e.item).val()==1) {
          var administrationId = $("#administration > li:first-child").attr("dataPk");
          $("#administration > li").removeClass("active");
          $("#administration > li:first-child").addClass("active");

          $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){
            var administrationCombobox =  $("#administrationCodeSearch").data("kendoComboBox");
            setValue(administrationCombobox,administrationId);
            administrationCombobox.trigger("change");
            administrationCombobox._isSelect = false;
            $("#service_info_list_view").getKendoListView().dataSource.read({
              "administration": administrationId
            });
          });
        }else if($(e.item).val()==2){
          var domainId = $("#domain > li:first-child").attr("dataPk");
          $("#domain > li").removeClass("active");
          $("#domain > li:first-child").addClass("active");
          $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){
            var domainCombobox=  $("#domainCodeSearch").data("kendoComboBox");
            setValue(domainCombobox,domainId);
            domainCombobox.trigger("change");
            domainCombobox._isSelect = false;
            console.log(domainCombobox);
            $("#service_info_list_view").getKendoListView().dataSource.read({
              "domain": domainId
            });
          });
        }else {
          var levelId = $("#level > li:first-child").attr("dataPk");
          $("#level > li").removeClass("active");
          $("#level > li:first-child").addClass("active");
          $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){
            var levelCombobox = $("#levelSearch").data("kendoComboBox");
            setValue(levelCombobox,levelId);
            levelCombobox.trigger("change");
            levelCombobox._isSelect = false;
            $("#service_info_list_view").getKendoListView().dataSource.read({
              "level": levelId
            });
          });
        }
      },
      scrollable: false
    }).data('kendoTabStrip');

    var setValue = function(combobox,value) {
      combobox.value(value);
    }

    $(document).on("click",".administration",function(event){
      event.preventDefault();
      $("#administration li").removeClass('active');
      $(this).addClass('active');

      var administrationCode = $(this).attr("dataPk");
      $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){
        $("#administrationCodeSearch").data("kendoComboBox").value(administrationCode);
        $("#administrationCodeSearch").data("kendoComboBox")._isSelect=false;
        $("#service_info_list_view").getKendoListView().dataSource.read({
          "administration": administrationCode
        });
        // TODO
        var url = "";
        if (administrationCode) {
          url = "${api.server}/serviceconfigs/pubish/" + administrationCode + "/domains";
          $("#domainCodeSearch").data('kendoComboBox').dataSource.read({
            url: url
          })
        } else {
          url = "${api.server}/serviceinfos/statistics/domains";
          $("#domainCodeSearch").data('kendoComboBox').dataSource.read({
            url: url
          })
        }
      });

    });

    $(document).on("click",".domain",function(event){
      event.preventDefault();

      $("#domain li").removeClass('active');
      $(this).addClass('active');

      var domainCode = $(this).attr("dataPk");
      $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){
        $("#domainCodeSearch").data("kendoComboBox").value(domainCode);
        $("#domainCodeSearch").data("kendoComboBox")._isSelect=false;
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
        $("#levelSearch").data("kendoComboBox")._isSelect=false;
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
        try{
          if(!"${domain}"){
            $("#administration > li:first-child").addClass("active");
            // $("#administrationCodeSearch").data("kendoComboBox").value($("#administration > li:first-child").attr("dataPk"));
            // $("#administrationCodeSearch").data("kendoComboBox")._isSelect = false;
            // $("#service_info_list_view").getKendoListView().dataSource.read({
            //   "administration": $("#administration > li:first-child").attr("dataPk")
            // });
          }
        }catch(err){
          console.log(err);
        }

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
        if("${domain}"){
          $("#domainCodeSearch").data("kendoComboBox").value("${domain}");
          $("#domainCodeSearch").data("kendoComboBox")._isSelect = false;
          $("#service_info_list_view").getKendoListView().dataSource.read({
            "domain": "${domain}"
          });
        }
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
      /*$("#serviceinfo-right-content").load("${ajax.serviceinfo_detail}",function(result){
        console.log(id);
        pullDataDetail(id);
      });*/

      serviceInfoRouter.navigate("/"+id);
    });

    $(document).on("click",".btn-revert",function(){
      funRevert();
    });

    function funRevert () {
      var tabstrip = $("#service_info_tabstrip").data("kendoTabStrip");
      var index=tabstrip.select().index();
      var content=tabstrip.contentElement(index);
      var id = $(content).find('li.active').attr("dataPk");
      console.log(id);

      $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){
        if(index == 2){
          $("#service_info_list_view").getKendoListView().dataSource.read({
            "level": id
          });
          var levelCombobox =  $("#levelSearch").data("kendoComboBox");
          setValue(levelCombobox,id);
          levelCombobox.trigger("change");
          levelCombobox._isSelect = false;
        }else if(index == 1){
         $("#service_info_list_view").getKendoListView().dataSource.read({
          "domain": id
        });
         var domainCombobox =  $("#domainCodeSearch").data("kendoComboBox");
         setValue(domainCombobox,id);
         domainCombobox.trigger("change");
         domainCombobox._isSelect = false;
       }else{
        $("#service_info_list_view").getKendoListView().dataSource.read({
          "administration": id
        });
        var administrationCombobox =  $("#administrationCodeSearch").data("kendoComboBox");
        setValue(administrationCombobox,id);
        administrationCombobox.trigger("change");
        administrationCombobox._isSelect = false;
      }

    });
    }

    $(document).on("click",".showInstruction",function(event){
      var instructionContent=$(this).attr("serviceInstruction");
      console.log(instructionContent);
      $("#modal-content-instruction").html(instructionContent);
      $("#instructionModal").modal("show");
    });


    /*initServiceinfo = function(){
      console.log("init function");

      var administration = getUrlParameter('administration');
      var domain = getUrlParameter('domain');
      var level = getUrlParameter('level');
      var keyword = getUrlParameter('keyword');

      console.log(administration);
      console.log(domain);
      console.log(level);
      console.log(keyword);

      if(domain!=null){
        $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){
          $("#service_info_list_view").getKendoListView().dataSource.read({
            "administration": administration,
            "domain": domain,
            "level": level,
            "keywords": keyword
          });
        });
        var domainCombobox=  $("#domainCodeSearch").data("kendoComboBox");
        console.log(domainCombobox);
        domainCombobox.value(1);
      }

      if(keyword!=null){
        $("#input_search_service_info").val(keyword);
      }
    }

    var getUrlParameter = function getUrlParameter(sParam) {
      var sPageURL = decodeURIComponent(window.location.search.substring(1)),
      sURLVariables = sPageURL.split('&'),
      sParameterName,i;

      for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
          return sParameterName[1] === undefined ? true : sParameterName[1];
        }
      }
    };*/

  });

/*$(window).load(function () {
  initServiceinfo();
});*/
</script>