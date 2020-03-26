new Vue({
    el: '#app',
    data: function () {
        return {
            userInfo: {
                username: '',
                password: '',
            }
        }
    },
    methods: {
        //验证登录信息
        login() {
            if (this.userInfo.username != '' && this.userInfo.password != '') {
                let that = this;
                //会员登录
                    $.ajax({
                        url: "/checkMember",
                        method: "get",
                        data: {
                            "memberId": this.userInfo.username, "password": this.userInfo.password,
                        },
                        success: function (data) {
                            if (data == 'fail') {
                                that.$message({
                                    message: '密码错误，请重新登录!',
                                    type: 'warning'
                                });
                                that.clear();
                            }
                            if (data != null) {
                                that.$message('登录成功！');
                                window.location.href = "toMemberGym?"+"token="+data;
                            }
                        }
                    });
            } else {
                this.$message({
                    message: '账号或者密码不能为空！！！',
                    type: 'warning'
                });
            }
        },

        clear() {
            this.userInfo.username = '';
            this.userInfo.password = '';
        },

    }
})
