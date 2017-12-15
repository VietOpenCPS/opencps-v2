<v-slide-x-transition>

<div class="row-header flex-break" v-if="!detailPage">

    <div class="background-triangle-big"> Tra cứu phương tiện</div>

    <div class="layout row wrap header_tools w-100-xs">

        <div class="flex w-100-xs" jx-bind="keywordsSearchTraCuuPhuongTien"></div>

        <v-btn v-if="navigationFilterview" v-on:click.native="navigationFilterview = !navigationFilterview" flat icon class="mr-4"><v-icon>fullscreen</v-icon></v-btn>

        <v-btn v-if="!navigationFilterview" v-on:click.native="navigationFilterview = !navigationFilterview" flat icon class="mr-4"><v-icon>fullscreen_exit</v-icon></v-btn>

    </div>

</div>

</v-slide-x-transition>

<v-slide-x-transition>

    <div class="layout wrap" jx-bind="paymentList" v-if="!detailPage"></div>

</v-slide-x-transition>
