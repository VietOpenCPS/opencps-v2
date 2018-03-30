<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="col-xs-2">	
	<div class = "row">
		<div class="col-sm-12">
			<button class="btn btn-active form-control" id="btn_create_new_dossier">Thêm mới kết quả</button>
		</div>

		<div class="col-md-12 MT10 filterField">
			<div class="form-group search-icon">
                <input type="text" class="form-control" placeholder="Nhập từ khóa">
            </div>
		</div>

		<div class="col-sm-12 filterField">
			<select class="form-control MB15" style="font-size: 13px">
                <option value="0" selected="">Trạng thái cấp giấy</option>
                <option value="1">1</option>
                <option value="2">2</option>
            </select>
		</div>

		<div class="col-sm-12 filterField">
			<select class="form-control MB15" style="font-size: 13px">
                <option value="0" selected="">Chủ giấy pháp/Giấy chứng nhận</option>
                <option value="1">1</option>
                <option value="2">2</option>
            </select>
		</div>

		<div class="col-sm-12 MB10">
			<div class="accordion">
				<div class="accordion-group">
					<div class="accordion-heading" style="background-color: #14bef0;border: none;font-family: 'Roboto-Regular'">
						<a class="" style="color: #ffffff" data-toggle="collapse" href="#groupFilterStatus">
							Danh sách hiển thị
						</a>
					</div>
					<div id="groupFilterStatus" class="accordion-body collapse in">
						<div class="accordion-inner">
							<ul id="profileStatus" class="ul-default ul-with-left-icon icon-folder have-bagde">
								<li dataPk='new' class='itemStatus' data-bind="click: filterStatus">
									<i class='fa fa-folder icon-left' aria-hidden='true'></i>  
									<a href='javascript:;' > Mới cấp</a>
								</li>
								<li dataPk='done' class='itemStatus' data-bind="click: filterStatus">
									<i class='fa fa-folder icon-left' aria-hidden='true' ></i>  
									<a href='javascript:;' > Sắp hết hạn</a>
								</li>
								<li dataPk='receiving' class='itemStatus' data-bind="click: filterStatus">
									<i class='fa fa-folder icon-left' aria-hidden='true'></i>  
									<a href='javascript:;' > Đã hết hạn</a>
								</li> 
								<li dataPk='waiting' class='itemStatus' data-bind="click: filterStatus">
									<i class='fa fa-folder icon-left' aria-hidden='true'></i>  
									<a href='javascript:;' > Tạm dừng</a>
								</li>
								<li dataPk='paying' class='itemStatus' data-bind="click: filterStatus">
									<i class='fa fa-folder icon-left' aria-hidden='true'></i>  
									<a href='javascript:;' > Thu hồi</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="col-sm-10">
	<div class="demo-section k-content wide">
		<div class="row-header"> <div class="background-triangle-big" style="width: 30%;">DANH SÁCH GIẤY PHÉP</div> </div>
		<div class="box" id="listView"></div>
		<div id="pager" class="k-pager-wrap"></div>
	</div>
	
	<script>
		$(function() {
	        var dataSource = new kendo.data.DataSource({
	            // transport: {
	            //     read: {
	            //         url: "http://localhost:8080/o/rest/v2/deliverables",
	            //         dataType: "json",
	            //         type: "GET"
	            //     }
	            // },
	            data: {
						    "total": 3,
						    "data": [
										{
										"so_chung_chi":"",
										"applicantName":"Nguyễn Quang Huy",
										"deliverableCode":"E002Cqb4gl",
										"govAgencyName":"Công ty TOYOTA Việt Nam",
										"deliverableType":"18174B90",
										"issueDate":"28/03/2018 16:46:28",
										"expireDate":"28/03/2018 16:34:13",
										"so_ho_so":" 20020/18/XH",
										"revalidate":"28/03/2018 16:41:00",
										"deliverableState":"1",
										"subject":"aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
										"nhan_hieu":"",
										"ma_so_doanh_nghiep":"2500150335",
										"ma_kieu_loai":"",
										"ten_thuong_mai":"",
										"ten_chung_chi":"Giấy chứng nhận Thẩm định thiết kế Xe cơ giới"
										},
										{
										"so_chung_chi":"",
										"applicantName":"Trần Huy Khánh",
										"deliverableCode":"bPSO6PVsap",
										"govAgencyName":"Công ty cổ phần Linh An",
										"deliverableType":"18ABE075",
										"issueDate":"28/03/2018 15:21:31",
										"expireDate":"02/02/2018 16:22:53",
										"so_ho_so":"0141/18/XH",
										"revalidate":"02/02/2018 16:20:58",
										"deliverableState":"2",
										"subject":"bbbbbbbbbbbbbbbbbbbbbbbbbbb",
										"nhan_hieu":"COUNTY 1.5",
										"ma_so_doanh_nghiep":"179031148",
										"ma_kieu_loai":"LA-167",
										"ten_thuong_mai":"XE COUNTY",
										"ten_chung_chi":"Giấy chứng nhận Thẩm định thiết kế Xe cơ giới"
										},
										{
										"so_chung_chi":"02915/VAQ09-04/18-00",
										"applicantName":"Trần Huy Khánh",
										"deliverableCode":"MDkE0hdwMd",
										"govAgencyName":"asasa",
										"deliverableType":"18BEB0DA",
										"issueDate":"28/03/2018 15:21:04",
										"expireDate":"23/03/2018 08:51:16",
										"subject":"cccccccccccccccccccccccccc",
										"deliverableState":"3",
										"so_ho_so":" 20011/18/XH",
										"revalidate":"23/03/2018 08:54:26",
										"bien_ban":"",
										"ma_kieu_loai":"LA-167",
										"ten_thuong_mai":"XE KL",
										"ten_chung_chi":"Giấy chứng nhận Thẩm định thiết kế Xe cơ giới"
										}
									]
						},
	            pageSize: 5,
				schema:{
					data:"data",
					total:"total"
				}
	        });


            $("#pager").kendoPager({
                dataSource: dataSource
            });

			$("#listView").kendoListView({
				dataSource: dataSource,
				template: kendo.template($("#proFileTemplate").html()),
			});

		});
	</script>
