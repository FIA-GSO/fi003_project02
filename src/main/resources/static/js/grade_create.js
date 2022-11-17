$(document).ready(function() {
    $("#saveBtn").click(function(){
        $('#loading').show();
        //sammeln zeilen die sich verändert haben
        var classId = $("#classId").text();
        var teacherId = $("#teacherId").text();
        var lessonId = $("#lessonId").text();
        var elementList = $('[id=gradeInput]');
        var studentList = $('[id=studentId]');
        for (let index = 0; index < elementList.length; index++) {
            const element = elementList[index];
            var listElements = $(element).children('.gradeSingleInput');
            var student_id = $(studentId[index]).text();
            var valueList = [];
            for (let index = 0; index < listElements.length; index++) {
                const element = listElements[index];
                var value = $(element).val();
                if(value !== ''){
                    var obj = {
                        'description' : '',
                        'lessonId' : lessonId,
                        'note' : value,
                        'studentId' : student_id,
                        'teacherId' : teacherId
                    }
                    valueList.push(obj)
                }
            }
            console.log(valueList)
            axios.post('/grade/records/' + lessonId + '/' + student_id, valueList)
            .then(function (response) {
                console.log(response)
            })
            .catch(function (error) {
                console.log(error);
            });
        }
        $('#loading').hide();
    }); 
    var elementList = $('[id=gradeInput]');
    for (let index = 0; index < elementList.length; index++) {
        const element = elementList[index];
        for (let index = 0; index < 28; index++) {
            $(element).append('<input type="text" class="form-control gradeSingleInput" style="width: 3em;" aria-label="Text input" id="inputField' + index + '">');
        }
    }
});