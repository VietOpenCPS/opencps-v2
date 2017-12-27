<!-- TODO paymentViewJX template one page view List detail template -->
<div id="tra_cuu_hoso_table_template" class="hidden">
	<template slot="items" slot-scope="props">
		<td style="padding-top: 3px;"> <v-checkbox primary hide-details v-model="props.selected" ></v-checkbox> </td>
		<td style="padding: 8px; padding-left: 0px;">{{ traCuuHoSoTablepage * 8 - 8 + props.index + 1 }}</td>
		<td style="padding: 8px;" class="text-xs-left">
			<a href="javascript:;" @click.prevent.stop="toDetailHoSo(props.item)">
				{{ props.item.serviceName }} 
				<br>
			</a>
			{{ props.item.applicantName }} 
		</td>
		<td style="padding: 8px;" class="text-xs-left">
			{{ props.item.dossierNo }} 
			<br v-if="props.item.dossierNo">
			{{ props.item.dossierId }} 
		</td>
		<td style="padding: 8px;" class="text-xs-left">
			{{ props.item.submitDate}}
			<br v-if="props.item.submitDate">
			{{ props.item.receiveDate}}
		</td>
		<td style="padding: 8px;" class="text-xs-left">
			{{ props.item.dueDate}}
		</td>
		
		<td style="padding: 8px;" class="text-xs-left" v-html="props.item.briefNote">
			
		</td>
		<td style="padding: 8px;" class="text-xs-left">{{ props.item.lastActionNote }}</td>
	</template>
</div>