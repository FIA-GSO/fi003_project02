$(document).ready(function() {
    $("#saveBtn").click(function(){
        $('#loading').show();
        //collects data from cells that changed
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
                        'teacherId' : teacherId,
                        'pos': index
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
    //Creates 28 input fields in a row, each with custom id for each element with id 'gradeInput'
    var elementList = $('[id=gradeInput]');
    for (let index = 0; index < elementList.length; index++) {
        const element = elementList[index];
        for (let i = 0; i < 28; i++) {
            $(element).append('<input type="text" class="form-control gradeSingleInput" style="width: 3em;" aria-label="Text input" id="inputField' + i + '">');
        }
        var lessonId = $("#lessonId").text();
        axios.get('/grade/records/' + lessonId + '/' + (index + 1))
        .then(function (response) {
            var list = response.data
            for (let d = 0; d < list.length; d++) {
                const el = list[d];
                $("[id=inputField" + el.pos + "]:eq(" + index + ")").val(el.note)
            }
        })
        .catch(function (error) {
            console.log(error);
        });
    }
});

function studentPhotoClick(studentId, studentPhoto) {
    if (studentPhoto === null){
        document.getElementById( "student" + studentId ).style.display = "none";
        document.getElementById( "photoUpload" + studentId ).style.display = "inline";
    }
}