<#if (Request)??>Package Control: 
<#include "init.ftl">
</#if>
<div class="row">
	<div class="col-xs-12 col-sm-3 panel P0">
		<div class="panel-body">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<button id="btn_add_registrationtemplate" class="k-button btn-primary form-control" title="Thêm mẫu hồ sơ"><i class="glyphicon glyphicon-plus"></i> Thêm mới </button>
				</div>
			</div>
			<#--  -->
			<div class="row MT10">
				<div class="col-xs-12 col-sm-12">
					<div class="input-group">
						<input type="text" class="form-control" id="input_search_registrationtemplate" placeholder="Tên server, Mã server" title="Nhập Tên server hoặc Mã server">
						<div class="input-group-addon btn-primary" id="btn_search_registration_template" title="Tìm kiếm"><i class="fa fa-search" aria-hidden="true"></i></div>
					</div>
				</div>
			</div>
		</div>

		<div class="col-xs-12 col-sm-12">

			<ul id ="registration_template_list_view" class="ul-with-border ul-with-border-style-2"></ul>

			<div id="registration_template_pager" class="k-pager-wrap"></div>
		</div>

		<script type="text/x-kendo-template" id="registration_template_template">
			<li class="clearfix" data-regTemplateId="#:id#" data-maserver="#:serverNo#" data-tenserver="#:serverName#" data-giaothuc="#:protocol#" style="padding: 10px 0 10px 5px;"  aria-selected="true">
				<div class="P0 col-xs-12 col-sm-12 registration-template-item" data-pk="#: id #">
					<strong class="col-sm-11 PL0 PR5">#: serverName #</strong>
					<a class="btn-group deleteItem col-sm-1 P0" registrationId="#:id#" href="javascript:;" title="Xóa">
						<i aria-hidden="true" class="fa fa-trash"></i>
					</a>
				</div>

			</li>
		</script>
	</div>

	<div class="col-xs-12 col-sm-9">
		<div class="panel panel-heading" style="background-color: #337ab7; color: #ffffff">THÔNG TIN SERVER</div>
		<div class="panel panel-body PT0">
			<#include "registrationtemplates_detail.ftl">
		</div>
	</div>
</div>

