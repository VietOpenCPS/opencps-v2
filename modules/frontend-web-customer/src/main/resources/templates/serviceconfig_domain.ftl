<#if (Request)??>
<#include "init.ftl">
</#if>
<input type="hidden" name="serviceConfigId" id="serviceConfigId">
<#-- <div class="panel">
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
													
													Mức ${serviceConfig.level}
													
												</div>

												<div class="col-xs-12 col-sm-1 border-left align-center">
													<button class="btn btn-reset btn-select-serviceConfig" data-pk="${serviceConfig.serviceConfigId}" admt-pk="${serviceInfo.serviceCode}">Chọn</button>
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
</div> -->

<div class="row">
	<div class="accordion" id="serviceConfigs">
	</div>
</div>
<script type="text/x-kendo-template" id="templateServiceConfigDomain">
	<div class="accordion-group">
		#if(typeof domainId !== "undefined"){#
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="\\#serviceConfigs" href="\\##:domainId#">
				<i class="fa fa-briefcase" aria-hidden="true"></i> #:domainName#
			</a>
		</div>
		<div id="#:domainId#" class="accordion-body collapse in">
			<div class="accordion-inner">
				<div class="accordion" id="accordion2">
					
					# for(var i=0 ;i < serviceInfos.length ; i++) {
					var serviceInfo = serviceInfos[i];
					#
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse" data-parent="\\##:domainId#" href="\\##:serviceInfo.serviceCode#">
								#:serviceInfo.serviceName#
							</a>
						</div>
						<div id="#:serviceInfo.serviceCode#" class="accordion-body collapse in">
							<div class="accordion-inner">
								# for (var j = 0; j < serviceInfo.serviceConfigs.length; j++){
								var serviceConfig = serviceInfo.serviceConfigs[i];
								#
								<div class="eq-height">
									<div class="col-xs-12 col-sm-10 align-middle">
										<a class="link-serviceInfo" data-pk="#:serviceConfig.serviceConfigId#" admt-pk="#:serviceInfo.serviceCode#" href="\\#">
											#:serviceConfig.govAgencyName#
										</a>
									</div>

									<div class="col-xs-12 col-sm-1 border-left center-all lh32 text-light-gray">

										Mức #:serviceConfig.level#

									</div>

									<div class="col-xs-12 col-sm-1 border-left align-center">
										<button class="btn btn-reset btn-select-serviceConfig" data-pk="#:serviceConfig.serviceConfigId#" admt-pk="#serviceInfo.serviceCode#">Chọn</button>
									</div>
								</div>
								#}#
							</div>
						</div>
					</div>
					#}#
				</div>
			</div>
		</div>

		#}#
	</div>
</script> 

<script type="text/javascript">
	$(document).ready(function(){
		var dataSourceServiceConfigDomain = new kendo.data.DataSource({
			transport : {
				read : function(options){
					$.ajax({
						url : "${spi.server}/serviceconfigs/domains",
						dataType : "json",
						type : "GET",
						headers : {"groupId": ${groupId}},
						success : function(result){
							options.success(result);
						},
						error : function(result){
							options.error(result);
						}
					});
				}
			},
			schema : {
				data : "domains",
			}
		});

		$("#serviceConfigs").kendoListView({
			dataSource : dataSourceServiceConfigDomain,
			template : kendo.template($("#templateServiceConfigDomain").html()),
			autoBind : true
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

		$('.btn-select-serviceConfig, .link-serviceInfo').unbind().click(function(){
			event.preventDefault();
			var serviceConfigId = $(this).attr("data-pk");
			$("#serviceConfigId").val(serviceConfigId);

			dataSourceProcessServiceConfig.read({
				serviceConfigId : serviceConfigId
			});
		});
	});

</script>
