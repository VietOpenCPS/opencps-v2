<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="">
  <div class="col-xs-12 col-sm-12 panel P0">
    <div class="row-header align-middle">
      <div class="background-triangle-big">DANH SÁCH THỦ TỤC HÀNH CHÍNH</div>
      <span>Hiển thị <span id="numPerPage"></span> trên <span id="totalItem"></span> tổng số thủ tục được tìm thấy</span>
      <span class="show-per-page">Hiển thị
        <span class="select-wrapper">
         <select class="ML5" id="slPageSize">
           <option value="5">5</option>
           <option value="10">10</option>
           <option value="15" selected="">15</option>
           <option value="25">25</option>
           <option value="50">50</option>
         </select>
       </span>
     </span>
   </div>
   <div class="panel-body">
    <div class="row">
      <div class="col-sm-8">
        <div class="row">
          <div class="col-xs-12 col-sm-4">
            <select class="form-control" id="administrationCodeSearch" name="administrationCodeSearch" placeholder="Chọn cơ quan quản lý">
              <#-- <#if serviceinfo.administrations?has_content>
              <#list serviceinfo.administrations as administration>
              <option value="${administration.administrationCode}">${administration.administrationName}</option>
              </#list>
              </#if> -->
            </select>
          </div>
          <div class="col-xs-12 col-sm-4 PL0">
            <select class="form-control" id="domainCodeSearch" name="domainCodeSearch" placeholder="Chọn lĩnh vực thủ tục">
             <#--  <#if serviceinfo.domains?has_content>
              <#list serviceinfo.domains as domain>
              <option value="${domain.domainCode}"> ${domain.domainName}</option>
              </#list>
              </#if> -->
            </select>
          </div>
          <div class="col-xs-12 col-sm-4 PL0">
            <select class="form-control" id="levelSearch" name="levelSearch" placeholder="Chọn mức độ">
              <#-- <#if serviceinfo.levels?has_content>
              <#list serviceinfo.levels as level>
              <option value="${level.levelCode}"> ${level.levelName}</option>
              </#list>
              </#if>-->
            </select>
          </div>
        </div>
      </div>
      <div class="col-xs-12 col-sm-4 PL0">
        <div class="row">
          <div class="col-sm-9">
            <div class="form-group search-icon">
              <input type="text" name="input_search_service_info" id="input_search_service_info" class="form-control input-sm" placeholder="Nhập từ khóa">
            </div>
          </div>
          <div class="col-sm-3">
            <button class="btn btn-active btn-small pull-right" id="btn-filter-serviceinfo" type="button">Tìm kiếm</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div>
    <!-- list view header -->
    <ul class="mimic-table">
      <li class="clearfix">
        <div class="col-sm-1 text-center">
         <b>STT</b>
       </div>
       <div class="col-sm-6 text-center">
         <b>Tên thủ tục</b>
       </div>
       <div class="col-sm-2 text-center">
         <b>Lĩnh vực thủ tục</b>
       </div>
       <div class="col-sm-1 text-center">
         <b>Mức độ</b>
       </div>
       <div class="col-sm-2 text-center">
         <b>Nộp hồ sơ</b>
       </div>
     </li>
   </ul>
   <ul id ="service_info_list_view" class="mimic-table"></ul>
   <div id="service_info_pager" class="k-pager-wrap full-width-pager pull-right PR15 PB15"></div>
 </div>

 <script type="text/x-kendo-template" id="service_info_template">
   <li class="clearfix eq-height-lg" data-pk="#: id #" style="padding: 10px 0 10px 5px;" role="option" aria-selected="true">
     <div class="col-sm-1 text-center center-all">
       #:itemIndex #
     </div>
     <div class="col-sm-6 item-serviceinfo text-hover-blue hover-pointer align-middle-lg" data-pk="#: id #">
      #: serviceName #
    </div>
    <div class="col-sm-2 align-middle-lg text-left">
      #: domainName #
    </div>
    <div class="col-sm-1 align-middle-lg text-left">
      Mức độ #: maxLevel #
    </div>
    <div class="col-sm-2 center-all">
      #if((typeof  serviceConfigs !== 'undefined') ){
          var govAgencyCode = "";
          var govAgencyName = "";
          var serviceInstruction = "";
          var serviceLevel = "";
          var serviceUrl = "";
          var serviceLevel3 = [];
          var serviceLevel2 = [];
          if ( serviceConfigs[1]) {
            for(var i=0; i< serviceConfigs.length;i++){
              <#-- govAgencyCode =  serviceConfigs[i].govAgencyCode;
              govAgencyName =  serviceConfigs[i].govAgencyName;
              serviceInstruction =  serviceConfigs[i].serviceInstruction;
              serviceLevel =  serviceConfigs[i].serviceLevel;
              serviceUrl =  serviceConfigs[i].serviceUrl;
              console.log(serviceLevel);
              console.log(serviceLevel>=3); -->
              if(serviceConfigs[i].serviceLevel>=3){
                serviceLevel3.push(serviceConfigs[i])
              } else {
               serviceLevel2.push(serviceConfigs[i])
              }
            }
            if(serviceLevel3.length > 0)
            {
              #
                 <div class="dropdown">
                    <button class="btn btn-active btn-small dropdown-toggle" type="button" data-toggle="dropdown">Nộp hồ sơ
                      <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu dropdown-menu-right">
                      #
                      if(serviceLevel2.length > 0)
                      {
                        for(var j=0; j< serviceLevel2.length;j++){
                          #
                            <li><a class="showInstruction" href="javascript:;" serviceInstruction="#:serviceInstruction#">
                              #:serviceLevel2[j].govAgencyName#</a></li>
                          #
                        }
                      }
                      for(var j=0; j< serviceLevel3.length;j++){
                        #
                        <li><a href="#:serviceUrl#">#:serviceLevel3[j].govAgencyName#</a></li>
                        #
                      }
                      #
                    </ul>
                  </div>
              #
            } else if(serviceLevel2.length > 0){
                #
                  <div class="dropdown">
                    <button class="btn btn-active btn-small dropdown-toggle" type="button" data-toggle="dropdown">Xem hướng dẫn
                      <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu dropdown-menu-right">
                      #
                      for(var j=0; j< serviceLevel2.length;j++){
                          #
                            <li><a class="showInstruction" href="javascript:;" serviceInstruction="#:serviceInstruction#">
                              #:serviceLevel2[j].govAgencyName#</a></li>
                          #
                        }
                      #
                    </ul>
                  </div>
                #            
            }
          } 
          else {
              govAgencyCode =  serviceConfigs.govAgencyCode;
              govAgencyName =  serviceConfigs.govAgencyName;
              serviceInstruction =  serviceConfigs.serviceInstruction;
              serviceLevel =  serviceConfigs.serviceLevel;
              serviceUrl =  serviceConfigs.serviceUrl;
              var serviceConfigId =  serviceConfigs.serviceConfigId;
              if(serviceLevel>=3){
                #
                 <div class="dropdown">
                  <button class="btn btn-active btn-small dropdown-toggle" type="button" data-toggle="dropdown">Nộp hồ sơ
                    <span class="caret"></span>
                  </button>
                  <ul class="dropdown-menu dropdown-menu-right">
                      <#-- <li><a href="#:serviceUrl#">#:govAgencyName#</a></li> -->
                      <li><a href="/web${(Request.layoutfriendurl)!}/dich-vu-cong\#/add-dvc/#:serviceConfigId#">#:govAgencyName#</a></li>
                  </ul>
                  </div>
                #
              } else {
                #
                  <div class="dropdown">
                  <button class="btn btn-active btn-small dropdown-toggle" type="button" data-toggle="dropdown">Xem hướng dẫn
                    <span class="caret"></span>
                  </button>
                  <ul class="dropdown-menu dropdown-menu-right">
                      <li><a class="showInstruction" href="javascript:;" serviceInstruction="#:serviceInstruction#">
              #:govAgencyName#</a></li>
                  </ul>
                  </div>
                #
              }
          }
      }#

  <#-- #if(true){#
  <button class="btn btn-small btn-active">Nộp hồ sơ</button>
  #}# -->
