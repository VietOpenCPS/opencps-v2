<#if (Request)??>
<#assign aui = PortletJspTagLibs["/META-INF/liferay-aui.tld"] />
<#assign liferay_portlet = PortletJspTagLibs["/META-INF/liferay-portlet-ext.tld"] />
<#assign liferay_security = PortletJspTagLibs["/META-INF/liferay-security.tld"] />
<#assign liferay_theme = PortletJspTagLibs["/META-INF/liferay-theme.tld"] />
<#assign liferay_ui = PortletJspTagLibs["/META-INF/liferay-ui.tld"] />
<#assign liferay_util = PortletJspTagLibs["/META-INF/liferay-util.tld"] />
<#assign portlet = PortletJspTagLibs["/META-INF/liferay-portlet.tld"] />

<@liferay_theme["defineObjects"] />

<@portlet["defineObjects"] />

<#assign ajax = (Request.ajax)!>
<#assign api = (Request.api)!>
<#assign serviceInfoStatuses = (Request.serviceInfoStatuses)!>
<#assign serviceInfo = (Request.SERVICE_INFO)!>
<#assign portletNamespace = (Request.portletNamespace)!>

<#assign groupId = themeDisplay.getScopeGroupId() />
<#assign userId = themeDisplay.getUserId() />
<#assign currentURL = themeDisplay.getURLCurrent() />
<#assign request = themeDisplay.getRequest() />
<#assign portalURL = (themeDisplay.getPortalURL())!>

<#assign url = (Request.url)!>
<#assign api = (Request.api)!>
<#assign constant = (Request.constant)!>


<!--INIT EMPLOYEE-->
<#if Request.employee_workingStatus??>
<#assign employee_workingStatus = Request.employee_workingStatus>
</#if>

<#if Request.employee??>
<#assign employee = Request.employee>
</#if>

<#if Request.employee_accountInfo??>
<#assign employee_accountInfo = Request.employee_accountInfo>
</#if>

<#if Request.employee_jobPos??>
<#assign employee_jobPos = Request.employee_jobPos>
</#if>


<#if Request.params??>
<#assign params = Request.params?eval>
</#if>
<#if Request.constants??>
<#assign constants = Request.constants?eval>
</#if>

<!--INIT  WORKING UNIT-->

<#if Request.activityType_dictItem??>
<#assign activityType_dictItem = Request.activityType_dictItem>
</#if>

<#if Request.dictCollection_dictItem??>
<#assign dictCollection_dictItem = Request.dictCollection_dictItem>
</#if>

<#if Request.documentType_dictItem??>
<#assign documentType_dictItem = Request.documentType_dictItem>
</#if>

<#if Request.dictCollection_dictCollection??>
<#assign dictCollection_dictCollection = Request.dictCollection_dictCollection>
</#if>

<#if Request.dictCollection_dictGroup??>
<#assign dictCollection_dictGroup = Request.dictCollection_dictGroup>
</#if>

<#if Request.label??>
<#assign label = Request.label>
</#if>

<#if Request.location??>
<#assign location = Request.location>
</#if>

<#if Request.notificationTemplate??>
<#assign notificationTemplate = Request.notificationTemplate>
</#if>

<#if Request.workspace??>
<#assign workspace = Request.workspace>
</#if>

<#if Request.officeSite??>
<#assign officeSite = Request.officeSite>
</#if>

<#if Request.workingUnit??>
<#assign workingUnit = Request.workingUnit>
</#if>

<#if Request.contact??>
<#assign contact = Request.contact>
</#if>

<#if Request.jobPos??>
<#assign jobPos = Request.jobPos>
</#if>

<#if Request.param??>
<#assign param = Request.param?eval>
</#if>

<#if Request.constant??>
<#assign constant = Request.constant?eval>
</#if>

<#if Request.workspace_jobposes??>
<#assign workspace_jobposes = Request.workspace_jobposes>
</#if>

<#assign isOmniadmin = Request.isOmniadmin>
</#if>

<!-- popup notification -->
<!-- container -->
<span id="notification" style="display:none;"></span>

<!-- templates -->
<script id="errorTemplate" type="text/x-kendo-template">
  <div class="popup-error-notification">
    <p>#= message #</p>
  </div>
</script>

<script id="successTemplate" type="text/x-kendo-template">
  <div class="popup-success-notification">
    <p>#= message #</p>
  </div>
</script>

<!-- script -->
<script type="text/javascript">
	// notification
  var notification;
  $(document).ready(function() {
    notification = $("#notification").kendoNotification({
      position: {
        pinned: true,
        top: 30,
        right: 30
      },
      autoHideAfter: 3500,
      stacking: "down",
      templates: [
      {
        type: "success",
        template: $("#successTemplate").html()
      },
      {
        type: "error",
        template: $("#errorTemplate").html()
      }
      ]
    }).data("kendoNotification");

    // kendo combo box auto open when click
    $("[data-role=combobox]").each(function() {
      var widget = $(this).getKendoComboBox();
      widget.input.on("focus", function() {
        widget.open();
      });
    });
  });
