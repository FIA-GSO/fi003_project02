$(document).ready(function() {
    $("#saveBtn").click(function(){
        $('#loading').show();
        //TODO sammeln zeilen die sich verändert haben
        //TODO jede geänderte zeile abschicken einzeln
        
        $('#loading').hide();
    }); 

    for (let index = 0; index < 28; index++) {
        $("#gradeInput").append('<input type="text" class="form-control" aria-label="Text input" id="inputField' + index + '">');
    }
});