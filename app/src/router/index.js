import Index from '../pages/index.vue'
import Detail from '../pages/Detail'
import Home from '../pages/Home'
import Empty from '../pages/Empty'
import About from '../pages/About'
import Contact from '../pages/Contact'
import Team from '../pages/Team'
import Video from '../pages/Video'
import DataProcessing from '../pages/DataProcessing'
import VueRouter from 'vue-router'




const routes = [
  { 
    path: '/',
    component: Index,
    redirect: '/home',
    children: [
      {
        path: 'home',
        component: Home,
      },
      {
        name:'detail',
        path: 'detail',
        component: Detail,
      },
      {
        name:'about',
        path: 'about',
        component: About,
      },
      {
        name:'contact',
        path: 'contact',
        component: Contact,
      },
      {
        name:'team',
        path: 'team',
        component: Team,
      },
      {
        path: 'video',
        component: Video,
      },
      {
        path: 'empty',
        component: Empty,
      },
      {
        path: 'DataProcessing',
        component: DataProcessing,
      }
    ]
},
];

const router = new VueRouter({
  mode: 'hash',
  routes // (缩写) 相当于 routes: routes
})
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err => err)
}


export default router