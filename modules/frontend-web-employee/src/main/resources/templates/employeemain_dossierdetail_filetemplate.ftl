<div class="modal-dialog modal-lg">

  <!-- Modal content-->
  <div class="modal-content">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal">&times;</button>
      <h4 class="modal-title">Tải hồ sơ</h4>
    </div>
    <div class="modal-body">
      <ul class="nav nav-tabs"> 
        <li class="active"> <a href="#1" data-toggle="tab">Tải giấy tờ lên</a> </li> 
        <li><a href="#2" data-toggle="tab">Kho lưu trữ</a> </li> 
      </ul> 
      <div class="tab-content "> 
        <div class="tab-pane active" id="1">
          <form>
            <div class="row" style="margin-top: 10px; margin-bottom: 10px;">
              <div class="col-sm-2">
                <strong>Tên giấy tờ <span style="color: red;">*</span></strong>
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
                  <div class="col-sm-4">
                    <input class="k-textbox form-control" name="fileTemplateNo">
                  </div>
                  <div class="col-sm-6 text-center">
                    <div class="row">
                      <div class="col-sm-4">
                        <strong>Ngày văn bản</strong>
                      </div>
                      <div class="col-sm-8">
                        <div class="form-group"> 
                          <div class="input-group date" id="datetimepicker1"> 
                            <input type="text" class="form-control intdate" placeholder="... / ... / ..."> 
                            <span class="input-group-addon"> 
                              <span class="fa fa-calendar icon-calendar"> 
                              </span> 
                            </span> 
                          </div> 
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="col-sm-2 text-right">
                    <input name="filesToUpload" id="filesToUpload" type="file" multiple onchange="makeFileList();" />
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-12">
                <ul id="fileList" class="list-inline">

                </ul>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-12" style="padding-top: 10px;">
                <button class="k-button btn-blue" style="margin-left: 20px;">Đồng ý</button>
                <button class="k-button" style="margin:0 10px;" data-dismiss="modal">Đóng</button>
                <i>Những trường có dấu <span style="color: red;">*</span> là dữ liệu bắt buộc nhập</i>
              </div>
            </div>
          </form>
        </div> 

        <div class="tab-pane" id="2">
          <div class="row" style="padding-top: 15px;">
            <div class="col-sm-4">
              <div class="form-group"> <div class="input-group stylish-input-group"> <input type="text" class="form-control" placeholder="Nhập từ khóa"> <span class="input-group-addon"> <button type="submit"> <span class="glyphicon glyphicon-search icon-search"></span> </button> </span> </div> </div>
            </div> 
            <div class="col-sm-8" style="padding-top: 5px;">
              <div class="row">
                <div class="col-sm-3">
                  <input type="checkbox" name="" id="cbx1"> <label for="cbx1">Giấy tờ nộp vào</label>
                </div>
                <div class="col-sm-3"> 
                  <input type="checkbox" name="" id="cbx2"> <label for="cbx2">Giấy tờ kết quả</label>
                </div>
                <div class="col-sm-6">
                  <input type="checkbox" name="" id="cbx3"> <label for="cbx3">Tìm kiếm giấy tờ có cùng biểu mẫu</label>
                </div>
              </div>
            </div>
          </div>
          <div class="row" style="margin-top: 10px; margin-bottom: 10px; border-bottom: 1px solid #ccc;">
            <div class="col-sm-5">
             <div class="row">
              <div class="col-sm-2 text-center">
                <strong>STT</strong>
              </div>
              <div class="col-sm-10">
                <strong>Thông tin biểu mẫu</strong>
              </div>
            </div>
          </div>
          <div class="col-sm-7">
            <div class="row">
              <div class="col-sm-5">
                <strong>Biểu mẫu </strong>
              </div>
              <div class="col-sm-5">
                <strong>Hồ sơ</strong>
              </div>
              <div class="col-sm-2">

              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <ul class="ul-with-border">
              <div id="listViewFileTemplateDialog"></div>
            </ul>
            <div id="pagerFileTemplateDialog"></div>
          </div>
        </div>

        <div class="row">
          <div class="col-sm-12" style="margin-left: 10px;">
            <button class="k-button btn-blue" id="btnShowEach">Đồng ý </button>
            <button class="k-button" data-dismiss="modal">Đóng </button>
          </div>
        </div>
        <script type="text/x-kendo-template" id="templateFileTemplateDialog">
          <li>
            <div class="row">
              <div class="col-sm-5">
                <div class="row">
                  <div class="col-sm-2 text-center">
                    <strong class="stt">1</strong>
                  </div>
                  <div class="col-sm-10">
                    Mã kí hiệu mẫu đơn: <strong>TT-THKH3</strong> <br>
                    Mã kí hiệu giấy tờ: - - - <br>
                    Ngày tạo tài liệu: <strong>11:00 am - 12-07-2017</strong>
                  </div>
                </div>
              </div>
              <div class="col-sm-7">
                <div class="row">
                  <div class="col-sm-5">
                    Ngày văn bản: --- <br>
                    Tên văn bản: <strong>Các bản vẽ thiết kế</strong>
                  </div>
                  <div class="col-sm-5">
                    Mã tiếp nhận: --- <br>
                    Mã hồ sơ: <strong>1234567T8</strong>
                  </div>
                  <div class="col-sm-2">
                    <button class="k-button form-control" style="margin-bottom: 5px;">Chọn</button>
                    <button class="k-button form-control">Xem</button>
                  </div>
                </div>
              </div>
            </div>
          </li>
        </script>
      </div>
    </div>
  </div>
</div>
</div>
<script type="text/javascript">
  var dataSourceFileTemplateDialog=new kendo.data.DataSource({
    transport:{
      read:function(options){
        $.ajax({
          url:"http://localhost:8081/api/admin/filetemplates",
          dataType:"json",
          type:"GET",
          data:{

          },
          success:function(result){
            options.success(result);
            printStt();
          },
          error:function(result){
            options.error(result);
          }
        });
      }
    },
    pageSize:3,
    schema:{
      data:"data",
      total:"total",
      model:{
        id:"fileTemplateId"
      }
    }
  });

  $("#listViewFileTemplateDialog").kendoListView({
    dataSource:dataSourceFileTemplateDialog,
    template:kendo.template($("#templateFileTemplateDialog").html())
  });
  $("#pagerFileTemplateDialog").kendoPager({
    dataSource:dataSourceFileTemplateDialog
  });

  $("#btnShowEach").click(function(){

  });

  var printStt=function(){
    var index=1;
    $(".stt").each(function(){
      $(this).text(index);
      index++;
    });
    index=1;
  }

  
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

  $('#datetimepicker1').datepicker({ icons: { time: "fa fa-clock-o", date: "fa fa-calendar", up: "fa fa-arrow-up", down: "fa fa-arrow-down" } });

</script>