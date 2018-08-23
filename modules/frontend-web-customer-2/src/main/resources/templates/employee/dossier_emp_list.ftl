<#-- <div class="row-header eq-height">
			
	<div class="col-sm-8 align-middle PL0">
		
		<div class="background-triangle-big">
			<i class="fa fa-leanpub" aria-hidden="true"></i>
		</div> 

		<span id="dossier-emp-list-title">

		</span> 

	</div>
	
	<div class="col-sm-4 align-middle dossier-emp-keyword">

		<div class="input-group">

			<input type="text" class="form-control border-rad-Lside"
				id="dossier-emp-keySearch" name="keywords"
				placeholder="Tìm theo tên, email, điện thoại...">

			<div class="input-group-addon btn-default border-rad-Rside">
				
				<i class="fa fa-search" aria-hidden="true"></i>

			</div>

		</div>

	</div>

</div>

<div class="row M0">

	<div class="col-sm-12 MT15 box box-s3">
		
		<div class="row PT15 PB15">
						
			<div class="form-group row M0 border-bottom PB5 border-color-lightblue align-middle">

				<div class="col-sm-1">
					
					<strong>STT</strong>
				
				</div>
				
				<div class="col-sm-3">
					
					<strong>
						<div>Tên thủ tục</div>
						<div>Cơ quan quản lý</div>

					</strong>
				
				</div>

				<div class="col-sm-1">
					
					<strong>

						<div>Mã hồ sơ</div>
						<div>Số hồ sơ</div>

					</strong>
				
				</div>
				
				<div class="col-sm-1">
					
					<strong>

						<div>Ngày gửi</div>
						<div>Ngày tiếp nhận</div>

					</strong>
				
				</div>

				<div class="col-sm-1">
					
					<strong>

						<div>Số chứng chỉ</div>

					</strong>
				
				</div>

				<div class="col-sm-3">
					
					<strong>

						<div>Nội dung</div>

					</strong>
				
				</div>

				<div class="col-sm-1">
					
					<strong>

						<div>Ghi chú</div>

					</strong>
				
				</div>

				<div class="col-sm-1">
					
					<strong>

						<div>Hành động</div>

					</strong>
				
				</div>

			</div>

			<ul id="dossier-emp-listview">
			</ul>

			<div class="col-sm-12 k-pager-style2 eq-height-lg">
				
				<div class="text-gray MR15 align-middle-lg">
					
					<i>
						Tổng số <span id="dossier-emp-list-counter">0</span> hồ sơ được tìm thấy
					</i>
					
				</div>

				<div class="align-middle-lg">

					<span class="text-nowrap MR10">Hiển thị </span>
				
					<input type="text" name="employee-select-page" id="employee-select-page">
				</div>

				<div id="dossier-emp-listview-pager">
					
				</div>

			</div>

		</div>

	</div>

</div>

