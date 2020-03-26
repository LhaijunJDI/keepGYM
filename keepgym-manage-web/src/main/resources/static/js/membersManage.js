new Vue({
    el: '#app',
    data: function () {
        return {
            //办理会员的信息
            form: {
                id: '',
                username: '',
                gender: '',
                phone: '',
                email: '',
                address: '',
                level: '',
                createId: ''
            },
            memberId: '',
            //全部会员信息
            tableData: [{}],
            //查询来的会员信息
            memberInfo: {
                id: '',
                username: '',
                gender: '',
                phone: '',
                email: '',
                address: '',
                endTime: '',
                status: '',
            },
            //续费信息
            renew: {
                level: '',
                time: '',
            },

            //修改会员信息的dialog
            dialogFormVisible: false,
            //续费的dialog
            renewDialog: false,
            //模糊查询的数据
            search: '',
            stopCard: {
                stopTime: '',
            },
            stopCardDialog: false,
        }

    },
    created() {
        this.toSearchAllMembers();
    },
    computed: {
        // 模糊搜索
        tables() {
            const search = this.search
            if (search) {
                // filter() 方法创建一个新的数组，新数组中的元素是通过检查指定数组中符合条件的所有元素。
                // 注意： filter() 不会对空数组进行检测。
                // 注意： filter() 不会改变原始数组。
                return this.tableData.filter(data => {
                    // some() 方法用于检测数组中的元素是否满足指定条件;
                    // some() 方法会依次执行数组的每个元素：
                    // 如果有一个元素满足条件，则表达式返回true , 剩余的元素不会再执行检测;
                    // 如果没有满足条件的元素，则返回false。
                    // 注意： some() 不会对空数组进行检测。
                    // 注意： some() 不会改变原始数组。
                    return Object.keys(data).some(key => {
                        // indexOf() 返回某个指定的字符在某个字符串中首次出现的位置，如果没有找到就返回-1；
                        // 该方法对大小写敏感！所以之前需要toLowerCase()方法将所有查询到内容变为小写。
                        return String(data[key]).toLowerCase().indexOf(search) > -1
                    })
                })
            }
            return this.tableData
        }
    },
    methods: {
        loginout() {
            this.$message({
                message: '您已成功退出！',
                type: 'warning'
            });
        },
        //会员打卡
        clockIn() {
            if (this.memberId == null || this.memberId === '') {
                this.$alert('请输入正确的会员卡号', '错误提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            } else {
                let that = this;
                $.ajax({
                    url: "/toClockIn",
                    type: "put",
                    data: {"memberId": that.memberId},
                    success: function (data) {
                        if (data === 'success') {
                            that.$alert('祝您健身愉快', '打卡成功', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    that.memberId = '';
                                }
                            });
                        }
                        if (data === 'fail') {
                            that.$alert('请检查用户信息是否输入正确', '打卡失败', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    that.memberId = '';
                                }
                            });
                        }
                        if (data === 'unused') {
                            that.$alert('该会员卡已被禁用', '打卡失败', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    that.memberId = '';
                                }
                            });
                        }
                    }
                });
            }
        },
        //会员离开
        clockOut() {
            if (this.memberId == null || this.memberId === '') {
                this.$alert('请输入正确的会员卡号', '错误提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            } else {
                let that = this;
                $.ajax({
                    url: "/toClockOut",
                    type: "put",
                    data: {"memberId": that.memberId},
                    success: function (data) {
                        if (data == 'success') {
                            that.$alert('欢迎下次光临', '签退成功', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    that.memberId = '';
                                }
                            });
                        }
                        if (data == 'fail') {
                            that.$alert('请检查用户信息是否输入正确', '签退失败', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    that.memberId = '';
                                }
                            });
                        }
                    }
                });
            }
        },

        //会员办理
        onSubmit() {
            let that = this;
            this.form.createId = document.getElementById("managerId").value;
            this.form.id = this.form.phone;
            var datas = JSON.stringify(this.form);
            $.ajax({
                url: "/toAddMember",
                type: "put",
                data: datas,
                contentType: "application/json",
                success: function (data) {
                    if (data == 'success') {
                        that.$alert('欢迎加入keepGym', '办理成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                                that.reset();
                                that.toSearchAllMembers();
                            }
                        });
                    }
                    if (data == 'fail') {
                        that.$alert('请检查用户信息是否输入正确', '办理失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                                that.reset();
                            }
                        });
                    }
                }
            });
        },
        //查找全部会员信息
        toSearchAllMembers() {
            let that = this;
            $.ajax({
                url: "/toSearchAllMembers",
                type: "get",
                data: {},
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    if (data != null) {
                        that.tableData = data;
                    }

                }
            })
        },

        reset() {
            this.form.id = '';
            this.form.username = '';
            this.form.gender = '';
            this.form.phone = '';
            this.form.email = '';
            this.form.address = '';
            this.form.level = '';
            this.form.createId = '';
        },
        //根据id查找要修改的会员信息
        editMemberInfo(memberId) {
            let that = this;
            this.memberInfo.id = memberId;
            $.ajax({
                url: "/toSearchMemberInfo",
                type: "get",
                data: {"memberId": memberId},
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        that.memberInfo = data;
                        that.dialogFormVisible = true;
                    }
                    if (data == null) {
                        that.$alert('请刷新重试', '编辑失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                                window.location.href = location.href;
                            }
                        });
                    }

                }
            })
        },
        //查找会员信息
        searchMemberInfo() {
            let that = this;
            $.ajax({
                url: "/toSearchMemberInfo",
                type: "get",
                data: {"memberId": that.memberId},
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        that.memberInfo = data;
                        that.renewDialog = true;
                    }
                    if (data == null) {
                        that.$alert('请刷新重试', '续费失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                                window.location.href = location.href;
                            }
                        });
                    }

                }
            })
        },
        //修改会员信息
        alterMemberInfo() {
            let that = this;
            var datas = JSON.stringify(that.memberInfo);
            $.ajax({
                url: "/toAlterMemberInfo",
                type: "post",
                data: datas,
                contentType: "application/json",
                success: function (data) {
                    if (data === 'success') {
                        that.$alert('', '修改成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                                that.dialogFormVisible = false;
                                that.toSearchAllMembers();
                            }
                        });
                    }
                    if (data === 'fail') {
                        that.$alert('请稍后再试', '修改失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                                that.dialogFormVisible = false;
                            }
                        });
                    }
                }
            });
        },

        //提醒是否删除会员
        confirmDelete(memberId) {
            let that = this;
            this.$confirm('你确定要删除该会员吗？', '删除提醒', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                that.deleteMember(memberId);
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消删除'
                });
            });
        },
        //确定删除会员
        deleteMember(memberId) {
            let that = this;
            $.ajax({
                url: "/toDeleteMember",
                type: "delete",
                data: {"memberId": memberId},
                success: function (data) {
                    if (data === 'success') {
                        that.$alert('', '删除成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                               that.toSearchAllMembers();
                            }
                        });
                    }
                    if (data === 'fail') {
                        that.$alert('请稍后再试', '删除失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                                window.location.href = location.href;
                            }
                        });
                    }
                }
            });
        },

        //检查续费时会员id是否为空
        checkRenewMemberId() {
            if (this.memberId == null || this.memberId === '') {
                this.$alert('请输入正确的会员卡号', '错误提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            } else {
                this.renewDialog = true;
            }
        },

        //会员续费
        renewTime() {
            if (this.memberId == null || this.memberId === '') {
                this.$alert('请输入正确的会员卡号', '错误提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            } else {
                let that = this;
                this.form.createId = document.getElementById("managerId").value;
                $.ajax({
                    url: "/toRenewMember",
                    type: "post",
                    data: {
                        "memberId": that.memberId,
                        "createId": that.form.createId,
                        "level": that.renew.level,
                        "time": that.renew.time
                    },
                    success: function (data) {
                        if (data == 'success') {
                            that.$alert('', '续费成功', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    that.renewDialog = false;
                                    that.toSearchAllMembers();
                                }
                            });
                        }
                        if (data == 'fail') {
                            that.$alert('请稍后再试', '续费失败', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    that.renewDialog = false;
                                }
                            });
                        }
                    }
                });
            }
        },

        //检查停卡时会员id是否为空
        checkStopCardMemberId() {
            if (this.memberId == null || this.memberId === '') {
                this.$alert('请输入正确的会员卡号', '错误提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            } else {
                this.stopCardDialog = true;
            }
        },

        //会员停卡
        stopCard1() {
            let that = this;
            this.form.createId = document.getElementById("managerId").value;
            $.ajax({
                url: "/toStopCard",
                type: "post",
                data: {
                    "memberId": that.memberId,
                    "createId": that.form.createId,
                    "time": that.stopCard.stopTime,
                },
                success: function (data) {
                    if (data === 'success') {
                        that.$alert('', '停卡成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                                that.stopCardDialog = false;
                                that.toSearchAllMembers();
                            }
                        });
                    }
                    if (data === 'fail') {
                        that.$alert('请稍后再试', '停卡失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                                that.stopCardDialog = false;
                            }
                        });
                    }
                }
            });
        },

    }
})
;