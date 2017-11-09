<#if (Request)??>
  <#include "init.ftl">
</#if>

<div class="panel">
  <div class="panel-heading P0">
    <div class="row-header">
      <div class="background-triangle-big">Thông tin chủ hồ sơ</div>
      <span class="panel-title">Thông tin đánh dấu <span class="red">*</span> là thông tin bắt buộc nhập</span>
    </div>
  </div>
  <div class="panel-body">
    <form id="fm">
  	  <div class="row">
        <div class="col-xs-12 col-sm-6">
          <div class="row">
            <div class="col-xs-12 col-sm-7">
              <p>Họ và tên <span class="red">*</span></p>
              <input type="text" name="subjectName" class="form-control" value="" placeholder="Họ và tên" required="required" validationMessage="Trường nhập yêu cầu bắt buộc">
            </div>
            <div class="col-xs-12 col-sm-5 PL0">
              <p>Số điện thoại</p>
              <input type="text" name="telNo" class="form-control" value="" placeholder="Số điện thoại" required="required" validationMessage="Trường nhập yêu cầu bắt buộc">
            </div>
          </div>
        </div>
        <div class="col-xs-12 col-sm-6 PL0">
          <div class="row">
            <div class="col-xs-12 col-sm-9">
              <p>Ngày sinh <span class="red">*</span></p>
              <div class="row">
                <div class="col-xs-12 col-sm-4">
                  <select class="form-control">
                    <option disabled>Ngay</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                  </select>
                </div>
                <div class="col-xs-12 col-sm-4 PL0">
                  <select class="form-control">
                    <option></option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                  </select>
                </div>
                <div class="col-xs-12 col-sm-4 PL0">
                  <select class="form-control">
                    <option></option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                  </select>
                </div>
              </div>
            </div>
            <div class="col-xs-12 col-sm-3 PL0">
              <p>Giới tính</p>
              <select class="form-control">
                <option value="1">Nam</option>
                <option value="2">Nữ</option>
              </select>
            </div>
          </div>
        </div>
  	  </div>
      <div class="row MT15">
        <div class="col-xs-12 col-sm-6">
          <div class="row">
            <div class="col-xs-12 col-sm-7">
              <p>Địa chỉ thư điện tử - Email <span class="red">*</span></p>
              <input type="text" name="email" class="form-control" value="" placeholder="Địa chỉ thư điện tử - Email" required="required" validationMessage="Trường nhập yêu cầu bắt buộc">
            </div>
            <div class="col-xs-12 col-sm-5 PL0">
              <p>CMND - Căn cước công dân <span class="red">*</span></p>
              <input type="text" name="personalId" class="form-control" value="" placeholder="CMND - Căn cước công dân" required="required" validationMessage="Trường nhập yêu cầu bắt buộc">
            </div>
          </div>
        </div>
        <div class="col-xs-12 col-sm-6 PL0">
          <div class="row">
            <div class="col-xs-12 col-sm-12">
              <p>Tải hình ảnh CMND/Thẻ căn cước công dân</p>
              <div class="fileinput fileinput-new input-group" data-provides="fileinput">
                  <div class="form-control" data-trigger="fileinput">
                    <i class="glyphicon glyphicon-file fileinput-exists"></i>
                    <span class="fileinput-filename"></span>
                  </div>
                  <span class="input-group-addon btn btn-default btn-file">
                    <span class="fileinput-new">Tải file</span>
                    <span class="fileinput-exists">Thay đổi</span>
                  <input type="file" name="filePersonalId" title="Tải hình ảnh CMND/Thẻ căn cước công dân">
                </span>
                <a href="#" class="input-group-addon btn btn-default fileinput-exists" data-dismiss="fileinput">Xóa</a>
              </div>
            </div>
          </div>
  	    </div>
      </div>
      <div class="row MT15">
        <div class="col-xs-12 col-sm-6">
          <p>Địa chỉ <span class="red">*</span></p>
          <input type="text" name="address" class="form-control" value="" placeholder="Ghi rõ thôn, số nhà, tên đường - phố" required="required" validationMessage="Trường nhập yêu cầu bắt buộc">
        </div>
        <div class="col-xs-12 col-sm-6 PL0">
          <div class="row">
            <div class="col-xs-12 col-sm-4">
              <p>Tỉnh/Thành phố <span class="red">*</span></p>
              <select class="form-control">
                <option></option>
                <option value="1">1</option>
                <option value="2">2</option>
              </select>
            </div>
            <div class="col-xs-12 col-sm-4 PL0">
              <p>Quận/Huyện <span class="red">*</span></p>
              <select class="form-control">
                <option></option>
                <option value="1">1</option>
                <option value="2">2</option>
              </select>
            </div>
            <div class="col-xs-12 col-sm-4 PL0">
              <p>Xã/Phường <span class="red">*</span></p>
              <select class="form-control">
                <option></option>
                <option value="1">1</option>
                <option value="2">2</option>
              </select>
            </div>
          </div>
        </div>
      </div>
      <div class="row MT15">
        <div class="col-xs-12 col-sm-12">
          <button class="btn btn-active" title="Cập nhật">Cập nhật</button>
        </div>
  		</div>
  	</form>
  </div>
</div>
