  <!-- TODO paymentViewJX template one page -->
  <div id="paymentViewJX_form_template" class="hidden">
    <div class="jx-content-wrap">
      <div class="layout wrap">
  		<#include "payment_detail.ftl">
        <v-slide-x-transition>
          <div class="row-header flex-break" v-if="!detailPage">
            <div class="background-triangle-big"> Kết quả tìm kiếm</div>
            <div class="layout row wrap header_tools w-100-xs">
            
              <div class="flex xs8 sm5 solo" jx-bind="paymentMethodSearch" v-show="listgroupFilterselected != 2">
  
              </div>
  
              <div class="flex xs4 sm2" jx-bind="paymentConfirm" v-show="listgroupFilterselected != 2">
  
              </div>
  
              <div class="flex pr-3 w-100-xs" jx-bind="keywordsSearch" style="display: flex;display: -webkit-flex;">
  
              </div>
  
            </div>
          </div>
        </v-slide-x-transition>
        
        
        <v-slide-x-transition>
          <div class="layout wrap" jx-bind="paymentList" v-if="!detailPage">
  
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
  </div>