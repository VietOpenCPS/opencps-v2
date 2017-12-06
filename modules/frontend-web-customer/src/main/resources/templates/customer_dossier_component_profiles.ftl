<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="modal-dialog modal-full">
	<!-- Modal content-->
	<div class="modal-content">

		<div class="modal-body eq-height">
			<div class="row eq-height M0 full-width">

				<div class="col-sm-3 box no-border-radius">
					<div class="row" style="margin-bottom : 3px;">
						<div class="col-sm-12 PT15 PB15" style="background-color: #ccc;">
							<span class="text-bold">Thành phần hồ sơ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <span>#${(dossierId)!}</span> <i class="fa fa-times pull-right hover-pointer" aria-hidden="true" data-dismiss="modal" style="font-size: 150%;"></i>
						</div>
					</div>
					<div class="row">
						<form action="" id="fileArchiveForm">
							<div class="col-sm-12 P0">
								<ul id="listViewDossierFile"></ul>
								<#assign index = 0>
								<script type="text/x-kendo-template" id="templateDossierFiles">
									<div class="accordion" id="accordion1">

										<div class="accordion-group">
											#
											console.log("flag");
											console.log(flag);
											if(flag){#

											<div class="accordion-heading">
												<a class="accordion-toggle dossier-partno-title" data-toggle="collapse" id="partNo#:dossierPartNo#" data-pk="#:dossierPartNo#" data-parent="" href="\\##:dossierPartNo#" aria-expanded="true">

												</a>
											</div>
											#}#

											#
											var collapseIn = "";
											if(dossierPartNo === "${dossierPartNo}"){ 
											collapseIn = "in";
										}
										#

										<div id="#:dossierPartNo#" class="accordion-body collapse #:collapseIn#" aria-expanded="true">

											<div class="accordion-inner">

												<div class="eq-height">

													<div class="col-xs-12 col-sm-9 align-middle">
														<a class="view-component-profile #if('${index}' === '0'){# active #}#" href="javascript:;" data-pk="#:referenceUid#" data-index="${index}" >
															#:displayName#
														</a>
													</div>

													<div class="col-xs-12 col-sm-3 align-center">
														<button class="btn btn-reset btn-delete-component-profile" data-pk="#:id#"><i class="fa fa-trash"></i> Xóa</button>
													</div>
												</div>

											</div>

										</div>
									</div>
									<#assign index = index + 1>
								</div>
							</script>

						</div>
					</form>
				</div>
			</div>

			<div class="col-sm-9">
				<div id="fileCarousel" class="carousel slide row" data-ride="carousel" data-interval="false"> 
					<ul class="carousel-inner" id="listViewCarouselDossierFile">

					</ul>
					<script type="text/x-kendo-template" id="templateCarouselDossierFile">
						<li class="item" data-pk="#:id#">
							
							<object data="${api.server}/dossiers/${dossierId}/files/#:id#/preview" type="application/pdf" width="100%" height="100%">
								<embed width="100%" height="100%" name="plugin" src="${api.server}/dossiers/${dossierId}/files/#:id#/preview">
								</object>
							</li>
						</script>
						<a class="left carousel-control control-left" href="#fileCarousel" data-slide="prev">
							<span class="glyphicon glyphicon-chevron-left"></span>
							<span class="sr-only">Previous</span>
						</a>
						<a class="right carousel-control control-right" href="#fileCarousel" data-slide="next">
							<span class="glyphicon glyphicon-chevron-right"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
				</div>

			</div>
		</div>

	</div>

</div>

<script type="text/javascript">
	$(function(){
		/*		var template = kendo.template($("#template").html());*/
		var fnBindClickDeleteFile = function(dataSource){
			$(".btn-delete-component-profile").unbind().click(function(event){
				event.preventDefault();
				var id=$(this).attr("data-pk");
				var cf = confirm("Bạn có muốn xóa tệp tin này!");
				if(cf){
					$.ajax({
						url : "${api.server}/dossiers/${dossierId}/files/"+id,
						type : "DELETE",
						dataType : "json",
						headers : {"groupId": ${groupId}},
						data : {

						},
						success:function(result){

							dataSource.pushDestroy(result);

							notification.show({
								message: "Xóa thành công!"
							}, "success");

						},
						error:function(result){
							notification.show({
								message: "Xẩy ra lỗi, vui lòng thử lại"
							}, "error");
						}

					});
				}
				
				console.log(id);
			});
		}

		var flag = 0;
		var dataSourceDossierFile=new kendo.data.DataSource({
			transport : {
				read : function(options) {
					$.ajax({
						url : "${api.server}/dossiers/${dossierId}/files",
						type : "GET",
						dataType : "json",
						headers : {"groupId": ${groupId}},
						success : function (result) {
							options.success(result);
							if(!result.data){
								$("#profileDetail").modal("hide");

								notification.show({
									message: "Bạn chưa tải file lên, Vui lòng tải lên để xem"
								}, "error");
							}else {
								$("#profileDetail").modal("show");
							}
						},
						error : function (result) {
							options.error(result);
						}
					});
				}
			},
			schema : {
				data : "data",
				total : "total",
				model : {
					id : "referenceUid"
				}
			},
			change: function() { 
				
			}
		});

		var dataSourceDossierFileCarousel=new kendo.data.DataSource({
			transport : {
				read : function(options) {
					$.ajax({
						url : "${api.server}/dossiers/${dossierId}/files",
						type : "GET",
						dataType : "json",
						headers : {"groupId": ${groupId}},
						success : function (result) {
							options.success(result);
						},
						error : function (result) {
							options.error(result);
						}
					});
				}
			},
			schema : {
				data : "data",
				total : "total",
				model : {
					id:"referenceUid"
				}
			},
			change: function() { 
				/*console.log(this.view());
				$("#fileCarousel .carousel-inner").html(kendo.render(template, this.view())); 
				$("#fileInner > li").first().addClass("active");*/
			}
		});

		/*dataSourceDossierComponentProfile.read();*/

		$("#listViewDossierFile").kendoListView({
			dataSource : dataSourceDossierFile,
			template : function(data) {
				if(flag !== data.dossierPartNo){
					data.flag = true;
					flag = data.dossierPartNo;
				}else {
					data.flag = false;
				}
				return kendo.template($("#templateDossierFiles").html())(data);
			},
			selectable : "single",
			dataBound : function(e) {
				console.log(e);
				printDossierPartName();
				fnBindClickDeleteFile($("#listViewDossierFile").getKendoListView().dataSource);
			},
			change: onChange
		});

		$("#listViewCarouselDossierFile").kendoListView({
			dataSource : dataSourceDossierFileCarousel,
			template : kendo.template($("#templateCarouselDossierFile").html()),
			selectable : "single",
			change: onChange,
			dataBound : function(e){
				$("#listViewCarouselDossierFile > li").first().addClass("active");
			}
		});

		function onChange() {
			/*var data = dataSourceDossierFile.view(),
			selected = $.map(this.select(), function(item) {
				$('#fileCarousel').carousel($(item).index());
			});*/
		}

		$('.control-left').click(function() {
			var index=getCurrentIndexSlide();
			console.log(index);
			/*$("#lvDossierComponentProfile").data("kendoListView").select(index);*/
		});

		$('.control-right').click(function() {
			var index=getCurrentIndexSlide();
			console.log(index);
			/*$("#lvDossierComponentProfile").dât("kendoListView").select(index);*/
		});

		var getCurrentIndexSlide=function(){
			var currentIndex = $('#fileCarousel li.active').index() + 1;
			return currentIndex;
		}


		$("#fileInner > li").first().addClass("active");

		$(document).on("click",".view-component-profile",function(event){
			event.preventDefault();
			var index=$(this).attr("data-index");
			console.log(index);
			$('#fileCarousel').carousel(parseInt(index));
		});

		var printDossierPartName = function() {
			$.ajax({
				url : "${api.server}/dossiertemplates/"+$("#dossierTemplateNo").val(),
				type : "GET",
				dataType : "json",
				headers : {"groupId": ${groupId}},
				data : {

				},
				async : false,
				success:function(result){
					var data =  result.dossierParts;
					console.log(data);
					if (data) {
						for(var i = 0; i<data.length; i++){

							$("#partNo"+data[i].partNo).html(data[i].partName);
						}
					}
				},
				error:function(result){
					
				}
				
			});
		}

		
	});
</script>