<script type="text/x-kendo-tmpl" id="dossier-emp-listview-template">
		
	<li class="PT10 PB10 border-bottom">
	
		<div class="row M0 ">

			<div class="col-sm-1">
				
				#=counter#
					
			</div>
			
			<div class="col-sm-3">
			
				<strong>

					<a href="${(url.dossierEmpPortlet.dossier_emp_list)!}&${portletNamespace}dossierId=#=dossierId#" class="link-detail-employee" data-pk="#=dossierId#" >
				
						#=serviceName#

					</a>

				</strong>

				<br>
				#=govAgencyName#
			</div>

			<div class="col-sm-1">
				# if(dossierNo != null && dossierNo != ""){#
		
					#=dossierNo#

				#} else {#
					<i class="text-gray">Chưa cập nhật</i>
				#}#
				<br>
				# if(dossierId != null && dossierId != ""){#
		
					#=dossierId#

				#} else {#
					<i class="text-gray">Chưa cập nhật</i>
				#}#
			</div>

			<div class="col-sm-1">
				#if ( submitDate!="" && submitDate!=null ) {#
					#= kendo.toString(kendo.parseDate(submitDate, 'yyyy-MM-dd'), 'dd/MM/yyyy')#
				#} else {#
					<i class="text-gray">__/__/____</i>
				#}#
				<br>
				#if ( receiveDate!="" && receiveDate!=null ) {#
					#= kendo.toString(kendo.parseDate(receiveDate, 'yyyy-MM-dd'), 'dd/MM/yyyy')#
				#} else {#
					<i class="text-gray">__/__/____</i>
				#}#
			</div>

			<div class="col-sm-1">
				# if(briefNote != null && briefNote != ""){#
		
					#=briefNote#

				#} else {#
					<i class="text-gray">Chưa cập nhật</i>
				#}#
			</div>

			<div class="col-sm-3">
				-Loại sản phẩm:
				# if(briefNote != null && briefNote != ""){#
		
					#=briefNote#

				#} else {#
					<i class="text-gray">Chưa cập nhật</i>
				#}#
				<br>
				-Tên thuơng mại:
				# if(briefNote != null && briefNote != ""){#
		
					#=briefNote#

				#} else {#
					<i class="text-gray">Chưa cập nhật</i>
				#}#
				<br>
				-Nhãn hiệu/số loại:
				# if(briefNote != null && briefNote != ""){#
		
					#=briefNote#

				#} else {#
					<i class="text-gray">Chưa cập nhật</i>
				#}#
				<br>
				<i class="text-gray">
					Hồ sơ này sẽ giải quyết trong vòng #=dossierId# ngày, khi đó bạn hãy đến cơ quan tiếp nhận hồ sơ để lấy kết quả khi hồ sơ hoàn tất.
				</i>
			</div>

			<div class="col-sm-1">
				# if(briefNote != null && briefNote != ""){#
		
					#=briefNote#

				#} else {#
					<i class="text-gray">Chưa cập nhật</i>
				#}#
			</div>

			<div class="col-xs-1 col-sm-1 text-right">

					<button type="button" class="btn-link no-border download-dossier" data-pk="#:dossierId#"
						data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang tải kết quả...">
						<i class="fa fa-download" aria-hidden="true"/>
						Tải kết quả
					</button>
					
					<button type="button" class="btn-link no-border coppy-dossier" data-pk="#:dossierId#"
						data-loading-text="<i class='fa fa-spinner fa-spin '></i> Đang tải sao chép...">
						<i class="fa fa-file-o" aria-hidden="true"></i>
						Sao chép
					</button>
					
			</div>


		</div>	
			
	 </li>

</script>

