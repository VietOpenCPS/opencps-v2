<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="modal-dialog modal-lg">
	<!-- Modal content-->
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h4 class="modal-title">Thành Phần Hồ Sơ</h4>
		</div>
		<div class="modal-body">
			<div class="row">
				<div class="col-sm-3 PR0">
					<div class="row">
						<form action="" id="fileArchiveForm">
							<div class="col-sm-12">
								<div class="form-group search-icon">
									<input type="text" class="form-control" placeholder="Nhập từ khóa">
								</div>
							</div>
							<div class="col-sm-12">
								<ul id="listViewDossierFile"></ul>
								<#assign index = 0>
								<script type="text/x-kendo-template" id="templateDossierFiles">
									<div class="accordion" id="accordion1">

										<div class="accordion-group">
											#if(flag){#
											<div class="accordion-heading">
												<a class="accordion-toggle dossier-partno-title" data-toggle="collapse" data-pk="#:dossierPartNo#" data-parent="" href="\\##:dossierPartNo#" aria-expanded="true">
												Part #:dossierPartNo#
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
					<div id="fileCarousel" class="carousel slide" data-ride="carousel" data-interval="false"> 
						<ul class="carousel-inner" id="listViewCarouselDossierFile">
							
						</ul>
						<script type="text/x-kendo-template" id="templateCarouselDossierFile">
							<li class="item" data-pk="#:id#">
								<iframe class="fred" style="border:1px solid \\#666CCC" title="PDF in an i-Frame" src="${api.server}/dossiers/${dossierId}/files/#:id#" frameborder="0" scrolling="auto" height="500" width="100%" >
								</iframe>
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
</div>
<script type="text/javascript">
	$(function(){
		/*		var template = kendo.template($("#template").html());*/
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
				printDossierPartName();
			},
			change: onChange
		});

		$("#listViewCarouselDossierFile").kendoListView({
			dataSource : dataSourceDossierFile,
			template : kendo.template($("#templateCarouselDossierFile").html()),
			selectable : "single",
			change: onChange
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

		$(document).on("click",".btn-delete-component-profile",function(event){
			event.preventDefault();
			var id=$(this).attr("data-pk");
			$.ajax({
				url : "${api.server}/dossiers/${dossierId}/files/"+id,
				type : "DELETE",
				dataType : "json",
				headers : {"groupId": ${groupId}},
				data : {
					id : id
				},
				success:function(result){
					var item = dataSourceDossierFile.get(id);
					if(item){
						dataSourceDossierFile.remove(item);
					}
					console.log(item);
					notification.show({
						message: "Đổi mật khẩu thành công"
					}, "success");

				},
				error:function(result){
					notification.show({
						message: "Xẩy ra lỗi, vui lòng thử lại"
					}, "error");
				}
				
			});
			console.log(id);
		});

		$("#fileInner > li").first().addClass("active");

		$(document).on("click",".view-component-profile",function(event){
			event.preventDefault();
			var index=$(this).attr("data-index");
			console.log(index);
			$('#fileCarousel').carousel(parseInt(index));
		});

		var printDossierPartName = function() {
			$.ajax({
				url : "${api.server}/dossiertemplates/201/parts",
				type : "GET",
				dataType : "json",
				headers : {"groupId": ${groupId}},
				data : {

				},
				async : false,
				success:function(result){
					var data =  result.data;
					for(var i = 0; i<data.length; i++){

						$(".dossier-partno-title [data-pk="+data[i].partNo+"]").html(data[i].partName);
					}
				},
				error:function(result){
					
				}
				
			});
		}

		
	});
</script>