</script>

<style type="text/css">
    /* Over the pointer-events:none, set the cursor to not-allowed.
    On this way you will have a more user friendly cursor. */
    .disabled-tab {
      cursor: not-allowed;
    }
    /* Clicks are not permitted and change the opacity. */
    li.disabled-tab > a[data-toggle="tab"] {
      pointer-events: none;
      filter: alpha(opacity=65);
      -webkit-box-shadow: none;
      box-shadow: none;
      opacity: .65;
    }
  </style>

  <script type="text/javascript">
    function showMessageToastr(type, message){
      toastr.options = {
        "closeButton": true,
        "debug": false,
        "progressBar": true,
        "positionClass": "toast-top-right",
        "onclick": null,
        "showDuration": "400",
        "hideDuration": "1000",
        "timeOut": "4000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
      };
      toastr[type](message);
    }

    function showMessageByAPICode (code) {

      var message,status;

      switch (code) {

        case 200:
        message = "Yêu cầu của bạn được xử lý thành công!";
        status = "success";
        break;

        case 401:
        message = "Yêu cầu của bạn xử lý thất bại, chưa đăng nhập vào hệ thống!!!";
        status = "error";
        break;

        case 403:
        message = "Yêu cầu của bạn xử lý thất bại, không có quyền thay đổi dữ liệu!!!";
        status = "error";
        break;

        case 404:
        message = "Yêu cầu của bạn xử lý thất bại, không tìm thấy tài nguyên!!!";
        status = "error";
        break;

        case 405:
        message = "Yêu cầu không được phép xử lý!!!";
        status = "error";
        break;

        case 409:
        message = "Yêu cầu của bạn xử lý thất bại, xung đột dữ liệu";
        status = "error";
        break;

        case 500:
        message = "Yêu cầu của bạn xử lý thất bại, lỗi hệ thống";
        status = "error";
        break;

        default:
        message = "Lỗi kết nối!!!";
        status = "error";
        break;
      }

      showMessageToastr(status, message);

    }

    function arrayToTree(arr, idKey, parentKey, childrenKey) {
      var tree = [],
      mappedArr = {},
      arrElem,
      mappedElem;

  //remove level cause kendo panelBar have a function level()
  arr = arr.map(function(item) {
    
    if (item.hasOwnProperty('level')) {
      item.treeLevel = item.level;
      delete item.level;
    }
    return item; 
  });

  idKey = (idKey==null || idKey=="" || idKey === undefined)?"id":idKey;
  parentKey = (parentKey==null || parentKey=="" || parentKey === undefined)?"parentId":parentKey;
  childrenKey = (childrenKey==null || childrenKey=="" || childrenKey === undefined)?"childrens":childrenKey; 

  // First map the nodes of the array to an object -> create a hash table.
  for(var i = 0, len = arr.length; i < len; i++) {
    arrElem = arr[i];
    mappedArr[arrElem[idKey]] = arrElem;
    mappedArr[arrElem[idKey]][childrenKey] = [];
  }


  for (var id in mappedArr) {
    
    if (mappedArr.hasOwnProperty(id)) {
      mappedElem = mappedArr[id];
      // If the element is not at the root level, add it to its parent array of children.
      if (mappedElem[parentKey]) {
        mappedArr[mappedElem[parentKey]][childrenKey].push(mappedElem);
      }
      // If the element is at the root level, add it to first level elements array.
      else {
        tree.push(mappedElem);
      }
    }
  }
  return tree;
}

function getImageBlob(url, imgTarget){
  var xhr = new XMLHttpRequest();
  xhr.open("GET", url);
  xhr.setRequestHeader("groupId", parseInt("${groupId}"));
  xhr.responseType = "arraybuffer";
  xhr.onerror = function() {};
  xhr.onload = function () {
    if (this.readyState == 4 && this.status === 200) {
            //console.log(this.response, typeof this.response);
            //console.log(this.getResponseHeader('content-type'));
            var blob = new Blob([xhr.response], {type: this.getResponseHeader('content-type')});
            var objectUrl = URL.createObjectURL(blob);
            //window.open(objectUrl);
            imgTarget.attr("src", objectUrl);
          }
        };
        xhr.send();
      }

    </script>

    <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;"> <div class="modal-dialog" style="position: relative !important;"> <div class="modal-content" style="padding-bottom: 15px;"> </div> </div> <!-- /.modal-dialog --> </div>


    <div class="modal fade" id="modal-lg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;"> <div class="modal-dialog modal-lg" style="position: relative !important;"> <div class="modal-content" style="padding-bottom: 15px;"> </div> </div> <!-- /.modal-dialog --> </div>

    

