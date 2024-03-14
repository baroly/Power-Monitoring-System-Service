<template>
    <div style="display: flex; line-height: center;">
        <div style="line-height: 70px;">
            <el-icon style="font-size: 20px;"  @click="collapse"><Fold /></el-icon>
        </div>
        <div style="flex: 1; text-align: center; font-size: 36px;">
            <span style="font-size: 60px;">智慧居家監測系統</span>
        </div>
        <div class="toolbar">

            <el-dropdown style="line-height: 60px;">
                <span class="el-dropdown-link">
                    {{ user.nickName }}<el-icon class="el-icon--right"><ArrowDownBold /></el-icon>
                </span>
                
                <template #dropdown>
                    <el-dropdown-menu style="height: 100%;">
                        <el-dropdown-item @click="toUser">我的帳戶</el-dropdown-item>
                        <el-dropdown-item @click="logout">登出</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
    </div>
</template>
<script lang="ts">
import { Menu as Setting, ArrowDownBold, Fold } from '@element-plus/icons-vue'

export default {
    name: 'HeaderManue',
    components:{
        Setting, ArrowDownBold, Fold
    },
    data(){
        return{
            user: JSON.parse(sessionStorage.getItem('CurUser') || "{}")
        }
    },
    methods:{
        toUser(){
            console.log("to user")
            this.$router.replace('/home')
        },
        logout(){
            console.log("log out")
            
            this.$confirm('確定要登出嗎?', '提示', {
                confirmButtonText: '確定',
                type: 'warning',
                center: true
            }).then(()=>{
                this.$message({
                    type: 'success',
                    message: '登出成功'
                })
                this.$router.push("/")
                sessionStorage.clear()
            }).catch(()=>{
                this.$message({
                    type: 'info',
                    message: '已取消登出'
                })
            })

            
        },
        collapse(){
            this.$emit('doCollapse')
        }
    },
    mounted() {
        this.$router.replace('/server')
    },
}
</script>