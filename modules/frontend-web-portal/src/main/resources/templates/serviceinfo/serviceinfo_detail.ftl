<#if (Request)??>
	<#include "init.ftl">
</#if>

<div class="row panel">
    <div class="row-header">
        <div class="background-triangle-big">CHI TIÊT THỦ TỤC HÀNH CHÍNH</div>
    </div>

    <div class="panel-body" id="dataDetailServiceInfo">
        <div class="row MB15">
            <div class="col-sm-12">
                <span class="MR10" id="maxLevel" data-bind="text:maxLevel"> </span> <span data-bind="text:serviceName" class="text-bold" id="serviceName"> </span>
            </div>
        </div>
        <div id="serviceinfo_detail_tabstrip">
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
                <div id="ttc" class="tab-pane fade in active MT20">
                    <div class="row-parts-content">
                        <div class="row" style="padding: 5px 0 5px 5px;">
                            <div class="col-sm-2">
                                <label>Cơ quan quản lý</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:administrationName" id="administrationName"></p>
                            </div>
                        </div>
                        <div class="row" style="padding: 5px 0 5px 5px;">
                            <div class="col-sm-2">
                                <label>Lĩnh vực</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:domainName" id="domainName"></p>
                            </div>
                        </div>
                        <div class="row" style="padding: 5px 0 5px 5px;">
                            <div class="col-sm-2">
                                <label>Cách thực hiện</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:methodText" id="methodText"></p>
                            </div>
                        </div>
                        <div class="row" style="padding: 5px 0 5px 5px;">
                            <div class="col-sm-2">
                                <label>Thời gian giải quyết</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:durationText" id="durationText"></p>
                            </div>
                        </div>
                        <div class="row" style="padding: 5px 0 5px 5px;">
                            <div class="col-sm-2">
                                <label>Đối tượng</label>
                            </div>
                            <div class="col-sm-10">
                                <p></p>
                            </div>
                        </div>
                        <div class="row" style="padding: 5px 0 5px 5px;">
                            <div class="col-sm-2">
                                <label>Kết quả giải quyết</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:resultText" id="resultText"></p>
                            </div>
                        </div>
                        <div class="row" style="padding: 5px 0 5px 5px;">
                            <div class="col-sm-2">
                                <label>Lệ phí</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:feeText" id="feeText"></p>
                            </div>
                        </div>
                        <div class="row" style="padding: 5px 0 5px 5px;">
                            <div class="col-sm-2">
                                <label>Căn cứ pháp lý</label>
                            </div>
                            <div class="col-sm-10">
                                <p data-bind="text:dossierText" id="dossierText"></p>
                            </div>
                        </div>
                        <input type="hidden" name="serviceinfoId" id="serviceinfoId" data-bind="value : serviceinfoId">
                        <div class="col-sm-12 PL0 MT15" id="submitDossier">
                            
                        </div>
                    </div>
                </div>
                <div id="ttth" class="tab-pane fade">
                   <p data-bind="text:processText" id="processText"></p>

                   <div class="col-sm-12 PL0 MT15">
                    <button class="btn">Xem hướng dẫn >></button>
                </div>
            </div>
            <div id="tphs" class="tab-pane fade">
                <ul class="mimic-table">
                    <li class="clearfix">
                        <div class="col-sm-1 text-center">
                           <b>STT</b>
                       </div>
                       <div class="col-sm-7 text-center">
                           <b>Tên biểu mẫu</b>
                       </div>
                       <div class="col-sm-2 text-center">
                           <b>Loại file</b>
                       </div>
                       <div class="col-sm-2 text-center">
                           <b>Kích thước</b>
                       </div>
                   </li>
               </ul>
               <ul id ="service_info_filetemplate" class="mimic-table" data-template="service_info_filetemplate_template" data-bind="source: fileTemplates">

               </ul>
               <script type="text/x-kendo-template" id="service_info_filetemplate_template">
                   <li class="clearfix item-serviceinfo-filetemplate eq-height-lg" data-bind="attr: {data-pk : fileTemplateNo}" style="padding: 10px 0 10px 5px;" role="option" aria-selected="true">
                    <div class="col-sm-1 text-center">
                        1
                    </div>
                    <div class="col-sm-7 text-center" data-bind="text: templateName">

                    </div>
                    <div class="col-sm-2 text-center" data-bind="text: fileType">

                    </div>
                    <div class="col-sm-2 text-center" data-bind="text: fileSize">

                    </div>
                </li>
            </script>

        </div>
        <div id="ycdk" class="tab-pane fade">
            <p data-bind="text:conditionText" id="conditionText"></p>
        </div>
    </div>
</div>
</div>

<div id="submitDossierModal" class="modal fade" role="dialog">
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
</div>
</div>

<script type="text/javascript">

    $(document).ready(function(){
     $("#slAdministration").kendoComboBox({
        placeholder : "Chọn cơ quan thực hiện",
        dataTextField : "name",
        dataValueField : "value",
        dataSource : [
        { "name" : "ccc", "value" : 1},
        { "name" : "abc", "value" : 2},
        { "name" : "ddd", "value" : 3},
        { "name" : "eee", "value" : 4}
        ],
            /*dataSource : {
                transport : {
                    read : {
                        url : "",
                        dataType : "json",
                        type : "GET",
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
                        id : "id"
                    }
                }
            },*/
            filter: "contains"
        });
 });

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
                            $("#maxLevel").addClass("label label-info");
                        }else if(result.maxLevel == 2){
                            $("#maxLevel").addClass("label label-warning");
                        }else if(result.maxLevel == 3){
                            $("#maxLevel").addClass("label label-danger");
                        }else {
                            $("#maxLevel").addClass("label label-info");
                        }

                        if(result.maxLevel>=3){
                            $("#submitDossier").html("");
                            $("#submitDossier").append('<button type="button" class="btn btn-active" data-toggle="modal" data-target="#submitDossierModal">Nộp hồ sơ >></button>');
                        }
                        return "Mức "+result.maxLevel;
                    },
                    serviceinfoId: result.serviceinfoId,
                    serviceName: result.serviceName,
                    administrationName: result.administrationName,
                    domainName: result.domainName,
                    resultText: result.resultText,
                    feeText: result.feeText,
                    methodText : result.methodText,
                    durationText : result.durationText,
                    dossierText : result.dossierText,
                    processText : result.processText,
                    conditionText : result.conditionText,
                    fileTemplates : result.fileTemplates
                });

                kendo.bind($("#dataDetailServiceInfo"), viewModel);
            },
            error : function(xhr){

            }
        });
    }

    $(function(){
        $("#btn-submit-serviceinfo").click(function(){
            var data = $("#fmServiceinfo").serialize();
            var id = $("#serviceinfoId").val();
            $.ajax({
                url : "${api.server}/serviceinfos/"+id,
                dataType : "json",
                type : "PUT",
                success : function(result){

                },
                error : function(xhr){

                }
            });
        });
    });
</script>
