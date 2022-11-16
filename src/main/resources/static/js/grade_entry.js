$(document).ready(function() {
    $("#saveBtn").click(function(){
        var host = '';
        var classe = $( "#inputClass" ).val();
        var teacher = $( "#inputTeacher" ).val();
        var course = $("#inputCourse").val();
        var startDate = new Date($( "#inputStartDate" ).val());
        var endDate = new Date($("#inputEndDate").val());
        var roomCode = $("#inputRoomCode").val();
        var description = $("#inputDescription").val();
        axios.post(host + '/grade/create', {
            "teacherId": teacher,
            "courseId": course,
            "classId": classe,
            "roomCode": roomCode,
            "startDatetime": startDate.getTime(),
            "endDatetime": endDate.getTime(),
            "description": description
        })
        .then(function (response) {
            window.location.href = "/";
        })
        .catch(function (error) {
            console.log(error);
        });
    }); 

    $("#closeBtn").click(function(){
        window.location.href = "/class";
    });
});