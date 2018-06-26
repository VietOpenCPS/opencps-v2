<!-- TODO paymentViewJX template one page view List detail template -->
<#-- <div id="danh_sach_hoso_table_template" class="hidden">
	<template slot="items" slot-scope="props">
		<td style="padding-top: 3px;"> <v-checkbox primary hide-details v-model="props.selected" ></v-checkbox> </td>
		<td style="padding: 8px; padding-left: 0px;">{{ danhSachHoSoTablepage * 15 - 15 + props.index + 1 }}</td>
		<td style="padding: 8px; width: 25%;" class="text-xs-left">
			<a href="javascript:;" @click.prevent.stop="toDetailHoSo(props.item)">
				{{ props.item.serviceName }} 
				<br>
			</a>
			{{ props.item.applicantName }} 
		</td>
		<td style="padding: 8px; width: 15%;" class="text-xs-left">

			{{ props.item.dossierNo }} 
			<br v-if="props.item.dossierIdCTN">
			{{ props.item.dossierIdCTN }} 
			
		</td>
		<td style="padding: 8px; width: 15%;" class="text-xs-left">
			{{ props.item.submitDate}}
			<br v-if="props.item.submitDate">
			{{ props.item.receiveDate}}
			<br v-if="props.item.dueDate">
			{{ props.item.dueDate}}
		</td>
		<td style="padding: 8px;" class="text-xs-left" v-html="props.item.lastActionNote"></td>
	</template>
</div>
 -->
<div id="danh_sach_hoso_table_template" class="hidden">
	<template slot="items" slot-scope="props">
		<#-- <td> <v-checkbox primary hide-details v-model="props.selected" ></v-checkbox> </td> -->
		<td style="width: 3%;" class="text-xs-center">{{ danhSachHoSoTablepage * 15 - 15 + props.index + 1 }}</td>
		<td class="text-xs-left" style="width: 10%;">

			{{ props.item.dossierIdCTN }} 
			<br v-if="props.item.dossierIdCTN">
			<#-- {{ props.item.dossierIdCTN }}  -->
			
		</td>
		<td class="text-xs-left" style="width: 15%;">{{ props.item.applicantName }}</td>
		<td class="text-xs-left" style="width: 23%;">
			<a href="javascript:;" @click.prevent.stop="toDetailHoSo(props.item)">
				{{ props.item.serviceName }} 
				<br>
			</a> 
		</td>
		<td class="text-xs-left" style="width: 15%;">
			{{ props.item.submitDate }}
			<br v-if="props.item.submitDate">
			{{ props.item.receiveDate }}
		</td>
		<td class="text-xs-left" style="width: 15%;">
			{{ props.item.dueDate }}
		</td>
		<!-- <td style="padding: 8px;" class="text-xs-left">
			{{ props.item.dueDate}}
		</td>
		
		<td style="padding: 8px;" class="text-xs-left" v-html="props.item.briefNote">
			
		</td> -->
		<td class="text-xs-left">{{props.item.lastActionNote}}</td>
	</template>
</div>


