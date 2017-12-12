<!-- TODO detailTemplate page -->
  <div v-if="detailPage" style="width: 100%;">
    <div class="row-header">
      <div class="background-triangle-big"> Hình thức thanh toán </div>
      <div class="layout row wrap header_tools row-blue">
  
        <div class="flex xs6 solo">
  
          <v-radio-group v-model="paymentMethodSearch" row>
            <v-radio label="Tiền mặt" value="Nộp trực tiếp"></v-radio>
            <v-radio label="Chuyển khoản" value="Chuyển khoản"></v-radio>
          </v-radio-group>
  
        </div>
        <div class="flex xs3 text-left">
          <v-btn class="my-0" flat @click.prevent.stop="paymentConfirmSingle(detailModel.index)">
            <v-icon>done</v-icon>
            Xác nhận
          </v-btn>
        </div>
        <div class="flex xs3 text-right">
  
          <v-btn flat class=" my-0 py-0 btn-border-left" color="grey darken-1" v-on:click.native="detailPage = !detailPage">
          	<v-icon class="mr-2">undo</v-icon>
            Quay lại
          </v-btn>
  
        </div>
  
      </div>
  
    </div>
    <div class="panel">
      <object id="objectView2" data="" width="100%" height="100%">
        <iframe src="http://localhost:8080/documents/20147/0/20171208081357103ILHVICIQ..pdf/5b8fc3ea-a9c7-6b9c-1a45-9b737190aaa5"
          width="100%" height="100%"> </iframe>
      </object>
    </div>
  </div>