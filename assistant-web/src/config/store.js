import Vue from 'vue'

const _store = {
  TOKEN_KEY() {
    return 'token';
  },
  setToken(token) {
    return localStorage.setItem(this.TOKEN_KEY(), token);
  },
  getToken() {
    return localStorage.getItem(this.TOKEN_KEY());
  },
  removeToken() {
    localStorage.removeItem(this.TOKEN_KEY())
  },
  USER_KEY() {
    return 'user';
  },
  getUser() {
    let json = sessionStorage.getItem(this.USER_KEY());
    return JSON.parse(json);
  },
  setUser(user) {
    let json = JSON.stringify(user);
    sessionStorage.setItem(this.USER_KEY(), json);
  },
  removeUser() {
    sessionStorage.removeItem(this.USER_KEY());
  }
};

Vue.prototype.$store = _store;

export default _store;
