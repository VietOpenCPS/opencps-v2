<div class="col-xs-2">
	<div class="row">
		<div class="col-sm-12">
			<button class="btn btn-active form-control" id="btn_create_new_dossier" data-bind = "click: load_serviceConfig">Thêm mới kết quả</button>
		</div>
		<div class="col-sm-12">
			<ul id="profileStatus" class="ul-default ul-with-left-icon icon-folder">
				<li dataPk='new' class='itemStatus MT10' data-bind="click: filterStatus">
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
		<div class="col-sm-12 MT15 filterField">
			<div class="MB10">
				<input name="govAgency" id="govAgency" data-role="combobox" data-placeholder="Chọn cơ quan" data-text-field="itemName" data-value-field="itemCode" data-source="dataGovAgency" data-bind="events: { change: eventLookup, dataBound: dataBound}">
			</div>
		</div>
		<div class="col-sm-12 filterField">
			<div class="MB10">
				<input name="serviceInfo" id="serviceInfo" data-role="combobox" data-placeholder="Chọn thủ tục hành chính" data-text-field="serviceName" data-value-field="serviceCode" data-bind="source:dataServiceInfo, events: { change: eventLookup,dataBound: dataBound}">
			</div>
		</div>
		<div class="col-sm-12 filterField">
			<div class="row">
				<div class="col-sm-6 PR5">
					<div class="MB10">
						<input name="year" id="year" data-role="combobox" data-placeholder="Năm" data-text-field="year" data-value-field="valYear" data-bind="source:dataYear, events: { change: eventLookup, dataBound: dataBound}">
					</div>
				</div>
				<div class="col-sm-6 PL5">
					<div class="MB10">
						<input name="month" id="month" data-role="combobox" data-placeholder="Tháng" data-text-field="month" data-value-field="valMonth" data-bind="source:dataMonth, events: { change: eventLookup, dataBound: dataBound}">
					</div>
				</div>
			</div>
		</div>
	</div>

</div>