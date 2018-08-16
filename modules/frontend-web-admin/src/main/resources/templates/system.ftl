<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="panel panel-body">
	<div class="nav-tabs-wrapper">
		<ul class="nav nav-tabs" id="system-tabstrip">
			<li class="active"  value="1">
				<a data-toggle="tab" href="#site">
					Công cụ Site
				</a>
			</li>
			<li class=""  value="2">
				<a data-toggle="tab" href="#resolveconflict">
					Sửa bất đồng bộ dữ liệu
				</a>
			</li>
		</ul>
		<div id="systemDetail" class="tab-content">
			<div id="site" class="tab-pane fade in active MT15">
				<div class="row">
					<div class="form-group">
						<button id="btn-deletesite" type="button" class="btn-active btn btn-danger">Xoá dữ liệu Site</button>
					</div>
				</div>
				<div class="row">
					<div id="progressDeleteSite" class="progress fade out">
					  <div class="progress-bar progress-bar-striped active" role="progressbar"
					  aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:40%">
					    40%
					  </div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-3">
						<label>Chọn site</label>
					</div>
					<div class="col-xs-12 col-sm-9">
						<div class="form-group">
							<select id="selectSite" class="form-control selectpicker" data-style="btn-info">
								<#list sites as site>
									<option value="${site.groupId}">${site.name}</option>
								<#else>
								    
								</#list>
							</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="table-responsive">
						  <table class="table table-hover">
						    <thead>
						      <tr>
						        <th>Dữ liệu</th>
						        <th>Thực thể</th>
						        <th>Số lượng</th>
						      </tr>
						    </thead>
						    <tbody>
						      <tr>
						        <td>Mẫu thông báo</td>
						        <td>Notificationtemplate</td>
						        <td id="NotificationtemplateCount"></td>
						      </tr>
						      <tr>
						        <td>Trao đổi</td>
						        <td>Comment</td>
						        <td id="CommentCount"></td>
						      </tr>
						      <tr>
						        <td>Ngày nghỉ</td>
						        <td>Holiday</td>
						        <td id="HolidayCount"></td>
						      </tr>
						      <tr>
						        <td>Ngữ nghĩa thao tác</td>
						        <td>ActionConfig</td>
						        <td id="ActionConfigCount"></td>
						      </tr>
						      <tr>
						        <td>Giấy phép</td>
						        <td>Deliverable</td>
						        <td id="DeliverableCount"></td>
						      </tr>
						      <tr>
						        <td>Hồ sơ</td>
						        <td>Dossier</td>
						        <td id="DossierCount"></td>
						      </tr>
						      <tr>
						        <td>Thao tác hồ sơ</td>
						        <td>DossierAction</td>
						        <td id="DossierActionCount"></td>
						      </tr>
						      <tr>
						        <td>Thành phần hồ sơ</td>
						        <td>DossierFile</td>
						        <td id="DossierFileCount"></td>
						      </tr>
						      <tr>
						        <td>Nhật ký hồ sơ</td>
						        <td>DossierLog</td>
						        <td id="DossierLogCount"></td>
						      </tr>
						      <tr>
						        <td>Thành phần mẫu hồ sơ</td>
						        <td>DossierPart</td>
						        <td id="DossierPartCount"></td>
						      </tr>
						      <tr>
						        <td>Thống kê hồ sơ</td>
						        <td>DossierStatistic</td>
						        <td id="DossierStatisticCount"></td>
						      </tr>
						      <tr>
						        <td>Mẫu hồ sơ</td>
						        <td>DossierTemplate</td>
						        <td id="DossierTemplateCount"></td>
						      </tr>
						      <tr>
						        <td>Cấu hình menu</td>
						        <td>MenuConfig</td>
						        <td id="MenuConfigCount"></td>
						      </tr>
						      <tr>
						        <td>Cấu hình thanh toán</td>
						        <td>PaymentConfig</td>
						        <td id="PaymentConfigCount"></td>
						      </tr>
						      <tr>
						        <td>Phiếu thanh toán</td>
						        <td>PaymentFile</td>
						        <td id="PaymentFileCount"></td>
						      </tr>
						      <tr>
						        <td>Thao tác</td>
						        <td>ProcessAction</td>
						        <td id="ProcessActionCount"></td>
						      </tr>
						      <tr>
						        <td>Cấu hình dịch vụ công</td>
						        <td>ProcessOption</td>
						        <td id="ProcessOptionCount"></td>
						      </tr>
						      <tr>
						        <td>Bước quy trình</td>
						        <td>ProcessStep</td>
						        <td id="ProcessStepCount"></td>
						      </tr>
						      <tr>
						        <td>Hồ sơ thương nhân</td>
						        <td>Registration</td>
						        <td id="RegistrationCount"></td>
						      </tr>
						      <tr>
						        <td>Mẫu đăng ký hồ sơ thương nhân</td>
						        <td>RegistrationForm</td>
						        <td id="RegistrationForm"></td>
						      </tr>
						      <tr>
						        <td>Nhật ký hồ sơ thương nhân</td>
						        <td>RegistrationLog</td>
						        <td id="RegistrationLogCount"></td>
						      </tr>
						      <tr>
						        <td>Cấu hình dịch vụ</td>
						        <td>ServiceConfig</td>
						        <td id="ServiceConfigCount"></td>
						      </tr>
						      <tr>
						        <td>Thủ tục hành chính</td>
						        <td>ServiceInfo</td>
						        <td id="ServiceInfoCount"></td>
						      </tr>
						      <tr>
						        <td>Quy trình</td>
						        <td>ServiceProcess</td>
						        <td id="ServiceProcessCount"></td>
						      </tr>
						      <tr>
						        <td>Cấu hình bước</td>
						        <td>StepConfig</td>
						        <td id="StepConfigCount"></td>
						      </tr>
						      <tr>
						        <td>Người làm thủ tục</td>
						        <td>Applicant</td>
						        <td id="ApplicantCount"></td>
						      </tr>
						      <tr>
						        <td>Cán bộ</td>
						        <td>Employee</td>
						        <td id="Employee"></td>
						      </tr>
						      <tr>
						        <td>Vị trí việc làm</td>
						        <td>JobPos</td>
						        <td id="JobPos"></td>
						      </tr>
						      <tr>
						        <td>Site cơ quan</td>
						        <td>OfficeSite</td>
						        <td id="OfficeSiteCount"></td>
						      </tr>
						      <tr>
						        <td>Cơ quan</td>
						        <td>WorkingUnit</td>
						        <td id="WorkingUnitCount"></td>
						      </tr>
						  </table>	
					  	</div>			
					</div>
				</div>
			</div>	
			<div id="resolveconflict" class="tab-pane fade in MT15">
				<div class="row">
					<div class="progress fade out">
					  <div class="progress-bar progress-bar-striped" role="progressbar"
					  aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:40%">
					    40%
					  </div>
					</div>
				</div>
				<div class="row">
					<button id="btn-searchconflict" type="button" class="btn btn-success">Kiểm tra đồng bộ hồ sơ</button>
					<button type="button" class="btn-active btn btn-danger">Sửa bất đồng bộ hồ sơ</button>
				</div>
			</div>	
		</div>
	</div>
</div>
<script type="text/javascript">
	(function($){
		$('#progressDeleteSite').attr('class', 'progress fade out');
		$('#btn-searchconflict').click(function() {
			$.ajax({
				url: "${api.server}" + "/dossiers/lucene/searchconflict",
				type: "GET",
				dataType: "json",
				headers: {"groupId": ${groupId}},
				data: {
							
				},
				success: function(result) {
					
				}
			});			
		});
		$('#btn-deletesite').click(function() {
			console.log($('#selectSite').val());
			$('#progressDeleteSite').attr('class', 'progress');
		});
	})(jQuery);
</script>
