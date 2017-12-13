  <!-- TODO paymentViewJX template one page -->
  <div id="dossierViewJX_form_template" class="hidden">
    <div class="jx-content-wrap">

      <v-slide-x-transition>
        <div class="layout wrap" v-if="stageFilterView === 'tra_cuu_hoso' && !detailPage">
        
          <#include "tra_cuu_hoso.ftl">

        </div>

        <div class="layout wrap" v-else-if="stageFilterView === 'tra_cuu_phuong_tien' && !detailPage">
        
          <#include "tra_cuu_phuong_tien.ftl">

        </div>

        <div class="layout wrap" v-else-if="stageFilterView === 'tra_cuu_thong_tin_doanh_nghiep' && !detailPage">
        
          <#include "tra_cuu_thong_tin_doanh_nghiep.ftl">

        </div>

        <div class="layout wrap" v-else-if="stageFilterView !== 'tra_cuu_hoso' && !detailPage">
        
          <#include "danh_sach_hoso.ftl">

        </div>
      </v-slide-x-transition>

      <v-slide-x-transition>
        <div class="layout wrap" v-if="detailPage">
        
          <#include "dossier_detail.ftl">

        </div>
      </v-slide-x-transition>
    </div>
  </div>