</div>
</li>
</script>
</div>
</div>

<script type="text/javascript">
  (function($){
    var localIndex = 0;

    var serviceInfoDataSource = new kendo.data.DataSource({
     transport: {
      read: function(options) {
        var page = options.data.page;
        var pageSize = options.data.pageSize;
        var start = (page - 1) * pageSize;
        var end = (page - 1) * pageSize + pageSize;
        var level = 0;
        console.log('options.data.level-------', options.data.level)
        if ($("#levelSearch").val()) {
          level = $("#levelSearch").data('kendoComboBox').value();
        }
       $.ajax({
        url: "${api.server}" + "/serviceinfos",
        type: "GET",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded",
        beforeSend: function(req) {
          req.setRequestHeader('groupId', ${groupId});
        },
        data: {
          keyword: options.data.keywords ? options.data.keywords : $("#input_search_service_info").val(),
          administration: options.data.administration ? options.data.administration : $("#administrationCodeSearch").data('kendoComboBox').value(),
          domain: options.data.domain ? options.data.domain : $("#domainCodeSearch").data('kendoComboBox').value(),
          level: options.data.level ? options.data.level: level,
          start: start,
          end: end
        },
        success: function(result) {
          options.success(result);
          if(parseInt($("#slPageSize").val()) > parseInt(serviceInfoDataSource.total())){
            $("#numPerPage").text(serviceInfoDataSource.total());
            $("#totalItem").text(serviceInfoDataSource.total());
          }else {
            $("#numPerPage").text($("#slPageSize").val());
            $("#totalItem").text(serviceInfoDataSource.total());
          }
        },
        error: function(result) {
          options.error(result);
        }
      });
     },
     create: function(options) {

     },
     update: function(options) {

     },
     destroy: function(options) {

     },
     parameterMap: function(options, operation) {
       if (operation !== "read" && options.models) {
        return {
         models: kendo.stringify(options.models)
       };
     }
   },
 },
 error: function(e){
   this.cancelChanges();
 },
 schema: {
  total: "total",
  data: "data",
  model : { id: "serviceInfoId" }
},
pageSize: 15,
serverPaging: true,
serverSorting: true,
serverFiltering: true
});

    $("#service_info_list_view").kendoListView({
     dataSource: serviceInfoDataSource,
     template: kendo.template($("#service_info_template").html()),
     template: function(data){

      var _pageSize = serviceInfoDataSource.pageSize();

      localIndex++;

      var currentPage = $("#service_info_pager").data("kendoPager").page();
      var totalPage =  $("#service_info_pager").data("kendoPager").totalPages();

      var index = (currentPage-1)*_pageSize + localIndex;

      data.itemIndex = index;

      return kendo.template($("#service_info_template").html())(data);

    },
    dataBinding: function() {
      record = (this.dataSource.page() -1) * this.dataSource.pageSize();
    },
    dataBound: function(e) {
      localIndex=0;
      var listView = e.sender;
      var firstItem = listView.element.children().first();
        //  the first select dossier template
        //  onSelectDossiertemplate(firstItem.attr("data-pk"));
      },
      autoBind : false
    });

    $("#service_info_pager").kendoPager({
     dataSource: serviceInfoDataSource,
     buttonCount: 5,
     info: false
   });

    $("#administrationCodeSearch").kendoComboBox({
      dataTextField: "administrationName",
      dataValueField: "administrationCode",
      filter: "contains",
      dataSource: {
        transport :{
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
        }
      },
      change: function (e) {
        console.log('administrationCodeSearch-------', this.value())
        $("#domainCodeSearch").data('kendoComboBox').select(-1)
        var url = "";
        if (this.value()) {
          url = "${api.server}/serviceconfigs/pubish/" + this.value() + "/domains";
          $("#domainCodeSearch").data('kendoComboBox').dataSource.read({
            url: url
          })
        } else {
          url = "${api.server}/serviceinfos/statistics/domains";
          $("#domainCodeSearch").data('kendoComboBox').dataSource.read({
            url: url
          })
        }
      }
    });

    $("#domainCodeSearch").kendoComboBox({
      dataTextField: "domainName",
      dataValueField: "domainCode",
      filter: "contains",
      dataSource: {
        transport :{
          read : function (options) {
            var url = "";
            if (!options.data.url) {
              url = "${api.server}/serviceinfos/statistics/domains";
            } else {
              url = options.data.url;
            }
            $.ajax({
              url : url,
              dataType : "json",
              type : "GET",
              beforeSend: function(req) {
                req.setRequestHeader('groupId', ${groupId});
              },
              success : function(result){
                if (result.data) {
                  options.success(result)
                } else {
                  options.success({
                    data: [],
                    total: 0
                  })
                }
              },
              error : function(xhr){
                options.error(xhr)
              }
            })
          }
        },
        schema : {
          data : "data",
          total : "total"
        }
      },
      change : function(){
        console.log("change");
      }
    });

    $("#levelSearch").kendoComboBox({
      dataTextField: "levelName",
      dataValueField: "level",
      filter: "contains",
      template: function(data){
        var levelName =  data.levelName;
        if(levelName.toString().indexOf("Mức độ") == -1){
          levelName = "Mức độ " + data.levelName;
          data.levelName = levelName;
        }
        return kendo.template('<span class="k-state-default">#:data.levelName#</span>')(data);
      },
      dataSource: {
        transport :{
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
        }
      }
    });

    // open combobox on focus
    $(function() {
     $("[data-role=combobox]").each(function() {
      var widget = $(this).getKendoComboBox();
      widget.input.on("focus", function() {
       widget.open();
     });
    });
   });

    $("#input_search_service_info").keyup(function(e){
     /*onSearchServiceInfo();*/
   });

    $("#btn_search_service_info").click(function(){
     onSearchServiceInfo();
   });

    function onSearchServiceInfo(){
      var level = ($("#levelSearch").val()=="") ? 0 : $("#levelSearch").val();
      console.log($("#levelSearch").val());
      console.log(level);
      serviceInfoDataSource.read({
        "administration": $("#administrationCodeSearch").val(),
        "domain": $("#domainCodeSearch").val(),
        "level": level,
        "keywords": $("#input_search_service_info").val()
      });
    }

    $("#slPageSize").change(function(){
      console.log($(this).val());
      if(parseInt($("#slPageSize").val()) > parseInt(serviceInfoDataSource.total())){
        $("#numPerPage").text(serviceInfoDataSource.total());
        $("#totalItem").text(serviceInfoDataSource.total());
      }else {
        $("#numPerPage").text($(this).val());
        $("#totalItem").text(serviceInfoDataSource.total());
      }
      $("#service_info_list_view").getKendoListView().dataSource.pageSize(parseInt($("#slPageSize").val(), 10));
    });

    $("#btn-filter-serviceinfo").click(function(){
      var level = ($("#levelSearch").val()==null) ? 0 : $("#levelSearch").val();
      serviceInfoDataSource.read({
        "administration": $("#administrationCodeSearch").val(),
        "domain": $("#domainCodeSearch").val(),
        "level": level,
        "keywords": $("#input_search_service_info").val()
      });
    });

    $("#input_search_service_info").kendoAutoComplete({
      dataSource: {
        transport :{
          read : function (options) {
            $.ajax({
              url : "${api.server}/serviceinfos",
              dataType : "json",
              type : "GET",
              beforeSend: function(req) {
                req.setRequestHeader('groupId', ${groupId});
              },
              data: {
                start: 0,
                end: 15,
                keyword: $("#input_search_service_info").val()
              },
              success : function(result){
                if (result.data) {
                  options.success(result);
                } else {
                  console.log('no data')
                  options.success({
                    data: [],
                    total: 0
                  });
                }
              },
              error : function(xhr){
                options.error(xhr)
              }
            })
          }
        },
        serverFiltering: true,
        schema : {
          data : "data",
          total : "total"
        }
      },
      dataTextField: "serviceName",
      filter: "contains",
      placeholder: "Nhập từ khóa",
      noDataTemplate: "Không có dữ liệu"
    });


  })(jQuery);
</script>
