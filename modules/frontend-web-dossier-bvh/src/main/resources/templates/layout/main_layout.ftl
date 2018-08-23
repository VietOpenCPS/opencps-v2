  <!-- TODO paymentViewJX template one page -->
  <div id="dossierViewJX_form_template" class="hidden">
    <div class="jx-content-wrap">

      <v-slide-x-transition>
        <div class="layout wrap" v-if="stageFilterView === 'tra_cuu_hoso' && !detailPage && !detailRegistPage ">
        
          <#include "tra_cuu_hoso.ftl">

        </div>

        <div class="layout wrap" v-else-if="stageFilterView === 'tra_cuu_phuong_tien' && !detailPage && !detailRegistPage ">
        
          <#include "tra_cuu_phuong_tien.ftl">

        </div>

        <div class="layout wrap" v-else-if="stageFilterView === 'tra_cuu_thong_tin_doanh_nghiep' && !detailRegistPage ">
        
          <#include "tra_cuu_thong_tin_doanh_nghiep.ftl">

        </div>

        <div class="layout wrap" v-else-if="stageFilterView !== 'tra_cuu' && !detailPage && !detailRegistPage ">
        
          <#include "danh_sach_hoso.ftl">

        </div>
      </v-slide-x-transition>

      <v-slide-x-transition>
        <div class="layout wrap" v-if="detailPage">
        
          <#include "dossier_detail.ftl">

        </div>
      </v-slide-x-transition>
	
	  <v-slide-x-transition>
        <div class="layout wrap" v-if="detailRegistPage">
        
          <#include "regist_detail.ftl">

        </div>
      </v-slide-x-transition>
      
      <v-fab-transition>
        <v-btn color="primary" dark fixed bottom right fab ripple v-show="offsetTop > 200"
        v-on:click.native="onScrollTop">
          <v-icon>keyboard_arrow_up</v-icon>
        </v-btn>
      </v-fab-transition>
      
    </div>
  </div>