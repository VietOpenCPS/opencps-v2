<div id="thong_tin_doanh_nghiep_table_template" class="hidden">
	<template slot="items" slot-scope="props">
		<td style="padding-top: 8px">{{ danhSachHoSoTablepage * 8 - 8 + props.index + 1 }}</td>
		<td style="padding: 8px;" class="text-xs-left">
			{{ props.item.applicantName }}  <br/>
			{{ props.item.address }}
		</td>
		<td style="padding: 8px;" class="text-xs-left">
			{{ props.item.applicantIdNo }} <br/>
			{{ props.item.contactTelNo }} <br/>
			{{ props.item.contactEmail }} 
		</td>
		<td style="padding: 8px;" class="text-xs-left">{{ props.item.contactName }}</td>
		<td style="padding: 8px;" class="text-xs-left">
			///
		</td>
		<td style="padding: 8px;" class="text-xs-left">
			///
		</td>

		<td style="padding: 8px;" class="text-xs-left">
			
			<a href="javascript:;" @click.prevent.stop="toDetailHoSoDoanhNghiep(props.item)">
				{{ props.item.registrationState | registrationState }}
			</a>
		
		</td>

	</template>
</div>