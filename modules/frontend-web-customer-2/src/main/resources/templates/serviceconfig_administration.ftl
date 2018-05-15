<#if (Request)??>
<#include "init.ftl">
</#if>



<input type="hidden" name="serviceConfigId" id="serviceConfigId">
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
					<a data-toggle="collapse" href=#:'\\#a'+govAgencyCode#>
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
									<a data-toggle="collapse" href=#:'\\#' +domains[i].domainCode#> 
										#:domains[i].domainName#
									</a>
								</div>
								<div id=#:domains[i].domainCode# class="accordion-body collapse in">
									<div class="accordion-inner P0">
										# if(domains[i].serviceConfigs.length > 0) {#
										# for (var j = 0; j < domains[i].serviceConfigs.length; j ++) {#
										<div class="eq-height">
											<div class="col-xs-12 col-sm-11 align-middle MR100">
												<a class="link-serviceInfo" data-pk="#:domains[i].serviceConfigs[j].serviceConfigId#" admt-pk="#domains[i].serviceConfigs.serviceConfigId#" href="\\#">
													#:domains[i].serviceConfigs[j].serviceInfoName#
												</a>


											</div>
											<div class="col-xs-12 col-sm-1 border-left ML100 center-all lh32 text-light-gray">
												Mức #:domains[i].serviceConfigs[j].level#
											</div>
											<div class="col-xs-12 col-sm-1 border-left align-center P0">
												<div class="dropdown">
													<button class="btn dropdown-toggle btn-select-serviceConfig" type="button" data-toggle="dropdown" data-pk="#:domains[i].serviceConfigs[j].serviceConfigId#">Chọn
														<span class="caret"></span>
													</button>
													<ul id="dropdown-menu#:domains[i].serviceConfigs[j].serviceConfigId#" class="dropdown-menu" data-pk="#:domains[i].serviceConfigs[j].serviceConfigId#">
														
													</ul>
												</div>

												
												<#-- <button class="btn btn-reset btn-select-serviceConfig text-light-gray" data-pk="#:domains[i].serviceConfigs[j].serviceConfigId#" admt-pk="#:domains[i].serviceConfigs[j].serviceConfigId#">Chọn</button> -->
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
</div>
<script type = "text/javascript">
	var fnGenServiceProcess = function(id,element){
		console.log("pass");
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

	var fnGetParamAndCreateDossier =  function(processOptionId, templateNo){
		var serviceConfigId = $("#serviceConfigId").val();
		if(templateNo && serviceConfigId){
			var dossierTemplateNo = templateNo;

			$.ajax({
				url : "${api.server}/serviceconfigs/"+serviceConfigId,
				dataType : "json",
				type : "GET",
				headers : {"groupId": ${groupId}},
				success : function(result){
					if(result){
						fnCreateDossier(dossierTemplateNo, result.serviceCode, result.govAgencyCode);
					}
				},
				error : function(result){

				}

			});

		} 
	};

	var fnCreateDossier = function(dossierTemplateNo,serviceCode,govAgencyCode){
		$.ajax({
			url : "${api.server}/dossiers",
			dataType : "json",
			type : "POST",
			data : {
				referenceUid : "",
				serviceCode : serviceCode,
				govAgencyCode : govAgencyCode,
				dossierTemplateNo : dossierTemplateNo,
				applicantName : "${(applicant.applicantName)!}",
				applicantIdType : "${(applicant.applicantIdType)!}",
				applicantIdNo : "${(applicant.applicantIdNo)!}",
				applicantIdDate : "01/01/2017 00:00:00",
				address : "${(applicant.address)!}",
				cityCode : "${(applicant.cityCode)!}",
				districtCode : "${(applicant.districtCode)!}",
				wardCode : "${(applicant.wardCode)!}",
				contactName : "${(applicant.contactName)!}",
				contactTelNo : "${(applicant.contactTelNo)!}",
				contactEmail : "${(applicant.contactEmail)!}"
			},
			headers : {"groupId": ${groupId}},
			success : function(result){
				$("#choiseProcessForDossier").modal("hide");

				manageDossier.navigate("/taohosomoi/chuanbihoso/"+result.dossierId);

			},
			error : function(result){
			}

		});
	};

	var dataSourceAdmin;
	$(document).ready(function(){


		var fnGenEventChoiseServiceConfig = function(){
			$('.btn-select-serviceConfig, .link-serviceInfo').unbind().click(function(){
				event.preventDefault();
				var serviceConfigId = $(this).attr("data-pk");
				$("#serviceConfigId").val(serviceConfigId);
			});
		};

		dataSourceAdmin = new kendo.data.DataSource({
			transport: {
				read: function(options) {
					$.ajax({
						url: "${api.server}/serviceconfigs/govagencies",
						type: "GET",
						dataType: "json",
						headers : {"groupId": ${groupId}},
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
				data: "govAgencies",
			}
		});

		$('#btn_search').click(function(){

			var input_Search = $('#input_search').val();
			if ($('#btn_fillter_by_admintration').hasClass('btn-active')){
				dataSourceAdmin.read({
					keyword: input_Search,
				});
			}
		});

		$("#listView").kendoListView({
			dataSource : dataSourceAdmin,
			template: kendo.template($("#templateAdmin").html()),
			autoBind : true,
			dataBound : function(){
				/*fnGenEventChoiseServiceConfig();*/
				fnGenEventChoiseServiceConfig();
				$(".dropdown-menu").each(function(){
					var id = $(this).attr("data-pk");
					fnGenServiceProcess(id, $(this));
				});
			}
		});

	});
</script>

