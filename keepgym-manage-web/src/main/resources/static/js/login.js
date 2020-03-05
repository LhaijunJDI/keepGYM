new Vue({
    el: '#app',
    data: function () {
        return {
            form: {
                username: '',
                password: '',
            },
        }
    },
    methods: {
        checkManage() {
            let that = this;
               if(this.form.username!= ''&& this.form.password!='') {
                $.ajax({
                    url: "/checkManage",
                    type: "get",
                    data: {"id": that.form.username, "password": that.form.password},
                    contentType: "application/json",
                    success: function (data) {
                        if (data == 'success') {
                            that.$message('登录成功！');
                            window.location.href = "/toManageIndex?managerId=" + that.form.username;
                        }
                        if (data == 'fail') {
                            that.$message({
                                message: '密码错误，请重新登录!',
                                type: 'warning'
                            });
                            that.clear();
                        }
                    }
                });
            }else {
                   this.$message({
                       message: '账号或者密码不能为空！！！',
                       type: 'warning'
                   });
               }

        },
        clear() {
            this.form.username = '';
            this.form.password = '';
        },

    }
})