import { createApp } from 'vue'; // 使用 Vue 3.x 的 createApp 函數
import App from './App.vue';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import PrimeVue from 'primevue/config';

import axios from 'axios';
//import { createRouter, createWebHistory } from 'vue-router'; // 使用 Vue 3.x 的路由
import rout from './router/index'

const app = createApp(App);

app.use(ElementPlus);
app.use(PrimeVue)
//app.use(VueRouter);

app.config.globalProperties.$axios = axios;
app.config.globalProperties.$url = 'http://192.168.0.54:3030';

// const router = createRouter({
//   history: createWebHistory(),
//   routes: [
//     {
//         path:'/',
//         name:'login',
//         component: ()=>import('./components/login.vue')
//     },
//     {
//         path:'/index',
//         name:'index',
//         component: ()=>import('./components/Index.vue')
//     }
//   ] // 你的路由配置在這裡
// });

app.use(rout);

app.mount('#app');
