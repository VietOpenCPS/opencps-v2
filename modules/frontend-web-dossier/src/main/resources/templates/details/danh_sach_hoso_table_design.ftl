<!-- TODO paymentViewJX template one page view List detail template -->
<div id="danh_sach_hoso_table_template" class="hidden">
	<template slot="items" slot-scope="props">
		<td style="padding-top: 8px;"> <v-checkbox primary hide-details v-model="props.selected" ></v-checkbox> </td>
		<td style="padding-top: 15px;">{{ props.index + 1 }}</td>
		<td style="padding-top: 15px;" class="text-xs-left">
			<a href="javascript:;" @click.prevent.stop="toDetailHoSo(props.item)">
				{{ props.item.serviceName }} 
				<br>
				{{ props.item.govAgencyName }} 
			</a>
			
		</td>
		<td style="padding-top: 15px;" class="text-xs-center">
			{{ props.item.dossierNo }} 
			<br>
			{{ props.item.dossierId }} 
		</td>
		<td style="padding-top: 15px;" class="text-xs-left">
			{{ props.item.submitDate | date }}
			<br>
			{{ props.item.receiveDate | date }}
		</td>
		<td style="padding-top: 15px;" class="text-xs-left">
			///
		</td>
		
		<td style="padding-top: 15px;" class="text-xs-left">
		
			-Loại sản phẩm: ///
			<br>
			-Tên thuơng mại: ///
			<br>
			-Nhãn hiệu/số loại: ///
			<br>
			{{ props.item.briefNote }}
		
		</td>
		<td style="padding-top: 15px;" class="text-xs-left">{{ props.item.lastActionNote }}</td>
	</template>
</div>


