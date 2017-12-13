<!-- TODO paymentViewJX template one page view List detail template -->
<div id="danh_sach_hoso_table_template" class="hidden">
	<template slot="items" slot-scope="props">
		<td style="padding-top: 8px;"> <v-checkbox primary hide-details v-model="props.selected" ></v-checkbox> </td>
		<td style="padding-top: 15px;">{{ props.index + 1 }}</td>
		<td style="padding-top: 15px;" class="text-xs-left">
			<a href="javascript:;" @click.prevent.stop="toDetailHoSo(props.item)">
			{{ props.item.dossierNo }}
			</a>
		</td>
		<td style="padding-top: 15px;" class="text-xs-center">
			<a href="javascript:;" @click.prevent.stop="toDetailHoSo(props.item)">
			{{ props.item.createDate | date }}
			</a>
		</td>
		<td style="padding-top: 15px;" class="text-xs-left">{{ props.item.paymentAmount | money }}</td>
		<td style="padding-top: 15px;" class="text-xs-left">{{ props.item.applicantName }}</td>
		<td style="padding-top: 15px;" class="text-xs-left">{{ props.item.applicantName }}</td>
		<td style="padding-top: 15px;" class="text-xs-left">{{ props.item.applicantName }}</td>
	</template>
</div>