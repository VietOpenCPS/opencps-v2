<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="panel">
	<div class="panel-body PT0">
		<div class="row">
			<div id="listViewdomain">
            </div>
		</div>
	</div>
</div>
	<script type="text/x-kendo-tmpl" id="templateDomain">
          #if(domains.length > 0) {#
          <div class="accordion" id=#:'acc1'+govAgencyCode#>
            <div class="accordion-group">
              <div class="accordion-heading">
                <a class="accordion-toggle" data-toggle="collapse" data-parent=#:'\\#acc1'+govAgencyCode# href=#:'\\#a'+govAgencyCode#>
                  <i class="fa fa-briefcase" aria-hidden="true"></i> #:govAgencyName #
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

	
<script type="text/javascript">
	$(document).ready(function(){
		var dataSource = new kendo.data.DataSource({
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
						
					});
				}
			},
			schema: {
				total: "total",
				data: "data.serviceconfig",
			}
		});


          $("#listViewdomain").kendoListView({
              dataSource : dataSource,
              template: kendo.template($("#templateDomain").html()),
            
          });

           $('#btn_search').click(function() {
           	var input_Search = $('#input_search').val();
            if($('#btn_fillter_by_domain').hasClass('btn-active')) {
              dataSource.read({
              	keyword: input_search,
              });
            }
          });

		$('.administration-combobox').each(function(item){
			$(this).kendoComboBox({
				filter: "contains",
			});
		});

		$("[data-role=combobox]").each(function() {
			var widget = $(this).getKendoComboBox();
			widget.input.on("focus", function() {
				widget.open();
			});
		});

		$('.btn-select-serviceInfo, .link-serviceInfo').each(function(item){
			$(this).click(function(){
				event.preventDefault();
				var serviceInfoId = $(this).attr("data-pk");
				var administrationCode = $('#govAgencyCode' + serviceInfoId).val();
				$("#left_container").load("${ajax.customer_dossier_detail}?${portletNamespace}administrationCode=" + administrationCode + "&${portletNamespace}serviceInfoId=" + serviceInfoId + "&${portletNamespace}dossierStatus=new", function(result){
					$("#left_container").show();
					$("#dossier_list").hide();
					$("#dossier_detail").hide();
				});
			});
		});
	});
</script>
