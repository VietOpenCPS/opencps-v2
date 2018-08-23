<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="modal-dialog modal-full">
	<!-- Modal content-->
	<div class="modal-content">
		<div class="modal-body eq-height">

			<div class="row eq-height M0 full-width">
				<div class="col-sm-3 box no-border-radius">
					<div class="row">
						<div class="col-sm-12 PT15 PB15" style="background-color: #ccc;">
							<span class="text-bold">Kho lưu trữ</span> <i class="fa fa-times pull-right hover-pointer" aria-hidden="true" data-dismiss="modal" style="font-size: 150%;"></i>
						</div>
					</div>
					<div class="row">
						<form action="" id="fileArchiveForm">
							<div class="col-sm-12 PT10">
								<div class="form-group search-icon">
									<input type="text" class="form-control" placeholder="Nhập từ khóa" id="keywordFileArchive" name="keywordFileArchive">
								</div>
							</div>
							<div class="col-sm-12">
								<ul id="lvDossierFileArchive" style="height:450px; overflow:auto;">

								</ul>
								<script type="text/x-kendo-template" id="dossierFileArchiveTemp">
									<li>
										<div class="">
											<input class="cbxDossierFile MR5 " data-pk="#:id#" type="checkbox" dossierfile-id="#:dossierFileId#"><label class="dossierFileItem">#:displayName#</label> 
										</div> 
									</li>
								</script>
							</div>
							<div class="col-sm-12">
								<button class="btn btn-active" id="btnChoiseFileArchive">Chọn</button>
							</div>
						</form>
					</div>
				</div>
				
				<div class="col-sm-9">
					
					<object id="objectViewPayment" data="" width="100%" height="100%">

					</object>
				</div>


			</div>
		</div>
	</div>
</div>
</div>
<script type="text/javascript">
	$(function(){

		$(document).on("click",".btn-delete-file",function(event){
			var id=$(this).attr("data-pk");
			console.log(id);
		});

		var dataSourceDossierFileArchive=new kendo.data.DataSource({
			transport : {
				read : function(options){
					$.ajax({
						url : "${api.server}/dossiers/dossierfiles",
						type : "GET",
						dataType : "json",
						headers: {"groupId": ${groupId}},
						data : {
							keyword : options.data.keyword,
							type : 1,
							owner : true,
							original : true
						},
						success : function(result){
							options.success(result);
						},
						error : function(result){
							options.error(result);
						}
					});
				}
			},
			schema : {
				data : "data",
				total : "total",
				model : {
					id : "referenceUid"
				}
			},
			change: function() { 
				$("#fileInner > li").first().addClass("active");
			}
		});

		dataSourceDossierFileArchive.read();

		$("#lvDossierFileArchive").kendoListView({
			dataSource : dataSourceDossierFileArchive,
			template : kendo.template($("#dossierFileArchiveTemp").html()),
			selectable : "single",
			change: onChange,
			dataBound : function(e){
				console.log("first");
				console.log($(".dossierFileItem").first());
				$(".dossierFileItem").first().addClass("text-light-blue");
			}
		});

		function onChange() {
			var data = dataSourceDossierFileArchive.view(),
			selected = $.map(this.select(), function(item) {
				
				return data[$(item).index()].id;
			});
			console.log(selected);
			if(selected){
				var url = "${api.server}/dossiers/${dossierId}/files/"+selected;
				var urlOut = "cvb";
				var urlReadFile = fileAttachmentUrl({
					method : "GET",
					url : url,
					async : false,
					success: function(options){
						urlOut = options.url;
						$("#objectViewPayment").html("");
						$("#objectViewPayment").append('<iframe src="'+urlOut+'" width="100%" height="100%">    </iframe>');

					},
					error: function(){}
				});

			}
			

		}


		$(document).on("click",".dossierFileItem",function(event){
			$(".dossierFileItem").removeClass("text-light-blue");
			$(this).addClass("text-light-blue");
			
		});

		$("#btnChoiseFileArchive").click(function(){
			var arrChecked=new Array();
			$(".cbxDossierFile").each(function(){
				if($(this).prop('checked')){
					arrChecked.push($(this).attr("dossierfile-id"));
				}
			});

			if(arrChecked){
				var dossierPartNo = "${(dossierPartNo)!}";
				var dossierTemplateNo = "${(dossierTemplateNo)!}";
				var dossierId = "${(dossierId)!}";
				var arrIsSucess = new Array();
				for (var i = 0; i < arrChecked.length; i++) {
					var fileId = arrChecked[i];
					var isSucess = fnCopyFile(fileId, dossierPartNo, dossierTemplateNo, dossierId);
					arrIsSucess.push(isSucess);
					

				}
				
				if(arrIsSucess){
					
					if( arrIsSucess.indexOf(false) < 0 ){

						notification.show({
							message: "Yêu cầu được thực hiện thành công!"
						}, "success");

					}else {

						notification.show({
							message: "Xảy ra lỗi, Vui lòng thử lại!"
						}, "error");

					}
					
					
				}
			}
			
		});

		var fnCopyFile = function(fileId, dossierPartNo, dossierTemplateNo, dossierId){
			var isCopy = false;
			$.ajax({
				url : "${api.server}/dossiers/${dossierId}/files/copyfile",
				dataType : "json",
				type : "POST",
				headers: {"groupId": ${groupId}},
				async : false,
				data : {
					dossierTemplateNo : dossierTemplateNo,
					dossierPartNo : dossierPartNo,
					dossierFileId : fileId
				},
				success : function(result){
					isCopy = true;
				},
				error : function(xhr){
					isCopy = false;
				}

			});
			return isCopy;
		}

		$('.control-left').click(function() {
			var index=getCurrentIndexSlide();
			$("#lvDossierFileArchive").data("kendoListView").select(index);

		});

		$('.control-right').click(function() {
			var index=getCurrentIndexSlide();
			$("#lvDossierFileArchive").data("kendoListView").select(index);
		});

		var getCurrentIndexSlide=function(){
			var currentIndex = $('#fileCarousel li.active').index() + 1;
			return currentIndex;
		}

		$("#keywordFileArchive").change(function(){
			var keyword = $(this).val();
			dataSourceDossierFileArchive.read({
				keyword : keyword
			});
		});
	});

