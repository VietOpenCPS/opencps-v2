<div id="list_document_out_template" class="hidden">
	<v-layout wrap class="px-4 align-center-flex row-list-style"> 
		<v-flex xs11>
			<div style="margin-left: 30px;"><span @click.prevent="viewOnNewTab(item)" style="cursor: pointer;">{{item.partName}}</span></div>
		</v-flex>
		<v-flex xs1 class="text-right">
		<v-btn color="primary" fab small dark class="small-btn-x" v-on:click.native="viewDossierFileVersionNewTabOrWindow(item)">
			{{item.counter}}
		</v-btn>
		</v-flex>
	</v-layout>
</div>

<style>
	.btn__link_normal {
		text-transform: none;
    	font-weight: normal;
		display: inline-table;
		height: auto;
		margin: 0px;
		position: relative;
	}
	.btn__link_normal .btn__content {
		white-space: normal;
		text-align: left;
	}
	.btn__link_delete {
		min-width: 60px !important;
	}
	.btn__link_delete .btn__content {
		color: #c62828 !important;
	}
</style>