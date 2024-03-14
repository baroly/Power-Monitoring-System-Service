<template>
    <div>
        <el-container style="height: 100%">
            <el-aside :width="aside_width" style="border-right: rgb(234, 234, 234) 1px solid;">
                <Aside :isCollapse="isCollapse"></Aside>
            </el-aside>

            <el-container style="height: 100%;">
                <el-header style="height: 90px; text-align: right; font-size: 12px; border-bottom: rgb(189, 206, 223) 1px solid;">
                    <HeaderManue @doCollapse="doCollapse"></HeaderManue>
                </el-header>

                <el-main>
                    <router-view/>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<script lang="ts">
import { defineComponent } from 'vue'
import Aside from './Aside.vue'
import HeaderManue from './HeaderManue.vue'
import MainContent from './MainContent.vue'

export default defineComponent({
    name: 'Index',
    components:{
        Aside, HeaderManue, MainContent
    },
    data(){
        return{
            isCollapse:false,
            aside_width:'200px'
        }
    },
    methods:{
        doCollapse(){
            console.log('123')
            this.isCollapse = !this.isCollapse
            if(this.isCollapse){
                this.aside_width = '64px'
            }else{
                this.aside_width = '200px'
            }
        }
    }
})
</script>

<style>
.layout-container-demo .el-header {
    position: relative;
    
    color: var(--el-text-color-primary);
}
.layout-container-demo .el-aside {
    color: var(--el-text-color-primary);
    background: var(--el-color-primary-light-8);
    height: 100%;
}
.layout-container-demo .el-menu {
    border-right: none;
}
.layout-container-demo .el-main {
    padding: 0;
}
.layout-container-demo .toolbar {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    height: 100%;
    right: 20px;
}
</style>