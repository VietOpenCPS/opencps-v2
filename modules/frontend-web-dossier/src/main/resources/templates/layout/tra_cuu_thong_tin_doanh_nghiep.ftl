<v-slide-x-transition>

<div class="row-header flex-break" v-if="!detailPage && !detailRegistPage">

    <div class="background-triangle-big"> Tra cứu doanh nghiệp</div>

    <div class="layout row wrap header_tools w-100-xs">

        <div class="flex w-100-xs" jx-bind="keywordsSearchTraCuuDoanhNghiep"></div>

        <v-btn v-if="navigationFilterview" v-on:click.native="navigationFilterview = !navigationFilterview" flat icon class="mr-4"><v-icon>fullscreen</v-icon></v-btn>

        <v-btn v-if="!navigationFilterview" v-on:click.native="navigationFilterview = !navigationFilterview" flat icon class="mr-4"><v-icon>fullscreen_exit</v-icon></v-btn>
		
		<v-btn flat class=" my-0 py-0 btn-border-left" color="grey darken-1" v-on:click.native="addRegistrations()">
					<v-icon class="mr-2">assignment</v-icon>
			Tạo mới
		</v-btn>
    </div>

</div>

</v-slide-x-transition>

<v-slide-x-transition>

    <div class="layout wrap" jx-bind="thongTinDoanhNghiepTable" v-if="!detailPage && !detailRegistPage"></div>

</v-slide-x-transition>