var fnUrlView = function(id){
	var urlView = "http://docs.google.com/gview?url=${api.server}/dossiers/${dossierId}/files/"+id+"&embedded=true";
	return urlView; 
}

function fileAttachmentUrl ( options) {

  // Use XMLHttpRequest instead of Jquery $ajax
  var xhttp = new XMLHttpRequest();
  var a,filename;
  var data = {};
  
  xhttp.onreadystatechange = function() {
  	
  	if (xhttp.readyState === 4 && xhttp.status === 200) {
  		
      // check for a filename
      var disposition = xhttp.getResponseHeader('Content-Disposition');
      if (disposition && disposition.indexOf('attachment') !== -1) {
      	var filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
      	var matches = filenameRegex.exec(disposition);
      	if (matches != null && matches[1]) filename = matches[1].replace(/['"]/g, '');
      }
      
      // Trick for making downloadable link
      a = document.createElement('a');
      a.href = window.URL.createObjectURL(xhttp.response);

      var url = window.URL.createObjectURL(xhttp.response);
      
      //callback success
      options.success({url : url, status : xhttp.status});
  } else if (xhttp.readyState === 4 && xhttp.status !== 200) {
  	options.error(xhttp.status);
  }
  
};

  // Post data to URL which handles post request
  xhttp.open(options.method, options.url);
  xhttp.setRequestHeader("Content-Type", "application/json");
  
  // others data header
  if (options.hasOwnProperty("headers")){
  	Object.keys( options.headers ).map(function(objectKey, index) {
  		var value = options.headers[objectKey];
  		xhttp.setRequestHeader(objectKey, value);
  	});
  }

  // You should set responseType as blob for binary responses
  if (options.hasOwnProperty("responseType")){
  	xhttp.responseType = options.responseType;
  } else {
  	xhttp.responseType = 'blob';
  }
  
  // Data to post
  if (options.hasOwnProperty("data")){
  	data = options.data;
  }
  
  // excecute request
  xhttp.send(data);
  
};

</script>