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
      #if(serviceInfos.length > 0) {#
      <div class="accordion" id=#:'acc'+domainId#>
        <div class="accordion-group">
          <div class="accordion-heading">
            <a class="accordion-toggle" data-toggle="collapse" data-parent=#:'\\#acc'+domainId# href=#:'\\#a'+domainId#>
              <i class="fa fa-university" aria-hidden="true"></i> #:domainName #
            </a>
          </div>
          <div id=#:'a'+domainId# class="accordion-body collapse in">
            <div class="accordion-inner">
              
              <div class="accordion" id="accordion2">
                #for (var i = 0; i < serviceInfos.length; i ++) { #
                #if(serviceInfos[i].govAgencys.length > 0) { #
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle" data-toggle="collapse" data-parent=#:'\\#a'+domainId# href=#:'\\#' +serviceInfos[i].serviceInfoId#> 
                      #:serviceInfos[i].serviceInfoName#
                    </a>
                  </div>
                  <div id=#:serviceInfos[i].serviceInfoId# class="accordion-body collapse in">
                    <div class="accordion-inner">
                      # if(serviceInfos[i].govAgencys.length > 0) {#
                      # for (var j = 0; j < serviceInfos[i].govAgencys.length; j ++) {#
                      <div class="eq-height">
                        <div class="col-xs-12 col-sm-10 align-middle">
                          <a class="link-serviceInfo" data-pk="" admt-pk="" href="">
                            #:serviceInfos[i].govAgencys[j].govAgencyName#
                          </a>
                        </div>
                        <div class="col-xs-12 col-sm-1 border-left center-all lh32 text-light-gray">
                          Mức #:serviceInfos[i].govAgencys[j].level#
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
              dataSource : dataSourceAdmin,
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

