<#if (Request)??>
	<#include "init.ftl">
</#if>
<div class="panel">
  <div class="panel-body PT0 PB0">
    <div class="row">
      <#-- <#if serviceconfig?has_content && serviceconfig.govAgencys?has_content>
        <div class="accordion" id="accordion1">
          <#list serviceconfig.govAgencys as govAgency>
            <div class="accordion-group">
              <div class="accordion-heading">
                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion1" href="#${govAgency.govAgencyCode}">
                  <i class="fa fa-university" aria-hidden="true"></i> ${govAgency.govAgencyName}
                </a>
              </div>
              <div id="${govAgency.govAgencyCode}" class="accordion-body collapse in">
                <div class="accordion-inner">
                  <#if govAgency?has_content && govAgency.domains?has_content>





                    <div class="accordion" id="accordion2">
                      <#list govAgency.domains as domain>
                        <div class="accordion-group">
                          <div class="accordion-heading">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#${govAgency.govAgencyCode}" href="#${domain.domainCode}">
                              ${domain.domainName}
                            </a>
                          </div>
                          <div id="${domain.domainCode}" class="accordion-body collapse in">
                            <div class="accordion-inner">
                              <#if domain?has_content && domain.serviceInfos?has_content>
                                  <#list domain.serviceInfos as serviceInfo>
                                    <div class="eq-height">
                                      <div class="col-xs-12 col-sm-10 align-middle">
                                        <a class="link-serviceInfo" data-pk="${serviceInfo.serviceInfoId}" admt-pk="${govAgency.govAgencyCode}" href="#">
                                          ${serviceInfo.serviceInfoName}
                                        </a>
                                      </div>
                                      <div class="col-xs-12 col-sm-1 border-left center-all lh32 text-light-gray">
                                        <#if serviceInfo.level == 1>
                                        Mức 1
                                        <#elseif serviceInfo.level == 2>
                                        Mức 2
                                        <#elseif serviceInfo.level == 3>
                                        Mức 3
                                        <#elseif serviceInfo.level == 4>
                                        Mức 4
                                        </#if>
                                      </div>
                                      <div class="col-xs-12 col-sm-1 border-left align-center">
                                        <button class="btn btn-reset btn-select-serviceInfo" data-pk="${serviceInfo.serviceInfoId}" admt-pk="${govAgency.govAgencyCode}">Chọn</button>
                                      </div>
                                    </div>
                                  </#list>
                              </#if>
                            </div>
                          </div>
                        </div>
                      </#list>
                    </div>



                  </#if>
                </div>
              </div>
            </div>
          </#list>
        </div>
      </#if> -->
    </div>
  </div>
</div>

<script type = "text/javascript">
  $(document).ready(function(){
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
