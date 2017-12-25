<!-- TODO detailTemplate page -->
<div v-if="detailRegistPage" style="width: 100%;">
	<div class="row-header">
		<div class="background-triangle-big"> Hồ sơ doanh nghiệp </div>
		<div class="layout row wrap header_tools row-blue">
	
			<div class="flex xs12 text-right" style="margin-left: auto;">
		
				<v-btn flat class=" my-0 py-0 btn-border-left" color="grey darken-1" v-on:click.native="detailRegistPage = !detailRegistPage">
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
						Tên tổ chức/ Doanh nghiệp: 
					</v-flex>
					<v-flex xs12 sm7 jx-bind="applicantName">
						{{detailRegistModel.applicantName}}
					</v-flex>
				</v-layout>
				<v-layout wrap class="pb-2">
					<v-flex xs12 sm5 class="text-right text-bold pr-2">
						Mã số thuế:
					</v-flex>
					<v-flex xs12 sm7 jx-bind="applicantIdNo">
						{{detailRegistModel.applicantIdNo}}
					</v-flex>
				</v-layout>
				<v-layout wrap class="pb-2">
					<v-flex xs12 sm5 class="text-right text-bold pr-2">
						Ngày cấp: 
					</v-flex>
					<v-flex xs12 sm7 jx-bind="applicantIdDate">
						{{detailRegistModel.applicantIdDate}}
					</v-flex>
				</v-layout>
				<v-layout wrap class="pb-2">
					<v-flex xs12 sm5 class="text-right text-bold pr-2">
						Người đại diện: 
					</v-flex>
					<v-flex xs12 sm7 jx-bind="contactName">
						{{detailRegistModel.contactName}}
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
						Địa chỉ email liên hệ: 
					</v-flex>
					<v-flex xs12 sm7 jx-bind="contactEmail">
						{{detailRegistModel.contactEmail}}
					</v-flex>
				</v-layout>
			</v-flex>
			<v-flex xs12 sm6>
				<v-layout wrap class="pb-2">
					<v-flex xs12 sm5 class="text-right text-bold pr-2">
						Địa chỉ: 
					</v-flex>
					<v-flex xs12 sm7 jx-bind="address">
						{{detailRegistModel.address}}
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

	<v-expansion-panel expand class="my-0 opencps-dossier-info">
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
	
</div>