<script type="text/javascript">
	
		var registrationTemplateDataSource = new kendo.data.DataSource({
			transport: {
				read: function(options) {
					$.ajax({
						// url: "${api.server}" + "/registrationtemplates",
						// url: "http://localhost:3000/registrationtemplates",
						url:"${api.server}/serverconfigs",
						type: "GET",
						dataType: "json",
						// headers: {"groupId": ${groupId}},
						data: {
							
						},
						success: function(result) {
							options.success(result);
							$("#registration_template_pager .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"})
						}
					});
				},
				parameterMap: function(options, operation) {
					if (operation !== "read" && options.models) {
						return {
							models: kendo.stringify(options.models)
						};
					}
				},
			},
			error: function(e){
				this.cancelChanges();
			},
			schema: {
				total: "total",
				data: "data",
				model : { id: "serverConfigId" }
			},
			pageSize: 10,
			serverPaging: false,
			serverSorting: false,
			serverFiltering: false,
			// sort: { field: "serverConfigId", dir: "desc" }  //sort: sắp xếp
		});

		// var firstTimeLoad = true;
		$("#registration_template_list_view").kendoListView({
			dataSource: registrationTemplateDataSource,
			template: kendo.template($("#registration_template_template").html()),
			selectable: true,
			dataBound: function(e) {
					var listView = e.sender;
					var firstItem = listView.element.children().first();
					listView.select(firstItem);
					// 
					$("#registration_template_pager .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"})
			},
			change: function() {
				var registrationTemplateId, registrationMaserver, registrationTenserver, registrationGiaothuc;
				var index = this.select().index();
				var dataItem = this.dataSource.view()[index];
				registrationTemplateId = this.select().attr("data-regTemplateId");
				registrationMaserver = this.select().attr("data-maserver");
				registrationTenserver = this.select().attr("data-tenserver");
				// registrationThamso = this.select().attr("data-thamso");
				registrationGiaothuc = this.select().attr("data-giaothuc");
				$("#btn_save_registration_template").attr("data-pk", dataItem.id);
				loadregistrationTempDetail(registrationTemplateId, registrationMaserver, registrationTenserver, registrationGiaothuc);
				deleteRegistrationTemp();
				$("#registration_template_pager .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"})
			}
		});
		var deleteRegistrationTemp = function () {
			$(".deleteItem").unbind().click(function(e) {
				e.preventDefault();
				event.stopPropagation();
				var itemId = $(e.currentTarget).attr("registrationId");
				var cf = confirm("Xác nhận xóa mẫu thành phần hồ sơ: "+itemId);
				if (cf) {
					$.ajax({
						// url: "${api.server}/registrationtemplates/" + itemId,
						url:"${api.server}/serverconfigs/"+ itemId,
						type: "DELETE",
						dataType: "json",
						// headers: {"groupId": ${groupId}},
						success: function(result) {
							notification.show({
								message: "Xóa mẫu thành phần hồ sơ thành công!"
							}, "success");
							$("#registration_template_list_view").getKendoListView().dataSource.read();
						},
						error: function(result) {
							notification.show({
								message: "Xảy ra lỗi, vui lòng thử lại"
							}, "error");
						}
					});
				}
			});
		};

		$("#registration_template_pager").kendoPager({
			dataSource: registrationTemplateDataSource,
			buttonCount: 2,
			messages: {
				display: "Hiển thị {0}-{1} trong {2} kết quả",
				empty: "Không có kết quả phù hợp!"
			},
			change: function() {
        $("#registration_template_pager .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"})
      }
		});

		$("#input_search_registrationtemplate").keyup(function(e){
			registrationtemplatesFilter();
		});

		$("#btn_search_registration_template").click(function(){
			registrationtemplatesFilter();
		});

		var registrationtemplatesFilter = function(){
			var inputSearch = $("#input_search_registrationtemplate").val();
			registrationTemplateDataSource.filter({
		    logic: "and",
		    filters: [
	 				
		      {
		        logic: "or",
		        filters: [
		          { field: "serverName", operator: "contains", value: inputSearch },
		      		{ field: "serverNo", operator: "contains", value: inputSearch }
		        ]
		     	}
		    ]
			})
		};
	// -------------
		// Button Add registrationtemplate
		$(document).on("click", "#btn_add_registrationtemplate", function(event){
			event.preventDefault();
			$("#registration_template_list_view li.k-state-selected").removeClass("k-state-selected");
			var viewModel = kendo.observable({
				serverNo : "",
				serverName : "",
				protocol: "",
				lastSync: ""
			});
			kendo.bind($("#registration_template_part_model"), viewModel);
			$("#btn_save_registration_template_part").attr("data-pk", "");
		});
// ---------------------
	  var loadregistrationTempDetail = function(registrationTemplateId, registrationMaserver, registrationTenserver, registrationGiaothuc){
	    
	    var lastSync;
	    $.ajax({
	      // url: "${api.server}" + "/registrationtemplates/" + registrationTemplateId+"/formscript",
	      // url: "http://localhost:3000/registrationformscript",
	      url:"${api.server}/serverconfigs/"+registrationTemplateId+"/configs",
	      type: "GET",
	      dataType: "text",
	      // headers: {"groupId": ${groupId}},
	      async: false,
	      success: function(result) {
	      	lastSync=result;
	      }
	    });
	    

	    var viewModel = kendo.observable({
	      serverName: registrationTenserver,
	      serverNo: registrationMaserver,
	      protocol: registrationGiaothuc,
	      lastSync: lastSync
	    });

	    kendo.bind($("#registration_template_part_model"), viewModel);
	    $("#btn_save_registration_template_part").attr("data-pk", registrationTemplateId);
	   
	  };

</script>
