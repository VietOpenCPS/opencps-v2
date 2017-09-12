<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row">
  <div class="col-xs-12 col-sm-12 panel P0">
    <div class="row-header">
      <div class="background-triangle-big">DANH SÁCH THỦ TỤC HÀNH CHÍNH</div>
      <span>Hiển thị 5 trên 2055 bản ghi</span>
      <span class="pull-right MT5 MR10">Hiển thị
        <select class="ML5" id="slPageSize">
          <option value="5" selected="">5</option>
          <option value="1">1</option>
          <option value="2">2</option>
        </select>
      </span>
    </div>
    <div class="panel-body">
      <div class="row">
        <div class="col-sm-8">
          <div class="row">
            <div class="col-xs-12 col-sm-4">
              <select class="form-control" id="administrationCodeSearch" name="administrationCodeSearch" placeholder="Chọn cơ quan quản lý">
                <#if serviceinfo?has_content && serviceinfo.administrations?has_content>
                <#list serviceinfo.administrations as administration>
                <option value="${administration.administrationCode}">${administration.administrationName}</option>
                </#list>
                </#if>
              </select>
            </div>
            <div class="col-xs-12 col-sm-4 PL0">
              <select class="form-control" id="domainCodeSearch" name="domainCodeSearch" placeholder="Chọn lĩnh vực thủ tục">
                <#if serviceinfo?has_content && serviceinfo.domains?has_content>
                <#list serviceinfo.domains as domain>
                <option value="${domain.domainCode}">${domain.domainName}</option>
                </#list>
                </#if>
              </select>
            </div>
            <div class="col-xs-12 col-sm-4 PL0">
              <select class="form-control" id="levelSearch" name="levelSearch" placeholder="Chọn mức độ">
                <#if serviceinfo?has_content && serviceinfo.levels?has_content>
                <#list serviceinfo.levels as level>
                <option value="${level.levelCode}">${level.levelName}</option>
                </#list>
                </#if>
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
         <div class="col-sm-7 text-center">
           <b>Tên thủ tục</b>
         </div>
         <div class="col-sm-2 text-center">
           <b>Lĩnh vực thủ tục</b>
         </div>
         <div class="col-sm-2 text-center">
           <b>Mức độ</b>
         </div>
       </li>
     </ul>
     <ul id ="service_info_list_view" class="mimic-table"></ul>
     <div id="service_info_pager" class="k-pager-wrap full-width-pager pull-right PR15 PB15"></div>
   </div>

   <script type="text/x-kendo-template" id="service_info_template">
     <li class="clearfix item-serviceinfo eq-height-lg" data-pk="#: id #" style="padding: 10px 0 10px 5px;" role="option" aria-selected="true">
       <div class="col-sm-1 text-center">
        1
      </div>
      <div class="col-sm-7 service-info-item" data-pk="#: id #">
        #: serviceName #
      </div>
      <div class="col-sm-2 text-center">
        #: domainName #
      </div>
      <div class="col-sm-2 text-center">
        #: maxLevel #
      </div>
    </li>
  </script>
</div>
</div>

<script type="text/javascript">
  (function($){
    var serviceInfoDataSource = new kendo.data.DataSource({
     transport: {
      read: function(options) {
       $.ajax({
        url: "${api.server}" + "/serviceinfos",
        type: "GET",
        dataType: "json",
        contentType : "application/x-www-form-urlencoded",
        beforeSend: function(req) {
          req.setRequestHeader('groupId', '20147');
        },
        data: {
          keyword: options.data.keywords,
          page: options.data.page,
          pageSize: options.data.pageSize,
          administration: options.data.administration,
          domain: options.data.domain,
          level: options.data.level
        },
        success: function(result) {
          options.success(result);
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
  model : { id: "serviceinfoId" },
},
pageSize: 10,
serverPaging: false,
serverSorting: false,
serverFiltering: false
});

    $("#service_info_list_view").kendoListView({
     dataSource: serviceInfoDataSource,
     template: kendo.template($("#service_info_template").html()),
     selectable: true,
     dataBinding: function() {
      record = (this.dataSource.page() -1) * this.dataSource.pageSize();
    },
    dataBound: function(e) {
     var listView = e.sender;
     var firstItem = listView.element.children().first();
     listView.select(firstItem);

        //  the first select dossier template
        //  onSelectDossiertemplate(firstItem.attr("data-pk"));
      }

    });

    $("#service_info_pager").kendoPager({
     dataSource: serviceInfoDataSource,
     buttonCount: 5,
     info: false
   });

    $("#administrationCodeSearch").kendoComboBox({
      dataTextField: "administrationName",
      dataValueField: "administrationCode",
      change: onSearchServiceInfo,
      filter: "contains",
      dataSource: {
        transport :{
          read : {
            url : "${api.server}/serviceinfos/statistics/agencies",
            dataType : "json",
            type : "GET",
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

    $("#domainCodeSearch").kendoComboBox({
      dataTextField: "domainName",
      dataValueField: "domainCode",
      change: onSearchServiceInfo,
      filter: "contains",
      dataSource: {
        transport :{
          read : {
            url : "${api.server}/serviceinfos/statistics/domains",
            dataType : "json",
            type : "GET",
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

    $("#levelSearch").kendoComboBox({
      dataTextField: "levelName",
      dataValueField: "level",
      change: onSearchServiceInfo,
      filter: "contains",
      dataSource: {
        transport :{
          read : {
            url : "${api.server}/serviceinfos/statistics/levels",
            dataType : "json",
            type : "GET",
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
     onSearchServiceInfo();
   });

    $("#btn_search_service_info").click(function(){
     onSearchServiceInfo();
   });

    function onSearchServiceInfo(){
      serviceInfoDataSource.read({
        "administration": $("#administrationCodeSearch").val(),
        "domain": $("#domainCodeSearch").val(),
        "level": $("#levelSearch").val(),
        "keywords": $("#input_search_service_info").val()
      });
    }

    $("#slPageSize").change(function(){
      console.log($(this).val());
      $("#service_info_list_view").getKendoListView().dataSource.pageSize(parseInt($("#slPageSize").val(), 10));
    });

    $("#btn-filter-serviceinfo").click(function(){
      serviceInfoDataSource.read({
        "administration": $("#administrationCodeSearch").val(),
        "domain": $("#domainCodeSearch").val(),
        "level": $("#levelSearch").val(),
        "keywords": $("#input_search_service_info").val()
      });
    });

    $(document).on("click",".item-serviceinfo",function(event){
      var id=$(this).attr("data-pk");
      console.log(id);
      $("#serviceinfo-right-content").load("${ajax.serviceinfo_detail}",function(result){
        pullDataDetail(id);
      });
    });
  })(jQuery);
</script>
