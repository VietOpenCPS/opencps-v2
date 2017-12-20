<div id="list_document_in_template" class="hidden">
	<v-layout wrap class="pr-4 pl-3 align-center-flex row-list-style" v-if="item.fileEntryId > 0"> 
		<v-flex xs12>
		<v-btn flat fab small class="small-btn-x mx-0" color="grey darken-1" v-on:click.native="downloadReferenceFile(item)">
			<v-icon>file_download</v-icon> 
		</v-btn>
		
		<v-btn flat small class="mx-0 px-0 btn__link_normal" v-on:click.native="downloadReferenceFile(item)">
			{{item.partName}}
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
		word-break: break-all;
	}
</style>