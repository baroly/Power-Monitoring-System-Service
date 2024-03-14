<template>
    <el-menu
        background-color="#545c64"
        active-text-color="#ffd04b"
        text-color="#fff"
        default-active="/server"
        style="height: 100vh;"
        :collapse="isCollapse"
        :collapse-transition="false"
        router>
        <el-menu-item index="/home">
            <el-icon><House /></el-icon>
            <span slot="title">Home</span>
        </el-menu-item>
        <el-menu-item index="/server">
            <el-icon><Platform /></el-icon>
            <span slot="#title">Server</span>
        </el-menu-item>
        <!-- <el-menu-item index="/setting">
            <el-icon><Tools /></el-icon>
            <span slot="#title">Setting</span>
        </el-menu-item> -->
    </el-menu>
</template>

<script lang="ts">
import { Menu as HomeFilled, House, Avatar, Tools, Platform } from '@element-plus/icons-vue'

export default{
    name: 'Aside',
    components:{
        HomeFilled, House, Avatar, Tools, Platform
    },
    data(){
        return {
            
        }
    },
    props:{
        isCollapse:Boolean
    }
}
</script>