<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="panel">
  <div class="">
    <div class="row row MT10 PL10 PR10">
      <div class="col-xs-12 col-sm-3">
        <button id="btn_fillter_by_admintration" class="btn btn-active form-control">Tìm theo cơ quan <i class="fa fa-university ML5" aria-hidden="true"></i>
        </div>
        <div class="col-xs-12 col-sm-3 PL0">
          <button id="btn_fillter_by_domain" class="btn form-control">Tìm theo lĩnh vực <i class="fa fa-archive ML5" aria-hidden="true"></i>
          </div>
          <div class="col-xs-12 col-sm-6 PL0">
           <div class="form-group">
            <div class="input-group">
             <input type="text" class="form-control" id="input_search" placeholder="Nhập từ khóa" title="Nhập từ khóa">
             <div class="input-group-addon" id="btn_search" title="Tìm kiếm">
               <a href="#"><i class="fa fa-search"></i></a>
             </div>
           </div>
         </div>
       </div>
     </div>
   </div>
 </div>
 
 <div id="serviceconfig_container">
  <#include "serviceconfig_administration.ftl">
</div>

<div id="choiseProcessForDossier" class="modal fade" role="dialog">
  <div class="modal-dialog  modal-lg">
    <div class="modal-content">

      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Lựa chọn trường hợp nộp hồ sơ</h4>
      </div>

      <div class="modal-body">
        <ul id="lvProcessServiceConfig" class="ul-with-border"></ul>
        <script type="text/x-kendo-template" id="templateProcessServiceConfig">
          <li class="">
           <div class="row">
            <div class="col-xs-1 text-center">
              #:itemIndex#
            </div>
            <div class="col-xs-9">
              #:templateName#
            </div>
            <div class="col-xs-2 text-center">
              <button class="btn btn-active btn-sm btn-choise-process" data-pk="#:id#"> Chọn</button>
            </div>
          </div>
        </li>
      </script>
    </div>

  </div>
</div>
</div>

<script type="text/javascript">
  var dataSourceProcessServiceConfig;

  $(document).ready(function(){

    $('#btn_fillter_by_domain').click(function(){
      $('#btn_fillter_by_admintration').removeClass('btn-active');
      $('#btn_fillter_by_domain').addClass('btn-active');
      $('#serviceconfig_container').load("${ajax.serviceconfig_domain}");
    });

    $('#btn_fillter_by_admintration').click(function(){
      $('#btn_fillter_by_admintration').addClass('btn-active');
      $('#btn_fillter_by_domain').removeClass('btn-active');
      $('#serviceconfig_container').load("${ajax.serviceconfig_administration}");
    });

    $('#btn_fillter_by_admintration').click(function() {
      $('#input_search').val('');
    });

    $('#btn_fillter_by_domain').click(function() {
      $('#input_search').val('');
    });


    /*$('#btn_search').change(function(){
      searchServiceInfo();
    });

    function searchServiceInfo(){
      var input_Search = $('#input_search').val();
        
      if ($('#btn_fillter_by_admintration').hasClass('btn-active')){

        dataSourceAdmin.read({
          keyword: input_search,
        });
        alert(typeof dataSourceAdmin);
        //$('#serviceconfig_container').load("${ajax.serviceconfig_domain}&" + $('#input_search').val());
      }
      if ($('#btn_fillter_by_domain').hasClass('btn-active')){
        dataSourceServiceConfigDomain.read({
          keyword: input_search,
        });
        //$('#serviceconfig_container').load("${ajax.serviceconfig_administration}&" + $('#input_search').val());
      }
    }*/

    dataSourceProcessServiceConfig = new kendo.data.DataSource({
      transport : {
        read : function(options){
          $.ajax({
            url : "${api.server}/serviceconfigs/"+options.data.serviceConfigId+"/processes",
            dataType : "json",
            type : "GET",
            headers : {"groupId": ${groupId}},
            success :  function(result){
              if(result.data){
                options.success(result);
                if (result.data.length === 1) {
                  console.log(result.data[0].processOptionId);
                  fnGetParamAndCreateDossier(result.data[0].processOptionId);
                  
                }else if (result.data.length > 1){
                  
                  $("#choiseProcessForDossier").modal("show");
                }
                
              }
            },
            error : function(result){
              options.error(result);
            }

          });
        }
      },
      schema : {
        total : "total",
        data : "data",
        model : {
          id : "processOptionId"
        }
      }
    });

    var indexProcess = 0;
    $("#lvProcessServiceConfig").kendoListView({
      dataSource : dataSourceProcessServiceConfig,
      autoBind : false,
      change : function(){

      },
      template : function(data){

        indexProcess ++;

        data.itemIndex = indexProcess;

        return kendo.template($("#templateProcessServiceConfig").html())(data);

      },
      dataBound : function(){
        indexProcess = 0;
        fnGenEventChoiseProcess();
      }

    });

    var fnGenEventChoiseProcess = function(){
      $(".btn-choise-process").unbind().click(function(){
        var processOptionId = $(this).attr("data-pk");
        console.log("process id");
        console.log(processOptionId);

        if(processOptionId){
          fnGetParamAndCreateDossier(processOptionId);
        }
      }); 
    }

    var fnGetParamAndCreateDossier =  function(processOptionId){
      var item = dataSourceProcessServiceConfig.get(processOptionId);
      var serviceConfigId = $("#serviceConfigId").val();
      if(item && serviceConfigId){
        var dossierTemplateId = item.dossierTemplateId;

        $.ajax({
          url : "${api.server}/serviceconfigs/"+serviceConfigId,
          dataType : "json",
          type : "GET",
          headers : {"groupId": ${groupId}},
          success : function(result){
            if(result){
              fnCreateDossier(dossierTemplateId, result.serviceCode, result.govAgencyCode);
            }
          },
          error : function(result){

          }

        });

      } 
    }

    var fnCreateDossier = function(dossierTemplateId,serviceCode,govAgencyCode){
      $.ajax({
        url : "${api.server}/dossiers",
        dataType : "json",
        type : "POST",
        data : {
          referenceUid : "",
          serviceCode : serviceCode,
          govAgencyCode : govAgencyCode,
          dossierTemplateNo : dossierTemplateId,
          applicantName : "${(applicant.applicantName)!}",
          applicantIdType : "${(applicant.applicantIdType)!}",
          applicantIdNo : "${(applicant.applicantIdNo)!}",
          applicantIdDate : "${(applicant.applicantIdDate)!}",
          address : "${(applicant.address)!}",
          cityCode : "${(applicant.cityCode)!}",
          districtCode : "${(applicant.districtCode)!}",
          wardCode : "${(applicant.wardCode)!}",
          contactName : "${(applicant.contactName)!}",
          contactTelNo : "${(applicant.contactTelNo)!}",
          contactEmail : "${(applicant.contactEmail)!}",
          password : "${(applicant.password)!}",
          online : "${(applicant.online)!}"
        },
        headers : {"groupId": ${groupId}},
        success : function(result){
          // neu thanh cong dong dialog
          $("#choiseProcessForDossier").modal("hide");

         // chuyen den man hinh chuan bi ho so
         $("#dossier_detail").load("${ajax.customer_dossier_detail}", function(result){
          $("#dossier_list").hide();
          $("#dossier_detail").show();
          $("#left_container").html("");
        });

       },
       error : function(result){
       }

     });
    }

  });
</script>
