$(document).ready(function() {
    $("#saveBtn").click(function(){
        var host = '';
        var class = $( "#inputClass" ).val();
        var teacher = $( "#inputTeacher" ).val();
        var course = $("#inputCourse").val();
        var startDate = $( "#inputStartDate" ).val();
        var endDate = $("#inputEndDate").val();
        axios.post(host + '/teacher/login', {
            "email": email,
            "password": password
        })
        .then(function (response) {
            window.location.href = "/";
        })
        .catch(function (error) {
            console.log(error);
        });
    }); 
});