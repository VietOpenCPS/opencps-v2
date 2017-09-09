<div class="panel panel-body MT20">
	<input type="hidden" name="idItemEmployeeDossier" id="idItemEmployeeDossier">

	<div class="row" style="border-bottom: 1px solid #ccc; padding-bottom: 5px;">
		<div class="col-sm-2 text-center">
			<div class="row">
				<input type="checkbox" name="cbx2">
				<i class="glyphicon glyphicon-cog dropdown-toggle" data-toggle="dropdown"  style="margin-right: 4px;"></i>
				<ul class="dropdown-menu dropdown-menu-right">
					<li><a href="#">Đánh dấu đã đọc</a></li>
					<li class="divider"></li>
					<li><a href="#">Đánh dấu đã đọc</a></li>
					<li class="divider"></li>
					<li><a href="#">Đánh dấu đã đọc</a></li>
					<li class="divider"></li>
					<li><a href="#">Đánh dấu đã đọc</a></li>
				</ul>
			</div>
		</div>
		<div class="col-sm-10">
			<div class="row">
				<div class="col-sm-8">
					<strong >Hồ sơ chờ tiếp nhận</strong>
				</div>
				<div class="col-sm-4 text-right">
					<i class="glyphicon glyphicon-calendar"></i>
					<i class="glyphicon glyphicon-sort-by-alphabet" id="sortByAlphabet" style="margin:0 3px;"></i>
					<i class="fa fa-arrows-alt" id="fullScreenEmployeeList" status="default"></i>
				</div>
			</div>
		</div>
	</div>
	<ul class='ul-with-border'>
		<div id='listViewProfile'></div>
	</ul>
	<div id='pagerProfile'></div>
	<script type="text/x-kendo-template" id="proFileTemplate">
		<li>
			<div class="row">
				<div class="col-sm-2 text-center">
					<input type="checkbox" name="cbx1"> <br>
					<i class="fa fa-circle blue"></i>
				</div>
				<div class="col-sm-10 generalityContent">
					<div class="itemEmployeeDossierList" dataPk="#:id#">
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
				<div class="extendContent" style="display: none;">
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
					url:"${api.server}/${employee.api.read}",
					dataType:"json",
					type:"GET",
					data:{
						statusCode:options.data.statusCode,
						serviceInfo:options.data.serviceInfo,
						ownerProfile:options.data.ownerProfile,
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
		remove:function(e){
			if(!confirm('Bạn có muốn xóa ?')){
				e.preventDefault();
			}
		},
		autoBind: false,
		change:function(){

		}
	});

	$("#pagerProfile").kendoPager({
		dataSource:dataSourceProfile,
		change: function() {
			if($("#fullScreenEmployeeList").attr("status")=="full"){
				$(".generalityContent").removeClass("col-sm-10");
				$(".generalityContent").addClass("col-sm-5");

				$(".extendContent").addClass("col-sm-5");
				$(".extendContent").show();
			}
		}
	});

	$(document).on("click",".itemEmployeeDossierList",function(event){
		var id=$(this).attr("dataPk");	
		$("#idItemEmployeeDossier").val(id);
		$("#employeemain_dossierdetail").load("${employee.ajax.employeemain_dossierdetail}?idItem="+id+"",function(result){
			dataSourceDossiserFileTemplate.read({
				id:id
			});
			dataSourceDossiserLog.read({
				id:id
			});
		});
	});


	$("#fullScreenEmployeeList").click(function(){
		if($(this).attr("status")=="default"){

			$("#employeemain_dossierlist").removeClass("col-sm-4");
			$("#employeemain_dossierlist").addClass("col-sm-12");

			$("#employeemain_dossierdetail").removeClass("col-sm-8");
			$("#employeemain_dossierdetail").hide();

			$("#employeemain_dossierlist").show();

			$(".generalityContent").removeClass("col-sm-10");
			$(".generalityContent").addClass("col-sm-5");

			//show nội dung mở rộng
			$(".extendContent").addClass("col-sm-5");
			$(".extendContent").show();
			$(this).attr("status","full");
			$(this).removeClass("fa fa-arrows-alt").addClass("fa fa-compress");
		}else{

			//khi ở trạng thái form mặc định đã full
			
			//mở rộng phần nôi dung mặc định
			$(".generalityContent").removeClass("col-sm-5");
			$(".generalityContent").addClass("col-sm-10");

			//ẩn phần nội dung mở rộng
			$(".extendContent").removeClass("col-sm-5");
			$(".extendContent").hide();
			
			$("#employeemain_dossierlist").removeClass("col-sm-12");
			$("#employeemain_dossierlist").addClass("col-sm-4");
			
			$("#employeemain_dossierdetail").addClass("col-sm-8");
			$("#employeemain_dossierdetail").show();

			$("#employeemain_dossierdetail").show();
			$(this).attr("status","default");
			$(this).removeClass("fa fa-compress").addClass("fa fa-arrows-alt");
		}
	});

</script>