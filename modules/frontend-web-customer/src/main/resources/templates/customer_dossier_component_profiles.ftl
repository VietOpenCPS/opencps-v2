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
								<#assign index=0>
								<#if componentProfile?has_content>
								<div class="accordion" id="accordion1">
									<#list componentProfile as componentItem>
									<div class="accordion-group">
										<div class="accordion-heading">
											<a class="accordion-toggle" data-toggle="collapse" data-parent="${componentItem.componentProfileId}" href="#${componentItem.componentProfileId}" aria-expanded="true">
												${componentItem.componentProfileName}
											</a>
										</div>
										<div id="${componentItem.componentProfileId}" class="accordion-body collapse in" aria-expanded="true">
											<#if componentItem?has_content>
											<div class="accordion-inner">
												<#list componentItem.item as componentItemChild>
												<div class="eq-height">
													
													<div class="col-xs-12 col-sm-9 align-middle">
														<a class="view-component-profile" href="#" data-pk="${componentItemChild.id}" data-index="${index}" >
															${componentItemChild.name}
														</a>
													</div>
													<#assign index=index+1>
													<div class="col-xs-12 col-sm-3 align-center">
														<button class="btn btn-reset btn-delete-component-profile" data-pk="${componentItemChild.id}"><i class="fa fa-trash"></i> Xóa</button>
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

								<#-- <ul id="lvDossierComponentProfile" style="height: auto; overflow: auto;" data-spy="scroll">

								</ul>
								<script type="text/x-kendo-template" id="dossierComponentProfileTemp">
									<li>
										<div class="row">
											<div class="col-sm-9">
												<label>Ảnh 3x4 nền trắng</label> 
											</div>
											<div class="col-sm-3">
												<button class="btn btn-bigger btn-reset btn-delete-component-profile" data-pk="1"><i class="fa fa-trash"></i> Xóa</button>
											</div>
										</div> 
									</li>
								</script> -->
							</div>
							<#-- <div class="col-sm-12">
								<button class="btn btn-active" id="btnChoiseFileArchive">Chọn</button>
							</div> -->
						</form>
					</div>
				</div>
				<#-- <div class="col-sm-9">
					<div id="fileCarousel" class="carousel slide" data-ride="carousel" data-interval="false"> 
						<ul class="carousel-inner" id="fileInner">

						</ul>
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
				<script type="text/x-kendo-template" id="template">
					<li class="item" data-pk="1">
						<iframe class="fred" style="border:1px solid \\#666CCC" title="PDF in an i-Frame" src="http://www.egr.msu.edu/classes/ece480/capstone/spring11/group02/documents/matt_appnote.pdf" frameborder="0" scrolling="auto" height="500" width="100%" >
						</iframe>
					</li>
				</script> -->

				<div class="col-sm-9">
					<div id="fileCarousel" class="carousel slide" data-ride="carousel" data-interval="false"> 
						<ul class="carousel-inner" id="fileInner">
							<#if componentProfile?has_content>
								<#list componentProfile as componentItem>
									<#list componentItem.item as item>
										<li class="item" data-pk="1">
											<iframe class="fred" style="border:1px solid \\#666CCC" title="PDF in an i-Frame" src="http://www.egr.msu.edu/classes/ece480/capstone/spring11/group02/documents/matt_appnote.pdf" frameborder="0" scrolling="auto" height="500" width="100%" >
											</iframe>
										</li>
									</#list>
								</#list>
							</#if>
						</ul>
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
		/*var template = kendo.template($("#template").html());

		var dataSourceDossierComponentProfile=new kendo.data.DataSource({
			transport : {
				read : {
					url : "http://localhost:8081/api/dossiers/1/dossierlogs",
					type : "GET",
					dataType : "json"
				}
			},
			schema : {
				data : "data",
				total : "total",
				model : {
					id:"id"
				}
			},
			change: function() { 
				console.log(this.view());
				$("#fileCarousel .carousel-inner").html(kendo.render(template, this.view())); 
				$("#fileInner > li").first().addClass("active");
			}
		});

		dataSourceDossierComponentProfile.read();

		$("#lvDossierComponentProfile").kendoListView({
			dataSource : dataSourceDossierComponentProfile,
			template : kendo.template($("#dossierComponentProfileTemp").html()),
			selectable : "single",
			change: onChange
		});

		function onChange() {
			var data = dataSourceDossierComponentProfile.view(),
			selected = $.map(this.select(), function(item) {
				$('#fileCarousel').carousel($(item).index());
			});
		}*/

		$('.control-left').click(function() {
			var index=getCurrentIndexSlide();
			/*$("#lvDossierComponentProfile").data("kendoListView").select(index);*/
		});

		$('.control-right').click(function() {
			var index=getCurrentIndexSlide();
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
				url:"${api.server}/dossiers/dossierfiles",
				type:"DELETE",
				dataType:"json",
				data:{
					id:id
				},
				success:function(result){

				},
				error:function(result){

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
	});
</script>