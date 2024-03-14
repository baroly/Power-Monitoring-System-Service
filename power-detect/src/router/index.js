import { createRouter, createWebHistory } from 'vue-router';
import login from '../components/login.vue'
import regist from '../components/regist.vue'
import index from '../components/Index.vue'
import home from '../components/Home.vue'
import maincontent from '../components/MainContent.vue'
import viewserver from '../components/ViewServer.vue'
import devData from '../components/DevData.vue'
import hisData from '../components/HisData.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
      {
          path:'/',
          name:'login',
          component: login
      },
      {
        path:'/regist',
        name:'regist',
        component: regist
      },
      {
          path:'/index',
          name:'index',
          component: index,
          children:[
            {
              path:'/server',
              name:'server',
              meta: {
                title: '檢視伺服器'
              },
              component: maincontent
            },
            {
              path:'/home',
              name:'home',
              meta: {
                title: '首頁'
              },
              component: home
            },
            {
              path:'/dev',
              name:'dev',
              meta: {
                title: '檢視IoT'
              },
              component: viewserver
            }
            ,
            {
              path:'/data',
              name:'devData',
              meta: {
                title: '資料檢視'
              },
              component: devData
            },
            {
              path:'/hisData',
                name:'hisData',
                component: hisData
            }
          ]
      },
    ] // 你的路由配置在這裡
  });

export default router;