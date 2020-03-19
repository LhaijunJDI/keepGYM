new Vue({
    el: '#app',
    data: function () {
        return {
            courseList: [{}],
            coachList:[{}],
            courseInfo: {
                courseId: '',
                name: '',
                type: '',
                num: '',
                startTime: '',
                endTime: '',
                coachId: '',
                content: '',
                picSrc: '',
                strStartTime: '',
                strEndTime: ''
            },
            dialogFormVisible: false,
            showPicDialog: false,
            pictureSrc: '',
            addCourseDialog: false,
            search: '',
            managerInfo: '',
            currentCourseId: '',
            currentCoachId: '',
            orderCourseDialog: false,
            orderCoachDialog: false,
            orderInfo: {
                memberId: '',
                num: '',
            },

        }
    },
    created() {
        this.toSearchAllCourse();
        this.toSearchManagerName();
        this.toSearchAllCoach();
    },
    computed: {
        // 模糊搜索
        tables() {
            const search = this.search
            if (search) {
                // filter() 方法创建一个新的数组，新数组中的元素是通过检查指定数组中符合条件的所有元素。
                // 注意： filter() 不会对空数组进行检测。
                // 注意： filter() 不会改变原始数组。
                return this.courseList.filter(data => {
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
            return this.courseList
        }
    },
    methods: {
        toSearchManagerName() {
            let that = this;
            var managerId = document.getElementById("managerId").value;
            $.ajax({
                url: "/toSearchManagerInfo",
                type: "get",
                data: {"managerId": managerId},
                success: function (data) {
                    if (data != null) {
                        that.managerInfo = data;
                    }
                }
            })
        },

        toSearchAllCoach(){
            let that = this;
            $.ajax({
                url: "/toSearchAllCoach",
                type: "get",
                data: {},
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        that.coachList = data;
                    }
                    if (data == null) {
                        that.$alert('请刷新后重再试', '教练列表显示失败', {
                            confirmButtonText: '确定',
                            callback: action => {

                            }
                        });
                    }

                }
            })
        },

        toSearchAllCourse() {
            let that = this;
            $.ajax({
                url: "/toSearchAllCourse",
                type: "get",
                data: {},
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        that.courseList = data;
                    }
                    if (data == null) {
                        that.$alert('请刷新后重再试', '课程显示失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    }

                }
            })
        },
        //根据id查找要修改的课程信息
        editCourseInfo(courseId) {
            let that = this;
            this.courseInfo.id = courseId;
            $.ajax({
                url: "/toSearchCourseInfo",
                type: "get",
                data: {"courseId": courseId},
                dataType: "json",
                success: function (data) {

                    if (data != null) {
                        that.courseInfo = data;
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

        alterCourseInfo() {
            let that = this;
            var datas = JSON.stringify(that.courseInfo);
            $.ajax({
                url: "/toAlterCourseInfo",
                type: "post",
                data: datas,
                contentType: "application/json",
                success: function (data) {
                    if (data == 'success') {
                        that.$alert('', '修改成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                                window.location.href = location.href;
                            }
                        });
                    }
                    if (data == 'fail') {
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

        confirmDelete(courseId) {
            let that = this;
            this.$confirm('你确定要删除该课程吗？', '删除提醒', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                that.deleteCourse(courseId);
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消删除'
                });

            });
        },

        deleteCourse(courseId) {
            let that = this;
            $.ajax({
                url: "/toDeleteCourse",
                type: "delete",
                data: {"courseId": courseId},
                success: function (data) {
                    if (data == 'success') {
                        that.$alert('', '删除成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                                window.location.href = location.href;
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

        showPic(picSrc) {
            console.log(picSrc);
            this.pictureSrc = picSrc;
            this.showPicDialog = true;
        },

        addCourse() {
            let that = this;
            var datas = JSON.stringify(this.courseInfo);
            $.ajax({
                url: "/toAddCourse",
                type: "put",
                data: datas,
                contentType:"application/json",
                success: function (data) {
                    if (data == 'success') {
                        that.$alert('', '发布成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                                window.location.href = location.href;
                            }
                        });
                    }
                    if (data == 'fail') {
                        that.$alert('请稍后再试', '发布失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                                window.location.href = location.href;
                            }
                        });
                    }
                }
            });
        },

        resetCourseInfo1() {
            this.courseInfo.courseId = '';
            this.courseInfo.name = '';
            this.courseInfo.type = '';
            this.courseInfo.num = '';
            this.courseInfo.startTime = '';
            this.courseInfo.endTime = '';
            this.courseInfo.coachId = '';
            this.courseInfo.content = '';
            this.courseInfo.picSrc = '';
            this.courseInfo.strStartTime = '';
            this.courseInfo.strEndTime = '';
        },
        resetCourseInfo() {
            this.resetCourseInfo1();
            this.courseInfo.picSrc = "members/assets/images/course/";
            this.addCourseDialog = true;
        },

        orderCourseDia(id) {
            this.currentCourseId = id;
            this.orderCourseDialog = true;
        },
        orderCoach(id) {
            this.currentCoachId = id;
            this.orderCoachDialog = true;
        },
        buyCoachClass() {
            let that = this;
            var managerId = document.getElementById("managerId").value;
            $.ajax({
                url: "/toBuyCoachClass",
                type: "put",
                data: {
                    "coachId": that.currentCoachId,
                    "memberId": that.orderInfo.memberId,
                    "num": that.orderInfo.num,
                    "managerId": managerId
                },
                success: function (data) {
                    if (data === 'success') {
                        that.$alert('', '购买成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                                window.location.href = location.href;
                            }
                        });
                    }
                    if (data === 'fail') {
                        that.$alert('请确认信息是否正确', '购买失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                                window.location.href = location.href;
                            }
                        });
                    }
                }
            });
        },
        orderCourse() {
            let that = this;
            $.ajax({
                url: "/toOrderCourse",
                type: "put",
                data: {
                   "courseId":that.currentCourseId,"memberId":that.orderInfo.memberId
                },
                success: function (data) {
                    if (data === 'success') {
                        that.$alert('', '预约成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                                window.location.href = location.href;
                            }
                        });
                    }
                    if (data === 'fail') {
                        that.$alert('请确认信息是否正确', '预约失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                                window.location.href = location.href;
                            }
                        });
                    }
                    if (data === 'already') {
                        that.$alert('该会员已预约了此课程', '预约失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                                that.orderCourseDialog = false;
                            }
                        });
                    }

                }
            });
        },
    },
});