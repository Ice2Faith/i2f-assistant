import router from '@/router'
import _store from "./store";

const whiteList = ['/login']

const defaultResources = ['/main']

router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    return next()
  }
  if (to.path === '/') {
    return next({path: '/login'})
  }

  if (whiteList.indexOf(to.path) !== -1) {
    // 在免登录白名单，直接进入
    return next()
  }

  if (_store.getToken()) {
    //获取角色权限判定是否拥有权限在进入
    let user = _store.getUser();
    let resources = user ? (user.resources ? user.resources : defaultResources) : defaultResources;
    if (resources.indexOf(to.path) !== -1) {
      // 拥有这项资源
      next()
    } else {
      // 不具有这项资源
      // next(`/login?redirect=${encodeURIComponent(to.fullPath)}`)
      next({path: from.path})
      alert('您没有此资源的访问权限')
    }
  } else {
    // 否则全部重定向到登录页
    next(`/login?redirect=${encodeURIComponent(to.fullPath)}`)
  }
})

