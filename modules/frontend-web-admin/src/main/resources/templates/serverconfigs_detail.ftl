<#if (Request)??>
	<#include "init.ftl">
</#if>

<div id="serverconfigs_part_model">

  <form id="fm">
    
    <div class="row MT10">
      <div class="col-xs-12 col-sm-12">
        <div class="row">
          <div class="col-xs-12 col-sm-12">
            Mã Server
          </div>
        </div>
        <div class="row MT5">
          <div class="col-xs-12 col-sm-12">
            <input id="serverNo" name="serverNo" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:serverNo"/>
          </div>
        </div>
      </div>
    </div>
    <div class="row MT10">
      <div class="col-xs-12 col-sm-12">
        <div class="row">
          <div class="col-xs-12 col-sm-12">
            Tên Server
          </div>
        </div>
        <div class="row MT5">
          <div class="col-xs-12 col-sm-12">
            <input id="serverName" name="serverName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:serverName"/>
          </div>
        </div>
      </div>
    </div>

    <div class="row MT10">
      <div class="col-xs-12 col-sm-12">
        <div class="row">
          <div class="col-xs-12 col-sm-12">
            Loại giao thức sử dụng
          </div>
        </div>
        <div class="row MT5">
          <div class="col-xs-12 col-sm-12">
            <textarea id="protocol" rows="5" name="protocol" class="k-textbox form-control" data-bind="value:protocol"></textarea>
          </div>
        </div>
      </div>
    </div>
    <div class="row MT10">
      <div class="col-xs-12 col-sm-12">
        <div class="row">
          <div class="col-xs-12 col-sm-12">
            Tham số cofigs
          </div>
        </div>
        <div class="row MT5">
          <div class="col-xs-12 col-sm-12">
            <textarea id="lastSync" rows="5" name="lastSync" class="k-textbox form-control" data-bind="value:lastSync"></textarea>
          </div>
        </div>
      </div>
    </div>
    <div class="row MT10 text-center">
      <button id="btn_save_serverconfigs_part" class="k-button btn-primary" title="Ghi lại">Ghi lại</button>
      <button id="btn_cancel_serverconfigs_part" class="k-button btn-default" title="Hủy bỏ">Hủy bỏ</button>
    </div>
  </form>
</div>

<div id="serverconfigsFormPreView" class="modal fade" role="dialog"> 
  <div class="modal-dialog modal-lg">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Preview</h4>
      </div>
      <div class="modal-body">
        <div class="row MB15">
          <div class="col-xs-12" style="height:450px;width:100%;overflow:auto;">
            <div id="serverconfigs-form-alpaca">

            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-xs-12">
            <button class="btn btn-default" data-dismiss="modal">
              Đóng
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
  // Button "Hủy bỏ"
  $(document).on("click", "#btn_cancel_serverconfigs_part", function(event){
    event.preventDefault();
    $("#serverconfigs_list_view").getKendoListView().dataSource.read();
  });
  // Button "Ghi lại"
  $(document).on("click", "#btn_save_serverconfigs_part", function(event){
    event.preventDefault();
    var serverconfigsPartDataPk = $(this).attr("data-pk");
    updateserverconfigsPart(serverconfigsPartDataPk);
  });
  // Button "ViewForm"
  // $(document).on("click","#btn-view-registrationtemplate-form",function(){
  //   var thamso = $("textarea#thamso").val();
  //   if(formScript){
      
  //     try {
  //       var formJson = eval("(" + formScript + ")");
  //       $("#registrationtemplate-form-alpaca").empty();
  //       $("#registrationtemplate-form-alpaca").alpaca(formJson);

  //       $("#registrationTemplateFormPreView").modal("show");

  //     } catch (e) {
  //       notification.show({
  //         message: "Form lỗi, Vui lòng kiểm tra lại cú pháp!"
  //       }, "error");

  //     }
  //   }else { 
  //     notification.show({
  //       message: "Vui lòng nhập Mã tạo form!"
  //     }, "error");
  //   }  
  // }); 
  // Function event "Ghi lại"
  var updateserverconfigsPart = function(serverconfigsPartDataPk){
    if (!validateTemplatePart()){
      return false;
    }
    var url = "";
    var type = "";

    // console.log('check: ',check);
    if (serverconfigsPartDataPk){
      // url = "${api.server}" + "/registrationtemplates/" + registrationTemplatePartDataPk;
      url="${api.server}/serverconfigs/"+ serverconfigsPartDataPk;
      type = "PUT";
    } else {
      // url = "${api.server}" + "/registrationtemplates";
      url="${api.server}/serverconfigs";
      type = "POST";
    }

    // console.log(url + type);

    $.ajax({
      url: url,
      type: type,
      dataType: "json",
      headers: {"groupId": ${groupId}},
      data: {
        serverNo: $("#serverNo").val(),
        serverName: $("#serverName").val(),
        protocol: $("#protocol").val(),
      },
      success: function(result) {
        
         var h = result.serverConfigId;

        var upFormscriptSuccess = false, upFormReportSuccess = false, upSampleDataSuccess = false;
        $.ajax({
          // url: "${api.server}" + "/registrationtemplates/" + registrationTemplatePartDataPk +"/formscript",
          url:"${api.server}/serverconfigs/"+h+"/configs",
          type: "PUT",
          dataType: "json",
          headers: {"groupId": ${groupId}},
          async: false,
          data: {
            lastSync: $("#lastSync").val()
          },
          success: function(result) {
            upFormscriptSuccess = true;
          }
        });
        if (upFormscriptSuccess && upFormReportSuccess && upSampleDataSuccess){
          notification.show({
            message: "Yêu cầu được thực hiện thành công"
          }, "success");

          $("#serverconfigs_list_view").getKendoListView().dataSource.read();
          
        } else {
          notification.show({
            message: "Xảy ra lỗi, vui lòng thử lại"
          }, "error");
        }
      },
      error: function(result) {
        notification.show({
          message: "Xảy ra lỗi, vui lòng thử lại"
        }, "error");
      }
    });
  };
  // Function event validate
  function validateTemplatePart(){
    if (!$("#serverNo").val()){
      notification.show({
        message: "Mời nhập số hiệu thành phần"
      }, "error");
      return false;
    }
    if (!$("#serverName").val()){
      notification.show({
        message: "Mời nhập tên thành phần"
      }, "error");
      return false;
    }
    
    return true;
  }

</script>




