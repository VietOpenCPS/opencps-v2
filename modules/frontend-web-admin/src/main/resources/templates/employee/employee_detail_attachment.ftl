<#include "init.ftl">

 <form id="fm-employee-detail-fileupload" action="" method="POST" enctype="multipart/form-data">
	<div class="row">
		<div class="col-sm-12">
			
			<div class="input-group image-preview btn-group">
				<h5><strong>Tập đính kèm</strong></h5>
			
				<!-- check permission -->
				<span class="input-group-btn">
					<div class="btn btn-active">
						<label for="employee-detail-fileupload" class="hover-pointer">
							<i class="fa fa-upload"></i>
							Tải lên
						</label>
						<input id="employee-detail-fileupload" class="hidden" type="file" name="file" multiple />
					</div>
				</span>
				
			</div>
		</div>
	</div>
   
	<div class="row">
		<div class="col-sm-12">
			<ul class="ul-default ul-with-border ul-with-right-icon ul-with-left-icon" id="employee-detail-files">
				<#if employee_fileAttachs?has_content>
					<#list employee_fileAttachs as oAttach>
						<li id="attachment-${(oAttach.fileAttachId)!}" class="PL0">
							<i class="fa fa-angle-double-right icon-left" aria-hidden="true"></i>
							<a class="attachment-name" href="javaScript:;" data-pk="${(oAttach.fileAttachId)!}">${(oAttach.fileName)!}</a>
							<div class="full-width">
								<div class="col-sm-12">
									
								</div>
							</div>
							<div class="btn-group mobilink-document-upload">
								
								<button type="button" class="remove-uploaded-file red" action-event="fa-times" title="Xóa tệp đã tải lên" data-pk="${(oAttach.fileAttachId)!}">
									<i aria-hidden="true" class="fa fa-times"></i>
								</button>
								

							</div>
						</li>
					</#list>
				</#if>
			</ul>
		</div>
	</div>
	
</form>

<script type="text/javascript">
$(function () {

	var employeeAttachFileBaseUrl = "${api.endpoint}/fileattachs";

	$('#fm-employee-detail-fileupload').fileupload({
		
		url: employeeAttachFileBaseUrl+"/upload" ,
		dataType: 'json',
		sequentialUploads: true,
		autoUpload: false,
		type: 'POST',
		beforeSend: function(xhr) {
			xhr.setRequestHeader("groupId", ${groupId});
		},
		formData: function( form ){

			var formDataI = form.serializeArray();

			formDataI.push( {"name": "fileName", "value": this.files[0].name} );
			formDataI.push( {"name": "fileType", "value": this.files[0].type} );
			formDataI.push( {"name": "fileSize", "value": this.files[0].size} );
			formDataI.push( {"name": "className", "value": "${(constants.className)!}"} );
			formDataI.push( {"name": "classPK", "value": "${(params.employeeId)!}" } );

			return formDataI;
		},	
		add: function (e, data) {
			
			var files = data.files;

			if(files != null && files.length > 0){
				var fileList = $('#employee-detail-files');

				$.each(files, function(index, file){
					
					var uploadId = makeid(5) + '-' + new Date().getTime();

					data.uploadId = uploadId;

					var template = 
						'<li class="PL0" id="upload-' + uploadId + '">' + 
							'<i class="fa fa-angle-double-right icon-left" aria-hidden="true"></i>' +
							'<a class="attachment-name" href="javaScript:;">' + file.name + '</a>' +
							'<div class="full-width">' +
								'<div class="col-sm-12">' +
									'<div class="progress-bar-contaier">' +
										'<span class="file-upload-progress progress-bar"></span>' + 
									'</div>' +
								'</div>' +
							'</div>' +
							'<div class="btn-group mobilink-document-upload">' +
							'</div>' +
						'</li>';

					fileList.append(template);
				});

				var jqXhr  = data.submit().error(function (jqXhr, textStatus, errorThrown){

					//showMessageByAPICode(jqXhr.status);

				});

			};

			data.abortFiles = [];

		},
		change: function (e, data) {

		},
		always: function(e, data){

		},
		submit: function (e, data) {
			
			if($.inArray(data.files[0].name, data.abortFiles) >= 0){
				return false;
			}

		},
		send: function (e, data) {

		},
		start: function (e, data) {

		},
		stop: function (e, data) {


		},
		progress: function (e, data) {

			var li = $('#upload-' + data.uploadId);
			var progressBar = li.find('span.file-upload-progress');
			var progress = parseInt(data.loaded / data.total * 100, 10);
			progressBar.css(
			  'width',
			  progress + '%'
			);

		},
		done: function (e, data) {

			var result = data.result;
			var li = $('#upload-' + data.uploadId);
			var progressBar = li.find('span.file-upload-progress');
			var attachmentName = li.find('a.attachment-name');
			var btnGroup = li.find('div.btn-group');

			setTimeout(function(){
				progressBar.text('Tải lên thành công');
				progressBar.parent().remove(); 



				li.attr("id", "attachment-" + result.fileAttachId);
				attachmentName.attr('data-pk', result.fileAttachId);

				btnGroup.append('<button type="button" class="remove-uploaded-file red" action-event="fa-times" title="Xóa tệp đã tải lên" data-pk="' + result.fileAttachId + '"><i aria-hidden="true" class="fa fa-times"></i></button>');

			}, 1000);
		},
		fail: function (e, data) {

			var li = $('#upload-' + data.uploadId);
			var progressBar = li.find('span.file-upload-progress');
			var btnGroup = li.find('div.btn-group');

			setTimeout(function(){
			
				progressBar.parent().remove();
				btnGroup.append('<span style="color:red">Tải lên lỗi</span>');
				btnGroup.append('<button type="button" class="remove-upload-error red" action-event="fa-times" title="Xóa tệp tải lên lỗi"><i aria-hidden="true" class="fa fa-times"></i></button>');

				btnGroup.delegate('button.remove-upload-error','click',function() {
				
					li.remove();
				
				});

			}, 1000);

		},
		progressall: function (e, data) {

		}
	}).prop('disabled', !$.support.fileInput)
	  .parent().addClass($.support.fileInput ? undefined : 'disabled');


	$('#employee-detail-files').delegate('button.remove-uploaded-file','click', function(e) {

		if (confirm("Bạn muốn xóa tệp tin này ?")) {
				
			e.preventDefault();
			var btn = e.currentTarget;
			var dataPK = $(btn).attr('data-pk');
			var li = $(btn).closest("li");

			$.ajax({

				url: employeeAttachFileBaseUrl + '/' + dataPK,
				type: 'DELETE',
				headers: {
					"groupId": ${groupId}
				},
				success: function(result) {

					showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
					li.remove();

				},
				error: function(xhr, textStatus, errorThrown) {
					
					showMessageByAPICode(xhr.status);
				
				},

			});
		
		} else {
			return;
		}

	});

	$('#employee-detail-files').delegate('a.attachment-name','click', function(e) {

		e.preventDefault();
		var dataPK = $(e.currentTarget).attr('data-pk');

		fileAttachmentDownload({
			method: "GET",
			url: employeeAttachFileBaseUrl + '/' + dataPK,
			headers: {"groupId": ${groupId}},
			success: function(sttCode){
				//showMessageByAPICode(sttCode);
			},
			error: function(sttCode){
				showMessageByAPICode(sttCode);
			}
		});
	});

});

</script>
 
<style>
	.full-width{
		width: 100%;
	}
	
	.file-upload-progress{
		border-radius: 3px;
		height: 20px;
	}
	
	.progress-bar-contaier {
		background-color: #ededed;
		height: 20px;
	}
		
</style>