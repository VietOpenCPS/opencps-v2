<#if (Request)??>
	<#include "init.ftl">
</#if>

<div id="registration_template_part_model">

  <form id="fm">
    <div class="row MT10">
      <div class="col-xs-12 col-sm-12">
        <div class="row">
          <div class="col-xs-12 col-sm-12">
            Cơ quan tiếp nhận hồ sơ doanh nghiệp:
          </div>
        </div>
        <div class="row MT5">
          <div class="col-xs-12 col-sm-6">
            <input name="govAgency" id="govAgencyDetail" data-role="combobox" data-placeholder="" data-text-field="itemName" data-value-field="itemCode" data-source="dataGovAgencyDetail">
          </div>
        </div>
      </div>
    </div>
    <div class="row MT10">
      <div class="col-xs-12 col-sm-8">
        <div class="row">
          <div class="col-xs-12 col-sm-12">
            Số hiệu thành phần
          </div>
        </div>
        <div class="row MT5">
          <div class="col-xs-12 col-sm-12">
            <input id="formNo" name="formNo" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:formNo"/>
          </div>
        </div>
      </div>
      <div class="col-xs-12 col-sm-4">
        <div class="row">
          <div class="col-xs-12 col-sm-12 invisible">invisible</div>
        </div>
        <div class="row MT5">
          <div class="col-xs-12 col-sm-12">
            <div class="checkbox MB0 MT5"> <input type="checkbox" id="required" name="required"> <label>Cho phép tạo nhiều thành phần</label> </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row MT10">
      <div class="col-xs-12 col-sm-12">
        <div class="row">
          <div class="col-xs-12 col-sm-12">
            Tên thành phần
          </div>
        </div>
        <div class="row MT5">
          <div class="col-xs-12 col-sm-12">
            <input id="formName" name="formName" class="k-textbox form-control" required="required" validationMessage="Trường nhập yêu cầu bắt buộc" data-bind="value:formName"/>
          </div>
        </div>
      </div>
    </div>

    <div class="row MT10">
      <div class="col-xs-12 col-sm-12">
        <div class="row">
          <div class="col-xs-12 col-sm-12">
            Mã tạo form
          </div>
        </div>
        <div class="row MT5">
          <div class="col-xs-12 col-sm-12">
            <textarea id="formScript" rows="5" name="formScript" class="k-textbox form-control" data-bind="value:formScript"></textarea>
          </div>
        </div>
      </div>
    </div>
    <div class="row MT10">
      <div class="col-xs-12 col-sm-12">
        <div class="row">
          <div class="col-xs-12 col-sm-12">
            Mã thiết kế xml jasper
          </div>
        </div>
        <div class="row MT5">
          <div class="col-xs-12 col-sm-12">
            <textarea id="formReport" rows="5" name="formReport" class="k-textbox form-control" data-bind="value:formReport"></textarea>
          </div>
        </div>
      </div>
    </div>
    <div class="row MT10">
      <div class="col-xs-12 col-sm-12">
        <div class="row">
          <div class="col-xs-12 col-sm-12">
            Dữ liệu mẫu
          </div>
        </div>
        <div class="row MT5">
          <div class="col-xs-12 col-sm-12">
            <textarea id="sampleData" rows="5" name="sampleData" class="k-textbox form-control" data-bind="value:sampleData"></textarea>
          </div>
        </div>
      </div>
    </div>
    <div class="row MT10 text-center">
      <button id="btn_save_registration_template_part" class="k-button btn-primary" title="Ghi lại">Ghi lại</button>
      <button id="btn_cancel_registration_template_part" class="k-button btn-default" title="Hủy bỏ">Hủy bỏ</button>
      <button class="btn btn-active" id="btn-view-registrationtemplate-form" type="button">
        View Form
      </button>
    </div>
  </form>
</div>

