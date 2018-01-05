<#if (Request)??>
<#include "init.ftl">
</#if>
<#-- <div class="modal-dialog modal-full"> -->
<!-- Modal content-->
<#-- <div class="modal-content"> -->

<div class="modal-body P0">
	<div class="row M0 full-width">

		<div class="col-sm-3 box no-border-radius">
			<div class="row" style="margin-bottom : 3px;">
				<div class="col-sm-12 PT15 PB15" style="background-color: #ccc;">
					<span class="text-bold">Thành phần hồ sơ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <span>#${(dossierId)!}</span>
					<#-- <i class="fa fa-times pull-right hover-pointer" aria-hidden="true" data-dismiss="modal" style="font-size: 150%;"></i> -->
				</div>
			</div>
			<div class="row">
				<form action="" id="fileArchiveForm">
					<div class="col-sm-12 P0">
						<ul id="listViewDossierFile"></ul>
						<#assign index = 0>

					<script type="text/x-kendo-template" id="templateDossierFiles">
						<div class="accordion" id="accordion1">

							<div class="accordion-group">


								<div class="accordion-heading">
									<a class="accordion-toggle dossier-partno-title slide-toggle-lv2" id="partNo#:value#" data-pk="#:value#" data-parent="" href="\\##:value#" aria-expanded="true">

									</a>
								</div>
								#
									var collapseIn = "";
									if(value === "${dossierPartNo}"){ 
										collapseIn = "in";
									} else {collapseIn = ""}
								#
								<div id="#:value#" class="accordion-body collapse #:collapseIn#" aria-expanded="true">

									<div class="accordion-inner">
										#
											for(var i=0; i < items.length; i++){
												#
													<div class="eq-height">

														<div class="col-xs-12 col-sm-9 align-middle">
															<span class="#if('${index}' === '0'){# active #}# hover-pointer item-file-component" data-pk="#:items[i].referenceUid#" data-index="${index}" >
																#:items[i].displayName#
															</span>
														</div>

														<div class="col-xs-12 col-sm-3 align-center">
															<button class="btn btn-reset btn-delete-component-profile" data-pk="#:items[i].referenceUid#" eForm="#:items[i].eForm#" type="button"><i class="fa fa-trash"></i> Xóa</button>
														</div>
													</div>
												#
											}
										#
										
									</div>

								</div>
							</div>
							<#assign index = index + 1>
						</div>
					</script>
				</div>
			</form>
		</div>
	</div>

	<div class="col-sm-9" style="height:100vh;background-color: rgba(0,0,0,0.4)">
				<#-- <div id="fileCarousel" class="carousel slide row" data-ride="carousel" data-interval="false"> 
					<ul class="carousel-inner" id="listViewCarouselDossierFile">

					</ul>
					<script type="text/x-kendo-template" id="templateCarouselDossierFile">
						<li class="item" data-pk="#:id#">
							
							<object data="${api.server}/dossiers/${dossierId}/files/#:id#/preview" type="application/pdf" width="100%" height="100%">
								<embed width="100%" height="100%" name="plugin" src="${api.server}/dossiers/${dossierId}/files/#:id#/preview" />
							</object>
						</li>
					</script>
					<a class="left carousel-control control-left" href="#fileCarousel" data-slide="prev">
						<span class="glyphicon glyphicon-chevron-left"></span>
						<span class="sr-only">Previous</span>
					</a>
					<a class="right carousel-control control-right" href="#fileCarousel" data-slide="next">
						<span class="glyphicon glyphicon-chevron-right"></span>
						<span class="sr-only">Next</span>
					</a>
				</div> -->

				<object id="objectView2" data="" width="100%" height="100%">

				</object>
			</div>

		</div>
	</div>

	<#-- </div> -->

	<#-- </div> -->

	<script type="text/javascript">
		$(function(){
			/*		var template = kendo.template($("#template").html());*/
			$(document).off("click",".btn-delete-component-profile");
			$(document).on("click",".btn-delete-component-profile",function(event){

				var id=$(this).attr("data-pk");
				var eForm = $(this).attr("eForm");
				var cf = confirm("Bạn có muốn xóa tệp tin này!");
				if(cf){
					if (eForm == "false") {
						console.log("eForm false");
						$.ajax({
							url : "${api.server}/dossiers/${dossierId}/files/"+id,
							type : "DELETE",
							dataType : "json",
							headers : {"groupId": ${groupId}},
							data : {

							},
							success:function(result){

								dataSourceDossierFile.pushDestroy(result);

								notification.show({
									message: "Xóa thành công!"
								}, "success");

							},
							error:function(result){
								notification.show({
									message: "Xẩy ra lỗi, vui lòng thử lại"
								}, "error");
							}

						});
					}else {
						console.log("eForm true");
						$.ajax({
							url : "${api.server}/dossiers/${dossierId}/files/"+id+"/formdata",
							type : "PUT",
							dataType : "json",
							headers : {"groupId": ${groupId}},
							data : {
								formdata: JSON.stringify({})
							},
							success:function(result){

								notification.show({
									message: "Xóa thành công!"
								}, "success");

							},
							error:function(result){
								notification.show({
									message: "Xẩy ra lỗi, vui lòng thử lại"
								}, "error");
							}

						});
					}
				}

				console.log(id);
			});

			var dataSourceDossierFile=new kendo.data.DataSource({
				transport : {
					read : function(options) {
						$.ajax({
							url : "${api.server}/dossiers/${dossierId}/files",
							type : "GET",
							dataType : "json",
							headers : {"groupId": ${groupId}},
							success : function (result) {
								options.success(result);
								if(!result.data){

									notification.show({
										message: "Bạn chưa tải file lên, Vui lòng tải lên để xem"
									}, "error");

								}
							},
							error : function (result) {
								options.error(result);
							}
						});
					}
				},
				schema : {
					data : "data",
					total : "total"
				},
				group: { field: "dossierPartNo" },
				change: function() { 

				}
			});

			$("#listViewDossierFile").kendoListView({
				dataSource : dataSourceDossierFile,
				template : kendo.template($("#templateDossierFiles").html()),
				selectable : "single",
				dataBound : function(e) {
					console.log(e);
					printDossierPartName();
					fnViewFirst();
				},
				change: function(){
					var data = dataSourceDossierFile.view(),
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
								$("#objectView2").html("");
								$("#objectView2").append('<iframe src="'+urlOut+'" width="100%" style="height:100vh">    </iframe>');

							},
							error: function(){}
						});


					}
				}
			});


			$(document).on("click",".item-file-component",function(event){
				$(".item-file-component").removeClass("text-light-blue");
				$(this).addClass("text-light-blue");

			});

			var printDossierPartName = function() {
				$.ajax({
					url : "${api.server}/dossiertemplates/"+"${(dossier.dossierTemplateNo)!}",
					type : "GET",
					dataType : "json",
					headers : {"groupId": ${groupId}},
					data : {

					},
					async : false,
					success:function(result){
						var data =  result.dossierParts;
						console.log(data);
						if (data) {
							for(var i = 0; i<data.length; i++){

								$("#partNo"+data[i].partNo).html(data[i].partName);
							}
						}
					},
					error:function(result){

					}

				});
			}


		});

var fnViewFirst = function(){
	var data = dataSourceDossierFile.view();
	for(var i = 0; i < data.length ; i++){
		if(data[i].dossierPartNo == "${(dossierPartNo)!}"){
			
			$("#listViewDossierFile").data("kendoListView").select(i);
			return ;
		}
	}
	
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

var fnConvertArrFile = function(arrFile){
	var arrConvert = new Object();
	if(arrFile){
		var flag = 0;
		for (var i = 0; i < arrFile.length; i++) {
			if(flag !== arrFile[i]){
				var obj = new Object()
			}
		}
	}
}
</script>