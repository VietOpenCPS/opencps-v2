<v-slide-x-transition>

<div class="row-header flex-break" v-if="!detailPage">

    <div class="background-triangle-big"> Kết quả tìm kiếm hồ sơ</div>

    <div class="layout row wrap header_tools w-100-xs">

        <div class="flex w-100-xs" jx-bind="keywordsSearchTraCuuHoSo"></div>

        <v-btn v-if="navigationFilterview" v-on:click.native="navigationFilterview = !navigationFilterview" flat icon class="mr-4"><v-icon>fullscreen</v-icon></v-btn>

        <v-btn v-if="!navigationFilterview" v-on:click.native="navigationFilterview = !navigationFilterview" flat icon class="mr-4"><v-icon>fullscreen_exit</v-icon></v-btn>
		
		<v-btn flat class=" my-0 py-0 btn-border-left" color="grey darken-1" v-on:click.native="advanced_filter = !advanced_filter">
			<v-icon class="mr-2" v-if="!advanced_filter">keyboard_arrow_down</v-icon>
			<v-icon class="mr-2" v-if="advanced_filter">keyboard_arrow_up</v-icon>
			Nâng cao
		</v-btn>
    </div>

</div>

</v-slide-x-transition>

<v-slide-y-transition>
	<div v-if="advanced_filter" class="panel" style="height: auto;width: 100%;margin: 0;border-radius: 0;border-top: 1px solid white;background-color: #e1e2e1;padding: 15px;">
		<div class="layout wrap">
			<div class="flex xs12 sm3 pr-4 no-wrap" jx-bind="advanced_filter_serviceInfo">
			
			</div>
			<div class="flex xs12 sm3 pr-4 no-wrap" jx-bind="advanced_filter_applicantName">
			
			</div>
			<div class="flex xs12 sm2 pr-4 no-wrap" jx-bind="advanced_filter_dossierIdCTN">
			
			</div>
			
			<div class="flex xs12 sm2 pr-4 no-wrap" jx-bind="advanced_filter_startDate">
			
			</div>
			<div class="flex xs12 sm2 pr-4 no-wrap" jx-bind="advanced_filter_endDate">
			
			</div>
		</div>
		<div class="layout wrap">
			<div class="flex xs12 sm3 pr-4 no-wrap" jx-bind="advanced_filter_dossierStatus">
			
			</div>
			<div class="flex xs12 sm3 pr-4 no-wrap" jx-bind="advanced_filter_loaiSanPham">
			
			</div>
			<div class="flex xs12 sm2 pr-4 no-wrap" jx-bind="advanced_filter_nhanHieu">
			
			</div>
			<div class="flex xs12 sm2 pr-4 no-wrap" jx-bind="advanced_filter_tenThuongMai">
			
			</div>
			<div class="flex xs12 sm2 pr-4 no-wrap" jx-bind="advanced_filter_maKieuLoai">
			
			</div>
		</div>
		<v-layout wrap>
			<v-flex xs12>
			 	<v-btn color="primary" class="mx-0 my-0 mt-2" v-on:click.native.prevent="advanced_filter_btn_click">Tìm kiếm</v-btn>
			</v-flex>
		</v-layout>
	</div>
</v-slide-y-transition>

<v-slide-x-transition>

    <div class="layout wrap" jx-bind="traCuuHoSoTable" v-if="!detailPage"></div>

</v-slide-x-transition>
