<div id="list_history_processing_template" class="hidden">
	<template slot="items" slot-scope="props">
		<td style="padding-top: 15px;">{{ props.index + 1 }}</td>
		<td style="padding-top: 15px;" class="text-xs-left">
		
			<p> Ông/bà <b>{{ props.item.author }}</b> 
			<span class="text-blue">( {{ props.item.payload.stepName }} )</span>
 			<br/>
			<span class="text-light-gray">{{ props.item.createDate | date }}</span>
			</p>
			<p>Ý kiến: {{ props.item.content }}</p>
			
			<p
				v-for="file in props.item.payload.files"
				:key="file.fileAttachId"
				@click.prevent.stop="downloadFile(file.fileAttachId)"
			>
				<img src="https://s20.postimg.org/4q1ojo0od/word.png">
				{{file.fileName}}
			</p>

		</td>
	</template>
</div>