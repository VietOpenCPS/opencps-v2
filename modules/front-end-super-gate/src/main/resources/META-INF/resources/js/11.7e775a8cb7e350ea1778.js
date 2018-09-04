webpackJsonp([11],{

/***/ 224:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(137)(
  /* script */
  __webpack_require__(682),
  /* template */
  __webpack_require__(699),
  /* scopeId */
  null,
  /* cssModules */
  null
)
Component.options.__file = "/Users/binhth/Documents/git_opencps/20-8-2018/frontend-opencps-v2.1/onegate_21_fe/src/components/Comment.vue"
if (Component.esModule && Object.keys(Component.esModule).some(function (key) {return key !== "default" && key !== "__esModule"})) {console.error("named exports are not supported in *.vue files.")}
if (Component.options.functional) {console.error("[vue-loader] Comment.vue: functional components are not supported with templates, they should use render functions.")}

/* hot reload */
if (false) {(function () {
  var hotAPI = require("vue-hot-reload-api")
  hotAPI.install(require("vue"), false)
  if (!hotAPI.compatible) return
  module.hot.accept()
  if (!module.hot.data) {
    hotAPI.createRecord("data-v-c8f36406", Component.options)
  } else {
    hotAPI.reload("data-v-c8f36406", Component.options)
  }
})()}

module.exports = Component.exports


/***/ }),

/***/ 517:
/***/ (function(module, exports, __webpack_require__) {

module.exports = { "default": __webpack_require__(642), __esModule: true };

/***/ }),

/***/ 642:
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__(644);
module.exports = __webpack_require__(30).Object.keys;


/***/ }),

/***/ 643:
/***/ (function(module, exports, __webpack_require__) {

// most Object methods by ES6 should accept primitives
var $export = __webpack_require__(51);
var core = __webpack_require__(30);
var fails = __webpack_require__(94);
module.exports = function (KEY, exec) {
  var fn = (core.Object || {})[KEY] || Object[KEY];
  var exp = {};
  exp[KEY] = exec(fn);
  $export($export.S + $export.F * fails(function () { fn(1); }), 'Object', exp);
};


/***/ }),

/***/ 644:
/***/ (function(module, exports, __webpack_require__) {

// 19.1.2.14 Object.keys(O)
var toObject = __webpack_require__(97);
var $keys = __webpack_require__(138);

__webpack_require__(643)('keys', function () {
  return function keys(it) {
    return $keys(toObject(it));
  };
});


/***/ }),

/***/ 682:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_keys__ = __webpack_require__(517);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_keys___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_keys__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_jquery__ = __webpack_require__(36);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_jquery___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_jquery__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_jquery_textcomplete__ = __webpack_require__(695);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_jquery_textcomplete___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_jquery_textcomplete__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_jquery_comments__ = __webpack_require__(694);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_jquery_comments___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_jquery_comments__);







/* harmony default export */ __webpack_exports__["default"] = ({
  props: ['classPK', 'className'],
  data: function data() {
    return {
      usersComment: [],
      comment: [],
      argShowMore: true,

      checkOpinion: true
    };
  },
  computed: {
    loading: function loading() {
      return this.$store.getters.loading;
    },
    initData: function initData() {
      return this.$store.getters.loadingInitData;
    }
  },
  watch: {
    classPK: function classPK(val) {
      var vm = this;
      vm.$store.dispatch('loadUsersComment', val).then(function (result) {
        vm.usersComment = result;
        vm.initComment();
      }).catch(function (reject) {
        vm.initComment();
      });
    }
  },
  created: function created() {},
  mounted: function mounted() {},

  methods: {
    runComment: function runComment() {
      var vm = this;
      if (vm.classPK) {
        vm.initComment();
      }
    },

    initComment: function initComment() {
      var vm = this;
      __WEBPACK_IMPORTED_MODULE_1_jquery___default()('#comments-container-el').comments({
        profilePictureURL: 'https://png.icons8.com/material/50/000000/guest-male.png',
        textareaRows: 2,
        enableAttachments: true,
        enableHashtags: true,
        enablePinging: true,
        postCommentOnEnter: false,
        forceResponsive: false,
        readOnly: false,
        textareaPlaceholderText: 'Thêm bình luận mới',
        newestText: 'Mới nhất',
        oldestText: 'Cũ nhất',
        popularText: 'Phổ biến',
        attachmentsText: 'Đính kèm',
        sendText: 'Gửi',
        replyText: 'Trả lời',
        editText: 'Sửa',
        editedText: 'Đã sửa',
        youText: 'Bạn',
        saveText: 'Ghi lại',
        deleteText: 'Xóa',

        hideRepliesText: 'Ẩn câu trả lời',
        noCommentsText: 'Không có bình luận nào',
        noAttachmentsText: 'Không có tệp đính kèm',
        attachmentDropText: 'Kéo thả tệp đính kèm',
        fieldMappings: {
          id: 'commentId',
          parent: 'parent',
          userId: 'userId',
          created: 'createdDate',
          modified: 'modifiedDate',
          content: 'content',
          fileURL: 'fileUrl',
          fileMimeType: 'fileType',
          fileName: 'fileName',
          pings: 'pings',
          creator: 'userId',
          fullname: 'fullname',
          profileURL: 'profileUrl',
          profilePictureURL: 'pictureUrl',
          isNew: 'isNew',
          createdByAdmin: 'isAdmin',
          createdByCurrentUser: 'createdByCurrentUser',
          upvoteCount: 'upvoteCount',
          userHasUpvoted: 'userHasUpvoted',
          email: 'email',
          opinion: 'opinion',
          className: 'className',
          classPK: 'classPK'
        },
        timeFormatter: function timeFormatter(time) {
          if (time) {
            var value = new Date(time);
            value.setHours(value.getHours() - 7);
            return value.getDate().toString().padStart(2, '0') + '/' + (value.getMonth() + 1).toString().padStart(2, '0') + '/' + value.getFullYear() + ' ' + value.getHours().toString().padStart(2, '0') + ':' + value.getMinutes().toString().padStart(2, '0');
          } else {
            return '';
          }
        },
        getUsers: function getUsers(onSuccess, onError) {
          onSuccess(vm.usersComment);
        },
        getComments: function getComments(onSuccess, onError) {
          var dataGet = {
            'classPK': vm.classPK,
            'className': vm.className
          };
          var promise = vm.$store.dispatch('loadCommentItems', dataGet);
          promise.then(function (result) {
            var data = [];
            __WEBPACK_IMPORTED_MODULE_1_jquery___default.a.each(result, function (index, item) {
              vm.comment = item;
              vm.formatComment(vm.comment);
              data.push(vm.comment);
            });
            onSuccess(data);
          }).catch(function (reject) {
            onSuccess([]);
            onError();
          });
        },
        postComment: function postComment(data, onSuccess, onError) {
          data.classPK = vm.classPK;
          data.className = vm.className;
          data.opinion = document.getElementById('opinion').checked;
          vm.$store.dispatch('postComment', data).then(function (result) {
            if (result.opinion) {
              __WEBPACK_IMPORTED_MODULE_1_jquery___default()('.opinion').hide();
            }
            document.getElementById('opinion').checked = false;
            vm.comment = result;
            vm.formatComment(vm.comment);
            onSuccess(vm.comment);
          });
        },
        putComment: function putComment(data, onSuccess, onError) {
          data.classPK = vm.classPK;
          data.className = vm.className;
          vm.$store.dispatch('putComment', data).then(function (result) {
            vm.comment = result;
            vm.formatComment(vm.comment);
            onSuccess(vm.comment);
          });
        },
        deleteComment: function deleteComment(data, onSuccess, onError) {
          data.classPK = vm.classPK;
          data.className = vm.className;
          vm.$store.dispatch('deleteComment', data).then(function (result) {
            onSuccess();
          }).catch(function (reject) {
            onError();
          });
        },
        upvoteComment: function upvoteComment(data, onSuccess, onError) {
          data.classPK = vm.classPK;
          data.className = vm.className;
          vm.$store.dispatch('upvoteComment', data).then(function (result) {
            vm.comment = result;
            vm.formatComment(vm.comment);
            onSuccess(vm.comment);
          }).catch(function (reject) {});
        },
        uploadAttachments: function uploadAttachments(comments, onSuccess, onError) {
          var responses = 0;
          var successfulUploads = [];
          var serverResponded = function serverResponded() {
            responses++;
            if (responses === comments.length) {
              if (successfulUploads.length === 0) {
                onError();
              } else {
                onSuccess(successfulUploads);
              }
            }
          };
          __WEBPACK_IMPORTED_MODULE_1_jquery___default()(comments).each(function (index, comment) {
            var formData = new FormData();
            __WEBPACK_IMPORTED_MODULE_1_jquery___default()(__WEBPACK_IMPORTED_MODULE_0_babel_runtime_core_js_object_keys___default()(comment)).each(function (index, key) {
              var value = comment[key];
              if (value) {
                formData.append(key, value);
              }
            });
            formData.append('file', comment.file);
            formData.append('className', vm.className);
            formData.append('classPK', vm.classPK);
            formData.append('parent', comment.parent != null ? comment.parent : 0);
            formData.append('fileName', comment.file.name);
            formData.append('fileType', comment.file.type);
            formData.append('fileSize', comment.file.size);
            formData.append('pings', comment.pings.join());
            formData.append('opinion', document.getElementById('opinion').checked);

            formData.append('email', vm.initData.user.userEmail);
            formData.append('fullname', vm.initData.user.userName);
            formData.append('opinion', document.getElementById('opinion').checked);
            __WEBPACK_IMPORTED_MODULE_1_jquery___default.a.ajax({
              url: vm.initData.commentApi + '/uploads',
              dataType: 'json',
              type: 'POST',
              headers: {
                'groupId': vm.initData.groupId
              },
              data: formData,
              cache: false,
              contentType: false,
              processData: false,
              success: function success(comment) {
                if (comment.opinion) {
                  __WEBPACK_IMPORTED_MODULE_1_jquery___default()('.opinion').hide();
                }
                document.getElementById('opinion').checked = false;
                vm.formatComment(comment);
                successfulUploads.push(vm.comment);
                serverResponded();
                if (comment.opinion) {
                  __WEBPACK_IMPORTED_MODULE_1_jquery___default()('.opinion').hide();
                }
                document.getElementById('opinion').checked = false;
              },
              error: function error(xhr, data) {
                serverResponded();
              }
            });
          });
        }
      });
    },
    formatComment: function formatComment(comment) {
      var vm = this;
      vm.comment = comment;
      if (comment.parent === 0) {
        vm.comment.parent = null;
      }
      if (comment.fileName === '') {
        vm.comment.fileName = null;
      }
      if (comment.fileType === '') {
        vm.comment.fileType = null;
      }
      if (comment.fileUrl === '') {
        vm.comment.fileUrl = null;
      }
      if (comment.pictureUrl === '') {
        vm.comment.pictureUrl = 'https://png.icons8.com/material/50/000000/guest-male.png';
      }
      vm.comment.fullname = comment.fullname;
      vm.comment.opinion = comment.opinion;
      vm.comment.pings = comment.pings.toString().split(',');
      vm.comment.createdDate = vm.comment.createDate;
      vm.comment.modifiedDate = vm.comment.modifiedDate;
    },
    dateTimeView: function dateTimeView(arg) {
      if (arg) {
        var value = new Date(arg);
        return value.getDate().toString().padStart(2, '0') + '/' + (value.getMonth() + 1).toString().padStart(2, '0') + '/' + value.getFullYear() + ' ' + value.getHours().toString().padStart(2, '0') + ':' + value.getMinutes().toString().padStart(2, '0');
      } else {
        return '';
      }
    }
  },
  filters: {
    dateTimeView: function dateTimeView(arg) {
      if (arg) {
        var value = new Date(arg);
        return value.getDate().toString().padStart(2, '0') + '/' + (value.getMonth() + 1).toString().padStart(2, '0') + '/' + value.getFullYear() + '  ' + value.getHours().toString().padStart(2, '0') + ':' + value.getMinutes().toString().padStart(2, '0');
      } else {
        return '';
      }
    }
  }
});

/***/ }),

