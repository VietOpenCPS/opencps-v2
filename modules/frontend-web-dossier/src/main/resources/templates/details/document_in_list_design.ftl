<div id="list_document_in_template" class="hidden">
	<v-layout wrap class="px-4 align-center-flex row-list-style"> 
		<v-flex xs11>
			<span class="text-bold" style="position: absolute;">{{index + 1}}.</span> 
			<div style="margin-left: 30px;"><span @click.prevent="viewOnNewTab(item)" style="cursor: pointer;">{{item.partName}}</span></div>
		</v-flex>
		<v-flex xs1 class="text-right">
		<v-btn color="primary" fab small dark class="small-btn-x" v-on:click.native="viewDossierFileVersionNewTabOrWindow(item)">
			{{item.counter}}
		</v-btn>
		</v-flex>
	</v-layout>
</div>

<div id="list_document_in_part_no_template" class="hidden">
	<div class="layout wrap align-center-flex row-list-style pl-4">
        <div class="flex xs12">
			<span class="text-bold pt-1" style="position: absolute;">{{index + 1}}.</span> 
			<div style="margin-left: 18px;"><v-btn flat small class="mx-0 px-0 btn__link_normal" v-on:click.native="previewDossierPDF(item)">{{item.displayName}}</v-btn></div>
        </div>
    </div>
</div>

<div id="list_document_out_part_no_template" class="hidden">
	<div class="layout wrap align-center-flex row-list-style pl-4">
        <div class="flex xs10">
			<span class="text-bold pt-1" style="position: absolute;">{{index + 1}}.</span> 
			<div style="margin-left: 18px;"><v-btn flat small class="mx-0 px-0 btn__link_normal" v-on:click.native="previewDossierPDF(item)">{{item.displayName}}</v-btn></div>
        </div>
		<div class="flex xs2">
			<v-btn flat small class="mx-0 px-0 btn__link_normal btn__link_delete" v-on:click.native="deleteDossierPDF(item, index)">
				<v-icon>delete</v-icon>
				Xoá
			</v-btn>
		</div>
    </div>
</div>