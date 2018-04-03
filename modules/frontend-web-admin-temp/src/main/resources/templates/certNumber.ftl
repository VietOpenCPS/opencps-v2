<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row box MT10">
	<div class="col-sm-12 MT15" id="viewLsCertNumber">
		<button class="btn btn-active MB15" id="btn-add-certnumber">Thêm tham số hệ thống</button> <br>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th class="text-center">STT</th>
					<th class="text-center">Tên tham số</th>
					<th class="text-center">Năm</th>
					<th class="text-center">Pattern</th>
					<th class="text-center">Số khởi tạo </th>
					<th class="text-center">Hành động</th>
				</tr>
			</thead>
			<tbody id='certNumberListView'>

			</tbody>
		</table>
		<div id='pagerCertNumber'></div>
		<script type="text/x-kendo-template" id="templateCertNumber">
			<tr>
				<td class="text-center" style="width: 8%;">#:itemIndex#</td>
				<td class="text-center hover-pointer item-certnumber" data-pk="#:id#" style="width: 25%;">
					#:id#
				</td>
				<td class="text-center">#:year#</td>
				<td class="text-center" style="width: 25%;">#:pattern#</td>
				<td class="text-center">#:initNumber#</td>
				<td class="text-center"><a class="certNumberItemDelete" href="javascript:;" data-pk="#:id#"><i class="fa fa-trash"></i> Xóa</a></td>
			</tr>
		</script>
	</div>
	<div class="col-sm-12 MT15" style="display: none;" id="certNumBerDetail">
		<div class="row MB15">
			<div class="col-sm-3 text-right">
				<span class="text-bold">Tên tham số:</span>
			</div>
			<div class="col-sm-9">
				<input  name="certId" id="certId" class="form-control" data-bind="value : certId" readonly>
			</div>
		</div> 
		<div class="row MB15">
			<div class="col-sm-3 text-right">
				<span class="text-bold">Năm:</span>
			</div>
			<div class="col-sm-9">
				<input  name="year" id="year" class="form-control" data-bind="value : year" required="required" validationMessage="Bạn phải chọn năm">
				<span data-for="year" class="k-invalid-msg"></span> 
			</div>
		</div>
		<div class="row MB15">
			<div class="col-sm-3 text-right">
				<span class="text-bold">Pattern:</span>
			</div>
			<div class="col-sm-9">
				<input  name="pattern" id="pattern" class="form-control" data-bind="value : pattern" required="required" validationMessage="Bạn phải điền pattern">
				<span data-for="pattern" class="k-invalid-msg"></span> 
			</div>
		</div>
		<div class="row MB15">
			<div class="col-sm-3 text-right">
				<span class="text-bold">Số khởi tạo:</span>
			</div>
			<div class="col-sm-9">
				<input  name="initNumber" id="initNumber" class="form-control" data-bind="value : initNumber" required="required" validationMessage="Bạn phải điền số khởi tạo">
				<span data-for="initNumber" class="k-invalid-msg"></span> 
			</div>
		</div>
		<div class="row MB15">
			<div class="col-sm-12 text-center">
				<button class="btn btn-sm btn-active" data-bind="attr : { data-pk : certId}" id="btn-update-cert">Đồng ý</button>
				<button class="btn btn-sm" id="btn-back-certnumber">Quay lại</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">

	var dataSourceCertNumber = new kendo.data.DataSource({
		transport : {
			read : function(options){
				$.ajax({
					url : "/o/rest/vr-app/certnumbers",
					dataType : "json",
					type : "GET",
					headers: {"groupId": ${(groupId)!}},
					success : function(result){
						if(result.data){
							options.success(result);
						}else {
							options.success({
								data : [],
								total : 0
							});
						}
					},
					error : function(result){
						options.error(result);
					}
				});
			}
		},
		pageSize : 10,
		schema : {
			data : "data",
			total : "total",
			model : {
				id : "certId"
			}
		}
	});

	var indexCertNumber = 0;
	$("#certNumberListView").kendoListView({
		dataSource : dataSourceCertNumber,
		template : function(data){
			indexCertNumber ++;

			data.itemIndex = indexCertNumber;

			return kendo.template($("#templateCertNumber").html())(data);
		},
		selectable : "single"
	});

	$("#pagerCertNumber").kendoPager({
		dataSource: dataSourceCertNumber,
		buttonCount: 2,
		messages: {
			display: "Hiển thị {0}-{1} trong {2} kết quả",
			empty: "Không có kết quả phù hợp!"
		}
	});

	$(document).off("click",".item-certnumber");
	$(document).on("click",".item-certnumber",function(event){
		var id = $(this).attr("data-pk");
		if(id){
			$.ajax({
				url : "/o/rest/vr-app/certnumbers/"+id,
				dataType : "json",
				type : "GET",
				headers: {"groupId": ${groupId}},
				success : function(result){
					var viewModel = kendo.observable({
						certId : result.certId,
						year : result.year,
						pattern : result.pattern,
						initNumber : result.initNumber
					});

					kendo.bind($("#certNumBerDetail"), viewModel);
					$("#certNumBerDetail").show();
					$("#viewLsCertNumber").hide();
					
				},
				error : function(result){
					
				}
			});
		}
	});

	var fnCertDetailFormCtr = function(id){
		var url = "";
		var type = "";
		if(id){
			url = "/o/rest/vr-app/certnumbers/"+id;
			type = "PUT";
		}else {
			url = "/o/rest/vr-app/certnumbers";
			type = "POST";
		}
		var certId = $("#certId").val();
		var year = $("#year").val();
		var pattern = $("#pattern").val();
		var initNumber = $("#initNumber").val();
		$.ajax({
			url : url,
			dataType : "json",
			type : type,
			headers: {"groupId": ${groupId}},
			data : {
				year : year,
				pattern : pattern,
				initNumber : initNumber
			},
			success : function(result){
				if(id){
					//dataSourceCertNumber.pushUpdate(result);
					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");
				}else {
					//dataSourceCertNumber.pushCreate(result);
					notification.show({
						message: "Yêu cầu được thực hiện thành công"
					}, "success");
				}
				$("#certNumBerDetail").show();
				$("#viewLsCertNumber").hide();

			},
			error : function(result){
				notification.show({
					message: "Xẩy ra lỗi, vui lòng thử lại"
				}, "error");
			}
		});
	}

	$("#btn-back-certnumber").click(function(){
		$("#certNumBerDetail").hide();
		$("#viewLsCertNumber").show();
		dataSourceCertNumber.read();
	});

	$("#btn-add-certnumber").click(function(){
		var viewModel = kendo.observable({
			certId : "",
			year : "",
			pattern : "",
			initNumber : ""
		});

		kendo.bind($("#certNumBerDetail"), viewModel);

		$("#certNumBerDetail").show();
		$("#viewLsCertNumber").hide();
	});

	$("#btn-update-cert").click(function(){
		var id = $(this).attr("data-pk");
		console.log("certId=========>",id);
		fnCertDetailFormCtr(id);
		$("#certNumBerDetail").hide();
		$("#viewLsCertNumber").show();
		dataSourceCertNumber.read();
	});

	$("#year").kendoComboBox({
		placeholder: "Chọn năm",
		dataSource : [
			{ name : 1999, value : 1999 },	
			{ name : 2001, value : 2001 },
			{ name : 2002, value : 2002 },
			{ name : 2003, value : 2003 },
			{ name : 2004, value : 2004 },
			{ name : 2005, value : 2005 },
			{ name : 2006, value : 2006 },
			{ name : 2007, value : 2007 },
			{ name : 2008, value : 2008 },
			{ name : 2009, value : 2009 },
			{ name : 2010, value : 2010 },
			{ name : 2011, value : 2011 },
			{ name : 2012, value : 2012 },
			{ name : 2013, value : 2013 },
			{ name : 2014, value : 2014 },
			{ name : 2015, value : 2015 },
			{ name : 2016, value : 2016 },
			{ name : 2017, value : 2017 },
			{ name : 2018, value : 2018 },
			{ name : 2019, value : 2019 },
			{ name : 2020, value : 2020 },
			{ name : 2021, value : 2021 },
			{ name : 2022, value : 2022 },
			{ name : 2023, value : 2023 }
		],
		dataTextField : "name",
		dataValueField : "value",
		index : 19,
		noDataTemplate : "Không có dữ liệu"
	});

	$(document).off("click",".certNumberItemDelete");
	$(document).on("click",".certNumberItemDelete",function(e){
		var id = $(this).attr("data-pk");
		if(id){
			var cf = confirm("Bạn có muốn xóa tham số này!");
			if(cf){
				$.ajax({
					url : "/o/rest/vr-app/certnumbers/"+id,
					dataType : "json",
					type : "DELETE",
					headers: {"groupId": ${groupId}},
					success : function(result){
						notification.show({
							message: "Yêu cầu được thực hiện thành công"
						}, "success");
					},
					error : function(result){
						notification.show({
							message: "Xẩy ra lỗi, vui lòng thử lại"
						}, "error");
					}
				});
			}
			
		}
	});
</script>