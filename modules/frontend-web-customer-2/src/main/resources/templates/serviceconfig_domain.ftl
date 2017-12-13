<#if (Request)??>
<#include "init.ftl">
</#if>
<input type="hidden" name="serviceConfigId" id="serviceConfigId">
<div class="row">
	<div class="col-xs-12">
		<div class="accordion" id="serviceConfigs">
		</div>
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
					
					# for(var i=0 ;i < govAgencys.length ; i++) {
					var govAgency = govAgencys[i];
					#
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle" data-toggle="collapse" data-parent="\\##:domainId#" href="\\#govAgencyCode_#:govAgency.govAgencyCode#">
								#:govAgency.govAgencyName#
							</a>
						</div>
						<div id="govAgencyCode_#:govAgency.govAgencyCode#" class="accordion-body collapse in">

							<div class="accordion-inner P0">
								# for (var j = 0; j < govAgency.serviceConfigs.length; j++){
								var serviceConfig = govAgency.serviceConfigs[j];
								#
								<div class="eq-height">
									<div class="col-xs-12 col-sm-11 align-middle MR100">
										<a class="link-govAgency" data-pk="#:serviceConfig.serviceConfigId#" admt-pk="#:govAgency.govAgencyCode#" href="\\#">
											#:serviceConfig.serviceName#
										</a>
									</div>

									<div class="col-xs-12 col-sm-1 border-left center-all lh32 text-light-gray ML100">

										Mức #:serviceConfig.level#

									</div>

									<div class="col-xs-12 col-sm-1 border-left align-center P0">
										
										<div class="dropdown">
											<button class="btn dropdown-toggle btn-select-serviceConfig" type="button" data-toggle="dropdown" 
												data-pk="#:serviceConfig.serviceConfigId#" admt-pk="#serviceInfo.serviceCode#">
												
												Chọn
												<span class="caret"></span>
												
											</button>
											<ul id="dropdown-menu#:serviceConfig.serviceConfigId#" class="dropdown-menu" data-pk="#:serviceConfig.serviceConfigId#">
												
											</ul>
										</div>
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
	var dataSourceServiceConfigDomain;
	$(document).ready(function(){

		var fnGenEventChoiseServiceConfig = function(){
			$('.btn-select-serviceConfig, .link-govAgency').unbind().click(function(event){
				
				event.preventDefault();
				var serviceConfigId = $(this).attr("data-pk");
				$("#serviceConfigId").val(serviceConfigId);

				/*dataSourceProcessServiceConfig.read({
					serviceConfigId : serviceConfigId
				});*/
			});
		}

		dataSourceServiceConfigDomain = new kendo.data.DataSource({
			transport : {
				read : function(options){
					$.ajax({
						url : "${api.server}/serviceconfigs/domains",
						dataType : "json",
						type : "GET",
						data: {
							keyword: options.data.keyword
						},
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
			autoBind : true,
			dataBound : function(){
				fnGenEventChoiseServiceConfig();
				$(".dropdown-menu").each(function(){
					var id = $(this).attr("data-pk");
					fnGenServiceProcess(id, $(this));
				});
			}
		});
		
		var fnGenServiceProcess = function(id,element){
			console.log("testtt");
			console.log($(element));
			$.ajax({
				url : "${api.server}/serviceconfigs/"+id+"/processes",
				dataType : "json",
				type : "GET",
				headers : {"groupId": ${groupId}},
				success : function(result){
					$(element).html("");
					if(result.data){
						var data = result.data;
						for (var i = 0; i < data.length; i++) {
							$(element).append('<li><span class="btn-choise-process hover-pointer" data-pk="'+data[i].processOptionId+'" data-template="'+data[i].templateNo+'">'+data[i].optionName+'</span></li>');
						}
					}
					$(".btn-choise-process").unbind().click(function(){
						console.log("choise process");
						var id = $(this).attr("data-pk");
						var templateNo = $(this).attr("data-template");
						fnGetParamAndCreateDossier(id,templateNo);
	
					});
				},
				error : function(result){
	
				}
			});
		};

		$('#btn_search').click(function(){
			var input_Search = $('#input_search').val();
			if ($('#btn_fillter_by_domain').hasClass('btn-active')){
				dataSourceServiceConfigDomain.read({
					keyword: input_Search,
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
		
	});

</script>