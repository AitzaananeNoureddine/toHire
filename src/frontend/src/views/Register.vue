<template>
    <section class="section section-shaped section-lg my-0">
        <div class="shape shape-style-1 bg-gradient-default">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
        </div>
        <div class="container pt-lg-md">
            <div class="row justify-content-center">
                <div class="col-lg-5">
                    <card type="secondary" shadow
                          header-classes="bg-white pb-5"
                          body-classes="px-lg-5 py-lg-5"
                          class="border-0">
                        <template>
                            <div class="text-muted text-center mb-3">
                                <small>Sign in with</small>
                            </div>
                            <div class="btn-wrapper text-center">
                                <base-button type="neutral">
                                    <img slot="icon" src="img/icons/common/github.svg">
                                    Github
                                </base-button>

                                <base-button type="neutral">
                                    <img slot="icon" src="img/icons/common/google.svg">
                                    Google
                                </base-button>
                            </div>
                        </template>
                        <template>
                            <div class="text-center text-muted mb-4">
                                <small>Or sign up with credentials</small>
                            </div>
                            <form role="form">
                                <base-input alternative
                                            placeholder="Email"
                                            v-model="email"
                                            addon-left-icon="ni ni-email-83">
                                </base-input>
                                <badge v-if="invalidEmail" type="warning" class="badge text-uppercase">{{msg}}</badge>
                                <base-input alternative
                                            type="password"
                                            placeholder="Password"
                                            v-model="password"
                                            addon-left-icon="ni ni-lock-circle-open">
                                </base-input>
                                <div class="text-muted font-italic mb-3">
                                    <small>password strength:
                                        <span v-bind:class="'text-success font-weight-700 '+ strength ">{{strength}}</span>
                                    </small>
                                </div>
                                <div class="text-center">
                                    <base-button type="primary" class="my-4" @btn-click="signUp">Create account</base-button>
                                </div>
                            </form>
                        </template>
                    </card>
                </div>
            </div>
        </div>
    </section>
</template>
<script>
import axios from 'axios'
export default {
    data() {
        return {
            email:'',
            password:'',
            invalidEmail: false,
            msg:'',
            strength:'Empty',
        }
    },
    watch:{
        password: function(){
            if(/^((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{6,}))|((?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z0-9])(?=.{8,}))$/.test(this.password)) this.strength = 'Strong';
            else if(/^[a-z]+$/.test(this.password)) this.strength = "Weak";
            else if(this.password === '') this.strength = "Empty";
            else this.strength = 'Medium';
        }
    },
    methods: {
        signUp(){
            if(!/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(this.email)){
                this.invalidEmail = true;
                this.msg = 'Invalid email';
            }
            else{
                this.invalidEmail = false;
                if(this.strength !== 'Weak' && this.strength !== 'Empty'){
                    const headers = { 'content-type': 'application/json' };
                    const data = { username: this.email, password: this.password };
                    axios
                        .post('api/auth/signup',data,headers)
                        .then(response => {
                            if(response.status === 200) this.$router.push("/dashboard");
                            else if(response.status === 400){
                                this.invalidEmail = true;
                                this.msg = 'Email already in use';
                            }
                        })
                }
            }
        }
    },
};
</script>
<style scoped>
    .badge{
        width: 100%;
        margin-bottom: 20px;
    }
    .Medium{
        color: #ff9500 !important;
    }
    .Strong{
        color: #4ad66d !important;
    }
    .Weak{
        color:#e63946 !important;
    }
    .Empty{
        color: #28292a !important;
    }
</style>
