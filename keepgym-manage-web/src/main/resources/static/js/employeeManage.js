new Vue({
    el: '#app',
    data: function () {
        return {
            managerList: [{}],
            coachList: [{}],
            search: '',
            showPicDialog: false,
            pictureSrc: '',
            managerInfo: {
                id: '',
                name: '',
                gender: '',
                email: '',
                phone: '',
                address: '',
                bankCard: '',
                position: '',
            },
            coachInfo: {
                id: '',
                name: '',
                gender: '',
                email: '',
                phone: '',
                address: '',
                bankCard: '',
                type: '',
                content: '',
                coachPic: '',
                teachTime: '',
                graduSchool: '',
            },
            alterManagerDialog: false,
            changePowerDialog: false,
            addManagerDialog: false,
            addCoachDialog: false,
            choiceEmployeeDialog: false,
            alterCoachDialog: false,
        }
    },
    computed: {
        // 模糊搜索
        managers() {
            const search = this.search
            if (search) {
                // filter() 方法创建一个新的数组，新数组中的元素是通过检查指定数组中符合条件的所有元素。
                // 注意： filter() 不会对空数组进行检测。
                // 注意： filter() 不会改变原始数组。
                return this.managerList.filter(data => {
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
            return this.managerList
        },
        coaches() {
            const search = this.search
            if (search) {
                // filter() 方法创建一个新的数组，新数组中的元素是通过检查指定数组中符合条件的所有元素。
                // 注意： filter() 不会对空数组进行检测。
                // 注意： filter() 不会改变原始数组。
                return this.coachList.filter(data => {
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
            return this.coachList
        },
    },
    created() {
        this.toSearchAllManager();
        this.toSearchAllCoach();
    },

    methods: {
        toSearchAllManager() {
            let that = this;
            $.ajax({
                url: "/toSearchAllManager",
                type: "get",
                data: {},
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    if (data != null) {
                        that.managerList = data;
                    }
                    if (data == null) {
                        that.$alert('请刷新后重再试', '管理员列表显示失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    }

                }
            })
        },

        toSearchAllCoach() {
            let that = this;
            $.ajax({
                url: "/toSearchAllCoach",
                type: "get",
                data: {},
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    if (data != null) {
                        that.coachList = data;
                    }
                    if (data == null) {
                        that.$alert('请刷新后重试', '教练列表显示失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    }
                }
            })
        },
        //显示照片
        showPic(picSrc) {
            this.pictureSrc = picSrc;
            this.showPicDialog = true;
        },

        editManagerInfo(managerId) {
            let that = this;
            this.managerInfo.id = managerId;
            $.ajax({
                url: "/toSearchManagerInfo",
                type: "get",
                data: {"managerId": managerId},
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        that.managerInfo = data;
                        that.alterManagerDialog = true;
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
        alterManagerInfo() {
            let that = this;
            var datas = JSON.stringify(that.managerInfo);
            $.ajax({
                url: "/toAlterManagerInfo",
                type: "post",
                data: datas,
                contentType: "application/json",
                success: function (data) {
                    if (data === 'success') {
                        that.$alert('', '修改成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                               that.toSearchAllManager();
                               that.alterManagerDialog = false;
                            }
                        });
                    }
                    if (data === 'fail') {
                        that.$alert('请稍后再试', '修改失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                                that.alterManagerDialog = false;
                            }
                        });
                    }
                }
            });
        },

        editCoachInfo(coachId) {
            let that = this;
            this.coachInfo.id = coachId;
            $.ajax({
                url: "/toSearchCoachInfo",
                type: "get",
                data: {"coachId": coachId},
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        that.coachInfo = data;
                        that.alterCoachDialog = true;
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
        alterCoachInfo() {
            let that = this;
            var datas = JSON.stringify(that.coachInfo);
            $.ajax({
                url: "/toAlterCoachInfo",
                type: "post",
                data: datas,
                contentType: "application/json",
                success: function (data) {
                    if (data == 'success') {
                        that.$alert('', '修改成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                               that.toSearchAllCoach();
                               that.alterCoachDialog = false;
                            }
                        });
                    }
                    if (data == 'fail') {
                        that.$alert('请稍后再试', '修改失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                                that.alterCoachDialog = false;
                            }
                        });
                    }
                }
            });
        },

        //删除提示
        confirmDeleteManager(id) {
            let that = this;
            this.$confirm('你确定要删除该管理员吗？', '删除提醒', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                that.deleteManager(id);
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消删除'
                });

            });
        },
        confirmDeleteCoach(id) {
            let that = this;
            this.$confirm('你确定要删除该教练吗？', '删除提醒', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                that.deleteCoach(id);
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消删除'
                });

            });
        },
        //删除管理员
        deleteManager(managerId) {
            let that = this;
            $.ajax({
                url: "/toDeleteManager",
                type: "delete",
                data: {"managerId": managerId},
                success: function (data) {
                    if (data == 'success') {
                        that.$alert('', '删除成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                              that.toSearchAllManager();
                            }
                        });
                    }
                    if (data == 'fail') {
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

        //删除教练
        deleteCoach(coachId) {
            let that = this;
            $.ajax({
                url: "/toDeleteCoach",
                type: "delete",
                data: {"coachId": coachId},
                success: function (data) {
                    if (data == 'success') {
                        that.$alert('', '删除成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                                that.toSearchAllCoach();
                            }
                        });
                    }
                    if (data == 'fail') {
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

        resetManagerInfo() {
            this.managerInfo = {};
            this.addManagerDialog = true;
        },
        resetCoachInfo() {
            this.coachInfo = {};
            this.coachInfo.pictureSrc = "members/assets/images/coach/";
            this.addCoachDialog = true;
        },
        addManager() {
            let that = this;
            var datas = JSON.stringify(this.managerInfo);
            $.ajax({
                url: "/toAddManager",
                type: "put",
                data: datas,
                contentType: "application/json",
                success: function (data) {
                    if (data === 'success') {
                        that.$alert('', '添加员工成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                                that.toSearchAllManager();
                                that.addManagerDialog = false;
                            }
                        });
                    }
                    if (data === 'fail') {
                        that.$alert('请稍后再试', '添加失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                                that.addManagerDialog = false;
                            }
                        });
                    }
                }
            });
        },
        addCoach() {
            let that = this;
            var datas = JSON.stringify(this.coachInfo);
            $.ajax({
                url: "/toAddCoach",
                type: "put",
                data: datas,
                contentType: "application/json",
                success: function (data) {
                    if (data == 'success') {
                        that.$alert('', '添加员工成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                                that.toSearchAllCoach();
                                that.addCoachDialog = false;
                            }
                        });
                    }
                    if (data == 'fail') {
                        that.$alert('请稍后再试', '添加失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                                that.addCoachDialog = false;
                            }
                        });
                    }
                }
            });
        },


    }
})