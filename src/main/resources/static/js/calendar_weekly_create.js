$(document).ready(function() {
    for (let index = 1; index <= 5; index++) {
        $("#tableArea").append('<div id="day' + index + '"> <table id="entryTable1" class="table" data-toggle="table" data-pagination="true" data-url="" data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-search="true" data-show-pagination-switch="true" data-id-field="class_id" data-page-list="[10, 25, 50, 100, ALL]" data-show-footer="false"> <thead class="table-light"> <tr> <div class="card text-center text-bg-secondary"> <div class="card-header"> <strong id="dayTag">Montag</strong> </div> </div> </tr> <tr> <th scope="col">Stunde</th> <th scope="col">Klasse</th> <th scope="col">Fach</th> <th scope="col">Raum</th> <th scope="col">Zeit von</th> <th scope="col">Zeit bis</th> </tr> </thead> <tbody> <!--lesson 1--> <tr id="inputArea"> <th scope="row" id="numLesson">1</th> <td> <div class="input-group mb-3" id="calendarInputClass1"> <input type="text" class="form-control"> </div> </td> <td> <div class="input-group mb-3" id="calendarInputCourse1"> <input type="text" class="form-control"> </div> </td> <td> <div class="input-group mb-3" id="calendarInputRoom1"> <input type="text" class="form-control"> </div> </td> <td> <div class="input-group mb-3" id="calendarInputTimeFrom1"> <input type="text" class="form-control"> </div> </td> <td> <div class="input-group mb-3" id="calendarInputTimeUntil1"> <input type="text" class="form-control"> </div> </td> </tr> <tr> <table id="weeklyTable"> <thead class="table-light"> <tr><strong>Notizen</strong></tr> </thead> <tbody> <tr> <div class="input-group mb-3" id="calendarInputNotes1"> <textarea class="form-control" id="exampleFormControlTextarea1" rows="1"></textarea> </div> </tr> </tbody> </table> </tr> </tbody> </table> </div>')
        switch (index) {
            case 1:
                $("#day" + index + " #dayTag").text("Montag");
                break;
            case 2:
                $("#day" + index + " #dayTag").text("Dienstag");
                break;
            case 3:
                $("#day" + index + " #dayTag").text("Mittwoch");
                break;
            case 4:
                $("#day" + index + " #dayTag").text("Donnerstag");
                break;
            case 5:
                $("#day" + index + " #dayTag").text("Freitag");
                break;
        }
        for (let i = 2; i <= 12; i++) {
            $("#day" + index).append('<table id="entryTable"' + i + ' class="table" data-toggle="table" data-pagination="true" data-url="" data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-search="true" data-show-pagination-switch="true" data-id-field="class_id" data-page-list="[10, 25, 50, 100, ALL]" data-show-footer="false"> <thead class="table-light"> <tr>  </tr> <tr> <th scope="col">Stunde</th> <th scope="col">Klasse</th> <th scope="col">Fach</th> <th scope="col">Raum</th> <th scope="col">Zeit von</th> <th scope="col">Zeit bis</th> </tr> </thead> <tbody> <!--lesson 1--> <tr id="inputArea"> <th scope="row" id="numLesson">' + i + '</th> <td> <div class="input-group mb-3" id="calendarInputClass1"> <input type="text" class="form-control"> </div> </td> <td> <div class="input-group mb-3" id="calendarInputCourse1"> <input type="text" class="form-control"> </div> </td> <td> <div class="input-group mb-3" id="calendarInputRoom1"> <input type="text" class="form-control"> </div> </td> <td> <div class="input-group mb-3" id="calendarInputTimeFrom1"> <input type="text" class="form-control"> </div> </td> <td> <div class="input-group mb-3" id="calendarInputTimeUntil1"> <input type="text" class="form-control"> </div> </td> </tr> <tr> </tr></tbody></table>')
            $("#day" + index).append('<strong>Notizen</strong>')
            $("#day" + index).append('<div class="input-group mb-3" id="calendarInputNotes' + i + '"> <textarea class="form-control" id="exampleFormControlTextarea1" rows="1"></textarea> </div>')
        }
    }

    $("#saveBtn").click(function(){
        $('#loading').show();
        
        $('#loading').hide();
    }); 
});