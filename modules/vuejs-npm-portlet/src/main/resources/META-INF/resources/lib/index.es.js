//
// We are using the runtime + compiler module in this case so that we don't need
// to process templates during build time.
//
// See https://vuejs.org/v2/guide/installation.html#Runtime-Compiler-vs-Runtime-only
// for more information.
//

// import Vue from 'vue/dist/vue.min';
import axios from 'axios';

export default function(portletNamespace) {
	
	// Application 1
	/*
	new Vue({
		el: `#${portletNamespace}login_vue`,
		data: {
			isSignedIn: false,
			userNameLogin: '',
			drawer: false,
			avatarURL: 'http://via.placeholder.com/350x150',
			notificationCount: 0,
			isShowUserMenu: false,
			toggle_exclusive: 0
		},
		created () {
	      let vm = this
	      vm.$nextTick(function () {
	        vm.isSignedIn = themeDisplay.isSignedIn()
	        vm.userNameLogin = themeDisplay.getUserName()
	        let element = document.getElementById('_npmvuelogin_login_vue')
	        if (element !== null && element !== undefined) {
	        	element.classList.remove('hidden__temp')
	        }
	        
	        if (vm.isSignedIn) {
	          let param = {
	            responseType: 'blob'
	          }
	          axios.get('/o/rest/v2/users/' + themeDisplay.getUserId() + '/photo', param).then(function (response) {
	            vm.avatarURL = window.URL.createObjectURL(response.data)
	          }).catch(function (error) {
	            console.log(error)
	          })
	        }
	      })
	    },
	    methods: {
	      showNoti () {
	        let vm = this
	        vm.drawer = !vm.drawer
	        console.log('aaaaaaaa')
	      },
	      doRegisterRedirect () {
	        let redirectURL = themeDisplay.getLayoutRelativeURL().substring(0, themeDisplay.getLayoutRelativeURL().lastIndexOf('\/'))
	        if (redirectURL !== '') {
	          window.location.href = redirectURL + '/register'
	        } else {
	          window.location.href = themeDisplay.getURLHome() + '/register'
	        }
	      },
	      doUserInfo () {
	        let redirectURL = themeDisplay.getLayoutRelativeURL().substring(0, themeDisplay.getLayoutRelativeURL().lastIndexOf('\/'))
	        window.location.href = redirectURL + '/profile'
	      },
	      doExitApp () {
	        window.location.href = '/c/portal/logout'
	      }
	    }
	});
	*/
}