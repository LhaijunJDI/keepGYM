new Vue({
    el: '#app',
    data: function () {
        return {
            radio1: '1',
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
                if (this.radio1 == '2') {
                    $.ajax({
                        url: "/getMemberById",
                        method: "get",
                        data: {
                            "id": this.userInfo.username, "password": this.userInfo.password,
                        },
                        success: function (data) {
                            if (data == 'OK') {
                                that.$message('登录成功！');
                                window.location.href = "toMemberGym?memberId="+that.userInfo.username;
                            } else if (data == 'False') {
                                that.$message({
                                    message: '密码错误，请重新登录!',
                                    type: 'warning'
                                });
                                that.clear();
                            } else if (data == 'noMember') {
                                that.$message({
                                    message: '账号不存在，请重新登录!',
                                    type: 'warning'
                                });
                                that.clear();
                            }
                        }
                    });
                }
                //管理员登录
                else {
                    $.ajax({
                        url: "/getManagerById",
                        method: "get",
                        data: {
                            "id": this.userInfo.username, "password": this.userInfo.password,
                        },
                        success: function (data) {
                            if (data == 'OK') {
                                that.$message('登录成功！');
                                window.location.href = "managerGym";
                            } else if (data == 'False') {
                                that.$message({
                                    message: '密码错误，请重新登录!',
                                    type: 'warning'
                                });
                                that.clear();
                            } else if (data == 'noManager') {
                                that.$message({
                                    message: '账号不存在，请重新登录!',
                                    type: 'warning'
                                });
                                that.clear();
                            }
                        }
                    });
                }
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
