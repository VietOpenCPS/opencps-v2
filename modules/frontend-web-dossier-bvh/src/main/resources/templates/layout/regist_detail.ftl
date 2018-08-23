<!-- TODO detailTemplate page -->
<div v-if="detailRegistPage" style="width: 100%;">
	<div class="row-header">
		<div class="background-triangle-big"> Hồ sơ doanh nghiệp </div>
		<div class="layout row wrap header_tools row-blue">
	
			<div class="flex xs12 text-right" style="margin-left: auto;">
		
				<v-btn flat class=" my-0 py-0 btn-border-left" color="grey darken-1" v-on:click.native="undoDetailRegistPage()">
					<v-icon class="mr-2">undo</v-icon>
				Quay lại
				</v-btn>
                <v-btn v-if="navigationFilterview" v-on:click.native="navigationFilterview = !navigationFilterview" flat icon class="mr-3 my-0 hidden-sm-and-down"><v-icon>fullscreen</v-icon></v-btn>

                <v-btn v-if="!navigationFilterview" v-on:click.native="navigationFilterview = !navigationFilterview" flat icon class="mr-3 my-0 hidden-sm-and-down"><v-icon>fullscreen_exit</v-icon></v-btn>

			</div>
	
		</div>
	
	</div>
	
	<v-expansion-panel expand class="my-0 opencps-dossier-info">
		<v-expansion-panel-content v-bind:value="true">
		
		<div slot="header" class="text-bold primary--text">I. Thông tin chung hồ sơ</div>
		<v-layout wrap class="px-4 pb-2">
			<v-flex xs12 sm6>
				<v-layout wrap class="pb-2">
					<v-flex xs12 sm5 class="text-right text-bold pr-2">
						Tên tổ chức/ Doanh nghiệp (<span style="color: red;">*</span>): 
					</v-flex>
					<v-flex xs12 sm7 jx-bind="applicantName">
						<span v-if="themeDisplay.getUserId() != detailRegistModel.userId">
						
							{{detailRegistModel.applicantName}}
							
						</span>
						<v-text-field v-else
			              v-model="detailRegistModel.applicantName"
			              placeholder="tên tổ chức, doanh nghiệp ..."
			              class="pt-0 mt--5"
			              single-line
			              :rules="[(v) => !!v || 'Tên tổ chức/ Doanh nghiệp bắt buộc phải nhập']"
          				  required
			            ></v-text-field>
					</v-flex>
				</v-layout>
				<v-layout wrap class="pb-2">
					<v-flex xs12 sm5 class="text-right text-bold pr-2">
						Mã số thuế (<span style="color: red;">*</span>):
					</v-flex>
					<v-flex xs12 sm7 jx-bind="applicantIdNo">
						<span v-if="themeDisplay.getUserId() != detailRegistModel.userId">
						
							{{detailRegistModel.applicantIdNo}}
							
						</span>
						<v-text-field v-else
			              v-model="detailRegistModel.applicantIdNo"
			              placeholder="mã số thuế ..."
			              class="pt-0 mt--5"
			              single-line
			              :rules="[(v) => !!v || 'Mã số thuế bắt buộc phải nhập']"
          				  required
			            ></v-text-field>
					</v-flex>
				</v-layout>
				<v-layout wrap class="pb-2">
					<v-flex xs12 sm5 class="text-right text-bold pr-2">
						Ngày cấp: 
					</v-flex>
					<v-flex xs12 sm7 jx-bind="applicantIdDate">
						<span v-if="themeDisplay.getUserId() != detailRegistModel.userId">
						
							{{detailRegistModel.applicantIdDate | date}}
							
						</span>
						<v-menu v-else
					        lazy
					        :close-on-content-click="false"
					        v-model="menu"
					        transition="scale-transition"
					        offset-y
					        full-width
					        :nudge-right="40"
					        max-width="290px"
					        min-width="290px"
					      >
					        <v-text-field
					          slot="activator"
					          class="pt-0 mt--5"
					          placeholder="ngày ... tháng ... năm"
					          v-model="detailRegistModel.applicantIdDate"
					          append-icon="event"
					          readonly
					          @blur="detailRegistModel.applicantIdDate = parseDate(dateFormatted)"
					        ></v-text-field>
					        <v-date-picker v-model="detailRegistModel.applicantIdDate" @input="dateFormatted = formatDate($event)" no-title scrollable actions>
					        </v-date-picker>
					      </v-menu>

					</v-flex>
				</v-layout>
				<v-layout wrap class="pb-2">
					<v-flex xs12 sm5 class="text-right text-bold pr-2">
						Người đại diện (<span style="color: red;">*</span>): 
					</v-flex>
					<v-flex xs12 sm7 jx-bind="contactName">
						<span v-if="themeDisplay.getUserId() != detailRegistModel.userId">
						
							{{detailRegistModel.contactName}}
							
						</span>
						<v-text-field v-else
			              v-model="detailRegistModel.contactName"
			              placeholder="tên người đại diện ..."
			              class="pt-0 mt--5"
			              single-line
			              :rules="[(v) => !!v || 'Người đại diện bắt buộc phải nhập']"
          				  required
			            ></v-text-field>
					</v-flex>
				</v-layout>
				<v-layout wrap class="pb-2">
					<v-flex xs12 sm5 class="text-right text-bold pr-2">
						Số điện thoại liên hệ: 
					</v-flex>
					<v-flex xs12 sm7 jx-bind="contactTelNo">
						{{detailRegistModel.contactTelNo}}
					</v-flex>
				</v-layout>
				<v-layout wrap class="pb-2">
					<v-flex xs12 sm5 class="text-right text-bold pr-2">
						Địa chỉ email liên hệ (<span style="color: red;">*</span>): 
					</v-flex>
					<v-flex xs12 sm7 jx-bind="contactEmail">
						<span v-if="themeDisplay.getUserId() != detailRegistModel.userId">
						
							{{detailRegistModel.contactEmail}}
							
						</span>
						<v-text-field v-else
			              v-model="detailRegistModel.contactEmail"
			              placeholder="địa chỉ email ..."
			              class="pt-0 mt--5"
			              single-line
			              :rules="[(v) => !!v || 'Địa chỉ email bắt buộc phải nhập']"
          				  required
			            ></v-text-field>
					</v-flex>
				</v-layout>
			</v-flex>
			<v-flex xs12 sm6>
				<v-layout wrap class="pb-2">
					<v-flex xs12 sm5 class="text-right text-bold pr-2">
						Địa chỉ (<span style="color: red;">*</span>): 
					</v-flex>
					<v-flex xs12 sm7 jx-bind="address">
						<span v-if="themeDisplay.getUserId() != detailRegistModel.userId">
						
							{{detailRegistModel.address}}
							
						</span>
						<v-text-field v-else
			              v-model="detailRegistModel.address"
			              placeholder="địa chỉ ..."
			              class="pt-0 mt--5 address--height"
			              multi-line
			              :rules="[(v) => !!v || 'Địa chỉ bắt buộc phải nhập']"
          				  required
			            ></v-text-field>
					</v-flex>
				</v-layout>
				<v-layout wrap class="pb-2">
					<v-flex xs12 sm5 class="text-right text-bold pr-2">
						Tỉnh/ Thành Phố: 
					</v-flex>
					<v-flex xs12 sm7 jx-bind="cityCode">
						{{detailRegistModel.cityName}}
					</v-flex>
				</v-layout>
				<v-layout wrap class="pb-2">
					<v-flex xs12 sm5 class="text-right text-bold pr-2">
						Quận/ Huyện: 
					</v-flex>
					<v-flex xs12 sm7 jx-bind="districtCode">
						{{detailRegistModel.districtName}}
					</v-flex>
				</v-layout>
				<v-layout wrap class="pb-2">
					<v-flex xs12 sm5 class="text-right text-bold pr-2">
						Xã/ Phường: 
					</v-flex>
					<v-flex xs12 sm7 jx-bind="wardCode">
						{{detailRegistModel.wardName}}
					</v-flex>
				</v-layout>
			</v-flex>
		</v-layout>

		</v-expansion-panel-content>
	</v-expansion-panel>

	<v-expansion-panel expand class="my-0 opencps-dossier-info opencps-dossier-part-style">
		<v-expansion-panel-content v-bind:value="true">
		
		<div slot="header" class="text-bold primary--text">II. Thành phần hồ sơ</div>
		<v-expansion-panel class="my-0 expansion__list_style">
	        <v-expansion-panel-content v-for="(item,i) in registForms" v-if="item" :key="item.referenceUid">
	        <div slot="header" @click="showAlpacaJSFORMRegist(item)">{{i + 1}}. {{item.formName}}</div>
	
	        <div :id="'regist_form_'+item.referenceUid" class="expansion-panel__header"></div>
	
	        </v-expansion-panel-content>
	    </v-expansion-panel>

		</v-expansion-panel-content>
	</v-expansion-panel>
	
	<v-btn primary class="ml-0 mr-0" v-on:click.native="detailRegistPage = !detailRegistPage" v-if="themeDisplay.getUserId() == detailRegistModel.userId">
		Ghi lại
	</v-btn>
	<v-btn primary class="ml-0 mr-0" v-on:click.native="registrationPheDuyet(2)" v-else-if="detailRegistModel.registrationState !== 2">
		<v-icon class="mr-2">done</v-icon>
		Phê duyệt
	</v-btn>
	<v-btn primary class="ml-0 mr-0" v-on:click.native="registrationPheDuyet(3)" v-if="detailRegistModel.registrationState !== 3 && detailRegistModel.registrationState !== 2" >
		<v-icon class="mr-2">undo</v-icon>
		Yêu cầu chỉnh sửa đăng kí
	</v-btn>
</div>
