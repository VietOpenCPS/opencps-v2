      <#if (Request)??>
      <#include "init.ftl">
      </#if>
      <div class="panel">
        <div class="panel-body PT0 PB0">
          <div class="row">
            <div id="listView">
            </div>
          </div>
        </div>
        <script type="text/x-kendo-tmpl" id="templateAdmin">
          #if(domains.length > 0) {#
          <div class="accordion" id=#:'acc1'+govAgencyCode#>
            <div class="accordion-group">
              <div class="accordion-heading">
                <a class="accordion-toggle" data-toggle="collapse" data-parent=#:'\\#acc1'+govAgencyCode# href=#:'\\#a'+govAgencyCode#>
                  <i class="fa fa-university" aria-hidden="true"></i> #:govAgencyName #
                </a>
              </div>
              <div id=#:'a'+govAgencyCode# class="accordion-body collapse in">
                <div class="accordion-inner">
                  <div class="accordion" id=#:'acc2'+govAgencyCode#>
                    #for (var i = 0; i < domains.length; i ++) { #
                    #if(domains[i].serviceConfigs.length > 0) { #
                    <div class="accordion-group">
                      <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent=#:'\\#a'+govAgencyCode# href=#:'\\#' +domains[i].domainCode#> 
                          #:domains[i].domainName#
                        </a>
                      </div>
                      <div id=#:domains[i].domainCode# class="accordion-body collapse in">
                        <div class="accordion-inner">
                          # if(domains[i].serviceConfigs.length > 0) {#
                          # for (var j = 0; j < domains[i].serviceConfigs.length; j ++) {#
                          <div class="eq-height">
                            <div class="col-xs-12 col-sm-10 align-middle">
                              <a class="link-serviceInfo" data-pk="" admt-pk="" href="">
                                #:domains[i].serviceConfigs[j].serviceInfoName#
                              </a>
                            </div>
                            <div class="col-xs-12 col-sm-1 border-left center-all lh32 text-light-gray">
                              Mức #:domains[i].serviceConfigs[j].level#
                            </div>
                            <div class="col-xs-12 col-sm-1 border-left align-center">
                              <button class="btn btn-reset btn-select-serviceInfo" data-pk="" admt-pk="">Chọn</button>
                            </div>
                          </div>
                          #}#
                          #}#
                        </div>
                      </div>
                    </div>
                    #}#
                    #}#
                  </div>
                </div>
              </div>
            </div>
          </div>
          #}#
        </script>
        <script type = "text/javascript">

          $(document).ready(function(){
            var dataSourceAdmin = new kendo.data.DataSource({
              transport: {
                read: function(options) {
                  $.ajax({
                    url: "http://127.0.0.1:8887/modules/frontend-web-customer/src/main/resources/templates/datasource/domain.json",
                    type: "get",
                    dataType: "json",
                    data: {
                      keyword: options.data.keyword,
                    },
                    success: function(result) {
                      options.success(result);
                    },
                    error: function(error) {
                      options.error(error);
                    }
                  });
                }
              },
              schema: {
                total: "total",
                data: "data.serviceconfig.domains",
              }
            });

            $("#listView").kendoListView({
              dataSource : [
              {
                "govAgencyCode": "SVHTTDLSL",
                "govAgencyName": "Sở Văn hóa Thể thao và Du lịch",
                "domains": [
                {
                  "domainCode": "LVVH",
                  "serviceConfigs": [
                  {
                    "serviceInfoId": "3001",
                    "serviceConfigId": "1602",
                    "level": "3",
                    "serviceInfoName": "Cấp Giấy phép nhập khẩu văn hóa phẩm không nhằm mục đích kinh doanh"
                  },
                  {
                    "serviceInfoId": "3001",
                    "serviceConfigId": "1602",
                    "level": "3",
                    "serviceInfoName": "Cấp Giấy phép nhập khẩu văn hóa phẩm không nhằm mục đích kinh doanh"
                  }
                  ],
                  "domainName": "Văn Hóa"
                },
                {
                  "domainCode": "LVVHa",
                  "serviceConfigs": [
                  {
                    "serviceInfoId": "3001",
                    "serviceConfigId": "1602",
                    "level": "3",
                    "serviceInfoName": "Cấp Giấy phép nhập khẩu văn hóa phẩm không nhằm mục đích kinh doanh"
                  },
                  {
                    "serviceInfoId": "3001",
                    "serviceConfigId": "1602",
                    "level": "3",
                    "serviceInfoName": "Cấp Giấy phép nhập khẩu văn hóa phẩm không nhằm mục đích kinh doanh"
                  },
                  {
                    "serviceInfoId": "3001",
                    "serviceConfigId": "1602",
                    "level": "3",
                    "serviceInfoName": "Cấp Giấy phép nhập khẩu văn hóa phẩm không nhằm mục đích kinh doanh"
                  }
                  ],
                  "domainName": "Văn Hóa"
                }
                ]
              }
              ],
              template: kendo.template($("#templateAdmin").html()),

            });


            $('#btn_search').click(function() {

              if($('#btn_fillter_by_admintration').hasClass('btn-active')) {
                var input_Search = $('#input_search').val();
                dataSourceAdmin.read({
                  keyword: input_search,
                });
              }
            });



            $('.btn-select-serviceInfo, .link-serviceInfo').each(function(item){
              $(this).click(function(){
                event.preventDefault();
                var govAgencyCode = $(this).attr("admt-pk");
                var serviceInfoId = $(this).attr("data-pk");
                $("#dossier_detail").load("${ajax.customer_dossier_detail}?${portletNamespace}govAgencyCode=" + govAgencyCode + "&${portletNamespace}serviceInfoId=" + serviceInfoId + "&${portletNamespace}dossierStatus=new", function(result){
                  $("#dossier_detail").show();
                  $("#dossier_list").hide();
                  $("#left_container").hide();
                });
              });
            });
          });
        </script>

