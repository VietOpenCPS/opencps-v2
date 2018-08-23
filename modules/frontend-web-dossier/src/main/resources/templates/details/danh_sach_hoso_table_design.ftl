<!-- TODO paymentViewJX template one page view List detail template -->
<div id="danh_sach_hoso_table_template" class="hidden">
	<template slot="items" slot-scope="props">
		<td style="padding-top: 3px;"> <v-checkbox primary hide-details v-model="props.selected" ></v-checkbox> </td>
		<td style="padding: 8px; padding-left: 0px;">{{ danhSachHoSoTablepage * 15 - 15 + props.index + 1 }}</td>
		<td style="padding: 8px;" class="text-xs-left">
			<a href="javascript:;" @click.prevent.stop="toDetailHoSo(props.item)">
				{{ props.item.serviceName }} 
				<br>
			</a>
			{{ props.item.applicantName }} 
		</td>
		<td style="padding: 8px;" class="text-xs-left">
			{{ props.item.dossierIdCTN }} 
			<br v-if="props.item.dossierNo">
			{{ props.item.dossierNo }} 
		</td>
		<td style="padding: 8px;" class="text-xs-left">
			{{ props.item.submitDate}}
			<br v-if="props.item.submitDate">
			{{ props.item.receiveDate}}
		</td>

		<td style="padding: 8px;" class="text-xs-center">
			<!-- <div v-for="cert in props.item.certNumber">
				<span>{{ cert.certNo }}</span> <br>
				<span>{{ cert.certDate }}</span>
			</div>
			<div v-if="!props.item.certNumber">
				<span>-</span> <br>
				<span>-</span>
			</div> -->

			<span v-if="props.item.certNo">{{ props.item.certNo }}</span>
			<span v-else>---</span> <br>
			<span v-if="props.item.certDate">{{ props.item.certDate }}</span>
			<span v-else>---</span>
		</td>
		
		<td style="padding: 8px;" class="text-xs-left" v-html="props.item.briefNote">
		

		</td>
		<td style="padding: 8px;" class="text-xs-left" v-html="props.item.applicantNote"></td>
	</template>
</div>


