<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="panel" id="completedDossierForm">
  <div class="panel-heading P0">
    <div class="row-header">
      <div class="background-triangle-big">Tên thủ tục</div>
      <span class="panel-title" data-bind="text:serviceName"><#-- Cấp giấy chứng nhận đăng ký bản quền tác giả cho cá nhân, tổ chức Việt Nam --></span>
    </div>
  </div>

  <div class="panel-body">

    <div class="row">
      <h3 class="text-center">NỘP HỒ SƠ THÀNH CÔNG</h3>
      <p class="text-center">Mã hồ sơ đã nộp: <span class="text-light-blue" data-bind="text:dossierNo"></span> <span class="text-bold" style="color: green;"></span> </p> 
    </div>

    <div class="row">
      <div class="col-xs-12 col-sm-12">
        <p><span class="text-bold">Ông/bà vui lòng gửi trực tiếp hoặc qua đường Bưu điện các giấy tờ sau đến <span data-bind="text:govAgencyName"></span> để hoàn tất thủ tục:</span></p>
      </div>
      <div class="col-sm-12">
      	<div style="margin-left: 35px; margin-bottom: 15px;">
          <ul >
            <#-- <#list customer.domain as domain>
            <li>- ${domain.name}</li>
            </#list> -->
            <ul>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-xs-12 col-sm-12">
            <p><i class="fa fa-book" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;<span class="text-bold">THÔNG TIN HỒ SƠ ĐÃ NỘP</span></p>
          </div>
          <ul class="col-sm-12 MB15" id="infoDossierFiled" data-template="dossier_template" data-bind="source: dossierTemplateParts">

          </ul>
          <script type="text/x-kendo-template" id="dossier_template">
            <li class="ML15">- <span data-bind="text:partName" role="option"></span> <br></li>
          </script>
        </div>

        <div class="row MB10">
          <div class="col-xs-12 col-sm-12">
            <p><i class="fa fa-map-marker" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;<span class="text-bold">ĐỊA ĐIỂM HOÀN TẤT THỦ TỤC</span></p>
          </div>
          <div class="col-sm-12 ML15">
            <span class="text-bold">Trụ sở <span data-bind="text:govAgencyName"></span> tại:</span> <br>
            <p class="text-bold">Địa chỉ: </span> <span></p>
          </div>
        </div>

        <div class="row" id="viaPostal" data-bind="value: viaPostal">
          <div class="col-sm-12">

            <p class="text-bold">Sau khi hoàn tất thủ tục <span data-bind="text:govAgencyName"></span> sẽ gửi kết quả qua đường bưu điện cho Ông/Bà qua địa chỉ</p>
            <span class="ML15" data-bind="text: postalAddress"></span>

          </div>
        </div>

        <div class="row">
          <div class="col-xs-12 col-sm-12">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1916.5583539623844!2d105.82030661842978!3d21.032872397155213!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ab0ee3daf717%3A0x6e8ee2ec81eed48e!2zQ-G7pWMgQuG6o24gUXV54buBbiBUw6FjIEdp4bqj!5e0!3m2!1sen!2s!4v1502935967495" width="100%" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
          </div>
        </div>

        <div class="row">
          <div class="col-xs-12 col-sm-12">
            <button class="btn btn-active" id="btn-completed-submiting-dossier">Hoàn tất việc nộp hồ sơ trực tuyến</button>
          </div>
        </div>
      </div>
    </div>

    <script type="text/javascript">

      var printDetailDossier = function(id){
        if(id){
          $.ajax({
            url : "${api.server}/dossiers/"+id,
            dataType : "json",
            type : "GET",
            headers : {"groupId": ${groupId}},
            success : function(result){
              console.log("success");
              var dossierTemplateParts = funGetDossierTemplate(${dossierTemplateId});

              var viewModel = kendo.observable({
                serviceName : result.serviceName,
                govAgencyName : result.govAgencyName,
                postalAddress : result.postalAddress,
                viaPostal : function(e){
                  console.log("viaPostal");
                  console.log(result.viaPostal);
                  if(result.viaPostal){
                    $("#viaPostal").remove();
                  }
                },
                dossierTemplateParts : dossierTemplateParts

              });

              kendo.bind($("#completedDossierForm"), viewModel);
            },
            error : function(result){

            }

          });
        }
      }

      printDetailDossier(${dossierId});

      $("#btn-completed-submiting-dossier").click(function(){

        manageDossier.navigate("/New");

        $("#mainType1").show();
        $("#mainType2").hide();       

      });

      var funGetDossierTemplate = function(dossierTemplateId){
        var dossierTemplates =  new Array();
        if(dossierTemplateId){
          $.ajax({
            url : "${api.server}/dossiertemplates/${dossierTemplateId}",
            dataType : "json",
            type : "GET",
            headers: {"groupId": ${groupId}},
            async : false,
            success : function(result){
              if(result){
                dossierTemplates = result.dossierParts;
                dossierTemplates = $.grep( dossierTemplates, function( item, i ) {
                  return item.partType === 1;
                });
              }
            },
            error : function(result){

            }
          });
        }
        return dossierTemplates;
      }

    </script>