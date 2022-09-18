// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import './secure/secure-vue-main'
import './secure/secure-axios'
import './config/store'
import './config/permission'

Vue.config.productionTip = false

Vue.prototype.$env = process.env

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})
