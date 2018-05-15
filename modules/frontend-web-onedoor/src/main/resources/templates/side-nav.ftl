<div class="col-xs-2">
	<div class="row">
		<div class="col-sm-12">
			<a class="btn btn-active form-control" id="btn_create_new_dossier" href="#/tao-ho-so-moi">Thêm hồ sơ tiếp nhận</a>
		</div>
	</div>

	<div class="row MT15" id="dossier-emp-navigator-filter-wrapper">
		<div class="col-md-12 MB10 filterField">
			<input id="dossier-emp-nav-selectbox-by-dossierNo" placeholder="Nhập từ khóa" name="dossierNo" class="form-control dossier-emp-nav-selectbox" data-bind="events: { keyup: filterDossierNo}" style="height:30px" />
		</div>
		
		<div class="hidden col-md-12 MB5">
			<input id="dossier-emp-nav-selectbox-by-startDate" placeholder="Từ ngày"
			data-datePicker
			data-datePicker-msg="Ngày không hợp lệ!"
			name="startDate" class="form-control dossier-emp-nav-selectbox" />
		</div>
		
		<div class="hidden col-md-12 MB5">
			<input id="dossier-emp-nav-selectbox-by-endDate" placeholder="Đến ngày"
			data-datePicker
			data-datePicker-msg="Ngày không hợp lệ!"
			name="endDate" class="form-control dossier-emp-nav-selectbox" />
		</div>
		<#--  -->
		<div class="col-sm-12 MB10">
			<div class="accordion">
				<div class="accordion-inner">
					<ul id="profileStatus" class="ul-default ul-with-left-icon icon-folder">
						<#if constants.dossierStatus?has_content>
						<#list constants.dossierStatus as odossierStatus>
						<li class='itemStatus'">
							<a href="#/${odossierStatus.value}">
							<i class='fa fa-folder icon-left' aria-hidden='true'></i>  
							<span class="hover-pointer text-hover-blue dossierStatus">${odossierStatus.text}</span></a>
							<#-- <span class="bagde">0</span> -->
						</li>
						</#list>
						</#if>
					</ul>
				</div>
			</div>
		</div>
		<#--  -->
		<div class="col-sm-12 MB10">
			<div class="accordion">
				<div class="accordion-group">
					<div class="accordion-heading" style="background-color: #14bef0;border: none;font-family: 'Roboto-Regular'">
						<a style="color: #ffffff" data-toggle="collapse" href="#chooseProcedure">
							Chọn thủ tục
						</a>
					</div>
					<div id="chooseProcedure" class="accordion-body collapse in">
						<div class="accordion-inner">
							<ul id="" class="ul-default have-bagde">
								<li class="hover-pointer text-hover-blue itemStatus" id="searchCC" 
								data-bind="click: filterInvestigation" >
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-12">
		<div class="accordion">
			<div class="accordion-group">
				<div class="accordion-heading" style="background-color: #14bef0;border: none;font-family: 'Roboto-Regular'">
					<a style="color: #ffffff" data-toggle="collapse" href="#groupLookup">
						Chọn chủ hồ sơ
					</a>
				</div>
				<div id="groupLookup" class="accordion-body collapse in">
					<div class="accordion-inner">
						<ul id="" class="ul-default have-bagde">
							<li class="hover-pointer text-hover-blue itemStatus" id="searchCC" 
							data-bind="click: filterInvestigation" >
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<#--  -->
</div>
</div>
<script type="text/javascript">
	
</script>