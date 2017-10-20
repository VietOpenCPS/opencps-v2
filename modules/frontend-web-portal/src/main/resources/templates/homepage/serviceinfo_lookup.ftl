<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="row">
	<#-- <div class="col-sm-9 col-sm-offset-1 text-center searchbar">
		<h3 class="text-blue"><i>HỆ THỐNG DỊCH VỤ CÔNG TRỰC TUYẾN</i></h3>
		<div class="input-group MB15">
			<input id="input_search_serviceinfo" type="text" class="form-control" placeholder="Nhập tên thủ tục hành chính">
			<div class="input-group-btn">
				<button class="btn btn-default" type="submit">
					<i class="glyphicon glyphicon-search"></i>
				</button>
			</div>
		</div>
	</div>
	<div class="col-sm-11">
		<div class="eq-height-lg">
			<div class="row" id="imgDomains">
				<div class="img-domains col-sm-4">
					<div class="pic-1"></div>
					<div class="center-all">
						<a href="http://v2.opencps.vn/thu-tuc-hanh-chinh?domain=LVMT">Lĩnh vực <span>VĂN HÓA</span></a>
					</div>
				</div>
				<div class="img-domains col-sm-4">
					<div class="pic-2"></div>
					<div class="center-all">
						<a href="http://v2.opencps.vn/thu-tuc-hanh-chinh?domain=LVATBX">Lĩnh vực <span>THỂ DỤC - THỂ THAO</span></a>
					</div>
				</div>
				<div class="img-domains col-sm-4">
					<div class="pic-3"></div>
					<div class="center-all">
						<a href="http://v2.opencps.vn/thu-tuc-hanh-chinh?domain=LVDL">Lĩnh vực <span>DU LỊCH</span></a>
					</div>
				</div>
			</div>
		</div>
	</div> -->
	<div class="col-sm-12">
		<span class="title">TRA CỨU HỒ SƠ</span>
	</div>
	<div class="input-group col-md-6">
		<input id="input_search_dossierinfo" type="text" class="form-control" placeholder="Nhập mã hồ sơ / Họ và tên">
		<div class="input-group-btn">
			<button class="btn btn-default" id="#filterButton">
				<i class="glyphicon glyphicon-search"></i>
			</button>
		</div>
	</div>
	<#--Render list hồ sơ tìm kiếm theo mã hồ sơ/ họ tên-->
	<div class="demo-section k-content wide">
        <div class="col-sm-12">
			<ul class="PT10 PB5" id="lvDossierResultSearch"></ul>
		</div>	
	</div>
	<!--Render thông tin hồ sơ cơ bản-->
	<div id="detailView"></div>
	
</div>
<script type="text/javascript">
	$(document).ready(function(){
		var dataSourceDossierResultSearch = new kendo.data.DataSource({
			transport: {
			    read: function (options) {
			        $.ajax({
			            url: "${api.server}/dossiers",
			            dataType: "json",
			            type: 'GET',
			            data: {
			            	page: options.data.page,
			                pageSize: options.data.pageSize,
			                param1: options.data.param1,
			                param2: options.data.param2,
			            },
			            success: function (result) {
			                options.success(result);
			            }
			        });
			     }
			}
			schema : {
				total : "total",
				data : "data",
				model : {
					id : "id"
				}
			}
		});
		$("#lvDossierResultSearch").kendoListView({
			dataSource : dataSourceDossierResult,
			template : kendo.template($("#tempDossierResult").html()),
			selectable: true
		});
		var evenSearch = function(){
		    var elementListView = $("#listView").getKendoListView();
		    elementListView.dataSource.read({param1: "value1", param2: "value2"})
		};

	    $("#input_search_dossierinfo").keypress( evenSearch() );
		$(document).on('click', '#filterButton', evenSearch());
	});
</script>
<#-- Render search input -->
<#-- <script type="text/javascript">
	$("#input_search_serviceinfo").kendoAutoComplete({
		dataSource: {
			transport :{
				read : {
					url : "${api.server}/serviceinfos",
					dataType : "json",
					type : "GET",
					beforeSend: function(req) {
						req.setRequestHeader('groupId', ${groupId});
					},
					success : function(result){

					},
					error : function(xhr){

					}
				}
			},
			schema : {
				data : "data",
				total : "total"
			}
		},
		dataTextField: "serviceName",
		filter: "contains",
		placeholder: "Nhập tên thủ tục hành chính",
		noDataTemplate: 'Không có dữ liệu'
	});
</script> -->
