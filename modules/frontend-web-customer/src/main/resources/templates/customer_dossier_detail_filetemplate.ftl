<div class="modal-dialog modal-lg">
	<!-- Modal content-->
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h4 class="modal-title">Kho lưu trữ</h4>
		</div>
		<div class="modal-body">

			<div class="row">
				<div class="col-sm-3">
					<div class="row">
						<form action="" id="fileArchiveForm">
							<div class="col-sm-12">
								<div class="form-group search-icon">
									<input type="text" class="form-control" placeholder="Nhập từ khóa">
								</div>
							</div>
							<div class="col-sm-12">
								<ul id="lvDossierFileArchive" style="height: auto; overflow: auto;" data-spy="scroll">

								</ul>
								<script type="text/x-kendo-template" id="dossierFileArchiveTemp">
									<li>
										<div>
											<input class="cbxDossierFile" data-pk="1" type="checkbox"><label>Ảnh 3x4 nền trắng</label> 
										</div> 
									</li>
								</script>
							</div>
							<div class="col-sm-12">
								<button class="btn btn-active" id="btnChoiseFileArchive">Chọn</button>
							</div>
						</form>
					</div>
				</div>
				<div class="col-sm-9">
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
				</script>
			</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
	$(function(){
		var template = kendo.template($("#template").html());

		$(document).on("click",".btn-delete-file",function(event){
			var id=$(this).attr("data-pk");
			console.log(id);
		});

		var dataSourceDossierFileArchive=new kendo.data.DataSource({
			transport : {
				read : {
					url : "${api.server}/dossiers/dossierfiles",
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
				$("#fileCarousel .carousel-inner").html(kendo.render(template, this.view())); 
				$("#fileInner > li").first().addClass("active");
			}
		});

		dataSourceDossierFileArchive.read();

		$("#lvDossierFileArchive").kendoListView({
			dataSource : dataSourceDossierFileArchive,
			template : kendo.template($("#dossierFileArchiveTemp").html()),
			selectable : "single",
			change: onChange
		});

		function onChange() {
			var data = dataSourceDossierFileArchive.view(),
			selected = $.map(this.select(), function(item) {
				console.log($(item).index());
				$('#fileCarousel').carousel($(item).index());
			});
		}

		$("#btnChoiseFileArchive").click(function(){
			var arrChecked=new Array();
			$(".cbxDossierFile").each(function(){
				if($(this).prop('checked')){
					arrChecked.push($(this).attr("data-pk"));
				}
			});
			$("#fileArchiveForm").submit(function(event){
				event.preventDefault();
				console.log(arrChecked.join());
				if(arrChecked.join()!=""){
					$("#uploadFileTemplateDialog").modal("hide");
				}else{
					console.log("Please choise file!");
					return false;
				}
			});
			
			
		});

		$('.control-left').click(function() {
			var index=getCurrentIndexSlide();
			$("#lvDossierFileArchive").data("kendoListView").select(index);

		});

		$('.control-right').click(function() {
			var index=getCurrentIndexSlide();
			$("#lvDossierFileArchive").data("kendoListView").select(index);
		});

		var getCurrentIndexSlide=function(){
			var currentIndex = $('#fileCarousel li.active').index() + 1;
			return currentIndex;
		}
	});
</script>