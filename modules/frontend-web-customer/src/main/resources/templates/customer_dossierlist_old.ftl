<#if (Request)??>
	<#include "init.ftl">
</#if>
<div class="panel-heading">
	<h3 class="panel-title"> Hồ sơ chờ bổ sung</h3>
</div>
<div class="panel-body	">
	<input type="hidden" name="idItemCustomerDossier" id="idItemCustomerDossier">
	<ul class='ul-with-border'>
		<div id='listViewProfile'></div>
	</ul>
	<div id='pagerProfile'></div>
	<script type="text/x-kendo-template" id="proFileTemplate">
		<li class="itemCustomerDossierList" dataPk="#:id#">
			<div class="row">	
				<div class="col-sm-6">
					<div>
						<div class="row">
							<strong>Mã hồ sơ:</strong>#:serviceCode#
						</div>
						<div class="row">
							<strong>Mã tiếp nhận:</strong>#:referenceUid#
						</div>
						<div class="row">
							#:serviceName#
						</div>
						<div class="row">
							#:govAgencyName#
						</div>
						<div class="row">
							#:applicantIdDate#
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="row">
						<div class="col-sm-12" style="margin-bottom: 10px;">
							<strong>Trích yếu nội dung:</strong> A comprehensive, jQuery based, end-to-end framework for web and mobile apps with built-in DataSource, custom-built from the ground up for reliability and lightning-fast performance.
						</div>
						<div class="col-sm-12">
							<strong>Ghi chú:</strong> #:DossierNote#
						</div>
					</div>
				</div>
			</div>
		</li>
	</script>
</div>

<script type="text/javascript">
	var dataSourceProfile=new kendo.data.DataSource({
		transport:{
			read:function(options){
				$.ajax({
					url:"${api.server}/dossiers",
					dataType:"json",
					type:"GET",
					data:{
						statusCode:options.data.statusCode,
						serviceInfo:options.data.serviceInfo,
						receiptCode:options.data.receiptCode,
						startDate:options.data.startDate,
						endDate:options.data.endDate,
						keyword:options.data.keyword
					},	
					success:function(result){
						options.success(result);
						console.log(options);
					},
					error:function(result){
						console.log(options);
						options.error(result);
					}
				});
			}
		},
		error: function(e) {         
			this.cancelChanges();
		},
		pageSize:7,
		schema:{
			data:"data",
			total:"total",
			model:{
				id:"dossierId"
			}
		}
	});

	$("#listViewProfile").kendoListView({
		dataSource:dataSourceProfile,
		template:kendo.template($("#proFileTemplate").html()),
		selectable: "single",
		/*remove:function(e){
			if(!confirm('Bạn có muốn xóa ?')){
				e.preventDefault();
			}
		},*/
		autoBind: false,
		change:function(){

		}
	});

	$("#pagerProfile").kendoPager({
		dataSource:dataSourceProfile
	});

	$(document).on("click",".itemCustomerDossierList",function(event){
		var id=$(this).attr("dataPk");	
		$("#idItemCustomerDossier").val(id);
		$("#dossier_detail").show();
		$("#dossier_list").hide();
		$("#dossier_detail").load("${ajax.customer_dossier_detail}",function(result){
			dataSourceDossiserFileTemplate.read({
				id:id
			});
			dataSourceDossiserLog.read({
				id:id
			});
		});
	});

</script>