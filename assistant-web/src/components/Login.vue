<template>
  <div>
    <div>
      <span>用户名</span><input type="text" v-model="form.username"/>
    </div>
    <div>
      <span>密码</span><input type="password" v-model="form.password"/>
    </div>
    <div>
      <span @click="doLogin">登录</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      form: {
        username: '',
        password: ''
      }
    }
  },
  created() {
    this.cleanStore();
    this.initSecureRsaKey();
  },
  methods: {
    cleanStore() {
      this.$store.removeToken();
      this.$store.removeUser();
      this.$secureTransfer.saveRsaPubKey('')
    },
    initSecureRsaKey() {
      this.$axios({
        url: '/secure/key',
        method: 'POST'
      }).then(({data}) => {
        let pubKey = data.data;
        this.$secureTransfer.saveRsaPubKey(pubKey);
      })
    },
    doLogin() {
      this.$axios({
        url: '/login',
        method: 'POST',
        data: {
          username: this.form.username,
          password: this.form.password
        }
      }).then(({data}) => {
        let token = data.data;
        this.$store.setToken(token);
        let user = {};
        this.$store.setUser(user);
        let redirect = this.$route.query.redirect;
        if (!redirect) {
          redirect = '/main';
        } else {
          redirect = decodeURIComponent(redirect)
        }
        this.$router.push({path: redirect})
      })
    }
  }
}
</script>

<style scoped>

</style>
