<template>
    <section class="section-hero section-shaped my-0">
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
        <div class="container shape-container d-flex align-items-center">
            <div class="col px-0">
                <div class="row justify-content-center align-items-center">
                    <div class="col-lg-7 text-center pt-lg">
                        <img src="img/brand/logo-light.png" style="width: 200px;" class="img-fluid logo">
                        <p class="lead text-white mt-4 mb-5">A plateforme where businesses can manage their projects' workflow and hire new recruits or freelancers</p>
                        <div class="btn-wrapper">
                            <router-link to="/login" slot="title" class="nav-link nav-link-inline" data-toggle="dropdown" role="button">
                                <base-button tag="a"
                                         href="https://demos.creative-tim.com/vue-argon-design-system/documentation"
                                         class="mb-3 mb-sm-0"
                                         type="info"
                                         icon="fa fa-sign-in">
                                    Login
                                </base-button>
                            </router-link>
                            <router-link to="/register" slot="title" class="nav-link nav-link-inline" data-toggle="dropdown" role="button">
                                <base-button tag="a"
                                         href="https://www.creative-tim.com/product/vue-argon-design-system"
                                         class="mb-3 mb-sm-0"
                                         type="white"
                                         icon="fa fa-user-plus">
                                    Register
                                </base-button>
                            </router-link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>
<script>
export default {};
</script>
<style scoped>
    .logo{
        position: relative;
        left: 50%;
        transform: translateX(-50%);
    }
    .nav-link-inline{
        display: inline;
    }
</style>
