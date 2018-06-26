<#include "init.ftl">
<!-- <!DOCTYPE html> -->
<!-- <html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Voting</title>
    <link href='https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons' rel="stylesheet" type="text/css">
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue"></script>
    <link href="https://unpkg.com/vuetify/dist/vuetify.min.css" rel="stylesheet">
    <script src="https://unpkg.com/vuetify/dist/vuetify.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
    
  </head> -->
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue"></script>
    <link href="https://unpkg.com/vuetify/dist/vuetify.min.css" rel="stylesheet">
    <script src="https://unpkg.com/vuetify/dist/vuetify.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>

    <div id="app">
      <v-app>
        <voting
        class_name="question_opencps"
        :class_pk="class_pk"
        :group_id="groupId"
        expanded="true"
        voting-title="Phiếu khảo sát"
        :voting_api="voiting_api"
        :user_id="user_id"
        ></voting>
      </v-app>
    </div>
    <!-- template voting -->
    <script type="text/x-template" id="voting">
      
      <div class="opencps-component-voting layout wrap">
        <div class="flex xs3" style="padding-right: 20px;">
          <v-flex>
            <v-select
            :items="dictItems"
            v-model="govObj"
            item-text="itemName"
            item-value="itemCode"
            label="Lựa chọn tổ chức"
            autocomplete
            chips
            @change="onchangeGovObj($event)"
            >
              <template slot="selection" slot-scope="data">
                <v-chip
                  close
                  deletable-chips
                  @input="onRemove()"
                >
                  <strong>{{ data.item.itemName }}</strong>&nbsp;
                </v-chip>
              </template>
            </v-select>
          
          </v-flex>
        </div>
        <div class="flex xs9">
          <v-expansion-panel expand class="expansion-blue">
            <v-expansion-panel-content v-bind:value="expanded" @input="($event) => ( expanded = $event )">
              <div slot="header" :class="expanded">
                <div class="custome-panel-heading-with-icon">
                  <div >
                    <span class="votingTitle">{{ votingTitle }}</span>
                  </div> 
                  
                  <v-icon style="cursor: pointer;" value="1" 
                    v-if="user_id == 20164" 
                    @click.stop="newVotingAdd"
                  
                  >add_circle
                  </v-icon>
                  
                </div>
              </div>
      
              <v-list class="opencps--voting" v-if="votingItems.length > 0">
                <template v-for="(item, index) in votingItems">
                  <v-list-tile :key="index">
                    <v-list-tile-content>
                      <v-list-tile-sub-title class="grey--text text--darken-4 px-4">
                        {{index + 1}}. {{ item.subject }}
                      </v-list-tile-sub-title>
                      <v-list-tile-sub-title class="text-right">
                         <small class="text-gray"> Tạo vào {{ item.createDate | datetimelog}}</small>
                        <v-btn v-if="user_id == item.userId" 
                          v-on:click.native="votingEdit(item, index)" 
                          flat icon class="py-0 px-0 mx-0 my-0 btn--edit--icon"
                        >
                          <v-icon>edit</v-icon>
                        </v-btn>
                        <v-btn v-if="user_id == item.userId"
                          v-on:click.native="votingRemove(item, index)" 
                          flat icon class="py-0 px-0 mx-0 my-0 btn--clear--icon"
                        >
                          <v-icon color="red darken-3">clear</v-icon>
                        </v-btn>
                      </v-list-tile-sub-title>
                      
                      <ul class="voting_results">
                        <v-radio-group v-model="item.selected">
                        <li v-for="(itemChilds, indexResults) in item.choices" v-bind:key="indexResults">
                          <div status="success" class="voting-results-wrap">
                            <div v-if="item.answersCount > 0 && item.answers[indexResults]" class="uploader-file-progress" :style="'background: white;transform: translateX(' + (item.answers[indexResults] / item.answersCount)*100 + '%);'"></div>
                            <div v-else class="uploader-file-progress" :style="'background: white;transform: translateX(0%);'"></div>
                            <div class="uploader-file-info">
                              <div class="voting-processing">
                                <v-radio 
                                  :label="itemChilds"
                                  color="primary"
                                  :value="indexResults + 1"></v-radio>
                              </div>
                              <div class="voting_result_summer">{{item.answers[indexResults]}} / {{item.answersCount}}</div>
                            </div>
                          </div>
                        </li>
                        </v-radio-group>
                        <div class="pl-3 pr-3" v-if="item.commentable"> 
                          <voting-result
                            :my_comment.sync="item.comment"
                            :voting_id="item.votingId"
                            :group_id="group_id"
                            :user_id="user_id"
                            :voting_api="voting_api"
                            @sync-vl="(val) => ( item.comment = val)"
                          >
                        </voting-result>
                        </div>
                      </ul>
                    </v-list-tile-content>
                  </v-list-tile>
                  <v-divider v-if="index + 1 < votingItems.length" :key="index"></v-divider>
                </template>
                <v-btn flat block color="primary" @click.native="doVottingResultSubmit"
                  :loading="votingDialog_hidden_loading" 
                  :disabled="votingDialog_hidden_loading"
                >
                  Gửi ý kiến &nbsp;&nbsp;
                  <v-icon class="primary--text">send</v-icon>
                </v-btn>
              </v-list>
              
              <v-card v-else>
                <v-card-text>
                  <div class="text-right text-gray pr-2">
                  <i>Chưa có {{votingTitle}}</i>
                  </div>
                </v-card-text>
              </v-card>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </div>
        
        <v-dialog v-model="votingDialog" persistent max-width="900" transition="fade-transition">
          <v-card :class="{'opencps-shared-root editable-wrap editable-wrap-before-select': ! (user_id == voteUserId || votingId === 0) }">
              <v-card-title class="px-0 py-0" style="background-color: rgb(214, 233, 247);min-height: 43px;position: fixed;max-width: 900px;width: 100%;z-index: 9999;">
                <span class="pl-3">Thêm nội dung lấy ý kiến</span>
                <v-spacer></v-spacer>
                
                <div class="menu" style="display: inline-block;">
                  <div class="menu__activator">
                    <v-btn icon slot="activator" @click.native="votingDialog = false">
                      <v-icon>close</v-icon>
                    </v-btn>
                  </div>
                </div>
              </v-card-title>

              <v-card-text class="voting__add" style="padding-top: 65px;">
                <v-form v-model="validFormVoting" ref="form" lazy-validation name="fullFormVoting">
                  <v-layout wrap>
                    <v-flex xs12 class="mb-2">
                      <v-text-field
                        label="Nội dung xin ý kiến"
                        placeholder="Nhập nội dung xin ý kiến"
                        :rules="[v => !!v || 'Nội dung xin ý kiến bắt buộc nhập']"
                        required
                        multi-line
                        autofocus
                        rows="3"
                        name="subject"
                        v-model="subject"
                      ></v-text-field>
                    </v-flex>
                    <v-flex xs12 :key="index" v-for="(item, index) in choices ">
                      <v-text-field
                        label="Câu trả lời"
                        placeholder="nhập câu trả lời..."
                        :name="'choice'+index"
                        :rules="[v => !!v || 'Câu trả lời bắt buộc nhập']"
                        autofocus
                        required
                        :value="item"
                        append-icon="clear"
                        :append-icon-cb="() => ( choiceRemove( item, index ))"
                      >
                      </v-text-field>
                    </v-flex>
                    <v-flex xs12 class="custome-panel-heading-with-icon">
                      <div class="votting__no_padding">
                        <v-checkbox label="Sử dụng câu trả lời khác" v-model="commentable" light></v-checkbox>
                      </div>
                      <v-btn
                        @click.native="choiceAdd"
                        icon
                        >
                        <v-icon class="primary--text" >add_circle</v-icon>
                      </v-btn>
                      
                    </v-flex>
                  </v-layout>
                </v-form>
              </v-card-text>
    
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn flat color="primary" @click.native="votingDialog = false">
                  <v-icon left>arrow_back</v-icon>Quay lại 
                </v-btn>
                <v-btn flat color="primary" @click.native="doVottingSubmit" 
                  v-if="(user_id == voteUserId || votingId === 0) && votingId === 0"
                  :loading="votingDialog_hidden_loading" 
                  :disabled="votingDialog_hidden_loading"
                >
                  <v-icon left>save</v-icon>Ghi lại 
                </v-btn>
                <v-btn flat color="primary" @click.native="doVottingEditSubmit" 
                  v-if="( user_id == voteUserId || votingId === 0) && votingId > 0"
                  :loading="votingDialog_hidden_loading" 
                  :disabled="votingDialog_hidden_loading"
                >
                  <v-icon left>save</v-icon>Ghi lại 
                </v-btn>
              </v-card-actions>
    
            </v-card>
        </v-dialog>
    
        <v-snackbar :timeout="3000" :top="true" :bottom="false" 
          :right="true" :left="false" :multi-line="true" 
          :vertical="false" v-model="snackbarSucc" class="snackbar-success" > 
          <v-icon flat color="white">check_circle</v-icon> 
          {{alertMess}}
          <v-btn flat fab mini color="white" @click.native="snackbarSucc = false">Tắt</v-btn> 
        </v-snackbar>
    
        <v-snackbar :timeout="3000" :top="true" :bottom="false" 
          :right="true" :left="false" :multi-line="true" 
          :vertical="false" v-model="snackbarErr" class="snackbar-error" > 
          <v-icon flat color="white">check_circle</v-icon> 
          {{alertMess}}
          <v-btn flat fab mini color="white" @click.native="snackbarErr = false">Tắt</v-btn> 
        </v-snackbar>
    
      </div>
    </script>
    <!-- template voting-result -->
    <script type="text/x-template" id="voting-result">
      <div :class="'voting-result'+voting_id">
        <v-layout wrap>
          <v-flex xs12>
            <v-text-field
              v-model="my_comment"
              @input="onChangeMyComment"
              placeholder="Nhập đáp án khác ..."
              textarea
              rows="2"
            >
            </v-text-field>
          </v-flex>
          <v-flex xs12 v-for="(item, index) in votingResultItems" :key="index">
            <div v-if="item.comment != ''">
              <v-divider v-if="index>0" class="mb-2 mt-2" :inset="true" ></v-divider>
              <div class="custome-panel-heading-with-icon">
                <div>
                  <v-icon class="pr-2" x-large>perm_identity</v-icon>
                  {{item.fullName}}
                </div>
                <i class='text-gray'> {{ getMoment(item.createDate).format('HH:mm DD/MM/YYYY') }}</i>
              </div>
              <div class="pl-5">
                {{item.comment}}
              </div>
            </div>
          </v-flex>
        </v-layout>
      </div>
    </script>
    <!--  -->
    <script type="text/javascript">
      document.addEventListener('DOMContentLoaded', function (event) {
        var voting_result = {
          template: '#voting-result',
          props: {},
          props: {
            'voting_id': 0,
            'group_id': ${groupId},
            'user_id': ${userId},
            'my_comment': {
              default : ""
            },
            'voting_api': '/o/rest/v2/pk5/votings'
          },
          data: function() {
            return {
              votingResultItems: []
            };
          },
          methods: {
            getMoment (date) {
              return moment(date)
            },
            onChangeMyComment: function() {
              var vm = this
              vm.$emit('sync-vl', vm.my_comment)
            } 
          },
          create() {
            let vm = this
            const config = {
                headers: {
                    'groupId': vm.group_id
                }
            }
          },
          mounted(){
            let vm = this
            const config = {
              headers: {
                'groupId': vm.group_id,
                'Accept' : 'application/json',
                'Content-Type': "application/x-www-form-urlencoded"
              }
            }
            // axios.get(vm.voting_api + '/' + vm.voting_id + '/results' + '?sort=createDate', config)
            axios.get(vm.voting_api + '/' + vm.voting_id + '/results', config)
            .then(function (response) {
              var serializable = response.data
              if (serializable.hasOwnProperty('data')) {
                vm.votingResultItems = serializable.data
                serializable.data.filter(function(item){
                  if (item.userId == vm.user_id){
                    vm.my_comment = item.comment
                  }
                  return true
                })
              }
            })
            .catch(function (error) {
              console.log(error)
            })
          }
        }
        // -------------------
        var voting = {
          template: '#voting',
          components: {
            'voting-result': voting_result
          },
          props: {
            id: null,
            class_pk: null,
            class_name: null,
            group_id: null,
            profilePictureURL: 'https://viima-app.s3.amazonaws.com/media/user_profiles/user-icon.png',
            voting_api: '',
            votingTitle: 'Lấy ý kiến thống nhất',
            // permission: 'read',
            user_id: 0
          },
          watch: {
            class_pk(val){
              var vm = this
              // vm.hasPermission = 'owner,leader,hoster,manager'.indexOf(vm.permission ||'none' )>=0?true:false
              // vm.isWriter = 'writer'.indexOf(vm.permission ||'none' )>=0?true:false
              const config = {
                headers: {
                  'groupId': vm.group_id,
                  'Accept': 'application/json',
                }
              }
              
              // axios.get(vm.voting_api + '/' + vm.class_name + '/' + vm.class_pk + '?sort=createDate', config)
              axios.get(vm.voting_api + '/' + vm.class_name + '/' + vm.class_pk, config)
              .then(function (response) {
                var serializable = response.data
                if (serializable.hasOwnProperty('data')) {
                  vm.votingItems = serializable.data
                  vm.openExpand(true)
                } else {
                  vm.votingItems = []
                  vm.openExpand(false)
                }
              })
              .catch(function (error) {
                console.log(error)
              })
            }
          },
          created () {
            var vm = this
            // vm.hasPermission = 'owner,leader,hoster,manager'.indexOf(vm.permission ||'none' )>=0?true:false
            // vm.isWriter = 'writer'.indexOf(vm.permission ||'none' )>=0?true:false
            const config = {
              headers: {
                'groupId': vm.group_id,
              }
            }
            vm.votingItems = []
            vm.$nextTick(function () {
              // axios.get(vm.voting_api + '/' + vm.class_name + '/' + vm.class_pk + '?sort=createDate', config)
              axios.get(vm.voting_api + '/' + vm.class_name + '/' + vm.class_pk, config)
                .then(function (response) {
                  var serializable = response.data
                  vm.serviceinfoFilterItems();
                  if (serializable.hasOwnProperty('data')) {
                   /* console.log('test');*/
                    vm.votingItems = serializable.data
                    vm.openExpand(true)
                  } else {
                    vm.votingItems = []
                    vm.openExpand(false)
                  }
                })
                .catch(function (error) {
                  console.log(error)
                })
            })
            
          },
          data () {
            return {
              subject: '',
              choices: ['',''],
              expanded: false,
              validFormVoting: true,
              commentable: false,
              votingItems: [],
              votingSelected: '',
              votingPer: 0,
              votingDialog: false,
              votingDialog_hidden_loading: false,
              votingId: 0,
              voteUserId: 0,
              newChoiceItem: '',
              voting_api: this.voting_api,
              isLoading: false,
              snackbarSucc: false,
              snackbarErr: false,
              alertMess: '',
              hasPermission: 'owner',
              isWriter: false,
              dictItems: [],
              govObj: null
            }
          },
          methods: {
            _initVotingList: function () {
              var vm = this
              vm.isLoading = true
              const config = {
                headers: {
                  'groupId': vm.group_id,
                }
              }
              vm.votingItems = []
              // axios.get(vm.voting_api + '/' + vm.class_name + '/' + vm.class_pk + '?sort=createDate', config)
              axios.get(vm.voting_api + '/' + vm.class_name + '/' + vm.class_pk, config)
                .then(function (response) {
                  var serializable = response.data
                  if (serializable.hasOwnProperty('data')) {
                    vm.votingItems = serializable.data
                    vm.openExpand(true)
                  } else {
                    vm.votingItems = []
                    vm.openExpand(false)
                  }
                  setTimeout(() => {
                    vm.isLoading = false
                  }, 500);
                })
                .catch(function (error) {
                  console.log(error)
                  vm.isLoading = false
                })
            },
            doVottingResultSubmit: function () {
              var vm = this
              vm.votingDialog_hidden_loading = true
              const config = {
                headers: {
                  'groupId': vm.group_id
                }
              }

              for (var key in vm.votingItems) {
                var params = new URLSearchParams()
                if ( vm.votingItems[key].comment ) {
                  params.append('comment', vm.votingItems[key].comment)
                }
                
                params.append('selected', vm.votingItems[key].selected)
                params.append('email', '')
                params.append('fullName', themeDisplay.getUserName())
                axios.post(vm.voting_api +'/'+vm.votingItems[key].votingId+'/results',
                  params,
                  config
                )
                .then(function (response) {
                  vm.votingDialog_hidden_loading = false
                  vm.alertMess = 'Cập nhật thành công!'
                  vm.snackbarSucc = true
                  vm._initVotingList()
                  
                })
                .catch(function (error) {
                  console.log(error)
                  vm.votingDialog_hidden_loading = false
                })
              }
            },
            doVottingSubmit: function () {
              var vm = this
              vm.votingDialog_hidden_loading = true
              const config = {
                headers: {
                  'groupId': vm.group_id
                }
              }

              if (!vm.validFormVoting) {
                vm.votingDialog_hidden_loading = false
                vm.alertMess = 'Nhập đầy đủ các trường dữ liệu bắt buộc'
                vm.snackbarErr = true
                return
              }

              var x = document.forms['fullFormVoting']
              var choicesItems = ''
              for(var i=0; i< x.length; i++){
                var field = x[i];
                
                if(field.name.indexOf('choice'||'none') >=0) {
                  if(field.value == '') {
                    vm.votingDialog_hidden_loading = false
                    vm.alertMess = 'Nhập đầy đủ các trường dữ liệu bắt buộc'
                    vm.snackbarErr = true
                    return
                  } else {
                    choicesItems += field.value + '\r\n'
                  }
                }
              }
              var params = new URLSearchParams()
              params.append('className', vm.class_name)
              params.append('classPK', vm.class_pk)
              params.append('subject', vm.subject)
              params.append('choices', choicesItems)
              params.append('commentable', vm.commentable)

              axios.post(vm.voting_api,
                params,
                config
              )
              .then(function (response) {
                /**vm._initVotingList()*/
                vm.votingItems.push(response.data)
                vm.openExpand(true)
                vm.votingDialog_hidden_loading = false
                vm.votingDialog = false
              })
              .catch(function (error) {
                console.log(error)
                vm.votingDialog_hidden_loading = false
              })
            },
            doVottingEditSubmit: function () {
              var vm = this
              vm.votingDialog_hidden_loading = true
              const config = {
                headers: {
                  'groupId': vm.group_id
                }
              }
              if (!vm.validFormVoting) {
                vm.votingDialog_hidden_loading = false
                return
              }
              var x = document.forms['fullFormVoting']
              var choicesItems = ''
              for(var i=0; i< x.length; i++){
                var field = x[i];
                
                if(field.name.indexOf('choice'||'none') >=0) {
                  choicesItems += field.value + '\r\n'
                }
              }
              var params = new URLSearchParams()
              params.append('className', vm.class_name)
              params.append('classPK', vm.class_pk)
              params.append('subject', vm.subject)
              params.append('choices', choicesItems)
              params.append('commentable', vm.commentable)

              axios.put(vm.voting_api + '/' + vm.votingId,
                params,
                config
              )
              .then(function (response) {
                vm.alertMess = 'Cập nhật thành công!'
                vm.snackbarSucc = true
                
                vm._initVotingList()
                vm.votingDialog_hidden_loading = false
                vm.votingDialog = false
              })
              .catch(function (error) {
                console.log(error)
                vm.votingDialog_hidden_loading = false
              })
            },
            newVotingAdd: function(){
              
              this.votingDialog = true
              this.votingId = 0
              this.subject = ''
              this.choices = [this.newChoiceItem, this.newChoiceItem]
              this.commentable = false
              this.voteUserId = 0
            },
            votingEdit: function (item, index) {
              
              this.votingDialog = true
              this.votingId = item.votingId
              this.subject = item.subject
              this.choices = item.choices
              this.commentable = item.commentable
              this.voteUserId = item.userId
            },
            votingRemove: function (item, index) {
              var vm = this
              vm.$dialog.confirm('Bạn có muốn xóa nội dung này?', {
                html: true,
                loader: true,
                okText: 'Xác nhận',
                cancelText: 'Quay lại',
                animation: 'fade'
              })
              .then((dialog) => {
                const config = {
                  headers: {
                    'groupId': vm.group_id
                  }
                }
                axios.delete(vm.voting_api + '/' + item.votingId, config).then(function (response) {
                  vm.votingItems.splice(index, 1)
                  if(vm.votingItems.length == 0){
                    vm.openExpand(false)
                  }
                })
                .catch(function (error) {
                  console.log(error)
                })
                dialog.close()
              })
              .catch((e) => {
                console.log(e)
              })
            },
            choiceAdd: function () {
              let vm = this
              vm.choices.push(vm.newchoiceItem)
            },
            choiceRemove: function (item, index) {
              
              if ( this.choices.length >2 ) {
                this.choices.splice(index, 1)
              }
            },
            openExpand (wanted) {
              if (wanted){
                if(!this.expanded){
                  $('.opencps-component-voting .expansion-panel__header').click()
                }
              } else {
                if(this.expanded){
                  $('.opencps-component-voting .expansion-panel__header').click()
                }
              }
            },
            serviceinfoFilterItems: function(){
              console.log('test111111');
              var vm = this;
               const config = {
                headers: {
                  'groupId': vm.group_id,
                  'Accept' : "application/json"
                }
              }
              var url = '/o/rest/v2/dictcollections/GOVERNMENT_AGENCY/dictitems?sort=sibling';

              vm.dictItems = []
              vm.isLoading = true
              axios.get(url, config).then(function (response) {
                var serializable = response.data;

                vm.dictItems = serializable.data;

              })
              .catch(function (error) {
                console.log(error);
                
              });
              setTimeout(() => {
                vm.isLoading = false
              }, 500);
            },
            onchangeGovObj: function(data){
              var vm = this
              /*console.log("ccccccccccc = " + vm.govObj);
              console.log("aaaaaaaaaaaa = " + data);*/
              vm.class_pk = data;
              /*console.log("vm.class_pk = " + vm.class_pk);
              console.log("ttttttttttt = " + JSON.stringify(vm.votingItems));*/
            },
            onRemove: function(){
              var vm = this
              vm.class_pk = 0;
              vm.govObj = [];
            }
          }
        }
        // ----------------------
        new Vue({
          el: '#app',
          components: {
            'voting': voting,
          },
          data: {
            class_pk: null,
            groupId: themeDisplay.getScopeGroupId(),
            detailModelSelected: [],
            user_id: themeDisplay.getUserId(),
            voiting_api: "/o/rest/v2/pk5/votings",
            class_pk: "0"
          }
        })
      })
    </script>
    <!--  -->
    <style>
      .opencps--voting .list__tile {
        height: auto;
      }
      .opencps--voting .voting_results {
        width: 100%;
      }
      .opencps--voting .voting_results .input-group__details{
        min-height: 2px;
      }
      .opencps--voting .voting_results li {
        position: relative;
        margin-bottom: 2px;
        display: block;
      }
      .uploader-file-progress {
        position: absolute;
        width: 100%;
        height: 100%;
        background: #e2eeff;
        -webkit-transform: translateX(-100%);
        transform: translateX(-100%);
      }
      .opencps--voting hr.divider{
        margin-bottom: 15px;
      }
      .opencps--voting .voting_result_summer {
        width: 20%;
        text-align: right;
        padding-right: 15px;
        position: absolute;
        right: 0;
        top: 8px;
      }
      .opencps--voting .voting-results-wrap {
        background: rgb(226, 238, 255);
      }
      .opencps--voting .voting-processing {
        width: 80%;
      }
      .opencps--voting .uploader-file-info {
        padding: 8px;
        padding-left: 20px;
      }
      .voting__add .input-group--text-field.input-group--textarea:not(.input-group--full-width) label{
        top: 0px !important;
        left: 0px !important;
        transform: translate(0, -8px) scale(1) !important;
      }
      .votting__no_padding label {
        padding-left: 0px;
      }
      .opencps--voting .btn .icon{
        font-size: 17px;
      }
      .opencps--voting .btn--block .btn__content {
        justify-content: left !important;
      }
      .opencps--voting > li .list__tile{
        padding: 0;
      }
      .opencps--voting .voting_results > .radio-group{
        padding: 0;
      }
      .custome-panel-heading-with-icon {
        display: -webkit-flex; /* Safari */
        -webkit-align-items: center; /* Safari 7.0+ */
        display: flex;
        align-items: center;
      }
      .custome-panel-heading-with-icon div {
        -webkit-flex: 1; /* Safari 6.1+ */
        flex: 1;
      }
      .votingTitle {
        font-size: 14px;
        font-weight: bold;
        text-transform: uppercase;
      }
    </style>
<!-- </html> -->