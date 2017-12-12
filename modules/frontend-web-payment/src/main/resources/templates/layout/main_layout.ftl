  <!-- TODO paymentViewJX template one page -->
  <div id="paymentViewJX_form_template" class="hidden">
    <div class="jx-content-wrap">
      <div class="layout wrap">
  		<#include "payment_detail.ftl">
        <v-slide-x-transition>
          <div class="row-header" v-if="!detailPage">
            <div class="background-triangle-big"> Kết quả tìm kiếm</div>
            <div class="layout row wrap header_tools">
            
              <div class="flex xs5 sm5 solo" jx-bind="paymentMethodSearch">
  
              </div>
  
              <div class="flex xs5 sm2" jx-bind="paymentConfirm">
  
              </div>
  
              <div class="flex pr-3" jx-bind="keywordsSearch">
  
              </div>
  
            </div>
          </div>
        </v-slide-x-transition>
        <v-slide-x-transition>
          <div class="layout wrap" jx-bind="paymentList" v-if="!detailPage">
  
          </div>
        </v-slide-x-transition>
        <div id="btn_view_more" class="text-center" style="width: 100%;" v-if="!detailPage">
          <v-scale-transition>
  
            <v-btn round dark color="blue darken-2" :loading="viewmore" :disabled="viewmore">
              {{xem_them}}
              <span slot="loader">Đang tải ...</span>
            </v-btn>
  
          </v-scale-transition>
        </div>
      </div>
    </div>
  </div>