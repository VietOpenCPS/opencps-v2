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
		<!-- TODO: get referenceUid -->
		<div class="panel">
			
				<v-layout wrap class="px-3 py-2">
					
					<v-flex xs12 >
						<span class="text-bold">Tên tổ chức/cá nhân: </span> {{detailModel.applicantName}}
					</v-flex>
					
					<v-flex xs12 >
						<span class="text-bold">Địa chỉ: </span> ///
					</v-flex>
					
					<v-flex xs12 >
						<span class="text-bold">Số phiếu: </span> {{detailModel.referenceUid}}
					</v-flex>
					
					<v-flex xs12 >
						<span class="text-bold">Ngày lập phiếu: </span> {{detailModel.createDate | date}}
					</v-flex>
					
				</v-layout>
			
			<div style="display: none;">
				<object id="objectView2" data="" width="100%" height="100%">
				<!--TODO: append iframe-->
				</object>
			</div>
		</div>
	</div>