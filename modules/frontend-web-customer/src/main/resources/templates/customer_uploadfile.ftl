<div class="modal-dialog modal-lg">
  <!-- Modal content-->
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal">&times;</button>
      <h4 class="modal-title">Tải hồ sơ</h4>
    </div>
    <div class="modal-body">
      <form id="uploadDossierFile" method="post" enctype="multipart/form-data">
        <div class="row MT10 MB10">
          <div class="col-sm-2">
            <strong>Tên giấy tờ <span class="red">*</span></strong>
          </div>
          <div class="col-sm-10">
            <input class="k-textbox form-control" name="fileTemplateName">
          </div>
        </div>
        <div class="row">
          <div class="col-sm-2">
            <strong>Số hiệu giấy tờ</strong>
          </div>
          <div class="col-sm-10">
            <div class="row">
              <div class="col-sm-12">
                <input class="k-textbox form-control" name="fileTemplateNo">
              </div>
              <#-- <div class="col-sm-6 text-center">
                <div class="row">
                  <div class="col-sm-4">
                    <strong>Ngày văn bản</strong>
                  </div>
                  <div class="col-sm-8">
                    <div class="form-group"> 
                      <div class="input-group date" id="datetimepicker2"> 
                        <input type="text" class="form-control intdate" placeholder="... / ... / ..."> 
                        <span class="input-group-addon"> 
                          <span class="fa fa-calendar icon-calendar"> 
                          </span> 
                        </span> 
                      </div> 
                    </div>
                  </div>
                </div>
              </div> -->
              <div class="col-sm-12 text-right MT10">
                <#-- <div class="fileinput fileinput-new input-group" data-provides="fileinput">
                 <div class="form-control" data-trigger="fileinput"><i class="glyphicon glyphicon-file fileinput-exists"></i> <span class="fileinput-filename"></span></div>
                 <span class="input-group-addon btn btn-default btn-file" id="filesToUpload" name="filesToUpload"><span class="fileinput-new">Select file</span><span class="fileinput-exists">Change</span> <input type="file" name="..."> </span> <a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">Remove</a> 
               </div> -->
               <input name="filesToUpload" id="filesToUpload" type="file" multiple onchange="makeFileList();" />
             </div>
           </div>
         </div>
       </div>
       <div class="row MT10">
        <div class="col-sm-2">

        </div>
        <div class="col-sm-10">
          <ul id="fileList" class="list-inline">

          </ul>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-12" style="padding-top: 10px;">
          <button class="btn btn-active ML20" id="btnSubmitUploadFile" type="button">Đồng ý</button>
          <button class="btn" style="margin:0 10px;" data-dismiss="modal">Đóng</button>
          <i>Những trường có dấu <span class="red">*</span> là dữ liệu bắt buộc nhập</i>
        </div>
      </div>
    </form>
  </div>
</div>
</div>
<script type="text/javascript">  
  function makeFileList() {
    var input = document.getElementById("filesToUpload");
    var ul = document.getElementById("fileList");
    while (ul.hasChildNodes()) {
      ul.removeChild(ul.firstChild);
    }
    for (var i = 0; i < input.files.length; i++) {
      var li = document.createElement("li");
      li.innerHTML = input.files[i].name;
      ul.appendChild(li);
    }
    if(!ul.hasChildNodes()) {
      var li = document.createElement("li");
      li.innerHTML = 'No Files Selected';
      ul.appendChild(li);
    }
  }
  $("#btnSubmitUploadFile").click(function(){
    var data = $('#uploadDossierFile').serialize();
    $.ajax({
      type : 'POST', 
      url  : '${api.server}/dossiers/{id}/files', 
      data : data,
      success :  function(result){                       

      },
      error:function(result){

      }
    });
    console.log("success!");
  });
  
</script>