</div>

<script type="text/x-kendo-template" id="proFileTemplate">
	<div class="row PL15 PR15 itemCustomerDossierList">
		<div class="row M0">
			<div class="row-blue align-middle">
				<div class="order-number">\#</div>
				<#-- <div class="dossier-number" data-toggle="tooltip" title="Mã hồ sơ"><span class="red">\\#</span> #:dossierId#</div> -->
				<div class="receive-number PL10"> #:deliverableCode#<span class="text-normal"> - </span>#:deliverableType# </div>
				#
					var status;
					if(deliverableState == "1"){
						status = "Hiệu lực";
					}else if(deliverableState == "2"){
						status = "Hết hạn";
					}else if(deliverableState == "3"){
						status = "Tạm dừng";
					}else if(deliverableState == "4"){
						status = "Thu hồi";
					}else if(deliverableState == "5"){
						status = "Từ chối";
					}
				#
				<span class="label btn btn-active MLA">#:status#</span> 
			</div>
		</div>
		<div class="col-sm-12 PL0 PT5 PB5">
			<div class="row M0">
				<div class="col-sm-9" style="border-right: 1px solid \\#ddd">
					<p class="MB5">
						<i class="fa fa-university" style="color: \\#84FAFA;" aria-hidden="true"></i> #:govAgencyName#
					</p>

					<p class="MB5">
						<i class="fa fa-user" style="color: \\#84FAFA;" aria-hidden="true"></i> #:applicantName#
					</p>

					<p class="MB5">#:subject#</p>

					<p class="actionDeliverable MB0">
						<a href="javascript:;" class="downloadAddRes PR20">
							<i class="fa fa-file-text" aria-hidden="true"></i> 
							Xem lịch sử
						</a>

						<a href="javascript:;" class="downloadProfile PR20">
							<i class="fa fa-calendar" aria-hidden="true">
							</i> Gia hạn
						</a>

						<a href="javascript:;" class="copyProfile PR20">
							<i class="fa fa-minus-circle" aria-hidden="true"></i> Tạm dừng
						</a>

						<a href="javascript:;" class="copyProfile PR20">
							<i class="fa fa-undo" aria-hidden="true"></i> Thu hồi
						</a>
					</p>
				</div>
				<#-- Content DATE -->
				<div class="col-sm-3 text-right">
					<div class="row">
						#if(issueDate != ""){#
							<p data-toggle="tooltip" title="Ngày gửi" class="pull-left PL10">Ngày cấp: 
							 #:issueDate#
							</p>
						#}#
						#if(expireDate != ""){#
							<p data-toggle="tooltip" title="Ngày tiếp nhận" class="pull-left PL10">Ngày hết hạn: 
								#:expireDate#
							</p>
						#}#
						#if(revalidate != ""){#
							<p data-toggle="tooltip" title="Ngày hẹn trả" class="pull-left PL10">Ngày gia hạn: 
							 #:revalidate#
							</p>
						#}#
						
					</div>
				</div>
			</div>
		</div>
	</div>
</script>