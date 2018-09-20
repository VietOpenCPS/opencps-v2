<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="row panel">
    <div class="row-header align-middle">
        <div class="background-triangle-big">CHI TIẾT THỦ TỤC HÀNH CHÍNH</div>
        <div class="pull-right group-icons align-middle-lg">
            <a href="javascript:;" onclick="fnBack();">
                <i class="fa fa-reply" aria-hidden="true"></i>
                Quay lại
            </a>
        </div>
    </div>
    <div class="panel-body" id="dataDetailServiceInfo">
        <div class="row MB15">
            <div class="col-sm-12">
                <span class="MR10" id="maxLevel" data-bind="text:maxLevel"> </span> <span data-bind="text:serviceName" class="text-bold" id="serviceName"> </span>
            </div>
        </div>
        <div id="serviceinfo_detail_tabstrip" class="row nav-tabs-wrapper">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a data-toggle="tab" href="#ttc">
                        Thông tin chung
                    </a>
                </li>
                <li>
                    <a data-toggle="tab" href="#ttth">
                        Trình tự thực hiện
                    </a>
                </li>
                <li>
                    <a data-toggle="tab" href="#tphs">
                        Thành phần hồ sơ
                    </a>
                </li>
                <li>
                    <a data-toggle="tab" href="#ycdk">
                        Yêu cầu điều kiện
                    </a>
                </li>
            </ul>
            <div class="tab-content">
                <div id="ttc" class="tab-pane fade in active">
                    <div class="row-parts-content">
                        <div class="row">
                            <div class="col-sm-2">
                                <label>Cơ quan quản lý</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="html:administrationName" id="administrationName"></p>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="col-sm-2">
                                <label class="MB10">Lĩnh vực</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="html:domainName" id="domainName"></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label class="MB10">Cách thực hiện</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="html:methodText" id="methodText"></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label class="MB10">Thời gian giải quyết</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="html:durationText" id="durationText"></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label class="MB10">Đối tượng</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="html:applicantText" id="applicantText"></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label class="MB10">Kết quả giải quyết</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="html:resultText" id="resultText"></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label class="MB10">Lệ phí</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="html:feeText" id="feeText"></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-2">
                                <label class="MB10">Căn cứ pháp lý</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="html:regularText" id="regularText"></p>
                            </div>
                        </div>
                        <input type="hidden" name="serviceinfoId" id="serviceinfoId" data-bind="value : serviceinfoId">
                        <div class="col-sm-12 PL0 MT10 submitDossier">

                        </div>
                    </div>
                </div>
                <div id="ttth" class="tab-pane fade">
                   <p class="" data-bind="html:processText" id="processText"></p>

                   <div class="col-sm-12 PL0 MT10 submitDossier">

                   </div>
               </div>
               <div id="tphs" class="tab-pane fade">
                   <p class="MB15 MT10" data-bind="html:dossierText" id="dossierText"></p>

                   <label>File biểu mẫu</label>
                   <ul class="ML10" id ="service_info_filetemplate" data-template="service_info_filetemplate_template" data-bind="source: fileTemplates">

                   </ul>
                   <script type="text/x-kendo-template" id="service_info_filetemplate_template">
                       <li class="clearfix item-serviceinfo-filetemplate eq-height-lg" data-bind="attr: {data-pk : fileTemplateNo}" role="option" aria-selected="true">
                        <a data-bind="attr : { href: fileTemplateDownLoad}"><i class="fa fa-download" aria-hidden="true"></i> <span data-bind="html: templateName"></span></a>
                    </li>
                </script>

                <div class="col-sm-12 PL0 MT10 submitDossier">

                </div>
            </div>
            <div id="ycdk" class="tab-pane fade">
                <p class="MT10 MB10" data-bind="html:conditionText" id="conditionText"></p>
                <div class="col-sm-12 PL0 MT10 submitDossier">

                </div>
            </div>
        </div>
    </div>
</div>

<#-- <div id="submitDossierModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Bạn hãy chọn cơ quan thực hiện</h4>
            </div>
            <div class="modal-body">
                <form id="fmServiceinfo">
                    <div class="row">
                        <div class="col-sm-12 text-center">
                            <input class="form-control" name="slAdministration" id="slAdministration">
                        </div>
                        <div class="col-sm-12 text-center MT15">
                            <button class="btn btn-active" id="btn-submit-serviceinfo" type="button"> Đồng ý</button><button class="btn ML10" data-dismiss="modal"> Hủy bỏ</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div> -->
</div>

