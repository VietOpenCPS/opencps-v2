<!-- TODO detailTemplate page -->
	<div v-if="detailPage" style="width: 100%;">
		<div class="row-header">
			<div class="background-triangle-big"> Hình thức thanh toán </div>
			<div class="layout row wrap header_tools row-blue">
		
			<div class="flex xs6 solo">
		
				<v-radio-group v-model="paymentMethodSearch" v-show="listgroupFilterselected != 2" row>
				<v-radio label="Tiền mặt" value="Nộp trực tiếp"></v-radio>
				<v-radio label="Chuyển khoản" value="Chuyển khoản"></v-radio>
				</v-radio-group>
		
			</div>
			
			<div class="flex xs3 text-left">
				<v-btn class="my-0" flat @click.prevent.stop="paymentConfirmSingle(detailModel.index)"  v-show="listgroupFilterselected != 2">
					<v-icon>done</v-icon>
					Xác nhận
				</v-btn>
			</div>
			
			<div class="flex xs3 text-right">
		
				<v-btn flat class=" my-0 py-0 btn-border-left" color="grey darken-1" v-on:click.native="backToList()">
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
						<span class="text-bold">Tên thủ tục: </span> {{detailModel.serviceName}}
					</v-flex>

					<v-flex xs12 >
						<span class="text-bold">Tên doanh nghiệp: </span> {{detailModel.applicantName}}
					</v-flex>
					
					<v-flex xs12 >
						<span class="text-bold">Tổng tiền: </span> <span class="red--text"> {{detailModel.paymentAmount | money}} VNĐ </span>
					</v-flex>

					<v-flex xs12 >
						<span class="text-bold">Tình trạng thanh toán: 
							<span v-if="detailModel.paymentStatus === 0"> Chờ nộp</span>
							<span v-else-if="detailModel.paymentStatus === 1"> Báo đã nộp</span>
							<span v-else-if="detailModel.paymentStatus === 2"> Hoàn Thành</span>
							<span v-else-if="detailModel.paymentStatus === 3"> Báo không hợp lệ</span>
						</span> 
					</v-flex>

					<v-flex xs12 >
						<span class="text-bold">Hình thức thanh toán: </span> {{detailModel.paymentMethod}}
					</v-flex>

					<v-flex xs12 >
						<span class="text-bold">Số phiếu: </span> {{detailModel.invoiceTemplateNo}}
					</v-flex>

					<v-flex xs12 >
						<span class="text-bold">Ngày xác nhận thu phí: </span> {{detailModel.approveDatetime}}
					</v-flex>
					
					<v-flex xs12 >
						<span class="text-bold">Ngày lập phiếu: </span> {{detailModel.createDate }}
					</v-flex>
					
				</v-layout>
			
			<div style="" v-if="detailModel.hasFile">
				<object id="objectView2" data="" width="100%" height="100%">
				<!--TODO: append iframe-->

				</object>
			</div>
			<div v-else>
				<div id="paymentPDFViewNotFound" class="text-center">Không tìm thấy file</div>
			</div>
		</div>
	</div>