new Vue({
    el: '#app',
    data: function () {
        //校验电话格式
        var checkPhone = (rule, value, callback) => {
            const phoneReg = /^1[3|4|5|7|8][0-9]{9}$/
            if (!value) {
                return callback(new Error('电话号码不能为空'))
            }
            setTimeout(() => {
                // Number.isInteger是es6验证数字是否为整数的方法,实际输入的数字总是识别成字符串
                // 所以在前面加了一个+实现隐式转换
                if (!Number.isInteger(+value)) {
                    callback(new Error('请输入数字值'))
                } else {
                    if (phoneReg.test(value)) {
                        callback()
                    } else {
                        callback(new Error('电话号码格式不正确'))
                    }
                }
            }, 100)
        };
        //校验邮箱格式
        var checkEmail = (rule, value, callback) => {
            const mailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
            if (!value) {
                return callback(new Error('邮箱不能为空'))
            }
            setTimeout(() => {
                if (mailReg.test(value)) {
                    callback()
                } else {
                    callback(new Error('请输入正确的邮箱格式'))
                }
            }, 100)
        };
        //校验密码格式
        var validatePass = (rule, value, callback) => {
            const reg = /^[a-zA-Z]\w{5,12}$/;
            if (!value) {
                callback(new Error('请输入新密码'));
            } else if (!reg.test(value)) {
                callback(new Error('密码以字母开头,长度在6~12之间 只能包含字母、数字和下划线'))
            } else {
                callback();
            }
        };
        //校验两次密码是否一致
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.pwd.newPwd) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            form: {
                username: '',
                gender: '',
                createTime: '',
                endTime: '',
                expire: '',
                phone: '',
                address: '',
                email: '',
            },

            formLabelWidth: '100px',
            alterPwdDialog: false,

            pwd: {
                originPwd: '',
                newPwd: '',
                confirmPwd: '',
            },
            editForm: {
                id: '',
                username: '',
                gender: '',
                phone: '',
                address: '',
                email: '',
            },

            rules: {
                phone: [
                    {required: true, validator: checkPhone, trigger: 'blur'}
                ],
                email: [
                    {required: true, validator: checkEmail, trigger: 'blur'}
                ],
                newPwd: [
                    {required: true, validator: validatePass, trigger: 'blur'}
                ],
                confirmPwd: [
                    {required: true, validator: validatePass2, trigger: 'blur'}
                ]
            }
        }
    },

    created() {
        this.getMemberInfo();
    },
    methods: {
        //获取会员信息
        getMemberInfo() {
            let that = this;
            var memberId = document.getElementById("memberId").value;
            $.ajax({
                url: "/toSearchMember",
                type: "get",
                data: {
                    "memberId": memberId,
                },
                contentType: 'application/json',
                success: function (data) {
                    if (data != null) {
                        that.form.username = data.username;
                        if (data.gender == '1') {
                            that.form.gender = '1';
                        } else {
                            that.form.gender = '2';
                        }
                        that.form.email = data.email;
                        that.form.createTime = data.createTime;
                        that.form.endTime = data.endTime;
                        that.form.phone = data.phone;
                        that.form.address = data.address;
                        that.form.expire = data.expire;
                    }
                }
            });
        },
        //提交修改请求
        onSubmit(form) {
            let that = this;
            var memberId = document.getElementById("memberId").value;
            that.editForm.id = memberId;
            that.editForm.username = that.form.username;
            that.editForm.gender = that.form.gender;
            that.editForm.phone = that.form.phone;
            that.editForm.address = that.form.address;
            that.editForm.email = that.form.email;
            var dates = JSON.stringify(this.editForm);
            this.$refs[form].validate((valid) => {
                if (valid) {
                    console.log(this.form);
                    $.ajax({
                        url: "/updateMember",
                        type: "put",
                        data: dates,
                        contentType: "application/json",
                        success: function (data) {
                            if (data == 'success') {
                                that.$message('修改成功！');
                                window.location.href = location.href;
                            }
                            if (data == 'fail') {
                                that.$message({
                                    message: '修改信息失败，请重新修改！',
                                    type: 'warning'
                                });
                                window.location.href = location.href;
                            }
                        }
                    });
                } else {
                    console.log('error!!');
                    return false;
                }
            });
        },
        alterPwd() {
            let that = this;
            var memberId = document.getElementById("memberId").value;
            $.ajax({
                url: "/alterMemberPwd",
                type: "put",
                data: {"memberId": memberId, "originPwd": that.pwd.originPwd, "newPwd": that.pwd.newPwd},
                success: function (data) {
                    if (data == 'success') {
                        that.$alert('', '密码修改成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                                window.location.href = location.href;
                            }
                        });
                    }
                    if (data == 'fail') {
                        that.$alert('请确认输入信息是否正确！', '修改失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                                that.pwd.originPwd = "";
                                that.pwd.newPwd = "";
                                that.pwd.confirmPwd = "";
                            }
                        });
                    }
                }

            })
        },
        resetPwd() {
            this.pwd.originPwd = "";
            this.pwd.newPwd = "";
            this.pwd.confirmPwd = "";
        },
        reset(){
            this.getMemberInfo();
        }
    }
})