/***/ 694:
/***/ (function(module, exports, __webpack_require__) {

/* WEBPACK VAR INJECTION */(function(jQuery) {var __WEBPACK_AMD_DEFINE_FACTORY__, __WEBPACK_AMD_DEFINE_ARRAY__, __WEBPACK_AMD_DEFINE_RESULT__;/* eslint-disable */

(function (factory) {
	if (true) {
		// AMD. Register as an anonymous module.
		!(__WEBPACK_AMD_DEFINE_ARRAY__ = [__webpack_require__(36)], __WEBPACK_AMD_DEFINE_FACTORY__ = (factory),
				__WEBPACK_AMD_DEFINE_RESULT__ = (typeof __WEBPACK_AMD_DEFINE_FACTORY__ === 'function' ?
				(__WEBPACK_AMD_DEFINE_FACTORY__.apply(exports, __WEBPACK_AMD_DEFINE_ARRAY__)) : __WEBPACK_AMD_DEFINE_FACTORY__),
				__WEBPACK_AMD_DEFINE_RESULT__ !== undefined && (module.exports = __WEBPACK_AMD_DEFINE_RESULT__));
	} else if (typeof module === 'object' && module.exports) {
		// Node/CommonJS
		module.exports = function(root, jQuery) {
			if (jQuery === undefined) {
				// require('jQuery') returns a factory that requires window to
				// build a jQuery instance, we normalize how we use modules
				// that require this pattern but the window provided is a noop
				// if it's defined (how jquery works)
				if (typeof window !== 'undefined') {
					jQuery = require('jquery');
				}
				else {
					jQuery = require('jquery')(root);
				}
			}
			factory(jQuery);
			return jQuery;
		};
	} else {
		// Browser globals
		factory(jQuery);
	}
}(function($) {

	var Comments = {
		// Instance variables
		$el: null,
		commentsById: {},
		usersById: {},
		dataFetched: false,
		currentSortKey: '',
		options: {},
		events: {
			// Close dropdowns
			'click': 'closeDropdowns',
			// Save comment on keydown
			'keydown [contenteditable]' : 'saveOnKeydown',
			// Listening changes in contenteditable fields (due to input event not working with IE)
			'focus [contenteditable]' : 'saveEditableContent',
			'keyup [contenteditable]' : 'checkEditableContentForChange',
			'paste [contenteditable]' : 'checkEditableContentForChange',
			'input [contenteditable]' : 'checkEditableContentForChange',
			'blur [contenteditable]' : 'checkEditableContentForChange',
			// Navigation
			'click .navigation li[data-sort-key]' : 'navigationElementClicked',
			'click .navigation li.title' : 'toggleNavigationDropdown',
			// Main comenting field
			'click .commenting-field.main .textarea': 'showMainCommentingField',
			'click .commenting-field.main .close' : 'hideMainCommentingField',
			// All commenting fields
			'click .commenting-field .textarea' : 'increaseTextareaHeight',
			'change .commenting-field .textarea' : 'increaseTextareaHeight textareaContentChanged',
			'click .commenting-field:not(.main) .close' : 'removeCommentingField',
			// Edit mode actions
			'click .commenting-field .send.enabled' : 'postComment',
			'click .commenting-field .update.enabled' : 'putComment',
			'click .commenting-field .delete.enabled' : 'deleteComment',
			'change .commenting-field .upload.enabled input[type="file"]' : 'fileInputChanged',
			// Other actions
			'click li.comment button.upvote' : 'upvoteComment',
			'click li.comment button.delete.enabled' : 'deleteComment',
			'click li.comment .hashtag' : 'hashtagClicked',
			'click li.comment .ping' : 'pingClicked',
			// Other
			'click li.comment ul.child-comments .toggle-all': 'toggleReplies',
			'click li.comment button.reply': 'replyButtonClicked',
			'click li.comment button.edit': 'editButtonClicked',
			// Drag & dropping attachments
			'dragenter' : 'showDroppableOverlay',
			'dragenter .droppable-overlay' : 'handleDragEnter',
			'dragleave .droppable-overlay' : 'handleDragLeaveForOverlay',
			'dragenter .droppable-overlay .droppable' : 'handleDragEnter',
			'dragleave .droppable-overlay .droppable' : 'handleDragLeaveForDroppable',
			'dragover .droppable-overlay' : 'handleDragOverForOverlay',
			'drop .droppable-overlay' : 'handleDrop',
			// Prevent propagating the click event into buttons under the autocomplete dropdown
			'click .dropdown.autocomplete': 'stopPropagation',
			'mousedown .dropdown.autocomplete': 'stopPropagation',
			'touchstart .dropdown.autocomplete': 'stopPropagation',
		},
		// Default options

		getDefaultOptions: function() {
			return {
				// User        
				profilePictureURL: '',
				currentUserIsAdmin: false,
				currentUserId: null,
				// Font awesome icon overrides        
				spinnerIconURL: '',
				upvoteIconURL: '',
				replyIconURL: '',
				uploadIconURL: '',
				attachmentIconURL: '',
				fileIconURL: '',
				noCommentsIconURL: '',
				noCommentsImptIconURL: '',
				// Strings to be formatted (for example localization)     
				textareaPlaceholderText: 'Thêm bình luận mới',
				newestText: 'Mới nhất',
				oldestText: 'Cũ nhất',
				importantCmtText: 'Ý kiến chính thức',
				popularText: 'Phổ biến',
				attachmentsText: 'Tệp đính kèm',
				sendText: 'Gửi',
				replyText: 'Trả lời',
				editText: 'Sửa',
				editedText: 'Đã sửa',
				youText: 'You',
				saveText: 'Ghi lại',
				deleteText: 'Xoá',
				newText: 'Mới',
				viewAllRepliesText: 'Xem tất cả __replyCount__ trả lời',
				hideRepliesText: 'Hide replies',
				noCommentsText: 'Không có bình luận',
				noCommentsImportantText: 'Không có ý kiến chính thức',
				noAttachmentsText: 'Không có tệp đính kèm',
				attachmentDropText: 'Kéo thả tệp để tải lên',
				textFormatter: function(text) {return text},
				
				// Functionalities        
				enableReplying: true,
				enableEditing: true,
				enableUpvoting: true,
				enableDeleting: true,
				enableAttachments: false,
				enableHashtags: false,
				enablePinging: false,
				enableDeletingCommentWithReplies: false,
				enableNavigation: true,
				postCommentOnEnter: false,
				forceResponsive: false,
				readOnly: false,
				defaultNavigationSortKey: 'newest',
				// Colors     
				highlightColor: '#2793e6',
				deleteButtonColor: '#C9302C',
				scrollContainer: this.$el,
				roundProfilePictures: false,
				textareaRows: 2,
				textareaRowsOnFocus: 2,
				textareaMaxRows: 5,
				maxRepliesVisible: 2,
				
				fieldMappings: {
					id: 'id',
					parent: 'parent',
					created: 'createdDate',
					modified: 'modifiedDate',
					content: 'content',
					file: 'file',
					fileURL: 'file_url',
					fileMimeType: 'file_mime_type',
					pings: 'pings',
					creator: 'creator',
					fullname: 'fullname',
					profileURL: 'profile_url',
					profilePictureURL: 'profile_picture_url',
					isNew: 'is_new',
					createdByAdmin: 'created_by_admin',
					createdByCurrentUser: 'created_by_current_user',
					upvoteCount: 'upvote_count',
					userHasUpvoted: 'user_has_upvoted'
				},
				
				getUsers: function(success, error) {success([])},
				getComments: function(success, error) {success([])},
				postComment: function(commentJSON, success, error) {success(commentJSON)},
				putComment: function(commentJSON, success, error) {success(commentJSON)},
				deleteComment: function(commentJSON, success, error) {success()},
				upvoteComment: function(commentJSON, success, error) {success(commentJSON)},
				hashtagClicked: function(hashtag) {},
				pingClicked: function(userId) {},
				uploadAttachments: function(commentArray, success, error) {success(commentArray)},
				refresh: function() {},
				timeFormatter: function(time) {return new Date(time).toLocaleDateString()},
				appendNewComments: function(commentJSONs, success, error) {success([])}
			}
		},

		// Initialization
		init: function(options, el) {
			this.$el = $(el);
			this.$el.addClass('jquery-comments');
			this.undelegateEvents();
			this.delegateEvents();
			// Detect mobile devices
			(function(a){(jQuery.browser=jQuery.browser||{}).mobile=/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows ce|xda|xiino/i.test(a)||/1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(a.substr(0,4))})(navigator.userAgent||navigator.vendor||window.opera);
			if($.browser.mobile) this.$el.addClass('mobile');
			// Init options
			this.options = $.extend(true, {}, this.getDefaultOptions(), options);;
			// Read-only mode
			if(this.options.readOnly) this.$el.addClass('read-only');
			// Set initial sort key
			this.currentSortKey = this.options.defaultNavigationSortKey;
			// Create CSS declarations for highlight color
			this.createCssDeclarations();
			// Fetching data and rendering
			this.fetchDataAndRender();
		},

		delegateEvents: function() {
			this.bindEvents(false);
		},

		undelegateEvents: function() {
			this.bindEvents(true);
		},

		bindEvents: function(unbind) {
				var bindFunction = unbind ? 'off' : 'on';
				for (var key in this.events) {
						var eventName = key.split(' ')[0];
						var selector = key.split(' ').slice(1).join(' ');
						var methodNames = this.events[key].split(' ');

						for(var index in methodNames) {
								if(methodNames.hasOwnProperty(index)) {
										var method = this[methodNames[index]];

										// Keep the context
										method = $.proxy(method, this);

										if (selector == '') {
												this.$el[bindFunction](eventName, method);
										} else {
												this.$el[bindFunction](eventName, selector, method);
										}
								}
						}
				}
		},

		// Basic functionalities
		fetchDataAndRender: function () {
			var self = this;
			setTimeout(function(){self.appendNewComments()},30000);
			this.commentsById = {};
			this.usersById = {};
			this.$el.empty();
			this.createHTML();
			// Render after data has been fetched
			var dataFetched = this.after(this.options.enablePinging ? 2 : 1, function() {
				self.dataFetched = true;
				self.render();
			});

			// Comments
			var commentsFetched = function(commentsArray) {
				// Convert comments to custom data model
				var commentModels = commentsArray.map(function(commentsJSON){
					return self.createCommentModel(commentsJSON)
				});
				// Sort comments by date (oldest first so that they can be appended to the data model
				// without caring dependencies)
				self.sortComments(commentModels, 'oldest');
				$(commentModels).each(function(index, commentModel) {
						self.addCommentToDataModel(commentModel);
				});
				dataFetched();
			};
			this.options.getComments(commentsFetched, dataFetched);

			// Users
			if(this.options.enablePinging) {
				var usersFetched = function(userArray) {
					$(userArray).each(function(index, user) {
						self.usersById[user.id] = user;
					});
					dataFetched();
				}
				this.options.getUsers(usersFetched, dataFetched);
			};
		},

		fetchNext: function() {
			var self = this;
			// Loading indicator
			var spinner = this.createSpinner();
			this.$el.find('ul#comment-list').append(spinner);
			var success = function (commentModels) {
				$(commentModels).each(function(index, commentModel) {
					self.createComment(commentModel);
				});
				spinner.remove();
			}

			var error = function() {
				spinner.remove();
			}
			this.options.getComments(success, error);
		},

		createCommentModel: function(commentJSON) {
			var commentModel = this.applyInternalMappings(commentJSON);
			commentModel.childs = [];
			this.commentsById[commentModel.id] = commentModel;
			return commentModel;
		},

		addCommentToDataModel: function(commentModel) {
			if(!(commentModel.id in this.commentsById)) {
				this.commentsById[commentModel.id] = commentModel;

				// Update child array of the parent (append childs to the array of outer most parent)
				if(commentModel.parent) {
					var outermostParent = this.getOutermostParent(commentModel.parent);
					outermostParent.childs.push(commentModel.id);
				}
			}
		},

		updateCommentModel: function(commentModel) {
			$.extend(this.commentsById[commentModel.id], commentModel);
		},

		render: function() {
			var self = this;
			// Prevent re-rendering if data hasn't been fetched
			if(!this.dataFetched) return;
			// Show active container
			this.showActiveContainer();
			// Create comments
			this.createComments();
			// Create attachments if enabled
			if(this.options.enableAttachments) this.createAttachments(); 
			// Create important comment
			this.createImportantComments();
			// Remove spinner
			this.$el.find('> .spinner').remove();
			this.options.refresh();
		},

		showActiveContainer: function() {
			var activeNavigationEl = this.$el.find('.navigation li[data-container-name].active');
			var containerName = activeNavigationEl.data('container-name');
			var containerEl = this.$el.find('[data-container="' + containerName + '"]');
			containerEl.siblings('[data-container]').hide();
			containerEl.show();
		},

		createComments: function() {
			var self = this;
			// Create the list element before appending to DOM in order to reach better performance
			this.$el.find('#comment-list').remove();
			var commentList = $('<ul/>', {
				id: 'comment-list',
				'class': 'main'
			});

			// Divide commments into main level comments and replies
			var mainLevelComments = [];
			var replies = [];

			$(this.getComments()).each(function(index, commentModel) {
				if(commentModel.parent == null) {
					mainLevelComments.push(commentModel);
				} else {
					replies.push(commentModel);
				}
			});

			// Append main level comments
			this.sortComments(mainLevelComments, this.currentSortKey);
			mainLevelComments.reverse();    // Reverse the order as they are prepended to DOM
			$(mainLevelComments).each(function(index, commentModel) {
				self.addComment(commentModel, commentList);
			});

			// Append replies in chronological order
			this.sortComments(replies, 'oldest');
			$(replies).each(function(index, commentModel) {
				self.addComment(commentModel, commentList);
			});

			// Appned list to DOM
			this.$el.find('[data-container="comments"]').prepend(commentList);
		},

		createAttachments: function() {
			var self = this;
			// Create the list element before appending to DOM in order to reach better performance
			this.$el.find('#attachment-list').remove();
			var attachmentList = $('<ul/>', {
				id: 'attachment-list',
				'class': 'main'
			});

			var attachments = this.getAttachments();
			this.sortComments(attachments, 'newest');
			attachments.reverse();    // Reverse the order as they are prepended to DOM
			$(attachments).each(function(index, commentModel) {
				self.addAttachment(commentModel, attachmentList);
			});

			// Appned list to DOM
			this.$el.find('[data-container="attachments"]').prepend(attachmentList);
		},
		createImportantComments: function() {
			var self = this;
			// Create the list element before appending to DOM in order to reach better performance
			this.$el.find('#importantCmt-list').remove();
			var importantCmtList = $('<ul/>', {
					id: 'importantCmt-list',
					'class': 'main'
			});

			var importantCmt = this.getImportantCmt();

			this.sortComments(importantCmt, 'newest');
			importantCmt.reverse();    // Reverse the order as they are prepended to DOM
			$(importantCmt).each(function(index, commentModel) {
					self.addImportantCmt(commentModel, importantCmtList);
			});

			// Appned list to DOM
			this.$el.find('[data-container="importantCmt"]').prepend(importantCmtList);
		},
		addComment: function(commentModel, commentList) {
			commentList = commentList || this.$el.find('#comment-list');
			var commentEl = this.createCommentElement(commentModel);

			// Case: reply
			if(commentModel.parent) {
				var directParentEl = commentList.find('.comment[data-id="'+commentModel.parent+'"]');

				// Re-render action bar of direct parent element
				this.reRenderCommentActionBar(commentModel.parent);

				// Force replies into one level only
				var outerMostParent = directParentEl.parents('.comment').last();
				if(outerMostParent.length == 0) outerMostParent = directParentEl;

				// Append element to DOM
				var childCommentsEl = outerMostParent.find('.child-comments');
				var commentingField = childCommentsEl.find('.commenting-field');
				if(commentingField.length) {
					commentingField.before(commentEl)
				} else {
					childCommentsEl.append(commentEl);
				}

				// Update toggle all -button
				this.updateToggleAllButton(outerMostParent);

			// Case: main level comment
			} else {
				commentList.prepend(commentEl);
			}
		},
		addAttachment: function(commentModel, commentList) {
			commentList = commentList || this.$el.find('#attachment-list');
			var commentEl = this.createCommentElement(commentModel);
			commentList.prepend(commentEl);
		},
		addImportantCmt: function(commentModel, commentList) {
			commentList = commentList || this.$el.find('#importantCmt-list');
			var commentEl = this.createCommentElement(commentModel);
			commentList.prepend(commentEl);
		},
		removeComment: function(commentId) {
			var self = this;
			var commentModel = this.commentsById[commentId];

			// Remove child comments recursively
			var childComments = this.getChildComments(commentModel.id);
			$(childComments).each(function(index, childComment) {
				self.removeComment(childComment.id);
			});

			// Update the child array of outermost parent
			if(commentModel.parent) {
				var outermostParent = this.getOutermostParent(commentModel.parent);
				var indexToRemove = outermostParent.childs.indexOf(commentModel.id);
				outermostParent.childs.splice(indexToRemove, 1);
			}

			// Remove the comment from data model
			delete this.commentsById[commentId];
			var commentElements = this.$el.find('li.comment[data-id="'+commentId+'"]');
			var parentEl = commentElements.parents('li.comment').last();

			// Remove the element
			commentElements.remove();

			// Update the toggle all button
			this.updateToggleAllButton(parentEl);
		},

		uploadAttachments: function(files, commentingField) {
			var self = this;
			if(!commentingField) commentingField = this.$el.find('.commenting-field.main');
			var isReply = !commentingField.hasClass('main');
			var fileCount = files.length;

			if(fileCount) {
				var uploadButton = commentingField.find('.upload');
				var textarea = commentingField.find('.textarea');

				// Disable upload button and append spinners while request is pending
				uploadButton.removeClass('enabled');
				var attachmentListSpinner = this.createSpinner();
				var commentListSpinner = this.createSpinner();
				this.$el.find('ul#attachment-list').prepend(attachmentListSpinner);
				if(isReply) {
					commentingField.before(commentListSpinner);
				} else {
					this.$el.find('ul#comment-list').prepend(commentListSpinner);
				}

				var success = function(commentArray) {
					$(commentArray).each(function(index, commentJSON) {
						var commentModel = self.createCommentModel(commentJSON);
						self.addCommentToDataModel(commentModel);
						self.addComment(commentModel);
						self.addAttachment(commentModel);
					});

					// Close the commenting field if all the uploads were successfull
					// and there's no content besides the attachment
					if(commentArray.length == fileCount && self.getTextareaContent(textarea).length == 0) {
						commentingField.find('.close').trigger('click');
					}

					// Enable upload button and remove spinners
					uploadButton.addClass('enabled');
					commentListSpinner.remove();
					attachmentListSpinner.remove();
				};

				var error = function() {
					// Enable upload button and remove spinners
					uploadButton.addClass('enabled');
					commentListSpinner.remove();
					attachmentListSpinner.remove();
				};

				var commentArray = [];
				$(files).each(function(index, file) {
					// Create comment JSON
					var commentJSON = self.createCommentJSON(textarea);
					commentJSON.id += '-' + index;
					commentJSON.content = '';
					commentJSON.file = file;
					commentJSON.fileURL = 'C:/fakepath/' + file.name;
					commentJSON.fileMimeType = file.type;
					// Reverse mapping
					commentJSON = self.applyExternalMappings(commentJSON);
					commentArray.push(commentJSON);
				});

				self.options.uploadAttachments(commentArray, success, error);
			}

			// Clear the input field
			uploadButton.find('input').val('');
		},

		updateToggleAllButton: function(parentEl) {
			// Don't hide replies if maxRepliesVisible is null or undefined
			if (this.options.maxRepliesVisible == null) return;

			var childCommentsEl = parentEl.find('.child-comments');
			var childComments = childCommentsEl.find('.comment');
			var toggleAllButton = childCommentsEl.find('li.toggle-all');
			childComments.removeClass('hidden-reply');

			// Select replies to be hidden
			if (this.options.maxRepliesVisible === 0) {
				var hiddenReplies = childComments;
			} else {
				var hiddenReplies = childComments.slice(0, -this.options.maxRepliesVisible);
			}

			// Add identifying class for hidden replies so they can be toggled
			hiddenReplies.addClass('hidden-reply');

			// Show all replies if replies are expanded
			if(toggleAllButton.find('span.text').text() == this.options.textFormatter(this.options.hideRepliesText)) {
				hiddenReplies.addClass('visible');
			}

			// Make sure that toggle all button is present
			if(childComments.length > this.options.maxRepliesVisible) {

				// Append button to toggle all replies if necessary
				if(!toggleAllButton.length) {

					toggleAllButton = $('<li/>', {
						'class': 'toggle-all highlight-font-bold'
					});
					var toggleAllButtonText = $('<span/>', {
						'class': 'text'
					});
					var caret = $('<span/>', {
						'class': 'caret'
					});

					// Append toggle button to DOM
					toggleAllButton.append(toggleAllButtonText).append(caret);
					childCommentsEl.prepend(toggleAllButton);
				}

				// Update the text of toggle all -button
				this.setToggleAllButtonText(toggleAllButton, false);
			// Make sure that toggle all button is not present
			} else {
				toggleAllButton.remove();
			}
		},

		sortComments: function (comments, sortKey) {
			var self = this;
			// Sort by popularity
			if(sortKey == 'popularity') {
				comments.sort(function(commentA, commentB) {
					var pointsOfA = commentA.childs.length;
					var pointsOfB = commentB.childs.length;

					if(self.options.enableUpvoting) {
						pointsOfA += commentA.upvoteCount;
						pointsOfB += commentB.upvoteCount;
					}

					if(pointsOfB != pointsOfA) {
						return pointsOfB - pointsOfA;

					} else {
						// Return newer if popularity is the same
						var createdA = new Date(commentA.created).getTime();
						var createdB = new Date(commentB.created).getTime();
						return createdB - createdA;
					}
				});
			// Sort by date
			} else {
				comments.sort(function(commentA, commentB) {
					var createdA = (new Date(commentA.created)).getTime();
					var createdB = (new Date(commentB.created)).getTime();
					if(sortKey == 'oldest') {
						return createdA - createdB;
					} else {
						return createdB - createdA;
					}
				});
			}
		},

		sortUsers: function(users) {
			users.sort(function(a,b) {
				var nameA = a.fullname.toLowerCase().trim();
				var nameB = b.fullname.toLowerCase().trim();
				if(nameA < nameB) return -1;
				if(nameA > nameB) return 1;
				return 0;
			});
		},

		sortAndReArrangeComments: function(sortKey) {
			var commentList = this.$el.find('#comment-list');

			// Get main level comments
			var mainLevelComments = this.getComments().filter(function(commentModel){return !commentModel.parent});
			this.sortComments(mainLevelComments, sortKey);

			// Rearrange the main level comments
			$(mainLevelComments).each(function(index, commentModel) {
					var commentEl = commentList.find('> li.comment[data-id='+commentModel.id+']');
					commentList.append(commentEl);
			});
		},

		showActiveSort: function() {
			var activeElements = this.$el.find('.navigation li[data-sort-key="' + this.currentSortKey + '"]');

			// Indicate active sort
			this.$el.find('.navigation li').removeClass('active');
			activeElements.addClass('active');

			// Update title for dropdown
			var titleEl = this.$el.find('.navigation .title');
			if(this.currentSortKey != 'attachments') {
				titleEl.addClass('active');
				titleEl.find('header').html(activeElements.first().html());
			} else {
				var defaultDropdownEl = this.$el.find('.navigation ul.dropdown').children().first();
				titleEl.find('header').html(defaultDropdownEl.html());
			} 

			// Show active container
			this.showActiveContainer();
		},

		forceResponsive: function() {
			this.$el.addClass('responsive');
		},

		// Event handlers

		closeDropdowns: function() {
			this.$el.find('.dropdown').hide();
		},

		saveOnKeydown: function(ev) {
			// Save comment on cmd/ctrl + enter
			if(ev.keyCode == 13) {
				var metaKey = ev.metaKey || ev.ctrlKey;
				if(this.options.postCommentOnEnter || metaKey) {                
					var el = $(ev.currentTarget);
					el.siblings('.control-row').find('.save').trigger('click');
					ev.stopPropagation();
					ev.preventDefault();
				}
			}
		},

		saveEditableContent: function(ev) {
			var el = $(ev.currentTarget);
			el.data('before', el.html());
		},

		checkEditableContentForChange: function(ev) {
			var el = $(ev.currentTarget);
			if (el.data('before') != el.html()) {
				el.data('before', el.html());
				el.trigger('change');
			}
		},

		navigationElementClicked: function(ev) {
			var navigationEl = $(ev.currentTarget);
			var sortKey = navigationEl.data().sortKey;

			// Sort the comments if necessary
			if(sortKey != 'attachments') {
					this.sortAndReArrangeComments(sortKey);
			}
			// Save the current sort key
			this.currentSortKey = sortKey;
			this.showActiveSort();
		},

		toggleNavigationDropdown: function(ev) {
			// Prevent closing immediately
			ev.stopPropagation();

			var dropdown = $(ev.currentTarget).find('~ .dropdown');
			dropdown.toggle();
		},

		showMainCommentingField: function(ev) {
			var mainTextarea = $(ev.currentTarget);
			mainTextarea.siblings('.control-row').show();
			mainTextarea.parent().find('.close').show();
			mainTextarea.focus();
		},

		hideMainCommentingField: function(ev) {
			var closeButton = $(ev.currentTarget);
			var mainTextarea = this.$el.find('.commenting-field.main .textarea');
			var mainControlRow = this.$el.find('.commenting-field.main .control-row');

			this.clearTextarea(mainTextarea);
			this.adjustTextareaHeight(mainTextarea, false);

			mainControlRow.hide();
			closeButton.hide();
			mainTextarea.blur();
		},

		increaseTextareaHeight: function(ev) {
			var textarea = $(ev.currentTarget);
			this.adjustTextareaHeight(textarea, true);
		},

		textareaContentChanged: function(ev) {
			var textarea = $(ev.currentTarget);
			var saveButton = textarea.siblings('.control-row').find('.save');

			// Update parent id if reply-to tag was removed
			if(!textarea.find('.reply-to.tag').length) {
				var commentId = textarea.attr('data-comment');

				// Case: editing comment
				if(commentId) {
					var parentComments = textarea.parents('li.comment');
					if(parentComments.length > 1) {
						var parentId = parentComments.last().data('id');
						textarea.attr('data-parent', parentId);
					}

				// Case: new comment
				} else {
					var parentId = textarea.parents('li.comment').last().data('id');
					textarea.attr('data-parent', parentId);
				}
			}

			// Move close button if scrollbar is visible
			var commentingField = textarea.parents('.commenting-field').first();
			if(textarea[0].scrollHeight > textarea.outerHeight()) {
				commentingField.addClass('scrollable');
			} else {
				commentingField.removeClass('scrollable');
			}

			// Check if content or parent has changed if editing
			var contentOrParentChangedIfEditing = true;
			var content = this.getTextareaContent(textarea, true);
			if(commentId = textarea.attr('data-comment')) {
				var contentChanged = content != this.commentsById[commentId].content;
				var parentFromModel;
				if(this.commentsById[commentId].parent) {
					parentFromModel = this.commentsById[commentId].parent.toString();
				}
				var parentChanged = textarea.attr('data-parent') != parentFromModel;
				contentOrParentChangedIfEditing = contentChanged || parentChanged;
			}

			// Check whether save button needs to be enabled
			if(content.length && contentOrParentChangedIfEditing) {
				saveButton.addClass('enabled');
			} else {
				saveButton.removeClass('enabled');
			}
		},

		removeCommentingField: function(ev) {
			var closeButton = $(ev.currentTarget);

			// Remove edit class from comment if user was editing the comment
			var textarea = closeButton.siblings('.textarea');
			if(textarea.attr('data-comment')) {
				closeButton.parents('li.comment').first().removeClass('edit');
			}

			// Remove the field
			var commentingField = closeButton.parents('.commenting-field').first();
			commentingField.remove();
		},

		appendNewComments: function() {
			var self = this;
			console.log($(self.$el.context), $(self.$el.context).length);
			if ($(self.$el.context).length == 0) {
				clearTimeout(myVar);
				return;
			}
			var commentJSONs = this.getComments();

			var success = function(commentsNew, commentsEdit) {
				for (var c in commentsNew) {
					self.createComment(commentsNew[c]);
				}
				
				for (var cmt in commentsEdit) {
					var commentModel = self.createCommentModel(commentsEdit[cmt]);
					self.updateCommentModel(commentModel);
					self.reRenderComment(commentModel.id);
				}
			};

			var error = function() {
				console.log('jquery comment append new error');
			};
			var myVar = setTimeout(function(){self.appendNewComments()},30000);
			this.options.appendNewComments(commentJSONs, success, error);
		},
			
		postComment: function(ev) {
			var self = this;
			var sendButton = $(ev.currentTarget);
			var commentingField = sendButton.parents('.commenting-field').first();
			var textarea = commentingField.find('.textarea');

			// Disable send button while request is pending
			sendButton.removeClass('enabled');

			// Create comment JSON
			var commentJSON = this.createCommentJSON(textarea);

			// Reverse mapping
			commentJSON = this.applyExternalMappings(commentJSON);

			var success = function(commentJSON) {
				self.currentSortKey = 'newest'
				self.showActiveSort()
				self.createComment(commentJSON);
				commentingField.find('.close').trigger('click');
			};

			var error = function() {
				sendButton.addClass('enabled');
			};

			this.options.postComment(commentJSON, success, error);
		},

		createComment: function(commentJSON) {
			var commentModel = this.createCommentModel(commentJSON);
			this.addCommentToDataModel(commentModel);
			this.addComment(commentModel);
			if (commentModel.opinion == true) {
				this.addImportantCmt(commentModel);
			}
		},

		putComment: function(ev) {
			var self = this;
			var saveButton = $(ev.currentTarget);
			var commentingField = saveButton.parents('.commenting-field').first();
			var textarea = commentingField.find('.textarea');

			// Disable send button while request is pending
			saveButton.removeClass('enabled');

			// Use a clone of the existing model and update the model after succesfull update
			var commentJSON =  $.extend({}, this.commentsById[textarea.attr('data-comment')]);
			$.extend(commentJSON, {
				parent: textarea.attr('data-parent') || null,
				content: this.getTextareaContent(textarea),
				pings: this.getPings(textarea),
				modified: new Date().getTime()
			});

			// Reverse mapping
			commentJSON = this.applyExternalMappings(commentJSON);
			
			var success = function(commentJSON) {
				// The outermost parent can not be changed by editing the comment so the childs array
				// of parent does not require an update

				var commentModel = self.createCommentModel(commentJSON);
			
				// Delete childs array from new comment model since it doesn't need an update
				delete commentModel['childs'];
				self.updateCommentModel(commentModel);
				// Close the editing field
				commentingField.find('.close').trigger('click');

				// Re-render the comment
				self.reRenderComment(commentModel.id);
			};

			var error = function() {
				saveButton.addClass('enabled');
			};

			this.options.putComment(commentJSON, success, error);
		},

		deleteComment: function(ev) {
			var self = this;
			var deleteButton = $(ev.currentTarget);
			var commentEl = deleteButton.parents('.comment').first();
			var commentJSON =  $.extend({}, this.commentsById[commentEl.attr('data-id')]);
			var commentId = commentJSON.id;
			var parentId = commentJSON.parent;

			// Disable send button while request is pending
			deleteButton.removeClass('enabled');

			// Reverse mapping
			commentJSON = this.applyExternalMappings(commentJSON);

			var success = function() {
				self.removeComment(commentId);
				if(parentId) self.reRenderCommentActionBar(parentId);
				// 
				if (commentJSON.opinion==true) {
					$(`[data-sort-key=${self.currentSortKey}]`).trigger('click')
				}
				// 
			};

			var error = function() {
				deleteButton.addClass('enabled');
			};

			this.options.deleteComment(commentJSON, success, error);
		},

		hashtagClicked: function(ev) {
			var el = $(ev.currentTarget);
			var value = el.attr('data-value');
			this.options.hashtagClicked(value);
		},

		pingClicked: function(ev) {
			var el = $(ev.currentTarget);
			var value = el.attr('data-value');
			this.options.pingClicked(value);
		},

		fileInputChanged: function(ev, files) {
			var files = ev.currentTarget.files;
			var commentingField = $(ev.currentTarget).parents('.commenting-field').first();
			this.uploadAttachments(files, commentingField);
		},

		upvoteComment: function(ev) {
			var self = this;
			var commentEl = $(ev.currentTarget).parents('li.comment').first();
			var commentModel = commentEl.data().model;

			// Check whether user upvoted the comment or revoked the upvote
			var previousUpvoteCount = commentModel.upvoteCount;
			var newUpvoteCount;
			if(commentModel.userHasUpvoted) {
				newUpvoteCount = previousUpvoteCount - 1;
			} else {
				newUpvoteCount = previousUpvoteCount + 1;
			}

			// Show changes immediatelly
			commentModel.userHasUpvoted = !commentModel.userHasUpvoted;
			commentModel.upvoteCount = newUpvoteCount;
			this.reRenderUpvotes(commentModel.id);

			// Reverse mapping
			var commentJSON = $.extend({}, commentModel);
			commentJSON = this.applyExternalMappings(commentJSON);

			var success = function(commentJSON) {
				var commentModel = self.createCommentModel(commentJSON);
				self.updateCommentModel(commentModel);
				self.reRenderUpvotes(commentModel.id);
			};

			var error = function() {
				// Revert changes
				commentModel.userHasUpvoted = !commentModel.userHasUpvoted;
				commentModel.upvoteCount = previousUpvoteCount;
				self.reRenderUpvotes(commentModel.id);
			};

			this.options.upvoteComment(commentJSON, success, error);
		},

		toggleReplies: function(ev) {
			var el = $(ev.currentTarget);
			el.siblings('.hidden-reply').toggleClass('visible');
			this.setToggleAllButtonText(el, true);
		},

		replyButtonClicked: function(ev) {
			var replyButton = $(ev.currentTarget);
			var outermostParent = replyButton.parents('li.comment').last();
			var parentId = replyButton.parents('.comment').first().data().id;

			// Remove existing field
			var replyField = outermostParent.find('.child-comments > .commenting-field');
			if(replyField.length) replyField.remove();
			var previousParentId = replyField.find('.textarea').attr('data-parent');

			// Create the reply field (do not re-create)
			if(previousParentId != parentId) {
				replyField = this.createCommentingFieldElement(parentId, null, false);
				outermostParent.find('.child-comments').append(replyField);

				// Move cursor to end
				var textarea = replyField.find('.textarea');
				this.moveCursorToEnd(textarea)

				// Make sure the reply field will be displayed
				var scrollTop = this.options.scrollContainer.scrollTop();
				var endOfReply = scrollTop + replyField.position().top + replyField.outerHeight();
				var endOfScrollable = scrollTop + this.options.scrollContainer.outerHeight();
				if(endOfReply > endOfScrollable) {
					var newScrollTop = scrollTop + (endOfReply - endOfScrollable);
					this.options.scrollContainer.scrollTop(newScrollTop);
				}
			}
		},

		editButtonClicked: function(ev) {
			var editButton = $(ev.currentTarget);
			var commentEl = editButton.parents('li.comment').first();
			var commentModel = commentEl.data().model;
			
			commentModel = this.commentsById[commentModel.id];
			commentEl.addClass('edit');

			// Create the editing field
			var editField = this.createCommentingFieldElement(commentModel.parent, commentModel.id, false);
			commentEl.find('.comment-wrapper').first().append(editField);

			// Append original content
			var textarea = editField.find('.textarea');
			textarea.attr('data-comment', commentModel.id);

			// Escaping HTML
			textarea.append(this.getFormattedCommentContent(commentModel, true));

			// Move cursor to end
			this.moveCursorToEnd(textarea);
		},

		showDroppableOverlay: function(ev) {
			if(this.options.enableAttachments) {
				this.$el.find('.droppable-overlay').css('top', this.$el[0].scrollTop);
				this.$el.find('.droppable-overlay').show();
				this.$el.addClass('drag-ongoing');
			}
		},

		handleDragEnter: function(ev) {
			var count = $(ev.currentTarget).data('dnd-count') || 0;
			count++;
			$(ev.currentTarget).data('dnd-count', count);
			$(ev.currentTarget).addClass('drag-over');
		},

		handleDragLeave: function(ev, callback) {
			var count = $(ev.currentTarget).data('dnd-count');
			count--;
			$(ev.currentTarget).data('dnd-count', count);

			if(count == 0) {            
				$(ev.currentTarget).removeClass('drag-over');
				if(callback) callback();
			}
		},

		handleDragLeaveForOverlay: function(ev) {
			var self = this;
			this.handleDragLeave(ev, function() {
				self.hideDroppableOverlay();
			});
		},

		handleDragLeaveForDroppable: function(ev) {
			this.handleDragLeave(ev);
		},

		handleDragOverForOverlay: function(ev) {
			ev.stopPropagation();
			ev.preventDefault();
			ev.originalEvent.dataTransfer.dropEffect = 'copy';
		},

		hideDroppableOverlay: function() {
			this.$el.find('.droppable-overlay').hide();
			this.$el.removeClass('drag-ongoing');
		},

		handleDrop: function(ev) {
			ev.preventDefault();

			// Reset DND counts
			$(ev.target).trigger('dragleave');

			// Hide the overlay and upload the files
			this.hideDroppableOverlay();
			this.uploadAttachments(ev.originalEvent.dataTransfer.files);
		},

		stopPropagation: function(ev) {
			ev.stopPropagation();
		},


		// HTML elements

		createHTML: function() {
			var self = this;
			// Commenting field
			var mainCommentingField = this.createCommentingFieldElement(null, null, true);
			mainCommentingField.addClass('main');
			this.$el.append(mainCommentingField);

			// Hide control row and close button
			var mainControlRow = mainCommentingField.find('.control-row');
			mainControlRow.hide();
			mainCommentingField.find('.close').hide();

			// Navigation bar
			if (this.options.enableNavigation) {
				this.$el.append(this.createNavigationElement());
				this.showActiveSort();
			}

			// Loading spinner
			var spinner = this.createSpinner();
			this.$el.append(spinner);

			// Comments container
			var commentsContainer = $('<div/>', {
				'class': 'data-container overflowComment',
				'data-container': 'comments'
			});
			this.$el.append(commentsContainer);
				
			// "No comments" placeholder
			var noComments = $('<div/>', {
				'class': 'no-comments no-data',
				text: this.options.textFormatter(this.options.noCommentsText)
			});
			var noCommentsIcon = $('<i/>', {
				'class': 'fa fa-comments fa-2x'
			});
			if(this.options.noCommentsIconURL.length) {
				noCommentsIcon.css('background-image', 'url("'+this.options.noCommentsIconURL+'")');
				noCommentsIcon.addClass('image');
			}
			noComments.prepend($('<br/>')).prepend(noCommentsIcon);            
			commentsContainer.append(noComments);

			// important Comment Container
			var importantCmtContainer = $('<div/>', {
				'class': 'data-container overflowComment',
				'data-container': 'importantCmt'
			});
			this.$el.append(importantCmtContainer);
				// "No comments Important" placeholder
			var noCommentsImpt = $('<div/>', {
				'class': 'no-comments no-data',
				text: this.options.textFormatter(this.options.noCommentsImportantText)
			});
			var noCommentsImptIcon = $('<i/>', {
				'class': 'fa fa-comments fa-2x'
			});
			if(this.options.noCommentsImptIconURL.length) {
				noCommentsImptIcon.css('background-image', 'url("'+this.options.noCommentsImptIconURL+'")');
				noCommentsImptIcon.addClass('image');
			}
			noCommentsImpt.prepend($('<br/>')).prepend(noCommentsImptIcon);            
			importantCmtContainer.append(noCommentsImpt);

			// Attachments
			if(this.options.enableAttachments) {
				// Attachments container
				var attachmentsContainer = $('<div/>', {
					'class': 'data-container overflowComment',
					'data-container': 'attachments'
				});
				this.$el.append(attachmentsContainer);
				
				// "No attachments" placeholder
				var noAttachments = $('<div/>', {
					'class': 'no-attachments no-data',
					text: this.options.textFormatter(this.options.noAttachmentsText)
				});
				var noAttachmentsIcon = $('<i/>', {
					'class': 'fa fa-paperclip fa-2x'
				});
				if(this.options.attachmentIconURL.length) {
					noAttachmentsIcon.css('background-image', 'url("'+this.options.attachmentIconURL+'")');
					noAttachmentsIcon.addClass('image');
				}
				noAttachments.prepend($('<br/>')).prepend(noAttachmentsIcon);
				attachmentsContainer.append(noAttachments);


				// Drag & dropping attachments
				var droppableOverlay = $('<div/>', {
					'class': 'droppable-overlay'
				});

				var droppableContainer = $('<div/>', {
					'class': 'droppable-container'
				});

				var droppable = $('<div/>', {
					'class': 'droppable'
				});

				var uploadIcon = $('<i/>', {
					'class': 'fa fa-upload fa-4x'
				});
				if(this.options.uploadIconURL.length) {
					uploadIcon.css('background-image', 'url("'+this.options.uploadIconURL+'")');
					uploadIcon.addClass('image');
				}

				var dropAttachmentText = $('<div/>', {
					text: this.options.textFormatter(this.options.attachmentDropText)
				});
				droppable.append(uploadIcon);
				droppable.append(dropAttachmentText);

				droppableOverlay.html(droppableContainer.html(droppable)).hide();
				this.$el.append(droppableOverlay);
			}
		},

		createProfilePictureElement: function(src) {
			if(src) {
				var profilePicture = $('<img/>', {
					src: src
				});
			} else {
				var profilePicture = $('<i/>', {
					'class': 'fa fa-user'
				});
			}
			profilePicture.addClass('profile-picture');
			if(this.options.roundProfilePictures) profilePicture.addClass('round');
			return profilePicture;
		},

		createCommentingFieldElement: function(parentId, existingCommentId, enableOpinion) {
			var self = this;
			// Commenting field
			var commentingField = $('<div/>', {
				'class': 'commenting-field'
			});

			// Profile picture
			if(existingCommentId) {
				var profilePictureURL = this.commentsById[existingCommentId].profilePictureURL;
			} else {
				var profilePictureURL = this.options.profilePictureURL;
			}
			var profilePicture = this.createProfilePictureElement(profilePictureURL);

			// New comment
			var textareaWrapper = $('<div/>', {
				'class': 'textarea-wrapper'
			});

			// Control row
			var controlRow = $('<div/>', {
				'class': 'control-row'
			});

			// Textarea
			var textarea = $('<div/>', {
				'class': 'textarea',
				'data-placeholder': this.options.textFormatter(this.options.textareaPlaceholderText),
				contenteditable: true
			});

			// Setting the initial height for the textarea
			this.adjustTextareaHeight(textarea, false);

			// Close button
			var closeButton = $('<span/>', {
				'class': 'close'
			}).append($('<span class="left"/>')).append($('<span class="right"/>'));

			// Save button text
			if(existingCommentId) {
				var saveButtonText = this.options.textFormatter(this.options.saveText);
				var opinionCmtId = existingCommentId
				// Delete button Edit Comment
				// var deleteButton = $('<span/>', {
				// 		'class': 'delete',
				// 		text: this.options.textFormatter(this.options.deleteText)
				// }).css('background-color', this.options.deleteButtonColor);
				// controlRow.append(deleteButton);

				// // Enable the delete button only if the user is allowed to delete
				// if(this.isAllowedToDelete(existingCommentId)) deleteButton.addClass('enabled')

			} else {
				var saveButtonText = this.options.textFormatter(this.options.sendText);
				var opinionCmtId =''
				// Add upload button if attachments are enabled
				if(this.options.enableAttachments) {
					var uploadButton = $('<span/>', {
						'class': 'enabled upload'
					});
					var uploadIcon = $('<i/>', {
						'class': 'md-icon',
						text: 'Tải lên'
					});
					var fileInput = $('<input/>', {
						type: 'file',
						'data-role': 'none' // Prevent jquery-mobile for adding classes
					});
					// Multi file upload might not work with backend as the the file names
					// may be the same causing duplicates
					if(!$.browser.mobile) fileInput.attr('multiple', 'multiple');

					if(this.options.uploadIconURL.length) {
						uploadIcon.css('background-image', 'url("'+this.options.uploadIconURL+'")');
						uploadIcon.addClass('image');
					}
					uploadButton.append(uploadIcon).append(fileInput);
					controlRow.append(uploadButton);
				}
			}

			// Save button
			var saveButtonClass = existingCommentId ? 'update' : 'send';
			var saveButton = $('<span/>', {
				'class': saveButtonClass + ' save highlight-background',
				text: saveButtonText
			});
			// Check opinion

			if (enableOpinion) {
				var checkOpinion = $('<input/>', {
					'class': 'opinion checkOpinion mt-1 mr-1',
					'id': 'opinion' + opinionCmtId,
					'name': 'opinion' + opinionCmtId,
					'type': 'checkbox'
				});
				var labelCheckOpinion = $('<label/>', {
					'class': 'opinion label-opinion',
					'for': 'opinion'+opinionCmtId,
					text: 'Ý kiến chính thức'
				});
				controlRow.append(checkOpinion).append(labelCheckOpinion);
			}
			
			// Populate the element
			controlRow.prepend(saveButton);
			textareaWrapper.append(closeButton).append(textarea).append(controlRow);
			commentingField.append(profilePicture).append(textareaWrapper);


			if(parentId) {

					// Set the parent id to the field if necessary
				textarea.attr('data-parent', parentId);

				// Append reply-to tag if necessary
				var parentModel = this.commentsById[parentId];
				if(parentModel.parent) {
					textarea.html('&nbsp;');    // Needed to set the cursor to correct place

					// Creating the reply-to tag
					var replyToName = '@' + parentModel.fullname;
					var replyToTag = this.createTagElement(replyToName, 'reply-to', parentModel.creator);
					textarea.prepend(replyToTag);
				}
			}

			// Pinging users
			if(this.options.enablePinging) {
					textarea.textcomplete([{
							/**match: /(^|\s)@(([a-zäöüß]|\s)*)$/im,*/
						match: /(^|\s)@(([0-9]|[^\u0000-\u007F]|[a-zäöüß]|\s)*)$/im,
							search: function (term, callback) {
								// console.log(term);
									term = self.normalizeSpaces(term);

									// Users excluding self and already pinged users
									var pings = self.getPings(textarea);
									var users = self.getUsers().filter(function(user) {
											var isSelf = user.id == self.options.currentUserId;
											var alreadyPinged = pings.indexOf(user.id) != -1;
											return !isSelf && !alreadyPinged;
									});

									// Sort users
									self.sortUsers(users);

									callback($.map(users, function (user) {
											var lowercaseTerm = term.toLowerCase();
											var nameMatch = user.fullname.toLowerCase().indexOf(lowercaseTerm) != -1;
											var emailMatch = user.email.toLowerCase().indexOf(lowercaseTerm) != -1;
											return nameMatch || emailMatch ? user : null;
									}));
							},
							template: function(user) {
									var wrapper = $('<div/>');

									var profilePictureEl = $('<img/>', {
											src: user.pictureUrl,
											'class': 'profile-picture round'
									});
									var detailsEl = $('<div/>', {
											'class': 'details',
									});
									var nameEl = $('<div/>', {
											'class': 'name',
									}).html(user.fullname);

									var emailEl = $('<div/>', {
											'class': 'email',
									}).html(user.email);
											
									if (user.email) {
											detailsEl.append(nameEl).append(emailEl);
									} else {
											detailsEl.addClass('no-email')
											detailsEl.append(nameEl)
									}

									wrapper.append(profilePictureEl).append(detailsEl);
									return wrapper.html();
							},
							replace: function (user) {
									var tag = self.createTagElement('@' + user.fullname, 'ping', user.id);
									return ' ' + tag[0].outerHTML + ' ';
							},
					}], {
							appendTo: '.jquery-comments',
							dropdownClassName: 'dropdown autocomplete',
							maxCount: 5,
							rightEdgeOffset: 0,
					});


					// OVERIDE TEXTCOMPLETE DROPDOWN POSITIONING

					$.fn.textcomplete.Dropdown.prototype.render = function(zippedData) {
						var contentsHtml = this._buildContents(zippedData);
						var unzippedData = $.map(zippedData, function (d) { return d.value; });
						if (zippedData.length) {
							var strategy = zippedData[0].strategy;
							if (strategy.id) {
								this.$el.attr('data-strategy', strategy.id);
							} else {
								this.$el.removeAttr('data-strategy');
							}
							this._renderHeader(unzippedData);
							this._renderFooter(unzippedData);
							if (contentsHtml) {
								this._renderContents(contentsHtml);
								this._fitToBottom();
								this._fitToRight();
								this._activateIndexedItem();
							}
							this._setScroll();
						} else if (this.noResultsMessage) {
							this._renderNoResultsMessage(unzippedData);
						} else if (this.shown) {
							this.deactivate();
						}

						// CUSTOM CODE
						// ===========

						// Adjust vertical position
						var top = parseInt(this.$el.css('top')) + self.options.scrollContainer.scrollTop();
						this.$el.css('top', top);

						// Adjust horizontal position
						var originalLeft = this.$el.css('left');
						this.$el.css('left', 0);    // Left must be set to 0 in order to get the real width of the el
						var maxLeft = self.$el.width() - this.$el.outerWidth();
						var left = Math.min(maxLeft, parseInt(originalLeft));
						this.$el.css('left', left);

						// ===========
					}
			}

			return commentingField;
	},

	createNavigationElement: function() {
		var navigationEl = $('<ul/>', {
			'class': 'navigation'
		});
		var navigationWrapper = $('<div/>', {
			'class': 'navigation-wrapper'
		});
		navigationEl.append(navigationWrapper);

		// Newest
		var newest = $('<li/>', {
			text: this.options.textFormatter(this.options.newestText),
			'data-sort-key': 'newest',
			'data-container-name': 'comments'
		});

		// Oldest
		var oldest = $('<li/>', {
			text: this.options.textFormatter(this.options.oldestText),
			'data-sort-key': 'oldest',
			'data-container-name': 'comments'
		});
		// Important
		var important = $('<li/>', {
			text: this.options.textFormatter(this.options.importantCmtText),
			'data-sort-key': 'important',
			'data-container-name': 'importantCmt'
		});
		// Popular
		var popular = $('<li/>', {
			text: this.options.textFormatter(this.options.popularText),
			'data-sort-key': 'popularity',
			'data-container-name': 'comments'
		});

		// Attachments
		var attachments = $('<li/>', {
			text: this.options.textFormatter(this.options.attachmentsText),
			'data-sort-key': 'attachments',
			'data-container-name': 'attachments'
		});

		// Attachments icon
		var attachmentsIcon = $('<i/>', {
			'class': 'fa fa-paperclip'
		});
		if(this.options.attachmentIconURL.length) {
			attachmentsIcon.css('background-image', 'url("'+this.options.attachmentIconURL+'")');
			attachmentsIcon.addClass('image');
		}
		attachments.prepend(attachmentsIcon);

		// Responsive navigation
		var dropdownNavigationWrapper = $('<div/>', {
			'class': 'navigation-wrapper responsive'
		});
		var dropdownNavigation = $('<ul/>', {
			'class': 'dropdown'
		});
		var dropdownTitle = $('<li/>', {
			'class': 'title'
		});
		var dropdownTitleHeader = $('<header/>');

		dropdownTitle.append(dropdownTitleHeader);
		dropdownNavigationWrapper.append(dropdownTitle);
		dropdownNavigationWrapper.append(dropdownNavigation);
		navigationEl.append(dropdownNavigationWrapper);

		// Populate elements
		navigationWrapper.append(newest).append(oldest).append(important);
		dropdownNavigation.append(newest.clone()).append(oldest.clone()).append(important.clone());

		if(this.options.enableReplying || this.options.enableUpvoting) {
			navigationWrapper.append(popular);
			dropdownNavigation.append(popular.clone());
		}
		if(this.options.enableAttachments) {
			navigationWrapper.append(attachments);
			dropdownNavigationWrapper.append(attachments.clone());
		}

		if(this.options.forceResponsive) this.forceResponsive();
		return navigationEl;
	},

	createSpinner: function() {
		var spinner = $('<div/>', {
			'class': 'spinner'
		});
		var spinnerIcon = $('<i/>', {
			'class': 'fa fa-spinner fa-spin'
		});
		if(this.options.spinnerIconURL.length) {
			spinnerIcon.css('background-image', 'url("'+this.options.spinnerIconURL+'")');
			spinnerIcon.addClass('image');
		}
		spinner.html(spinnerIcon);
		return spinner;
	},

	createCommentElement: function(commentModel) {

		// Comment container element
		var commentEl = $('<li/>', {
			'data-id': commentModel.id,
			'class': 'comment'
		}).data('model', commentModel);

		if(commentModel.createdByCurrentUser) commentEl.addClass('by-current-user');
		if(commentModel.createdByAdmin) commentEl.addClass('by-admin');

		// Child comments
		var childComments = $('<ul/>', {
			'class': 'child-comments'
		});

		// Comment wrapper
		var commentWrapper = this.createCommentWrapperElement(commentModel);

		commentEl.append(commentWrapper);
		if(commentModel.parent == null) commentEl.append(childComments);
		return commentEl;
	},

	createCommentWrapperElement: function(commentModel) {
		var commentWrapper = $('<div/>', {
			'class': 'comment-wrapper'
		});

		// Profile picture
		var profilePicture = this.createProfilePictureElement(commentModel.profilePictureURL);

		// Time
		var time = $('<time/>', {
			text: this.options.timeFormatter(commentModel.created),
			'data-original': commentModel.created
		});

		// Name
		var nameText = commentModel.createdByCurrentUser ? this.options.textFormatter(this.options.youText) : commentModel.fullname;
		var name = $('<div/>', {
			'class': 'name'
		});
		if(commentModel.profileURL) {
			var link = $('<a/>', {
				href: commentModel.profileURL,
				text: nameText
			});
			name.html(link);
		} else {
			name.text(nameText);
		}

		// Highlight name for own comments and admin
		if(commentModel.createdByCurrentUser || commentModel.createdByAdmin) name.addClass('highlight-font-bold');

		// Show reply-to name if parent of parent exists
		if(commentModel.parent) {
			var parent = this.commentsById[commentModel.parent];
			if(parent.parent) {
				var replyTo = $('<span/>', {
					'class': 'reply-to',
					text: parent.fullname
				});

				// reply icon
				var replyIcon = $('<i/>', {
					'class': 'fa fa-share'
				});
				if(this.options.replyIconURL.length) {
					replyIcon.css('background-image', 'url("'+this.options.replyIconURL+'")');
					replyIcon.addClass('image');
				}

				replyTo.prepend(replyIcon);
				name.append(replyTo);
			}
		}

		// New tag
		if(commentModel.isNew) {
			var newTag = $('<span/>', {
				'class': 'new highlight-background',
				text: this.options.newText
			});
			name.append(newTag);
		}

		// Wrapper
		var wrapper = $('<div/>', {
			'class': 'wrapper'
		});
		// Note Ý kiến chính thức
		var noteOpinion = $('<div/>', {
			'class': 'note-opinion',
			text: '(Ý kiến chính thức)'
		});
		// Content
		var content = $('<div/>', {
			'class': 'content'
		});

		// Case: attachment
		var isAttachment = commentModel.fileURL != undefined;
		if(isAttachment) {
			var format = null;
			var type = null;

			// Type and format
			if(commentModel.fileMimeType) {
				var mimeTypeParts = commentModel.fileMimeType.split('/');
				if(mimeTypeParts.length == 2) {
					format = mimeTypeParts[1];
					type = mimeTypeParts[0];
				}
			}

			// Attachment link
			var link = $('<a/>', {
				'class': 'attachment',
				href: commentModel.fileURL,
				target: '_blank'
			});
			
			// var link = $('<a/>', {
			// 		'class': 'attachment',
			// 		href: 'javascript:viewCommentFileAttach("' + commentModel.fileURL + '","' + commentModel.fileMimeType + '");'
			// });
			
			// Case: image preview
			if(type == 'image') {
				var image = $('<img/>', {
					src: commentModel.fileURL
				});
				link.html(image);
			// Case: video preview
			} else if(type == 'video') {
				var video = $('<video/>', {
					src: commentModel.fileURL,
					type: commentModel.fileMimeType,
					controls: 'controls'
				});
				link.html(video);

			// Case: icon and text
			} else {

				// Icon
				var availableIcons = ['archive', 'audio', 'code', 'excel', 'image', 'movie', 'pdf', 'photo',
					'picture', 'powerpoint', 'sound', 'video', 'word', 'zip'];
				
				var iconClass = 'fa fa-file-o';
				if(availableIcons.indexOf(format) > 0) {
					iconClass = 'fa fa-file-' + format + '-o';
				} else if(availableIcons.indexOf(type) > 0) {
					iconClass = 'fa fa-file-' + type + '-o';
				}

				var fileIcon = $('<i/>', {
					'class': iconClass
				});
				if(this.options.fileIconURL.length) {
					fileIcon.css('background-image', 'url("'+this.options.fileIconURL+'")');
					fileIcon.addClass('image');
				}
				// File name
				/**
				var parts = commentModel.fileURL.split('/');
				var fileName = parts[parts.length - 1];
				fileName = fileName.split('?')[0];
				fileName = decodeURIComponent(fileName);
				*/
				var fileName = commentModel.fileName;
				link.text(fileName);
				link.prepend(fileIcon);
			}
			content.html(link);
		// Case: regular comment
		} else {
			content.html(this.getFormattedCommentContent(commentModel));
		}

		// Edited timestamp
		if(commentModel.modified && commentModel.modified != commentModel.created) {
			var editedTime = this.options.timeFormatter(commentModel.modified);
			var edited = $('<time/>', {
				'class': 'edited',
				text: this.options.textFormatter(this.options.editedText) + ' ' + editedTime,
				'data-original': commentModel.modified
			});
			content.append(edited);
		}

		// Actions
		var actions = $('<span/>', {
			'class': 'actions'
		});

		// Reply
		var reply = $('<button/>', {
			'class': 'action reply',
			'type': 'button',
			text: this.options.textFormatter(this.options.replyText)
		});

		// Upvote icon
		var upvoteIcon = $('<i/>', {
			'class': 'fa fa-thumbs-up'
		});
		if(this.options.upvoteIconURL.length) {
			upvoteIcon.css('background-image', 'url("'+this.options.upvoteIconURL+'")');
			upvoteIcon.addClass('image');
		}

		// Upvotes
		var upvotes = this.createUpvoteElement(commentModel);

		// Append buttons for actions that are enabled
		if(this.options.enableReplying) actions.append(reply);
		if(this.options.enableUpvoting) actions.append(upvotes);

		if(commentModel.createdByCurrentUser || this.options.currentUserIsAdmin) {
			// Case: delete button for attachment and currentUser
			if(isAttachment && this.isAllowedToDelete(commentModel.id)) {
				var deleteButton = $('<button/>', {
					'class': 'action delete enabled',
					text: this.options.textFormatter(this.options.deleteText)
				});
				actions.append(deleteButton);

			// Case: edit button for regular comment
			} else if(!isAttachment && this.options.enableEditing) {
				var editButton = $('<button/>', {
					'class': 'action edit',
					text: this.options.textFormatter(this.options.editText)
				});
				actions.append(editButton);
				var deleteButton = $('<button/>', {
					'class': 'action delete enabled',
					text: this.options.textFormatter(this.options.deleteText)
				});
				actions.append(deleteButton);
			}
		}

		wrapper.append(content);
		if(commentModel.opinion == true) {
			wrapper.append(noteOpinion);
		}
		wrapper.append(actions);
		commentWrapper.append(profilePicture).append(time).append(name).append(wrapper);
		return commentWrapper;
	},

	createUpvoteElement: function(commentModel) {
		// Upvote icon
		var upvoteIcon = $('<i/>', {
			'class': 'fa fa-thumbs-up'
		});
		if(this.options.upvoteIconURL.length) {
			upvoteIcon.css('background-image', 'url("'+this.options.upvoteIconURL+'")');
			upvoteIcon.addClass('image');
		}

		// Upvotes
		var upvoteEl = $('<button/>', {
			'class': 'action upvote' + (commentModel.userHasUpvoted ? ' highlight-font' : '')
		}).append($('<span/>', {
			text: commentModel.upvoteCount,
			'class': 'upvote-count'
		})).append(upvoteIcon);

		return upvoteEl;
	},

	createTagElement: function(text, extraClasses, value) {
		var tagEl = $('<input/>', {
			'class': 'tag',
			'type': 'button',
			'data-role': 'none',
		});
		if(extraClasses) tagEl.addClass(extraClasses);
		tagEl.val(text);
		tagEl.attr('data-value', value);
		return tagEl;
	},

	reRenderComment: function(id) {
		var commentModel = this.commentsById[id];
		var commentElements = this.$el.find('li.comment[data-id="'+commentModel.id+'"]');

		var self = this;
		commentElements.each(function(index, commentEl) {
			var commentWrapper = self.createCommentWrapperElement(commentModel);
			$(commentEl).find('.comment-wrapper').first().replaceWith(commentWrapper);
		});
	},

	reRenderCommentActionBar: function(id) {
		var commentModel = this.commentsById[id];
		var commentElements = this.$el.find('li.comment[data-id="'+commentModel.id+'"]');

		var self = this;
		commentElements.each(function(index, commentEl) {
			var commentWrapper = self.createCommentWrapperElement(commentModel);
			$(commentEl).find('.actions').first().replaceWith(commentWrapper.find('.actions'));
		});
	},

	reRenderUpvotes: function(id) {
		var commentModel = this.commentsById[id];
		var commentElements = this.$el.find('li.comment[data-id="'+commentModel.id+'"]');

		var self = this;
		commentElements.each(function(index, commentEl) {
			var upvotes = self.createUpvoteElement(commentModel);
			$(commentEl).find('.upvote').first().replaceWith(upvotes);
		});
	},


	// Styling
	createCssDeclarations: function() {

		// Remove previous css-declarations
		$('head style.jquery-comments-css').remove();

		// Navigation underline
		this.createCss('.jquery-comments ul.navigation li.active:after {background: '
			+ this.options.highlightColor  + ' !important;',
			+'}');

		// Dropdown active element
		this.createCss('.jquery-comments ul.navigation ul.dropdown li.active {background: '
			+ this.options.highlightColor  + ' !important;',
			+'}');

		// Background highlight
		this.createCss('.jquery-comments .highlight-background {background: '
			+ this.options.highlightColor  + ' !important;',
			+'}');

		// Font highlight
		this.createCss('.jquery-comments .highlight-font {color: '
			+ this.options.highlightColor + ' !important;'
			+'}');
		this.createCss('.jquery-comments .highlight-font-bold {color: '
			+ this.options.highlightColor + ' !important;'
			+ 'font-weight: bold;'
			+'}');
	},

	createCss: function(css) {
		var styleEl = $('<style/>', {
			type: 'text/css',
			'class': 'jquery-comments-css',
			text: css
		});
		$('head').append(styleEl);
	},

	//
	getComments: function() {
		var self = this;
		let commentArr = Object.keys(this.commentsById).map(function(id){return self.commentsById[id]})
		let importantCmtArr = commentArr.filter(function(comment){return comment.opinion})
		let importantCmtUser = commentArr.filter(function(comment){return comment.opinion&&comment.createdByCurrentUser})
		let attachCmtArr = commentArr.filter(function(comment){return comment.fileURL != undefined})
		if (commentArr.length > 3) {
			$('.data-container[data-container=comments]').addClass('lessEl')
		} else {
			$('.data-container[data-container=comments]').addClass('fullEl')
		}
		// 
		if (importantCmtArr.length > 3) {
			$('.data-container[data-container=importantCmt]').addClass('lessEl')
		} else {
			$('.data-container[data-container=importantCmt]').addClass('fullEl')
		}
		if (importantCmtUser.length > 0) {
			$('.opinion').hide()
		} else {
			$('.opinion').show()
		}
		// 
		if (attachCmtArr.length > 3) {
			$('.data-container[data-container=attachments]').addClass('lessEl')
		} else {
			$('.data-container[data-container=attachments]').addClass('fullEl')
		}

		return Object.keys(this.commentsById).map(function(id){return self.commentsById[id]});
	},

	getUsers: function() {
		var self = this;
		return Object.keys(this.usersById).map(function(id){return self.usersById[id]});
	},

	getChildComments: function(parentId) {
		return this.getComments().filter(function(comment){return comment.parent == parentId});
	},

	getAttachments: function() {
		var self = this;
		return this.getComments().filter(function(comment){return comment.fileURL != undefined});
	},
	getImportantCmt: function() {
		var self = this;
		return this.getComments().filter(function(comment){return comment.opinion == true});
	},
	getOutermostParent: function(directParentId) {
		var parentId = directParentId;
		do {
			var parentComment = this.commentsById[parentId];
			parentId = parentComment.parent;
		} while(parentComment.parent != null);
		return parentComment;
	},

	createCommentJSON: function(textarea) {
		var time = new Date().toISOString();
		var commentJSON = {
			id: 'c' +  (this.getComments().length + 1),   // Temporary id
			parent: textarea.attr('data-parent') || null,
			created: time,
			modified: time,
			content: this.getTextareaContent(textarea),
			pings: this.getPings(textarea),
			fullname: this.options.textFormatter(this.options.youText),
			profilePictureURL: this.options.profilePictureURL,
			createdByCurrentUser: true,
			upvoteCount: 0,
			userHasUpvoted: false
		};
		return commentJSON;
	},

	isAllowedToDelete: function(commentId) {
		if(this.options.enableDeleting) {
			var isAllowedToDelete = true;
			if(!this.options.enableDeletingCommentWithReplies) {
				$(this.getComments()).each(function(index, comment) {
					if(comment.parent == commentId) isAllowedToDelete = false;
				});
			}
			return isAllowedToDelete;
		}
		return false;
	},

	setToggleAllButtonText: function(toggleAllButton, toggle) {
		var self = this;
		var textContainer = toggleAllButton.find('span.text');
		var caret = toggleAllButton.find('.caret');

		var showExpandingText = function() {
				var text = self.options.textFormatter(self.options.viewAllRepliesText);
				var replyCount = toggleAllButton.siblings('.comment').length;
				text = text.replace('__replyCount__', replyCount);
				textContainer.text(text);
		};

		var hideRepliesText = this.options.textFormatter(this.options.hideRepliesText);

		if(toggle) {
			// Toggle text
			if(textContainer.text() == hideRepliesText) {
				showExpandingText();
			} else {
				textContainer.text(hideRepliesText);
			}
			// Toggle direction of the caret
			caret.toggleClass('up');
		} else {
			// Update text if necessary
			if(textContainer.text() != hideRepliesText) {
				showExpandingText();
			}
		}
	},

	adjustTextareaHeight: function(textarea, focus) {
		var textareaBaseHeight = 2.2;
		var lineHeight = 1.45;

		var setRows = function(rows) {
			var height = textareaBaseHeight + (rows - 1) * lineHeight;
			textarea.css('height', height + 'em');
		};

		textarea = $(textarea);
		var rowCount = focus == true ? this.options.textareaRowsOnFocus : this.options.textareaRows;
		do {
			setRows(rowCount);
			rowCount++;
			var isAreaScrollable = textarea[0].scrollHeight > textarea.outerHeight();
			var maxRowsUsed = this.options.textareaMaxRows == false ?
				false : rowCount > this.options.textareaMaxRows;
		} while(isAreaScrollable && !maxRowsUsed);
	},

	clearTextarea: function(textarea) {
		textarea.empty().trigger('input');
	},

	getTextareaContent: function(textarea, humanReadable) {
		var textareaClone = textarea.clone();

		// Remove reply-to tag
		textareaClone.find('.reply-to.tag').remove();

		// Replace tags with text values
		textareaClone.find('.tag.hashtag').replaceWith(function(){
			return humanReadable ? $(this).val() : '#' + $(this).attr('data-value');
		});
		textareaClone.find('.tag.ping').replaceWith(function(){
			return humanReadable ? $(this).val() : '@' + $(this).attr('data-value');
		});

		var ce = $('<pre/>').html(textareaClone.html());
		ce.find('div, p, br').replaceWith(function() { return '\n' + this.innerHTML; });

		// Trim leading spaces
		var text = ce.text().replace(/^\s+/g, '');

		// Normalize spaces
		var text = this.normalizeSpaces(text);
		return text;
	},

	getFormattedCommentContent: function(commentModel, replaceNewLines) {
		var html = this.escape(commentModel.content);
		html = this.linkify(html);
		html = this.highlightTags(commentModel, html);
		if(replaceNewLines) html = html.replace(/(?:\n)/g, '<br>');
		return html;
	},

	getPings: function(textarea) {
		return $.map(textarea.find('.ping'), function(el){return parseInt($(el).attr('data-value'))});
	},

	moveCursorToEnd: function(el) {
		el = $(el)[0];

		// Trigger input to adjust size
		$(el).trigger('input');

		// Scroll to bottom
		$(el).scrollTop(el.scrollHeight);

		// Move cursor to end
		if (typeof window.getSelection != 'undefined' && typeof document.createRange != 'undefined') {
			var range = document.createRange();
			range.selectNodeContents(el);
			range.collapse(false);
			var sel = window.getSelection();
			sel.removeAllRanges();
			sel.addRange(range);
		} else if (typeof document.body.createTextRange != 'undefined') {
			var textRange = document.body.createTextRange();
			textRange.moveToElementText(el);
			textRange.collapse(false);
			textRange.select();
		}

		// Focus
		el.focus();
	},

	escape: function(inputText) {
		return $('<pre/>').text(this.normalizeSpaces(inputText)).html();
	},

	normalizeSpaces: function(inputText) {
		return inputText.replace(new RegExp('\u00a0', 'g'), ' ');   // Convert non-breaking spaces to reguar spaces
	},

	after: function(times, func) {
		var self = this;
		return function() {
			times--;
			if (times == 0) {
				return func.apply(self, arguments);
			}
		}
	},

	highlightTags: function(commentModel, html) {
		if(this.options.enableHashtags) html = this.highlightHashtags(commentModel, html);
		if(this.options.enablePinging) html = this.highlightPings(commentModel, html);
		return html;
	},

	highlightHashtags: function(commentModel, html) {
		var self = this;

		if(html.indexOf('#') != -1) {
			var __createTag = function(tag) {
				var tag = self.createTagElement('#' + tag, 'hashtag', tag);
				return tag[0].outerHTML;
			}

			var regex = /(^|\s)#([a-zäöüß\d-_]+)/gim;
			html = html.replace(regex, function($0, $1, $2){
				return $1 + __createTag($2);
			});
		}
		return html;
	},

	highlightPings: function(commentModel, html) {
		var self = this;

		if(html.indexOf('@') != -1) {

			var __createTag = function(user) {
				var tag = self.createTagElement('@' + user.fullname, 'ping', user.id);
				return tag[0].outerHTML;
			}

			var highlightedHtml = '';
			$(commentModel.pings).each(function(index, id) {
				if(id in self.usersById) {
					var user = self.usersById[id];
					var pingText = '@' + user.id;

					var endIndex = html.indexOf(pingText) + pingText.length;
					var current = html.slice(0, endIndex);
					highlightedHtml += current.replace(pingText, __createTag(user));

					html = html.slice(endIndex);
				}
			});
			highlightedHtml += html;
			return highlightedHtml;
		}
		return html;
	},

	linkify: function(inputText) {
		var replacedText, replacePattern1, replacePattern2, replacePattern3;

		// URLs starting with http://, https://, file:// or ftp://
		replacePattern1 = /(^|\s)((https?|ftp|file):\/\/[-A-Z0-9+&@#\/%?=~_|!:,.;]*[-A-Z0-9+&@#\/%=~_|])/gim;
		replacedText = inputText.replace(replacePattern1, '$1<a href="$2" target="_blank">$2</a>');

		// URLs starting with "www." (without // before it, or it'd re-link the ones done above).
		replacePattern2 = /(^|\s)(www\.[\S]+(\b|$))/gim;
		replacedText = replacedText.replace(replacePattern2, '$1<a href="http://$2" target="_blank">$2</a>');

		// Change email addresses to mailto:: links.
		replacePattern3 = /(^|\s)(([a-zA-Z0-9\-\_\.]+)@[a-zA-Z\_]+?(\.[a-zA-Z]{2,6})+)/gim;
		replacedText = replacedText.replace(replacePattern3, '$1<a href="mailto:$2">$2</a>');

		// If there are hrefs in the original text, let's split
		// the text up and only work on the parts that don't have urls yet.
		var count = inputText.match(/<a href/g) || [];

		if(count.length > 0){
			// Keep delimiter when splitting
			var splitInput = inputText.split(/(<\/a>)/g);
			for (var i = 0 ; i < splitInput.length ; i++){
				if(splitInput[i].match(/<a href/g) == null){
					splitInput[i] = splitInput[i].replace(replacePattern1, '<a href="$1" target="_blank">$1</a>').replace(replacePattern2, '$1<a href="http://$2" target="_blank">$2</a>').replace(replacePattern3, '<a href="mailto:$1">$1</a>');
				}
			}
			var combinedReplacedText = splitInput.join('');
			return combinedReplacedText;
		} else {
			return replacedText;
		}
	},

	waitUntil: function(condition, callback) {
		var self = this;

		if(condition()) {
			callback();
		} else {
			setTimeout(function() {
				self.waitUntil(condition, callback);
			}, 100);
		}
	},

	applyInternalMappings: function(commentJSON) {
		// Inverting field mappings
		var invertedMappings = {};
		var mappings = this.options.fieldMappings;
		for (var prop in mappings) {
			if(mappings.hasOwnProperty(prop)) {
				invertedMappings[mappings[prop]] = prop;
			}
		}

		return this.applyMappings(invertedMappings, commentJSON);
	},

	applyExternalMappings: function(commentJSON) {
		var mappings = this.options.fieldMappings;
		return this.applyMappings(mappings, commentJSON);
	},

	applyMappings: function(mappings, commentJSON) {
		var result = {};

		for(var key1 in commentJSON) {
			if(key1 in mappings) {
				var key2 = mappings[key1];
				result[key2] = commentJSON[key1];
			}
		}
		return result;
	}

	};

	$.fn.comments = function(options) {
		return this.each(function() {
			var comments = Object.create(Comments);
			$.data(this, 'comments', comments);
			comments.init(options || {}, this);
		});
	};
}));


/* eslint-disable */
/* WEBPACK VAR INJECTION */}.call(exports, __webpack_require__(36)))

/***/ }),

/***/ 695:
/***/ (function(module, exports, __webpack_require__) {

var __WEBPACK_AMD_DEFINE_FACTORY__, __WEBPACK_AMD_DEFINE_ARRAY__, __WEBPACK_AMD_DEFINE_RESULT__;(function (factory) {
  if (true) {
    // AMD. Register as an anonymous module.
    !(__WEBPACK_AMD_DEFINE_ARRAY__ = [__webpack_require__(36)], __WEBPACK_AMD_DEFINE_FACTORY__ = (factory),
				__WEBPACK_AMD_DEFINE_RESULT__ = (typeof __WEBPACK_AMD_DEFINE_FACTORY__ === 'function' ?
				(__WEBPACK_AMD_DEFINE_FACTORY__.apply(exports, __WEBPACK_AMD_DEFINE_ARRAY__)) : __WEBPACK_AMD_DEFINE_FACTORY__),
				__WEBPACK_AMD_DEFINE_RESULT__ !== undefined && (module.exports = __WEBPACK_AMD_DEFINE_RESULT__));
  } else if (typeof module === "object" && module.exports) {
    var $ = require('jquery');
    module.exports = factory($);
  } else {
    // Browser globals
    factory(jQuery);
  }
}(function (jQuery) {

/*!
 * jQuery.textcomplete
 *
 * Repository: https://github.com/yuku-t/jquery-textcomplete
 * License:    MIT (https://github.com/yuku-t/jquery-textcomplete/blob/master/LICENSE)
 * Author:     Yuku Takahashi
 */

if (typeof jQuery === 'undefined') {
  throw new Error('jQuery.textcomplete requires jQuery');
}

+function ($) {
  'use strict';

  var warn = function (message) {
    if (console.warn) { console.warn(message); }
  };

  var id = 1;

  $.fn.textcomplete = function (strategies, option) {
    var args = Array.prototype.slice.call(arguments);
    return this.each(function () {
      var self = this;
      var $this = $(this);
      var completer = $this.data('textComplete');
      if (!completer) {
        option || (option = {});
        option._oid = id++;  // unique object id
        completer = new $.fn.textcomplete.Completer(this, option);
        $this.data('textComplete', completer);
      }
      if (typeof strategies === 'string') {
        if (!completer) return;
        args.shift()
        completer[strategies].apply(completer, args);
        if (strategies === 'destroy') {
          $this.removeData('textComplete');
        }
      } else {
        // For backward compatibility.
        // TODO: Remove at v0.4
        $.each(strategies, function (obj) {
          $.each(['header', 'footer', 'placement', 'maxCount'], function (name) {
            if (obj[name]) {
              completer.option[name] = obj[name];
              warn(name + 'as a strategy param is deprecated. Use option.');
              delete obj[name];
            }
          });
        });
        completer.register($.fn.textcomplete.Strategy.parse(strategies, {
          el: self,
          $el: $this
        }));
      }
    });
  };

}(jQuery);

+function ($) {
  'use strict';

  // Exclusive execution control utility.
  //
  // func - The function to be locked. It is executed with a function named
  //        `free` as the first argument. Once it is called, additional
  //        execution are ignored until the free is invoked. Then the last
  //        ignored execution will be replayed immediately.
  //
  // Examples
  //
  //   var lockedFunc = lock(function (free) {
  //     setTimeout(function { free(); }, 1000); // It will be free in 1 sec.
  //     console.log('Hello, world');
  //   });
  //   lockedFunc();  // => 'Hello, world'
  //   lockedFunc();  // none
  //   lockedFunc();  // none
  //   // 1 sec past then
  //   // => 'Hello, world'
  //   lockedFunc();  // => 'Hello, world'
  //   lockedFunc();  // none
  //
  // Returns a wrapped function.
  var lock = function (func) {
    var locked, queuedArgsToReplay;

    return function () {
      // Convert arguments into a real array.
      var args = Array.prototype.slice.call(arguments);
      if (locked) {
        // Keep a copy of this argument list to replay later.
        // OK to overwrite a previous value because we only replay
        // the last one.
        queuedArgsToReplay = args;
        return;
      }
      locked = true;
      var self = this;
      args.unshift(function replayOrFree() {
        if (queuedArgsToReplay) {
          // Other request(s) arrived while we were locked.
          // Now that the lock is becoming available, replay
          // the latest such request, then call back here to
          // unlock (or replay another request that arrived
          // while this one was in flight).
          var replayArgs = queuedArgsToReplay;
          queuedArgsToReplay = undefined;
          replayArgs.unshift(replayOrFree);
          func.apply(self, replayArgs);
        } else {
          locked = false;
        }
      });
      func.apply(this, args);
    };
  };

  var isString = function (obj) {
    return Object.prototype.toString.call(obj) === '[object String]';
  };

  var uniqueId = 0;
  var initializedEditors = [];

  function Completer(element, option) {
    this.$el        = $(element);
    this.id         = 'textcomplete' + uniqueId++;
    this.strategies = [];
    this.views      = [];
    this.option     = $.extend({}, Completer.defaults, option);

    if (!this.$el.is('input[type=text]') && !this.$el.is('input[type=search]') && !this.$el.is('textarea') && !element.isContentEditable && element.contentEditable != 'true') {
      throw new Error('textcomplete must be called on a Textarea or a ContentEditable.');
    }

    // use ownerDocument to fix iframe / IE issues
    if (element === element.ownerDocument.activeElement) {
      // element has already been focused. Initialize view objects immediately.
      this.initialize()
    } else {
      // Initialize view objects lazily.
      var self = this;
      this.$el.one('focus.' + this.id, function () { self.initialize(); });

      // Special handling for CKEditor: lazy init on instance load
      if ((!this.option.adapter || this.option.adapter == 'CKEditor') && typeof CKEDITOR != 'undefined' && (this.$el.is('textarea'))) {
        CKEDITOR.on("instanceReady", function(event) { //For multiple ckeditors on one page: this needs to be executed each time a ckeditor-instance is ready.

          if($.inArray(event.editor.id, initializedEditors) == -1) { //For multiple ckeditors on one page: focus-eventhandler should only be added once for every editor.
            initializedEditors.push(event.editor.id);
			
            event.editor.on("focus", function(event2) {
				//replace the element with the Iframe element and flag it as CKEditor
				self.$el = $(event.editor.editable().$);
				if (!self.option.adapter) {
					self.option.adapter = $.fn.textcomplete['CKEditor'];
				}
				self.option.ckeditor_instance = event.editor; //For multiple ckeditors on one page: in the old code this was not executed when adapter was alread set. So we were ALWAYS working with the FIRST instance.
              	self.initialize();
            });
          }
        });
      }
    }
  }

  Completer.defaults = {
    appendTo: 'body',
    className: '',  // deprecated option
    dropdownClassName: 'dropdown-menu textcomplete-dropdown',
    maxCount: 10,
    zIndex: '100',
    rightEdgeOffset: 30
  };

  $.extend(Completer.prototype, {
    // Public properties
    // -----------------

    id:         null,
    option:     null,
    strategies: null,
    adapter:    null,
    dropdown:   null,
    $el:        null,
    $iframe:    null,

    // Public methods
    // --------------

    initialize: function () {
      var element = this.$el.get(0);
      
      // check if we are in an iframe
      // we need to alter positioning logic if using an iframe
      if (this.$el.prop('ownerDocument') !== document && window.frames.length) {
        for (var iframeIndex = 0; iframeIndex < window.frames.length; iframeIndex++) {
          if (this.$el.prop('ownerDocument') === window.frames[iframeIndex].document) {
            this.$iframe = $(window.frames[iframeIndex].frameElement);
            break;
          }
        }
      }
      
      
      // Initialize view objects.
      this.dropdown = new $.fn.textcomplete.Dropdown(element, this, this.option);
      var Adapter, viewName;
      if (this.option.adapter) {
        Adapter = this.option.adapter;
      } else {
        if (this.$el.is('textarea') || this.$el.is('input[type=text]') || this.$el.is('input[type=search]')) {
          viewName = typeof element.selectionEnd === 'number' ? 'Textarea' : 'IETextarea';
        } else {
          viewName = 'ContentEditable';
        }
        Adapter = $.fn.textcomplete[viewName];
      }
      this.adapter = new Adapter(element, this, this.option);
    },

    destroy: function () {
      this.$el.off('.' + this.id);
      if (this.adapter) {
        this.adapter.destroy();
      }
      if (this.dropdown) {
        this.dropdown.destroy();
      }
      this.$el = this.adapter = this.dropdown = null;
    },

    deactivate: function () {
      if (this.dropdown) {
        this.dropdown.deactivate();
      }
    },

    // Invoke textcomplete.
    trigger: function (text, skipUnchangedTerm) {
      if (!this.dropdown) { this.initialize(); }
      text != null || (text = this.adapter.getTextFromHeadToCaret());
      var searchQuery = this._extractSearchQuery(text);
      if (searchQuery.length) {
        var term = searchQuery[1];
        // Ignore shift-key, ctrl-key and so on.
        if (skipUnchangedTerm && this._term === term && term !== "") { return; }
        this._term = term;
        this._search.apply(this, searchQuery);
      } else {
        this._term = null;
        this.dropdown.deactivate();
      }
    },

    fire: function (eventName) {
      var args = Array.prototype.slice.call(arguments, 1);
      this.$el.trigger(eventName, args);
      return this;
    },

    register: function (strategies) {
      Array.prototype.push.apply(this.strategies, strategies);
    },

    // Insert the value into adapter view. It is called when the dropdown is clicked
    // or selected.
    //
    // value    - The selected element of the array callbacked from search func.
    // strategy - The Strategy object.
    // e        - Click or keydown event object.
    select: function (value, strategy, e) {
      this._term = null;
      this.adapter.select(value, strategy, e);
      this.fire('change').fire('textComplete:select', value, strategy);
      this.adapter.focus();
    },

    // Private properties
    // ------------------

    _clearAtNext: true,
    _term:        null,

    // Private methods
    // ---------------

    // Parse the given text and extract the first matching strategy.
    //
    // Returns an array including the strategy, the query term and the match
    // object if the text matches an strategy; otherwise returns an empty array.
    _extractSearchQuery: function (text) {
      for (var i = 0; i < this.strategies.length; i++) {
        var strategy = this.strategies[i];
        var context = strategy.context(text);
        if (context || context === '') {
          var matchRegexp = $.isFunction(strategy.match) ? strategy.match(text) : strategy.match;
          if (isString(context)) { text = context; }
          var match = text.match(matchRegexp);
          if (match) { return [strategy, match[strategy.index], match]; }
        }
      }
      return []
    },

    // Call the search method of selected strategy..
    _search: lock(function (free, strategy, term, match) {
      var self = this;
      strategy.search(term, function (data, stillSearching) {
        if (!self.dropdown.shown) {
          self.dropdown.activate();
        }
        if (self._clearAtNext) {
          // The first callback in the current lock.
          self.dropdown.clear();
          self._clearAtNext = false;
        }
        self.dropdown.setPosition(self.adapter.getCaretPosition());
        self.dropdown.render(self._zip(data, strategy, term));
        if (!stillSearching) {
          // The last callback in the current lock.
          free();
          self._clearAtNext = true; // Call dropdown.clear at the next time.
        }
      }, match);
    }),

    // Build a parameter for Dropdown#render.
    //
    // Examples
    //
    //  this._zip(['a', 'b'], 's');
    //  //=> [{ value: 'a', strategy: 's' }, { value: 'b', strategy: 's' }]
    _zip: function (data, strategy, term) {
      return $.map(data, function (value) {
        return { value: value, strategy: strategy, term: term };
      });
    }
  });

  $.fn.textcomplete.Completer = Completer;
}(jQuery);

+function ($) {
  'use strict';

  var $window = $(window);

  var include = function (zippedData, datum) {
    var i, elem;
    var idProperty = datum.strategy.idProperty
    for (i = 0; i < zippedData.length; i++) {
      elem = zippedData[i];
      if (elem.strategy !== datum.strategy) continue;
      if (idProperty) {
        if (elem.value[idProperty] === datum.value[idProperty]) return true;
      } else {
        if (elem.value === datum.value) return true;
      }
    }
    return false;
  };

  var dropdownViews = {};
  $(document).on('click', function (e) {
    var id = e.originalEvent && e.originalEvent.keepTextCompleteDropdown;
    $.each(dropdownViews, function (key, view) {
      if (key !== id) { view.deactivate(); }
    });
  });

  var commands = {
    SKIP_DEFAULT: 0,
    KEY_UP: 1,
    KEY_DOWN: 2,
    KEY_ENTER: 3,
    KEY_PAGEUP: 4,
    KEY_PAGEDOWN: 5,
    KEY_ESCAPE: 6
  };

  // Dropdown view
  // =============

  // Construct Dropdown object.
  //
  // element - Textarea or contenteditable element.
  function Dropdown(element, completer, option) {
    this.$el       = Dropdown.createElement(option);
    this.completer = completer;
    this.id        = completer.id + 'dropdown';
    this._data     = []; // zipped data.
    this.$inputEl  = $(element);
    this.option    = option;

    // Override setPosition method.
    if (option.listPosition) { this.setPosition = option.listPosition; }
    if (option.height) { this.$el.height(option.height); }
    var self = this;
    $.each(['maxCount', 'placement', 'footer', 'header', 'noResultsMessage', 'className'], function (_i, name) {
      if (option[name] != null) { self[name] = option[name]; }
    });
    this._bindEvents(element);
    dropdownViews[this.id] = this;
  }

  $.extend(Dropdown, {
    // Class methods
    // -------------

    createElement: function (option) {
      var $parent = option.appendTo;
      if (!($parent instanceof $)) { $parent = $($parent); }
      var $el = $('<ul></ul>')
        .addClass(option.dropdownClassName)
        .attr('id', 'textcomplete-dropdown-' + option._oid)
        .css({
          display: 'none',
          left: 0,
          position: 'absolute',
          zIndex: option.zIndex
        })
        .appendTo($parent);
      return $el;
    }
  });

  $.extend(Dropdown.prototype, {
    // Public properties
    // -----------------

    $el:       null,  // jQuery object of ul.dropdown-menu element.
    $inputEl:  null,  // jQuery object of target textarea.
    completer: null,
    footer:    null,
    header:    null,
    id:        null,
    maxCount:  null,
    placement: '',
    shown:     false,
    data:      [],     // Shown zipped data.
    className: '',

    // Public methods
    // --------------

    destroy: function () {
      // Don't remove $el because it may be shared by several textcompletes.
      this.deactivate();

      this.$el.off('.' + this.id);
      this.$inputEl.off('.' + this.id);
      this.clear();
      this.$el.remove();
      this.$el = this.$inputEl = this.completer = null;
      delete dropdownViews[this.id]
    },

    render: function (zippedData) {
      var contentsHtml = this._buildContents(zippedData);
      var unzippedData = $.map(zippedData, function (d) { return d.value; });
      if (zippedData.length) {
        var strategy = zippedData[0].strategy;
        if (strategy.id) {
          this.$el.attr('data-strategy', strategy.id);
        } else {
          this.$el.removeAttr('data-strategy');
        }
        this._renderHeader(unzippedData);
        this._renderFooter(unzippedData);
        if (contentsHtml) {
          this._renderContents(contentsHtml);
          this._fitToBottom();
          this._fitToRight();
          this._activateIndexedItem();
        }
        this._setScroll();
      } else if (this.noResultsMessage) {
        this._renderNoResultsMessage(unzippedData);
      } else if (this.shown) {
        this.deactivate();
      }
    },

    setPosition: function (pos) {
      // Make the dropdown fixed if the input is also fixed
      // This can't be done during init, as textcomplete may be used on multiple elements on the same page
      // Because the same dropdown is reused behind the scenes, we need to recheck every time the dropdown is showed
      var position = 'absolute';
      // Check if input or one of its parents has positioning we need to care about
      this.$inputEl.add(this.$inputEl.parents()).each(function() {
        if($(this).css('position') === 'absolute') // The element has absolute positioning, so it's all OK
          return false;
        if($(this).css('position') === 'fixed') {
          pos.top -= $window.scrollTop();
          pos.left -= $window.scrollLeft();
          position = 'fixed';
          return false;
        }
      });
      this.$el.css(this._applyPlacement(pos));
      this.$el.css({ position: position }); // Update positioning

      return this;
    },

    clear: function () {
      this.$el.html('');
      this.data = [];
      this._index = 0;
      this._$header = this._$footer = this._$noResultsMessage = null;
    },

    activate: function () {
      if (!this.shown) {
        this.clear();
        this.$el.show();
        if (this.className) { this.$el.addClass(this.className); }
        this.completer.fire('textComplete:show');
        this.shown = true;
      }
      return this;
    },

    deactivate: function () {
      if (this.shown) {
        this.$el.hide();
        if (this.className) { this.$el.removeClass(this.className); }
        this.completer.fire('textComplete:hide');
        this.shown = false;
      }
      return this;
    },

    isUp: function (e) {
      return e.keyCode === 38 || (e.ctrlKey && e.keyCode === 80);  // UP, Ctrl-P
    },

    isDown: function (e) {
      return e.keyCode === 40 || (e.ctrlKey && e.keyCode === 78);  // DOWN, Ctrl-N
    },

    isEnter: function (e) {
      var modifiers = e.ctrlKey || e.altKey || e.metaKey || e.shiftKey;
      return !modifiers && (e.keyCode === 13 || e.keyCode === 9 || (this.option.completeOnSpace === true && e.keyCode === 32))  // ENTER, TAB
    },

    isPageup: function (e) {
      return e.keyCode === 33;  // PAGEUP
    },

    isPagedown: function (e) {
      return e.keyCode === 34;  // PAGEDOWN
    },

    isEscape: function (e) {
      return e.keyCode === 27;  // ESCAPE
    },

    // Private properties
    // ------------------

    _data:    null,  // Currently shown zipped data.
    _index:   null,
    _$header: null,
    _$noResultsMessage: null,
    _$footer: null,

    // Private methods
    // ---------------

    _bindEvents: function () {
      this.$el.on('mousedown.' + this.id, '.textcomplete-item', $.proxy(this._onClick, this));
      this.$el.on('touchstart.' + this.id, '.textcomplete-item', $.proxy(this._onClick, this));
      this.$el.on('mouseover.' + this.id, '.textcomplete-item', $.proxy(this._onMouseover, this));
      this.$inputEl.on('keydown.' + this.id, $.proxy(this._onKeydown, this));
    },

    _onClick: function (e) {
      var $el = $(e.target);
      e.preventDefault();
      e.originalEvent.keepTextCompleteDropdown = this.id;
      if (!$el.hasClass('textcomplete-item')) {
        $el = $el.closest('.textcomplete-item');
      }
      var datum = this.data[parseInt($el.data('index'), 10)];
      this.completer.select(datum.value, datum.strategy, e);
      var self = this;
      // Deactive at next tick to allow other event handlers to know whether
      // the dropdown has been shown or not.
      setTimeout(function () {
        self.deactivate();
        if (e.type === 'touchstart') {
          self.$inputEl.focus();
        }
      }, 0);
    },

    // Activate hovered item.
    _onMouseover: function (e) {
      var $el = $(e.target);
      e.preventDefault();
      if (!$el.hasClass('textcomplete-item')) {
        $el = $el.closest('.textcomplete-item');
      }
      this._index = parseInt($el.data('index'), 10);
      this._activateIndexedItem();
    },

    _onKeydown: function (e) {
      if (!this.shown) { return; }

      var command;

      if ($.isFunction(this.option.onKeydown)) {
        command = this.option.onKeydown(e, commands);
      }

      if (command == null) {
        command = this._defaultKeydown(e);
      }

      switch (command) {
        case commands.KEY_UP:
          e.preventDefault();
          this._up();
          break;
        case commands.KEY_DOWN:
          e.preventDefault();
          this._down();
          break;
        case commands.KEY_ENTER:
          e.preventDefault();
          this._enter(e);
          break;
        case commands.KEY_PAGEUP:
          e.preventDefault();
          this._pageup();
          break;
        case commands.KEY_PAGEDOWN:
          e.preventDefault();
          this._pagedown();
          break;
        case commands.KEY_ESCAPE:
          e.preventDefault();
          this.deactivate();
          break;
      }
    },

    _defaultKeydown: function (e) {
      if (this.isUp(e)) {
        return commands.KEY_UP;
      } else if (this.isDown(e)) {
        return commands.KEY_DOWN;
      } else if (this.isEnter(e)) {
        return commands.KEY_ENTER;
      } else if (this.isPageup(e)) {
        return commands.KEY_PAGEUP;
      } else if (this.isPagedown(e)) {
        return commands.KEY_PAGEDOWN;
      } else if (this.isEscape(e)) {
        return commands.KEY_ESCAPE;
      }
    },

    _up: function () {
      if (this._index === 0) {
        this._index = this.data.length - 1;
      } else {
        this._index -= 1;
      }
      this._activateIndexedItem();
      this._setScroll();
    },

    _down: function () {
      if (this._index === this.data.length - 1) {
        this._index = 0;
      } else {
        this._index += 1;
      }
      this._activateIndexedItem();
      this._setScroll();
    },

    _enter: function (e) {
      var datum = this.data[parseInt(this._getActiveElement().data('index'), 10)];
      this.completer.select(datum.value, datum.strategy, e);
      this.deactivate();
    },

    _pageup: function () {
      var target = 0;
      var threshold = this._getActiveElement().position().top - this.$el.innerHeight();
      this.$el.children().each(function (i) {
        if ($(this).position().top + $(this).outerHeight() > threshold) {
          target = i;
          return false;
        }
      });
      this._index = target;
      this._activateIndexedItem();
      this._setScroll();
    },

    _pagedown: function () {
      var target = this.data.length - 1;
      var threshold = this._getActiveElement().position().top + this.$el.innerHeight();
      this.$el.children().each(function (i) {
        if ($(this).position().top > threshold) {
          target = i;
          return false
        }
      });
      this._index = target;
      this._activateIndexedItem();
      this._setScroll();
    },

    _activateIndexedItem: function () {
      this.$el.find('.textcomplete-item.active').removeClass('active');
      this._getActiveElement().addClass('active');
    },

    _getActiveElement: function () {
      return this.$el.children('.textcomplete-item:nth(' + this._index + ')');
    },

    _setScroll: function () {
      var $activeEl = this._getActiveElement();
      var itemTop = $activeEl.position().top;
      var itemHeight = $activeEl.outerHeight();
      var visibleHeight = this.$el.innerHeight();
      var visibleTop = this.$el.scrollTop();
      if (this._index === 0 || this._index == this.data.length - 1 || itemTop < 0) {
        this.$el.scrollTop(itemTop + visibleTop);
      } else if (itemTop + itemHeight > visibleHeight) {
        this.$el.scrollTop(itemTop + itemHeight + visibleTop - visibleHeight);
      }
    },

    _buildContents: function (zippedData) {
      var datum, i, index;
      var html = '';
      for (i = 0; i < zippedData.length; i++) {
        if (this.data.length === this.maxCount) break;
        datum = zippedData[i];
        if (include(this.data, datum)) { continue; }
        index = this.data.length;
        this.data.push(datum);
        html += '<li class="textcomplete-item" data-index="' + index + '"><a>';
        html +=   datum.strategy.template(datum.value, datum.term);
        html += '</a></li>';
      }
      return html;
    },

    _renderHeader: function (unzippedData) {
      if (this.header) {
        if (!this._$header) {
          this._$header = $('<li class="textcomplete-header"></li>').prependTo(this.$el);
        }
        var html = $.isFunction(this.header) ? this.header(unzippedData) : this.header;
        this._$header.html(html);
      }
    },

    _renderFooter: function (unzippedData) {
      if (this.footer) {
        if (!this._$footer) {
          this._$footer = $('<li class="textcomplete-footer"></li>').appendTo(this.$el);
        }
        var html = $.isFunction(this.footer) ? this.footer(unzippedData) : this.footer;
        this._$footer.html(html);
      }
    },

    _renderNoResultsMessage: function (unzippedData) {
      if (this.noResultsMessage) {
        if (!this._$noResultsMessage) {
          this._$noResultsMessage = $('<li class="textcomplete-no-results-message"></li>').appendTo(this.$el);
        }
        var html = $.isFunction(this.noResultsMessage) ? this.noResultsMessage(unzippedData) : this.noResultsMessage;
        this._$noResultsMessage.html(html);
      }
    },

    _renderContents: function (html) {
      if (this._$footer) {
        this._$footer.before(html);
      } else {
        this.$el.append(html);
      }
    },

    _fitToBottom: function() {
      var windowScrollBottom = $window.scrollTop() + $window.height();
      var height = this.$el.height();
      if ((this.$el.position().top + height) > windowScrollBottom) {
        // only do this if we are not in an iframe
        if (!this.completer.$iframe) {
          this.$el.offset({top: windowScrollBottom - height});
        }
      }
    },

    _fitToRight: function() {
      // We don't know how wide our content is until the browser positions us, and at that point it clips us
      // to the document width so we don't know if we would have overrun it. As a heuristic to avoid that clipping
      // (which makes our elements wrap onto the next line and corrupt the next item), if we're close to the right
      // edge, move left. We don't know how far to move left, so just keep nudging a bit.
      var tolerance = this.option.rightEdgeOffset; // pixels. Make wider than vertical scrollbar because we might not be able to use that space.
      var lastOffset = this.$el.offset().left, offset;
      var width = this.$el.width();
      var maxLeft = $window.width() - tolerance;
      while (lastOffset + width > maxLeft) {
        this.$el.offset({left: lastOffset - tolerance});
        offset = this.$el.offset().left;
        if (offset >= lastOffset) { break; }
        lastOffset = offset;
      }
    },

    _applyPlacement: function (position) {
      // If the 'placement' option set to 'top', move the position above the element.
      if (this.placement.indexOf('top') !== -1) {
        // Overwrite the position object to set the 'bottom' property instead of the top.
        position = {
          top: 'auto',
          bottom: this.$el.parent().height() - position.top + position.lineHeight,
          left: position.left
        };
      } else {
        position.bottom = 'auto';
        delete position.lineHeight;
      }
      if (this.placement.indexOf('absleft') !== -1) {
        position.left = 0;
      } else if (this.placement.indexOf('absright') !== -1) {
        position.right = 0;
        position.left = 'auto';
      }
      return position;
    }
  });

  $.fn.textcomplete.Dropdown = Dropdown;
  $.extend($.fn.textcomplete, commands);
}(jQuery);

+function ($) {
  'use strict';

  // Memoize a search function.
  var memoize = function (func) {
    var memo = {};
    return function (term, callback) {
      if (memo[term]) {
        callback(memo[term]);
      } else {
        func.call(this, term, function (data) {
          memo[term] = (memo[term] || []).concat(data);
          callback.apply(null, arguments);
        });
      }
    };
  };

  function Strategy(options) {
    $.extend(this, options);
    if (this.cache) { this.search = memoize(this.search); }
  }

  Strategy.parse = function (strategiesArray, params) {
    return $.map(strategiesArray, function (strategy) {
      var strategyObj = new Strategy(strategy);
      strategyObj.el = params.el;
      strategyObj.$el = params.$el;
      return strategyObj;
    });
  };

  $.extend(Strategy.prototype, {
    // Public properties
    // -----------------

    // Required
    match:      null,
    replace:    null,
    search:     null,

    // Optional
    id:         null,
    cache:      false,
    context:    function () { return true; },
    index:      2,
    template:   function (obj) { return obj; },
    idProperty: null
  });

  $.fn.textcomplete.Strategy = Strategy;

}(jQuery);

+function ($) {
  'use strict';

  var now = Date.now || function () { return new Date().getTime(); };

  // Returns a function, that, as long as it continues to be invoked, will not
  // be triggered. The function will be called after it stops being called for
  // `wait` msec.
  //
  // This utility function was originally implemented at Underscore.js.
  var debounce = function (func, wait) {
    var timeout, args, context, timestamp, result;
    var later = function () {
      var last = now() - timestamp;
      if (last < wait) {
        timeout = setTimeout(later, wait - last);
      } else {
        timeout = null;
        result = func.apply(context, args);
        context = args = null;
      }
    };

    return function () {
      context = this;
      args = arguments;
      timestamp = now();
      if (!timeout) {
        timeout = setTimeout(later, wait);
      }
      return result;
    };
  };

  function Adapter () {}

  $.extend(Adapter.prototype, {
    // Public properties
    // -----------------

    id:        null, // Identity.
    completer: null, // Completer object which creates it.
    el:        null, // Textarea element.
    $el:       null, // jQuery object of the textarea.
    option:    null,

    // Public methods
    // --------------

    initialize: function (element, completer, option) {
      this.el        = element;
      this.$el       = $(element);
      this.id        = completer.id + this.constructor.name;
      this.completer = completer;
      this.option    = option;

      if (this.option.debounce) {
        this._onKeyup = debounce(this._onKeyup, this.option.debounce);
      }

      this._bindEvents();
    },

    destroy: function () {
      this.$el.off('.' + this.id); // Remove all event handlers.
      this.$el = this.el = this.completer = null;
    },

    // Update the element with the given value and strategy.
    //
    // value    - The selected object. It is one of the item of the array
    //            which was callbacked from the search function.
    // strategy - The Strategy associated with the selected value.
    select: function (/* value, strategy */) {
      throw new Error('Not implemented');
    },

    // Returns the caret's relative coordinates from body's left top corner.
    getCaretPosition: function () {
      var position = this._getCaretRelativePosition();
      var offset = this.$el.offset();

      // Calculate the left top corner of `this.option.appendTo` element.
      var $parent = this.option.appendTo;
      if ($parent) {
         if (!($parent instanceof $)) { $parent = $($parent); }
         var parentOffset = $parent.offsetParent().offset();
         offset.top -= parentOffset.top;
         offset.left -= parentOffset.left;
      }

      position.top += offset.top;
      position.left += offset.left;
      return position;
    },

    // Focus on the element.
    focus: function () {
      this.$el.focus();
    },

    // Private methods
    // ---------------

    _bindEvents: function () {
      this.$el.on('keyup.' + this.id, $.proxy(this._onKeyup, this));
    },

    _onKeyup: function (e) {
      if (this._skipSearch(e)) { return; }
      this.completer.trigger(this.getTextFromHeadToCaret(), true);
    },

    // Suppress searching if it returns true.
    _skipSearch: function (clickEvent) {
      switch (clickEvent.keyCode) {
        case 9:  // TAB
        case 13: // ENTER
        case 16: // SHIFT
        case 17: // CTRL
        case 18: // ALT
        case 33: // PAGEUP
        case 34: // PAGEDOWN
        case 40: // DOWN
        case 38: // UP
        case 27: // ESC
          return true;
      }
      if (clickEvent.ctrlKey) switch (clickEvent.keyCode) {
        case 78: // Ctrl-N
        case 80: // Ctrl-P
          return true;
      }
    }
  });

  $.fn.textcomplete.Adapter = Adapter;
}(jQuery);

+function ($) {
  'use strict';

  // Textarea adapter
  // ================
  //
  // Managing a textarea. It doesn't know a Dropdown.
  function Textarea(element, completer, option) {
    this.initialize(element, completer, option);
  }

  $.extend(Textarea.prototype, $.fn.textcomplete.Adapter.prototype, {
    // Public methods
    // --------------

    // Update the textarea with the given value and strategy.
    select: function (value, strategy, e) {
      var pre = this.getTextFromHeadToCaret();
      var post = this.el.value.substring(this.el.selectionEnd);
      var newSubstr = strategy.replace(value, e);
      var regExp;
      if (typeof newSubstr !== 'undefined') {
        if ($.isArray(newSubstr)) {
          post = newSubstr[1] + post;
          newSubstr = newSubstr[0];
        }
        regExp = $.isFunction(strategy.match) ? strategy.match(pre) : strategy.match;
        pre = pre.replace(regExp, newSubstr);
        this.$el.val(pre + post);
        this.el.selectionStart = this.el.selectionEnd = pre.length;
      }
    },

    getTextFromHeadToCaret: function () {
      return this.el.value.substring(0, this.el.selectionEnd);
    },

    // Private methods
    // ---------------

    _getCaretRelativePosition: function () {
      var p = $.fn.textcomplete.getCaretCoordinates(this.el, this.el.selectionStart);
      return {
        top: p.top + this._calculateLineHeight() - this.$el.scrollTop(),
        left: p.left - this.$el.scrollLeft(),
        lineHeight: this._calculateLineHeight()
      };
    },

    _calculateLineHeight: function () {
      var lineHeight = parseInt(this.$el.css('line-height'), 10);
      if (isNaN(lineHeight)) {
        // http://stackoverflow.com/a/4515470/1297336
        var parentNode = this.el.parentNode;
        var temp = document.createElement(this.el.nodeName);
        var style = this.el.style;
        temp.setAttribute(
          'style',
          'margin:0px;padding:0px;font-family:' + style.fontFamily + ';font-size:' + style.fontSize
        );
        temp.innerHTML = 'test';
        parentNode.appendChild(temp);
        lineHeight = temp.clientHeight;
        parentNode.removeChild(temp);
      }
      return lineHeight;
    }
  });

  $.fn.textcomplete.Textarea = Textarea;
}(jQuery);

+function ($) {
  'use strict';

  var sentinelChar = '吶';

  function IETextarea(element, completer, option) {
    this.initialize(element, completer, option);
    $('<span>' + sentinelChar + '</span>').css({
      position: 'absolute',
      top: -9999,
      left: -9999
    }).insertBefore(element);
  }

  $.extend(IETextarea.prototype, $.fn.textcomplete.Textarea.prototype, {
    // Public methods
    // --------------

    select: function (value, strategy, e) {
      var pre = this.getTextFromHeadToCaret();
      var post = this.el.value.substring(pre.length);
      var newSubstr = strategy.replace(value, e);
      var regExp;
      if (typeof newSubstr !== 'undefined') {
        if ($.isArray(newSubstr)) {
          post = newSubstr[1] + post;
          newSubstr = newSubstr[0];
        }
        regExp = $.isFunction(strategy.match) ? strategy.match(pre) : strategy.match;
        pre = pre.replace(regExp, newSubstr);
        this.$el.val(pre + post);
        this.el.focus();
        var range = this.el.createTextRange();
        range.collapse(true);
        range.moveEnd('character', pre.length);
        range.moveStart('character', pre.length);
        range.select();
      }
    },

    getTextFromHeadToCaret: function () {
      this.el.focus();
      var range = document.selection.createRange();
      range.moveStart('character', -this.el.value.length);
      var arr = range.text.split(sentinelChar)
      return arr.length === 1 ? arr[0] : arr[1];
    }
  });

  $.fn.textcomplete.IETextarea = IETextarea;
}(jQuery);

// NOTE: TextComplete plugin has contenteditable support but it does not work
//       fine especially on old IEs.
//       Any pull requests are REALLY welcome.

+function ($) {
  'use strict';

  // ContentEditable adapter
  // =======================
  //
  // Adapter for contenteditable elements.
  function ContentEditable (element, completer, option) {
    this.initialize(element, completer, option);
  }

  $.extend(ContentEditable.prototype, $.fn.textcomplete.Adapter.prototype, {
    // Public methods
    // --------------

    // Update the content with the given value and strategy.
    // When an dropdown item is selected, it is executed.
    select: function (value, strategy, e) {
      var pre = this.getTextFromHeadToCaret();
      // use ownerDocument instead of window to support iframes
      var sel = this.el.ownerDocument.getSelection();
      
      var range = sel.getRangeAt(0);
      var selection = range.cloneRange();
      selection.selectNodeContents(range.startContainer);
      var content = selection.toString();
      var post = content.substring(range.startOffset);
      var newSubstr = strategy.replace(value, e);
      var regExp;
      if (typeof newSubstr !== 'undefined') {
        if ($.isArray(newSubstr)) {
          post = newSubstr[1] + post;
          newSubstr = newSubstr[0];
        }
        regExp = $.isFunction(strategy.match) ? strategy.match(pre) : strategy.match;
        pre = pre.replace(regExp, newSubstr)
            .replace(/ $/, "&nbsp"); // &nbsp necessary at least for CKeditor to not eat spaces
        range.selectNodeContents(range.startContainer);
        range.deleteContents();
        
        // create temporary elements
        var preWrapper = this.el.ownerDocument.createElement("div");
        preWrapper.innerHTML = pre;
        var postWrapper = this.el.ownerDocument.createElement("div");
        postWrapper.innerHTML = post;
        
        // create the fragment thats inserted
        var fragment = this.el.ownerDocument.createDocumentFragment();
        var childNode;
        var lastOfPre;
        while (childNode = preWrapper.firstChild) {
        	lastOfPre = fragment.appendChild(childNode);
        }
        while (childNode = postWrapper.firstChild) {
        	fragment.appendChild(childNode);
        }
        
        // insert the fragment & jump behind the last node in "pre"
        range.insertNode(fragment);
        range.setStartAfter(lastOfPre);
        
        range.collapse(true);
        sel.removeAllRanges();
        sel.addRange(range);
      }
    },

    // Private methods
    // ---------------

    // Returns the caret's relative position from the contenteditable's
    // left top corner.
    //
    // Examples
    //
    //   this._getCaretRelativePosition()
    //   //=> { top: 18, left: 200, lineHeight: 16 }
    //
    // Dropdown's position will be decided using the result.
    _getCaretRelativePosition: function () {
      var range = this.el.ownerDocument.getSelection().getRangeAt(0).cloneRange();
      var wrapperNode = range.endContainer.parentNode;
      var node = this.el.ownerDocument.createElement('span');
      range.insertNode(node);
      range.selectNodeContents(node);
      range.deleteContents();
      setTimeout(function() { wrapperNode.normalize(); }, 0);
      var $node = $(node);
      var position = $node.offset();
      position.left -= this.$el.offset().left;
      position.top += $node.height() - this.$el.offset().top;
      position.lineHeight = $node.height();
      
      // special positioning logic for iframes
      // this is typically used for contenteditables such as tinymce or ckeditor
      if (this.completer.$iframe) {
        var iframePosition = this.completer.$iframe.offset();
        position.top += iframePosition.top;
        position.left += iframePosition.left;
        // We need to get the scrollTop of the html-element inside the iframe and not of the body-element,
        // because on IE the scrollTop of the body-element (this.$el) is always zero.
        position.top -= $(this.completer.$iframe[0].contentWindow.document).scrollTop();
      }
      
      $node.remove();
      return position;
    },

    // Returns the string between the first character and the caret.
    // Completer will be triggered with the result for start autocompleting.
    //
    // Example
    //
    //   // Suppose the html is '<b>hello</b> wor|ld' and | is the caret.
    //   this.getTextFromHeadToCaret()
    //   // => ' wor'  // not '<b>hello</b> wor'
    getTextFromHeadToCaret: function () {
      var range = this.el.ownerDocument.getSelection().getRangeAt(0);
      var selection = range.cloneRange();
      selection.selectNodeContents(range.startContainer);
      return selection.toString().substring(0, range.startOffset);
    }
  });

  $.fn.textcomplete.ContentEditable = ContentEditable;
}(jQuery);

// NOTE: TextComplete plugin has contenteditable support but it does not work
//       fine especially on old IEs.
//       Any pull requests are REALLY welcome.

+function ($) {
  'use strict';

  // CKEditor adapter
  // =======================
  //
  // Adapter for CKEditor, based on contenteditable elements.
  function CKEditor (element, completer, option) {
    this.initialize(element, completer, option);
  }

  $.extend(CKEditor.prototype, $.fn.textcomplete.ContentEditable.prototype, {
    _bindEvents: function () {
      var $this = this;
      this.option.ckeditor_instance.on('key', function(event) {
        var domEvent = event.data;
        $this._onKeyup(domEvent);
        if ($this.completer.dropdown.shown && $this._skipSearch(domEvent)) {
          return false;
        }
      }, null, null, 1); // 1 = Priority = Important!
      // we actually also need the native event, as the CKEditor one is happening to late
      this.$el.on('keyup.' + this.id, $.proxy(this._onKeyup, this));
    },
});

  $.fn.textcomplete.CKEditor = CKEditor;
}(jQuery);

// The MIT License (MIT)
// 
// Copyright (c) 2015 Jonathan Ong me@jongleberry.com
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
// associated documentation files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify, merge, publish, distribute,
// sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in all copies or
// substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
// NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
// DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//
// https://github.com/component/textarea-caret-position

(function ($) {

// The properties that we copy into a mirrored div.
// Note that some browsers, such as Firefox,
// do not concatenate properties, i.e. padding-top, bottom etc. -> padding,
// so we have to do every single property specifically.
var properties = [
  'direction',  // RTL support
  'boxSizing',
  'width',  // on Chrome and IE, exclude the scrollbar, so the mirror div wraps exactly as the textarea does
  'height',
  'overflowX',
  'overflowY',  // copy the scrollbar for IE

  'borderTopWidth',
  'borderRightWidth',
  'borderBottomWidth',
  'borderLeftWidth',
  'borderStyle',

  'paddingTop',
  'paddingRight',
  'paddingBottom',
  'paddingLeft',

  // https://developer.mozilla.org/en-US/docs/Web/CSS/font
  'fontStyle',
  'fontVariant',
  'fontWeight',
  'fontStretch',
  'fontSize',
  'fontSizeAdjust',
  'lineHeight',
  'fontFamily',

  'textAlign',
  'textTransform',
  'textIndent',
  'textDecoration',  // might not make a difference, but better be safe

  'letterSpacing',
  'wordSpacing',

  'tabSize',
  'MozTabSize'

];

var isBrowser = (typeof window !== 'undefined');
var isFirefox = (isBrowser && window.mozInnerScreenX != null);

function getCaretCoordinates(element, position, options) {
  if(!isBrowser) {
    throw new Error('textarea-caret-position#getCaretCoordinates should only be called in a browser');
  }

  var debug = options && options.debug || false;
  if (debug) {
    var el = document.querySelector('#input-textarea-caret-position-mirror-div');
    if ( el ) { el.parentNode.removeChild(el); }
  }

  // mirrored div
  var div = document.createElement('div');
  div.id = 'input-textarea-caret-position-mirror-div';
  document.body.appendChild(div);

  var style = div.style;
  var computed = window.getComputedStyle? getComputedStyle(element) : element.currentStyle;  // currentStyle for IE < 9

  // default textarea styles
  style.whiteSpace = 'pre-wrap';
  if (element.nodeName !== 'INPUT')
    style.wordWrap = 'break-word';  // only for textarea-s

  // position off-screen
  style.position = 'absolute';  // required to return coordinates properly
  if (!debug)
    style.visibility = 'hidden';  // not 'display: none' because we want rendering

  // transfer the element's properties to the div
  properties.forEach(function (prop) {
    style[prop] = computed[prop];
  });

  if (isFirefox) {
    // Firefox lies about the overflow property for textareas: https://bugzilla.mozilla.org/show_bug.cgi?id=984275
    if (element.scrollHeight > parseInt(computed.height))
      style.overflowY = 'scroll';
  } else {
    style.overflow = 'hidden';  // for Chrome to not render a scrollbar; IE keeps overflowY = 'scroll'
  }

  div.textContent = element.value.substring(0, position);
  // the second special handling for input type="text" vs textarea: spaces need to be replaced with non-breaking spaces - http://stackoverflow.com/a/13402035/1269037
  if (element.nodeName === 'INPUT')
    div.textContent = div.textContent.replace(/\s/g, '\u00a0');

  var span = document.createElement('span');
  // Wrapping must be replicated *exactly*, including when a long word gets
  // onto the next line, with whitespace at the end of the line before (#7).
  // The  *only* reliable way to do that is to copy the *entire* rest of the
  // textarea's content into the <span> created at the caret position.
  // for inputs, just '.' would be enough, but why bother?
  span.textContent = element.value.substring(position) || '.';  // || because a completely empty faux span doesn't render at all
  div.appendChild(span);

  var coordinates = {
    top: span.offsetTop + parseInt(computed['borderTopWidth']),
    left: span.offsetLeft + parseInt(computed['borderLeftWidth'])
  };

  if (debug) {
    span.style.backgroundColor = '#aaa';
  } else {
    document.body.removeChild(div);
  }

  return coordinates;
}

$.fn.textcomplete.getCaretCoordinates = getCaretCoordinates;

}(jQuery));

return jQuery;
}));


/***/ }),

/***/ 699:
/***/ (function(module, exports, __webpack_require__) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "comment-temp"
  }, [_c('div', [_c('v-expansion-panel', {
    staticClass: "expansion-pl-transparent"
  }, [_c('v-expansion-panel-content', {
    attrs: {
      "hide-actions": "",
      "value": true
    }
  }, [_c('v-card', {
    staticClass: "comments__container"
  }, [_c('v-card-text', {
    staticClass: "pl-4"
  }, [_c('div', {
    attrs: {
      "id": "comments-container-el"
    }
  })])], 1)], 1)], 1)], 1)])
},staticRenderFns: []}
module.exports.render._withStripped = true
if (false) {
  module.hot.accept()
  if (module.hot.data) {
     require("vue-hot-reload-api").rerender("data-v-c8f36406", module.exports)
  }
}

/***/ })

});
//# sourceMappingURL=11.7e775a8cb7e350ea1778.js.map