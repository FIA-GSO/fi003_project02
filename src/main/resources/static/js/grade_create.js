$(document).ready(function() {
    $("#saveBtn").click(function(){
        $('#loading').show();
        //TODO sammeln zeilen die sich verändert haben
        //TODO jede geänderte zeile abschicken einzeln
        
        $('#loading').hide();
    }); 
    var elementList = $('[id=gradeInput]');
    for (let index = 0; index < elementList.length; index++) {
        const element = elementList[index];
        for (let index = 0; index < 28; index++) {
            $(element).append('<input type="text" class="form-control" aria-label="Text input" id="inputField' + index + '">');
        }
    }
});