<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="row">
	<div class="col-xs-12 col-sm-3 panel P0">
		<div class="panel-body">
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<button id="btn_add_serverconfigs" class="k-button btn-primary form-control" title="Thêm mẫu hồ sơ"><i class="glyphicon glyphicon-plus"></i> Thêm mới </button>
				</div>
			</div>
			<#--  -->
			<div class="row MT10">
				<div class="col-xs-12 col-sm-12">
					<div class="input-group">
						<input type="text" class="form-control" id="input_search_serverconfigs" placeholder="Tên server, Mã server" title="Nhập Tên server hoặc Mã server">
						<div class="input-group-addon btn-primary" id="btn_search_serverconfigs" title="Tìm kiếm"><i class="fa fa-search" aria-hidden="true"></i></div>
					</div>
				</div>
			</div>
		</div>

		<div class="col-xs-12 col-sm-12">

			<ul id ="serverconfigs_list_view" class="ul-with-border ul-with-border-style-2"></ul>

			<div id="serverconfigs_pager" class="k-pager-wrap"></div>
		</div>

		<script type="text/x-kendo-template" id="serverconfigs_template">
			<li class="clearfix" data-serverconfigs="#:id#" data-maserver="#:serverNo#" data-tenserver="#:serverName#" data-giaothuc="#:protocol#" style="padding: 10px 0 10px 5px;"  aria-selected="true">
				<div class="P0 col-xs-12 col-sm-12 serverconfigs-item" data-pk="#: id #">
					<strong class="col-sm-11 PL0 PR5">#: serverName #</strong>
					<a class="btn-group deleteItem col-sm-1 P0" serverconfigsId="#:id#" href="javascript:;" title="Xóa">
						<i aria-hidden="true" class="fa fa-trash"></i>
					</a>
				</div>

			</li>
		</script>
	</div>

	<div class="col-xs-12 col-sm-9">
		<div class="panel panel-heading" style="background-color: #337ab7; color: #ffffff">THÔNG TIN SERVER</div>
		<div class="panel panel-body PT0">
			<#include "serverconfigs_detail.ftl">
		</div>
	</div>
</div>

<script type="text/javascript">
	
		var serverconfigsDataSource = new kendo.data.DataSource({
			transport: {
				read: function(options) {
					$.ajax({
						// url: "${api.server}" + "/registrationtemplates",
						// url: "http://localhost:3000/registrationtemplates",
						url:"${api.server}/serverconfigs",
						type: "GET",
						dataType: "json",
						headers: {"groupId": ${groupId}},
						data: {
							
						},
						success: function(result) {
							options.success(result);
							$("#serverconfigs_pager .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"})
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
		$("#serverconfigs_list_view").kendoListView({
			dataSource: serverconfigsDataSource,
			template: kendo.template($("#serverconfigs_template").html()),
			selectable: true,
			dataBound: function(e) {
					var listView = e.sender;
					var firstItem = listView.element.children().first();
					listView.select(firstItem);
					// 
					$("#serverconfigs_pager .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"})
			},
			change: function() {
				var serverconfigId, serverconfigsMaserver, serverconfigsTenserver, serverconfigsGiaothuc;
				var index = this.select().index();
				var dataItem = this.dataSource.view()[index];
				serverconfigId = this.select().attr("data-serverconfigs");
				serverconfigsMaserver = this.select().attr("data-maserver");
				serverconfigsTenserver = this.select().attr("data-tenserver");
				// registrationThamso = this.select().attr("data-thamso");
				registrationGiaothuc = this.select().attr("data-giaothuc");
				$("#btn_save_serverconfigs").attr("data-pk", dataItem.id);
				loadserverconfigsDetail(serverconfigId, serverconfigsMaserver, serverconfigsTenserver, serverconfigsGiaothuc);
				deleteServerconfigs();
				$("#serverconfigs_pager .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"})
			}
		});
		var deleteServerconfigs = function () {
			$(".deleteItem").unbind().click(function(e) {
				e.preventDefault();
				event.stopPropagation();
				var itemId = $(e.currentTarget).attr("serverconfigsId");
				var cf = confirm("Xác nhận xóa mẫu thành phần hồ sơ: "+itemId);
				if (cf) {
					$.ajax({
						// url: "${api.server}/registrationtemplates/" + itemId,
						url:"${api.server}/serverconfigs/"+ itemId,
						type: "DELETE",
						dataType: "json",
						headers: {"groupId": ${groupId}},
						success: function(result) {
							notification.show({
								message: "Xóa mẫu thành phần hồ sơ thành công!"
							}, "success");
							$("#serverconfigs_list_view").getKendoListView().dataSource.read();
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

		$("#serverconfigs_pager").kendoPager({
			dataSource: serverconfigsDataSource,
			buttonCount: 2,
			messages: {
				display: "Hiển thị {0}-{1} trong {2} kết quả",
				empty: "Không có kết quả phù hợp!"
			},
			change: function() {
        $("#serverconfigs_pager .k-link").css({"border-radius":"0","border-color":"#ddd","height":"27px","margin-right":"0px"})
      }
		});

		$("#input_search_serverconfigs").keyup(function(e){
			serverconfigsFilter();
		});

		$("#btn_search_serverconfigs").click(function(){
			serverconfigsFilter();
		});

		var serverconfigsFilter = function(){
			var inputSearch = $("#input_search_serverconfigs").val();
			serverconfigsDataSource.filter({
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
		$(document).on("click", "#btn_add_serverconfigs", function(event){
			event.preventDefault();
			$("#serverconfigs_list_view li.k-state-selected").removeClass("k-state-selected");
			var viewModel = kendo.observable({
				serverNo : "",
				serverName : "",
				protocol: "",
				lastSync: ""
			});
			kendo.bind($("#serverconfigs_part_model"), viewModel);
			$("#btn_save_serverconfigs_part").attr("data-pk", "");
		});
// ---------------------
	  var loadserverconfigsDetail = function(serverconfigId, serverconfigsMaserver, serverconfigsTenserver, serverconfigsGiaothuc){
	    
	    var lastSync;
	    $.ajax({
	      // url: "${api.server}" + "/registrationtemplates/" + registrationTemplateId+"/formscript",
	      // url: "http://localhost:3000/registrationformscript",
	      url:"${api.server}/serverconfigs/"+serverconfigId+"/configs",
	      type: "GET",
	      dataType: "text",
	      headers: {"groupId": ${groupId}},
	      async: false,
	      success: function(result) {
	      	lastSync=result;
	      }
	    });
	    

	    var viewModel = kendo.observable({
	      serverName: serverconfigsTenserver,
	      serverNo: serverconfigsMaserver,
	      protocol: serverconfigsGiaothuc,
	      lastSync: lastSync
	    });

	    kendo.bind($("#serverconfigs_part_model"), viewModel);
	    $("#btn_save_serverconfigs_part").attr("data-pk", serverconfigId);
	   
	  };

</script>
