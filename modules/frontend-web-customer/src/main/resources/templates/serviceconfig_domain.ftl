<#if (Request)??>
	<#include "init.ftl">
</#if>
<div class="panel">
	<div class="panel-body PT0">
		<div class="row">
			<#if serviceconfig?has_content && serviceconfig.domains?has_content>
				<div class="accordion" id="accordion1">
					<#list serviceconfig.domains as domain>
						<div class="accordion-group">
							<div class="accordion-heading">
								<a class="accordion-toggle" data-toggle="collapse" data-parent="#${domain.domainId}" href="#${domain.domainId}" aria-expanded="true">
									${domain.domainName}
								</a>
							</div>
							<div id="${domain.domainId}" class="accordion-body collapse in" aria-expanded="true">
								<#if domain?has_content && domain.serviceInfos?has_content>
									<div class="accordion-inner">
										<#list domain.serviceInfos as serviceInfo>
										<div class="eq-height">
											<div class="col-xs-12 col-sm-7 align-middle">
												<a class="link-serviceInfo" href="#" data-pk="${serviceInfo.serviceInfoId}">
													${serviceInfo.serviceInfoName}
												</a>
											</div>
											<div class="col-xs-12 col-sm-1 border-left align-center lh32 text-light-gray">
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
											<div class="col-xs-12 col-sm-3 border-left text-right">
												<select class="form-control administration-combobox" name="govAgencyCode${serviceInfo.serviceInfoId}" id="govAgencyCode${serviceInfo.serviceInfoId}" placeholder="Cơ quan thực hiện">
						              <#if serviceInfo.administrations?has_content>
						                <#list serviceInfo.administrations as administration>
						                  <option value="${administration.administrationCode}">${administration.administrationName}</option>
						                </#list>
						              </#if>
												</select>
											</div>
											<div class="col-xs-12 col-sm-1 border-left align-center">
												<button class="btn btn-reset btn-select-serviceInfo" data-pk="${serviceInfo.serviceInfoId}">Chọn</button>
											</div>
										</div>
										</#list>
									</div>
								</#if>
							</div>
						</div>
					</#list>
				</div>
			</#if>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
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
	      $("#left_container").load("${ajax.customer_prepare_file_detail}?${portletNamespace}administrationCode=" + administrationCode + "&${portletNamespace}serviceInfoId=" + serviceInfoId + "&${portletNamespace}dossierStatus=new", function(result){
	          $("#left_container").show();
	          $("#dossier_list").hide();
	          $("#dossier_detail").hide();
	      });
			});
    });
	});
</script>