<script type="text/javascript">
    var setValue = function(combobox,value) {
      combobox.value(value);
    }
    var fnBack = function() {
        var tabstrip = $("#service_info_tabstrip").data("kendoTabStrip");
      var index=tabstrip.select().index();
      var content=tabstrip.contentElement(index);
      var id = $(content).find('li.active').attr("dataPk");
      console.log(id);

      $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){
        if(index == 2){
          $("#service_info_list_view").getKendoListView().dataSource.read({
            "level": id
          });
          var levelCombobox =  $("#levelSearch").data("kendoComboBox");
          setValue(levelCombobox,id);
          levelCombobox.trigger("change");
          levelCombobox._isSelect = false;
        }else if(index == 1){
         $("#service_info_list_view").getKendoListView().dataSource.read({
          "domain": id
        });
         var domainCombobox =  $("#domainCodeSearch").data("kendoComboBox");
         setValue(domainCombobox,id);
         domainCombobox.trigger("change");
         domainCombobox._isSelect = false;
       }else{
        $("#service_info_list_view").getKendoListView().dataSource.read({
          "administration": id
        });
        var administrationCombobox =  $("#administrationCodeSearch").data("kendoComboBox");
        setValue(administrationCombobox,id);
        administrationCombobox.trigger("change");
        administrationCombobox._isSelect = false;
      }

    });
    }

    var pullDataDetail= function(id){
        console.log(id);
        $.ajax({
            url : "${api.server}/serviceinfos/"+id,
            dataType : "json",
            type : "GET",
            success : function(result){
                var viewModel = kendo.observable({
                    maxLevel: function(){
                        if(result.maxLevel == 1){
                            $("#maxLevel").addClass("label label-dvc-lv1");
                        }else if(result.maxLevel == 2){
                            $("#maxLevel").addClass("label label-dvc-lv2");
                        }else if(result.maxLevel == 3){
                            $("#maxLevel").addClass("label label-dvc-lv3");
                        }else {
                            $("#maxLevel").addClass("label label-dvc-lv4");
                        }
                        if((typeof result.serviceConfigs !== 'undefined')){
                            var submitDsier = '<div class="dropdown"><button class="btn btn-active btn-small  dropdown-toggle" type="button" data-toggle="dropdown">Nộp hồ sơ <span class="caret"></span></button><ul class="dropdown-menu">';

                            if (result.serviceConfigs[1]) {
                                console.log("ccc3333");
                                for(var i=0; i<result.serviceConfigs.length;i++){
                                    var item = result.serviceConfigs[i];
                                    if(item.serviceLevel>=3){
                                        submitDsier += '<li><a href="/web${(Request.layoutfriendurl)!}/dich-vu-cong#/add-dvc/' + item.serviceConfigId + '">'+item.govAgencyName+'</a></li>';
                                    }else{
                                        submitDsier += '<li><a href="javascript:;" class="showInstruction" serviceInstruction='+item.serviceInstruction+'>'+item.govAgencyName+'</a></li>';
                                    }
                                }
                            }else{
                                if(result.serviceConfigs.serviceLevel>=3){
                                    submitDsier = '<div class="dropdown"><button class="btn btn-active btn-small  dropdown-toggle" type="button" data-toggle="dropdown">Nộp hồ sơ <span class="caret"></span></button><ul class="dropdown-menu">';
                                    // submitDsier += '<li><a href='+result.serviceConfigs.serviceUrl+'>'+result.serviceConfigs.govAgencyName+'</a></li>';
                                     submitDsier += '<li><a href="/web${(Request.layoutfriendurl)!}/dich-vu-cong#/add-dvc/' + result.serviceConfigs.serviceConfigId + '">'+result.serviceConfigs.govAgencyName+'</a></li>';
                                }else{
                                    submitDsier = '<div class="dropdown"><button class="btn btn-active btn-small dropdown-toggle" type="button" data-toggle="dropdown">Xem hướng dẫn<span class="caret"></span></button><ul class="dropdown-menu">';
                                    submitDsier += '<li><a href="javascript:;" class="showInstruction" serviceInstruction='+result.serviceConfigs.serviceInstruction+'>'+result.serviceConfigs.govAgencyName+'</a></li>';
                                }
                            }
                            submitDsier += '</ul>';
                            submitDsier += '<button class="btn ML5 btn-revert">Quay lại</button>';
                            submitDsier += '</div>'
                            $(".submitDossier").html(submitDsier);
                            
                        }else {
                            $(".submitDossier").html('<button class="btn ML5 btn-revert">Quay lại</button>');
                        }
                        return "Mức độ "+result.maxLevel;
                    },
                    fileTemplateDownLoad : function(e){
                        var serviceInfoId = $("#serviceinfoId").val();
                        return '${api.server}/serviceinfos/'+serviceInfoId+'/filetemplates/'+e.fileTemplateNo
                    },
                    serviceinfoId: result.serviceInfoId,
                    serviceName: result.serviceName,
                    administrationName: result.administrationName,
                    domainName: result.domainName,
                    resultText: result.resultText,
                    feeText: result.feeText,
                    methodText : result.methodText,
                    durationText : result.durationText,
                    dossierText : result.dossierText,
                    processText : result.processText,
                    applicantText : result.applicantText,
                    regularText : result.regularText,
                    conditionText : result.conditionText,
                    fileTemplates : result.fileTemplates
                });

                kendo.bind($("#dataDetailServiceInfo"), viewModel);
            },
            error : function(xhr){

            }
        });
}

    /*var loadAdministration = function(id){
       $("#slAdministration").kendoComboBox({
        placeholder : "Chọn cơ quan thực hiện",
        dataTextField : "govAgencyName",
        dataValueField : "govAgencyCode",
        dataSource : {
            transport : {
                read : {
                    url : "",
                    dataType : "json",
                    type : "GET",
                    beforeSend: function(req) {
                        req.setRequestHeader('groupId', ${groupId});
                    },
                    success : function(result){

                    },
                    error : function(xhr){

                    }
                }
            },
            schema : {
                data : "data",
                total : "total",
                model : {
                    id : "govAgencyCode"
                }
            }
        },
        filter: "contains"
    });
}*/

/*$(function(){
    $("#btn-submit-serviceinfo").click(function(){
        var data = $("#fmServiceinfo").serialize();
        var id = $("#serviceinfoId").val();
        $.ajax({
            url : "",
            dataType : "json",
            type : "PUT",
            contentType : "application/x-www-form-urlencoded",
            success : function(result){

            },
            error : function(xhr){

            }
        });
    });

    
});*/
</script>
