<template>
    <div class="registBody">
        <div class="registDiv">
            <div class="regist-content">
                <h1 class="regist-title">註冊用戶</h1>
                <el-form :model="registForm" label-width="100px" :rules="rules" ref="registForm">
                    <el-form-item label="帳號" prop="userName">
                        <el-input style="width: 80%;" type="text" v-model="registForm.userName"
                            autocomlate="off"></el-input>
                    </el-form-item>
                    <el-form-item label="密碼" prop="userPassword">
                        <el-input style="width: 80%;" type="password" v-model="registForm.userPassword"
                            show-passwrod autocomlate="off" @keyup.enter.native="confirm"></el-input>
                    </el-form-item>
                    <el-form-item label="暱稱" prop="nickName">
                        <el-input style="width: 80%;" type="text" v-model="registForm.nickName"
                            show-passwrod autocomlate="off" @keyup.enter.native="confirm"></el-input>
                    </el-form-item>
                    <el-form-item label="電子信箱" prop="userEmail">
                        <el-input style="width: 80%;" type="text" v-model="registForm.userEmail"
                            show-passwrod autocomlate="off" @keyup.enter.native="confirm"></el-input>
                    </el-form-item>
                </el-form>
                    <el-button style="margin-left: 10%;margin-top: 8%;" type="primary"
                         @click="login" :disabled="confirm_disabled">返回登入</el-button>
                    <el-button style="margin-left: 35%;margin-top: 8%;" type="primary"
                         @click="confirm" :disabled="confirm_disabled">註冊帳號</el-button>
                
            </div>
        </div>
    </div>
</template>
<script lang="ts">
export default {
    name: 'regist',
    data(){
        return{
            confirm_disabled:false,
            registForm:{
                userName:'',
                userPassword:'',
                nickName:'',
                userEmail:''
            },
            rules:{
                userName:[
                    { required: true, message: '請輸入帳號', trigger: 'blur' },
                ],
                userPassword:[
                    { required: true, message: '請輸入密碼', trigger: 'blur' },
                ],
                nickName:[
                    { required: true, message: '請輸入暱稱', trigger: 'blur' },
                ],
                userEmail:[
                    { required: true, message: '請輸入電子信箱', trigger: 'blur' },
                ],
            }
        }
    },
    methods:{
        confirm(){
            this.confirm_disabled=true
            console.log("aaa")
            this.$refs.registForm.validate((valid) => {
                if(valid){
                    this.$axios.post('http://192.168.0.54:3030/user/regist', this.registForm)
                    .then(resp => resp=resp.data).then(resp => {
                        if(resp.code == 200){
                            alert('註冊成功')
                            this.$router.replace('/')
                            //sessionStorage.setItem("CurUser", JSON.stringify(resp.data))
                            //this.$router.replace('/index')
                        }else{
                            alert('帳號已存在,註冊失敗')
                            this.confirm_disabled=false
                        }
                    }).catch(error => {
                        alert('伺服器異常請稍後再試')
                        console.log('伺服器異常請稍後再試')
                    })
                }else{
                    this.confirm_disabled=true
                    console.log('fail')
                    this.confirm_disabled=false
                    return false
                }
            })
        },
        login(){
            this.$router.replace('/')
        }
    },
}
</script>
<style scoped>
.registBody{
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: #B3C0D1;
}
.registDiv{
    position: absolute;
    top: 45%;
    left: 50%;
    margin-top: -200px;
    margin-left: -250px;
    width: 450px;
    height: 410px;
    background: #fff;
    border-radius: 5%;
}
.regist-title{
    margin: 20px 0;
    text-align: center;
}
.regist-content{
    width: 400px;
    height: 250px;
    position: absolute;
    top: 25px;
    left: 25px;
}
</style>