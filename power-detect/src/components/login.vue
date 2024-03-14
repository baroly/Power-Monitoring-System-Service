<template>
    <div class="loginBody">
        <div class="loginDiv">
            <div class="ligin-content">
                <h1 class="login-title">用戶登入</h1>
                <el-form :model="loginForm" label-width="100px" :rules="rules" ref="loginForm">
                    <el-form-item label="帳號" prop="userName">
                        <el-input style="width: 80%;" type="text" v-model="loginForm.userName"
                            autocomlate="off"></el-input>
                    </el-form-item>
                    <el-form-item label="密碼" prop="userPassword">
                        <el-input style="width: 80%;" type="password" v-model="loginForm.userPassword"
                            show-passwrod autocomlate="off" @keyup.enter.native="confirm"></el-input>
                    </el-form-item>
                </el-form>
                    <el-button style="margin-left: 10%;margin-top: 20%;" type="primary"
                         @click="regist" :disabled="confirm_disabled">註冊</el-button>
                    <el-button style="margin-left: 42%;margin-top: 20%;" type="primary"
                         @click="confirm" :disabled="confirm_disabled">登入</el-button>
                
            </div>
        </div>
    </div>
</template>
<script lang="ts">
export default {
    name: 'login',
    data(){
        return{
            confirm_disabled:false,
            loginForm:{
                userName:'',
                userPassword:'',
            },
            rules:{
                userName:[
                    { required: true, message: '請輸入帳號', trigger: 'blur' },
                ],
                userPassword:[
                    { required: true, message: '請輸入密碼', trigger: 'blur' },
                ],
            }
        }
    },
    methods:{
        confirm(){
            this.confirm_disabled=true
            this.$refs.loginForm.validate((valid) => {
                if(valid){
                    this.$axios.post('http://192.168.0.54:3030/user/login', this.loginForm)
                    .then(resp => resp=resp.data).then(resp => {
                        if(resp.code == 200){
                            sessionStorage.setItem("CurUser", JSON.stringify(resp.data))
                            this.$router.replace('/server')
                        }else{
                            alert('登入失敗，請確認帳號密碼')
                            this.confirm_disabled=false
                        }
                    }).catch(error => {
                        console.log('伺服器異常請稍後再試')
                        this.confirm_disabled=false
                    })
                }else{
                    this.confirm_disabled=false
                    console.log('fail')
                    return false
                }
            })
        },
        regist(){
            this.$router.replace('/regist')
        }
    },
}
</script>
<style scoped>
.loginBody{
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: #B3C0D1;
}
.loginDiv{
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -200px;
    margin-left: -250px;
    width: 450px;
    height: 330px;
    background: #fff;
    border-radius: 5%;
}
.login-title{
    margin: 20px 0;
    text-align: center;
}
.login-content{
    width: 400px;
    height: 250px;
    position: absolute;
    top: 25px;
    left: 25px;
}
</style>