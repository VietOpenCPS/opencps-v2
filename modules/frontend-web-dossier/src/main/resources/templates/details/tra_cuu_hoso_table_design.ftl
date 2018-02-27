<!-- TODO paymentViewJX template one page view List detail template -->
<div id="tra_cuu_hoso_table_template" class="hidden">
	<template slot="items" slot-scope="props">

		<td style="padding-top: 3px;"> <v-checkbox primary hide-details v-model="props.selected" ></v-checkbox> </td>
		<td style="padding: 8px; padding-left: 0px;">{{ traCuuHoSoTablepage * 15 - 15 + props.index + 1 }}</td>
		<td style="padding: 8px;" class="text-xs-left">
			<a href="javascript:;" @click.prevent.stop="toDetailHoSo(props.item)">
				<span v-if="props.item.ten_chung_chi">{{ props.item.ten_chung_chi }}</span> 
				<br>
			</a>
			
		</td>
		<td style="padding: 8px;" class="text-xs-left">
			<!-- <span v-bind:id="'ma_ho_so' + props.item.deliverableId"></span> <br>
			<span v-bind:id="'so_ho_so' + props.item.deliverableId"></span> -->
			 {{ props.item.ma_ho_so }} 
			<br v-if="props.item.so_ho_so">
			{{ props.item.so_ho_so }}  
		</td>
		<td style="padding: 8px;" class="text-xs-left">
			<!-- <span v-bind:id="'ngay_gui' + props.item.deliverableId"></span> <br>
			<span v-bind:id="'ngay_tiep_nhan' + props.item.deliverableId"></span> -->
			
			{{ props.item.ngay_gui}}
			<br v-if="props.item.ngay_tiep_nhan">
			{{ props.item.ngay_tiep_nhan}} 
		</td>
		<td style="padding: 8px;" class="text-xs-left">
			<!-- <span v-bind:id="'so_chung_chi' + props.item.deliverableId"></span> <br>
			<span v-bind:id="'ngay_ky_cc' + props.item.deliverableId"></span> -->
			
			{{ props.item.so_chung_chi}}
			<br v-if="props.item.ngay_ky_cc">
			{{ props.item.ngay_ky_cc}} 
		</td>
		
		<td style="padding: 8px;" class="text-xs-left">
			{{ props.item.ten_doanh_nghiep }} 
		</td>
		<td style="padding: 8px;" class="text-xs-left"><!-- {{ props.item.lastActionNote }} --></td>
	</template>
</div>