<script>
	$(function() {

		var getDossierEmpBaseUrl = "${api.endpoint}/dossiers";
			
		var getDossierEmpDataSource = new kendo.data.DataSource({
			
			transport: {
				
				read: function(options) {
					
					var filterKeys = $("#dossier-emp-listview")[0]["filterKeys"];
					filterKeys = (filterKeys!=null && typeof filterKeys === 'object')?filterKeys:{};
					
					var page = options.data.page;
					var pageSize = options.data.pageSize;
					filterKeys["sort"] = 'createDate';
					filterKeys["sort"] = 'true';
					filterKeys["start"] = (page - 1)*pageSize;
					filterKeys["end"] = (page - 1)*pageSize + pageSize;
					
					
					$.ajax({
					
						url: getDossierEmpBaseUrl,
						dataType: "json",
						type: 'GET',
						headers: {
							"groupId": ${groupId}
						},
						data: filterKeys,
						success: function(result) {
							
							$('#dossier-emp-list-counter').html(result.total);
							setHeader(filterKeys);
							result["data"] = result.total==0 ? []: result["data"];
							options.success(result);
							
						},
						error: function(xhr, textStatus, errorThrown) {
							
							showMessageToastr("error", 'Yêu cầu của bạn xử lý thất bại!');
						
						}
					
					});

				},
				destroy: function(options) {
					
					var confirmWindown = showWindowConfirm('#template-confirm','Cảnh báo','Bạn có chắc muốn xóa bản ghi này?', $("#dossier-emp-listview") );
				
					confirmWindown.then(function(confirmed){
					
						if(confirmed){
	
							$.ajax({
								url: getDossierEmpBaseUrl + "/" + options.data.employeeId,
								type: 'DELETE',
								success: function(result) {
									
									options.success();
									$('#dossier-emp-list-counter').html($("#dossier-emp-listview").getKendoListView().dataSource.total());
									showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
									
								},
								error: function(xhr, textStatus, errorThrown) {
									
									options.error(xhr, textStatus, errorThrown);
									showMessageByAPICode(xhr.status);
								
								}
				
							});
	
						} else{
							
							options.error();
						}
					});
					
				},
				parameterMap: function(options, operation) {
					
					if (operation !== "read" && options.models) {
						return {
							models: kendo.stringify(options.models)
						};
					}
				}
			},
			schema: {
				data: "data",
				total: "total",
				model: {
					id: "dossierId",
					fields: {
						dossierId: {
							editable: false,
							nullable: true
						},
						govAgencyName: {
							type: "string"
						}

					}
				}
			},
			error: function(e) {
				
				this.cancelChanges();
				
			},
			pageSize: 5,
			serverPaging: true,
			serverSorting: true,
			serverFiltering: true
			
		});
		
		var  employeeListView = $("#dossier-emp-listview").kendoListView({
		
			remove: function(e) {
				
			},
			
			dataSource: getDossierEmpDataSource,
			
			template: function(data){

				return kendo.template($("#dossier-emp-listview-template").html())(data);

			}

		});
		
		$("#employee-select-page").kendoDropDownList({
			
			optionLabel: "",
			dataTextField: "text",
			dataValueField: "value",
			dataSource: [
				{text: "5", value: 5 },
				{text: "10", value: 10 },
				{text: "15", value: 15 },
				{text: "25", value: 25 },
				{text: "50", value: 50 }
			],
			select: function (e) {

				var dataItem = this.dataItem(e.item);

				$("#dossier-emp-listview").data("kendoListView").dataSource.pageSize(dataItem.value);

			}
		});

		$("#dossier-emp-listview-pager").kendoPager({
			dataSource: getDossierEmpDataSource,
			input: true,
			numeric: false,
			messages: {
				empty: "Không có kết quả phù hợp!",
				display: "",
				page: "",
				of: "/ {0}"
			}
		});
		
		function setHeader (params) {
		
			var titleHead = "";
			var splitSym = "";
	
			for (var x in params) {
				
				if ( x=="keywords" && params.keywords!= "") {
				
					titleHead = "Tìm kiếm theo từ khoá: " + params.keywords;
				
				} else if ( x.indexOf("_text") > 0 && params[x] != "" && params[x] != null) {
					
					if(!params[x]) delete params[x];
					
					titleHead = titleHead + splitSym + params[x];
					splitSym = ", ";
	
				}
	
			}
			
			if (titleHead == "") {
				titleHead = "Kết quả tìm kiếm hồ sơ.";
			} else {
				titleHead = titleHead + ".";
			}
	
			$('#dossier-emp-list-title').html(titleHead);
		
		};

		$('#dossier-emp-list-wrapper').delegate('button.download-dossier','click', function(event) {

			event.preventDefault();
			event.stopPropagation();
			event.stopImmediatePropagation();

			var dataPK = $(event.currentTarget).attr('data-pk');
			$(event.currentTarget).button('loading');

			fileAttachmentDownload({
				method: "GET",
				url: getDossierEmpBaseUrl + "/"+ dataPK +"/download",
				headers: {"groupId": ${groupId}},
				success: function(sttCode){
					$(event.currentTarget).button('reset');
				},
				error: function(sttCode){
					$(event.currentTarget).button('reset');
					showMessageByAPICode(sttCode);
				}
			});
	
		});

		$('#dossier-emp-list-wrapper').delegate('button.coppy-dossier','click', function(event) {

			event.preventDefault();
			event.stopPropagation();
			event.stopImmediatePropagation();

			var dataPK = $(event.currentTarget).attr('data-pk');
			$(event.currentTarget).button('loading');

			$.ajax({
					
				url: getDossierEmpBaseUrl + "/"+ dataPK +"/cloning",
				dataType: "json",
				type: 'POST',
				headers: {
					"groupId": ${groupId}
				},
				success: function(result) {
					
					console.log("//TODO:");
					$(event.currentTarget).button('reset');
					
				},
				error: function(xhr, textStatus, errorThrown) {
					
					$(event.currentTarget).button('reset');
					showMessageToastr("error", 'Yêu cầu của bạn xử lý thất bại!');
				
				}
			
			});
	
		});
		
	});
 </script> -->