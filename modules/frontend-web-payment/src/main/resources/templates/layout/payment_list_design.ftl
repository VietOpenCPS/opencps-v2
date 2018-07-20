<!-- TODO paymentViewJX template one page view List detail template -->
<div id="activity_expand_list_template" class="hidden">
	<template slot="items" slot-scope="props">
	
		<td style="padding-top: 2px;"> <v-checkbox primary hide-details v-model="props.selected" ></v-checkbox> </td>

		<td style="padding-top: 8px; padding-left: 0px;;">{{ paymentListpage * 15 - 15 + props.index + 1 }}</td>

		<td style="padding-top: 8px; padding-left: 0px;;">
			<a href="javascript:;" @click.prevent.stop="toPaymentDetail(props.index)">
				{{ props.item.serviceName }} | {{ props.item.applicantName }}
			</a>
		</td>

		<td style="padding-top: 8px; padding-left: 0px; padding-right: 0px;" class="text-xs-center" v-on:click.native="toPaymentDetail(props.index)">
			{{ props.item.dossierNo }} <br/>
			{{ props.item.dossierIdCTN }}
		</td>

		<td style="padding-top: 8px;" class="text-xs-center red--text" v-on:click.native="toPaymentDetail(props.index)">{{ props.item.paymentAmount | money }} VNĐ</td>

		<td style="padding-top: 8px;" class="text-xs-center">
			<span v-if="props.item.paymentStatus === 0"> Chờ nộp</span>
			<span v-else-if="props.item.paymentStatus === 1"> Báo đã nộp</span>
			<span v-else-if="props.item.paymentStatus === 2"> Hoàn Thành</span>
			<span v-else-if="props.item.paymentStatus === 3"> Báo không hợp lệ</span>
		</td>

		<td style="padding-top: 8px;" class="text-xs-center">{{ props.item.paymentMethod }}</td>

		<#-- <td style="padding-top: 8px; padding-left: 0px; padding-right: 0px;" class="text-xs-left" v-on:click.native="toPaymentDetail(props.index)">
			<a href="javascript:;" @click.prevent.stop="toPaymentDetail(props.index)">
			{{ props.item.invoiceTemplateNo }}
			</a>
		</td> -->

		<td style="padding-top: 8px;" class="text-xs-center">{{ props.item.confirmDatetime }}</td>

		<#-- <span v-html="props.item.briefNote"></span> -->
		<!-- <v-btn v-on:click.native="toPaymentDetail(props.index)" flat class="mx-0 my-0" color="grey darken-1">Chi tiết <v-icon>forward</v-icon>
		</v-btn> -->
		
		
	</template>
</div>