<!-- TODO paymentViewJX template one page view List detail template -->
<div id="thong_tin_doanh_nghiep_table_template" class="hidden">
	<template slot="items" slot-scope="props">
		<td style="padding-top: 15px;">{{ props.index + 1 }}</td>
		<td style="padding-top: 15px;" class="text-xs-left">{{ props.item.dossierNo }}</td>
		<td style="padding-top: 15px;" class="text-xs-center">{{ props.item.createDate | date }}</td>
		<td style="padding-top: 15px;" class="text-xs-left">{{ props.item.paymentAmount | money }}</td>
		<td style="padding-top: 15px;" class="text-xs-left">{{ props.item.applicantName }}</td>
		<td style="padding-top: 15px;" class="text-xs-left">{{ props.item.applicantName }}</td>
		<td style="padding-top: 15px;" class="text-xs-left">{{ props.item.applicantName }}</td>
	</template>
</div>