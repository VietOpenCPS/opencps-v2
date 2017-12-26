<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row">
	<input type="text" class="form-control" id="govAgencyCodeSearch" name="govAgencyCodeSearch">
	<button class="btn btn-active form-control" id="btn-update-dossierbusiness">Cập nhật hồ sơ Doanh Nghiệp</button>
	<ul id="lsRegistrationsLogs" class="ul-with-border">
		
	</ul>
	<script type="text/x-keno-template" id="registrationsTemplate">
		<li class="registrationsLogItem">
			 <p> Lần #:content#</p>
		</li>
	</script>
</div>

<script type="text/javascript">

	$("#btn-update-dossierbusiness").click(function(){
		var indexRegistrationsPart = 0 ;
		$("#lsRegistrationtemplates").kendoListView({
			dataSource : dataSourceRegistrationsTemplate,
			change : function(){

			},
			template : function(data){

				if(!data.removed){
					indexRegistrationsPart ++;
					data.itemIndex = indexRegistrationsPart;
				}

				return kendo.template($("#templateRegistrationtemplates").html())(data);

			},
			dataBound : function(){
				indexRegistrationsPart = 0;
			}
		});
	});

	var fnGetRegistrationsId =  function(){
		var registrationId = 0;
		$.ajax({
			url : "${api.server}/registrations",
			dataType : "json",
			type : "GET",
			headers : {"groupId": ${groupId}},
			async : false,
			data : {
				owner : true
			},
			success : function(result){
				console.log(result);
				if(result.data){
					registrationId = result.data[0].registrationId;
					console.log(registrationId);
				}
			},
			error : function(result){

			}
		});

		return registrationId;
	}

	$("#govAgencyCodeSearch").kendoComboBox({
		placeholder : "Chọn cơ quan đăng ký",
		dataTextField : "itemName",
		dataValueField : "itemCode",
		noDataTemplate : "Không có dữ liệu",
		autoBind : true,
		dataSource : {
			transport : {
				read : function(options){
					$.ajax({
						url : "${api.server}/dictcollections/GOVERNMENT_AGENCY/dictitems",
						dataType : "json",
						type : "GET",
						headers: {"groupId": ${groupId}},
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
				data : "data",
				total : "total"
			}
		},
		change : function(e){
			var value = this.value();
			console.log(value);

			/*if(value){
				dataSourceRegistrationsLogs.read({
					agency : value
				});
			}*/

		}

	});

	var dataSourceRegistrationsLogs = new kendo.data.DataSource({
		transport :{
			read : function(options){
				var registrationId = fnGetRegistrationsId();

				$.ajax({
					url : "${api.server}/registrations/"+registrationId+"/logs",
					dataType : "json",
					type : "GET",
					headers : {"groupId": ${groupId}},
					data : {

					},
					success : function(result){
						options.success(result);
						/*ì(result.dât)Ơ
							vả payLoad = result.dât[0ư.payload;
							vả payLoadObj = JSON.parse(payLoad);
							$("#lsRegistrationtemplates").getKendoListView().dataSource.read(payLoadObj);
						}else Ơ

						Ư*/
					},
					error : function(result){
						options.error(result);
					}
				});
			}
		},
		schema : {
			total : "total",
			data : "data",
			model : {
				id : "registrationLogId"
			}
		}
	});

	$("#lsRegistrationsLogs").kendoListView({
		dataSource : dataSourceRegistrationsLogs,
		selectable: "single",
		change : function(){

			console.log("change logs");
			var data = dataSourceRegistrationsLogs.view(),
			selected = $.map(this.select(), function(item) {
				return data[$(item).index()].payload;
			});
			console.log(selected);
			if(selected){
				console.log("selected");
				var payLoadObj = JSON.parse(selected);

				console.log(payLoadObj);
				var dataSourceRegistrationsLogsTemplate = new kendo.data.DataSource({
					data : payLoadObj,
					schema : {
						model : {
							id : "referenceUid"
						}
					}
				});

				var indexRegistrationsPart =0 ;
				$("#lsRegistrationtemplates").kendoListView({
					dataSource : dataSourceRegistrationsLogsTemplate,
					change : function(){

					},
					template : function(data){
						console.log("template");
						
						/*var dataObj = JSON.parse(data);
						console.log(dataObj);
						console.log("not removed");*/
						indexRegistrationsPart ++;
						data.itemIndex = indexRegistrationsPart;

						return kendo.template($("#templateRegistrationtemplatesLogs").html())(data);

					},
					dataBound : function(){
						indexRegistrationsPart = 0;
					}
				});

			}
		},
		template :  kendo.template($("#registrationsTemplate").html()),
		dataBound : function(){
			
		}
	});

	var fnCreateRegistrations = function(govAgencyCode){
		var registrationId = 0;
		$.ajax({
			url : "${api.server}/registrations",
			dataType : "json",
			type : "POST",
			headers : {"groupId": ${groupId}},
			async : false,
			data : {
				applicantName : "${(applicant.applicantName)!}",
				applicantIdType : "${(applicant.applicantIdType)!}",
				applicantIdNo : "${(applicant.applicantIdNo)!}",
				applicantIdDate : "${(applicant.applicantIdDate)!}",
				address : "${(applicant.address)!}",
				cityCode : "${(applicant.cityCode)!}",
				districtCode : "${(applicant.districtCode)!}",
				wardCode : "${(applicant.wardCode)!}",
				contactName :"${(applicant.contactName)!}",
				contactTelNo : "${(applicant.contactTelNo)!}",
				contactEmail : "${(applicant.contactEmail)!}",
				govAgencyCode : govAgencyCode,
				/*registrationState : 1,
				submitting : true*/
			},
			success : function(result){
				registrationId = result.registrationId;
				manageRegistrations.navigate("/registration/"+result.registrationId);
			},
			error : function(result){

			}
		});

		return registrationId;
	}

	$(function(){
		var registrationId = fnGetRegistrationsId();
		if(parseInt(registrationId) == 0){
			console.log("create!");
			registrationId = fnCreateRegistrations("BGTVTCDKVN");
			manageRegistrations.navigate("/registration/"+registrationId);
		}else {
			console.log("has registration!");
			manageRegistrations.navigate("/registration/"+registrationId);
		}
	});
</script>