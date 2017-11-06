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
						<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion1" href="#${domain.domainId}1">
							<i class="fa fa-briefcase" aria-hidden="true"></i> ${domain.domainName}
						</a>
					</div>
					<div id="${domain.domainId}1" class="accordion-body collapse in">
						<div class="accordion-inner">
							<#if domain?has_content && domain.serviceInfos?has_content>
							<div class="accordion" id="accordion2">
								<#list domain.serviceInfos as serviceInfo>
								<div class="accordion-group">
									<div class="accordion-heading">
										<a class="accordion-toggle" data-toggle="collapse" data-parent="#${domain.domainId}" href="#${serviceInfo.serviceCode}">
											${serviceInfo.serviceName}
										</a>
									</div>
									<div id="${serviceInfo.serviceCode}" class="accordion-body collapse in">
										<div class="accordion-inner">
											<#if serviceInfo?has_content>
											<#list serviceInfo.serviceConfigs as serviceConfig>
											<div class="eq-height">
												<div class="col-xs-12 col-sm-10 align-middle">
													<a class="link-serviceInfo" data-pk="${serviceConfig.serviceConfigId}" admt-pk="${serviceInfo.serviceCode}" href="#">
														${serviceConfig.govAgencyName}
													</a>
												</div>
												<div class="col-xs-12 col-sm-1 border-left center-all lh32 text-light-gray">
													<#if serviceConfig.level == 1>
													Mức 1
													<#elseif serviceConfig.level == 2>
													Mức 2
													<#elseif serviceConfig.level == 3>
													Mức 3
													<#elseif serviceConfig.level == 4>
													Mức 4
													</#if>
												</div>
												<div class="col-xs-12 col-sm-1 border-left align-center">
													<button class="btn btn-reset btn-select-serviceInfo" data-pk="${serviceConfig.serviceConfigId}" admt-pk="${serviceInfo.serviceCode}">Chọn</button>
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

		$('.btn-select-serviceInfo, .link-serviceInfo').unbind().click(function(){
			event.preventDefault();
			var serviceConfigId = $(this).attr("data-pk");

			$("#choiseProcessForDossier").modal("show");

			dataSourceProcessServiceConfig.read({
				serviceConfigId : serviceConfigId
			});
			
			/*$("#dossier_detail").load("${ajax.customer_dossier_detail}", function(result){
				$("#dossier_list").hide();
				$("#dossier_detail").show();
				$("#left_container").html("");
			});*/
				/*var serviceConfigId = $(this).attr("data-pk");
				var administrationCode = $('#govAgencyCode' + serviceInfoId).val();
				$("#left_container").load("${ajax.customer_dossier_detail}?${portletNamespace}administrationCode=" + administrationCode + "&${portletNamespace}serviceInfoId=" + serviceInfoId + "&${portletNamespace}dossierStatus=new", function(result){
					$("#left_container").show();
					$("#dossier_list").hide();
					$("#dossier_detail").hide();
				});*/
			});
	});
</script>
