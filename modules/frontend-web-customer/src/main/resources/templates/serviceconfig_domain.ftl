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
										<a class="accordion-toggle" data-toggle="collapse" data-parent="#${domain.domainId}" href="#${serviceInfo.serviceInfoId}">
											${serviceInfo.serviceInfoName}
										</a>
									</div>
									<div id="${serviceInfo.serviceInfoId}" class="accordion-body collapse in">
										<div class="accordion-inner">
											<#if serviceInfo?has_content>
											<#list serviceInfo.govAgencys as govAgency>
											<div class="eq-height">
												<div class="col-xs-12 col-sm-10 align-middle">
													<a class="link-serviceInfo" data-pk="${govAgency.govAgencyCode}" admt-pk="${serviceInfo.serviceInfoId}" href="#">
														${govAgency.govAgencyName}
													</a>
												</div>
												<div class="col-xs-12 col-sm-1 border-left center-all lh32 text-light-gray">
													<#if govAgency.level == 1>
													Mức 1
													<#elseif govAgency.level == 2>
													Mức 2
													<#elseif govAgency.level == 3>
													Mức 3
													<#elseif govAgency.level == 4>
													Mức 4
													</#if>
												</div>
												<div class="col-xs-12 col-sm-1 border-left align-center">
													<button class="btn btn-reset btn-select-serviceInfo" data-pk="${govAgency.govAgencyCode}" admt-pk="${serviceInfo.serviceInfoId}">Chọn</button>
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