<div id="registrationTemplateFormPreView" class="modal fade" role="dialog"> 
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
            <div id="registrationtemplate-form-alpaca">

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
  $(document).on("click", "#btn_cancel_registration_template_part", function(event){
    event.preventDefault();
    $("#registration_template_list_view").getKendoListView().dataSource.read();
  });
  // Button "Ghi lại"
  $(document).on("click", "#btn_save_registration_template_part", function(event){
    event.preventDefault();
    var registrationTemplatePartDataPk = $(this).attr("data-pk");
    updateregistrationTemplatePart(registrationTemplatePartDataPk);
  });
  // Button "ViewForm"
  $(document).on("click","#btn-view-registrationtemplate-form",function(){
    var formScript = $("textarea#formScript").val();
    var sampleData = $("textarea#sampleData").val();
    if(formScript){
      
      try {
        var formJson = eval("(" + formScript + ")");
        $("#registrationtemplate-form-alpaca").empty();
        $("#registrationtemplate-form-alpaca").alpaca(formJson);

        $("#registrationTemplateFormPreView").modal("show");

      } catch (e) {
        notification.show({
          message: "Form lỗi, Vui lòng kiểm tra lại cú pháp!"
        }, "error");

      }
    }else { 
      notification.show({
        message: "Vui lòng nhập Mã tạo form!"
      }, "error");
    }  
  }); 
  // Function event "Ghi lại"
  var updateregistrationTemplatePart = function(registrationTemplatePartDataPk){
    if (!validateTemplatePart()){
      return false;
    }
    var url = "";
    var type = "";

    // console.log(registrationTemplatePartDataPk);
    // console.log($("#govAgencyDetail").val());
    // console.log($("#formName").val());
    // console.log($("#formNo").val());
    // console.log($('#required').is(':checked'));

    if (registrationTemplatePartDataPk){
      url = "${api.server}" + "/registrationtemplates/" + registrationTemplatePartDataPk;
      type = "PUT";
    } else {
      url = "${api.server}" + "/registrationtemplates";
      type = "POST";
    }

    // console.log(url + type);

    $.ajax({
      url: url,
      type: type,
      dataType: "json",
      headers: {"groupId": ${groupId}},
      data: {
        govAgencyCode : $("#govAgencyDetail").val(),
        formNo: $("#formNo").val(),
        formName: $("#formName").val(),
        multiple: $('#required').is(':checked')
      },
      success: function(result) {
        
        registrationTemplatePartDataPk = result.registrationTemplateId;

        var upFormscriptSuccess = false, upFormReportSuccess = false, upSampleDataSuccess = false;
        $.ajax({
          url: "${api.server}" + "/registrationtemplates/" + registrationTemplatePartDataPk +"/formscript",
          type: "PUT",
          dataType: "json",
          headers: {"groupId": ${groupId}},
          async: false,
          data: {
            formScript: $("#formScript").val()
          },
          success: function(result) {
            upFormscriptSuccess = true;
          }
        });
        $.ajax({
          url: "${api.server}" + "/registrationtemplates/" + registrationTemplatePartDataPk +"/formreport",
          type: "PUT",
          dataType: "json",
          headers: {"groupId": ${groupId}},
          async: false,
          data: {
            formReport: $("#formReport").val()
          },
          success: function(result) {
            upFormReportSuccess = true;
          }
        });
        $.ajax({
          url: "${api.server}" + "/registrationtemplates/" + registrationTemplatePartDataPk +"/sampledata",
          type: "PUT",
          dataType: "json",
          headers: {"groupId": ${groupId}},
          async: false,
          data: {
            sampleData: $("#sampleData").val()
          },
          success: function(result) {
            upSampleDataSuccess = true;
          }
        });

        if (upFormscriptSuccess && upFormReportSuccess && upSampleDataSuccess){
          notification.show({
            message: "Yêu cầu được thực hiện thành công"
          }, "success");

          $("#registration_template_list_view").getKendoListView().dataSource.read();
          
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
    if (!$("#formNo").val()){
      notification.show({
        message: "Mời nhập số hiệu thành phần"
      }, "error");
      return false;
    }
    if (!$("#formName").val()){
      notification.show({
        message: "Mời nhập tên thành phần"
      }, "error");
      return false;
    }
    
    return true;
  }

</script>

<script type="text/javascript">

  var dataGovAgencyDetail = new kendo.data.DataSource({
    transport:{
      read:{
        // url: "http://localhost:3000/dictitems",
        url:"${api.server}/dictcollections/GOVERNMENT_AGENCY/dictitems",
        dataType:"json",
        type:"GET",
        headers : {"groupId": ${groupId}}
      }
    },
    schema:{
      data:"data",
      total:"total"
    }
  });
  var govAgencyDetail = kendo.observable({
    dataGovAgencyDetail: dataGovAgencyDetail,
    dataBound: function() {
      $(".k-clear-value").addClass("k-hidden")
    }
  });
  kendo.bind($("#govAgencyDetail"), govAgencyDetail);
</script>


