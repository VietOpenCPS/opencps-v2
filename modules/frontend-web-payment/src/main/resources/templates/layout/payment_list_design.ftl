<!-- TODO paymentViewJX template one page view List detail template -->
<div id="activity_expand_list_template" class="hidden">
	<template slot="items" slot-scope="props">
	
		<td style="padding-top: 2px;"> <v-checkbox primary hide-details v-model="props.selected" ></v-checkbox> </td>

		<td style="padding-top: 8px; padding-left: 0px;;">{{ paymentListpage * 15 - 15 + props.index + 1 }}</td>

		<td style="padding-top: 8px; padding-left: 0px;;">
			<a href="javascript:;" @click.prevent.stop="toPaymentDetail(props.index)">
				{{ props.item.serviceName }}
			</a>
		</td>

		<td style="padding-top: 8px; padding-left: 0px; padding-right: 0px;" class="text-xs-left" v-on:click.native="toPaymentDetail(props.index)">
			<a href="javascript:;" @click.prevent.stop="toPaymentDetail(props.index)">
			{{ props.item.invoiceTemplateNo }}
			</a>
		</td>
		<td style="padding-top: 8px;" class="text-xs-center">{{ props.item.createDate | date }}</td>

		<td style="padding-top: 8px;" class="text-xs-right red--text" v-on:click.native="toPaymentDetail(props.index)">{{ props.item.paymentAmount | money }} VNĐ</td>

		<td style="padding-top: 8px;" class="text-xs-left" v-on:click.native="toPaymentDetail(props.index)">{{ props.item.applicantName }}</td>

		<td style="padding-top: 8px; padding-left: 0px; padding-right: 0px;" class="text-xs-center" v-on:click.native="toPaymentDetail(props.index)">
			{{ props.item.dossierNo }} <br/>
			{{ props.item.dossierIdCTN }}
		</td>

		<td style="padding-top: 5px;" class="text-xs-left">

		<span v-html="props.item.briefNote"></span>
		<!-- <v-btn v-on:click.native="toPaymentDetail(props.index)" flat class="mx-0 my-0" color="grey darken-1">Chi tiết <v-icon>forward</v-icon>
		</v-btn> -->
		
		</td>
	</template>
</div>