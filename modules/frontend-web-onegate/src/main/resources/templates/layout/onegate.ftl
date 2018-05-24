<div style="width: 100%;">
	<div class="row-header">
		<div class="background-triangle-big"> Tên thủ tục </div>
		<div class="layout row wrap header_tools row-blue">
	
			<div class="flex xs12 sm12 solo pl-4 text-ellipsis">
		
				{{detailModel.dossierNo}} {{detailModel.serviceName}}
		
			</div>
			<!-- <div class="flex xs5 sm3 text-right" style="margin-left: auto;">
					
				<v-btn flat class=" my-0 py-0 btn-border-left" color="grey darken-1" v-on:click.native.prevent.stop="undoDetailPage()">
					<v-icon class="mr-2">undo</v-icon>
				Quay lại
				</v-btn>
			                <v-btn v-if="navigationFilterview" v-on:click.native.prevent.stop="navigationFilterview = !navigationFilterview" flat icon class="mr-3 my-0 hidden-sm-and-down"><v-icon>fullscreen</v-icon></v-btn>
			
			                <v-btn v-if="!navigationFilterview" v-on:click.native.prevent.stop="navigationFilterview = !navigationFilterview" flat icon class="mr-3 my-0 hidden-sm-and-down"><v-icon>fullscreen_exit</v-icon></v-btn>
			
			</div> -->
		</div>
	
	</div>
	<v-expansion-panel>
		<v-expansion-panel-content>
					<div slot="header">
						<div class="background-triangle-big"> I </div>Thông tin chung
					</div>
					<v-card>
						
						<div class="layout row">
							<div class="flex sm3">
								<span>Tìm kiếm nhanh</span>
							</div>
							<div class="flex sm6">
								<v-text-field
									v-model="applicantIdNo"
									:append-icon=""
									name=""
									label="Số CMTND/Hộ chiếu/Mã số doanh nghiệp"
									counter>
								</v-text-field>
							</div>
							<div class="flex sm3">
								
							</div>
						</div>

						<div class="layout row">
							<div class="flex sm3">
								<span>Chọn thủ tục</span>
							</div>
							<div class="flex sm3">
								<v-select
									:items="states"
									:filter="customFilter"
									v-model="a1"
									item-text="name"
									label="Chọn thủ tục"
									autocomplete
								></v-select>
							</div>
							<div class="flex sm6">
								<v-select
									:items="states"
									:filter="customFilter"
									v-model="a1"
									item-text="name"
									label="Chọn giấy phép"
									autocomplete
								></v-select>
							</div>
						</div>

						<div class="layout row">
							<div class="flex sm3">
								
							</div>
							<div class="flex sm9">
								<v-select
									:items="states"
									:filter="customFilter"
									v-model="a1"
									item-text="name"
									label="Chọn dịch vụ"
									autocomplete
								></v-select>
							</div>
						</div>

						<div class="layout row">
							<div class="flex sm3">
								Mã tiếp nhận
							</div>
							<div class="flex sm3">
								<v-text-field
									v-model="dossierNo"
									name=""
									label="Mã tiếp nhận"
									>
								</v-text-field>
							</div>
							<div class="flex sm3">
								<span>Thời gian giải quyết</span>
							</div>
							<div class="flex sm3">
								<span>{{  }}</span>
							</div>
						</div>

						<div class="layout row">
							<div class="flex sm3">
								Ngày giờ tiếp nhận
							</div>
							<div class="flex sm3">
								<v-text-field
									v-model="dossierNo"
									name=""
									label="Ngày giờ tiếp nhận"
									>
								</v-text-field>
							</div>
							<div class="flex sm3">
								<span>Ngày hẹn trả</span>
							</div>
							<div class="flex sm3">
								<v-text-field
									v-model="dossierNo"
									name=""
									label="Ngày hẹn trả"
									>
								</v-text-field>
							</div>
						</div>

					</v-card>
		</v-expansion-panel-content>

		<v-expansion-panel-content>
					<div slot="header">
						<div class="background-triangle-big"> II </div>Thông tin nộp hồ sơ
					</div>
					<v-card>
						<div class="layout row">
							<div class="flex sm3">
								<span>Họ và tên</span>
							</div>
							<div class="flex sm3">
								<v-text-field
									v-model="dossierNo"
									name=""
									label="Họ và tên">
								</v-text-field>
							</div>
							<div class="flex sm3">
								<span>CMTND/ Hộ chiếu</span>
							</div>
							<div class="flex sm3">
								<v-text-field
									v-model="dossierNo"
									name=""
									label="CMTND/ Hộ chiếu">
								</v-text-field>
							</div>
						</div>

						<div class="layout row">
							<div class="flex sm3">
								<span> Địa chỉ </span>
							</div>
							<div class="flex sm3">
								<v-text-field
									v-model="dossierNo"
									name=""
									label="Địa chỉ">
								</v-text-field>
							</div>
							<div class="flex sm3">
								<span>Số điện thoại</span>
							</div>
							<div class="flex sm3">
								<v-text-field
									v-model="dossierNo"
									name=""
									label="Số điện thoại">
								</v-text-field>
							</div>
						</div>

					</v-card>
		</v-expansion-panel-content>

		<v-expansion-panel-content>
					<div slot="header">
						<div class="background-triangle-big"> III </div>Thông tin hồ sơ
						<div class="flex sm12">
							<v-radio-group v-model="applicantType" row>
								<v-radio label="Công dân" value="citizen" ></v-radio>
								<v-radio label="Doanh Nghiệp" value="business"></v-radio>
							</v-radio-group>
						</div>
					</div>
					<v-card>

						<div class="layout row">
							<div class="flex sm2">
								<span> Họ và tên </span> <span class="red">(*)</span>
							</div>
							<div class="flex sm2">
								<v-text-field
									v-model="dossierNo"
									name=""
									>
								</v-text-field>
							</div>
							<div class="flex sm2">
								<span> Ngày sinh </span> <span class="red">*</span>
							</div>
							<div class="flex sm2">
								<v-date-picker v-model="value" :landscape="true" :reactive="true"></v-date-picker>
							</div>
							<div class="flex sm2">
								<span>Giới tính</span>
							</div>
							<div class="flex sm2">
								<v-select
								  :items="items"
								  v-model="value"
								  
								></v-select>
							</div>
						</div>

						<div class="layout row">
							<div class="flex sm2">
								<span>Địa chỉ</span> <span class="red">*</span>
							</div>
							<div class="flex sm10">
								<v-text-field
								  name="name"
								  id="id"
								></v-text-field>
							</div>
						</div>
						
						<div class="layout row">
							<div class="flex sm2">
								<span>Tỉnh/Thành phố</span> <span class="red">*</span>
							</div>
							<div class="flex sm2">
								<v-select
								  :items="items"
								  item-text="itemName"
								  item-value="itemCode"
								  v-model="city"
								  label="Chọn Tỉnh/Thành phố"
								></v-select>
							</div>

							<div class="flex sm2">
								<span>Quận/Huyện</span> <span class="red">*</span>
							</div>
							<div class="flex sm2">
								<v-select
								  :items="items"
								  item-text="itemName"
								  item-value="itemCode"
								  v-model="district"
								  label="Chọn Quận/Huyện"
								></v-select>
							</div>

							<div class="flex sm3">
								<span>Xã phường</span>
							</div>
							<div class="flex sm3">
								<v-select
								  :items="items"
								  item-text="itemName"
								  item-value="itemCode"
								  v-model="ward"
								  label="Chọn Xã/Phường"
								></v-select>
							</div>
						</div>

						<div class="layout row">
							
							<div class="flex sm2">
								<span> Số điện thoại </span> 
							</div>
							<div class="flex sm2">
								<v-text-field
									v-model="dossierNo"
									name=""
									counter>
								</v-text-field>
							</div>

							<div class="flex sm2">
								<span> Địa chỉ Email</span>
							</div>
							<div class="flex sm6">
								<v-text-field
									v-model="dossierNo"
									name=""
									>
								</v-text-field>

							</div>
						</div>

						<div class="layout row">
							<div class="flex sm2">
								<span> Số CMTND </span> <span class="red">*</span>
							</div>
							<div class="flex sm10">
								<v-text-field
									v-model="dossierNo"
									name=""
									multi-line
										>
								</v-text-field>
							</div>
							
						</div>
					</v-card>
		</v-expansion-panel-content>

		<v-expansion-panel-content>
					<div slot="header">
						<div class="background-triangle-big"> IV </div>	Tài liệu đính kèm theo
					</div>
					<v-card>
						<div class="layout row">
							<div class="flex sm12">
								<table v-for="item in ">
									
								</table>
							</div>
						</div>
					</v-card>
		</v-expansion-panel-content>
				
		<v-expansion-panel-content>
					<div slot="header">
						<div class="background-triangle-big"> V </div>	Lệ phí
					</div>
					<v-card>

						<div class="layout row">
							<div class="flex sm3">
								<span> Phí </span> <span class="red">(*)</span>
							</div>
							<div class="flex sm3">
								<v-text-field
									v-model="dossierNo"
									name=""
									label="Phí"
									>
								</v-text-field>
							</div>
							<div class="flex sm3">
								<span>Tạm thu</span>
							</div>
							<div class="flex sm3">
								<v-text-field
									v-model="dossierNo"
									name=""
									label="Tạm thu">
								</v-text-field>
							</div>
						</div>

						<div class="row">
							<div class="flex sm3">
								<span> Lệ phí</span>
							</div>
							<div class="flex sm3">
								<v-text-field
									v-model="dossierNo"
									name=""
									label="Lệ phí"
									>
								</v-text-field>
							</div>
							<div class="flex sm3">
								<span>Còn phải nộp</span>
							</div>
							<div class="flex sm3">
								<v-text-field
								  name="name"
								  id="id"
								  label="Còn phải nộp"
								></v-text-field>
							</div>
						</div>

						<div class="layout row">
							<div class="flex sm3">
								<span> Khác </span> 
							</div>
							<div class="flex sm9">
								<v-text-field
									v-model="dossierNo"
									name=""
									label="Khác"
									>
								</v-text-field>
							</div>
						</div>

						<div class="layout row">
							<div class="flex sm3">
								<span> Ghi chú </span> 
							</div>
							<div class="flex sm3">
								<v-text-field
								name=""
								label="Ghi chú"
								value=""
								multi-line
								></v-text-field>
							</div>
						</div>
					</v-card>
		</v-expansion-panel-content>

		<v-expansion-panel-content>
					<div slot="header">
						<div class="background-triangle-big"> VI </div>	Dịch vụ chuyển phát kết quả
						<v-checkbox
						label="Đăng ký kết quả tại nhà"
						v-model="checkbox"
						></v-checkbox>
					</div>
					<v-card>

						<div class=" layout row">
							<div class="flex sm2">
								<span> Dịch vụ đăng ký</span>
							</div>
							<div class="flex sm6">
								<v-select
								  :items="items"
								  v-model="value"
								  label="EMS"
								></v-select>
							</div>
							<div class="flex sm5">
								<v-select
								  :items="items"
								  v-model="value"
								  label="label"
								></v-select>
							</div>
						</div>

						<div class="layout row">
							<div class="flex sm2">
								<span> SĐT người nhận </span> 
							</div>
							<div class="flex sm10">
								<v-text-field
									v-model="dossierNo"
									name=""
									>
								</v-text-field>
							</div>
						</div>

						<div class="layout row">
							<div class="flex sm2">
								<span> Địa chỉ trả kết quả </span> 
							</div>
							<div class="flex sm10">
								<v-text-field
								  name="name"
								  id="id"
								  multi-line
								></v-text-field>
							</div>
						</div>

						<div class="layout row">
							<div class="flex sm2">
								<span> Tỉnh/Thành phố </span> 
							</div>
							<div class="flex sm2">
								<v-select
								  :items="items"
								  v-model="value"
								></v-select>
							</div>
							<div class="flex sm2">
								<span> Quận/Huyện </span> 
							</div>
							<div class="flex sm2">
								<v-select
								  :items="items"
								  v-model="value"
								></v-select>
							</div>
							<div class="flex sm2">
								<span> Xã Phường </span> 
							</div>
							<div class="flex sm2">
								<v-select
								  :items="items"
								  v-model="value"
								></v-select>
							</div>
							
						</div>
					</v-card>
		</v-expansion-panel-content>

	</v-expansion-panel>
	<div>
		
	</div>
</div>